package tables;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CriterionTable {

	private StringProperty name;
	private StringProperty description;

	public CriterionTable(String name, String description) {
		super();
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
	}

	
	@Override
	public String toString() {

		return name.get();
	}
	
	
	
	
	
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
}
