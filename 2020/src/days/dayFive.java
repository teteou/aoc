package days;

import utils.dailyInput;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class dayFive  extends dailyInput {
    private static Set<Integer> asientos = new HashSet<>();

    // Part One
    private static void partOne(List<String> list) {
        int[] asiento = new int[8];
        int max = 0;
        for (String s : list) {
            s = s.replaceAll("[F|L]", "0").replaceAll("[B|R]", "1");
            max = Math.max(max, Integer.parseInt(s, 2));
            asientos.add(Integer.parseInt(s, 2)); //for Part Two
        }
        System.out.println(max);
    }

    // PArt Two
    private static void partTwo() {
        IntStream.iterate(asientos.size(), i -> i > 1, i -> i - 1)
                .filter(i -> !asientos.contains(i))
                .findFirst()
                .ifPresent(System.out::println);
    }

    public static void main (String[]args){
            partOne(getStringList());
            partTwo();

    }
}
