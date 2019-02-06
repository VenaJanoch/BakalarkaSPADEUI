package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MilestoneTable extends BasicTable {

	/** Globální proměnné třídy **/
	private StringProperty criterion;
	private StringProperty description;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 * 
	 * @param name
	 *            jméno milestone
	 * @param description
	 *            description
	 * @param criterium
	 *            criterio array
	 */
	public MilestoneTable(String name, String description, String criterium, int id) {
		super(name, id);
		this.criterion = new SimpleStringProperty(criterium);
		this.description = new SimpleStringProperty(description);
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

	public String getDescription() {
		return description.get();
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public void setDescription(String description) {
		this.description.set(description);
	}
}
