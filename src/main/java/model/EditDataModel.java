package model;

import SPADEPAC.*;
import interfaces.IEditDataModel;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditDataModel implements IEditDataModel {

    private Project project;
    private DataModel dataModel;


    public  EditDataModel(Project project, DataModel dataModel){
        this.project = project;
        this.dataModel = dataModel;
    }

    public void editDataInCPR(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex, ArrayList<Integer> roleIndicator, int id) {

        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);
        dataModel.addDataToCPR(cpr, nameForManipulator, nameIndicators, roleIndex, roleIndicator);

    }

    public void editDataInBranch(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, boolean isMainBranch, int id) {
        Branch branch = dataModel.getBranch(id);
        dataModel.addDataToBranch(branch, nameForManipulator, nameIndicators, isMainBranch);
    }

    public void editDataInArtifact(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                                   ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                                   ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                                   ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                                   ArrayList<Integer> typeIndicator, int instanceCount, int id){
        Artifact artifact = dataModel.getArtifact(id);
        dataModel.addDataToArtifact(artifact, nameForManipulator,  nameIndicators, descForManipulator,  descriptionIndicators,
                createdDate, dateIndicator, isCreate, authorIndex,  typeIndex,  authorIndicator, typeIndicator, instanceCount);
    }

    public void editDataInChange( ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                                  ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator,
                                  boolean selected, int id){
        Change change = dataModel.getChange(id);
        dataModel.addDataToChange(change,   nameForManipulator,  descForManipulator, artifactForManipulator, nameIndicators, descIndicator, selected);
    }

    public void editDataInPhase(ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
            ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
            ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
            ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, int id){
        Phase phase =  dataModel.getPhase(id);
        dataModel.addDataToPhase(phase, actName, endDateL, desc, confIndex, milestoneIndex,  workUnitIndexList,
                workUnitIndicators, nameIndicator, endDateIndicator, descIndicator, confIndicator, milestoneIndicator);
    }

    public void editDataInIteration(ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                    ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                    ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                    ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, int id) {
        Iteration iteration = dataModel.getIteration(id);
        dataModel.addDataToIteration(iteration, actName, endDateL,  startDateL, desc,
                confIndex,  workUnitIndexList, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator);
    }

    public void editDataInActivity(ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                   ArrayList<ArrayList<Integer>> setOfItemOnCanvas, ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,
                                   ArrayList<Integer> workUnitIndicators, int id) {
        Activity activity = dataModel.getActivity(id);
        dataModel.addDataToActivity(activity, nameForManipulator, descriptionForManipulator, setOfItemOnCanvas, nameIndicators,  descIndicators,  workUnitIndicators);

    }

    public void editDataInWorkUnit(List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                                   ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                   ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                   ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                   ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                   ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                   ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,  ArrayList<ArrayList<Integer>> workUnits, int id) {
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        dataModel.addDataToWorkUnit(workUnit,nameForManipulator, description, categoryForManipulator,
                assigneIndex, authorIndex, priorityIndex,severityIndex,
                typeIndex, resolutionIndex, statusIndex,
                estimateForDataManipulator, nameIndicator, descriptionIndicator, categoryIndicator,
                assigneIndicator, authorIndicator, priorityIndicator, severityIndicator,
                typeIndicator, resolutionIndicator, statusIndicator,
                estimateIndicator, isExist, relations, workUnits);
    }

    public void editDataInConfiguration(ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                        boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                        ArrayList<ArrayList<Integer>> branches, ArrayList<ArrayList<Integer>> changeIndexs,
                                        ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                        ArrayList<Integer> authorIndicator, ArrayList<Integer> branchIndicator, ArrayList<Integer> changeIndicator, int instanceCount, int id){
        Configuration configuration = dataModel.getConfiguration(id);
        dataModel.addDataToConfiguration(configuration, actName, createDate, isRelease, authorIndex, cprs, branches, changeIndexs,
                cprIndicators, nameIndicator, createdIndicator, authorIndicator, branchIndicator, changeIndicator, instanceCount);

    }

    public void editDataInCriterion( ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                     ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, int id){
        Criterion criterion = dataModel.getCriterion(id);
        dataModel.addDataToCriterion(criterion,  nameForManipulator, descForManipulator, nameIndicator, descIndicator);
    }

    public void editDataInPriority(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        Priority priority = dataModel.getPriority(id);
        dataModel.addDataToPriority(priority, nameForManipulator,  nameIndicator, classST, superST, classString, superSting);

    }

    public void editDataInSeverity(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        Severity severity = dataModel.getSeverity(id);
        dataModel.addDataToSeverity(severity, nameForManipulator, nameIndicator, classST, superST, classString, superSting);
    }

    public void editDataInRelation(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {

        Relation relation = dataModel.getRelation(id);
        dataModel.addDataToRelation(relation, nameForManipulator, nameIndicator, classST, superST, classString, superSting);
    }

    public void editDataInResolution(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                                     ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        Resolution relation = dataModel.getResolution(id);
        dataModel.addDataToResolution(relation, nameForManipulator, nameIndicator, classST, superST, classString, superSting);
    }

    public void editDataInRole(ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> type,
                               ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> typeIndicator, int instanceCount, int id) {

        Role role = dataModel.getRole(id);
        dataModel.addDatToRole(role, nameForManipulator, descForManipulator, type,
                nameIndicator, descIndicator, typeIndicator, instanceCount);
    }

    public void editDataInMilestone(ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                    ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                                    ArrayList<ArrayList<Integer>> criterionIndex, int id) {
        Milestone milestone = dataModel.getMilestone(id);
        dataModel.addDataToMilestone(milestone, nameForManipulator, descForManipulator,
                nameIndicator, descIndicator, criterionIndicator,criterionIndex);
    }

    public void editDataInRoleType(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        RoleType roleType = dataModel.getRoleType(id);
        dataModel.addDataToRoleType(roleType, nameForManipulator, nameIndicator, classST,superST, classString, superSting);
    }

    public void editDataInStatus(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                 ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        Status status = dataModel.getStatus(id);
        dataModel.addDataToStatus(status, nameForManipulator, nameIndicator, classST, superST, classString, superSting);
    }
    public void editDataInType(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> classST,
                               ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, int id) {
        Type type = dataModel.getType(id);;
        dataModel.addDataToType(type, nameForManipulator, nameIndicator, classST, superST, classString, superSting);
    }

    public void editTagInConfiguration(String tag, int configId, int id) {
        Configuration configuration = dataModel.getConfiguration(configId);
        configuration.getTags().remove(id);
        configuration.getTags().add(id, tag);
    }

    public void editDataInVCSTag(ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                 ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, int id){
        VCSTag tag = dataModel.getVCSTag(id);
        dataModel.addDataToVCSTag(tag, nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator);
    }

    public void editDataInCommit(ArrayList<String> nameForManipulator,  ArrayList<Integer> nameIndicator, boolean release, int instanceCount, int id){
        Commit commit = dataModel.getCommit(id);
        dataModel.addDataToCommit(commit, nameForManipulator, nameIndicator, release, instanceCount);
    }
    
    public void editCoordinatesInCommit(int x, int y, int id){
        Commit commit = dataModel.getCommit(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToCommit(coordinates, commit);
    }

    public void editCoordinatesInCommitedConfiguration(int x, int y, int id){
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToCommitedConfiguration(coordinates, commitedConfiguration);
    }

    public void editCoordinatesInConfiguration(int x, int y, int id){
        Configuration configuration = dataModel.getConfiguration(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToConfiguration(coordinates, configuration);
    }

    public void editCoordinatesInArtifact(int x, int y, int id){
        Artifact artifact = dataModel.getArtifact(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToArtifact(coordinates, artifact);
    }

    public void editCoordinatesInRole(int x, int y, int id){
        Role role = dataModel.getRole(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToRole(coordinates, role);
    }
    
    public void editDataInCommitedConfiguration(ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                                ArrayList<LocalDate> dateFromDatePicker, ArrayList<Integer> dateIndicator, int instanceCount, int id){
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(id);
        dataModel.addDataToCommitedConfiguration(commitedConfiguration, nameForManipulator, nameIndicator, dateFromDatePicker, dateIndicator, instanceCount);
    }

    public void editDataInProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                           ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators){
        dataModel.addDataToProject(nameForManipulator, startDate1, endDate1, descriptionForManipulator, workUnitsForManipulator,
                workUnitIndicators, nameIndicators, date1Indicators, date2Indicators, descIndicators);
    }


    public void updateItemList(SegmentType formType, SegmentType elementType, int elementIdList){
        switch (formType){
            case Change:
                switch (elementType ) {
                    case Artifact:
                        for (Change segment : project.getChanges()) {
                            List<Integer> type = segment.getArtifactIndex();
                            List<Integer> artifactIdId = dataModel.getArtifactId(type);
                                int deleteIndexInProject = dataModel.getArtifactIndexInProject(elementIdList);
                                for (int i = 0; i < type.size(); i++) {
                                    int index = type.get(i);
                                    if( artifactIdId.get(i) == elementIdList ){
                                        segment.getArtifactIndex().remove(i);
                                    }else if(index > deleteIndexInProject ){
                                        segment.getArtifactIndex().remove(i);
                                        segment.getArtifactIndex().add(i, index -1);
                                    }
                                }

                        }
                }
                break;
        }

    }


    public void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIdList){
        if (elementIdList == null){
            return;
        }
        switch (formType) {
            case WorkUnit:
                updateWUListItem(elementType, elementIdList);
                break;
            case Milestone:

                switch (elementType ) {
                    case Criterion:
                        for (Milestone segment : project.getMilestones()) {
                            int i = 0;
                            for (CriterionList list : segment.getCriteriaIndexs()){
                                updateElementListFromSegment(elementIdList, list.getCriterions());
                                if (list.getCriterions().size() == 0){
                                    segment.getCriteriaIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;
                    default:
                }
                break;
            case Role:
                for (Role segment : project.getRoles()) {
                    List<Integer> type = segment.getType();
                    List<Integer> roleTypeId = dataModel.getRoleTypeId(type);
                    for(int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getRoleTypeIndexInProject(deleteId);
                        for (int i = 0; i < roleTypeId.size(); i++) {
                            int index = type.get(i);
                            if (roleTypeId.get(i) == deleteId) {
                                segment.getType().remove(i);
                                segment.getTypeIndicator().remove(i);
                            } else if (type.get(i) > deleteIndexInProject) {
                                segment.getType().remove(i);
                                segment.getType().add(i, index -1);
                                segment.getTypeIndicator().remove(i);
                            }
                        }
                    }
                }

                break;
            case ConfigPersonRelation:
                switch (elementType ) {
                    case Role:
                        for (ConfigPersonRelation segment : project.getCpr()) {

                            List<Integer> type = segment.getPersonIndex();
                            List<Integer> personId = dataModel.getRoleId(type);

                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                for (int i = 0; i < personId.size(); i++) {
                                    int index = type.get(i);
                                    if (personId.get(i) == deleteId) {
                                        segment.getPersonIndex().remove(i);
                                        segment.getPersonIndicator().remove(i);
                                    } else if (index > deleteIndexInProject) {
                                        segment.getPersonIndex().remove(i);
                                        segment.getPersonIndex().add(i, index -1);
                                        segment.getPersonIndicator().remove(index);
                                    }
                                }
                            }

                        }
                        break;
                        default:
                }
                break;
            case Artifact:
                switch (elementType ) {
                    case Role:
                        for (Artifact segment : project.getArtifacts()) {

                            List<Integer> type = segment.getAuthorIndex();
                            List<Integer> personId = dataModel.getRoleId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                for (int i = 0; i < personId.size(); i++) {
                                    int index = type.get(i);
                                    if (personId.get(i) == deleteId) {
                                        segment.getAuthorIndex().remove(i);
                                        segment.getAuthorIndicator().remove(i);
                                    } else if (index > deleteIndexInProject) {
                                        segment.getAuthorIndex().remove(i);
                                        segment.getAuthorIndex().add(i, index -1);
                                        segment.getAuthorIndicator().remove(index);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                }
                break;
            case Phase:
                switch (elementType ) {
                    case Milestone:
                        for (Phase segment : project.getPhases()) {

                            List<Integer> type = segment.getMilestoneIndex();
                            List<Integer> milestoneId = dataModel.getMilestoneId(type);

                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getMilestoneIndexInProject(deleteId);
                                for (int i = 0; i < type.size(); i++) {
                                    int index = type.get(i);
                                    if (milestoneId.get(i) == deleteId) {
                                        segment.getMilestoneIndex().remove(i);
                                        segment.getMilestoneIndicator().remove(i);
                                    } else if (index > deleteIndexInProject) {
                                        segment.getMilestoneIndex().remove(i);
                                        segment.getMilestoneIndex().add(i, index -1);
                                        segment.getMilestoneIndicator().remove(index);
                                    }
                                }
                            }
                        }
                        break;
                    case Configuration:
                        for (Phase segment : project.getPhases()) {

                            List<Integer> type = segment.getConfiguration();
                            List<Integer> configurationId = dataModel.getConfigurationId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getConfigurationIndexInProject(deleteId);
                                for (int i = 0; i < type.size(); i++) {
                                    int index = type.get(i);
                                    if( configurationId.get(i) == deleteId ){
                                        segment.getConfiguration().remove(i);
                                        segment.getConfigurationIndicator().remove(i);
                                    }else if(index > deleteIndexInProject ){
                                        segment.getConfiguration().remove(i);
                                        segment.getConfiguration().add(i, index -1);
                                        segment.getConfigurationIndicator().remove(i);
                                    }
                                }
                            }
                        }
                        break;
                    default:
                }
                break;
            case Iteration:
                switch (elementType ) {
                    case Configuration:
                        for (Iteration segment : project.getIterations()) {
                          List<Integer> type = segment.getConfiguration();
                            List<Integer> configurationId = dataModel.getConfigurationId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getConfigurationIndexInProject(deleteId);
                                for (int i = 0; i < type.size(); i++) {
                                    int index = type.get(i);
                                    if( configurationId.get(i) == deleteId ){
                                        segment.getConfiguration().remove(i);
                                        segment.getConfigurationIndicator().remove(i);
                                    }else if(index > deleteIndexInProject ){
                                        segment.getConfiguration().remove(i);
                                        segment.getConfiguration().add(i, index -1);
                                        segment.getConfigurationIndicator().remove(i);
                                    }
                                }
                            }
                        }
                        }
                break;
            case Configuration:

                switch (elementType ) {
                    case Role:
                        for (Configuration segment : project.getConfiguration()) {
                            List<Integer> type = segment.getAuthorIndex();
                            List<Integer> personId = dataModel.getRoleId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                for (int i = 0; i < personId.size(); i++) {
                                    int index = type.get(i);
                                    if (personId.get(i) == deleteId) {
                                        segment.getAuthorIndex().remove(i);
                                        segment.getAuthorIndicator().remove(i);
                                    } else if (index > deleteIndexInProject) {
                                        segment.getAuthorIndex().remove(i);
                                        segment.getAuthorIndex().add(i, index -1);
                                        segment.getAuthorIndicator().remove(i);
                                    }
                                }
                            }
                        }
                        break;
                    case ConfigPersonRelation:
                        for (Configuration segment : project.getConfiguration()) {
                            int i = 0;
                            for (CPRSList list : segment.getCPRsIndexs()){
                                updateElementListFromSegment(elementIdList, list.getCPRs());
                                if (list.getCPRs().size() == 0){
                                    segment.getCPRsIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;
                    case Branch:
                        for (Configuration segment : project.getConfiguration()) {
                            int i = 0;
                            for (BranchList list : segment.getBranchesIndexs()){
                                updateElementListFromSegment(elementIdList, list.getBranches());
                                if (list.getBranches().size() == 0){
                                    segment.getBranchesIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;

                    default:
                }
            default:

        }
    }

    private void updateConfigurationList(){
    }



    private void updateElementListFromSegmet(int changeValue, List<Integer> elementList ){

        int changeIndex = 0;
        int change = 0;

        for(int i = 0; i < elementList.size(); i++){
            if(changeValue == elementList.get(i)){
                changeIndex = i;
                change++;
            }
        }

        if(change == 0){
            for(int i = 0; i < elementList.size(); i++){
                if(changeValue > elementList.get(i)){
                    continue;
                }
                int value = elementList.get(i) - 1;
                elementList.set(i, value);
            }
            return;
        }

        for(int i = elementList.size() - 1; i >= 0 ; i--){
            if(elementList.get(i) == changeValue){
                break;
            }else {
                int value = elementList.get(i) - 1;
                elementList.set(i, value);
            }

        }

        elementList.remove(changeIndex);

    }

    private void updateElementListFromSegment(ArrayList<Integer> indices, List<Integer> elementList ){

        for (int j = indices.size() - 1; j >= 0; j--){

            updateElementListFromSegmet(indices.get(j), elementList);

        }
    }

    private void updateWUListItem(SegmentType element, ArrayList<Integer> elementIdList) {

        switch (element) {
            case Priority:
                for (WorkUnit segment : project.getWorkUnits()) {
//                    int type = segment.getPriorityIndex();
//                    int priorityId = dataModel.getPriorityId(type);
//
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getPriorityIndexInProject(deleteId);
//                        if( priorityId == deleteId ){
//                            segment.setPriorityIndex(-1);
//                        }else if(type > deleteIndexInProject ){
//                            segment.setPriorityIndex(type - 1);
//                        }
//                    }
                }
                break;
            case Severity:
                for (WorkUnit segment : project.getWorkUnits()) {
//                    int type = segment.getSeverityIndex();
//                    int priorityId = dataModel.getSeverityId(type);
//
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getSeverityIndexInProject(deleteId);
//                        if( priorityId == deleteId ){
//                            segment.setSeverityIndex(-1);
//                        }else if(type > deleteIndexInProject ){
//                            segment.setSeverityIndex(type - 1);
//                        }
//                    }
                }
                break;
            case Role:
                for (WorkUnit segment : project.getWorkUnits()) {
//                    int index = segment.getAuthorIndex();
//                    int index2 = segment.getAssigneeIndex();
//                    int personId = dataModel.getRoleId(index);
//                    int personId2 = dataModel.getRoleId(index);
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
//                        if( personId == deleteId ){
//                            segment.setAuthorIndex(-1);
//                        }else if(index > deleteIndexInProject ){
//                            segment.setAuthorIndex(index - 1);
//                        }
//                        if( personId2 == deleteId ){
//                            segment.setAssigneeIndex(-1);
//                        }else if(index2 > deleteIndexInProject ){
//                            segment.setAssigneeIndex(index2 - 1);
//                        }
//                    }
                }
                break;
            case Resolution:
                for (WorkUnit segment : project.getWorkUnits()) {
//                    int type = segment.getResolutionIndex();
//                    int priorityId = dataModel.getResolutionId(type);
//
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getResolutionIndexInProject(deleteId);
//                        if( priorityId == deleteId ){
//                            segment.setResolutionIndex(-1);
//                        }else if(type > deleteIndexInProject ){
//                            segment.setResolutionIndex(type - 1);
//                        }
//                    }
                }
                break;
            case Status:
                for (WorkUnit segment : project.getWorkUnits()) {
//                    int type = segment.getStatusIndex();
//                    int priorityId = dataModel.getStatusId(type);
//
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getStatusIndexInProject(deleteId);
//                        if( priorityId == deleteId ){
//                            segment.setStatusIndex(-1);
//                        }else if(type > deleteIndexInProject ){
//                            segment.setStatusIndex(type - 1);
//                        }
//                    }
                }
                break;
            case Type:

                for (WorkUnit segment : project.getWorkUnits()) {
//                    int type = segment.getTypeIndex();
//                    int priorityId = dataModel.getTypeId(type);
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getTypeIndexInProject(deleteId);
//                        if( priorityId == deleteId ){
//                            segment.setTypeIndex(-1);
//                        }else if(type > deleteIndexInProject ){
//                            segment.setTypeIndex(type - 1);
//                        }
//                    }
                }
                break;
            case Relation:

                break;
            default:

        }

    }

}
