package database;

import SPADEPAC.Priority;
import controllers.VerifyController;
import services.Constans;

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


	public ArrayList<SQLVerifyObject> getPriorityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("priority_classification", classId);
		String sql = "SELECT p.id FROM priority p" + atributeSection + "WHERE i.projectId = ?";

		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, projectVerifyId, null);

	}


}
