package database;

import SPADEPAC.Activity;
import controllers.VerifyController;
import services.Constans;

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
	 * Vrací seznam artefaktů patřících osobě s id v parametru
	 * @param idOsoby id osoby pro výběr artefaktů
	 * @return seznam artefaktů
	 */
	public ArrayList<Activity> getRoleOsoba(int idOsoby) {
		return getRoleOsoba(idOsoby);
	}


	public ArrayList<SQLVerifyObject> getRoleProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicators, int classId, int superClassId) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicators);
		atributeSection += SQLAtributeCreator.createClassAttribute("role_classification", classId);
		String sql = "SELECT p.id FROM role p" + atributeSection + "WHERE i.projectId = ?";

		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, projectVerifyId, new ArrayList<>());

	}
}
