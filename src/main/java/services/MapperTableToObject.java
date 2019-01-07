package services;

import javafx.collections.ObservableList;
import tables.BasicTable;

import java.util.*;

public class MapperTableToObject {

    private SegmentLists lists;

    private Map<Integer, ArrayList<TableToObjectInstanc>> milestoneToCriterionMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleToRoleTypeMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> priorityToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> severityToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> resolutionToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> statusToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> typeToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> relationToWUMapper;


    public MapperTableToObject(SegmentLists lists) {
        this.lists = lists;
        this.milestoneToCriterionMapper = new HashMap<>();
        this.roleToRoleTypeMapper = new HashMap<>();
        this.priorityToWUMapper = new HashMap<>();
        this.severityToWUMapper = new HashMap<>();
        this.resolutionToWUMapper = new HashMap<>();
        this.statusToWUMapper = new HashMap<>();
        this.typeToWUMapper = new HashMap<>();
        this.relationToWUMapper = new HashMap<>();
    }

    public void mapTableToObject(SegmentType segmentType, ArrayList<Integer> indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstanceToMap(indexList, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            default:

        }
    }

    public void mapTableToObject(SegmentType segmentType, int index, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Branch:
             //   addInstanceToMap(indexList, lists.getCriterionObservable(), instanc, );
                break;
            case Priority:
                addInstanceToMap(index, lists.getPriorityTypeObservable(), instanc, priorityToWUMapper);
                break;
            case Severity:
                addInstanceToMap(index, lists.getSeverityTypeObservable(), instanc, severityToWUMapper);
                break;
            case Role:
                addInstanceToMap(index, lists.getRoleTypeObservable(), instanc, roleToRoleTypeMapper);
                break;
            case ConfigPersonRelation:
                break;
            case Relation:
                addInstanceToMap(index, lists.getRelationTypeObservable(), instanc, relationToWUMapper);
                break;
            case Resolution:
                addInstanceToMap(index, lists.getResolutionTypeObservable(), instanc, resolutionToWUMapper);
                break;
            case Status:
                addInstanceToMap(index, lists.getStatusTypeObservable(), instanc, statusToWUMapper);
                break;
            case Type:
                addInstanceToMap(index, lists.getTypeObservable(), instanc, typeToWUMapper);
                break;
            case Configuration:
                break;
            default:

        }
    }

    private void addInstanceToMap(ArrayList<Integer> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                 Map<Integer, ArrayList<TableToObjectInstanc>> map ) {
        for (int index : criterionIndex) {
            addInstanceToMap(index, list, instanc, map);
        }
    }

    private void addInstanceToMap(int index, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                 Map<Integer, ArrayList<TableToObjectInstanc>> map ){
        int key = list.get(index).getId();
        if (key != -1) {
            if (map.containsKey(key)) {
                map.get(key).add(instanc);
            } else {
                ArrayList<TableToObjectInstanc> listName = new ArrayList<>();
                listName.add(instanc);
                map.put(key, listName);
            }
        }
    }


    public void updateValueList(Map<Integer, ArrayList<TableToObjectInstanc>> map, ArrayList<Integer> dependencCriterion, ArrayList<Integer> ids) {
        for (int i : map.keySet()) {
            ArrayList<TableToObjectInstanc> objectList = map.get(i);
            ArrayList<TableToObjectInstanc> objectListTmp = new ArrayList<>();
            if (objectList != null) {
                objectListTmp.addAll(objectList);
                for (int j = objectListTmp.size() - 1; j >= 0; j--) {
                    for (int k : ids) {
                        if (objectListTmp.get(j).getId() == k) {
                            objectList.remove(j);
                        }
                    }

                }
            }
        }

    }

    public void deleteFromMap(Map<Integer, ArrayList<TableToObjectInstanc>> map, ArrayList<Integer> dependencCriterion){
        for (int i : dependencCriterion) {
            map.remove(i);
        }
    }


    public void mapTableToWU(int assigneIndex, int authorIndex, int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex,
                             int statusIndex, int indexForm, String WUName) {
        mapTableToObject(SegmentType.Priority, priorityIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
        mapTableToObject(SegmentType.Severity, severityIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
       // mapTableToObject(SegmentType.Role, assigneIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
        // TODO: Upravit mapovani role do ostatnich segmentu ne jenom do Unitu
       // mapTableToObject(SegmentType.Role, authorIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
        mapTableToObject(SegmentType.Type, typeIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
        mapTableToObject(SegmentType.Status, statusIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );
        mapTableToObject(SegmentType.Resolution, resolutionIndex, new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit) );

    }

    /**
     * Getters and Setters
     **/

    public Map<Integer, ArrayList<TableToObjectInstanc>> getMilestoneToCriterionMapper() {
        return milestoneToCriterionMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleToRoleTypeMapper() {
        return roleToRoleTypeMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPriorityToWUMapper() {
        return priorityToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getSeverityToWUMapper() {
        return severityToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getResolutionToWUMapper() {
        return resolutionToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getStatusToWUMapper() {
        return statusToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getTypeToWUMapper() {
        return typeToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRelationToWUMapper() {
        return relationToWUMapper;
    }
}
