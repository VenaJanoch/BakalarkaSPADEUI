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

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getPersonProjekt(int idProjekt) {
		return getPersonProjekt(idProjekt, null);
	}


//	public ArrayList<Person> getPersonyConfiguration(int configurationId) {
//		return getPersonyKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Person> getPersonyOsoba(int idOsoby) {
		return getPersonyOsoba(idOsoby);
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


	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdPersonu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getPersonProjekt(int idProjekt, ArrayList<Integer> seznamIdPersonu) {

		String sql = "SELECT i.id FROM person i WHERE superProjectId = 18 ";

	//	if(seznamIdPersonu != null && !seznamIdPersonu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPersonu) +")";

		return null; // getPersony(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdPersonu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Person> getPersonyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdPersonu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdPersonu != null && !seznamIdPersonu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPersonu) +")";
//		return getPersony(sql, idKonfigurace, seznamIdPersonu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdPersonu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Person> getPersonyOsoba(int idOsoby, ArrayList<Integer> seznamIdPersonu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdPersonu != null && !seznamIdPersonu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPersonu) +")";
//
//		return getPersony(sql, idOsoby, seznamIdPersonu);
//	}

}
