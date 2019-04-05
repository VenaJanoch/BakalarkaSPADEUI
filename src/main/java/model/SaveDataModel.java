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

    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId){
        createLink(linkId, startId, endId, LinkType.COMMITED_CONFIGURATION_CONFIGURATION );

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(startId);
        commitedConfiguration.getCommit().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getCommitedConfiguration().add(startId);
    }

    private void createLink(int linkId, int startId, int endId, LinkType linkType){
        Link linkP = objF.createLink();

        linkP.setType(linkType);
        linkP.setId(linkId);
        linkP.setStartIndex(startId);
        linkP.setEndIndex(endId);

        project.getLinks().add(linkP);
    }

    public void createNewPersonToArtifactRelation(int linkId, int startId, int endId){

        createLink(linkId, startId, endId, LinkType.PERSON_ARTIFACT);

        Person person = dataModel.getRole(startId);
        person.getArtifacts().add(endId);
        Artifact artifact = dataModel.getArtifact(endId);
        artifact.getAuthorIndex().add(startId);
    }


    public void createNewPersonToConfigurationRelation(int linkId, int startId, int endId){
        createLink( linkId, startId, endId, LinkType.PERSON_CONFIGURATION);

        Person person = dataModel.getRole(startId);
        person.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getAuthorIndex().add(startId);
    }

    public void createNewArtifacToConfigurationRelation(int linkId, int startId, int endId){
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
        ArrayList list = new ArrayList();
        list.add("");
        phase.getName().addAll(list);
        project.getPhases().add(phase);
    }

    public void createNewIteration(int id) {
        Iteration iteration = objF.createIteration();
        iteration.setId(id);
        iteration.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        iteration.getName().addAll(list);
        project.getIterations().add(iteration);
    }

    public void createNewActivity(int id) {
        Activity activity = objF.createActivity();
        activity.setId(id);
        activity.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        activity.getName().addAll(list);
        project.getActivities().add(activity);

    }

    public void createNewWorkUnit(int id) {
        WorkUnit workUnit = objF.createWorkUnit();
        workUnit.setId(id);
        workUnit.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        workUnit.getName().addAll(list);
        project.getWorkUnits().add(workUnit);
    }

    public void createNewConfiguration(int id) {
        Configuration configuration = objF.createConfiguration();
        configuration.setId(id);
        configuration.setCount(1);
        configuration.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        configuration.getName().addAll(list);
        project.getConfiguration().add(configuration);
    }

    public void createNewChange(int id) {
        Change change = objF.createChange();
        change.setId(id);
        change.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        change.getName().addAll(list);
        project.getChanges().add(change);
    }

    public void createNewArtifact(int id) {
        Artifact artifact = objF.createArtifact();
        artifact.setId(id);
        artifact.setCount(1);
        artifact.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        artifact.getName().addAll(list);
        project.getArtifacts().add(artifact);
    }

    public void createNewBranch(String nameForManipulator, int id, boolean isMain) {
        Branch branch = objF.createBranch();
        branch.setId(id);
        branch.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        branch.getName().addAll(list);
        project.getBranches().add(branch);
    }

    public void createNewCPR(int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setExist(true);
        cpr.setId(id);
        ArrayList list = new ArrayList();
        list.add("");
        cpr.getName().addAll(list);
        project.getCpr().add(cpr);
    }

    public void createNewCriterion(String nameForManipulator, String descForManipulator, int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setExist(true);
        criterion.setId(id);
        ArrayList list = new ArrayList();
        list.add("");
        criterion.getName().addAll(list);
        project.getCriterions().add(criterion);
    }

    public void createNewPriority(String nameForManipulator, String classST, String superST, int id) {

        Priority priority = objF.createPriority();
        priority.setExist(true);
        priority.setId(id);
        ArrayList list = new ArrayList();
        list.add("");
        priority.getName().addAll(list);
        project.getPriority().add(priority);
    }

    public void createNewSeverity(String nameForManipulator, String classST, String superST, int id) {

        Severity severity = objF.createSeverity();
        severity.setId(id);
        severity.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        severity.getName().addAll(list);
        project.getSeverity().add(severity);
    }

    public void createNewRelation(String nameForManipulator, String classST, String superST, int id) {

        Relation relation = objF.createRelation();
        relation.setId(id);
       relation.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        relation.getName().addAll(list);
        project.getRelation().add(relation);
    }

    public void createNewResolution(String nameForManipulator, String classST, String superST, int id) {

        Resolution resolution = objF.createResolution();
        resolution.setId(id);
        resolution.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        resolution.getName().addAll(list);
        project.getResolution().add(resolution);

    }

    public void createNewRole(int id) {

        Person role = objF.createPerson();
        role.setId(id);
        role.setCount(1);
        role.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        role.getName().addAll(list);
        project.getRoles().add(role);
    }

    public void createNewMilestone(String nameForManipulator, String description, ArrayList<Integer> criterionIndex, int id) {

        Milestone milestone = objF.createMilestone();
        milestone.setId(id);
        milestone.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        milestone.getName().addAll(list);
        project.getMilestones().add(milestone);
    }

    public void createNewRoleType(String nameForManipulator, String classST, String superST, int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setId(id);
        roleType.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        roleType.getName().addAll(list);
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
        ArrayList list = new ArrayList();
        list.add("");
        status.getName().addAll(list);
  //      dataModel.addDataToStatus(status, nameForManipulator, classST, superST);
        project.getStatus().add(status);
    }

    public void createNewType(String nameForManipulator, String classST, String superST, int id) {

        Type type = objF.createType();
        type.setId(id);
        type.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        type.getName().addAll(list);
  //      dataModel.addDataToType(type, nameForManipulator, classST, superST);
        project.getTypes().add(type);
    }

    @Override
    public void createNewVCSTag(int id) {
        VCSTag vcsTag = objF.createVCSTag();
        vcsTag.setId(id);
        vcsTag.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        vcsTag.getName().addAll(list);
        project.getVcsTag().add(vcsTag);
    }

    @Override
    public void createNewCommit(int id) {
        Commit commit = objF.createCommit();
        commit.setId(id);
        commit.setCount(1);
        commit.getNameIndicator().add(0);
        commit.setExist(true);
        ArrayList list = new ArrayList();
        list.add("");
        commit.getName().addAll(list);
        project.getCommit().add(commit);
    }

    @Override
    public void createNewCommitedConfiguration(int id) {
        CommitedConfiguration commitedConfiguration = objF.createCommitedConfiguration();
        commitedConfiguration.setId(id);
        commitedConfiguration.setCount(1);
        commitedConfiguration.setExist(true);
        commitedConfiguration.getNameIndicator().add(0);
        ArrayList list = new ArrayList();
        list.add("");
        commitedConfiguration.getName().addAll(list);
        project.getCommitConfiguration().add(commitedConfiguration);
    }
}
