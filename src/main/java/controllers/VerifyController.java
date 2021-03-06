package controllers;

import SPADEPAC.*;
import database.*;
import graphics.windows.VerifyWindow;
import model.DataModel;
import services.Constans;
import services.SegmentType;
import tables.VerifyTable;

import java.util.ArrayList;
import java.util.List;

public class VerifyController {

    /**
     * Identifikator zvoleneho projektu
     **/
    private int projectVerifyId;
    /**
     * Okno vysledky
     **/
    private VerifyWindow verifyWindow;
    /**
     * Model pro overeni
     **/
    private DataModel verifyDataModel;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne
     *
     * @param dataModel
     */
    public VerifyController(DataModel dataModel) {
        this.verifyWindow = new VerifyWindow(this);
        this.verifyDataModel = dataModel;
    }

    /**
     * Metoda pro zobrazeni okna
     * Zavola metodu pro overeni prvku datoveho modelu
     *
     * @param projectVerifyId identifikator projektu
     */
    public void verifyInstanceInProject(int projectVerifyId) {
        this.projectVerifyId = projectVerifyId;
        verifyWindow.show();
        ArrayList<VerifyTable> verifyTables = fillVerifyTables();
        verifyWindow.setItemsToTable(verifyTables);
    }

    /**
     * Metoda postupne vola metody pro overeni jednotlivych casti modelu
     *
     * @return seznam instaci VerifyTable pro vysledkove okno
     */
    private ArrayList<VerifyTable> fillVerifyTables() {
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        ArrayList<VerifyTable> verifyTmp = new ArrayList<>();
        Project project = verifyDataModel.getProject();

        verifyTmp = addProjectTOVerifyTable(project);
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addPhaseTOVerifyTable(verifyDataModel.getPhases());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addIterationTOVerifyTable(verifyDataModel.getIterations());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addActivityTOVerifyTable(verifyDataModel.getActivities());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addPriorityTOVerifyTable(verifyDataModel.getPriorities());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addSeverityTOVerifyTable(verifyDataModel.getSeverities());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addStatusTOVerifyTable(verifyDataModel.getStatuses());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addTypeTOVerifyTable(verifyDataModel.getTypes());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addRelationTOVerifyTable(verifyDataModel.getRelations());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addResolutionTOVerifyTable(verifyDataModel.getResolutions());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addPersonTOVerifyTable(verifyDataModel.getPersons());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addVCSTagTOVerifyTable(verifyDataModel.getVCSTags());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCPRTOVerifyTable(verifyDataModel.getConfigPersonRelations());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addRoleTypeTOVerifyTable(verifyDataModel.getRoleTypes());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addBranchTOVerifyTable(verifyDataModel.getBranches());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCriterionTOVerifyTable(verifyDataModel.getCriterions());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addArtifactTOVerifyTable(verifyDataModel.getArtifacts());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addChangeTOVerifyTable(verifyDataModel.getChanges());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCommitTOVerifyTable(verifyDataModel.getCommits());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addCommitedConfigurationTOVerifyTable(verifyDataModel.getCommitedConfiguration());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addConfigurationTOVerifyTable(verifyDataModel.getConfigurations());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addWorkUnitTOVerifyTable(verifyDataModel.getWorkUnits());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        verifyTmp = addMilestoneTOVerifyTable(verifyDataModel.getMilestones());
        if (verifyTmp.size() != 0) {
            verifyTables.addAll(verifyTmp);
        }

        return verifyTables;
    }

