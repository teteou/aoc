package days;

import utils.DailyInput;

import java.util.HashMap;
import java.util.List;

public class Day14 extends DailyInput{
    // Main
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }
    // Part One
    public static void partOne(List<String> rows) {
        var mask = "";
        HashMap<String, Long> memory = new HashMap<>();
        for (var r : rows) {
            String[] p = r.split(" = ");
            if(p[0].equals("mask")){
                mask = p[1];
            }
            else {
                String binary = Long.toBinaryString(Long.parseLong(p[1]));
                binary = ("000000000000000000000000000000000000" + binary).substring(binary.length());

                StringBuilder a = new StringBuilder();
                for(int i = 0; i<36; i++){
                    if (mask.charAt(i) == 'X'){
                        a.append(binary.charAt(i));
                    }
                    else{
                        a.append(mask.charAt(i));
                    }

                }
                long res = Long.parseLong(a.toString(), 2);
                memory.put(p[0], res);
            }
        }
        long ans = 0;
        for (long i : memory.values()){
            ans += i;
        }
        System.out.println(ans);
    }

    // Part Two
    public static void partTwo(List<String> rows) {
        var mask = "";
        HashMap<Long, Long> memory = new HashMap<>();
        for (var r : rows) {
            String[] p = r.split(" = ");
            if(p[0].equals("mask")){
                mask = p[1];
            }
            else {
                String addr = Long.toBinaryString(Long.parseLong(p[0].substring(4, p[0].length()-1)));
                addr = ("000000000000000000000000000000000000" + addr).substring(addr.length());

                StringBuilder a = new StringBuilder();
                for(int i = 0; i<36; i++){
                    if (mask.charAt(i) == 'X'){
                        a.append("X");
                    }
                    else if (mask.charAt(i) == '1'){
                        a.append("1");
                    }
                    else{
                        a.append(addr.charAt(i));
                    }
                }
                addr = a.toString();

                long[] addrs = findMems(addr);

                for (long i : addrs){
                    memory.put(i, Long.parseLong(p[1]));
                }
            }
        }

        long ans = 0;
        for (long i : memory.values()){
            ans += i;
        }
        System.out.println(ans);
    }
    public static long[] findMems(String addr){
        //count x
        int count = 0;
        for(int i = 0; i < addr.length(); i++){
            if (addr.charAt(i) == 'X'){
                count++;
            }
        }
        long poss = (long) Math.pow(2, count);
        long[] result = new long[(int)poss];

        for (int i = 0; i < poss; i++){
            String binary = Long.toBinaryString(i);
            String a = "000000000000000000000000000000000000" + binary;
            binary = a.substring(a.length()-count);

            StringBuilder res = new StringBuilder();
            int k = 0;
            for (int j = 0; j < addr.length(); j++){
                if (addr.charAt(j) == 'X'){
                    res.append(binary.charAt(k));
                    k++;
                }
                else {
                    res.append(addr.charAt(j));
                }
            }
            result[i] = Long.parseLong(res.toString(), 2);
        }
        return result;
    }

}
