package me.darksoul.Engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.util.Optional;

public class Display {
    private static long window;
    private static int width;
    private static int height;
    private static String title;

    private static double lastFrameTime;
    private static double deltaTime;
    private static boolean isInitialized = false;

    // Create the display window, optimized
    public static void create(int width, int height, String title, Optional<Long> fullScreen) {
        if (isInitialized) {
            ErrorLogger.error("Display", "Display has already been initialized.");
            return;
        }

        Display.width = width;
        Display.height = height;
        Display.title = title;

        if (!GLFW.glfwInit()) {
            ErrorLogger.error("Display", "Unable to initialize GLFW.");
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Configure GLFW window hints
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // Fullscreen logic
        long monitor = fullScreen.orElse(MemoryUtil.NULL);
        window = GLFW.glfwCreateWindow(width, height, title, monitor, MemoryUtil.NULL);

        if (window == MemoryUtil.NULL) {
            ErrorLogger.error("Display", "Failed to create the GLFW window.");
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwSetWindowPos(window, 100, 100);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);  // Enable V-Sync
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        lastFrameTime = getCurrentTime();
        isInitialized = true;
    }

    // Destroy the display window with improved cleanup
    public static void destroy() {
        if (window != MemoryUtil.NULL) {
            GLFW.glfwDestroyWindow(window);
            window = MemoryUtil.NULL;
        }
        GLFW.glfwTerminate();
        isInitialized = false;
    }

    // Check whether the window should close
    public static boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    // Update the display window and calculate delta time
    public static void update() {
        if (window == MemoryUtil.NULL || !isInitialized) {
            ErrorLogger.error("Display", "Window is not created or already destroyed.");
            return;
        }

        GLFW.glfwPollEvents();  // Poll events once per frame (optimized for reduced calls)

        // Swap buffers and calculate delta time
        GLFW.glfwSwapBuffers(window);
        double currentFrameTime = getCurrentTime();
        deltaTime = currentFrameTime - lastFrameTime;
        lastFrameTime = currentFrameTime;
    }

    // Get the delta time for frame updates
    public static float getDeltaTime() {
        return (float) deltaTime;
    }

    // Clear the screen with a given color
    public static void clear(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    // Retrieve the current time from GLFW (optimized)
    private static double getCurrentTime() {
        return GLFW.glfwGetTime();
    }

    // Get the window handle
    public static long getWindow() {
        return window;
    }
}
