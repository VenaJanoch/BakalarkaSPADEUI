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
            , ClassTable classTable, boolean exist, int id) {
        
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        
        classTable.setName(dataPreparer.createTableItemIdName(id,nameForManipulator.get(0)));
        classTable.setClassType(classString.get(0));
        classTable.setSuperType(superSting.get(0));
        classTable.setExist(exist);
        switch (segmentType) {
            case Severity:
                editDataFromSeverity(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Priority:
                editDataFromPriority(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Status:
                editDataFromStatus(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Type:
                editDataFromType(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Relation:
                editDataFromRelation(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            case Resolution:
                editDataFromResolution(nameForManipulator, nameIndicator, classIndices, superClassIndices,classString, superSting, classTable, exist, id);
                break;
            default:
        }
    }

    private void editDataFromResolution(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                        ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
         dataManipulator.editDataInResolution(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    private void editDataFromRelation(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
          dataManipulator.editDataInRelation(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    private void editDataFromType(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInType(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    private void editDataFromStatus(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInStatus(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    private void editDataFromPriority(ArrayList<String> name, ArrayList<Integer> nameIndicator,

                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInPriority(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist,id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

    public void editDataFromRoleType(ArrayList<String> name, ArrayList<Integer> nameIndicators,
                                     ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> classListIndex,
                                     ArrayList<Integer> superClassListIndex, ArrayList<String> classList, ArrayList<String> superClassList,
                                     RoleTypeTable table, boolean exist, int id){

        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

        table.setName(dataPreparer.createTableItemIdName(id,nameForManipulator.get(0)));
        table.setExist(exist);
        dataManipulator.editDataInRoleType(name, nameIndicators, descForManipulator, descriptionIndicators, classListIndex, superClassListIndex,
                classList, superClassList, exist, id);
        segmentLists.updateListItem(SegmentType.Role_Type, table.getId(), table);
    }



    public void editDataFromPerson(ArrayList<String> name, ArrayList<Integer> nameIndicator, String count, ArrayList<Integer> roleTypeIndex,
                                   ArrayList<Integer> roleTypeIndicators, PersonTable personTable, boolean exist, int id) {

        int instanceCount;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

            ArrayList<Integer> typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);

            String roleName = nameForManipulator.get(0);
            dataManipulator.editDataInRole(nameForManipulator, typeFormManipulator, nameIndicator, roleTypeIndicators, instanceCount, exist, id);

            personTable.setName(roleName);
            segmentLists.updateListItem(SegmentType.Person, id, personTable);

            ArrayList<Integer> roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
            mapperTableToObject.mapTableToObject(SegmentType.Person, roleType, new TableToObjectInstanc(personTable.getName(), personTable.getId(),
                    SegmentType.Person));
            mapperTableToObject.updateValueList(roleType, mapperTableToObject.getPersonToRoleTypeMapper(),
                    id, roleName);

            int formIndex = identificatorCreater.getRoleSegmentIndexToFormMaper().get(id);

            formController.setNameToItem(formIndex, roleName);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }


    }

    private void editDataFromSeverity(ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices,  ArrayList<Integer> superClassIndices, ArrayList<String> classString,  ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInSeverity(name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

    public void editDataFromMilestone(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String>description, ArrayList<Integer> descriptionIndicators,
                                      MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                                      ArrayList<Integer> criterionIndicators, boolean exist,  int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
       criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
       dataManipulator.editDataInMilestone(nameForManipulator,  descForManipulator, nameIndicators, descriptionIndicators, criterionIndicators, criterionIndex, exist, milestoneTable.getId());


       // milestoneTable.setCriterion(dataPreparer.prepareDependencyArray(criterionIndex, segmentLists.getCriterionObservable()));
        milestoneTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator.get(0)));
        milestoneTable.setExist(exist);

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
            CriterionTable criterionTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        String milestoneName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        criterionTable.setName(milestoneName);
//        if(descForManipulator != null){
//            criterionTable.setDescription(description.get(0));
//        }
        criterionTable.setExist(exist);

        dataManipulator.editDataInCriterion(nameForManipulator, descForManipulator, nameIndicators, descriptionIndicators,exist, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

    public void editDataFromCPR(ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<Integer> roleIndex,
                                ArrayList<Integer> roleIndicators, boolean exist, CPRTable cprTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<Integer> roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);
        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(nameForManipulator, nameIndicators, roleManipulatorId, roleIndicators, exist, cprId);
        String cprName = dataPreparer.createTableItemIdName(cprId, nameForManipulator.get(0));
        cprTable.setName(cprName);
        cprTable.setExist(exist);

        if(roleManipulatorId.size() != 0){
//            cprTable.setRole(dataPreparer.prepareDependency(roleManipulatorId.get(0), segmentLists.getRoleObservable()));
            ArrayList<Integer> roleId = dataModel.getRoleId(roleManipulatorId);
            segmentLists.updateListItem(SegmentType.Config_Person_Relation, cprId, cprTable);
            mapperTableToObject.mapTableToObject(SegmentType.Config_Person_Relation, roleId,
                    new TableToObjectInstanc(cprTable.getName(), cprTable.getId(), SegmentType.Config_Person_Relation));
            mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, cprName);
        }

    }

    public void editDataFromBranch(ArrayList<String> nameST, ArrayList<Integer> nameIndicators,  boolean isMainBranch, boolean exist, BranchTable branchTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(nameForManipulator, nameIndicators, isMainBranch, exist, branchId);
        String branchName = dataPreparer.createTableItemIdName(branchId, nameForManipulator.get(0));
        branchTable.setName(branchName);
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
    public void editDataFromPhase(ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
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
        dataManipulator.editDataInPhase(nameForManipulator, date, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicator,  endDateIndicator, descIndicator,  confIndicator,  milestoneIndicator, exist, id);

        String phaseName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        phaseTable.setName(phaseName);
        phaseTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToPhase(milestoneForManipulator, configIdForManipulator, workUnitIndicies, phaseName, id);

    }

    @Override
    public void editDataFromIteration(ArrayList<String> actName, ArrayList<LocalDate> endDateL,  ArrayList<LocalDate> startDateL, ArrayList<String> desc,
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

       dataManipulator.editDataInIteration(nameForManipulator, endDate1, startDate1, descriptionForManipulator, configIdForManipulator,
               workUnitsForManipulator, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator, exist, id);

        String iterationName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        iterationTable.setName(iterationName);
        iterationTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToIteration(configIdForManipulator, workUnitIndicies, iterationName, id);


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
                                     ArrayList<Integer> nameIndicators,  ArrayList<Integer> descIndicators,  ArrayList<Integer> workUnitIndicators,
                                     ArrayList<LocalDate> endDate,  ArrayList<Integer> endDateIndicators, ActivityTable activityTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDate);

        dataManipulator.editDataInActivity(nameForManipulator, descriptionForManipulator, workUnitsForManipulator,
                nameIndicators,  descIndicators, workUnitIndicators, endDate1, endDateIndicators, exist, id);

        activityTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Activity, workUnitIndicies, new TableToObjectInstanc(String.valueOf(id), id, SegmentType.Activity));

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
        changeTable.setExist(exist);
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
        artifactTable.setExist(exist);
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
        workUnitTable.setExist(isExist);
        workUnitTable.setName(dataPreparer.createTableItemIdName(id, nameForManipulator.get(0)));
        mapperTableToObject.mapTableToWU(assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator, typeForManipulator, resolutionForManipulator
                ,statusIndex, id, workUnitTable.getName());
        segmentLists.updateListItem(SegmentType.Work_Unit, id, workUnitTable);
        }catch (NumberFormatException e){
            e.printStackTrace();
            Alerts.showWrongNumberFormat("Estimated time");
        }
    }

    @Override
    public void editDataFromConfiguration(ArrayList<String> actName, ArrayList<LocalDate> createDate,
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

            dataManipulator.editDataInConfiguration(nameForManipulator, date, isRelease, roleIdForManipulator,
                    cprsForManipulator, changesForManipulator, cprIndicators, nameIndicator, createdIndicator,
                    authorIndicator, changeIndicator, instanceCount, exist, configId);
            String itemName = nameForManipulator.get(0);
            int formIndex = identificatorCreater.getConfigurationFormIndex(configId);
            ConfigTable configTable = new ConfigTable(itemName, "", formIndex, exist, configId);
            configTable.setExist(exist);
            segmentLists.getConfigObservable().add(configTable);

            formController.setNameToItem(formIndex, itemName);
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
            mapperTableToObject.mapTableToConfiguration(roleIdForManipulator, cprsForManipulator, null, itemName, configId);


        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

    @Override
    public void editDataFromVCSTag(ArrayList<String> name, ArrayList<String> description,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, boolean exist, int id){
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator =InputController.fillTextMapper(description);
//        if(descriptionForManipulator != null){
//            tagTable.setDescription(descriptionForManipulator.get(0));
//        }

        dataManipulator.editDataInVCSTag(nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator, exist, id);
        tagTable.setExist(exist);
        String VCSTagName = dataPreparer.createTableItemIdName(id, nameForManipulator.get(0));
        tagTable.setName(VCSTagName);
        segmentLists.getVCSTag().add(tagTable);
    }

    @Override
    public void editDataFromCommit(ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator,
                                   ArrayList<ArrayList<Integer>> branches, ArrayList<Integer> branchIndicator, boolean release, String count, boolean exist, int id) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<Integer> tagForManipulator = dataPreparer.prepareIndexForManipulator(tag);
            ArrayList<ArrayList<Integer>> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branches);
            dataManipulator.editDataInCommit(nameForManipulator, nameIndicator, tagForManipulator, tagIndicator, branchesForManipulator,
                     branchIndicator, release, instanceCount, exist, id);

            int formIndex = identificatorCreater.getCommitFormIndex(id);

            formController.setNameToItem(formIndex, nameForManipulator.get(0));
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
    public void editDataFromCommitedConfiguration(ArrayList<String> name, ArrayList<LocalDate>  dateFromDatePicker, ArrayList<Integer> nameIndicator,
                                                  ArrayList<Integer> dateIndicator, String count, boolean exist, int commitedConfigurationId) {

        int instanceCount = 0;
        try {
            instanceCount = Integer.parseInt(count);
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(dateFromDatePicker);

            dataManipulator.editDataInCommitedConfiguration(nameForManipulator, nameIndicator, date, dateIndicator, instanceCount, exist, commitedConfigurationId);

            int formIndex = identificatorCreater.getCommitedConfigurationFormIndex(commitedConfigurationId);
            formController.setNameToItem(formIndex, nameForManipulator.get(0));
            formController.setItemInstanceCount(formIndex, instanceCount);
            formController.setItemColor(formIndex, exist);
        }catch (NumberFormatException e){
            Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

}


