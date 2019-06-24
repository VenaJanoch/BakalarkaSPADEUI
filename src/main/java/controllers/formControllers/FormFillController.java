package controllers.formControllers;

import SPADEPAC.*;
import abstractform.BasicForm;
import controllers.DataPreparer;
import controllers.LinkControl;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import forms.*;
import graphics.canvas.CanvasItem;
import interfaces.IFormDataController;
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
/**
 * Trida predstavujici controller pro zpetne nahrani dat z XML, pripadne kopirovani prvku
 *
 * @author Václav Janoch
 */
public class FormFillController {

    /** Promenne predstavujici instance objektu z datoveho modelu pro moznost vytvareni konkretnich prvku **/
    private DataManipulator dataManipulator;
    private DataModel dataModel;
    private IdentificatorCreater identificatorCreater;
    private SegmentLists segmentLists;

    /** Promenne predstavujici instace objektu kontroleru potrebnych pro cinnost tritdy **/
    private FormController formController;
    private IFormDataController formDataController;
    private CanvasItemController canvasItemController;
    private CanvasController canvasController;
    private LinkControl linkControl;

    /**Seznam formularu**/
    private ArrayList<BasicForm> forms;


    /**
     * Kontroler tridy
     * Zinicializuje globalni promenne tridy
     * @param formController instace tridy FormController pro formularu
     * @param formDataController instance tridy FormDataController pro praci s tabulkovimi formulari
     * @param dataModel instace datoveho modelu
     * @param canvasItemController kontroler pro praci s prvky platna
     * @param identificatorCreater instance tridy pro vytvreni identifikatoru
     * @param lists prehledove seznamy
     * @param linkControl kontroler potrebny pro vytvareni spojnic
     */
    public FormFillController(FormController formController, IFormDataController formDataController, DataModel dataModel, CanvasItemController canvasItemController,
                              IdentificatorCreater identificatorCreater, SegmentLists lists, LinkControl linkControl) {
        this.formController = formController;
        this.forms = formController.getForms();
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getDataManipulator();
        this.canvasItemController = canvasItemController;
        this.identificatorCreater = identificatorCreater;
        this.segmentLists = lists;
        this.linkControl = linkControl;
        this.formDataController = formDataController;
        this.formDataController.setFormFillController(this);
    }

    /**
     * Metoda pro postupne volani metod pro zpetne nahrani dat z XML
     */
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

