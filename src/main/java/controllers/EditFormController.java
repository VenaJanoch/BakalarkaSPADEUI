package controllers;

import interfaces.IEditDataModel;
import interfaces.IEditFormController;
import model.DataModel;
import model.IdentificatorCreater;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import tables.*;

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



    public void editDataFromRole(String nameST, String description, RoleTable roleTable, int roleTypeIndex, int id) {

        roleTable.setName(id + "_" + nameST);
        roleTable.setDescription(description);

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(description);
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);

        dataManipulator.editDataInRole(nameForManipulator, descForManipulator, typeFormManipulator, roleTable.getId());
        segmentLists.updateListItem(SegmentType.Role, id, roleTable);

        int roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
        mapperTableToObject.updateValueList(roleType, mapperTableToObject.getRoleToRoleTypeMapper(),
                roleTable.getId(), roleTable.getName());

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

}


