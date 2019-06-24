package controllers;

import services.Alerts;
import services.Constans;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Trida slouzici pro kontrolu vstupu do editacniho formulare
 *
 * @author Václav Janoch
 */
public class InputController {

    /**
     * Metoda pro overeni, ze vstup je cislo
     * @param input hodnota textove podobe
     * @param fieldName nazev pole, ze ktereho hodnota pochazi
     * @return Upravena hodnota na int
     * @throws NumberFormatException V pripade spatneho formatu je vyhozena tato vyjimka
     */
    public static Integer isNumber(String input, String fieldName) throws NumberFormatException {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Alerts.showWrongNumberFormat(fieldName);
            throw e;

        }
    }

    /**
     * Pretizena metoda pro overeni, ze vstup je cislo a je v zadanem intervalu
     * Pro vice textovych vstupu
     * @param input list hodnot z textove podobe
     * @param filedName nazev pole, ze ktereho hodnota pochazi
     * @param minValue minimalni hodnota intervalu
     * @param maxValue maximalni hodnota intervalu
     * @return Upravena hodnota na int
     */
    public static ArrayList<Integer> isNumber(ArrayList<String> input, String filedName, int minValue, int maxValue) {
        ArrayList<Integer> list = new ArrayList<>();

        for (String number : input) {
            list.add(isNumber(number, minValue, maxValue, filedName));
        }
        return list;
    }

    /**
     * Pretizena metoda pro overeni, ze vstup je cislo pro vice stringu
     * @param input seznam stringu pro overeni
     * @param filedName nazev pole ze ktereho texty pochazi
     * @return seznam intu ziskanych z puvodnich textu
     */
    public static ArrayList<Integer> isNumber(ArrayList<String> input, String filedName) {
        ArrayList<Integer> list = new ArrayList<>();

        for (String number : input) {
            list.add(isNumber(number, filedName));
            ;
        }
        return list;
    }
    /**
     * Pretizena metoda pro overeni, ze vstup je cislo a je v zadanem intervalu
     * Pro vice textovych vstupu
     * @param input hodnota z textove podobe
     * @param fieldName nazev pole, ze ktereho hodnota pochazi
     * @param minValue minimalni hodnota intervalu
     * @param maxValue maximalni hodnota intervalu
     * @return Upravena hodnota na int
     */
    public static int isNumber(String input, int minValue, int maxValue, String fieldName) throws NumberFormatException {
        Integer testValue = isNumber(input, fieldName);

        if (testValue != null) {
            if ((minValue > testValue) || (maxValue < testValue)) {
                Alerts.showNumberOffInterval(minValue, maxValue);
                throw new NumberFormatException();
            }
        }

        return testValue;

    }

    /**
     * Pretizena metoda pro overeni, ze vstup je desetine cislo
     * Pro vice textovych vstupu
     * @param input list hodnot z textove podobe
     * @param field nazev pole, ze ktereho hodnota pochazi
     * @return Upravena hodnota na int
     */
    public static ArrayList<Double> isDoubleNumber(ArrayList<String> input, String field) {
        ArrayList<Double> list = new ArrayList<>();

        for (String number : input) {
            list.add(isDoubleNumber(number, field));
            ;
        }
        return list;
    }

    /**
     * Metoda pro overeni, ze vstup je desetine cislo
     * @param input hodnota textove podobe
     * @param field nazev pole, ze ktereho hodnota pochazi
     * @return Upravena hodnota na int
     * @throws NumberFormatException V pripade spatneho formatu je vyhozena tato vyjimka
     */
    public static Double isDoubleNumber(String input, String field) throws NumberFormatException {
        try {
            double number = Double.parseDouble(input);
            return number;
        } catch (NumberFormatException e) {
            Alerts.showWrongNumberFormat(field);
            throw e;
        }
    }

    /**
     * Pretizena metoda pro overeni, ze vstup je desetine cislo a je v zadanem intervalu
     * Pro vice textovych vstupu
     * @param input list hodnot z textove podobe
     * @param field nazev pole, ze ktereho hodnota pochazi
     * @param minValue minimalni hodnota intervalu
     * @param maxValue maximalni hodnota intervalu
     * @return Upravena hodnota na int
     */
    public static void isDoubleNumber(String input, String field, double minValue, double maxValue) {
        Double testValue = isDoubleNumber(input, field);

        if (testValue != null) {
            if ((minValue > testValue) || (maxValue < testValue)) {
                Alerts.showDoubleOffInterval(minValue, maxValue);
            }
        }

    }

    /**
     * Metoda sloužící k namapování prázdného nebo neexistujícího prvku na
     * prázdný element
     *
     * @param text text
     * @return vysledný řetězec
     */
    public static String fillTextMapper(String text) {

        if (text.equals("null*")) {
            return "";
        }

        return text;

    }

    /**
     * Metoda pro kontrolu vyplneneho textoveho pole
     * @param texts seznam textu pro kontrolu
     * @return vraceni zkontolovanych textu
     */
    public static ArrayList<String> fillTextMapper(ArrayList<String> texts) {

        ArrayList<String> checkTexts = new ArrayList<>();
        for (String text : texts) {
            checkTexts.add(fillTextMapper(text));
        }
        return checkTexts;

    }


    /**
     * Metoda pro kontrolu pole s Name
     * @param names seznam hodnot name
     * @return upravene hodnoty
     */
    public static ArrayList<String> fillNameTextMapper(ArrayList<String> names) {
        ArrayList<String> checkNames = new ArrayList<>();
        for (String name : names) {
            checkNames.add(fillNameTextMapper(name));
        }
        return checkNames;
    }

    /**
     * Metoda pro upravu pole se jmenem
     * @param name textovy vstup
     * @return upraveny textovy retezec
     */
    public static String fillNameTextMapper(String name) {
        if (name.equals("null*")) {
            return "";
        }
        return name;
    }

    /**
     * Pretizena metoda pro kontrolu zadanych dat
     * @param date zadane datum
     * @return upravene datum
     */
    public static ArrayList<LocalDate> checkDate(ArrayList<LocalDate> date) {
        ArrayList<LocalDate> list = new ArrayList<>();
        for (LocalDate date1 : date) {
            list.add(checkDate(date1));
        }
        return list;
    }

    /**
     * Metoda pro kontrolu zadanych dat
     * @param date Date
     * @return upravene Date
     */
    public static LocalDate checkDate(LocalDate date) {
        if (date == null) {
            return Constans.nullDate;
        }
        return date;
    }
}
