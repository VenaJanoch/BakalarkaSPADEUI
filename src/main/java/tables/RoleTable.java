package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoleTable extends BasicTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty type;
    private StringProperty description;

    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné
     *
     * @param name Jméno typu role
     * @param desc popis description
     * @param type typ role
     */
    public RoleTable(String name, String desc, String type, int id) {
        super(name, id);
        this.type = new SimpleStringProperty(type);
        this.description = new SimpleStringProperty(desc);
    }

    /**
     * Vypíše identifikaci o prvku
     */
    @Override
    public String toString() {

        return getName();
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
