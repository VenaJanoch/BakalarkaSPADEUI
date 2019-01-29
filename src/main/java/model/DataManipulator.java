package model;

import Controllers.LinkControl;
import SPADEPAC.*;
import XML.ProcessGenerator;
import forms.*;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import services.*;
import tables.BasicTable;
import tables.MilestoneTable;

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


    public void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIndexList){
        if (elementIndexList == null){
            return;
        }
        switch (formType) {
            case WorkUnit:
                updateWUListItem(elementType, elementIndexList);
                break;
            case Milestone:

                switch (elementType ) {
                    case Criterion:
                        for (Milestone segment : project.getMilestones()) {
                            updateElementListFromSegment(elementIndexList, segment.getCriteriaIndexs());
                        }
                        break;
                    default:
                }
                break;
            case Role:
                for (Role segment : project.getRoles()) {
                    for(int i : elementIndexList){
                        int type = segment.getType();
                        if( type == i ){
                            segment.setType(-1);
                        }else if(type > i ){
                            segment.setType(type - 1);
                        }
                    }

                }

                break;
            case Configuration:

                    switch (elementType ) {
                        case Role:
                            for (Configuration segment : project.getConfiguration()) {
                                for(int i : elementIndexList){
                                    int type = segment.getAuthorIndex();
                                    if( type == i ){
                                        segment.setAuthorIndex(-1);
                                    }else if(type > i ){
                                        segment.setAuthorIndex(type - 1);
                                    }
                                }

                            }
                            break;
                        case ConfigPersonRelation:
                            for (Configuration segment : project.getConfiguration()) {
                                updateElementListFromSegment(elementIndexList, segment.getCPRsIndexs());
                            }
                            break;
                        case Branch:
                            for (Configuration segment : project.getConfiguration()) {
                                updateElementListFromSegment(elementIndexList, segment.getBranchesIndexs());
                            }
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

    private void updateWUListItem(SegmentType type, ArrayList<Integer> elementIndexList) {

        switch (type) {
            case Priority:
                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getPriorityIndex();
                        if( index == i ){
                            segment.setPriorityIndex(-1);
                        }else if(index > i ){
                            segment.setPriorityIndex(index - 1);
                        }
                    }
                }
                break;
            case Severity:
                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getSeverityIndex();
                        if( index == i ){
                            segment.setSeverityIndex(-1);
                        }else if(index > i ){
                            segment.setSeverityIndex(index - 1);
                        }
                    }
                }
                break;
            case Role:
                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getAuthorIndex();
                        int index2 = segment.getAssigneeIndex();
                        if( index == i ){
                            segment.setAuthorIndex(-1);
                        }else if(index > i ){
                            segment.setAuthorIndex(index - 1);
                        }
                        if( index2 == i ){
                            segment.setAssigneeIndex(-1);
                        }else if(index2 > i ){
                            segment.setAssigneeIndex(index2 - 1);
                        }
                    }
                }
                break;
            case Resolution:
                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getResolutionIndex();
                        if( index == i ){
                            segment.setResolutionIndex(-1);
                        }else if(index > i ){
                            segment.setResolutionIndex(index - 1);
                        }
                    }
                }
                break;
            case Status:
                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getStatusIndex();
                        if( index == i ){
                            segment.setStatusIndex(-1);
                        }else if(index > i ){
                            segment.setStatusIndex(index - 1);
                        }
                    }
                }
                break;
            case Type:

                for (WorkUnit segment : project.getWorkUnits()) {
                    for(int i : elementIndexList){
                        int index = segment.getTypeIndex();
                        if( index == i ){
                            segment.setTypeIndex(-1);
                        }else if(index > i ){
                            segment.setTypeIndex(index - 1);
                        }
                    }
                }
            case Relation:

                break;
            default:

        }

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

    public void addDataToCPR(String nameForManipulator, int roleIndex, int configIndex, int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setConfigurationIndex(configIndex);
        cpr.setPersonIndex(roleIndex);
        cpr.setName(nameForManipulator);
        cpr.setId(id);
        project.getCpr().add(cpr);
    }

    public void removeCPR(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getCpr().remove((int)indexList.get(i));
        }
    }

    public void addDataToCriterion(String nameForManipulator, String descForManipulator, int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setDescription(descForManipulator);
        criterion.setName(nameForManipulator);
        criterion.setId(id);
        project.getCriterions().add(criterion);

    }

    public void removeCriterion(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getCriterions().remove((int)indexList.get(i));
        }
    }

    public void addDataToMilestone(String nameForManipulator, List<Integer> criterionIndex, int id) {

        Milestone milestone = objF.createMilestone();
        milestone.getCriteriaIndexs().addAll(criterionIndex);
        milestone.setName(nameForManipulator);
        milestone.setId(id);
        //TODO milestone description? Zjistit Zda ma byt ve formulari
        project.getMilestones().add(milestone);
    }

    public void removeMilestone(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getMilestones().remove((int)indexList.get(i));
        }
    }
    public void addDataToPriority(String nameForManipulator, String classST, String superST, int index) {

        Priority priority = objF.createPriority();
        priority.setPriorityClass(classST);
        priority.setPrioritySuperClass(superST);
        priority.setName(nameForManipulator);
        project.getPriority().add(priority);

    }

    public void removePriority(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getPriority().remove((int)indexList.get(i));
        }
    }

    public void addDataToSeverity(String nameForManipulator, String classST, String superST, int id) {

        Severity severity = objF.createSeverity();
        severity.setSeverityClass(classST);
        severity.setSeveritySuperClass(superST);
        severity.setName(nameForManipulator);
        severity.setId(id);
        project.getSeverity().add(severity);

    }

    public void removeSeverity(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getSeverity().remove((int)indexList.get(i));
        }
    }


    public void addDataToRelation(String nameForManipulator, String classST, String superST, int index) {

        Relation relation = objF.createRelation();
        relation.setRelationClass(classST);
        relation.setRelationSuperClass(superST);
        relation.setName(nameForManipulator);
        project.getRelation().add(relation);

    }

    public void removeRelation(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRelation().remove((int)indexList.get(i));
        }
    }

    public void addDataToResolution(String nameForManipulator, String classST, String superST, int id) {

        Resolution resolution = objF.createResolution();
        resolution.setResolutionClass(classST);
        resolution.setResolutionSuperClass(superST);
        resolution.setName(nameForManipulator);
        resolution.setId(id);
        project.getResolution().add(resolution);

    }

    public void removeResolution(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getResolution().remove((int)indexList.get(i));
        }
    }

    public void addDataToRole(String nameForManipulator, String descForManipulator, int type, int id) {

        Role role = objF.createRole();
        role.setDescription(descForManipulator);
        role.setName(nameForManipulator);
        role.setType(type);
        role.setId(id);
        project.getRoles().add(role);
    }

    public void removeRole(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRoles().remove((int)indexList.get(i));
        }
    }

    public void addDataToRoleType(String nameForManipulator, String classST, String superST, int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setRoleClass(classST);
        roleType.setRoleSuperClass(superST);
        roleType.setName(nameForManipulator);
        roleType.setId(id);
        project.getRoleType().add(roleType);
    }

    public void removeRoleType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getRoleType().remove((int)indexList.get(i));
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

    public void addDataToStatus(String nameForManipulator, String classST, String superST, int id) {

        Status status = objF.createStatus();
        status.setStatusClass(classST);
        status.setStatusSuperClass(superST);
        status.setName(nameForManipulator);
        status.setId(id);
        project.getStatus().add(status);
    }

    public void removeStatus(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getStatus().remove((int)indexList.get(i));
        }
    }

    public void addDataToType(String nameForManipulator, String classST, String superST, int id) {

        Type type = objF.createType();
        type.setTypeClass(classST);
        type.setTypeSuperClass(superST);
        type.setName(nameForManipulator);
        type.setId(id);
        project.getTypes().add(type);
    }

    public void removeType(ObservableList<Integer> indexList) {
        for(int i = indexList.size() -1; i >= 0; i-- ){
            project.getTypes().remove((int)indexList.get(i));
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

    public void copyDataFromActivity(int oldActivityId, int newActivityId) {
        Activity oldActivity = project.getActivities().get(oldActivityId);
        Activity newActivity = project.getActivities().get(newActivityId);

        newActivity.setCoordinates(oldActivity.getCoordinates());
        newActivity.setName(oldActivity.getName());
        newActivity.setDescription(oldActivity.getDescription());

       int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldActivity.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldActivity.getWorkUnits().get(i));
            newActivity.getWorkUnits().add(workUnitSize + i);
        }
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
        newWu.setResolutionIndex(oldWu.getResolutionIndex());
        newWu.setStatusIndex(oldWu.getStatusIndex());
        newWu.setTypeIndex(oldWu.getTypeIndex());
        newWu.setSeverityIndex(oldWu.getSeverityIndex());
        newWu.setPriorityIndex(oldWu.getPriorityIndex());
        newWu.setName(oldWu.getName());
        newWu.setExist(oldWu.isExist());
        newWu.setEstimatedTime(oldWu.getEstimatedTime());
        newWu.setDescription(oldWu.getDescription());
        newWu.setCoordinates(oldWu.getCoordinates());
        newWu.setCategory(oldWu.getCategory());
        newWu.setAuthorIndex(oldWu.getAuthorIndex());
        newWu.setAssigneeIndex(oldWu.getAssigneeIndex());
        newWu.setRelationIndex(oldWu.getRelationIndex());
    }

    public void copyDataFromIteration(int oldIteratationId, int newIterationId) {
        Iteration oldIteration = project.getIterations().get(oldIteratationId);
        Iteration newIteration = project.getIterations().get(newIterationId);

        newIteration.setCoordinates(oldIteration.getCoordinates());
        newIteration.setName(oldIteration.getName());
        newIteration.setDescription(oldIteration.getDescription());
        newIteration.setStartDate(oldIteration.getStartDate());
        newIteration.setEndDate(oldIteration.getEndDate());
        newIteration.setConfiguration(oldIteration.getConfiguration());

        int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldIteration.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldIteration.getWorkUnits().get(i));
            newIteration.getWorkUnits().add(workUnitSize + i);
        }

    }


    public void copyDataFromPhase(int phaseId, int newPhaseId) {
        Phase oldPhase = project.getPhases().get(phaseId);
        Phase newPhase = project.getPhases().get(newPhaseId);

        newPhase.setCoordinates(oldPhase.getCoordinates());
        newPhase.setName(oldPhase.getName());
        newPhase.setDescription(oldPhase.getDescription());
        newPhase.setEndDate(oldPhase.getEndDate());
        newPhase.setConfiguration(oldPhase.getConfiguration());
        newPhase.setConfiguration(oldPhase.getConfiguration());
        newPhase.setMilestoneIndex(oldPhase.getMilestoneIndex());

        int workUnitSize = project.getWorkUnits().size();
        for (int i =0; i < oldPhase.getWorkUnits().size(); i++){
            copyDataFromWorkUnit(oldPhase.getWorkUnits().get(i));
            newPhase.getWorkUnits().add(workUnitSize + i);
        }

    }

    public void copyDataFromChange(int changeId, int newchangeId) {

        Change oldChange = project.getChanges().get(changeId);
        Change newChange = project.getChanges().get(newchangeId);

        newChange.setCoordinates(oldChange.getCoordinates());
        newChange.setName(oldChange.getName());
        newChange.setDescriptoin(oldChange.getDescriptoin());
        newChange.setExist(oldChange.isExist());
        }

    public void copyDataFromArtifact(int artifactId, int newArtifactId) {
        Artifact oldArtifact = project.getArtifacts().get(artifactId);
        Artifact newArtifact = project.getArtifacts().get(newArtifactId);

        newArtifact.setCoordinates(oldArtifact.getCoordinates());
        newArtifact.setName(oldArtifact.getName());
        newArtifact.setDescriptoin(oldArtifact.getDescriptoin());
        newArtifact.setExist(oldArtifact.isExist());
        newArtifact.setMimeType(oldArtifact.getMimeType());
        newArtifact.setCreated(oldArtifact.getCreated());
        newArtifact.setAuthorIndex(oldArtifact.getAuthorIndex());
        newArtifact.setArtifactIndex(oldArtifact.getArtifactIndex());
    }

    public Phase getPhase(Integer phaseIndex) {
        return project.getPhases().get(phaseIndex);
    }

    public ArrayList getCriterionIds(List<Integer> criterionIndex) {

        List<Criterion> criterion = project.getCriterions();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for( int i : criterionIndex){
            if(i != -1){
                existCriterionId.add(criterion.get(i).getId());
            }

        }
        return existCriterionId;
    }

    public int getMilestoneId(int milestoneIndexForManipulator) {

        return project.getMilestones().get(milestoneIndexForManipulator).getId();

    }

    public int roleTypeIndex(int typeFormManipulator) {
        int index = typeFormManipulator;
        if(typeFormManipulator != -1){
            index = project.getRoleType().get(typeFormManipulator).getId();
        }
        return index;
    }
}
