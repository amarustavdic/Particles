package com.nowhereland.gui;

import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

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


        //---------------------------------------------------


        // Triangle vertices
        float vertices[] = {
                -0.5f, -0.5f, 0.0f, // Bottom-left
                0.5f, -0.5f, 0.0f, // Bottom-right
                0.0f,  0.5f, 0.0f  // Top-center
        };

        // Generate and bind VAO
        int vao = glGenVertexArrays();
        glBindVertexArray(vao);

        // Generate and bind VBO
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        // Convert vertices array to a FloatBuffer
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
        vertexBuffer.put(vertices).flip();

        // Upload vertex data to GPU
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Define vertex attribute (position: layout location 0)
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        // Unbind VBO and VAO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        //---------------------------------------------------
        // SHADERS
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader,
                "#version 330 core\n" +
                        "layout (location = 0) in vec3 aPos;\n" +
                        "void main() {\n" +
                        "    gl_Position = vec4(aPos, 1.0);\n" +
                        "}");
        glCompileShader(vertexShader);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader,
                "#version 330 core\n" +
                        "out vec4 FragColor;\n" +
                        "void main() {\n" +
                        "    FragColor = vec4(1.0, 0.5, 0.2, 1.0);\n" +  // Orange color
                        "}");
        glCompileShader(fragmentShader);

        int shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        //---------------------------------------------------


        // Render loop
        while (!glfwWindowShouldClose(window)) {
            // input
            processInput(window);

            // rendering commands here
            glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            // Use shader program
            glUseProgram(shaderProgram);

            // Draw the triangle
            glBindVertexArray(vao);
            glDrawArrays(GL_TRIANGLES, 0, 3);
            glBindVertexArray(0);

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
