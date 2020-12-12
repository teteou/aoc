package days;

import utils.DailyInput;

import java.util.List;

public class Day12 extends DailyInput {
    // Main
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }

    // Part One
    public static void partOne(List<String> rows) {
        var faces = new char[] { 'E', 'S', 'W', 'N' };
        var face = 0;
        Coordenada position = new Coordenada(0, 0);
        for (var r : rows) {
            var shift = Integer.parseInt(r.substring(1));
            var direction = r.charAt(0);
            face = applyRule(position, shift, direction, face, faces);
        }
        System.out.println(Math.abs(position.x) + Math.abs(position.y));
    }

    private static int applyRule(Coordenada position, int shift, char direction, int face, char[] faces) {
        switch (direction) {
            case 'N':
                position.y += shift;
                break;
            case 'S':
                position.y -= shift;
                break;
            case 'E':
                position.x += shift;
                break;
            case 'W':
                position.x -= shift;
                break;
            case 'R':
                face = (face + (shift / 90)) % 4;
                break;
            case 'L':
                face = Math.floorMod(face - shift / 90, 4);
                break;
            case 'F':
                applyRule(position, shift, faces[face], face, faces);
        }
        return face;
    }

    public static void partTwo(List<String> rows) {

        Coordenada shipPosition = new Coordenada(0, 0);
        Coordenada waypointPosition = new Coordenada(10, 1);
        for (var r : rows) {
            var shift = Integer.parseInt(r.substring(1));
            var direction = r.charAt(0);
            switch (direction) {
                case 'N':
                    waypointPosition.y += shift;
                    break;
                case 'S':
                    waypointPosition.y -= shift;
                    break;
                case 'E':
                    waypointPosition.x += shift;
                    break;
                case 'W':
                    waypointPosition.x -= shift;
                    break;
                case 'R': {
                    for (var i = 0; i < shift / 90; i++) {
                        var tmpx = waypointPosition.x;
                        var tmpy = waypointPosition.y;
                        waypointPosition.x = tmpy;
                        waypointPosition.y = -tmpx;
                    }
                    break;
                }
                case 'L':
                    for (var i = 0; i < shift / 90; i++) {
                        var tmpx = waypointPosition.x;
                        var tmpy = waypointPosition.y;
                        waypointPosition.x = -tmpy;
                        waypointPosition.y = tmpx;
                    }
                    break;
                case 'F':
                    shipPosition.x += waypointPosition.x * shift;
                    shipPosition.y += waypointPosition.y * shift;
            }
        }
        System.out.println(Math.abs(shipPosition.x) + Math.abs(shipPosition.y));
    }
    public static class Coordenada {
        public int x;
        public int y;

        public Coordenada(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Coordenada other = (Coordenada) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        public int distance(Coordenada other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
        }
    }
}
