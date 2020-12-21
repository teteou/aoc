package days;

import utils.DailyInput;

import java.util.*;

public class Day21 extends DailyInput {

    private static List<String> input;
    private static Map<String, Set<String>> allergenMap;

    public static void main(String[] args) {

        input = getStringList();

        System.out.println("Part 1: " + partOne());
        System.out.println(partTwo());
    }

    public static long partOne() {
        allergenMap = new HashMap<>(input.size());

        List<String> ingredientList = new ArrayList<>(input.size());

        for (String line : input) {
            String[] parts = line.split("[(|)]");

            Set<String> ingredientsForProduct = new HashSet<>(Arrays.asList(parts[0].split(" ")));
            ingredientList.addAll(ingredientsForProduct);

            Set<String> allergensForProduct = new HashSet<>(Arrays.asList(parts[1].substring(9).split(", ")));

            for (String allergen : allergensForProduct) {
                if (allergenMap.containsKey(allergen)) {
                    Set<String> ingredients = new HashSet<>();
                    for (String existingIngredient : allergenMap.get(allergen)) {
                        if (ingredientsForProduct.contains(existingIngredient)) {
                            ingredients.add(existingIngredient);
                        }
                    }
                    allergenMap.put(allergen, ingredients);
                } else {
                    allergenMap.put(allergen, ingredientsForProduct);
                }
            }

        }

        HashSet<String> finalSet = new HashSet<>();
        allergenMap.forEach((a, s) -> finalSet.addAll(s));

        return ingredientList.stream().filter(i -> !finalSet.contains(i)).count();
    }

    public static long partTwo() {
        Set<String> solo = new HashSet<>();
        boolean done = false;
        while (!done) {
            done = true;
            for (Set<String> ingredients : allergenMap.values()) {
                if (ingredients.size() == 1) {
                    solo.add(String.valueOf(ingredients.toArray()[0]));
                } else {
                    ingredients.removeAll(solo);
                    done = false;
                }
            }
        }
        List<String> keys = new ArrayList<>(allergenMap.keySet());
        Collections.sort(keys);
        List<String> answer = new ArrayList<>();
        for (String allergen : keys) {
            answer.add((String) allergenMap.get(allergen).toArray()[0]);
        }

        System.out.println("Part 2: "+String.join("," , answer));
        return 0L;
    }
}
