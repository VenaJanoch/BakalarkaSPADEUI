package controllers;

import controlPanels.WorkUnitControlPanel;
import graphics.ComboBoxItem;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import services.SegmentLists;

import java.util.ArrayList;

public class WorkUnitControlPanelController {

    private WorkUnitControlPanel workUnitControlPanel;
    private GridPane controlPane;
    private ArrayList<ComboBoxItem> workUnitBoxis;
    private ArrayList<ComboBoxItem> relationBoxis;
    private SegmentLists segmentLists;
    private int relationRowIndex;
    private int workuUnitRowIndex;

    public WorkUnitControlPanelController(WorkUnitControlPanel workUnitControlPanel, SegmentLists segmentLists){
        this.workUnitControlPanel = workUnitControlPanel;
        this.controlPane = workUnitControlPanel.getControlPane();
        this.workUnitBoxis = new ArrayList<>();
        this.relationBoxis = new ArrayList<>();
        this.segmentLists = segmentLists;
        this.relationRowIndex = 10;
        this.workuUnitRowIndex = 11;
    }

    public void addRelationToPanel(Button editButton, Button addButton, RadioButton radioButton){
      ComboBoxItem  workUnitCB = new ComboBoxItem("Work Unit: ", segmentLists.getWorkUnitsObservable());
      ComboBoxItem  relationCB = new ComboBoxItem("Relation: ", segmentLists.getRelationTypeObservable());
      workUnitBoxis.add(workUnitCB);
      relationBoxis.add(relationCB);

      controlPane.getChildren().remove(addButton);
      controlPane.getChildren().remove(editButton);
      controlPane.getChildren().remove(radioButton);

        relationRowIndex = relationRowIndex + 2;
        workuUnitRowIndex = workuUnitRowIndex +2;
      controlPane.add(relationCB.getItemButton(), 0, relationRowIndex);
      controlPane.add(relationCB.getItemNameLB(), 1, relationRowIndex);
      controlPane.setHalignment(relationCB.getItemNameLB(), HPos.RIGHT);
      controlPane.add(relationCB.getItemCB(), 2, relationRowIndex);
      controlPane.add(workUnitCB.getItemNameLB(), 1, workuUnitRowIndex);
      controlPane.setHalignment(workUnitCB.getItemNameLB(), HPos.RIGHT);
      controlPane.add(workUnitCB.getItemCB(), 2, workuUnitRowIndex);


      controlPane.add(addButton, 1, workuUnitRowIndex + 1);
      controlPane.add(radioButton, 2, workuUnitRowIndex + 2);
      controlPane.add(editButton, 3, workuUnitRowIndex +3);
    }


}
