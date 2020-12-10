package days;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day01 {

    // ==== Part One ====
    public static void getPares(Integer[] arr, int sum)
    {

        // Consider all possible pairs and check their sums
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if ((arr[i] + arr[j]) == sum) {
                    System.out.println("Esta son la parejas que suman 2020");
                    System.out.println("Pairs is " + arr[i] + ", " + arr[j]);
                    int operacion = arr[i]* arr[j];
                    System.out.println("Final Rersult: "+operacion);
                }
    }

    // ==== Part Two ====
    // returns true if there is triplet with sum equal
    // to 'sum' present in A[]. Also, prints the triplet
    boolean encuentraTriplete(Integer[] arr, int arr_size, int sum)
    {
        int l, r;

        // Fix the first element as A[i]
        for (int i = 0; i < arr_size - 2; i++) {

            // Fix the second element as A[j]
            for (int j = i + 1; j < arr_size - 1; j++) {

                // Now look for the third number
                for (int k = j + 1; k < arr_size; k++) {
                    if (arr[i] + arr[j] + arr[k] == sum) {
                        System.out.println("Triplet is " + arr[i] + ", " + arr[j] + ", " + arr[k]);
                        int operacion = arr[i] * arr[j] * arr[k];

                        System.out.println("El resultado es: " + operacion);
                        return true;
                    }
                }
            }
        }

        // If we reach here, then no triplet was found
        return false;
    }
    // MAIN
    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("/Users/cypherpunk5/Projects/aoc/2020/src/inputs/Day_1.txt");
        Scanner scanner = new Scanner(filePath);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        Day01 triplet = new Day01();
        Integer[] arr = list.toArray(new Integer[0]);
        int sum = 2020;
        System.out.println("Part One");
        getPares(arr, sum);
        System.out.println("Part Two");
        int arr_size = arr.length;
        triplet.encuentraTriplete(arr, arr_size, sum);

    }
}
