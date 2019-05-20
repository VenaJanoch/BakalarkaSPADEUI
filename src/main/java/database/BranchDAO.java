package database;

import SPADEPAC.Branch;
import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IBranchDAO
 */
public class BranchDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public BranchDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }


    public ArrayList<SQLVerifyObject> getBranchyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, boolean isMain) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createBooleanAttribute("isMain", isMain);


        String sql = "SELECT b.id FROM branch b join configuration_branch cb on b.id = cb.branchId" +
                " join configuration c on c.id = cb.configurationId " + atributeSection + " AND c.projectId = ?";

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, new ArrayList<>());

    }
}
