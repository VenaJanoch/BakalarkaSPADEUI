package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MilestoneTable extends BasicTable {

	/** Globální proměnné třídy **/
	private StringProperty criterion;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 * 
	 * @param name
	 *            jméno milestone
	 * @param criterium
	 *            criterio array
	 */
	public MilestoneTable(String name, String criterium, int id) {
		super(name, id);
		this.criterion = new SimpleStringProperty(criterium);
	}
	
	/**
	 * Vypíše info o prvku 
	 */
	@Override
	public String toString() {

		return getName();
	}

	/** Globální proměnné třídy **/

	public final String getCriterion() {
		return criterion.get();
	}

	public final void setCriterion(String name) {
		this.criterion.set(name);

	}
}
