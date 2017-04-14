package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CPRTable {

	private StringProperty name;
	private StringProperty role;
	
	public CPRTable(String name, String role) {
		super();
		this.name = new SimpleStringProperty(name);
		this.role = new SimpleStringProperty(role);
	}
	
	
	public final String getName() {
		return name.get();
	}

	public final void setName(String name) {
		this.name.set(name);

	}
	
	public final String getRole() {
		return role.get();
	}

	public final void setRole(String role) {
		this.role.set(role);

	}

}
