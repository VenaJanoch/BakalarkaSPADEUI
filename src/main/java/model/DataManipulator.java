package model;

import SPADEPAC.*;
import XML.ProcessGenerator;

import java.util.*;

public class DataManipulator{


    private Project project;
    private ProcessGenerator procesGener;
    private DataModel dataModel;
    private ObjectFactory objF;

    public DataManipulator(ProcessGenerator processGenerator, DataModel dataModel) {
        this.procesGener = processGenerator;
        this.dataModel = dataModel;
        this.project = dataModel.getProject();
        this.objF = dataModel.getObjF();
    }


    public void setRelationIndexToLink(int id, int relationIndex) {
    project.getLinks().get(id).setRelationIndex(relationIndex);
    }

    public void copyDataFromActivity(int oldActivityId, int newActivityId) {
        Activity oldActivity = project.getActivities().get(oldActivityId);
        Activity newActivity = project.getActivities().get(newActivityId);

      //  newActivity.setCoordinates(oldActivity.getCoordinates());
     //   newActivity.setName(oldActivity.getName());
     //   newActivity.setDescription(oldActivity.getDescription());

       int workUnitSize = project.getWorkUnits().size();
      //  for (int i =0; i < oldActivity.getWorkUnits().size(); i++){
      //      copyDataFromWorkUnit(oldActivity.getWorkUnits().get(i));
      //      newActivity.getWorkUnits().add(workUnitSize + i);
       // }
    }

    public void copyDataFromWorkUnit(int oldWUId){
        WorkUnit oldWu = project.getWorkUnits().get(oldWUId);
        WorkUnit newWu = objF.createWorkUnit();
        copyDataFromWorkUnit(oldWu, newWu);
        project.getWorkUnits().add(newWu);

    }

    public void copyDataFromWorkUnit(int oldWUId, int newWUId){
        WorkUnit oldWu = project.getWorkUnits().get(oldWUId);
        WorkUnit newWu = project.getWorkUnits().get(newWUId);
        copyDataFromWorkUnit(oldWu, newWu);

    }

    public  void copyDataFromWorkUnit(WorkUnit oldWu, WorkUnit newWu){
//        newWu.setResolutionIndex(oldWu.getResolutionIndex());
//        newWu.setStatusIndex(oldWu.getStatusIndex());
//        newWu.setTypeIndex(oldWu.getTypeIndex());
//        newWu.setSeverityIndex(oldWu.getSeverityIndex());
//        newWu.setPriorityIndex(oldWu.getPriorityIndex());
//        newWu.setName(oldWu.getName());
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
        Iteration oldIteration = project.getIterations().get(oldIteratationId);
        Iteration newIteration = project.getIterations().get(newIterationId);

//        newIteration.setCoordinates(oldIteration.getCoordinates());
//        newIteration.setName(oldIteration.getName());
//        newIteration.setDescription(oldIteration.getDescription());
//        newIteration.setStartDate(oldIteration.getStartDate());
//        newIteration.setEndDate(oldIteration.getEndDate());
//        newIteration.setConfiguration(oldIteration.getConfiguration());
//
//        int workUnitSize = project.getWorkUnits().size();
//        for (int i =0; i < oldIteration.getWorkUnits().size(); i++){
//            copyDataFromWorkUnit(oldIteration.getWorkUnits().get(i));
//            newIteration.getWorkUnits().add(workUnitSize + i);
//        }

    }


    public void copyDataFromPhase(int phaseId, int newPhaseId) {
        Phase oldPhase = project.getPhases().get(phaseId);
        Phase newPhase = project.getPhases().get(newPhaseId);

//        newPhase.setCoordinates(oldPhase.getCoordinates());
//        newPhase.setName(oldPhase.getName());
//        newPhase.setDescription(oldPhase.getDescription());
//        newPhase.setEndDate(oldPhase.getEndDate());
//        newPhase.setConfiguration(oldPhase.getConfiguration());
//        newPhase.setConfiguration(oldPhase.getConfiguration());
//        newPhase.setMilestoneIndex(oldPhase.getMilestoneIndex());
//
//        int workUnitSize = project.getWorkUnits().size();
//        for (int i =0; i < oldPhase.getWorkUnits().size(); i++){
//            copyDataFromWorkUnit(oldPhase.getWorkUnits().get(i));
//            newPhase.getWorkUnits().add(workUnitSize + i);
//        }

    }

    public void copyDataFromChange(int changeId, int newchangeId) {

        Change oldChange = project.getChanges().get(changeId);
        Change newChange = project.getChanges().get(newchangeId);

//        newChange.setCoordinates(oldChange.getCoordinates());
//        newChange.setName(oldChange.getName());
//        newChange.setDescriptoin(oldChange.getDescriptoin());
//        newChange.setExist(oldChange.isExist());
        }

    public void copyDataFromArtifact(int artifactId, int newArtifactId) {
        Artifact oldArtifact = project.getArtifacts().get(artifactId);
        Artifact newArtifact = project.getArtifacts().get(newArtifactId);

//        newArtifact.setCoordinates(oldArtifact.getCoordinates());
//        newArtifact.setName(oldArtifact.getName());
//        newArtifact.setDescriptoin(oldArtifact.getDescriptoin());
//        newArtifact.setExist(oldArtifact.isExist());
//        newArtifact.setMimeType(oldArtifact.getMimeType());
//        newArtifact.setCreated(oldArtifact.getCreated());
//        newArtifact.setAuthorIndex(oldArtifact.getAuthorIndex());
//        newArtifact.setArtifactIndex(oldArtifact.getArtifactIndex());
    }

