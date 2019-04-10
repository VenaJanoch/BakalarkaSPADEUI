package database;

import SPADEPAC.Milestone;
import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IMilestoneDAO
 */
public class MilestoneDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public MilestoneDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getMilestoneProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														   List<Integer> descriptionIndicator, List<Integer> criterions) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createStringAttribute("description", description, descriptionIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("mc.criterionId", criterions);


		String sql = "SELECT p.id FROM milestone p join milestone_criterion mc on mc.milestoneId = p.id " + atributeSection;

		return SQLAtributeCreator.getAtributesFromDB(pripojeni,verifyController, sql, -1, null);

	}

}
