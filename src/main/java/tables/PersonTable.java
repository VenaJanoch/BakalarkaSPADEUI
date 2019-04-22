package tables;

import javafx.beans.property.StringProperty;

public class PersonTable extends BasicTable {

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
     */
    public PersonTable(String name,  boolean exist, int id) {
        super(name, exist, id);
     //   this.type = new SimpleStringProperty(type);
     //   this.description = new SimpleStringProperty(desc);
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
