package com.nowhereland.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*; // Import OpenGL bindings

public class GUI implements Runnable {

    private int width = 800;
    private int height = 600;

    private Thread thread;

    public GUI() {

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW.");
        }

        // Set OpenGL version to 3.3 and use core profile
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create a window
        long window = glfwCreateWindow(width, height, "LearnOpenGL", 0, 0);
        if (window == 0) {
            System.out.println("Failed to create the GLFW window.");
            glfwTerminate();
            return;
        }

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // This loads OpenGL functions after making the context current
        createCapabilities();

        // Set the viewport to match the window dimensions
        glViewport(0, 0, width, height);

        // Set the framebuffer size callback
        glfwSetFramebufferSizeCallback(window, (win, newWidth, newHeight) -> {
            width = newWidth;  // Update global width
            height = newHeight; // Update global height
            glViewport(0, 0, width, height);
        });

        // Render loop
        while (!glfwWindowShouldClose(window)) {
            // input
            processInput(window);

            // rendering commands here
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // check and call events and swap the buffers
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwTerminate();
    }

    private void processInput(long window) {
        if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
            glfwSetWindowShouldClose(window, true);
        }
    }

    // TODO: Later would like this to run on separate thread
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new GUI();
    }
}
