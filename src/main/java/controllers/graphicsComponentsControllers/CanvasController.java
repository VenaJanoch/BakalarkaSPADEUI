package controllers.graphicsComponentsControllers;

import SPADEPAC.ConfigPersonRelation;
import com.jfoenix.controls.JFXDrawer;
import controllers.ApplicationController;
import controllers.formControllers.FormController;
import controllers.formControllers.ManipulationController;
import graphics.canvas.CanvasItem;
import graphics.canvas.DragAndDropCanvas;
import graphics.canvas.NodeLink;
import graphics.canvas.ItemContexMenu;
import graphics.controlPanelItems.LineComboBox;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import services.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;


public class CanvasController {

    private DragAndDropCanvas canvas;
    private boolean arrow;
    private boolean startArrow;

    private ToggleButton linkButton;
    private CanvasItemController canvasItemController;
    private ItemContexMenu itemContexMenu;
    private ManipulationController manipulationController;
    private FormController formController;
    private SelectionController selectionController;

    private CanvasType canvasType;

    private Map<Integer, CanvasItem> listOfItemOnCanvas = new HashMap<>();
    private int newFormIndex;
    private double rectFishWidth = 0;

    /**
     * Konstrukor tridy
     * Zavola zakladni konstruktor tridy a nastavi potrebne globalni promenne
     * @param canvasType
     * @param leftDrawerPanel
     * @param rightDrawerPanel
     * @param applicationController
     */
    public CanvasController(CanvasType canvasType, JFXDrawer leftDrawerPanel, JFXDrawer rightDrawerPanel, ApplicationController applicationController) {
        this(canvasType, applicationController);
        this.canvas = new DragAndDropCanvas(this);//, leftDrawerPanel, rightDrawerPanel);
    }

    /**
     * Konstrukor tridy, nastavi potrebne globalni promenne
     * @param canvasType
     * @param applicationController
     */
    public CanvasController(CanvasType canvasType, ApplicationController applicationController) {

        this.canvas = new DragAndDropCanvas(this);
        this.canvasType = canvasType;
        this.canvasItemController = applicationController.getCanvasItemController();
        this.manipulationController = applicationController.getManipulationController();
        this.itemContexMenu = new ItemContexMenu(manipulationController, this, canvasItemController);
        this.formController = applicationController.getFormController();
        this.linkButton = new ToggleButton();
        this.selectionController = applicationController.getSelectionController();

    }

    /**
     * Pomocna metoda pro reakci na stisk klavesy ESC
     */
    public void pressESCAction() {
        arrow = false;
        startArrow = false;
        canvas.setCursor(Cursor.DEFAULT);
        linkButton.setSelected(false);
        selectionController.clear();
    }
    /**
     * Metoda pro reakci na klavesove zkratky
     * Umoznuje rekci na crtl+v, ctrl+c, ctrl+x, delete a escape
     * @param event KeyEvent
     */
    public void keyPressAction(KeyEvent event) {
        if (Constans.controlV.match(event)) {
            pasteItem();
        } else if (Constans.controlC.match(event)) {
            copyItem();
        } else if (Constans.controlX.match(event)) {
            cutItem();
        } else if (event.getCode() == KeyCode.DELETE) {

            if (selectionController.getSelection() != null) {
                manipulationController.deleteItem(canvasItemController, this);
            }

        } else if (event.getCode() == KeyCode.ESCAPE) {
            if (manipulationController.getLink() != null) {
                manipulationController.getLink().coverBackgroundPolygon();
               selectionController.clear();
            }
            pressESCAction();
        }

    }

    /**
     * Pomocna metoda pro pridani spojnice do platna
     * @param link instance tridy NodeLink
     */
    public void addLinkToCanvas(NodeLink link) {
        canvas.getCanvas().getChildren().add(link);
    }

    /**
     * Pomocna metoda pro pridani polygonu okolo spojnice
     * @param backgroundPlygon instace tridy Polygon
     */
    public void addPolygonToCanvas(Polygon backgroundPlygon) {
        canvas.getCanvas().getChildren().add(backgroundPlygon);
    }

    /**
     * Metoda pro pridani prvku platna na platno horniho panelu
     * @param segment String Jmeno pridavaneho prvku
     * @param x poloha prvku
     * @param y poloha prvku
     * @return CanvasItem nova instace tridz CanvasItem
     */
    public CanvasItem addCanvasItemFromPanel(String segment, double x, double y) {

        SegmentType type = findSegmentType(segment);

        CanvasItem canvasItem = addCanvasItemFromPanel(type, x + Constans.NEW_INSTACE_OFFSET, y + Constans.NEW_INSTACE_OFFSET);
        return canvasItem;
    }

