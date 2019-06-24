package controllers;

import interfaces.IFormDataController;
import javafx.collections.ObservableList;
import model.IdentificatorCreater;
import services.Constans;
import tables.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**
 * Trida slouzici pro upravu dat z formularu nebo datoveho modelu
 *
 * @author Václav Janoch
 */
public class DataPreparer {

    /**Metoda pro spojeni identificatoru a jmena**/
    private String prepareTableName(String name, int id) {
        if (name == null) {
            name = "";
        }
        return id + "_" + name;
    }


    /**
     * Metoda pro upravu indexu v seznamu seznamu
     * @param indices seznam seznamu indexu
     * @return upravene indexi
     */
    public ArrayList<ArrayList<Integer>> prepareIndicesForForm(ArrayList<ArrayList<Integer>> indices) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (List<Integer> i : indices) {
            list.add(prepareIndiciesForForm(i));
        }
        return list;
    }

    public String prepareDependency(int dependencyIndex, ObservableList observableList) {
        String dependency = "";
        if (dependencyIndex != -1) {
            dependency = observableList.get(dependencyIndex + 1).toString();
        }

        return dependency;
    }

    /**
     * Metoda pro vytvoreni instace MilestoneTable
     * @param nameST Jmeno elementu
     * @param id identifikator elementu
     * @return nova instace MilestoneTable
     */
    public MilestoneTable prepareMilestoneTable(String nameST, int id) {

        String idName = prepareTableName(nameST, id);
        return new MilestoneTable(idName, true, id);
    }

    /**
     * Metoda upravujici indexi z datoveho modelu pro formulare
     * @param indicies seznam indexu z datoveho modelu
     * @return upraveny seznam indexu z datoveho modelu pro formular
     */
    public ArrayList<Integer> prepareIndiciesForForm(List<Integer> indicies) {
        ArrayList<Integer> formIndicies = new ArrayList<>();

        for (int i : indicies) {
            formIndicies.add(prepareIndexForForm(i));
        }
        return formIndicies;
    }

    /**
     * Metoda pro upravu indexu z datoveho modelu pro formular
     * @param index index pro upraveni
     * @return upraveny index
     */
    public int prepareIndexForForm(Integer index) {
        if (index == null) {
            return 0;
        }
        return index + 1;
    }

    /**
     * Metoda pro upravu indexu z formulare pro datovy model
     * @param index index z formulare
     * @return upraveny index
     */
    public int prepareIndexForManipulator(int index) {
        return index - 1;
    }

    /**
     * Metoda pro upravu indexu z formulare pro model
     * @param indices seznam s indexi pro upravu
     * @return seznam upravenych indexu
     */
    public ArrayList<Integer> prepareIndexForManipulator(List<Integer> indices) {
        ArrayList<Integer> tmpIndices = new ArrayList<>();
        for (int index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    /**
     * Metoda pro upravu indexi v seznamu seznamu
     * @param indices seznam se seznami indexu
     * @return upravene seznamy
     */
    public ArrayList<ArrayList<Integer>> prepareIndicesForManipulator(ArrayList<ArrayList<Integer>> indices) {
        ArrayList<ArrayList<Integer>> tmpIndices = new ArrayList<>();
        for (List<Integer> index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    /**
     * Metoda pro prevod data ve formatu XMLGregorianCalendar do formatu LocalDate
     * @param dateXML instace XMLGregorianCalendar
     * @return prevedeny datum do LocalDate
     */
    public static ArrayList<LocalDate> prepareDateForForm(List<XMLGregorianCalendar> dateXML) {
        ArrayList<LocalDate> values = new ArrayList();
        for (XMLGregorianCalendar date : dateXML) {
            values.add(convertDateFromXML(date));
        }
        return values;
    }

    /**
     * Umožní převedení data ve formátu XMLGregorianCalendar uloženého v XML do
     * formátu LocalDate
     *
     * @param xmlDate XMLGregorianCalendar
     * @return LocalDate
     */
    public static LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

        if (xmlDate == null) {
            return null;
        }

        Date date = xmlDate.toGregorianCalendar().getTime();
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();

        if (localDate.equals(Constans.nullDate)){
            return null;
        }

        return localDate;
    }

    /**
     * Metoda pro prevod double do textoveho retezce
     * @param estimates dooble hodnota pro prevedeni
     * @return prevedena hodnota
     */
    public ArrayList convertDoubleToString(List estimates) {
        ArrayList list = new ArrayList<Double>();
        for (double estimate : (List<Double>) estimates) {
            list.add(String.valueOf(estimate));
        }

        return list;
    }

    /**
     * Metoda pro vytoveni noveho instace CPRTable
     * @param name jmeno pro instaceni
     * @param id identifikator instace
     * @return nova instace CPRTable
     */
    public CPRTable prepareCPRTable(String name, int id) {
        String idName = prepareTableName(name, id);
        return new CPRTable(idName, "", true, id);
    }

    /**
     * Metoda pro prevod int do textoveho retezce
     * @param progresses dooble hodnota pro prevedeni
     * @return prevedena hodnota
     */
    public ArrayList convertIntToString(List progresses) {
        ArrayList list = new ArrayList<Integer>();
        for (int progress : (List<Integer>) progresses) {
            list.add(String.valueOf(progress));
        }

        return list;
    }
}
