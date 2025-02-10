import gui.View;
import simulator.Particle;
import simulator.Simulator;

public class Main {

    public static void main(String[] args) {

        var simulator = new Simulator(200, 100000);

        var view = new View(simulator.getParticles().toArray(new Particle[0]));
        simulator.addListener(view);

        simulator.run();

    }
}
