package interfaces;

import controllers.formControllers.FormFillController;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici rozhrani pro praci s daty ve formulari
 *
 * @author Václav Janoch
 */
public interface IFormDataController {
    /**
     * Metoda slouzi k vytvoreni noveho segmentu Phase
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy PhaseTable, ktera je nasledne vlozena do prislusne tabulky
     *
     * @param tableView Tabluka do ktere v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromPhaseForm(TableView<PhaseTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho segmentu Phase
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy PhaseTable, ktera je nasledne vlozena do prislusne tabulky
     *
     * @param tableView Tabluka do ktere v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromIterationForm(TableView<IterationTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho segmentu Iteration
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy IterationTable, ktera je nasledne vlozena do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromActivityForm(TableView<ActivityTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu VCSTag
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy VCSTagTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromVCSTagForm(TableView<VCSTagTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Work Unit
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy WorkUnitTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromWorkUnit(TableView<WorkUnitTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Change
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy ChangeTable, ktera je vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromChangeForm(TableView<ChangeTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Branch
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy BranchTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromBranch(TableView<BranchTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Configuration Person
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy CPRTable, ktera je
     * vkladana do prislusne tabulky a prehledoveho seznamu
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromCPR(TableView<CPRTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Criterion
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy CriterionTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromCriterionForm(TableView<CriterionTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Milestone
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy MilestoneTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromMilestoneForm(TableView<MilestoneTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Priority
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy PriorityTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromPriority(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Severity
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy SeverityTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromSeverity(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Resolution
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy ResolutionTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromResolutionForm(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Relation
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy RelationTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromRelationForm(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Role Type
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy RoleTypeTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromRoleTypeForm(TableView<RoleTypeTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Status
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy StatusTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromStatusForm(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda slouzi k vytvoreni noveho elementu Type
     * Za pomoci kontroleru FormController je instance vytvorena v datovem modelu
     * Nasledne je vytvorena instace tridy TypeTable, ktera je vkladana do prislusne tabulky
     *
     * @param tableView Tabluka do ktere je v konecne fazi pridana nova instace a prehledoveho seznamu
     * @param isExist   existence prvku
     * @return identifikator v datovem modelu
     */
    int saveDataFromTypeForm(TableView<ClassTable> tableView, boolean isExist);

