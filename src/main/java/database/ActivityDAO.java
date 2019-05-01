package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IActivityDAO
 */
public class ActivityDAO {
	private Connection connection;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public ActivityDAO(VerifyController verifyController){
		this.connection = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getActivityFormProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
															 List<String> description, List<Integer> descriptionIndicator,
															 List<XMLGregorianCalendar> endDate, List<Integer> endDateIndicator, List<Integer> workUnits) {

		String atributeSection = "";
		atributeSection += SQLAtributeCreator.createStringAttribute("a.name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createStringAttribute("a.description", description, descriptionIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);

		String sql = "SELECT a.id FROM activity a join work_unit wu on wu.activityId = a.id AND superProjectId = ? " + atributeSection;

		return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, projectVerifyId, new ArrayList<>());

	}
}
