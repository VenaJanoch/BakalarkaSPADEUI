package services;
/**
 * Trida predstavujici kontajner pro uchovani informaci pro mapu o navaznostech prvku
 *
 * @author Václav Janoch
 */
public class TableToObjectInstanc {
    /**
     * Globalni promenne tridy
     */
    private String name;
    private int id;
    private SegmentType segmentType;

    /**
     * Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param name jmeno prvku
     * @param id identifikator
     * @param segmentType typ prvku
     */
    public TableToObjectInstanc(String name, int id, SegmentType segmentType) {
        this.name = name;
        this.id = id;
        this.segmentType = segmentType;
    }

    public String getName() {
        return segmentType.name() + ": " + name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object object) {
        {
            boolean sameSame = false;

            if (object != null && object instanceof TableToObjectInstanc) {
                sameSame = this.id == ((TableToObjectInstanc) object).id;
            }
            return sameSame;
        }
    }
}
