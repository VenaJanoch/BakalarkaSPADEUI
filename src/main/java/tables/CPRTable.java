package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CPRTable extends BasicTable{

	/** Globální proměnné třídy **/ 
	private StringProperty role;
	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 * @param name jméno CPR
	 * @param role jméno vybrané role
	 */
	public CPRTable(String name, String role, int id) {
		super(name, id);
		this.role = new SimpleStringProperty(role);
	}
	
	/**
	 * Vypíše identifikaci o prvku
	 */
	@Override
	public String toString() {

		return getName();
	}
	
	/** Getrs and Setrs **/
	public final String getRole() {
		return role.get();
	}

	public final void setRole(String role) {
		this.role.set(role);

	}
}
