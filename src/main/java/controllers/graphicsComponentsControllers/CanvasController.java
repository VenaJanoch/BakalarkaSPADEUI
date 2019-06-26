package controllers.graphicsComponentsControllers;

import com.jfoenix.controls.JFXDrawer;
import controllers.ApplicationController;
import controllers.LinkControl;
import controllers.formControllers.FormController;
import controllers.formControllers.ManipulationController;
import graphics.canvas.*;
import graphics.controlPanelItems.LineComboBox;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import services.CanvasType;
import services.Constans;
import services.DragContext;
import services.SegmentType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Trida predstavujici controller pro graficky prvek CanvasController
 * Predstavujici modelovaci platno
 *
 * @author Václav Janoch
 */
public class CanvasController {

    /**
     * Graficke prvky obsazene v objektu platna
     **/
    private DragAndDropCanvas canvas;
    private ToggleButton linkButton;
    private ItemContexMenu itemContexMenu;
    private Background background;
    private Background background2;
    private Image image;
    private Image image2;

    /**
     * Promenna pro urceni stavy pro kresleni
     **/
    private boolean arrow;
    /**
     * Promenna pro urceni pocatecniho stavu kreseleni spojnice
     **/
    private boolean startArrow;
    /**
     * Promenna pro urceni stisku tlacitka
     **/
    private boolean isPressLinkButton = true;

    /**
     * Promenne predstavujic kontrolenery
     **/
    private CanvasItemController canvasItemController;
    private ManipulationController manipulationController;
    private FormController formController;
    private SelectionController selectionController;
    /**
     * Promenna pro urceni typu platna
     **/
    private CanvasType canvasType;

    private Map<Integer, CanvasItem> listOfItemOnCanvas = new HashMap<>();
    private int newFormIndex;
    private double rectFishWidth = 0;


