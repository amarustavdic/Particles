public class Particle {

    private double c; // Charge of the particle (positive or negative)
    private double x, y; // Position on the 2D plane
    private double vx, vy; // Velocity in x and y directions (speed)

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
        // TODO: Might wanna optimize this a bit
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
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

    @Override
    public String toString() {
        return "Particle { x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", c=" + c + " }";
    }

}
