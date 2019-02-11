package Controllers;

import abstractform.BasicForm;
import interfaces.IDeleteFormController;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;
import tables.BasicTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.TagTable;

import java.util.ArrayList;

public class DeleteFormController implements IDeleteFormController {

    private ArrayList<BasicForm> forms;
    private DataManipulator dataManipulator;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DeleteControl deleteControl;
    private SegmentLists segmentLists;
    private FormController formController;

    public DeleteFormController(FormController formController, DataManipulator dataManipulator, IdentificatorCreater identificatorCreater,
                                MapperTableToObject mapperTableToObject, DeleteControl deleteControl, SegmentLists segmentLists) {
        this.forms = formController.getForms();
        this.dataManipulator = dataManipulator;
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
        dataManipulator.removeActivity(indexForm);
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
        dataManipulator.removeWorkUnit(identificatorCreater.getWorkUnitIndexMaper().get(indexForm));
    }


    public void deleteChange(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }
        dataManipulator.removeChange(indexForm);

    }

    public void deleteArtifact(int indexForm) {
        if (!forms.get(indexForm).isSave()) {
            forms.remove(indexForm);
            forms.add(indexForm, null);
        }

        dataManipulator.removeArtifact(indexForm);

    }

    public void deleteIterationForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }

        dataManipulator.removeIteration(identificatorCreater.getIterationIndex(formIdentificator));
    }

    public void deletePhaseForm(int formIdentificator) {

        if (!forms.get(formIdentificator).isSave()) {
            forms.remove(formIdentificator);
            forms.add(formIdentificator, null);
        }
        dataManipulator.removePhase(identificatorCreater.getPhaseIndex(formIdentificator));
    }

    public void deleteConfiguration(ArrayList<BasicTable> selection, TableView<ConfigTable> tableView) {

        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationMap())) {
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeConfiguration(tableView.getSelectionModel().getSelectedIndices());
            segmentLists.removeItemFromObservableList(SegmentType.Configuration, indexList);

            ArrayList phaseListData = deleteControl.findIndicesForDeleteData(SegmentType.Phase, indexList);
            ArrayList iterationListData = deleteControl.findIndicesForDeleteData(SegmentType.Iteration, indexList);

            dataManipulator.updateItemList(SegmentType.Phase, SegmentType.Configuration, phaseListData);
            dataManipulator.updateItemList(SegmentType.Iteration, SegmentType.Configuration, iterationListData);

            mapperTableToObject.deleteFromConfigurationMaps(indexList);
            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCPRMapper(), indexList);
            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToBranchMapper(), indexList);
            mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToRoleMapper(), indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();

            formController.setConfigurationFormToTableForm();
        }
    }

    public void deleteType(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getTypeToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList typeListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeType(typeListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Type, indexList);
            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Type, new ArrayList<>(typeListObservable));
            mapperTableToObject.deleteFromMap(mapperTableToObject.getTypeToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();

        }
    }

    public void deleteStatus(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getStatusToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList statusListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeStatus(statusListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Status, indexList);

            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Status, new ArrayList<>(statusListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getStatusToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        dataManipulator.removeTag(indexList, configId);
        segmentLists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void deleteRoleType(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleToRoleTypeMapper())){
            ArrayList<Integer> indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();
            dataManipulator.removeRoleType(roleTypeListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.RoleType, indexList);
            // ArrayList roleList = deleteControl.findIndicesForDelete(SegmentType.Role, indexList); //TODO Mozna spatne nejede pres ..DeleteData
            dataManipulator.updateItemList(SegmentType.Role, SegmentType.RoleType, new ArrayList<>(roleTypeListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getRoleToRoleTypeMapper(), indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.roleFormIndex);
        }
    }

    public void deleteRole(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getRoleMaps())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            dataManipulator.removeRole(tableView.getSelectionModel().getSelectedIndices());
            segmentLists.removeItemFromObservableList(SegmentType.Role, indexList);

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

    public void deleteRelation(ArrayList<BasicTable> list, TableView<ClassTable> view) {
        ArrayList indexList = deleteControl.findIndicesForDelete(list);
        dataManipulator.removeRelation(view.getSelectionModel().getSelectedIndices());
        segmentLists.removeItemFromObservableList(SegmentType.Relation, indexList);
        view.getItems().removeAll(list);
        view.getSelectionModel().clearSelection();
    }

    public void deleteResolution(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getResolutionToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeResolution(resolutionListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Resolution, indexList);

            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Resolution, new ArrayList<>(resolutionListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getResolutionToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteSeverity(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getSeverityToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeSeverity(severityObservableList);
            segmentLists.removeItemFromObservableList(SegmentType.Severity, indexList);

            dataManipulator.updateItemList(SegmentType.WorkUnit, SegmentType.Severity, new ArrayList<>(indexList));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getSeverityToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deletePriority(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPriorityToWUMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removePriority(priorityObservableList);
            segmentLists.removeItemFromObservableList(SegmentType.Priority, indexList);
            dataManipulator.updateItemList(SegmentType.WorkUnit,SegmentType.Priority, new ArrayList<>(priorityObservableList));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getPriorityToWUMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteMilestone(ArrayList<BasicTable> selection, TableView tableView) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPhaseToMilestone())){
            ArrayList<Integer> indexList = deleteControl.findIndicesForDelete(selection);
            segmentLists.removeItemFromObservableList(SegmentType.Milestone, indexList);
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

    public void deleteCriterion(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList criterionListObservable = tableView.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCriterion(criterionListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Criterion, indexList);
            dataManipulator.updateItemList(SegmentType.Milestone, SegmentType.Criterion, new ArrayList<>(criterionListObservable));
            mapperTableToObject.deleteFromMap(mapperTableToObject.getMilestoneToCriterionMapper(), indexList);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    public void deleteCPR(ArrayList<BasicTable> selection, TableView view) {

        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToCPRMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList cprListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCPR(cprListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, indexList);

            dataManipulator.updateItemList(SegmentType.Configuration, SegmentType.ConfigPersonRelation, new ArrayList<>(cprListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getCPRToRoleMapper(), indexList);

            view.getSelectionModel().clearSelection();
            formController.showForm(Constans.cprFormIndex);
        }
    }

    public void deleteBranch(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToBranchMapper())){
            ArrayList indexList = deleteControl.findIndicesForDelete(selection);
            ObservableList branchListObservable = view.getSelectionModel().getSelectedIndices();
            dataManipulator.removeCPR(branchListObservable);
            segmentLists.removeItemFromObservableList(SegmentType.Branch, indexList);

            dataManipulator.updateItemList(SegmentType.Configuration, SegmentType.Branch, new ArrayList<>(branchListObservable));
            mapperTableToObject.deleteFromMap( mapperTableToObject.getConfigurationToBranchMapper(), indexList);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }

    }
}
