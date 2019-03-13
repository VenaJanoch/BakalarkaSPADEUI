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
    private FormController formController;


    public EditFormController(DataModel dataModel, IdentificatorCreater identificatorCreater,
                              MapperTableToObject mapperTableToObject, SegmentLists segmentLists, DataPreparer dataPreparer, FormController formController) {
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getEditDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.formController = formController;
    }

    public void editDataFromClass(SegmentType segmentType, String name, String className, String superClassName, ClassTable classTable, int id) {
        
        String nameForManipulator = InputController.fillNameTextMapper(name);
        
        classTable.setName(dataPreparer.createTableItemIdName(id,nameForManipulator));
        classTable.setClassType(className);
        classTable.setSuperType(superClassName);

        switch (segmentType) {
            case RoleType:
                editDataFromRoleType(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Severity:
                editDataFromSeverity(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Priority:
                editDataFromPriority(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Status:
                editDataFromStatus(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Type:
                editDataFromType(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Relation:
                editDataFromRelation(nameForManipulator, className, superClassName, classTable, id);
                break;
            case Resolution:
                editDataFromResolution(nameForManipulator, className, superClassName, classTable, id);
                break;
            default:
        }
    }

    private void editDataFromResolution(String name, String className, String superClassName, ClassTable classTable, int id) {
         dataManipulator.editDataInResolution(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    private void editDataFromRelation(String name, String className, String superClassName, ClassTable classTable, int id) {
          dataManipulator.editDataInRelation(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    private void editDataFromType(String name, String className, String superClassName, ClassTable classTable, int id) {

        dataManipulator.editDataInType(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    private void editDataFromStatus(String name, String className, String superClassName, ClassTable classTable, int id) {

        dataManipulator.editDataInStatus(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    private void editDataFromPriority(String name, String className, String superClassName, ClassTable classTable, int id) {
        dataManipulator.editDataInPriority(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

    public void editDataFromTag(String tag, TagTable tagTable, int configFormId, int id) {
        tagTable.setName(id + "_" + tag);
        int configurationId = identificatorCreater.getConfigurationId(configFormId);
        dataManipulator.editTagInConfiguration(tag, configurationId, id);
        tagTable.setTag(tag);
        tagTable.setName(tag);
    }

    private void editDataFromRoleType(String name, String className, String superClassName, ClassTable classTable, int id) {

        dataManipulator.editDataInRoleType(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.RoleType, classTable.getId(), classTable);
    }



    public void editDataFromRole(String nameST, String description, int roleTypeIndex, RoleTable roleTable, int id) {


        String nameForManipulator = InputController.fillNameTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(description);
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);
        int roleId = identificatorCreater.getRoleIndexMaper().get(id);

        dataManipulator.editDataInRole(nameForManipulator, descForManipulator, typeFormManipulator, roleId);

        roleTable.setName(nameForManipulator);
        segmentLists.updateListItem(SegmentType.Role, id, roleTable);

        int roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
        mapperTableToObject.updateValueList(roleType, mapperTableToObject.getRoleToRoleTypeMapper(),
               id, nameForManipulator);

        formController.setNameToItem(identificatorCreater.getRoleSegmentIndexToFormMaper().get(id), nameForManipulator);
    }

    private void editDataFromSeverity(String name, String className, String superClassName, ClassTable classTable, int id) {
        dataManipulator.editDataInSeverity(name, className, superClassName, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

    public void editDataFromMilestone(String nameST, MilestoneTable milestoneTable, ArrayList<Integer> criterionIndex, int id) {

        String nameForManipulator = InputController.fillNameTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(milestoneTable.getDescription());
        criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        dataManipulator.editDataInMilestone(nameForManipulator, descForManipulator, criterionIndex, milestoneTable.getId());

        ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
        milestoneTable.setCriterion(dataPreparer.prepareDependencyArray(criterionIndex, segmentLists.getCriterionObservable()));

        milestoneTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator));
        if (descForManipulator != null){
            milestoneTable.setDescription(descForManipulator);
        }

        segmentLists.updateListItem(SegmentType.Milestone, id, milestoneTable);

        mapperTableToObject.updateValueList(criterionIndicies, mapperTableToObject.getMilestoneToCriterionMapper(),
                milestoneTable.getId(), milestoneTable.getName());
    }

    public void editDataFromCriterion(String nameST, String description, CriterionTable criterionTable, int id) {

        String nameForManipulator = InputController.fillNameTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        criterionTable.setName(id + "_" + nameForManipulator);
        if(descForManipulator != null){
            criterionTable.setDescription(description);
        }


        dataManipulator.editDataInCriterion(nameForManipulator, descForManipulator, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

    public void editDataFromCPR(String nameST, int roleIndex, CPRTable cprTable) {

        String nameForManipulator = InputController.fillNameTextMapper(nameST);
        int roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);
        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(nameForManipulator, roleManipulatorId, cprId);
        String cprName = dataPreparer.createTableItemIdName(cprId, nameForManipulator);
        cprTable.setName(cprName);
        cprTable.setRole(dataPreparer.prepareDependency(roleManipulatorId, segmentLists.getRoleObservable()));
        int roleId = dataModel.getRoleId(roleManipulatorId);
        segmentLists.updateListItem(SegmentType.ConfigPersonRelation, cprId, cprTable);

        mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, cprName);
    }

    public void editDataFromBranch(String nameST, boolean isMainBranch, BranchTable branchTable) {

        String nameForManipulator = InputController.fillNameTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(nameForManipulator, isMainBranch, branchId);
        String branchName = dataPreparer.createTableItemIdName(branchId, nameForManipulator);
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
        String nameForManipulator = InputController.fillNameTextMapper(name);
        String descriptionForManipulator = InputController.fillTextMapper(description);
        LocalDate date = InputController.checkDate(endDate);
        int configIdForManipulator = segmentLists.getConfigObservable().get(configurationId).getId();
        int milestoneForManipulator = segmentLists.getMilestoneObservable().get(milestonId).getId();
        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInPhase(nameForManipulator, date, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, id);

        String phaseName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        phaseTable.setName(phaseName);
        phaseTable.setConfiguration(segmentLists.getConfigObservable().get(configurationId).getName());
        phaseTable.setMilestone(segmentLists.getMilestoneObservable().get(milestonId).getName());

    }

    @Override
    public void editDataFromIteration(String name, String description, LocalDate startDate, LocalDate endDate, int configurationId, ArrayList<Integer> workUnits, IterationTable iterationTable,
                                      int id) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        String descriptionForManipulator = InputController.fillTextMapper(description);
        LocalDate startDate1 = InputController.checkDate(startDate);
        LocalDate endDate1 = InputController.checkDate(endDate);

        int configIdForManipulator = segmentLists.getConfigObservable().get(configurationId).getId();

        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInIteration(nameForManipulator, startDate1, endDate1, descriptionForManipulator, configIdForManipulator, workUnitsForManipulator, id);

        String iterationName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        iterationTable.setName(iterationName);
        iterationTable.setConfiguration(segmentLists.getConfigObservable().get(configurationId).getName());
    }

    @Override
    public void editDataFromActivity(String name, String description, ArrayList<Integer> workUnits, ActivityTable activityTable,
                                      int id) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        String descriptionForManipulator = InputController.fillTextMapper(description);
        if(descriptionForManipulator != null){
            activityTable.setDescription(descriptionForManipulator);
        }

        ArrayList<Integer> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInActivity(nameForManipulator, descriptionForManipulator, workUnitsForManipulator, id);

        String activityName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        activityTable.setName(activityName);

    }

    @Override
    public void editDataFromChange(String name, String description, boolean exist, ChangeTable changeTable,
                                     int id) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        String descriptionForManipulator = InputController.fillTextMapper(description);
        if(descriptionForManipulator != null){
            changeTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInChange(nameForManipulator, descriptionForManipulator, exist, id);

        String changeName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        changeTable.setName(changeName);

    }

    @Override
    public void editDataFromArtifact(String name, String description, boolean exist, int roleIndex, int typeIndex, LocalDate localDate, ArtifactTable artifactTable,
                                   int id) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        LocalDate date = InputController.checkDate(localDate);
        String descriptionForManipulator = InputController.fillTextMapper(description);
        if (descriptionForManipulator != null){
            artifactTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInArtifact(nameForManipulator, descriptionForManipulator, date, exist, roleIndex, typeIndex, id);

        String artifactName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        artifactTable.setName(artifactName);

    }
    @Override
    public void editDataFromWorkUnit(String name, String description, String estimatedTime, int priorityIndex, int severityIndex, int resolutionIndex,
                                     int statusIndex, String category, int typeIndex, int assigneIndex, int authorIndex, boolean selected, WorkUnitTable workUnitTable, int id) {

        String nameForManipulator = InputController.fillNameTextMapper(name);
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
        workUnitTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator));
    }

    @Override
    public void editDataFromConfiguration(String name, LocalDate createDate, int autohorIndex, boolean isRelease, ObservableList<Integer> cprsIndicies,
                                          ObservableList<Integer> branchIndicies, ObservableList<Integer> changeIndicies, int configId) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        LocalDate date = InputController.checkDate(createDate);
        int roleIdForManipulator = segmentLists.getRoleObservable().get(autohorIndex).getId();

        ArrayList<Integer> cprsForManipulator = dataPreparer.prepareIndicesForManipulator(cprsIndicies);
        ArrayList<Integer> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branchIndicies);
        ArrayList<Integer> changesForManipulator = dataPreparer.prepareIndicesForManipulator(changeIndicies);

        dataManipulator.editDataInConfiguration(nameForManipulator, date, isRelease, roleIdForManipulator,
                cprsForManipulator, branchesForManipulator, changesForManipulator, configId);
    }

    @Override
    public void editDataFromVCSTag(String name, String description, VCSTagTable tagTable, int id){
        String nameForManipulator = InputController.fillNameTextMapper(name);
        String descriptionForManipulator =InputController.fillTextMapper(description);
        if(descriptionForManipulator != null){
            tagTable.setDescription(descriptionForManipulator);
        }

        dataManipulator.editDataInVCSTag(nameForManipulator, descriptionForManipulator, id);

        String artifactName = dataPreparer.createTableItemIdName(id, nameForManipulator);
        tagTable.setName(artifactName);
    }

    @Override
    public void editDataFromCommit(String name, boolean release, int id) {
        String nameForManipulator = InputController.fillNameTextMapper(name);

        dataManipulator.editDataInCommit(nameForManipulator, release, id);

    }

    @Override
    public void editDataFromCommitedConfiguration(String name, LocalDate dateFromDatePicker, int commitedConfigurationId) {
        String nameForManipulator = InputController.fillNameTextMapper(name);
        LocalDate date = InputController.checkDate(dateFromDatePicker);
        dataManipulator.editDataInCommitedConfiguration(nameForManipulator, date, commitedConfigurationId);

    }

}


