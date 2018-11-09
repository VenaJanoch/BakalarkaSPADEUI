package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CPRTable {

	/** Globální proměnné třídy **/ 
	private StringProperty name;
	private StringProperty role;
	private int id;
	
	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 * @param name jméno CPR
	 * @param role jméno vybrané role
	 */
	public CPRTable(String name, String role, int id) {
		super();
		this.name = new SimpleStringProperty(name);
		this.role = new SimpleStringProperty(role);
		this.id = id;
	}
	
	/**
	 * Vypíše identifikaci o prvku
	 */
	@Override
	public String toString() {

		return name.get();
	}
	
	/** Getrs and Setrs **/
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

	public int getId() {
		return id;
	}
}
