package database;

public class SQLVerifyObject {

    private int id;
    private String sqlCommand;
    private boolean exist;
    private int modelCount;
    private int projectCount;

    public SQLVerifyObject(int id, String sqlCommand, boolean exist) {
        this.id = id;
        this.sqlCommand = sqlCommand;
        this.exist = exist;
    }

    public SQLVerifyObject(int id, String sqlCommand, boolean exist, int modelCount, int projectCount) {
        this.id = id;
        this.sqlCommand = sqlCommand;
        this.exist = exist;
        this.modelCount = modelCount;
        this.projectCount = projectCount;
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

    public int getModelCount() {
        return modelCount;
    }

    public int getProjectCount() {
        return projectCount;
    }
}
