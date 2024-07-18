package lk.darkoinnovex.Ayu.util;

import java.util.Random;

public class PinGenerator {

    public static short generateRandomFourDigitNumber() {
        Random random = new Random();
        return (short) (1000 + random.nextInt(9000));
    }
}
