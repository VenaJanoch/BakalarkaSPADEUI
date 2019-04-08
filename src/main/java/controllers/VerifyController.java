package controllers;

import SPADEPAC.*;
import database.*;
import graphics.VerifyWindow;
import model.DataModel;
import services.Constans;
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

        return verifyTables;
    }

    private VerifyTable createVerifyTable(String name, int id, boolean isExist, boolean isExistInProject, String sql){

        String projectExist = "YES";
        String result = Constans.OK_VERIFY_RESULT;

        if (isExist && !isExistInProject){
            projectExist = "NO";
            result = Constans.BAD_VERIFY_RESULT;
        }else if ((!isExist && isExistInProject)){
            result = Constans.BAD_VERIFY_RESULT;
        }

        return new VerifyTable( name + id, id, isExist, projectExist, result, sql);
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
                verifyTable = createVerifyTable("Phase:", modelPhase.getId(), modelPhase.isExist(), phase.isExist(), phase.getSqlCommand());

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
                verifyTable = createVerifyTable("Criterion:", modelCriterion.getId(), modelCriterion.isExist(), criterion.isExist(), criterion.getSqlCommand());

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
                verifyTable = createVerifyTable("Activity:", modelActivity.getId(), modelActivity.isExist(), activity.isExist(), activity.getSqlCommand());

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
                verifyTable = createVerifyTable("Branch: ", modelBranch.getId(), modelBranch.isExist(), branch.isExist(), branch.getSqlCommand());

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
                verifyTable = createVerifyTable("Severity:", modelSeverity.getId(), modelSeverity.isExist(), severity.isExist(), severity.getSqlCommand());

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
                verifyTable = createVerifyTable("RoleType:", modelRoleType.getId(), modelRoleType.isExist(), severity.isExist(), severity.getSqlCommand());

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
                verifyTable = createVerifyTable("Priority:", modelPriority.getId(), modelPriority.isExist(), priority.isExist(), priority.getSqlCommand());

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

            for (Person modelPerson : persons){
                projectPersons = databazeProjekt.getPersonyProjekt(projectVerifyId, modelPerson.getName(), modelPerson.getNameIndicator());

                SQLVerifyObject person = projectPersons.get(0);
                verifyTable = createVerifyTable("Person:", modelPerson.getId(), modelPerson.isExist(), person.isExist(), person.getSqlCommand());

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
                verifyTable = createVerifyTable("Iteration:", modelIteration.getId(), modelIteration.isExist(), iteration.isExist(), iteration.getSqlCommand());

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
                verifyTable = createVerifyTable("Status:", modelStatus.getId(), modelStatus.isExist(), status.isExist(), status.getSqlCommand());

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
                verifyTable = createVerifyTable("Relation:", modelRelation.getId(), modelRelation.isExist(), relation.isExist(), relation.getSqlCommand());

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
                verifyTable = createVerifyTable("Resolution:", modelResolution.getId(), modelResolution.isExist(), resolution.isExist(), resolution.getSqlCommand());

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
                verifyTable = createVerifyTable("Type:", modelType.getId(), modelType.isExist(), type.isExist(), type.getSqlCommand());

            }


        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }

        verifyTables.add(verifyTable);
        return verifyTables;
    }
    
//    private ArrayList<VerifyTable> addCriterionTOVerifyTable(List<Criterion> criterions) {
//
//        ArrayList<Criterio> projekty = new ArrayList<>();
//        try {
//            ProjektDAO databazeProjekt = new ProjektDAO();
//            projekty = databazeProjekt.getProjekt();
//        } catch (Exception e) {
//            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
//            e.printStackTrace();
//        }
//        return projekty;
//
//        return null;
//    }


    public SQLVerifyObject createSQLVerifyObject(int id, String sql, boolean exist) {
        return new SQLVerifyObject(id, sql, exist);
    }
}
