package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IConfigurationDAO
 */
public class ConfigurationDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public ConfigurationDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getConfigurationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, 
														 List<XMLGregorianCalendar> endDate, List<Integer> endDateIndicator,
														 List<Integer> changeId, List<Integer> committedId, List<Integer> personIds, List<Integer> artifactIds ) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("wi.authorId", personIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("ch.change", changeId).substring(5);
		//atributeSection += SQLAtributeCreator.createArtifactAttribute("p.workItemId", artifactIds);
		//atributeSection += SQLAtributeCreator.createIdAttribute("wi.id", committedId);

		//	atributeSection += SQLAtributeCreator.createCPRAttribute("wi.authorId", roleIds);
		String sql = "SELECT p.id FROM work_item wi join configuration p on p.id = wi.id join configuration_change ch on ch.configurationId = p.id AND p.projectId = ? " + atributeSection;
		ArrayList<List<Integer>> paramIds = new ArrayList<>();
		paramIds.add(personIds);
		paramIds.add(changeId);
		return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, paramIds);

	}
}
