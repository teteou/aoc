package days;
import utils.DailyInput;

import java.util.*;

public class Day06 extends DailyInput {

    // MAIN
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }

    // Part Ones
    public static void partOne(List<String> list) {
        var respuestas = new ArrayList<String>();
        var sb = new StringBuilder();
        for (var s : list) {
            if (!s.isBlank()) {
                sb.append(s);
            } else {
                respuestas.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        respuestas.add(sb.toString());
        var s = respuestas.stream().mapToInt(a -> {
            var uniques = new HashSet<>();
            a.chars().forEach(c -> uniques.add(c));
            return uniques.size();
        }).sum();
        System.out.println(s);
    }

    // Part Two
    public static void partTwo(List<String> list) {
        var respuestas = new ArrayList<String>();
        var sb = new StringBuilder();
        for (var s : list) {
            if (!s.isBlank()) {
                sb.append(s+",");
            } else {
                var tmp = sb.toString();
                respuestas.add(tmp.substring(0,tmp.length()-1));
                sb = new StringBuilder();
            }
        }
        var tmp = sb.toString();
        respuestas.add(tmp.substring(0,tmp.length()-1));
        var s = respuestas.stream().mapToLong(a -> {
            var singles = a.split(",");
            if (singles.length == 1) return a.length();
            var first = singles[0];
            return first.chars().filter(c -> {
                var i = 1;
                while(i < singles.length && singles[i].chars().anyMatch(c1 -> c1 == c)) {
                    i++;
                }
                return i == singles.length;
            }).count();
        }).sum();
        System.out.println(s);
    }
}
