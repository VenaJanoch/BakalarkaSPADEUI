package controllers;

import javafx.collections.ObservableList;
import model.IdentificatorCreater;
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

public class DataPreparer {

    private IdentificatorCreater identificatorCreater;

    public DataPreparer(IdentificatorCreater identificatorCreater) {
        this.identificatorCreater = identificatorCreater;
    }

    private String prepareTableName(String name, int id) {
        if (name == null) {
            name = "";
        }
        return id + "_" + name;
    }

    public String prepareDependencyArray(List dependencyArray, ObservableList observableList) {
        String dependency = "";
        if (dependencyArray != null) {
            dependency = prepareIndexForTable(dependencyArray, observableList).toString();
        }

        return dependency;
    }

    public String prepareDependency(int dependencyIndex, ObservableList observableList) {
        String dependency = "";
        if (dependencyIndex != -1) {
            dependency = observableList.get(dependencyIndex + 1).toString();
        }

        return dependency;
    }

    public MilestoneTable prepareMilestoneTable(String nameST, int id) {

        String idName = prepareTableName(nameST, id);
        //   String criterion = prepareDependencyArray(criterionArray, observableList);

        return new MilestoneTable(idName, true, id);
    }

    public PersonTable prepareRoleTable(String name, String description, int id, int type, ObservableList<BasicTable> roleTypeObservable) {
        String idName = prepareTableName(name, id);
        String typeName = prepareDependency(type, roleTypeObservable);
        return new PersonTable(idName, true, id);
    }

    public CPRTable prepareCPRTable(String name, int id) {
        String idName = prepareTableName(name, id);
        //  String roleName = prepareDependency(roleIndex, roleObservable) ;
        return new CPRTable(idName, "", true, id);
    }

    public BranchTable prepareBranchTable(String name, boolean main, int id) {
        String idName = prepareTableName(name, id);
        String mainST = "NO";
        if (main) {
            mainST = "YES";
        }
        return new BranchTable(idName, mainST, main, true, id);
    }


    public String createTableItemIdName(int id, String name) {
        return id + "_" + prepareStringForForm(name);
    }

    public ArrayList<ArrayList<Integer>> prepareIndicesForForm(ArrayList<ArrayList<Integer>> indices) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (List<Integer> i : indices) {
            list.add(prepareIndiciesForForm(i));
        }
        return list;
    }


    public ArrayList<Integer> prepareIndiciesForForm(List<Integer> indicies) {
        ArrayList<Integer> formIndicies = new ArrayList<>();

        for (int i : indicies) {
            formIndicies.add(prepareIndexForForm(i));
        }
        return formIndicies;
    }

    public int prepareIndexForForm(Integer index) {
        if (index == null) {
            return 0;
        }
        return index + 1;
    }

    public ArrayList<String> prepareIndexForTable(List<Integer> indexs, ObservableList observableList) {
        ArrayList values = new ArrayList();
        for (int i : indexs) {
            values.add(observableList.get(i + 1));
        }
        return values;
    }

    public String prepareStringForForm(String text) {
        if (text == null) {
            return "";
        }
        return text;
    }


    public String prepareEstimateForForm(Double estimate) {

        if (estimate == -1.0 || estimate == null) {
            return "";
        }


        return String.valueOf(estimate);
    }

    public int prepareIndexForManipulator(int index) {
        //if(index != 0){
        //  return  index -1;
        //}
        return index - 1;
    }

    public ArrayList<Integer> prepareIndexForManipulator(List<Integer> indices) {
        ArrayList<Integer> tmpIndices = new ArrayList<>();
        for (int index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    public ArrayList<ArrayList<Integer>> prepareIndicesForManipulator(ArrayList<ArrayList<Integer>> indices) {
        ArrayList<ArrayList<Integer>> tmpIndices = new ArrayList<>();
        for (List<Integer> index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    public ArrayList<Integer> prepareCanvasItemIndexForManipulator(Set<Integer> keys) {

        ArrayList<Integer> indices = new ArrayList<>();
        for (Integer i : keys) {
            indices.add(identificatorCreater.getWorkUnitIndex(i));
        }

        return indices;
    }

    public ArrayList<Integer> prepareIndexForMultiComboBox(List<Integer> indexs) {

        ArrayList<Integer> values = new ArrayList();
        for (int i : indexs) {
            values.add(prepareIndexForForm(i));
        }
        return values;
    }

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
        return localDate;
    }

    public ArrayList convertDoubleToString(List estimates) {
        ArrayList list = new ArrayList<Double>();
        for (double estimate : (List<Double>) estimates) {
            list.add(String.valueOf(estimate));
        }

        return list;
    }

    public ArrayList convertIntToString(List progresses) {
        ArrayList list = new ArrayList<Integer>();
        for (int progress : (List<Integer>) progresses) {
            list.add(String.valueOf(progress));
        }

        return list;
    }
}
