package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku segmentu Iteration
 *
 * @author Václav Janoch
 */
public class IterationTable extends BasicTable {

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
    public IterationTable(String name, boolean exist, int id) {
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
