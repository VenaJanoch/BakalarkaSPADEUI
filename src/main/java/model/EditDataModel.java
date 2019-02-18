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

    public void editDataInCPR(String nameForManipulator, int roleIndex, int id) {

        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);
        dataModel.addDataToCPR(cpr, nameForManipulator, roleIndex);

    }

    public void editDataInBranch(String nameForManipulator, boolean isMainBranch, int id) {
        Branch branch = dataModel.getBranch(id);
        dataModel.addDataToBranch(branch, nameForManipulator, isMainBranch);
    }

    public void editDataInArtifact(String nameForManipulator, String descForManipulator, LocalDate createdDate, boolean isCreate, int x, int y, int authorIndex, int typeIndex,
                                   int id){
        Artifact artifact = dataModel.getArtifact(id);
        dataModel.addDataToArtifact(artifact, nameForManipulator, descForManipulator, createdDate, isCreate, x, y, authorIndex, typeIndex);
    }

    public void editDataInChange(String nameForManipulator, String descForManipulator, int x, int y, boolean selected, int id){
        Change change =dataModel.getChange(id);
        dataModel.addDataToChange(change, nameForManipulator, descForManipulator, x, y, selected);
    }

    public void editDataInPhase(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, int x, int y,
                                ArrayList<Integer> itemIndexList, int id){
        Phase phase =  dataModel.getPhase(id);
        dataModel.addDataToPhase(phase, actName, endDateL, desc, confIndex, milestoneIndex, x, y, itemIndexList);
    }

    public void editDataInIteration(String nameForManipulator, LocalDate startDate, LocalDate endDate, String descriptionForManipulator,
                                    int configIndex, int x, int y, ArrayList<Integer> itemIndexList, int id) {
        Iteration iteration = dataModel.getIteration(id);
        dataModel.addDataToIteration(iteration, nameForManipulator, startDate, endDate, descriptionForManipulator, configIndex, x, y, itemIndexList);
    }

    public void editDataInActivity(String nameForManipulator, String descriptionForManipulator, int x, int y, ArrayList<Integer> setOfItemOnCanvas, int id) {
        Activity activity = dataModel.getActivity(id);
        dataModel.addDataToActivity(activity, nameForManipulator, descriptionForManipulator, x, y, setOfItemOnCanvas);

    }

    public void editDataInWorkUnit(String nameForManipulator,String description, String categoryForManipulator, int assigneIndex, int authorIndex,
                                   int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex, int statusIndex,
                                   int x, int y, double estimateForDataManipulator,boolean isExist, int id, boolean isProjectCanvas) {
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        dataModel.addDataToWorkUnit(workUnit, nameForManipulator, description, categoryForManipulator, assigneIndex, authorIndex, priorityIndex, severityIndex,
                typeIndex, resolutionIndex, statusIndex, x, y, estimateForDataManipulator, isExist, id, isProjectCanvas);
    }

    public void editDataInConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex,
                                        List<Integer> branches, List<Integer> cprs, ArrayList artifactIndexs, ArrayList changeIndexs, int id){
        Configuration configuration = dataModel.getConfiguration(id);
        dataModel.addDataToConfiguration(configuration, actName, createDate, isRelease, authorIndex, branches, cprs, artifactIndexs, changeIndexs);

    }

    public void editDataInCriterion(String nameForManipulator, String descForManipulator, int id){
        Criterion criterion = dataModel.getCriterion(id);
        dataModel.addDataToCriterion(criterion, nameForManipulator, descForManipulator);
    }

    public void editDataInPriority(String nameForManipulator, String classST, String superST, int id) {
        Priority priority = dataModel.getPriority(id);
        dataModel.addDataToPriority(priority, nameForManipulator, classST, superST);

    }

    public void editDataInSeverity(String nameForManipulator, String classST, String superST, int id) {
        Severity severity = dataModel.getSeverity(id);
        dataModel.addDataToSeverity(severity, nameForManipulator, classST, superST);
    }

    public void editDataInRelation(String nameForManipulator, String classST, String superST, int id) {

        Relation relation = dataModel.getRelation(id);
        relation.setRelationClass(classST);
        relation.setRelationSuperClass(superST);
        relation.setName(nameForManipulator);
    }

    public void editDataInResolution(String nameForManipulator, String classST, String superST, int id) {
        Resolution relation = dataModel.getResolution(id);
        dataModel.addDataToResolution(relation, nameForManipulator, classST, superST);
    }

    public void editDataInRole(String nameForManipulator, String descForManipulator, int type, int id) {

        Role role = dataModel.getRole(id);
        dataModel.addDatToRole(role, nameForManipulator, descForManipulator, type);
    }

    public void editDataInMilestone(String nameForManipulator, String descForManipulator, ArrayList<Integer> criterionIndex, int id) {
        Milestone milestone = dataModel.getMilestone(id);
        dataModel.addDataToMilestone(milestone, nameForManipulator, descForManipulator, criterionIndex);
    }

    public void editDataInRoleType(String nameForManipulator, String classST, String superST, int id) {
        RoleType roleType = dataModel.getRoleType(id);
        dataModel.addDataToRoleType(roleType, nameForManipulator, classST,superST);
    }

    public void editDataInStatus(String nameForManipulator, String classST, String superST, int id) {
        Status status = dataModel.getStatus(id);
        dataModel.addDataToStatus(status, nameForManipulator, classST, superST);
    }
    public void editDataInType(String nameForManipulator, String classST, String superST, int id) {
        Type type = dataModel.getType(id);;
        dataModel.addDataToType(type, nameForManipulator, classST, superST);
    }

    public void editTagInConfiguration(String tag, int configId, int id) {
        Configuration configuration = dataModel.getConfiguration(configId);
        configuration.getTags().remove(id);
        configuration.getTags().add(id, tag);
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
                            updateElementListFromSegment(elementIdList, segment.getCriteriaIndexs());
                        }
                        break;
                    default:
                }
                break;
            case Role:
                for (Role segment : project.getRoles()) {
                    int type = segment.getType();
                    int roleTypeId = dataModel.getRoleTypeId(type);
                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getRoleTypeIndexInProject(deleteId);
                        if( roleTypeId == deleteId ){
                            segment.setType(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setType(type - 1);
                        }
                    }

                }

                break;
            case ConfigPersonRelation:
                switch (elementType ) {
                    case Role:
                        for (ConfigPersonRelation segment : project.getCpr()) {

                            int type = segment.getPersonIndex();
                            int personId = dataModel.getRoleId(type);

                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                if( personId == deleteId ){
                                    segment.setPersonIndex(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setPersonIndex(type - 1);
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

                            int type = segment.getAuthorIndex();
                            int personId = dataModel.getRoleId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                if( personId == deleteId ){
                                    segment.setAuthorIndex(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setAuthorIndex(type - 1);
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

                            int type = segment.getMilestoneIndex();
                            int milestoneId = dataModel.getMilestoneId(type);

                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getMilestoneIndexInProject(deleteId);
                                if( milestoneId == deleteId ){
                                    segment.setMilestoneIndex(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setMilestoneIndex(type - 1);
                                }
                            }
                        }
                        break;
                    case Configuration:
                        for (Phase segment : project.getPhases()) {

                            int type = segment.getConfiguration();
                            int configurationId = dataModel.getConfigurationId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getConfigurationIndexInProject(deleteId);
                                if( configurationId == deleteId ){
                                    segment.setConfiguration(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setConfiguration(type - 1);
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

                            int type = segment.getConfiguration();
                            int configurationId = dataModel.getConfigurationId(type);
                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getConfigurationIndexInProject(deleteId);
                                if( configurationId == deleteId ){
                                    segment.setConfiguration(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setConfiguration(type - 1);
                                }
                            }
                        }
                        break;
                    default:
                }
                break;
            case Configuration:

                switch (elementType ) {
                    case Role:
                        for (Configuration segment : project.getConfiguration()) {
                            int type = segment.getAuthorIndex();
                            int personId = dataModel.getRoleId(type);

                            for(int deleteId : elementIdList){
                                int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                                if( personId == deleteId ){
                                    segment.setAuthorIndex(-1);
                                }else if(type > deleteIndexInProject ){
                                    segment.setAuthorIndex(type - 1);
                                }
                            }

                        }
                        break;
                    case ConfigPersonRelation:
                        for (Configuration segment : project.getConfiguration()) {
                            updateElementListFromSegment(elementIdList, segment.getCPRsIndexs());
                        }
                        break;
                    case Branch:
                        for (Configuration segment : project.getConfiguration()) {
                            updateElementListFromSegment(elementIdList, segment.getBranchesIndexs());
                        }
                        break;
                    default:
                }
            default:

        }
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
                    int type = segment.getPriorityIndex();
                    int priorityId = dataModel.getPriorityId(type);

                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getPriorityIndexInProject(deleteId);
                        if( priorityId == deleteId ){
                            segment.setPriorityIndex(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setPriorityIndex(type - 1);
                        }
                    }
                }
                break;
            case Severity:
                for (WorkUnit segment : project.getWorkUnits()) {
                    int type = segment.getSeverityIndex();
                    int priorityId = dataModel.getSeverityId(type);

                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getSeverityIndexInProject(deleteId);
                        if( priorityId == deleteId ){
                            segment.setSeverityIndex(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setSeverityIndex(type - 1);
                        }
                    }
                }
                break;
            case Role:
                for (WorkUnit segment : project.getWorkUnits()) {
                    int index = segment.getAuthorIndex();
                    int index2 = segment.getAssigneeIndex();
                    int personId = dataModel.getRoleId(index);
                    int personId2 = dataModel.getRoleId(index);
                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getRoleIndexInProject(deleteId);
                        if( personId == deleteId ){
                            segment.setAuthorIndex(-1);
                        }else if(index > deleteIndexInProject ){
                            segment.setAuthorIndex(index - 1);
                        }
                        if( personId2 == deleteId ){
                            segment.setAssigneeIndex(-1);
                        }else if(index2 > deleteIndexInProject ){
                            segment.setAssigneeIndex(index2 - 1);
                        }
                    }
                }
                break;
            case Resolution:
                for (WorkUnit segment : project.getWorkUnits()) {
                    int type = segment.getResolutionIndex();
                    int priorityId = dataModel.getResolutionId(type);

                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getResolutionIndexInProject(deleteId);
                        if( priorityId == deleteId ){
                            segment.setResolutionIndex(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setResolutionIndex(type - 1);
                        }
                    }
                }
                break;
            case Status:
                for (WorkUnit segment : project.getWorkUnits()) {
                    int type = segment.getStatusIndex();
                    int priorityId = dataModel.getStatusId(type);

                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getStatusIndexInProject(deleteId);
                        if( priorityId == deleteId ){
                            segment.setStatusIndex(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setStatusIndex(type - 1);
                        }
                    }
                }
                break;
            case Type:

                for (WorkUnit segment : project.getWorkUnits()) {
                    int type = segment.getTypeIndex();
                    int priorityId = dataModel.getTypeId(type);
                    for(int deleteId : elementIdList){
                        int deleteIndexInProject = dataModel.getTypeIndexInProject(deleteId);
                        if( priorityId == deleteId ){
                            segment.setTypeIndex(-1);
                        }else if(type > deleteIndexInProject ){
                            segment.setTypeIndex(type - 1);
                        }
                    }
                }
                break;
            case Relation:

                break;
            default:

        }

    }

}
