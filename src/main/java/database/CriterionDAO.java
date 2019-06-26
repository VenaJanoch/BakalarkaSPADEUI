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
public class CriterionDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public CriterionDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Criterion
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId      identifikator zvoleneho projektu
     * @param name                 seznam s atributy name
     * @param nameIndicator        seznam s indexi ukazatelu rovnosti
     * @param description          seznam s atributy description
     * @param descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getCriterionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                          List<Integer> descriptionIndicator) {

        String atributeSection = "1";
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("description", description, descriptionIndicator);

        String sql = "SELECT c.id FROM criterion c WHERE " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, new ArrayList<>());

    }

}
