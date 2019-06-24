package database;

import SPADEPAC.BranchList;
import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 * @author Vaclav Janoch
 */
public class CommitDAO {
    private Connection pripojeni;                
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public CommitDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    
        this.verifyController = verifyController;
    }


/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Commit
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param description seznam s atributy description
* @param descriptionIndicator seznam s indexi ukazatelu rovnosti
* @param createdDate seznam s datumy vytvoreni
* @param createDateAttribute seznam s indexi ukazatelu rovnosti
* @param personIds identifikatory zavislych Person
* @param isRelease informace o tom zda je commit release
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getCommitProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, boolean isRelease,
                                                       List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> createdDate,
                                                       List<Integer> createdDateIndicator, List<Integer> personIds) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createBooleanAttribute("p.isRelease", isRelease);
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", createdDate, createdDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", personIds);

        String sql = "SELECT wi.id FROM work_item wi join commit p on p.id = wi.id" + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(personIds);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);
    }
}
