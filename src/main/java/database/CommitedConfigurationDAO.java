package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní
 *
 * @author Vaclav Janoch
 */
public class CommitedConfigurationDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public CommitedConfigurationDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Committed Configuration
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name seznam s atributy name
     * @param nameIndicator seznam s indexi ukazatelu rovnosti
     * @param description seznam s atributy description
     * @param descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @param createdDate seznam s datumy vytvoreni
     * @param createdDateIndicator seznam s indexi ukazatelu rovnosti
     * @param commitedDay seznam s datumy commitu
     * @param commitedDayIndicator seznam s indexi ukazatelu rovnosti
     * @param personIds identifikatory zavislych Person
     * @param commitId identifikatory zaviskych Commit
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getCommitedConfigurationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                                      List<XMLGregorianCalendar> commitedDay, List<Integer> commitedDayIndicator, List<String> description,
                                                                      List<Integer> descriptionIndicator, List<XMLGregorianCalendar> createdDate,
                                                                      List<Integer> createdDateIndicator, List<Integer> commitId, List<Integer> personIds) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("p.committed", commitedDay, commitedDayIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", createdDate, createdDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wir.rightItemId", commitId);
        atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", personIds);

        String sql = "SELECT wi.id FROM work_item wi join committed_configuration p on p.id = wi.id join work_item_relation wir on wir.leftItemId = wi.id " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(commitId);
        paramIds.add(personIds);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);
    }
}
