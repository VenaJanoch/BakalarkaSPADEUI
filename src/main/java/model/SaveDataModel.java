package model;

import SPADEPAC.*;
import interfaces.ISaveDataModel;

import java.util.ArrayList;

public class SaveDataModel implements ISaveDataModel {

    private DataModel dataModel;
    private ObjectFactory objF;

    /**
     * Konstruktor tridy 
     * Zinicializuje globalni promenne
     * @param dataModel instace tridy DataModel
     * @param objF instance factory ObjectFactory
     */
    public SaveDataModel(DataModel dataModel, ObjectFactory objF) {
        this.dataModel = dataModel;
        this.objF = objF;
    }

    /**
     * Metoda pro vytvoreni relace mezi Commited Connfiguratio a Configuration v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createCommitedConfigurationToConfigurationRelation(int linkId, Integer startId, Integer endId) {
        createLink(linkId, startId, endId, LinkType.COMMITED_CONFIGURATION_CONFIGURATION);

        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(startId);
        commitedConfiguration.getConfiguration().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getCommitedConfiguration().add(startId);
    }

    /**
     * Pomocna metoda pro vytvoreni instace spojnice 
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     * @param linkType Type o jaky druh spojnice se jedna
     */
    private void createLink(int linkId, int startId, int endId, LinkType linkType) {
        Link linkP = objF.createLink();

        linkP.setType(linkType);
        linkP.setId(linkId);
        linkP.setStartIndex(startId);
        linkP.setEndIndex(endId);

        dataModel.getLinks().add(linkP);
    }
    /**
     * Metoda pro vytvoreni relace mezi Person a Artifact v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createNewPersonToArtifactRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_ARTIFACT);

        Person person = dataModel.getPerson(startId);
        person.getArtifacts().add(endId);
        Artifact artifact = dataModel.getArtifact(endId);
        artifact.getAuthorIndex().add(startId);
    }
    /**
     * Metoda pro vytvoreni relace mezi Person a Commit v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createNewPersonToCommitRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_COMMIT);

        Person person = dataModel.getPerson(startId);
        person.getCommit().add(endId);
        Commit commit = dataModel.getCommit(endId);
        commit.getAuthor().add(startId);
    }

    /**
     * Metoda pro vytvoreni relace mezi Committed Configuration a Configuration v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createNewPersonToCommittedConfigurationRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.PERSON_COMMITTED_CONFIGURATION);

        Person person = dataModel.getPerson(startId);
        person.getCommittedConfiguration().add(endId);
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(endId);
        commitedConfiguration.getRole().add(startId);
    }

    /**
     * Metoda pro vytvoreni relace mezi Person a Configuration v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createNewPersonToConfigurationRelation(int linkId, int startId, int endId) {
        createLink(linkId, startId, endId, LinkType.PERSON_CONFIGURATION);

        Person person = dataModel.getPerson(startId);
        person.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getAuthorIndex().add(startId);
    }

    /**
     * Metoda pro vytvoreni relace mezi Artifact a Configuration v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createNewArtifacToConfigurationRelation(int linkId, int startId, int endId) {
        createLink(linkId, startId, endId, LinkType.ARTIFACT_CONFIGURATION);


        Artifact artifact = dataModel.getArtifact(startId);
        artifact.getConfigurations().add(endId);
        Configuration configuration = dataModel.getConfiguration(endId);
        configuration.getArtifactsIndexs().add(startId);
    }

    /**
     * Metoda pro vytvoreni relace mezi Commited Connfiguration a Commit v datovem Modelu
     * @param linkId identifikator relace
     * @param startId Pocatecni identifikator
     * @param endId Koncovy identifikator
     */
    public void createCommitToCommitedConfigurationRelation(int linkId, int startId, int endId) {

        createLink(linkId, startId, endId, LinkType.COMMIT_COMMITED_CONFIGURATION);

        Commit commit = dataModel.getCommit(startId);
        commit.getCommitedConfiguration().add(endId);
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(endId);
        commitedConfiguration.getCommit().add(startId);
    }

