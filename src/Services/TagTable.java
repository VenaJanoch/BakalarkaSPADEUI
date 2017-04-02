package Services;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TagTable {

	private StringProperty tag;

	@Override
	public String toString() {
	
		return tag.get();
	}
	
	
	public TagTable(String tag) {
		super();
		this.tag = new SimpleStringProperty(tag);
	}

	public final String getTag() {
		return tag.get();
	}

	public final void setTag(String name) {
		this.tag.set(name);
		
	}

}
