package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Trida slouzici pro vytvareni a uchovavani unikatnich identifikatoru prvku datoveho modelu
 *
 * @author Václav Janoch
 */
public class IdentificatorCreater {
    /**
     * Globální proměnné třídy
     * promenne uchovavajici aktualni ID
     **/
    private int linesID = -1;
    private int phaseID = -1;
    private int iterationID = -1;
    private int activityID = -1;
    private int workUnitID = -1;
    private int milestoneID = -1;
    private int criteriumID = -1;
    private int personID = -1;
    private int branchID = -1;
    private int changeID = -1;
    private int artifactID = -1;
    private int configPRID = -1;
    private int configID = -1;
    private int tagID = -1;
    private int CPRID = -1;
    private int VCSTag = -1;
    private int commidID = -1;
    private int commtedConfigurationID = -1;

    private int priorityID = -1;
    private int severityID = -1;
    private int resolutionID = -1;
    private int relationID = -1;
    private int statusID = -1;
    private int typeID = -1;
    private int roleTypeID = -1;

    private int index = 1;

    /**
     * Kolekce pro uchovani referenci identifikatoru prvku a identifikatoru formulare
     **/
    private Map<Integer, Integer> personSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> personIndexToIdMaper = new HashMap<>();
    private Map<Integer, Integer> artifactSegmentIdToFormIndexMaper = new HashMap<>();
    private Map<Integer, Integer> artifactIndexToIdMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitIndexToIdMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitSegmentIdToFormIndexMaper = new HashMap<>();
    private Map<Integer, Integer> commitIndexToIdMaper = new HashMap<>();
    private Map<Integer, Integer> commitIdtoIndexMaper = new HashMap<>();
    private Map<Integer, Integer> commitedConfigurationIndexToIdMaper = new HashMap<>();
    private Map<Integer, Integer> commitedConfigurationIdToIndexMaper = new HashMap<>();
    private Map<Integer, Integer> activityFormToIdMapper = new HashMap<>();
    private Map<Integer, Integer> configurationIndexToIdMapper = new HashMap<>();
    private Map<Integer, Integer> configurationIdToIndexMapper = new HashMap<>();

    public int setDataToConfigurationMappers(int segmentId) {
        configurationIdToIndexMapper.put(segmentId, index);
        configurationIndexToIdMapper.put(index, segmentId);
        configID = segmentId;
        index++;
        return index - 1;
    }


    /**
     * Metoda pro nastaveni datovych struktor, pri nacitani prvku z xml
     *
     * @param segmentId identifikator CommitedConfiguration
     * @return form identifikator
     */
    public int setDataToCommitedConfigurationMapper(int segmentId) {
        commitedConfigurationIndexToIdMaper.put(index, segmentId);
        commitedConfigurationIdToIndexMaper.put(segmentId, index);
        commtedConfigurationID = segmentId;
        index++;
        return index - 1;
    }

    /**
     * Metoda pro nastaveni datovych struktor, pri nacitani prvku z xml
     *
     * @param segmentId identifikator commit
     * @return form identifikator
     */
    public int setDataToCommitMapper(int segmentId) {
        commitIndexToIdMaper.put(index, segmentId);
        commitIdtoIndexMaper.put(segmentId, index);
        index++;
        commidID = segmentId;
        return index - 1;
    }

    /**
     * Metoda pro nastaveni datovych struktor, pri nacitani prvku z xml
     *
     * @param segmentId identifikator Artifact
     * @return form identifikator
     */
    public int setDataToArtifactMappers(int segmentId) {

        artifactIndexToIdMaper.put(index, segmentId);
        artifactSegmentIdToFormIndexMaper.put(segmentId, index);
        index++;
        artifactID = segmentId;
        return index - 1;
    }

    /**
     * Metoda pro nastaveni datovych struktor, pri nacitani prvku z xml
     *
     * @param segmentId identifikator Person
     * @return form identifikator
     */
    public int setDataToPersonMappers(int segmentId) {
        personIndexToIdMaper.put(index, segmentId);
        personSegmentIndexToFormMaper.put(segmentId, index);
        index++;
        personID = segmentId;
        return index - 1;
    }


