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
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import services.TableToObjectInstanc;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Trida predstavujici controller pro rizeni datovych operaci
 * pro prvky na platne a tabulkove elementy a segmenty
 *
 * @author Václav Janoch
 */
public class FormDataController implements IFormDataController {

    /**
     * Promenne predstavujici instance objektu z datoveho modelu pro moznost vytvareni konkretnich prvku
     **/
    private SegmentLists lists;
    private DataManipulator dataManipulator;
    private ISaveDataModel saveDataModel;
    private IdentificatorCreater identificatorCreater;

    /**
     * Promenne predstavujici instace objektu kontroleru potrebnych pro cinnost tritdy
     **/
    private MapperTableToObject mapperTableToObject;
    private DataPreparer dataPreparer;
    private FormFillController formFillController;
    private FormController formController;

    /**
     * Kontroler tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param formController       instace tridy FormController pro formularu
     * @param lists                prehledove seznamy
     * @param mapperTableToObject  instace tridy MapperTableObject pro mapovani instaci mezi sebou
     * @param dataModel            instance datoveho modelu
     * @param identificatorCreater instance tridy pro vytvreni identifikatoru
     * @param dataPreparer         instace tridy DataPreparer pro pripravu dat do datoveho modelu
     */
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


    /**
     * Metoda slouzi k vytvoreni noveho segmentu Phase
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy PhaseTable, ktera je nasledne vlozena do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromPhaseForm(TableView<PhaseTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Phase);
        PhaseTable table = new PhaseTable(String.valueOf(id), isExist, id);
        editTableItems(tableTV, table);
        return id;
    }


    /**
     * Metoda slouzi k vytvoreni noveho segmentu Iteration
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy IterationTable, ktera je nasledne vlozena do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromIterationForm(TableView<IterationTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Iteration);
        IterationTable table = new IterationTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
        return id;
    }

    /**
     * Metoda pro pridani novych polozek do konkretni tabulky
     *
     * @param tableTV Tabulka pro pridani dat
     * @param table   polozka pro pridani
     */
    private void editTableItems(TableView tableTV, BasicTable table) {
        if (tableTV != null) {
            tableTV.getItems().add(table);
            tableTV.sort();
            int lastItem = tableTV.getItems().size();
            tableTV.getSelectionModel().select(lastItem - 1);
        }
    }

