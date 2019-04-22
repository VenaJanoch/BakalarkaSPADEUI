package model;

import SPADEPAC.*;
import XML.ProcessGenerator;

import java.util.*;

public class DataManipulator{


    private ProcessGenerator procesGener;
    private DataModel dataModel;
    private ObjectFactory objF;

    public DataManipulator(ProcessGenerator processGenerator, DataModel dataModel) {
        this.procesGener = processGenerator;
        this.dataModel = dataModel;
        this.objF = dataModel.getObjF();
    }


    public void setRelationIndexToLink(int id, int relationIndex) {
 //   dataModel.getLinks().get(id).setRelationIndex(relationIndex);
    }

    public void copyDataFromActivity(int oldActivityId, int newActivityId) {
        Activity oldActivity = dataModel.getActivities().get(oldActivityId);
        Activity newActivity = dataModel.getActivities().get(newActivityId);

      //  newActivity.setCoordinates(oldActivity.getCoordinates());
     //   newActivity.setAlias(oldActivity.getAlias());
     //   newActivity.setDescription(oldActivity.getDescription());

       int workUnitSize = dataModel.getWorkUnits().size();
      //  for (int i =0; i < oldActivity.getWorkUnits().size(); i++){
      //      copyDataFromWorkUnit(oldActivity.getWorkUnits().get(i));
      //      newActivity.getWorkUnits().add(workUnitSize + i);
       // }
    }

    public void copyDataFromWorkUnit(int oldWUId){
        WorkUnit oldWu = dataModel.getWorkUnits().get(oldWUId);
        WorkUnit newWu = objF.createWorkUnit();
        copyDataFromWorkUnit(oldWu, newWu);
        dataModel.getWorkUnits().add(newWu);

    }

    public void copyDataFromWorkUnit(int oldWUId, int newWUId){
        WorkUnit oldWu = dataModel.getWorkUnits().get(oldWUId);
        WorkUnit newWu = dataModel.getWorkUnits().get(newWUId);
        copyDataFromWorkUnit(oldWu, newWu);

    }

    public  void copyDataFromWorkUnit(WorkUnit oldWu, WorkUnit newWu){
//        newWu.setResolutionIndex(oldWu.getResolutionIndex());
//        newWu.setStatusIndex(oldWu.getStatusIndex());
//        newWu.setTypeIndex(oldWu.getTypeIndex());
//        newWu.setSeverityIndex(oldWu.getSeverityIndex());
//        newWu.setPriorityIndex(oldWu.getPriorityIndex());
//        newWu.setAlias(oldWu.getAlias());
//        newWu.setExist(oldWu.isExist());
//        newWu.setEstimatedTime(oldWu.getEstimatedTime());
//        newWu.setDescription(oldWu.getDescription());
//        newWu.setCoordinates(oldWu.getCoordinates());
//        newWu.setCategory(oldWu.getCategory());
//        newWu.setAuthorIndex(oldWu.getAuthorIndex());
//        newWu.setAssigneeIndex(oldWu.getAssigneeIndex());
//        newWu.setRelationIndex(oldWu.getRelationIndex());
    }

    public void copyDataFromIteration(int oldIteratationId, int newIterationId) {
        Iteration oldIteration = dataModel.getIterations().get(oldIteratationId);
        Iteration newIteration = dataModel.getIterations().get(newIterationId);

//        newIteration.setCoordinates(oldIteration.getCoordinates());
//        newIteration.setAlias(oldIteration.getAlias());
//        newIteration.setDescription(oldIteration.getDescription());
//        newIteration.setStartDate(oldIteration.getStartDate());
//        newIteration.setEndDate(oldIteration.getEndDate());
//        newIteration.setConfiguration(oldIteration.getConfiguration());
//
//        int workUnitSize = dataModel.getWorkUnits().size();
//        for (int i =0; i < oldIteration.getWorkUnits().size(); i++){
//            copyDataFromWorkUnit(oldIteration.getWorkUnits().get(i));
//            newIteration.getWorkUnits().add(workUnitSize + i);
//        }

    }


