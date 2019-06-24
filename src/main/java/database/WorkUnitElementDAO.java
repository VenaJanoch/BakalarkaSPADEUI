package database;

import controllers.VerifyController;
import services.Constans;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat artefaktů z databáze implementující
 * @author Vaclav Janoch
 */
public class WorkUnitElementDAO {
    private Connection pripojeni;               
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController
     */
    public WorkUnitElementDAO(VerifyController verifyController) {
        this.pripojeni = Constans.CONNECTION;   
        this.verifyController = verifyController;
    }


/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Priority
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getPriorityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("priority_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicatorsss);

        String sql = "SELECT p.id FROM priority p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);

        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Status
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getStatusProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List<Integer> classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createStringAttribute("p.name", name, nameIndicatorsss);
        atributeSection += SQLAtributeCreator.createClassAttribute("status_classification", classId, projectVerifyId);
        String sql = "SELECT p.id FROM status p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Severity
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getSeverityProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List classId, List superClassId) {

        String atributeSection = "";

        atributeSection += SQLAtributeCreator.createClassAttribute("severity_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicatorsss);

        String sql = "SELECT p.id FROM severity p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Type
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getTypeProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("wu_type_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicatorsss);

        String sql = "SELECT p.id FROM wu_type p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Relation
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getRelationProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("relation_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicatorsss);
        String sql = "SELECT p.id FROM relation p " + atributeSection;

        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }
/**
* Metoda pro vytvoreni SQL dotazu pro urcitou instanci Resolution
* Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
* @param projectVerifyId identifikator zvoleneho projektu
* @param name seznam s atributy name
* @param nameIndicatorsss seznam s indexi ukazatelu rovnosti
* @param classId seznam identifikatory trid
* @param superClassId seznam s identifikatory super trid
* @return Seznam SQLVerifyObject s daty z databaze
**/
    public ArrayList<SQLVerifyObject> getResolutionProjekt(int projectVerifyId, List<String> name, List<Integer> nameIndicatorsss, List classId, List superClassId) {

        String atributeSection = "";
        atributeSection += SQLAtributeCreator.createClassAttribute("resolution_classification", classId, projectVerifyId);
        atributeSection += SQLAtributeCreator.createStringAttribute("name", name, nameIndicatorsss);
        String sql = "SELECT p.id FROM resolution p " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();
        paramIds.add(classId);
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(projectVerifyId);
        paramIds.add(idList);
        return SQLAtributeCreator.findInstanceInDB(pripojeni, verifyController, sql, -1, paramIds);

    }

}
