package me.darksoul.Engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorEnterCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private static long window;

    private static boolean[] keys = new boolean[GLFW_KEY_LAST];
    private static boolean[] keysPressed = new boolean[GLFW_KEY_LAST];

    private static boolean[] mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;

    public static void init(long windowHandle) {
        window = windowHandle;

        GLFW.glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW_PRESS) {
                    keys[key] = true;
                    keysPressed[key] = true;
                } else if (action == GLFW_RELEASE) {
                    keys[key] = false;
                }
            }
        });

        GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                if (action == GLFW_PRESS) {
                    mouseButtons[button] = true;
                } else if (action == GLFW_RELEASE) {
                    mouseButtons[button] = false;
                }
            }
        });

        GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        });

        GLFW.glfwSetCursorEnterCallback(window, new GLFWCursorEnterCallback() {
            @Override
            public void invoke(long window, boolean entered) {

            }
        });
    }

    public static void update() {
        for (int i = 0; i < keysPressed.length; i++) {
            if (keysPressed[i]) {
                keysPressed[i] = false;
            }
        }
    }

    public static boolean isKeyPressed(int key) {
        return keys[key];
    }

    public static boolean isKeyJustPressed(int key) {
        return keysPressed[key];
    }

    public static boolean isMouseButtonPressed(int button) {
        return mouseButtons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static void cleanup() {
        GLFW.glfwSetKeyCallback(window, null);
        GLFW.glfwSetMouseButtonCallback(window, null);
        GLFW.glfwSetCursorPosCallback(window, null);
        GLFW.glfwSetCursorEnterCallback(window, null);
    }
}
