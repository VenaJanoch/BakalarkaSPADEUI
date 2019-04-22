package tables;

import javafx.beans.property.StringProperty;

public class ProjectTable extends BasicTable {

	/** Globální proměnné třídy **/
	private StringProperty description;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnnné třídy
	 *
	 * @param name
	 *            jméno milestone

	 *
	 */
	public ProjectTable(String name, int id) {
		super(name, true, id);

	}
	
	/**
	 * Vypíše info o prvku 
	 */
	@Override
	public String toString() {

		return getAlias();
	}

	/** Globální proměnné třídy **/

}