    /**
     * Metoda pro inkrementaci indexu Role type
     *
     * @return aktualni identifikator prvku
     */
    public int createRoleTypeID() {

        roleTypeID++;

        return roleTypeID;

    }

    /**
     * Metoda pro inkrementaci indexu Type
     *
     * @return aktualni identifikator prvku
     */
    public int createTypeID() {

        typeID++;

        return typeID;

    }

    /**
     * Metoda pro inkrementaci indexu Status
     *
     * @return aktualni identifikator prvku
     */
    public int createStatusID() {

        statusID++;

        return statusID;

    }

    /**
     * Metoda pro inkrementaci indexu Relation
     *
     * @return aktualni identifikator prvku
     */
    public int createRelationID() {

        relationID++;

        return relationID;

    }

    /**
     * Metoda pro inkrementaci indexu Resolution
     *
     * @return aktualni identifikator prvku
     */
    public int createResolutionID() {

        resolutionID++;

        return resolutionID;

    }

    /**
     * Metoda pro inkrementaci indexu Severity
     *
     * @return aktualni identifikator prvku
     */
    public int createSeverityID() {

        severityID++;

        return severityID;

    }

    /**
     * Metoda pro inkrementaci indexu Priority
     *
     * @return aktualni identifikator prvku
     */
    public int createPriorityID() {

        priorityID++;

        return priorityID;

    }

    /**
     * Metoda pro inkrementaci indexu Phase
     *
     * @return aktualni identifikator prvku
     */
    public int createPhaseID() {

        phaseID++;
        return phaseID;

    }

    /**
     * Metoda pro inkrementaci indexu Iteration
     *
     * @return aktualni identifikator prvku
     */
    public int createIterationID() {

        iterationID++;
        return iterationID;
    }

    /**
     * Metoda pro inkrementaci indexu Activity
     *
     * @return aktualni identifikator prvku
     */
    public int createActivityID() {

        activityID++;
        return activityID;
    }

    /**
     * Metoda pro inkrementaci indexu Work Unit
     *
     * @return aktualni identifikator prvku
     */
    public int createWorkUnitID() {

        workUnitID++;
        return workUnitID;
    }

    /**
     * Metoda pro inkrementaci indexu Milestone
     *
     * @return aktualni identifikator prvku
     */
    public int createMilestoneID() {
        milestoneID++;
        return milestoneID;
    }

    /**
     * Metoda pro inkrementaci indexu Criterion
     *
     * @return aktualni identifikator prvku
     */
    public int createCriterionID() {
        criteriumID++;
        return criteriumID;
    }

    /**
     * Metoda pro inkrementaci indexu Person
     * Dale je inkrementovan index poctu formularu(prvku na platne vyvolavajici editacni panel)
     * a reference v mapach
     *
     * @return aktualni identifikator formulare
     */
    public int createPersonID() {
        personID++;
        personIndexToIdMaper.put(index, personID);
        personSegmentIndexToFormMaper.put(personID, index);
        index++;
        return index - 1;
    }

    /**
     * Metoda pro inkrementaci indexu Branch
     *
     * @return aktualni identifikator prvku
     */
    public int createBranchID() {
        branchID++;
        return branchID;
    }

    /**
     * Metoda pro inkrementaci indexu Change
     *
     * @return aktualni identifikator prvku
     */
    public int createChangeID() {
        changeID++;
        return changeID;
    }

    /**
     * Metoda pro inkrementaci indexu Artifact
     * Dale je inkrementovan index poctu formularu(prvku na platne vyvolavajici editacni panel)
     * a reference v mapach
     *
     * @return aktualni identifikator formulare
     */
    public int createArtifactID() {
        artifactID++;
        artifactIndexToIdMaper.put(index, artifactID);
        artifactSegmentIdToFormIndexMaper.put(artifactID, index);
        index++;
        return index - 1;
    }

    /**
     * Metoda pro inkrementaci indexu Configuration Person
     *
     * @return aktualni identifikator prvku
     */
    public int createCPRID() {
        configPRID++;
        return configPRID;
    }

