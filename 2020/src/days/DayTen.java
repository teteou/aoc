package days;

import utils.DailyInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DayTen extends DailyInput {
    public static void main(String[] args)  {

        List<Integer> list = new ArrayList<>();
        list.add(0);

        for(Integer ks : getIntegerList()){
            list.add(ks);
        }
        Collections.sort(list);

        System.out.println(list);

        partOne(list);
        partTwo(list);
    }

    // Part One
    public static void partOne(List<Integer> list) {
        int oneDiff = 0;
        int threeDiff = 1;
        for (int i = 1; i < list.size(); i++) {
            int count = list.get(i) - list.get(i - 1);
            if (count == 1) oneDiff++;
            if (count == 3) threeDiff++;
        }
        System.out.println(oneDiff * threeDiff);
    }

    // Part Two
    public static void partTwo(List<Integer> list) {
        HashMap<Integer, List<Integer>> choices = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            int number = list.get(i);
            choices.put(number, new ArrayList<>());
            if (list.contains(number + 1)) choices.get(number).add(number + 1);
            if (list.contains(number + 2)) choices.get(number).add(number + 2);
            if (list.contains(number + 3)) choices.get(number).add(number + 3);
            if (choices.get(number).size() == 0) choices.get(number).add(number);
        }
        HashMap<Integer, Long> containedEndings = new HashMap<>();
        containedEndings.put(Collections.max(new ArrayList<>(choices.keySet())), 1L);
        List<Integer> keys = new ArrayList<>(choices.keySet());
        Collections.sort(keys);
        Collections.reverse(keys);
        for (int endContainer : keys) {
            long endsContained = 0;
            List<Integer> container = choices.get(endContainer);
            for (int containerElement : container) {
                endsContained += containedEndings.get(containerElement);
            }
            containedEndings.put(endContainer, endsContained);
        }
        System.out.println(containedEndings.get(0));
    }
}
