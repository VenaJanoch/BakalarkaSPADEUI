package tables;

/**
 * Trida predstavujici kontejner pro tabulku elementu WorkUnit
 *
 * @author Václav Janoch
 */
public class WorkUnitTable extends BasicTable {

    /**
     * Konstruktor třídy Zinicializuje globální proměnnné třídy
     * @param name alias prvku
     * @param exist existence prvku
     * @param id identifikator prvku
     */
    public WorkUnitTable(String name, boolean exist, int id) {
        super(name, exist, id);
    }

    /**
     * Vypíše info o prvku
     */
    @Override
    public String toString() {

        return getAlias();
    }

}
