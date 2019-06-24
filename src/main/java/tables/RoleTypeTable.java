package tables;

import javafx.beans.property.StringProperty;

/**
 * Trida predstavujici kontejner pro tabulku elementu RoleType
 *
 * @author Václav Janoch
 */
public class RoleTypeTable extends ClassTable {

    /**
     * Globální proměnné třídy
     **/

    private StringProperty description;

    /**
     * Konstruktor třídy
     *
     * @param name      jméno výčtového typu
     * @param classType class
     * @param superType super class
     */
    public RoleTypeTable(String name, String classType, boolean exist, String superType, int id) {
        super(name, classType, superType, exist, id);
        //  this.description = new SimpleStringProperty(description);
    }


    /**
     * Vypíše informace o prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }


    /**
     * Getrs and Setrs
     */


    public final String getDescriptionType() {
        return description.get();
    }

    public final void setDescription(String descriptionType) {
        this.description.set(descriptionType);

    }
}
