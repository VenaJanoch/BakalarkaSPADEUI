package controllers.formControllers;

import controllers.DataPreparer;
import controllers.InputController;
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

    public void editDataFromClass(SegmentType segmentType, String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        
        classTable.setAlias(alias);
        classTable.setClassType(classString.get(0));
        classTable.setSuperType(superSting.get(0));
        classTable.setExist(exist);
        switch (segmentType) {
            case Severity:
                editDataFromSeverity(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Priority:
                editDataFromPriority(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Status:
                editDataFromStatus(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Type:
                editDataFromType(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Relation:
                editDataFromRelation(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Resolution:
                editDataFromResolution(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            default:
        }
    }

    private void editDataFromResolution(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                        ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
         dataManipulator.editDataInResolution(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    private void editDataFromRelation(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
          dataManipulator.editDataInRelation(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    private void editDataFromType(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInType(alias,name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    private void editDataFromStatus(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInStatus(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    private void editDataFromPriority(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,

                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInPriority(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist,id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

    public void editDataFromRoleType(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicators,
                                     ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> classListIndex,
                                     ArrayList<Integer> superClassListIndex, ArrayList<String> classList, ArrayList<String> superClassList,
                                     RoleTypeTable table, boolean exist, int id){

        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

        table.setAlias(alias);
        table.setExist(exist);
        dataManipulator.editDataInRoleType(alias, nameForManipulator, nameIndicators, descForManipulator, descriptionIndicators, classListIndex, superClassListIndex,
                classList, superClassList, exist, id);
        segmentLists.updateListItem(SegmentType.Role_Type, table.getId(), table);
    }



    public void editDataFromPerson(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, String count, ArrayList<Integer> roleTypeIndex,
                                   ArrayList<Integer> roleTypeIndicators, PersonTable personTable, boolean exist, int id) {

        int instanceCount;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<Integer> typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);

            dataManipulator.editDataInRole(alias, nameForManipulator, typeFormManipulator, nameIndicator, roleTypeIndicators, instanceCount, exist, id);

            personTable.setAlias(alias);
            segmentLists.updateListItem(SegmentType.Person, id, personTable);

            ArrayList<Integer> roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
            mapperTableToObject.mapTableToObject(SegmentType.Person, roleType, new TableToObjectInstanc(personTable.getAlias(), personTable.getId(),
                    SegmentType.Person));
            mapperTableToObject.updateValueList(roleType, mapperTableToObject.getPersonToRoleTypeMapper(),
                    id, alias);

            int formIndex = identificatorCreater.getRoleSegmentIndexToFormMaper().get(id);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }


    }

    private void editDataFromSeverity(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInSeverity(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

    public void editDataFromMilestone(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
                                      MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                                      ArrayList<Integer> criterionIndicators, boolean exist,  int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
       criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
       dataManipulator.editDataInMilestone(alias, nameForManipulator,  descForManipulator, nameIndicators, descriptionIndicators, criterionIndicators, criterionIndex, exist, milestoneTable.getId());


       // milestoneTable.setCriterion(dataPreparer.prepareDependencyArray(criterionIndex, segmentLists.getCriterionObservable()));
        milestoneTable.setAlias(alias);
        milestoneTable.setExist(exist);

        if (criterionIndex.size() != 0){
            ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
            mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getAlias(), id,
                    SegmentType.Milestone));
            mapperTableToObject.updateValueList(criterionIndicies, mapperTableToObject.getMilestoneToCriterionMapper(),
                    milestoneTable.getId(), milestoneTable.getAlias());
        }

        segmentLists.updateListItem(SegmentType.Milestone, id, milestoneTable);


    }

    public void editDataFromCriterion(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
            CriterionTable criterionTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        criterionTable.setAlias(alias);
        criterionTable.setExist(exist);

        dataManipulator.editDataInCriterion(alias, nameForManipulator, descForManipulator, nameIndicators, descriptionIndicators,exist, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

    public void editDataFromCPR(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex,
                                ArrayList<Integer> roleIndicators, boolean exist, CPRTable cprTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<Integer> roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);
        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(alias, nameForManipulator, nameIndicators, roleManipulatorId, roleIndicators, exist, cprId);
        cprTable.setAlias(alias);
        cprTable.setExist(exist);

        if(roleManipulatorId.size() != 0){
//            cprTable.setRole(dataPreparer.prepareDependency(roleManipulatorId.get(0), segmentLists.getRoleObservable()));
            ArrayList<Integer> roleId = dataModel.getRoleId(roleManipulatorId);
            segmentLists.updateListItem(SegmentType.Config_Person_Relation, cprId, cprTable);
            mapperTableToObject.mapTableToObject(SegmentType.Config_Person_Relation, roleId,
                    new TableToObjectInstanc(cprTable.getAlias(), cprTable.getId(), SegmentType.Config_Person_Relation));
            mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, alias);
        }

    }

    public void editDataFromBranch(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators,  boolean isMainBranch, boolean exist, BranchTable branchTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(alias, nameForManipulator, nameIndicators, isMainBranch, exist, branchId);
        branchTable.setAlias(alias);
        if (isMainBranch) {
            branchTable.setMain("YES");
        } else {
            branchTable.setMain("NO");
        }
        branchTable.setMainBool(isMainBranch);
        branchTable.setExist(exist);
        segmentLists.updateListItem(SegmentType.Branch, branchId, branchTable);
    }

    @Override
    public void editDataFromPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                                  ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                  ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                  ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator,
                                  PhaseTable phaseTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> date = InputController.checkDate(endDateL);
        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);
        ArrayList<Integer> milestoneForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);
        dataManipulator.editDataInPhase(alias, nameForManipulator, date, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicator,  endDateIndicator, descIndicator,  confIndicator,  milestoneIndicator, exist, id);

        phaseTable.setAlias(alias);
        phaseTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToPhase(milestoneForManipulator, configIdForManipulator, workUnitIndicies, alias, id);

    }

    @Override
    public void editDataFromIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                      ArrayList<Integer> confIndex,  ArrayList<ArrayList<Integer>> workUnitIndexList,
                                      ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                      ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator,
                                      IterationTable iterationTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> startDate1 = InputController.checkDate(startDateL);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDateL);

        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);
        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);

       dataManipulator.editDataInIteration(alias, nameForManipulator, endDate1, startDate1, descriptionForManipulator, configIdForManipulator,
               workUnitsForManipulator, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator, exist, id);

        iterationTable.setAlias(alias);
        iterationTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToIteration(configIdForManipulator, workUnitIndicies, alias, id);


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
    public void editDataFromActivity(String alias, ArrayList<String> name, ArrayList<String> description,  ArrayList<ArrayList<Integer>> workUnits,
                                     ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators,
                                     ArrayList<LocalDate> endDate,  ArrayList<Integer> endDateIndicators, ActivityTable activityTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDate);

        dataManipulator.editDataInActivity(alias, nameForManipulator, descriptionForManipulator, workUnitsForManipulator,
                nameIndicators,  descIndicators, workUnitIndicators, endDate1, endDateIndicators, exist, id);

        activityTable.setExist(exist);
        activityTable.setAlias(alias);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Activity, workUnitIndicies, new TableToObjectInstanc(alias, id, SegmentType.Activity));

    }

    @Override
    public void editDataFromChange(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,  ArrayList<String> description, ArrayList<Integer> artifact,
                                   ArrayList<Integer> descriptionIndicator, boolean exist, ChangeTable changeTable,
                                     int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);
        ArrayList<Integer> artifactForManipulator = dataPreparer.prepareIndexForManipulator(artifact);

        dataManipulator.editDataInChange(alias, nameForManipulator, descriptionForManipulator, artifactForManipulator, nameIndicator, descriptionIndicator, exist, id);
        changeTable.setExist(exist);
        changeTable.setAlias(alias);
        ArrayList<Integer> artifactId = dataModel.getArtifactId(artifactForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Change, artifactId, new TableToObjectInstanc(alias, id, SegmentType.Change));

    }

    @Override
    public void editDataFromArtifact(String alias,  ArrayList<String> name, ArrayList<String> description, boolean exist,
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

        dataManipulator.editDataInArtifact(alias, nameForManipulator, nameIndicator, descriptionForManipulator, descriptionIndicator,
                date, dateIndicator, exist, roleIndex, typeIndex, roleIndicator, typeIndicator, instanceCount, id);

        artifactTable.setAlias(alias);
        artifactTable.setExist(exist);
        int formIndex = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(id);
        formController.setNameToItem(formIndex, alias);
        formController.setItemInstanceCount(formIndex, instanceCount);
        formController.setItemColor(formIndex, exist);

        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }
    @Override
    public void editDataFromWorkUnit(String alias, ArrayList<String> name, ArrayList<String> description, ArrayList<String> category,
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
        dataManipulator.editDataInWorkUnit(alias, nameForManipulator, descriptionForManipulator, categoryForManipulator,
                assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator,
                typeForManipulator, resolutionForManipulator, statusForManipulator,
                estimateForDataManipulator, nameIndicator,  descriptionIndicator,  categoryIndicator,
                assigneIndicator, authorIndicator, priorityIndicator,  severityIndicator,
                typeIndicator, resolutionIndicator, statusIndicator, estimateIndicator, isExist, relationForManipulator, workUnitsForManipulator, id);
        workUnitTable.setExist(isExist);
        workUnitTable.setAlias(alias);
        mapperTableToObject.mapTableToWU(assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator, typeForManipulator, resolutionForManipulator
                ,statusIndex, id, workUnitTable.getAlias());
        segmentLists.updateListItem(SegmentType.Work_Unit, id, workUnitTable);
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongNumberFormat("Estimated time");
        }
    }

    @Override
    public void editDataFromConfiguration(String alias, ArrayList<String> actName, ArrayList<LocalDate> createDate,
                                          boolean isRelease, ArrayList<Integer> authorIndex, ArrayList<ArrayList<Integer>> cprs,
                                          ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator,
                                          ArrayList<Integer> createdIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> changeIndicator,
                                          String count, boolean exist, int configId) {

        int instanceCount;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
            ArrayList<LocalDate> date = InputController.checkDate(createDate);
            ArrayList<Integer> roleIdForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);

            ArrayList<ArrayList<Integer>> cprsForManipulator = dataPreparer.prepareIndicesForManipulator(cprs);

            ArrayList<ArrayList<Integer>> changesForManipulator = dataPreparer.prepareIndicesForManipulator(changeIndexs);

            dataManipulator.editDataInConfiguration(alias, nameForManipulator, date, isRelease, roleIdForManipulator,
                    cprsForManipulator, changesForManipulator, cprIndicators, nameIndicator, createdIndicator,
                    authorIndicator, changeIndicator, instanceCount, exist, configId);
            int formIndex = identificatorCreater.getConfigurationFormIndex(configId);
            ConfigTable configTable = new ConfigTable(alias, "", formIndex, exist, configId);
            configTable.setExist(exist);
            segmentLists.updateListItem(SegmentType.Configuration,configId, configTable);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
            mapperTableToObject.mapTableToConfiguration(roleIdForManipulator, cprsForManipulator, changesForManipulator, alias, configId);


        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

    @Override
    public void editDataFromVCSTag(String alias, ArrayList<String> name, ArrayList<String> description,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, boolean exist, int id){
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator =InputController.fillTextMapper(description);
//        if(descriptionForManipulator != null){
//            tagTable.setDescription(descriptionForManipulator.get(0));
//        }

        dataManipulator.editDataInVCSTag(alias, nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator, exist, id);
        tagTable.setExist(exist);
        tagTable.setAlias(alias);
        segmentLists.getVCSTag().add(tagTable);
    }

    @Override
    public void editDataFromCommit(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator,
                                   ArrayList<ArrayList<Integer>> branches, ArrayList<Integer> branchIndicator, boolean release, String count, boolean exist, int id) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<Integer> tagForManipulator = dataPreparer.prepareIndexForManipulator(tag);
            ArrayList<ArrayList<Integer>> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branches);
            dataManipulator.editDataInCommit(alias, nameForManipulator, nameIndicator, tagForManipulator, tagIndicator, branchesForManipulator,
                     branchIndicator, release, instanceCount, exist, id);

            int formIndex = identificatorCreater.getCommitFormIndex(id);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);

            ArrayList<Integer> branchIndicies = dataModel.getBranchIndices(branchesForManipulator);
            ArrayList<Integer> tagIndicies = dataModel.getVCSTagIndices(tagForManipulator);
            mapperTableToObject.mapTableToCommit(branchIndicies, tagIndicies, String.valueOf(id), id);

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
    public void editDataFromCommitedConfiguration(String alias, ArrayList<String> name, ArrayList<LocalDate>  dateFromDatePicker, ArrayList<Integer> nameIndicator,
                                                  ArrayList<Integer> dateIndicator, String count, boolean exist, int commitedConfigurationId) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(dateFromDatePicker);

            dataManipulator.editDataInCommitedConfiguration(alias, nameForManipulator, nameIndicator, date, dateIndicator, instanceCount, exist, commitedConfigurationId);

            int formIndex = identificatorCreater.getCommitedConfigurationFormIndex(commitedConfigurationId);
            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

}


