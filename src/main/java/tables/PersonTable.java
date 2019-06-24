package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku elementu Person
 *
 * @author Václav Janoch
 */
public class PersonTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty type;
    private StringProperty description;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     * @param name alias prvku
     * @param exist existence prvku
     * @param id identifikator prvku
     */
    public PersonTable(String name, boolean exist, int id) {
        super(name, exist, id);
    }

    /**
     * Vypíše identifikaci o prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }


    /**
     * Getrs and Setrs
     **/

    public final String getType() {
        return type.get();
    }

    public final void setType(String name) {
        this.type.set(name);

    }

    public final String getDescription() {
        return description.get();
    }

    public final void setDescription(String description) {
        this.description.set(description);

    }


}
