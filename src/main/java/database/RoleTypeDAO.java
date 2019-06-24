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
public class RoleTypeDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public RoleTypeDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Role
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name seznam s atributy name
     * @param nameIndicators seznam s indexi ukazatelu rovnosti
     * @param classId seznam identifikatory trid
     * @param superClassId seznam s identifikatory super trid
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
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
