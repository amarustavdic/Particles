package com.nowhereland;

import com.nowhereland.gui.View;
import com.nowhereland.simulator.Particle;
import com.nowhereland.simulator.Simulator;

import java.util.HashMap;
import java.util.Map;

// TODO: fps limit to 60
// TODO: modes: -s (sequential as default), -p (parallel), -d (distributed)
// TODO: number of particles
// TODO: number of cycles
// TODO: measure runtime needed to complete

public class Main {

    public static void main(String[] args) {

        // java -jar app.jar -gui=800x600 -s -n=3000 -c=500

        Map<String, String> params = new HashMap<>();
        for (var s : args) {
            String[] split = s.split("=");
            String value = null;
            try {
                value = split[1];
            } catch (Exception e) {
                value = "";
            }
            params.put(split[0], value);
        }

        // Checking for must have arguments
        if (!params.containsKey("-n") || !params.containsKey("-c")) {
            System.out.println("Missing required parameters!");
            System.out.println("Usage: java -jar particles.jar [-n=<int>] [-c=<int>]");
            System.out.println("Example: java -jar particles.jar -n=10 -c=10");
            System.out.println("Optional:");
            System.out.println("[-gui=<int>x<int>]");
            System.out.println("[-s] sequential mode");
            System.out.println("[-p] parallel mode");
            System.out.println("[-d] distributed mode");
            System.out.println("Example: java -jar particles.jar -gui=1200x800 -n=10 -c=10");
            System.exit(1);
            return;
        }

        int width = 800;
        int height = 600;
        char mode = 's'; // Execution mode (can be -s, -p, -d)
        int n = 0; // Number of particles
        int c = 0; // Number of cycles

        // Parsing window size
        if (params.containsKey("-gui")) {
            String[] tokens = params.get("-gui").split("x");
            System.out.println(params.get("-gui"));
            if (tokens.length == 2) {
                width = Integer.parseInt(tokens[0]);
                height = Integer.parseInt(tokens[1]);
            }
        }

        // Parsing mode
        if (params.containsKey("-p")) mode = 'p'; // parallel
        if (params.containsKey("-d")) mode = 'd'; // distributed

        // Parsing required
        n = Integer.parseInt(params.get("-n"));
        c = Integer.parseInt(params.get("-c"));


//        System.out.println(
//                "Width: " + width + ", Height: " + height + ", Mode: " + mode + ", N: " + n + ", C: " + c
//        );


        var simulator = new Simulator(n, c);

        if (params.containsKey("-gui")) {
            var view = new View(simulator.getParticles().toArray(new Particle[0]));
            simulator.addListener(view);
        }

        simulator.run();

    }
}