    /**
     * Metoda slouzi k vytvoreni noveho segmentu Activity
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy ActivityTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromActivityForm(TableView<ActivityTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Activity);
        ActivityTable table = new ActivityTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);

        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Work Unit
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy WorkUnitTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromWorkUnit(TableView<WorkUnitTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Work_Unit);
        WorkUnitTable table = new WorkUnitTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
        lists.getWorkUnitsObservable().add(table);
        return id;
    }

    public boolean saveDataFromConfiguration(String actName, LocalDate createDate, boolean isRelease, int authorIndex, ArrayList<Integer> branchIndex,
                                             ArrayList<Integer> cprIndex, Map<Integer, CanvasItem> itemIndexList, boolean isNew, int indexForm) {


        ArrayList artefactList = new ArrayList();
        ArrayList changeList = new ArrayList();

        for (int index : itemIndexList.keySet()) {
            if (identificatorCreater.getPersonIndexToIdMaper().get(index) != null) {
                changeList.add(identificatorCreater.getPersonIndexToIdMaper().get(index));
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


    /**
     * Metoda slouzi k vytvoreni noveho elementu Change
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy ChangeTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromChangeForm(TableView<ChangeTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Change);
        ChangeTable table = new ChangeTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
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

    /**
     * Metoda slouzi k vytvoreni noveho elementu VCSTag
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy VCSTagTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromVCSTagForm(TableView<VCSTagTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.VCSTag);
        VCSTagTable table = new VCSTagTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
        lists.getVCSTag().add(table);
        return id;
    }


    /**
     * Metoda slouzi k vytvoreni noveho elementu Branch
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy BranchTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromBranch(TableView<BranchTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Branch);
        BranchTable table = new BranchTable(String.valueOf(id), "", true, isExist, id);

        editTableItems(tableTV, table);
        lists.getBranchObservable().add(table);
        return id;

    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Configuration Person
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy CPRTable, ktera je
     * vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromCPR(TableView<CPRTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Config_Person_Relation);
        CPRTable table = new CPRTable(String.valueOf(id), "", isExist, id);

        editTableItems(tableTV, table);
        lists.getCPRObservable().add(table);
        return id;

    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Criterion
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy CriterionTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromCriterionForm(TableView<CriterionTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Criterion);
        CriterionTable table = new CriterionTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
        lists.getCriterionObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Milestone
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy MilestoneTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromMilestoneForm(TableView<MilestoneTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Milestone);
        MilestoneTable table = new MilestoneTable(String.valueOf(id), isExist, id);

        editTableItems(tableTV, table);
        lists.getMilestoneObservable().add(table);
        return id;

    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Priority
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy PriorityTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromPriority(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Priority);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getPriorityTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Severity
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy SeverityTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromSeverity(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Severity);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getSeverityTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Resolution
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy ResolutionTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromResolutionForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Resolution);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getResolutionTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Relation
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy RelationTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromRelationForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Relation);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getRelationTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Role Type
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy RoleTypeTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromRoleTypeForm(TableView<RoleTypeTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Role_Type);
        RoleTypeTable table = new RoleTypeTable(String.valueOf(id), "", isExist, "", id);

        editTableItems(tableTV, table);
        lists.getRoleTypeObservable().add(table);
        return id;

    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Status
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy StatusTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromStatusForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Status);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getStatusTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda slouzi k vytvoreni noveho elementu Type
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy TypeTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableTV Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist existence prvku
     * @return identifikator v datovem modelu
     */
    public int saveDataFromTypeForm(TableView<ClassTable> tableTV, boolean isExist) {

        int id = formController.createTableItem(SegmentType.Type);
        ClassTable table = new ClassTable(String.valueOf(id), "", "", isExist, id);

        editTableItems(tableTV, table);
        lists.getTypeObservable().add(table);
        return id;
    }

    /**
     * Metoda pro ziskani dat o elementu Criterion z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    public List[] getCriterionData(int id) {
        return dataManipulator.getCriterionData(id);
    }

    /**
     * Metoda pro ziskani dat o elementu Milestone z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    public List[] getMilestoneStringData(int id) {
        return dataManipulator.getMilestoneData(id);
    }

    /**
     * Metoda pro ziskani dat o elementu Person z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    public List[] getPersonStringData(int id) {
        List[] data = dataManipulator.getPersonData(id);
        ArrayList<Integer> typeIndices = dataPreparer.prepareIndiciesForForm(data[1]);
        data[1] = typeIndices;
        return data;
    }

    /**
     * Metoda rozhodujici o tom ktera metoda pro ziskani dat z datoveho modelu bude zavolana
     * Rozhoduje se na zakladne zadaneho typu vyctovych typu pro WorkUnit
     *
     * @param segmentType typy segmentu nebo elementu
     * @param id          identifikator konkretniho prvku
     * @return pole listu s daty
     */
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


