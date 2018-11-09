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
	private int id;
	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 * 
	 * @param name
	 *            jméno milestone
	 * @param criterium
	 *            criterio array
	 */
	public MilestoneTable(String name, String criterium, int id) {
		super();
		this.name = new SimpleStringProperty(name);
		this.criterion = new SimpleStringProperty(criterium);
		this.id = id;

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

	public int getId() {
		return id;
	}
}
