package days;
import utils.DailyInput;
import java.util.ArrayList;
import java.util.List;

public class Day04 extends DailyInput {

    //
    private static String[] validators = {"ecl:", "pid:", "eyr:", "hcl:", "byr:", "iyr:", "hgt:"};
    private static String[] eyeColor = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
    private static List<String> documentos = new ArrayList<>();

    // Part One
    public static void partOne(List<String> list){
        StringBuilder doc = new StringBuilder();
        int documentosValidos = 0;

        for (String datos : list) {
            if (!datos.equals("")) {
                doc.append(datos).append(" ");
            } else {
                int camposValidos = 0;
                for (String validator : validators) {
                    if (doc.toString().contains(validator)) {
                        camposValidos++;
                    }
                }
                if (camposValidos == 7) {
                    documentos.add(doc.toString());
                    documentosValidos++;
                }
                doc = new StringBuilder();
            }
        }
        System.out.println("Documentos validos: " + documentosValidos);
    }

    // Part Two
    private static void partTwo() {
        int docuemntosValidos = 0;
        for (String documento : documentos) {
            int valid = 0;
            for (String s : documento.split(" ")) {
                String data = s.substring(s.indexOf(":") + 1);
                for (int i = 0; i < validators.length; i++) {
                    String validator = validators[i];
                    if (s.contains(validator)) {
                        switch (i) {
                            case 0:
                                for (String s1 : eyeColor) {
                                    if (data.contains(s1)) {
                                        valid++;
                                        break;
                                    }
                                }
                                break;
                            case 1:
                                if (data.matches("[0-9]{9}")) {
                                    valid++;
                                }
                                break;
                            case 2:
                                if (Integer.parseInt(data) >= 2020 && Integer.parseInt(data) <= 2030) {
                                    valid++;
                                }
                                break;
                            case 3:
                                if (data.matches("#(\\w){6}")) {
                                    valid++;
                                }
                                break;
                            case 4:
                                if (Integer.parseInt(data) >= 1920 && Integer.parseInt(data) <= 2002) {
                                    valid++;
                                }
                                break;
                            case 5:
                                if (Integer.parseInt(data) >= 2010 && Integer.parseInt(data) <= 2020) {
                                    valid++;
                                }
                                break;
                            case 6:
                                if (data.contains("cm")) {
                                    if (Integer.parseInt(data.replaceAll("cm", "")) >= 150 && Integer.parseInt(data.replaceAll("cm", "")) <= 193) {
                                        valid++;
                                    }
                                } else if (data.contains("in")) {
                                    if (Integer.parseInt(data.replaceAll("in", "")) >= 59 && Integer.parseInt(data.replaceAll("in", "")) <= 76) {
                                        valid++;
                                    }
                                }
                                break;
                        }

                    }
                }
            }
            if (valid == 7) {
                docuemntosValidos++;
            }
        }
        System.out.println(docuemntosValidos);
    }



    // MAIN
    public static void main(String[] args) {
        partOne(getStringList());
        partTwo();
    }
}
