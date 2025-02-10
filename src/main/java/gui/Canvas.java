package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Canvas extends JPanel {

    private final List<Drawable> particles = new CopyOnWriteArrayList<>();
    private BufferedImage buffer;

    public Canvas() {
        this.particles.addAll(particles);
        setDoubleBuffered(true);
    }

    public void addDrawable(Drawable p) {
        particles.add(p);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);


        Graphics2D g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(23, 23, 23));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        for (Drawable p : particles) {
            p.draw(g2d);
        }
        g2d.dispose();

        g.drawImage(buffer, 0, 0, null);
    }
}
