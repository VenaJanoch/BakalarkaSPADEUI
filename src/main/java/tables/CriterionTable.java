package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CriterionTable {

	/** Globální proměnné třídy **/
	private StringProperty name;
	private StringProperty description;
	private  int id;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param name
	 *            jméno criterion
	 * @param description
	 *            popis criterion
	 */
	public CriterionTable(String name, String description, int id) {
		super();
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.id = id;
	}

	/**
	 * Vypíše popis prvku
	 */
	@Override
	public String toString() {

		return name.get();
	}

	/** Getrs and Setrs **/
	public final String getName() {
		return name.get();
	}

	public final void setName(String name) {
		this.name.set(name);

	}

	public final String getDescription() {
		return description.get();
	}

	public final void setDescription(String descript) {
		this.description.set(descript);

	}

	public int getId() {
		return id;
	}
}
