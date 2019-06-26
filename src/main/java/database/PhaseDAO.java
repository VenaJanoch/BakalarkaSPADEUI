package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 *
 * @author Vaclav Janoch
 */
public class PhaseDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public PhaseDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Phase
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId      identifikator zvoleneho projektu
     * @param name                 seznam s atributy name
     * @param nameIndicator        seznam s indexi ukazatelu rovnosti
     * @param description          seznam s atributy description
     * @param descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @param endDate              seznam s datumy ukonceni
     * @param endDateIndicator     seznam s indexi ukazatelu rovnosti
     * @param milestoneId          identifikatory zavislych Milestonu
     * @param configId             identifikatory zavislych Configuration
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getPhaseyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                       List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate,
                                                       List<Integer> endDateIndicator, List<Integer> milestoneId, List<Integer> configId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("i.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("i.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("i.milestoneId", milestoneId);
        atributeSection += SQLAtributeCreator.createIdAttribute("i.configurationId", configId);
        String sql = "SELECT i.id FROM phase i join work_unit wu on wu.phaseId = i.id WHERE i.superProjectId = ? " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(milestoneId);
        paramIds.add(configId);

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, paramIds);
    }


}
