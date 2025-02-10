import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Canvas extends JPanel {

    private final List<Particle> particles = new CopyOnWriteArrayList<>();
    private BufferedImage buffer;

    public Canvas() {
        this.particles.addAll(particles);
        setDoubleBuffered(true);
    }

    public void addParticle(Particle p) {
        particles.add(p);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);


        Graphics2D g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        for (Particle p : particles) {
            g2d.fillOval((int) p.getX(), (int) p.getY(), 10, 10);
        }
        g2d.dispose();

        g.drawImage(buffer, 0, 0, null);
    }
}
