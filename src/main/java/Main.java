public class Main {

    public static void main(String[] args) {

        var simulator = new Simulator(30, 100000);

        var view = new View(simulator.getParticles().toArray(new Particle[0]));
        simulator.addListener(view);

        var logger = new Logger();
        simulator.addListener(logger);

        simulator.run();

    }
}

class Logger implements SimulatorListener{

    private int cycleCount = 0;

    @Override
    public void onCycleComplete() {
        cycleCount++;
        if (cycleCount % 100 == 0) {
            System.out.println("Cycle completed: " + cycleCount);
        }
    }
}
