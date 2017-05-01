package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MilestoneTable {

	/** Globální proměnné třídy **/
	private StringProperty name;
	private StringProperty criterion;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 * 
	 * @param name
	 *            jméno milestone
	 * @param criterium
	 *            criterio array
	 */
	public MilestoneTable(String name, String criterium) {
		super();
		this.name = new SimpleStringProperty(name);
		this.criterion = new SimpleStringProperty(criterium);

	}
	
	/**
	 * Vypíše info o prvku 
	 */
	@Override
	public String toString() {

		return name.get();
	}

	/** Globální proměnné třídy **/
	public final String getName() {
		return name.get();
	}

	public final void setName(String name) {
		this.name.set(name);

	}

	public final String getCriterion() {
		return criterion.get();
	}

	public final void setCriterion(String name) {
		this.criterion.set(name);

	}

}
