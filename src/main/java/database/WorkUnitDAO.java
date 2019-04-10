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
														 List<Integer> statusIds, List<Integer> typeIds, List<Integer> relationIds, List<Integer> workUnitsInRelationIds ) {

		String atributeSection = "";
		//atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
		//atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createIdAttribute("wuc.categoryId", category);
		atributeSection += SQLAtributeCreator.createDoubleAttribute("wu.estimatedTime", estimateTime);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.assigneeId", assigneeIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.priorityId", priorityIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.severityId", severityIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.statusId", statusIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.wuTypeId", typeIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wu.resolutionId", resolutionIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wir.relationId", relationIds);
		atributeSection += SQLAtributeCreator.createIdAttribute("wir.rightId", workUnitsInRelationIds);


		String sql = "SELECT wu.id FROM work_unit wu " +
				"join work_unit_category wuc on wu.id = wuc.workUnitId " +
				//"join work_item_relation wir on wir.leftItemId = wu.id " +
				"AND wu.projectId = ? " + atributeSection;

		return SQLAtributeCreator.getAtributesFromDB(pripojeni, verifyController, sql, projectVerifyId, null);

	}
}
