package database;

import SPADEPAC.Phase;
import controllers.VerifyController;
import services.Constans;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující rozhraní IPhaseDAO
 */
public class PhaseDAO {
    private Connection pripojeni;                //připojení k databázi
    private VerifyController verifyController;

    /**
     * Konstruktor třídy
     */
    public PhaseDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;    //nastaví připojení uložené ve třídě Konstanty
        this.verifyController = verifyController;
    }

    public ArrayList<SQLVerifyObject> getPhaseyProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                       List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate,
                                                       List<Integer> endDateIndicator, List<Integer> milestoneId, List<Integer> configId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("i.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("i.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);
        atributeSection += SQLAtributeCreator.createIdAttribute("i.milestoneId", milestoneId);
        atributeSection += SQLAtributeCreator.createIdAttribute("i.configurationId", configId);
        String sql = "SELECT i.id FROM phase i join work_unit wu on wu.phaseId = i.id WHERE i.superProjectId = ? " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(milestoneId);
        paramIds.add(configId);

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, projectVerifyId, paramIds);
    }


    /**
     * Vrací seznam artefaktů patřících do projektu s id v parametru a splňující podmínky filtru artefaktů
     *
     * @param idProjekt      id projektu pro výběr artefaktů
     * @param seznamIdPhaseu seznam povolených artefaktů
     * @return seznam artefaktů
     */
    public ArrayList<SQLVerifyObject> getPhaseyProjekt(int idProjekt, ArrayList<Integer> seznamIdPhaseu) {

        String sql = "SELECT i.id FROM phase i WHERE superProjectId = 18 ";

        //	if(seznamIdPhaseu != null && !seznamIdPhaseu.isEmpty())
        //		sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPhaseu) +")";

        return null; // getPhasey(sql, idProjekt);
    }

    /**
     * Vrací seznam artefaktů patřících do konfigurace s id v parametru a splňující podmínky filtru artefaktů
     * @param idKonfigurace id konfigurace pro výběr artefaktů
     * @param seznamIdPhaseu seznam povolených artefaktů
     * @return seznam artefaktů
     */
//	public ArrayList<Phase> getPhaseyKonfigurace(int idKonfigurace, ArrayList<Integer> seznamIdPhaseu) {
//		String sql = "SELECT a.id, i.name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "left join work_item_change c on i.id = c.workItemId "
//				   + "left join configuration_change g on c.id = g.changeId "
//				   + "WHERE g.configurationId = ? ";
//
//		if(seznamIdPhaseu != null && !seznamIdPhaseu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPhaseu) +")";
//		return getPhasey(sql, idKonfigurace, seznamIdPhaseu);
//	}

    /**
     * Vrací seznam artefaktů patřících osobě s id v parametru a splňující podmínky filtru výběru
     * @param idOsoby id osoby pro výběr artefaktů
     * @param seznamIdPhaseu seznam povolených artefaktů
     * @return seznam artefaktů
     */
//	public ArrayList<Phase> getPhaseyOsoba(int idOsoby, ArrayList<Integer> seznamIdPhaseu) {
//		String sql = "SELECT a.id, a.artifactClass as name, "
//				   + "coalesce(i.created, STR_TO_DATE('"+ Konstanty.DATUM_PRAZDNY + "', '%Y-%m-%d') ) as created, "
//				   + "a.mimeType, a.size "
//				   + "FROM artifact a "
//				   + "left join work_item i on a.id = i.id "
//				   + "WHERE i.authorId = ? ";
//
//		if(seznamIdPhaseu != null && !seznamIdPhaseu.isEmpty())
//			sql += " and a.id in ("+ Konstanty.getZnakyParametru(seznamIdPhaseu) +")";
//
//		return getPhasey(sql, idOsoby, seznamIdPhaseu);
//	}

}
