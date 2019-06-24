package services;

import javafx.collections.ObservableList;
import tables.BasicTable;

import java.util.*;

public class MapperTableToObject {

    private SegmentLists lists;

    private Map<Integer, ArrayList<TableToObjectInstanc>> personToRoleTypeMapper; // Vazba mezi Role Type a Person, vychazejici z Person

    private Map<Integer, ArrayList<TableToObjectInstanc>> wuToRoleMapper; // Vazba mezi Role a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> CPRToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> artifactToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> commitToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> committedConfigurationToRoleMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToConfigurationMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> iterationToConfigurationMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToCommitedConfigurationMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> commitedConfigurationToCommitMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> changeToArtifactMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToArtifactMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToChangeMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToCPRMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToBranchMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> configurationToVCSTagMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> WUToPriorityMapper; // Vazba mezi Priority a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> WUToSeverityMapper; // Vazba mezi Severity a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> WUToResolutionMapper; // Vazba mezi Resolution a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> WUStatusMapper; // Vazba mezi Status a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> WUTotypeMapper; // Vazba mezi Type a WU, vychazejici z WU
    private Map<Integer, ArrayList<TableToObjectInstanc>> WUTORelationMapper; // Vazba mezi Relation a WU, vychazejici z WU

    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> iterationToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> activityToWUMapper;

    private Map<Integer, ArrayList<TableToObjectInstanc>> milestoneToCriterionMapper; //Vazba mezi Criterions a Milestone, vychazejici z Milestone
    private Map<Integer, ArrayList<TableToObjectInstanc>> phaseToMilestone;


    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> roleMaps;
    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> configurationMaps;
    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> artifactMaps;
    private ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> workUnitMaps;

    public MapperTableToObject(SegmentLists lists) {
        this.lists = lists;
        this.milestoneToCriterionMapper = new HashMap<>();
        this.personToRoleTypeMapper = new HashMap<>();
        this.WUToPriorityMapper = new HashMap<>();
        this.WUToSeverityMapper = new HashMap<>();
        this.WUToResolutionMapper = new HashMap<>();
        this.WUStatusMapper = new HashMap<>();
        this.WUTotypeMapper = new HashMap<>();
        this.WUTORelationMapper = new HashMap<>();
        this.wuToRoleMapper = new HashMap<>();
        this.artifactToRoleMapper = new HashMap<>();
        this.CPRToRoleMapper = new HashMap<>();
        this.configurationToRoleMapper = new HashMap<>();
        this.configurationToCPRMapper = new HashMap<>();
        this.configurationToChangeMapper = new HashMap<>();
        this.phaseToMilestone = new HashMap<>();
        this.phaseToConfigurationMapper = new HashMap<>();
        this.iterationToConfigurationMapper = new HashMap<>();
        this.changeToArtifactMapper = new HashMap<>();
        this.phaseToWUMapper = new HashMap<>();
        this.iterationToWUMapper = new HashMap<>();
        this.activityToWUMapper = new HashMap<>();
        this.configurationToBranchMapper = new HashMap<>();
        this.configurationToVCSTagMapper = new HashMap<>();
        this.configurationToCommitedConfigurationMapper = new HashMap<>();
        this.commitedConfigurationToCommitMapper = new HashMap<>();
        this.configurationToArtifactMapper = new HashMap<>();
        this.commitToRoleMapper = new HashMap<>();
        this.committedConfigurationToRoleMapper = new HashMap<>();
        this.roleMaps = new ArrayList<>();
        this.configurationMaps = new ArrayList<>();
        this.artifactMaps = new ArrayList<>();
        this.workUnitMaps = new ArrayList<>();
        initRoleMapsList();
        initConfigurationMapList();
        initArtifactMapList();
        initWorkUnitMapList();
    }

    private void initWorkUnitMapList() {
        workUnitMaps.add(phaseToWUMapper);
        workUnitMaps.add(iterationToWUMapper);
        workUnitMaps.add(activityToWUMapper);
    }

    private void initArtifactMapList() {
        artifactMaps.add(changeToArtifactMapper);
        artifactMaps.add(configurationToArtifactMapper);
    }


    private void initConfigurationMapList() {
        configurationMaps.add(phaseToConfigurationMapper);
        configurationMaps.add(iterationToConfigurationMapper);
    }

    private void initRoleMapsList() {
        roleMaps.add(wuToRoleMapper);
        roleMaps.add(configurationToRoleMapper);
        roleMaps.add(artifactToRoleMapper);
        roleMaps.add(CPRToRoleMapper);
        roleMaps.add(commitToRoleMapper);
        roleMaps.add(committedConfigurationToRoleMapper);
    }

