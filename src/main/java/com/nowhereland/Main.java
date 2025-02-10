package com.nowhereland;

import com.nowhereland.gui.View;
import com.nowhereland.simulator.Particle;
import com.nowhereland.simulator.Simulator;

public class Main {

    public static void main(String[] args) {

        var simulator = new Simulator(30, 100000);

        var view = new View(simulator.getParticles().toArray(new Particle[0]));
        simulator.addListener(view);

        simulator.run();

    }
}
