package controllers.formControllers;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Milestone;
import abstractControlPane.ControlPanel;
import abstractform.BasicForm;
import controlPanels.*;
import controllers.ApplicationController;
import controllers.DataPreparer;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import forms.*;
import graphics.canvas.CanvasItem;
import graphics.panels.DragAndDropItemPanel;
import interfaces.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trida predstavujici controller pro rizeni datovych operaci
 * pro prvky na platne a tabulkove elementy a segmentzy
 *
 * @author Václav Janoch
 */
public class FormController {

    /** Tridy datoveho modelu **/
    private DataManipulator dataManipulator;
    private IdentificatorCreater identificatorCreater;
    private SegmentLists segmentLists;
    private DataModel dataModel;
    private ISaveDataModel saveDataModel;

    /** Kolekce pro uchovani formularu, controlnich panelu a prvku na platne **/
    private ArrayList<BasicForm> forms;
    private ArrayList<ControlPanel> controlPanels;
    private Map<Integer, CanvasItem> canvasItemList;

    /** Promenne predstavujic jednotlive tabulkove formulare **/
    private PhaseForm phaseForm;
    private IterationForm iterationForm;
    private ActivityForm activityForm;
    private WorkUnitForm workUnitForm;
    private ChangeForm changeForm;
    private VCSTagForm VCSTagForm;
    private MilestoneForm milestoneForm;
    private ConfigPersonRelationForm CPRForm;
    private PriorityForm priorityForm;
    private SeverityForm severityForm;
    private RelationForm relationForm;
    private ResolutionForm resolutionForm;
    private StatusForm statusForm;
    private BranchForm branchFrom;
    private TypeForm typeForm;
    private CriterionForm criterionForm;
    private RoleTypeForm roleTypeForm;

    /** Ostatni kontrolery vyuzivane ve tride **/
    private ApplicationController applicationController;
    private IFormDataController formDataController;
    private IEditFormController editFormController;
    private IDeleteFormController deleteFormController;
    private FormFillController formFillController;
    private DataPreparer dataPreparer;
    private DrawerPanelController drawerPanelController;
    private SelectItemController selectItemController;
    private CanvasItemController canvasItemController;


    /**
     * Konstruktor tridy
     * Zinicializuje potrebne globalni promenne
     * @param identificatorCreater trida pro vytvareni identifikatoru
     * @param dataModel trida pro praci s datovym modelem
     * @param applicationController trida obsahujici contrllery aplikace
     * @param segmentLists instace tridy uchovaajici prehledove seznamy
     * @param dataPreparer instace tridy pro pripravu dat
     * @param drawerPanelController instance tridy pro praci s drawer panelem
     * @param selectItemController instance tridy pro praci s vyberem dat
     */
    public FormController(IdentificatorCreater identificatorCreater, DataModel dataModel,
                          ApplicationController applicationController, SegmentLists segmentLists, DataPreparer dataPreparer,
                          DrawerPanelController drawerPanelController, SelectItemController selectItemController) {

        this.drawerPanelController = drawerPanelController;
        this.selectItemController = selectItemController;
        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.applicationController = applicationController;
        this.forms = new ArrayList<>();
        this.controlPanels = new ArrayList<>();
        this.canvasItemList = new HashMap<>();

        this.dataModel = dataModel;
        this.saveDataModel = dataModel.getSaveDataModel();

        this.identificatorCreater = identificatorCreater;

    }

    /**
     * Metoda pro vytvoreni instaci jednotlivych tabulkovych formularu
     * @param formDataController instace tridy pro vytvareni novych prvku v datovych strukturach
     * @param editFormController instace tridy pro editaci novych prvku v datovych strukturach
     * @param deleteFormController instace tridy pro delete novych prvku v datovych strukturach
     */
    public void initBasicForms(IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController) {

        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.deleteFormController = deleteFormController;

        controlPanels.add(new ProjectControlPanel("Edit", formDataController, editFormController, this));

        CanvasController canvasController = new CanvasController(CanvasType.Phase, applicationController);
        phaseForm = new PhaseForm(this, formDataController, editFormController, deleteFormController, canvasController,
                new DragAndDropItemPanel(canvasController, Constans.phaseDragTextIndexs, drawerPanelController), SegmentType.Phase, Constans.phaseFormIndex);
        forms.add(phaseForm);

        iterationForm = new IterationForm(this, formDataController, editFormController, deleteFormController, canvasController, new DragAndDropItemPanel(canvasController,
                Constans.iterationDragTextIndexs, drawerPanelController), SegmentType.Iteration, Constans.iterationFormIndex);
        forms.add(iterationForm);

        activityForm = new ActivityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Activity);
        forms.add(activityForm);

