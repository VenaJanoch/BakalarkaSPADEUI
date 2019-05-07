package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IPriorityDAO
 */
public class WorkUnitElementDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public WorkUnitElementDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


    public ArrayList<SQLVerifyObject> getPriorityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("priority_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);

        String sql = "SELECT p.id FROM priority p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

    public ArrayList<SQLVerifyObject> getStatusProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List<Integer> classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicators);
        atributeSection += SQLAtributeCreator.createClassAttribute("status_classification", classId, projectVerifyId);
        String sql = "SELECT p.id FROM status p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

    public ArrayList<SQLVerifyObject> getSeverityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List classId, List superClassId) {

        String atributeSection = "";

        atributeSection += SQLAtributeCreator.createClassAttribute("severity_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);

        String sql = "SELECT p.id FROM severity p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

    public ArrayList<SQLVerifyObject> getTypeProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("wu_type_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);

        String sql = "SELECT p.id FROM wu_type p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

    public ArrayList<SQLVerifyObject> getRelationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("relation_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
        String sql = "SELECT p.id FROM relation p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

    public ArrayList<SQLVerifyObject> getResolutionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("resolution_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
        String sql = "SELECT p.id FROM resolution p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

}
