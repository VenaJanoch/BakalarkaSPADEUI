package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní ICommitedConfigurationDAO
 */
public class CommitedConfigurationDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public CommitedConfigurationDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getCommitedConfigurationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
																	  List<XMLGregorianCalendar> commitedDay, List<Integer> commitedDayIndicator) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicator).substring(5);
		atributeSection += SQLAtributeCreator.createDateAttribute("p.committed", commitedDay, commitedDayIndicator);
		//atributeSection += SQLAtributeCreator.createCommitAttribute("p.committed", commitedDay, commitedDayIndicator).substring(5);

		String sql = "SELECT wi.id FROM work_item wi join committed_configuration p on p.id = wi.id" + atributeSection;

		return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, null);
	}
}
