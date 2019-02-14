package model;

import SPADEPAC.*;
import XML.ProcessGenerator;
import interfaces.ISaveDataModel;

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

    public void createWorkUnitRelation(int startIndex, Integer endIndex) {

        Link linkP = objF.createLink();

        linkP.setType("WorkUnit");
        linkP.setLeftUnitIndex(startIndex);
        linkP.setRightUnitIndex(endIndex);

        project.getLinks().add(linkP);

    }

    public void createNewPhase(int id) {
        Phase phase = objF.createPhase();
        phase.setId(id);
        project.getPhases().add(phase);
    }

    public void createNewIteration(int id) {
        Iteration iteration = objF.createIteration();
        iteration.setId(id);
        project.getIterations().add(iteration);
    }

    public void createNewActivity(int id) {
        Activity activity = objF.createActivity();
        activity.setId(id);
        project.getActivities().add(activity);

    }

    public void createNewWorkUnit(int id) {
        WorkUnit workUnit = objF.createWorkUnit();
        workUnit.setId(id);
        project.getWorkUnits().add(workUnit);
    }

    public void createNewConfiguration(int id) {
        Configuration configuration = objF.createConfiguration();
        configuration.setId(id);
        project.getConfiguration().add(configuration);
    }

    public void createNewChance(int id) {
        Change change = objF.createChange();
        change.setId(id);
        project.getChanges().add(change);
    }

    public void createNewArtifact(int id) {
        Artifact artifact = objF.createArtifact();
        artifact.setId(id);
        project.getArtifacts().add(artifact);
    }

    public void createNewBranch(String nameForManipulator, int id, boolean isMain) {
        Branch branch = objF.createBranch();
        branch.setId(id);
        dataModel.addDataToBranch(branch,nameForManipulator, isMain);
        project.getBranches().add(branch);
    }

    public void createNewCPR(String nameForManipulator, int roleIndex, int id) {

        ConfigPersonRelation cpr = objF.createConfigPersonRelation();
        cpr.setId(id);
        dataModel.addDataToCPR(cpr, nameForManipulator, roleIndex);
        project.getCpr().add(cpr);
    }

    public void createNewCriterion(String nameForManipulator, String descForManipulator, int id) {

        Criterion criterion = objF.createCriterion();
        criterion.setId(id);
        dataModel.addDataToCriterion(criterion, nameForManipulator, descForManipulator);
        project.getCriterions().add(criterion);
    }

    public void createNewPriority(String nameForManipulator, String classST, String superST, int id) {

        Priority priority = objF.createPriority();
        priority.setId(id);
        dataModel.addDataToPriority(priority, nameForManipulator, classST, superST);
        project.getPriority().add(priority);
    }

    public void createNewSeverity(String nameForManipulator, String classST, String superST, int id) {

        Severity severity = objF.createSeverity();
        severity.setId(id);
        dataModel.addDataToSeverity(severity, nameForManipulator, classST, superST);
        project.getSeverity().add(severity);
    }

    public void createNewRelation(String nameForManipulator, String classST, String superST, int id) {

        Relation relation = objF.createRelation();
        relation.setId(id);
        dataModel.addDataToRelation(relation, nameForManipulator, classST, superST);
        project.getRelation().add(relation);
    }

    public void createNewResolution(String nameForManipulator, String classST, String superST, int id) {

        Resolution resolution = objF.createResolution();
        resolution.setId(id);
        dataModel.addDataToResolution(resolution, nameForManipulator, classST, superST);
        project.getResolution().add(resolution);

    }

    public void crateNewRole(String nameForManipulator, String descForManipulator, int type, int id) {

        Role role = objF.createRole();
        role.setId(id);
        dataModel.addDatToRole(role, nameForManipulator, descForManipulator, type);
        project.getRoles().add(role);
    }

    public void createNewMilestone(String nameForManipulator, String description, ArrayList<Integer> criterionIndex, int id) {

        Milestone milestone = objF.createMilestone();
        milestone.setId(id);
        dataModel.addDataToMilestone(milestone, nameForManipulator, description, criterionIndex);
        project.getMilestones().add(milestone);
    }

    public void createNewRoleType(String nameForManipulator, String classST, String superST, int id) {

        RoleType roleType = objF.createRoleType();
        roleType.setId(id);
        dataModel.addDataToRoleType(roleType, nameForManipulator, classST, superST);
        project.getRoleType().add(roleType);
    }

    public void addTagToConfiguration(String tag, int configId, int index) {
        Configuration configuration = project.getConfiguration().get(configId);
        configuration.getTags().add(tag);
    }

    public void crateNewStatus(String nameForManipulator, String classST, String superST, int id) {

        Status status = objF.createStatus();
        status.setId(id);
        dataModel.addDataToStatus(status, nameForManipulator, classST, superST);
        project.getStatus().add(status);
    }

    public void createNewType(String nameForManipulator, String classST, String superST, int id) {

        Type type = objF.createType();
        type.setId(id);
        dataModel.addDataToType(type, nameForManipulator, classST, superST);
        project.getTypes().add(type);
    }

}
