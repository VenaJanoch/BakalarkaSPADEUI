package controllers.formControllers;

import abstractControlPane.ControlPanel;
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

import java.util.ArrayList;

/**
 * Trida predstavujici controller pro odstraneni prvku z datovych struktur
 * Predstavujici modelovaci platno
 *
 * @author Václav Janoch
 */
public class DeleteFormController implements IDeleteFormController {

    public ArrayList<ControlPanel> panels;
    public IDeleteDataModel deleteDataModel;
    public IEditDataModel editDataModel;
    public DataModel dataModel;
    public IdentificatorCreater identificatorCreater;
    public MapperTableToObject mapperTableToObject;
    public DeleteControl deleteControl;
    public SegmentLists segmentLists;
    public FormController formController;

    /**
     * Konstruktor tridy
     * Inicializuje potrebne globalni promenne
     * Parametry jsou tridy
     * @param formController
     * @param dataModel
     * @param identificatorCreater
     * @param mapperTableToObject
     * @param deleteControl
     * @param segmentLists
     */
    public DeleteFormController(FormController formController, DataModel dataModel, IdentificatorCreater identificatorCreater,
                                MapperTableToObject mapperTableToObject, DeleteControl deleteControl, SegmentLists segmentLists) {
        this.panels = formController.getControlPanels();
        this.dataModel = dataModel;
        this.editDataModel = dataModel.getEditDataModel();
        this.deleteDataModel = dataModel.getDeleteDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.deleteControl = deleteControl;
        this.segmentLists = segmentLists;
        this.formController = formController;
    }


    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param activityList
     * @param selection
     */
    public void deleteActivityForm(ObservableList activityList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeActivity(activityList);
        segmentLists.removeItemFromObservableList(SegmentType.Activity, indexList);
    }

