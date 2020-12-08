package days;

import utils.DailyInput;
import java.util.ArrayList;
import java.util.List;

public class DayEight extends DailyInput {

    // Main
    public static void main(String[] args)  {
        partOne(getStringList());
        partTwo(getStringList());
    }

    // Part One
    private static void partOne(List<String> list) {
        List<String> instrucciones = new ArrayList<>();
        List<Integer> done = new ArrayList<>();
        int contador = 0;
        for (String s : list){
            instrucciones.add(s);
        }

        for (int i = 0; !done.contains(i); i++){
            done.add(i);
            String instr = instrucciones.get(i);
            int value = Integer.parseInt(instr.substring(4));
            if (instr.startsWith("acc")){
                contador += value;
            }
            else if (instr.startsWith("jmp")) {
                i += value;
                i--;
            }
        }
        System.out.println(contador);
    }

    // Part Two
    private static void partTwo(List<String> list) {

        List<String> instrucciones = new ArrayList<>();

        for (String s : list){
            instrucciones.add(s);
        }


        for (int i = 0; i < instrucciones.size(); i++) {
            int q = 0;
            String a = instrucciones.get(i);
            if (a.startsWith("jmp")){
                instrucciones.set(i, "nop" + a.substring(3));
                q = comprobarInstruciones(instrucciones);
                instrucciones.set(i, "jmp" + a.substring(3));
            }
            else if (a.startsWith("nop")){
                instrucciones.set(i, "jmp" + a.substring(3));
                q = comprobarInstruciones(instrucciones);
                instrucciones.set(i, "nop" + a.substring(3));
            }
            if (q != 0) {
                System.out.println(q);
                break;
            }
        }

    }
    public static int comprobarInstruciones(List<String> instrucciones) {
        int i = 0;
        int prev = 0;
        int contador = 0;
        List<Integer> done = new ArrayList<>();

        while(!done.contains(i) && i < instrucciones.size()){
            done.add(i);
            String instr = instrucciones.get(i);
            int value = Integer.parseInt(instr.substring(4));
            if (instr.startsWith("acc")){
                contador += value;
            }
            else if (instr.startsWith("jmp")) {
                prev = i;
                i += value;
                i--;
            }
            i++;
        }
        if (i >= instrucciones.size()){
            return contador;
        }
        return 0;
    }

}
