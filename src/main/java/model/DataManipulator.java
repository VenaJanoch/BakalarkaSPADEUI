package model;

import SPADEPAC.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida slouzici pro upravu dat pro kontroler potazmo view, copirovani jednotlivych prvku datoveho modelu
 *
 * @author Václav Janoch
 */

public class DataManipulator {

    private DataModel dataModel;


    public DataManipulator(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Artifact
     *
     * @param artifactId    identificator prvku pro kopirovani
     * @param newArtifactId identificator prvku pro nakopirovani dat
     * @param x             nova x pozice na platne
     * @param y             nova y pozice na platne
     */
    public void copyDataFromArtifact(int artifactId, int newArtifactId, double x, double y) {
        Artifact oldArtifact = dataModel.getArtifact(artifactId);
        Artifact newArtifact = dataModel.getArtifact(newArtifactId);
        Coordinates coordinates = dataModel.createCoords((int) x, (int) y);

        newArtifact.setAlias(String.valueOf(newArtifactId));
        newArtifact.setCoordinates(coordinates);
        newArtifact.getName().addAll(oldArtifact.getName());
        newArtifact.getNameIndicator().addAll(oldArtifact.getNameIndicator());
        newArtifact.getDescription().addAll(oldArtifact.getDescription());
        newArtifact.getDescriptionIndicator().addAll(oldArtifact.getDescriptionIndicator());
        newArtifact.setExist(oldArtifact.isExist());
        newArtifact.getMimeType().addAll(oldArtifact.getMimeType());
        newArtifact.getMimeTypeIndex().addAll(oldArtifact.getMimeTypeIndex());
        newArtifact.getMimeTypeIndicator().addAll(oldArtifact.getMimeTypeIndicator());
        newArtifact.getCreated().addAll(oldArtifact.getCreated());
        newArtifact.getCreatedIndicator().addAll(oldArtifact.getCreatedIndicator());
        newArtifact.getAuthorIndex().addAll(oldArtifact.getAuthorIndex());
        newArtifact.getAuthorIndicator().addAll(oldArtifact.getAuthorIndicator());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu CommitedConfigruration
     *
     * @param commitedConfigurationId    identificator prvku pro kopirovani
     * @param newCommitedConfigurationId identificator prvku pro nakopirovani dat
     * @param x                          nova x pozice na platne
     * @param y                          nova y pozice na platne
     */
    public void copyDataFromCommitedConfiguration(int commitedConfigurationId, int newCommitedConfigurationId, double x, double y) {
        CommitedConfiguration oldCommitedConfiguration = dataModel.getCommitedConfiguration(commitedConfigurationId);
        CommitedConfiguration newCommitedConfiguration = dataModel.getCommitedConfiguration(newCommitedConfigurationId);
        Coordinates coordinates = dataModel.createCoords((int) x, (int) y);

        newCommitedConfiguration.setAlias(String.valueOf(newCommitedConfigurationId));
        newCommitedConfiguration.setCoordinates(coordinates);
        newCommitedConfiguration.getName().addAll(oldCommitedConfiguration.getName());
        newCommitedConfiguration.getNameIndicator().addAll(oldCommitedConfiguration.getNameIndicator());
        newCommitedConfiguration.setExist(oldCommitedConfiguration.isExist());
        newCommitedConfiguration.getCommitedDay().addAll(oldCommitedConfiguration.getCommitedDay());
        newCommitedConfiguration.getCommitedDayIndicator().addAll(oldCommitedConfiguration.getCommitedDayIndicator());
        newCommitedConfiguration.getDescription().addAll(oldCommitedConfiguration.getDescription());
        newCommitedConfiguration.getDescriptionIndicator().addAll(oldCommitedConfiguration.getDescriptionIndicator());
        newCommitedConfiguration.getCreated().addAll(oldCommitedConfiguration.getCreated());
        newCommitedConfiguration.getCreatedIndicator().addAll(oldCommitedConfiguration.getCreatedIndicator());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Commit
     *
     * @param commitId    identificator prvku pro kopirovani
     * @param newCommitId identificator prvku pro nakopirovani dat
     * @param x           nova x pozice na platne
     * @param y           nova y pozice na platne
     */
    public void copyDataFromCommit(int commitId, int newCommitId, double x, double y) {
        Commit oldCommit = dataModel.getCommit(commitId);
        Commit newCommit = dataModel.getCommit(newCommitId);
        Coordinates coordinates = dataModel.createCoords((int) x, (int) y);

        newCommit.setAlias(String.valueOf(newCommitId));
        newCommit.setCoordinates(coordinates);
        newCommit.getName().addAll(oldCommit.getName());
        newCommit.getNameIndicator().addAll(oldCommit.getNameIndicator());
        newCommit.setExist(oldCommit.isExist());
        newCommit.getDescription().addAll(oldCommit.getDescription());
        newCommit.getDescriptionIndicator().addAll(oldCommit.getDescriptionIndicator());
        newCommit.getCreated().addAll(oldCommit.getCreated());
        newCommit.getCreatedIndicator().addAll(oldCommit.getCreatedIndicator());

    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Configuration
     *
     * @param configurationId    identificator prvku pro kopirovani
     * @param newConfigurationId identificator prvku pro nakopirovani dat
     * @param x                  nova x pozice na platne
     * @param y                  nova y pozice na platne
     */
    public void copyDataFromConfiguration(int configurationId, int newConfigurationId, double x, double y) {
        Configuration oldConfiguration = dataModel.getConfiguration(configurationId);
        Configuration newConfiguration = dataModel.getConfiguration(newConfigurationId);

        Coordinates coordinates = dataModel.createCoords((int) x, (int) y);
        newConfiguration.setAlias(String.valueOf(newConfigurationId));
        newConfiguration.setCoordinates(coordinates);
        newConfiguration.getName().addAll(oldConfiguration.getName());
        newConfiguration.getNameIndicator().addAll(oldConfiguration.getNameIndicator());
        newConfiguration.setExist(oldConfiguration.isExist());
        newConfiguration.getCreated().addAll(oldConfiguration.getCreated());
        newConfiguration.getAuthorIndex().addAll(oldConfiguration.getAuthorIndex());
        newConfiguration.getAuthorIndicator().addAll(oldConfiguration.getAuthorIndicator());
        newConfiguration.getCPRsIndicator().addAll(oldConfiguration.getCPRsIndicator());
        newConfiguration.getCPRsIndexs().addAll(oldConfiguration.getCPRsIndexs());
        newConfiguration.getChangesIndicator().addAll(oldConfiguration.getChangesIndicator());
        newConfiguration.getChangesIndexs().addAll(oldConfiguration.getChangesIndexs());
        newConfiguration.getCreatedIndicator().addAll(oldConfiguration.getCreatedIndicator());
        newConfiguration.getBranchIndexs().addAll(oldConfiguration.getBranchIndexs());
        newConfiguration.getBranchIndicator().addAll(oldConfiguration.getBranchIndicator());
        newConfiguration.getTagIndex().addAll(oldConfiguration.getTagIndex());
        newConfiguration.getTagsIndicator().addAll(oldConfiguration.getTagsIndicator());
        newConfiguration.getDescription().addAll(oldConfiguration.getDescription());
        newConfiguration.getDescriptionIndicator().addAll(oldConfiguration.getDescriptionIndicator());

    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Person
     *
     * @param personId    identificator prvku pro kopirovani
     * @param newPersonId identificator prvku pro nakopirovani dat
     * @param x           nova x pozice na platne
     * @param y           nova y pozice na platne
     */
    public void copyDataFromPerson(int personId, int newPersonId, double x, double y) {
        Person oldPerson = dataModel.getPerson(personId);
        Person newPerson = dataModel.getPerson(newPersonId);

        Coordinates coordinates = dataModel.createCoords((int) x, (int) y);

        newPerson.setCoordinates(coordinates);
        newPerson.getName().addAll(oldPerson.getName());
        newPerson.getNameIndicator().addAll(oldPerson.getNameIndicator());
        newPerson.setExist(oldPerson.isExist());
        newPerson.getType().addAll(oldPerson.getType());
        newPerson.getTypeIndicator().addAll(oldPerson.getTypeIndicator());
        newPerson.setAlias(String.valueOf(newPersonId));
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o segmentu Phase
     *
     * @param phaseId    identificator prvku pro kopirovani
     * @param newPhaseId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromPhase(int phaseId, int newPhaseId) {
        Phase oldPhase = dataModel.getPhase(phaseId);
        Phase newPhase = dataModel.getPhase(newPhaseId);

        newPhase.setAlias(String.valueOf(newPhaseId));
        newPhase.getName().addAll(oldPhase.getName());
        newPhase.getNameIndicator().addAll(oldPhase.getNameIndicator());
        newPhase.getDescription().addAll(oldPhase.getDescription());
        newPhase.getDescriptionIndicator().addAll(oldPhase.getDescriptionIndicator());
        newPhase.getEndDate().addAll(oldPhase.getEndDate());
        newPhase.getEndDateIndicator().addAll(oldPhase.getEndDateIndicator());
        newPhase.setExist(oldPhase.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Work Unit
     *
     * @param workUnitId    identificator prvku pro kopirovani
     * @param newWorkUnitId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromWorkUnit(int workUnitId, int newWorkUnitId) {
        WorkUnit oldWorkUnit = dataModel.getWorkUnit(workUnitId);
        WorkUnit newWorkUnit = dataModel.getWorkUnit(newWorkUnitId);

        newWorkUnit.setAlias(String.valueOf(newWorkUnitId));
        newWorkUnit.getName().addAll(oldWorkUnit.getName());
        newWorkUnit.getNameIndicator().addAll(oldWorkUnit.getNameIndicator());
        newWorkUnit.getDescription().addAll(oldWorkUnit.getDescription());
        newWorkUnit.getDescriptionIndicator().addAll(oldWorkUnit.getDescriptionIndicator());
        newWorkUnit.getCreated().addAll(oldWorkUnit.getCreated());
        newWorkUnit.getCreatedIndicator().addAll(oldWorkUnit.getCreatedIndicator());
        newWorkUnit.getEstimatedTimeIndicator().addAll(oldWorkUnit.getEstimatedTimeIndicator());
        newWorkUnit.getEstimatedTime().addAll(oldWorkUnit.getEstimatedTime());
        newWorkUnit.getProgressIndicator().addAll(oldWorkUnit.getProgressIndicator());
        newWorkUnit.getProgress().addAll(oldWorkUnit.getProgress());
        newWorkUnit.getCategoryIndicator().addAll(oldWorkUnit.getCategoryIndicator());
        newWorkUnit.getCategory().addAll(oldWorkUnit.getCategory());
        newWorkUnit.setExist(oldWorkUnit.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o segmentu Iteration
     *
     * @param iterationId    identificator prvku pro kopirovani
     * @param newIterationId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromIteration(int iterationId, int newIterationId) {
        Iteration oldIteration = dataModel.getIteration(iterationId);
        Iteration newIteration = dataModel.getIteration(newIterationId);

        newIteration.setAlias(String.valueOf(newIterationId));
        newIteration.getName().addAll(oldIteration.getName());
        newIteration.getNameIndicator().addAll(oldIteration.getNameIndicator());
        newIteration.getDescription().addAll(oldIteration.getDescription());
        newIteration.getDescriptionIndicator().addAll(oldIteration.getDescriptionIndicator());
        newIteration.getEndDate().addAll(oldIteration.getEndDate());
        newIteration.getEndDateIndicator().addAll(oldIteration.getEndDateIndicator());
        newIteration.getStartDate().addAll(oldIteration.getStartDate());
        newIteration.getStartDateIndicator().addAll(oldIteration.getStartDateIndicator());
        newIteration.setExist(oldIteration.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o segmentu Activity
     *
     * @param activityId    identificator prvku pro kopirovani
     * @param newActivityId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromActivity(int activityId, int newActivityId) {
        Activity oldActivity = dataModel.getActivity(activityId);
        Activity newActivity = dataModel.getActivity(newActivityId);

        newActivity.setAlias(String.valueOf(newActivityId));
        newActivity.getName().addAll(oldActivity.getName());
        newActivity.getNameIndicator().addAll(oldActivity.getNameIndicator());
        newActivity.getDescription().addAll(oldActivity.getDescription());
        newActivity.getDescriptionIndicator().addAll(oldActivity.getDescriptionIndicator());
        newActivity.getEndDate().addAll(oldActivity.getEndDate());
        newActivity.getEndDateIndicator().addAll(oldActivity.getEndDateIndicator());
        newActivity.setExist(oldActivity.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Milestone
     *
     * @param milestoneId    identificator prvku pro kopirovani
     * @param newMilestoneId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromMilestone(int milestoneId, int newMilestoneId) {
        Milestone oldMilestone = dataModel.getMilestone(milestoneId);
        Milestone newMilestone = dataModel.getMilestone(newMilestoneId);

        newMilestone.setAlias(String.valueOf(newMilestoneId));
        newMilestone.getName().addAll(oldMilestone.getName());
        newMilestone.getNameIndicator().addAll(oldMilestone.getNameIndicator());
        newMilestone.getDescription().addAll(oldMilestone.getDescription());
        newMilestone.getDescriptionIndicator().addAll(oldMilestone.getDescriptionIndicator());
        newMilestone.setExist(oldMilestone.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Criterion
     *
     * @param criterionId    identificator prvku pro kopirovani
     * @param newCriterionId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromCriterion(int criterionId, int newCriterionId) {
        Criterion oldCriterion = dataModel.getCriterion(criterionId);
        Criterion newCriterion = dataModel.getCriterion(newCriterionId);

        newCriterion.setAlias(String.valueOf(newCriterionId));
        newCriterion.getName().addAll(oldCriterion.getName());
        newCriterion.getNameIndicator().addAll(oldCriterion.getNameIndicator());
        newCriterion.getDescription().addAll(oldCriterion.getDescription());
        newCriterion.getDescriptionIndicator().addAll(oldCriterion.getDescriptionIndicator());
        newCriterion.setExist(oldCriterion.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Configuration Person Relation
     *
     * @param cprId    identificator prvku pro kopirovani
     * @param newCPRId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromCPR(int cprId, int newCPRId) {
        ConfigPersonRelation oldCPR = dataModel.getConfigPersonRelation(cprId);
        ConfigPersonRelation newCPR = dataModel.getConfigPersonRelation(newCPRId);

        newCPR.setAlias(String.valueOf(newCPRId));
        newCPR.getName().addAll(oldCPR.getName());
        newCPR.getNameIndicator().addAll(oldCPR.getNameIndicator());
        newCPR.getDescription().addAll(oldCPR.getDescription());
        newCPR.getDescriptionIndicator().addAll(oldCPR.getDescriptionIndicator());
        newCPR.setExist(oldCPR.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Branch
     *
     * @param branchId    identificator prvku pro kopirovani
     * @param newBranchId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromBranch(int branchId, int newBranchId) {
        Branch oldBranch = dataModel.getBranch(branchId);
        Branch newBranch = dataModel.getBranch(newBranchId);

        newBranch.setAlias(String.valueOf(newBranchId));
        newBranch.getName().addAll(oldBranch.getName());
        newBranch.getNameIndicator().addAll(oldBranch.getNameIndicator());
        newBranch.setIsMain(oldBranch.isIsMain());
        //   newBranch.get().addAll(oldBranch.getDescription());
        //   newBranch.getDescriptionIndicator().addAll(oldBranch.getDescriptionIndicator());
        newBranch.setExist(oldBranch.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Change
     *
     * @param changeId    identificator prvku pro kopirovani
     * @param newChangeId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromChange(int changeId, int newChangeId) {
        Change oldChange = dataModel.getChange(changeId);
        Change newChange = dataModel.getChange(newChangeId);

        newChange.setAlias(String.valueOf(newChangeId));
        newChange.getName().addAll(oldChange.getName());
        newChange.getNameIndicator().addAll(oldChange.getNameIndicator());
        newChange.getDescription().addAll(oldChange.getDescription());
        newChange.getDescriptionIndicator().addAll(oldChange.getDescriptionIndicator());
        newChange.setExist(oldChange.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu VCSTag
     *
     * @param vcsTagId    identificator prvku pro kopirovani
     * @param newVCSTagId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromVCSTag(int vcsTagId, int newVCSTagId) {
        VCSTag oldVCSTag = dataModel.getVCSTag(vcsTagId);
        VCSTag newVCSTag = dataModel.getVCSTag(newVCSTagId);

        newVCSTag.setAlias(String.valueOf(newVCSTagId));
        newVCSTag.getName().addAll(oldVCSTag.getName());
        newVCSTag.getNameIndicator().addAll(oldVCSTag.getNameIndicator());
        newVCSTag.getDescription().addAll(oldVCSTag.getDescription());
        newVCSTag.getDescriptionIndicator().addAll(oldVCSTag.getDescriptionIndicator());
        newVCSTag.setExist(oldVCSTag.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Role Type
     *
     * @param roleTypeId    identificator prvku pro kopirovani
     * @param newRoleTypeId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromRoleType(int roleTypeId, int newRoleTypeId) {
        RoleType oldRoleType = dataModel.getRoleType(roleTypeId);
        RoleType newRoleType = dataModel.getRoleType(newRoleTypeId);

        newRoleType.setAlias(String.valueOf(newRoleTypeId));
        newRoleType.getName().addAll(oldRoleType.getName());
        newRoleType.getNameIndicator().addAll(oldRoleType.getNameIndicator());
        newRoleType.getDescription().addAll(oldRoleType.getDescription());
        newRoleType.getDescriptionIndicator().addAll(oldRoleType.getDescriptionIndicator());
        newRoleType.getRoleTypeSuperClassIndex().addAll(oldRoleType.getRoleTypeSuperClassIndex());
        newRoleType.getRoleTypeSuperClass().addAll(oldRoleType.getRoleTypeSuperClass());
        newRoleType.getRoleTypeClassIndex().addAll(oldRoleType.getRoleTypeClassIndex());
        newRoleType.getRoleTypeClass().addAll(oldRoleType.getRoleTypeClass());

        newRoleType.setExist(oldRoleType.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Priority
     *
     * @param priorityId    identificator prvku pro kopirovani
     * @param newPriorityId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromPriority(int priorityId, int newPriorityId) {
        Priority oldPriority = dataModel.getPriority(priorityId);
        Priority newPriority = dataModel.getPriority(newPriorityId);

        newPriority.setAlias(String.valueOf(newPriorityId));
        newPriority.getName().addAll(oldPriority.getName());
        newPriority.getNameIndicator().addAll(oldPriority.getNameIndicator());
        newPriority.getPrioritySuperClassIndex().addAll(oldPriority.getPrioritySuperClassIndex());
        newPriority.getPrioritySuperClass().addAll(oldPriority.getPrioritySuperClass());
        newPriority.getPriorityClassIndex().addAll(oldPriority.getPriorityClassIndex());
        newPriority.getPriorityClass().addAll(oldPriority.getPriorityClass());

        newPriority.setExist(oldPriority.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Severity
     *
     * @param severityId    identificator prvku pro kopirovani
     * @param newSeverityId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromSeverity(int severityId, int newSeverityId) {
        Severity oldSeverity = dataModel.getSeverity(severityId);
        Severity newSeverity = dataModel.getSeverity(newSeverityId);

        newSeverity.setAlias(String.valueOf(newSeverityId));
        newSeverity.getName().addAll(oldSeverity.getName());
        newSeverity.getNameIndicator().addAll(oldSeverity.getNameIndicator());
        newSeverity.getSeveritySuperClassIndex().addAll(oldSeverity.getSeveritySuperClassIndex());
        newSeverity.getSeveritySuperClass().addAll(oldSeverity.getSeveritySuperClass());
        newSeverity.getSeverityClassIndex().addAll(oldSeverity.getSeverityClassIndex());
        newSeverity.getSeverityClass().addAll(oldSeverity.getSeverityClass());

        newSeverity.setExist(oldSeverity.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Status
     *
     * @param statusId    identificator prvku pro kopirovani
     * @param newStatusId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromStatus(int statusId, int newStatusId) {
        Status oldStatus = dataModel.getStatus(statusId);
        Status newStatus = dataModel.getStatus(newStatusId);

        newStatus.setAlias(String.valueOf(newStatusId));
        newStatus.getName().addAll(oldStatus.getName());
        newStatus.getNameIndicator().addAll(oldStatus.getNameIndicator());
        newStatus.getStatusSuperClassIndex().addAll(oldStatus.getStatusSuperClassIndex());
        newStatus.getStatusSuperClass().addAll(oldStatus.getStatusSuperClass());
        newStatus.getStatusClassIndex().addAll(oldStatus.getStatusClassIndex());
        newStatus.getStatusClass().addAll(oldStatus.getStatusClass());

        newStatus.setExist(oldStatus.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Type
     *
     * @param typeId    identificator prvku pro kopirovani
     * @param newTypeId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromType(int typeId, int newTypeId) {
        Type oldType = dataModel.getType(typeId);
        Type newType = dataModel.getType(newTypeId);

        newType.setAlias(String.valueOf(newTypeId));
        newType.getName().addAll(oldType.getName());
        newType.getNameIndicator().addAll(oldType.getNameIndicator());
        newType.getTypeSuperClassIndex().addAll(oldType.getTypeSuperClassIndex());
        newType.getTypeSuperClass().addAll(oldType.getTypeSuperClass());
        newType.getTypeClassIndex().addAll(oldType.getTypeClassIndex());
        newType.getTypeClass().addAll(oldType.getTypeClass());

        newType.setExist(oldType.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Relation
     *
     * @param relationId    identificator prvku pro kopirovani
     * @param newRelationId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromRelation(int relationId, int newRelationId) {
        Relation oldRelation = dataModel.getRelation(relationId);
        Relation newRelation = dataModel.getRelation(newRelationId);

        newRelation.setAlias(String.valueOf(newRelationId));
        newRelation.getName().addAll(oldRelation.getName());
        newRelation.getNameIndicator().addAll(oldRelation.getNameIndicator());
        newRelation.getRelationSuperClassIndex().addAll(oldRelation.getRelationSuperClassIndex());
        newRelation.getRelationSuperClass().addAll(oldRelation.getRelationSuperClass());
        newRelation.getRelationClassIndex().addAll(oldRelation.getRelationClassIndex());
        newRelation.getRelationClass().addAll(oldRelation.getRelationClass());

        newRelation.setExist(oldRelation.isExist());
    }

    /**
     * Metoda pro kopirovani zakladnich informaci o elementu Resolution
     *
     * @param resolutionId    identificator prvku pro kopirovani
     * @param newResolutionId identificator prvku pro nakopirovani dat
     */
    public void copyDataFromResolution(int resolutionId, int newResolutionId) {
        Resolution oldResolution = dataModel.getResolution(resolutionId);
        Resolution newResolution = dataModel.getResolution(newResolutionId);

        newResolution.setAlias(String.valueOf(newResolutionId));
        newResolution.getName().addAll(oldResolution.getName());
        newResolution.getNameIndicator().addAll(oldResolution.getNameIndicator());
        newResolution.getResolutionSuperClassIndex().addAll(oldResolution.getResolutionSuperClassIndex());
        newResolution.getResolutionSuperClass().addAll(oldResolution.getResolutionSuperClass());
        newResolution.getResolutionClassIndex().addAll(oldResolution.getResolutionClassIndex());
        newResolution.getResolutionClass().addAll(oldResolution.getResolutionClass());

        newResolution.setExist(oldResolution.isExist());
    }

    /**
     * Metoda pro prevod dat o Criterion z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getCriterionData(int id) {
        List[] data = new List[5];
        Criterion criterion = dataModel.getCriterion(id);

        if (criterion.getName() != null) {
            data[0] = criterion.getName();
        }

        if (criterion.getDescription() != null) {
            data[1] = criterion.getDescription();
        }

        if (criterion.getNameIndicator() != null) {
            data[2] = criterion.getNameIndicator();
        }

        if (criterion.getDescriptionIndicator() != null) {
            data[3] = criterion.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(criterion.isExist());
        list.add(null);
        list.add(criterion.getAlias());
        data[4] = list;

        return data;
    }

    /**
     * Metoda pro prevod dat o Milestone z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getMilestoneData(int id) {
        List[] data = new List[6];
        Milestone milestone = dataModel.getMilestones().get(id);
        if (milestone.getName() != null) {
            data[0] = milestone.getName();
        }

        if (milestone.getDescription() != null) {
            data[1] = milestone.getDescription();
        }

        if (milestone.getNameIndicator() != null) {
            data[2] = milestone.getNameIndicator();
        }

        if (milestone.getDescriptionIndicator() != null) {
            data[3] = milestone.getDescriptionIndicator();
        }

        if (milestone.getCriteriaIndicator() != null) {
            data[4] = milestone.getCriteriaIndicator();
        }

        List list = new ArrayList();
        list.add(milestone.isExist());
        list.add(null);
        list.add(milestone.getAlias());
        data[5] = list;


        return data;
    }

    /**
     * Metoda pro ziskani identifikatoru Criterion z elemtnu Milestone
     *
     * @param id identifikator Milestone
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getCriterionFromMilestone(int id) {
        Milestone milestone = dataModel.getMilestone(id);
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (CriterionList list1 : milestone.getCriteriaIndexs()) {
            list.add((ArrayList<Integer>) list1.getCriterions());
        }


        return list;
    }

    /**
     * Metoda pro prevod dat o Person z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getPersonData(int id) {
        List[] data = new List[5];
        Person role = dataModel.getPerson(id);

        if (role.getName() != null) {
            data[0] = role.getName();
        }

        if (role.getType() != null) {
            data[1] = role.getType();
        }

        if (role.getNameIndicator() != null) {
            data[2] = role.getNameIndicator();
        }


        if (role.getTypeIndicator() != null) {
            data[3] = role.getTypeIndicator();
        }

        ArrayList list = new ArrayList();
        list.add(role.isExist());
        list.add(role.getCount());
        list.add(role.getCountIndicator());
        list.add(role.getAlias());
        data[4] = list;


        return data;

    }

    /**
     * Metoda pro prevod dat o Role Type z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getRoleTypeData(int id) {
        List[] data = new List[7];
        RoleType roleType = dataModel.getRoleType(id);
        if (roleType.getName() != null) {
            data[0] = roleType.getName();
        }

        if (roleType.getRoleTypeClassIndex().size() != 0) {
            data[1] = roleType.getRoleTypeClassIndex();
        }

        if (roleType.getRoleTypeSuperClassIndex().size() != 0) {
            data[2] = roleType.getRoleTypeSuperClassIndex();
        }


        if (roleType.getNameIndicator() != null) {
            data[3] = roleType.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(roleType.isExist());
        list.add(null);
        list.add(roleType.getAlias());
        data[4] = list;

        if (roleType.getDescription() != null) {
            data[5] = roleType.getDescription();
        }

        if (roleType.getDescriptionIndicator() != null) {
            data[6] = roleType.getDescriptionIndicator();
        }


        return data;
    }

    /**
     * Metoda pro prevod dat o Severity z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getSeverityData(int id) {
        List[] data = new List[5];
        Severity severity = dataModel.getSeverity(id);
        if (severity.getName() != null) {
            data[0] = severity.getName();
        }

        if (severity.getSeverityClassIndex().size() != 0) {
            data[1] = severity.getSeverityClassIndex();
        }

        if (severity.getSeveritySuperClassIndex().size() != 0) {
            data[2] = severity.getSeveritySuperClassIndex();
        }


        if (severity.getNameIndicator() != null) {
            data[3] = severity.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(severity.isExist());
        list.add(null);
        list.add(severity.getAlias());
        data[4] = list;
        return data;
    }

    /**
     * Metoda pro prevod dat o Priority z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getPriorityData(int id) {
        List[] data = new List[5];
        Priority priority = dataModel.getPriority(id);
        if (priority.getName() != null) {
            data[0] = priority.getName();
        }

        if (priority.getPriorityClassIndex().size() != 0) {
            data[1] = priority.getPriorityClassIndex();
        }

        if (priority.getPrioritySuperClassIndex().size() != 0) {
            data[2] = priority.getPrioritySuperClassIndex();
        }


        if (priority.getNameIndicator() != null) {
            data[3] = priority.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(priority.isExist());
        list.add(null);
        list.add(priority.getAlias());
        data[4] = list;
        return data;
    }

    /**
     * Metoda pro prevod dat o Status z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getStatusData(int id) {
        List[] data = new List[5];
        Status status = dataModel.getStatus(id);
        if (status.getName() != null) {
            data[0] = status.getName();
        }

        if (status.getStatusClassIndex().size() != 0) {
            data[1] = status.getStatusClassIndex();
        }

        if (status.getStatusSuperClassIndex().size() != 0) {
            data[2] = status.getStatusSuperClassIndex();
        }


        if (status.getNameIndicator() != null) {
            data[3] = status.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(status.isExist());
        list.add(null);
        list.add(status.getAlias());
        data[4] = list;
        return data;
    }

    /**
     * Metoda pro prevod dat o Type z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getTypeData(int id) {
        List[] data = new List[5];
        Type type = dataModel.getType(id);
        if (type.getName() != null) {
            data[0] = type.getName();
        }

        if (type.getTypeClassIndex().size() != 0) {
            data[1] = type.getTypeClassIndex();
        }

        if (type.getTypeSuperClassIndex().size() != 0) {
            data[2] = type.getTypeSuperClassIndex();
        }


        if (type.getNameIndicator() != null) {
            data[3] = type.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(type.isExist());
        list.add(null);
        list.add(type.getAlias());
        data[4] = list;
        return data;
    }

    /**
     * Metoda pro prevod dat o Relation z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getRelationData(int id) {
        List[] data = new List[5];
        Relation relation = dataModel.getRelation(id);
        if (relation.getName() != null) {
            data[0] = relation.getName();
        }
        if (relation.getRelationClassIndex().size() != 0) {
            data[1] = relation.getRelationClassIndex();
        }

        if (relation.getRelationSuperClassIndex().size() != 0) {
            data[2] = relation.getRelationSuperClassIndex();
        }

        if (relation.getNameIndicator() != null) {
            data[3] = relation.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(relation.isExist());
        list.add(null);
        list.add(relation.getAlias());
        data[4] = list;

        return data;
    }

    /**
     * Metoda pro prevod dat o Resolution z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getResolutionData(int id) {
        List[] data = new List[5];
        Resolution resolution = dataModel.getResolution(id);

        if (resolution.getName() != null) {
            data[0] = resolution.getName();
        }
        if (resolution.getResolutionClassIndex().size() != 0) {
            data[1] = resolution.getResolutionClassIndex();
        }

        if (resolution.getResolutionSuperClassIndex().size() != 0) {
            data[2] = resolution.getResolutionSuperClassIndex();
        }


        if (resolution.getNameIndicator() != null) {
            data[3] = resolution.getNameIndicator();
        }

        List list = new ArrayList();
        list.add(resolution.isExist());
        list.add(null);
        list.add(resolution.getAlias());
        data[4] = list;

        if (resolution.getResolutionSuperClassIndex().size() != 0) {
            data[2] = resolution.getResolutionSuperClassIndex();
        }


        if (resolution.getNameIndicator() != null) {
            data[3] = resolution.getNameIndicator();
        }

        return data;
    }

    /**
     * Metoda pro prevod dat o Confing Person Relation z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getCPRData(int id) {
        List[] data = new List[7];
        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);

        if (cpr.getName() != null) {
            data[0] = cpr.getName();
        }

        if (cpr.getPersonIndex() != null) {
            data[1] = cpr.getPersonIndex();
        }

        if (cpr.getNameIndicator() != null) {
            data[2] = cpr.getNameIndicator();
        }

        if (cpr.getPersonIndicator() != null) {
            data[3] = cpr.getPersonIndicator();
        }
        List list = new ArrayList();
        list.add(cpr.isExist());
        list.add(null);
        list.add(cpr.getAlias());
        data[4] = list;

        if (cpr.getDescription() != null) {
            data[5] = cpr.getDescription();
        }

        if (cpr.getDescriptionIndicator() != null) {
            data[6] = cpr.getDescriptionIndicator();
        }

        return data;
    }

    /**
     * Metoda pro prevod dat o Branch z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getBranchStringData(int id) {
        List[] data = new List[3];
        Branch branch = dataModel.getBranch(id);
        ;
        if (branch.getName() != null) {
            data[0] = branch.getName();
        }
        if (branch.getNameIndicator() != null) {
            data[1] = branch.getNameIndicator();
        }
        List list = new ArrayList();
        list.add(branch.isExist());
        data[2] = list;
        if (branch.isIsMain() != null) {

            list.add(1, branch.isIsMain());
        }
        list.add(branch.getAlias());

        return data;
    }

    /**
     * Metoda pro prevod dat o Phase z datoveho modelu do seznamu s jednotlivymi parametry segmentu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getPhaseStringData(int id) {
        List[] data = new List[12];
        Phase phase = dataModel.getPhase(id);
        ;

        if (phase.getName() != null) {
            data[0] = phase.getName();
        }

        if (phase.getDescription() != null) {
            data[1] = phase.getDescription();
        }

        if (phase.getConfiguration() != null) {
            data[2] = phase.getConfiguration();
        }

        if (phase.getMilestoneIndex() != null) {
            data[3] = phase.getMilestoneIndex();
        }

        if (phase.getEndDate() != null) {
            data[4] = phase.getEndDate();
        }

        if (phase.getNameIndicator() != null) {
            data[5] = phase.getNameIndicator();
        }

        if (phase.getDescriptionIndicator() != null) {
            data[6] = phase.getDescriptionIndicator();
        }

        if (phase.getConfigurationIndicator() != null) {
            data[7] = phase.getConfigurationIndicator();
        }

        if (phase.getMilestoneIndicator() != null) {
            data[8] = phase.getMilestoneIndicator();
        }

        if (phase.getEndDateIndicator() != null) {
            data[9] = phase.getEndDateIndicator();
        }

        if (phase.getWorkUnitsIndicator() != null) {
            data[10] = phase.getWorkUnitsIndicator();
        }

        List list = new ArrayList();
        list.add(phase.isExist());
        list.add(null);
        list.add(phase.getAlias());
        data[11] = list;

        return data;
    }

    /**
     * Metoda pro ziskani identifikatoru WorkUnit z segmentu Phase
     *
     * @param phaseId identifikator Phase
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getWorkUnitFromPhase(int phaseId) {

        Phase phase = dataModel.getPhase(phaseId);
        return getWorkUnitFrom(phase.getWorkUnits());
    }

    /**
     * Metoda pro ziskani identifikatoru Work Unit z elemetnu Work Unit
     *
     * @param id identifikator Work Unit
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getWorkUniFromWorkUnit(int id) {
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        return getWorkUnitFrom(workUnit.getWorkUnits());
    }

    /**
     * Metoda pro ziskani identifikatoru WorkUnit z projektu
     *
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getWorkUnitFromProject() {
        Project project = dataModel.getProject();
        return getWorkUnitFrom(project.getWorkUnitIndexs());
    }

    /**
     * Metoda pro ziskani identifikatoru WorkUnit z segmentu Iteration
     *
     * @param iterationId identifikator Iteration
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getWorkUnitFromIteration(int iterationId) {

        Iteration iteration = dataModel.getIteration(iterationId);
        return getWorkUnitFrom(iteration.getWorkUnits());
    }

    /**
     * Metoda pro ziskani identifikatoru WorkUnit z segmentu Activity
     *
     * @param activityId identifikator Activity
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getWorkUnitFromActivity(int activityId) {

        Activity activity = dataModel.getActivity(activityId);
        return getWorkUnitFrom(activity.getWorkUnits());
    }

    /**
     * Metoda pro prevod identifikatoru ulozenych v datove strukture WorkUnitList
     * do seznamu
     *
     * @param inputList Seznam instaci WorkUnitList
     * @return ArrayList<ArrayList   <   Integer>>
     */

    private ArrayList<ArrayList<Integer>> getWorkUnitFrom(List<WorkUnitList> inputList) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (WorkUnitList list1 : inputList) {
            list.add((ArrayList<Integer>) list1.getWorkUnits());
        }

        return list;
    }

    /**
     * Metoda pro prevod dat o Projectu z datoveho modelu do seznamu s jednotlivymi parametry
     *
     * @return List s jednotlivymi parametry
     */
    public List[] getProjectStringData() {

        List[] data = new List[11];
        Project project = dataModel.getProject();

        if (project.getName() != null) {
            data[0] = project.getName();
        }


        if (project.getDescription() != null) {
            data[1] = project.getDescription();
        }

        if (project.getStartDate() != null) {
            data[2] = project.getStartDate();
        }

        if (project.getEndDate() != null) {
            data[3] = project.getEndDate();
        }

        if (project.getNameIndicator() != null) {
            data[4] = project.getNameIndicator();
        }


        if (project.getDescriptionIndicator() != null) {
            data[5] = project.getDescriptionIndicator();
        }

        if (project.getStartDateIndicator() != null) {
            data[6] = project.getStartDateIndicator();
        }

        if (project.getEndDateIndicator() != null) {
            data[7] = project.getEndDateIndicator();
        }

        if (project.getWorkUnitsIndicator() != null) {
            data[8] = project.getWorkUnitsIndicator();
        }

        return data;

    }

    /**
     * Metoda pro prevod dat o Iteration z datoveho modelu do seznamu s jednotlivymi parametry segmentu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getIterationStringData(int id) {
        List[] data = new List[12];
        Iteration iteration = dataModel.getIteration(id);
        ;

        if (iteration.getName() != null) {
            data[0] = iteration.getName();
        }


        if (iteration.getDescription() != null) {
            data[1] = iteration.getDescription();
        }

        if (iteration.getConfiguration() != null) {
            data[2] = iteration.getConfiguration();
        }

        if (iteration.getStartDate() != null) {
            data[3] = iteration.getStartDate();
        }

        if (iteration.getEndDate() != null) {
            data[4] = iteration.getEndDate();
        }

        if (iteration.getNameIndicator() != null) {
            data[5] = iteration.getNameIndicator();
        }


        if (iteration.getDescriptionIndicator() != null) {
            data[6] = iteration.getDescriptionIndicator();
        }

        if (iteration.getConfigurationIndicator() != null) {
            data[7] = iteration.getConfigurationIndicator();
        }

        if (iteration.getStartDateIndicator() != null) {
            data[8] = iteration.getStartDateIndicator();
        }

        if (iteration.getEndDateIndicator() != null) {
            data[9] = iteration.getEndDateIndicator();
        }

        if (iteration.getWorkUnitsIndicator() != null) {
            data[10] = iteration.getWorkUnitsIndicator();
        }

        List list = new ArrayList();
        list.add(iteration.isExist());
        list.add(null);
        list.add(iteration.getAlias());
        data[11] = list;

        return data;
    }

    /**
     * Metoda pro prevod dat o Activity z datoveho modelu do seznamu s jednotlivymi parametry segmentu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getActivityStringData(int id) {
        List[] data = new List[8];
        Activity activity = dataModel.getActivity(id);
        ;
        if (activity.getName() != null) {
            data[0] = activity.getName();
        }

        if (activity.getDescription() != null) {
            data[1] = activity.getDescription();
        }

        if (activity.getWorkUnitsIndicator() != null) {
            data[2] = activity.getWorkUnitsIndicator();
        }

        if (activity.getNameIndicator() != null) {
            data[3] = activity.getNameIndicator();
        }

        if (activity.getDescriptionIndicator() != null) {
            data[4] = activity.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(activity.isExist());
        list.add(null);
        list.add(activity.getAlias());
        data[5] = list;


        if (activity.getEndDate() != null) {
            data[6] = activity.getEndDate();
        }

        if (activity.getEndDateIndicator() != null) {
            data[7] = activity.getEndDateIndicator();
        }
        return data;
    }

    /**
     * Metoda pro prevod dat o Change z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getChangeStringData(int id) {

        List[] data = new List[6];
        Change change = dataModel.getChange(id);
        ;

        if (change.getName() != null) {
            data[0] = change.getName();
        }

        if (change.getDescription() != null) {
            data[1] = change.getDescription();
        }

        if (change.getNameIndicator() != null) {
            data[2] = change.getNameIndicator();
        }

        if (change.getDescriptionIndicator() != null) {
            data[3] = change.getDescriptionIndicator();
        }

        List list = new ArrayList();
        data[4] = list;
        if (change.isExist() != null) {

            list.add(change.isExist());
        }
        list.add(null);
        list.add(change.getAlias());

        if (change.getArtifactIndex() != null) {
            data[5] = change.getArtifactIndex();
        }

        return data;
    }

    /**
     * Metoda pro prevod dat o Artifact z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getArtifactStringData(int id) {
        List[] data = new List[11];
        Artifact artifact = dataModel.getArtifact(id);
        ;

        if (artifact.getName() != null) {
            data[0] = artifact.getName();
        }

        if (artifact.getDescription() != null) {
            data[1] = artifact.getDescription();
        }

        if (artifact.getAuthorIndex() != null) {
            data[2] = artifact.getAuthorIndex();
        }

        if (artifact.getCreated() != null) {
            data[3] = artifact.getCreated();
        }

        if (artifact.getMimeTypeIndex() != null) {
            data[4] = artifact.getMimeTypeIndex();
        }

        if (artifact.getNameIndicator() != null) {
            data[5] = artifact.getNameIndicator();
        }

        if (artifact.getDescriptionIndicator() != null) {
            data[6] = artifact.getDescriptionIndicator();
        }

        if (artifact.getAuthorIndicator() != null) {
            data[7] = artifact.getAuthorIndicator();
        }

        if (artifact.getCreatedIndicator() != null) {
            data[8] = artifact.getCreatedIndicator();
        }

        if (artifact.getMimeTypeIndicator() != null) {
            data[9] = artifact.getMimeTypeIndicator();
        }


        List list = new ArrayList();
        list.add(artifact.isExist());
        list.add(artifact.getCount());
        list.add(artifact.getCountIndicator());
        list.add(artifact.getAlias());
        data[10] = list;


        return data;
    }

    /**
     * Metoda pro prevod dat o Work Unit z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getWorkUnitStringData(int id) {

        List[] data = new List[29];
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        ;

        if (workUnit.getName() != null) {
            data[0] = workUnit.getName();
        }

        if (workUnit.getDescription() != null) {
            data[1] = workUnit.getDescription();
        }

        if (workUnit.getEstimatedTime() != null) {
            data[2] = workUnit.getEstimatedTime();
        }

        if (workUnit.getCategory() != null) {
            data[3] = workUnit.getCategory();
        }

        if (workUnit.getPriorityIndex() != null) {
            data[4] = workUnit.getPriorityIndex();
        }

        if (workUnit.getSeverityIndex() != null) {
            data[5] = workUnit.getSeverityIndex();
        }

        if (workUnit.getResolutionIndex() != null) {
            data[6] = workUnit.getResolutionIndex();
        }

        if (workUnit.getStatusIndex() != null) {
            data[7] = workUnit.getStatusIndex();
        }

        if (workUnit.getTypeIndex() != null) {
            data[8] = workUnit.getTypeIndex();
        }

        if (workUnit.getAssigneeIndex() != null) {
            data[9] = workUnit.getAssigneeIndex();
        }

        if (workUnit.getAuthorIndex() != null) {
            data[10] = workUnit.getAuthorIndex();
        }

        if (workUnit.getNameIndicator() != null) {
            data[11] = workUnit.getNameIndicator();
        }

        if (workUnit.getDescriptionIndicator() != null) {
            data[12] = workUnit.getDescriptionIndicator();
        }

        if (workUnit.getEstimatedTimeIndicator() != null) {
            data[13] = workUnit.getEstimatedTimeIndicator();
        }

        if (workUnit.getCategoryIndicator() != null) {
            data[14] = workUnit.getCategoryIndicator();
        }

        if (workUnit.getPriorityIndicator() != null) {
            data[15] = workUnit.getPriorityIndicator();
        }

        if (workUnit.getSeverityIndicator() != null) {
            data[16] = workUnit.getSeverityIndicator();
        }

        if (workUnit.getResolutionIndicator() != null) {
            data[17] = workUnit.getResolutionIndicator();
        }

        if (workUnit.getStatusIndicator() != null) {
            data[18] = workUnit.getStatusIndicator();
        }

        if (workUnit.getTypeIndicator() != null) {
            data[19] = workUnit.getTypeIndicator();
        }

        if (workUnit.getAssigneeIndex() != null) {
            data[20] = workUnit.getAssigneeIndicator();
        }

        if (workUnit.getAuthorIndex() != null) {
            data[21] = workUnit.getAuthorIndex();
        }

        List list = new ArrayList();
        list.add(workUnit.isExist());
        list.add(null);
        list.add(workUnit.getAlias());
        data[22] = list;


        if (workUnit.getRelationIndex() != null) {
            data[23] = workUnit.getRelationIndex();
        }

        if (workUnit.getWorkUnits() != null) {
            data[24] = workUnit.getWorkUnits();
        }

        if (workUnit.getCreated() != null) {
            data[25] = workUnit.getCreated();
        }

        if (workUnit.getCreatedIndicator() != null) {
            data[26] = workUnit.getCreatedIndicator();
        }

        if (workUnit.getProgress() != null) {
            data[27] = workUnit.getProgress();
        }

        if (workUnit.getProgressIndicator() != null) {
            data[28] = workUnit.getProgressIndicator();
        }

        return data;

    }

    /**
     * Metoda pro prevod dat o Configuration z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param id identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getConfigurationStringData(int id) {

        List[] data = new List[11];
        Configuration configuration = dataModel.getConfiguration(id);
        ;

        if (configuration.getName() != null) {
            data[0] = configuration.getName();
        }

        if (configuration.getCreated() != null) {
            data[1] = configuration.getCreated();
        }

        if (configuration.getTagIndex() != null) {
            data[2] = configuration.getTagsIndicator();
        }

        if (configuration.getNameIndicator() != null) {
            data[3] = configuration.getNameIndicator();
        }

        if (configuration.getCreatedIndicator() != null) {
            data[4] = configuration.getCreatedIndicator();
        }

        if (configuration.getAuthorIndicator() != null) {
            data[5] = configuration.getAuthorIndicator();
        }

        if (configuration.getCPRsIndicator() != null) {
            data[6] = configuration.getCPRsIndicator();
        }

        if (configuration.getBranchIndicator() != null) {
            data[7] = configuration.getBranchIndicator();
        }

        if (configuration.getChangesIndicator() != null) {
            data[8] = configuration.getChangesIndicator();
        }

        List list = new ArrayList();
        list.add(configuration.isExist());
        list.add(configuration.getCount());
        list.add(configuration.getCountIndicator());
        list.add(configuration.getAlias());
        data[9] = list;

        if (configuration.getTagsIndicator() != null) {
            data[10] = configuration.getTagsIndicator();
        }
        return data;

    }

    /**
     * Metoda pro ziskani identifikatoru Config Person Relation z elementu Configuration
     *
     * @param configId identifikator Configuration
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getCPRFromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getCPRFrom(configuration.getCPRsIndexs());
    }

    /**
     * Metoda pro prevod identifikatoru ulozenych v datove strukture CPRList
     * do seznamu
     *
     * @param inputList Seznam instaci CPRList
     * @return ArrayList<ArrayList   <   Integer>>
     */
    private ArrayList<ArrayList<Integer>> getCPRFrom(List<CPRSList> inputList) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (CPRSList list1 : inputList) {
            list.add((ArrayList<Integer>) list1.getCPRs());
        }

        return list;
    }

    /**
     * Metoda pro prevod identifikatoru ulozenych v datove strukture BranchList
     * do seznamu
     *
     * @param inputList Seznam instaci BranchList
     * @return ArrayList<ArrayList   <   Integer>>
     */
    private ArrayList<ArrayList<Integer>> getBranchFrom(List<BranchList> inputList) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (BranchList list1 : inputList) {
            list.add((ArrayList<Integer>) list1.getBranches());
        }

        return list;
    }

    /**
     * Metoda pro prevod identifikatoru ulozenych v datove strukture ChangeList
     * do seznamu
     *
     * @param inputList Seznam instaci ChangeList
     * @return ArrayList<ArrayList   <   Integer>>
     */
    private ArrayList<ArrayList<Integer>> getChangeFrom(List<ChangeList> inputList) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (ChangeList list1 : inputList) {
            list.add((ArrayList<Integer>) list1.getChanges());
        }

        return list;
    }

    /**
     * Metoda pro ziskani identifikatoru Branch z elementu Configuration
     *
     * @param configId identifikator Configuration
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getBranchfromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getBranchFrom(configuration.getBranchIndexs());
    }

    /**
     * Metoda pro ziskani identifikatoru Change z elementu Configuration
     *
     * @param configId identifikator Configuration
     * @return ArrayList<ArrayList   <   Integer>>
     */
    public ArrayList<ArrayList<Integer>> getChangeFromConfiguration(int configId) {
        Configuration configuration = dataModel.getConfiguration(configId);
        return getChangeFrom(configuration.getChangesIndexs());
    }

    /**
     * Metoda pro prevod dat o VCSTAg z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param tagId identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getVCSTagStringData(int tagId) {
        List[] data = new List[5];
        VCSTag vcsTag = dataModel.getVCSTag(tagId);
        ;

        if (vcsTag.getName() != null) {
            data[0] = vcsTag.getName();
        }

        if (vcsTag.getDescription() != null) {
            data[1] = vcsTag.getDescription();
        }

        if (vcsTag.getNameIndicator() != null) {
            data[2] = vcsTag.getNameIndicator();
        }

        if (vcsTag.getDescriptionIndicator() != null) {
            data[3] = vcsTag.getDescriptionIndicator();
        }

        List list = new ArrayList();
        list.add(vcsTag.isExist());
        list.add(null);
        list.add(vcsTag.getAlias());
        data[4] = list;
        return data;
    }

    /**
     * Metoda pro prevod dat o Commit z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param commitId identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getCommitStringData(int commitId) {
        List[] data = new List[7];
        Commit commit = dataModel.getCommit(commitId);
        ;
        if (commit.getName() != null) {
            data[0] = commit.getName();
        }

        if (commit.getNameIndicator() != null) {
            data[1] = commit.getNameIndicator();
        }

        ArrayList list = new ArrayList();
        list.add(commit.isExist());
        list.add(commit.getCount());
        list.add(commit.getCountIndicator());
        list.add(commit.getAlias());
        data[2] = list;
        if (commit.isRelease() != null) {
            list.add(commit.isRelease());
        }

        if (commit.getDescription() != null) {
            data[3] = commit.getDescription();
        }

        if (commit.getDescriptionIndicator() != null) {
            data[4] = commit.getDescriptionIndicator();
        }

        if (commit.getCreated() != null) {
            data[5] = commit.getCreated();
        }

        if (commit.getCreatedIndicator() != null) {
            data[6] = commit.getCreatedIndicator();
        }

        return data;
    }

    /**
     * Metoda pro prevod dat o Commited Configuration z datoveho modelu do seznamu s jednotlivymi parametry elementu
     *
     * @param commitedId identificator instance pro prevod
     * @return List s jednotlivymi parametry
     */
    public List[] getCommitedConfigurationStringData(int commitedId) {
        List[] data = new List[9];
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(commitedId);
        ;

        if (commitedConfiguration.getName() != null) {
            data[0] = commitedConfiguration.getName();
        }
        if (commitedConfiguration.getNameIndicator() != null) {
            data[1] = commitedConfiguration.getNameIndicator();
        }

        if (commitedConfiguration.getCommitedDay() != null) {
            data[2] = commitedConfiguration.getCommitedDay();
        }

        if (commitedConfiguration.getCommitedDayIndicator() != null) {
            data[3] = commitedConfiguration.getCommitedDayIndicator();
        }

        ArrayList list = new ArrayList();
        list.add(commitedConfiguration.isExist());
        list.add(commitedConfiguration.getCount());
        list.add(commitedConfiguration.getCountIndicator());
        list.add(commitedConfiguration.getAlias());
        data[4] = list;

        if (commitedConfiguration.getCreated() != null) {
            data[5] = commitedConfiguration.getCreated();
        }
        if (commitedConfiguration.getCreatedIndicator() != null) {
            data[6] = commitedConfiguration.getCreatedIndicator();
        }

        if (commitedConfiguration.getDescription() != null) {
            data[7] = commitedConfiguration.getDescription();
        }

        if (commitedConfiguration.getDescriptionIndicator() != null) {
            data[8] = commitedConfiguration.getDescriptionIndicator();
        }

        return data;

    }


}
