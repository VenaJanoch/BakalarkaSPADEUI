package Controllers;

import services.Alerts;

public class InputController {

    public static Integer isNumber(String input){
        try {
            int number = Integer.parseInt(input);
            return number;
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongNumberFormat();
        }
        return null;
    }

    public static void isNumber(String input, int minValue, int maxValue) {
        Integer testValue = isNumber(input);

        if(testValue != null){
            if((minValue > testValue) || (maxValue < testValue)){
                Alerts.showNumberOffInterval(minValue, maxValue);
            }
        }

    }

    public static Double isDoubleNumber(String input){
        try {
            double number = Double.parseDouble(input);
            return number;
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongDoubleFormat();
        }
        return null;
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


        if (text == null || text.equals("")) {
            return null;
        }

        return text;

    }

}
