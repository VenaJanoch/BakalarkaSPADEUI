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


    public FormDataController(FormController formController, DeleteControl deleteControl,
                       SegmentLists lists, MapperTableToObject mapperTableToObject, DataManipulator dataManipulator, IdentificatorCreater identificatorCreater){
        this.formController = formController;
        this.formControl = new FormControl();
        this.deleteControl = deleteControl;
        this.lists = lists;
        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;

    }

    private int prepareIndexForManipulator(int index){
        //if(index != 0){
          //  return  index -1;
        //}
        return index - 1;
    }
    
    private ArrayList<Integer> prepareIndicesForManipulator(List<Integer> indices) {
        ArrayList<Integer> tmpIndices = new ArrayList<>();
        for (int index : indices) {
            tmpIndices.add(prepareIndexForManipulator(index));
        }
        return tmpIndices;
    }

    private ArrayList<Integer>  prepareCanvasItemIndexForManipulator(Set<Integer> keys){

        ArrayList<Integer> indices = new ArrayList<>();
        for(Integer i : keys){
            indices.add(identificatorCreater.getWorkUnitIndex(i));
        }

        return indices;
    }

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToPhase(nameForManipulator, endDateL, descriptionForManipulator, prepareIndexForManipulator(confIndex),
                prepareIndexForManipulator(milestoneIndex), coords[0], coords[1], prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), identificatorCreater.getPhaseIndex(indexForm));
       formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }



    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, prepareIndexForManipulator(chooseConfigID),
                coords[0], coords[1], prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), identificatorCreater.getIterationIndex(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToActivity(nameForManipulator, descriptionForManipulator, coords[0], coords[1], prepareCanvasItemIndexForManipulator(mapOfItemOnCanvas.keySet()),
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

        dataManipulator.addDataToWorkUnit(nameForManipulator, descriptionForManipulator ,categoryForManipulator, prepareIndexForManipulator(assigneIndex),
                prepareIndexForManipulator(authorIndex), prepareIndexForManipulator(priorityIndex), prepareIndexForManipulator(severityIndex),
                prepareIndexForManipulator(typeIndex), prepareIndexForManipulator(resolutionIndex), prepareIndexForManipulator(statusIndex),
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
        dataManipulator.addDataToConfiguration(nameForManipulator, createDate, isRelease, prepareIndexForManipulator(authorIndex),
                prepareIndicesForManipulator(branchIndex), prepareIndicesForManipulator(cprIndex), artefactList, changeList, identificatorCreater.getConfigurationIndex(indexForm));
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
                prepareIndexForManipulator(authorIndex), typeIndex, identificatorCreater.getArtifactIndex(indexForm));
        lists.getArtifactObservable().add(actName);
        formController.setNameToItem(indexForm, nameForManipulator);
        return  true;
    }
    public void saveDataFromBranch(String nameST, String idName, int id, boolean isMain) {

        String nameForManipulator = InputController.fillTextMapper(nameST);

        dataManipulator.addDataToBranch(nameForManipulator, id, isMain);
        lists.getBranchObservable().add(idName);
    }

    public void deleteBranch(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        dataManipulator.removeBranch(indexList);
        lists.removeItemFromObservableList(SegmentType.Branch, indexList);


    }

    public void saveDataFromCPR(String nameST, String idName, int roleIndex, int configIndex, int index) {
        String nameForManipulator = InputController.fillTextMapper(nameST);

        dataManipulator.addDataToCPR(nameForManipulator, prepareIndexForManipulator(roleIndex), prepareIndexForManipulator(configIndex), index);
        lists.getCPRObservable().add(idName);
    }

    public void deleteCPR(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        dataManipulator.removeCPR(indexList);
        lists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, indexList);
    }

    public void saveDataFromCriterionForm(String nameST, CriterionTable criterionTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        dataManipulator.addDataToCriterion(nameForManipulator, descForManipulator);
        lists.getCriterionObservable().add(criterionTable);
    }

    public void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView ) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeCriterion(indexList);
            lists.removeItemFromObservableList(SegmentType.Criterion, indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            ArrayList milestoneList = deleteControl.findIndicesForDelete(SegmentType.Milestone, indexList);
            deleteMilestone(milestoneList);
            mapperTableToObject.updateValueList( mapperTableToObject.getMilestoneToCriterionMapper(), indexList, milestoneList);
        }
    }

    public void saveDataFromMilestoneForm(String nameST, ArrayList<Integer> criterionIndex, MilestoneTable milestoneTable) {

        mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndex, new TableToObjectInstanc(milestoneTable.getName(), milestoneTable.getId(),
                SegmentType.Milestone));
        String nameForManipulator = InputController.fillTextMapper(nameST);
        criterionIndex = prepareIndicesForManipulator(criterionIndex);

        dataManipulator.addDataToMilestone(nameForManipulator, criterionIndex);
        lists.getMilestoneObservable().add(milestoneTable);
    }
    public void deleteMilestoneObservable(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        deleteMilestone(indexList);
    }

    public void deleteMilestone(ArrayList<Integer> list) {

        dataManipulator.removeMilestone(list);
        ArrayList<Integer> tableIndices =  lists.removeItemFromObservableList(SegmentType.Milestone, list);
        MilestoneForm milestoneForm = (MilestoneForm)formController.getForms().get(Constans.milestoneFormIndex);

        TableView<MilestoneTable> tv = milestoneForm.getTableTV();
        for(Integer i : tableIndices){
            tv.getSelectionModel().select(i);
        }
        ObservableList<MilestoneTable> selection = FXCollections
                .observableArrayList(tv.getSelectionModel().getSelectedItems());
        tv.getItems().removeAll(selection);

        tv.getSelectionModel().clearSelection();


    }

    public void saveDataFromPriority(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToPriority(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getPriorityTypeObservable().add(tableItem);
    }

    public void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPriorityToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removePriority(view.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Priority, indexList);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
            ArrayList wuList = deleteControl.findIndicesForDelete(SegmentType.Priority, indexList);
            ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.Priority, indexList);
            formController.updateWUListItem(SegmentType.Priority, wuList);
            dataManipulator.updateWUListItem(SegmentType.Priority, wuListData);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getPriorityToWUMapper(), indexList);
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
            dataManipulator.removeSeverity(view.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Severity, indexList);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
            ArrayList wuList = deleteControl.findIndicesForDelete(SegmentType.Severity, indexList);
            ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.Severity, indexList);
            formController.updateWUListItem(SegmentType.Severity, wuList);
            dataManipulator.updateWUListItem(SegmentType.Severity, wuListData);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getSeverityToWUMapper(), indexList);
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
            dataManipulator.removeResolution(view.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Resolution, indexList);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
            ArrayList wuList = deleteControl.findIndicesForDelete(SegmentType.Resolution, indexList);
            ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.Resolution, indexList);
            formController.updateWUListItem(SegmentType.Resolution, wuList);
            dataManipulator.updateWUListItem(SegmentType.Resolution, wuListData);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getResolutionToWUMapper(), indexList);
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

        mapperTableToObject.mapTableToObject(SegmentType.Role, typeIndex, new TableToObjectInstanc(roleTable.getName(), roleTable.getId(), SegmentType.Role));
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(roleTable.getDesc());
        int typeFormManipulator = prepareIndexForManipulator(typeIndex);

        dataManipulator.addDataToRole(nameForManipulator, descForManipulator, typeFormManipulator);
        lists.getRoleObservable().add(roleTable);
    }

    public void deleteRoleObservable(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        deleteRole(indexList);
    }

    public void deleteRole(ArrayList<Integer> roleTypeIndexes){
        dataManipulator.removeRole(roleTypeIndexes);
        lists.removeItemFromObservableList(SegmentType.Role, roleTypeIndexes);
        RoleForm milestoneForm = (RoleForm) formController.getForms().get(Constans.roleFormIndex);

        TableView<RoleTable> tv = milestoneForm.getTableTV();
        for(Integer i : roleTypeIndexes){
            tv.getSelectionModel().select(i);
        }

        ObservableList<RoleTable> selection = FXCollections
                .observableArrayList(tv.getSelectionModel().getSelectedItems());
        tv.getItems().removeAll(selection);
        tv.getSelectionModel().clearSelection();
    }

    public void saveDataFromRoleTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToRoleType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(),classTable.getId());
        lists.getRoleTypeObservable().add(classTable);
    }

    public void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleToRoleTypeMapper())){
            ArrayList<Integer> indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeRoleType(indexList);
            lists.removeItemFromObservableList(SegmentType.RoleType, indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            ArrayList roleList = deleteControl.findIndicesForDelete(SegmentType.Role, indexList);
            deleteRole(roleList);
            deleteMilestone(roleList);
            mapperTableToObject.updateValueList( mapperTableToObject.getRoleToRoleTypeMapper(), indexList, roleList);
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
            dataManipulator.removeStatus(view.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Status, indexList);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
            ArrayList wuList = deleteControl.findIndicesForDelete(SegmentType.Status, indexList);
            ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.Status, indexList);
            formController.updateWUListItem(SegmentType.Status, wuList);
            dataManipulator.updateWUListItem(SegmentType.Status, wuListData);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getStatusToWUMapper(), indexList);
        }
    }

    private ArrayList<Integer> prepareIndicesForManipulatorData(ArrayList<Integer> list, Map<Integer, Integer> map){
        ArrayList<Integer> deleteList = new ArrayList<>();
        for(int i : list){
            deleteList.add(map.get(i));
        }
        return deleteList;
    }

    public void saveDataFromTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getTypeObservable().add(classTable);
    }

    public void deleteType(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getTypeToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeType(view.getSelectionModel().getSelectedIndices());
            lists.removeItemFromObservableList(SegmentType.Type, indexList);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
            ArrayList wuList = deleteControl.findIndicesForDelete(SegmentType.Type, indexList);
            ArrayList wuListData = deleteControl.findIndicesForDeleteData(SegmentType.Type, indexList);
            formController.updateWUListItem(SegmentType.Type, wuList);
            dataManipulator.updateWUListItem(SegmentType.Type, wuListData);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getTypeToWUMapper(), indexList);
        }
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        dataManipulator.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }
}
