package database;

import SPADEPAC.Priority;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IPriorityDAO
 */
public class WorkUnitElementDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public WorkUnitElementDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getPriorityyProjekt(int idProjekt) {
		return getPriorityyProjekt(idProjekt, null);
	}


//	public ArrayList<Priority> getPriorityyConfiguration(int configurationId) {
//		return getPriorityyKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Priority> getPriorityyOsoba(int idOsoby) {
		return getPriorityyOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getPriorityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("priority_classification", classId);
		String sql = "SELECT p.id FROM priority p" + atributeSection + "WHERE i.projectId = ?";

		//	if(seznamIdPriorityu != null && !seznamIdPriorityu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPriorityu) +")";
		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}

	public ArrayList<SQLVerifyObject> getStatusProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("status_classification", classId);
		String sql = "SELECT p.id FROM status p" + atributeSection + "WHERE i.projectId = ?";

		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni, verifyController, sql, projectVerifyId, paramsInt);

	}

	public ArrayList<SQLVerifyObject> getSeverityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("severity_classification", classId);
		String sql = "SELECT p.id FROM severity p" + atributeSection + "WHERE i.projectId = ?";

		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}

	public ArrayList<SQLVerifyObject> getTypeProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("wu_type_classification", classId);
		String sql = "SELECT p.id FROM wu_type p" + atributeSection + "WHERE i.projectId = ?";

		//	if(seznamIdTypeu != null && !seznamIdTypeu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdTypeu) +")";
		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}

	public ArrayList<SQLVerifyObject> getRelationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("relation_classification", classId);
		String sql = "SELECT p.id FROM relation p" + atributeSection + "WHERE i.projectId = ?";

		//	if(seznamIdRelationu != null && !seznamIdRelationu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdRelationu) +")";
		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}

	public ArrayList<SQLVerifyObject> getResolutionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("resolution_classification", classId);
		String sql = "SELECT p.id FROM resolution p" + atributeSection + "WHERE i.projectId = ?";

		//	if(seznamIdResolutionu != null && !seznamIdResolutionu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdResolutionu) +")";
		int[] paramsInt = new int[1];
		paramsInt[0] = classId;
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, paramsInt);

	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdPriorityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getPriorityyProjekt(int idProjekt, ArrayList<Integer> seznamIdPriorityu) {

		String sql = "SELECT i.id FROM priority i WHERE superProjectId = ? ";

	//	if(seznamIdPriorityu != null && !seznamIdPriorityu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPriorityu) +")";

		return null; // getPriorityy(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdPriorityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Priority> getPriorityyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdPriorityu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdPriorityu != null && !seznamIdPriorityu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPriorityu) +")";
//		return getPriorityy(sql, idKonfigurace, seznamIdPriorityu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdPriorityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Priority> getPriorityyOsoba(int idOsoby, ArrayList<Integer> seznamIdPriorityu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdPriorityu != null && !seznamIdPriorityu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPriorityu) +")";
//
//		return getPriorityy(sql, idOsoby, seznamIdPriorityu);
//	}

}
