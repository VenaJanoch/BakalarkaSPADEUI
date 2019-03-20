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
        List<Role> items = project.getRoles();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> getBranchIndices(ArrayList<Integer> branchIndex) {
        List<Branch> branches = project.getBranches();
        ArrayList<Integer> existBranchId = new ArrayList();
        for (int i : branchIndex) {
            if (i != -1) {
                existBranchId.add(branches.get(i).getId());
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

    public Role getRole(int id) {
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
        , ArrayList<Integer> roleIndicators) {
        cpr.getPersonIndex().addAll(roleIndex);
        cpr.getPersonIndicator().addAll(roleIndicators);
        cpr.getName().addAll(nameForManipulator);
        cpr.getNameIndicator().addAll(nameIndicators);
    }

    public void addDataToBranch(Branch branch, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, boolean isMain) {
        branch.getName().addAll(nameForManipulator);
        branch.getNameIndicator().addAll(nameIndicators);
        branch.setIsMain(isMain);
    }

    public void addDataToArtifact(Artifact artifact, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                                  ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                                  ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                                  ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                                  ArrayList<Integer> typeIndicator, int instanceCount) {
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

    private ArrayList<String> getItemFormEnumList(ArtifactClass[] values, ArrayList<Integer> typeIndex) {
        ArrayList<String> valueList = new ArrayList<>();
        for (int i = 0; i < typeIndex.size(); i++){
            valueList.add(values[typeIndex.get(i)].name());
        }
        return valueList;
    }

    public void addDataToChange(Change change, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected) {
        change.getName().addAll(nameForManipulator);
        change.getDescription().addAll(descForManipulator);
        change.getNameIndicator().addAll(nameIndicators);
        change.getDescriptionIndicator().addAll(descIndicator);
        change.setExist(selected);
    }

    public void addDataToPhase(Phase phase, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator ) {

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
        phase.getWorkUnits().clear();
    }

    public void addDataToIteration(Iteration iteration,  ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                   ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                   ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                   ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator) {

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

    }

    public void addDataToCommitedConfiguration(CommitedConfiguration commitedConfiguration,ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                               ArrayList<LocalDate> startDate, ArrayList<Integer> dateIndicator, int instanceCount) {
        commitedConfiguration.getName().addAll(nameForManipulator);
        commitedConfiguration.getNameIndicator().addAll(nameIndicator);
        commitedConfiguration.getCommitedDayIndicator().addAll(dateIndicator);
        commitedConfiguration.getCommitedDay().addAll(convertDate(startDate));
        commitedConfiguration.setCount(instanceCount);
    }

    public WorkUnitList addWorkUnitList(List<Integer> list){
        WorkUnitList wulist = objF.createWorkUnitList();
        wulist.getWorkUnits().addAll(list);
        return wulist;
    }


    public void addDataToActivity(Activity activity, List<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                  ArrayList<ArrayList<Integer>> setOfItemOnCanvas, ArrayList<Integer> nameIndicators,
                                  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators) {

        activity.getDescription().addAll(descriptionForManipulator);
        activity.getName().addAll(nameForManipulator);
        for (List<Integer> list : setOfItemOnCanvas){
            activity.getWorkUnits().add(addWorkUnitList(list));
        }
        activity.getNameIndicator().addAll(nameIndicators);
        activity.getDescriptionIndicator().addAll(descIndicators);
        activity.getWorkUnitsIndicator().addAll(workUnitIndicators);

    }

    public void addDataToWorkUnit(WorkUnit workUnit, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                                  ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                  ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                  ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                  ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                  ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                  ArrayList<Integer> estimateIndicator, boolean isExist) {

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

    public void addDataToConfiguration(Configuration configuration, ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                       boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                       ArrayList<ArrayList<Integer>> branches, ArrayList<ArrayList<Integer>> changeIndexs,
                                       ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                       ArrayList<Integer> authorIndicator, ArrayList<Integer> branchIndicator, ArrayList<Integer> changeIndicator, int instanceCount) {

        configuration.getName().addAll(actName);
        configuration.getCreated().addAll(convertDate(createDate));
        configuration.getAuthorIndex().addAll(authorIndex);
        configuration.getNameIndicator().addAll(nameIndicator);
        configuration.getCreatedIndicator().addAll(createdIndicator);
        configuration.getAuthorIndicator().addAll(authorIndicator);
        configuration.getCPRsIndicator().addAll(cprIndicators);
        configuration.getChangesIndicator().addAll(changeIndicator);
        configuration.getBranchesIndicator().addAll(branchIndicator);
        configuration.setIsRelease(isRelease);
        configuration.setCount(instanceCount);

        configuration.getBranchesIndexs().clear();
        configuration.getCPRsIndexs().clear();
        configuration.getChangesIndexs().clear();

        for (List<Integer> list : cprs){
            CPRSList cprList = objF.createCPRSList();
            cprList.getCPRs().addAll(list);
            configuration.getCPRsIndexs().add(cprList);
        }

        for (List<Integer> list : branches){
            BranchList cprList = objF.createBranchList();
            cprList.getBranches().addAll(list);
            configuration.getBranchesIndexs().add(cprList);
        }

        for (List<Integer> list : changeIndexs){
            ChangeList cprList = objF.createChangeList();
            cprList.getChanges().addAll(list);
            configuration.getChangesIndexs().add(cprList);
        }
    }


    public void addDataToCriterion(Criterion criterion, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator) {
        criterion.getName().addAll(nameForManipulator);
        criterion.getDescription().addAll(descForManipulator);
        criterion.getNameIndicator().addAll(nameIndicator);
        criterion.getDescriptionIndicator().addAll(descIndicator);
    }

    public void addDataToPriority(Priority priority, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        priority.getName().addAll(nameForManipulator);
        priority.getNameIndicator().addAll(nameIndicator);
        priority.getPriorityClass().addAll(classString);
        priority.getPriorityClassIndex().addAll(classST);
        priority.getPrioritySuperClass().addAll(superSting);
        priority.getPrioritySuperClassIndex().addAll(superST);
    }

    public void addDataToSeverity(Severity severity, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {
         
        severity.getName().addAll(nameForManipulator);
        severity.getNameIndicator().addAll(nameIndicator);
        severity.getSeverityClass().addAll(classString);
        severity.getSeverityClassIndex().addAll(classST);
        severity.getSeveritySuperClass().addAll(superSting);
        severity.getSeveritySuperClassIndex().addAll(superST);
    }

    
    public void addDataToRoleType(RoleType roleType, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        roleType.getName().addAll(nameForManipulator);
        roleType.getNameIndicator().addAll(nameIndicator);
        roleType.getRoleTypeClass().addAll(classString);
        roleType.getRoleTypeClassIndex().addAll(classST);
        roleType.getRoleTypeSuperClass().addAll(superSting);
        roleType.getRoleTypeSuperClassIndex().addAll(superST);
    }

    public void addDataToStatus(Status status, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        status.getName().addAll(nameForManipulator);
        status.getNameIndicator().addAll(nameIndicator);
        status.getStatusClass().addAll(classString);
        status.getStatusClassIndex().addAll(classST);
        status.getStatusSuperClass().addAll(superSting);
        status.getStatusSuperClassIndex().addAll(superST);
    }

    public void addDataToType(Type type, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        type.getName().addAll(nameForManipulator);
        type.getNameIndicator().addAll(nameIndicator);
        type.getTypeClass().addAll(classString);
        type.getTypeClassIndex().addAll(classST);
        type.getTypeSuperClass().addAll(superSting);
        type.getTypeSuperClassIndex().addAll(superST);
    }

    public void addDataToRelation(Relation relation, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        relation.getName().addAll(nameForManipulator);
        relation.getNameIndicator().addAll(nameIndicator);
        relation.getRelationClass().addAll(classString);
        relation.getRelationClassIndex().addAll(classST);
        relation.getRelationSuperClass().addAll(superSting);
        relation.getRelationSuperClassIndex().addAll(superST);
    }

    public void addDataToResolution(Resolution resolution, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classST,  ArrayList<Integer> superST, ArrayList<String> classString,  ArrayList<String> superSting) {

        resolution.getName().addAll(nameForManipulator);
        resolution.getNameIndicator().addAll(nameIndicator);
        resolution.getResolutionClass().addAll(classString);
        resolution.getResolutionClassIndex().addAll(classST);
        resolution.getResolutionSuperClass().addAll(superSting);
        resolution.getResolutionSuperClassIndex().addAll(superST);
    }


    public void addDatToRole(Role role, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> type,
                             ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> typeIndicator, int instanceCount) {
        role.getName().addAll(nameForManipulator);
        role.getNameIndicator().addAll(nameIndicator);
        role.getDescriptionIndicator().addAll(descIndicator);
        role.getTypeIndicator().addAll(typeIndicator);
        role.getDescription().addAll(descForManipulator);
        role.setCount(instanceCount);
        role.getType().addAll(type);
    }


    public void addDataToProject(String nameForManipulator, String descForManipulator, LocalDate startDate, LocalDate endDate) {
        project.setDescription(descForManipulator);
        project.setName(nameForManipulator);
        project.setStartDate(convertDate(startDate));
        project.setEndDate(convertDate(endDate));
    }

    public int getMilestoneId(int milestoneIndexForManipulator) {

        return project.getMilestones().get(milestoneIndexForManipulator).getId();

    }

    public ArrayList<Integer> getRoleId(ArrayList<Integer> roleIndex) {
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


    public ArrayList getCriterionIds(List<Integer> criterionIndex) {

        List<Criterion> criterion = project.getCriterions();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for (int i : criterionIndex) {
            if (i != -1) {
                existCriterionId.add(criterion.get(i).getId());
            }

        }
        return existCriterionId;
    }


    public void addDataToMilestone(Milestone milestone, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                                   ArrayList<ArrayList<Integer>> criterionIndex) {
        milestone.getCriteriaIndexs().clear();
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

    public List<Role> getRoles() {
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

    public int getConfigurationId(int index) {
        return project.getConfiguration().get(index).getId();
    }

    public int getRoleTypeId(int index) {

        return project.getRoleType().get(index).getId();
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

    private int getCommitIndexInProject(int id) {
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

    private int getCommitedConfigurationIndexInProject(int id) {
        List<CommitedConfiguration> items = project.getCommitConfiguration();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void addDataToVCSTag(VCSTag tag, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator) {

        tag.getName().addAll(nameForManipulator);
        tag.getNameIndicator().addAll(nameIndicator);
        tag.getDescription().addAll(descriptionForManipulator);
        tag.getDescriptionIndicator().addAll(descriptionIndicator);
    }

    public void addDataToCommit(Commit commit, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, boolean release, int instanceCount) {
        commit.setRelease(release);
        commit.getName().addAll(nameForManipulator);
        commit.getNameIndicator().addAll(nameIndicator);
        commit.setCount(instanceCount);
    }
}
