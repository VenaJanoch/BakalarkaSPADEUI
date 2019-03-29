package tables;

public class WorkUnitTable extends BasicTable {
    public WorkUnitTable(String name,  boolean exist, int id) {
        super(name, exist, id);
    }
    /**
     * Vypíše info o prvku
     */
    @Override
    public String toString() {

        return getName();
    }

}
