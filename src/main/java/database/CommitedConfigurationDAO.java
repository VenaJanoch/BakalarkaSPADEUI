package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICommitedConfigurationDAO
 */
public class CommitedConfigurationDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public CommitedConfigurationDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


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
