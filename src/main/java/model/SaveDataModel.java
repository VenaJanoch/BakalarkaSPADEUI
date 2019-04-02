package model;

import SPADEPAC.*;
import XML.ProcessGenerator;
import interfaces.ISaveDataModel;
import tables.CommitTable;
import tables.CommitedConfigurationTable;

import java.util.ArrayList;

public class SaveDataModel implements ISaveDataModel {

    private Project project;
    private DataModel dataModel;
    private ObjectFactory objF;

    public SaveDataModel(Project project,DataModel dataModel,  ObjectFactory objF) {
        this.dataModel = dataModel;
        this.project = project;
        this.objF = objF;
    }

    public void createChangeArtifactRelation(int artifactIndex, int changeIndex) {

        Link linkP = objF.createLink();

        linkP.setType("Config");
        linkP.setArtifactIndex(artifactIndex);
        linkP.setChangeIndex(changeIndex);


        project.getLinks().add(linkP);

        project.getChanges().get(changeIndex).getArtifactIndex().add(artifactIndex);
        project.getArtifacts().get(artifactIndex).getChangeIndex().add(changeIndex);
    }

    public void createCommitedConfigurationToConfigurationRelation(Integer startId, Integer endId){
        Link linkP = objF.createLink();

        linkP.setType("Config");
        linkP.setArtifactIndex(startId);
        linkP.setChangeIndex(endId);

        project.getLinks().add(linkP);

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(startId);
        commitedConfiguration.getCommit().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getCommitedConfiguration().add(startId);
    }

    private void createLink(int startId, int endId){
        Link linkP = objF.createLink();

        linkP.setType("Config");
        linkP.setArtifactIndex(startId);
        linkP.setChangeIndex(endId);

        project.getLinks().add(linkP);
    }

    public void createNewPersonToArtifactRelation(int startId, int endId){

        createLink(startId, endId);

        Person person = dataModel.getRole(startId);
        person.getArtifacts().add(endId);
        Artifact artifact = dataModel.getArtifact(endId);
        artifact.getAuthorIndex().add(startId);
    }


    public void createNewPersonToConfigurationRelation(int startId, int endId){
        createLink(startId, endId);

        Person person = dataModel.getRole(startId);
        person.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getAuthorIndex().add(startId);
    }

    public void createNewArtifacToConfigurationRelation(int startId, int endId){
        createLink(startId, endId);


        Artifact artifact = dataModel.getArtifact(startId);
        artifact.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getArtifactsIndexs().add(startId);
    }



    public void createCommitToCommitedConfigurationRelation(int startId, int endId) {

        createLink(startId, endId);

        Commit commit = dataModel.getCommit(startId);
        commit.getCommitedConfiguration().add(endId);
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(endId);
        commitedConfiguration.getCommit().add(startId);
    }

    public void createWorkUnitRelation(int startIndex, Integer endIndex) {

        Link linkP = objF.createLink();

        linkP.setType("Work_Unit");
        linkP.setLeftUnitIndex(startIndex);
        linkP.setRightUnitIndex(endIndex);

        project.getLinks().add(linkP);

    }

    public void createNewPhase(int id) {
        Phase phase = objF.createPhase();
        phase.setId(id);
        phase.setExist(true);
        project.getPhases().add(phase);
    }

    public void createNewIteration(int id) {
        Iteration iteration = objF.createIteration();
        iteration.setId(id);
        iteration.setExist(true);
        project.getIterations().add(iteration);
    }

    public void createNewActivity(int id) {
        Activity activity = objF.createActivity();
        activity.setId(id);
        activity.setExist(true);
        project.getActivities().add(activity);

    }

    public void createNewWorkUnit(int id) {
        WorkUnit workUnit = objF.createWorkUnit();
        workUnit.setId(id);
        workUnit.setExist(true);
        project.getWorkUnits().add(workUnit);
    }

    public void createNewConfiguration(int id) {
        Configuration configuration = objF.createConfiguration();
        configuration.setId(id);
        configuration.setCount(1);
        configuration.setExist(true);
        project.getConfiguration().add(configuration);
    }

