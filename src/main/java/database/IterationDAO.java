package database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import controllers.VerifyController;
import services.Constans;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 * @author Vaclav Janoch
 */
public class IterationDAO {
    private Connection connection;              
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public IterationDAO(VerifyController verifyController) {
        this.connection = Constans.CONNECTION;    
        this.verifyController = verifyController;
    }

 /**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Iteration
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param description seznam s atributy description
* @param descriptionIndicator seznam s indexi ukazatelu rovnosti
* @param startDate seznam s datumy vytvoreni
* @param startDateIndicator seznam s indexi ukazatelu rovnosti
* @param endDate seznam s datumy ukonceni
* @param endDate seznam s indexi ukazatelu rovnosti
* @param configId identifikatory zavislych Configuration
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getIterationyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                           List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate, List<XMLGregorianCalendar> startDate,
                                                           List<Integer> endDateIndicator, List<Integer> startDateIndicator, List<Integer> configId) {

        String atributeSection = "";
        int i = 0;
        atributeSection += SQLAtributeCreator.createStringAttribute("i.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("i.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("i.endDate", endDate, endDateIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("i.startDate", startDate, startDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("i.configurationId", configId);
        String sql = "SELECT i.id FROM iteration i join work_unit wu on wu.iterationId = i.id WHERE i.superProjectId = ? " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(configId);
        return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, projectVerifyId, paramIds);

    }
}
