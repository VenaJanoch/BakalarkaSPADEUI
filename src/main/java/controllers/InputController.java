package controllers;

import services.Alerts;
import services.Constans;

import java.time.LocalDate;
import java.util.ArrayList;

public class InputController {

    public static Integer isNumber(String input, String fieldName){
        try {
            int number = Integer.parseInt(input);
            return number;
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongNumberFormat(fieldName);
        }
        return null;
    }

    public static void isNumber(String input, int minValue, int maxValue, String fieldName) {
        Integer testValue = isNumber(input, fieldName);

        if(testValue != null){
            if((minValue > testValue) || (maxValue < testValue)){
                Alerts.showNumberOffInterval(minValue, maxValue);
            }
        }

    }

    public static ArrayList<Double> isDoubleNumber(ArrayList<String> input) {
        ArrayList<Double> list = new ArrayList<>();

        for (String number : input){
            list.add(isDoubleNumber(number));;
        }
        return list;
    }


    public static Double isDoubleNumber(String input) throws NumberFormatException {
        try {
            double number = Double.parseDouble(input);
            return number;
        }catch (NumberFormatException e){
            throw e;
        }
    }

    public static void isDoubleNumber(String input, double minValue, double maxValue) {
        Double testValue = isDoubleNumber(input);

        if(testValue != null){
            if((minValue > testValue) || (maxValue < testValue)){
                Alerts.showDoubleOffInterval(minValue, maxValue);
            }
        }

    }

    /**
     * Metoda sloužící k namapování prázdného nebo neexistujícího prvku na
     * prázdný element
     *
     * @param text
     *            text
     * @return vysledný řetězec
     */
    public static String fillTextMapper(String text) {

        if (text.equals("null*")) {
            return "";
        }

        return text;

    }

    public static ArrayList<String> fillTextMapper(ArrayList<String> texts) {

        ArrayList<String> checkTexts = new ArrayList<>();
        for (String text : texts){
            checkTexts.add(fillTextMapper(text));
        }
        return checkTexts;

    }


    public static ArrayList<String> fillNameTextMapper(ArrayList<String> names) {
       ArrayList<String> checkNames = new ArrayList<>();
        for (String name : names){
            checkNames.add(fillNameTextMapper(name));
        }

        if (checkNames.size() == 0){
            checkNames.add("");
        }

    return checkNames;
    }

    public static String fillNameTextMapper(String name) {
        if(name.equals("null*")){
            return "";
        }
        return name;
    }

    public static ArrayList<LocalDate> checkDate(ArrayList<LocalDate> date) {
        ArrayList<LocalDate> list = new ArrayList<>();
        for (LocalDate date1 : date){
            list.add(checkDate(date1));
        }
        return date;
    }


    public static LocalDate checkDate(LocalDate date) {
        if(date.equals(Constans.nullDate)){
            return null;
        }
        return date;
    }
}
