package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IVCSTagDAO
 */
public class TagDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public TagDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getVCSTagProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														 List<Integer> descriptionIndicator, List<Integer> configIds) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createStringAttribute("p.description", description, descriptionIndicator);
		//atributeSection += " AND p.configurationId = " + configIds;

		String sql = "SELECT p.id FROM tag p WHERE " + atributeSection;

		return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, null);
	}
}
