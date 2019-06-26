package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 *
 * @author Vaclav Janoch
 */
public class BranchDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public BranchDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Branch
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name seznam s atributy name
     * @param nameIndicator seznam s indexi ukazatelu rovnosti
     * @param isMain informace o main vetve
     **/
    public ArrayList<SQLVerifyObject> getBranchyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, boolean isMain) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createBooleanAttribute("isMain", isMain);


        String sql = "SELECT b.id FROM branch b join configuration_branch cb on b.id = cb.branchId" +
                " join configuration c on c.id = cb.configurationId " + atributeSection + " AND c.projectId = ?";

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, new ArrayList<>());

    }
}