    /**
     * Metoda pro odstraneni segmetnu Activity
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection
     * @param view
     */
    public void deleteActivityWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection)) {

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();
            deleteActivityForm(phaseListObservable, selection);
            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param workUnitList
     * @param selection
     */
    public void deleteWorkUnit(ObservableList workUnitList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getPhaseToWUMapper(), indexList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getIterationToWUMapper(), indexList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getActivityToWUMapper(), indexList);
        editDataModel.updateItemList(SegmentType.Phase, SegmentType.Work_Unit, indexList);
        editDataModel.updateItemList(SegmentType.Iteration, SegmentType.Work_Unit, indexList);
        editDataModel.updateItemList(SegmentType.Activity, SegmentType.Work_Unit, indexList);

        deleteDataModel.removeWorkUnit(workUnitList);
        segmentLists.removeItemFromObservableList(SegmentType.Work_Unit, indexList);
    }

    /**
     * Metoda pro odstraneni elementu
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection
     * @param view
     */
    public void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWorkUnitMaps())) {

            ObservableList listObservable = view.getSelectionModel().getSelectedIndices();
            deleteWorkUnit(listObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }


    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param changeList
     * @param selection
     */
    public void deleteChange(ObservableList changeList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToChangeMapper(), indexList);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Change, indexList);
        segmentLists.removeItemFromObservableList(SegmentType.Change, indexList);
        deleteDataModel.removeChange(changeList);

    }

    /**
     * Metoda pro odstraneni elementu Change
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteChangeWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToChangeMapper())) {

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteChange(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param indexForm
     * @return
     */
    public boolean deleteArtifact(int indexForm) {
        ArrayList list = new ArrayList();
        int id = identificatorCreater.getArtifactIndexToIdMaper().get(indexForm);
            list.add(segmentLists.getArtifactTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getArtifactMaps())) {

                panels.remove(indexForm);
                panels.add(indexForm, null);
                editDataModel.updateItemList(SegmentType.Change, SegmentType.Artifact, id);
                editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Artifact, id);

                mapperTableToObject.updateValueList(mapperTableToObject.getChangeToArtifactMapper(), id);
                mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToArtifactMapper(), id);

                mapperTableToObject.deleteFromMap(mapperTableToObject.getArtifactToRoleMapper(), id);
                segmentLists.removeItemFromObservableList(SegmentType.Artifact, id);
                deleteDataModel.removeArtifact(id);
                return true;
            }

        return false;

    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param iterationList
     * @param selection
     */
    public void deleteIterationForm(ObservableList iterationList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removeIteration(iterationList);
        segmentLists.removeItemFromObservableList(SegmentType.Iteration, indexList);
    }

    /**
     * Metoda pro odstraneni segmetnu Iteration
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteIterationWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection)) {

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deleteIterationForm(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }
    /**
     * Metoda pro odstraneni segmentu Phase
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deletePhaseWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection)) {

            ObservableList phaseListObservable = view.getSelectionModel().getSelectedIndices();

            deletePhaseForm(phaseListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param phaseList
     * @param selection
     */
    public void deletePhaseForm(ObservableList phaseList, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);
        deleteDataModel.removePhase(phaseList);
        segmentLists.removeItemFromObservableList(SegmentType.Phase, indexList);
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param indexForm
     * @return
     */
    public boolean deleteConfigurationWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getConfigurationId(indexForm);
            list.add(segmentLists.getConfigurationTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getConfigurationMaps())) {

                panels.remove(indexForm);
                panels.add(indexForm, null);
                deleteConfiguration(id);
                return true;
            }
        return false;
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param id
     */
    public void deleteConfiguration(int id) {

        editDataModel.updateItemList(SegmentType.Phase, SegmentType.Configuration, id);
        editDataModel.updateItemList(SegmentType.Iteration, SegmentType.Configuration, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCPRMapper(), id);
        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToRoleMapper(), id);

        mapperTableToObject.deleteFromConfigurationMaps(id);
        deleteDataModel.removeConfiguration(id);
        segmentLists.removeItemFromObservableList(SegmentType.Configuration, id);
    }
    /**
     * Metoda pro odstraneni elementu Commit
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param indexForm index formulare pro ostraneni
     */
    public boolean deleteCommitWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getCommitId(indexForm);
            list.add(segmentLists.getCommitTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getCommitedConfigurationToCommitMapper())) {

                panels.remove(indexForm);
                panels.add(indexForm, null);
                deleteCommit(id);
                return true;
            }
        return false;
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param id
     */

    public void deleteCommit(int id) {

        editDataModel.updateItemList(SegmentType.Committed_Configuration, SegmentType.Commit, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getCommitedConfigurationToCommitMapper(), id);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getCommitedConfigurationToCommitMapper(), id);
        deleteDataModel.removeCommit(id);
        segmentLists.removeItemFromObservableList(SegmentType.Commit, id);
    }

    /**
     * Metoda pro odstraneni elementu Committed configuration
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param indexForm index formulare pro odstraneni
     */
    public boolean deleteCommitedConfigurationWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getCommitedConfigurationId(indexForm);
            list.add(segmentLists.getCommitedConfigurationTable(id));
            if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getConfigurationToCommitedConfigurationMapper())) {

                panels.remove(indexForm);
                panels.add(indexForm, null);
                deleteCommitedConfiguration(id);
                return true;
            }
        return false;
    }
    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param id
     */
    public void deleteCommitedConfiguration(int id) {

        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Committed_Configuration, id);

        mapperTableToObject.updateValueList(mapperTableToObject.getConfigurationToCommitedConfigurationMapper(), id);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToCommitedConfigurationMapper(), id);
        deleteDataModel.removeCommitedConfiguration(id);
        segmentLists.removeItemFromObservableList(SegmentType.Committed_Configuration, id);
    }
    /**
     * Metoda pro odstraneni elementu Type
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUTotypeMapper())) {

            ObservableList typeListObservable = view.getSelectionModel().getSelectedIndices();

            deleteType(typeListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }


    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param typeListObservable
     * @param selection
     */
    public void deleteType(ObservableList typeListObservable, ArrayList<BasicTable> selection) {

        ArrayList indexList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Type, indexList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUTotypeMapper(), indexList);

        deleteDataModel.removeType(typeListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Type, indexList);


    }
    /**
     * Metoda pro odstraneni elementu Status
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUStatusMapper())) {

            ObservableList statusListObservable = view.getSelectionModel().getSelectedIndices();
            deleteStatus(statusListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param statusListObservable
     * @param selection
     */
    public void deleteStatus(ObservableList statusListObservable, ArrayList<BasicTable> selection) {
        ArrayList indexList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Status, new ArrayList<Integer>(statusListObservable));
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUStatusMapper(), indexList);

        deleteDataModel.removeStatus(statusListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Status, indexList);


    }
    /**
     * Metoda pro odstraneni elementu Role Type
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    public void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPersonToRoleTypeMapper())) {

            ObservableList roleTypeListObservable = tableView.getSelectionModel().getSelectedIndices();

            deleteRoleType(roleTypeListObservable, selection);

            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param roleTypeListObservable
     * @param selection
     */
    public void deleteRoleType(ObservableList roleTypeListObservable, ArrayList<BasicTable> selection) {
        ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Person, SegmentType.Role_Type, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getPersonToRoleTypeMapper(), idList);

        deleteDataModel.removeRoleType(roleTypeListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Role_Type, idList);
    }

    /**
     * Metoda pro odstraneni elementu Person
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     */
    public boolean deletePersonWithDialog(int indexForm) {

        ArrayList list = new ArrayList();
        int id = identificatorCreater.getRoleId(indexForm);

        list.add(segmentLists.getPersonTable(id));
        if (Alerts.showDeleteItemCascadeAlert(list, mapperTableToObject.getRoleMaps())) {

            panels.remove(indexForm);
            panels.add(indexForm, null);
            deletePerson(id);
            return true;
        }
        return false;
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param id
     */
    public void deletePerson(int id) {

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Artifact, SegmentType.Person, id);
        editDataModel.updateItemList(SegmentType.Config_Person_Relation, SegmentType.Person, id);

        mapperTableToObject.deleteFromRoleMaps(id);
        mapperTableToObject.updateValueList(mapperTableToObject.getPersonToRoleTypeMapper(), id);

        deleteDataModel.removeRole(id);
        segmentLists.removeItemFromObservableList(SegmentType.Person, id);
    }
    /**
     * Metoda pro odstraneni elementu Relation
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param list zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteRelationWithDialog(ArrayList<BasicTable> list, TableView<ClassTable> view) {

        ObservableList observableList = view.getSelectionModel().getSelectedIndices();
        deleteRelation(observableList, list);

        view.getItems().removeAll(list);
        view.getSelectionModel().clearSelection();
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param observableList
     * @param list
     */
    public void deleteRelation(ObservableList observableList, ArrayList<BasicTable> list) {
        ArrayList idList = deleteControl.findIndicesForDelete(list);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Relation, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUTORelationMapper(), idList);


        deleteDataModel.removeRelation(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Relation, idList);
    }

    /**
     * Metoda pro odstraneni elementu Resolution
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteResolutionWithDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToResolutionMapper())) {

            ObservableList resolutionListObservable = view.getSelectionModel().getSelectedIndices();

            deleteResolution(resolutionListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }
    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param resolutionListObservable
     * @param selection
     */
    public void deleteResolution(ObservableList resolutionListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Resolution, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUToResolutionMapper(), idList);

        deleteDataModel.removeResolution(resolutionListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Resolution, idList);
    }
    /**
     * Metoda pro odstraneni elementu Severity
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToSeverityMapper())) {
            ObservableList severityObservableList = view.getSelectionModel().getSelectedIndices();

            deleteSeverity(severityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param severityObservableList
     * @param selection
     */
    public void deleteSeverity(ObservableList severityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Severity, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUToSeverityMapper(), idList);

        deleteDataModel.removeSeverity(severityObservableList);
        segmentLists.removeItemFromObservableList(SegmentType.Severity, idList);
    }
    /**
     * Metoda pro odstraneni elementu Priority
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getWUToPriorityMapper())) {
            ObservableList priorityObservableList = view.getSelectionModel().getSelectedIndices();

            deletePriority(priorityObservableList, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param priorityObservableList
     * @param selection
     */
    public void deletePriority(ObservableList priorityObservableList, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Work_Unit, SegmentType.Priority, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getWUToPriorityMapper(), idList);

        deleteDataModel.removePriority(priorityObservableList);
        segmentLists.removeItemFromObservableList(SegmentType.Priority, idList);
    }

    /**
     * Metoda pro odstraneni elementu Milestone
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    public void deleteMilestoneWithDialog(ArrayList<BasicTable> selection, TableView tableView) {

        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getPhaseToMilestone())) {
            ObservableList listObservable = tableView.getSelectionModel().getSelectedIndices();
            deleteMilestone(listObservable, selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param observableList
     * @param selection
     */
    public void deleteMilestone(ObservableList observableList, ArrayList<BasicTable> selection) {
        ArrayList<Integer> idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Phase, SegmentType.Milestone, idList);

        mapperTableToObject.deleteFromMap(mapperTableToObject.getPhaseToMilestone(), idList);
        mapperTableToObject.updateValueList(mapperTableToObject.getMilestoneToCriterionMapper(), idList);

        deleteDataModel.removeMilestone(observableList);
        segmentLists.removeItemFromObservableList(SegmentType.Milestone, idList);

    }
    /**
     * Metoda pro odstraneni elementu Criterion
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    public void deleteCriterionWithDialog(ArrayList<BasicTable> selection, TableView tableView) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getMilestoneToCriterionMapper())) {

            ObservableList criterionListObservable = tableView.getSelectionModel().getSelectedIndices();
            deleteCriterion(criterionListObservable, selection);
            tableView.getItems().removeAll(selection);
            tableView.getSelectionModel().clearSelection();
            formController.showForm(Constans.milestoneFormIndex);
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param criterionListObservable
     * @param selection
     */
    public void deleteCriterion(ObservableList criterionListObservable, ArrayList<BasicTable> selection) {

        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Milestone, SegmentType.Criterion, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getMilestoneToCriterionMapper(), idList);

        deleteDataModel.removeCriterion(criterionListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Criterion, idList);

    }
    /**
     * Metoda pro odstraneni elementu CPR
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteCPRWithDialog(ArrayList<BasicTable> selection, TableView view) {

        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToCPRMapper())) {
            ObservableList cprListObservable = view.getSelectionModel().getSelectedIndices();
            deleteCPR(cprListObservable, selection);
            view.getSelectionModel().clearSelection();
            formController.showForm(Constans.cprFormIndex);
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param cprListObservable
     * @param selection
     */

    public void deleteCPR(ObservableList cprListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);

        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Config_Person_Relation, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToCPRMapper(), idList);

        deleteDataModel.removeCPR(cprListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Config_Person_Relation, idList);

    }
    /**
     * Metoda pro odstraneni elementu VCSTag
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableTV prehledova tabulka
     */
    public void deleteVCSTagWithDialog(ArrayList<BasicTable> selection, TableView tableTV) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToVCSTagMapper())) {
            ObservableList branchListObservable = tableTV.getSelectionModel().getSelectedIndices();
            deleteBranch(branchListObservable, selection);

            tableTV.getItems().removeAll(selection);
            tableTV.getSelectionModel().clearSelection();
        }
    }

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     */
    public void deleteVCSTag(ObservableList tagListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.VCSTag, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToVCSTagMapper(), idList);

        deleteDataModel.removeVCSTag(tagListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.VCSTag, idList);
    }

    /**
     * Metoda pro odstraneni elementu Branch
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    public void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view) {
        if (Alerts.showDeleteItemCascadeAlert(selection, mapperTableToObject.getConfigurationToBranchMapper())) {

            ObservableList branchListObservable = view.getSelectionModel().getSelectedIndices();
            deleteBranch(branchListObservable, selection);

            view.getItems().removeAll(selection);
            view.getSelectionModel().clearSelection();
        }

    }


    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Artifact
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removePersonArtifactLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromPersonToArtifactRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getArtifactToRoleMapper(), result[1]);
        deleteDataModel.removePersonArtifactLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Commit
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removePersonCommitLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromPersonToCommitRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getCommitToRoleMapper(), result[1]);
        deleteDataModel.removePersonCommitLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Committed Configuration
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removePersonCommittedConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromPersonToCommittedConfigurationRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getCommittedConfigurationToRoleMapper(), result[1]);
        deleteDataModel.removePersonCommittedConfigurationLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Configuration
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removePersonConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromPersonToConfigurationRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getConfigurationToRoleMapper(), result[1]);
        deleteDataModel.removePersonConfigurationLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky Artifact a Configuration
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removeArtifactConfiguraionLink(int arrowId, int startId, int endId, boolean isModelDelete) {

        Integer[] result = formController.findResultsFromArtifactToConfigurationRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getConfigurationToArtifactMapper(), result[1]);
        deleteDataModel.removeArtifactConfigurationLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky CommitedConfiguration a Configuration
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removeCommitedConfigurationConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromCommitedConfigurationToConfigurationRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getConfigurationToCommitedConfigurationMapper(), result[1]);
        deleteDataModel.removeCommitedConfigurationConfigurationLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro odstraneni spojnice mezi prvky Commit a Committed Configuration
     * @param arrowId identifikator spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    public void removeCommitComiitedConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete) {
        Integer[] result = formController.findResultsFromCommitToCommitedConfigurationRelation(startId, endId);
        mapperTableToObject.clearValueList(result[0], mapperTableToObject.getCommitedConfigurationToCommitMapper(), result[1]);
        deleteDataModel.removeCommitCommitedConfigurationLink(arrowId, result[0], result[1], isModelDelete);
    }

    /**
     * Metoda pro zavolani konkretnich metoda pro odstraneni elementu a segmetnu z datovych struktur
     * @param list
     * @param tableTV
     * @param segmentType
     */
    @Override
    public void deleteItemWithDialog(ArrayList<BasicTable> list, TableView tableTV, SegmentType segmentType) {
        switch (segmentType) {
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

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     * @param branchListObservable
     * @param selection
     */
    public void deleteBranch(ObservableList branchListObservable, ArrayList<BasicTable> selection) {
        ArrayList idList = deleteControl.findIndicesForDelete(selection);
        editDataModel.updateItemList(SegmentType.Configuration, SegmentType.Branch, idList);
        mapperTableToObject.deleteFromMap(mapperTableToObject.getConfigurationToBranchMapper(), idList);

        deleteDataModel.removeBranch(branchListObservable);
        segmentLists.removeItemFromObservableList(SegmentType.Branch, idList);
    }
}
