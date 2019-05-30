package controllers.formControllers;

import controllers.DataPreparer;
import controllers.InputController;
import graphics.canvas.CanvasItem;
import interfaces.IFormDataController;
import interfaces.ISaveDataModel;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;
import tables.*;

import java.time.LocalDate;
import java.util.*;

public class FormDataController implements IFormDataController {

    private SegmentLists lists;
    private DataManipulator dataManipulator;
    private FormFillController formFillController;
    private ISaveDataModel saveDataModel;
    private FormController formController;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DataPreparer dataPreparer;


    public FormDataController(FormController formController, SegmentLists lists, MapperTableToObject mapperTableToObject, DataModel dataModel,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer) {
        this.formController = formController;
        this.lists = lists;
        this.saveDataModel = dataModel.getSaveDataModel();
        this.dataManipulator = dataModel.getDataManipulator();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.dataPreparer = dataPreparer;
    }


    public int saveDataFromPhaseForm(TableView<PhaseTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Phase);
        PhaseTable table = new PhaseTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        return id;
    }


    public int saveDataFromIterationForm(TableView<IterationTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Iteration);
        IterationTable table = new IterationTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        return id;
    }

    public int saveDataFromActivityForm(TableView<ActivityTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Activity);
        ActivityTable table = new ActivityTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        return id;
    }

    public int saveDataFromWorkUnit(TableView<WorkUnitTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Work_Unit);
        WorkUnitTable table = new WorkUnitTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getWorkUnitsObservable().add(table);
        return id;
    }

    public boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                             ArrayList<Integer> cprIndex, Map<Integer, CanvasItem> itemIndexList, boolean isNew, int indexForm) {


        String nameForManipulator = InputController.fillTextMapper(actName);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();
        authorIndex = dataPreparer.prepareIndexForManipulator(authorIndex);
        //  branchIndex = dataPreparer.prepareIndicesForManipulator(branchIndex);
        //  cprIndex = dataPreparer.prepareIndicesForManipulator(cprIndex);

        for (int index : itemIndexList.keySet()) {
            if (identificatorCreater.getRoleIndexToIdMaper().get(index) != null) {
                changeList.add(identificatorCreater.getRoleIndexToIdMaper().get(index));
            } else {
                artefactList.add(identificatorCreater.getArtifactIndexToIdMaper().get(index));
            }
        }
        String release = "NO";
        if (isRelease) {
            release = "YES";
        }
        int configIndex = identificatorCreater.getConfigurationId(indexForm);
        //editDataModel.editDataInConfiguration(nameForManipulator, createDate, isRelease, authorIndex , branchIndex,cprIndex,
        //      changeList, configIndex );
        String idName = identificatorCreater.getConfigurationId(indexForm) + "_" + actName;
        ConfigTable configTable = new ConfigTable(idName, release, indexForm, true, configIndex);
        if (isNew) {
            lists.getConfigObservable().add(configTable);
      //      formController.setNewItemToConfigurationTable(idName, release, indexForm, configIndex);
        } else {
            lists.getConfigObservable().remove(configIndex + 1);
            lists.getConfigObservable().add(configIndex + 1, configTable);
         //   setEditItemInConfigurationTable(configTable);
        }

     //   formController.setConfigurationFormToTableForm();
        return true;
    }



    public int saveDataFromChangeForm(TableView<ChangeTable> tableTV, boolean isExist) {

            int id = formController.createTableItem(SegmentType.Change);
            ChangeTable table = new ChangeTable(String.valueOf(id), isExist, id);

            tableTV.getItems().add(table);
            tableTV.sort();
            int lastItem = tableTV.getItems().size();
            tableTV.getSelectionModel().select(lastItem - 1);
            lists.getChangeObservable().add(table);
            return id;

    }

    public boolean saveDataFromArtifact(String actName, LocalDate createdDate, String type, String desc, int authorIndex,
                                        int typeIndex, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        formController.setItemColor(indexForm, selected);

        //  editDataModel.editDataInArtifact(nameForManipulator, descForManipulator, createdDate, selected,
        //         dataPreparer.prepareIndexForManipulator(authorIndex), typeIndex, identificatorCreater.getArtifactIndex(indexForm));
        //   lists.getArtifactObservable().add(actName);
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
    }


    public int saveDataFromVCSTagForm(TableView<VCSTagTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.VCSTag);
        VCSTagTable table = new VCSTagTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getVCSTag().add(table);
        return id;
    }

    public void createArtifactToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewArtifacToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Artifact, startId, new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }


    public void createCommitToCommitedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createCommitToCommitedConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Committed_Configuration, SegmentType.Commit, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Committed_Configuration));
    }

    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createCommitedConfigurationToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Committed_Configuration, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }


    public void createNewPersonToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }


    public void createNewPersonToArtifactRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToArtifactRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Artifact, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Artifact));
    }

    public void createNewPersonToCommitRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToCommitRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Commit, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Commit));
    }

    public void createNewPersonToCommittedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToCommittedConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Committed_Configuration, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Committed_Configuration));
    }

    @Override
    public void createCopyTableItem(ArrayList<BasicTable> list, TableView tableView, SegmentType segmentType) {
        switch (segmentType){
            case Activity:
                for (BasicTable table : list){
                    formFillController.fillActivityForm(tableView, table.getId() );
                }
                break;
            case Phase:
                for (BasicTable table : list){
                    formFillController.fillPhaseForm(tableView, table.getId() );
                }
                break;
            case Iteration:
                for (BasicTable table : list){
                    formFillController.fillIterationForm(tableView, table.getId() );
                }
                break;
            case Change:
                for (BasicTable table : list){
                    formFillController.fillChangeForm(tableView, table.getId() );
                }
                break;
            case VCSTag:
                for (BasicTable table : list){
                    formFillController.fillVCSTagForm(tableView, table.getId() );
                }
                break;
            case Config_Person_Relation:
                for (BasicTable table : list){
                    formFillController.fillCPRForm(tableView, table.getId() );
                }
                break;
            case Branch:
                for (BasicTable table : list){
                    formFillController.fillBranchForm(tableView, table.getId() );
                }
                break;
            case Criterion:
                for (BasicTable table : list){
                    formFillController.fillCriterionForm(tableView, table.getId() );
                }
                break;
            case Milestone:
                for (BasicTable table : list){
                    formFillController.fillMilestoneForm(tableView, table.getId() );
                }
                break;
            case Priority:
                for (BasicTable table : list){
                    formFillController.fillPriorityForm(tableView, table.getId() );
                }
                break;
            case Severity:
                for (BasicTable table : list){
                    formFillController.fillSeverityForm(tableView, table.getId() );
                }
                break;
            case Resolution:
                for (BasicTable table : list){
                    formFillController.fillResolutionForm(tableView, table.getId() );
                }
                break;
            case Relation:
                for (BasicTable table : list){
                    formFillController.fillRelationForm(tableView, table.getId() );
                }
                break;
            case Status:
                for (BasicTable table : list){
                    formFillController.fillStatusForm(tableView, table.getId() );
                }
                break;
            case Type:
                for (BasicTable table : list){
                    formFillController.fillTypeForm(tableView, table.getId() );
                }
            case Role_Type:
                for (BasicTable table : list){
                    formFillController.fillRoleTypeForm(tableView, table.getId() );
                }
            case Work_Unit:
                for (BasicTable table : list){
                    formFillController.fillWorkUnitForm(tableView, table.getId() );
                }
                break;
                default:
        }
    }


    public int saveDataFromBranch(TableView<BranchTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Branch);
        BranchTable table = new BranchTable(String.valueOf(id), "", true, isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getBranchObservable().add(table);
        return id;

    }

    public int saveDataFromCPR(TableView<CPRTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Config_Person_Relation);
        CPRTable table = new CPRTable(String.valueOf(id), "", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getCPRObservable().add(table);
        return id;

    }

    public int saveDataFromCriterionForm(TableView<CriterionTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Criterion);
        CriterionTable table = new CriterionTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getCriterionObservable().add(table);
        return id;
    }

    public int saveDataFromMilestoneForm(TableView<MilestoneTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Milestone);
        MilestoneTable table = new MilestoneTable(String.valueOf(id), isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getMilestoneObservable().add(table);
        return id;

    }

    public int saveDataFromPriority(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Priority);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getPriorityTypeObservable().add(table);
        return id;
    }

    public int saveDataFromSeverity(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Severity);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getSeverityTypeObservable().add(table);
        return id;
    }

    public int saveDataFromResolutionForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Resolution);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getResolutionTypeObservable().add(table);
        return id;
    }

    public int saveDataFromRelationForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Relation);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getRelationTypeObservable().add(table);
        return id;
    }

    public void saveDataFromRoleForm(String nameST, int typeIndex, PersonTable personTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(personTable.getDescription());
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);

        saveDataModel.createNewPerson(personTable.getId());
        lists.getRoleObservable().add(personTable);

        // int roleTypeIndex = dataModel.getRoleTypeIndex(typeFormManipulator);
        // mapperTableToObject.mapTableToObject(SegmentType.Person, roleTypeIndex, new TableToObjectInstanc(personTable.getAlias(), personTable.getId(), SegmentType.Person));
    }

    public int saveDataFromRoleTypeForm(TableView<RoleTypeTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Role_Type);
        RoleTypeTable table = new RoleTypeTable(String.valueOf(id), "", isExist, "", id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getRoleTypeObservable().add(table);
        return id;

    }


    public int saveDataFromStatusForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Status);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getStatusTypeObservable().add(table);
        return id;
    }

    public int saveDataFromTypeForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Type);
        ClassTable table = new ClassTable(String.valueOf(id),"","", isExist, id);

        tableTV.getItems().add(table);
        tableTV.sort();
        int lastItem = tableTV.getItems().size();
        tableTV.getSelectionModel().select(lastItem - 1);
        lists.getTypeObservable().add(table);
        return id;
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
        //       dataModel.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }

    public MilestoneTable prepareMilestoneToTable(String nameST, String description, int id, ArrayList criterionArray) {
        return dataPreparer.prepareMilestoneTable(nameST, id);
    }

    public PersonTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex) {
        return dataPreparer.prepareRoleTable(nameST, description, id, dataPreparer.prepareIndexForManipulator(roleTypeIndex),
                lists.getRoleTypeObservable());
    }

    public CPRTable prepareCPRToTable(String nameST, int roleIndex, int id) {
        return dataPreparer.prepareCPRTable(nameST, id);
    }

    public BranchTable prepareBranchToTable(String nameST, boolean main, int id) {
        return dataPreparer.prepareBranchTable(nameST, main, id);
    }

    public List[] getCriterionData(int id) {
        return dataManipulator.getCriterionData(id);
    }

    public List[] getMilestoneStringData(int id) {
        return dataManipulator.getMilestoneData(id);
    }

    public ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id) {
        ArrayList<ArrayList<Integer>> criterion = dataManipulator.getCriterionFromMilestone(id);

        return dataPreparer.prepareIndicesForManipulator(criterion);
    }

    public List[] getPersonStringData(int id) {
        List[] data = dataManipulator.getPersonData(id);
        ArrayList<Integer> typeIndices = dataPreparer.prepareIndiciesForForm(data[1]);
        data[1] = typeIndices;
        return data;
    }

    public List[] getClassStringData(SegmentType segmentType, int id) {
        switch (segmentType) {
            case Role_Type:
                return dataManipulator.getRoleTypeData(id);
            case Severity:
                return dataManipulator.getSeverityData(id);
            case Priority:
                return dataManipulator.getPriorityData(id);
            case Status:
                return dataManipulator.getStatusData(id);
            case Type:
                return dataManipulator.getTypeData(id);
            case Relation:
                return dataManipulator.getRelationData(id);
            case Resolution:
                return dataManipulator.getResolutionData(id);
            default:
                return null;
        }
    }


    public ObservableList<BasicTable> getRoleList() {
        return lists.getRoleObservable();
    }

    public List[] getCPRStringData(int id) {
        List[] data = dataManipulator.getCPRData(id);
        ArrayList<Integer> indices = dataPreparer.prepareIndiciesForForm(data[1]);
        data[1] = indices;
        return data;
    }


    public List[] getBranchStringData(int id) {
        return dataManipulator.getBranchStringData(id);
    }


    public List[] getPhaseStringData(int id) {
        List[] data = dataManipulator.getPhaseStringData(id);
        ArrayList indices1 = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList indices2 = dataPreparer.prepareIndiciesForForm(data[3]);
        ArrayList date = dataPreparer.prepareDateForForm(data[4]);
        data[2] = indices1;
        data[3] = indices2;
        data[4] = date;
        return data;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getWorkUnitFromSegment(int id, SegmentType segmentType) {
        ArrayList<ArrayList<Integer>> indexList;

        switch (segmentType) {
            case Phase:
                indexList = dataManipulator.getWorkUnitFromPhase(id);
                break;
            case Work_Unit:
                indexList = dataManipulator.getWorkUniFromWorkUnit(id);
                break;
            case Iteration:
                indexList = dataManipulator.getWorkUnitFromIteration(id);
                break;
            case Activity:
                indexList = dataManipulator.getWorkUnitFromActivity(id);
                break;
            case Project:
                indexList = dataManipulator.getWorkUnitFromProject();
                break;
            default:
                return null;
        }

        return dataPreparer.prepareIndicesForForm(indexList);

    }

    @Override
    public List[] getIterationStringData(int id) {
        List[] data = dataManipulator.getIterationStringData(id);
        ArrayList indices1 = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList date1 = dataPreparer.prepareDateForForm(data[3]);
        ArrayList date2 = dataPreparer.prepareDateForForm(data[4]);
        data[2] = indices1;
        data[3] = date1;
        data[4] = date2;

        return data;
    }

    @Override
    public List[] getActivityStringData(int id) {

        List[] data = dataManipulator.getActivityStringData(id);
        ArrayList date1 = dataPreparer.prepareDateForForm(data[6]);
        data[6] = date1;
        return data;
    }

    @Override
    public List[] getChangeStringData(int id) {
        List[] data = dataManipulator.getChangeStringData(id);
        List<Integer> artifact = dataPreparer.prepareIndiciesForForm(data[5]);
        data[5] = artifact;

        return data;
    }

    @Override
    public List[] getArtifactStringData(int id) {

        List[] data = dataManipulator.getArtifactStringData(id);
        ArrayList<Integer> indicies = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[3]);

        data[2] = indicies;
        data[3] = dates;

        return data;
    }

    @Override
    public List[] getWorkUnitStringData(int id) {

        List[] data = dataManipulator.getWorkUnitStringData(id);

        ArrayList priorityIndicies = dataPreparer.prepareIndiciesForForm(data[4]);
        ArrayList estimate = dataPreparer.convertDoubleToString(data[2]);
        ArrayList progress = dataPreparer.convertIntToString(data[27]);
        ArrayList<Integer> indicies1 = dataPreparer.prepareIndiciesForForm(data[5]);
        ArrayList indicies2 = dataPreparer.prepareIndiciesForForm(data[6]);
        ArrayList indicies3 = dataPreparer.prepareIndiciesForForm(data[7]);
        ArrayList indicies4 = dataPreparer.prepareIndiciesForForm(data[8]);
        ArrayList indicies5 = dataPreparer.prepareIndiciesForForm(data[9]);
        ArrayList indicies6 = dataPreparer.prepareIndiciesForForm(data[10]);
        ArrayList indicies23 = dataPreparer.prepareIndiciesForForm(data[23]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[25]);
        data[2] = estimate;
        data[4] = priorityIndicies;
        data[5] = indicies1;
        data[6] = indicies2;
        data[7] = indicies3;
        data[8] = indicies4;
        data[9] = indicies5;
        data[10] = indicies6;
        data[23] = indicies23;
        data[25] = dates;
        data[27] = progress;
        return data;
    }

    @Override
    public List[] getConfigurationStringData(int id) {

        List[] data = dataManipulator.getConfigurationStringData(id);

        ArrayList<Integer> inidices = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[1]);

        data[2] = inidices;
        data[1] = dates;

        return data;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getCPRFromConfiguration(configId));
        return list;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getBranchesFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getBranchfromConfiguration(configId));
        return list;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getChangesFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getChangeFromConfiguration(configId));
        return list;
    }

    @Override
    public List[] getVCSTagStringData(int tagId) {
        return dataManipulator.getVCSTagStringData(tagId);
    }

    @Override
    public List[] getCommitStringData(int commidId) {
        List[] data = dataManipulator.getCommitStringData(commidId);
        ArrayList<LocalDate> created = dataPreparer.prepareDateForForm(data[5]);
        data[5] = created;
        return data;
    }

    @Override
    public List[] getCommitedConfigurationStringData(int commitedId) {
        List[] data = dataManipulator.getCommitedConfigurationStringData(commitedId);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[2]);
        ArrayList<LocalDate> created = dataPreparer.prepareDateForForm(data[5]);
        data[2] = dates;
        data[5] = created;
        return data;
    }

    @Override
    public List[] getProjectStringData() {
        List[] data = dataManipulator.getProjectStringData();
        ArrayList<LocalDate> dates1 = dataPreparer.prepareDateForForm(data[2]);
        ArrayList<LocalDate> dates2 = dataPreparer.prepareDateForForm(data[3]);

        data[2] = dates1;
        data[3] = dates2;

        return data;
    }

    @Override
    public List[] getRoleTypeStringData(int id) {
        List[] data = dataManipulator.getRoleTypeData(id);
        return data;
    }

    public void setFormFillController(FormFillController formFillController) {
        this.formFillController = formFillController;
    }
}
