package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MilestoneTable {

	private StringProperty name;
	private StringProperty criterion;


	public MilestoneTable(String name, String criterium) {
		super();
		this.name = new SimpleStringProperty(name);
		this.criterion = new SimpleStringProperty(criterium);
		
	}

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
	

	

	@Override
	public String toString() {

		return name.get();
	}

}
