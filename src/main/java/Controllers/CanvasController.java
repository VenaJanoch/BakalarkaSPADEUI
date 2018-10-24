package Controllers;

import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import services.*;

import java.awt.dnd.DragSourceDropEvent;

public class CanvasController {

    private Control control;
    private DragAndDropCanvas canvas;
    private boolean arrow;
    private boolean startArrow;

    public CanvasController(Control control, DragAndDropCanvas canvas) {
        this.control = control;
        this.canvas = canvas;
    }


    public void keyPressAction(KeyEvent event) {
        if (Constans.controlV.match(event)) {
            pasteItem();
        } else if (Constans.controlC.match(event)) {
            copyItem();
        } else if (Constans.controlX.match(event)) {
            cutItem();
        } else if (event.getCode() == KeyCode.DELETE) {
            if (control.getManipulation().getClicItem() != null) {
                CanvasItem item = control.getManipulation().getClicItem();
                control.getManipulation().deleteItem(item);
            } else if (control.getManipulation().getLink() != null) {
                control.getManipulation().getLink().deleteArrow();

            }

        } else if (event.getCode() == KeyCode.ESCAPE) {
            if (control.getManipulation().getLink() != null) {
                control.getManipulation().getLink().getBackgroundPolygon().setStroke(Color.TRANSPARENT);
            }
        }

    }


    public CanvasItem addItem(String segment, double x, double y) {

        SegmentType type = Control.findSegmentType(segment);
        CanvasItem item = control.createCanvasItem();
        canvas.getCanvas().getChildren().add(item);
        return item;

    }

    public void dragAndDrop(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString()) {

            addItem(db.getString(), event.getSceneX(), event.getSceneY());
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
     * Metoda pro přídání z kopírovaného prvku na plátno
     *
     * @param segment SegmentType
     * @param x
     * @param y
     * @return CanvasItem
     */
    public CanvasItem addCopyItem(SegmentType segment, CanvasType canvasType, double x, double y) {
        if (FormControl.copyControl(segment, canvasType)) {

            CanvasItem item = control.createCanvasItem(); // CanvasItem(segment, "New", control, control.getForms().get(indexForm), 2, x, y,contexMenu, control.getLinkControl(), this);
            canvas.getCanvas().getChildren().add(item);
            return item;
        } else {

            Alerts.badCopyItem(segment, canvasType);
        }
        return null;

    }

    /**
     * Restartuje plátno. Vymaže všechny prvky plátna
     */
    public void restart() {

        arrow = false;
        startArrow = false;
        canvas.getCanvas().getChildren().clear();
    }


    /**
     * Metoda pro reakci na klávesouvou zkratku pro vložení prvku
     */

    private void pasteItem() {
        control.getManipulation().pasteItem(canvas);
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro kopírování prvku
     */
    private void copyItem() {
        CanvasItem item = control.getManipulation().getClicItem();
        control.getManipulation().copyItem(item);
    }

    /**
     * Metoda pro reakci na klávesouvou zkratku pro vyjmutí prvku
     */
    private void cutItem() {
        CanvasItem item = control.getManipulation().getClicItem();
        control.getManipulation().cutItem(item);
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
            control.getContexMenu().setDgCanvas(canvas);

            control.getContexMenu().show(canvas.getCanvas(), t.getScreenX(), t.getScreenY());
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
     * Metoda pro přidání nového prvku na plátno
     *
     * @param segment
     *            Sting
     * @param x
     * @param y
     * @return CanvasItem
     */

}
