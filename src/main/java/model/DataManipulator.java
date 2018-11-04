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
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

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



            parseProject();
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

        fillFormsXML.fillPhasesFromXML(forms.get(0));
        fillFormsXML.fillIterationFromXML(forms.get(0));
        fillFormsXML.fillActivityFromXML(forms.get(0));
        fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnitIndexs());
        fillFormsXML.createLinks(project.getLinks());
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
        project.getPhases().remove(index);
        project.getPhases().add(index, null);
    }



    public void createNewIteration(int index) {
        Iteration iteration = objF.createIteration();

        int segmentId = identificatorCreater.getPhaseIndex(index);
        project.getIterations().add(segmentId, iteration);
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
                               ArrayList itemIndexList, int indexForm) {

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
                                   int configIndex, int x, int y, ArrayList itemIndexList, int indexForm) {

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
}
