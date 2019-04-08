package database;

import SPADEPAC.Activity;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IActivityDAO
 */
public class RoleDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public RoleDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getRoleProjekt(int idProjekt) {
		return getRoleProjekt(idProjekt, null);
	}


//	public ArrayList<Activity> getRoleConfiguration(int configurationId) {
//		return getRoleKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Activity> getRoleOsoba(int idOsoby) {
		return getRoleOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getRoleProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("role_classification", classId);
		String sql = "SELECT p.id FROM role p" + atributeSection + "WHERE i.projectId = ?";

		//	if(seznamIdTypeu != null && !seznamIdTypeu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdTypeu) +")";
		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}



	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getRoleProjekt(int idProjekt, ArrayList<Integer> seznamIdActivityu) {

		String sql = "SELECT i.id FROM activity i WHERE superProjectId = ? ";

	//	if(seznamIdActivityu != null && !seznamIdActivityu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdActivityu) +")";

		return null; // getRole(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Activity> getRoleKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdActivityu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdActivityu != null && !seznamIdActivityu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdActivityu) +")";
//		return getRole(sql, idKonfigurace, seznamIdActivityu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Activity> getRoleOsoba(int idOsoby, ArrayList<Integer> seznamIdActivityu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdActivityu != null && !seznamIdActivityu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdActivityu) +")";
//
//		return getRole(sql, idOsoby, seznamIdActivityu);
//	}

}
