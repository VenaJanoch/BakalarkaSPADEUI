package database;

import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 *
 * @author Vaclav Janoch
 */
public class WorkUnitDAO {
    private Connection pripojeni;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public WorkUnitDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Work Unit
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId identifikator zvoleneho projektu
     * @param name            seznam s atributy name
     * @param nameIndicator   seznam s indexi ukazatelu rovnosti
     * @param createdDate     seznam s datumy vytvoreni
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getWorkUnitProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator, List<Double> estimateTime, List<Integer> progress, List<Integer> category,
                                                         List<Integer> assigneeIds, List<Integer> priorityIds, List<Integer> severityIds, List<Integer> resolutionIds,
                                                         List<Integer> statusIds, List<Integer> typeIds, List<Integer> relationIds, List<Integer> workUnitsInRelationIds,
                                                         List<XMLGregorianCalendar> createdDate, List<Integer> createdDateIndicator, List<Integer> estimateIndicators, List<Integer> progressIndicator) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("wi.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("wi.created", createdDate, createdDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wuc.categoryId", category);
        atributeSection += SQLAtributeCreator.createDoubleAttribute("wu.estimatedTime", estimateTime, estimateIndicators);
        atributeSection += SQLAtributeCreator.createIntAttribute("wu.progress", progress, progressIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.assigneeId", assigneeIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.priorityId", priorityIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.severityId", severityIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.statusId", statusIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.wuTypeId", typeIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wu.resolutionId", resolutionIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wir.relationId", relationIds);
        atributeSection += SQLAtributeCreator.createIdAttribute("wir.rightId", workUnitsInRelationIds);

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(category);
        paramIds.add(assigneeIds);
        paramIds.add(priorityIds);
        paramIds.add(severityIds);
        paramIds.add(statusIds);
        paramIds.add(typeIds);
        paramIds.add(resolutionIds);
        paramIds.add(relationIds);
        paramIds.add(workUnitsInRelationIds);


        String sql = "SELECT wu.id FROM work_unit wu " +
                "join work_item wi on wu.id = wi.id " +
                "join work_unit_category wuc on wu.id = wuc.workUnitId " +
                "join work_item_relation wir on wir.leftItemId = wu.id " +
                "AND wu.projectId = ? " + atributeSection;

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, paramIds);

    }
}
