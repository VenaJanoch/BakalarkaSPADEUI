package tables;

import javafx.beans.property.StringProperty;

public class CriterionTable extends BasicTable{

	/** Globální proměnné třídy **/
	private StringProperty description;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param name
	 *            jméno criterion
	 *
	 */
	public CriterionTable(String name,  boolean exist, int id) {
		super(name, exist, id);
	//	this.description = new SimpleStringProperty(description);
	}

	/**
	 * Vypíše popis prvku
	 */
	@Override
	public String toString() {

		return getAlias();
	}

	/** Getrs and Setrs **/

	public final String getDescription() {
		return description.get();
	}

	public final void setDescription(String descript) {
		this.description.set(descript);

	}
}
