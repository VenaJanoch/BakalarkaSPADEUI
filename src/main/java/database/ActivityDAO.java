package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze
 * @author Vaclav Janoch
 */
public class ActivityDAO {
    private Connection connection;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public ActivityDAO(VerifyController verifyController) {
        this.connection = Constans.CONNECTION;
        this.verifyController = verifyController;
    }


/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Activity
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param  projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param description seznam s atributy description
* @param descriptionIndicator seznam s indexi ukazatelu rovnosti
* @param endDate seznam s datumy ukonceni
* @param endDateIndicator seznam s indexi ukazatelu rovnosti
* @param workUnits identifikatory zavislych WorkUnitu
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getActivityFormProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                             List<String> description, List<Integer> descriptionIndicator,
                                                             List<XMLGregorianCalendar> endDate, List<Integer> endDateIndicator, List<Integer> workUnits) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("a.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("a.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);

        String sql = "SELECT a.id FROM activity a join work_unit wu on wu.activityId = a.id AND superProjectId = ? " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, projectVerifyId, new ArrayList<>());

    }
}
