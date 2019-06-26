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
public class CPRDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public CPRDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Configuration Person
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name seznam s atributy name
     * @param nameIndicator seznam s indexi ukazatelu rovnosti
     * @param description seznam s atributy description
     * @param descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @param personIds identifikatory zavislych Person
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getCPRProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                    List<Integer> descriptionIndicator, List<Integer> personIds) {

        String atributeSection = "1";
        atributeSection += SQLAtributeCreator.createStringAttribute("c.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("c.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("c.personId", personIds);

        String sql = "SELECT c.id FROM configuration_person_relation c WHERE " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(personIds);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

}
