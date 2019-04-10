package database;

import SPADEPAC.Activity;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IActivityDAO
 */
public class ActivityDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public ActivityDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getActivityyProjekt(int idProjekt) {
		return getActivityyProjekt(idProjekt, null);
	}


//	public ArrayList<Activity> getActivityyConfiguration(int configurationId) {
//		return getActivityyKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Activity> getActivityyOsoba(int idOsoby) {
		return getActivityyOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getActivityyProjekt(int projectVerifyId, List<XMLGregorianCalendar> endDate, List<Integer> endDateIndicator) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);

		String sql = "SELECT a.id FROM activity a join work_unit wu on wu.activityId = a.id AND superProjectId = ? " + atributeSection;

		//	if(seznamIdActivityu != null && !seznamIdActivityu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdActivityu) +")";
		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, projectVerifyId, null);

	}



	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getActivityyProjekt(int idProjekt, ArrayList<Integer> seznamIdActivityu) {

		String sql = "SELECT i.id FROM activity i WHERE superProjectId = ? ";

	//	if(seznamIdActivityu != null && !seznamIdActivityu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdActivityu) +")";

		return null; // getActivityy(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Activity> getActivityyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdActivityu) {
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
//		return getActivityy(sql, idKonfigurace, seznamIdActivityu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdActivityu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Activity> getActivityyOsoba(int idOsoby, ArrayList<Integer> seznamIdActivityu) {
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
//		return getActivityy(sql, idOsoby, seznamIdActivityu);
//	}

}
