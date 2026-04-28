package utils;

import java.util.Random;

public class Randomizer {

    private static final Random r = new Random();

    public static int getRandom(int limit) {
        int random = r.nextInt(limit);
        return random;
    }
}