    /**
     * Metoda pro vyhodnoceni vysledku ziskanych z databaze
     *
     * @param type             type elementu nebo segmentu
     * @param alias            alias prvku
     * @param modelCount       pocet instaci v modelu
     * @param countIndicator   ukazatel rovnosti poctu instanci
     * @param projectCount     pocet instanci v projektu
     * @param id               identifikator prvku
     * @param isExist          existence prvku
     * @param isExistInProject existence prvku v projektu
     * @param sql              SQL dotaz
     * @return
     */
    private VerifyTable createVerifyTable(SegmentType type, String alias, int modelCount, int countIndicator, int projectCount, int id, boolean isExist,
                                          boolean isExistInProject, String sql) {

        String result = Constans.OK_VERIFY_RESULT;
        String projectExist = "YES";
        String countIndicatorChar = "=";
        if (!isExistInProject) {
            projectExist = "NO";
            projectCount = 0;
        }


        if (isExist) {
            if (!isExistInProject) {
                result = Constans.BAD_VERIFY_RESULT;
            }

            if (countIndicator == 0) {
                if (modelCount < projectCount || modelCount > projectCount) {
                    result = Constans.BAD_VERIFY_RESULT;
                }
            } else if (countIndicator == 1) {
                if (modelCount == projectCount || modelCount > projectCount) {
                    result = Constans.BAD_VERIFY_RESULT;
                }
                countIndicatorChar = ">";
            } else if (countIndicator == 2) {
                if (modelCount == projectCount || modelCount < projectCount) {
                    result = Constans.BAD_VERIFY_RESULT;
                }
                countIndicatorChar = "<";
            }
        } else {
            if (isExistInProject) {
                result = Constans.BAD_VERIFY_RESULT;
                if (countIndicator == 0) {
                    if (modelCount < projectCount || modelCount > projectCount) {
                        result = Constans.OK_VERIFY_RESULT;
                    }
                } else if (countIndicator == 1) {
                    if (modelCount == projectCount || modelCount < projectCount) {
                        result = Constans.OK_VERIFY_RESULT;
                    }

                } else if (countIndicator == 2) {
                    if (modelCount == projectCount || modelCount > projectCount) {
                        result = Constans.OK_VERIFY_RESULT;
                    }
                }
            }
            if (countIndicator == 1) {
                countIndicatorChar = ">";
            } else if (countIndicator == 2) {
                countIndicatorChar = "<";
            }

        }

        return new VerifyTable(type + ": " + alias, id, isExist, modelCount, countIndicatorChar, projectCount, projectExist, result, sql);
    }

