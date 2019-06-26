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
public class ChangeDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public ChangeDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Change
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @ projectVerifyId identifikator zvoleneho projektu
     * @ name seznam s atributy name
     * @ nameIndicator seznam s indexi ukazatelu rovnosti
     * @ description seznam s atributy description
     * @ descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @ artifactIds identifikatory zavislych Artifaktu
     * @ return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getChangeProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                       List<Integer> descriptionIndicator, List<Integer> artifactIds) {

        String atributeSection = "1";
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("p.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("p.workItemId", artifactIds);

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(artifactIds);

        String sql = "SELECT fch.id FROM work_item_change p join field_change fch on fch.workItemChangeId = p.id AND " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);
    }
}