    public void createNewChange(int id) {
        Change change = objF.createChange();
        change.setId(id);
        change.setExist(true);
        project.getChanges().add(change);
    }

    public void createNewArtifact(int id) {
        Artifact artifact = objF.createArtifact();
        artifact.setId(id);
        artifact.setCount(1);
        artifact.setExist(true);
        project.getArtifacts().add(artifact);
    }

    public void createNewBranch(String nameForManipulator, int id, boolean isMain) {
        Branch branch = objF.createBranch();
        branch.setId(id);
        branch.setExist(true);
        project.getBranches().add(branch);
    }

    public void createNewCPR(int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setExist(true);
        cpr.setId(id);
        project.getCpr().add(cpr);
    }

    public void createNewCriterion(String nameForManipulator, String descForManipulator, int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setExist(true);
        criterion.setId(id);
        project.getCriterions().add(criterion);
    }

    public void createNewPriority(String nameForManipulator, String classST, String superST, int id) {

        Priority priority = objF.createPriority();
        priority.setExist(true);
        priority.setId(id);
        project.getPriority().add(priority);
    }

    public void createNewSeverity(String nameForManipulator, String classST, String superST, int id) {

        Severity severity = objF.createSeverity();
        severity.setId(id);
        severity.setExist(true);
        project.getSeverity().add(severity);
    }

    public void createNewRelation(String nameForManipulator, String classST, String superST, int id) {

        Relation relation = objF.createRelation();
        relation.setId(id);
       relation.setExist(true);
        project.getRelation().add(relation);
    }

    public void createNewResolution(String nameForManipulator, String classST, String superST, int id) {

        Resolution resolution = objF.createResolution();
        resolution.setId(id);
        resolution.setExist(true);
        project.getResolution().add(resolution);

    }

    public void createNewRole(int id) {

        Person role = objF.createPerson();
        role.setId(id);
        role.setCount(1);
        role.setExist(true);
        project.getRoles().add(role);
    }

    public void createNewMilestone(String nameForManipulator, String description, ArrayList<Integer> criterionIndex, int id) {

        Milestone milestone = objF.createMilestone();
        milestone.setId(id);
        milestone.setExist(true);
        project.getMilestones().add(milestone);
    }

    public void createNewRoleType(String nameForManipulator, String classST, String superST, int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setId(id);
        roleType.setExist(true);
   //     dataModel.addDataToRoleType(roleType, nameForManipulator, classST, superST);
        project.getRoleType().add(roleType);
    }

    public void addTagToConfiguration(String tag, int configId, int index) {
        Configuration configuration = project.getConfiguration().get(configId);
        configuration.getTags().add(tag);
    }

    public void createNewStatus(String nameForManipulator, String classST, String superST, int id) {

        Status status = objF.createStatus();
        status.setId(id);
        status.setExist(true);
  //      dataModel.addDataToStatus(status, nameForManipulator, classST, superST);
        project.getStatus().add(status);
    }

    public void createNewType(String nameForManipulator, String classST, String superST, int id) {

        Type type = objF.createType();
        type.setId(id);
        type.setExist(true);
  //      dataModel.addDataToType(type, nameForManipulator, classST, superST);
        project.getTypes().add(type);
    }

    @Override
    public void createNewVCSTag(int id) {
        VCSTag vcsTag = objF.createVCSTag();
        vcsTag.setId(id);
        vcsTag.setExist(true);
        project.getVcsTag().add(vcsTag);
    }

    @Override
    public void createNewCommit(int id) {
        Commit commit = objF.createCommit();
        commit.setId(id);
        commit.setCount(1);
        commit.getNameIndicator().add(0);
        commit.setExist(true);
        project.getCommit().add(commit);
    }

    @Override
    public void createNewCommitedConfiguration(int id) {
        CommitedConfiguration commitedConfiguration = objF.createCommitedConfiguration();
        commitedConfiguration.setId(id);
        commitedConfiguration.setCount(1);
        commitedConfiguration.setExist(true);
        commitedConfiguration.getNameIndicator().add(0);
        project.getCommitConfiguration().add(commitedConfiguration);
    }
}
