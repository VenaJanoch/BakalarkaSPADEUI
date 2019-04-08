package database;

import SPADEPAC.Criterion;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICriterionDAO
 */
public class CriterionDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public CriterionDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getCriterionProjekt(int idProjekt) {
		return getCriterionProjekt(idProjekt, null);
	}


//	public ArrayList<Criterion> getCriterionConfiguration(int configurationId) {
//		return getCriterionKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Criterion> getCriterionOsoba(int idOsoby) {
		return getCriterionOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getCriterionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														   List<Integer> descriptionIndicator) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createStringAttribute("description", description, descriptionIndicator);

		String sql = "SELECT * FROM criterion  WHERE " + atributeSection;

		//	if(seznamIdcriterions != null && !seznamIdcriterions.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdcriterions) +")";

		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, -1, null);

	}



	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdcriterions seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getCriterionProjekt(int idProjekt, ArrayList<Integer> seznamIdcriterions) {

		String sql = "SELECT i.id FROM criterion i WHERE superProjectId = 18 ";

	//	if(seznamIdcriterions != null && !seznamIdcriterions.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdcriterions) +")";

		return null; // getCriterion(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdcriterions seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Criterion> getCriterionKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdcriterions) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdcriterions != null && !seznamIdcriterions.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdcriterions) +")";
//		return getCriterion(sql, idKonfigurace, seznamIdcriterions);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdcriterions seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Criterion> getCriterionOsoba(int idOsoby, ArrayList<Integer> seznamIdcriterions) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdcriterions != null && !seznamIdcriterions.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdcriterions) +")";
//
//		return getCriterion(sql, idOsoby, seznamIdcriterions);
//	}

}
