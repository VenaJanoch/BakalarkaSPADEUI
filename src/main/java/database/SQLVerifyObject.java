package database;

public class SQLVerifyObject {

    private int id;
    private String sqlCommand;
    private boolean exist;

    public SQLVerifyObject(int id, String sqlCommand, boolean exist){
        this.id = id;
        this.sqlCommand = sqlCommand;
        this.exist = exist;
    }

    public int getId() {
        return id;
    }

    public String getSqlCommand() {
        return sqlCommand;
    }

    public boolean isExist() {
        return exist;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
