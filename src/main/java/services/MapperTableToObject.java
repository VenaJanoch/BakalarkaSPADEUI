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
    private Map<Integer, ArrayList<TableToObjectInstanc>> wuToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> CPRToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToBranchMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> artifactToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configToCPR;
    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToMilestone;


    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> roleMaps;

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
        this.wuToRoleMapper = new HashMap<>();
        this.artifactToRoleMapper = new HashMap<>();
        this.configurationToRoleMapper = new HashMap<>();
        this.CPRToRoleMapper = new HashMap<>();
        this.configToCPR = new HashMap<>();
        this.phaseToMilestone = new HashMap<>();
        this.configurationToBranchMapper = new HashMap<>();

        this.roleMaps = new ArrayList<>();
        initRoleMapsList();
    }

    private void initRoleMapsList(){
        roleMaps.add(wuToRoleMapper);
        roleMaps.add(configurationToRoleMapper);
        roleMaps.add(artifactToRoleMapper);
        roleMaps.add(CPRToRoleMapper);


    }

    public void mapTableToObject(SegmentType segmentType, ArrayList<Integer> indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstanceToMap(indexList, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            case Configuration:
                addInstanceToMap(indexList, lists.getRoleObservable(), instanc, configurationToRoleMapper);
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
                addInstanceToMap(index, lists.getRoleObservable(), instanc, artifactToRoleMapper);
                break;
            case ConfigPersonRelation:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, CPRToRoleMapper);
                break;
            case Configuration:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, configurationToRoleMapper);
                break;
            case Phase:
                addInstanceToMap(index, lists.getMilestoneObservable(), instanc, phaseToMilestone);
                break;
            case Branch: // Todo predelat na vetev pro Configuration
                addInstanceToMap(index, lists.getBranchObservable(), instanc, configurationToBranchMapper);
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
        addInstanceToMap(assigneIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), wuToRoleMapper);
        addInstanceToMap(authorIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.WorkUnit), wuToRoleMapper);
    }


    private void addInstanceToMap(ArrayList<Integer> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                 Map<Integer, ArrayList<TableToObjectInstanc>> map ) {
        for (int index : criterionIndex) {
            addInstanceToMap(index, list, instanc, map);
        }
    }

    private void addInstanceToMap(int key, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                 Map<Integer, ArrayList<TableToObjectInstanc>> map ){
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


    public void updateValueList(Map<Integer, ArrayList<TableToObjectInstanc>> map, ArrayList<Integer> ids) {
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

    public void deleteFromRoleMaps(ArrayList<Integer> dependencyRole) {
        for (int i : dependencyRole) {
            wuToRoleMapper.remove(i);
            CPRToRoleMapper.remove(i);
            configurationToRoleMapper.remove(i);
            artifactToRoleMapper.remove(i);
        }

    }

    public void updateRoleMaps(ArrayList<Integer> indexList) {
        updateValueList(wuToRoleMapper, indexList);
        updateValueList(CPRToRoleMapper, indexList);
        updateValueList(configurationToRoleMapper, indexList);
        updateValueList(artifactToRoleMapper, indexList);
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

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWuToRoleMapper() {
        return wuToRoleMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getCPRToRoleMapper() {
        return CPRToRoleMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToRoleMapper() {
        return configurationToRoleMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getArtifactToRoleMapper() {
        return artifactToRoleMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigToCPR() {
        return configToCPR;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getRoleMaps() {
        return roleMaps;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPhaseToMilestone() {
        return phaseToMilestone;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToBranchMapper() {
        return configurationToBranchMapper;
    }
}
