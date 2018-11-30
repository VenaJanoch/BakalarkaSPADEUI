package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoleTable {

    /**
     * Globální proměnné třídy
     **/
    private StringProperty name;
    private StringProperty type;
    private StringProperty desc;

    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné
     *
     * @param name Jméno typu role
     * @param desc popis description
     * @param type typ role
     */
    public RoleTable(String name, String desc, String type) {
        super();
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.desc = new SimpleStringProperty(desc);
    }

    /**
     * Vypíše identifikaci o prvku
     */
    @Override
    public String toString() {

        return name.get();
    }


    /**
     * Getrs and Setrs
     **/
    public final String getName() {
        return name.get();
    }

    public final void setName(String name) {
        this.name.set(name);

    }

    public final String getType() {
        return type.get();
    }

    public final void setType(String name) {
        this.type.set(name);

    }

    public final String getDesc() {
        return desc.get();
    }

    public final void setDesc(String desc) {
        this.desc.set(desc);

    }




}