    public void copyDataFromPhase(int phaseId, int newPhaseId) {
        Phase oldPhase = dataModel.getPhases().get(phaseId);
        Phase newPhase = dataModel.getPhases().get(newPhaseId);

//        newPhase.setCoordinates(oldPhase.getCoordinates());
//        newPhase.setAlias(oldPhase.getAlias());
//        newPhase.setDescription(oldPhase.getDescription());
//        newPhase.setEndDate(oldPhase.getEndDate());
//        newPhase.setConfiguration(oldPhase.getConfiguration());
//        newPhase.setConfiguration(oldPhase.getConfiguration());
//        newPhase.setMilestoneIndex(oldPhase.getMilestoneIndex());
//
//        int workUnitSize = dataModel.getWorkUnits().size();
//        for (int i =0; i < oldPhase.getWorkUnits().size(); i++){
//            copyDataFromWorkUnit(oldPhase.getWorkUnits().get(i));
//            newPhase.getWorkUnits().add(workUnitSize + i);
//        }

    }

    public void copyDataFromChange(int changeId, int newchangeId) {

        Change oldChange = dataModel.getChanges().get(changeId);
        Change newChange = dataModel.getChanges().get(newchangeId);

//        newChange.setCoordinates(oldChange.getCoordinates());
//        newChange.setAlias(oldChange.getAlias());
//        newChange.setDescriptoin(oldChange.getDescriptoin());
//        newChange.setExist(oldChange.isExist());
        }

    public void copyDataFromArtifact(int artifactId, int newArtifactId) {
        Artifact oldArtifact = dataModel.getArtifact(artifactId);
        Artifact newArtifact = dataModel.getArtifact(newArtifactId);

        newArtifact.setCoordinates(oldArtifact.getCoordinates());
        newArtifact.getName().addAll(oldArtifact.getName());
        newArtifact.getNameIndicator().addAll(oldArtifact.getNameIndicator());
        newArtifact.getDescription().addAll(oldArtifact.getDescription());
        newArtifact.getDescriptionIndicator().addAll(oldArtifact.getDescriptionIndicator());
        newArtifact.setExist(oldArtifact.isExist());
        newArtifact.getMimeType().addAll(oldArtifact.getMimeType());
        newArtifact.getMimeTypeIndex().addAll(oldArtifact.getMimeTypeIndex());
        newArtifact.getMimeTypeIndicator().addAll(oldArtifact.getMimeTypeIndicator());
        newArtifact.getCreated().addAll(oldArtifact.getCreated());
        newArtifact.getCreatedIndicator().addAll(oldArtifact.getCreatedIndicator());
        newArtifact.getAuthorIndex().addAll(oldArtifact.getAuthorIndex());
        newArtifact.getAuthorIndicator().addAll(oldArtifact.getAuthorIndicator());
    }


    public void copyDataFromCommitedConfiguration(int commitedConfigurationId, int newCommitedConfigurationId) {
        CommitedConfiguration oldCommitedConfiguration = dataModel.getCommitedConfiguration(commitedConfigurationId);
        CommitedConfiguration newCommitedConfiguration = dataModel.getCommitedConfiguration(newCommitedConfigurationId);

        newCommitedConfiguration.setCoordinates(oldCommitedConfiguration.getCoordinates());
        newCommitedConfiguration.getName().addAll(oldCommitedConfiguration.getName());
        newCommitedConfiguration.getNameIndicator().addAll(oldCommitedConfiguration.getNameIndicator());
        newCommitedConfiguration.setExist(oldCommitedConfiguration.isExist());
        newCommitedConfiguration.getCommitedDay().addAll(oldCommitedConfiguration.getCommitedDay());
        newCommitedConfiguration.getCommitedDayIndicator().addAll(oldCommitedConfiguration.getCommitedDayIndicator());
    }
    

    public void copyDataFromCommit(int commitId, int newCommitId) {
        Commit oldCommit = dataModel.getCommit(commitId);
        Commit newCommit = dataModel.getCommit(newCommitId);

        newCommit.setCoordinates(oldCommit.getCoordinates());
        newCommit.getName().addAll(oldCommit.getName());
        newCommit.getNameIndicator().addAll(oldCommit.getNameIndicator());
        newCommit.setExist(oldCommit.isExist());
        newCommit.getBranch().addAll(oldCommit.getBranch());
        newCommit.getBranchIndicator().addAll(oldCommit.getBranchIndicator());
        newCommit.getTags().addAll(oldCommit.getTags());
        newCommit.getTagsIndicator().addAll(oldCommit.getTagsIndicator());
    }
    

