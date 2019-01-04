package tables;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BranchTable extends BasicTable{

	/** Globální proměnné třídy **/
	private StringProperty main;

	/**
	 * Konstruktor třídy 
	 * Zinicializuje globální proměnné
	 * @param name jméno branch
	 * @param main hodnota main
	 */
	
	public BranchTable(String name, String main, int id) {
		super(name, id);
		this.main = new SimpleStringProperty(main);
	}

	/**
	 * Vypíše informace o prvku
	 */
	@Override
	public String toString() {

		return getName();
	}
	
	/** Getrs and Setrs **/

	public String getMain() {
		return main.get();
	}

	public StringProperty mainProperty() {
		return main;
	}


}
