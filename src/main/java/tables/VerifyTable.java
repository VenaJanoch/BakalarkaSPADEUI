package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku vysledku z databaze
 *
 * @author Václav Janoch
 */
public class VerifyTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty isExistInProject;
    private StringProperty result;
    private StringProperty sql;
    private StringProperty instanceCount;
    private StringProperty projectInstanceCount;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name             jmeno prvku
     * @param id               identifikatoru prvku
     * @param exist            existence prvku v modelu
     * @param isExistInProject existence prvku v projektu
     * @param result           vysledek
     * @param sql              slozeny SQL dotaz
     */
    public VerifyTable(String name, int id, boolean exist, String isExistInProject, String result, String sql) {
        super(name, exist, id);
        this.isExistInProject = new SimpleStringProperty(isExistInProject);
        this.result = new SimpleStringProperty(result);
        this.sql = new SimpleStringProperty(sql);
        this.instanceCount = new SimpleStringProperty("-");
        this.projectInstanceCount = new SimpleStringProperty("-");

    }

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name                 jmeno prvku
     * @param id                   identifikatoru prvku
     * @param exist                existence prvku v modelu
     * @param instanceCont         pocet instanci v modelu
     * @param countIndicator       ukazatel nerovnosti
     * @param projectInstanceCount pocet instaci v projektu
     * @param isExistInProject     existence prvku v projektu
     * @param result               vysledek
     * @param sql                  slozeny SQL dotaz
     */
    public VerifyTable(String name, int id, boolean exist, int instanceCont, String countIndicator, int projectInstanceCount, String isExistInProject, String result, String sql) {
        this(name, id, exist, isExistInProject, result, sql);
        if (instanceCont == -1) {
            this.projectInstanceCount = new SimpleStringProperty("----");
            this.instanceCount = new SimpleStringProperty("----");
        } else {
            this.projectInstanceCount = new SimpleStringProperty(String.valueOf(projectInstanceCount));
            this.instanceCount = new SimpleStringProperty(countIndicator + " " + String.valueOf(instanceCont));
        }
    }

    /**
     * Vypíše info o prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }

    public String getIsExistInProject() {
        return isExistInProject.get();
    }

    public StringProperty isExistInProjectProperty() {
        return isExistInProject;
    }

    public String getResult() {
        return result.get();
    }

    public StringProperty resultProperty() {
        return result;
    }

    public String getSql() {
        return sql.get();
    }

    public StringProperty sqlProperty() {
        return sql;
    }

    public String getInstanceCount() {
        return instanceCount.get();
    }

    public StringProperty instanceCountProperty() {
        return instanceCount;
    }

    public String getProjectInstanceCount() {
        return projectInstanceCount.get();
    }

    public StringProperty projectInstanceCountProperty() {
        return projectInstanceCount;
    }

    /** Globální proměnné třídy **/

}