    public void copyDataFromConfiguration(int configurationId, int newConfigurationId) {
        Configuration oldConfiguration = dataModel.getConfiguration(configurationId);
        Configuration newConfiguration = dataModel.getConfiguration(newConfigurationId);

        newConfiguration.setCoordinates(oldConfiguration.getCoordinates());
        newConfiguration.getName().addAll(oldConfiguration.getName());
        newConfiguration.getNameIndicator().addAll(oldConfiguration.getNameIndicator());
        newConfiguration.setExist(oldConfiguration.isExist());
        newConfiguration.getCreated().addAll(oldConfiguration.getCreated());
        newConfiguration.getAuthorIndex().addAll(oldConfiguration.getAuthorIndex());
        newConfiguration.getAuthorIndicator().addAll(oldConfiguration.getAuthorIndicator());
        newConfiguration.getCPRsIndicator().addAll(oldConfiguration.getCPRsIndicator());
        newConfiguration.getCPRsIndexs().addAll(oldConfiguration.getCPRsIndexs());
        newConfiguration.getChangesIndicator().addAll(oldConfiguration.getChangesIndicator());
        newConfiguration.getChangesIndexs().addAll(oldConfiguration.getChangesIndexs());
        newConfiguration.getCreatedIndicator().addAll(oldConfiguration.getCreatedIndicator());
    }
    
    
    public void copyDataFromPerson(int artifactId, int newPersonId) {
        Person oldPerson = dataModel.getRole(artifactId);
        Person newPerson = dataModel.getRole(newPersonId);

        newPerson.setCoordinates(oldPerson.getCoordinates());
        newPerson.getName().addAll(oldPerson.getName());
        newPerson.getNameIndicator().addAll(oldPerson.getNameIndicator());
        newPerson.setExist(oldPerson.isExist());
        newPerson.getType().addAll(oldPerson.getType());
        newPerson.getTypeIndicator().addAll(oldPerson.getTypeIndicator());
    }

