package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IterationTable extends BasicTable {

	/** Globální proměnné třídy **/
	private StringProperty milestone;
	private StringProperty configuration;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 *
	 * @param name
	 *            jméno milestone

	 *
	 */
	public IterationTable(String name, String configuration, int id) {
		super(name, id);
		this.configuration = new SimpleStringProperty(configuration);
	}
	
	/**
	 * Vypíše info o prvku 
	 */
	@Override
	public String toString() {

		return getName();
	}

	/** Globální proměnné třídy **/

	public String getConfiguration() {
		return configuration.get();
	}

	public StringProperty configurationProperty() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration.set(configuration);
	}
}
