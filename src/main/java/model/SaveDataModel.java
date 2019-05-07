package model;

import SPADEPAC.*;
import interfaces.ISaveDataModel;

import java.util.ArrayList;

public class SaveDataModel implements ISaveDataModel {

    private DataModel dataModel;
    private ObjectFactory objF;

    public SaveDataModel(DataModel dataModel, ObjectFactory objF) {
        this.dataModel = dataModel;
        this.objF = objF;
    }

    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId) {
        createLink(linkId, startId, endId, LinkType.COMMITED_CONFIGURATION_CONFIGURATION);

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(startId);
        commitedConfiguration.getConfiguration().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getCommitedConfiguration().add(startId);
    }

    private void createLink(int linkId, int startId, int endId, LinkType linkType) {
        Link linkP = objF.createLink();

        linkP.setType(linkType);
        linkP.setId(linkId);
        linkP.setStartIndex(startId);
        linkP.setEndIndex(endId);

        dataModel.getLinks().add(linkP);
    }

    public void createNewPersonToArtifactRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_ARTIFACT);

        Person person = dataModel.getPerson(startId);
        person.getArtifacts().add(endId);
        Artifact artifact = dataModel.getArtifact(endId);
        artifact.getAuthorIndex().add(startId);
    }

    public void createNewPersonToCommitRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_COMMIT);

        Person person = dataModel.getPerson(startId);
        person.getCommit().add(endId);
        Commit commit = dataModel.getCommit(endId);
        commit.getAuthor().add(startId);
    }

    public void createNewPersonToCommittedConfigurationRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_COMMITTED_CONFIGURATION);

        Person person = dataModel.getPerson(startId);
        person.getCommittedConfiguration().add(endId);
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(endId);
        commitedConfiguration.getRole().add(startId);
    }

    public void createNewPersonToConfigurationRelation(int linkId, int startId, int endId) {
        createLink(linkId, startId, endId, LinkType.PERSON_CONFIGURATION);

        Person person = dataModel.getPerson(startId);
        person.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getAuthorIndex().add(startId);
    }

    public void createNewArtifacToConfigurationRelation(int linkId, int startId, int endId) {
        createLink(linkId, startId, endId, LinkType.ARTIFACT_CONFIGURATION);


        Artifact artifact = dataModel.getArtifact(startId);
        artifact.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getArtifactsIndexs().add(startId);
    }


    public void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.COMMIT_COMMITED_CONFIGURATION);

        Commit commit = dataModel.getCommit(startId);
        commit.getCommitedConfiguration().add(endId);
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(endId);
        commitedConfiguration.getCommit().add(startId);
    }

    public void createNewPhase(int id) {
        Phase phase = objF.createPhase();
        phase.setId(id);
        phase.setExist(true);
        phase.setAlias(String.valueOf(id));
        dataModel.getPhases().add(phase);
    }

    public void createNewIteration(int id) {
        Iteration iteration = objF.createIteration();
        iteration.setId(id);
        iteration.setExist(true);
        iteration.setAlias(String.valueOf(id));
        dataModel.getIterations().add(iteration);
    }

    public void createNewActivity(int id) {
        Activity activity = objF.createActivity();
        activity.setId(id);
        activity.setExist(true);
        activity.setAlias(String.valueOf(id));
        dataModel.getActivities().add(activity);

    }

    public void createNewWorkUnit(int id) {
        WorkUnit workUnit = objF.createWorkUnit();
        workUnit.setId(id);
        workUnit.setExist(true);
        workUnit.setAlias(String.valueOf(id));
        dataModel.getWorkUnits().add(workUnit);
    }

    public void createNewConfiguration(int id) {
        Configuration configuration = objF.createConfiguration();
        configuration.setId(id);
        configuration.setCount(1);
        configuration.setExist(true);
        configuration.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        configuration.setCoordinates(cor);
        dataModel.getConfigurations().add(configuration);
    }

    public void createNewChange(int id) {
        Change change = objF.createChange();
        change.setId(id);
        change.setExist(true);
        change.setAlias(String.valueOf(id));
        dataModel.getChanges().add(change);
    }

    public void createNewArtifact(int id) {
        Artifact artifact = objF.createArtifact();
        artifact.setId(id);
        artifact.setCount(1);
        artifact.setExist(true);
        artifact.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        artifact.setCoordinates(cor);
        dataModel.getArtifacts().add(artifact);
    }

    public void createNewBranch(String nameForManipulator, int id, boolean isMain) {
        Branch branch = objF.createBranch();
        branch.setId(id);
        branch.setExist(true);
        branch.setIsMain(false);
        branch.setAlias(String.valueOf(id));
        dataModel.getBranches().add(branch);
    }

    public void createNewCPR(int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setExist(true);
        cpr.setId(id);
        cpr.setAlias(String.valueOf(id));
        dataModel.getConfigPersonRelations().add(cpr);
    }

    public void createNewCriterion(String nameForManipulator, String descForManipulator, int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setExist(true);
        criterion.setId(id);
        criterion.setAlias(String.valueOf(id));
        dataModel.getCriterions().add(criterion);
    }

    public void createNewPriority(String nameForManipulator, String classST, String superST, int id) {

        Priority priority = objF.createPriority();
        priority.setExist(true);
        priority.setId(id);
        priority.setAlias(String.valueOf(id));
        dataModel.getPriorities().add(priority);
    }

    public void createNewSeverity(String nameForManipulator, String classST, String superST, int id) {

        Severity severity = objF.createSeverity();
        severity.setId(id);
        severity.setExist(true);
        severity.setAlias(String.valueOf(id));
        dataModel.getSeverities().add(severity);
    }

    public void createNewRelation(String nameForManipulator, String classST, String superST, int id) {

        Relation relation = objF.createRelation();
        relation.setId(id);
        relation.setExist(true);
        relation.setAlias(String.valueOf(id));
        dataModel.getRelations().add(relation);
    }

    public void createNewResolution(String nameForManipulator, String classST, String superST, int id) {

        Resolution resolution = objF.createResolution();
        resolution.setId(id);
        resolution.setExist(true);
        resolution.setAlias(String.valueOf(id));
        dataModel.getResolutions().add(resolution);

    }

    public void createNewPerson(int id) {

        Person role = objF.createPerson();
        role.setId(id);
        role.setCount(1);
        role.setExist(true);
        role.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        role.setCoordinates(cor);
        dataModel.getPersons().add(role);
    }

    public void createNewMilestone(String nameForManipulator, String description, ArrayList<Integer> criterionIndex, int id) {

        Milestone milestone = objF.createMilestone();
        milestone.setId(id);
        milestone.setExist(true);
        milestone.setAlias(String.valueOf(id));
        dataModel.getMilestones().add(milestone);
    }

    public void createNewRoleType(String nameForManipulator, String classST, String superST, int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setId(id);
        roleType.setExist(true);
        roleType.setAlias(String.valueOf(id));
        dataModel.getRoleTypes().add(roleType);
    }

    public void createNewStatus(String nameForManipulator, String classST, String superST, int id) {

        Status status = objF.createStatus();
        status.setId(id);
        status.setExist(true);
        status.setAlias(String.valueOf(id));
        dataModel.getStatuses().add(status);
    }

    public void createNewType(String nameForManipulator, String classST, String superST, int id) {

        Type type = objF.createType();
        type.setId(id);
        type.setExist(true);
        type.setAlias(String.valueOf(id));
        dataModel.getTypes().add(type);
    }

    @Override
    public void createNewVCSTag(int id) {
        VCSTag vcsTag = objF.createVCSTag();
        vcsTag.setId(id);
        vcsTag.setExist(true);
        vcsTag.setAlias(String.valueOf(id));
        dataModel.getVCSTags().add(vcsTag);
    }

    @Override
    public void createNewCommit(int id) {
        Commit commit = objF.createCommit();
        commit.setId(id);
        commit.setCount(1);
        commit.getNameIndicator().add(0);
        commit.setExist(true);
        commit.setRelease(false);
        commit.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        commit.setCoordinates(cor);
        dataModel.getCommits().add(commit);
    }

    @Override
    public void createNewCommitedConfiguration(int id) {
        CommitedConfiguration commitedConfiguration = objF.createCommitedConfiguration();
        commitedConfiguration.setId(id);
        commitedConfiguration.setCount(1);
        commitedConfiguration.setExist(true);
        commitedConfiguration.getNameIndicator().add(0);
        commitedConfiguration.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        commitedConfiguration.setCoordinates(cor);
        dataModel.getCommitedConfiguration().add(commitedConfiguration);
    }
}
