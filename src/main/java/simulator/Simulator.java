package simulator;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

// TODO: This one could use strategy pattern later on for diff modes
public class Simulator {

    private HashSet<SimulatorListener> listeners = new HashSet<>();

    private int cycles;
    private Particle[] particles;

    public Simulator(int particles, int cycles) {
        this.cycles = cycles;
        this.particles = new Particle[particles];

        var rand = new Random();
        for (int i = 0; i < particles; i++) {
            this.particles[i] = new Particle(
                    rand.nextDouble(800),
                    rand.nextDouble(600),
                    (i % 2 == 0) ? 10 : -10,
                    rand.nextDouble(0.001),
                    rand.nextDouble(0.001)
            );
        }
    }

    public void run() {
        new Thread(() -> {
            while (true) {
                for (var pi : particles) {
                    for (var pj : particles) {
                        pi.interact(pj);
                        pi.update();
                    }
                }
                notifyListeners();

                try {
                    Thread.sleep(1000 / 60); // 60 FPS cap
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void addListener(SimulatorListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SimulatorListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (var l : listeners) {
            l.onCycleComplete();
        }
    }

    public List<Particle> getParticles() {
        return List.of(particles);
    }

}