    /**
     * Metoda pro ziskani dat o elementu Criterion z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getCriterionData(int id);

    /**
     * Metoda pro ziskani dat o elementu Milestone z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getMilestoneStringData(int id);

    /**
     * Metoda pro ziskani identifikatoru criterion ke konkretnimu Milestone
     *
     * @param id identifikator Milestone
     * @return list s identifikatory criterion patricich pro Milestone
     */
    ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id);

    /**
     * Metoda pro ziskani dat o elementu Person z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getPersonStringData(int id);

    /**
     * Metoda rozhodujici o tom ktera metoda pro ziskani dat z datoveho modelu bude zavolana
     * Rozhoduje se na zakladne zadaneho typu vyctovych typu pro WorkUnit
     *
     * @param segmentType typy segmentu nebo elementu
     * @param id          identifikator konkretniho prvku
     * @return pole listu s daty
     */
    List[] getClassStringData(SegmentType segmentType, int id);

    /**
     * Metoda pro ziskani prehledoveho seznamu o Person
     *
     * @return observer seznam elementu Person
     */
    ObservableList<BasicTable> getPersonList();

    /**
     * Metoda pro ziskani dat o elementu Configuration Person z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getCPRStringData(int id);

    /**
     * Metoda pro ziskani dat o elementu Branch z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getBranchStringData(int id);

    /**
     * Metoda pro ziskani dat o segmentu Phase z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getPhaseStringData(int id);

    /**
     * Pomocna metoda o rozhodnuti pro ktery segmentu pripadne work unitu bude vracen seznam
     * identifikatoru jeho work unitu
     *
     * @param id    identifikator prvky
     * @param phase typ segmentu nebo work unitu
     * @return seznam prislusnych identifikatoru
     */
    ArrayList<ArrayList<Integer>> getWorkUnitFromSegment(int id, SegmentType phase);

    /**
     * Metoda pro ziskani dat o segmentu Iteration z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getIterationStringData(int id);

    /**
     * Metoda pro ziskani dat o segmentu Activity z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getActivityStringData(int id);

    /**
     * Metoda pro ziskani dat o elementu Change z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getChangeStringData(int id);

    /**
     * Metoda pro ziskani dat o elementu Artifact z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getArtifactStringData(int id);

    /**
     * Metoda pro ziskani dat o elementu Work Unit z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getWorkUnitStringData(int id);

    /**
     * Metoda pro ziskani dat o elementu Configuration z datoveho modelu v poli listu
     *
     * @param configId identifikator elementu
     * @return pole listu s daty
     */
    List[] getConfigurationStringData(int configId);

    /**
     * Metoda pro ziskani identifikatoru criterion ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId);

    /**
     * Metoda pro ziskani identifikatoru Branch ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    ArrayList<ArrayList<Integer>> getBranchesFromConfiguration(int configId);

    /**
     * Metoda pro ziskani identifikatoru Change ke konkretnimu Configuration
     *
     * @param configId identifikator Configuration
     * @return list s identifikatory criterion patricich pro Configuration
     */
    ArrayList<ArrayList<Integer>> getChangesFromConfiguration(int configId);

    /**
     * Metoda pro ziskani dat o elementu VCSTag z datoveho modelu v poli listu
     *
     * @param tagId identifikator elementu
     * @return pole listu s daty
     */
    List[] getVCSTagStringData(int tagId);

    /**
     * Metoda pro ziskani dat o elementu Commit z datoveho modelu v poli listu
     *
     * @param commitId identifikator elementu
     * @return pole listu s daty
     */
    List[] getCommitStringData(int commitId);

    /**
     * Metoda pro ziskani dat o elementu Committed Configuration z datoveho modelu v poli listu
     *
     * @param commitedConfigurationId identifikator elementu
     * @return pole listu s daty
     */
    List[] getCommitedConfigurationStringData(int commitedConfigurationId);

    /**
     * Metoda pro ziskani dat o projektu z datoveho modelu v poli listu
     *
     * @return pole listu s daty
     */
    List[] getProjectStringData();

    /**
     * Metoda pro ziskani dat o elementu Role Type z datoveho modelu v poli listu
     *
     * @param id identifikator elementu
     * @return pole listu s daty
     */
    List[] getRoleTypeStringData(int id);

    /**
     * Metoda pro vytvoreni relace mezi Artifact a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createArtifactToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Commit a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createCommitToCommitedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Committed Configuration a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Person a Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createNewPersonToConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Person a Artifact
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createNewPersonToArtifactRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Person a Commit
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createNewPersonToCommitRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda pro vytvoreni relace mezi Person a Committed Configuration
     * Nejprve je volana metoda pro vytvoreni z datoveho skladu a nasledne namapovany potrebne reference
     *
     * @param linkId  identifikator nove spojnice
     * @param startId identifikator pocatecniho prvku
     * @param endId   identifikator koncoveho prvku
     * @param isXML   nacteni se neprovede v pripade nacitani z XML
     */
    void createNewPersonToCommittedConfigurationRelation(int linkId, Integer startId, Integer endId, boolean isXML);

    /**
     * Metoda slouzici k rozhodnuti, ktery typ prvku se bude kopirovat z tabulkoveho formulare
     *
     * @param list        seznam prvku pro kopirovani
     * @param tableView   instance tabulky, kam se budou nove prvky vkladat
     * @param segmentType typ elementu nebo segmentu
     */
    void createCopyTableItem(ArrayList<BasicTable> list, TableView tableView, SegmentType segmentType);

    /**
     * Metoda pro nastaveni instace FormFillController
     *
     * @param formFillController FormFillController
     */
    void setFormFillController(FormFillController formFillController);
}
