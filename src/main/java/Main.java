import javax.swing.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        Canvas canvas = new Canvas();
        frame.add(canvas);

        frame.setVisible(true);

        int n = 20;

        var rand  = new Random();
        var particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            particles[i] = new Particle(
                    rand.nextDouble(800),
                    rand.nextDouble(600),
                    rand.nextDouble(5) - 2.5,
                    rand.nextDouble(0.001),
                    rand.nextDouble(0.001)
            );
            canvas.addParticle(particles[i]);
        }


        new Thread(() -> {
            while (true) {
                for (var pi : particles) {
                    for (var pj : particles) {
                        pi.interact(pj);
                        pi.update();
                    }
                }
                canvas.repaint();

                try {
                    Thread.sleep(1000 / 60);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}
