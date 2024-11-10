package me.darksoul;

import me.darksoul.Engine.*;
import me.darksoul.Engine.Events.EventManager;
import me.darksoul.Engine.Events.EventPriority;
import me.darksoul.Tests.Config;
import me.darksoul.Tests.InitEvent;
import me.darksoul.Tests.InitEventListener;
import org.lwjgl.openal.ALC;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.util.Optional;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;


public class Main {
    public static void main(String[] args) throws Exception {
        // Event
        InitEventListener initEventListener = new InitEventListener();
        InitEventListener initEventListener1 = new InitEventListener();
        EventManager.registerListener(InitEvent.class, initEventListener, EventPriority.NORMAL);
        EventManager.registerListener(InitEvent.class, initEventListener1, EventPriority.NORMAL);

        // Sound
        Sound sound = new Sound(Thread.currentThread().getContextClassLoader().getResource("drum.wav").getFile(),
                false,
                0.0f,
                0.0f,
                0.0f);

        // Resource Manager
        ResourceManager.addResource(0, new Config());
        ResourceManager.addResource(1, sound);

        Display.create(ResourceManager.getResource(0, Config.class).Width,
                ResourceManager.getResource(0, Config.class).Height,
                ResourceManager.getResource(0, Config.class).title,
                Optional.of(MemoryUtil.NULL));

        Input.init(Display.getWindow());
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // Dispatch Event
        EventManager.dispatchEvent(new InitEvent("Hello World!"));

        // Play Sound
        ResourceManager.getResource(1, Sound.class).play();

        // Loop
        while (!Display.shouldClose()) {
            Display.clear(0.2f, 0.3f, 0.3f, 1.0f);

            if (Input.isKeyJustPressed(GLFW_KEY_ESCAPE)) {
                break;
            }

            Input.update();
            Display.update();
        }

        // CleanUp
        Input.cleanup();
        ALC.destroy();
        ResourceManager.getResource(1, Sound.class).cleanup();
        Display.destroy();
    }
}

