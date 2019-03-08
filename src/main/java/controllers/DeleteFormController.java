package controllers;

import abstractform.BasicForm;
import interfaces.IDeleteDataModel;
import interfaces.IDeleteFormController;
import interfaces.IEditDataModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;
import tables.BasicTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.TagTable;

import java.util.ArrayList;

public class DeleteFormController implements IDeleteFormController {

    private ArrayList<BasicForm> forms;
    private IDeleteDataModel deleteDataModel;
    private IEditDataModel editDataModel;
    private DataModel dataModel;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DeleteControl deleteControl;
    private SegmentLists segmentLists;
    private FormController formController;

    public DeleteFormController(FormController formController, DataModel dataModel, IdentificatorCreater identificatorCreater,
                                MapperTableToObject mapperTableToObject, DeleteControl deleteControl, SegmentLists segmentLists) {
        this.forms = formController.getForms();
        this.dataModel = dataModel;
        this.editDataModel = dataModel.getEditDataModel();
        this.deleteDataModel = dataModel.getDeleteDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.deleteControl = deleteControl;
        this.segmentLists = segmentLists;
        this.formController = formController;
    }
    
    
    private void deleteActivityForm(ObservableList activityList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeActivity(activityList);
        segmentLists.removeItemFromObservableList(SegmentType.Activity, indexList);
    }

