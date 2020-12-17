package days;
import utils.DailyInput;

import java.util.List;

public class Day17 extends DailyInput{
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }

    // Part One
    public static void partOne(List<String> lines) {
        String[] inputs = getStringList().toArray(new String[0]);
        char[][][] start = new char[1][inputs.length][inputs[0].length()];

        for (int i = 0; i < inputs.length; i++) {
            start[0][i] = inputs[i].toCharArray();
        }

        int rounds = 0;
        while (rounds < 6) {
            start = augmentGrid(start);
            rounds++;
        }

        int answer = 0;
        for (int z = 0; z < start.length; z++) {
            for (int x = 0; x < start[0].length; x++) {
                for (int y = 0; y < start[0][0].length; y++) {
                    if (start[z][x][y] == '#') {
                        answer = answer + 1;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static char[][][] augmentGrid(char[][][] grid) {
        char[][][] augmented = new char[grid.length + 2][grid[0].length + 2][grid[0][0].length + 2];

        // Initialize
        for (int z = 0; z < grid.length; z++) {
            for (int x = 0; x < grid[0].length; x++) {
                for (int y = 0; y < grid[0][0].length; y++) {
                    augmented[z + 1][x + 1][y + 1] = grid[z][x][y];
                }
            }
        }
        char[][][] modified = new char[grid.length + 2][grid[0].length + 2][grid[0][0].length + 2];
        for (int z = 0; z < augmented.length; z++) {
            for (int x = 0; x < augmented[0].length; x++) {
                for (int y = 0; y < augmented[0][0].length; y++) {
                    int active_around = count_active(x, y, z, augmented);
                    if (augmented[z][x][y] == '#') {
                        if (active_around == 2 || active_around == 3) {
                            modified[z][x][y] = '#';
                        } else {
                            modified[z][x][y] = '.';
                        }
                    } else if (augmented[z][x][y] == '.' || augmented[z][x][y] == '\u0000') {
                        if (active_around == 3) {
                            modified[z][x][y] = '#';
                        } else {
                            modified[z][x][y] = '.';
                        }
                    } else {
                        modified[z][x][y] = '.';
                    }
                }
            }
        }

        return modified;
    }
    static boolean isActive(int x, int y, int z, char[][][] grid) {
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid[0][0].length || z < 0 || z >= grid.length) {
            return false;
        }

        return grid[z][x][y] == '#';
    }

    static int count_active(int x, int y, int z, char[][][] grid) {
        int active = 0;
        for (int dx = x - 1; dx <= x + 1; dx++) {
            for (int dy = y - 1; dy <= y + 1; dy++) {
                for (int dz = z - 1; dz <= z + 1; dz++) {
                    if (dx == x && dy == y && dz == z) {
                        continue;
                    }
                    if (isActive(dx, dy, dz, grid)) {
                        active = active + 1;
                    }
                }
            }
        }
        return active;
    }

    // Part Two
    public static void partTwo(List<String> lines) {
        // Get input
        String[] inputs = getStringList().toArray(new String[0]);
        char[][][][] start = new char[1][1][inputs.length][inputs[0].length()];
        for (int i = 0; i < inputs.length; i++) {
            start[0][0][i] = inputs[i].toCharArray();
        }

        int rounds = 0;
        while (rounds < 6) {
            start = augmentGrid_4d(start);
            rounds++;
        }

        int answer = 0;
        for (int w = 0; w < start.length; w++) {
            for (int z = 0; z < start[0].length; z++) {
                for (int x = 0; x < start[0][0].length; x++) {
                    for (int y = 0; y < start[0][0][0].length; y++) {
                        if (start[w][z][x][y] == '#') {
                            answer = answer + 1;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static char[][][][] augmentGrid_4d(char[][][][] grid) {
        char[][][][] augmented =
                new char[grid.length + 2][grid[0].length + 2][grid[0][0].length + 2][grid[0][0][0].length + 2];

        // Initialize
        for (int w = 0; w < grid.length; w++) {
            for (int z = 0; z < grid[0].length; z++) {
                for (int x = 0; x < grid[0][0].length; x++) {
                    for (int y = 0; y < grid[0][0][0].length; y++) {
                        augmented[w + 1][z + 1][x + 1][y + 1] = grid[w][z][x][y];
                    }
                }
            }
        }

        char[][][][] modified =
                new char[grid.length + 2][grid[0].length + 2][grid[0][0].length + 2][grid[0][0][0].length + 2];
        for (int w = 0; w < augmented.length; w++) {
            for (int z = 0; z < augmented[0].length; z++) {
                for (int x = 0; x < augmented[0][0].length; x++) {
                    for (int y = 0; y < augmented[0][0][0].length; y++) {
                        int active_around = count_active_4d(x, y, z, w, augmented);
                        if (augmented[w][z][x][y] == '#') {
                            if (active_around == 2 || active_around == 3) {
                                modified[w][z][x][y] = '#';
                            } else {
                                modified[w][z][x][y] = '.';
                            }
                        } else if (augmented[w][z][x][y] == '.' || augmented[w][z][x][y] == '\u0000') {
                            if (active_around == 3) {
                                modified[w][z][x][y] = '#';
                            } else {
                                modified[w][z][x][y] = '.';
                            }
                        } else {
                            modified[w][z][x][y] = '.';
                        }
                    }
                }
            }
        }

        return modified;
    }

    static boolean isActive_4d(int x, int y, int z, int w, char[][][][] grid) {
        if (x < 0 || x >= grid[0][0].length || y < 0 || y >= grid[0][0][0].length || z < 0 || z >= grid[0].length
                || w < 0 || w >= grid.length) {
            return false;
        }

        return grid[w][z][x][y] == '#';
    }

    static int count_active_4d(int x, int y, int z, int w, char[][][][] grid) {
        int active = 0;
        for (int dx = x - 1; dx <= x + 1; dx++) {
            for (int dy = y - 1; dy <= y + 1; dy++) {
                for (int dz = z - 1; dz <= z + 1; dz++) {
                    for (int dw = w - 1; dw <= w + 1; dw++) {
                        if (dx == x && dy == y && dz == z && dw == w) {
                            continue;
                        }
                        if (isActive_4d(dx, dy, dz, dw, grid)) {
                            active = active + 1;
                        }
                    }

                }
            }
        }
        return active;
    }

}
