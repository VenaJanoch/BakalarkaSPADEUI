package tables;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BranchTable {

	private StringProperty name;
	private StringProperty main;

	public BranchTable(String name, String main) {
		super();
		this.name = new SimpleStringProperty(name);
		this.main = new SimpleStringProperty(main);
	}

	
	public final String getName() {
		return name.get();
	}

	public final void setName(String name) {
		this.name.set(name);

	}
	
	public final String getMain() {
		return main.get();
	}

	public final void setMain(String main) {
		this.main.set(main);

	}
}
