package tables;

import javafx.beans.property.StringProperty;


/**
 * Trida predstavujici kontejner pro tabulku elementu Change
 *
 * @author Václav Janoch
 */
public class ChangeTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty description;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name  alias prvku
     * @param exist existence prvku
     * @param id    identifikator prvku
     */
    public ChangeTable(String name, boolean exist, int id) {
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

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String name) {
        this.description.set(name);

    }

}