        workUnitForm = new WorkUnitForm(this, formDataController, editFormController, deleteFormController, canvasController,
                SegmentType.Work_Unit, Constans.workUnitFormIndex);
        forms.add(workUnitForm);

        milestoneForm = new MilestoneForm(
                this, formDataController, editFormController, deleteFormController, SegmentType.Milestone);
        forms.add(milestoneForm);

        criterionForm = new CriterionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Criterion);
        forms.add(criterionForm);

        CPRForm = new ConfigPersonRelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.Config_Person_Relation);
        forms.add(CPRForm);

        roleTypeForm = new RoleTypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Role_Type);
        forms.add(roleTypeForm);


        priorityForm = new PriorityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Priority);
        forms.add(priorityForm);

        severityForm = new SeverityForm(this, formDataController, editFormController, deleteFormController, SegmentType.Severity);
        forms.add(severityForm);

        relationForm = new RelationForm(this, formDataController, editFormController, deleteFormController, SegmentType.Relation);
        forms.add(relationForm);

        resolutionForm = new ResolutionForm(this, formDataController, editFormController, deleteFormController, SegmentType.Resolution);
        forms.add(resolutionForm);

        statusForm = new StatusForm(this, formDataController, editFormController, deleteFormController, SegmentType.Status);
        forms.add(statusForm);

        typeForm = new TypeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Type);
        forms.add(typeForm);

        branchFrom = new BranchForm(this, formDataController, editFormController, deleteFormController, SegmentType.Branch);
        forms.add(branchFrom);

        changeForm = new ChangeForm(this, formDataController, editFormController, deleteFormController, SegmentType.Change);
        forms.add(changeForm);

        VCSTagForm = new VCSTagForm(this, formDataController, editFormController, deleteFormController, SegmentType.VCSTag);
        forms.add(VCSTagForm);


        forms.add(null);


    }


    /**
     * Metoda pro určení metody pro vytvoření konkrétního segmentu nebo elementu
     * <p>
     * kořenový formulář
     *
     * @return identifikátory objektu pro CanvasItem
     */
    public int createNewForm(SegmentType type, CanvasType canvasType) {

        switch (type) {
            case Configuration:
                return createNewConfigurationPanel();
            case Person:
                return createNewRolePanel();
            case Artifact:
                return createNewArtifactPanel();
            case Commit:
                return createNewCommitPanel();
            case Committed_Configuration:
                return createNewCommitedConfigurationPanel();
            default:
                return -1;
        }

    }

    private int createNewCommitPanel() {
        int formIndex = createNewCommitFormWithoutManipulator(true);
        int id = identificatorCreater.getCommitId(formIndex);
        saveDataModel.createNewCommit(id);
        return formIndex;
    }


    /**
     * Metoda pro vytvoreni objektu predstavuji Commit bez vytvoreni noveho identifikatoru
     * @param exist existence prvku
     * @param id identifikator
     * @param formIndex index formular
     * @return index formular
     */
    public int createNewCommitFormWithoutCreateId(boolean exist, int id, int formIndex) {
        CommitTable commitTable = new CommitTable(String.valueOf(id), "", true, exist, id);

        CommitControlPanel commitControlPanel = new CommitControlPanel(
                "Edit", formDataController, editFormController, this, commitTable, id, formIndex);
        segmentLists.getCommitObservable().add(commitTable);
        controlPanels.add(commitControlPanel);
        return formIndex;

    }


    /**
     * Metoda pro vytvoreni objektu predstavuji Commit bez vyuziti datoveho modleu
     * @param exist existence prvku
     * @return index formular
     */
    public int createNewCommitFormWithoutManipulator(boolean exist) {

        int index = identificatorCreater.createCommitID();
        int id = identificatorCreater.getCommitId(index);
        return createNewCommitFormWithoutCreateId(exist, id, index);
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Committed Configuration bez vytvoreni noveho identifikatoru
     * @param exist existence prvku
     * @param id identifikator
     * @param formIndex index formular
     * @return index formular
     */
    public int createNewCommitedConfigurationFormWithoutCreateId(boolean exist, int id, int formIndex) {
        CommitedConfigurationTable commitTable = new CommitedConfigurationTable(String.valueOf(id), "", exist, id);

        CommitedConfigurationControlPanel commitControlPanel = new CommitedConfigurationControlPanel(
                "Edit", formDataController, editFormController, this, commitTable, id, formIndex);
        segmentLists.getCommitedConfigurationObservable().add(commitTable);
        controlPanels.add(commitControlPanel);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji CommittedConfiguration s vyuziti datoveho modleu
     * @return index formular
     */
    private int createNewCommitedConfigurationPanel() {
        int formIndex = createNewCommitedConfigurationPanelFormWithoutManipulator(true);
        int id = identificatorCreater.getCommitedConfigurationId(formIndex);
        saveDataModel.createNewCommitedConfiguration(id);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici CommittedConfiguration bez vyuziti datoveho modleu
     * @param exist existence prvku
     * @return index formular
     */
    public int createNewCommitedConfigurationPanelFormWithoutManipulator(boolean exist) {

        int index = identificatorCreater.createCommiedConfigurationtID();
        int id = identificatorCreater.getCommitedConfigurationId(index);
        return createNewCommitedConfigurationFormWithoutCreateId(exist, id, index);
    }
    /**
     * Metoda pro vytvoreni objektu predstavujici Role s vyuzitim datoveho modelu
     * @return index formular
     */
    private int createNewRolePanel() {
        int formIndex = createNewRoleFormWithoutManipulator(true);
        int id = identificatorCreater.getRoleIndexToIdMaper().get(formIndex);
        saveDataModel.createNewPerson(id);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici Person bez vytvoreni noveho identifikatoru
     * @param exist existence prvku
     * @param id identifikator
     * @param formIndex index formular
     * @return index formular
     */
    public int createNewPersonFormWithoutCreateId(boolean exist, int id, int formIndex) {

        PersonTable personTable = new PersonTable(String.valueOf(id), exist, id);

        PersonControlPanel personControlPanel = new PersonControlPanel(
                "Edit", formDataController, editFormController, this, personTable, id, formIndex);

        segmentLists.getPersonObservable().add(personTable);
        controlPanels.add(personControlPanel);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici Person bez vyuziti datoveho modleu
     * @param exist existence prvku
     * @return index formular
     */
    public int createNewRoleFormWithoutManipulator(boolean exist) {

        int index = identificatorCreater.createRoleID();
        int id = identificatorCreater.getRoleIndexToIdMaper().get(index);
        return createNewPersonFormWithoutCreateId(exist, id, index);
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Artifact s vyuziti datoveho modleu
     * @return index formular
     */
    private int createNewArtifactPanel() {
        int formIndex = createNewArtifactFormWithoutManipulator("", "", true);
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(formIndex);
        saveDataModel.createNewArtifact(id);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici Artifact bez vytvoreni noveho identifikatoru
     * @param exist existence prvku
     * @param id identifikator
     * @param formIndex index formular
     * @return index formular
     */
    public int createNewArtifactFormWithoutCreateId(String name, boolean exist, int id, int formIndex) {

        ArtifactTable artifactTable = new ArtifactTable(String.valueOf(id), exist, id);

        ArtifactControlPanel artifactControlPanel = new ArtifactControlPanel(
                "Edit", formDataController, editFormController, this, artifactTable, id, formIndex);

        segmentLists.getArtifactObservable().add(artifactTable);
        controlPanels.add(artifactControlPanel);
        return formIndex;

    }

    public int createNewArtifactFormWithoutManipulator(String name, String description, boolean exist) {
        int index = identificatorCreater.createArtifactID();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(index);
        return createNewArtifactFormWithoutCreateId(name, exist, id, index);

    }
    /**
     * Metoda pro vytvoreni objektu predstavuji Change s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewChangeForm(int id) {
        saveDataModel.createNewChange(id);

        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Configuration s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewConfigurationPanel() {
        int formIndex = createNewConfiguratioFormWithoutManipulator("", true);
        int id = identificatorCreater.getConfigurationId(formIndex);
        saveDataModel.createNewConfiguration(id);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici Configuration bez vytvoreni noveho identifikatoru
     * @param exist existence prvku
     * @param id identifikator
     * @param formIndex index formular
     * @return index formular
     */
    public int createNewConfigurationFormWithoutCreateId(String name, boolean exist, int id, int formIndex) {

        ConfigTable configTable = new ConfigTable(String.valueOf(id), "", formIndex, exist, id);

        ConfigurationControlPanel roleControlPanel = new ConfigurationControlPanel(
                "Edit", formDataController, editFormController, this, configTable, id, formIndex);

        segmentLists.getConfigObservable().add(configTable);
        controlPanels.add(roleControlPanel);
        return formIndex;
    }

    /**
     * Metoda pro vytvoreni objektu predstavujici Configuration bez vyuziti datoveho modelu
     * @param exist existence prvku
     * @return index formular
     */
    public int createNewConfiguratioFormWithoutManipulator(String name, boolean exist) {
        int index = identificatorCreater.createConfigurationID();
        int id = identificatorCreater.getConfigurationId(index);

        return createNewConfigurationFormWithoutCreateId(name, exist, id, index);
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Work Unit s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewWorkUnitForm(int id) {
        saveDataModel.createNewWorkUnit(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Activity s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewActivityForm(int id) {

        saveDataModel.createNewActivity(id);

        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Phase s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewPhaseForm(int id) {
        saveDataModel.createNewPhase(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Branch s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewBranchForm(int id) {
        saveDataModel.createNewBranch(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Milestone s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewMilestoneForm(int id) {
        saveDataModel.createNewMilestone(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji CPR s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewCPRForm(int id) {
        saveDataModel.createNewCPR(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji VCSTag s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    private int createNewVCSTagForm(int id) {
        saveDataModel.createNewVCSTag(id);
        return id;
    }


    /**
     * Metoda pro vytvoreni objektu predstavuji Criterion s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewCriterionForm(int id) {
        saveDataModel.createNewCriterion(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Priority s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewPriorityForm(int id) {
        saveDataModel.createNewPriority(id);
        return id;
    }
    /**
     * Metoda pro vytvoreni objektu predstavuji Severity s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewSeverityForm(int id) {
        saveDataModel.createNewSeverity(id);
        return id;
    }
    /**
     * Metoda pro vytvoreni objektu predstavuji Relation s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewRelationForm(int id) {
        saveDataModel.createNewRelation(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Resolution s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewResolutionForm(int id) {
        saveDataModel.createNewResolution(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Status s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewStatusForm(int id) {
        saveDataModel.createNewStatus(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Type s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewTypeForm(int id) {
        saveDataModel.createNewType(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Role Type s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    public int createNewRoleTypeForm(int id) {
        saveDataModel.createNewRoleType(id);
        return id;
    }

    /**
     * Metoda pro vytvoreni objektu predstavuji Iteration s vyuziti datoveho modleu
     * @return  identifikator prvku
     */
    int createNewIterationForm(int id) {

        saveDataModel.createNewIteration(id);

        return id;
    }

    /**
     * Metoda pro rozhodnuti o tom ktery formular bude zobrazen
     * @param formIdentificator identifikator formulare
     */
    public void showForm(int formIdentificator) {
        BasicForm form = forms.get(formIdentificator);
        switch (form.getSegmentType()) {
            case Milestone:

                List<Milestone> milestones = dataModel.getMilestones();
                MilestoneForm milestoneForm = (MilestoneForm) form;
                TableView tableView = milestoneForm.getTableTV();
                tableView.getItems().clear();
                for (int i = 0; i < milestones.size(); i++) {
                    Milestone milestone = milestones.get(i);
                    tableView.getItems().add(dataPreparer.prepareMilestoneTable(milestone.getName().get(0), milestone.getId()));

                }
                tableView.sort();
                tableView.refresh();
                break;

            case Config_Person_Relation:
                List<ConfigPersonRelation> cprlist = dataModel.getConfigPersonRelations();
                ConfigPersonRelationForm cprForm = (ConfigPersonRelationForm) form;
                TableView cprTableView = cprForm.getTableTV();
                cprTableView.getItems().clear();
                for (int i = 0; i < cprlist.size(); i++) {
                    ConfigPersonRelation cpr = cprlist.get(i);
                    CPRTable cprTable = dataPreparer.prepareCPRTable(cpr.getName().get(0), cpr.getId());
                    cprTableView.getItems().add(cprTable);

                }
                cprTableView.sort();
                cprTableView.refresh();
                break;
            default:

        }


    }

    /**
     * Metoda pro ziskani souradnic prvku umisteneho na modelovacim platne
     * @param indexForm identifikator formulare
     * @return pole souradnic
     */
    public int[] getCoordsFromItem(int indexForm) {

        CanvasItem item = canvasItemList.get(indexForm);
        int x = (int) item.getTranslateX();
        int y = (int) item.getTranslateY();
        int[] coords = {x, y};
        return coords;
    }

    /**
     * Metoda pro nastaveni jmena do instace prvku na platne
     * @param index identifikator prvku
     * @param name jmeno pro nastaveni do prvku
     */
    public void setNameToItem(int index, String name) {

        CanvasItem item = canvasItemList.get(index);
        item.setNameText(name);
    }

    /**
     * Metoda pro nastaveni poctu instaci do ikonky prvku na platne
     * @param index identifikator formulare
     * @param instanceCount pocet instanci
     * @param countIndicator identifikator ukazatele nerovnosti
     */
    public void setItemInstanceCount(int index, int instanceCount, int countIndicator) {
        CanvasItem item = canvasItemList.get(index);
        canvasItemController.setInstanceCount(item, instanceCount, countIndicator);
    }

    /**
     * Metoda pro vyjmuti prvku z platna
     * @param id identifikator prvku
     */
    public void removeCanvasItemFromList(int id) {
        canvasItemList.put(id, null);
    }

    /**
     * Metoda pro pridani prvku do platna
     * @param formIndex identifikator formulare
     * @param item instace prvku platna
     */
    public void addCanvasItemToList(int formIndex, CanvasItem item) {
        canvasItemList.put(formIndex, item);
    }

    /**
     * Metoda pro nastaveni barvky prvku v zavislosti na existenci instace
     * @param indexForm identifikator formulare
     * @param isExist informace o existenci
     */
    public void setItemColor(int indexForm, boolean isExist) {

        CanvasItem item = canvasItemList.get(indexForm);
        if (!isExist) {
            item.getSegmentInfo().setRectangleColor(Constans.nonExistRectangleBorderColor);
        } else {
            item.getSegmentInfo().setRectangleColor(Constans.rectangleBorderColor);
        }

    }

    /**
     * Metoda pro ziskani identifikatoru z identifikatoru formulare
     * @param type instace SegmentType
     * @param formIndex identifikator formulare
     * @return identifikator v datovem modelu
     */
    public int getSegmetIdFromFormId(SegmentType type, int formIndex) {

        switch (type) {
            case Person:
                return identificatorCreater.getRoleId(formIndex);
            case Iteration:
                return identificatorCreater.getIterationId(formIndex);
            case Activity:
                return identificatorCreater.getActivityId(formIndex);
            case Committed_Configuration:
                return identificatorCreater.getCommitedConfigurationId(formIndex);
            case Configuration:
                return identificatorCreater.getConfigurationId(formIndex);
            case Commit:
                return identificatorCreater.getCommitId(formIndex);
            case Artifact:
                return identificatorCreater.getArtifactId(formIndex);
            default:
                return -1;
        }
    }


    /**
     * Metoda pro vytvoreni tabulkoveho prvku
     * @param segmentType instace prvku Segment Type s informaci o typu segmentu
     * @return identifikator instace
     */
    public int createTableItem(SegmentType segmentType) {
        int id;
        switch (segmentType) {
            case Work_Unit:
                id = identificatorCreater.createWorkUnitID();
                createNewWorkUnitForm(id);
                return id;
            case VCSTag:
                id = identificatorCreater.createVCSTagID();
                createNewVCSTagForm(id);
                return id;
            case Iteration:
                id = identificatorCreater.createIterationID();
                createNewIterationForm(id);
                return id;
            case Activity:
                id = identificatorCreater.createActivityID();
                createNewActivityForm(id);
                return id;
            case Change:
                id = identificatorCreater.createChangeID();
                createNewChangeForm(id);
                return id;
            case Phase:
                id = identificatorCreater.createPhaseID();
                createNewPhaseForm(id);
                return id;
            case Branch:
                id = identificatorCreater.createBranchID();
                createNewBranchForm(id);
                return id;
            case Priority:
                id = identificatorCreater.createPriorityID();
                createNewPriorityForm(id);
                return id;
            case Severity:
                id =  identificatorCreater.createSeverityID();
                createNewSeverityForm(id);
                return id;
            case Milestone:
                id = identificatorCreater.createMilestoneID();
                createNewMilestoneForm(id);
                return id;
            case Criterion:
                id = identificatorCreater.createCriterionID();
                createNewCriterionForm(id);
                return id;
            case Person:
                return identificatorCreater.createRoleID();
            case Role_Type:
                id = identificatorCreater.createRoleTypeID();
                createNewRoleTypeForm(id);
                return id;
            case Config_Person_Relation:
                id = identificatorCreater.createCPRID();
                createNewCPRForm(id);
                return id;
            case Relation:
                id = identificatorCreater.createRelationID();
                createNewRelationForm(id);
                return id;
            case Resolution:
                id = identificatorCreater.createResolutionID();
                createNewResolutionForm(id);
                return id;
            case Status:
                id = identificatorCreater.createStatusID();
                createNewStatusForm(id);
                return id;
            case Type:
                id = identificatorCreater.createTypeID();
                createNewTypeForm(id);
                return id;
            case Tag:
                return identificatorCreater.createTagID();
            default:
                return -1;
        }
    }

    public ArrayList<BasicForm> getForms() {
        return forms;
    }


    /**
     * Metoda pro zobrazeni editacniho Drawer panelu v zavislosti na panelu
     * @param editControlPanel instace tridy ControlPanel predstavujici editacni panel
     */
    public void showEditControlPanel(ControlPanel editControlPanel) {
        drawerPanelController.showRightPanel(editControlPanel);
    }

    /**
     * Metoda pro nastaveni dat do drawer panelu v zavisloti na identifikatoru
     * @param formIndex identifikator formulare
     */
    public void showEditControlPanel(int formIndex) {

        ControlPanel panel = controlPanels.get(formIndex);
        IControlPanel controlPanel = (IControlPanel) panel;
        controlPanel.showEditControlPanel();
        drawerPanelController.showRightPanel(panel);
    }

    /**
     * Metoda pro nastaveni souradnic prvkum na platne
     * @param segmentType Typ prvku kteremu se budou souradnic nastavovat
     * @param newTranslateX x souradnice
     * @param newTranslateY y souradnice
     * @param fromIndex identifikator formulare
     */
    public void setCoordinatesToCanvasItem(SegmentType segmentType, double newTranslateX, double newTranslateY, int fromIndex) {
        int id = 0;
        switch (segmentType) {
            case Person:
                id = identificatorCreater.getRoleId(fromIndex);
                editFormController.editCoordsInRole(newTranslateX, newTranslateY, id);
                break;
            case Commit:
                id = identificatorCreater.getCommitId(fromIndex);
                editFormController.editCoordsInCommit(newTranslateX, newTranslateY, id);
                break;

            case Committed_Configuration:
                id = identificatorCreater.getCommitedConfigurationId(fromIndex);
                editFormController.editCoordsInCommitedConfiguration(newTranslateX, newTranslateY, id);
                break;
            case Configuration:
                id = identificatorCreater.getConfigurationId(fromIndex);
                editFormController.editCoordsInConfiguration(newTranslateX, newTranslateY, id);
                break;
            case Artifact:
                id = identificatorCreater.getArtifactIndexToIdMaper().get(fromIndex);
                editFormController.editCoordsInArtifact(newTranslateX, newTranslateY, id);
                break;
        }
    }

    /**
     * Metoda pro nalezi spravneho poradi identifikatoru spojnice
     * @param startIndex identifikator prvniho prvku jako pocatecni
     * @param endIndex identifikator prvniho prvku jako koncovi
     * @param startIndex1 identifikator druheho prvku jako pocatecni
     * @param endIndex1 identifikator druheho prvku jako koncovi
     * @return pole identifikatoru ve spravne poradi
     */
    private Integer[] findCorectId(Integer startIndex, Integer endIndex, Integer startIndex1, Integer endIndex1) {
        Integer[] result = new Integer[2];

        Integer firstResult = -1;
        Integer secondResult = -1;

        if (startIndex != null) {
            firstResult = startIndex;
        } else {
            firstResult = endIndex;
        }

        if (startIndex1 != null) {
            secondResult = startIndex1;
        } else {
            secondResult = endIndex1;
        }
        result[0] = firstResult;
        result[1] = secondResult;
        return result;
    }

    /**
     * Metoda pro vytvoreni relace mezi Commit a Committed Configuration
     * @param linkId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId, boolean isXML) {
        Integer[] result = findResultsFromCommitToCommitedConfigurationRelation(startId, endId);

        formDataController.createCommitToCommitedConfigurationRelation(linkId, result[0], result[1], isXML);
    }

    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Commit a Committed Configuration
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromCommitToCommitedConfigurationRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getCommitId(startSegmentId);
        Integer endIndex = identificatorCreater.getCommitId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getCommitedConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getCommitedConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    /**
     * Metoda pro vytvoreni relace mezi Committed Configuration a Configuration
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createCommitedConfigurationToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromCommitedConfigurationToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createCommitedConfigurationToConfigurationRelation(linkId, result[0], result[1], isXML);

    }
    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Commited Configuration a Configuration
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromCommitedConfigurationToConfigurationRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getCommitedConfigurationId(startSegmentId);
        Integer endIndex = identificatorCreater.getCommitedConfigurationId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);
    }


    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Artifact a Configuration
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromArtifactToConfigurationRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getArtifactId(startSegmentId);
        Integer endIndex = identificatorCreater.getArtifactId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);
    }

    /**
     * Metoda pro vytvoreni relace mezi Artifact a Configuration Configuration
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createArtifactToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromArtifactToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createArtifactToConfigurationRelation(linkId, result[0], result[1], isXML);

    }

    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Person a Artifact
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromPersonToArtifactRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getArtifactId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getArtifactId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Person a Commit
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromPersonToCommitRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getCommitId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getCommitId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Person a CommittedConfiguration
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromPersonToCommittedConfigurationRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getCommitedConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getCommitedConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    /**
     * Metoda pro ziskani spravneho poradi identifikatoru mezi Person a Configuration
     * @param startSegmentId pocatecni identifikator
     * @param endSegmentId koncovy identifikator
     * @return spravne poradi identifikatoru
     */
    public Integer[] findResultsFromPersonToConfigurationRelation(int startSegmentId, int endSegmentId) {
        Integer startIndex = identificatorCreater.getRoleId(startSegmentId);
        Integer endIndex = identificatorCreater.getRoleId(endSegmentId);

        Integer startIndex1 = identificatorCreater.getConfigurationId(startSegmentId);
        Integer endIndex2 = identificatorCreater.getConfigurationId(endSegmentId);

        return findCorectId(startIndex, endIndex, startIndex1, endIndex2);

    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Configuration Configuration
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createRoleToConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToConfigurationRelation(linkId, result[0], result[1], isXML);

    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Artifact
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createRoleToArtifactRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToArtifactRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToArtifactRelation(linkId, result[0], result[1], isXML);

    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Commit
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createRoleToCommitRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToCommitRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToCommitRelation(linkId, result[0], result[1], isXML);

    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Committed Configuration
     * @param linkId identifikator spojnice
     * @param startSegmentId identifikator pocatecniho prvku
     * @param endSegmentId identifikator koncoveho prvku
     * @param isXML informace zde je relace vytvarena z XML
     */
    public void createRoleToCommttedConfigurationRelation(int linkId, int startSegmentId, int endSegmentId, boolean isXML) {

        Integer[] result = findResultsFromPersonToCommittedConfigurationRelation(startSegmentId, endSegmentId);
        formDataController.createNewPersonToCommittedConfigurationRelation(linkId, result[0], result[1], isXML);

    }


/** Getters and Setters **/

    public void setFormFillController(FormFillController formFillController) {
        this.formFillController = formFillController;
    }

    public Map<Integer, CanvasItem> getCanvasItemList() {
        return canvasItemList;
    }

    public ObservableList<BasicTable> getCriterionObservable() {
        return segmentLists.getCriterionObservable();
    }

    public ObservableList<BasicTable> getRoleObservable() {
        return segmentLists.getPersonObservable();
    }

    public ObservableList<BasicTable> getRoleTypeObservable() {
        return segmentLists.getRoleTypeObservable();
    }

    public BasicForm getForm(int formiIndex) {
        return forms.get(formiIndex);
    }

    public SegmentLists getSegmentLists() {
        return segmentLists;
    }

    public void setCanvasItemController(CanvasItemController canvasItemController) {
        this.canvasItemController = canvasItemController;
    }

    public ArrayList<ControlPanel> getControlPanels() {
        return controlPanels;
    }
}

