package model;

import SPADEPAC.*;
import XML.ProcessGenerator;
import interfaces.IDeleteDataModel;
import interfaces.IEditDataModel;
import interfaces.ISaveDataModel;
import org.omg.CORBA.INTERNAL;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class DataModel {

    private IEditDataModel editDataModel;
    private ISaveDataModel saveDataModel;
    private IDeleteDataModel deleteDataModel;
    private DataManipulator dataManipulator;
    private ProcessGenerator processGenerator;


    private Project project;
    private ObjectFactory objF;

    public DataModel(ProcessGenerator processGenerator) {
        this.objF = new ObjectFactory();
        this.project = objF.createProject();
        this.processGenerator = processGenerator;
        this.editDataModel = new EditDataModel(project, this);
        this.saveDataModel = new SaveDataModel(project, this, objF);
        this.deleteDataModel = new DeleteDataModel(project, this);
        this.dataManipulator = new DataManipulator(processGenerator, this);


    }

    public int getPhaseIndexInProject(int id) {
        List<Phase> items = project.getPhases();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getIterationIndexInProject(int id) {
        List<Iteration> items = project.getIterations();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getActivityIndexInProject(int id) {
        List<Activity> items = project.getActivities();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getMilestoneIndexInProject(int id) {
        List<Milestone> items = project.getMilestones();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getRoleTypeIndexInProject(int id) {
        List<RoleType> roleTypes = project.getRoleType();
        for (int i = 0; i < roleTypes.size(); i++) {

            if (roleTypes.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getSeverityIndexInProject(int id) {
        List<Severity> severities = project.getSeverity();
        for (int i = 0; i < severities.size(); i++) {

            if (severities.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getPriorityIndexInProject(int id) {
        List<Priority> item = project.getPriority();
        for (int i = 0; i < item.size(); i++) {

            if (item.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getStatusIndexInProject(int id) {
        List<Status> items = project.getStatus();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getTypeIndexInProject(int id) {
        List<Type> items = project.getTypes();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getRelationIndexInProject(int id) {
        List<Relation> items = project.getRelation();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getResolutionIndexInProject(int id) {
        List<Resolution> items = project.getResolution();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getRoleIndexInProject(int id) {
        List<Person> items = project.getRoles();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getVCSTagIndices(ArrayList<Integer> tagIndex) {
        List<VCSTag> vcsTages = project.getVcsTag();
        ArrayList<Integer> existVCSTagId = new ArrayList();

            for (int i : tagIndex) {
                if (i != -1) {
                    existVCSTagId.add(vcsTages.get(i).getId());
                }
            }
        return existVCSTagId;
    }
    

    public ArrayList<Integer> getBranchIndices(ArrayList<ArrayList<Integer>> branchIndex) {
        List<Branch> branches = project.getBranches();
        ArrayList<Integer> existBranchId = new ArrayList();
        for (ArrayList<Integer> list : branchIndex){
            for (int i : list) {
                if (i != -1) {
                    existBranchId.add(branches.get(i).getId());
                }
            }
        }
        return existBranchId;
    }

    public int getCPRIndexInProject(int id) {
        List<ConfigPersonRelation> items = project.getCpr();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getCPRIndices(ArrayList<Integer> cprIndex) {
        List<ConfigPersonRelation> cprs = project.getCpr();
        ArrayList<Integer> existCprId = new ArrayList();
        for (int i : cprIndex) {
            if (i != -1) {
                existCprId.add(cprs.get(i).getId());
            }

        }
        return existCprId;
    }

    public int getVCSTAgProjectIndex(int deleteId) {
        List<VCSTag> items = project.getVcsTag();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == deleteId) {
                return i;
            }
        }
        return -1;
    }
    
    public int getConfigurationIndexInProject(int id) {
        List<Configuration> items = project.getConfiguration();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }



    public int getArtifactIndexInProject(int id) {
        List<Artifact> items = project.getArtifacts();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getChangeIndexInProject(int id) {
        List<Change> items = project.getChanges();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getBranchIndexInProject(int id) {
        List<Branch> items = project.getBranches();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getCriterionIndexInProject(int id) {
        List<Criterion> items = project.getCriterions();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int getWUIndexInProject(int id) {
        List<WorkUnit> items = project.getWorkUnits();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Activity getActivity(int id) {
        return project.getActivities().get(getActivityIndexInProject(id));
    }

    public Artifact getArtifact(int id) {
        return project.getArtifacts().get(getArtifactIndexInProject(id));
    }

    public Branch getBranch(int id) {
        return project.getBranches().get(getBranchIndexInProject(id));
    }

    public Change getChange(int id) {
        return project.getChanges().get(getChangeIndexInProject(id));
    }

    public ConfigPersonRelation getConfigPersonRelation(int id) {
        return project.getCpr().get(getCPRIndexInProject(id));
    }

    public Configuration getConfiguration(int id) {
        return project.getConfiguration().get(getConfigurationIndexInProject(id));
    }

    public Criterion getCriterion(int id) {
        return project.getCriterions().get(getCriterionIndexInProject(id));
    }

    public Iteration getIteration(int id) {
        return project.getIterations().get(getIterationIndexInProject(id));
    }

    public Milestone getMilestone(int id) {
        return project.getMilestones().get(getMilestoneIndexInProject(id));
    }

    public Phase getPhase(int id) {
        return project.getPhases().get(getPhaseIndexInProject(id));
    }

    public Priority getPriority(int id) {
        return project.getPriority().get(getPriorityIndexInProject(id));
    }

    public Relation getRelation(int id) {
        return project.getRelation().get(getRelationIndexInProject(id));
    }

    public Resolution getResolution(int id) {
        return project.getResolution().get(getResolutionIndexInProject(id));
    }

    public Person getRole(int id) {
        return project.getRoles().get(getRoleIndexInProject(id));
    }

    public RoleType getRoleType(int id) {
        return project.getRoleType().get(getRoleTypeIndexInProject(id));
    }

    public Severity getSeverity(int id) {
        return project.getSeverity().get(getSeverityIndexInProject(id));
    }

    public Status getStatus(int id) {
        return project.getStatus().get(getStatusIndexInProject(id));
    }

    public Type getType(int id) {
        return project.getTypes().get(getTypeIndexInProject(id));
    }

    public WorkUnit getWorkUnit(int id) {
        return project.getWorkUnits().get(getWUIndexInProject(id));
    }


    public void addDataToCPR(ConfigPersonRelation cpr, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex
        , ArrayList<Integer> roleIndicators, boolean exist) {
       
        clearDataInCPR(cpr);
        cpr.getPersonIndex().addAll(roleIndex);
        cpr.getPersonIndicator().addAll(roleIndicators);
        cpr.getName().addAll(nameForManipulator);
        cpr.getNameIndicator().addAll(nameIndicators);
        cpr.setExist(exist);
    }
    
    private void clearDataInCPR(ConfigPersonRelation cpr){
        cpr.getPersonIndicator().clear();
        cpr.getPersonIndex().clear();
        cpr.getNameIndicator().clear();
        cpr.getName().clear();
    }

    public void addDataToBranch(Branch branch, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, boolean isMain, boolean exist) {
        
        clearDataInBranch(branch);
        branch.getName().addAll(nameForManipulator);
        branch.getNameIndicator().addAll(nameIndicators);
        branch.setIsMain(isMain);
        branch.setExist(exist);
    }
    
    private void clearDataInBranch(Branch branch){
        branch.getName().clear();
        branch.getNameIndicator().clear();
    }

    public void addDataToArtifact(Artifact artifact, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                                  ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                                  ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                                  ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                                  ArrayList<Integer> typeIndicator, int instanceCount) {
        clearDataInArtifact(artifact);
        
        artifact.getName().addAll(nameForManipulator);
        artifact.getAuthorIndex().addAll(authorIndex);
        artifact.getCreated().addAll(convertDate(createdDate));
        artifact.getDescription().addAll(descForManipulator);
        artifact.setExist(isCreate);
        artifact.setCount(instanceCount);
        
        artifact.getMimeType().addAll(getItemFormEnumList(ArtifactClass.values(), typeIndex));
        artifact.getNameIndicator().addAll(nameIndicators);
        artifact.getDescriptionIndicator().addAll(descriptionIndicators);
        artifact.getCreatedIndicator().addAll(dateIndicator);
        artifact.getMimeTypeIndicator().addAll(typeIndicator);
        artifact.getAuthorIndicator().addAll(authorIndicator);

    }
    
    private void clearDataInArtifact(Artifact artifact){
        artifact.getNameIndicator().clear();
        artifact.getName().clear();
        artifact.getAuthorIndicator().clear();
        artifact.getAuthorIndex().clear();
        artifact.getCreatedIndicator().clear();
        artifact.getCreated().clear();
        artifact.getDescriptionIndicator().clear();
        artifact.getDescription().clear();
        artifact.getMimeTypeIndicator().clear();
        artifact.getMimeType().clear();
    }

    private ArrayList<String> getItemFormEnumList(ArtifactClass[] values, ArrayList<Integer> typeIndex) {
        ArrayList<String> valueList = new ArrayList<>();
        for (int i = 0; i < typeIndex.size(); i++){
            valueList.add(values[typeIndex.get(i)].name());
        }
        return valueList;
    }

    public void addDataToChange(Change change, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                                ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected) {
        clearDataInChange(change);
        change.getName().addAll(nameForManipulator);
        change.getDescription().addAll(descForManipulator);
        change.getNameIndicator().addAll(nameIndicators);
        change.getDescriptionIndicator().addAll(descIndicator);
        change.getArtifactIndex().addAll(artifactForManipulator);
        change.setExist(selected);

    }
    
    private void clearDataInChange(Change change){
        change.getNameIndicator().clear();
        change.getName().clear();
        change.getDescriptionIndicator().clear();
        change.getDescription().clear();
    }

    public void addDataToPhase(Phase phase, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, boolean exist ) {
        clearDataInPhase(phase);
       phase.getName().addAll(actName);
       phase.getDescription().addAll(desc);
       phase.getConfiguration().addAll(confIndex);
       phase.getMilestoneIndex().addAll(milestoneIndex);
       phase.getEndDate().addAll(convertDate(endDateL));
        for (List<Integer> list : workUnitIndexList){
            phase.getWorkUnits().add(addWorkUnitList(list));
        }
        phase.getNameIndicator().addAll(nameIndicator);
        phase.getDescriptionIndicator().addAll(descIndicator);
        phase.getConfigurationIndicator().addAll(confIndicator);
        phase.getEndDateIndicator().addAll(endDateIndicator);
        phase.getMilestoneIndicator().addAll(milestoneIndicator);
        phase.getWorkUnitsIndicator().addAll(workUnitIndicators);
        phase.setExist(exist);
    }

    private void clearDataInPhase(Phase phase) {
        
        phase.getNameIndicator().clear();
        phase.getName().clear();
        phase.getDescriptionIndicator().clear();
        phase.getDescription().clear();
        phase.getConfigurationIndicator().clear();
        phase.getConfiguration().clear();
        phase.getMilestoneIndicator().clear();
        phase.getMilestoneIndex().clear();
        phase.getEndDateIndicator().clear();
        phase.getEndDate().clear();
        phase.getWorkUnitsIndicator().clear();
        phase.getWorkUnits().clear();
    }

    public void addDataToIteration(Iteration iteration,  ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                   ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                   ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                   ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, boolean exist) {
        clearDataInIteration(iteration);
        iteration.getName().addAll(actName);
        iteration.getDescription().addAll(desc);
        iteration.getEndDate().addAll(convertDate(endDateL));
        iteration.getStartDate().addAll(convertDate(startDateL));
        iteration.getConfiguration().addAll(confIndex);
        for (List<Integer> list : workUnitIndexList){
            iteration.getWorkUnits().add(addWorkUnitList(list));
        }

        iteration.getNameIndicator().addAll(nameIndicator);
        iteration.getDescriptionIndicator().addAll(descIndicator);
        iteration.getEndDateIndicator().addAll(endDateIndicator);
        iteration.getStartDateIndicator().addAll(startDateIndicator);
        iteration.getConfigurationIndicator().addAll(confIndicator);
        iteration.getWorkUnitsIndicator().addAll(workUnitIndicators);
        iteration.setExist(exist);

    }

    private void clearDataInIteration(Iteration iteration) {
        iteration.getNameIndicator().clear();
        iteration.getName().clear();
        iteration.getDescriptionIndicator().clear();
        iteration.getDescription().clear();
        iteration.getEndDateIndicator().clear();
        iteration.getEndDate().clear();
        iteration.getStartDateIndicator().clear();
        iteration.getStartDate().clear();
        iteration.getConfigurationIndicator().clear();
        iteration.getConfiguration().clear();
        iteration.getWorkUnitsIndicator().clear();
        iteration.getWorkUnits().clear();
    }

    public void addDataToCommitedConfiguration(CommitedConfiguration commitedConfiguration,ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                               ArrayList<LocalDate> startDate, ArrayList<Integer> dateIndicator, int instanceCount, boolean exist) {
        
        clearDataInCommitedConfiguration(commitedConfiguration);
        commitedConfiguration.getName().addAll(nameForManipulator);
        commitedConfiguration.getNameIndicator().addAll(nameIndicator);
        commitedConfiguration.getCommitedDayIndicator().addAll(dateIndicator);
        commitedConfiguration.getCommitedDay().addAll(convertDate(startDate));
        commitedConfiguration.setCount(instanceCount);
        commitedConfiguration.setExist(exist);
    }

    private void clearDataInCommitedConfiguration(CommitedConfiguration commitedConfiguration) {
        commitedConfiguration.getNameIndicator().clear();
        commitedConfiguration.getName().clear();
        commitedConfiguration.getCommitedDayIndicator().clear();
        commitedConfiguration.getCommitedDay().clear();
    }


    public WorkUnitList addWorkUnitList(List<Integer> list){
        WorkUnitList wulist = objF.createWorkUnitList();
        wulist.getWorkUnits().addAll(list);
        return wulist;
    }


    public void addDataToActivity(Activity activity, List<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                  ArrayList<ArrayList<Integer>> setOfItemOnCanvas, ArrayList<Integer> nameIndicators,
                                  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators, boolean exist) {
        
        clearDataInActivity(activity);
        activity.getDescription().addAll(descriptionForManipulator);
        activity.getName().addAll(nameForManipulator);
        for (List<Integer> list : setOfItemOnCanvas){
            activity.getWorkUnits().add(addWorkUnitList(list));
        }
        activity.getNameIndicator().addAll(nameIndicators);
        activity.getDescriptionIndicator().addAll(descIndicators);
        activity.getWorkUnitsIndicator().addAll(workUnitIndicators);
        activity.setExist(exist);
    }

    private void clearDataInActivity(Activity activity) {
        activity.getNameIndicator().clear();
        activity.getName().clear();
        activity.getDescriptionIndicator().clear();
        activity.getDescription().clear();
        activity.getWorkUnits().clear();
        activity.getDescriptionIndicator().clear();
    }


    public void addDataToWorkUnit(WorkUnit workUnit, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                                  ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                  ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                  ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                  ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                  ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                  ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,
                                  ArrayList<ArrayList<Integer>> workUnits) {
        clearDataInWorkUnit(workUnit);
        
        workUnit.getAssigneeIndex().addAll(assigneIndex);
        workUnit.getAuthorIndex().addAll(authorIndex);
        workUnit.getCategory().addAll(categoryForManipulator);
        workUnit.getDescription().addAll(description);
        workUnit.getEstimatedTime().addAll(estimateForDataManipulator);
        workUnit.setExist(isExist);
        workUnit.getName().addAll(nameForManipulator);
        workUnit.getPriorityIndex().addAll(priorityIndex);
        workUnit.getSeverityIndex().addAll(severityIndex);
        workUnit.getTypeIndex().addAll(typeIndex);
        workUnit.getStatusIndex().addAll(statusIndex);
        workUnit.getResolutionIndex().addAll(resolutionIndex);
        workUnit.getRelationIndex().addAll(relations);
        for (List<Integer> list : workUnits){
            workUnit.getWorkUnits().add(addWorkUnitList(list));
        }
        workUnit.getAssigneeIndicator().addAll(assigneIndicator);
        workUnit.getAuthorIIndicator().addAll(authorIndicator);
        workUnit.getCategoryIndicator().addAll(categoryIndicator);
        workUnit.getDescriptionIndicator().addAll(descriptionIndicator);
        workUnit.getEstimatedTimeIndicator().addAll(estimateIndicator);
        workUnit.getNameIndicator().addAll(nameIndicator);
        workUnit.getPriorityIndicator().addAll(priorityIndicator);
        workUnit.getSeverityIndicator().addAll(severityIndicator);
        workUnit.getTypeIndicator().addAll(typeIndicator);
        workUnit.getStatusIndicator().addAll(statusIndicator);
        workUnit.getResolutionIndicator().addAll(resolutionIndicator);
    }

    private void clearDataInWorkUnit(WorkUnit workUnit) {
        workUnit.getAssigneeIndex().clear();
        workUnit.getAuthorIndex().clear();
        workUnit.getCategory().clear();
        workUnit.getDescription().clear();
        workUnit.getEstimatedTime().clear();;
        workUnit.getName().clear();
        workUnit.getPriorityIndex().clear();
        workUnit.getSeverityIndex().clear();
        workUnit.getTypeIndex().clear();
        workUnit.getStatusIndex().clear();
        workUnit.getResolutionIndex().clear();
        workUnit.getRelationIndex().clear();
        workUnit.getWorkUnits().clear();

        workUnit.getAssigneeIndicator().clear();
        workUnit.getAuthorIIndicator().clear();
        workUnit.getCategoryIndicator().clear();
        workUnit.getDescriptionIndicator().clear();
        workUnit.getEstimatedTimeIndicator().clear();
        workUnit.getNameIndicator().clear();
        workUnit.getPriorityIndicator().clear();
        workUnit.getSeverityIndicator().clear();
        workUnit.getTypeIndicator().clear();
        workUnit.getStatusIndicator().clear();
        workUnit.getResolutionIndicator().clear();
     
    }

    public void addDataToConfiguration(Configuration configuration, ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                       boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                       ArrayList<ArrayList<Integer>> changeIndexs,
                                       ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                       ArrayList<Integer> authorIndicator, ArrayList<Integer> changeIndicator, int instanceCount, boolean exist) {
        clearDataInConfiguration(configuration);
        configuration.getName().addAll(actName);
        configuration.getCreated().addAll(convertDate(createDate));
        configuration.getAuthorIndex().addAll(authorIndex);
        configuration.getNameIndicator().addAll(nameIndicator);
        configuration.getCreatedIndicator().addAll(createdIndicator);
        configuration.getAuthorIndicator().addAll(authorIndicator);
        configuration.getCPRsIndicator().addAll(cprIndicators);
        configuration.getChangesIndicator().addAll(changeIndicator);
        configuration.setIsRelease(isRelease);
        configuration.setCount(instanceCount);
        configuration.setExist(exist);
        for (List<Integer> list : cprs){
            CPRSList cprList = objF.createCPRSList();
            cprList.getCPRs().addAll(list);
            configuration.getCPRsIndexs().add(cprList);
        }



        for (List<Integer> list : changeIndexs){
            ChangeList cprList = objF.createChangeList();
            cprList.getChanges().addAll(list);
            configuration.getChangesIndexs().add(cprList);
        }
    }

    private void clearDataInConfiguration(Configuration configuration) {
        configuration.getCPRsIndexs().clear();
        configuration.getChangesIndexs().clear();
        configuration.getName().clear();
        configuration.getCreated().clear();
        configuration.getAuthorIndex().clear();
        configuration.getNameIndicator().clear();
        configuration.getCreatedIndicator().clear();
        configuration.getAuthorIndicator().clear();
        configuration.getCPRsIndicator().clear();
        configuration.getChangesIndicator().clear();
        configuration.getBranchesIndicator().clear();
    }


    public void addDataToCriterion(Criterion criterion, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, boolean exist) {
        clearDataInCriterion(criterion);
        criterion.getName().addAll(nameForManipulator);
        criterion.getDescription().addAll(descForManipulator);
        criterion.getNameIndicator().addAll(nameIndicator);
        criterion.getDescriptionIndicator().addAll(descIndicator);
        criterion.setExist(exist);
    }

    private void clearDataInCriterion(Criterion criterion) {
        criterion.getDescriptionIndicator().clear();
        criterion.getDescription().clear();
        criterion.getNameIndicator().clear();
        criterion.getName().clear();
    }

    public void addDataToPriority(Priority priority, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {

        clearDataInPriority(priority);
        priority.getName().addAll(nameForManipulator);
        priority.getNameIndicator().addAll(nameIndicator);
        priority.getPriorityClass().addAll(classString);
        priority.getPriorityClassIndex().addAll(classST);
        priority.getPrioritySuperClass().addAll(superSting);
        priority.getPrioritySuperClassIndex().addAll(superST);
        priority.setExist(exist);
    }

    private void clearDataInPriority(Priority priority) {
        priority.getNameIndicator().clear();
        priority.getName().clear();
        priority.getPrioritySuperClass().clear();
        priority.getPrioritySuperClassIndex().clear();
        priority.getPriorityClass().clear();
        priority.getPriorityClassIndex().clear();
        priority.getPriorityClassIndicator().clear();
        priority.getPriorityClassIndicator().clear();
    }

    public void addDataToSeverity(Severity severity, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
         clearDataInSeverity(severity);
        severity.getName().addAll(nameForManipulator);
        severity.getNameIndicator().addAll(nameIndicator);
        severity.getSeverityClass().addAll(classString);
        severity.getSeverityClassIndex().addAll(classST);
        severity.getSeveritySuperClass().addAll(superSting);
        severity.getSeveritySuperClassIndex().addAll(superST);
        severity.setExist(exist);
    }

    private void clearDataInSeverity(Severity severity) {
        severity.getNameIndicator().clear();
        severity.getName().clear();
        severity.getSeveritySuperClass().clear();
        severity.getSeveritySuperClassIndex().clear();
        severity.getSeverityClass().clear();
        severity.getSeverityClassIndex().clear();
        severity.getSeverityClassIndicator().clear();
        severity.getSeverityClassIndicator().clear();
    }
    
    public void addDataToRoleType(RoleType roleType, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descForManipulator, ArrayList<Integer> descIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
        clearDataInRoleType(roleType);
        roleType.getDescriptionIndicator().addAll(descIndicator);
        roleType.getDescription().addAll(descForManipulator);
        roleType.getName().addAll(nameForManipulator);
        roleType.getNameIndicator().addAll(nameIndicator);
        roleType.getRoleTypeClass().addAll(classString);
        roleType.getRoleTypeClassIndex().addAll(classST);
        roleType.getRoleTypeSuperClass().addAll(superSting);
        roleType.getRoleTypeSuperClassIndex().addAll(superST);
        roleType.setExist(exist);
    }

    private void clearDataInRoleType(RoleType roleType) {
        roleType.getDescription().clear();
        roleType.getDescriptionIndicator().clear();
        roleType.getNameIndicator().clear();
        roleType.getName().clear();
        roleType.getRoleTypeSuperClass().clear();
        roleType.getRoleTypeSuperClassIndex().clear();
        roleType.getRoleTypeClass().clear();
        roleType.getRoleTypeClassIndex().clear();
        roleType.getRoleTypeClassIndicator().clear();
        roleType.getRoleTypeClassIndicator().clear();
    }
    
    public void addDataToStatus(Status status, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
        clearDataInStatus(status);
        status.getName().addAll(nameForManipulator);
        status.getNameIndicator().addAll(nameIndicator);
        status.getStatusClass().addAll(classString);
        status.getStatusClassIndex().addAll(classST);
        status.getStatusSuperClass().addAll(superSting);
        status.getStatusSuperClassIndex().addAll(superST);
        status.setExist(exist);
    }

    private void clearDataInStatus(Status status) {
        status.getNameIndicator().clear();
        status.getName().clear();
        status.getStatusSuperClass().clear();
        status.getStatusSuperClassIndex().clear();
        status.getStatusClass().clear();
        status.getStatusClassIndex().clear();
        status.getStatusClassIndicator().clear();
        status.getStatusClassIndicator().clear();
    }

    public void addDataToType(Type type, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
        clearDataInType(type);
        type.getName().addAll(nameForManipulator);
        type.getNameIndicator().addAll(nameIndicator);
        type.getTypeClass().addAll(classString);
        type.getTypeClassIndex().addAll(classST);
        type.getTypeSuperClass().addAll(superSting);
        type.getTypeSuperClassIndex().addAll(superST);
        type.setExist(exist);
    }

    private void clearDataInType(Type type) {
        type.getNameIndicator().clear();
        type.getName().clear();
        type.getTypeSuperClass().clear();
        type.getTypeSuperClassIndex().clear();
        type.getTypeClass().clear();
        type.getTypeClassIndex().clear();
        type.getTypeClassIndicator().clear();
        type.getTypeClassIndicator().clear();
    }
    
    public void addDataToRelation(Relation relation, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
        clearDataInRelation(relation);
        relation.getName().addAll(nameForManipulator);
        relation.getNameIndicator().addAll(nameIndicator);
        relation.getRelationClass().addAll(classString);
        relation.getRelationClassIndex().addAll(classST);
        relation.getRelationSuperClass().addAll(superSting);
        relation.getRelationSuperClassIndex().addAll(superST);
        relation.setExist(exist);
    }

    private void clearDataInRelation(Relation relation) {
        relation.getNameIndicator().clear();
        relation.getName().clear();
        relation.getRelationSuperClass().clear();
        relation.getRelationSuperClassIndex().clear();
        relation.getRelationClass().clear();
        relation.getRelationClassIndex().clear();
        relation.getRelationClassIndicator().clear();
        relation.getRelationClassIndicator().clear();
    }

    public void addDataToResolution(Resolution resolution, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting, boolean exist) {
        clearDataInResolution(resolution);
        resolution.getName().addAll(nameForManipulator);
        resolution.getNameIndicator().addAll(nameIndicator);
        resolution.getResolutionClass().addAll(classString);
        resolution.getResolutionClassIndex().addAll(classST);
        resolution.getResolutionSuperClass().addAll(superSting);
        resolution.getResolutionSuperClassIndex().addAll(superST);
        resolution.setExist(exist);
    }

    private void clearDataInResolution(Resolution resolution) {
        resolution.getNameIndicator().clear();
        resolution.getName().clear();
        resolution.getResolutionSuperClass().clear();
        resolution.getResolutionSuperClassIndex().clear();
        resolution.getResolutionClass().clear();
        resolution.getResolutionClassIndex().clear();
        resolution.getResolutionClassIndicator().clear();
        resolution.getResolutionClassIndicator().clear();
    }


    public void addDatToRole(Person role, ArrayList<String> nameForManipulator, ArrayList<Integer> type,
                             ArrayList<Integer> nameIndicator,  ArrayList<Integer> typeIndicator, int instanceCount, boolean exist) {
        clearDataInRole(role);
        role.getName().addAll(nameForManipulator);
        role.getNameIndicator().addAll(nameIndicator);
        role.getTypeIndicator().addAll(typeIndicator);
        role.setExist(exist);

        role.setCount(instanceCount);
        role.getType().addAll(type);
    }

    private void clearDataInRole(Person role) {
        role.getName().clear();
        role.getNameIndicator().clear();
        role.getTypeIndicator().clear();
        role.getType().clear();
    }


    public void addDataToProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                                 ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                                 ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators) {
        
        clearDataInProject();
        
        project.getName().addAll(nameForManipulator);
        project.getDescription().addAll(descriptionForManipulator);
        project.getEndDate().addAll(convertDate(endDate1));
        project.getStartDate().addAll(convertDate(startDate1));

        for (List<Integer> list : workUnitsForManipulator){
            project.getWorkUnitIndexs().add(addWorkUnitList(list));
        }

        project.getNameIndicator().addAll(nameIndicators);
        project.getDescriptionIndicator().addAll(descIndicators);
        project.getEndDateIndicator().addAll(date1Indicators);
        project.getStartDateIndicator().addAll(date2Indicators);
        project.getWorkUnitsIndicator().addAll(workUnitIndicators);

    }

    private void clearDataInProject() {
        project.getName().clear();
        project.getDescription().clear();
        project.getEndDate().clear();
        project.getStartDate().clear();
        project.getWorkUnitIndexs().clear();

        project.getNameIndicator().clear();
        project.getDescriptionIndicator().clear();
        project.getEndDateIndicator().clear();
        project.getStartDateIndicator().clear();
        project.getWorkUnitsIndicator().clear();
    }

    public void addDataToMilestone(Milestone milestone, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                                   ArrayList<ArrayList<Integer>> criterionIndex, boolean exist) {
        clearDataInMilestone(milestone);
        milestone.getCriteriaIndexs().clear();
        milestone.setExist(exist);
        for (List<Integer> list : criterionIndex){
            CriterionList cprList = objF.createCriterionList();
            cprList.getCriterions().addAll(list);
            milestone.getCriteriaIndexs().add(cprList);
        }

        milestone.getName().addAll(nameForManipulator);
        milestone.getDescription().addAll(descForManipulator);
        milestone.getCriteriaIndicator().addAll(criterionIndicator);
        milestone.getNameIndicator().addAll(nameIndicator);
        milestone.getDescriptionIndicator().addAll(descIndicator);
    }

    private void clearDataInMilestone(Milestone milestone) {
        milestone.getCriteriaIndexs().clear();
        milestone.getName().clear();
        milestone.getDescription().clear();
        milestone.getCriteriaIndicator().clear();
        milestone.getNameIndicator().clear();
        milestone.getDescriptionIndicator().clear();
    }

    public void addDataToVCSTag(VCSTag tag, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, boolean exist) {
        clearDataInVCSTag(tag);
        tag.getName().addAll(nameForManipulator);
        tag.getNameIndicator().addAll(nameIndicator);
        tag.getDescription().addAll(descriptionForManipulator);
        tag.getDescriptionIndicator().addAll(descriptionIndicator);
        tag.setExist(exist);
    }

    private void clearDataInVCSTag(VCSTag tag) {
        tag.getNameIndicator().clear();
        tag.getName().clear();
        tag.getDescriptionIndicator().clear();
        tag.getDescription().clear();
    }

    public void addDataToCommit(Commit commit, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,  ArrayList<Integer> tag,
                                ArrayList<Integer> tagIndicator, ArrayList<ArrayList<Integer>> branches, ArrayList<Integer> branchIndicator,
                                boolean release, int instanceCount, boolean exist) {
        clearDataInCommit(commit);
        commit.setRelease(release);
        commit.getName().addAll(nameForManipulator);
        commit.getNameIndicator().addAll(nameIndicator);
        commit.getTags().addAll(tag);
        commit.getTagsIndicator().addAll(tagIndicator);
        for (List<Integer> list : branches){
            BranchList cprList = objF.createBranchList();
            cprList.getBranches().addAll(list);
            commit.getBranch().add(cprList);
        }
        commit.getBranchIndicator().addAll(branchIndicator);
        commit.setCount(instanceCount);
        commit.setExist(exist);
    }

    public void setCoordinatesToCommit(Coordinates coordinates, Commit commit){
        commit.setCoordinates(coordinates);
    }

    public void setCoordinatesToCommitedConfiguration(Coordinates coordinates, CommitedConfiguration commitedConfiguration){
        commitedConfiguration.setCoordinates(coordinates);
    }

    public void setCoordinatesToConfiguration(Coordinates coordinates, Configuration configuration){
        configuration.setCoordinates(coordinates);
    }

    public void setCoordinatesToArtifact(Coordinates coordinates, Artifact artifact){
        artifact.setCoordinates(coordinates);
    }

    public void setCoordinatesToRole(Coordinates coordinates, Person role){
        role.setCoordinates(coordinates);
    }
    
    private void clearDataInCommit(Commit commit) {
        commit.getNameIndicator().clear();
        commit.getName().clear();
        commit.getTagsIndicator().clear();
        commit.getTags().clear();
        commit.getBranchIndicator().clear();
        commit.getBranch().clear();
    }


    public int getMilestoneId(int milestoneIndexForManipulator) {

        return project.getMilestones().get(milestoneIndexForManipulator).getId();

    }

    public ArrayList<Integer> getMilestoneId(List<Integer> milestoneIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : milestoneIndex){
            list.add(getMilestoneId(i));
        }
        return list;
    }

    public ArrayList<Integer> getRoleId(List<Integer> roleIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : roleIndex){
            list.add(getRoleId(i));
        }
        return list;
    }


    public int getRoleId(int roleIndex) {
        int index = roleIndex;
        if (roleIndex != -1) {
            index = project.getRoles().get(roleIndex).getId();
        }
        return index;
    }

    public ArrayList<Integer> getRoleTypeIndex(ArrayList<Integer> typeFormManipulator) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : typeFormManipulator){
            list.add(getRoleTypeIndex(i));
        }
        return list;
    }

    public int getRoleTypeIndex(int typeFormManipulator) {
        int index = typeFormManipulator;
        if (typeFormManipulator != -1) {
            index = project.getRoleType().get(typeFormManipulator).getId();
        }
        return index;
    }

    public ArrayList getWorkUnitIds(ArrayList<ArrayList<Integer>> workUnitIndex) {

        List<WorkUnit> criterion = project.getWorkUnits();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for (ArrayList<Integer> list : workUnitIndex){
            for (int i : list) {
                if (i != -1) {
                    existCriterionId.add(criterion.get(i).getId());
                }

            }
        }
        return existCriterionId;
    }

    public ArrayList getCriterionIds(ArrayList<ArrayList<Integer>> criterionIndex) {

        List<Criterion> criterion = project.getCriterions();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for (ArrayList<Integer> list : criterionIndex){
        for (int i : list) {
            if (i != -1) {
                existCriterionId.add(criterion.get(i).getId());
            }

        }
        }
        return existCriterionId;
    }


   


    public static ArrayList<XMLGregorianCalendar> convertDate(ArrayList<LocalDate> date) {

        ArrayList<XMLGregorianCalendar> list = new ArrayList<>();

        for(LocalDate lDate : date){
            list.add(convertDate(lDate));
        }

        return list;
    }


    /**
     * Umožní převedení data ve formátu LocalDate do formátu
     * XMLGregorianCalendar pro uložení do XML
     *
     * @param Ldate LocalDate
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertDate(LocalDate Ldate) {

        if (Ldate == null) {
            return null;
        }

        Instant instant = Instant.from(Ldate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        GregorianCalendar c = new GregorianCalendar();

        XMLGregorianCalendar dateXML = null;
        try {

            c.setTime(date);
            dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateXML;
    }

    public Coordinates createCoords(int x, int y) {
        Coordinates coord = objF.createCoordinates();
        coord.setXCoordinate(x);
        coord.setYCoordinate(y);
        return coord;
    }

    public void saveProcess(File xmlProcessFile) {
        processGenerator.saveProcess(xmlProcessFile, project);
    }

    public void parseProject(File xmlProject) {
        project = processGenerator.readProcess(xmlProject);
    }

    /**
     * Pomocná metoda pro validaci souboru
     */

    public void validate() {

        processGenerator.validate(project);
    }


    public IEditDataModel getEditDataModel() {
        return editDataModel;
    }

    public ISaveDataModel getSaveDataModel() {
        return saveDataModel;
    }

    public IDeleteDataModel getDeleteDataModel() {
        return deleteDataModel;
    }

    public DataManipulator getDataManipulator() {
        return dataManipulator;
    }

    public List<Milestone> getMilestones() {
        return project.getMilestones();
    }

    public List<Person> getRoles() {
        return project.getRoles();
    }

    public List<ConfigPersonRelation> getCpr() {
        return project.getCpr();
    }

    public Project getProject() {
        return project;
    }

    public ObjectFactory getObjF() {
        return objF;
    }

    public ArrayList<Integer> getConfigurationId(List<Integer> index) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<Configuration> configList = project.getConfiguration();
        for (int configurationIndex : index){
            idList.add(configList.get(configurationIndex).getId());
        }
        return idList;
    }

    public List<Integer> getTagId(List<Integer> index) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<VCSTag> configList = project.getVcsTag();
        for (int configurationIndex : index){
            idList.add(configList.get(configurationIndex).getId());
        }
        return idList;

    }

    public ArrayList<Integer> getArtifactId(List<Integer> artifactForManipulator) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<Artifact> artifactList = project.getArtifacts();
        for (int artifactIndex : artifactForManipulator){
            idList.add(artifactList.get(artifactIndex).getId());
        }
        return idList;
    }

    public int getArtifactId(int index){
        return project.getArtifacts().get(index).getId();
    }

    public int getCommitedConfigurationId(int index){
        return project.getCommitConfiguration().get(index).getId();
    }

    public ArrayList<Integer> getCommitedConfigurationId(List<Integer> artifactForManipulator){
        ArrayList<Integer> idList = new ArrayList<>();
        List<CommitedConfiguration> artifactList = project.getCommitConfiguration();
        for (int artifactIndex : artifactForManipulator){
            idList.add(artifactList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getRoleTypeId(List<Integer> roleTypeForManipulator) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<RoleType> roleTypeList = project.getRoleType();
        for (int artifactIndex : roleTypeForManipulator){
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public int getPriorityId(int index) {

        return project.getPriority().get(index).getId();
    }

    public int getSeverityId(int index) {

        return project.getSeverity().get(index).getId();
    }

    public int getTypeId(int index) {

        return project.getTypes().get(index).getId();
    }

    public int getResolutionId(int index) {

        return project.getResolution().get(index).getId();
    }

    public int getRelationId(int index) {

        return project.getRelation().get(index).getId();
    }

    public int getStatusId(int index) {

        return project.getStatus().get(index).getId();
    }


    public VCSTag getVCSTag(int id) {
        return project.getVcsTag().get(getVCSTagIndexInProject(id));
    }

    private int getVCSTagIndexInProject(int id) {
        List<VCSTag> items = project.getVcsTag();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Commit getCommit(int commitId) {
        return project.getCommit().get(getCommitIndexInProject(commitId));
    }

    public int getCommitIndexInProject(int id) {
        List<Commit> items = project.getCommit();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public CommitedConfiguration getCommitedConfiguration(int commitedId)  {
        return project.getCommitConfiguration().get(getCommitedConfigurationIndexInProject(commitedId));
    }

    public int getCommitedConfigurationIndexInProject(int id) {
        List<CommitedConfiguration> items = project.getCommitConfiguration();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    public int getLinkIndexInProject(int arrowId) {
        List<Link> items = project.getLinks();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == arrowId) {
                return i;
            }
        }
        return -1;
    }

    public int getCommitId(int commitIndex) {
        return project.getCommit().get(commitIndex).getId();
    }
}
