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
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public BranchDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getBranchyProjekt(int idProjekt) {
		return getBranchyProjekt(idProjekt, null);
	}


//	public ArrayList<Branch> getBranchyConfiguration(int configurationId) {
//		return getBranchyKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Branch> getBranchyOsoba(int idOsoby) {
		return getBranchyOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getBranchyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, boolean isMain) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createBooleanAttribute("isMain", isMain);

		String sql = "SELECT b.id FROM branch b  WHERE " + atributeSection;

		//	if(seznamIdBranchu != null && !seznamIdBranchu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdBranchu) +")";

		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, -1, null);

	}



	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdBranchu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getBranchyProjekt(int idProjekt, ArrayList<Integer> seznamIdBranchu) {

		String sql = "SELECT i.id FROM branch i WHERE superProjectId = 18 ";

	//	if(seznamIdBranchu != null && !seznamIdBranchu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdBranchu) +")";

		return null; // getBranchy(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdBranchu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Branch> getBranchyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdBranchu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdBranchu != null && !seznamIdBranchu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdBranchu) +")";
//		return getBranchy(sql, idKonfigurace, seznamIdBranchu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdBranchu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Branch> getBranchyOsoba(int idOsoby, ArrayList<Integer> seznamIdBranchu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdBranchu != null && !seznamIdBranchu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdBranchu) +")";
//
//		return getBranchy(sql, idOsoby, seznamIdBranchu);
//	}

}
