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

    private Map<Integer, Integer> roleSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> roleIndexToIdMaper = new HashMap<>();
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

    public void setDataToConfigurationMappers(int formIndex, int segmentId){
        configurationIdToIndexMapper.put(segmentId, formIndex);
        configurationIndexToIdMapper.put(formIndex, segmentId);
        configID++;
    }
    
    public void setDataToActivityMapper(int formIndex, int segmentId){
        activityFormToIdMapper.put(formIndex, segmentId);
        activityID++;
    }

    public void setDataToIterationMapper(int formIndex, int segmentId){
        commitedConfigurationIndexToIdMaper.put(formIndex, segmentId);
        iterationID++;
    }

    public void setDataToCommitMapper(int formIndex, int segmentId){
        commitIndexToIdMaper.put(formIndex, segmentId);
        commidID++;
    }

    public void setDataToWorkUnitsMappers(int formIndex, int segmentId){
        workUnitIndexToIdMaper.put(formIndex, segmentId);
        workUnitSegmentIdToFormIndexMaper.put(segmentId, formIndex);
        workUnitID++;
    }

    public void setDataToArtifactMappers(int formIndex, int segmentId){
        artifactIndexToIdMaper.put(formIndex, segmentId);
        artifactSegmentIdToFormIndexMaper.put(segmentId, formIndex);
        artifactID++;

    }

    public void setDataToChangeMappers(int formIndex, int segmentId){
        roleIndexToIdMaper.put(formIndex, segmentId);
        roleSegmentIndexToFormMaper.put(segmentId, formIndex);
        changeID++;
    }
    
    
    
    
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
        return phaseID;

    }

    public int createIterationID() {

        iterationID++;
        return iterationID;
    }

    public int createActivityID() {

        activityID++;
        return activityID;
    }

    public int createWorkUnitID() {

        workUnitID++;
        return workUnitID;
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
        roleIndexToIdMaper.put(index, roleID);
        roleSegmentIndexToFormMaper.put(roleID, index);
        index++;
        return index - 1;
    }

    public int createBranchID() {
        branchID++;
        return branchID;
    }

    public int createChangeID() {
        changeID++;
        return changeID;
    }

    public int createArtifactID() {
        artifactID++;
        artifactIndexToIdMaper.put(index, artifactID);
        artifactSegmentIdToFormIndexMaper.put(artifactID, index);
        index++;
        return index -1;
    }

    public int createCPRID() {
        configPRID++;
        return configPRID;
    }

    public int createCommitID() {
        commidID++;
        commitIndexToIdMaper.put(index, commidID);
        commitIdtoIndexMaper.put(commidID, index);
        index++;
        return index - 1;
    }

    public int createCommiedConfigurationtID() {
        commtedConfigurationID++;
        commitedConfigurationIndexToIdMaper.put(index, commtedConfigurationID);
        commitedConfigurationIdToIndexMaper.put(commtedConfigurationID, index);
        index++;
        return index - 1;
    }

    public int createConfigurationID() {
        configID++;
        configurationIndexToIdMapper.put(index, configID);
        configurationIdToIndexMapper.put(configID, index);
        index++;
        return index - 1;
    }

    public int createVCSTagID() {
        VCSTag++;
        return VCSTag;
    }

    public int createTagID() {
        tagID++;
        return tagID;
    }

    public Integer getChangeId(int formIndex) {

        return getRoleIndexToIdMaper().get(formIndex);
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
        return getArtifactIndexToIdMaper().get(formIndex);
    }

    public Integer getWorkUnitIndex(int startSegmentId) {
        return getWorkUnitIndexToIdMaper().get(startSegmentId);
    }

    public Integer getRoleId(int formIndex) {
        return roleIndexToIdMaper.get(formIndex);
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

    public Map<Integer, Integer> getRoleIndexToIdMaper() {
        return roleIndexToIdMaper;
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

    public Map<Integer, Integer> getRoleSegmentIndexToFormMaper() {
        return roleSegmentIndexToFormMaper;
    }

    public Map<Integer, Integer> getArtifactSegmentIdToFormIndexMaper() {
        return artifactSegmentIdToFormIndexMaper;
    }


}
