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
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleToCPR;
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleToConfiguration;
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleToArtifact;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configToCPR;


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
        this.roleToWUMapper = new HashMap<>();
        this.roleToArtifact = new HashMap<>();
        this.roleToConfiguration = new HashMap<>();
        this.roleToCPR = new HashMap<>();
        this.configToCPR = new HashMap<>();
    }

    public void mapTableToObject(SegmentType segmentType, ArrayList<Integer> indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstanceToMap(indexList, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            case Configuration:
                addInstanceToMap(indexList, lists.getRoleObservable(), instanc, roleToConfiguration);
                break;
            default:

        }
    }

    public void mapTableToObject(SegmentType segmentType, int index, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Role:
                addInstanceToMap(index, lists.getRoleTypeObservable(), instanc, roleToRoleTypeMapper);
                break;
            case Artifact:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, roleToArtifact);
                break;
            case ConfigPersonRelation:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, roleToCPR);
                break;
            case Configuration:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, roleToConfiguration);
                break;
            default:

        }
    }

    public void mapTableToWU(int assigneIndex, int authorIndex, int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex,
                             int statusIndex, int indexForm, String WUName) {
        addInstanceToMap(priorityIndex, lists.getPriorityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), priorityToWUMapper);
        addInstanceToMap(severityIndex, lists.getSeverityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), severityToWUMapper);
        addInstanceToMap(typeIndex, lists.getTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), typeToWUMapper);
        addInstanceToMap(statusIndex, lists.getStatusTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), statusToWUMapper);
        addInstanceToMap(resolutionIndex, lists.getResolutionTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), resolutionToWUMapper);
        addInstanceToMap(assigneIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), roleToWUMapper );
        addInstanceToMap(authorIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), roleToWUMapper );
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
                ArrayList tmpList = map.get(key);
                if(!tmpList.contains(instanc)){
                    tmpList.add(instanc);
                }
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

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleToWUMapper() {
        return roleToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleToCPR() {
        return roleToCPR;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleToConfiguration() {
        return roleToConfiguration;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleToArtifact() {
        return roleToArtifact;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigToCPR() {
        return configToCPR;
    }
}
