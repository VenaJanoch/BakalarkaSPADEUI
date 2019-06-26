package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 *
 * @author Vaclav Janoch
 */
public class CategoryDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public CategoryDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Category
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name seznam s atributy name
     * @param nameIndicator seznam s indexi ukazatelu rovnosti
     **/
    public ArrayList<SQLVerifyObject> getCategoryyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("c.name", name, nameIndicator);


        String sql = "SELECT c.id FROM category c  WHERE c.projectInstanceId = ? " + atributeSection;


        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, new ArrayList<>());

    }
}
