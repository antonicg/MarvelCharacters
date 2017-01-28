package com.antonicastejon.marvelcharacters.utils.random;

import java.util.Random;

/**
 * Created by Antoni Castejón on 28/01/2017.
 */

public class RandomUtils {

    public static int getRandomBetween(int rangeStart, int rangeEnd) {
        Random r = new Random(System.currentTimeMillis());
        return r.nextInt(rangeEnd - rangeStart) + rangeStart;
    }
}
