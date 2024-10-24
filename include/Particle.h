#pragma once

#include <SFML/Graphics.hpp>

class Particle {
public:
    float x, y;     // Position of the particle
    float vx, vy;   // Velocity of the particle
    float charge;   // Charge of the particle
    float mass;     // Mass of the particle (defaults to 1)

    Particle(float x, float y, float vx, float vy, float charge, float mass = 1.0f);

    // Euclidean distance between two particles
    [[nodiscard]] float distanceTo(const Particle& other) const;

    // Calculate force between two particles
    [[nodiscard]] sf::Vector2f calculateForce(const Particle& other) const;

    // Update particle position based on velocity
    void updatePosition(float dt);

    // Update particle velocity based on force
    void applyForce(const sf::Vector2f& force, float dt);
};
