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
    
    
    public void deleteActivityForm(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        deleteDataModel.removeActivity(indexForm);
    }

    public void deleteWorkUnit(ArrayList<Integer> indicesForm) {

        for(int i : indicesForm){
            deleteWorkUnit(identificatorCreater.getWorkUnitIndex(i));
        }
    }

    public void deleteWorkUnit(int indexForm) {

        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        deleteDataModel.removeWorkUnit(identificatorCreater.getWorkUnitIndexMaper().get(indexForm));
    }

    public void deleteChange(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        deleteDataModel.removeChange(indexForm);

    }

    public void deleteArtifact(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }

        deleteDataModel.removeArtifact(indexForm);

    }

    public void deleteIterationForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }

        deleteDataModel.removeIteration(identificatorCreater.getIterationId(formIdentificator));
    }

    public void deletePhaseForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }
        deleteDataModel.removePhase(identificatorCreater.getPhaseId(formIdentificator));
    }

    public void deleteConfiguration(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView) {

        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationMap())) {
            ArrayList idList = deleteControl.findIndicesForDelete(selection);

            editDataModel.updateItemList(SegmentType.Phase, SegmentType.Configuration, idList);
            editDataModel.updateItemList(SegmentType.Iteration, SegmentType.Configuration, idList);

            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCPRMapper(), idList);
            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToBranchMapper(), idList);
            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToRoleMapper(), idList);

            mapperTableToObject.deleteFromConfigurationMaps(idList);
            deleteDataModel.removeConfiguration(tableView.getSelectionModel().getSelectedIndices());
            segmentLists.removeItemFromObservableList(SegmentType.Configuration, idList);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();

            formController.setConfigurationFormToTableForm();
        }
    }

    public void deleteType(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getTypeToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList typeListObservable = view.getSelectionModel().getSelectedIndices();
            deleteDataModel.removeType(typeListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Type, indexList);
            editDataModel.updateItemList(SegmentType.WorkUnit,SegmentType.Type, new ArrayList<>(typeListObservable));
            mapperTableToObject.deleteFromMap(mapperTableToObject.getTypeToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();

        }
    }

    public void deleteStatus(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getStatusToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList statusListObservable = view.getSelectionModel().getSelectedIndices();
            deleteDataModel.removeStatus(statusListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Status, indexList);

            editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Status, new ArrayList<>(statusListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getStatusToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        deleteDataModel.removeTag(indexList, configId);
        segmentLists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleToRoleTypeMapper())){
            ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);
            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.Role, SegmentType.RoleType, idList);
            mapperTableToObject.deleteFromMap(mapperTableToObject.getRoleToRoleTypeMapper(), idList);

            deleteDataModel.removeRoleType(roleTypeListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.RoleType, idList);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.roleFormIndex);
        }
    }

    public void deleteRole(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleMaps())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);

            editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Role, idList);
            editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Role, idList);
            editDataModel.updateItemList(SegmentType.Artifact, SegmentType.Role, idList);
            editDataModel.updateItemList(SegmentType.ConfigPersonRelation, SegmentType.Role, idList);

            mapperTableToObject.deleteFromRoleMaps(idList);
            mapperTableToObject.updateValueList( mapperTableToObject.getRoleToRoleTypeMapper(), idList);

            deleteDataModel.removeRole(tableView.getSelectionModel().getSelectedIndices());
            segmentLists.removeItemFromObservableList(SegmentType.Role, idList);
            tableView.getSelectionModel().clearSelection();

            formController.showForm(Constans.roleFormIndex);


        }

    }

    public void deleteRelation(ArrayList<BasicTable> list, TableView<ClassTable> view) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        deleteDataModel.removeRelation(view.getSelectionModel().getSelectedIndices());
        segmentLists.removeItemFromObservableList(SegmentType.Relation, indexList);
        view.getItems().removeAll(list);
        view.getSelectionModel().clearSelection();
    }

    public void deleteResolution(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getResolutionToWUMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Resolution, idList);
            mapperTableToObject.deleteFromMap(mapperTableToObject.getResolutionToWUMapper(), idList);

            deleteDataModel.removeResolution(resolutionListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Resolution, idList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteSeverity(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getSeverityToWUMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.WorkUnit, SegmentType.Severity, idList);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getSeverityToWUMapper(), idList);

            deleteDataModel.removeSeverity(severityObservableList);
            segmentLists.removeItemFromObservableList(SegmentType.Severity, idList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPriorityToWUMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.WorkUnit,SegmentType.Priority, idList);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getPriorityToWUMapper(), idList);

            deleteDataModel.removePriority(priorityObservableList);
            segmentLists.removeItemFromObservableList(SegmentType.Priority, idList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteMilestone(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPhaseToMilestone())){
            ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);

            editDataModel.updateItemList(SegmentType.Phase,SegmentType.Milestone, idList);

            mapperTableToObject.deleteFromMap(mapperTableToObject.getPhaseToMilestone(), idList);
            mapperTableToObject.updateValueList( mapperTableToObject.getMilestoneToCriterionMapper(), idList);

            deleteDataModel.removeMilestone(tableView.getSelectionModel().getSelectedIndices());
            segmentLists.removeItemFromObservableList(SegmentType.Milestone, idList);

            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList criterionListObservable = tableView.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.Milestone, SegmentType.Criterion, idList);
            mapperTableToObject.deleteFromMap(mapperTableToObject.getMilestoneToCriterionMapper(), idList);

            deleteDataModel.removeCriterion(criterionListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Criterion, idList);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void deleteCPR(ArrayList<BasicTable> selection, TableView view) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToCPRMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList cprListObservable = view.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.Configuration, SegmentType.ConfigPersonRelation, idList);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getCPRToRoleMapper(), idList);

            deleteDataModel.removeCPR(cprListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, idList);

            view.getSelectionModel().clearSelection();
            formController.showForm(Constans.cprFormIndex);
        }
    }

    public void deleteBranch(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToBranchMapper())){
            ArrayList idList = deleteControl.findIndicesForDelete(selection);
            ObservableList branchListObservable = view.getSelectionModel().getSelectedIndices();

            editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Branch, idList);
            mapperTableToObject.deleteFromMap( mapperTableToObject.getConfigurationToBranchMapper(), idList);

            deleteDataModel.removeBranch(branchListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Branch, idList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }

    }
}