    /**
     * Metoda pro pridani prvku platna na platno horniho panelu
     * @param type SegmentType typ pridavaneho prvku
     * @param x poloha prvku
     * @param y poloha prvku
     * @return CanvasItem nova instace tridz CanvasItem
     */
    public CanvasItem addCanvasItemFromPanel(SegmentType type, double x, double y) {

        newFormIndex = getFormIndexFromNewForm(type, canvasType);
        String segmentId = createSegmentId(type, newFormIndex);
        CanvasItem item = canvasItemController.createCanvasItem(type, segmentId, newFormIndex, "New", 1, 0, x, y, this);
        canvas.getCanvas().getChildren().add(item);
        listOfItemOnCanvas.put(newFormIndex, item);
        return item;

    }


    /**
     * Metoda pro pridani prvku na platno
     * @param type SegmetType
     * @param formIndex identificator
     * @param name jmeno prvku
     * @param x x souradnice
     * @param y y souradncie
     * @param instanceCount pocet instanci
     * @param countIndicator pocet ukazatelu
     * @return CanvaItem
     */
    public CanvasItem addCanvasItemFromExistData(SegmentType type, int formIndex, String name, double x, double y, int instanceCount, int countIndicator) {
        String segmentId = createSegmentId(type, formIndex);
        CanvasItem item = canvasItemController.createCanvasItem(type, segmentId, formIndex, name, instanceCount, countIndicator, x, y, this);
        canvas.getCanvas().getChildren().add(item);
        listOfItemOnCanvas.put(formIndex, item);
        return item;
    }

    /**
     * Metoda pro pridani prvku na platno z existujicich dat ulozenych v XML
     * @param type SegmetType
     * @param formIndex identificator
     * @param name jmeno prvku
     * @param x x souradnice
     * @param y y souradncie
     * @param instanceCount pocet instanci
     * @param countIndicator pocet ukazatelu
     * @param isExist existence prvku
     * @return CanvasItem
     */
    public CanvasItem addCanvasItemFromExistData(SegmentType type, int formIndex, String name, double x, double y, int instanceCount, int countIndicator, boolean isExist) {
        CanvasItem item = addCanvasItemFromExistData(type, formIndex, name, x, y, instanceCount, countIndicator);
        formController.setItemColor(formIndex, isExist);
        return item;
    }

    /**
     * Metoda pro vytovreni identificatoru jednotlivych prvku platn
     * @param type instatace tridy SegmentType
     * @param formIndex identificator instace datoveho modelu, ktery bude prvek platna predstavovat
     * @return String identificator
     */
    public String createSegmentId(SegmentType type, int formIndex) {

        int id = formController.getSegmetIdFromFormId(type, formIndex);
        String number = String.format("%03d", id);

        return type.name() + "_" + number;

    }

    private int getFormIndexFromNewForm(SegmentType segmentType, CanvasType canvasType) {

        return formController.createNewForm(segmentType, canvasType);
    }

    /**
     * Metoda pro reakci na drag and drop pridani prvku z platna
     * @param event DragEvent
     */
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

    /**
     * Pomocna metoda pro ukonceni Drag and Drop
     * @param event instace tridz DragEvent
     */
    public void dragAndOver(DragEvent event) {
        if (event.getGestureSource() != this && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro vložení prvku
     */

    public void pasteItem() {

        double newX;
        double newY;

            if (selectionController.getSelection().size() > 1){
                for (CanvasItem canvasItem : selectionController.getSelection()){
                    newX = canvasItem.getTranslateX() + canvasItem.getWidth() + rectFishWidth;
                    newY = canvasItem.getTranslateY() + canvasItem.getHeight() + Constans.NEW_INSTACE_OFFSET;
                    manipulationController.pasteItem(this, canvasItemController, newX, newY, canvasItem);
                }

            }else {
                Set selection = selectionController.getSelection();

                if (selection.size() != 0){
                    CanvasItem canvasItem = selectionController.getSelection().iterator().next();
                    newX = canvasItem.getTranslateX() + canvasItem.getWidth() + Constans.NEW_INSTACE_OFFSET;
                    newY = canvasItem.getTranslateY() + canvasItem.getHeight() + Constans.NEW_INSTACE_OFFSET;
                    manipulationController.pasteItem(this, canvasItemController, newX, newY, canvasItem);
                }
            }



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



    /**
     * Metoda pro nastaveni nove podoby kurzoru na platne
     * @param cursor instace tridy Cursor
     */
    public void setCursorToCanvas(Cursor cursor) {
        canvas.setCursor(cursor);
    }

    public void getItemContexMenu(CanvasItem canvasItem, MouseEvent t) {
        itemContexMenu.show(canvas, t.getScreenX(), t.getScreenY());
    }

    public void addRelationCBToCanvas(LineComboBox relationCB, Polygon polygon) {

        canvas.getCanvas().getChildren().addAll(relationCB, polygon);
    }

    /**
     * * Metoda pro ziskani Eventhandleru pro reakci na stisk mysi
     * @param dragContext pocatecni bod
     * @param rect instace tridy Rectangle
     * @param canvas instance tridy AnchorPane
     * @return EventHandler
     */
    public EventHandler<MouseEvent> getOnMousePressedEventHandler(DragContext dragContext, Rectangle rect, AnchorPane canvas) {
        EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    dragContext.mouseAnchorX = event.getX();// event.getSceneX();
                    dragContext.mouseAnchorY = event.getY();//

                    rect.setX(dragContext.mouseAnchorX);
                    rect.setY(dragContext.mouseAnchorY);
                    rect.setWidth(0);
                    rect.setHeight(0);

                    canvas.getChildren().add(rect);

                    event.consume();
                }
            }
        };
        return onMousePressedEventHandler;
    }

