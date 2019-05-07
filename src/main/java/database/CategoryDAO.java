package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICategoryDAO
 */
public class CategoryDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public CategoryDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }

    public ArrayList<SQLVerifyObject> getCategoryyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("c.name", name, nameIndicator);


        String sql = "SELECT c.id FROM category c  WHERE c.projectInstanceId = ? " + atributeSection;


        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, new ArrayList<>());

    }
}
