package database;

import SPADEPAC.Person;
import controllers.VerifyController;
import services.Constans;

import javax.xml.bind.attachment.AttachmentMarshaller;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 * @author Vaclav Janoch
 */
public class PersonDAO {
    private Connection pripojeni;                
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public PersonDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    
        this.verifyController = verifyController;
    }

/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Person
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param  projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicator seznam s indexi ukazatelu rovnosti
* @param roleTypeIds identifikatory zavislych Person
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getPersonProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<Integer> roleTypeIds) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("pr.roleId", roleTypeIds);
        String sql = "SELECT p.id FROM person p join person_role pr on pr.personId = p.id AND p.projectId = ? " + atributeSection;
        ArrayList<List<Integer>> ids = new ArrayList<>();
        ids.add(roleTypeIds);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, ids);

    }
}