    public List[] getCriterionData(int id) {
        List[] data = new List[4];
        Criterion criterion = project.getCriterions().get(getCriterionIndexInProject(id));
        
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

        return data;
    }

    private int getCriterionIndexInProject(int id) {
        List<Criterion> items = project.getCriterions();
        for ( int i = 0; i < items.size(); i++){

            if (items.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public List[] getMilestoneData(int id) {
        List[] data = new List[5];
        Milestone milestone = project.getMilestones().get(id);
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

        if(milestone.getCriteriaIndexs() != null){
            data[4] = milestone.getCriteriaIndicator();
        }


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
        List[] data = new List[7];
        Role role = dataModel.getRole(id);

        if(role.getName() != null){
            data[0] = role.getName();
        }

        if(role.getDescription() != null){
            data[1] = role.getDescription();
        }

        if(role.getType() != null){
            data[2] = role.getType();
        }
        
        if(role.getNameIndicator() != null){
            data[3] = role.getNameIndicator();
        }

        if(role.getDescriptionIndicator() != null){
            data[4] = role.getDescriptionIndicator();
        }

        if(role.getTypeIndicator() != null){
            data[5] = role.getTypeIndicator();
        }
        
        ArrayList list = new ArrayList();
        list.add(role.getCount());
        data[6] = list;


        return data;

    }

    public List[] getRoleTypeData(int id) {
        List[] data = new List[4];
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
            data[3] = roleType.getName();
        }
       
        return data;
    }


    public List[] getSeverityData(int id) {
        List[] data = new List[4];
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
            data[3] = severity.getName();
        }
        return data;
    }

    public List[] getPriorityData(int id) {
        List[] data = new List[4];
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
            data[3] = priority.getName();
        }
        return data;
    }

    public List[] getStatusData(int id) {
        List[] data = new List[4];
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
            data[3] = status.getName();
        }
        return data;
    }

    public List[] getTypeData(int id) {
        List[] data = new List[4];
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
            data[3] = type.getName();
        }
        return data;
    }


    public List[] getRelationData(int id) {
        List[] data = new List[4];
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


        if(relation.getNameIndicator().size() != 0){
            data[3] = relation.getName();
        }
        return data;
    }

    public List[] getResolutionData(int id) {
        List[] data = new List[4];
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
            data[3] = resolution.getName();
        }
        return data;
    }

    public List[] getCPRData(int id) {
        List[] data = new List[4];
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
        data[2] = list;
        if(branch.isIsMain() != null){

            list.add(branch.isIsMain());
            data[2] = list;
        }

        return data;
    }


    public String getTagStringData(int id, int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return configuration.getTags().get(id);
    }

    public List[] getPhaseStringData(int id) {
        List[] data = new List[11];
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


        return data;
    }

    public ArrayList<ArrayList<Integer>> getWorkUnitFromPhase(int phaseId) {

        Phase phase = dataModel.getPhase(phaseId);
        return getWorkUnitFrom(phase.getWorkUnits());
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
            data[3] = project.getStartDate();
        }

        if(project.getEndDate() != null){
            data[4] = project.getEndDate();
        }

        if(project.getNameIndicator() != null){
            data[5] = project.getNameIndicator();
        }


        if(project.getDescriptionIndicator() != null){
            data[6] = project.getDescriptionIndicator();
        }

        if(project.getStartDateIndicator() != null){
            data[8] = project.getStartDateIndicator();
        }

        if(project.getEndDateIndicator() != null){
            data[9] = project.getEndDateIndicator();
        }

        if(project.getWorkUnitsIndicator() != null){
            data[10] = project.getWorkUnitsIndicator();
        }

        return data;

    }
    
    public List[] getIterationStringData(int id) {
        List[] data = new List[11];
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

        return data;
    }

    public List[] getActivityStringData(int id) {
        List[] data = new List[5];
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

        return data;
    }

    public List[] getChangeStringData(int id) {

        List[] data = new List[5];
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
            data[4] = list;
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

        if(artifact.getMimeType() != null){
            data[4] = artifact.getMimeType();
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
        data[10] = list;
        if(artifact.isExist() != null){

            list.add(artifact.isExist());
            data[10] = list;
        }


        return data;
    }

    public List[] getWorkUnitStringData(int id) {

        List[] data = new List[23];
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
        data[22] = list;
        if(workUnit.isExist() != null){

            list.add(workUnit.isExist());
            data[22] = list;
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

        if(configuration.isIsRelease() != null){
            List list = new ArrayList();
            list.add(configuration.isIsRelease());
            data[9] = list;
        }

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

    public  ArrayList<ArrayList<Integer>> getBranchfromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getBranchFrom(configuration.getBranchesIndexs());
    }

    public  ArrayList<ArrayList<Integer>> getChangeFromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getChangeFrom(configuration.getChangesIndexs());
    }

    public List[] getVCSTagStringData(int tagId) {
        List[] data = new List[4];
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

        return data;
    }

    public List[] getCommitStringData(int commitId) {
            List[] data = new List[3];
            Commit commit = dataModel.getCommit(commitId);;
        if(commit.getName() != null){
            data[0] = commit.getName();
        }

        if(commit.getNameIndicator() != null){
            data[1] = commit.getNameIndicator();
        }


        if(commit.isRelease() != null){
            ArrayList list = new ArrayList();
            list.add(commit.isRelease());
           data[2] = list;
        }


            return data;
    }

    public List[] getCommitedConfigurationStringData(int commitedId) {
        List[] data = new List[4];
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

        return data;

    }



}
