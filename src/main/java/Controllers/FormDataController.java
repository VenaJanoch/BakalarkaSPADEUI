package Controllers;

import graphics.CanvasItem;
import javafx.collections.ObservableList;
import model.DataManipulator;
import model.IdentificatorCreater;
import org.junit.experimental.theories.FromDataPoints;
import services.*;
import tables.*;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormDataController {

    private FormControl formControl;
    private ApplicationController applicationController;
    private DeleteControl deleteControl;
    private SegmentLists lists;
    private DataManipulator dataManipulator;
    private FormController formController;
    private IdentificatorCreater identificatorCreater;

    public FormDataController(FormController formController, DeleteControl deleteControl,
                       SegmentLists lists, DataManipulator dataManipulator, IdentificatorCreater identificatorCreater){
        this.formController = formController;
        this.formControl = new FormControl();
        this.deleteControl = deleteControl;
        this.lists = lists;
        this.dataManipulator = dataManipulator;
        this.identificatorCreater = identificatorCreater;
    }

    private int prepareIndexForManipulator(int index){
        if(index != 0){
            return  index -1;
        }
        return index;
    }
    
    private List<Integer> prepareIndexsForManipulator(List<Integer> indexis) {
        List<Integer> tmpIndexis = new ArrayList<>();
        for (int index : indexis) {
            tmpIndexis.add(prepareIndexForManipulator(index));
        }
        return tmpIndexis;
    }

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToPhase(nameForManipulator, endDateL, descriptionForManipulator, prepareIndexForManipulator(confIndex),
                prepareIndexForManipulator(milestoneIndex), coords[0], coords[1], itemIndexList.keySet(), indexForm);
        return true;
    }

    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, prepareIndexForManipulator(chooseConfigID),
                coords[0], coords[1], itemIndexList.keySet(), indexForm);
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descriptionForManipulator = formControl.fillTextMapper(desc);
        int[] coords = formController.getCoordsFromItem(indexForm);

        dataManipulator.addDataToActivity(nameForManipulator, descriptionForManipulator, coords[0], coords[1], mapOfItemOnCanvas.keySet(), indexForm);
        return true;

    }

    public boolean saveDataFromWorkUnit(String actName,String description, String category, int assigneIndex, int authorIndex, int priorityIndex,int severityIndex,
                                        int typeIndex, int resolutionIndex, int statusIndex, String estimated, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String categoryForManipulator = formControl.fillTextMapper(category);

        int[] coords = formController.getCoordsFromItem(indexForm);

        double estimateForDataManipulator = -1.0;

        try {
            if (!estimated.equals("")) {
                estimateForDataManipulator = Double.parseDouble(estimated);
            }

        } catch (NumberFormatException e) {
            Alerts.showWrongEstimatedTimeAlert();
            return  false;
        }

        formController.setItemColor(indexForm, selected);

        dataManipulator.addDataToWorkUnit(nameForManipulator, description ,categoryForManipulator, prepareIndexForManipulator(assigneIndex),
                prepareIndexForManipulator(authorIndex), prepareIndexForManipulator(priorityIndex), prepareIndexForManipulator(severityIndex),
                prepareIndexForManipulator(typeIndex), prepareIndexForManipulator(resolutionIndex), prepareIndexForManipulator(statusIndex),
                coords[0], coords[1], estimateForDataManipulator, selected, indexForm);

        return  true;
    }

    public void saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                          ArrayList<Integer> cprIndex, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = formControl.fillTextMapper(actName);
        int[] coords = formController.getCoordsFromItem(indexForm);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();

        for(int index : itemIndexList.keySet()) {
            if(identificatorCreater.getChangeIndexMaper().get(index) != null){
                changeList.add(index);
            }else {
                artefactList.add(index);
            }
        }

        dataManipulator.addDataToConfiguration(nameForManipulator, createDate, isRelease, coords[0], coords[1],
                prepareIndexForManipulator(authorIndex), prepareIndexsForManipulator(branchIndex),
                prepareIndexsForManipulator(cprIndex), artefactList, changeList, indexForm);
        lists.getConfigObservable().add(actName);

    }


    public boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descForManipulator = formControl.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        formController.setItemColor(indexForm, selected);
        dataManipulator.addDataToChange(nameForManipulator, descForManipulator, coords[0], coords[1], selected, indexForm);

        return true;
    }

    public boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                        int typeIndex, boolean selected, int indexForm) {

        String nameForManipulator = formControl.fillTextMapper(actName);
        String descForManipulator = formControl.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        formController.setItemColor(indexForm, selected);

        dataManipulator.addDataToArtifact(nameForManipulator, descForManipulator, createdDate, selected, coords[0], coords[1],
                prepareIndexForManipulator(authorIndex), typeIndex, indexForm);
        lists.getArtifactObservable().add(actName);

        return  true;
    }
    public void saveDataFromBranch(String nameST, String idName, int id, boolean isMain) {

        String nameForManipulator = formControl.fillTextMapper(nameST);

        dataManipulator.addDataToBranch(nameForManipulator, id, isMain);
        lists.getBranchObservable().add(idName);
    }

    public void deleteBranch(ObservableList<BranchTable> list) {
        ArrayList indexList = deleteControl.deleteBranch(list);
        dataManipulator.removeBranch(indexList);
        lists.removeItemFromObservableList(SegmentType.Branch, indexList);


    }

    public void saveDataFromCPR(String nameST, String idName, int roleIndex, int configIndex, int index) {
        String nameForManipulator = formControl.fillTextMapper(nameST);

        dataManipulator.addDataToCPR(nameForManipulator, prepareIndexForManipulator(roleIndex), prepareIndexForManipulator(configIndex), index);
        lists.getCPRObservable().add(idName);
    }

    public void deleteCPR(ObservableList<CPRTable> list) {
        ArrayList indexList = deleteControl.deleteCPR(list);
        dataManipulator.removeCPR(indexList);
        lists.removeItemFromObservableList(SegmentType.ConfigPersonRelation, indexList);
    }

    public void saveDataFromCriterionForm(String nameST, String idName, String descriptionST, int id) {
        String nameForManipulator = formControl.fillTextMapper(nameST);
        String descForManipulator = formControl.fillTextMapper(descriptionST);
        dataManipulator.addDataToCriterion(nameForManipulator, descForManipulator, id);
        lists.getCriterionObservable().add(idName);
    }

    public void deleteCriterion(ObservableList<CriterionTable> list) {
        ArrayList indexList = deleteControl.deleteCriterion(list);
        dataManipulator.removeCriterion(indexList);
        lists.removeItemFromObservableList(SegmentType.Criterion, indexList);
    }

    public void saveDataFromMilestoneForm(String nameST, String idName, List<Integer> criterionIndex, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToMilestone(nameForManipulator, criterionIndex, id);
        lists.getMilestoneObservable().add(idName);
    }
    public void deleteMilestone(ObservableList<MilestoneTable> list) {
        ArrayList indexList = deleteControl.deleteMilestone(list);
        dataManipulator.removeMilestone(indexList);
        lists.removeItemFromObservableList(SegmentType.Milestone, indexList);
    }

    public void saveDataFromPriority(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToPriority(nameForManipulator, classST, superST, id);
        lists.getPriorityTypeObservable().add(idName);
    }

    public void deletePriority(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deletePriority(list);
        dataManipulator.removePriority(indexList);
        lists.removeItemFromObservableList(SegmentType.Priority, indexList);
    }

    public void saveDataFromSeverity(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToSeverity(nameForManipulator, classST, superST, id);
        lists.getSeverityTypeObservable().add(idName);
    }

    public void deleteSeverity(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteSeverity(list);
        dataManipulator.removeSeverity(indexList);
        lists.removeItemFromObservableList(SegmentType.Severity, indexList);
    }

    public void saveDataFromResolutionForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToResolution(nameForManipulator, classST, superST, id);
        lists.getResolutionTypeObservable().add(idName);
    }

    public void deleteResolution(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteResolution(list);
        dataManipulator.removeResolution(indexList);
        lists.removeItemFromObservableList(SegmentType.Resolution, indexList);
    }

    public void saveDataFromRelationForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToRelation(nameForManipulator, classST, superST, id);
        lists.getRelationTypeObservable().add(idName);
    }

    public void deleteRelation(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteRelation(list);
        dataManipulator.removeRelation(indexList);
        lists.removeItemFromObservableList(SegmentType.Relation, indexList);
    }

    public void saveDataFromRoleForm(String nameST, String idName, String desc, int type, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        String descForManipulator = formControl.fillTextMapper(desc);

        dataManipulator.addDataToRole(nameForManipulator, descForManipulator, type,id);
        lists.getRoleObservable().add(idName);
    }

    public void deleteRole(ObservableList<RoleTable> list) {
        ArrayList indexList = deleteControl.deleteRole(list);
        dataManipulator.removeRole(indexList);
        lists.removeItemFromObservableList(SegmentType.Role, indexList);
    }

    public void saveDataFromRoleTypeForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToRoleType(nameForManipulator, classST, superST,id);
        lists.getRoleTypeObservable().add(idName);
    }

    public void deleteRoleType(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteRoleType(list);
        dataManipulator.removeRoleType(indexList);
        lists.removeItemFromObservableList(SegmentType.RoleType, indexList);
    }

    public void saveDataFromTagForm(String tag, String idName, int configId, int id) {
        String tagForManipulator = formControl.fillTextMapper(tag);

        dataManipulator.addTagToConfiguration(tagForManipulator, configId, id);
    }

    public void deleteTag(int configId, ObservableList<TagTable> list) {

        ArrayList indexList = deleteControl.deleteTag(list);
        dataManipulator.removeTag(indexList, configId);
        lists.removeItemFromObservableList(SegmentType.Tag, indexList);

    }

    public void saveDataFromStatusForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToStatus(nameForManipulator, classST, superST,id);
        lists.getStatusTypeObservable().add(idName);
    }

    public void deleteStatus(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteStatus(list);
        dataManipulator.removeStatus(indexList);
        lists.removeItemFromObservableList(SegmentType.Status, indexList);
    }

    public void saveDataFromTypeForm(String nameST, String idName, String classST, String superST, int id) {

        String nameForManipulator = formControl.fillTextMapper(nameST);
        dataManipulator.addDataToType(nameForManipulator, classST, superST,id);
        lists.getTypeObservable().add(idName);
    }

    public void deleteType(ObservableList<ClassTable> list) {
        ArrayList indexList = deleteControl.deleteType(list);
        dataManipulator.removeType(indexList);
        lists.removeItemFromObservableList(SegmentType.Type, indexList);
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = formControl.fillTextMapper(nameST);
        String descForManipulator = formControl.fillTextMapper(desc);

        dataManipulator.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }
}
