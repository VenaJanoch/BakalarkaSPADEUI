package tables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClassTable {

	/** Globální proměnné třídy **/
	private StringProperty name;
	private StringProperty classType;
	private StringProperty superType;
	
	
	/**
	 * Konstruktor třídy
	 * @param name jméno výčtového typu
	 * @param classType class
	 * @param superType super class
	 */
	public ClassTable(String name, String classType, String superType) {
		super();
		this.name = new SimpleStringProperty(name);
		this.classType = new SimpleStringProperty(classType);
		this.superType = new SimpleStringProperty(superType);
		
	}
	

	/**
	 * Vypíše informace o prvku
	 */
	@Override
	public String toString() {

		return name.get();
	}

	
	/** Getrs and Setrs */
	public final String getName() {
		return name.get();
	}

	public final void setName(String name) {
		this.name.set(name);

	}
	
	public final String getClassType() {
		return classType.get();
	}

	public final void setClassType(String classType) {
		this.classType.set(classType);

	}
	
	public final String getSuperType() {
		return superType.get();
	}

	public final void setSuperType(String superType) {
		this.superType.set(superType);

	}



}
