package model;

import SPADEPAC.*;
import XML.ProcessGenerator;
import interfaces.IDeleteDataModel;
import interfaces.IEditDataModel;
import interfaces.ISaveDataModel;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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


    public void addDataToCPR(ConfigPersonRelation cpr, String nameForManipulator, int roleIndex) {
        cpr.setPersonIndex(roleIndex);
        cpr.setName(nameForManipulator);
    }

    public void addDataToBranch(Branch branch, String nameForManipulator, boolean isMain) {
        branch.setName(nameForManipulator);
        branch.setIsMain(isMain);
    }

    public void addDataToArtifact(Artifact artifact, String nameForManipulator, String descForManipulator, LocalDate createdDate, boolean isCreate, int authorIndex, int typeIndex) {
        artifact.setName(nameForManipulator);
        artifact.setAuthorIndex(authorIndex);
        artifact.setCreated(convertDate(createdDate));
        artifact.setDescriptoin(descForManipulator);
        artifact.setExist(isCreate);
        artifact.setMimeType(ArtifactClass.values()[typeIndex].name());
    }

    public void addDataToChange(Change change, String nameForManipulator, String descForManipulator, boolean selected) {
        change.setDescriptoin(descForManipulator);
        change.setName(nameForManipulator);
        change.setExist(selected);
    }

    public void addDataToPhase(Phase phase, String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex,
                               ArrayList<Integer> itemIndexList) {
        phase.setEndDate(convertDate(endDateL));
        phase.setConfiguration(getConfigurationIndexInProject(confIndex));
        phase.setDescription(desc);
        phase.setMilestoneIndex(getMilestoneIndexInProject(milestoneIndex));
        phase.setName(actName);
        phase.getWorkUnits().clear();
        phase.getWorkUnits().addAll(itemIndexList);
    }

    public void addDataToIteration(Iteration iteration, String nameForManipulator, LocalDate startDate, LocalDate endDate, String descriptionForManipulator,
                                   int configIndex, ArrayList<Integer> itemIndexList) {
        iteration.setConfiguration(configIndex);
        iteration.setDescription(descriptionForManipulator);
        iteration.setEndDate(convertDate(endDate));
        iteration.setStartDate(convertDate(startDate));
        iteration.setName(nameForManipulator);
        iteration.getWorkUnits().clear();
        iteration.getWorkUnits().addAll(itemIndexList);

    }

    public void addDataToCommitedConfiguration(CommitedConfiguration commitedConfiguration, LocalDate startDate) {
        commitedConfiguration.setCommitedDay(convertDate(startDate));

    }


    public void addDataToActivity(Activity activity, String nameForManipulator, String descriptionForManipulator, ArrayList<Integer> setOfItemOnCanvas) {

        activity.setDescription(descriptionForManipulator);
        activity.setName(nameForManipulator);
        activity.getWorkUnits().addAll(setOfItemOnCanvas);

    }

    public void addDataToWorkUnit(WorkUnit workUnit, String nameForManipulator, String description, String categoryForManipulator, int assigneIndex, int authorIndex,
                                  int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex, int statusIndex,
                                  double estimateForDataManipulator, boolean isExist, int id) {

        workUnit.setAssigneeIndex(assigneIndex);
        workUnit.setAuthorIndex(authorIndex);
        workUnit.setCategory(categoryForManipulator);
        workUnit.setDescription(description);
        workUnit.setEstimatedTime(estimateForDataManipulator);
        workUnit.setExist(isExist);
        workUnit.setName(nameForManipulator);
        workUnit.setPriorityIndex(priorityIndex);
        workUnit.setSeverityIndex(severityIndex);
        workUnit.setTypeIndex(typeIndex);
        workUnit.setStatusIndex(statusIndex);
        workUnit.setResolutionIndex(resolutionIndex);
    }

    public void addDataToConfiguration(Configuration configuration, String actName, LocalDate createDate, boolean isRelease, int authorIndex, List<Integer> cprs,
                                       List<Integer> branches, ArrayList changeIndexs) {

        configuration.setAuthorIndex(authorIndex);
        configuration.setCreate(convertDate(createDate));
        configuration.setIsRelease(isRelease);
        configuration.setName(actName);

        configuration.getBranchesIndexs().clear();
        configuration.getBranchesIndexs().addAll(branches);

        configuration.getCPRsIndexs().clear();
        configuration.getCPRsIndexs().addAll(cprs);

        configuration.getChangesIndexs().clear();
        configuration.getChangesIndexs().addAll(changeIndexs);

    }


    public void addDataToCriterion(Criterion criterion, String nameForManipulator, String descForManipulator) {
        criterion.setName(nameForManipulator);
        criterion.setDescription(descForManipulator);
    }

    public void addDataToPriority(Priority priority, String nameForManipulator, String classST, String superST) {
        priority.setName(nameForManipulator);
        priority.setPriorityClass(classST);
        priority.setPrioritySuperClass(superST);
    }

    public void addDataToSeverity(Severity severity, String nameForManipulator, String classST, String superST) {
        severity.setName(nameForManipulator);
        severity.setSeverityClass(classST);
        severity.setSeveritySuperClass(superST);
    }

    public void addDataToRoleType(RoleType roleType, String nameForManipulator, String classST, String superST) {
        roleType.setName(nameForManipulator);
        roleType.setRoleClass(classST);
        roleType.setRoleSuperClass(superST);
    }

    public void addDataToStatus(Status status, String nameForManipulator, String classST, String superST) {
        status.setName(nameForManipulator);
        status.setStatusClass(classST);
        status.setStatusSuperClass(superST);
    }

    public void addDataToType(Type type, String nameForManipulator, String classST, String superST) {
        type.setName(nameForManipulator);
        type.setTypeClass(classST);
        type.setTypeSuperClass(superST);
    }

    public void addDataToRelation(Relation relation, String nameForManipulator, String classST, String superST) {
        relation.setName(nameForManipulator);
        relation.setRelationClass(classST);
        relation.setRelationSuperClass(superST);
    }

    public void addDataToResolution(Resolution resolution, String nameForManipulator, String classST, String superST) {
        resolution.setName(nameForManipulator);
        resolution.setResolutionClass(classST);
        resolution.setResolutionSuperClass(superST);
    }


    public void addDatToRole(Role role, String nameForManipulator, String descForManipulator, int type) {
        role.setName(nameForManipulator);
        role.setDescription(descForManipulator);
        role.setType(type);
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

    public int getRoleId(int roleIndex) {
        int index = roleIndex;
        if (roleIndex != -1) {
            index = project.getRoles().get(roleIndex).getId();
        }
        return index;
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


    public void addDataToMilestone(Milestone milestone, String nameForManipulator, String descForManipulator, ArrayList<Integer> criterionIndex) {
        milestone.getCriteriaIndexs().clear();
        milestone.getCriteriaIndexs().addAll(criterionIndex);
        milestone.setName(nameForManipulator);
        milestone.setDescription(descForManipulator);
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
}
