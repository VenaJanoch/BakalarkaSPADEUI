package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IArtifactDAO
 */
public class ArtifactDAO {
	private Connection connection;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public ArtifactDAO(VerifyController verifyController){
		this.connection = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getArtifactProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<String> description,
														 List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate,
														 List<Integer> endDateIndicator, List<Integer> roleIds, List<String> type,
														 List<Integer> typeIndicator) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createStringAttribute("p.artifactClass", type, typeIndicator);
		atributeSection += SQLAtributeCreator.createStringAttribute("p.description", description, descriptionIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", roleIds);

		String sql = "SELECT wi.id FROM work_item wi join artifact p on p.id = wi.id" + atributeSection;
		ArrayList<List<Integer>> paramIds = new ArrayList<>();
		paramIds.add(roleIds);
		return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, -1, paramIds);

	}
}
