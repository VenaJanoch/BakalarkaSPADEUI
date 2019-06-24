package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Trida predstavujici kontejner pro tabulku elementu Committed Configuration
 *
 * @author Václav Janoch
 */
public class CommitedConfigurationTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty description;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name jméno milestone
     */
    public CommitedConfigurationTable(String name, String description, boolean exist, int id) {
        super(name, exist, id);
        this.description = new SimpleStringProperty(description);
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

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String name) {
        this.description.set(name);

    }

}
