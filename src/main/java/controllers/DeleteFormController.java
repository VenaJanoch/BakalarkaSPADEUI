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
           //deleteWorkUnit(identificatorCreater.getWorkUnitIndex(i));
        }
    }

    private void deleteWorkUnit(ObservableList workUnitList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeWorkUnit(workUnitList);
        segmentLists.removeItemFromObservableList(SegmentType.Work_Unit, indexList);
    }

    public void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWorkUnitMaps())){

            ObservableList listObservable = view.getSelectionModel().getSelectedIndices();
            deleteWorkUnit(listObservable, selection);

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

    public boolean deleteArtifact(int indexForm) {
        ArrayList list = new ArrayList();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(indexForm);
        if (!forms.get(indexForm).isSave()) {
            list.add(segmentLists.getArtifactTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getArtifactMaps())) {

                forms.remove(indexForm);
                forms.add(indexForm, null);
                editDataModel.updateItemList(SegmentType.Change, SegmentType.Artifact, id);
                editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Artifact, id);

                mapperTableToObject.updateValueList(mapperTableToObject.getChangeToArtifactMapper(), id);
                mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToArtifactMapper(), id);

                mapperTableToObject.deleteFromMap(mapperTableToObject.getArtifactToRoleMapper(), id);
                segmentLists.removeItemFromObservableList(SegmentType.Artifact, id);
                deleteDataModel.removeArtifact(id);
                return true;
            }
        }

        return false;

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

    public boolean deleteConfigurationWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getConfigurationId(indexForm);
        if (!forms.get(indexForm).isSave()) {
            list.add(segmentLists.getConfigurationTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getConfigurationMaps())) {

                forms.remove(indexForm);
                forms.add(indexForm, null);
                deleteConfiguration(id);
                return true;
            }
        }
            return false;
    }

    private void deleteConfiguration(int id) {

        editDataModel.updateItemList(SegmentType.Phase, SegmentType.Configuration, id);
        editDataModel.updateItemList(SegmentType.Iteration, SegmentType.Configuration, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCPRMapper(), id);
        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToRoleMapper(), id);

        mapperTableToObject.deleteFromConfigurationMaps(id);
        deleteDataModel.removeConfiguration(id);
        segmentLists.removeItemFromObservableList(SegmentType.Configuration, id);
    }

    public boolean deleteCommitWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getCommitId(indexForm);
        if (!forms.get(indexForm).isSave()) {
            list.add(segmentLists.getCommitTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getCommitedConfigurationToCommitMapper())) {

                forms.remove(indexForm);
                forms.add(indexForm, null);
                deleteCommit(id);
                return true;
            }
        }
        return false;
    }

    private void deleteCommit(int id) {

        editDataModel.updateItemList(SegmentType.Committed_Configuration, SegmentType.Commit, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getCommitedConfigurationToCommitMapper(), id);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getCommitedConfigurationToCommitMapper() ,id);
        deleteDataModel.removeCommit(id);
        segmentLists.removeItemFromObservableList(SegmentType.Commit, id);
    }
    
    

    public boolean deleteCommitedConfigurationWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getCommitedConfigurationId(indexForm);
        if (!forms.get(indexForm).isSave()) {
            list.add(segmentLists.getCommitedConfigurationTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getConfigurationToCommitedConfigurationMapper())) {

                forms.remove(indexForm);
                forms.add(indexForm, null);
                deleteCommitedConfiguration(id);
                return true;
            }
        }
        return false;
    }

    public void deleteCommitedConfiguration(int id) {

        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Committed_Configuration, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCommitedConfigurationMapper(), id);
        
        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToCommitedConfigurationMapper(), id);
        deleteDataModel.removeCommitedConfiguration(id);
        segmentLists.removeItemFromObservableList(SegmentType.Committed_Configuration, id);
    }

    public void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUTotypeMapper())){

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
        editDataModel.updateItemList(SegmentType.Work_Unit,SegmentType.Type, new ArrayList<>(typeListObservable));
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUTotypeMapper(), indexList);

    }

    public void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUStatusMapper())){

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

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Status, new ArrayList<>(statusListObservable));
        mapperTableToObject.deleteFromMap( mapperTableToObject.getWUStatusMapper(), indexList);
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        deleteDataModel.removeTag(indexList, configId);
        segmentLists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPersonToRoleTypeMapper())){

            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();

            deleteRoleType(roleTypeListObservable, selection);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
        }
    }

    public void deleteRoleType(ObservableList roleTypeListObservable, ArrayList<BasicTable> selection) {
        ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Person, SegmentType.Role_Type, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getPersonToRoleTypeMapper(), idList);

        deleteDataModel.removeRoleType(roleTypeListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Role_Type, idList);
    }



    public boolean deleteRoleWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getRoleId(indexForm);

            list.add(segmentLists.getArtifactTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getRoleMaps())) {

                forms.remove(indexForm);
                forms.add(indexForm, null);

                deleteRole(id);
                return true;
            }
        return false;
    }

    public void deleteRole(int id) {

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Artifact, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Config_Person_Relation, SegmentType.Person, id);

        mapperTableToObject.deleteFromRoleMaps(id);
        mapperTableToObject.updateValueList( mapperTableToObject.getPersonToRoleTypeMapper(), id);

        deleteDataModel.removeRole(id);
        segmentLists.removeItemFromObservableList(SegmentType.Person, id);
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
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToResolutionMapper())){

            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();

            deleteResolution(resolutionListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteResolution(ObservableList resolutionListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Resolution, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUToResolutionMapper(), idList);

        deleteDataModel.removeResolution(resolutionListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Resolution, idList);
    }

    public void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToSeverityMapper())){
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();

            deleteSeverity(severityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deleteSeverity(ObservableList severityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Severity, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getWUToSeverityMapper(), idList);

        deleteDataModel.removeSeverity(severityObservableList);
        segmentLists.removeItemFromObservableList(SegmentType.Severity, idList);
    }

    public void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToPriorityMapper())){
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();

            deletePriority(priorityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    public void deletePriority(ObservableList priorityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit,SegmentType.Priority, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getWUToPriorityMapper(), idList);

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

        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Config_Person_Relation, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getCPRToRoleMapper(), idList);

        deleteDataModel.removeCPR(cprListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Config_Person_Relation, idList);

    }

    private void deleteVCSTagWithDialog(ArrayList<BasicTable> selection, TableView tableTV) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getCommitToVCSTagMapper())) {
            ObservableList branchListObservable = tableTV.getSelectionModel().getSelectedIndices();
            deleteBranch(branchListObservable, selection);

            tableTV.getItems().removeAll(selection);
            tableTV.getSelectionModel().clearSelection();
        }
    }

    public void deleteVCSTag(ObservableList tagListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Commit, SegmentType.Branch, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getCommitToVCSTagMapper(), idList);

        deleteDataModel.removeVCSTag(tagListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.VCSTag, idList);
    }


    public void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view) {
        if(Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getCommitToBranchMapper())){

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
            case Work_Unit:
                deleteWorkUnitWithDialog(list, tableTV);
                break;
            case Milestone:
                deleteMilestoneWithDialog(list, tableTV);
                break;
            case Criterion:
                deleteCriterionWithDialog(list, tableTV);
                break;
            case Config_Person_Relation:
                deleteCPRWithDialog(list, tableTV);
                break;
            case Role_Type:
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
            case VCSTag:
                deleteVCSTagWithDialog(list, tableTV);
                break;
        }

    }



    public void deleteBranch(ObservableList branchListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Commit, SegmentType.Branch, idList);
        mapperTableToObject.deleteFromMap( mapperTableToObject.getCommitToBranchMapper(), idList);

        deleteDataModel.removeBranch(branchListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Branch, idList);
    }
}