    public List[] getCriterionData(int id) {
        List[] data = new List[5];
        Criterion criterion = dataModel.getCriterions().get(getCriterionIndexInProject(id));
        
        if(criterion.getName() != null){
            data[0] = criterion.getName();
        }

        if(criterion.getDescription() != null){
            data[1] = criterion.getDescription();
        }

        if(criterion.getNameIndicator() != null){
            data[2] = criterion.getNameIndicator();
        }

        if(criterion.getDescriptionIndicator() != null){
            data[3] = criterion.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(criterion.isExist());
        list.add(null);
        list.add(criterion.getAlias());
        data[4] = list;

        return data;
    }

    private int getCriterionIndexInProject(int id) {
        List<Criterion> items = dataModel.getCriterions();
        for ( int i = 0; i < items.size(); i++){

            if (items.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public List[] getMilestoneData(int id) {
        List[] data = new List[6];
        Milestone milestone = dataModel.getMilestones().get(id);
        if(milestone.getName() != null){
            data[0] = milestone.getName();
        }

        if(milestone.getDescription() != null){
            data[1] = milestone.getDescription();
        }

        if(milestone.getNameIndicator() != null){
            data[2] = milestone.getNameIndicator();
        }

        if(milestone.getDescriptionIndicator() != null){
            data[3] = milestone.getDescriptionIndicator();
        }

        if(milestone.getCriteriaIndicator() != null){
            data[4] = milestone.getCriteriaIndicator();
        }

        List list = new ArrayList();
        list.add(milestone.isExist());
        list.add(null);
        list.add(milestone.getAlias());
        data[5] = list;


        return data;
    }

    public ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id) {
        Milestone milestone = dataModel.getMilestone(id);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (CriterionList list1 : milestone.getCriteriaIndexs()){
            list.add((ArrayList<Integer>) list1.getCriterions());
        }


        return list;
    }


    public List[] getRoleData(int id) {
        List[] data = new List[5];
        Person role = dataModel.getRole(id);

        if(role.getName() != null){
            data[0] = role.getName();
        }

        if(role.getType() != null){
            data[1] = role.getType();
        }
        
        if(role.getNameIndicator() != null){
            data[2] = role.getNameIndicator();
        }


        if(role.getTypeIndicator() != null){
            data[3] = role.getTypeIndicator();
        }
        
        ArrayList list = new ArrayList();
        list.add(role.isExist());
        list.add(role.getCount());
        list.add(role.getAlias());
        data[4] = list;


        return data;

    }

    public List[] getRoleTypeData(int id) {
        List[] data = new List[7];
        RoleType roleType = dataModel.getRoleType(id);
        if(roleType.getName() != null){
            data[0] = roleType.getName();
        }

        if(roleType.getRoleTypeClassIndex().size() != 0){
            data[1] = roleType.getRoleTypeClassIndex();
        }

        if(roleType.getRoleTypeSuperClassIndex().size() != 0){
            data[2] = roleType.getRoleTypeSuperClassIndex();
        }


        if(roleType.getNameIndicator() != null){
            data[3] = roleType.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(roleType.isExist());
        list.add(null);
        list.add(roleType.getAlias());
        data[4] = list;

        if(roleType.getDescription() != null){
            data[5] = roleType.getDescription();
        }

        if(roleType.getDescriptionIndicator() != null){
            data[6] = roleType.getDescriptionIndicator();
        }


       
        return data;
    }


    public List[] getSeverityData(int id) {
        List[] data = new List[5];
        Severity severity = dataModel.getSeverity(id);
        if(severity.getName() != null){
            data[0] = severity.getName();
        }
        
        if(severity.getSeverityClassIndex().size() != 0){
            data[1] = severity.getSeverityClassIndex();
        }

        if(severity.getSeveritySuperClassIndex().size() != 0){
            data[2] = severity.getSeveritySuperClassIndex();
        }


        if(severity.getNameIndicator() != null){
            data[3] = severity.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(severity.isExist());
        list.add(null);
        list.add(severity.getAlias());
        data[4] = list;
        return data;
    }

    public List[] getPriorityData(int id) {
        List[] data = new List[5];
        Priority priority = dataModel.getPriority(id);
        if(priority.getName() != null){
            data[0] = priority.getName();
        }

        if(priority.getPriorityClassIndex().size() != 0){
            data[1] = priority.getPriorityClassIndex();
        }

        if(priority.getPrioritySuperClassIndex().size() != 0){
            data[2] = priority.getPrioritySuperClassIndex();
        }


        if(priority.getNameIndicator() != null){
            data[3] = priority.getNameIndicator();
        }
        
        List list = new ArrayList();
        list.add(priority.isExist());
        list.add(null);
        list.add(priority.getAlias());
        data[4] = list;
        return data;
    }

    public List[] getStatusData(int id) {
        List[] data = new List[5];
        Status status = dataModel.getStatus(id);
        if(status.getName() != null){
            data[0] = status.getName();
        }
        
        if(status.getStatusClassIndex().size() != 0){
            data[1] = status.getStatusClassIndex();
        }

        if(status.getStatusSuperClassIndex().size() != 0){
            data[2] = status.getStatusSuperClassIndex();
        }


        if(status.getNameIndicator() != null){
            data[3] = status.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(status.isExist());
        list.add(null);
        list.add(status.getAlias());
        data[4] = list;
        return data;
    }

    public List[] getTypeData(int id) {
        List[] data = new List[5];
        Type type = dataModel.getType(id);
        if(type.getName() != null){
            data[0] = type.getName();
        }

        if(type.getTypeClassIndex().size() != 0){
            data[1] = type.getTypeClassIndex();
        }

        if(type.getTypeSuperClassIndex().size() != 0){
            data[2] = type.getTypeSuperClassIndex();
        }


        if(type.getNameIndicator() != null){
            data[3] = type.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(type.isExist());
        list.add(null);
        list.add(type.getAlias());
        data[4] = list;
        return data;
    }


    public List[] getRelationData(int id) {
        List[] data = new List[5];
        Relation relation = dataModel.getRelation(id);
        if(relation.getName() != null){
            data[0] = relation.getName();
        }
        if(relation.getRelationClassIndex().size() != 0){
            data[1] = relation.getRelationClassIndex();
        }

        if(relation.getRelationSuperClassIndex().size() != 0){
            data[2] = relation.getRelationSuperClassIndex();
        }

        if(relation.getNameIndicator() != null){
            data[3] = relation.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(relation.isExist());
        list.add(null);
        list.add(relation.getAlias());
        data[4] = list;

        return data;
    }

    public List[] getResolutionData(int id) {
        List[] data = new List[5];
        Resolution resolution = dataModel.getResolution(id);

        if(resolution.getName() != null){
            data[0] = resolution.getName();
        }
        if(resolution.getResolutionClassIndex().size() != 0){
            data[1] = resolution.getResolutionClassIndex();
        }

        if(resolution.getResolutionSuperClassIndex().size() != 0){
            data[2] = resolution.getResolutionSuperClassIndex();
        }


        if(resolution.getNameIndicator() != null){
            data[3] = resolution.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(resolution.isExist());
        list.add(null);
        list.add(resolution.getAlias());
        data[4] = list;

        return data;
    }

    public List[] getCPRData(int id) {
        List[] data = new List[5];
        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);

        if(cpr.getName() != null){
            data[0] = cpr.getName();
        }
        
        if(cpr.getPersonIndex() != null){
            data[1] = cpr.getPersonIndex();
        }

        if(cpr.getNameIndicator() != null){
            data[2] = cpr.getNameIndicator();
        }

        if(cpr.getPersonIndicator() != null){
            data[3] = cpr.getPersonIndicator();
        }
        List list = new ArrayList();
        list.add(cpr.isExist());
        list.add(null);
        list.add(cpr.getAlias());
        data[4] = list;
        return data;
    }

    public List[] getBranchStringData(int id) {
        List[] data = new List[3];
        Branch branch = dataModel.getBranch(id);;
        if(branch.getName() != null){
            data[0] = branch.getName();
        }
        if(branch.getNameIndicator() != null){
            data[1] = branch.getNameIndicator();
        }
        List list = new ArrayList();
        list.add(branch.isExist());
        list.add(null);
        list.add(branch.getAlias());
        data[2] = list;
        if(branch.isIsMain() != null){

            list.add(branch.isIsMain());
        }

        return data;
    }


    public String getTagStringData(int id, int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return configuration.getTags().get(id);
    }

    public List[] getPhaseStringData(int id) {
        List[] data = new List[12];
        Phase phase = dataModel.getPhase(id);;
        
        if(phase.getName() != null){
            data[0] = phase.getName();
        }
        
        if(phase.getDescription() != null){
            data[1] = phase.getDescription();
        }
        
        if(phase.getConfiguration() != null){
            data[2] = phase.getConfiguration();
        }

        if(phase.getMilestoneIndex() != null){
            data[3] = phase.getMilestoneIndex();
        }

        if(phase.getEndDate() != null){
            data[4] = phase.getEndDate();
        }

        if(phase.getNameIndicator() != null){
            data[5] = phase.getNameIndicator();
        }

        if(phase.getDescriptionIndicator() != null){
            data[6] = phase.getDescriptionIndicator();
        }

        if(phase.getConfigurationIndicator() != null){
            data[7] = phase.getConfigurationIndicator();
        }

        if(phase.getMilestoneIndicator() != null){
            data[8] = phase.getMilestoneIndicator();
        }

        if(phase.getEndDateIndicator() != null){
            data[9] = phase.getEndDateIndicator();
        }

        if(phase.getWorkUnitsIndicator() != null){
            data[10] = phase.getWorkUnitsIndicator();
        }

        List list = new ArrayList();
        list.add(phase.isExist());
        list.add(null);
        list.add(phase.getAlias());
        data[11] = list;

        return data;
    }

    public ArrayList<ArrayList<Integer>> getWorkUnitFromPhase(int phaseId) {

        Phase phase = dataModel.getPhase(phaseId);
        return getWorkUnitFrom(phase.getWorkUnits());
    }

    public ArrayList<ArrayList<Integer>> getWorkUniFromWorkUnit(int id) {
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        return getWorkUnitFrom(workUnit.getWorkUnits());
    }

    public ArrayList<ArrayList<Integer>> getWorkUnitFromProject() {
        Project project = dataModel.getProject();
        return getWorkUnitFrom(project.getWorkUnitIndexs());
    }


    public ArrayList<ArrayList<Integer>> getWorkUnitFromIteration(int iterationId) {

        Iteration iteration = dataModel.getIteration(iterationId);
        return getWorkUnitFrom(iteration.getWorkUnits());
    }

    public  ArrayList<ArrayList<Integer>> getWorkUnitFromActivity(int activityId) {

        Activity activity = dataModel.getActivity(activityId);
        return getWorkUnitFrom(activity.getWorkUnits());
    }
    
    private ArrayList<ArrayList<Integer>> getWorkUnitFrom( List<WorkUnitList> inputList ){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (WorkUnitList list1 : inputList){
            list.add((ArrayList<Integer>) list1.getWorkUnits());
        }
        
        return list;
    }

    public List[] getProjectStringData() {

        List[] data = new List[11];
        Project project = dataModel.getProject();

        if(project.getName() != null){
            data[0] = project.getName();
        }


        if(project.getDescription() != null){
            data[1] = project.getDescription();
        }

        if(project.getStartDate() != null){
            data[2] = project.getStartDate();
        }

        if(project.getEndDate() != null){
            data[3] = project.getEndDate();
        }

        if(project.getNameIndicator() != null){
            data[4] = project.getNameIndicator();
        }


        if(project.getDescriptionIndicator() != null){
            data[5] = project.getDescriptionIndicator();
        }

        if(project.getStartDateIndicator() != null){
            data[6] = project.getStartDateIndicator();
        }

        if(project.getEndDateIndicator() != null){
            data[7] = project.getEndDateIndicator();
        }

        if(project.getWorkUnitsIndicator() != null){
            data[8] = project.getWorkUnitsIndicator();
        }

        return data;

    }
    
    public List[] getIterationStringData(int id) {
        List[] data = new List[12];
        Iteration iteration = dataModel.getIteration(id);;

        if(iteration.getName() != null){
            data[0] = iteration.getName();
        }


        if(iteration.getDescription() != null){
            data[1] = iteration.getDescription();
        }

        if(iteration.getConfiguration() != null){
            data[2] = iteration.getConfiguration();
        }

        if(iteration.getStartDate() != null){
            data[3] = iteration.getStartDate();
        }

        if(iteration.getEndDate() != null){
            data[4] = iteration.getEndDate();
        }

        if(iteration.getNameIndicator() != null){
            data[5] = iteration.getNameIndicator();
        }


        if(iteration.getDescriptionIndicator() != null){
            data[6] = iteration.getDescriptionIndicator();
        }

        if(iteration.getConfigurationIndicator() != null){
            data[7] = iteration.getConfigurationIndicator();
        }

        if(iteration.getStartDateIndicator() != null){
            data[8] = iteration.getStartDateIndicator();
        }

        if(iteration.getEndDateIndicator() != null){
            data[9] = iteration.getEndDateIndicator();
        }

        if(iteration.getWorkUnitsIndicator() != null){
            data[10] = iteration.getWorkUnitsIndicator();
        }

        List list = new ArrayList();
        list.add(iteration.isExist());
        list.add(null);
        list.add(iteration.getAlias());
        data[11] = list;

        return data;
    }

    public List[] getActivityStringData(int id) {
        List[] data = new List[8];
        Activity activity = dataModel.getActivity(id);;
        if(activity.getName() != null){
            data[0] = activity.getName();
        }
        
        if(activity.getDescription() != null){
            data[1] = activity.getDescription();
        }

        if(activity.getWorkUnitsIndicator() != null){
            data[2] = activity.getWorkUnitsIndicator();
        }

        if(activity.getNameIndicator() != null){
            data[3] = activity.getNameIndicator();
        }

        if(activity.getDescriptionIndicator() != null){
            data[4] = activity.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(activity.isExist());
        list.add(null);
        list.add(activity.getAlias());
        data[5] = list;

        if(activity.getEndDate() != null){
            data[6] = activity.getEndDate();
        }

        if(activity.getEndDateIndicator() != null){
            data[7] = activity.getEndDateIndicator();
        }

        return data;
    }

    public List[] getChangeStringData(int id) {

        List[] data = new List[6];
        Change change = dataModel.getChange(id);;

        if(change.getName() != null){
            data[0] = change.getName();
        }
        
        if(change.getDescription() != null){
            data[1] = change.getDescription();
        }

        if(change.getNameIndicator() != null){
            data[2] = change.getNameIndicator();
        }

        if(change.getDescriptionIndicator() != null){
            data[3] = change.getDescriptionIndicator();
        }

        List list = new ArrayList();
        data[4] = list;
        if(change.isExist() != null){

            list.add(change.isExist());
        }
        list.add(null);
        list.add(change.getAlias());

        if(change.getArtifactIndex() != null){
            data[5] = change.getArtifactIndex();
        }

        return data;
    }

    public List[] getArtifactStringData(int id) {
        List[] data = new List[11];
        Artifact artifact = dataModel.getArtifact(id);;

        if(artifact.getName() != null){
            data[0] = artifact.getName();
        }

        if(artifact.getDescription() != null){
            data[1] = artifact.getDescription();
        }

        if(artifact.getAuthorIndex() != null){
            data[2] = artifact.getAuthorIndex();
        }

        if(artifact.getCreated() != null){
            data[3] = artifact.getCreated();
        }

        if(artifact.getMimeTypeIndex() != null){
            data[4] = artifact.getMimeTypeIndex();
        }

        if(artifact.getNameIndicator() != null){
            data[5] = artifact.getNameIndicator();
        }

        if(artifact.getDescriptionIndicator() != null){
            data[6] = artifact.getDescriptionIndicator();
        }

        if(artifact.getAuthorIndicator() != null){
            data[7] = artifact.getAuthorIndicator();
        }

        if(artifact.getCreatedIndicator() != null){
            data[8] = artifact.getCreatedIndicator();
        }

        if(artifact.getMimeTypeIndicator() != null){
            data[9] = artifact.getMimeTypeIndicator();
        }


        List list = new ArrayList();
        list.add(artifact.isExist());
        list.add(artifact.getCount());
        list.add(artifact.getAlias());
        data[10] = list;



        return data;
    }

    public List[] getWorkUnitStringData(int id) {

        List[] data = new List[25];
        WorkUnit workUnit = dataModel.getWorkUnit(id);;
       
        if(workUnit.getName() != null){
            data[0] = workUnit.getName();
        }

        if(workUnit.getDescription() != null){
            data[1] = workUnit.getDescription();
        }

        if(workUnit.getEstimatedTime() != null){
            data[2] = workUnit.getEstimatedTime();
        }

        if(workUnit.getCategory() != null){
            data[3] = workUnit.getCategory();
        }

        if(workUnit.getPriorityIndex() != null){
            data[4] = workUnit.getPriorityIndex();
        }

        if(workUnit.getSeverityIndex() != null){
            data[5] = workUnit.getSeverityIndex();
        }

        if(workUnit.getResolutionIndex() != null){
            data[6] = workUnit.getResolutionIndex();
        }

        if(workUnit.getStatusIndex() != null){
            data[7] = workUnit.getStatusIndex();
        }

        if(workUnit.getTypeIndex() != null){
            data[8] = workUnit.getTypeIndex();
        }

        if(workUnit.getAssigneeIndex() != null){
            data[9] = workUnit.getAssigneeIndex();
        }

        if(workUnit.getAuthorIndex() != null){
            data[10] = workUnit.getAuthorIndex();
        }

        if(workUnit.getNameIndicator() != null){
            data[11] = workUnit.getNameIndicator();
        }

        if(workUnit.getDescriptionIndicator() != null){
            data[12] = workUnit.getDescriptionIndicator();
        }

        if(workUnit.getEstimatedTimeIndicator() != null){
            data[13] = workUnit.getEstimatedTimeIndicator();
        }

        if(workUnit.getCategoryIndicator() != null){
            data[14] = workUnit.getCategoryIndicator();
        }

        if(workUnit.getPriorityIndicator() != null){
            data[15] = workUnit.getPriorityIndicator();
        }

        if(workUnit.getSeverityIndicator() != null){
            data[16] = workUnit.getSeverityIndicator();
        }

        if(workUnit.getResolutionIndicator() != null){
            data[17] = workUnit.getResolutionIndicator();
        }

        if(workUnit.getStatusIndicator() != null){
            data[18] = workUnit.getStatusIndicator();
        }

        if(workUnit.getTypeIndicator() != null){
            data[19] = workUnit.getTypeIndicator();
        }

        if(workUnit.getAssigneeIndex() != null){
            data[20] = workUnit.getAssigneeIndicator();
        }

        if(workUnit.getAuthorIndex() != null){
            data[21] = workUnit.getAuthorIndex();
        }

        List list = new ArrayList();
        list.add(workUnit.isExist());
        list.add(null);
        list.add(workUnit.getAlias());
        data[22] = list;


        if (workUnit.getRelationIndex() != null){
            data[23] = workUnit.getRelationIndex();
        }

        if(workUnit.getWorkUnits() != null){
            data[24] = workUnit.getWorkUnits();
        }


        return data;
        
    }

    public List[] getConfigurationStringData(int id) {

        List[] data = new List[10];
        Configuration configuration = dataModel.getConfiguration(id);;

        if(configuration.getName() != null){
            data[0] = configuration.getName();
        }
        
        if(configuration.getCreated() != null){
            data[1] = configuration.getCreated();
        }

        if(configuration.getAuthorIndex() != null){
            data[2] = configuration.getAuthorIndex();
        }

        if(configuration.getNameIndicator() != null){
            data[3] = configuration.getNameIndicator();
        }

        if(configuration.getCreatedIndicator() != null){
            data[4] = configuration.getCreatedIndicator();
        }

        if(configuration.getAuthorIndicator() != null){
            data[5] = configuration.getAuthorIndicator();
        }

        if(configuration.getCPRsIndicator() != null){
            data[6] = configuration.getCPRsIndicator();
        }

        if(configuration.getBranchesIndicator() != null){
            data[7] = configuration.getBranchesIndicator();
        }

        if(configuration.getChangesIndicator() != null){
            data[8] = configuration.getChangesIndicator();
        }

        List list = new ArrayList();
        list.add(configuration.isExist());
        list.add(configuration.getCount());
        list.add(configuration.getAlias());
        data[9] = list;

        return data;
        
    }

    public ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getCPRFrom(configuration.getCPRsIndexs());
    }

    private ArrayList<ArrayList<Integer>> getCPRFrom( List<CPRSList> inputList ){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (CPRSList list1 : inputList){
            list.add((ArrayList<Integer>) list1.getCPRs());
        }

        return list;
    }

    private ArrayList<ArrayList<Integer>> getBranchFrom( List<BranchList> inputList ){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (BranchList list1 : inputList){
            list.add((ArrayList<Integer>) list1.getBranches());
        }

        return list;
    }

    private ArrayList<ArrayList<Integer>> getChangeFrom( List<ChangeList> inputList ){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (ChangeList list1 : inputList){
            list.add((ArrayList<Integer>) list1.getChanges());
        }

        return list;
    }

    public  ArrayList<ArrayList<Integer>> getBranchfromCommit(int commidId) {
        Commit commit = dataModel.getCommit(commidId);
        return getBranchFrom(commit.getBranch());
    }

    public  ArrayList<ArrayList<Integer>> getBranchfromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return null; // getBranchFrom(configuration.getBranchesIndexs());
    }

    public  ArrayList<ArrayList<Integer>> getChangeFromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getChangeFrom(configuration.getChangesIndexs());
    }

    public List[] getVCSTagStringData(int tagId) {
        List[] data = new List[5];
        VCSTag vcsTag = dataModel.getVCSTag(tagId);;

        if(vcsTag.getName() != null){
            data[0] = vcsTag.getName();
        }

        if(vcsTag.getDescription() != null){
            data[1] = vcsTag.getDescription();
        }

        if(vcsTag.getNameIndicator() != null){
            data[2] = vcsTag.getNameIndicator();
        }

        if(vcsTag.getDescriptionIndicator() != null){
            data[3] = vcsTag.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(vcsTag.isExist());
        list.add(null);
        list.add(vcsTag.getAlias());
        data[4] = list;
        return data;
    }

    public List[] getCommitStringData(int commitId) {
            List[] data = new List[6];
            Commit commit = dataModel.getCommit(commitId);;
        if(commit.getName() != null){
            data[0] = commit.getName();
        }

        if(commit.getNameIndicator() != null){
            data[1] = commit.getNameIndicator();
        }

        if(commit.getTags() != null){
            data[2] = commit.getTags();
        }

        if(commit.getTagsIndicator() != null){
            data[3] = commit.getTagsIndicator();
        }

        if(commit.getBranchIndicator() != null){
            data[4] = commit.getBranchIndicator();
        }


        ArrayList list = new ArrayList();
        list.add(commit.isExist());
        list.add(commit.getCount());
        list.add(commit.getAlias());
        data[5] = list;
        if(commit.isRelease() != null){
            list.add(commit.isRelease());
        }


            return data;
    }

    public List[] getCommitedConfigurationStringData(int commitedId) {
        List[] data = new List[6];
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(commitedId);;

        if(commitedConfiguration.getName() != null){
            data[0] = commitedConfiguration.getName();
        }
        if(commitedConfiguration.getNameIndicator() != null){
            data[1] = commitedConfiguration.getNameIndicator();
        }

        if(commitedConfiguration.getCommitedDay() != null){
            data[2] = commitedConfiguration.getCommitedDay();
        }

        if(commitedConfiguration.getCommitedDayIndicator() != null){
            data[3] = commitedConfiguration.getCommitedDayIndicator();
        }

        ArrayList list = new ArrayList();
        list.add(commitedConfiguration.isExist());
        list.add(commitedConfiguration.getCount());
        list.add(commitedConfiguration.getAlias());
        data[4] = list;

        return data;

    }



}