    /**
     * Konstrukor tridy
     * Zavola zakladni konstruktor tridy a nastavi potrebne globalni promenne
     *
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
     *
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
        this.loadLinkButtonImage();

    }

    /**
     * Metoda pro nacteni obrazku predstavujici ikonku spojnice
     */
    public void loadLinkButtonImage() {
        FileInputStream input = null;
        FileInputStream input2 = null;
        try {
            input = new FileInputStream(Constans.SIPKA);
            input2 = new FileInputStream(Constans.SIPKA2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        image = new Image(input);
        image2 = new Image(input2);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        background = new Background(backgroundImage);
        background2 = new Background(backgroundImage2);

        linkButton.setId("linkButton");
        linkButton.setFont(Font.font("Verdana", 25));
        linkButton.setOnAction(event -> createArrowButtonEvent());

    }


    /**
     * Metoda pro nastaveni potrebneho vzhledu tlacitka predstavujici ikonku spojnice
     *
     * @param background instace tridy Background
     * @param image      instace tridy image
     */
    public void setLinkButtonBackground(Background background, Image image) {
        linkButton.setMinWidth(image.getWidth());
        linkButton.setMinHeight(image.getHeight());
        linkButton.setBackground(background);
    }

    /**
     * Pomocná metoda pro nastavaní reakce na stisk tlačítka pro přepnutí modu
     */
    public void createArrowButtonEvent() {

        if (changeArrow()) {
            setCursorToCanvas(Cursor.CROSSHAIR);
            setLinkButtonBackground(background2, image2);
            linkButton.setCursor(Cursor.DEFAULT);
            isPressLinkButton = false;
        } else {
            setCursorToCanvas(Cursor.DEFAULT);
            setLinkButtonBackground(background, image);
            isPressLinkButton = true;
        }
    }

    /**
     * Pomocna metoda pro reakci na stisk klavesy ESC
     */
    public void pressESCAction() {
        if (!isPressLinkButton) {
            createArrowButtonEvent();
            selectionController.clear();
        }


    }

    /**
     * Metoda pro reakci na klavesove zkratky
     * Umoznuje rekci na crtl+v, ctrl+c, ctrl+x, delete a escape
     *
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
     *
     * @param link instance tridy NodeLink
     */
    public void addLinkToCanvas(NodeLink link) {
        canvas.getCanvas().getChildren().add(link);
    }

    /**
     * Pomocna metoda pro pridani polygonu okolo spojnice
     *
     * @param backgroundPlygon instace tridy Polygon
     */
    public void addPolygonToCanvas(Polygon backgroundPlygon) {
        canvas.getCanvas().getChildren().add(backgroundPlygon);
    }

    /**
     * Metoda pro pridani prvku platna na platno horniho panelu
     *
     * @param segment String Jmeno pridavaneho prvku
     * @param x       poloha prvku
     * @param y       poloha prvku
     * @return CanvasItem nova instace tridz CanvasItem
     */
    public CanvasItem addCanvasItemFromPanel(String segment, double x, double y) {

        SegmentType type = findSegmentType(segment);

        CanvasItem canvasItem = addCanvasItemFromPanel(type, x + Constans.NEW_INSTACE_OFFSET, y + Constans.NEW_INSTACE_OFFSET);
        return canvasItem;
    }

    /**
     * Metoda pro pridani prvku platna na platno horniho panelu
     *
     * @param type SegmentType typ pridavaneho prvku
     * @param x    poloha prvku
     * @param y    poloha prvku
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
     *
     * @param type           SegmetType
     * @param formIndex      identificator
     * @param name           jmeno prvku
     * @param x              x souradnice
     * @param y              y souradncie
     * @param instanceCount  pocet instanci
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
     *
     * @param type           SegmetType
     * @param formIndex      identificator
     * @param name           jmeno prvku
     * @param x              x souradnice
     * @param y              y souradncie
     * @param instanceCount  pocet instanci
     * @param countIndicator pocet ukazatelu
     * @param isExist        existence prvku
     * @return CanvasItem
     */
    public CanvasItem addCanvasItemFromExistData(SegmentType type, int formIndex, String name, double x, double y, int instanceCount, int countIndicator, boolean isExist) {
        CanvasItem item = addCanvasItemFromExistData(type, formIndex, name, x, y, instanceCount, countIndicator);
        formController.setItemColor(formIndex, isExist);
        return item;
    }

    /**
     * Metoda pro vytovreni identificatoru jednotlivych prvku platn
     *
     * @param type      instatace tridy SegmentType
     * @param formIndex identificator instace datoveho modelu, ktery bude prvek platna predstavovat
     * @return String identificator
     */
    public String createSegmentId(SegmentType type, int formIndex) {

        int id = formController.getSegmetIdFromFormId(type, formIndex);
        String number = String.format("%03d", id);

        return type.name() + "_" + number;

    }

    /**
     * Metoda vytvoreni a ziskani id noveho prvku.
     *
     * @param segmentType instance tridy uchovavajici informaci o typu segmentu
     * @param canvasType  instace tridy uchovavajici informaci o typy platna
     * @return
     */
    private int getFormIndexFromNewForm(SegmentType segmentType, CanvasType canvasType) {

        return formController.createNewForm(segmentType, canvasType);
    }

    /**
     * Metoda pro reakci na drag and drop pridani prvku z platna
     *
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
     *
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

        if (selectionController.getSelection().size() > 1) {
            for (CanvasItem canvasItem : selectionController.getSelection()) {
                newX = canvasItem.getTranslateX() + canvasItem.getWidth() + rectFishWidth;
                newY = canvasItem.getTranslateY() + canvasItem.getHeight() + Constans.NEW_INSTACE_OFFSET;
                manipulationController.pasteItem(this, canvasItemController, newX, newY, canvasItem);
            }

        } else {
            Set selection = selectionController.getSelection();

            if (selection.size() != 0) {
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
            startArrow = true;
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
        } else {
            itemContexMenu.hide();
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
     *
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
     *
     * @param dragContext pocatecni bod
     * @param rect        instace tridy Rectangle
     * @param canvas      instance tridy AnchorPane
     * @return EventHandler
     */
    public EventHandler<MouseEvent> getOnMousePressedEventHandler(DragContext dragContext, Rectangle rect, AnchorPane canvas) {
        EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (!manipulationController.isChoosedItem() && !manipulationController.isMultiSelect()) {
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
            }
        };
        return onMousePressedEventHandler;
    }

    /**
     * Metoda pro ziskani EventDandleru pro reakci na release udalosti
     *
     * @param rect   instace tridy Rectangle
     * @param canvas instace tridy AnchorPane
     * @return EventHandler
     */
    public EventHandler<MouseEvent> getOnMouseReleasedEventHandler(Rectangle rect, AnchorPane canvas) {
        EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ElementsLink delLink = null;
                boolean removeLink = false;
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (!event.isShiftDown() && !event.isControlDown()) {
                        if (!manipulationController.isChoosedItem()) {
                            selectionController.clear();
                        }
                    }

                    for (Node node : canvas.getChildren()) {
                        if (node instanceof CanvasItem) {
                            CanvasItem item = (CanvasItem) node;
                            multiselectAction(item, event, rect, node);
                        } else if (node instanceof ElementsLink) {
                            if (node.getBoundsInParent().intersects(rect.getBoundsInParent())) {
                                ElementsLink link = (ElementsLink) node;
                                LinkControl linkControl = ((ElementsLink) node).getLinkControl();
                                if (event.getButton().equals(MouseButton.PRIMARY)) {
                                    link.polygonClickedReaction(event);
                                    linkControl.pressedDeleteArrow(event, link);
                                    if (event.getClickCount() == 2) {
                                        delLink = link;
                                    }
                                }
                            }
                        }
                        //   selectionController.log();

                    }

                    removeRectangle();
                }
                if (delLink != null) {
                    canvas.getChildren().remove(delLink.getBackgroundPolygon());
                    canvas.getChildren().remove(delLink);
                }
                event.consume();
                manipulationController.setChoosedItem(false);
                if (selectionController.getSelection().isEmpty()) {
                    manipulationController.setMultiSelect(false);
                }
            }
        };

        return onMouseReleasedEventHandler;
    }

    /**
     * Metoda zajistujici reakci pri vyberu vice prvku na platne
     *
     * @param item  instace Canvas Item
     * @param event udalost mysi
     * @param rect  instace Rectangle
     * @param node  instace Node
     */
    private void multiselectAction(CanvasItem item, MouseEvent event, Rectangle rect, Node node) {
        if (node.getBoundsInParent().intersects(rect.getBoundsInParent())) {

            if (event.isShiftDown()) {

                selectionController.add(item);
                manipulationController.setMultiSelect(true);
            } else if (event.isControlDown()) {

                if (selectionController.contains(item)) {
                    selectionController.remove(item);
                } else {
                    selectionController.add(item);
                    manipulationController.setMultiSelect(true);
                }
            } else {
                selectionController.add(item);
                manipulationController.setMultiSelect(true);
            }
        }
    }


    /**
     * Pomocna metoda pro odstraneni obdelniku pro multiselect z platna
     */
    public void removeRectangle() {
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
     *
     * @param dragContext pocatecni bod ze ktereho se bude vykreslovat ctverec
     * @param rect        insatace tridy Rectangle
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
                        // manipulationController.setMultiSelect(true);
                        event.consume();

                    }
                }
            }
        };

        return onMouseDraggedEventHandler;
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
     * Metoda pro odstraneni prvku z platna
     *
     * @param chooseCanvasItem instace pro odejmuti z platna
     */
    public void removeCanvasItem(CanvasItem chooseCanvasItem) {
        AnchorPane canvas = getCanvas().getCanvas();
        canvas.getChildren().remove(chooseCanvasItem);
        removeRectangle();
        selectionController.clear();
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

    public Background getBackground() {
        return background;
    }

    public Background getBackground2() {
        return background2;
    }

    public Image getImage() {
        return image;
    }

    public Image getImage2() {
        return image2;
    }
}