    public void deleteActivityWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection)){

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteActivityForm(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }
    
    public void deleteWorkUnit(ArrayList<Integer> indicesForm) {

        for(int i : indicesForm){
      //      deleteWorkUnit(identificatorCreater.getWorkUnitIndex(i));
        }
    }

    private void deleteWorkUnit(ObservableList workUnitList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeWorkUnit(workUnitList);
        segmentLists.removeItemFromObservableList(SegmentType.WorkUnit, indexList);    
    }

    public void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection)){

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteWorkUnit(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }
    

    private void deleteChange(ObservableList changeList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeChange(changeList);
        segmentLists.removeItemFromObservableList(SegmentType.Change, indexList);

    }

    public void deleteChangeWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection)){

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteChange(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteArtifact(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }

        deleteDataModel.removeArtifact(indexForm);

    }
    

    private void deleteIterationForm(ObservableList iterationList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeIteration(iterationList);
        segmentLists.removeItemFromObservableList(SegmentType.Iteration, indexList);
    }

    public void deleteIterationWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection)){

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteIterationForm(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }
    
    private void deletePhaseWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection)){

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deletePhaseForm(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deletePhaseForm(ObservableList phaseList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removePhase(phaseList);
        segmentLists.removeItemFromObservableList(SegmentType.Phase, indexList);
    }

    public void deleteConfigurationWithDialog(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView) {

        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationMap())) {

            ObservableList observableList =  tableView.getSelectionModel().getSelectedIndices();

            deleteConfiguration(observableList, selection);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();

            formController.setConfigurationFormToTableForm();
        }
    }

    public void deleteConfiguration(ObservableList observableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Phase, SegmentType.Configuration, idList);
        editDataModel.updateItemList(SegmentType.Iteration, SegmentType.Configuration, idList);

        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCPRMapper(), idList);
        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToBranchMapper(), idList);
        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToRoleMapper(), idList);

        mapperTableToObject.deleteFromConfigurationMaps(idList);
        deleteDataModel.removeConfiguration(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Configuration, idList);
    }

    public void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getTypeToWUMapper())){

            ObservableList typeListObservable = view.getSelectionModel().getSelectedIndices();

            deleteType(typeListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }


    }

    public void deleteType(ObservableList typeListObservable, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeType(typeListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Type, indexList);
        editDataModel.updateItemList(SegmentType.WorkUnit,SegmentType.Type, new ArrayList<>(typeListObservable));
        mapperTableToObject.deleteFromMap(mapperTableToObject.getTypeToWUMapper(), indexList);

    }

    public void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getStatusToWUMapper())){

            ObservableList statusListObservable = view.getSelectionModel().getSelectedIndices();
            deleteStatus(statusListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteStatus(ObservableList statusListObservable, ArrayList<BasicTable> selection) {
        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeStatus(statusListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Status, indexList);

        editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Status, new ArrayList<>(statusListObservable));
        mapperTableToObject.deleteFromMap( mapperTableToObject.getStatusToWUMapper(), indexList);
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        deleteDataModel.removeTag(indexList, configId);
        segmentLists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleToRoleTypeMapper())){

            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();

            deleteRoleType(roleTypeListObservable, selection);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.roleFormIndex);
        }
    }

    public void deleteRoleType(ObservableList roleTypeListObservable, ArrayList<BasicTable> selection) {
        ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Role, SegmentType.RoleType, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getRoleToRoleTypeMapper(), idList);

        deleteDataModel.removeRoleType(roleTypeListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.RoleType, idList);
    }

    public void deleteRoleWithDialog(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleMaps())){

            ObservableList observableList = tableView.getSelectionModel().getSelectedIndices();

            deleteRole(observableList, selection);

            tableView.getSelectionModel().clearSelection();

            formController.showForm(Constans.roleFormIndex);


        }

    }

    public void deleteRole(ObservableList observableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Role, idList);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Role, idList);
        editDataModel.updateItemList(SegmentType.Artifact, SegmentType.Role, idList);
        editDataModel.updateItemList(SegmentType.ConfigPersonRelation, SegmentType.Role, idList);

        mapperTableToObject.deleteFromRoleMaps(idList);
        mapperTableToObject.updateValueList( mapperTableToObject.getRoleToRoleTypeMapper(), idList);

        deleteDataModel.removeRole(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Role, idList);
    }

    public void deleteRelationWithDialog(ArrayList<BasicTable> list, TableView<ClassTable> view) {

        ObservableList observableList = view.getSelectionModel().getSelectedIndices();
        deleteRelation(observableList, list);

        view.getItems().removeAll(list);
        view.getSelectionModel().clearSelection();
    }

    public void deleteRelation(ObservableList observableList, ArrayList<BasicTable> list) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        deleteDataModel.removeRelation(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Relation, indexList);
    }


    public void deleteResolutionWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getResolutionToWUMapper())){

            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();

            deleteResolution(resolutionListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteResolution(ObservableList resolutionListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Resolution, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getResolutionToWUMapper(), idList);

        deleteDataModel.removeResolution(resolutionListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Resolution, idList);
    }

    public void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getSeverityToWUMapper())){
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();

            deleteSeverity(severityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteSeverity(ObservableList severityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Severity, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getSeverityToWUMapper(), idList);

        deleteDataModel.removeSeverity(severityObservableList);
        segmentLists.removeItemFromObservableList(SegmentType.Severity, idList);
    }

    public void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPriorityToWUMapper())){
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();

            deletePriority(priorityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deletePriority(ObservableList priorityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.WorkUnit,SegmentType.Priority, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getPriorityToWUMapper(), idList);

        deleteDataModel.removePriority(priorityObservableList);
        segmentLists.removeItemFromObservableList(SegmentType.Priority, idList);
    }


    public void deleteMilestoneWithDialog(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPhaseToMilestone())){
            ObservableList listObservable = tableView.getSelectionModel().getSelectedIndices();
            deleteMilestone(listObservable, selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void deleteMilestone(ObservableList observableList, ArrayList<BasicTable> selection) {
        ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Phase,SegmentType.Milestone, idList);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getPhaseToMilestone(), idList);
        mapperTableToObject.updateValueList( mapperTableToObject.getMilestoneToCriterionMapper(), idList);

        deleteDataModel.removeMilestone(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Milestone, idList);

    }

    public void deleteCriterionWithDialog(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){

            ObservableList criterionListObservable = tableView.getSelectionModel().getSelectedIndices();
            deleteCriterion(criterionListObservable, selection);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void deleteCriterion(ObservableList criterionListObservable, ArrayList<BasicTable> selection) {

        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Milestone, SegmentType.Criterion, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getMilestoneToCriterionMapper(), idList);

        deleteDataModel.removeCriterion(criterionListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Criterion, idList);

    }

    public void deleteCPRWithDialog(ArrayList<BasicTable> selection, TableView view) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToCPRMapper())){
            ObservableList cprListObservable = view.getSelectionModel().getSelectedIndices();
            deleteCPR(cprListObservable, selection);
            view.getSelectionModel().clearSelection();
            formController.showForm(Constans.cprFormIndex);
        }
    }

    public void deleteCPR(ObservableList cprListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.ConfigPersonRelation, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getCPRToRoleMapper(), idList);

        deleteDataModel.removeCPR(cprListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, idList);

    }

    public void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToBranchMapper())){

            ObservableList branchListObservable = view.getSelectionModel().getSelectedIndices();
            deleteBranch(branchListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }

    }

    @Override
    public void deleteItemWithDialog(ArrayList<BasicTable> list, TableView tableTV, SegmentType segmentType) {
        switch (segmentType){
            case Phase:
                deletePhaseWithDialog(list, tableTV);
            break;
            case Iteration:
                deleteIterationWithDialog(list, tableTV);
                break;
            case Activity:
                deleteActivityWithDialog(list, tableTV);
                break;
            case WorkUnit:
                deleteWorkUnitWithDialog(list, tableTV);
                break;
            case Milestone:
                deleteMilestoneWithDialog(list, tableTV);
                break;
            case Criterion:
                deleteCriterionWithDialog(list, tableTV);
                break;
            case ConfigPersonRelation:
                deleteCPRWithDialog(list, tableTV);
                break;
            case RoleType:
                deleteRoleTypeWithDialog(list, tableTV);
                break;
            case Priority:
                deletePriorityWithDialog(list, tableTV);
                break;
            case Severity:
                deleteSeverityWithDialog(list, tableTV);
                break;
            case Status:
                deleteStatusWithDialog(list, tableTV);
                break;
            case Type:
                deleteTypeWithDialog(list, tableTV);
                break;
            case Resolution:
                deleteResolutionWithDialog(list, tableTV);
                break;
            case Relation:
                deleteRelationWithDialog(list, tableTV);
                break;
            case Change:
                deleteChangeWithDialog(list, tableTV);
                break;
            case Branch:
                deleteBranchDialog(list, tableTV);
                break;
        }

    }

    public void deleteBranch(ObservableList branchListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Branch, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getConfigurationToBranchMapper(), idList);

        deleteDataModel.removeBranch(branchListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Branch, idList);
    }
}
