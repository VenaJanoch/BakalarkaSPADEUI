package Controllers;

import forms.MilestoneForm;
import forms.RoleForm;
import graphics.CanvasItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.time.LocalDate;
import java.util.*;

public class FormDataController {

    private FormControl formControl;
    private ApplicationController applicationController;
    private DeleteControl deleteControl;
    private SegmentLists lists;
    private DataManipulator dataManipulator;
    private FormController formController;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DataPreparer dataPreparer;


    public FormDataController(FormController formController, DeleteControl deleteControl,
                       SegmentLists lists, MapperTableToObject mapperTableToObject, DataManipulator dataManipulator,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer){
        this.formController = formController;
        this.formControl = new FormControl();
        this.deleteControl = deleteControl;
        this.lists = lists;
        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.dataPreparer = dataPreparer;

    }

    

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int phaseIndex = identificatorCreater.getPhaseIndex(indexForm);

        int[] coords = formController.getCoordsFromItem(indexForm);
        int milestoneIndexForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        dataManipulator.addDataToPhase(nameForManipulator, endDateL, descriptionForManipulator, dataPreparer.prepareIndexForManipulator(confIndex),
               milestoneIndexForManipulator , coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), phaseIndex);
       formController.setNameToItem(indexForm, nameForManipulator);

       mapperTableToObject.mapTableToObject(SegmentType.Phase, milestoneIndexForManipulator, new TableToObjectInstanc(actName, phaseIndex,
                SegmentType.Phase));
        return true;
    }



    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, dataPreparer.prepareIndexForManipulator(chooseConfigID),
                coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), identificatorCreater.getIterationIndex(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToActivity(nameForManipulator, descriptionForManipulator, coords[0], coords[1], dataPreparer.prepareCanvasItemIndexForManipulator(mapOfItemOnCanvas.keySet()),
                identificatorCreater.getActivityIndex(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
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

        dataManipulator.addDataToWorkUnit(nameForManipulator, descriptionForManipulator ,categoryForManipulator, dataPreparer.prepareIndexForManipulator(assigneIndex),
                dataPreparer.prepareIndexForManipulator(authorIndex), dataPreparer.prepareIndexForManipulator(priorityIndex), dataPreparer.prepareIndexForManipulator(severityIndex),
                dataPreparer.prepareIndexForManipulator(typeIndex), dataPreparer.prepareIndexForManipulator(resolutionIndex), dataPreparer.prepareIndexForManipulator(statusIndex),
                coords[0], coords[1], estimateForDataManipulator, selected, identificatorCreater.getWorkUnitIndex(indexForm), isProjectCanvas);
        formController.setNameToItem(indexForm, nameForManipulator);

        mapperTableToObject.mapTableToWU(assigneIndex, authorIndex, priorityIndex, severityIndex, typeIndex, resolutionIndex, statusIndex, indexForm, nameForManipulator);
        return  true;
    }

    public boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                          ArrayList<Integer> cprIndex, Map<Integer,CanvasItem> itemIndexList, boolean isNew, int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();

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
        dataManipulator.addDataToConfiguration(nameForManipulator, createDate, isRelease, dataPreparer.prepareIndexForManipulator(authorIndex),
                dataPreparer.prepareIndicesForManipulator(branchIndex), dataPreparer.prepareIndicesForManipulator(cprIndex), artefactList, changeList, identificatorCreater.getConfigurationIndex(indexForm));
        int configIndex = identificatorCreater.getConfigurationIndex(indexForm);
        String idName = identificatorCreater.getConfigurationIndex(indexForm) + "_" + actName;

        if (isNew){
            lists.getConfigObservable().add(idName);
            formController.setNewItemToConfigurationTable(idName, release, indexForm);
        }else{
            lists.getConfigObservable().remove(configIndex + 1);
            lists.getConfigObservable().add(configIndex + 1, idName);
            formController.setEditItemInConfigurationTable(idName, release, indexForm, configIndex);
        }

        formController.setConfigurationFormToTableForm();
        return true;
    }


    public boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        formController.setItemColor(indexForm, selected);
        dataManipulator.addDataToChange(nameForManipulator, descForManipulator, coords[0], coords[1], selected, identificatorCreater.getChangeIndex(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }

    public boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                        int typeIndex, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        formController.setItemColor(indexForm, selected);

        dataManipulator.addDataToArtifact(nameForManipulator, descForManipulator, createdDate, selected, coords[0], coords[1],
                dataPreparer.prepareIndexForManipulator(authorIndex), typeIndex, identificatorCreater.getArtifactIndex(indexForm));
        lists.getArtifactObservable().add(actName);
        formController.setNameToItem(indexForm, nameForManipulator);
        return  true;
    }
    public void saveDataFromBranch(String nameST, BranchTable branchTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);

        dataManipulator.addDataToBranch(nameForManipulator, branchTable.getId(), branchTable.isMainBool());
        lists.getBranchObservable().add(branchTable);
    }

    public void deleteBranch(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToBranchMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList branchListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCPR(branchListObservable);
            lists.removeItemFromObservableList(SegmentType.Branch, indexList);

            dataManipulator.updateItemList(SegmentType.Configuration, SegmentType.Branch, new ArrayList<>(branchListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getConfigurationToBranchMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }

    }

    public void saveDataFromCPR(String nameST, int roleIndex, int configIndex, CPRTable cprTable) {
        mapperTableToObject.mapTableToObject(SegmentType.ConfigPersonRelation, roleIndex,
                new TableToObjectInstanc(cprTable.getName(), cprTable.getId(), SegmentType.ConfigPersonRelation));
        String nameForManipulator = InputController.fillTextMapper(nameST);

        dataManipulator.addDataToCPR(nameForManipulator, dataPreparer.prepareIndexForManipulator(roleIndex),
                dataPreparer.prepareIndexForManipulator(configIndex), cprTable.getId());
        lists.getCPRObservable().add(cprTable);
    }

    public void deleteCPR(ArrayList<BasicTable> selection, TableView view) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigToCPR())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList cprListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCPR(cprListObservable);
            lists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, indexList);

            dataManipulator.updateItemList(SegmentType.Configuration, SegmentType.ConfigPersonRelation, new ArrayList<>(cprListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getCPRToRoleMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void saveDataFromCriterionForm(String nameST, CriterionTable criterionTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        dataManipulator.addDataToCriterion(nameForManipulator, descForManipulator, criterionTable.getId());
        lists.getCriterionObservable().add(criterionTable);
    }

    public void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList criterionListObservable = tableView.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCriterion(criterionListObservable);
            lists.removeItemFromObservableList(SegmentType.Criterion, indexList);
            dataManipulator.updateItemList(SegmentType.Milestone, SegmentType.Criterion, new ArrayList<>(criterionListObservable));
            mapperTableToObject.deleteFromMap(mapperTableToObject.getMilestoneToCriterionMapper(), indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void saveDataFromMilestoneForm(String nameST, ArrayList<Integer> criterionIndex, MilestoneTable milestoneTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        dataManipulator.addDataToMilestone(nameForManipulator, criterionIndex, milestoneTable.getId());
        lists.getMilestoneObservable().add(milestoneTable);
        ArrayList<Integer> criterionIndicies = dataManipulator.getCriterionIds(criterionIndex);
        mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getName(), milestoneTable.getId(),
                SegmentType.Milestone));
    }


    public void deleteMilestone(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPhaseToMilestone())){
            ArrayList<Integer> indexList = deleteControl.findIndicesForDelete(selection);
            lists.removeItemFromObservableList(SegmentType.Milestone, indexList);
            ArrayList phaseDataList = deleteControl.findIndicesForDeleteData(SegmentType.Phase, indexList);
            ObservableList milestoneListObservable = tableView.getSelectionModel().getSelectedIndices();
            dataManipulator.removeMilestone(milestoneListObservable);
            dataManipulator.updateItemList(SegmentType.Phase,SegmentType.Milestone, phaseDataList);
            mapperTableToObject.deleteFromMap(mapperTableToObject.getPhaseToMilestone(), indexList);
            mapperTableToObject.updateValueList( mapperTableToObject.getMilestoneToCriterionMapper(), indexList);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void saveDataFromPriority(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToPriority(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getPriorityTypeObservable().add(tableItem);
    }

    public void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPriorityToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removePriority(priorityObservableList);
            lists.removeItemFromObservableList(SegmentType.Priority, indexList);
            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Priority, new ArrayList<>(priorityObservableList));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getPriorityToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void saveDataFromSeverity(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToSeverity(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getSeverityTypeObservable().add(tableItem);
    }

    public void deleteSeverity(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getSeverityToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeSeverity(severityObservableList);
            lists.removeItemFromObservableList(SegmentType.Severity, indexList);

           dataManipulator.updateItemList(SegmentType.WorkUnit, SegmentType.Severity, new ArrayList<>(indexList));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getSeverityToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void saveDataFromResolutionForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToResolution(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getResolutionTypeObservable().add(classTable);
    }

    public void deleteResolution(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getResolutionToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeResolution(resolutionListObservable);
            lists.removeItemFromObservableList(SegmentType.Resolution, indexList);

            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Resolution, new ArrayList<>(resolutionListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getResolutionToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void saveDataFromRelationForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToRelation(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getRelationTypeObservable().add(classTable);
    }

    public void deleteRelation(ArrayList<BasicTable> list, TableView<ClassTable> view) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        dataManipulator.removeRelation(view.getSelectionModel().getSelectedIndices());
        lists.removeItemFromObservableList(SegmentType.Relation, indexList);
        view.getItems().removeAll(list);
        view.getSelectionModel().clearSelection();
    }

    public void saveDataFromRoleForm(String nameST, int typeIndex, RoleTable roleTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(roleTable.getDesc());
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);

        dataManipulator.addDataToRole(nameForManipulator, descForManipulator, typeFormManipulator, roleTable.getId());
        lists.getRoleObservable().add(roleTable);

        int roleTypeIndex = dataManipulator.roleTypeIndex(typeFormManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Role, roleTypeIndex, new TableToObjectInstanc(roleTable.getName(), roleTable.getId(), SegmentType.Role));
    }

    public void deleteRole(ArrayList<BasicTable> selection, TableView tableView) {

       if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleMaps())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeRole(tableView.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Role, indexList);
            tableView.getSelectionModel().clearSelection();

           ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.WorkUnit, indexList);
           ArrayList cprListData = deleteControl.findIndicesForDeleteData(SegmentType.ConfigPersonRelation, indexList);
           ArrayList configurationListData = deleteControl.findIndicesForDeleteData(SegmentType.Configuration, indexList);
           ArrayList artifactListData = deleteControl.findIndicesForDeleteData(SegmentType.Artifact, indexList);

           dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Role, wuListData);
           dataManipulator.updateItemList(SegmentType.ConfigPersonRelation,SegmentType.Role, cprListData);
           dataManipulator.updateItemList(SegmentType.Configuration,SegmentType.Role, configurationListData);
           dataManipulator.updateItemList(SegmentType.Artifact,SegmentType.Role, artifactListData);

           mapperTableToObject.deleteFromRoleMaps(indexList);
           mapperTableToObject.updateValueList( mapperTableToObject.getRoleToRoleTypeMapper(), indexList);

           tableView.getSelectionModel().clearSelection();
           formController.showForm(Constans.roleFormIndex);


       }

      }

    public boolean deleteRole(ArrayList<Integer> roleTypeIndexes){

        ArrayList<Integer> tableIndices = lists.removeItemFromObservableList(SegmentType.Role, roleTypeIndexes);
        RoleForm milestoneForm = (RoleForm) formController.getForms().get(Constans.roleFormIndex);
        TableView<RoleTable> tv = milestoneForm.getTableTV();
        for(int i : tableIndices){
            tv.getSelectionModel().select(i);
        }
        dataManipulator.removeRole(tv.getSelectionModel().getSelectedIndices());
        ObservableList<RoleTable> selection = FXCollections
                .observableArrayList(tv.getSelectionModel().getSelectedItems());
        tv.getItems().removeAll(selection);
        tv.getSelectionModel().clearSelection();

        return false;
    }

    public void saveDataFromRoleTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToRoleType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(),classTable.getId());
        lists.getRoleTypeObservable().add(classTable);
    }

    public void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleToRoleTypeMapper())){
            ArrayList<Integer> indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();
            dataManipulator.removeRoleType(roleTypeListObservable);
            lists.removeItemFromObservableList(SegmentType.RoleType, indexList);
           // ArrayList roleList = deleteControl.findIndicesForDelete(SegmentType.Role, indexList); //TODO Mozna spatne nejede pres ..DeleteData
            dataManipulator.updateItemList(SegmentType.Role, SegmentType.RoleType, new ArrayList<>(roleTypeListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getRoleToRoleTypeMapper(), indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.roleFormIndex);
        }
    }

    public void saveDataFromTagForm(String tag, String idName, int configId, int id) {
        String tagForManipulator = InputController.fillTextMapper(tag);

        dataManipulator.addTagToConfiguration(tagForManipulator, configId, id);
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        dataManipulator.removeTag(indexList, configId);
        lists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void saveDataFromStatusForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToStatus(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getStatusTypeObservable().add(classTable);
    }

    public void deleteStatus(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getStatusToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList statusListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeStatus(statusListObservable);
            lists.removeItemFromObservableList(SegmentType.Status, indexList);

            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Status, new ArrayList<>(statusListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getStatusToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void saveDataFromTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getTypeObservable().add(classTable);
    }

    public void deleteType(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getTypeToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList typeListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeType(typeListObservable);
            lists.removeItemFromObservableList(SegmentType.Type, indexList);
            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Type, new ArrayList<>(typeListObservable));
            mapperTableToObject.deleteFromMap(mapperTableToObject.getTypeToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();

        }
    }


    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        dataManipulator.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }

    public MilestoneTable prepareMilestoneToTable(String nameST, int id, ArrayList criterionArray) {
     return   dataPreparer.prepareMilestoneTable(nameST, id, dataPreparer.prepareIndicesForManipulator(criterionArray), lists.getCriterionObservable());
    }

    public RoleTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex) {
        return   dataPreparer.prepareRoleTable(nameST, description, id, dataPreparer.prepareIndexForManipulator(roleTypeIndex),
                lists.getRoleTypeObservable());
    }
}
