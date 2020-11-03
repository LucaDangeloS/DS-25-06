package e1;
import java.util.Random;

public class RNG {
    private static Random rand;

    RNG() { //creación de la semilla aleatoria para el RNG
        rand = new Random();
    }

    RNG(int seed) { //creacion de dado trucado pasando semilla
        rand = new Random(seed);
    }

    public static int Roll(int max) {
        return rand.nextInt(max);
    }
}