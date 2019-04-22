package tables;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BasicTable {
    private int id;
    private StringProperty idString;
    private StringProperty existString;
    private StringProperty alias;
    private boolean exist;

    public BasicTable(String alias, boolean exist, int id){
        this.id = id;
        this.idString = new SimpleStringProperty(Integer.toString(id));
        this.exist = exist;
        this.setStringExist(exist);
        this.alias = new SimpleStringProperty(alias);
    }

    public void setStringExist(boolean exist){
        if (exist){
            existString = new SimpleStringProperty("Yes");
        }else{
            existString = new SimpleStringProperty("No");
        }
    }

    public int getId() {
        return id;
    }

    public final String getAlias() {
        return alias.get();
    }

    public final void setAlias(String name) {
        this.alias.set(name);
    }

    public String getIdString() {
        return idString.get();
    }

    public StringProperty idStringProperty() {
        return idString;
    }

    public String getExistString() {
        return existString.get();
    }

    public StringProperty existStringProperty() {
        return existString;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
       setStringExist(exist);
    }

    public boolean isExist() {
        return exist;
    }
}

