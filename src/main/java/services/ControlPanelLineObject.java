package services;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import tables.BasicTable;

public class ControlPanelLineObject {

    private String name;
    private ControlPanelLineType lineType;
    private ObservableList<BasicTable> segmentsList;
    private ParamType type;

    public ControlPanelLineObject(String name, ControlPanelLineType lineType, ParamType type){
        this.name = name;
        this.lineType = lineType;
        this.type = type;
    }

    public ControlPanelLineObject(String name, ControlPanelLineType lineType, ParamType type, ObservableList<BasicTable> segmentsList){
        this(name, lineType, type);
        this.segmentsList = segmentsList;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ControlPanelLineType getLineType() {
        return lineType;
    }

    public ObservableList<BasicTable> getSegmentsList() {
        return segmentsList;
    }

    public ParamType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