    /**
     * Metoda pro kontrolu elementu CommittedConfiguration v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param commitedConfigurations seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addCommitedConfigurationTOVerifyTable(List<CommitedConfiguration> commitedConfigurations) {

        ArrayList<SQLVerifyObject> projectCommitedConfigurations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CommitedConfigurationDAO databazeProjekt = new CommitedConfigurationDAO(this);
            CommitDAO commitDAO = new CommitDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            for (CommitedConfiguration modelCommitedConfiguration : commitedConfigurations) {

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelCommitedConfiguration.getRole(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> commitDBId = SQLAtributeCreator.createCommitAttribute(modelCommitedConfiguration.getCommit(), verifyDataModel, commitDAO, personDAO, roleTypeDAO, projectVerifyId);

                projectCommitedConfigurations = databazeProjekt.getCommitedConfigurationProjekt(projectVerifyId, modelCommitedConfiguration.getName(), modelCommitedConfiguration.getNameIndicator(),
                        modelCommitedConfiguration.getCommitedDay(), modelCommitedConfiguration.getCommitedDayIndicator(),
                        modelCommitedConfiguration.getDescription(), modelCommitedConfiguration.getDescriptionIndicator(), modelCommitedConfiguration.getCreated(),
                        modelCommitedConfiguration.getCreatedIndicator(), commitDBId, roleDBId);

                SQLVerifyObject commitedConfiguration = projectCommitedConfigurations.get(0);
                verifyTable = createVerifyTable(SegmentType.Committed_Configuration, modelCommitedConfiguration.getAlias(), modelCommitedConfiguration.getCount(), modelCommitedConfiguration.getCountIndicator(),
                        projectCommitedConfigurations.size(), modelCommitedConfiguration.getId(), modelCommitedConfiguration.isExist(), commitedConfiguration.isExist(),
                        commitedConfiguration.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Phase v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param phases seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addPhaseTOVerifyTable(List<Phase> phases) {

        ArrayList<SQLVerifyObject> projectPhases;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            PhaseDAO databazeProjekt = new PhaseDAO(this);
            MilestoneDAO milestoneDAO = new MilestoneDAO(this);
            ConfigurationDAO configurationDAO = new ConfigurationDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            ChangeDAO changeDAO = new ChangeDAO(this);
            BranchDAO branchDAO = new BranchDAO(this);
            CommitedConfigurationDAO commitedConfigurationDAO = new CommitedConfigurationDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            CommitDAO commitDAO = new CommitDAO(this);
            CPRDAO cprdao = new CPRDAO(this);
            VCSTagDAO vcsTagDAO = new VCSTagDAO(this);
            CriterionDAO criterionDAO = new CriterionDAO(this);
            for (Phase modelPhase : phases) {


                ArrayList<Integer> milestoneDBId = SQLAtributeCreator.createMilestoneAttribute(modelPhase.getMilestoneIndex(), verifyDataModel,
                        milestoneDAO, criterionDAO, projectVerifyId);
                ArrayList<Integer> configDBId = SQLAtributeCreator.createConfigurationAttribute(modelPhase.getConfiguration(), verifyDataModel,
                        configurationDAO, personDAO, roleTypeDAO, changeDAO, branchDAO, vcsTagDAO, cprdao, artifactDAO, commitedConfigurationDAO, commitDAO, projectVerifyId);


                projectPhases = databazeProjekt.getPhaseyProjekt(projectVerifyId, modelPhase.getName(), modelPhase.getNameIndicator(), modelPhase.getDescription(), modelPhase.getDescriptionIndicator(),
                        modelPhase.getEndDate(), modelPhase.getEndDateIndicator(), milestoneDBId, configDBId);

                SQLVerifyObject phase = projectPhases.get(0);
                verifyTable = createVerifyTable(SegmentType.Phase, modelPhase.getAlias(), -1, -1, -1, modelPhase.getId(), modelPhase.isExist(), phase.isExist(), phase.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Commit v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param commits seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addCommitTOVerifyTable(List<Commit> commits) {

        ArrayList<SQLVerifyObject> projectCommits;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CommitDAO databazeProjekt = new CommitDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            for (Commit modelCommit : commits) {
                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelCommit.getAuthor(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);
                projectCommits = databazeProjekt.getCommitProjekt(projectVerifyId, modelCommit.getName(), modelCommit.getNameIndicator(),
                        modelCommit.isRelease(), modelCommit.getDescription(), modelCommit.getDescriptionIndicator(), modelCommit.getCreated(), modelCommit.getCreatedIndicator(), roleDBId);
                SQLVerifyObject commit = projectCommits.get(0);
                verifyTable = createVerifyTable(SegmentType.Commit, modelCommit.getAlias(), modelCommit.getCount(), modelCommit.getCountIndicator(), projectCommits.size(), modelCommit.getId(), modelCommit.isExist(), commit.isExist(), commit.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Criterion v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param criterions seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addCriterionTOVerifyTable(List<Criterion> criterions) {

        ArrayList<SQLVerifyObject> projectCriterions;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CriterionDAO databazeProjekt = new CriterionDAO(this);

            for (Criterion modelCriterion : criterions) {
                projectCriterions = databazeProjekt.getCriterionProjekt(projectVerifyId, modelCriterion.getName(), modelCriterion.getNameIndicator(),
                        modelCriterion.getDescription(), modelCriterion.getDescriptionIndicator());

                SQLVerifyObject criterion = projectCriterions.get(0);
                verifyTable = createVerifyTable(SegmentType.Criterion, modelCriterion.getAlias(), -1, -1, -1, modelCriterion.getId(), modelCriterion.isExist(), criterion.isExist(), criterion.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Activity v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param activitys seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addActivityTOVerifyTable(List<Activity> activitys) {

        ArrayList<SQLVerifyObject> projectActivitys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ActivityDAO databazeProjekt = new ActivityDAO(this);
            WorkUnitDAO WUDAO = new WorkUnitDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            WorkUnitElementDAO workUnitElementDAO = new WorkUnitElementDAO(this);
            CategoryDAO categoryDAO = new CategoryDAO(this);

            for (Activity modelActivity : activitys) {
                ArrayList<Integer> WUDBId = SQLAtributeCreator.createWorkUnitAttribute(modelActivity.getWorkUnits(), verifyDataModel, WUDAO, workUnitElementDAO, personDAO, roleTypeDAO, categoryDAO, projectVerifyId);
                projectActivitys = databazeProjekt.getActivityFormProjekt(projectVerifyId, modelActivity.getName(), modelActivity.getNameIndicator(),
                        modelActivity.getDescription(), modelActivity.getDescriptionIndicator(), modelActivity.getEndDate(), modelActivity.getEndDateIndicator(), WUDBId);


                SQLVerifyObject activity = projectActivitys.get(0);
                verifyTable = createVerifyTable(SegmentType.Activity, modelActivity.getAlias(), -1, -1, -1, modelActivity.getId(), modelActivity.isExist(), activity.isExist(), activity.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Branch v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param branchs seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addBranchTOVerifyTable(List<Branch> branchs) {

        ArrayList<SQLVerifyObject> projectBranchs;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            BranchDAO databazeProjekt = new BranchDAO(this);

            for (Branch modelBranch : branchs) {
                projectBranchs = databazeProjekt.getBranchyProjekt(projectVerifyId, modelBranch.getName(), modelBranch.getNameIndicator(), modelBranch.isIsMain());

                SQLVerifyObject branch = projectBranchs.get(0);
                verifyTable = createVerifyTable(SegmentType.Branch, modelBranch.getAlias(), -1, -1, -1, modelBranch.getId(), modelBranch.isExist(), branch.isExist(), branch.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Severity v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param severitys seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addSeverityTOVerifyTable(List<Severity> severitys) {

        ArrayList<SQLVerifyObject> projectSeveritys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Severity modelSeverity : severitys) {
                projectSeveritys = databazeProjekt.getSeverityProjekt(projectVerifyId, modelSeverity.getName(), modelSeverity.getNameIndicator(),
                        modelSeverity.getSeverityClassIndex(), modelSeverity.getSeveritySuperClassIndex());

                SQLVerifyObject severity = projectSeveritys.get(0);
                verifyTable = createVerifyTable(SegmentType.Severity, modelSeverity.getAlias(), -1, -1, -1, modelSeverity.getId(), modelSeverity.isExist(), severity.isExist(), severity.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Role Type v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param roleTypes seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addRoleTypeTOVerifyTable(List<RoleType> roleTypes) {

        ArrayList<SQLVerifyObject> projectRoleTypes;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            RoleTypeDAO databazeProjekt = new RoleTypeDAO(this);

            for (RoleType modelRoleType : roleTypes) {
                projectRoleTypes = databazeProjekt.getRoleProjekt(projectVerifyId, modelRoleType.getName(), modelRoleType.getNameIndicator(),
                        modelRoleType.getRoleTypeClassIndex(), modelRoleType.getRoleTypeSuperClassIndex());

                SQLVerifyObject severity = projectRoleTypes.get(0);
                verifyTable = createVerifyTable(SegmentType.Role_Type, modelRoleType.getAlias(), -1, -1, -1, modelRoleType.getId(), modelRoleType.isExist(), severity.isExist(), severity.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }

        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Priority v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param prioritys seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addPriorityTOVerifyTable(List<Priority> prioritys) {

        ArrayList<SQLVerifyObject> projectPrioritys;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Priority modelPriority : prioritys) {
                projectPrioritys = databazeProjekt.getPriorityProjekt(projectVerifyId, modelPriority.getName(), modelPriority.getNameIndicator(),
                        modelPriority.getPriorityClassIndex(), modelPriority.getPrioritySuperClassIndex());

                SQLVerifyObject priority = projectPrioritys.get(0);
                verifyTable = createVerifyTable(SegmentType.Priority, modelPriority.getAlias(), -1, -1, -1, modelPriority.getId(), modelPriority.isExist(), priority.isExist(), priority.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Person v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param persons seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addPersonTOVerifyTable(List<Person> persons) {

        ArrayList<SQLVerifyObject> projectPersons;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            PersonDAO databazeProjekt = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            for (Person modelPerson : persons) {

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createRoleTypeAttribute(modelPerson.getType(), verifyDataModel, roleTypeDAO, projectVerifyId);

                projectPersons = databazeProjekt.getPersonProjekt(projectVerifyId, modelPerson.getName(), modelPerson.getNameIndicator(), roleDBId);

                SQLVerifyObject person = projectPersons.get(0);
                verifyTable = createVerifyTable(SegmentType.Person, modelPerson.getAlias(), modelPerson.getCount(), modelPerson.getCountIndicator(), projectPersons.size(), modelPerson.getId(), modelPerson.isExist(), person.isExist(), person.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Artifact v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param artifacts seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addArtifactTOVerifyTable(List<Artifact> artifacts) {

        ArrayList<SQLVerifyObject> projectArtifacts;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ArtifactDAO databazeProjekt = new ArtifactDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);

            for (Artifact modelArtifact : artifacts) {


                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelArtifact.getAuthorIndex(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);

                projectArtifacts = databazeProjekt.getArtifactProjekt(projectVerifyId, modelArtifact.getName(), modelArtifact.getNameIndicator(), modelArtifact.getDescription(), modelArtifact.getDescriptionIndicator(),
                        modelArtifact.getCreated(), modelArtifact.getCreatedIndicator(), roleDBId, modelArtifact.getMimeType(), modelArtifact.getMimeTypeIndicator());

                SQLVerifyObject artifact = projectArtifacts.get(0);
                verifyTable = createVerifyTable(SegmentType.Artifact, modelArtifact.getAlias(), modelArtifact.getCount(), modelArtifact.getCountIndicator(), projectArtifacts.size(), modelArtifact.getId(), modelArtifact.isExist(), artifact.isExist(), artifact.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Configuration Person v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param cprs seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addCPRTOVerifyTable(List<ConfigPersonRelation> cprs) {

        ArrayList<SQLVerifyObject> projectcprs;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            CPRDAO databazeProjekt = new CPRDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);

            for (ConfigPersonRelation modelCPR : cprs) {


                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelCPR.getPersonIndex(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);

                projectcprs = databazeProjekt.getCPRProjekt(projectVerifyId, modelCPR.getName(), modelCPR.getNameIndicator(), modelCPR.getDescription(), modelCPR.getDescriptionIndicator(),
                        roleDBId);

                SQLVerifyObject cprObject = projectcprs.get(0);
                verifyTable = createVerifyTable(SegmentType.Config_Person_Relation, modelCPR.getAlias(), -1, -1, -1, modelCPR.getId(),
                        modelCPR.isExist(), cprObject.isExist(), cprObject.getSqlCommand());
                verifyTables.add(verifyTable);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Configuration v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param configurations seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addConfigurationTOVerifyTable(List<Configuration> configurations) {

        ArrayList<SQLVerifyObject> projectConfigurations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ConfigurationDAO databazeProjekt = new ConfigurationDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            ChangeDAO changeDAO = new ChangeDAO(this);
            BranchDAO branchDAO = new BranchDAO(this);
            CommitedConfigurationDAO commitedConfigurationDAO = new CommitedConfigurationDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            CommitDAO commitDAO = new CommitDAO(this);
            CPRDAO cprdao = new CPRDAO(this);
            VCSTagDAO vcsTagDAO = new VCSTagDAO(this);
            for (Configuration modelConfiguration : configurations) {

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelConfiguration.getAuthorIndex(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> changeDBId = SQLAtributeCreator.createChangeAttribute(modelConfiguration.getChangesIndexs(), verifyDataModel, changeDAO, artifactDAO, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(modelConfiguration.getArtifactsIndexs(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleTypeDAO);
                ArrayList<Integer> committedConfigurationDBId = SQLAtributeCreator.createCommittedAttribute(modelConfiguration.getCommitedConfiguration(), verifyDataModel, commitedConfigurationDAO, commitDAO, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> branchDBId = SQLAtributeCreator.createBranchAttribute(modelConfiguration.getBranchIndexs(), verifyDataModel, branchDAO, artifactDAO, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> tagDBId = SQLAtributeCreator.createVCSTagAttribute(modelConfiguration.getTagIndex(), verifyDataModel, vcsTagDAO, projectVerifyId);
                ArrayList<Integer> cprDBId = SQLAtributeCreator.createCPRAttribute(modelConfiguration.getCPRsIndexs(), verifyDataModel, cprdao, personDAO, roleTypeDAO, projectVerifyId);

                projectConfigurations = databazeProjekt.getConfigurationProjekt(projectVerifyId, modelConfiguration.getName(), modelConfiguration.getNameIndicator(),
                        modelConfiguration.getDescription(), modelConfiguration.getDescriptionIndicator(), modelConfiguration.getCreated(), modelConfiguration.getCreatedIndicator(),
                        changeDBId, branchDBId, cprDBId, tagDBId, committedConfigurationDBId, roleDBId, artifactDBId);

                SQLVerifyObject configuration = projectConfigurations.get(0);
                verifyTable = createVerifyTable(SegmentType.Configuration, modelConfiguration.getAlias(), modelConfiguration.getCount(), modelConfiguration.getCountIndicator(), projectConfigurations.size(), modelConfiguration.getId(), modelConfiguration.isExist(), configuration.isExist(), configuration.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }
        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Changes v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param changes seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addChangeTOVerifyTable(List<Change> changes) {

        ArrayList<SQLVerifyObject> projectChanges;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ChangeDAO databazeProjekt = new ChangeDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            for (Change modelChange : changes) {


                ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(modelChange.getArtifactIndex(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleTypeDAO);
                projectChanges = databazeProjekt.getChangeProjekt(projectVerifyId, modelChange.getName(), modelChange.getNameIndicator(), modelChange.getDescription(), modelChange.getDescriptionIndicator()
                        , artifactDBId);

                SQLVerifyObject change = projectChanges.get(0);
                verifyTable = createVerifyTable(SegmentType.Change, modelChange.getAlias(), -1, -1, -1, modelChange.getId(), modelChange.isExist(), change.isExist(), change.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Iteration v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param iterations seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addIterationTOVerifyTable(List<Iteration> iterations) {

        ArrayList<SQLVerifyObject> projectIterations = new ArrayList<>();
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            IterationDAO databazeProjekt = new IterationDAO(this);
            ConfigurationDAO configurationDAO = new ConfigurationDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            ChangeDAO changeDAO = new ChangeDAO(this);
            BranchDAO branchDAO = new BranchDAO(this);
            CommitedConfigurationDAO commitedConfigurationDAO = new CommitedConfigurationDAO(this);
            ArtifactDAO artifactDAO = new ArtifactDAO(this);
            CommitDAO commitDAO = new CommitDAO(this);
            CPRDAO cprdao = new CPRDAO(this);
            VCSTagDAO vcsTagDAO = new VCSTagDAO(this);
            for (Iteration modelIteration : iterations) {

                ArrayList<Integer> configDBId = SQLAtributeCreator.createConfigurationAttribute(modelIteration.getConfiguration(), verifyDataModel,
                        configurationDAO, personDAO, roleTypeDAO, changeDAO, branchDAO, vcsTagDAO, cprdao, artifactDAO, commitedConfigurationDAO, commitDAO, projectVerifyId);


                projectIterations = databazeProjekt.getIterationyProjekt(projectVerifyId, modelIteration.getName(), modelIteration.getNameIndicator(), modelIteration.getDescription(), modelIteration.getDescriptionIndicator(), modelIteration.getEndDate(), modelIteration.getStartDate(),
                        modelIteration.getEndDateIndicator(), modelIteration.getStartDateIndicator(), configDBId);

                SQLVerifyObject iteration = projectIterations.get(0);
                verifyTable = createVerifyTable(SegmentType.Iteration, modelIteration.getAlias(), -1, -1, -1, modelIteration.getId(), modelIteration.isExist(), iteration.isExist(), iteration.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    private ArrayList<VerifyTable> addProjectTOVerifyTable(Project modelProject) {

        ArrayList<SQLVerifyObject> projectProjects = new ArrayList<>();
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            ProjectDAO databazeProjekt = new ProjectDAO(this);


            projectProjects = databazeProjekt.getProject(projectVerifyId, modelProject.getName(), modelProject.getNameIndicator(), modelProject.getDescription(),
                    modelProject.getDescriptionIndicator(), modelProject.getEndDate(), modelProject.getStartDate(),
                    modelProject.getEndDateIndicator(), modelProject.getStartDateIndicator());

            SQLVerifyObject project = projectProjects.get(0);
            verifyTable = createVerifyTable(SegmentType.Project, "Project", -1, -1, -1, projectVerifyId, true, project.isExist(), project.getSqlCommand());
            verifyTables.add(verifyTable);
        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Status v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param statuss seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addStatusTOVerifyTable(List<Status> statuss) {

        ArrayList<SQLVerifyObject> projectStatuss;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Status modelStatus : statuss) {
                projectStatuss = databazeProjekt.getStatusProjekt(projectVerifyId, modelStatus.getName(), modelStatus.getNameIndicator(),
                        modelStatus.getStatusClassIndex(), modelStatus.getStatusSuperClassIndex());

                SQLVerifyObject status = projectStatuss.get(0);
                verifyTable = createVerifyTable(SegmentType.Status, modelStatus.getAlias(), -1, -1, -1, modelStatus.getId(), modelStatus.isExist(), status.isExist(), status.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Relation v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param relations seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addRelationTOVerifyTable(List<Relation> relations) {

        ArrayList<SQLVerifyObject> projectRelations;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Relation modelRelation : relations) {
                projectRelations = databazeProjekt.getRelationProjekt(projectVerifyId, modelRelation.getName(), modelRelation.getNameIndicator(),
                        modelRelation.getRelationClassIndex(), modelRelation.getRelationSuperClassIndex());

                SQLVerifyObject relation = projectRelations.get(0);
                verifyTable = createVerifyTable(SegmentType.Relation, modelRelation.getAlias(), -1, -1, -1, modelRelation.getId(), modelRelation.isExist(), relation.isExist(), relation.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Resolution v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param resolutions seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addResolutionTOVerifyTable(List<Resolution> resolutions) {

        ArrayList<SQLVerifyObject> projectResolutions;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Resolution modelResolution : resolutions) {
                projectResolutions = databazeProjekt.getResolutionProjekt(projectVerifyId, modelResolution.getName(), modelResolution.getNameIndicator(),
                        modelResolution.getResolutionClassIndex(), modelResolution.getResolutionSuperClassIndex());

                SQLVerifyObject resolution = projectResolutions.get(0);
                verifyTable = createVerifyTable(SegmentType.Resolution, modelResolution.getAlias(), -1, -1, -1, modelResolution.getId(), modelResolution.isExist(), resolution.isExist(), resolution.getSqlCommand());
                verifyTables.add(verifyTable);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Type v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param types seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addTypeTOVerifyTable(List<Type> types) {

        ArrayList<SQLVerifyObject> projectTypes;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitElementDAO databazeProjekt = new WorkUnitElementDAO(this);

            for (Type modelType : types) {
                projectTypes = databazeProjekt.getTypeProjekt(projectVerifyId, modelType.getName(), modelType.getNameIndicator(),
                        modelType.getTypeClassIndex(), modelType.getTypeSuperClassIndex());

                SQLVerifyObject type = projectTypes.get(0);
                verifyTable = createVerifyTable(SegmentType.Type, modelType.getAlias(), -1, -1, -1, modelType.getId(), modelType.isExist(), type.isExist(), type.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu VCSTag v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param tag seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addVCSTagTOVerifyTable(List<VCSTag> tag) {

        ArrayList<SQLVerifyObject> projectTypes;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {

            VCSTagDAO databazeProjekt = new VCSTagDAO(this);

            for (VCSTag modelTag : tag) {
                projectTypes = databazeProjekt.getVCSTagProjekt(projectVerifyId, modelTag.getName(), modelTag.getNameIndicator(),
                        modelTag.getDescription(), modelTag.getDescriptionIndicator());

                SQLVerifyObject type = projectTypes.get(0);
                verifyTable = createVerifyTable(SegmentType.VCSTag, modelTag.getAlias(), -1, -1, -1, modelTag.getId(), modelTag.isExist(), type.isExist(), type.getSqlCommand());
                verifyTables.add(verifyTable);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Work Unit v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param workUnits seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addWorkUnitTOVerifyTable(List<WorkUnit> workUnits) {

        ArrayList<SQLVerifyObject> projectWorkUnits;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            WorkUnitDAO databazeProjekt = new WorkUnitDAO(this);
            PersonDAO personDAO = new PersonDAO(this);
            RoleTypeDAO roleTypeDAO = new RoleTypeDAO(this);
            WorkUnitElementDAO workUnitElementDAO = new WorkUnitElementDAO(this);
            CategoryDAO categoryDAO = new CategoryDAO(this);
            for (WorkUnit modelWorkUnit : workUnits) {

                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(modelWorkUnit.getAuthorIndex(), verifyDataModel, personDAO, roleTypeDAO, projectVerifyId);
                ArrayList<Integer> priorityDBId = SQLAtributeCreator.createPriorityAttribute(modelWorkUnit.getPriorityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> severityDBId = SQLAtributeCreator.createSeverityAttribute(modelWorkUnit.getSeverityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> statusDBId = SQLAtributeCreator.createStatusAttribute(modelWorkUnit.getStatusIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> typeDBId = SQLAtributeCreator.createTypeAttribute(modelWorkUnit.getTypeIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> resolutionDBId = SQLAtributeCreator.createResolutionAttribute(modelWorkUnit.getResolutionIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> categoryDBId = SQLAtributeCreator.createCategoryAttribute(modelWorkUnit.getCategory(), modelWorkUnit.getCategoryIndicator(), verifyDataModel, categoryDAO, projectVerifyId);
                ArrayList<Integer> relationDBId = SQLAtributeCreator.createRelationAttribute(modelWorkUnit.getRelationIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> workUntiInRelationDBId = SQLAtributeCreator.createWorkUnitAttribute(modelWorkUnit.getWorkUnits(), verifyDataModel, databazeProjekt, workUnitElementDAO, personDAO, roleTypeDAO, categoryDAO, projectVerifyId);

                projectWorkUnits = databazeProjekt.getWorkUnitProjekt(projectVerifyId, modelWorkUnit.getName(), modelWorkUnit.getNameIndicator(), modelWorkUnit.getEstimatedTime(), modelWorkUnit.getProgress(), categoryDBId,
                        roleDBId, priorityDBId, severityDBId, resolutionDBId, statusDBId, typeDBId, relationDBId, workUntiInRelationDBId, modelWorkUnit.getCreated(), modelWorkUnit.getCreatedIndicator(), modelWorkUnit.getEstimatedTimeIndicator(), modelWorkUnit.getProgressIndicator());

                SQLVerifyObject workUnit = projectWorkUnits.get(0);
                verifyTable = createVerifyTable(SegmentType.Work_Unit, modelWorkUnit.getAlias(), -1, -1, -1, modelWorkUnit.getId(), modelWorkUnit.isExist(), workUnit.isExist(), workUnit.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro kontrolu elementu Milestone v konkretnim projektu
     * Metoda nejprve zavola DAO objekty pro ziskani ID konkretnich zavislych elementu
     * Nasledne zavola DAO elementu pro ziskani dat z Databaze
     *
     * @param milestones seznam instaci CommittedConfiguration
     * @return seznam instaci VerifyTable s vysledky
     */
    private ArrayList<VerifyTable> addMilestoneTOVerifyTable(List<Milestone> milestones) {

        ArrayList<SQLVerifyObject> projectMilestones;
        ArrayList<VerifyTable> verifyTables = new ArrayList<>();
        VerifyTable verifyTable = null;
        try {
            MilestoneDAO databazeProjekt = new MilestoneDAO(this);
            CriterionDAO criterionDAO = new CriterionDAO(this);

            for (Milestone modelMilestone : milestones) {

                ArrayList<Integer> criterionDBId = SQLAtributeCreator.createCriterionAttribute(modelMilestone.getCriteriaIndexs(), verifyDataModel, criterionDAO, projectVerifyId);

                projectMilestones = databazeProjekt.getMilestoneProjekt(projectVerifyId, modelMilestone.getName(), modelMilestone.getNameIndicator(), modelMilestone.getDescription(), modelMilestone.getDescriptionIndicator(),
                        criterionDBId);

                SQLVerifyObject milestone = projectMilestones.get(0);
                verifyTable = createVerifyTable(SegmentType.Milestone, modelMilestone.getAlias(), -1, -1, -1, modelMilestone.getId(), modelMilestone.isExist(), milestone.isExist(), milestone.getSqlCommand());
                verifyTables.add(verifyTable);
            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return verifyTables;
    }

    /**
     * Metoda pro vytvoreni nove instace SQLVerifyObject
     *
     * @param id    identifikator
     * @param sql   SQL dotaz
     * @param exist existence prvku
     * @return nova instace prvku
     */
    public SQLVerifyObject createSQLVerifyObject(int id, String sql, boolean exist) {
        return new SQLVerifyObject(id, sql, exist);
    }
}
