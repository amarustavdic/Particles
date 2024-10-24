#include <SFML/Graphics.hpp>
#include <vector>
#include <random>
#include "Particle.h"

// Function to generate random float values within a range
float randomFloat(const float low, const float high) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> distr(low, high);
    return static_cast<float>(distr(gen));
}

int main() {
    // Constants
    int width = 800, height = 600;
    int numParticles = 1000;

    // Create window
    sf::RenderWindow window(sf::VideoMode(width, height), "Particle Simulation");
    window.setFramerateLimit(60);

    std::vector<Particle> particles;
    for (int i = 0; i < numParticles; ++i) {
        float x = randomFloat(50, static_cast<float>(width) - 50);
        float y = randomFloat(50, static_cast<float>(height) - 50);
        float vx = randomFloat(-50, 50);
        float vy = randomFloat(-50, 50);
        float charge = randomFloat(-100, 100);
        particles.emplace_back(x, y, vx, vy, charge);
    }

    // Main loop
    while (window.isOpen()) {
        sf::Event event{};
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed)
                window.close();
        }
        window.clear();

        for (size_t i = 0; i < particles.size(); ++i) {
            float dt = 0.01;
            sf::Vector2f netForce(0, 0);

            for (size_t j = 0; j < particles.size(); ++j) {
                if (i != j) {
                    sf::Vector2f force = particles[i].calculateForce(particles[j]);
                    netForce += force;
                }
            }

            particles[i].applyForce(netForce, dt);
            particles[i].updatePosition(dt);

            int radius = 3;

            if (particles[i].x <= 0 || particles[i].x >= static_cast<float>(width - 2 * radius)) particles[i].vx *= -1;
            if (particles[i].y <= 0 || particles[i].y >= static_cast<float>(height - 2 * radius)) particles[i].vy *= -1;

            sf::CircleShape shape(radius);
            shape.setFillColor(particles[i].charge > 0 ? sf::Color::Red : sf::Color::Blue);
            shape.setPosition(particles[i].x, particles[i].y);
            window.draw(shape);
        }
        window.display();
    }

    return 0;
}
