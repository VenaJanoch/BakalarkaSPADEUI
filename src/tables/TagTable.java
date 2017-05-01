package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TagTable {

	/** Globální proměnné třídy **/
	private StringProperty tag;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné
	 * 
	 * @param tag
	 *            String prvek pro přídání do tabulky
	 */
	public TagTable(String tag) {
		super();
		this.tag = new SimpleStringProperty(tag);
	}

	/**
	 * Metoda pro výpis identifikace o tagu
	 */
	@Override
	public String toString() {

		return tag.get();
	}

	/*** Getrs and Setrs **/
	public final String getTag() {
		return tag.get();
	}

	public final void setTag(String name) {
		this.tag.set(name);

	}
}
