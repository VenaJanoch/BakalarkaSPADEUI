package model;

import Controllers.LinkControl;
import SPADEPAC.*;
import XML.ProcessGenerator;
import forms.ProjectForm;
import javafx.collections.ObservableList;
import services.*;

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
    private SegmentLists lists;

    public DataManipulator(ProcessGenerator processGenerator, IdentificatorCreater identificatorCreater) {
        this.procesGener = processGenerator;
        this.identificatorCreater = identificatorCreater;

        objF = new ObjectFactory();
        project = objF.createProject();
        lists = new SegmentLists(project);

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

    /**
     * Umožní převedení data ve formátu XMLGregorianCalendar uloženého v XML do
     * formátu LocalDate
     *
     * @param xmlDate XMLGregorianCalendar
     * @return LocalDate
     */
    public LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

        if (xmlDate == null) {
            return null;
        }

        Date date = xmlDate.toGregorianCalendar().getTime();
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    public Project getProject() {
        return project;
    }

    public void createChangeArtifactRelation(int artifactIndex, int changeIndex) {

        Link linkP = objF.createLink();

        linkP.setType("Config");
        linkP.setArtifactIndex(artifactIndex);
        linkP.setChangeIndex(changeIndex);

        lists.getLinksList().add(linkP);

        lists.getChangeList().get(changeIndex).getArtifactIndex().add(artifactIndex);
        lists.getArtifactList().get(artifactIndex).getChangeIndex().add(changeIndex);
    }

    public void createWorkUnitRelation(Integer startIndex, Integer endIndex) {

        Link linkP = objF.createLink();

        linkP.setType("WorkUnit");
        linkP.setLeftUnitIndex(startIndex);
        linkP.setRightUnitIndex(endIndex);

        lists.getLinksList().add(linkP);
        lists.getWorkUnitList().get(startIndex).setRightUnitIndex(endIndex);
        lists.getWorkUnitList().get(endIndex).setLeftUnitIndex(startIndex);

    }

    public void createNewPhase(int index) {
        Phase phase = objF.createPhase();

        int segmentId = identificatorCreater.getPhaseIndex(index);
        project.getPhases().add(segmentId, phase);
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



    public void createNewIteration(int index) {
        Iteration iteration = objF.createIteration();

        int segmentId = identificatorCreater.getIterationIndex(index);
        project.getIterations().add(segmentId, iteration);
    }

    public void createNewActivity(int index) {
        Activity activity = objF.createActivity();
        int segmentId = identificatorCreater.getActivityIndex(index);
        project.getActivities().add(segmentId, activity);

    }

    public ObservableList<String> getCriterionObservable() {
        return lists.getCriterionObservable();
    }

    public ObservableList<String> getRoleObservable() {
        return lists.getRoleObservable();
    }

    public ObservableList<String> getRoleTypeObservable() {
        return lists.getRoleTypeObservable();
    }

    public ObservableList<String> getConfigurationObservable() {
        return lists.getConfigObservable();
    }

    public ObservableList<String> getMilestoneObservable() {
        return lists.getMilestoneObservable();
    }

    public Coordinates createCoords(int x, int y){
        Coordinates coord = objF.createCoordinates();
        coord.setXCoordinate(x);
        coord.setYCoordinate(y);
        return coord;
    }

    public void addDataToPhase(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, int x, int y,
                               Set<Integer> itemIndexList, int indexForm) {

        Phase phase =  project.getPhases().get(identificatorCreater.getPhaseIndex(indexForm));
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
                                   int configIndex, int x, int y, Set<Integer> itemIndexList, int indexForm) {

        Iteration iteration = project.getIterations().get(identificatorCreater.getIterationIndex(indexForm));
        iteration.setConfiguration(configIndex);
        iteration.setDescription(descriptionForManipulator);
        iteration.setEndDate(convertDate(endDate));
        iteration.setStartDate(convertDate(startDate));
        iteration.setName(nameForManipulator);
        iteration.setCoordinates(createCoords(x,y));
        iteration.getWorkUnits().clear();
        iteration.getWorkUnits().addAll(itemIndexList);

    }

    public void addDataToActivity(String nameForManipulator, String descriptionForManipulator, int x, int y, Set<Integer> setOfItemOnCanvas, int indexForm) {

        Activity activity = project.getActivities().get(identificatorCreater.getActivityIndex(indexForm));
        activity.setDescription(descriptionForManipulator);
        activity.setName(nameForManipulator);
        activity.setCoordinates(createCoords(x, y));
        activity.getWorkUnits().addAll(setOfItemOnCanvas);

    }


    public void createNewWorkUnit(int index) {
        WorkUnit workUnit = objF.createWorkUnit();
        int segmentId = identificatorCreater.getWorkUnitIndex(index);
        project.getWorkUnits().add(segmentId,workUnit);
    }

    public void addDataToWorkUnit(String nameForManipulator,String description, String categoryForManipulator, int assigneIndex, int authorIndex,
                                  int priorityIndex, int severityIndex, int typeIndex, int resolutionIndex, int statusIndex,
                                  int x, int y, double estimateForDataManipulator,boolean isExist, int indexForm) {

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

    public void addDataToConfiguration(String actName, LocalDate createDate, boolean isRelease, int x, int y, int authorIndex,
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

    public void createNewConfiguration(int index) {
        Configuration configuration = objF.createConfiguration();
        int segmentId = identificatorCreater.getConfigurationIndex(index);
        project.getConfiguration().add(segmentId,configuration);
    }

    public void removeConfiguration(int indexForm){
        int index = identificatorCreater.getConfigurationIndex(indexForm);
        project.getConfiguration().remove(index);
        project.getConfiguration().add(index, null);
    }

    public ObservableList<String> getPriorityObservable() {
        return  lists.getPriorityObservable();
    }

    public ObservableList<String> getSeverityObservable() {
        return  lists.getSeverityTypeObservable();
    }

    public ObservableList<String> getStatusObservable() {
        return  lists.getStatusTypeObservable();
    }

    public ObservableList<String> getResolutionObservable() {
        return  lists.getResolutionTypeObservable();
    }

    public void addDataToChange(String nameForManipulator, String descForManipulator, int x, int y, boolean selected, int indexForm) {
        Change change = project.getChanges().get(indexForm);
        change.setCoordinates(createCoords(x, y));
        change.setDescriptoin(descForManipulator);
        change.setName(nameForManipulator);
        change.setExist(selected);
        change.setExist(selected);
    }

    public void createNewChance(int index) {
        Change change = objF.createChange();

        int segmentId = identificatorCreater.getChangeIndex(index);
        project.getChanges().add(segmentId, change);
    }

    public void removeChange(int indexForm) {
        int index = identificatorCreater.getChangeIndex(indexForm);
        project.getChanges().remove(index);
        project.getChanges().add(index, null);

    }

    public void createNewArtifact(int index) {
        Artifact artifact = objF.createArtifact();
        int segmentId = identificatorCreater.getArtifactIndex(index);
        project.getArtifacts().add(segmentId, artifact);
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

    public SegmentLists getSegmentLists() {
        return lists;
    }
}
