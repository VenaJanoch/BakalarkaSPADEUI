package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PhaseTable extends BasicTable {

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
	public PhaseTable(String name,  boolean exist, int id) {
		super(name, exist, id);
//		this.milestone = new SimpleStringProperty(milestone);
//		this.configuration = new SimpleStringProperty(configuration);
	}
	
	/**
	 * Vypíše info o prvku 
	 */
	@Override
	public String toString() {

		return getName();
	}

	/** Globální proměnné třídy **/

	public final String getMilestone() {
		return milestone.get();
	}

	public final void setMilestone(String name) {
		this.milestone.set(name);

	}

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
