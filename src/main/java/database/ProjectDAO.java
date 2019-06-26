package database;

import controllers.VerifyController;
import services.Constans;
import tables.ProjectTable;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída zajišťující výběr dat projektů z databáze implementující
 *
 * @author Vaclav Janoch
 */
public class ProjectDAO {

    private Connection connection;
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     */
    public ProjectDAO() {
        this.connection = Constans.CONNECTION;
    }

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param verifyController
     */
    public ProjectDAO(VerifyController verifyController) {
        this();
        this.verifyController = verifyController;
    }

    /**
     * Vrací seznam projektů v databázi
     *
     * @return seznam projektů
     */
    public ArrayList<ProjectTable> getProject() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ProjectTable> listProjektu = new ArrayList<ProjectTable>();
        try {
            stmt = connection.prepareStatement("SELECT p.id, p.name FROM project p");
            rs = stmt.executeQuery();
            while (rs.next()) {
                listProjektu.add(new ProjectTable(rs.getString("name"), rs.getInt("id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listProjektu;
    }

    /**
     * Metoda pro vytvoreni SQL dotazu pro urcitou instanci Project
     * Metoda slozi jednotlive parametry pro SQL dotaz a zavola metodu ze tridy SQLAtributeCreator pro ziskani dat z databaze
     *
     * @param projectVerifyId      identifikator zvoleneho projektu
     * @param name                 seznam s atributy name
     * @param nameIndicator        seznam s indexi ukazatelu rovnosti
     * @param description          seznam s atributy description
     * @param descriptionIndicator seznam s indexi ukazatelu rovnosti
     * @param startDate            seznam s datumy vytvoreni
     * @param startDateIndicator   seznam s indexi ukazatelu rovnosti
     * @param endDate              seznam s datumy vytvoreni
     * @param endDateIndicator     seznam s indexi ukazatelu rovnosti
     * @return Seznam SQLVerifyObject s daty z databaze
     **/
    public ArrayList<SQLVerifyObject> getProject(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
                                                 List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate, List<XMLGregorianCalendar> startDate,
                                                 List<Integer> endDateIndicator, List<Integer> startDateIndicator) {

        String atributeSection = "";
        int i = 0;
        atributeSection += SQLAtributeCreator.createStringAttribute("i.name", name, nameIndicator);
        atributeSection += SQLAtributeCreator.createStringAttribute("i.description", description, descriptionIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);
        atributeSection += SQLAtributeCreator.createDateAttribute("startDate", startDate, startDateIndicator);

        String sql = "SELECT i.id FROM project i WHERE i.Id = ? " + atributeSection;
        ArrayList<List<Integer>> paramIds = new ArrayList<>();

        return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, projectVerifyId, paramIds);

    }

}
