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
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToCPRMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> artifactToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToMilestone;
    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToConfigurationMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> iterationToConfigurationMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> changeToArtifactMapper;


    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> roleMaps;
    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> configurationMaps;

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
        this.CPRToRoleMapper = new HashMap<>();
        this.configurationToRoleMapper = new HashMap<>();
        this.configurationToCPRMapper = new HashMap<>();
        this.configurationToBranchMapper = new HashMap<>();
        this.phaseToMilestone = new HashMap<>();
        this.phaseToConfigurationMapper = new HashMap<>();
        this.iterationToConfigurationMapper = new HashMap<>();
        this.changeToArtifactMapper = new HashMap<>();



        this.roleMaps = new ArrayList<>();
        this.configurationMaps = new ArrayList<>();
        initRoleMapsList();
        initConfigurationMapList();
    }

    private void initConfigurationMapList(){
        configurationMaps.add(phaseToConfigurationMapper);
        configurationMaps.add(iterationToConfigurationMapper);
    }
    private void initRoleMapsList(){
        roleMaps.add(wuToRoleMapper);
        roleMaps.add(configurationToRoleMapper);
        roleMaps.add(artifactToRoleMapper);
        roleMaps.add(CPRToRoleMapper);
    }

    public void mapTableToObjects(SegmentType segmentType, ArrayList<ArrayList<Integer>> indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstancesToMap(indexList, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            default:

        }
    }

    public void mapTableToObject(SegmentType segmentType, ArrayList<Integer> index, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstanceToMap(index, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            case Person:
                addInstanceToMap(index, lists.getRoleTypeObservable(), instanc, roleToRoleTypeMapper);
                break;
            case Artifact:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, artifactToRoleMapper);
                break;
            case Config_Person_Relation:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, CPRToRoleMapper);
                break;
            case Configuration:
                addInstanceToMap(index, lists.getRoleObservable(), instanc, configurationToRoleMapper);
                break;
            case Iteration:
                addInstanceToMap(index, lists.getConfigObservable(), instanc, iterationToConfigurationMapper);
                break;
            case Change:
                addInstanceToMap(index, lists.getArtifactObservable(), instanc, changeToArtifactMapper);
                break;
            default:

        }
    }

    public void mapTableToWU(ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex,
                             ArrayList<Integer> severityIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex,
                             ArrayList<Integer> statusIndex, int indexForm, String WUName) {
        addInstanceToMap(priorityIndex, lists.getPriorityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), priorityToWUMapper);
        addInstanceToMap(severityIndex, lists.getSeverityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), severityToWUMapper);
        addInstanceToMap(typeIndex, lists.getTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), typeToWUMapper);
        addInstanceToMap(statusIndex, lists.getStatusTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), statusToWUMapper);
        addInstanceToMap(resolutionIndex, lists.getResolutionTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), resolutionToWUMapper);
        addInstanceToMap(assigneIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), wuToRoleMapper);
        addInstanceToMap(authorIndex, lists.getRoleObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), wuToRoleMapper);
    }

    public void mapTableToConfiguration(ArrayList<Integer> roleIndex, ArrayList<ArrayList<Integer>> cprIndicies, ArrayList<ArrayList<Integer>> branches,
                                        String configName, int configIndex){
        addInstanceToMap(roleIndex, lists.getRoleObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToRoleMapper);
        addInstancesToMap(cprIndicies, lists.getCPRObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToCPRMapper);
        addInstancesToMap(branches, lists.getBranchObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToBranchMapper);
    }

    public void mapTableToPhase(ArrayList<Integer> milestoneId, ArrayList<Integer> configurationId, String phaseName, int phaseId){
        addInstanceToMap(milestoneId, lists.getMilestoneObservable(), new TableToObjectInstanc(phaseName, phaseId, SegmentType.Phase), phaseToMilestone);
        addInstanceToMap(configurationId, lists.getConfigObservable(), new TableToObjectInstanc(phaseName, phaseId, SegmentType.Phase), phaseToConfigurationMapper);
    }




    private void addInstanceToMap(ArrayList<Integer> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                 Map<Integer, ArrayList<TableToObjectInstanc>> map ) {
        for (int index : criterionIndex) {
            addInstanceToMap(index, list, instanc, map);
    }
    }

    private void addInstancesToMap(ArrayList<ArrayList<Integer>> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                  Map<Integer, ArrayList<TableToObjectInstanc>> map ) {
        for (ArrayList<Integer> index : criterionIndex) {
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


    public void updateValueList(ArrayList<Integer> criterionIndicies, Map<Integer, ArrayList<TableToObjectInstanc>> map, int id, String name) {
        for (int i : criterionIndicies) {
            updateValueList(i, map, i, name);
        }

    }
    public void updateValueList(int key, Map<Integer, ArrayList<TableToObjectInstanc>> map, int id, String name) {
            ArrayList<TableToObjectInstanc> objectList = map.get(key);
            if (objectList != null) {
                for (TableToObjectInstanc instanc : objectList) {
                    if (instanc.getId() == id) {
                        instanc.setName(name);
                    }
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

    public void updateValueList(Map<Integer, ArrayList<TableToObjectInstanc>> map, int ids) {
        for (int i : map.keySet()) {
            ArrayList<TableToObjectInstanc> objectList = map.get(i);
            ArrayList<TableToObjectInstanc> objectListTmp = new ArrayList<>();
            if (objectList != null) {
                objectListTmp.addAll(objectList);
                for (int j = objectListTmp.size() - 1; j >= 0; j--) {

                        if (objectListTmp.get(j).getId() == ids) {
                            objectList.remove(j);

                    }

                }
            }
        }

    }


    public void deleteFromMap(Map<Integer, ArrayList<TableToObjectInstanc>> map, ArrayList<Integer> dependencCriterion){
        for (int i : dependencCriterion) {
            deleteFromMap(map, i);
        }
    }

    public void deleteFromMap(Map<Integer, ArrayList<TableToObjectInstanc>> map, int dependencCriterion){
        map.remove(dependencCriterion);
    }

    public void deleteFromRoleMaps(ArrayList<Integer> dependencyRole) {
        for (int i : dependencyRole) {
            wuToRoleMapper.remove(i);
            CPRToRoleMapper.remove(i);
            configurationToRoleMapper.remove(i);
            artifactToRoleMapper.remove(i);
        }

    }

    public void deleteFromConfigurationMaps(ArrayList<Integer> dependencyConfiguration) {
        for (int i : dependencyConfiguration) {
            phaseToConfigurationMapper.remove(i);
            iterationToConfigurationMapper.remove(i);
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

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToCPRMapper() {
        return configurationToCPRMapper;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getRoleMaps() {
        return roleMaps;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getConfigurationMap() {
        return configurationMaps;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPhaseToMilestone() {
        return phaseToMilestone;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToBranchMapper() {
        return configurationToBranchMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPhaseToConfigurationMapper() {
        return phaseToConfigurationMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getIterationToConfigurationMapper() {
        return iterationToConfigurationMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getChangeToArtifactMapper() {
        return changeToArtifactMapper;
    }
}
