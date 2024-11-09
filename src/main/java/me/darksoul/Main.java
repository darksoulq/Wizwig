package me.darksoul;

import me.darksoul.Engine.Display;
import me.darksoul.Engine.Events.EventManager;
import me.darksoul.Engine.Events.EventPriority;
import me.darksoul.Engine.Input;
import me.darksoul.Engine.ResourceManager;
import me.darksoul.Tests.Config;
import me.darksoul.Tests.InitEvent;
import me.darksoul.Tests.InitEventListener;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    public static void main(String[] args) {
        // Event
        InitEventListener initEventListener = new InitEventListener();
        InitEventListener initEventListener1 = new InitEventListener();
        EventManager.registerListener(InitEvent.class, initEventListener, EventPriority.NORMAL);
        EventManager.registerListener(InitEvent.class, initEventListener1, EventPriority.NORMAL);

        // Resource Manager
        ResourceManager.addResource(0, new Config());

        Display.create(ResourceManager.getResource(0, Config.class).Width,
                ResourceManager.getResource(0, Config.class).Height,
                ResourceManager.getResource(0, Config.class).title,
                ResourceManager.getResource(0, Config.class).fullscreen);

        Input.init(Display.getWindow());

        // Dispatch Event
        EventManager.dispatchEvent(new InitEvent("Hello World!"));


        while (!Display.shouldClose()) {
            Display.clear(0.2f, 0.3f, 0.3f, 1.0f);

            if (Input.isKeyJustPressed(GLFW_KEY_ESCAPE)) {
                break;
            }

            Input.update();
            Display.update();
        }

        Input.cleanup();
        Display.destroy();
    }
}

