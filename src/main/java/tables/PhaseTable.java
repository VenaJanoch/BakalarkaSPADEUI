package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku segmentu Phase
 *
 * @author Václav Janoch
 */
public class PhaseTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty milestone;
    private StringProperty configuration;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name  alias prvku
     * @param exist existence prvku
     * @param id    identifikator prvku
     */
    public PhaseTable(String name, boolean exist, int id) {
        super(name, exist, id);
    }

    /**
     * Vypíše info o prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }

    /**
     * Globální proměnné třídy
     **/

    public final String getMilestone() {
        return milestone.get();
    }

    public final void setMilestone(String name) {
        this.milestone.set(name);

    }

    public String getConfiguration() {
        return configuration.get();
    }

    public StringProperty configurationProperty() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration.set(configuration);
    }
}
