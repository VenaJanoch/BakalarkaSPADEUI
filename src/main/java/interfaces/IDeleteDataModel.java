package interfaces;

import javafx.collections.ObservableList;

/**
 * Trida predstavujici rozhrani pro mazani dat z datoveho modelu
 *
 * @author Václav Janoch
 */

public interface IDeleteDataModel {

    /**
     * Metoda pro odstraneni instanci Change z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeChange(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Type z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeType(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Status z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeStatus(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Role Type z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeRoleType(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Milestone z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeMilestone(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Role z datoveho modelu
     *
     * @param id identifikator instace pro smazani
     */
    void removePerson(int id);

    /**
     * Metoda pro odstraneni instanci Resolution z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeResolution(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Relation z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeRelation(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Severity z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeSeverity(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Priority z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removePriority(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Criterion z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeCriterion(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Configuration Person z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeCPR(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Branch z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeBranch(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci VCSTag z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeVCSTag(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni spojnice mezi Person a Artifact
     *
     * @param arrowId       identifikator spojnice
     * @param personId      identifikator Person
     * @param artifactId    identifikator Artifact
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */
    void removePersonArtifactLink(int arrowId, int personId, int artifactId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Person a Commit
     *
     * @param arrowId       identifikator spojnice
     * @param personId      identifikator Person
     * @param commitId      identifikator Artifact
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */
    void removePersonCommitLink(int arrowId, int personId, int commitId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Person a committed configuration
     *
     * @param arrowId       identifikator spojnice
     * @param personId      identifikator Person
     * @param commitId      identifikator Artifact
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */
    void removePersonCommittedConfigurationLink(int arrowId, int personId, int commitId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Person a Configuration
     *
     * @param arrowId         identifikator spojnice
     * @param personId        identifikator Person
     * @param configurationId identifikator Artifact
     * @param isModelDelete   informace o tom, zda uz byla spojnice smazana
     */
    void removePersonConfigurationLink(int arrowId, int personId, int configurationId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Artifact a Configuration
     *
     * @param arrowId         identifikator spojnice
     * @param artifactId      identifikator Artifact
     * @param configurationId identifikator Artifact
     * @param isModelDelete   informace o tom, zda uz byla spojnice smazana
     */

    void removeArtifactConfigurationLink(int arrowId, int artifactId, int configurationId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Committed configuration a Configuration
     *
     * @param arrowId       identifikator spojnice
     * @param commitId      identifikator Commit
     * @param committedId   identifikator Committed Configuration
     * @param isModelDelete informace o tom, zda uz byla spojnice smazana
     */

    void removeCommitCommitedConfigurationLink(int arrowId, int commitId, int committedId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni spojnice mezi Committed Configuration a Configuration
     *
     * @param arrowId         identifikator spojnice
     * @param committedId     identifikator Person
     * @param configurationId identifikator Artifact
     * @param isModelDelete   informace o tom, zda uz byla spojnice smazana
     */

    void removeCommitedConfigurationConfigurationLink(int arrowId, int committedId, int configurationId, boolean isModelDelete);

    /**
     * Metoda pro odstraneni instanci Artifact z datoveho modelu
     *
     * @param id identifikator instace pro smazani
     */
    void removeArtifact(int id);

    /**
     * Metoda pro odstraneni instanci Configuration z datoveho modelu
     *
     * @param id identifikator instace pro smazani
     */
    void removeConfiguration(int id);

    /**
     * Metoda pro odstraneni instanci Work Unit z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeWorkUnit(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Activity z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeActivity(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Iteration z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removeIteration(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Phase z datoveho modelu
     *
     * @param indexList seznam indexu pro smazani z datoveho modelu
     */
    void removePhase(ObservableList<Integer> indexList);

    /**
     * Metoda pro odstraneni instanci Committed Configuration z datoveho modelu
     *
     * @param id identifikator instace pro smazani
     */
    void removeCommitedConfiguration(int id);

    /**
     * Metoda pro odstraneni instanci Commit z datoveho modelu
     *
     * @param id identifikator instace pro smazani
     */
    void removeCommit(int id);
}
