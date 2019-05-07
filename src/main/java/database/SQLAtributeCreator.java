package database;

import SPADEPAC.*;
import controllers.DataPreparer;
import controllers.VerifyController;
import model.DataModel;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLAtributeCreator {

    public static String createDateAttribute(String atribute, List<XMLGregorianCalendar> date, List<Integer> dateIndicator) {
        int i = 0;
        String atributeSection = "";
        for (XMLGregorianCalendar xmlDate : date) {
            atributeSection += " AND ";
            atributeSection += atribute + " ";
            atributeSection += String.valueOf(Constans.NUMBER_INDICATORS[dateIndicator.get(i)] + " ");
            atributeSection += "'" + DataPreparer.convertDateFromXML(xmlDate).toString() + "'";
            i++;
        }

        return atributeSection;
    }

    /**
     * Spustí zadaný skript a naplní seznam artefaktů
     *
     * @param sql skript pro spuštění
     * @param id  identifikátor výběru
     * @return seznam artefaktů
     */
    public static ArrayList<SQLVerifyObject> findInstanceInDB(Connection pripojeni, VerifyController verifyController, String sql, int id,
                                                              ArrayList<List<Integer>> ids) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<SQLVerifyObject> list = new ArrayList<SQLVerifyObject>();
        try {
            stmt = pripojeni.prepareStatement(sql);
            int i = 1;
            if (id != -1) {
                stmt.setInt(1, id);
                i++;
            }

            for (List<Integer> identificators : ids) {
                for (int j : identificators) {
                    stmt.setInt(i, j);
                    i++;
                }
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(verifyController.createSQLVerifyObject(rs.getInt("id"), stmt.toString().substring(48), true));
            }

            if (list.size() == 0) {
                SQLVerifyObject verifyObject = verifyController.createSQLVerifyObject(-1, sql, false);
                list.add(verifyObject);
            }

        } catch (SQLException e) {
            //	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaScriptPhase"));
            e.printStackTrace();
        } catch (Exception e) {
            //	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaDataPhase"));
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static String createStringAttribute(String atributName, List<String> name1, List<Integer> nameIndicator) {

        String atributeSection = "";
        if (nameIndicator.size() != 0) {
            int i = 0;
            for (String namel : name1) {
                int indicator = nameIndicator.get(i);
                atributeSection += " AND ";
                atributeSection += atributName + " ";
                atributeSection += String.valueOf(Constans.INDICATORS[indicator] + " ");

                if (indicator > 1) {
                    atributeSection += "'%" + namel + "%'";
                } else {

                    atributeSection += "'" + namel + "'";
                }
                i++;
            }
        }

        return atributeSection;
    }

    public static String createBooleanAttribute(String atributName, boolean isMain) {

        String atributeSection = "";
        atributeSection += " AND ";
        atributeSection += atributName;
        int main = 1;
        if (!isMain) {
            main = 0;
        }

        atributeSection += "=" + String.valueOf(main) + " ";

        return atributeSection;

    }

    public static String createClassAttribute(String element_classification, List classId, int id) {

        String atributeSection = "";
        if (classId.size() != 0) {
            atributeSection += "join " + element_classification + " c on c.id = ? "; // + classId ;
            atributeSection += "AND p.projectInstanceId = ?";
        }else {
            atributeSection += "WHERE p.projectInstanceId = ?";
        }


        return atributeSection;
    }

    public static String createBranchAttribute(String elementName, ArrayList<Integer> branches) {
        String atributeSection = "";
        for (int branchtId : branches) {
            atributeSection += " AND ";
            atributeSection += elementName + " = " + branchtId;
        }
        return atributeSection;
    }

    public static String createIntAttribute(String elementName, List<Integer> attributsId) {
        String atributeSection = "";
        for (int id : attributsId) {
            atributeSection += " AND ";
            atributeSection += elementName + " = ?"; // + id;
        }
        return atributeSection;
    }

    public static String createIdAttribute(String elementName, List<Integer> roleIds) {

        String atributeSection = "";
        for (int roleId : roleIds) {
            atributeSection += " AND ";
            atributeSection += elementName + " = ?"; // +  roleId;
        }
        return atributeSection + " ";
    }

    public static String[] createArtifactAttribute(String elementName, List<Integer> artifactIds) {
        String atributeSection = "";
        String atributeSectionText = "";
        for (int artifactId : artifactIds) {
            atributeSection += " AND ";
            atributeSection += elementName + " = ?"; //+ artifactId;
            atributeSectionText += " AND ";
            atributeSectionText += elementName + " = " + artifactId;
        }
        return new String[]{atributeSection, atributeSectionText};
    }

    public static String createDoubleAttribute(String elementName, List<Double> estimateTimes) {
        String atributeSection = "";
        for (double estimateTime : estimateTimes) {
            atributeSection += " AND ";
            atributeSection += elementName + " = " + estimateTime;
        }
        return atributeSection + " ";
    }


    public static ArrayList<Integer> createPersonAttribute(List<Integer> authorIndex, DataModel verifyDataModel, PersonDAO personDAO, RoleDAO roleDAO, int projectVerifyId) {
        ArrayList<Integer> roleDBId = new ArrayList<>();
        SQLVerifyObject roleArtifacts;
        for (int i : authorIndex) {
            Person person = verifyDataModel.getPerson(verifyDataModel.getRoleId(i));
            ArrayList<Integer> roleTypeDBId = SQLAtributeCreator.createRoleTypeAttribute(person.getType(), verifyDataModel, roleDAO, projectVerifyId);

            roleArtifacts = personDAO.getPersonProjekt(projectVerifyId, person.getName(), person.getNameIndicator(), roleTypeDBId).get(0);
            if (roleArtifacts != null) {
                roleDBId.add(roleArtifacts.getId());
            }
        }

        return roleDBId;
    }

    public static ArrayList<Integer> createCommittedAttribute(List<Integer> commitedConfiguration, DataModel verifyDataModel, CommitedConfigurationDAO commitedConfigurationDAO, CommitDAO commitDAO,
                                                              PersonDAO personDAO, RoleDAO roleDAO, int projectVerifyId) {
        ArrayList<Integer> committedDBId = new ArrayList<>();
        SQLVerifyObject projectCommitted;

        for (int i : commitedConfiguration) {
            CommitedConfiguration commitedConfiguration1 = verifyDataModel.getCommitedConfiguration(verifyDataModel.getCommitedConfigurationId(i));
            ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(commitedConfiguration1.getRole(), verifyDataModel, personDAO, roleDAO, projectVerifyId);
            ArrayList<Integer> commitDBId = SQLAtributeCreator.createCommitAttribute(commitedConfiguration1.getCommit(), verifyDataModel, commitDAO, personDAO, roleDAO, projectVerifyId);

            projectCommitted = commitedConfigurationDAO.getCommitedConfigurationProjekt(projectVerifyId, commitedConfiguration1.getName(), commitedConfiguration1.getNameIndicator(),
                    commitedConfiguration1.getCommitedDay(), commitedConfiguration1.getCommitedDayIndicator(),
                    commitedConfiguration1.getDescription(), commitedConfiguration1.getDescriptionIndicator(), commitedConfiguration1.getCreated(),
                    commitedConfiguration1.getCreatedIndicator(), commitDBId, roleDBId).get(0);


            if (projectCommitted != null) {
                committedDBId.add(projectCommitted.getId());
            }
        }
        return committedDBId;

    }


    public static ArrayList<Integer> createChangeAttribute(List<ChangeList> changesIndexs, DataModel verifyDataModel, ChangeDAO changeDAO, ArtifactDAO artifactDAO, PersonDAO personDAO, RoleDAO roleDAO, int projectVerifyId) {
        ArrayList<Integer> roleDBId = new ArrayList<>();
        SQLVerifyObject projectChanges;
        for (ChangeList changelist : changesIndexs)
            for (int i : changelist.getChanges()) {
                Change change = verifyDataModel.getChange(verifyDataModel.getChangeId(i));
                ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(change.getArtifactIndex(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleDAO);

                projectChanges = changeDAO.getChangeProjekt(projectVerifyId, change.getName(), change.getNameIndicator(), change.getDescription(), change.getDescriptionIndicator()
                        , artifactDBId).get(0);


                if (projectChanges != null) {
                    roleDBId.add(projectChanges.getId());
                }
            }
        return roleDBId;
    }

    public static ArrayList<Integer> createBranchAttribute(List<BranchList> branchsIndexs, DataModel verifyDataModel, BranchDAO branchDAO, ArtifactDAO artifactDAO, PersonDAO personDAO, RoleDAO roleDAO, int projectVerifyId) {
        ArrayList<Integer> roleDBId = new ArrayList<>();
        SQLVerifyObject projectBranchs;
        for (BranchList branchlist : branchsIndexs)
            for (int i : branchlist.getBranches()) {
                Branch branch = verifyDataModel.getBranch(verifyDataModel.getBranchId(i));

                projectBranchs = branchDAO.getBranchyProjekt(projectVerifyId, branch.getName(), branch.getNameIndicator(), branch.isIsMain()).get(0);


                if (projectBranchs != null) {
                    roleDBId.add(projectBranchs.getId());
                }
            }
        return roleDBId;
    }

    public static ArrayList<Integer> createRoleTypeAttribute(List<Integer> roleIndex, DataModel verifyDataModel, RoleDAO roleDAO, int projectVerifyId) {
        ArrayList<Integer> roleDBId = new ArrayList<>();
        SQLVerifyObject roleArtifacts;
        for (int i : roleIndex) {
            RoleType roleType = verifyDataModel.getRoleType(verifyDataModel.getRoleTypeId(i));
            roleArtifacts = roleDAO.getRoleProjekt(projectVerifyId, roleType.getName(), roleType.getNameIndicator(), roleType.getRoleTypeClassIndex(), roleType.getRoleTypeSuperClassIndex()).get(0);
            if (roleArtifacts != null) {
                roleDBId.add(roleArtifacts.getId());
            }
        }

        return roleDBId;
    }

    public static ArrayList<Integer> createPriorityAttribute(List<Integer> priorityIndex, DataModel verifyDataModel, WorkUnitElementDAO priorityDAO, int projectVerifyId) {
        ArrayList<Integer> priorityDBId = new ArrayList<>();
        SQLVerifyObject priorityArtifacts;
        for (int i : priorityIndex) {
            Priority priority = verifyDataModel.getPriority(verifyDataModel.getPriorityId(i));
            priorityArtifacts = priorityDAO.getPriorityProjekt(projectVerifyId, priority.getName(), priority.getNameIndicator(), priority.getPriorityClassIndex(), priority.getPrioritySuperClassIndex()).get(0);
            if (priorityArtifacts != null) {
                priorityDBId.add(priorityArtifacts.getId());
            }
        }
        return priorityDBId;
    }

    public static ArrayList<Integer> createSeverityAttribute(List<Integer> severityIndex, DataModel verifyDataModel, WorkUnitElementDAO severityDAO, int projectVerifyId) {
        ArrayList<Integer> severityDBId = new ArrayList<>();
        SQLVerifyObject severityArtifacts;
        for (int i : severityIndex) {
            Severity severity = verifyDataModel.getSeverity(verifyDataModel.getSeverityId(i));
            severityArtifacts = severityDAO.getSeverityProjekt(projectVerifyId, severity.getName(), severity.getNameIndicator(), severity.getSeverityClassIndex(), severity.getSeveritySuperClassIndex()).get(0);
            if (severityArtifacts != null) {
                severityDBId.add(severityArtifacts.getId());
            }
        }
        return severityDBId;
    }

    public static ArrayList<Integer> createStatusAttribute(List<Integer> statusIndex, DataModel verifyDataModel, WorkUnitElementDAO statusDAO, int projectVerifyId) {
        ArrayList<Integer> statusDBId = new ArrayList<>();
        SQLVerifyObject statusArtifacts;
        for (int i : statusIndex) {
            Status status = verifyDataModel.getStatus(verifyDataModel.getStatusId(i));
            statusArtifacts = statusDAO.getStatusProjekt(projectVerifyId, status.getName(), status.getNameIndicator(), status.getStatusClassIndex(), status.getStatusSuperClassIndex()).get(0);
            if (statusArtifacts != null) {
                statusDBId.add(statusArtifacts.getId());
            }
        }
        return statusDBId;
    }

    public static ArrayList<Integer> createTypeAttribute(List<Integer> typeIndex, DataModel verifyDataModel, WorkUnitElementDAO typeDAO, int projectVerifyId) {
        ArrayList<Integer> typeDBId = new ArrayList<>();
        SQLVerifyObject typeArtifacts;
        for (int i : typeIndex) {
            Type type = verifyDataModel.getType(verifyDataModel.getTypeId(i));
            typeArtifacts = typeDAO.getTypeProjekt(projectVerifyId, type.getName(), type.getNameIndicator(), type.getTypeClassIndex(), type.getTypeSuperClassIndex()).get(0);
            if (typeArtifacts != null) {
                typeDBId.add(typeArtifacts.getId());
            }
        }
        return typeDBId;
    }

    public static ArrayList<Integer> createResolutionAttribute(List<Integer> resolutionIndex, DataModel verifyDataModel, WorkUnitElementDAO resolutionDAO, int projectVerifyId) {
        ArrayList<Integer> resolutionDBId = new ArrayList<>();
        SQLVerifyObject resolutionArtifacts;
        for (int i : resolutionIndex) {
            Resolution resolution = verifyDataModel.getResolution(verifyDataModel.getResolutionId(i));
            resolutionArtifacts = resolutionDAO.getResolutionProjekt(projectVerifyId, resolution.getName(), resolution.getNameIndicator(), resolution.getResolutionClassIndex(), resolution.getResolutionSuperClassIndex()).get(0);
            if (resolutionArtifacts != null) {
                resolutionDBId.add(resolutionArtifacts.getId());
            }
        }
        return resolutionDBId;
    }

    public static ArrayList<Integer> createRelationAttribute(List<Integer> relationIndex, DataModel verifyDataModel, WorkUnitElementDAO relationDAO, int projectVerifyId) {
        ArrayList<Integer> relationDBId = new ArrayList<>();
        SQLVerifyObject relationArtifacts;
        for (int i : relationIndex) {
            Relation relation = verifyDataModel.getRelation(verifyDataModel.getRelationId(i));
            relationArtifacts = relationDAO.getRelationProjekt(projectVerifyId, relation.getName(), relation.getNameIndicator(), relation.getRelationClassIndex(), relation.getRelationSuperClassIndex()).get(0);
            if (relationArtifacts != null) {
                relationDBId.add(relationArtifacts.getId());
            }
        }
        return relationDBId;
    }

    public static ArrayList<Integer> createCategoryAttribute(List<String> category, List<Integer> categoryIndicator, DataModel verifyDataModel, CategoryDAO categoryDAO, int projectVerifyId) {
        ArrayList<Integer> categoryDBId = new ArrayList<>();
        SQLVerifyObject categoryArtifacts;

        categoryArtifacts = categoryDAO.getCategoryyProjekt(projectVerifyId, category, categoryIndicator).get(0);
        if (categoryArtifacts != null) {
            categoryDBId.add(categoryArtifacts.getId());
        }

        return categoryDBId;
    }

    public static ArrayList<Integer> createCriterionAttribute(List<CriterionList> criteriaIndexs, DataModel verifyDataModel, CriterionDAO criterionDAO, int projectVerifyId) {
        ArrayList<Integer> criterionDBId = new ArrayList<>();
        SQLVerifyObject criterionArtifacts;
        for (CriterionList list : criteriaIndexs) {
            for (int criterionIndex : list.getCriterions()) {
                Criterion criterion = verifyDataModel.getCriterion(verifyDataModel.getCriterionId(criterionIndex));
                criterionArtifacts = criterionDAO.getCriterionProjekt(projectVerifyId, criterion.getName(),
                        criterion.getNameIndicator(), criterion.getDescription(), criterion.getDescriptionIndicator()).get(0);
                if (criterionArtifacts != null) {
                    criterionDBId.add(criterionArtifacts.getId());
                }
            }
        }
        return criterionDBId;
    }

    public static ArrayList<Integer> createWorkUnitAttribute(List<WorkUnitList> workUnits, DataModel verifyDataModel, WorkUnitDAO workUnitDAO, WorkUnitElementDAO workUnitElementDAO,
                                                             PersonDAO personDAO, RoleDAO roleDAO, CategoryDAO categoryDAO, int projectVerifyId) {
        ArrayList<Integer> workUnitDBId = new ArrayList<>();
        SQLVerifyObject workUnitArtifacts;
        for (WorkUnitList list : workUnits) {
            for (int workUnitIndex : list.getWorkUnits()) {
                WorkUnit workUnit = verifyDataModel.getWorkUnit(verifyDataModel.getWorkUnitId(workUnitIndex));
                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(workUnit.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);
                ArrayList<Integer> priorityDBId = SQLAtributeCreator.createPriorityAttribute(workUnit.getPriorityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> severityDBId = SQLAtributeCreator.createSeverityAttribute(workUnit.getSeverityIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> statusDBId = SQLAtributeCreator.createStatusAttribute(workUnit.getStatusIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> typeDBId = SQLAtributeCreator.createTypeAttribute(workUnit.getTypeIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> resolutionDBId = SQLAtributeCreator.createResolutionAttribute(workUnit.getResolutionIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);
                ArrayList<Integer> categoryDBId = SQLAtributeCreator.createCategoryAttribute(workUnit.getCategory(), workUnit.getCategoryIndicator(), verifyDataModel, categoryDAO, projectVerifyId);
                ArrayList<Integer> relationDBId = SQLAtributeCreator.createRelationAttribute(workUnit.getRelationIndex(), verifyDataModel, workUnitElementDAO, projectVerifyId);

                workUnitArtifacts = workUnitDAO.getWorkUnitProjekt(projectVerifyId, workUnit.getName(), workUnit.getNameIndicator(), workUnit.getEstimatedTime(), categoryDBId,
                        roleDBId, priorityDBId, severityDBId, resolutionDBId, statusDBId, typeDBId, relationDBId, new ArrayList<>(), workUnit.getCreated(), workUnit.getCreatedIndicator()).get(0);

                if (workUnitArtifacts != null) {
                    workUnitDBId.add(workUnitArtifacts.getId());
                }
            }
        }
        return workUnitDBId;
    }


    public static ArrayList<Integer> createArtifactAttribute(List<Integer> artifactIndex, DataModel verifyDataModel, ArtifactDAO artifactDAO,
                                                             int projectVerifyId, PersonDAO personDAO, RoleDAO roleDAO) {
        SQLVerifyObject artifactChanges;
        ArrayList<Integer> artifactDBId = new ArrayList<>();
        for (int i : artifactIndex) {
            Artifact artifact = verifyDataModel.getArtifact(verifyDataModel.getArtifactId(i));
            ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(artifact.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);

            artifactChanges = artifactDAO.getArtifactProjekt(projectVerifyId, artifact.getName(), artifact.getNameIndicator(),
                    artifact.getDescription(), artifact.getDescriptionIndicator(), artifact.getCreated(), artifact.getCreatedIndicator(), roleDBId, artifact.getMimeType(), artifact.getMimeTypeIndicator()).get(0);
            if (artifactChanges != null) {
                artifactDBId.add(artifactChanges.getId());
            }
        }
        return artifactDBId;
    }

    public static ArrayList<Integer> createVCSTagAttribute(List<Integer> vcsTagIndex, DataModel verifyDataModel, VCSTagDAO vcsTagDAO, int projectVerifyId) {
        SQLVerifyObject vcsTagChanges;
        ArrayList<Integer> vcsTagDBId = new ArrayList<>();
        for (int i : vcsTagIndex) {
            VCSTag vcsTag = verifyDataModel.getVCSTag(verifyDataModel.getTagId(i));
            vcsTagChanges = vcsTagDAO.getVCSTagProjekt(projectVerifyId, vcsTag.getName(), vcsTag.getNameIndicator(),
                    vcsTag.getDescription(), vcsTag.getDescriptionIndicator()).get(0);
            if (vcsTagChanges != null) {
                vcsTagDBId.add(vcsTagChanges.getId());
            }
        }
        return vcsTagDBId;
    }


    public static ArrayList<Integer> createCommitAttribute(List<Integer> commitIndexs, DataModel verifyDataModel, CommitDAO commitDAO, PersonDAO personDAO,
                                                           RoleDAO roleDAO, int projectVerifyId) {

        SQLVerifyObject commitCommitted;
        ArrayList<Integer> artifactDBId = new ArrayList<>();
        for (int i : commitIndexs) {
            Commit commit = verifyDataModel.getCommit(verifyDataModel.getCommitId(i));
            ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(commit.getAuthor(), verifyDataModel, personDAO, roleDAO, projectVerifyId);

            commitCommitted = commitDAO.getCommitProjekt(projectVerifyId, commit.getName(), commit.getNameIndicator(),
                    commit.isRelease(), commit.getDescription(), commit.getDescriptionIndicator(), commit.getCreated(),
                    commit.getCreatedIndicator(), roleDBId).get(0);
            if (commitCommitted != null) {
                artifactDBId.add(commitCommitted.getId());
            }
        }
        return artifactDBId;
    }

    public static ArrayList<Integer> createCPRAttribute(List<CPRSList> cprIndexs, DataModel verifyDataModel, CPRDAO cprDAO, PersonDAO personDAO,
                                                        RoleDAO roleDAO, int projectVerifyId) {

        SQLVerifyObject cprCPRted;
        ArrayList<Integer> cprDBId = new ArrayList<>();
        for (CPRSList cprsList : cprIndexs) {
            for (int i : cprsList.getCPRs()) {
                ConfigPersonRelation cpr = verifyDataModel.getConfigPersonRelation(verifyDataModel.getCPRId(i));
                ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(cpr.getPersonIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);

                cprCPRted = cprDAO.getCPRProjekt(projectVerifyId, cpr.getName(), cpr.getNameIndicator(), cpr.getDescription(), cpr.getDescriptionIndicator(), roleDBId).get(0);
                if (cprCPRted != null) {
                    cprDBId.add(cprCPRted.getId());
                }
            }
        }
        return cprDBId;
    }

    public static ArrayList<Integer> createMilestoneAttribute(List<Integer> milestoneIndex, DataModel verifyDataModel, MilestoneDAO milestoneDAO, CriterionDAO criterionDAO,
                                                              int projectVerifyId) {
        SQLVerifyObject milestoneChanges;
        ArrayList<Integer> milestoneDBId = new ArrayList<>();
        for (int i : milestoneIndex) {
            Milestone milestone = verifyDataModel.getMilestone(verifyDataModel.getMilestoneId(i));
            ArrayList<Integer> criterionDBId = SQLAtributeCreator.createCriterionAttribute(milestone.getCriteriaIndexs(), verifyDataModel, criterionDAO, projectVerifyId);

            milestoneChanges = milestoneDAO.getMilestoneProjekt(projectVerifyId, milestone.getName(), milestone.getNameIndicator(), milestone.getDescription(), milestone.getDescriptionIndicator(),
                    criterionDBId).get(0);

            if (milestoneChanges != null) {
                milestoneDBId.add(milestoneChanges.getId());
            }
        }
        return milestoneDBId;
    }

    public static ArrayList<Integer> createConfigurationAttribute(List<Integer> configurationIndex, DataModel verifyDataModel, ConfigurationDAO configurationDAO, PersonDAO personDAO, RoleDAO roleDAO,
                                                                  ChangeDAO changeDAO, BranchDAO branchDAO, VCSTagDAO vcsTagDAO, CPRDAO cprdao, ArtifactDAO artifactDAO, CommitedConfigurationDAO commitedConfigurationDAO, CommitDAO commitDAO,
                                                                  int projectVerifyId) {
        SQLVerifyObject configurationChanges;
        ArrayList<Integer> configurationDBId = new ArrayList<>();
        for (int i : configurationIndex) {
            Configuration configuration = verifyDataModel.getConfiguration(verifyDataModel.getConfigurationId(i));
            ArrayList<Integer> roleDBId = SQLAtributeCreator.createPersonAttribute(configuration.getAuthorIndex(), verifyDataModel, personDAO, roleDAO, projectVerifyId);
            ArrayList<Integer> changeDBId = SQLAtributeCreator.createChangeAttribute(configuration.getChangesIndexs(), verifyDataModel, changeDAO, artifactDAO, personDAO, roleDAO, projectVerifyId);
            ArrayList<Integer> artifactDBId = SQLAtributeCreator.createArtifactAttribute(configuration.getArtifactsIndexs(), verifyDataModel, artifactDAO, projectVerifyId, personDAO, roleDAO);
            ArrayList<Integer> committedConfigurationDBId = SQLAtributeCreator.createCommittedAttribute(configuration.getCommitedConfiguration(), verifyDataModel, commitedConfigurationDAO, commitDAO, personDAO, roleDAO, projectVerifyId);
            ArrayList<Integer> branchDBId = SQLAtributeCreator.createBranchAttribute(configuration.getBranchIndexs(), verifyDataModel, branchDAO, artifactDAO, personDAO, roleDAO, projectVerifyId);
            ArrayList<Integer> tagDBId = SQLAtributeCreator.createVCSTagAttribute(configuration.getTagIndex(), verifyDataModel, vcsTagDAO, projectVerifyId);
            ArrayList<Integer> cprDBId = SQLAtributeCreator.createCPRAttribute(configuration.getCPRsIndexs(), verifyDataModel, cprdao, personDAO, roleDAO, projectVerifyId);

            configurationChanges = configurationDAO.getConfigurationProjekt(projectVerifyId, configuration.getName(), configuration.getNameIndicator(),
                    configuration.getDescription(), configuration.getDescriptionIndicator(), configuration.getCreated(), configuration.getCreatedIndicator(),
                    changeDBId, branchDBId, cprDBId, tagDBId, committedConfigurationDBId, roleDBId, artifactDBId).get(0);


            if (configurationChanges != null) {
                configurationDBId.add(configurationChanges.getId());
            }
        }
        return configurationDBId;
    }

}