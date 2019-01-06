package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import tables.*;

public class DeleteControl {

    /**
     * Globální proměnné třídy
     **/

    private SegmentLists lists;
    private MapperTableToObject mapperTableToObject;


    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     */
    public DeleteControl(SegmentLists lists, MapperTableToObject mapperTableToObject) {

        this.lists = lists;
        this.mapperTableToObject = mapperTableToObject;
    }

    public ArrayList<Integer> findIndicesForDelete(SegmentType segmentType, ArrayList<Integer> indicesList) {

        switch (segmentType) {
            case Branch:
                break;
            case Priority:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getPriorityToWUMapper());
            case Severity:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getSeverityToWUMapper());
            case Milestone:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getMilestoneToCriterionMapper());
            case Criterion:
                break;
            case Role:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getRoleToRoleTypeMapper());
            case RoleType:
                break;
            case ConfigPersonRelation:
                break;
            case Relation:
                break;
            case Resolution:
                break;
            case Status:
                break;
            case Type:
                break;
            case Configuration:
                break;
            default:

        }
        return null;
    }

    /**
     * Vymazání informací o elementu Tag a smazání ze seznamů
     *
     * @param tables
     */
    public ArrayList deleteTag(ObservableList<TagTable> tables) {
        ArrayList indexForDelete = new ArrayList();

        for (TagTable table : tables) {

            int index = lists.getPriorityTypeObservable().indexOf(table.getTag());
            indexForDelete.add(index);
        }
        return indexForDelete;
    }

    public ArrayList<Integer> findIndicesForDelete(ArrayList<BasicTable> tables) {
        ArrayList indexForDelete = new ArrayList();

        for (BasicTable table : tables) {
            indexForDelete.add(table.getId());
        }
        return indexForDelete;
    }

    private ArrayList<Integer> findTableToObjectIndicesForDelete(ArrayList<Integer> indices, Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        ArrayList<Integer> listToDelete = new ArrayList<>();
        for (Integer i : indices) {
            List<TableToObjectInstanc> tabToObjList = map.get(i);
            if (tabToObjList != null) {
                for (TableToObjectInstanc j : tabToObjList) {
                    if (!listToDelete.contains(j.getId())) {
                        listToDelete.add(j.getId());
                    }
                }
            }
        }
        return listToDelete;
    }
}
