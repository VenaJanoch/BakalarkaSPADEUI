package Controllers;

import graphics.*;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CanvasController {

  //  private Control control;
    private DragAndDropCanvas canvas;
    private boolean arrow;
    private boolean startArrow;

    private ToggleButton linkButton;
    private CanvasItemController canvasItemController;
    private ItemContexMenu itemContexMenu;
    private ManipulationController manipulationController;
    private FormController formController;

    private CanvasType canvasType;

    private Map<Integer, CanvasItem> listOfItemOnCanvas = new HashMap<>();

    public CanvasController(CanvasType canvasType, ApplicationController applicationController) { // todo je potreba canvasType? Smazat

        this.canvas = new DragAndDropCanvas(this);
        this.canvasType = canvasType;
        this.canvas.setOnKeyPressed(event -> pressESCAction());
        this.canvasItemController = applicationController.getCanvasItemController();
        this.manipulationController = applicationController.getManipulationController();
        this.itemContexMenu = new ItemContexMenu(manipulationController,this,canvasItemController);
        this.formController = applicationController.getFormController();
        this.linkButton = new ToggleButton();
    }


    public void keyPressAction(KeyEvent event) {
        if (Constans.controlV.match(event)) {
            pasteItem();
        } else if (Constans.controlC.match(event)) {
            copyItem();
        } else if (Constans.controlX.match(event)) {
            cutItem();
        } else if (event.getCode() == KeyCode.DELETE) {

            if (manipulationController.getChooseCanvasItem() != null) {
                manipulationController.deleteItem(canvasItemController);
            }

        } else if (event.getCode() == KeyCode.ESCAPE) {
            if (manipulationController.getLink() != null) {
               manipulationController.getLink().coverBackgroundPolygon();
            }
        }

    }

    public void addLinkToCanvas(NodeLink link){
        canvas.getCanvas().getChildren().add(link);
    }

    public void addPolygonToCanvas(Polygon backgroundPlygon){
        canvas.getCanvas().getChildren().add(backgroundPlygon);
    }


    public CanvasItem addCanvasItemFromPanel(String segment, double x, double y) {

        SegmentType type = Control.findSegmentType(segment);

        int formIndex = getFormIndexFromNewForm(type, canvasType);
        String segmentId = createSegmentId(type, formIndex);
        CanvasItem item = canvasItemController.createCanvasItem(type,segmentId,formIndex,"New", x, y, this);
        canvas.getCanvas().getChildren().add(item);
        listOfItemOnCanvas.put(formIndex, item);
        return item;

    }

    public CanvasItem addCanvasItemFromExistData(SegmentType type, int formIndex, String name, double x, double y){
        String segmentId = createSegmentId(type, formIndex);
        CanvasItem item = canvasItemController.createCanvasItem(type,segmentId,formIndex,name, x, y, this);
        canvas.getCanvas().getChildren().add(item);
        listOfItemOnCanvas.put(formIndex, item);
        return item;
    }

    public CanvasItem addCanvasItemFromExistData(SegmentType type, int formIndex, String name, double x, double y, boolean isExist){
        CanvasItem item  =  addCanvasItemFromExistData(type, formIndex, name, x,  y);
        formController.setItemColor(formIndex, isExist);
        return item;
    }

    public String createSegmentId(SegmentType type, int formIndex) {

        int id = formController.getSegmetIdFromFromId(type,formIndex);
        String number = String.format("%03d", id);

        return type.name() +  "_" +  number;

    }

    private int getFormIndexFromNewForm(SegmentType segmentType, CanvasType  canvasType){

        return formController.createNewForm(segmentType, canvasType);
    }

    public void addCopyCanvasItemToCanvas(SegmentType segmentType, double x, double y) {

        int formIndex = getFormIndexFromNewForm(segmentType, canvasType);
        CanvasItem item = canvasItemController.createCanvasItem(segmentType,"d",formIndex,"New", x, y, this);
        canvas.getCanvas().getChildren().add(item);
    }

    public void dragAndDrop(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString()) {

            addCanvasItemFromPanel(db.getString(), event.getSceneX(), event.getSceneY());
            success = true;
        }

        event.setDropCompleted(success);
        event.consume();
    }

    public void dragAndOver(DragEvent event) {
        if (event.getGestureSource() != this && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro vložení prvku
     */

    private void pasteItem() {
        manipulationController.pasteItem(this, canvasItemController);
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro kopírování prvku
     */
    private void copyItem() {
        manipulationController.copyItem(this);
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro vyjmutí prvku
     */
    private void cutItem() {
        manipulationController.cutItem(this, canvasItemController);
    }

    /**
     * Metoda rozhodující o aktuálním modu
     */

    public boolean changeArrow() {

        if (arrow) {

            arrow = false;
        } else {
            arrow = true;
            startArrow = false;
        }

        return arrow;

    }


    /**
     * Kontrolní metoda pro kontrolu dvojkliku
     *
     * @param t
     */
    public void setClicFromDragPoint(MouseEvent t) {

        if (t.getButton().equals(MouseButton.SECONDARY)) {

            manipulationController.setChooseCanvas(canvas);
            itemContexMenu.show(canvas.getCanvas(), t.getScreenX(), t.getScreenY());
        }else {
            manipulationController.controlCopyItem();
        }

    }

    /**
     * MouseEvent Handler pro reakci na stisknutí tlačítka myši a zavolání
     * kontrolní metody pro vyvolání kontextového okna
     */
    public EventHandler<MouseEvent> getOnMousePressedHandler() {

        EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                setClicFromDragPoint(t);
            }
        };
        return OnMousePressedEventHandler;
    }

    public void pressESCAction() {
        arrow = false;
        startArrow = false;
        canvas.setCursor(Cursor.DEFAULT);
        linkButton.setSelected(false);
    }

    public void setCursorToCanvas(Cursor cursor) {
        canvas.setCursor(cursor);
    }

    public void getItemContexMenu(CanvasItem canvasItem, MouseEvent t) {
        manipulationController.setChooseCanvasItem(canvasItem);
        itemContexMenu.show(canvas, t.getScreenX(), t.getScreenY());
    }

    public void addRelationCBToCanvas(LineComboBox relationCB, Polygon polygon) {

        canvas.getCanvas().getChildren().addAll(relationCB, polygon);
    }

    /** Getrs and Setrs **/

    public Map<Integer, CanvasItem> getListOfItemOnCanvas() {
        return listOfItemOnCanvas;
    }

    public ToggleButton getLinkButton() {
        return linkButton;
    }

    public boolean isArrow() {
        return arrow;
    }

    public boolean isStartArrow() {
        return startArrow;
    }

    public void setStartArrow(boolean startArrow) {
        this.startArrow = startArrow;
    }

    public CanvasType getCanvasType() {
        return canvasType;
    }

    public DragAndDropCanvas getCanvas() {
        return canvas;
    }


}



