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

    private int index = 0;

    private Map<Integer, Integer> roleSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> roleIndexMaper = new HashMap<>();
    private Map<Integer, Integer> artifactSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> artifactIndexMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitIndexMaper = new HashMap<>();
    private Map<Integer, Integer> workUnitSegmentIndexToFormMaper = new HashMap<>();
    private Map<Integer, Integer> commitIndexMaper = new HashMap<>();
    private Map<Integer, Integer> commitedConfigurationIndexMaper = new HashMap<>();
    private Map<Integer, Integer> activityFormToIdMapper = new HashMap<>();
    private Map<Integer, Integer> configurationFormToIndexMapper = new HashMap<>();
    private Map<Integer, Integer> configurationIndexToFormMapper = new HashMap<>();

    public void setDataToConfigurationMappers(int formIndex, int segmentId){
        configurationIndexToFormMapper.put(segmentId, formIndex);
        configurationFormToIndexMapper.put(formIndex, segmentId);
        configID++;
    }
    
    public void setDataToActivityMapper(int formIndex, int segmentId){
        activityFormToIdMapper.put(formIndex, segmentId);
        activityID++;
    }

    public void setDataToIterationMapper(int formIndex, int segmentId){
        commitedConfigurationIndexMaper.put(formIndex, segmentId);
        iterationID++;
    }

    public void setDataToCommitMapper(int formIndex, int segmentId){
        commitIndexMaper.put(formIndex, segmentId);
        commidID++;
    }

    public void setDataToWorkUnitsMappers(int formIndex, int segmentId){
        workUnitIndexMaper.put(formIndex, segmentId);
        workUnitSegmentIndexToFormMaper.put(segmentId, formIndex);
        workUnitID++;
    }

    public void setDataToArtifactMappers(int formIndex, int segmentId){
        artifactIndexMaper.put(formIndex, segmentId);
        artifactSegmentIndexToFormMaper.put(segmentId, formIndex);
        artifactID++;

    }

    public void setDataToChangeMappers(int formIndex, int segmentId){
        roleIndexMaper.put(formIndex, segmentId);
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
        roleIndexMaper.put(index, roleID);
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
        artifactIndexMaper.put(index, artifactID);
        artifactSegmentIndexToFormMaper.put(artifactID, index);
        index++;
        return index -1;
    }

    public int createCPRID() {
        configPRID++;
        return configPRID;
    }

    public int createCommitID() {
        commidID++;
        commitIndexMaper.put(index, commidID);
        index++;
        return index - 1;
    }

    public int createCommiedConfigurationtID() {
        commtedConfigurationID++;
        commitedConfigurationIndexMaper.put(index, commtedConfigurationID);
        index++;
        return index - 1;
    }

    public int createConfigurationID() {
        configID++;
        configurationFormToIndexMapper.put(index, configID);
        configurationIndexToFormMapper.put(configID, index);
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

        return getRoleIndexMaper().get(formIndex);
    }

    public Integer getCommitId(int formIndex) {
        return commitIndexMaper.get(formIndex);
    }

    public Integer getCommitedConfigurationId(int formIndex) {
        return commitedConfigurationIndexMaper.get(formIndex);
    }

    public Integer getArtifactIndex(int formIndex) {
        return getArtifactIndexMaper().get(formIndex);
    }

    public Integer getWorkUnitIndex(int startSegmentId) {
        return getWorkUnitIndexMaper().get(startSegmentId);
    }

    public Integer getRoleId(int formIndex) {
        return roleIndexMaper.get(formIndex);
    }

    public Integer getIterationId(int formIdentificator) {
        return commitedConfigurationIndexMaper.get(formIdentificator);
    }

    public Integer getActivityId(int formIdentificator) {
        return activityFormToIdMapper.get(formIdentificator);
    }

    public Integer getConfigurationFormIndex(int index) {
        return configurationIndexToFormMapper.get(index);
    }

    public Integer getConfigurationId(int formIdentificator) {
        return configurationFormToIndexMapper.get(formIdentificator);
    }


    public int createLineID() {

        linesID++;

        return linesID;

    }

    public Map<Integer, Integer> getRoleIndexMaper() {
        return roleIndexMaper;
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

    public Map<Integer, Integer> getRoleSegmentIndexToFormMaper() {
        return roleSegmentIndexToFormMaper;
    }

    public Map<Integer, Integer> getArtifactSegmentIndexToFormMaper() {
        return artifactSegmentIndexToFormMaper;
    }


}