    /**
     * Pretizena metoda pro naplneni formulare daty z Configuration
     * Slouzi v pripade potreby prvek kopirovat
     * @param oldFormId identifikator kopirovane instace
     * @param x x souradnice
     * @param y y souradnice
     */
    public void fillConfigurationForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Configuration, canvasController.getCanvasType());
        int configurationId = identificatorCreater.getConfigurationId(oldFormId);
        int newConfigurationId = identificatorCreater.getConfigurationId(newFormId);
        dataManipulator.copyDataFromConfiguration(configurationId, newConfigurationId, x, y);
        fillConfigurationForm(newConfigurationId, newFormId, canvasController);
    }

    /**
     * Pretizena metoda pro naplneni prvku na platne daty o Configuration z datoveho modelu
     * @param segmentId idententifikator prvku pro ziskani dat
     * @param formId identifikator formulare pro naplneni
     * @param canvasController kontroler pro rizeni udalosti na platne
     */
    private void fillConfigurationForm(int segmentId, int formId, CanvasController canvasController) {

        Configuration configuration = dataModel.getConfiguration(segmentId);
        String name = configuration.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Configuration, formId, name, configuration.getCoordinates().getXCoordinate(),
                configuration.getCoordinates().getYCoordinate(), configuration.getCount(), configuration.getCountIndicator(), configuration.isExist());

    }

    /**
     * Pretizena metoda pro vytvoreni formularu z XML souboru
     * Metoda projede veskere instance Configuration v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillConfigurationForm() {
        for (Configuration configuration : dataModel.getConfigurations()) {

            String name = configuration.getAlias();
            int formId = identificatorCreater.setDataToConfigurationMappers(configuration.getId());
            formController.createNewConfigurationFormWithoutCreateId(name, configuration.isExist(), configuration.getId(), formId);


            fillConfigurationForm(configuration.getId(), formId, canvasController);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Change v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
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
    /**
     * Pretizena metoda pro naplneni formulare daty z Commit
     * Slouzi v pripade potreby prvek kopirovat
     * @param oldFormId identifikator kopirovane instace
     * @param x x souradnice
     * @param y y souradnice
     */
    public void fillCommitForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Commit, canvasController.getCanvasType());
        int commitId = identificatorCreater.getCommitId(oldFormId);
        int newCommitId = identificatorCreater.getCommitId(newFormId);
        dataManipulator.copyDataFromCommit(commitId, newCommitId, x, y);
        fillCommitForm(newCommitId, newFormId, canvasController);
    }

    /**
     * Pretizena metoda pro naplneni prvku na platne daty o Commit z datoveho modelu
     * @param segmentId idententifikator prvku pro ziskani dat
     * @param formId identifikator formulare pro naplneni
     * @param canvasController kontroler pro rizeni udalosti na platne
     */
    private void fillCommitForm(int segmentId, int formId, CanvasController canvasController) {

        Commit commit = dataModel.getCommit(segmentId);
        String name = commit.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Commit, formId, name, commit.getCoordinates().getXCoordinate(),
                commit.getCoordinates().getYCoordinate(), commit.getCount(), commit.getCountIndicator(), commit.isExist());

    }
    /**
     * Pretizena metoda pro vytvoreni formularu z XML souboru
     * Metoda projede veskere instance Commit v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillCommitForm() {
        for (Commit commit : dataModel.getCommits()) {

            String name = commit.getAlias();
            int formId = identificatorCreater.setDataToCommitMapper(commit.getId());
            formController.createNewCommitFormWithoutCreateId(commit.isExist(), commit.getId(), formId);


            fillCommitForm(commit.getId(), formId, canvasController);
        }
    }

    /**
     * Pretizena metoda pro naplneni formulare daty z Committed Configuration
     * Slouzi v pripade potreby prvek kopirovat
     * @param oldFormId identifikator kopirovane instace
     * @param x x souradnice
     * @param y y souradnice
     */
    public void fillCommitedConfigurationForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Committed_Configuration, canvasController.getCanvasType());
        int commitConfigurationId = identificatorCreater.getCommitedConfigurationId(oldFormId);
        int newCommitConfigurationId = identificatorCreater.getCommitedConfigurationId(newFormId);
        dataManipulator.copyDataFromCommitedConfiguration(commitConfigurationId, newCommitConfigurationId, x, y);
        fillCommitedConfigurationForm(newCommitConfigurationId, newFormId, canvasController);
    }

    /**
     * Pretizena metoda pro naplneni prvku na platne daty o Commited Configuration z datoveho modelu
     * @param segmentId idententifikator prvku pro ziskani dat
     * @param formId identifikator formulare pro naplneni
     * @param canvasController kontroler pro rizeni udalosti na platne
     */
    private void fillCommitedConfigurationForm(int segmentId, int formId, CanvasController canvasController) {

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(segmentId);

        String name = commitedConfiguration.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Committed_Configuration, formId, name, commitedConfiguration.getCoordinates().getXCoordinate(),
                commitedConfiguration.getCoordinates().getYCoordinate(), commitedConfiguration.getCount(), commitedConfiguration.getCountIndicator(), commitedConfiguration.isExist());

    }

    /**
     * Pretizena metoda pro vytvoreni formularu z XML souboru
     * Metoda projede veskere instance Commited Configuration v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillCommitedConfigurationForm() {
        for (CommitedConfiguration commitedConfiguration : dataModel.getCommitedConfiguration()) {

            String name = commitedConfiguration.getAlias();

            int formId = identificatorCreater.setDataToCommitedConfigurationMapper(commitedConfiguration.getId());
            formController.createNewCommitedConfigurationFormWithoutCreateId(commitedConfiguration.isExist(), commitedConfiguration.getId(), formId);

            fillCommitedConfigurationForm(commitedConfiguration.getId(), formId, canvasController);
        }
    }

    /**
     * Pretizena metoda pro naplneni formulare daty z Artifact
     * Slouzi v pripade potreby prvek kopirovat
     * @param oldFormId identifikator kopirovane instace
     * @param x x souradnice
     * @param y y souradnice
     */
    public void fillArtifactForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Artifact, canvasController.getCanvasType());
        int artifactId = identificatorCreater.getArtifactId(oldFormId);
        int newArtifactId = identificatorCreater.getArtifactId(newFormId);
        dataManipulator.copyDataFromArtifact(artifactId, newArtifactId, x, y);
        fillArtifactForm(newArtifactId, newFormId, canvasController);
    }

    /**
     * Pretizena metoda pro naplneni prvku na platne daty o Artifact z datoveho modelu
     * @param segmentId idententifikator prvku pro ziskani dat
     * @param formId identifikator formulare pro naplneni
     * @param canvasController kontroler pro rizeni udalosti na platne
     */
    private void fillArtifactForm(int segmentId, int formId, CanvasController canvasController) {

        Artifact artifact = dataModel.getArtifact(segmentId);
        String name = artifact.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Artifact, formId, name, artifact.getCoordinates().getXCoordinate(),
                artifact.getCoordinates().getYCoordinate(), artifact.getCount(), artifact.getCountIndicator(), artifact.isExist());

    }

    /**
     * Pretizena metoda pro vytvoreni formularu z XML souboru
     * Metoda projede veskere instance Artifact v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillArtifactForm() {
        for (Artifact artifact : dataModel.getArtifacts()) {

            String name = artifact.getAlias();
            int formId = identificatorCreater.setDataToArtifactMappers(artifact.getId());
            formController.createNewArtifactFormWithoutCreateId(name, artifact.isExist(), artifact.getId(), formId);
            fillArtifactForm(artifact.getId(), formId, canvasController);
        }
    }
    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Branch v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillBranchForm() {
        BranchForm form = (BranchForm) forms.get(Constans.branchIndex);
        for (int i = 0; i < dataModel.getBranches().size(); i++) {
            Branch segment = dataModel.getBranches().get(i);
           identificatorCreater.createBranchID();
            String idName = segment.getAlias();
            String isMain = "NO";
            if (segment.isIsMain()) {
                isMain = "YES";
            }

            BranchTable table = new BranchTable(idName, isMain, segment.isIsMain(), segment.isExist(), segment.getId());

            form.getTableTV().getItems().add(table);
            segmentLists.getBranchObservable().add(table);
        }
    }

    /**Pomocna metoda pro vytvoreni vyctovych typu Work Unit**/
    private ClassTable createClassTable(int id, String name, List<String> lclass, List<String> lsuperClass, boolean exist) {

        return new ClassTable(name, "", "", exist, id);
    }
    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Type v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillTypeForm() {
        TypeForm form = (TypeForm) forms.get(Constans.wuTypeFormIndex);
        for (int i = 0; i < dataModel.getTypes().size(); i++) {
            Type segment = dataModel.getTypes().get(i);
           identificatorCreater.createTypeID();
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getTypeClass(),
                    segment.getTypeSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getTypeObservable().add(table);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Status v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillStatusForm() {
        StatusForm form = (StatusForm) forms.get(Constans.statusFormIndex);
        for (int i = 0; i < dataModel.getStatuses().size(); i++) {
            Status segment = dataModel.getStatuses().get(i);
            identificatorCreater.createStatusID();
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getStatusClass(),
                    segment.getStatusSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getStatusTypeObservable().add(table);
        }
    }
    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projde veskere instance Resolution v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillResolutionForm() {
        ResolutionForm form = (ResolutionForm) forms.get(Constans.resolutionormIndex);
        for (int i = 0; i < dataModel.getResolutions().size(); i++) {
            Resolution segment = dataModel.getResolutions().get(i);
            identificatorCreater.createResolutionID();

            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getResolutionClass(),
                    segment.getResolutionSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getResolutionTypeObservable().add(table);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Relation v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillRelationForm() {
        RelationForm form = (RelationForm) forms.get(Constans.relationFormIndex);
        for (int i = 0; i < dataModel.getRelations().size(); i++) {
            Relation segment = dataModel.getRelations().get(i);
            identificatorCreater.createRelationID();
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getRelationClass(),
                    segment.getRelationSuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getRelationTypeObservable().add(table);
        }
    }
    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Severity v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillSeverityForm() {
        SeverityForm form = (SeverityForm) forms.get(Constans.severityFormIndex);
        for (int i = 0; i < dataModel.getSeverities().size(); i++) {
            Severity segment = dataModel.getSeverities().get(i);
           identificatorCreater.createSeverityID();
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getSeverityClass(),
                    segment.getSeveritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getSeverityTypeObservable().add(table);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Priority v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillPriorityForm() {
        PriorityForm form = (PriorityForm) forms.get(Constans.priorityFormIndex);
        for (int i = 0; i < dataModel.getPriorities().size(); i++) {
            Priority segment = dataModel.getPriorities().get(i);
            identificatorCreater.createPriorityID();
            ClassTable table = createClassTable(segment.getId(), segment.getAlias(), segment.getPriorityClass(),
                    segment.getPrioritySuperClass(), segment.isExist());

            form.getTableTV().getItems().add(table);
            segmentLists.getPriorityTypeObservable().add(table);
        }
    }
    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Configuration Person v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
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

    /**
     * Pretizena metoda pro naplneni formulare daty z Person
     * Slouzi v pripade potreby prvek kopirovat
     * @param oldFormId identifikator kopirovane instace
     * @param x x souradnice
     * @param y y souradnice
     */
    public void fillPersonForm(int oldFormId, double x, double y) {
        int newFormId = formController.createNewForm(SegmentType.Person, canvasController.getCanvasType());
        int personId = identificatorCreater.getRoleIndexToIdMaper().get(oldFormId);
        int newPersonId = identificatorCreater.getRoleIndexToIdMaper().get(newFormId);
        dataManipulator.copyDataFromPerson(personId, newPersonId, x, y);
        fillPersonForm(newPersonId, newFormId, canvasController);
    }

    /**
     * Pretizena metoda pro naplneni prvku na platne daty o Person z datoveho modelu
     * @param segmentId idententifikator prvku pro ziskani dat
     * @param formId identifikator formulare pro naplneni
     * @param canvasController kontroler pro rizeni udalosti na platne
     */
    private void fillPersonForm(int segmentId, int formId, CanvasController canvasController) {

        Person person = dataModel.getPerson(segmentId);
        String name = person.getAlias();
        canvasController.addCanvasItemFromExistData(SegmentType.Person, formId, name, person.getCoordinates().getXCoordinate(),
                person.getCoordinates().getYCoordinate(), person.getCount(), person.getCountIndicator(), person.isExist());

    }
    /**
     * Pretizena metoda pro vytvoreni prvku z XML souboru
     * Metoda projede veskere instance Person v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillPersonForm() {
        for (Person person : dataModel.getPersons()) {

            String name = person.getAlias();

            int formId = identificatorCreater.setDataToRoleMappers(person.getId());
            formController.createNewPersonFormWithoutCreateId(person.isExist(), person.getId(), formId);

            fillPersonForm(person.getId(), formId, canvasController);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Role Type v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillRoleTypeForm() {
        RoleTypeForm roleTypeForm = (RoleTypeForm) forms.get(Constans.roleTypeIndex);
        for (int i = 0; i < dataModel.getRoleTypes().size(); i++) {
            RoleType segment = dataModel.getRoleTypes().get(i);
            identificatorCreater.createRoleTypeID();
            String name = segment.getAlias();

            RoleTypeTable table = new RoleTypeTable(name, "", segment.isExist(), "", segment.getId());
            TableView<RoleTypeTable> roleType = roleTypeForm.getTableTV();
            roleType.getItems().add(table);
            segmentLists.getRoleTypeObservable().add(table);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Milestone v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillMilestoneForm() {
        MilestoneForm milestoneForm = (MilestoneForm) forms.get(Constans.milestoneFormIndex);
        for (int i = 0; i < dataModel.getMilestones().size(); i++) {
            Milestone milestone = dataModel.getMilestones().get(i);
            int id = milestone.getId();
            identificatorCreater.createMilestoneID();
            String idName = milestone.getAlias();
            MilestoneTable milestoneTable = new MilestoneTable(idName, milestone.isExist(), id);
            milestoneForm.getTableTV().getItems().add(milestoneTable);
            segmentLists.getMilestoneObservable().add(milestoneTable);
        }
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Criterion v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    private void fillCriterionForm() {
        CriterionForm criterionForm = (CriterionForm) forms.get(Constans.criterionFormIndex);
        for (Criterion criterion : dataModel.getCriterions()) {
            identificatorCreater.createCriterionID();
            TableView<CriterionTable> criterionView = criterionForm.getTableTV();
            CriterionTable criterionTable = new CriterionTable(criterion.getAlias(), criterion.isExist(), criterion.getId());
            criterionView.getItems().add(criterionTable);
            segmentLists.getCriterionObservable().add(criterionTable);
        }

    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Phase v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    public void fillPhaseForm() {
        PhaseForm phaseForm = (PhaseForm) forms.get(Constans.phaseFormIndex);
        for (Phase phase : dataModel.getPhases()) {
            identificatorCreater.createPhaseID();
            TableView<PhaseTable> phaseView = phaseForm.getTableTV();
            PhaseTable phaseTable = new PhaseTable(phase.getAlias(), phase.isExist(), phase.getId());
            phaseView.getItems().add(phaseTable);
        }

    }
    /**
     * Pretizena metoda pro kopirovani instace Activity
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldActivityId identifikator kopirovaneho prvku
     */
    public void fillActivityForm(TableView<ActivityTable> tableView, int oldActivityId){
        Activity activity = dataModel.getActivity(oldActivityId);
        int newActivityId = formDataController.saveDataFromActivityForm(tableView, activity.isExist());
        dataManipulator.copyDataFromActivity(oldActivityId, newActivityId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Iteration
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldIterationId identifikator kopirovaneho prvku
     */
    public void fillIterationForm(TableView<IterationTable> tableView, int oldIterationId){
        Iteration iteration = dataModel.getIteration(oldIterationId);
        int newIterationId = formDataController.saveDataFromIterationForm(tableView, iteration.isExist());
        dataManipulator.copyDataFromIteration(oldIterationId, newIterationId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Phase
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldPhaseId identifikator kopirovaneho prvku
     */
    public void fillPhaseForm(TableView<PhaseTable> tableView, int oldPhaseId){
        Phase phase = dataModel.getPhase(oldPhaseId);
        int newPhaseId = formDataController.saveDataFromPhaseForm(tableView, phase.isExist());
        dataManipulator.copyDataFromPhase(oldPhaseId, newPhaseId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Change
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldChangeId identifikator kopirovaneho prvku
     */
    public void fillChangeForm(TableView<ChangeTable> tableView, int oldChangeId){
        Change phase = dataModel.getChange(oldChangeId);
        int newChangeId = formDataController.saveDataFromChangeForm(tableView, phase.isExist());
        dataManipulator.copyDataFromChange(oldChangeId, newChangeId);
    }
    /**
     * Pretizena metoda pro kopirovani instace VCSTag
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldVCSTagId identifikator kopirovaneho prvku
     */
    public void fillVCSTagForm(TableView<VCSTagTable> tableView, int oldVCSTagId){
        VCSTag phase = dataModel.getVCSTag(oldVCSTagId);
        int newVCSTagId = formDataController.saveDataFromVCSTagForm(tableView, phase.isExist());
        dataManipulator.copyDataFromVCSTag(oldVCSTagId, newVCSTagId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Configuration Person
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldCPRId identifikator kopirovaneho prvku
     */
    public void fillCPRForm(TableView<CPRTable> tableView, int oldCPRId){
        ConfigPersonRelation phase = dataModel.getConfigPersonRelation(oldCPRId);
        int newCPRId = formDataController.saveDataFromCPR(tableView, phase.isExist());
        dataManipulator.copyDataFromCPR(oldCPRId, newCPRId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Branch
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldBranchId identifikator kopirovaneho prvku
     */
    public void fillBranchForm(TableView<BranchTable> tableView, int oldBranchId){
        Branch phase = dataModel.getBranch(oldBranchId);
        int newBranchId = formDataController.saveDataFromBranch(tableView, phase.isExist());
        dataManipulator.copyDataFromBranch(oldBranchId, newBranchId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Criterion
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldCriterionId identifikator kopirovaneho prvku
     */
    public void fillCriterionForm(TableView<CriterionTable> tableView, int oldCriterionId){
        Criterion phase = dataModel.getCriterion(oldCriterionId);
        int newCriterionId = formDataController.saveDataFromCriterionForm(tableView, phase.isExist());
        dataManipulator.copyDataFromCriterion(oldCriterionId, newCriterionId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Milestone
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldMilestoneId identifikator kopirovaneho prvku
     */
    public void fillMilestoneForm(TableView<MilestoneTable> tableView, int oldMilestoneId){
        Milestone phase = dataModel.getMilestone(oldMilestoneId);
        int newMilestoneId = formDataController.saveDataFromMilestoneForm(tableView, phase.isExist());
        dataManipulator.copyDataFromMilestone(oldMilestoneId, newMilestoneId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Role Type
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldRoleTypeId identifikator kopirovaneho prvku
     */
    public void fillRoleTypeForm(TableView<RoleTypeTable> tableView, int oldRoleTypeId){
        RoleType phase = dataModel.getRoleType(oldRoleTypeId);
        int newRoleTypeId = formDataController.saveDataFromRoleTypeForm(tableView, phase.isExist());
        dataManipulator.copyDataFromRoleType(oldRoleTypeId, newRoleTypeId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Priority
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldPriorityId identifikator kopirovaneho prvku
     */
    public void fillPriorityForm(TableView<ClassTable> tableView, int oldPriorityId){
        Priority phase = dataModel.getPriority(oldPriorityId);
        int newPriorityId = formDataController.saveDataFromPriority(tableView, phase.isExist());
        dataManipulator.copyDataFromPriority(oldPriorityId, newPriorityId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Severity
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldSeverityId identifikator kopirovaneho prvku
     */
    public void fillSeverityForm(TableView<ClassTable> tableView, int oldSeverityId){
        Severity phase = dataModel.getSeverity(oldSeverityId);
        int newSeverityId = formDataController.saveDataFromSeverity(tableView, phase.isExist());
        dataManipulator.copyDataFromSeverity(oldSeverityId, newSeverityId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Status
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldStatusId identifikator kopirovaneho prvku
     */
    public void fillStatusForm(TableView<ClassTable> tableView, int oldStatusId){
        Status phase = dataModel.getStatus(oldStatusId);
        int newStatusId = formDataController.saveDataFromStatusForm(tableView, phase.isExist());
        dataManipulator.copyDataFromStatus(oldStatusId, newStatusId);
    }
    /**
     * Pretizena metoda pro kopirovani instace Type
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldTypeId identifikator kopirovaneho prvku
     */
    public void fillTypeForm(TableView<ClassTable> tableView, int oldTypeId){
        Type phase = dataModel.getType(oldTypeId);
        int newTypeId = formDataController.saveDataFromTypeForm(tableView, phase.isExist());
        dataManipulator.copyDataFromType(oldTypeId, newTypeId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Relation
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldRelationId identifikator kopirovaneho prvku
     */
    public void fillRelationForm(TableView<ClassTable> tableView, int oldRelationId){
        Relation phase = dataModel.getRelation(oldRelationId);
        int newRelationId = formDataController.saveDataFromRelationForm(tableView, phase.isExist());
        dataManipulator.copyDataFromRelation(oldRelationId, newRelationId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Resolution
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldResolutionId identifikator kopirovaneho prvku
     */
    public void fillResolutionForm(TableView<ClassTable> tableView, int oldResolutionId){
        Resolution phase = dataModel.getResolution(oldResolutionId);
        int newResolutionId = formDataController.saveDataFromResolutionForm(tableView, phase.isExist());
        dataManipulator.copyDataFromResolution(oldResolutionId, newResolutionId);
    }

    /**
     * Pretizena metoda pro kopirovani instace Work Unit
     * A zavolani datoveho modelu pro nakopirovani informaci o starem prvku
     * @param tableView instance tridy TableView pro vlozeni nove instace
     * @param oldWorkUnitId identifikator kopirovaneho prvku
     */
    public void fillWorkUnitForm(TableView<WorkUnitTable> tableView, int oldWorkUnitId){
        WorkUnit phase = dataModel.getWorkUnit(oldWorkUnitId);
        int newWorkUnitId = formDataController.saveDataFromWorkUnit(tableView, phase.isExist());
        dataManipulator.copyDataFromWorkUnit(oldWorkUnitId, newWorkUnitId);
    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Activity v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    public void fillActivityForm() {
        ActivityForm activityForm = (ActivityForm) forms.get(Constans.activityFormIndex);
        for (Activity activity : dataModel.getActivities()) {
            identificatorCreater.createActivityID();
            TableView<ActivityTable> activityView = activityForm.getTableTV();
            ActivityTable activityTable = new ActivityTable(activity.getAlias(), activity.isExist(), activity.getId());
            activityView.getItems().add(activityTable);
        }

    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Iteration v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    public void fillIterationForm() {
        IterationForm iterationForm = (IterationForm) forms.get(Constans.iterationFormIndex);
        for (Iteration iteration : dataModel.getIterations()) {
            identificatorCreater.createIterationID();
            TableView<IterationTable> iterationView = iterationForm.getTableTV();
            IterationTable iterationTable = new IterationTable(iteration.getAlias(), iteration.isExist(), iteration.getId());
            iterationView.getItems().add(iterationTable);
        }

    }

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance Work Unit v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
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

    /**
     * Pretizena metoda pro vytvoreni tabulkovych prvku z XML souboru
     * Metoda projede veskere instance VCSTag v datovem modelu
     * a zavola potrebne metody pro naplneni datovych struktur a grafickych prvku
     */
    public void fillVCSTagForm() {
        VCSTagForm tagForm = (VCSTagForm) forms.get(Constans.VCSTagIndex);
        for (VCSTag tag : dataModel.getVCSTags()) {
            identificatorCreater.createVCSTagID();
            TableView<VCSTagTable> tagView = tagForm.getTableTV();
            VCSTagTable tagTable = new VCSTagTable(tag.getAlias(), tag.isExist(), tag.getId());
            tagView.getItems().add(tagTable);
        }

    }

    /**
     * Metoda pro prekresleni sponice pri vytvoreni spojnice
     * @param item prvke platna
     * @param formId identifikator formulare
     */
    private void repaintLink(CanvasItem item, int formId) {

        double x = item.getTranslateX();
        double y = item.getTranslateY();
        double width = item.getLength();
        double height = item.getHeight();

        linkControl.repaintArrow(SegmentType.Artifact, formId, x + 1, y + 1, width, height);
        Point2D point = new Point2D(x, y);
        item.setPosition(point);
    }

    /**
     * Metoda pro vytvoreni spojnic z XML souboru
     * Metoda na zaklade typu spojnice zavola potrebne metody
     **/
    public void fillLink() {
        for (int i = 0; i < dataModel.getLinks().size(); i++) {
            Link link = dataModel.getLinks().get(i);
            int linkId = link.getId();
            switch (link.getType()) {
                case PERSON_ARTIFACT:
                    createPersonArtifactLink(linkId, link);
                    break;
                case PERSON_COMMITTED_CONFIGURATION:
                    createPersonCommittedConfigurationLink(linkId, link);
                    break;
                case PERSON_COMMIT:
                    createPersonCommitLink(linkId, link);
                    break;
                case PERSON_CONFIGURATION:
                    createPersonConfigurationLink(linkId, link);
                    break;
                case ARTIFACT_CONFIGURATION:
                    createArtifactConfigurationLink(linkId, link);
                    break;
                case COMMITED_CONFIGURATION_CONFIGURATION:
                    createCommittedConfigurationConfigurationLink(linkId, link);
                    break;
                case COMMIT_COMMITED_CONFIGURATION:
                    createCommitCommittedConfigurationLink(linkId, link);
                    break;
            }
        }

    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Commit a CommittedConfiguration
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
    public void createCommitCommittedConfigurationLink(int linkId, Link link){
       int startId = link.getStartIndex();
       int startFormId = identificatorCreater.getCommitFormIndex(startId);
       CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Commit, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);
        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getCommitedConfigurationFormIndex(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);

        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Committed_Configuration,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi CommittedConfiguration Configuration
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
    public void createCommittedConfigurationConfigurationLink(int linkId, Link link){
            int startId = link.getStartIndex();
            int startFormId = identificatorCreater.getCommitedConfigurationFormIndex(startId);
            CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
            linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Committed_Configuration, item.getTranslateX(),
                    item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
            repaintLink(item, startFormId);
            int endId = link.getEndIndex();
            int endFormId = identificatorCreater.getConfigurationFormIndex(endId);
            CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);

        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Artifact a Configuration
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
    public void createArtifactConfigurationLink(int linkId, Link link){
        int startId = link.getStartIndex();
        int startFormId = identificatorCreater.getArtifactIndex(startId);
        CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Artifact, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);
        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getConfigurationFormIndex(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);

        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Person a Configuration
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
    public void createPersonConfigurationLink(int linkId, Link link){
        int startId = link.getStartIndex();
        int startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
        CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);
        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getConfigurationFormIndex(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);

        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Configuration,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Person a Commit
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
    public void createPersonCommitLink(int linkId, Link link){
        int startId = link.getStartIndex();
        int startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
        CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);

        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getCommitFormIndex(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);
        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Commit,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }


    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Person a CommittedConfiguration
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
        public void createPersonCommittedConfigurationLink(int linkId, Link link){
        int startId = link.getStartIndex();
        int startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
        CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);

        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getCommitedConfigurationFormIndex(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);
        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Committed_Configuration,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Metoda pro vytvoreni graficke podoby spojnice mezi Person a Artifact
     * @param linkId idetifikator linku
     * @param link instace linku v datovem modelu
     */
        public void createPersonArtifactLink(int linkId, Link link){
        int startId = link.getStartIndex();
        int startFormId = identificatorCreater.getRoleSegmentIndexToFormMaper().get(startId);
        CanvasItem item = canvasController.getListOfItemOnCanvas().get(startFormId);
        linkControl.ArrowManipulation(false, canvasController, item.getFormIdentificator(), SegmentType.Person, item.getTranslateX(),
                item.getTranslateY(), item.getLength(), item.getHeight(), true, linkId);
        repaintLink(item, startFormId);
        int endId = link.getEndIndex();
        int endFormId = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(endId);
        CanvasItem endItem = canvasController.getListOfItemOnCanvas().get(endFormId);

        linkControl.ArrowManipulation(true, canvasController, endItem.getFormIdentificator(), SegmentType.Artifact,
                endItem.getTranslateX(), endItem.getTranslateY(), endItem.getLength(), endItem.getHeight(),
                true, linkId);
        repaintLink(endItem, endFormId);
        identificatorCreater.setLinkId(linkId);
    }

    /**
     * Geters
     */

    public void setProjectCanvasController(CanvasController dataModelCanvasController) {
        this.canvasController = dataModelCanvasController;
    }


}
