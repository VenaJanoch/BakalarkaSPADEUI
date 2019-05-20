package controllers.formControllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import controllers.DataPreparer;
import controllers.LinkControl;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import forms.*;
import graphics.canvas.CanvasItem;
import javafx.geometry.Point2D;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormFillController {

    private DataManipulator dataManipulator;
    private DataModel dataModel;
    private FormController formController;
    private CanvasItemController canvasItemController;
    private ArrayList<BasicForm> forms;
    private CanvasController dataModelCanvasController;
    private IdentificatorCreater identificatorCreater;
    private SegmentLists segmentLists;
    private LinkControl linkControl;
    private Map<Integer, CanvasItem> canvasItemList;
    private DataPreparer dataPreparer;

    public FormFillController(FormController formController, DataModel dataModel, CanvasItemController canvasItemController,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer, SegmentLists lists, LinkControl linkControl,
                              Map<Integer, CanvasItem> canvasItemList) {
        this.formController = formController;
        this.forms = formController.getForms();
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getDataManipulator();
        this.canvasItemController = canvasItemController;
        this.identificatorCreater = identificatorCreater;
        this.segmentLists = lists;
        this.linkControl = linkControl;
        this.canvasItemList = canvasItemList;
        this.dataPreparer = dataPreparer;

    }


    public void createFormsFromData() {

        fillCriterionForm();
        fillMilestoneForm();
        fillRoleTypeForm();
        fillPersonForm();
        fillCPR();
        fillPriorityForm();
        fillSeverityForm();
        fillRelationForm();
        fillResolutionForm();
        fillStatusForm();
        fillTypeForm();
        fillBranchForm();
        fillConfigurationForm();
        fillCommitForm();
        fillCommitedConfigurationForm();
        fillPhaseForm();
        fillActivityForm();
        fillChangeForm();
        fillArtifactForm();
        fillIterationForm();
        fillWorkUnitForm();
        fillVCSTagForm();
        fillLink();


    }


    public void fillConfigurationForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Configuration, dataModelCanvasController.getCanvasType());
        int configurationId = identificatorCreater.getConfigurationId(oldFormId);
        int newConfigurationId = identificatorCreater.getConfigurationId(newFormId);
        dataManipulator.copyDataFromConfiguration(configurationId, newConfigurationId, x, y);
        fillConfigurationForm(newConfigurationId, newFormId, dataModelCanvasController);
    }

    private void fillConfigurationForm(int segmentId, int formId, CanvasController canvasController) {

        Configuration configuration = dataModel.getConfiguration(segmentId);
        String name = configuration.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Configuration, formId, name, configuration.getCoordinates().getXCoordinate(),
                configuration.getCoordinates().getYCoordinate(), configuration.getCount(), configuration.getCountIndicator(), configuration.isExist());

    }

    private void fillConfigurationForm() {
        for (Configuration configuration : dataModel.getConfigurations()) {

            String name = configuration.getAlias();
            int formId = identificatorCreater.setDataToConfigurationMappers(configuration.getId());
            formController.createNewConfigurationFormWithoutCreateId(name, configuration.isExist(), configuration.getId(), formId);


            fillConfigurationForm(configuration.getId(), formId, dataModelCanvasController);
        }
    }


    private void fillChangeForm() {
        ChangeForm form = (ChangeForm) forms.get(Constans.changeFormIndex);
        List<Change> list = dataModel.getChanges();
        for (int i = 0; i < list.size(); i++) {
            Change segment = list.get(i);
            identificatorCreater.createChangeID();
            String idName = segment.getAlias();
            ChangeTable table = new ChangeTable(idName, segment.isExist(), segment.getId());

            form.getTableTV().getItems().add(table);
            segmentLists.getChangeObservable().add(table);
        }
    }

    public void fillCommitForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Commit, dataModelCanvasController.getCanvasType());
        int commitId = identificatorCreater.getCommitId(oldFormId);
        int newCommitId = identificatorCreater.getCommitId(newFormId);
        dataManipulator.copyDataFromCommit(commitId, newCommitId, x, y);
        fillCommitForm(newCommitId, newFormId, dataModelCanvasController);
    }

    private void fillCommitForm(int segmentId, int formId, CanvasController canvasController) {

        Commit commit = dataModel.getCommit(segmentId);

        String name = commit.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Commit, formId, name, commit.getCoordinates().getXCoordinate(),
                commit.getCoordinates().getYCoordinate(), commit.getCount(), commit.getCountIndicator(), commit.isExist());

    }

    private void fillCommitForm() {
        for (Commit commit : dataModel.getCommits()) {

            String name = commit.getAlias();
            int formId = identificatorCreater.setDataToCommitMapper(commit.getId());
            formController.createNewCommitFormWithoutCreateId(name, commit.isExist(), commit.getId(), formId);


            fillCommitForm(commit.getId(), formId, dataModelCanvasController);
        }
    }

    public void fillCommitedConfigurationForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Committed_Configuration, dataModelCanvasController.getCanvasType());
        int commitConfigurationId = identificatorCreater.getCommitedConfigurationId(oldFormId);
        int newCommitConfigurationId = identificatorCreater.getCommitedConfigurationId(newFormId);
        dataManipulator.copyDataFromCommitedConfiguration(commitConfigurationId, newCommitConfigurationId, x, y);
        fillCommitedConfigurationForm(newCommitConfigurationId, newFormId, dataModelCanvasController);
    }

    private void fillCommitedConfigurationForm(int segmentId, int formId, CanvasController canvasController) {

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(segmentId);

        String name = commitedConfiguration.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Committed_Configuration, formId, name, commitedConfiguration.getCoordinates().getXCoordinate(),
                commitedConfiguration.getCoordinates().getYCoordinate(), commitedConfiguration.getCount(), commitedConfiguration.getCountIndicator(), commitedConfiguration.isExist());

    }

    private void fillCommitedConfigurationForm() {
        for (CommitedConfiguration commitedConfiguration : dataModel.getCommitedConfiguration()) {

            String name = commitedConfiguration.getAlias();

            int formId = identificatorCreater.setDataToCommitedConfigurationMapper(commitedConfiguration.getId());
            formController.createNewCommitedConfigurationFormWithoutCreateId(name, commitedConfiguration.isExist(), commitedConfiguration.getId(), formId);

            fillCommitedConfigurationForm(commitedConfiguration.getId(), formId, dataModelCanvasController);
        }
    }


    public void fillArtifactForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Artifact, dataModelCanvasController.getCanvasType());
        int artifactId = identificatorCreater.getArtifactId(oldFormId);
        int newArtifactId = identificatorCreater.getArtifactId(newFormId);
        dataManipulator.copyDataFromArtifact(artifactId, newArtifactId, x, y);
        fillArtifactForm(newArtifactId, newFormId, dataModelCanvasController);
    }

    private void fillArtifactForm(int segmentId, int formId, CanvasController canvasController) {

        Artifact artifact = dataModel.getArtifact(segmentId);

        String name = artifact.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Artifact, formId, name, artifact.getCoordinates().getXCoordinate(),
                artifact.getCoordinates().getYCoordinate(), artifact.getCount(), artifact.getCountIndicator(), artifact.isExist());

    }

    private void fillArtifactForm() {
        for (Artifact artifact : dataModel.getArtifacts()) {

            String name = artifact.getAlias();
            // String description = artifact.getDescription().get(0);

            int formId = identificatorCreater.setDataToArtifactMappers(artifact.getId());
            formController.createNewArtifactFormWithoutCreateId(name, artifact.isExist(), artifact.getId(), formId);
            fillArtifactForm(artifact.getId(), formId, dataModelCanvasController);
        }
    }

    private void fillBranchForm() {
        BranchForm form = (BranchForm) forms.get(Constans.branchIndex);
        for (int i = 0; i < dataModel.getBranches().size(); i++) {
            Branch segment = dataModel.getBranches().get(i);
            int id = formController.createTableItem(SegmentType.Branch);
            String idName = segment.getAlias();
            String isMain = "NO";
            if (segment.isIsMain()) {
                isMain = "YES";
            }

            BranchTable table = new BranchTable(idName, isMain, segment.isIsMain(), segment.isExist(), id);

            form.getTableTV().getItems().add(table);
            segmentLists.getBranchObservable().add(table);
        }
    }

    private ClassTable createClassTable(int id, String name, List<String> lclass, List<String> lsuperClass, boolean exist) {

//        String classType = lclass.get(0);
//        String superClass = lsuperClass.get(0);

        return new ClassTable(name, "", "", exist, id);
    }

    private void fillTypeForm() {
        TypeForm form = (TypeForm) forms.get(Constans.wuTypeFormIndex);
        for (int i = 0; i < dataModel.getTypes().size(); i++) {
            Type segment = dataModel.getTypes().get(i);
            formController.createTableItem(SegmentType.Type);
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getTypeClass(),
                    segment.getTypeSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getTypeObservable().add(table);
        }
    }

    private void fillStatusForm() {
        StatusForm form = (StatusForm) forms.get(Constans.statusFormIndex);
        for (int i = 0; i < dataModel.getStatuses().size(); i++) {
            Status segment = dataModel.getStatuses().get(i);
            formController.createTableItem(SegmentType.Status);
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getStatusClass(),
                    segment.getStatusSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getStatusTypeObservable().add(table);
        }
    }

    private void fillResolutionForm() {
        ResolutionForm form = (ResolutionForm) forms.get(Constans.resolutionormIndex);
        for (int i = 0; i < dataModel.getResolutions().size(); i++) {
            Resolution segment = dataModel.getResolutions().get(i);
            formController.createTableItem(SegmentType.Resolution);

            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getResolutionClass(),
                    segment.getResolutionSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getResolutionTypeObservable().add(table);
        }
    }

    private void fillRelationForm() {
        RelationForm form = (RelationForm) forms.get(Constans.relationFormIndex);
        for (int i = 0; i < dataModel.getRelations().size(); i++) {
            Relation segment = dataModel.getRelations().get(i);
            formController.createTableItem(SegmentType.Relation);

            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getRelationClass(),
                    segment.getRelationSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getRelationTypeObservable().add(table);
        }
    }

    private void fillSeverityForm() {
        SeverityForm form = (SeverityForm) forms.get(Constans.severityFormIndex);
        for (int i = 0; i < dataModel.getSeverities().size(); i++) {
            Severity segment = dataModel.getSeverities().get(i);
            formController.createTableItem(SegmentType.Severity);

            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getSeverityClass(),
                    segment.getSeveritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getSeverityTypeObservable().add(table);
        }
    }

    private void fillPriorityForm() {
        PriorityForm form = (PriorityForm) forms.get(Constans.priorityFormIndex);
        for (int i = 0; i < dataModel.getPriorities().size(); i++) {
            Priority segment = dataModel.getPriorities().get(i);
            formController.createTableItem(SegmentType.Priority);

            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getPriorityClass(),
                    segment.getPrioritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getPriorityTypeObservable().add(table);
        }
    }

    private void fillCPR() {
        ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) forms.get(Constans.cprFormIndex);
        for (int i = 0; i < dataModel.getConfigPersonRelations().size(); i++) {
            ConfigPersonRelation cpr = dataModel.getConfigPersonRelations().get(i);
            int id = cpr.getId();
            identificatorCreater.createCPRID();
            String name = cpr.getAlias();

            CPRTable cprTable = new CPRTable(name, "", cpr.isExist(), id);

            cprForm.getTableTV().getItems().add(cprTable);
            segmentLists.getCPRObservable().add(cprTable);
        }
    }

    public void fillPersonForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Person, dataModelCanvasController.getCanvasType());
        int personId = identificatorCreater.getRoleIndexToIdMaper().get(oldFormId);
        int newPersonId = identificatorCreater.getRoleIndexToIdMaper().get(newFormId);
        dataManipulator.copyDataFromPerson(personId, newPersonId, x, y);
        fillPersonForm(newPersonId, newFormId, dataModelCanvasController);
    }

    private void fillPersonForm(int segmentId, int formId, CanvasController canvasController) {

        Person person = dataModel.getPerson(segmentId);
        String name = person.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Person, formId, name, person.getCoordinates().getXCoordinate(),
                person.getCoordinates().getYCoordinate(), person.getCount(), person.getCountIndicator(), person.isExist());

    }

    private void fillPersonForm() {
        for (Person person : dataModel.getPersons()) {

            String name = person.getAlias();

            int formId = identificatorCreater.setDataToRoleMappers(person.getId());
            formController.createNewPersonFormWithoutCreateId(name, person.isExist(), person.getId(), formId);

            fillPersonForm(person.getId(), formId, dataModelCanvasController);
        }
    }

    private void fillRoleTypeForm() {
        RoleTypeForm roleTypeForm = (RoleTypeForm) forms.get(Constans.roleTypeIndex);
        for (int i = 0; i < dataModel.getRoleTypes().size(); i++) {
            RoleType segment = dataModel.getRoleTypes().get(i);
            formController.createTableItem(SegmentType.Role_Type);

            String name = segment.getAlias();

            RoleTypeTable table = new RoleTypeTable(name, "", segment.isExist(), "", segment.getId());
            TableView<RoleTypeTable> roleType = roleTypeForm.getTableTV();
            roleType.getItems().add(table);
            segmentLists.getRoleTypeObservable().add(table);
        }
    }

    private void fillMilestoneForm() {
        MilestoneForm milestoneForm = (MilestoneForm) forms.get(Constans.milestoneFormIndex);
        for (int i = 0; i < dataModel.getMilestones().size(); i++) {
            Milestone milestone = dataModel.getMilestones().get(i);
            int id = milestone.getId();
            formController.createTableItem(SegmentType.Milestone);
            String idName = milestone.getAlias();
            MilestoneTable milestoneTable = new MilestoneTable(idName, milestone.isExist(), id);
            milestoneForm.getTableTV().getItems().add(milestoneTable);
            segmentLists.getMilestoneObservable().add(milestoneTable);
        }
    }


    private void fillCriterionForm() {
        CriterionForm criterionForm = (CriterionForm) forms.get(Constans.criterionFormIndex);
        for (Criterion criterion : dataModel.getCriterions()) {
            formController.createTableItem(SegmentType.Criterion);
            TableView<CriterionTable> criterionView = criterionForm.getTableTV();
            CriterionTable criterionTable = new CriterionTable(criterion.getAlias(), criterion.isExist(), criterion.getId());
            criterionView.getItems().add(criterionTable);
            segmentLists.getCriterionObservable().add(criterionTable);
        }

    }


    public void fillPhaseForm() {
        PhaseForm phaseForm = (PhaseForm) forms.get(Constans.phaseFormIndex);
        for (Phase phase : dataModel.getPhases()) {
            identificatorCreater.createPhaseID();
            TableView<PhaseTable> phaseView = phaseForm.getTableTV();
            PhaseTable phaseTable = new PhaseTable(phase.getAlias(), phase.isExist(), phase.getId());
            phaseView.getItems().add(phaseTable);
        }

    }

    public void fillActivityForm() {
        ActivityForm activityForm = (ActivityForm) forms.get(Constans.activityFormIndex);
        for (Activity activity : dataModel.getActivities()) {
            identificatorCreater.createActivityID();
            TableView<ActivityTable> activityView = activityForm.getTableTV();
            ActivityTable activityTable = new ActivityTable(activity.getAlias(), activity.isExist(), activity.getId());
            activityView.getItems().add(activityTable);
        }

    }

    public void fillIterationForm() {
        IterationForm iterationForm = (IterationForm) forms.get(Constans.iterationFormIndex);
        for (Iteration iteration : dataModel.getIterations()) {
            identificatorCreater.createIterationID();
            TableView<IterationTable> iterationView = iterationForm.getTableTV();
            IterationTable iterationTable = new IterationTable(iteration.getAlias(), iteration.isExist(), iteration.getId());
            iterationView.getItems().add(iterationTable);
        }

    }

    public void fillWorkUnitForm() {
        WorkUnitForm workUnitForm = (WorkUnitForm) forms.get(Constans.workUnitFormIndex);
        for (WorkUnit workUnit : dataModel.getWorkUnits()) {
            identificatorCreater.createWorkUnitID();
            TableView<WorkUnitTable> workUnitView = workUnitForm.getTableTV();
            WorkUnitTable workUnitTable = new WorkUnitTable(workUnit.getAlias(), workUnit.isExist(), workUnit.getId());
            workUnitView.getItems().add(workUnitTable);
            segmentLists.getWorkUnitsObservable().add(workUnitTable);
        }

    }

    public void fillVCSTagForm() {
        VCSTagForm tagForm = (VCSTagForm) forms.get(Constans.VCSTagIndex);
        for (VCSTag tag : dataModel.getVCSTags()) {
            identificatorCreater.createVCSTagID();
            TableView<VCSTagTable> tagView = tagForm.getTableTV();
            VCSTagTable tagTable = new VCSTagTable(tag.getAlias(), tag.isExist(), tag.getId());
            tagView.getItems().add(tagTable);
        }

    }

    private void repaintLink(CanvasItem item, int formId) {

        double x = item.getTranslateX();
        double y = item.getTranslateY();
        double width = item.getLength();
        double height = item.getHeight();

        linkControl.repaintArrow(SegmentType.Artifact, formId, x + 1, y + 1, width, height);
        Point2D point = new Point2D(x, y);
        item.setPosition(point);
    }

    public void fillLink() {
        for (int i = 0; i < dataModel.getLinks().size(); i++) {
            Link link = dataModel.getLinks().get(i);
            int linkId = link.getId();
            int startId;
            int endId;
            int startFormId;
            int endFormId;
            CanvasItem item;
            CanvasItem endItem;
            switch (link.getType()) {
                case PERSON_ARTIFACT:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Artifact,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case PERSON_COMMITTED_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);

                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getCommitedConfigurationFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);
                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Committed_Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case PERSON_COMMIT:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);

                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getCommitFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);
                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Commit,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case PERSON_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case ARTIFACT_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getArtifactIndex(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Artifact, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case COMMITED_CONFIGURATION_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getCommitedConfigurationFormIndex(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Committed_Configuration, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getConfigurationFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
                case COMMIT_COMMITED_CONFIGURATION:
                    startId = link.getStartIndex();
                    startFormId = identificatorCreater.getCommitFormIndex(startId);
                    item = dataModelCanvasController.getListOfItemOnCanvas().get(startFormId);
                    linkControl.ArrowManipulation(false, dataModelCanvasController, item.getFormIdentificator(), SegmentType.Commit, item.getTranslateX(),
                            item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
                    repaintLink(item, startFormId);
                    endId = link.getEndIndex();
                    endFormId = identificatorCreater.getCommitedConfigurationFormIndex(endId);
                    endItem = dataModelCanvasController.getListOfItemOnCanvas().get(endFormId);

                    linkControl.ArrowManipulation(true, dataModelCanvasController, endItem.getFormIdentificator(), SegmentType.Committed_Configuration,
                            endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                            true, linkId);
                    repaintLink(endItem, endFormId);
                    identificatorCreater.setLinkId(linkId);
                    break;
            }
        }

    }


    /**
     * Geters
     */

    public void setProjectCanvasController(CanvasController dataModelCanvasController) {
        this.dataModelCanvasController = dataModelCanvasController;
    }


}
