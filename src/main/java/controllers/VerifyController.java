package controllers;

import SPADEPAC.*;
import database.*;
import graphics.windows.VerifyWindow;
import model.DataModel;
import services.Constans;
import services.SQLAtributeCreator;
import services.SegmentType;
import tables.VerifyTable;

import java.util.ArrayList;
import java.util.List;

public class VerifyController {

    private int projectVerifyId;
    private VerifyWindow verifyWindow;
    private DataModel verifyDataModel;

    public VerifyController(DataModel dataModel){
        this.verifyWindow = new VerifyWindow(this);
        this.verifyDataModel = dataModel;
    }

    public void verifyInstanceInProject(int projectVerifyId){
        this.projectVerifyId = projectVerifyId;
        verifyWindow.show();
        ArrayList<VerifyTable> verifyTables = fillVerifyTables();
        verifyWindow.setItemsToTable(verifyTables);
    }

    private ArrayList<VerifyTable> fillVerifyTables() {
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        ArrayList<VerifyTable> verifyTmp = new ArrayList<>();
        Project project = verifyDataModel.getProject();
  //      verifyTables.addAll(addCriterionTOVerifyTable(project.getCriterions()));
        
        verifyTmp = addPhaseTOVerifyTable(project.getPhases());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }
        
        verifyTmp =addIterationTOVerifyTable(project.getIterations());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addActivityTOVerifyTable(project.getActivities());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addPriorityTOVerifyTable(project.getPriority());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addSeverityTOVerifyTable(project.getSeverity());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addStatusTOVerifyTable(project.getStatus());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addTypeTOVerifyTable(project.getTypes());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addRelationTOVerifyTable(project.getRelation());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addResolutionTOVerifyTable(project.getResolution());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }
        
        verifyTmp = addPersonTOVerifyTable(project.getRoles());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addRoleTypeTOVerifyTable(project.getRoleType());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addBranchTOVerifyTable(project.getBranches());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCriterionTOVerifyTable(project.getCriterions());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addArtifactTOVerifyTable(project.getArtifacts());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addChangeTOVerifyTable(project.getChanges());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCommitTOVerifyTable(project.getCommit());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCommitedConfigurationTOVerifyTable(project.getCommitConfiguration());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addConfigurationTOVerifyTable(project.getConfiguration());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addWorkUnitTOVerifyTable(project.getWorkUnits());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addMilestoneTOVerifyTable(project.getMilestones());
        if (verifyTmp.get(0) != null){
            verifyTables.addAll(verifyTmp);
        }

        return verifyTables;
    }

    private VerifyTable createVerifyTable(SegmentType type, String alias, int modelCount, int projectCount, int id, boolean isExist,
                                          boolean isExistInProject, String sql){

        String result = Constans.OK_VERIFY_RESULT;
        String projectExist = "YES";
        if (isExist && !isExistInProject){
            projectExist = "NO";
            result = Constans.BAD_VERIFY_RESULT;
        }else if ((!isExist && isExistInProject)){
            result = Constans.BAD_VERIFY_RESULT;
        }

        if (modelCount != projectCount){
            projectExist = "NO";
            result = Constans.BAD_VERIFY_RESULT;
        }

        return new VerifyTable( type + ": " + alias, id, isExist, modelCount, projectCount, projectExist, result, sql);
    }

    private ArrayList<VerifyTable> addCommitedConfigurationTOVerifyTable(List<CommitedConfiguration> commitedConfigurations) {

        ArrayList<SQLVerifyObject> projectCommitedConfigurations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CommitedConfigurationDAO databazeProjekt = new CommitedConfigurationDAO(this);

            for (CommitedConfiguration modelCommitedConfiguration : commitedConfigurations){
                projectCommitedConfigurations = databazeProjekt.getCommitedConfigurationProjekt(projectVerifyId,
                        modelCommitedConfiguration.getName(), modelCommitedConfiguration.getNameIndicator(), modelCommitedConfiguration.getCommitedDay(), modelCommitedConfiguration.getCommitedDayIndicator());

                SQLVerifyObject commitedConfiguration = projectCommitedConfigurations.get(0);
                verifyTable = createVerifyTable(SegmentType.Committed_Configuration, modelCommitedConfiguration.getAlias(), modelCommitedConfiguration.getCount(), projectCommitedConfigurations.size(),
                        modelCommitedConfiguration.getId(), modelCommitedConfiguration.isExist(), commitedConfiguration.isExist(),
                        commitedConfiguration.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    


    private ArrayList<VerifyTable> addPhaseTOVerifyTable(List<Phase> phases) {

        ArrayList<SQLVerifyObject> projectPhases;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            PhaseDAO databazeProjekt = new PhaseDAO(this);

            for (Phase modelPhase : phases){
                projectPhases = databazeProjekt.getPhaseyProjekt(projectVerifyId, modelPhase.getEndDate(), modelPhase.getEndDateIndicator());

                SQLVerifyObject phase = projectPhases.get(0);
                verifyTable = createVerifyTable(SegmentType.Phase, modelPhase.getAlias(), -1, -1, modelPhase.getId(), modelPhase.isExist(), phase.isExist(), phase.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addCommitTOVerifyTable(List<Commit> commits) {

        ArrayList<SQLVerifyObject> projectCommits;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CommitDAO databazeProjekt = new CommitDAO(this);

            for (Commit modelCommit : commits){
                projectCommits = databazeProjekt.getCommitProjekt(projectVerifyId, modelCommit.getName(), modelCommit.getNameIndicator(), modelCommit.isRelease(), modelCommit.getBranch(), modelCommit.getTags());

                SQLVerifyObject commit = projectCommits.get(0);
                verifyTable = createVerifyTable(SegmentType.Commit, modelCommit.getAlias(), modelCommit.getCount(), projectCommits.size(),  modelCommit.getId(), modelCommit.isExist(), commit.isExist(), commit.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }
    

    private ArrayList<VerifyTable> addCriterionTOVerifyTable(List<Criterion> criterions) {

        ArrayList<SQLVerifyObject> projectCriterions;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CriterionDAO databazeProjekt = new CriterionDAO(this);

            for (Criterion modelCriterion : criterions){
                projectCriterions = databazeProjekt.getCriterionProjekt(projectVerifyId, modelCriterion.getName(), modelCriterion.getNameIndicator(),
                        modelCriterion.getDescription(), modelCriterion.getDescriptionIndicator());

                SQLVerifyObject criterion = projectCriterions.get(0);
                verifyTable = createVerifyTable(SegmentType.Criterion, modelCriterion.getAlias(), -1, -1, modelCriterion.getId(), modelCriterion.isExist(), criterion.isExist(), criterion.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addActivityTOVerifyTable(List<Activity> activitys) {

        ArrayList<SQLVerifyObject> projectActivitys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ActivityDAO databazeProjekt = new ActivityDAO(this);

            for (Activity modelActivity : activitys){
                projectActivitys = databazeProjekt.getActivityyProjekt(projectVerifyId, modelActivity.getEndDate(), modelActivity.getEndDateIndicator());

                SQLVerifyObject activity = projectActivitys.get(0);
                verifyTable = createVerifyTable(SegmentType.Activity, modelActivity.getAlias(), -1, -1, modelActivity.getId(), modelActivity.isExist(), activity.isExist(), activity.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addBranchTOVerifyTable(List<Branch> branchs) {

        ArrayList<SQLVerifyObject> projectBranchs;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            BranchDAO databazeProjekt = new BranchDAO(this);

            for (Branch modelBranch : branchs){
                projectBranchs = databazeProjekt.getBranchyProjekt(projectVerifyId, modelBranch.getName(), modelBranch.getNameIndicator(), modelBranch.isIsMain());

                SQLVerifyObject branch = projectBranchs.get(0);
                verifyTable = createVerifyTable(SegmentType.Branch, modelBranch.getAlias(), -1, -1, modelBranch.getId(), modelBranch.isExist(), branch.isExist(), branch.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addSeverityTOVerifyTable(List<Severity> severitys) {

        ArrayList<SQLVerifyObject> projectSeveritys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Severity modelSeverity : severitys){
                projectSeveritys = databazeProjekt.getSeverityProjekt(projectVerifyId, modelSeverity.getName(), modelSeverity.getNameIndicator(),
                        modelSeverity.getSeverityClassIndex().get(0), modelSeverity.getSeveritySuperClassIndex().get(0));

                SQLVerifyObject severity = projectSeveritys.get(0);
                verifyTable = createVerifyTable(SegmentType.Severity, modelSeverity.getAlias(), -1, -1, modelSeverity.getId(), modelSeverity.isExist(), severity.isExist(), severity.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addRoleTypeTOVerifyTable(List<RoleType> severitys) {

        ArrayList<SQLVerifyObject> projectRoleTypes;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            RoleDAO databazeProjekt = new RoleDAO(this);

            for (RoleType modelRoleType : severitys){
                projectRoleTypes = databazeProjekt.getRoleProjekt(projectVerifyId, modelRoleType.getName(), modelRoleType.getNameIndicator(),
                        modelRoleType.getRoleTypeClassIndex().get(0), modelRoleType.getRoleTypeSuperClassIndex().get(0));

                SQLVerifyObject severity = projectRoleTypes.get(0);
                verifyTable = createVerifyTable(SegmentType.Role_Type, modelRoleType.getAlias(), -1, -1, modelRoleType.getId(), modelRoleType.isExist(), severity.isExist(), severity.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addPriorityTOVerifyTable(List<Priority> prioritys) {

        ArrayList<SQLVerifyObject> projectPrioritys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Priority modelPriority : prioritys){
                projectPrioritys = databazeProjekt.getPriorityProjekt(projectVerifyId, modelPriority.getName(), modelPriority.getNameIndicator(),
                        modelPriority.getPriorityClassIndex().get(0), modelPriority.getPrioritySuperClassIndex().get(0));

                SQLVerifyObject priority = projectPrioritys.get(0);
                verifyTable = createVerifyTable(SegmentType.Priority, modelPriority.getAlias(), -1, -1, modelPriority.getId(), modelPriority.isExist(), priority.isExist(), priority.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addPersonTOVerifyTable(List<Person> persons) {

        ArrayList<SQLVerifyObject> projectPersons;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            PersonDAO databazeProjekt = new PersonDAO(this);
            RoleDAO roleDAO = new RoleDAO(this);
            for (Person modelPerson : persons){

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createRoleTypeAttribute(modelPerson.getType(), verifyDataModel, roleDAO, projectVerifyId);

                projectPersons = databazeProjekt.getPersonProjekt(projectVerifyId, modelPerson.getName(), modelPerson.getNameIndicator(), roleDBId);

                SQLVerifyObject person = projectPersons.get(0);
                verifyTable = createVerifyTable(SegmentType.Person, modelPerson.getAlias(), modelPerson.getCount(), projectPersons.size(), modelPerson.getId(), modelPerson.isExist(), person.isExist(), person.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addArtifactTOVerifyTable(List<Artifact> artifacts) {

        ArrayList<SQLVerifyObject> projectArtifacts;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ArtifactDAO databazeProjekt = new ArtifactDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleDAO roleDAO = new RoleDAO(this);

            for (Artifact modelArtifact : artifacts){


                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelArtifact.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);

                projectArtifacts = databazeProjekt.getArtifactProjekt(projectVerifyId, modelArtifact.getName(), modelArtifact.getNameIndicator(), modelArtifact.getDescription(), modelArtifact.getDescriptionIndicator(),
                        modelArtifact.getCreated(), modelArtifact.getCreatedIndicator(), roleDBId);

                SQLVerifyObject artifact = projectArtifacts.get(0);
                verifyTable = createVerifyTable(SegmentType.Artifact, modelArtifact.getAlias(), modelArtifact.getCount(), projectArtifacts.size(),  modelArtifact.getId(), modelArtifact.isExist(), artifact.isExist(), artifact.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addConfigurationTOVerifyTable(List<Configuration> configurations) {

        ArrayList<SQLVerifyObject> projectConfigurations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ConfigurationDAO databazeProjekt = new ConfigurationDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleDAO roleDAO = new RoleDAO(this);
            ChangeDAO changeDAO = new ChangeDAO(this);
            CommitedConfigurationDAO commitedConfigurationDAO = new CommitedConfigurationDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            for (Configuration modelConfiguration : configurations){

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelConfiguration.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);
                ArrayList<Integer> changeDBId = SQLAtributeCreator.createChangeAttribute(modelConfiguration.getChangesIndexs(), verifyDataModel, changeDAO, artifactDAO, personDAO, roleDAO, projectVerifyId);
                ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(modelConfiguration.getArtifactsIndexs(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleDAO);
                ArrayList<Integer> committedConfigurationDBId = SQLAtributeCreator.createCommittedAttribute(modelConfiguration.getCommitedConfiguration(), verifyDataModel, commitedConfigurationDAO, projectVerifyId);

                projectConfigurations = databazeProjekt.getConfigurationProjekt(projectVerifyId, modelConfiguration.getName(), modelConfiguration.getNameIndicator(),
                        modelConfiguration.getCreated(), modelConfiguration.getCreatedIndicator(),
                        changeDBId, committedConfigurationDBId, roleDBId, artifactDBId);

                SQLVerifyObject configuration = projectConfigurations.get(0);
                verifyTable = createVerifyTable(SegmentType.Configuration, modelConfiguration.getAlias(), modelConfiguration.getCount(), projectConfigurations.size(), modelConfiguration.getId(), modelConfiguration.isExist(), configuration.isExist(), configuration.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }
    

    private ArrayList<VerifyTable> addChangeTOVerifyTable(List<Change> changes) {

        ArrayList<SQLVerifyObject> projectChanges;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ChangeDAO databazeProjekt = new ChangeDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleDAO roleDAO = new RoleDAO(this);
            for (Change modelChange : changes){


                ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(modelChange.getArtifactIndex(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleDAO);
                projectChanges = databazeProjekt.getChangeProjekt(projectVerifyId, modelChange.getName(), modelChange.getNameIndicator(), modelChange.getDescription(), modelChange.getDescriptionIndicator()
                       , artifactDBId);

                SQLVerifyObject change = projectChanges.get(0);
                verifyTable = createVerifyTable(SegmentType.Change, modelChange.getAlias(), -1, -1, modelChange.getId(), modelChange.isExist(), change.isExist(), change.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }
    
    
    
    private ArrayList<VerifyTable> addIterationTOVerifyTable(List<Iteration> iterations) {

        ArrayList<SQLVerifyObject> projectIterations = new ArrayList<>();
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            IterationDAO databazeProjekt = new IterationDAO(this);

            for (Iteration modelIteration : iterations){
                projectIterations = databazeProjekt.getIterationyProjekt(projectVerifyId, modelIteration.getEndDate(), modelIteration.getStartDate(),
                        modelIteration.getEndDateIndicator(), modelIteration.getStartDateIndicator());

                SQLVerifyObject iteration = projectIterations.get(0);
                verifyTable = createVerifyTable(SegmentType.Iteration, modelIteration.getAlias(), -1, -1, modelIteration.getId(), modelIteration.isExist(), iteration.isExist(), iteration.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

       verifyTables.add(verifyTable);
        return verifyTables;
    }


    private ArrayList<VerifyTable> addStatusTOVerifyTable(List<Status> statuss) {

        ArrayList<SQLVerifyObject> projectStatuss;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Status modelStatus : statuss){
                projectStatuss = databazeProjekt.getStatusProjekt(projectVerifyId, modelStatus.getName(), modelStatus.getNameIndicator(),
                        modelStatus.getStatusClassIndex().get(0), modelStatus.getStatusSuperClassIndex().get(0));

                SQLVerifyObject status = projectStatuss.get(0);
                verifyTable = createVerifyTable(SegmentType.Status, modelStatus.getAlias(), -1, -1, modelStatus.getId(), modelStatus.isExist(), status.isExist(), status.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addRelationTOVerifyTable(List<Relation> relations) {

        ArrayList<SQLVerifyObject> projectRelations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Relation modelRelation : relations){
                projectRelations = databazeProjekt.getRelationProjekt(projectVerifyId, modelRelation.getName(), modelRelation.getNameIndicator(),
                        modelRelation.getRelationClassIndex().get(0), modelRelation.getRelationSuperClassIndex().get(0));

                SQLVerifyObject relation = projectRelations.get(0);
                verifyTable = createVerifyTable(SegmentType.Relation, modelRelation.getAlias(), -1, -1, modelRelation.getId(), modelRelation.isExist(), relation.isExist(), relation.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addResolutionTOVerifyTable(List<Resolution> resolutions) {

        ArrayList<SQLVerifyObject> projectResolutions;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Resolution modelResolution : resolutions){
                projectResolutions = databazeProjekt.getResolutionProjekt(projectVerifyId, modelResolution.getName(), modelResolution.getNameIndicator(),
                        modelResolution.getResolutionClassIndex().get(0), modelResolution.getResolutionSuperClassIndex().get(0));

                SQLVerifyObject resolution = projectResolutions.get(0);
                verifyTable = createVerifyTable(SegmentType.Resolution, modelResolution.getAlias(), -1, -1, modelResolution.getId(), modelResolution.isExist(), resolution.isExist(), resolution.getSqlCommand());

            }
        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addTypeTOVerifyTable(List<Type> types) {

        ArrayList<SQLVerifyObject> projectTypes;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Type modelType : types){
                projectTypes = databazeProjekt.getTypeProjekt(projectVerifyId, modelType.getName(), modelType.getNameIndicator(),
                        modelType.getTypeClassIndex().get(0), modelType.getTypeSuperClassIndex().get(0));

                SQLVerifyObject type = projectTypes.get(0);
                verifyTable = createVerifyTable(SegmentType.Type, modelType.getAlias(),-1, -1, modelType.getId(), modelType.isExist(), type.isExist(), type.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addWorkUnitTOVerifyTable(List<WorkUnit> workUnits) {

        ArrayList<SQLVerifyObject> projectWorkUnits;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitDAO databazeProjekt = new WorkUnitDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleDAO roleDAO = new RoleDAO(this);
            WorkUnitElementDAO workUnitElementDAO = new WorkUnitElementDAO(this);
            CategoryDAO categoryDAO = new CategoryDAO(this);
            for (WorkUnit modelWorkUnit : workUnits){

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelWorkUnit.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);
                ArrayList<Integer> priorityDBId = SQLAtributeCreator.createPriorityAttribute(modelWorkUnit.getPriorityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> severityDBId = SQLAtributeCreator.createSeverityAttribute(modelWorkUnit.getSeverityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> statusDBId = SQLAtributeCreator.createStatusAttribute(modelWorkUnit.getStatusIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> typeDBId = SQLAtributeCreator.createTypeAttribute(modelWorkUnit.getTypeIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> resolutionDBId = SQLAtributeCreator.createResolutionAttribute(modelWorkUnit.getResolutionIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> categoryDBId = SQLAtributeCreator.createCategoryAttribute(modelWorkUnit.getCategory(), modelWorkUnit.getCategoryIndicator(), verifyDataModel, categoryDAO, projectVerifyId);
                ArrayList<Integer> relationDBId = SQLAtributeCreator.createRelationAttribute(modelWorkUnit.getRelationIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> workUntiInRelationDBId = SQLAtributeCreator.createWorkUnitAttribute(modelWorkUnit.getWorkUnits(), verifyDataModel, databazeProjekt, workUnitElementDAO, personDAO, roleDAO, categoryDAO, projectVerifyId);

                projectWorkUnits = databazeProjekt.getWorkUnitProjekt(projectVerifyId, modelWorkUnit.getName(), modelWorkUnit.getNameIndicator(), modelWorkUnit.getEstimatedTime(), categoryDBId,
                        roleDBId, priorityDBId, severityDBId, resolutionDBId, statusDBId, typeDBId, relationDBId, workUntiInRelationDBId);

                SQLVerifyObject workUnit = projectWorkUnits.get(0);
                verifyTable = createVerifyTable(SegmentType.Work_Unit, modelWorkUnit.getAlias(), -1, -1, modelWorkUnit.getId(), modelWorkUnit.isExist(), workUnit.isExist(), workUnit.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }

    private ArrayList<VerifyTable> addMilestoneTOVerifyTable(List<Milestone> milestones) {

        ArrayList<SQLVerifyObject> projectMilestones;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            MilestoneDAO databazeProjekt = new MilestoneDAO(this);
            CriterionDAO criterionDAO = new CriterionDAO(this);

            for (Milestone modelMilestone : milestones){

                ArrayList<Integer> criterionDBId = SQLAtributeCreator.createCriterionAttribute(modelMilestone.getCriteriaIndexs(), verifyDataModel, criterionDAO, projectVerifyId);

                projectMilestones = databazeProjekt.getMilestoneProjekt(projectVerifyId, modelMilestone.getName(), modelMilestone.getNameIndicator(), modelMilestone.getDescription(), modelMilestone.getDescriptionIndicator(),
                        criterionDBId);

                SQLVerifyObject milestone = projectMilestones.get(0);
                verifyTable = createVerifyTable(SegmentType.Milestone, modelMilestone.getAlias(),  -1, -1, modelMilestone.getId(), modelMilestone.isExist(), milestone.isExist(), milestone.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }


    public SQLVerifyObject createSQLVerifyObject(int id, String sql, boolean exist) {
        return new SQLVerifyObject(id, sql, exist);
    }
}
