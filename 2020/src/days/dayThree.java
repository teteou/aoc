package days;

import utils.dailyInput;

import java.util.List;

public class dayThree extends dailyInput {


    // Part One
    private static long partOne(int pendienteX, int pendienteY) {
        List<String> list = getStringList();
        int arbolesTotales = 0;
        for (int i = pendienteY, j = pendienteX; i < list.size(); i = i + pendienteY, j = j + pendienteX) {
            char[] chars = list.get(i).toCharArray();
            if (j >= chars.length) {
                j = j - chars.length;
            }
            if (chars[j] == '#') {
                arbolesTotales++;
            }
        }
        return arbolesTotales;
    }

    // Part Two
    private static void partTwo() {
        System.out.println(partOne(1, 1) *
                partOne(3, 1) *
                partOne(5, 1) *
                partOne(7, 1) *
                partOne(1, 2));
    }

    //MAIN
    public static void main(String[] args) {
        System.out.println(partOne(3, 1));
        partTwo();
    }
}
