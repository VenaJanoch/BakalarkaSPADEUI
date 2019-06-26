package interfaces;

/**
 * Trida predstavujici rozhrani pro vytvareni instanci v datovem modelu
 *
 * @author Václav Janoch
 */
public interface ISaveDataModel {
    /**
     * Metoda pro vytvoreni nove instace segmentu Phase
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewPhase(int id);

    /**
     * Metoda pro vytvoreni nove instace segmentu Iteration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewIteration(int id);

    /**
     * Metoda pro vytvoreni nove instace segmentu Activity
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewActivity(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Work Unit
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewWorkUnit(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Configuration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewConfiguration(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Change
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewChange(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Artifact
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewArtifact(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Branch
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewBranch(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Configuration Person Relation
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewCPR(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Criterion
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewCriterion(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Priority
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewPriority(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Severity
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewSeverity(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Relation
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewRelation(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Resolution
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewResolution(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Person
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewPerson(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Milestone
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewMilestone(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Role Type
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewRoleType(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Status
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewStatus(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Type
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewType(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu VCSTag
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewVCSTag(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Commit
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewCommit(int id);

    /**
     * Metoda pro vytvoreni nove instace elementu Committed Configuration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu
     *
     * @param id identifikator nove vytvarene instacne
     */
    void createNewCommitedConfiguration(int id);

    /**
     * Metoda pro vytvoreni relace mezi Commited Connfiguratio a Configuration v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId);

    /**
     * Metoda pro vytvoreni relace mezi Commited Connfiguration a Commit v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId);

    /**
     * Metoda pro vytvoreni relace mezi Artifact a Configuration v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createNewArtifacToConfigurationRelation(int linkId, int startId, int endId);

    /**
     * Metoda pro vytvoreni relace mezi Person a Configuration v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createNewPersonToConfigurationRelation(int linkId, int startId, int endId);

    /**
     * Metoda pro vytvoreni relace mezi Person a Artifact v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createNewPersonToArtifactRelation(int linkId, int startId, int endId);

    /**
     * Metoda pro vytvoreni relace mezi Person a Commit v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createNewPersonToCommitRelation(int linkId, int startId, int endId);

    /**
     * Metoda pro vytvoreni relace mezi Committed Configuration a Configuration v datovem Modelu
     *
     * @param linkId  identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId   Koncovy identifikator
     */
    void createNewPersonToCommittedConfigurationRelation(int linkId, int startId, int endId);

}
