package database;

import SPADEPAC.Person;
import controllers.VerifyController;
import services.Constans;

import javax.xml.bind.attachment.AttachmentMarshaller;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IPersonDAO
 */
public class PersonDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public PersonDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getPersonProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<Integer> roleTypeIds) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createIntAttribute("pr.roleId", roleTypeIds);
		String sql = "SELECT p.id FROM person p join person_role pr on pr.personId = p.id AND p.projectId = ? " + atributeSection;
		ArrayList<List<Integer>> ids = new ArrayList<>();
		ids.add(roleTypeIds);
		return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, ids);

	}
}
