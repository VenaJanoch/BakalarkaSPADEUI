package controllers;

import forms.ConfigurationTableForm;
import graphics.CanvasItem;
import interfaces.IEditDataModel;
import interfaces.IFormDataController;
import interfaces.ISaveDataModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.time.LocalDate;
import java.util.*;

public class FormDataController implements IFormDataController {

    private SegmentLists lists;
    private DataManipulator dataManipulator;
    private ISaveDataModel saveDataModel;
    private IEditDataModel editDataModel;
    private DataModel dataModel;
    private FormController formController;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DataPreparer dataPreparer;


    public FormDataController(FormController formController, SegmentLists lists, MapperTableToObject mapperTableToObject, DataModel dataModel,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer){
        this.formController = formController;
        this.lists = lists;
        this.dataModel = dataModel;
        this.saveDataModel = dataModel.getSaveDataModel();
        this.dataManipulator = dataModel.getDataManipulator();
        this.editDataModel = dataModel.getEditDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.dataPreparer = dataPreparer;
    }

    

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int phaseId = identificatorCreater.getPhaseId(indexForm);

        int[] coords = formController.getCoordsFromItem(indexForm);
        int milestoneIndexForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        int configurationIndexFromManipulator = dataPreparer.prepareIndexForManipulator(confIndex);

