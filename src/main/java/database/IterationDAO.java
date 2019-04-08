package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.datatype.XMLGregorianCalendar;

import SPADEPAC.Iteration;
import controllers.DataPreparer;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IIterationDAO
 */
public class IterationDAO{
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public IterationDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getIterationyProjekt(int idProjekt) {
		return getIterationyProjekt(idProjekt, null);
	}


//	public ArrayList<Iteration> getIterationyConfiguration(int configurationId) {
//		return getIterationyKonfigurace(configurationId, null);
//	}
	
	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Iteration> getIterationyOsoba(int idOsoby) {
		return getIterationyOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getIterationyProjekt(int projectVerifyId, List<XMLGregorianCalendar> endDate, List<XMLGregorianCalendar> startDate,
														   List<Integer> endDateIndicator, List<Integer> startDateIndicator) {

		String atributeSection = "";
		int i = 0;
		atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("startDate", startDate, startDateIndicator);

		String sql = "SELECT * FROM iteration  WHERE superProjectId = ? " + atributeSection;

		//	if(seznamIdIterationu != null && !seznamIdIterationu.isEmpty())
		//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdIterationu) +")";

		return SQLAtributeCreator.getAtributesFromDB(pripojeni, verifyController, sql, projectVerifyId,null);

	}



	/**
	 * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
	 * @param idProjekt id projektu pro výběr artefaktů
	 * @param seznamIdIterationu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<SQLVerifyObject> getIterationyProjekt(int idProjekt, ArrayList<Integer> seznamIdIterationu) {

		String sql = "SELECT i.id FROM iteration i WHERE superProjectId = 18 ";

	//	if(seznamIdIterationu != null && !seznamIdIterationu.isEmpty())
	//		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdIterationu) +")";

		return getIterationy(sql, idProjekt);
	}

	/**
	 * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
	 * @param idKonfigurace id konfigurace pro výběr artefaktů
	 * @param seznamIdIterationu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Iteration> getIterationyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdIterationu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdIterationu != null && !seznamIdIterationu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdIterationu) +")";
//		return getIterationy(sql, idKonfigurace, seznamIdIterationu);
//	}

	/**
	 * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @param seznamIdIterationu seznam povolených artefaktů
	 * @return seznam artefaktů
	 */
//	public ArrayList<Iteration> getIterationyOsoba(int idOsoby, ArrayList<Integer> seznamIdIterationu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdIterationu != null && !seznamIdIterationu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdIterationu) +")";
//
//		return getIterationy(sql, idOsoby, seznamIdIterationu);
//	}

	/**
	 * Spustí zadaný skript a naplní seznam artefaktů
	 * @param sql skript pro spuštění
	 * @param id identifikátor výběru
	 * @return seznam artefaktů
	 */
	private ArrayList<SQLVerifyObject> getIterationy(String sql, int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		ArrayList<SQLVerifyObject> listIteration = new ArrayList<SQLVerifyObject>();
		try {
			stmt = pripojeni.prepareStatement(sql);
			int i = 1;
			//stmt.setInt(i++, id);
//			if(seznamIdIterationu != null && !seznamIdIterationu.isEmpty()){
//				for(int x = 0; x < seznamIdIterationu.size(); x++)
//					stmt.setInt(i++, seznamIdIterationu.get(x));
//			}
			
			rs = stmt.executeQuery();
			while(rs.next()){
				listIteration.add(verifyController.createSQLVerifyObject(rs.getInt("id"), sql, true));
			}
			
		} catch (SQLException e) {
		//	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaScriptIteration"));
			e.printStackTrace();
		} catch (Exception e){
		//	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaDataIteration"));
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listIteration;		
	}

}