    /**
     * Metoda pro ziskani dat o elementu Configuration Person z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    public List[] getCPRStringData(int id) {
        List[] data = dataManipulator.getCPRData(id);
        ArrayList<Integer> indices = dataPreparer.prepareIndiciesForForm(data[1]);
        data[1] = indices;
        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Branch z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    public List[] getBranchStringData(int id) {
        return dataManipulator.getBranchStringData(id);
    }

    /**
     * Metoda pro ziskani dat o segmentu Phase z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
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

    /**
     * Metoda pro ziskani dat o segmentu Iteration z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
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

    /**
     * Metoda pro ziskani dat o segmentu Activity z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getActivityStringData(int id) {

        List[] data = dataManipulator.getActivityStringData(id);
        ArrayList date1 = dataPreparer.prepareDateForForm(data[6]);
        data[6] = date1;
        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Change z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getChangeStringData(int id) {
        List[] data = dataManipulator.getChangeStringData(id);
        List<Integer> artifact = dataPreparer.prepareIndiciesForForm(data[5]);
        data[5] = artifact;

        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Artifact z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getArtifactStringData(int id) {

        List[] data = dataManipulator.getArtifactStringData(id);
        ArrayList<Integer> indicies = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[3]);

        data[2] = indicies;
        data[3] = dates;

        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Work Unit z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
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

    /**
     * Metoda pro ziskani dat o elementu Configuration z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getConfigurationStringData(int id) {

        List[] data = dataManipulator.getConfigurationStringData(id);

        ArrayList<Integer> inidices = dataPreparer.prepareIndiciesForForm(data[2]);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[1]);

        data[2] = inidices;
        data[1] = dates;

        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu VCSTag z datoveho modelu v poli listu
     *
     * @param tagId identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getVCSTagStringData(int tagId) {
        return dataManipulator.getVCSTagStringData(tagId);
    }

    /**
     * Metoda pro ziskani dat o elementu Commit z datoveho modelu v poli listu
     *
     * @param commidId identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getCommitStringData(int commidId) {
        List[] data = dataManipulator.getCommitStringData(commidId);
        ArrayList<LocalDate> created = dataPreparer.prepareDateForForm(data[5]);
        data[5] = created;
        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Committed Configuration z datoveho modelu v poli listu
     *
     * @param commitedId identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getCommitedConfigurationStringData(int commitedId) {
        List[] data = dataManipulator.getCommitedConfigurationStringData(commitedId);
        ArrayList<LocalDate> dates = dataPreparer.prepareDateForForm(data[2]);
        ArrayList<LocalDate> created = dataPreparer.prepareDateForForm(data[5]);
        data[2] = dates;
        data[5] = created;
        return data;
    }

    /**
     * Metoda pro ziskani dat o projektu z datoveho modelu v poli listu
     *
     * @return pole listu s daty
     */
    @Override
    public List[] getProjectStringData() {
        List[] data = dataManipulator.getProjectStringData();
        ArrayList<LocalDate> dates1 = dataPreparer.prepareDateForForm(data[2]);
        ArrayList<LocalDate> dates2 = dataPreparer.prepareDateForForm(data[3]);

        data[2] = dates1;
        data[3] = dates2;

        return data;
    }

