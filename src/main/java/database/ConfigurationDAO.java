package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IConfigurationDAO
 */
public class ConfigurationDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public ConfigurationDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


    public ArrayList<SQLVerifyObject> getConfigurationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description, List<Integer> descriptionIndicator,
                                                              List<XMLGregorianCalendar> createdDate, List<Integer> createdDateIndicator,
                                                              List<Integer> changeId, List<Integer> branchId, List<Integer> cprId, List<Integer> tagId, List<Integer> committedId, List<Integer> personIds, List<Integer> artifactIds) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", createdDate, createdDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", personIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("ch.changeId", changeId);
        atributeSection += SQLAtributeCreator.createIdAttribute("br.branchId", branchId);
        atributeSection += SQLAtributeCreator.createIdAttribute("cpr.id", cprId);
        atributeSection += SQLAtributeCreator.createIdAttribute("wir.rightItemId", artifactIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wir.rightItemId", committedId);

        String sql = "SELECT p.id FROM work_item wi join configuration p on p.id = wi.id " +
                "join configuration_change ch on ch.configurationId = p.id " +
                "join configuration_branch br on br.configurationId = p.id " +
                "join configuration_person_relation cpr on cpr.configurationId = p.id " +
                "join work_item_relation wir on wir.leftItemId = wi.id " +
                "AND p.projectId = ? " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(personIds);
        paramIds.add(changeId);
        paramIds.add(branchId);
        paramIds.add(cprId);
        paramIds.add(artifactIds);
        paramIds.add(committedId);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, paramIds);

    }
}
