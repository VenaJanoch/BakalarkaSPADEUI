package database;

import controllers.VerifyController;
import services.Constans;
import services.SQLAtributeCreator;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IWorkUnitDAO
 */
public class WorkUnitDAO {
	private Connection pripojeni;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public WorkUnitDAO(VerifyController verifyController){
		this.pripojeni = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
		this.verifyController = verifyController;
	}


	public ArrayList<SQLVerifyObject> getWorkUnitProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<Double> estimateTime, List<Integer> category,
														 List<Integer> assigneeIds, List<Integer> priorityIds, List<Integer> severityIds, List<Integer> resolutionIds,
														 List<Integer> statusIds, List<Integer> typeIds, List<Integer> relation ) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
		//atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("wuc.categoryId", category);
		atributeSection += SQLAtributeCreator.createDoubleAttribute("p.estimatedTime", estimateTime);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.assigneeId", assigneeIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.priorityId", priorityIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.severityId", severityIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.statusId", statusIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.wuTypeId", typeIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("p.resolutionId", resolutionIds);


		String sql = "SELECT p.id FROM work_unit p join work_unit_category wuc on p.id = wuc.workUnitId AND p.projectId = ? " + atributeSection;

		return SQLAtributeCreator.getAtributesFromDB(pripojeni, verifyController, sql, projectVerifyId, null);

	}
}
