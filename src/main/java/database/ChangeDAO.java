package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IChangeDAO
 */
public class ChangeDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public ChangeDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


    public ArrayList<SQLVerifyObject> getChangeProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                       List<Integer> descriptionIndicator, List<Integer> artifactIds) {

        String atributeSection = "1";
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("p.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("p.workItemId", artifactIds);

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(artifactIds);

        String sql = "SELECT fch.id FROM work_item_change p join field_change fch on fch.workItemChangeId = p.id AND " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);
    }
}
