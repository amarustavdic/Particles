package com.nowhereland.gui;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GUI {
    private long window;
    private int width = 800;
    private int height = 600;

    public void run() {
        init();
        loop();
        cleanup();
    }

    private void init() {
        // Setup error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create window
        window = glfwCreateWindow(width, height, "LWJGL Circle", NULL, NULL);
        if (window == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        // Center window
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities(); // Initialize OpenGL bindings

        setupViewport();
    }

    private void setupViewport() {
        glViewport(0, 0, width, height); // Set OpenGL viewport
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, 0, height, -1, 1); // Ensures correct aspect ratio
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void loop() {
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);

            glColor3f(0.0f, 1.0f, 0.0f); // Set color (Green)
            drawCircle((float) width / 2, (float) height / 2);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    private void drawCircle(float x, float y) {
        float aspectRatio = (float) width / height;

        glBegin(GL_TRIANGLE_FAN);
        glVertex2f(x, y); // Center of the circle

        for (int i = 0; i <= 50; i++) {
            double angle = Math.PI * 2 * i / 50;
            float dx = (float) Math.cos(angle) * (float) 100;
            float dy = (float) Math.sin(angle) * (float) 100 * aspectRatio; // Aspect ratio fix
            glVertex2f(x + dx, y + dy);
        }

        glEnd();
    }

    private void cleanup() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static void main(String[] args) {
        new GUI().run();
    }

}
