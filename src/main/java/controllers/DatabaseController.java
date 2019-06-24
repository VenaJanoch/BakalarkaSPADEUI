package controllers;

import database.ProjectDAO;
import services.Alerts;
import services.Constans;
import tables.ProjectTable;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Trida predstavujici controller pro rizeni pripojeni aplikace k databazi
 *
 * @author Václav Janoch
 */
public class DatabaseController {

    /**Identifikator zvoleneho projektu**/
    private int chooseProjectId;
    /**Instace tridy **/
    private VerifyController verifyController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param verifyController Instace kontroleru VerifyController
     */
    public DatabaseController(VerifyController verifyController) {
        this.verifyController = verifyController;
    }

    /**
     * Metoda pro ziskani projektu v datovem skladu nastroje SPADe
     * @return seznam instaci ProjectTable
     */
    public ArrayList<ProjectTable> findProjectInDatabase() {
        ArrayList<ProjectTable> projekty = new ArrayList<>();
        try {
            ProjectDAO databazeProjekt = new ProjectDAO();
            projekty = databazeProjekt.getProject();
        } catch (Exception e) {
            Alerts.SQLConnectionError();
            e.printStackTrace();
        }
        return projekty;
    }

    /**
     * Metoda pro kontorlu modelu proti datum ve skladu
     * Nastavi se id zvoleneho projektu a zavola metoda z VerifyControlleru
     * @param projectId
     */
    public void confirmProjectWithModel(int projectId) {
        this.chooseProjectId = projectId;
        verifyController.verifyInstanceInProject(projectId);

    }

    /**
     * Pokud souhlasí login a heslo, přihlásí se do databáze
     *
     * @param login název uživatelského účtu
     * @param password heslo k uživatelskému účtu
     */
    public boolean logIn(String login, String password) {

        if (login.equals("") || password.length() == 0) {
            Alerts.SQLLogError();
            return false;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Constans.CONNECTION = DriverManager.getConnection(Constans.DATABASE_PATH, login, password);
            } catch (SQLException e) {
                Alerts.SQLConnectionError();
                return false;
            } catch (Exception e) {
                Alerts.SQLLogError();
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}
