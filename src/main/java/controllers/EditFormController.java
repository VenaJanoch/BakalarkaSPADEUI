package controllers;

import interfaces.IEditDataModel;
import interfaces.IEditFormController;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void editDataFromClass(SegmentType segmentType,ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {
        
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        
        classTable.setName(dataPreparer.createTableItemIdName(id,nameForManipulator.get(0)));
        classTable.setClassType(classString.get(0));
        classTable.setSuperType(superSting.get(0));

        switch (segmentType) {
            case RoleType:
                editDataFromRoleType(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Severity:
                editDataFromSeverity(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Priority:
                editDataFromPriority(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Status:
                editDataFromStatus(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Type:
                editDataFromType(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Relation:
                editDataFromRelation(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            case Resolution:
                editDataFromResolution(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, id);
                break;
            default:
        }
    }

    private void editDataFromResolution(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                        ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {
         dataManipulator.editDataInResolution(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    private void editDataFromRelation(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {
          dataManipulator.editDataInRelation(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    private void editDataFromType(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {

        dataManipulator.editDataInType(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    private void editDataFromStatus(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {

        dataManipulator.editDataInStatus(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    private void editDataFromPriority(ArrayList<String> name, ArrayList<Integer> nameIndicator,

                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {
        dataManipulator.editDataInPriority(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

    public void editDataFromTag(String tag, TagTable tagTable, int configFormId, int id) {
        tagTable.setName(id + "_" + tag);
        int configurationId = identificatorCreater.getConfigurationId(configFormId);
        dataManipulator.editTagInConfiguration(tag, configurationId, id);
        tagTable.setTag(tag);
        tagTable.setName(tag);
    }

    private void editDataFromRoleType(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
                                        , ClassTable classTable, int id) {

        dataManipulator.editDataInRoleType(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.RoleType, classTable.getId(), classTable);
    }



    public void editDataFromRole(ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> descriptionIndicator,
                                 String count, ArrayList<Integer> roleTypeIndex, ArrayList<Integer> roleTypeIndicators, RoleTable roleTable, int id) {

        int instanceCount;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
            ArrayList<Integer> typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);

            String roleName = nameForManipulator.get(0);
            dataManipulator.editDataInRole(nameForManipulator, descForManipulator, typeFormManipulator,nameIndicator, descriptionIndicator,
                    roleTypeIndicators, instanceCount, id);

            roleTable.setName(roleName);
            segmentLists.updateListItem(SegmentType.Role, id, roleTable);

            ArrayList<Integer> roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
            mapperTableToObject.mapTableToObject(SegmentType.Role, roleType, new TableToObjectInstanc(roleTable.getName(), roleTable.getId(),
                    SegmentType.Role));
            mapperTableToObject.updateValueList(roleType, mapperTableToObject.getRoleToRoleTypeMapper(),
                    id, roleName);

            int formIndex = identificatorCreater.getRoleSegmentIndexToFormMaper().get(id);

            formController.setNameToItem(formIndex, roleName);
            formController.setItemInstanceCount(formIndex, instanceCount);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }


    }

    private void editDataFromSeverity(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, int id) {
        dataManipulator.editDataInSeverity(name, nameIndicator, classIndices, superClassIndices, classString, superSting, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

    public void editDataFromMilestone(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
                                      MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                                      ArrayList<Integer> criterionIndicators,  int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
       criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
       dataManipulator.editDataInMilestone(nameForManipulator,  descForManipulator, nameIndicators, descriptionIndicators, criterionIndicators, criterionIndex, milestoneTable.getId());


       // milestoneTable.setCriterion(dataPreparer.prepareDependencyArray(criterionIndex, segmentLists.getCriterionObservable()));
        milestoneTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator.get(0)));


        if (criterionIndex.size() != 0){
            ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
            mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getName(), id,
                    SegmentType.Milestone));
            mapperTableToObject.updateValueList(criterionIndicies, mapperTableToObject.getMilestoneToCriterionMapper(),
                    milestoneTable.getId(), milestoneTable.getName());
        }

        segmentLists.updateListItem(SegmentType.Milestone, id, milestoneTable);


    }

    public void editDataFromCriterion(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
            CriterionTable criterionTable, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        String milestoneName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        criterionTable.setName(milestoneName);
//        if(descForManipulator != null){
//            criterionTable.setDescription(description.get(0));
//        }


        dataManipulator.editDataInCriterion(nameForManipulator, descForManipulator, nameIndicators, descriptionIndicators, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

    public void editDataFromCPR(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex,
                                ArrayList<Integer> roleIndicators, CPRTable cprTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<Integer> roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);
        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(nameForManipulator, nameIndicators, roleManipulatorId, roleIndicators, cprId);
        String cprName = dataPreparer.createTableItemIdName(cprId, nameForManipulator.get(0));
        cprTable.setName(cprName);

        if(roleManipulatorId.size() != 0){
            cprTable.setRole(dataPreparer.prepareDependency(roleManipulatorId.get(0), segmentLists.getRoleObservable()));
            ArrayList<Integer> roleId = dataModel.getRoleId(roleManipulatorId);
            segmentLists.updateListItem(SegmentType.ConfigPersonRelation, cprId, cprTable);
            mapperTableToObject.mapTableToObject(SegmentType.ConfigPersonRelation, roleId,
                    new TableToObjectInstanc(cprTable.getName(), cprTable.getId(), SegmentType.ConfigPersonRelation));
            mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, cprName);
        }

    }

    public void editDataFromBranch(ArrayList<String> nameST, ArrayList<Integer> nameIndicators,  boolean isMainBranch, BranchTable branchTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(nameForManipulator, nameIndicators, isMainBranch, branchId);
        String branchName = dataPreparer.createTableItemIdName(branchId, nameForManipulator.get(0));
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
    public void editDataFromPhase(ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                                  ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                  ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                  ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator,
                                  PhaseTable phaseTable, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> date = InputController.checkDate(endDateL);
        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);
        ArrayList<Integer> milestoneForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);
        dataManipulator.editDataInPhase(nameForManipulator, date, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicator,  endDateIndicator, descIndicator,  confIndicator,  milestoneIndicator, id);

        String phaseName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        phaseTable.setName(phaseName);

        mapperTableToObject.mapTableToPhase(milestoneForManipulator, configIdForManipulator, phaseName , id);

    }

    @Override
    public void editDataFromIteration(ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                      ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                      ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                      ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator,
                                      IterationTable iterationTable, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> startDate1 = InputController.checkDate(startDateL);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDateL);

        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);
       dataManipulator.editDataInIteration(nameForManipulator, startDate1, endDate1, descriptionForManipulator, configIdForManipulator,
               workUnitsForManipulator, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator, id);

        String iterationName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        iterationTable.setName(iterationName);

        mapperTableToObject.mapTableToObject(SegmentType.Iteration, configIdForManipulator,
                new TableToObjectInstanc(iterationName, id, SegmentType.Iteration));

    }

    @Override
   public void editDataFromProject(ArrayList<String> name, ArrayList<LocalDate> startDate, ArrayList<LocalDate> endDate, ArrayList<String> desc,
                             ArrayList<ArrayList<Integer>> workUnit, ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators,
                             ArrayList<Integer> date1Indicators, ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators){

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> startDate1 = InputController.checkDate(startDate);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDate);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnit);
        dataManipulator.editDataInProject(nameForManipulator, startDate1, endDate1, descriptionForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicators, date1Indicators, date2Indicators, descIndicators);


    }


    @Override
    public void editDataFromActivity(ArrayList<String> name, ArrayList<String> description,  ArrayList<ArrayList<Integer>> workUnits,
                                     ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators, ActivityTable activityTable, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        dataManipulator.editDataInActivity(nameForManipulator, descriptionForManipulator, workUnitsForManipulator,
                nameIndicators,  descIndicators, workUnitIndicators, id);

        String activityName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        activityTable.setName(activityName);

    }

    @Override
    public void editDataFromChange(ArrayList<String> name, ArrayList<Integer> nameIndicator,  ArrayList<String> description, ArrayList<Integer> artifact,
                                   ArrayList<Integer> descriptionIndicator, boolean exist, ChangeTable changeTable,
                                     int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);
        ArrayList<Integer> artifactForManipulator = dataPreparer.prepareIndexForManipulator(artifact);
//        if(descriptionForManipulator != null){
//            changeTable.setDescription(descriptionForManipulator.get(0));
//        }

        dataManipulator.editDataInChange(nameForManipulator, descriptionForManipulator, artifactForManipulator, nameIndicator, descriptionIndicator, exist, id);

        String changeName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        changeTable.setName(changeName);
        ArrayList<Integer> artifactId = dataModel.getArtifactId(artifactForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Change, artifactId, new TableToObjectInstanc(changeName, id, SegmentType.Change));

    }

    @Override
    public void editDataFromArtifact( ArrayList<String> name, ArrayList<String> description, boolean exist,
                                      ArrayList<Integer> roleIndex, ArrayList<Integer> typeIndex, ArrayList<LocalDate> localDate,
                                      ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                                      ArrayList<Integer> roleIndicator,  ArrayList<Integer> typeIndicator, ArrayList<Integer> dateIndicator,
                                     ArtifactTable artifactTable, String count, int id) {
        int instanceCount = 1;
        try {
            instanceCount = Integer.parseInt(count);

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<LocalDate> date = InputController.checkDate(localDate);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        dataManipulator.editDataInArtifact(nameForManipulator, nameIndicator, descriptionForManipulator, descriptionIndicator,
                date, dateIndicator, exist, roleIndex, typeIndex, roleIndicator, typeIndicator, instanceCount, id);

        String artifactName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        artifactTable.setName(artifactName);
        int formIndex = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(id);
        formController.setNameToItem(formIndex, nameForManipulator.get(0));
        formController.setItemInstanceCount(formIndex, instanceCount);
        formController.setItemColor(formIndex, exist);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }
    @Override
    public void editDataFromWorkUnit(ArrayList<String> name, ArrayList<String> description, ArrayList<String> category,
                                     ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                     ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                     ArrayList<String> estimatedTime, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                     ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                     ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                     ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations,  ArrayList<ArrayList<Integer>> workUnits, WorkUnitTable workUnitTable, int id) {

        try {
            ArrayList<Double> estimateForDataManipulator = new ArrayList<>();
        if (estimatedTime.size() != 0) {
            estimateForDataManipulator = InputController.isDoubleNumber(estimatedTime);
        }

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> categoryForManipulator = InputController.fillTextMapper(category);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        ArrayList<Integer> relationForManipulator = dataPreparer.prepareIndexForManipulator(relations);


        ArrayList<Integer> assigneForManipulator = dataPreparer.prepareIndexForManipulator(assigneIndex);
        ArrayList<Integer> authorForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);
        ArrayList<Integer> priorityForManipulator = dataPreparer.prepareIndexForManipulator(priorityIndex);
        ArrayList<Integer> severityForManipulator = dataPreparer.prepareIndexForManipulator(severityIndex);
        ArrayList<Integer> typeForManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);
        ArrayList<Integer> resolutionForManipulator = dataPreparer.prepareIndexForManipulator(resolutionIndex);
        ArrayList<Integer> statusForManipulator = dataPreparer.prepareIndexForManipulator(statusIndex);
        dataManipulator.editDataInWorkUnit(nameForManipulator, descriptionForManipulator, categoryForManipulator,
                assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator,
                typeForManipulator, resolutionForManipulator, statusForManipulator,
                estimateForDataManipulator, nameIndicator,  descriptionIndicator,  categoryIndicator,
                assigneIndicator, authorIndicator, priorityIndicator,  severityIndicator,
                typeIndicator, resolutionIndicator, statusIndicator, estimateIndicator, isExist, relationForManipulator, workUnitsForManipulator, id);

        workUnitTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator.get(0)));
        mapperTableToObject.mapTableToWU(assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator, typeForManipulator, resolutionForManipulator
                ,statusIndex, id, workUnitTable.getName());
        segmentLists.updateListItem(SegmentType.WorkUnit, id, workUnitTable);
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongNumberFormat("Estimated time");
        }
    }

    @Override
    public void editDataFromConfiguration(ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                          boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                          ArrayList<ArrayList<Integer>> branches, ArrayList<ArrayList<Integer>> changeIndexs,
                                          ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> createdIndicator,
                                          ArrayList<Integer> authorIndicator, ArrayList<Integer> branchIndicator, ArrayList<Integer> changeIndicator,
                                          String count, int configId) {

        int instanceCount;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
            ArrayList<LocalDate> date = InputController.checkDate(createDate);
            ArrayList<Integer> roleIdForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);

            ArrayList<ArrayList<Integer>> cprsForManipulator = dataPreparer.prepareIndicesForManipulator(cprs);
            ArrayList<ArrayList<Integer>> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branches);
            ArrayList<ArrayList<Integer>> changesForManipulator = dataPreparer.prepareIndicesForManipulator(changeIndexs);

            dataManipulator.editDataInConfiguration(nameForManipulator, date, isRelease, roleIdForManipulator,
                    cprsForManipulator, branchesForManipulator, changesForManipulator, cprIndicators, nameIndicator, createdIndicator,
                    authorIndicator,  branchIndicator, changeIndicator, instanceCount, configId);
            String itemName = nameForManipulator.get(0);
            int formIndex = identificatorCreater.getConfigurationFormIndex(configId);
            ConfigTable configTable = new ConfigTable(itemName, "", formIndex, configId);
            segmentLists.getConfigObservable().add(formIndex, configTable);

            formController.setNameToItem(formIndex, itemName);
            formController.setItemInstanceCount(formIndex, instanceCount);

            mapperTableToObject.mapTableToConfiguration(roleIdForManipulator, branchesForManipulator, cprsForManipulator, itemName, configId);


        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

    @Override
    public void editDataFromVCSTag(ArrayList<String> name, ArrayList<String> description,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, int id){
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator =InputController.fillTextMapper(description);
//        if(descriptionForManipulator != null){
//            tagTable.setDescription(descriptionForManipulator.get(0));
//        }

        dataManipulator.editDataInVCSTag(nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator, id);

        String VCSTagName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        tagTable.setName(VCSTagName);
    }

    @Override
    public void editDataFromCommit(ArrayList<String> name, ArrayList<Integer> nameIndicator, boolean release, String count, int id) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

            dataManipulator.editDataInCommit(nameForManipulator, nameIndicator, release, instanceCount, id);

            int formIndex = identificatorCreater.getCommitFormIndex(id);

            formController.setNameToItem(formIndex, nameForManipulator.get(0));
            formController.setItemInstanceCount(formIndex, instanceCount);

        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }

    }

    @Override
    public void editCoordsInCommit(double x, double y, int id){
        dataManipulator.editCoordinatesInCommit((int)x, (int)y, id);
    }

    @Override
    public void editCoordsInCommitedConfiguration(double x, double y, int id){
        dataManipulator.editCoordinatesInCommitedConfiguration((int)x, (int)y, id);
    }

    @Override
    public void editCoordsInConfiguration(double x, double y, int id){
        dataManipulator.editCoordinatesInConfiguration((int)x, (int)y, id);
    }

    @Override
    public void editCoordsInArtifact(double x, double y, int id){
        dataManipulator.editCoordinatesInArtifact((int)x, (int)y, id);
    }

    @Override
    public void editCoordsInRole(double x, double y, int id){
        dataManipulator.editCoordinatesInRole((int)x, (int)y, id);
    }


    @Override
    public void editDataFromCommitedConfiguration(ArrayList<String> name, ArrayList<LocalDate>  dateFromDatePicker, ArrayList<Integer> nameIndicator,
                                                  ArrayList<Integer> dateIndicator, String count, int commitedConfigurationId) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(dateFromDatePicker);

            dataManipulator.editDataInCommitedConfiguration(nameForManipulator, nameIndicator, date, dateIndicator, instanceCount, commitedConfigurationId);

            int formIndex = identificatorCreater.getCommitedConfigurationFormIndex(commitedConfigurationId);
            formController.setNameToItem(formIndex, nameForManipulator.get(0));
            formController.setItemInstanceCount(formIndex, instanceCount);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

}


