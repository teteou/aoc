package days;

import utils.DailyInput;
import java.util.List;

public class Day25 extends DailyInput {

    public static void main(String[] args) {
        final List<String> input = getStringList();
        partOne(input);
        partTwo(input);

    }
    private static void partOne(List<String> input) {
        final int pkey1 = Integer.parseInt(input.get(0));
        final int pkey2 = Integer.parseInt(input.get(1));
        long value = 1;
        int loopsize = 0;
        for (int i = 0; i < 1000000000; i++) {
            value *= 7;
            value %= 20201227;
            if (value == pkey1) {
                loopsize = i + 1;
                break;
            }
        }

        //System.out.println(loopsize);

        long key = 1;
        for (int j = 0; j < loopsize; j++) {
            key *= pkey2;
            key %= 20201227;
        }

        System.out.println(key);
    }

    private static void partTwo(List<String> input) {
        System.out.println("Te la dan de gratis... 😢😭");
    }
}
