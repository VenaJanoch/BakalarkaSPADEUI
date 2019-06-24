package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 * @author Vaclav Janoch
 */
public class MilestoneDAO {
    private Connection pripojeni;               
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public MilestoneDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;   
        this.verifyController = verifyController;
    }

/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Milestone
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param description seznam s atributy description
* @param descriptionIndicator seznam s indexi ukazatelu rovnosti
* @param criterions identifikatory zavislych Criterion
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getMilestoneProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                          List<Integer> descriptionIndicator, List<Integer> criterions) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("mc.criterionId", criterions);
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(criterions);

        String sql = "SELECT p.id FROM milestone p join milestone_criterion mc on mc.milestoneId = p.id " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

}
