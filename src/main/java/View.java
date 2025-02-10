import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements SimulatorListener{

    private Canvas canvas;

    public View(Particle[] particles) {
        setTitle("Particles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setLocationRelativeTo(null);

        canvas = new Canvas();
        for (Particle particle : particles) {
            canvas.addParticle(particle);
        }
        canvas.repaint();
        canvas.setPreferredSize(new Dimension(800, 600));

        add(canvas);
        setVisible(true);
        pack();
    }

    @Override
    public void onCycleComplete() {
        SwingUtilities.invokeLater(() -> {
            canvas.repaint();
        });
    }
}