    /**
     * Metoda pro ziskani dat o elementu Role Type z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    @Override
    public List[] getRoleTypeStringData(int id) {
        List[] data = dataManipulator.getRoleTypeData(id);
        return data;
    }

    /**
     * Metoda pro ziskani identifikatoru criterion ke konkretnimu Milestone
     *
     * @param id identifikator Milestone
     * @return list s identifikatory criterion patricich pro Milestone
     */
    public ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id) {
        ArrayList<ArrayList<Integer>> criterion = dataManipulator.getCriterionFromMilestone(id);

        return dataPreparer.prepareIndicesForManipulator(criterion);
    }


    /**
     * Pomocna metoda o rozhodnuti pro ktery segmentu pripadne work unitu bude vracen seznam
     * identifikatoru jeho work unitu
     *
     * @param id          identifikator prvky
     * @param segmentType typ segmentu nebo work unitu
     * @return seznam prislusnych identifikatoru
     */
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


    /**
     * Metoda pro ziskani identifikatoru criterion ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    @Override
    public ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getCPRFromConfiguration(configId));
        return list;
    }

    /**
     * Metoda pro ziskani identifikatoru Branch ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    @Override
    public ArrayList<ArrayList<Integer>> getBranchesFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getBranchfromConfiguration(configId));
        return list;
    }

    /**
     * Metoda pro ziskani identifikatoru Change ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    @Override
    public ArrayList<ArrayList<Integer>> getChangesFromConfiguration(int configId) {
        ArrayList<ArrayList<Integer>> list = dataPreparer.prepareIndicesForForm(dataManipulator.getChangeFromConfiguration(configId));
        return list;
    }


    /**
     * Metoda pro vytvoreni relace mezi Artifact a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createArtifactToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewArtifacToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Artifact, startId, new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }

    /**
     * Metoda pro vytvoreni relace mezi Commit a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createCommitToCommitedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createCommitToCommitedConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Committed_Configuration, SegmentType.Commit, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Committed_Configuration));
    }

    /**
     * Metoda pro vytvoreni relace mezi Committed Configuration a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createCommitedConfigurationToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Committed_Configuration, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createNewPersonToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Configuration, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Configuration));
    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Artifact
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createNewPersonToArtifactRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToArtifactRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Artifact, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Artifact));
    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Commit
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createNewPersonToCommitRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToCommitRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Commit, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Commit));
    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Committed Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    public void createNewPersonToCommittedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML) {
        if (!isXML) {
            saveDataModel.createNewPersonToCommittedConfigurationRelation(linkId, startId, endId);
        }
        mapperTableToObject.mapTableToObjects(SegmentType.Committed_Configuration, SegmentType.Person, startId,
                new TableToObjectInstanc(String.valueOf(endId), endId, SegmentType.Committed_Configuration));
    }

    /**
     * Metoda slouzici k rozhodnuti, ktery typ prvku se bude kopirovat z tabulkoveho formulare
     *
     * @param list        seznam prvku pro kopirovani
     * @param tableView   instance tabulky, kam se budou nove prvky vkladat
     * @param segmentType typ elementu nebo segmentu
     */
    @Override
    public void createCopyTableItem(ArrayList<BasicTable> list, TableView tableView, SegmentType segmentType) {
        switch (segmentType) {
            case Activity:
                for (BasicTable table : list) {
                    formFillController.fillActivityForm(tableView, table.getId());
                }
                break;
            case Phase:
                for (BasicTable table : list) {
                    formFillController.fillPhaseForm(tableView, table.getId());
                }
                break;
            case Iteration:
                for (BasicTable table : list) {
                    formFillController.fillIterationForm(tableView, table.getId());
                }
                break;
            case Change:
                for (BasicTable table : list) {
                    formFillController.fillChangeForm(tableView, table.getId());
                }
                break;
            case VCSTag:
                for (BasicTable table : list) {
                    formFillController.fillVCSTagForm(tableView, table.getId());
                }
                break;
            case Config_Person_Relation:
                for (BasicTable table : list) {
                    formFillController.fillCPRForm(tableView, table.getId());
                }
                break;
            case Branch:
                for (BasicTable table : list) {
                    formFillController.fillBranchForm(tableView, table.getId());
                }
                break;
            case Criterion:
                for (BasicTable table : list) {
                    formFillController.fillCriterionForm(tableView, table.getId());
                }
                break;
            case Milestone:
                for (BasicTable table : list) {
                    formFillController.fillMilestoneForm(tableView, table.getId());
                }
                break;
            case Priority:
                for (BasicTable table : list) {
                    formFillController.fillPriorityForm(tableView, table.getId());
                }
                break;
            case Severity:
                for (BasicTable table : list) {
                    formFillController.fillSeverityForm(tableView, table.getId());
                }
                break;
            case Resolution:
                for (BasicTable table : list) {
                    formFillController.fillResolutionForm(tableView, table.getId());
                }
                break;
            case Relation:
                for (BasicTable table : list) {
                    formFillController.fillRelationForm(tableView, table.getId());
                }
                break;
            case Status:
                for (BasicTable table : list) {
                    formFillController.fillStatusForm(tableView, table.getId());
                }
                break;
            case Type:
                for (BasicTable table : list) {
                    formFillController.fillTypeForm(tableView, table.getId());
                }
            case Role_Type:
                for (BasicTable table : list) {
                    formFillController.fillRoleTypeForm(tableView, table.getId());
                }
            case Work_Unit:
                for (BasicTable table : list) {
                    formFillController.fillWorkUnitForm(tableView, table.getId());
                }
                break;
            default:
        }
    }

    /**
     * Metoda pro nastaveni instace FormFillController
     *
     * @param formFillController FormFillController
     */
    public void setFormFillController(FormFillController formFillController) {
        this.formFillController = formFillController;
    }

    /**
     * Metoda pro ziskani prehledoveho seznamu o Person
     *
     * @return observer seznam elementu Person
     */
    public ObservableList<BasicTable> getPersonList() {
        return lists.getPersonObservable();
    }

}
