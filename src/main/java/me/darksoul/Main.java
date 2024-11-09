package me.darksoul;

import me.darksoul.Engine.Display;
import me.darksoul.Engine.Input;

import static org.lwjgl.glfw.GLFW.*;

public class Main {
    public static void main(String[] args) {
        Display.create(800, 600, "LWJGL 3 Input Handling");

        Input.init(Display.getWindow());

        while (!Display.shouldClose()) {
            Display.clear(0.2f, 0.3f, 0.3f, 1.0f);

            if (Input.isKeyJustPressed(GLFW_KEY_W)) {
                System.out.println("W key was just pressed");
            }

            if (Input.isMouseButtonPressed(GLFW_MOUSE_BUTTON_LEFT)) {
                System.out.println("Left mouse button is pressed");
            }

            Input.update();
            Display.update();
        }

        Input.cleanup();
        Display.destroy();
    }
}

