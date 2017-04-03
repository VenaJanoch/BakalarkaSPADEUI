package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RoleTypeTable {

	private StringProperty name;
	private StringProperty classType;
	private StringProperty superClassType;

	public RoleTypeTable(String name, String classType, String superClass) {
		super();
		this.name = new SimpleStringProperty(name);
		this.classType = new SimpleStringProperty(classType);
		this.superClassType = new SimpleStringProperty(superClass);
	}

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

	public final String getSuperClassType() {
		return superClassType.get();
	}

	public final void setSuperClassType(String superClassType) {
		this.superClassType.set(superClassType);

	}

	@Override
	public String toString() {

		return name.get();
	}

}
