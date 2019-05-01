package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IVCSTagDAO
 */
public class VCSTagDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public VCSTagDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}



	public ArrayList<SQLVerifyObject> getVCSTagProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														   List<Integer> descriptionIndicator) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("c.name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createStringAttribute("c.description", description, descriptionIndicator);

		String sql = "SELECT c.id FROM tag c WHERE " + atributeSection;

		ArrayList<List<Integer>> paramIds = new ArrayList<>();
		return SQLAtributeCreator.findInstanceInDB(pripojeni,verifyController, sql, -1, paramIds);

	}

}
