package database;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import controllers.VerifyController;
import services.Constans;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IIterationDAO
 */
public class IterationDAO {
    private Connection connection;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public IterationDAO(VerifyController verifyController) {
        this.connection = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


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
