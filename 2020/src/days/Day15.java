package days;

import utils.DailyInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 extends DailyInput {
    // Main
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }
    // Part One
    public static void partOne(List<String> rows) {

        for (String ks : rows){
            String[] input = ks.split(",");
            List<Long> sequence = new ArrayList<>();
            long prev = -1;
            for (String i : input){
                sequence.add(prev);
                prev = Integer.parseInt(i);
            }
            while (sequence.size() <= 2020){
                int a = sequence.lastIndexOf(prev);
                long b;
                if (a == -1) {
                    b = 0;
                }
                else {
                    b = sequence.size()-a;
                }
                sequence.add(prev);
                prev = b;
            }
            System.out.println(sequence.get(2020));
        }
    }
    // Part Two
    public static void partTwo(List<String> rows) {
        for (String ks : rows){
            String[] input = ks.split(",");
            List<Long> sequence = new ArrayList<>();

            Map<Long, Long> seq = new HashMap<>();
            long prev = -1;
            long count = 0;
            for (String i : input){
                seq.put(prev, count);
                count++;
                prev = Integer.parseInt(i);
            }
            while (count <= 30000000){
                long b;
                if (!seq.containsKey(prev)) {
                    b = 0;
                }
                else {
                    b =count-seq.get(prev);
                }
                seq.put(prev, count);
                count++;
                prev = b;
            }
            for (Map.Entry<Long, Long> m : seq.entrySet()){
                if (30000000 == m.getValue()){
                    System.out.println(m.getKey());
                }
            }
        }
    }
}
