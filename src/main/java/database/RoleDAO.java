package database;

import SPADEPAC.Activity;
import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IActivityDAO
 */
public class RoleDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public RoleDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


    /**
     * Vrací seznam artefaktů patřících osobě s id v parametru
     *
     * @param idOsoby id osoby pro výběr artefaktů
     * @return seznam artefaktů
     */
    public ArrayList<Activity> getRoleOsoba(int idOsoby) {
        return getRoleOsoba(idOsoby);
    }


    public ArrayList<SQLVerifyObject> getRoleProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, List<Integer> classId, List superClassId) {
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("role_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicators);
        String sql = "SELECT p.id FROM role p " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
}
