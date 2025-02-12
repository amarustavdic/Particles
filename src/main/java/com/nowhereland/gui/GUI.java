package com.nowhereland.gui;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*; // Import OpenGL bindings

public class GUI {

    public GUI() {

        // Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW.");
        }

        // Set OpenGL version to 3.3 and use core profile
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        // Create a window (800x600)
        long window = glfwCreateWindow(800, 600, "LearnOpenGL", 0, 0);
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
        glViewport(0, 0, 800, 600);

        // Set the framebuffer size callback
        glfwSetFramebufferSizeCallback(window, (win, width, height) -> glViewport(0, 0, width, height));

        // Render loop
        while (!glfwWindowShouldClose(window)) {
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwTerminate();
    }


    public static void main(String[] args) {
        new GUI();
    }
}
