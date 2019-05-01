package database;

import SPADEPAC.Criterion;
import controllers.VerifyController;
import services.Constans;

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

	public ArrayList<SQLVerifyObject> getCriterionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														   List<Integer> descriptionIndicator) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createStringAttribute("description", description, descriptionIndicator);

		String sql = "SELECT c.id FROM criterion c WHERE " + atributeSection;

		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, -1, null);

	}

}
