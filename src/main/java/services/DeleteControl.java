package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.collections.ObservableList;
import model.IdentificatorCreater;
import tables.*;

public class DeleteControl {

    /**
     * Globální proměnné třídy
     **/

    private SegmentLists lists;
    private MapperTableToObject mapperTableToObject;
    private IdentificatorCreater idCreater;


    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     */
    public DeleteControl(SegmentLists lists, MapperTableToObject mapperTableToObject, IdentificatorCreater identificatorCreater) {

        this.lists = lists;
        this.mapperTableToObject = mapperTableToObject;
        this.idCreater = identificatorCreater;
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
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getRelationToWUMapper());
            case Resolution:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getResolutionToWUMapper());
            case Status:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getStatusToWUMapper());
            case Type:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getTypeToWUMapper());
            case Configuration:
                break;
            default:

        }
        return null;
    }

    public ArrayList<Integer> findIndicesForDeleteData(SegmentType segmentType, ArrayList<Integer> indicesList) {

        switch (segmentType) {
            case Branch:
                break;
            case Priority:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getPriorityToWUMapper());
            case Severity:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getSeverityToWUMapper());
            case Milestone:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getMilestoneToCriterionMapper());
            case Criterion:
                break;
            case Role:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getRoleToRoleTypeMapper());
            case RoleType:
                break;
            case ConfigPersonRelation:
                break;
            case Relation:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getRelationToWUMapper());
            case Resolution:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getResolutionToWUMapper());
            case Status:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getStatusToWUMapper());
            case Type:
                return findTableToObjectIndicesForDeleteData(indicesList, mapperTableToObject.getTypeToWUMapper());
            case Configuration:
                break;
            default:

        }
        return null;
    }

    private ArrayList<Integer> findTableToObjectIndicesForDeleteData(ArrayList<Integer> indicesList, Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        ArrayList<Integer> list = findTableToObjectIndicesForDelete(indicesList, map);
        ArrayList<Integer> indexDataList = new ArrayList<>();

        for(int i : list){
            indexDataList.add(idCreater.getWorkUnitIndexMaper().get(i));
        }

        return indexDataList;
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
