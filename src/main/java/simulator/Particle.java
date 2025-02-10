package simulator;

import gui.Drawable;

import java.awt.*;

public class Particle implements Drawable {

    private double c; // Charge of the particle (positive or negative)
    private volatile double x, y; // Position on the 2D plane
    private volatile double vx, vy; // Velocity in x and y directions (speed)

    public Particle(double x, double y, double c, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.vx = vx;
        this.vy = vy;
    }

    /**
     * Calculates Euclidean distance between two Particles.
     * @param other Other simulator.Particle.
     * @return Distance between the two.
     */
    public double distance(Particle other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * Calculates the force between two particles.
     * @param other Other particle.
     * @return Force between the two.
     */
    public double force(Particle other) {
        double d = distance(other);
        // Avoiding division by zero
        if (d == 0) return 0;
        return Math.abs(this.c * other.c) / (d * d);
    }

    /**
     * Updates velocity of particle based of the interaction
     * with other particle.
     * @param other Other particle.
     */
    public void interact(Particle other) {
        double d = distance(other);
        if (d == 0) return; // Avoid self-interaction
        double f = force(other);

        double dx = (other.x - this.x) / d;
        double dy = (other.y - this.y) / d;

        // Determine attraction or repulsion
        double direction = (this.c * other.c > 0) ? 1 : -1;

        // Update velocity
        this.vx += direction * f * dx;
        this.vy += direction * f * dy;
    }

    /**
     * Updates position of the particle.
     */
    public void update() {
        this.x += this.vx;
        this.y += this.vy;

        int width = 800;
        int height = 600;

        // Bounce off left and right walls
        if (x <= 0 || x >= width - 8) { // 8 is the particle size
            vx = -vx;
            x = Math.max(0, Math.min(x, width - 8));
        }

        // Bounce off top and bottom walls
        if (y <= 0 || y >= height - 8) {
            vy = -vy;
            y = Math.max(0, Math.min(y, height - 8));
        }
    }

    @Override
    public void draw(Graphics2D g) {
        var color = (c > 0) ? Color.RED : Color.BLUE;
        g.setColor(color);
        g.fillOval((int) x, (int) y, 8, 8);
    }

    @Override
    public String toString() {
        return "simulator.Particle { x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", c=" + c + " }";
    }

}
