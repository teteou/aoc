package days;

import utils.DailyInput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class DayNine extends DailyInput {

    // Main
    public static void main(String[] args)  {
        ArrayList<Long> longs = new ArrayList<>();
        for(String ks : getStringList()){
            longs.add(Long.parseLong(ks));
        }
        partOne(longs);
        partTwo(longs);
    }

    public static void partOne(List<Long> numeros) {
        var cola = new ArrayDeque<Long>(numeros);
        var preambulo = new ArrayDeque<Long>();
        for (int i = 0; i < 25; i++) {
            preambulo.add(cola.pop());
        }
        for (var n : cola) {
            var encontrar = false;
            for (var n1 : preambulo) {
                for (var n2 : preambulo) {
                    if (n1 + n2 == n) {
                        encontrar = true;
                    }
                }
            }
            if (encontrar) {
                preambulo.pop();
                preambulo.add(n);
            } else {
                System.out.println(n);
                break;
            }
        }
    }

    // Part Two
    public static void partTwo(List<Long> numeros) {
        var cola = new ArrayDeque<Long>(numeros);
        var preambulo = new ArrayDeque<Long>();
        var weak = 0l;
        for (int i = 0; i < 25; i++) {
            preambulo.add(cola.pop());
        }
        for (var n : cola) {
            var encontar = false;
            for (var n1 : preambulo) {
                for (var n2 : preambulo) {
                    if (n1 + n2 == n) {
                        encontar = true;
                    }
                }
            }
            if (encontar) {
                preambulo.pop();
                preambulo.add(n);
            } else {
                weak = n;
                break;
            }
        }
        var contiguo = new ArrayList<Long>();
        int i = 0;
        while (contiguo.stream().mapToLong(x->x).sum() != weak) {
            contiguo = new ArrayList<Long>();
            int j = i;
            while (contiguo.stream().mapToLong(x->x).sum() < weak) {
                contiguo.add(numeros.get(j++));
            }
            i++;
        }
        System.out.println(contiguo.stream().mapToLong(x->x).min().getAsLong()+contiguo.stream().mapToLong(x->x).max().getAsLong());
    }
}
