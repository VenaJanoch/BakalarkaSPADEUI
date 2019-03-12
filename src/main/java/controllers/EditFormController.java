package controllers;

import interfaces.IEditDataModel;
import interfaces.IEditFormController;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import model.DataModel;
import model.IdentificatorCreater;
import services.CanvasType;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditFormController implements IEditFormController {

    private IEditDataModel dataManipulator;
    private DataModel dataModel;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private SegmentLists segmentLists;
    private DataPreparer dataPreparer;


    public EditFormController(DataModel dataModel, IdentificatorCreater identificatorCreater,
                              MapperTableToObject mapperTableToObject, SegmentLists segmentLists, DataPreparer dataPreparer) {
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getEditDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
    }

    public void editDataFromClass(SegmentType segmentType, String name, String className, String superClassName, ClassTable classTable, int id) {
        classTable.setName(id + "_" + name);
        classTable.setClassType(className);
        classTable.setSuperType(superClassName);

        switch (segmentType) {
            case RoleType:
                editDataFromRoleType(name, className, superClassName, classTable, id);
                break;
            case Severity:
                editDataFromSeverity(name, className, superClassName, classTable, id);
                break;
            case Priority:
                editDataFromPriority(name, className, superClassName, classTable, id);
                break;
            case Status:
                editDataFromStatus(name, className, superClassName, classTable, id);
                break;
            case Type:
                editDataFromType(name, className, superClassName, classTable, id);
                break;
            case Relation:
                editDataFromRelation(name, className, superClassName, classTable, id);
                break;
            case Resolution:
                editDataFromResolution(name, className, superClassName, classTable, id);
                break;
            default:
        }
    }

    private void editDataFromResolution(String name, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        dataManipulator.editDataInResolution(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    private void editDataFromRelation(String name, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        dataManipulator.editDataInRelation(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    private void editDataFromType(String name, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        dataManipulator.editDataInType(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    private void editDataFromStatus(String name, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        dataManipulator.editDataInStatus(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    private void editDataFromPriority(String nameST, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.editDataInPriority(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

    public void editDataFromTag(String tag, TagTable tagTable, int configFormId, int id) {
        tagTable.setName(id + "_" + tag);
        int configurationId = identificatorCreater.getConfigurationId(configFormId);
        dataManipulator.editTagInConfiguration(tag, configurationId, id);
        tagTable.setTag(tag);
        tagTable.setName(tag);
    }

    private void editDataFromRoleType(String nameST, String className, String superClassName, ClassTable classTable, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.editDataInRoleType(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.RoleType, classTable.getId(), classTable);
    }



    public void editDataFromRole(String nameST, String description, int roleTypeIndex, RoleTable roleTable, int id) {


        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(description);
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);
        int roleId = identificatorCreater.getRoleIndexMaper().get(id);
        dataManipulator.editDataInRole(nameForManipulator, descForManipulator, typeFormManipulator, roleId);
        segmentLists.updateListItem(SegmentType.Role, id, roleTable);
        String roleName = id + nameST;
        int roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
        mapperTableToObject.updateValueList(roleType, mapperTableToObject.getRoleToRoleTypeMapper(),
               id, roleName);


    }

    private void editDataFromSeverity(String nameST, String className, String superClassName, ClassTable classTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        dataManipulator.editDataInSeverity(nameForManipulator, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

    public void editDataFromMilestone(String nameST, MilestoneTable milestoneTable, ArrayList<Integer> criterionIndex, int id) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(milestoneTable.getDescription());
        criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        dataManipulator.editDataInMilestone(nameForManipulator, descForManipulator, criterionIndex, milestoneTable.getId());

        ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
        milestoneTable.setCriterion(dataPreparer.prepareDependencyArray(criterionIndex, segmentLists.getCriterionObservable()));

        segmentLists.updateListItem(SegmentType.Milestone, id, milestoneTable);

        mapperTableToObject.updateValueList(criterionIndicies, mapperTableToObject.getMilestoneToCriterionMapper(),
                milestoneTable.getId(), milestoneTable.getName());
    }

    public void editDataFromCriterion(String nameST, String description, CriterionTable criterionTable, int id) {

        criterionTable.setName(id + "_" + nameST);
        criterionTable.setDescription(description);

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        dataManipulator.editDataInCriterion(nameForManipulator, descForManipulator, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

    public void editDataFromCPR(String nameST, int roleIndex, CPRTable cprTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        int roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);
        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(nameForManipulator, roleManipulatorId, cprId);
        String cprName = cprId + "_" + nameST;
        cprTable.setName(cprName);
        cprTable.setRole(dataPreparer.prepareDependency(roleManipulatorId, segmentLists.getRoleObservable()));
        int roleId = dataModel.getRoleId(roleManipulatorId);
        segmentLists.updateListItem(SegmentType.ConfigPersonRelation, cprId, cprTable);

        mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, cprName);
    }

    public void editDataFromBranch(String nameST, boolean isMainBranch, BranchTable branchTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(nameForManipulator, isMainBranch, branchId);
        String branchName = branchId + "_" + nameST;
        branchTable.setName(branchName);
        if (isMainBranch) {
            branchTable.setMain("YES");
        } else {
            branchTable.setMain("NO");
        }
        branchTable.setMainBool(isMainBranch);

        segmentLists.updateListItem(SegmentType.Branch, branchId, branchTable);
    }

    @Override
    public void editDataFromPhase(String name, String description, LocalDate endDate, int milestonId, int configurationId, ArrayList<Integer> workUnits,
                                  PhaseTable phaseTable, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
        }
        int configIdForManipulator = segmentLists.getConfigObservable().get(configurationId).getId();
        int milestoneForManipulator = segmentLists.getMilestoneObservable().get(milestonId).getId();
        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInPhase(nameForManipulator, endDate, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, id);

        String phaseName = id + "_" + name;
        phaseTable.setName(phaseName);
        phaseTable.setConfiguration(segmentLists.getConfigObservable().get(configurationId).getName());
        phaseTable.setMilestone(segmentLists.getMilestoneObservable().get(milestonId).getName());

    }

    @Override
    public void editDataFromIteration(String name, String description, LocalDate startDate, LocalDate endDate, int configurationId, ArrayList<Integer> workUnits, IterationTable iterationTable,
                                      int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
        }
        int configIdForManipulator = segmentLists.getConfigObservable().get(configurationId).getId();

        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInIteration(nameForManipulator, startDate, endDate, descriptionForManipulator, configIdForManipulator, workUnitsForManipulator, id);

        String iterationName = id + "_" + name;
        iterationTable.setName(iterationName);
        iterationTable.setConfiguration(segmentLists.getConfigObservable().get(configurationId).getName());
    }

    @Override
    public void editDataFromActivity(String name, String description, ArrayList<Integer> workUnits, ActivityTable activityTable,
                                      int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
            activityTable.setDescription(descriptionForManipulator);
        }

        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInActivity(nameForManipulator, descriptionForManipulator, workUnitsForManipulator, id);

        String activityName = id + "_" + name;
        activityTable.setName(activityName);

    }

    @Override
    public void editDataFromChange(String name, String description, boolean exist, ChangeTable changeTable,
                                     int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
            changeTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInChange(nameForManipulator, descriptionForManipulator, exist, id);

        String changeName = id + "_" + name;
        changeTable.setName(changeName);

    }

    @Override
    public void editDataFromArtifact(String name, String description, boolean exist, int roleIndex, int typeIndex, LocalDate localDate, ArtifactTable artifactTable,
                                   int id) {
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
            artifactTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInArtifact(nameForManipulator, descriptionForManipulator, localDate, exist, roleIndex, typeIndex, id);

        String artifactName = id + "_" + name;
        artifactTable.setName(artifactName);

    }
    @Override
    public void editDataFromWorkUnit(String name, String description, String estimatedTime, int priorityIndex, int severityIndex, int resolutionIndex,
                                     int statusIndex, String category, int typeIndex, int assigneIndex, int authorIndex, boolean selected, WorkUnitTable workUnitTable, int id) {

        String nameForManipulator = InputController.fillTextMapper(name);
        String categoryForManipulator = InputController.fillTextMapper(category);
        String descriptionForManipulator = InputController.fillTextMapper(description);

        Double estimateForDataManipulator = -1.0;

        if (estimatedTime != null && !estimatedTime.equals("")) {
            estimateForDataManipulator = InputController.isDoubleNumber(estimatedTime);
        }

        int assigneForManipulator = dataPreparer.prepareIndexForManipulator(assigneIndex);
        int authorForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);
        int priorityForManipulator = dataPreparer.prepareIndexForManipulator(priorityIndex);
        int severityForManipulator = dataPreparer.prepareIndexForManipulator(severityIndex);
        int typeForManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);
        int resolutionForManipulator = dataPreparer.prepareIndexForManipulator(resolutionIndex);
        int statusForManipulator = dataPreparer.prepareIndexForManipulator(statusIndex);
        dataManipulator.editDataInWorkUnit(nameForManipulator, descriptionForManipulator ,categoryForManipulator, assigneForManipulator, authorForManipulator,
                priorityForManipulator ,severityForManipulator, typeForManipulator,resolutionForManipulator , statusForManipulator, estimateForDataManipulator, selected, id);
        workUnitTable.setName(id + "_" + name);
    }

    @Override
    public void editDataFromConfiguration(String name, LocalDate createDate, int autohorIndex, boolean isRelease, ObservableList<Integer> cprsIndicies,
                                          ObservableList<Integer> branchIndicies, ObservableList<Integer> changeIndicies, int configId) {
        String nameForManipulator = InputController.fillTextMapper(name);

        int roleIdForManipulator = segmentLists.getRoleObservable().get(autohorIndex).getId();

        ArrayList<Integer> cprsForManipulator = dataPreparer.prepareIndicesForManipulator(cprsIndicies);
        ArrayList<Integer> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branchIndicies);
        ArrayList<Integer> changesForManipulator = dataPreparer.prepareIndicesForManipulator(changeIndicies);

        dataManipulator.editDataInConfiguration(nameForManipulator, createDate, isRelease, roleIdForManipulator,
                cprsForManipulator, branchesForManipulator, changesForManipulator, configId);
    }

    @Override
    public void editDataFromVCSTag(String name, String description, VCSTagTable tagTable, int id){
        String nameForManipulator = InputController.fillTextMapper(name);
        String descriptionForManipulator = "NotExist";
        if(description != null){
            descriptionForManipulator = InputController.fillTextMapper(description);
            tagTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInVCSTag(nameForManipulator, descriptionForManipulator, id);

        String artifactName = id + "_" + name;
        tagTable.setName(artifactName);
    }

    @Override
    public void editDataFromCommit(String name, boolean release, int id) {
        String nameForManipulator = InputController.fillTextMapper(name);

        dataManipulator.editDataInCommit(nameForManipulator, release, id);

    }

    @Override
    public void editDataFromCommitedConfiguration(String name, LocalDate dateFromDatePicker, int commitedConfigurationId) {
        String nameForManipulator = InputController.fillTextMapper(name);

        dataManipulator.editDataInCommitedConfiguration(nameForManipulator, dateFromDatePicker, commitedConfigurationId);

    }

}


