package controllers;

import database.ProjectDAO;
import services.Alerts;
import services.Constans;
import tables.ProjectTable;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseController {

    private int chooseProjectId;
    private VerifyController verifyController;

    public DatabaseController(VerifyController verifyController) {
        this.verifyController = verifyController;
    }

    public ArrayList<ProjectTable> findProjectInDatabase() {
        ArrayList<ProjectTable> projekty = new ArrayList<>();
        try {
            ProjectDAO databazeProjekt = new ProjectDAO();
            projekty = databazeProjekt.getProject();
        } catch (Exception e) {
            //   JOptionPane.showMessageDialog(null, Konstanty.POPISY.getProperty("chybaNacteniProjektu"));
            e.printStackTrace();
        }
        return projekty;
    }

    public void confirmProjectWithModel(int projectId) {
        this.chooseProjectId = projectId;
        verifyController.verifyInstanceInProject(projectId);

    }

    /**
     * Pokud souhlasí login a heslo, přihlásí se do databáze a spustí hlavní okno programu
     *
     * @param login název uživatelského účtu
     * @param heslo heslo k uživatelskému účtu
     */
    public boolean logIn(String login, String heslo) {

        if (login.equals("") || heslo.length() == 0) {
            // JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaUdaju"));
            Alerts.SQLLogError();
            return false;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Constans.CONNECTION = DriverManager.getConnection(Constans.DATABASE_PATH, login, heslo);
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