    /**
     * Metoda pro inkrementaci indexu Commit
     * Dale je inkrementovan index poctu formularu(prvku na platne vyvolavajici editacni panel)
     * a reference v mapach
     *
     * @return aktualni identifikator formulare
     */
    public int createCommitID() {
        commidID++;
        commitIndexToIdMaper.put(index, commidID);
        commitIdtoIndexMaper.put(commidID, index);
        index++;
        return index - 1;
    }

    /**
     * Metoda pro inkrementaci indexu Committed Configuration
     * Dale je inkrementovan index poctu formularu(prvku na platne vyvolavajici editacni panel)
     * a reference v mapach
     *
     * @return aktualni identifikator formulare
     */
    public int createCommiedConfigurationtID() {
        commtedConfigurationID++;
        commitedConfigurationIndexToIdMaper.put(index, commtedConfigurationID);
        commitedConfigurationIdToIndexMaper.put(commtedConfigurationID, index);
        index++;
        return index - 1;
    }

    /**
     * Metoda pro inkrementaci indexu Configuration
     * Dale je inkrementovan index poctu formularu(prvku na platne vyvolavajici editacni panel)
     * a reference v mapach
     *
     * @return aktualni identifikator formulare
     */
    public int createConfigurationID() {
        configID++;
        configurationIndexToIdMapper.put(index, configID);
        configurationIdToIndexMapper.put(configID, index);
        index++;
        return index - 1;
    }

    /**
     * Metoda pro inkrementaci indexu VCSTag
     *
     * @return aktualni identifikator prvku
     */
    public int createVCSTagID() {
        VCSTag++;
        return VCSTag;
    }


    /**
     * Getters and Setters
     **/
    public Integer getChangeId(int formIndex) {

        return getPersonIndexToIdMaper().get(formIndex);
    }

    public Integer getCommitFormIndex(int id) {
        return commitIdtoIndexMaper.get(id);
    }

    public Integer getCommitId(int formIndex) {
        return commitIndexToIdMaper.get(formIndex);
    }

    public Integer getCommitedConfigurationId(int formIndex) {
        return commitedConfigurationIndexToIdMaper.get(formIndex);
    }

    public Integer getArtifactId(int formIndex) {
        return artifactIndexToIdMaper.get(formIndex);
    }

    public Integer getCommitedConfigurationFormIndex(int id) {
        return commitedConfigurationIdToIndexMaper.get(id);
    }

    public Integer getArtifactIndex(int formIndex) {
        return artifactSegmentIdToFormIndexMaper.get(formIndex);
    }

    public Integer getWorkUnitIndex(int startSegmentId) {
        return getWorkUnitIndexToIdMaper().get(startSegmentId);
    }

    public Integer getRoleId(int formIndex) {
        return personIndexToIdMaper.get(formIndex);
    }

    public Integer getIterationId(int formIdentificator) {
        return commitedConfigurationIndexToIdMaper.get(formIdentificator);
    }

    public Integer getActivityId(int formIdentificator) {
        return activityFormToIdMapper.get(formIdentificator);
    }

    public Integer getConfigurationFormIndex(int index) {
        return configurationIdToIndexMapper.get(index);
    }

    public Integer getConfigurationId(int formIdentificator) {
        return configurationIndexToIdMapper.get(formIdentificator);
    }


    public int createLineID() {

        linesID++;

        return linesID;

    }

    public Map<Integer, Integer> getPersonIndexToIdMaper() {
        return personIndexToIdMaper;
    }

    public Map<Integer, Integer> getArtifactIndexToIdMaper() {
        return artifactIndexToIdMaper;
    }

    public Map<Integer, Integer> getWorkUnitIndexToIdMaper() {
        return workUnitIndexToIdMaper;
    }

    public Map<Integer, Integer> getWorkUnitSegmentIdToFormIndexMaper() {
        return workUnitSegmentIdToFormIndexMaper;
    }

    public Map<Integer, Integer> getPersonSegmentIndexToFormMaper() {
        return personSegmentIndexToFormMaper;
    }

    public Map<Integer, Integer> getArtifactSegmentIdToFormIndexMaper() {
        return artifactSegmentIdToFormIndexMaper;
    }


    public void setLinkId(Integer id) {
        linesID = id;
    }
}
