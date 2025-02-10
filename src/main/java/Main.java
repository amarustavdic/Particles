import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rand = new Random();

        int n = 1000;

        var particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle(
                    rand.nextDouble(100),
                    rand.nextDouble(100),
                    (i % 2 > 0) ? -1 : 1,
                    rand.nextDouble(2),
                    rand.nextDouble(2)
            );
        }

        System.out.println(particles[0]);

        int cycles = 10;
        while (cycles-- > 0) {
            for (var pi : particles) {
                for (var pj : particles) {
                    pi.interact(pj);
                    pi.update();
                }
            }
        }

        System.out.println(particles[0]);


    }
}
