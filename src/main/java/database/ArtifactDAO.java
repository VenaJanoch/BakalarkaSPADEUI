package database;

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
public class ArtifactDAO {
    private Connection connection;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public ArtifactDAO(VerifyController verifyController) {
        this.connection = Constans.CONNECTION;  
        this.verifyController = verifyController;
    }


/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Artifact
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param description seznam s atributy description
* @param descriptionIndicator seznam s indexi ukazatelu rovnosti
* @param endDate seznam s datumy ukonceni
* @param endDateIndicator seznam s indexi ukazatelu rovnosti
* @param type identifikatory vyctu Type
* @param typeIndicator seznam s indexi ukazatelu rovnosti
* @param roleIds identifikatory zavislych Person objektu
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getArtifactProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
                                                         List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate,
                                                         List<Integer> endDateIndicator, List<Integer> roleIds, List<String> type,
                                                         List<Integer> typeIndicator) {

        ArrayList<List<Integer>> paramIds = new ArrayList<>();

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("p.artifactClass", type, typeIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", endDate, endDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", roleIds);

        String roleJoin = "";
        if (roleIds.size() == 0){
            roleJoin = " join person per on per.id = wi.authorId AND per.projectId = ?";
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(projectVerifyId);
            paramIds.add(idList);
        }

        String sql = "SELECT wi.id FROM work_item wi join artifact p on p.id = wi.id"+ roleJoin + atributeSection;
        paramIds.add(roleIds);
        return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, -1, paramIds);

    }
}
