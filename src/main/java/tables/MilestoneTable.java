package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku elementu Milestone
 *
 * @author Václav Janoch
 */
public class MilestoneTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty criterion;
    private StringProperty description;

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     *
     * @param name  alias prvku
     * @param exist existence prvku
     * @param id    identifikator prvku
     */
    public MilestoneTable(String name, boolean exist, int id) {
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

    public final String getCriterion() {
        return criterion.get();
    }

    public final void setCriterion(String name) {
        this.criterion.set(name);

    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
