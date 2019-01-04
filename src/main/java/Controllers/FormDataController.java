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
    
    private List<Integer> prepareIndexsForManipulator(List<Integer> indexis) {
        List<Integer> tmpIndexis = new ArrayList<>();
        for (int index : indexis) {
            tmpIndexis.add(prepareIndexForManipulator(index));
        }
        return tmpIndexis;
    }

    private ArrayList<Integer>  prepareCanvasItemIndexForManipulator(Set<Integer> keys){

        ArrayList<Integer> indexs = new ArrayList<>();
        for(Integer i : keys){
            indexs.add(identificatorCreater.getWorkUnitIndex(i));
        }

        return indexs;
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
                prepareIndexsForManipulator(branchIndex), prepareIndexsForManipulator(cprIndex), artefactList, changeList, identificatorCreater.getConfigurationIndex(indexForm));
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
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeBranch(indexList);
        lists.removeItemFromObservableList(SegmentType.Branch, indexList);


    }

    public void saveDataFromCPR(String nameST, String idName, int roleIndex, int configIndex, int index) {
        String nameForManipulator = InputController.fillTextMapper(nameST);

        dataManipulator.addDataToCPR(nameForManipulator, prepareIndexForManipulator(roleIndex), prepareIndexForManipulator(configIndex), index);
        lists.getCPRObservable().add(idName);
    }

    public void deleteCPR(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeCPR(indexList);
        lists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, indexList);
    }

    public void saveDataFromCriterionForm(String nameST, String idName, String descriptionST, int id) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(descriptionST);
        dataManipulator.addDataToCriterion(nameForManipulator, descForManipulator, id);
        lists.getCriterionObservable().add(idName);
    }

    public void deleteCriterion(ArrayList<BasicTable> selection) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){
            ArrayList indexList = deleteControl.findIndexesForDelete(selection);
            dataManipulator.removeCriterion(indexList);
            lists.removeItemFromObservableList(SegmentType.Criterion, indexList);
            deleteMilestone(deleteControl.findMilestonesIndexesForDelete(indexList));
        }

    }

    public void saveDataFromMilestoneForm(String nameST, String idName, List<Integer> criterionIndex, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        criterionIndex = prepareIndexsForManipulator(criterionIndex);

        mapperTableToObject.mapMilestoneToCriterion( criterionIndex, new TableToObjectInstanc(idName, id, SegmentType.Milestone));
        dataManipulator.addDataToMilestone(nameForManipulator, criterionIndex);
        lists.getMilestoneObservable().add(idName);
    }
    public void deleteMilestoneObservable(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        deleteMilestone(indexList);
    }

    public void deleteMilestone(ArrayList<Integer> list) {
        dataManipulator.removeMilestone(list);
        lists.removeItemFromObservableList(SegmentType.Milestone, list);
        MilestoneForm milestoneForm = (MilestoneForm)formController.getForms().get(Constans.milestoneFormIndex);

        TableView<MilestoneTable> tv = milestoneForm.getTableTV();
        for(Integer i : list){
            tv.getSelectionModel().select(i);
        }

        ObservableList<MilestoneTable> selection = FXCollections
                .observableArrayList(tv.getSelectionModel().getSelectedItems());
        tv.getItems().removeAll(selection);

        tv.getSelectionModel().clearSelection();

    }

    public void saveDataFromPriority(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToPriority(nameForManipulator, classST, superST, id);
        lists.getPriorityTypeObservable().add(idName);
    }

    public void deletePriority(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removePriority(indexList);
        lists.removeItemFromObservableList(SegmentType.Priority, indexList);
    }

    public void saveDataFromSeverity(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToSeverity(nameForManipulator, classST, superST, id);
        lists.getSeverityTypeObservable().add(idName);
    }

    public void deleteSeverity(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeSeverity(indexList);
        lists.removeItemFromObservableList(SegmentType.Severity, indexList);
    }

    public void saveDataFromResolutionForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToResolution(nameForManipulator, classST, superST, id);
        lists.getResolutionTypeObservable().add(idName);
    }

    public void deleteResolution(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeResolution(indexList);
        lists.removeItemFromObservableList(SegmentType.Resolution, indexList);
    }

    public void saveDataFromRelationForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToRelation(nameForManipulator, classST, superST, id);
        lists.getRelationTypeObservable().add(idName);
    }

    public void deleteRelation(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteRelation(list);
        dataManipulator.removeRelation(indexList);
        lists.removeItemFromObservableList(SegmentType.Relation, indexList);
    }

    public void saveDataFromRoleForm(String nameST, String idName, String desc, int type, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        int typeFormManipulator = prepareIndexForManipulator(type);

        mapperTableToObject.mapRoleTypeToRole(typeFormManipulator, new TableToObjectInstanc(idName, id, SegmentType.Role));
        dataManipulator.addDataToRole(nameForManipulator, descForManipulator, typeFormManipulator);
        lists.getRoleObservable().add(idName);
    }

    public void deleteRoleObservable(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
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

    public void saveDataFromRoleTypeForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToRoleType(nameForManipulator, classST, superST,id);
        lists.getRoleTypeObservable().add(idName);
    }

    public void deleteRoleType(ArrayList<BasicTable> selection) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleTypeToRoleMapper())){
            ArrayList<Integer> indexList = deleteControl.findIndexesForDelete(selection);
            dataManipulator.removeRoleType(indexList);
            lists.removeItemFromObservableList(SegmentType.RoleType, indexList);
            deleteRole(deleteControl.findRoleIndexesForDelete(indexList));
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

    public void saveDataFromStatusForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToStatus(nameForManipulator, classST, superST,id);
        lists.getStatusTypeObservable().add(idName);
    }

    public void deleteStatus(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeStatus(indexList);
        lists.removeItemFromObservableList(SegmentType.Status, indexList);
    }

    public void saveDataFromTypeForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.addDataToType(nameForManipulator, classST, superST,id);
        lists.getTypeObservable().add(idName);
    }

    public void deleteType(ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndexesForDelete(list);
        dataManipulator.removeType(indexList);
        lists.removeItemFromObservableList(SegmentType.Type, indexList);
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        dataManipulator.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }
}
