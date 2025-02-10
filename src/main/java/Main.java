import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random rand = new Random();

        var p1 = new Particle(1, 1, 1, 1, 1);
        var p2 = new Particle(2, 1, 1, 1, 1);

        System.out.println(p1);
        System.out.println(p2);

        p1.interact(p2);

        System.out.println(p1);

    }
}
