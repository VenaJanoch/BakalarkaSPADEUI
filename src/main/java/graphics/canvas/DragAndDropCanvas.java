package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import services.Constans;
import services.DragContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Třída reprezentujcí kreslící plátno
 *
 * @author Václav Janoch
 */
public class DragAndDropCanvas extends ScrollPane {

    /**
     * Globální proměnné třídy
     **/
    private Scene mScene;
    private AnchorPane canvas;
    private ItemContexMenu contexMenu;
    private DragContext dragContext = new DragContext();
    private Rectangle rect;

    private CanvasController canvasController;


    /**
     * Konstruktor třídy Zinicializuje Globální proměnné třídy a nastaví reakce
     * na detekci drag and drop
     *
     * @param canvasController ItemContexMenu
     */
    public DragAndDropCanvas(CanvasController canvasController) {

        super();
        this.canvasController = canvasController;
        this.canvas = new AnchorPane();

        canvas.setMinWidth(Constans.canvasMaxWidth);
        canvas.setMinHeight(Constans.canvasMaxHeight);

        canvas.setId("canvasID");
        this.setId("canvas");
        this.setContent(canvas);

        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        canvas.setBackground(new Background(new BackgroundFill(Color.rgb(248, 248, 248), CornerRadii.EMPTY, Insets.EMPTY)));

        canvas.setOnMouseClicked(canvasController.getOnMousePressedHandler());

        rect = new Rectangle( 0,0,0,0);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(1);
       // rect.setStrokeLineCap(StrokeLineCap.ROUND);
        rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, canvasController.getOnMousePressedEventHandler(dragContext, rect, canvas));
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, canvasController.getOnMouseDraggedEventHandler(dragContext, rect));
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, canvasController.getOnMouseReleasedEventHandler(rect, canvas));

        this.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            canvasController.keyPressAction(event);
        });

        canvas.setOnDragOver(event -> {
            canvasController.dragAndOver(event);
        });

        canvas.setOnDragEntered(event -> {
            event.consume();

        });

        canvas.setOnDragExited(event -> {
            event.consume();
        });

        canvas.setOnDragDropped(event -> {
            canvasController.dragAndDrop(event);


        });

    }


    /**
     * Konstruktor třídy Zinicializuje Globální proměnné třídy a nastaví reakce
     * na detekci drag and drop
     */
    //public DragAndDropCanvas(CanvasController canvasController, JFXDrawer leftDrawerPanel , JFXDrawer rightDrawerPanel) {
    //	this(canvasController);

    //	canvas.getChildren().addAll(leftDrawerPanel, rightDrawerPanel);


    //}
    public void clearCanvas() {
        canvas.getChildren().clear();
    }

    /**
     * Getrs and Setrs
     **/
    public Scene getMScene() {
        return mScene;
    }

    public void setMScene(Scene scene) {
        this.mScene = scene;
    }

    public AnchorPane getCanvas() {
        return canvas;
    }

    public void setCanvas(AnchorPane canvas) {
        this.canvas = canvas;
    }

    public Rectangle getRect() {
        return rect;
    }
}


