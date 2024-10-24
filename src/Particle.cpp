#include "Particle.h"

#include <cmath>

Particle::Particle(const float x, const float y, const float vx, const float vy, const float charge, const float mass)
    : x(x), y(y), vx(vx), vy(vy), charge(charge), mass(mass) {
}

float Particle::distanceTo(const Particle &other) const {
    return static_cast<float>(std::sqrt(std::pow(x - other.x, 2) + std::pow(y - other.y, 2)));
}

sf::Vector2f Particle::calculateForce(const Particle &other) const {
    const float dist = distanceTo(other);
    if (dist == 0) return {0, 0};  // Avoid division by zero

    // Calculate force magnitude
    float forceMagnitude = std::abs(charge * other.charge) / (dist * dist);

    // Calculate the direction of the force
    const float dx = other.x - x;
    const float dy = other.y - y;
    const float distanceNorm = std::sqrt(dx * dx + dy * dy);

    // Normalize direction and apply force magnitude
    float fx = forceMagnitude * (dx / distanceNorm);
    float fy = forceMagnitude * (dy / distanceNorm);

    // Determine if the force is attractive or repulsive
    if (charge * other.charge > 0) {
        // Same charge (repulsive)
        return {-fx, -fy};
    }
    // Opposite charge (attractive)
    return {fx, fy};
}

void Particle::updatePosition(const float dt) {
    x += vx * dt;
    y += vy * dt;
}

void Particle::applyForce(const sf::Vector2f &force, const float dt) {
    vx += (force.x / mass) * dt;
    vy += (force.y / mass) * dt;
}


