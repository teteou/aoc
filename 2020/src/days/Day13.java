package days;

import utils.DailyInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 extends DailyInput{
    // Main
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        for(String ks : getStringList()){
            inputs.add(ks);
        }

        partOne(inputs);
        partTwo(inputs);
    }

    // Part One
    public static void partOne(List<String> inputs) {
        int earlyTimestamp = Integer.parseInt(inputs.get(0));
        List<Integer> buses = new ArrayList<>();
        for (String s : inputs.get(1).split(",")) {
            if (s.equals("x")) continue;
            buses.add(Integer.parseInt(s));
        }

        int lowestTime = -1;
        int busId = 0;
        for (int bus : buses) {
            int timeDiff = bus - (earlyTimestamp % bus);
            if (lowestTime == -1 || lowestTime > timeDiff) {
                busId = bus;
                lowestTime = timeDiff;
            }
        }
        System.out.println(lowestTime * busId);
    }

    // Part Two
    public static void partTwo(List<String> inputs) {
        List<String> inputList = Arrays.asList(inputs.get(1).split(","));

        long t = 100000000000000L;
        int i = 0;
        long s = 1;

        while (i < inputList.size()) {
            if (inputList.get(i).equals("x")) {
                i++;
                continue;
            }
            int value = Integer.parseInt(inputList.get(i));
            if ((t + i) % value == 0) {
                s *= value;
                i++;
                continue;
            }
            t += s;
        }

        System.out.println(t);
    }

}
