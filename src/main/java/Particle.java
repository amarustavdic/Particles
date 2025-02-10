public class Particle {

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
     * @param other Other Particle.
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

        if (this.x > 800) this.x = 0;
        if (this.y > 600) this.y = 0;
    }

    @Override
    public String toString() {
        return "Particle { x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", c=" + c + " }";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
