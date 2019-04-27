package database;

import SPADEPAC.BranchList;
import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICommitDAO
 */
public class CommitDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public CommitDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getCommitProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, boolean isRelease, List<BranchList> branches, List<Integer> tag ) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("p.identifier", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createBooleanAttribute("p.isRelease", isRelease);
	//	atributeSection += SQLAtributeCreator.createBranchAttribute("wi.authorId", branches);
	//	atributeSection += SQLAtributeCreator.createTagAttribute("wi.authorId", tag);
		//atributeSection += " AND p.configurationId = " + configIds;

		String sql = "SELECT wi.id FROM work_item wi join commit p on p.id = wi.id" + atributeSection;
		ArrayList<List<Integer>> paramIds = new ArrayList<>();
		return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, null);
	}
}
