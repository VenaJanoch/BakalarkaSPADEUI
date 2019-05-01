package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICPRDAO
 */
public class CPRDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public CPRDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}



	public ArrayList<SQLVerifyObject> getCPRProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														   List<Integer> descriptionIndicator, List<Integer> personIds) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("c.name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createStringAttribute("c.description", description, descriptionIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("c.personId", personIds);

		String sql = "SELECT c.id FROM configuration_person_relation c WHERE " + atributeSection;

		ArrayList<List<Integer>> paramIds = new ArrayList<>();
		paramIds.add(personIds);
		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, -1, paramIds);

	}

}
