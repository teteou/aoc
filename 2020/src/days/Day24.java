package days;

import utils.DailyInput;
import utils.HexTile;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day24 extends DailyInput {

    public static void main(String[] args) {
        var lines = getStringList();

        Set<HexTile> set = new HashSet<>();
        var center = new HexTile(0, 0);
        for (var line : lines) {
            var tile = center.getTile(line);
            if (!set.add(tile)) {
                set.remove(tile);
            }
        }
        int solution1 = set.size();

        for (int i = 0; i < 100; i++) {
            var s = set;
            set = set.stream()
                    .flatMap(tile -> tile.getNeighbors(true).stream())
                    .distinct()
                    .filter(tile -> {
                        boolean black = s.contains(tile);
                        int cnt = countBlackNeighbors(tile, s);
                        return (black && (cnt == 1 || cnt == 2)) || (!black && cnt == 2);
                    }).collect(Collectors.toSet());
        }
        int solution2 = set.size();

        System.out.println("Part 1: " + solution1);
        System.out.println("Part 2: " + solution2);
    }

    private static int countBlackNeighbors(HexTile tile, Set<HexTile> set) {
        return (int) tile.getNeighbors().stream().filter(set::contains).count();
    }

}
