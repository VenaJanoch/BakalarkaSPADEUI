package controllers;

import forms.ConfigurationTableForm;
import graphics.CanvasItem;
import interfaces.IEditDataModel;
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
    private ISaveDataModel saveDataModel;
    private IEditDataModel editDataModel;
    private DataModel dataModel;
    private FormController formController;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private DataPreparer dataPreparer;


    public FormDataController(FormController formController, SegmentLists lists, MapperTableToObject mapperTableToObject, DataModel dataModel,
                              IdentificatorCreater identificatorCreater, DataPreparer dataPreparer){
        this.formController = formController;
        this.lists = lists;
        this.dataModel = dataModel;
        this.saveDataModel = dataModel.getSaveDataModel();
        this.dataManipulator = dataModel.getDataManipulator();
        this.editDataModel = dataModel.getEditDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.dataPreparer = dataPreparer;
    }

    

    public boolean saveDataFromPhaseForm(String actName, LocalDate endDateL, String desc, int confIndex, int milestoneIndex, Map<Integer, CanvasItem> itemIndexList,
                                         int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int phaseId = identificatorCreater.getRoleId(indexForm);

        int[] coords = formController.getCoordsFromItem(indexForm);
        int milestoneIndexForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        int configurationIndexFromManipulator = dataPreparer.prepareIndexForManipulator(confIndex);

     //   editDataModel.editDataInPhase(nameForManipulator, endDateL, descriptionForManipulator, configurationIndexFromManipulator,
      //         milestoneIndexForManipulator, dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), phaseId);
        formController.setNameToItem(indexForm, nameForManipulator);

        String segmentId = formController.getSegmentIdentificator(indexForm);
    //    mapperTableToObject.mapTableToPhase(milestoneIndexForManipulator, configurationIndexFromManipulator, segmentId , phaseId);

        return true;
    }



    public boolean saveDataFromIterationForm(String actName, LocalDate startDate, LocalDate endDate, String desc, int chooseConfigID, Map<Integer,CanvasItem> itemIndexList, int indexForm) {
        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);
        int configurationIdForManipulator = dataPreparer.prepareIndexForManipulator(chooseConfigID);
        int iterationId = identificatorCreater.getIterationId(indexForm);

     //   editDataModel.editDataInIteration(nameForManipulator,startDate, endDate, descriptionForManipulator, configurationIdForManipulator ,
     //           dataPreparer.prepareCanvasItemIndexForManipulator(itemIndexList.keySet()), identificatorCreater.getIterationId(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);

        String segmentId = formController.getSegmentIdentificator(indexForm);
      //  ArrayList<Integer> configurationID = dataModel.getConfigurationId(configurationIdForManipulator);
      //  mapperTableToObject.mapTableToObject(SegmentType.Iteration, configurationIdForManipulator, new TableToObjectInstanc(segmentId, iterationId, SegmentType.Iteration));
        return true;
    }

    public boolean saveDataFromActivityForm(String actName, String desc, Map<Integer, CanvasItem> mapOfItemOnCanvas, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descriptionForManipulator = InputController.fillTextMapper(desc);
        int[] coords = formController.getCoordsFromItem(indexForm);

       // editDataModel.editDataInActivity(nameForManipulator, descriptionForManipulator, dataPreparer.prepareCanvasItemIndexForManipulator(mapOfItemOnCanvas.keySet()),
         //       identificatorCreater.getActivityId(indexForm));
        String segmentId = formController.getSegmentIdentificator(indexForm);
        formController.setNameToItem(indexForm, segmentId);
        return true;

    }

    public boolean saveDataFromWorkUnit(String actName, BasicTable workUnitTable) {

        lists.getWorkUnitsObservable().add(workUnitTable);
        return  true;
    }

    public boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                          ArrayList<Integer> cprIndex, Map<Integer,CanvasItem> itemIndexList, boolean isNew, int indexForm) {


        String nameForManipulator = InputController.fillTextMapper(actName);
        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();
        authorIndex = dataPreparer.prepareIndexForManipulator(authorIndex);
      //  branchIndex = dataPreparer.prepareIndicesForManipulator(branchIndex);
      //  cprIndex = dataPreparer.prepareIndicesForManipulator(cprIndex);

        for(int index : itemIndexList.keySet()) {
            if(identificatorCreater.getRoleIndexToIdMaper().get(index) != null){
                changeList.add(identificatorCreater.getRoleIndexToIdMaper().get(index));
            }else {
                artefactList.add(identificatorCreater.getArtifactIndexToIdMaper().get(index));
            }
        }
        String release = "NO";
        if(isRelease){
            release = "YES";
        }
        int configIndex = identificatorCreater.getConfigurationId(indexForm);
        //editDataModel.editDataInConfiguration(nameForManipulator, createDate, isRelease, authorIndex , branchIndex,cprIndex,
          //      changeList, configIndex );
        String idName = identificatorCreater.getConfigurationId(indexForm) + "_" + actName;
        ConfigTable configTable = new ConfigTable(idName, release, indexForm, true, configIndex);
        if (isNew){
            lists.getConfigObservable().add(configTable);
            formController.setNewItemToConfigurationTable(idName, release, indexForm, configIndex);
        }else{
            lists.getConfigObservable().remove(configIndex + 1);
            lists.getConfigObservable().add(configIndex + 1, configTable);
            setEditItemInConfigurationTable(configTable);
        }

        formController.setConfigurationFormToTableForm();
        return true;
    }

    public void setEditItemInConfigurationTable(ConfigTable configTable) {

        ConfigurationTableForm configurationTableForm = (ConfigurationTableForm) formController.getForms().get(Constans.configurationFormIndex);
        TableView<ConfigTable> configTableTableView = configurationTableForm.getTableTV();
        int id = configTableTableView.getSelectionModel().getSelectedIndex();
        configTableTableView.getItems().remove(id);
        configurationTableForm.getTableTV().getItems().add(id,configTable);
        configurationTableForm.getTableTV().sort();

    }


    public boolean saveDataFromChange(String actName, String desc, boolean selected, int indexForm) {

        String nameForManipulator = InputController.fillTextMapper(actName);
        String descForManipulator = InputController.fillTextMapper(desc);

        int[] coords = formController.getCoordsFromItem(indexForm);

        formController.setItemColor(indexForm, selected);
       // editDataModel.editDataInChange(nameForManipulator, descForManipulator, selected, identificatorCreater.getChangeId(indexForm));
        formController.setNameToItem(indexForm, nameForManipulator);
        return true;
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
        return  true;
    }


   public void saveDataFromVCSTag(String nameST, VCSTagTable table){

       lists.getVCSTag().add(table);
    }

    public void createArtifactToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML){
        if (!isXML){
            saveDataModel.createNewArtifacToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Artifact, startId, new TableToObjectInstanc( String.valueOf(endId), startId, SegmentType.Configuration));
    }


    public void createCommitToCommitedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML){
        if (!isXML) {
            saveDataModel.createCommitToCommitedConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Committed_Configuration, null, startId, new TableToObjectInstanc( String.valueOf(endId), startId, SegmentType.Committed_Configuration));
    }

    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML){
        if (!isXML) {
            saveDataModel.createCommitedConfigurationToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Committed_Configuration, startId,
                new TableToObjectInstanc( String.valueOf(endId), startId, SegmentType.Configuration));
    }



    public void createNewPersonToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML){
        if (!isXML) {
            saveDataModel.createNewPersonToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Person, startId,
                new TableToObjectInstanc( String.valueOf(endId), startId, SegmentType.Configuration));
    }


    public void createNewPersonToArtifactRelation(int linkId, Integer startId, Integer endId, boolean isXML){
        if (!isXML) {
            saveDataModel.createNewPersonToArtifactRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Artifact, SegmentType.Person, startId,
                new TableToObjectInstanc( String.valueOf(endId), startId, SegmentType.Artifact));
    }


    public void saveDataFromBranch(String nameST, BranchTable branchTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);

        saveDataModel.createNewBranch(nameForManipulator, branchTable.getId(), branchTable.isMainBool());
        lists.getBranchObservable().add(branchTable);
    }





    public void saveDataFromCPR(String nameST, int roleListIndex, CPRTable cprTable) {

        lists.getCPRObservable().add(cprTable);
    }

    public void saveDataFromCriterionForm(String nameST, CriterionTable criterionTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(criterionTable.getDescription());
        saveDataModel.createNewCriterion(nameForManipulator, descForManipulator, criterionTable.getId());
        lists.getCriterionObservable().add(criterionTable);
    }

    public void saveDataFromMilestoneForm(String nameST, String description, ArrayList<Integer> criterionIndex, MilestoneTable milestoneTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(description);
    //    criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        saveDataModel.createNewMilestone(nameForManipulator,descForManipulator, criterionIndex, milestoneTable.getId());
        lists.getMilestoneObservable().add(milestoneTable);
       // ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
      //  mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getName(), milestoneTable.getId(),
      //          SegmentType.Milestone));
}

    public void saveDataFromPriority(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewPriority(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getPriorityTypeObservable().add(tableItem);
    }

    public void saveDataFromSeverity(String nameST, ClassTable tableItem) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewSeverity(nameForManipulator, tableItem.getClassType(), tableItem.getSuperType(), tableItem.getId());
        lists.getSeverityTypeObservable().add(tableItem);
    }

    public void saveDataFromResolutionForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewResolution(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getResolutionTypeObservable().add(classTable);
    }

    public void saveDataFromRelationForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewRelation(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getRelationTypeObservable().add(classTable);
    }

    public void saveDataFromRoleForm(String nameST, int typeIndex, PersonTable personTable) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(personTable.getDescription());
        int typeFormManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);

        saveDataModel.createNewRole(personTable.getId());
        lists.getRoleObservable().add(personTable);

       // int roleTypeIndex = dataModel.getRoleTypeIndex(typeFormManipulator);
       // mapperTableToObject.mapTableToObject(SegmentType.Person, roleTypeIndex, new TableToObjectInstanc(personTable.getName(), personTable.getId(), SegmentType.Person));
}

    public void saveDataFromRoleTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewRoleType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(),classTable.getId());
        lists.getRoleTypeObservable().add(classTable);
    }

    public void saveDataFromTagForm(String tag, int configId, int id) {
        String tagForManipulator = InputController.fillTextMapper(tag);

        saveDataModel.addTagToConfiguration(tagForManipulator, configId, id);
    }

    public void saveDataFromStatusForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewStatus(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getStatusTypeObservable().add(classTable);
    }

    public void saveDataFromTypeForm(String nameST, ClassTable classTable) {

        String nameForManipulator = InputController.fillTextMapper(nameST);
        saveDataModel.createNewType(nameForManipulator, classTable.getClassType(), classTable.getSuperType(), classTable.getId());
        lists.getTypeObservable().add(classTable);
    }

    public void saveDataFromProjectFrom(String nameST, LocalDate endDate, LocalDate startDate, String desc) {
        String nameForManipulator = InputController.fillTextMapper(nameST);
        String descForManipulator = InputController.fillTextMapper(desc);
 //       dataModel.addDataToProject(nameForManipulator, descForManipulator, startDate, endDate);

    }

    public MilestoneTable prepareMilestoneToTable(String nameST, String description, int id, ArrayList criterionArray) {
     return   dataPreparer.prepareMilestoneTable(nameST, id);
    }

    public PersonTable prepareRoleToTable(String nameST, String description, int id, int roleTypeIndex) {
        return   dataPreparer.prepareRoleTable(nameST, description, id, dataPreparer.prepareIndexForManipulator(roleTypeIndex),
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

    public ArrayList<ArrayList<Integer>>  getCriterionFromMilestone(int id) {
        ArrayList<ArrayList<Integer>> criterion = dataManipulator.getCriterionFromMilestone(id);

        return dataPreparer.prepareIndicesForManipulator(criterion);
    }

    public List[] getPersonStringData(int id) {
        List[] data = dataManipulator.getRoleData(id);
        ArrayList<Integer> typeIndices = dataPreparer.prepareIndiciesForForm(data[1]);
        data[1] = typeIndices;
        return data ;
    }

    public List[] getClassStringData(SegmentType segmentType, int id) {
        switch (segmentType){
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


    public  List[] getBranchStringData(int id) {
        return dataManipulator.getBranchStringData(id);
    }


    public  String getTagData(int id, int configFormId) {
        return dataManipulator.getTagStringData(id, identificatorCreater.getConfigurationId(configFormId));
    }

    public  List[] getPhaseStringData(int id){
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
    public  ArrayList<ArrayList<Integer>> getWorkUnitFromSegment(int id, SegmentType segmentType) {
        ArrayList<ArrayList<Integer>> indexList;

        switch (segmentType){
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

        ArrayList priorityIndicies = dataPreparer.prepareIndexForManipulator(data[4]);
        ArrayList indicies1 = dataPreparer.prepareIndexForManipulator(data[5]);
        ArrayList indicies2 = dataPreparer.prepareIndexForManipulator(data[6]);
        ArrayList indicies3 = dataPreparer.prepareIndexForManipulator(data[7]);
        ArrayList indicies4 = dataPreparer.prepareIndexForManipulator(data[8]);
        ArrayList indicies5 = dataPreparer.prepareIndexForManipulator(data[9]);
        ArrayList indicies6 = dataPreparer.prepareIndexForManipulator(data[10]);

        data[4] = priorityIndicies;
        data[5] = indicies1;
        data[6] = indicies2;
        data[7] = indicies3;
        data[8] = indicies4;
        data[9] = indicies5;
        data[10] = indicies6;

        return data;
    }

    @Override
    public List[] getConfigurationStringData(int id) {

        List[] data =  dataManipulator.getConfigurationStringData(id);

        ArrayList<Integer> inidices = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[1]);

        data[2] = inidices;
        data[1] = dates;

        return data;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getCPRFromConfiguration(configId));
        return list ;
    }

    @Override
    public ArrayList<ArrayList<Integer>> getBranchesFromCommit(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getBranchfromCommit(configId));
        return list;
    }
    @Override
    public ArrayList<ArrayList<Integer>> getChangesFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getChangeFromConfiguration(configId));
        return list;
    }

    @Override
    public List[] getVCSTagStringData(int tagId){
        return dataManipulator.getVCSTagStringData(tagId);
    }

    @Override
    public List[] getCommitStringData(int commidId){
        return dataManipulator.getCommitStringData(commidId);
    }

    @Override
    public List[] getCommitedConfigurationStringData(int commitedId){
        List[] data = dataManipulator.getCommitedConfigurationStringData(commitedId);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[2]);

        data[2] = dates;
        return data;
    }

    @Override
    public List[] getProjectStringData(){
        List[] data = dataManipulator.getProjectStringData();
        ArrayList<LocalDate> dates1 = dataPreparer.prepareDateForForm(data[2]);
        ArrayList<LocalDate> dates2 = dataPreparer.prepareDateForForm(data[3]);

        data[2] = dates1;
        data[3] = dates2;

        return data;
    }

    @Override
    public List[] getRoleTypeStringData(int id){
        List[] data = dataManipulator.getRoleTypeData(id);
        return data;
    }

}