        editDataModel.editDataInPhase(nameForManipulator, endDateL, descriptionForManipulator, configurationIndexFromManipulator,
               milestoneIndexForManipulator , coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), phaseId);
        formController.setNameToItem(indexForm, nameForManipulator);

        String segmentId = formController.getSegmentIdentificator(indexForm);
        mapperTableToObject.mapTableToPhase(milestoneIndexForManipulator, configurationIndexFromManipulator, segmentId , phaseId);

        return true;
    }



    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        int configurationIdForManipulator = dataPreparer.prepareIndexForManipulator(chooseConfigID);
        int iterationId = identificatorCreater.getIterationId(indexForm);

        editDataModel.editDataInIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, configurationIdForManipulator ,
                coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), identificatorCreater.getIterationId(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);

        String segmentId = formController.getSegmentIdentificator(indexForm);
        mapperTableToObject.mapTableToObject(SegmentType.Iteration, configurationIdForManipulator, new TableToObjectInstanc(segmentId, iterationId, SegmentType.Iteration));
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int[] coords = formController.getCoordsFromItem(indexForm);

        editDataModel.editDataInActivity(nameForManipulator, descriptionForManipulator, coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(mapOfItemOnCanvas.keySet()),
                identificatorCreater.getActivityId(indexForm));
        String segmentId = formController.getSegmentIdentificator(indexForm);
        formController.setNameToItem(indexForm, segmentId);
        return true;

    }

    public boolean saveDataFromWorkUnit(String actName,String description, String category, int assigneIndex, int authorIndex, int priorityIndex,int severityIndex,
                                        int typeIndex, int resolutionIndex, int statusIndex, String estimated, boolean selected, int indexForm, CanvasType canvasType) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String categoryForManipulator = InputController.fillTextMapper(category);
        String descriptionForManipulator = InputController.fillTextMapper(description);

        int[] coords = formController.getCoordsFromItem(indexForm);

        Double estimateForDataManipulator = -1.0;

            if (!estimated.equals("")) {
                estimateForDataManipulator = InputController.isDoubleNumber(estimated);
                if(estimateForDataManipulator == null){
                    return false;
                }
            }

        boolean isProjectCanvas = false;
        if(canvasType.equals(CanvasType.Project)){
            isProjectCanvas = true;
        }

        formController.setItemColor(indexForm, selected);

        int assigneForManipulator = dataPreparer.prepareIndexForManipulator(assigneIndex);
        int authorForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);
        int priorityForManipulator = dataPreparer.prepareIndexForManipulator(priorityIndex);
        int severityForManipulator = dataPreparer.prepareIndexForManipulator(severityIndex);
        int typeForManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);
        int resolutionForManipulator = dataPreparer.prepareIndexForManipulator(resolutionIndex);
        int statusForManipulator = dataPreparer.prepareIndexForManipulator(statusIndex);
        editDataModel.editDataInWorkUnit(nameForManipulator, descriptionForManipulator ,categoryForManipulator, assigneForManipulator, authorForManipulator,
                priorityForManipulator ,severityForManipulator ,typeForManipulator,resolutionForManipulator , statusForManipulator , coords[0], coords[1],
                estimateForDataManipulator, selected, identificatorCreater.getWorkUnitIndex(indexForm), isProjectCanvas);
        formController.setNameToItem(indexForm, nameForManipulator);
        String segmentId = formController.getSegmentIdentificator(indexForm);
        mapperTableToObject.mapTableToWU(assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator, typeForManipulator,
                resolutionForManipulator, statusForManipulator, indexForm, segmentId);
        return  true;
    }

    public boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                          ArrayList<Integer> cprIndex, Map<Integer,CanvasItem> itemIndexList, boolean isNew, int indexForm) {


        String nameForManipulator = InputController.fillTextMapper(actName);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();
        authorIndex = dataPreparer.prepareIndexForManipulator(authorIndex);
        branchIndex = dataPreparer.prepareIndicesForManipulator(branchIndex);
        cprIndex = dataPreparer.prepareIndicesForManipulator(cprIndex);

        for(int index : itemIndexList.keySet()) {
            if(identificatorCreater.getChangeIndexMaper().get(index) != null){
                changeList.add(identificatorCreater.getChangeIndexMaper().get(index));
            }else {
                artefactList.add(identificatorCreater.getArtifactIndexMaper().get(index));
            }
        }
        String release = "NO";
        if(isRelease){
            release = "YES";
        }
        int configIndex = identificatorCreater.getConfigurationId(indexForm);
        editDataModel.editDataInConfiguration(nameForManipulator, createDate, isRelease, authorIndex , branchIndex,cprIndex,
                artefactList, changeList, configIndex );
        String idName = identificatorCreater.getConfigurationId(indexForm) + "_" + actName;
        ConfigTable configTable = new ConfigTable(idName, release, indexForm, configIndex);
        if (isNew){
            lists.getConfigObservable().add(configTable);
            formController.setNewItemToConfigurationTable(idName, release, indexForm, configIndex);
        }else{
            lists.getConfigObservable().remove(configIndex + 1);
            lists.getConfigObservable().add(configIndex + 1, configTable);
            setEditItemInConfigurationTable(configTable);
        }

        formController.setConfigurationFormToTableForm();
        ArrayList<Integer> branchIndicies = dataModel.getBranchIndices(branchIndex);
        ArrayList<Integer> cprIndicies = dataModel.getCPRIndices(cprIndex);
        int roleIndex = dataModel.getRoleId(authorIndex);
        mapperTableToObject.mapTableToConfiguration(roleIndex, branchIndicies, cprIndicies, idName, configIndex);
        return true;
    }

    public void setEditItemInConfigurationTable(ConfigTable configTable) {

        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) formController.getForms().get(Constans.configurationFormIndex);
        TableView<ConfigTable> configTableTableView = configurationTableForm.getTableTV();
        int id = configTableTableView.getSelectionModel().getSelectedIndex();
        configTableTableView.getItems().remove(id);
        configurationTableForm.getTableTV().getItems().add(id,configTable);
        configurationTableForm.getTableTV().sort();

    }


    public boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        formController.setItemColor(indexForm, selected);
        editDataModel.editDataInChange(nameForManipulator, descForManipulator, coords[0], coords[1], selected, identificatorCreater.getChangeId(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }

    public boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                        int typeIndex, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        formController.setItemColor(indexForm, selected);

        editDataModel.editDataInArtifact(nameForManipulator, descForManipulator, createdDate, selected, coords[0], coords[1],
                dataPreparer.prepareIndexForManipulator(authorIndex), typeIndex, identificatorCreater.getArtifactIndex(indexForm));
        lists.getArtifactObservable().add(actName);
        formController.setNameToItem(indexForm, nameForManipulator);
        return  true;
    }
    public void saveDataFromBranch(String nameST, BranchTable branchTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);

        saveDataModel.createNewBranch(nameForManipulator, branchTable.getId(), branchTable.isMainBool());
        lists.getBranchObservable().add(branchTable);
    }





    public void saveDataFromCPR(String nameST, int roleListIndex, CPRTable cprTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        int roleIndexFormManipulator = dataPreparer.prepareIndexForManipulator(roleListIndex);
        saveDataModel.createNewCPR(nameForManipulator, roleIndexFormManipulator, cprTable.getId());
        lists.getCPRObservable().add(cprTable);

        int roleId = dataModel.getRoleId(roleIndexFormManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.ConfigPersonRelation, roleId,
                new TableToObjectInstanc(cprTable.getName(), cprTable.getId(), SegmentType.ConfigPersonRelation));

    }

    public void saveDataFromCriterionForm(String nameST, CriterionTable criterionTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        saveDataModel.createNewCriterion(nameForManipulator, descForManipulator, criterionTable.getId());
        lists.getCriterionObservable().add(criterionTable);
    }

    public void saveDataFromMilestoneForm(String nameST, String description, ArrayList<Integer> criterionIndex, MilestoneTable milestoneTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(description);
        criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        saveDataModel.createNewMilestone(nameForManipulator,descForManipulator, criterionIndex, milestoneTable.getId());
        lists.getMilestoneObservable().add(milestoneTable);
        ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
        mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getName(), milestoneTable.getId(),
                SegmentType.Milestone));
}

    public void saveDataFromPriority(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewPriority(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getPriorityTypeObservable().add(tableItem);
    }

    public void saveDataFromSeverity(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewSeverity(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getSeverityTypeObservable().add(tableItem);
    }

    public void saveDataFromResolutionForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewResolution(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getResolutionTypeObservable().add(classTable);
    }

    public void saveDataFromRelationForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewRelation(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getRelationTypeObservable().add(classTable);
    }

    public void saveDataFromRoleForm(String nameST, int typeIndex, RoleTable roleTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(roleTable.getDescription());
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);

        saveDataModel.crateNewRole(nameForManipulator, descForManipulator, typeFormManipulator, roleTable.getId());
        lists.getRoleObservable().add(roleTable);

        int roleTypeIndex = dataModel.getRoleTypeIndex(typeFormManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Role, roleTypeIndex, new TableToObjectInstanc(roleTable.getName(), roleTable.getId(), SegmentType.Role));
}

    public void saveDataFromRoleTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewRoleType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(),classTable.getId());
        lists.getRoleTypeObservable().add(classTable);
    }

    public void saveDataFromTagForm(String tag, int configId, int id) {
        String tagForManipulator = InputController.fillTextMapper(tag);

        saveDataModel.addTagToConfiguration(tagForManipulator, configId, id);
    }

    public void saveDataFromStatusForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.crateNewStatus(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getStatusTypeObservable().add(classTable);
    }

    public void saveDataFromTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getTypeObservable().add(classTable);
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        dataModel.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }

    public MilestoneTable prepareMilestoneToTable(String nameST, String description, int id, ArrayList criterionArray) {
     return   dataPreparer.prepareMilestoneTable(nameST, description, id, dataPreparer.prepareIndicesForManipulator(criterionArray), lists.getCriterionObservable());
    }

    public RoleTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex) {
        return   dataPreparer.prepareRoleTable(nameST, description, id, dataPreparer.prepareIndexForManipulator(roleTypeIndex),
                lists.getRoleTypeObservable());
    }

    public CPRTable prepareCPRToTable(String nameST, int roleIndex, int id) {
        return dataPreparer.prepareCPRTable(nameST, dataPreparer.prepareIndexForManipulator(roleIndex), id, lists.getRoleObservable());
    }

    public BranchTable prepareBranchToTable(String nameST, boolean main, int id) {
        return dataPreparer.prepareBranchTable(nameST, main, id);
    }

    public String[] getCriterionData(int id) {
        return dataManipulator.getCriterionData(id);
    }

    public String[] getMilestoneStringData(int id) {
        return dataManipulator.getMilestoneData(id);
    }

    public List getCriterionFromMilestone(int id) {
        List<Integer> criterion = dataManipulator.getCriterionFromMilestone(id);

        return dataPreparer.prepareIndiciesForForm(criterion);
    }

    public String[] getRoleStringData(int id) {
        return dataManipulator.getRoleData(id);
    }

    public String[] getClassStringData(SegmentType segmentType, int id) {
        switch (segmentType){
            case RoleType:
                    return dataManipulator.getRoleTypeData(id);
            case Severity:
                return dataManipulator.getSeverityData(id);
            case Priority:
                return dataManipulator.getPriorityData(id);
            case Status:
                return dataManipulator.getStatusData(id);
            case Type:
                return dataManipulator.getTypeData(id);
            case Relation:
                return dataManipulator.getRelationData(id);
            case Resolution:
                return dataManipulator.getResolutionData(id);
            default:
                return null;
        }
    }


    public ObservableList<BasicTable> getRoleList() {
        return lists.getRoleObservable();
    }

    public String[] getCPRStringData(int id) {
        return dataManipulator.getCPRData(id);
    }


    public String[] getBranchStringData(int id) {
        return dataManipulator.getBranchStringData(id);
    }


    public String getTagData(int id, int configFormId) {
        return dataManipulator.getTagStringData(id, identificatorCreater.getConfigurationId(configFormId));
    }


}
