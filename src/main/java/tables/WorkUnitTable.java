package tables;

public class WorkUnitTable extends BasicTable {
    public WorkUnitTable(String name, int id) {
        super(name, id);
    }
    /**
     * Vypíše info o prvku
     */
    @Override
    public String toString() {

        return getName();
    }

}
