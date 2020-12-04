
package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class dailyInput {

    private static final Path today = Path.of("src/inputs/" + "Day_" + LocalDate.now().getDayOfMonth() + ".txt");
    public static List<String> todayList = new ArrayList<>();

    private static void loadInput() {
        try {
            if (todayList.isEmpty()) {
                todayList = Files.readAllLines(today).stream().map(String::toString).collect(Collectors.toList());
            }
        } catch (IOException e) {
            System.out.println("ERROR: can't read file.\n" + e);
        }
    }


    protected static List<Integer> getIntegerList() {
        loadInput();
        return todayList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    protected static List<String> getStringList() {
        loadInput();
        return todayList;
    }
}