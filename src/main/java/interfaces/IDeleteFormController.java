package interfaces;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;

/**
 * Trida predstavujici rozhrani pro mazani dat z formularu
 *
 * @author Václav Janoch
 */
public interface IDeleteFormController {


    /**
     * Metoda pro odstraneni segmetnu Activity
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v tabulce
     * @param tableView instance TableView
     */
    void deleteActivityWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni elementu
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v tabulce
     * @param tableView instance TableView
     */
    void deleteWorkUnitWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni elementu
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v tabulce
     * @param tableView instance TableView
     */
    void deleteChangeWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     *
     * @param indexForm
     * @return index smazanych prvku
     */
    boolean deleteArtifact(int indexForm);

    /**
     * Metoda pro odstraneni segmetnu Iteration
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    void deleteIterationWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     *
     * @param phaseList
     * @param selection
     */
    void deletePhaseForm(ObservableList phaseList, ArrayList<BasicTable> selection);

    /**
     * Metoda pro odstraneni prvku z datovych struktur
     * Nejprve jsou zjisteny id prvku pro odstraneni
     * nasledne jsou aktualizovany reference na prvek, mapovaci mapy a odstraneny informace z datovych struktur
     *
     * @param formIndex
     * @return
     */
    boolean deleteConfigurationWithDialog(int formIndex);

    /**
     * Metoda pro odstraneni elementu Commit
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param formIndex index formulare pro ostraneni
     */
    boolean deleteCommitWithDialog(int formIndex);

    /**
     * Metoda pro odstraneni elementu Committed configuration
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param formIndex index formulare pro odstraneni
     */
    boolean deleteCommitedConfigurationWithDialog(int formIndex);

    /**
     * Metoda pro odstraneni elementu Type
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteTypeWithDialog(ArrayList<BasicTable> selection, TableView view);

    /**
     * Metoda pro odstraneni elementu Status
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteStatusWithDialog(ArrayList<BasicTable> selection, TableView view);

    /**
     * Metoda pro odstraneni elementu RoleType
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    void deleteRoleTypeWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    boolean deletePersonWithDialog(int indexForm);

    /**
     * Metoda pro odstraneni elementu Relation
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param list zvolene prvky v prehledove tabulce pro odstraneni
     * @param view prehledova tabulka
     */
    void deleteRelationWithDialog(ArrayList<BasicTable> list, TableView<ClassTable> view);

    /**
     * Metoda pro odstraneni elementu Resolution
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteResolutionWithDialog(ArrayList<BasicTable> selection, TableView view);

    /**
     * Metoda pro odstraneni elementu Severity
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteSeverityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);

    /**
     * Metoda pro odstraneni elementu Priority
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deletePriorityWithDialog(ArrayList<BasicTable> selection, TableView<ClassTable> view);

    /**
     * Metoda pro odstraneni elementu Milestone
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    void deleteMilestoneWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni elementu Criterion
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param tableView prehledova tabulka
     */
    void deleteCriterionWithDialog(ArrayList<BasicTable> selection, TableView tableView);

    /**
     * Metoda pro odstraneni elementu CPR
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteCPRWithDialog(ArrayList<BasicTable> selection, TableView view);

    /**
     * Metoda pro odstraneni elementu Branch
     * Metoda slouzi pro vyvolani potvrzovaciho dialogu a zavolani metody pro odstraneni prvku z datovych struktur
     *
     * @param selection zvolene prvky v prehledove tabulce pro odstraneni
     * @param view      prehledova tabulka
     */
    void deleteBranchDialog(ArrayList<BasicTable> selection, TableView view);

    /**
     * Metoda pro zavolani konkretnich metoda pro odstraneni elementu a segmetnu z datovych struktur
     *
     * @param list        seznam vybranych prvku
     * @param tableTV     instace tridy tableView
     * @param segmentType typ elementu pro smazani
     */
    void deleteItemWithDialog(ArrayList<BasicTable> list, TableView tableTV, SegmentType segmentType);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Artifact
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removePersonArtifactLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removePersonConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Artifact a Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removeArtifactConfiguraionLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky CommitedConfiguration a Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removeCommitedConfigurationConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Commit a Committed Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removeCommitComiitedConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Commit
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removePersonCommitLink(int arrowId, int startId, int endId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi prvky Person a Committed Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param startId       identifikator pocatecniho prvku
     * @param endId         identifikator koncoveho prvku
     * @param isModelDelete informaco o tom zda byla spojnice jiz smazana z datovoho modelu
     */
    void removePersonCommittedConfigurationLink(int arrowId, int startId, int endId, boolean isModelDelete);
}
