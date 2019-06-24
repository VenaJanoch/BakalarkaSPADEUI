package services;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import tables.BasicTable;

/**
 * Třída predstavujici kontajner pro uchovani informaci o typu editacniho panelu
 *
 * @author Václav Janoch
 */
public class ControlPanelLineObject {

    /**
     * Globalni promenne tridy
     */
    private String name;
    private ControlPanelLineType lineType;
    private ObservableList segmentsList;
    private ObservableList segmentsList2;
    private ParamType type;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * @param name jmeno
     * @param lineType typ radku
     * @param type parametry radku
     */
    public ControlPanelLineObject(String name, ControlPanelLineType lineType, ParamType type) {
        this.name = name;
        this.lineType = lineType;
        this.type = type;
    }

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * @param name jmeno
     * @param lineType typ radku
     * @param type parametry radku
     * @param segmentsList list pro vyberovy combobox
     */
    public ControlPanelLineObject(String name, ControlPanelLineType lineType, ParamType type, ObservableList segmentsList) {
        this(name, lineType, type);
        this.segmentsList = segmentsList;
        this.type = type;
    }

/**
 * Konstruktor tridy, zinicializuje globalni promenne tridy
 * @param name jmeno
 * @param lineType typ radku
 * @param type parametry radku
 * @param segmentsList prvni seznam pro vyberovy combobox
 * @param segmentsList2 druhy seznam pro vyberovy combobox
 */
    public ControlPanelLineObject(String name, ControlPanelLineType lineType, ParamType type, ObservableList segmentsList, ObservableList segmentsList2) {
        this(name, lineType, type, segmentsList);
        this.segmentsList2 = segmentsList2;
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

    public ObservableList getSegmentsList2() {
        return segmentsList2;
    }

    @Override
    public String toString() {
        return name;
    }
}
