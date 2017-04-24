package tables;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConfigTable {

	private StringProperty name;
	private StringProperty release;
	private IntegerProperty id;

	public ConfigTable(String name, String release, int id) {
		super();
		this.name = new SimpleStringProperty(name);
		this.release = new SimpleStringProperty(release);
		this.id = new SimpleIntegerProperty(id);
		
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
	
	public final String getRelease() {
		return release.get();
	}

	public final void setRelease(String release) {
		this.release.set(release);

	}


	public IntegerProperty getId() {
		return id;
	}


	public void setId(IntegerProperty id) {
		this.id = id;
	}
}
