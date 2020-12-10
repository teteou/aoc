package days;
import utils.DailyInput;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 extends DailyInput{

    // Main
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo(getStringList());
    }


    // Part One
    public static void partOne(List<String> list) {
        var maletas = new HashMap<String, List<String>>();
        for (var rule : list) {
            var tokens = rule.split(" ");
            var contenedor = tokens[0]+" "+tokens[1];
            var contiene = new ArrayList<String>();
            if (!"no".equals(tokens[4])) {
                for (int i = 5; i < tokens.length; i+=4) {
                    contiene.add(tokens[i]+" "+tokens[i+1]);
                }
            }
            maletas.put(contenedor, contiene);
        }
        System.out.println(encuentrasContenedores(maletas, new HashSet<String>(Arrays.asList("shiny gold"))).size());
    }

    private static Set<String> encuentrasContenedores(HashMap<String, List<String>> maletas, Set<String> colores) {
        var contenedores = maletas.keySet().stream().filter(bag -> colores.stream().anyMatch(color -> maletas.get(bag).contains(color))).collect(Collectors.toSet());
        if (!contenedores.isEmpty())
            contenedores.addAll(encuentrasContenedores(maletas, contenedores));
        return contenedores;
    }

    public static void partTwo(List<String> list) {
        var bags = new HashMap<String, List<Maleta>>();
        for (var rule : list) {
            var tokens = rule.split(" ");
            var contenedor = tokens[0]+" "+tokens[1];
            var contiene = new ArrayList<Maleta>();
            if (!"no".equals(tokens[4])) {
                for (int i = 5; i < tokens.length; i+=4) {
                    contiene.add(new Maleta(tokens[i]+" "+tokens[i+1], Integer.parseInt(tokens[i-1])));
                }
            }
            bags.put(contenedor, contiene);
        }
        System.out.println(contieneMaletas(bags, "shiny gold"));
    }

    public static long contieneMaletas(HashMap<String, List<Maleta>> maletas, String color) {
        var root = maletas.entrySet().stream().filter(b -> b.getKey().equals(color)).findAny().get();
        var c = root.getValue().isEmpty() ? 0 : root.getValue().stream().mapToLong(bag -> bag.contiene +bag.contiene * contieneMaletas(maletas, bag.color)).sum();
        return c;
    }

    static class Maleta {
        String color;
        int contiene;
        public Maleta(String color, int contiene) {
            super();
            this.color = color;
            this.contiene = contiene;
        }

    }
}