    /**
     * Metoda pro vytvoreni nove instace segmentu Phase
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewPhase(int id) {
        Phase phase = objF.createPhase();
        phase.setId(id);
        phase.setExist(true);
        phase.setAlias(String.valueOf(id));
        dataModel.getPhases().add(phase);
    }
   
    /**
     * Metoda pro vytvoreni nove instace segmentu Iteration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewIteration(int id) {
        Iteration iteration = objF.createIteration();
        iteration.setId(id);
        iteration.setExist(true);
        iteration.setAlias(String.valueOf(id));
        dataModel.getIterations().add(iteration);
    }
   
    /**
     * Metoda pro vytvoreni nove instace segmentu Activity
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewActivity(int id) {
        Activity activity = objF.createActivity();
        activity.setId(id);
        activity.setExist(true);
        activity.setAlias(String.valueOf(id));
        dataModel.getActivities().add(activity);

    }
   
    /**
     * Metoda pro vytvoreni nove instace elementu Work Unit
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewWorkUnit(int id) {
        WorkUnit workUnit = objF.createWorkUnit();
        workUnit.setId(id);
        workUnit.setExist(true);
        workUnit.setAlias(String.valueOf(id));
        dataModel.getWorkUnits().add(workUnit);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Configuration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewConfiguration(int id) {
        Configuration configuration = objF.createConfiguration();
        configuration.setId(id);
        configuration.setCount(1);
        configuration.setCountIndicator(0);
        configuration.setExist(true);
        configuration.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        configuration.setCoordinates(cor);
        dataModel.getConfigurations().add(configuration);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Change
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewChange(int id) {
        Change change = objF.createChange();
        change.setId(id);
        change.setExist(true);
        change.setAlias(String.valueOf(id));
        dataModel.getChanges().add(change);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Artifact
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewArtifact(int id) {
        Artifact artifact = objF.createArtifact();
        artifact.setId(id);
        artifact.setCount(1);
        artifact.setCountIndicator(0);
        artifact.setExist(true);
        artifact.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        artifact.setCoordinates(cor);
        dataModel.getArtifacts().add(artifact);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Branch
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewBranch(int id) {
        Branch branch = objF.createBranch();
        branch.setId(id);
        branch.setExist(true);
        branch.setIsMain(false);
        branch.setAlias(String.valueOf(id));
        dataModel.getBranches().add(branch);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Configuration Person Relation
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewCPR(int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setExist(true);
        cpr.setId(id);
        cpr.setAlias(String.valueOf(id));
        dataModel.getConfigPersonRelations().add(cpr);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Criterion
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewCriterion(int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setExist(true);
        criterion.setId(id);
        criterion.setAlias(String.valueOf(id));
        dataModel.getCriterions().add(criterion);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Priority
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewPriority(int id) {

        Priority priority = objF.createPriority();
        priority.setExist(true);
        priority.setId(id);
        priority.setAlias(String.valueOf(id));
        dataModel.getPriorities().add(priority);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Severity
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewSeverity(int id) {

        Severity severity = objF.createSeverity();
        severity.setId(id);
        severity.setExist(true);
        severity.setAlias(String.valueOf(id));
        dataModel.getSeverities().add(severity);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Relation
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewRelation(int id) {

        Relation relation = objF.createRelation();
        relation.setId(id);
        relation.setExist(true);
        relation.setAlias(String.valueOf(id));
        dataModel.getRelations().add(relation);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Resolution
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewResolution(int id) {

        Resolution resolution = objF.createResolution();
        resolution.setId(id);
        resolution.setExist(true);
        resolution.setAlias(String.valueOf(id));
        dataModel.getResolutions().add(resolution);

    }

    /**
     * Metoda pro vytvoreni nove instace elementu Person
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewPerson(int id) {

        Person person = objF.createPerson();
        person.setId(id);
        person.setCount(1);
        person.setCountIndicator(0);
        person.setExist(true);
        person.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        person.setCoordinates(cor);
        dataModel.getPersons().add(person);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Milestone
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewMilestone(int id) {

        Milestone milestone = objF.createMilestone();
        milestone.setId(id);
        milestone.setExist(true);
        milestone.setAlias(String.valueOf(id));
        dataModel.getMilestones().add(milestone);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Role Type
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewRoleType(int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setId(id);
        roleType.setExist(true);
        roleType.setAlias(String.valueOf(id));
        dataModel.getRoleTypes().add(roleType);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Status
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewStatus(int id) {

        Status status = objF.createStatus();
        status.setId(id);
        status.setExist(true);
        status.setAlias(String.valueOf(id));
        dataModel.getStatuses().add(status);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Type
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    public void createNewType(int id) {

        Type type = objF.createType();
        type.setId(id);
        type.setExist(true);
        type.setAlias(String.valueOf(id));
        dataModel.getTypes().add(type);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu VCSTag
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    @Override
    public void createNewVCSTag(int id) {
        VCSTag vcsTag = objF.createVCSTag();
        vcsTag.setId(id);
        vcsTag.setExist(true);
        vcsTag.setAlias(String.valueOf(id));
        dataModel.getVCSTags().add(vcsTag);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Commit
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    @Override
    public void createNewCommit(int id) {
        Commit commit = objF.createCommit();
        commit.setId(id);
        commit.setCount(1);
        commit.setCountIndicator(0);
        commit.setExist(true);
        commit.setRelease(false);
        commit.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        commit.setCoordinates(cor);
        dataModel.getCommits().add(commit);
    }

    /**
     * Metoda pro vytvoreni nove instace elementu Committed Configuration
     * Jsou nastaveny pocatecny hodnoty a instace je pridana do datoveho modelu 
     * @param id identifikator nove vytvarene instacne
     */
    @Override
    public void createNewCommitedConfiguration(int id) {
        CommitedConfiguration commitedConfiguration = objF.createCommitedConfiguration();
        commitedConfiguration.setId(id);
        commitedConfiguration.setCount(1);
        commitedConfiguration.setCountIndicator(0);
        commitedConfiguration.setExist(true);
        commitedConfiguration.setAlias(String.valueOf(id));
        Coordinates cor = objF.createCoordinates();
        cor.setXCoordinate(0);
        cor.setYCoordinate(0);
        commitedConfiguration.setCoordinates(cor);
        dataModel.getCommitedConfiguration().add(commitedConfiguration);
    }
}
