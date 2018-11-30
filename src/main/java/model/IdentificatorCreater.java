package model;

import java.util.HashMap;
import java.util.Map;

public class IdentificatorCreater {
    /**
     * Globální proměnné třídy
     **/
    private int linesID = -1;
    private int phaseID = -1;
    private int iterationID = -1;
    private int activityID = -1;
    private int workUnitID = -1;
    private int milestoneID = -1;
    private int criteriumID = -1;
    private int roleID = -1;
    private int branchID = -1;
    private int changeID = -1;
    private int artifactID = -1;
    private int configPRID = -1;
    private int configID = -1;
    private int tagID = -1;
    private int CPRID = -1;

    private int priorityID = -1;
    private int severityID = -1;
    private int resolutionID = -1;
    private int relationID = -1;
    private int statusID = -1;
    private int typeID = -1;
    private int roleTypeID = -1;

    private int index = 14;

    private Map<Integer, Integer> changeSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> changeIndexMaper = new HashMap<>();
    private Map<Integer, Integer> artifactSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> artifactIndexMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitIndexMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> phaseIndexMaper = new HashMap<>();
    private Map<Integer, Integer> iterationIndexMaper = new HashMap<>();
    private Map<Integer, Integer> activityIndexMaper = new HashMap<>();
    private Map<Integer, Integer> configurationFormToIndexMaper = new HashMap<>();
    private Map<Integer, Integer> configurationIndexToFormMaper = new HashMap<>();

    /**
     * Metody pro inkrementaci počtu daného prvku
     */

    public int createRoleTypeID() {

        roleTypeID++;

        return roleTypeID;

    }

    public int createTypeID() {

        typeID++;

        return typeID;

    }

    public int createStatusID() {

        statusID++;

        return statusID;

    }

    public int createRelationID() {

        relationID++;

        return relationID;

    }

    public int createResolutionID() {

        resolutionID++;

        return resolutionID;

    }

    public int createSeverityID() {

        severityID++;

        return severityID;

    }

    public int createPriorityID() {

        priorityID++;

        return priorityID;

    }

    public int createPhaseID() {

        phaseID++;
        phaseIndexMaper.put(index, phaseID);
        index++;
        return index - 1;

    }

    public int createIterationID() {

        iterationID++;
        iterationIndexMaper.put(index, iterationID);
        index++;
        return index - 1;
    }

    public int createActivityID() {

        activityID++;
        activityIndexMaper.put(index, activityID);
        index++;
        return index - 1;
    }

    public int createWorkUnitID() {

        workUnitID++;
        workUnitIndexMaper.put(index, workUnitID);
        workUnitSegmentIndexToFormMaper.put(workUnitID, index);
        index++;
        return index - 1;
    }

    public int createMilestoneID() {
        milestoneID++;
        return milestoneID;
    }

    public int createCriterionID() {
        criteriumID++;
        return criteriumID;
    }

    public int createRoleID() {
        roleID++;
        return roleID;
    }

    public int createBranchID() {
        branchID++;
        return branchID;
    }

    public int createChangeID() {
        changeID++;
        changeIndexMaper.put(index, changeID);
        changeSegmentIndexToFormMaper.put(changeID, index);
        index++;
        return index - 1;
    }

    public int createArtifactID() {
        artifactID++;
        artifactIndexMaper.put(index, artifactID);
        artifactSegmentIndexToFormMaper.put(artifactID, index);
        index++;
        return index - 1;
    }

    public int createCPRID() {
        configPRID++;
        return configPRID;
    }

    public int createConfigurationID() {
        configID++;
        configurationFormToIndexMaper.put(index, configID);
        configurationIndexToFormMaper.put(configID, index);
        index++;
        return index - 1;
    }

    public int createTagID() {
        tagID++;
        return tagID;
    }

    public Integer getChangeIndex(int formIndex) {

        return getChangeIndexMaper().get(formIndex);
    }

    public Integer getArtifactIndex(int formIndex) {
        return getArtifactIndexMaper().get(formIndex);
    }

    public Integer getWorkUnitIndex(int startSegmentId) {
        return getWorkUnitIndexMaper().get(startSegmentId);
    }

    public Integer getPhaseIndex(int formIndex) {
        return phaseIndexMaper.get(formIndex);
    }

    public Integer getIterationIndex(int formIdentificator) {
        return iterationIndexMaper.get(formIdentificator);
    }

    public Integer getActivityIndex(int formIdentificator) {
        return activityIndexMaper.get(formIdentificator);
    }

    public Integer getConfigurationFormIndex(int index) {
        return configurationIndexToFormMaper.get(index);
    }

    public Integer getConfigurationIndex(int formIdentificator) {
        return configurationFormToIndexMaper.get(formIdentificator);
    }


    public int createLineID() {

        linesID++;

        return linesID;

    }

    public Map<Integer, Integer> getChangeIndexMaper() {
        return changeIndexMaper;
    }

    public Map<Integer, Integer> getArtifactIndexMaper() {
        return artifactIndexMaper;
    }

    public Map<Integer, Integer> getWorkUnitIndexMaper() {
        return workUnitIndexMaper;
    }

    public Map<Integer, Integer> getWorkUnitSegmentIndexToFormMaper() {
        return workUnitSegmentIndexToFormMaper;
    }

    public Map<Integer, Integer> getChangeSegmentIndexToFormMaper() {
        return changeSegmentIndexToFormMaper;
    }

    public Map<Integer, Integer> getArtifactSegmentIndexToFormMaper() {
        return artifactSegmentIndexToFormMaper;
    }
}
