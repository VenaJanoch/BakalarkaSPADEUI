package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku elementu Criterion
 *
 * @author Václav Janoch
 */
public class CriterionTable extends BasicTable {

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
    public CriterionTable(String name, boolean exist, int id) {
        super(name, exist, id);
    }

    /**
     * Vypíše popis prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }

    /**
     * Getrs and Setrs
     **/

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String descript) {
        this.description.set(descript);

    }
}
