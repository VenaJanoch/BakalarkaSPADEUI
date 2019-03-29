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
            case Person:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getRoleToRoleTypeMapper());
            case Role_Type:
                break;
            case Config_Person_Relation:
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

    public ArrayList<Integer> findIndicesForDeleteData(SegmentType segmentType, SegmentType elementType, ArrayList<Integer> idList) {

        switch (segmentType) {
            case Phase:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getPhaseToMilestone());
            case Work_Unit:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getPhaseToMilestone());
            case Branch:
                break;
            case Priority:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getPriorityToWUMapper());
            case Severity:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getSeverityToWUMapper());
            case Milestone:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getMilestoneToCriterionMapper());
            case Criterion:
                break;
            case Person:
                switch (elementType){
                    case Work_Unit:
                        findTableToObjectIndicesForDelete(idList, mapperTableToObject.getRoleMaps().get(0) );
                    break;
                    case Configuration:
                        findTableToObjectIndicesForDelete(idList, mapperTableToObject.getRoleMaps().get(1) );
                        break;
                    case Artifact:
                        findTableToObjectIndicesForDelete(idList, mapperTableToObject.getRoleMaps().get(2) );
                        break;
                    case Config_Person_Relation:
                        findTableToObjectIndicesForDelete(idList, mapperTableToObject.getRoleMaps().get(3) );
                        break;
                    default:
                }
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getRoleToRoleTypeMapper());
            case Role_Type:
                break;
            case Config_Person_Relation:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getCPRToRoleMapper());
            case Relation:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getRelationToWUMapper());
            case Resolution:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getResolutionToWUMapper());
            case Status:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getStatusToWUMapper());
            case Type:
                return findTableToObjectIndicesForDeleteData(idList, mapperTableToObject.getTypeToWUMapper());
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
            indexDataList.add(idCreater.getWorkUnitIndexToIdMaper().get(i));
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
        ArrayList idForDelete = new ArrayList();

        for (BasicTable table : tables) {
            idForDelete.add(table.getId());
        }
        return idForDelete;
    }

    private ArrayList<Integer> findTableToObjectIndicesForDelete(ArrayList<Integer> id, Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        ArrayList<Integer> listToDelete = new ArrayList<>();
        for (Integer i : id) {
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
