package me.darksoul.Engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

public class Display {
    private static long window;
    private static int width;
    private static int height;
    private static String title;

    private static double lastFrameTime;
    private static double deltaTime;

    public static void create(int width, int height, String title, long fullScreen) {
        Display.width = width;
        Display.height = height;
        Display.title = title;

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        window = GLFW.glfwCreateWindow(width, height, title, fullScreen, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        GLFW.glfwSetWindowPos(window, 100, 100);
        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwSwapInterval(1);  // Enable V-Sync
        GLFW.glfwShowWindow(window);

        GL.createCapabilities();

        lastFrameTime = getCurrentTime();
    }

    public static void destroy() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public static boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public static void update() {
        GLFW.glfwPollEvents();

        GLFW.glfwSwapBuffers(window);

        double currentFrameTime = getCurrentTime();
        deltaTime = currentFrameTime - lastFrameTime;
        lastFrameTime = currentFrameTime;
    }

    public static float getDeltaTime() {
        return (float) deltaTime;
    }

    public static void clear(float r, float g, float b, float a) {
        GL11.glClearColor(r, g, b, a);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private static double getCurrentTime() {
        return GLFW.glfwGetTime();
    }

    public static long getWindow() {
        return window;
    }
}
