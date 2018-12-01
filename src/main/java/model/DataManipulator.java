package model;

import Controllers.LinkControl;
import SPADEPAC.*;
import XML.ProcessGenerator;
import forms.CriterionForm;
import forms.ProjectForm;
import forms.ResolutionForm;
import javafx.collections.ObservableList;
import services.*;

import javax.swing.text.html.HTML;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class DataManipulator {


    public static ObjectFactory objF;
    private Project project;
    private ProcessGenerator procesGener;
    private IdentificatorCreater identificatorCreater;


    public DataManipulator(ProcessGenerator processGenerator, IdentificatorCreater identificatorCreater) {
        this.procesGener = processGenerator;
        this.identificatorCreater = identificatorCreater;

        objF = new ObjectFactory();
        project = objF.createProject();
    }


    /**
     * Vytvoří nové instance s potřebnými třídamy a restartuje třídu Control
     *
     * @param file
     */
    public void parseFile(File file) {
        Project tmpProject = procesGener.readProcess(file);
        if (tmpProject != null) {
/*
            project = tmpProject;

            ProjectForm form = new ProjectForm(this, project, canvas);
            forms.add(0, form);

            lists.restartLists(project);
            fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl,
                    deleteControl, formControl);
            fillFormsXML.fillProjectFromXML(form);

            linkControl = new LinkControl(this, lists, objF, idCreater);
            linkControl.restart();
            deleteControl = new DeleteControl(this, lists, project);

            fillForms = new FillForms(this, lists, project, forms, objF, idCreater, deleteControl, formControl);
            fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater, deleteControl, formControl);
            manipulation.restart(fillCopy, project, deleteControl, forms);



            parseProject();*/
        }
    }

    /**
     * Pomocná metoda pro validaci souboru
     */

    public void validate() {

        procesGener.validate(project);

    }

    /**
     * Pomocná metoda pro zpracování segmentů procesu
     */

    private void parseProject() {

      //  fillFormsXML.fillPhasesFromXML(forms.get(0));
      //  fillFormsXML.fillIterationFromXML(forms.get(0));
      //  fillFormsXML.fillActivityFromXML(forms.get(0));
      //  fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnitIndexs());
      //  fillFormsXML.createLinks(project.getLinks());
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


    public Project getProject() {
        return project;
    }

    public void createChangeArtifactRelation(int artifactIndex, int changeIndex) {

        Link linkP = objF.createLink();

        linkP.setType("Config");
        linkP.setArtifactIndex(artifactIndex);
        linkP.setChangeIndex(changeIndex);


        project.getLinks().add(linkP);

        project.getChanges().get(changeIndex).getArtifactIndex().add(artifactIndex);
        project.getArtifacts().get(artifactIndex).getChangeIndex().add(changeIndex);
    }

    public void removeWorkUnitRelation(int startItemId, int endItemId) {

        project.getWorkUnits().get(startItemId).setRelationIndex(null);
        project.getWorkUnits().get(endItemId).setRelationIndex(null);

    }

    public void createWorkUnitRelation(int startIndex, Integer endIndex) {

        Link linkP = objF.createLink();

        linkP.setType("WorkUnit");
        linkP.setLeftUnitIndex(startIndex);
        linkP.setRightUnitIndex(endIndex);

        project.getLinks().add(linkP);

    }

    public void createNewPhase() {
        Phase phase = objF.createPhase();
        project.getPhases().add(phase);
    }

    public void removePhase(int formIdentificator) {
        int index = identificatorCreater.getPhaseIndex(formIdentificator);
        project.getPhases().remove(index);
        project.getPhases().add(index, null);
    }

    public void removeIteration(int formIdentificator) {
        int index = identificatorCreater.getIterationIndex(formIdentificator);
        project.getIterations().remove(index);
        project.getIterations().add(index, null);
    }

    public void removeActivity(int formIdentificator) {
        int index = identificatorCreater.getActivityIndex(formIdentificator);
        project.getActivities().remove(index);
        project.getActivities().add(index, null);
    }



    public void createNewIteration() {
        Iteration iteration = objF.createIteration();
        project.getIterations().add(iteration);
    }

    public void createNewActivity() {
        Activity activity = objF.createActivity();
        project.getActivities().add(activity);

    }

    public Coordinates createCoords(int x, int y){
        Coordinates coord = objF.createCoordinates();
        coord.setXCoordinate(x);
        coord.setYCoordinate(y);
        return coord;
    }

    public void addDataToPhase(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, int x, int y,
                               ArrayList<Integer> itemIndexList, int indexForm) {

        Phase phase =  project.getPhases().get(indexForm);
        phase.setEndDate(convertDate(endDateL));
        phase.setConfiguration(confIndex);
        phase.setDescription(desc);
        phase.setMilestoneIndex(milestoneIndex);
        phase.setName(actName);
        phase.getWorkUnits().clear();
        phase.getWorkUnits().addAll(itemIndexList);
        phase.setCoordinates(createCoords(x,y));
    }

    public void addDataToIteration(String nameForManipulator, LocalDate startDate, LocalDate endDate, String descriptionForManipulator,
                                   int configIndex, int x, int y, ArrayList<Integer> itemIndexList, int indexForm) {

        Iteration iteration = project.getIterations().get(indexForm);
        iteration.setConfiguration(configIndex);
        iteration.setDescription(descriptionForManipulator);
        iteration.setEndDate(convertDate(endDate));
        iteration.setStartDate(convertDate(startDate));
        iteration.setName(nameForManipulator);
        iteration.setCoordinates(createCoords(x,y));
        iteration.getWorkUnits().clear();
        iteration.getWorkUnits().addAll(itemIndexList);

    }

    public void addDataToActivity(String nameForManipulator, String descriptionForManipulator, int x, int y, ArrayList<Integer> setOfItemOnCanvas, int indexForm) {

        Activity activity = project.getActivities().get(indexForm);
        activity.setDescription(descriptionForManipulator);
        activity.setName(nameForManipulator);
        activity.setCoordinates(createCoords(x, y));
        activity.getWorkUnits().addAll(setOfItemOnCanvas);

    }


    public void createNewWorkUnit() {
        WorkUnit workUnit = objF.createWorkUnit();
        project.getWorkUnits().add(workUnit);
    }

    public void addDataToWorkUnit(String nameForManipulator,String description, String categoryForManipulator, int assigneIndex, int authorIndex,
                                  int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex, int statusIndex,
                                  int x, int y, double estimateForDataManipulator,boolean isExist, int indexForm, boolean isProjectCanvas) {

        if(isProjectCanvas){
            project.getWorkUnitIndexs().add(indexForm);
        }

        WorkUnit workUnit = project.getWorkUnits().get(indexForm);
        workUnit.setAssigneeIndex(assigneIndex);
        workUnit.setAuthorIndex(authorIndex);
        workUnit.setCategory(categoryForManipulator);
        workUnit.setCoordinates(createCoords(x, y));
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

    public void addDataToConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex,
                                       List<Integer> branches, List<Integer> cprs, ArrayList artifactIndexs, ArrayList changeIndexs, int indexForm) {
        Configuration configuration = project.getConfiguration().get(indexForm);
        configuration.setAuthorIndex(authorIndex);
        configuration.setCreate(convertDate(createDate));
        configuration.setIsRelease(isRelease);
        configuration.setName(actName);

        configuration.getBranchesIndexs().clear();
        configuration.getBranchesIndexs().addAll(branches);

        configuration.getCPRsIndexs().clear();
        configuration.getCPRsIndexs().addAll(cprs);

        configuration.getArtifactsIndexs().clear();
        configuration.getArtifactsIndexs().addAll(artifactIndexs);

        configuration.getChangesIndexs().clear();
        configuration.getChangesIndexs().addAll(changeIndexs);

    }

    public void removeWorkUnit(int indexForm) {
        int index = identificatorCreater.getWorkUnitIndex(indexForm);
        project.getWorkUnits().remove(index);
        project.getWorkUnits().add(index, null);

    }

    public void createNewConfiguration() {
        Configuration configuration = objF.createConfiguration();
        project.getConfiguration().add(configuration);
    }

    public void removeConfiguration(int indexForm){
        int index = identificatorCreater.getConfigurationIndex(indexForm);
        project.getConfiguration().remove(index);
        project.getConfiguration().add(index, null);
    }

    public void addDataToChange(String nameForManipulator, String descForManipulator, int x, int y, boolean selected, int indexForm) {
        Change change = project.getChanges().get(indexForm);
        change.setCoordinates(createCoords(x, y));
        change.setDescriptoin(descForManipulator);
        change.setName(nameForManipulator);
        change.setExist(selected);
    }

    public void createNewChance() {
        Change change = objF.createChange();
        project.getChanges().add(change);
    }

    public void removeChange(int indexForm) {
        int index = identificatorCreater.getChangeIndex(indexForm);
        project.getChanges().remove(index);
        project.getChanges().add(index, null);

    }

    public void createNewArtifact() {
        Artifact artifact = objF.createArtifact();
        project.getArtifacts().add(artifact);
    }

    public void addDataToArtifact(String nameForManipulator, String descForManipulator, LocalDate createdDate, boolean isCreate, int x, int y, int authorIndex, int typeIndex,
                                  int indexForm) {

        Artifact artifact = project.getArtifacts().get(indexForm);
        artifact.setName(nameForManipulator);
        artifact.setAuthorIndex(authorIndex);
        artifact.setCoordinates(createCoords(x, y));
        artifact.setCreated(convertDate(createdDate));
        artifact.setDescriptoin(descForManipulator);
        artifact.setExist(isCreate);
        artifact.setMimeType(ArtifactClass.values()[typeIndex].name());
    }

    public void removeArtifact(int indexForm) {

        int index = identificatorCreater.getArtifactIndex(indexForm);
        project.getArtifacts().remove(index);
        project.getArtifacts().add(index, null);
    }
    public void addDataToBranch(String nameForManipulator, int id, boolean isMain) {
        Branch branch = objF.createBranch();
        branch.setIsMain(isMain);
        branch.setName(nameForManipulator);
        project.getBranches().add(branch);
    }

    public void removeBranch(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getBranches().remove(i);
        }
    }

    public void removeArtifactChangeLink(int artifactID, int changeID) {


        project.getArtifacts().get(artifactID).getChangeIndex().remove(0);
        project.getChanges().get(changeID).getArtifactIndex().remove(0);

    }

    public void addDataToCPR(String nameForManipulator, int roleIndex, int configIndex, int index) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setConfigurationIndex(configIndex);
        cpr.setPersonIndex(roleIndex);
        cpr.setName(nameForManipulator);
        project.getCpr().add(cpr);
    }

    public void removeCPR(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getCpr().remove(i);
        }
    }

    public void addDataToCriterion(String nameForManipulator, String descForManipulator, int index) {

        Criterion criterion = objF.createCriterion();
        criterion.setDescription(descForManipulator);
        criterion.setName(nameForManipulator);
        project.getCriterions().add(criterion);

    }

    public void removeCriterion(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getCriterions().remove(i);
        }
    }

    public void addDataToMilestone(String nameForManipulator, List<Integer> criterionIndex, int index) {

        Milestone milestone = objF.createMilestone();
        milestone.getCriteriaIndexs().addAll(criterionIndex);
        milestone.setName(nameForManipulator);
        //TODO milestone description? Zjistit Zda ma byt ve formulari
        project.getMilestones().add(milestone);
    }

    public void removeMilestone(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getMilestones().remove(i);
        }
    }

    public void addDataToPriority(String nameForManipulator, String classST, String superST, int index) {

        Priority priority = objF.createPriority();
        priority.setPriorityClass(classST);
        priority.setPrioritySuperClass(superST);
        priority.setName(nameForManipulator);
        project.getPriority().add(priority);

    }

    public void removePriority(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getPriority().remove(i);
        }
    }

    public void addDataToSeverity(String nameForManipulator, String classST, String superST, int index) {

        Severity severity = objF.createSeverity();
        severity.setSeverityClass(classST);
        severity.setSeveritySuperClass(superST);
        severity.setName(nameForManipulator);
        project.getSeverity().add(severity);

    }

    public void removeSeverity(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getSeverity().remove(i);
        }
    }

    public void addDataToRelation(String nameForManipulator, String classST, String superST, int index) {

        Relation relation = objF.createRelation();
        relation.setRelationClass(classST);
        relation.setRelationSuperClass(superST);
        relation.setName(nameForManipulator);
        project.getRelation().add(relation);

    }

    public void removeRelation(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getRelation().remove(i);
        }
    }

    public void addDataToResolution(String nameForManipulator, String classST, String superST, int index) {

        Resolution resolution = objF.createResolution();
        resolution.setResolutionClass(classST);
        resolution.setResolutionSuperClass(superST);
        resolution.setName(nameForManipulator);
        project.getResolution().add(resolution);

    }

    public void removeResolution(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getResolution().remove(i);
        }
    }

    public void addDataToRole(String nameForManipulator, String descForManipulator, int type, int index) {

        Role role = objF.createRole();
        role.setDescription(descForManipulator);
        role.setName(nameForManipulator);
        role.setType(type);
        project.getRoles().add(role);
    }

    public void removeRole(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getRoles().remove(i);
        }
    }

    public void addDataToRoleType(String nameForManipulator, String classST, String superST, int index) {

        RoleType roleType = objF.createRoleType();
        roleType.setRoleClass(classST);
        roleType.setRoleSuperClass(superST);
        roleType.setName(nameForManipulator);
        project.getRoleType().add(roleType);
    }

    public void removeRoleType(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getRoleType().remove(i);
        }
    }

    public void addTagToConfiguration(String tag, int configId, int index) {
        Configuration configuration = project.getConfiguration().get(configId);
        configuration.getTags().add(tag);
    }

    public void removeTag(ArrayList<Integer> indexList, int configId) {
        Configuration configuration = project.getConfiguration().get(configId);
        for(Integer i : indexList){
            configuration.getTags().remove(i);
        }

    }

    public void addDataToStatus(String nameForManipulator, String classST, String superST, int index) {

        Status status = objF.createStatus();
        status.setStatusClass(classST);
        status.setStatusSuperClass(superST);
        status.setName(nameForManipulator);
        project.getStatus().add(status);
    }

    public void removeStatus(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getStatus().remove(i);
        }
    }

    public void addDataToType(String nameForManipulator, String classST, String superST, int index) {

        Type type = objF.createType();
        type.setTypeClass(classST);
        type.setTypeSuperClass(superST);
        type.setName(nameForManipulator);
        project.getTypes().add(type);
    }

    public void removeType(ArrayList<Integer> indexList) {
        for(Integer i : indexList){
            project.getTypes().remove(i);
        }
    }

    public void addDataToProject(String nameForManipulator, String descForManipulator, LocalDate startDate, LocalDate endDate) {
        project.setDescription(descForManipulator);
        project.setName(nameForManipulator);
        project.setStartDate(convertDate(startDate));
        project.setEndDate(convertDate(endDate));
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setRelationIndexToLink(int id, int relationIndex) {
    project.getLinks().get(id).setRelationIndex(relationIndex);
    }
}
