package Controllers;

import javafx.collections.ObservableList;
import model.IdentificatorCreater;
import services.SegmentLists;
import tables.BasicTable;
import tables.MilestoneTable;
import tables.RoleTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DataPreparer {

    private IdentificatorCreater identificatorCreater;

    public DataPreparer(IdentificatorCreater identificatorCreater){
        this.identificatorCreater = identificatorCreater;
    }

    private String prepareTableName(String name, int id){
        if(name == null){
            name = "";
        }
        return id + "_" + name;
    }

    private String prepareDependencyArray(List dependencyArray, ObservableList observableList){
        String dependency = "";
        if (dependencyArray != null) {
            dependency = prepareIndexForTable(dependencyArray, observableList).toString();
        }

        return dependency;
    }

    private String prepareDependency(int dependencyIndex, ObservableList observableList){
        String dependency = "";
        if (dependency != null || dependencyIndex != -1) {
            dependency = observableList.get(dependencyIndex + 1).toString();
        }

        return dependency;
    }

    public MilestoneTable prepareMilestoneTable(String nameST, int id, List criterionArray, ObservableList observableList) {

        String idName = prepareTableName(nameST, id);
        String criterion = prepareDependencyArray(criterionArray, observableList);

        return new MilestoneTable(idName, criterion, id);
    }

    public RoleTable prepareRoleTable(String name, String description, int id, int type, ObservableList<BasicTable> roleTypeObservable) {
        String idName = prepareTableName(name, id);
        String typeName = prepareDependency(type, roleTypeObservable) ;
        return new RoleTable(idName, description, typeName,id);
    }

    public String createTableItemIdName(int id, String name){
        return id + "_" + prepareStringForForm(name);
    }

    public int prepareIndexForForm(Integer index){
        if((Integer)index == null){
            return 0;
        }
        return index + 1;
    }

    public ArrayList<String> prepareIndexForTable(List<Integer> indexs, ObservableList observableList){
        ArrayList values = new ArrayList();
        for (int i : indexs){
            values.add(observableList.get(i + 1));
        }
        return values;
    }

    public String prepareStringForForm(String text){
        if (text == null){
            return "";
        }
        return text;
    }


    public int prepareIndexForManipulator(int index){
        //if(index != 0){
        //  return  index -1;
        //}
        return index - 1;
    }

    public ArrayList<Integer> prepareIndicesForManipulator(List<Integer> indices) {
        ArrayList<Integer> tmpIndices = new ArrayList<>();
        for (int index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    public ArrayList<Integer> prepareCanvasItemIndexForManipulator(Set<Integer> keys){

        ArrayList<Integer> indices = new ArrayList<>();
        for(Integer i : keys){
            indices.add(identificatorCreater.getWorkUnitIndex(i));
        }

        return indices;
    }


}