    /**
     * Metoda pro ziskani EventDandleru pro reakci na release udalosti
     * @param rect instace tridy Rectangle
     * @param canvas instace tridy AnchorPane
     * @return EventHandler
     */
    public EventHandler<MouseEvent> getOnMouseReleasedEventHandler(Rectangle rect, AnchorPane canvas) {
        EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (!event.isShiftDown() && !event.isControlDown()) {
                    selectionController.clear();
                }

                for (Node node : canvas.getChildren()) {

                    if (node instanceof CanvasItem) {
                        CanvasItem item = (CanvasItem) node;
                        if (node.getBoundsInParent().intersects(rect.getBoundsInParent())) {

                            if (event.isShiftDown()) {

                                selectionController.add(item);

                            } else if (event.isControlDown()) {

                                if (selectionController.contains(item)) {
                                    selectionController.remove(item);
                                } else {
                                    selectionController.add(item);
                                }
                            } else {
                                selectionController.add(item);
                            }

                        }
                    }

                }

             //   selectionController.log();

               removeRectangle();

                event.consume();
            }
            }
        };

        return onMouseReleasedEventHandler;
    }

   public void removeRectangle(){
        Rectangle rect = canvas.getRect();
        rectFishWidth = rect.getWidth();

        rect.setX(0);
        rect.setY(0);
        rect.setWidth(0);
        rect.setHeight(0);

        canvas.getCanvas().getChildren().remove(rect);

    }
    /**
     * Metoda pro ziskani Eventhandleru pro reakci na dragAndDrop
     * @param dragContext pocatecni bod ze ktereho se bude vykreslovat ctverec
     * @param rect insatace tridy Rectangle
     * @return EventHadler
     */
    public EventHandler<MouseEvent> getOnMouseDraggedEventHandler(DragContext dragContext, Rectangle rect) {
        EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (!manipulationController.isChoosedItem()) {
                        double offsetX = event.getSceneX() - dragContext.mouseAnchorX;
                        double offsetY = event.getSceneY() - dragContext.mouseAnchorY;

                        if (offsetX > 0)
                            rect.setWidth(offsetX);
                        else {
                            rect.setX(event.getSceneX());
                            rect.setWidth(dragContext.mouseAnchorX - rect.getX());
                        }

                        if (offsetY > 0) {
                            rect.setHeight(offsetY);
                        } else {
                            rect.setY(event.getSceneY());
                            rect.setHeight(dragContext.mouseAnchorY - rect.getY());
                        }

                        event.consume();

                    }
                }
            }
        };

        return onMouseDraggedEventHandler;
    }

    public void clearCanvas() {
        canvas.clearCanvas();
    }

    /**
     * Pomocná metoda pro určení výčtového typu SegmentType pomocí Stringu
     *
     * @param segmentName String s názvem segmentu
     * @return SegmentType
     */
    public SegmentType findSegmentType(String segmentName) {

        for (int i = 0; i < SegmentType.values().length; i++) {

            if (SegmentType.values()[i].name().equals(segmentName)) {

                return SegmentType.values()[i];
            }

        }
        return null;

    }


    /**
     * Getrs and Setrs
     **/

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


    public void removeCanvasItem(CanvasItem chooseCanvasItem) {
        AnchorPane canvas = getCanvas().getCanvas();
        canvas.getChildren().remove(chooseCanvasItem);
        removeRectangle();
        selectionController.clear();
    }
}