    public void mapTableToObjects(SegmentType segmentType, ArrayList<ArrayList<Integer>> indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Milestone:
                addInstancesToMap(indexList, lists.getCriterionObservable(), instanc, milestoneToCriterionMapper);
                break;
            default:

        }
    }

    public void mapTableToObjects(SegmentType segmentType, SegmentType destinationSegment, int indexList, TableToObjectInstanc instanc) {

        switch (segmentType) {
            case Configuration:
                switch (destinationSegment) {
                    case Artifact:
                        addInstanceToMap(indexList, lists.getConfigObservable(), instanc, configurationToArtifactMapper);
                        break;
                    case Committed_Configuration:
                        addInstanceToMap(indexList, lists.getConfigObservable(), instanc, configurationToCommitedConfigurationMapper);
                        break;
                    case Person:
                        addInstanceToMap(indexList, lists.getPersonObservable(), instanc, configurationToRoleMapper);
                        break;
                    default:
                }

                break;
            case Committed_Configuration:
                switch (destinationSegment) {
                    case Commit:
                        addInstanceToMap(indexList, lists.getCommitedConfigurationObservable(), instanc, commitedConfigurationToCommitMapper);
                        break;
                    case Person:
                        addInstanceToMap(indexList, lists.getPersonObservable(), instanc, committedConfigurationToRoleMapper);
                        break;
                    default:
                }

                break;
            case Artifact:
                addInstanceToMap(indexList, lists.getPersonObservable(), instanc, artifactToRoleMapper);
                break;
            case Commit:
                addInstanceToMap(indexList, lists.getPersonObservable(), instanc, commitToRoleMapper);
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
                addInstanceToMap(index, lists.getRoleTypeObservable(), instanc, personToRoleTypeMapper);
                break;
            case Artifact:
                addInstanceToMap(index, lists.getPersonObservable(), instanc, artifactToRoleMapper);
                break;
            case Config_Person_Relation:
                addInstanceToMap(index, lists.getPersonObservable(), instanc, CPRToRoleMapper);
                break;
            case Configuration:
                addInstanceToMap(index, lists.getPersonObservable(), instanc, configurationToRoleMapper);
                break;
            case Change:
                addInstanceToMap(index, lists.getArtifactObservable(), instanc, changeToArtifactMapper);
            case Activity:
                addInstanceToMap(index, lists.getWorkUnitsObservable(), instanc, activityToWUMapper);
            default:

        }
    }

    public void mapTableToWU(ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex,
                             ArrayList<Integer> severityIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex,
                             ArrayList<Integer> statusIndex, int indexForm, String WUName) {
        addInstanceToMap(priorityIndex, lists.getPriorityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), WUToPriorityMapper);
        addInstanceToMap(severityIndex, lists.getSeverityTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), WUToSeverityMapper);
        addInstanceToMap(typeIndex, lists.getTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), WUTotypeMapper);
        addInstanceToMap(statusIndex, lists.getStatusTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), WUStatusMapper);
        addInstanceToMap(resolutionIndex, lists.getResolutionTypeObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), WUToResolutionMapper);
        addInstanceToMap(assigneIndex, lists.getPersonObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), wuToRoleMapper);
        addInstanceToMap(authorIndex, lists.getPersonObservable(), new TableToObjectInstanc(WUName, indexForm, SegmentType.Work_Unit), wuToRoleMapper);
    }

    public void mapTableToConfiguration(ArrayList<ArrayList<Integer>> branchesIndicies, ArrayList<ArrayList<Integer>> cprIndicies, ArrayList<ArrayList<Integer>> changes, ArrayList<Integer> tag,
                                        String configName, int configIndex) {
        addInstancesToMap(branchesIndicies, lists.getBranchObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToBranchMapper);
        addInstancesToMap(cprIndicies, lists.getCPRObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToCPRMapper);
        addInstancesToMap(changes, lists.getChangeObservable(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToChangeMapper);
        addInstanceToMap(tag, lists.getVCSTag(), new TableToObjectInstanc(configName, configIndex, SegmentType.Configuration), configurationToVCSTagMapper);
    }

    public void mapTableToPhase(ArrayList<Integer> milestoneId, ArrayList<Integer> configurationId, ArrayList<Integer> workUnitId, String phaseName, int phaseId) {
        addInstanceToMap(milestoneId, lists.getMilestoneObservable(), new TableToObjectInstanc(phaseName, phaseId, SegmentType.Phase), phaseToMilestone);
        addInstanceToMap(configurationId, lists.getConfigObservable(), new TableToObjectInstanc(phaseName, phaseId, SegmentType.Phase), phaseToConfigurationMapper);
        addInstanceToMap(workUnitId, lists.getWorkUnitsObservable(), new TableToObjectInstanc(phaseName, phaseId, SegmentType.Phase), phaseToWUMapper);
    }

    public void mapTableToIteration(ArrayList<Integer> configurationId, ArrayList<Integer> workUnitId, String iterationName, int iterationId) {
        addInstanceToMap(configurationId, lists.getConfigObservable(), new TableToObjectInstanc(iterationName, iterationId, SegmentType.Iteration), iterationToConfigurationMapper);
        addInstanceToMap(workUnitId, lists.getWorkUnitsObservable(), new TableToObjectInstanc(iterationName, iterationId, SegmentType.Iteration), iterationToWUMapper);
    }

    private void addInstanceToMap(ArrayList<Integer> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                  Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        for (int index : criterionIndex) {
            addInstanceToMap(index, list, instanc, map);
        }
    }

    private void addInstancesToMap(ArrayList<ArrayList<Integer>> criterionIndex, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                   Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        for (ArrayList<Integer> index : criterionIndex) {
            addInstanceToMap(index, list, instanc, map);
        }
    }


    private void addInstanceToMap(int key, ObservableList<BasicTable> list, TableToObjectInstanc instanc,
                                  Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        if (key != -1) {
            if (map.containsKey(key)) {
                ArrayList tmpList = map.get(key);
                if (!tmpList.contains(instanc)) {
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

    public void clearValueList(int key, Map<Integer, ArrayList<TableToObjectInstanc>> map, int valueId) {
        ArrayList<TableToObjectInstanc> objectList = map.get(key);
        if (objectList != null) {
            for (int i = 0; i < objectList.size(); i++) {
                if (objectList.get(i).getId() == valueId) {
                    objectList.remove(i);
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


    public void deleteFromMap(Map<Integer, ArrayList<TableToObjectInstanc>> map, ArrayList<Integer> dependencCriterion) {
        for (int i : dependencCriterion) {
            deleteFromMap(map, i);
        }
    }

    public void deleteFromMap(Map<Integer, ArrayList<TableToObjectInstanc>> map, int dependencCriterion) {
        map.remove(dependencCriterion);
    }

    public void deleteFromRoleMaps(ArrayList<Integer> dependencyRole) {
        for (int i : dependencyRole) {
            deleteFromRoleMaps(i);
        }

    }

    public void deleteFromRoleMaps(int id) {
        wuToRoleMapper.remove(id);
        CPRToRoleMapper.remove(id);
        configurationToRoleMapper.remove(id);
        artifactToRoleMapper.remove(id);
        commitToRoleMapper.remove(id);
        committedConfigurationToRoleMapper.remove(id);
    }


    public void deleteFromConfigurationMaps(ArrayList<Integer> dependencyConfiguration) {
        for (int i : dependencyConfiguration) {
            deleteFromConfigurationMaps(i);
        }
    }

    public void deleteFromConfigurationMaps(int id) {
        phaseToConfigurationMapper.remove(id);
        iterationToConfigurationMapper.remove(id);
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

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPersonToRoleTypeMapper() {
        return personToRoleTypeMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUToPriorityMapper() {
        return WUToPriorityMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUToSeverityMapper() {
        return WUToSeverityMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUToResolutionMapper() {
        return WUToResolutionMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUStatusMapper() {
        return WUStatusMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUTotypeMapper() {
        return WUTotypeMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getWUTORelationMapper() {
        return WUTORelationMapper;
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


    public Map<Integer, ArrayList<TableToObjectInstanc>> getPhaseToConfigurationMapper() {
        return phaseToConfigurationMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getIterationToConfigurationMapper() {
        return iterationToConfigurationMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getChangeToArtifactMapper() {
        return changeToArtifactMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToCommitedConfigurationMapper() {
        return configurationToCommitedConfigurationMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getCommitedConfigurationToCommitMapper() {
        return commitedConfigurationToCommitMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToArtifactMapper() {
        return configurationToArtifactMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getPhaseToWUMapper() {
        return phaseToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getIterationToWUMapper() {
        return iterationToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getActivityToWUMapper() {
        return activityToWUMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToBranchMapper() {
        return configurationToBranchMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToVCSTagMapper() {
        return configurationToVCSTagMapper;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getConfigurationMaps() {
        return configurationMaps;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getArtifactMaps() {
        return artifactMaps;
    }

    public ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> getWorkUnitMaps() {
        return workUnitMaps;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getConfigurationToChangeMapper() {
        return configurationToChangeMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getCommitToRoleMapper() {
        return commitToRoleMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getCommittedConfigurationToRoleMapper() {
        return committedConfigurationToRoleMapper;
    }
}
