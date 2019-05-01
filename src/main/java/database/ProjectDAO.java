package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controllers.VerifyController;
import services.Constans;
import tables.ProjectTable;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Třída zajišťující výběr dat projektů z databáze implementující rozhraní IProjektDAO
 */
public class ProjectDAO {

	private Connection connection;				//připojení k databázi
	private VerifyController verifyController;
	/**
	 * Konstruktor třídy
	 */
	public ProjectDAO(){
		this.connection = Constans.CONNECTION;	//nastaví připojení uložené ve třídě Konstanty
	}

	/**
	 * Konstruktor třídy
	 */
	public ProjectDAO(VerifyController verifyController){
		this();
		this.verifyController = verifyController;
	}

	/**
	 * Vrací seznam projektů v databázi
	 * @return seznam projektů
	 */
	public ArrayList<ProjectTable> getProject(){
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ProjectTable> listProjektu = new ArrayList<ProjectTable>();
		try {
			stmt = connection.prepareStatement("SELECT p.id, p.name FROM project p");
			rs = stmt.executeQuery();
			while(rs.next()){
				listProjektu.add(new ProjectTable(rs.getString("name"), rs.getInt("id")));
			}

		} catch (SQLException e) {
		//	JOptionPane.showMessageDialog(null , "Chyba při spuštění skriptu projektů!");
			e.printStackTrace();
		} catch (Exception e){
		//	JOptionPane.showMessageDialog(null , "Chyba při výběru projektů z databáze!");
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

	public ArrayList<SQLVerifyObject> getProject(int projectVerifyId, List<String> name, List<Integer> nameIndicator,
												 List<String> description, List<Integer> descriptionIndicator, List<XMLGregorianCalendar> endDate, List<XMLGregorianCalendar> startDate,
												 List<Integer> endDateIndicator, List<Integer> startDateIndicator) {

		String atributeSection = "";
		int i = 0;
		atributeSection += SQLAtributeCreator.createStringAttribute("i.name", name, nameIndicator);
		atributeSection += SQLAtributeCreator.createStringAttribute("i.description", description, descriptionIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("endDate", endDate, endDateIndicator);
		atributeSection += SQLAtributeCreator.createDateAttribute("startDate", startDate, startDateIndicator);

		String sql = "SELECT i.id FROM project WHERE i.superProjectId = ? " + atributeSection;
		ArrayList<List<Integer>> paramIds = new ArrayList<>();

		return SQLAtributeCreator.findInstanceInDB(connection, verifyController, sql, projectVerifyId, paramIds);

	}

}
