package days;

import utils.DailyInput;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day18 extends DailyInput {
    // Main
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        for(String ks : getStringList()){
            inputs.add(ks);
        }

        calcularInputs(inputs, false);
        calcularInputs(inputs, true);
    }

    public static void calcularInputs(List<String> inputs, boolean order) {
        long added = 0L;
        for (String equation : inputs) added += calcularEcuacion(equation, order);
        System.out.println(added);
    }

    public static long calcularEcuacion(String s, boolean order) {
        Pattern pattern = Pattern.compile("(\\((\\+|\\*| |\\d)+\\))");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            s = s.replace(matcher.group(), String.valueOf(calcularEcuacion(matcher.group().replace("(", "").replace(")", ""), order)));
            matcher = pattern.matcher(s);
        }
        Pattern eqPattern = Pattern.compile("(\\d+ [+*] \\d+)");
        if (order) eqPattern = Pattern.compile("(\\d+ \\+ \\d+)");
        Matcher eqMatcher = eqPattern.matcher(s);
        while (eqMatcher.find()) {
            s = s.replaceFirst(Pattern.quote(eqMatcher.group()), calculoBasico(eqMatcher.group()) + "");
            eqMatcher = eqPattern.matcher(s);
        }
        if (order) {
            eqPattern = Pattern.compile("(\\d+ \\* \\d+)");
            eqMatcher = eqPattern.matcher(s);
            while (eqMatcher.find()) {
                s = s.replaceFirst(Pattern.quote(eqMatcher.group()), calculoBasico(eqMatcher.group()) + "");
                eqMatcher = eqPattern.matcher(s);
            }
        }
        return Long.parseLong(s);
    }

    public static long calculoBasico(String s) {
        Long firstNumber = Long.parseLong(s.split(" ")[0]);
        String operator = s.split(" ")[1];
        Long secondNumber = Long.parseLong(s.split(" ")[2]);
        switch (operator) {
            case "+":
                return firstNumber + secondNumber;
            case "*":
                return firstNumber * secondNumber;
            default:
                throw new UnsupportedOperationException();
        }
    }
}
