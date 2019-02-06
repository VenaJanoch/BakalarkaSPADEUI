package services;

public class TableToObjectInstanc {
    private String name;
    private int id;
    private SegmentType segmentType;

    public TableToObjectInstanc(String name, int id, SegmentType segmentType){
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
}
