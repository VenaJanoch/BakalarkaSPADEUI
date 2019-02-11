package tables;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigTable extends BasicTable {

	/**
	 * Globální proměnné třídy
	 **/
	private StringProperty release;
	private IntegerProperty formIndex;


	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 *
	 * @param name    jméno configurace
	 * @param release hodnota release
	 * @param id      identikace
	 */
	public ConfigTable(String name, String release, int formIndex, int id) {
		super(name, id);
		this.release = new SimpleStringProperty(release);
		this.formIndex = new SimpleIntegerProperty(formIndex);

	}

	/**
	 * Vypíše informace o prvku
	 */
	@Override
	public String toString() {

		return getName();
	}

	/**
	 * Getrs and Setrs
	 **/

	public final String getRelease() {
		return release.get();
	}

	public final void setRelease(String release) {
		this.release.set(release);

	}


	public IntegerProperty getIdProperty() {
		return formIndex;
	}
}
