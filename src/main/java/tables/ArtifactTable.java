package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku elementu Artifact
 *
 * @author Václav Janoch
 */
public class ArtifactTable extends BasicTable {

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
    public ArtifactTable(String name, boolean exist, int id) {
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
