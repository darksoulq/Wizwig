package me.darksoul;

import me.darksoul.Engine.Display;

public class Main {
    private static float deltaTime;

    public static void main(String[] args) {
        Display.create(800, 600, "Wizwig Test");

        while (!Display.shouldClose()) {
            Display.clear(0.2f, 0.3f, 0.3f, 1.0f);

            Display.update();
            deltaTime = Display.getDeltaTime();

        }
        Display.destroy();
    }
}
