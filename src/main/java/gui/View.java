package gui;

import simulator.SimulatorListener;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements SimulatorListener {

    private Canvas canvas;

    public View(Drawable[] particles) {
        setTitle("Particles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        for (Drawable particle : particles) {
            canvas.addDrawable(particle);
        }
        canvas.repaint();
        canvas.setPreferredSize(new Dimension(800, 600));
        canvas.setSize(800, 600);

        add(canvas);
        pack();
        setVisible(true);
    }

    @Override
    public void onCycleComplete() {
        SwingUtilities.invokeLater(() -> {
            canvas.repaint();
        });
    }
}
