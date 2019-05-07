package graphics.panels;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import graphics.canvas.DragSegmentButton;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import services.Constans;
import services.SegmentType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Třída vytvářející panel tlačítek umožnujících přidání prvku na plátno
 *
 * @author Václav Janoch
 */
public class DragAndDropItemPanel extends HBox {

    /**
     * Globální proměnné třídy
     **/
    private DragSegmentButton[] dragSegmnets;
    private ToggleButton linkButton;
    private int[] itemArray;
    private CanvasController canvasController;

    private DrawerPanelController drawerPanelController;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     *
     * @param itemArray int[]
     */
    public DragAndDropItemPanel(int[] itemArray, DrawerPanelController drawerPanelController) {
        super();
        this.setPrefWidth(Constans.width);
        this.setPadding(new Insets(5));
        this.itemArray = itemArray;
        this.drawerPanelController = drawerPanelController;

        this.setId("dgItem");

        dragSegmnets = new DragSegmentButton[itemArray.length];
    }

    /**
     * Přetížený konstruktor třídy Zinicializuje globální proměnné třídy a
     * nastaví Control, určen pro přidání panelu do hlavního okna
     *
     * @param itemArray int[]
     */
    public DragAndDropItemPanel(CanvasController canvasController, int[] itemArray, DrawerPanelController drawerPanelController) {
        this(itemArray, drawerPanelController);
        this.canvasController = canvasController;
        createDragItems();
    }

    /**
     * Pomocná metoda pro vytvoření tlačítek v hlavním okně
     */
    public void createDragItems() {
        for (int i = 0; i < itemArray.length; i++) {
            dragSegmnets[i] = new DragSegmentButton(SegmentType.values()[itemArray[i]], canvasController);
            FileInputStream input = null;
            try {
                //String path = DragAndDropItemPanel.class.getClassLoader().getResource(Constans.PANEL_BUTTONS[i]).toString();
                input = new FileInputStream(Constans.PANEL_BUTTONS[i]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            Image image = new Image(input);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

            Background background = new Background(backgroundImage);
            dragSegmnets[i].setMinWidth(image.getWidth());
            dragSegmnets[i].setMinHeight(image.getHeight());
            dragSegmnets[i].setBackground(background);

            this.getChildren().add(dragSegmnets[i]);

        }
        createLinkButton();
        // this.setAlignment(Pos.CENTER);
    }

    /**
     * Metoda pro vytvoření tlačítka umožnujícího přepnutí na kreslící mod.
     * Posunutí tlačítka od ostatních prvků, vytvoření oddělujícího panelu
     */
    public void createLinkButton() {

        HBox box = new HBox(10);
        linkButton = canvasController.getLinkButton();
        FileInputStream input = null;
        try {
            input = new FileInputStream(Constans.SIPKA);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);
        linkButton.setMinWidth(image.getWidth());
        linkButton.setMinHeight(image.getHeight());
        linkButton.setBackground(background);
        linkButton.setId("linkButton");
        box.setMinWidth(80);

        SplitPane splitPane = new SplitPane();
        box.getChildren().addAll(splitPane, linkButton);
        HBox.setMargin(splitPane, new Insets(0, 0, 0, 5));

        linkButton.setFont(Font.font("Verdana", 25));
        linkButton.setOnAction(event -> createArrowButtonEvent());
        linkButton.setOnKeyPressed(event -> presESC(event));

        this.getChildren().addAll(box);

    }


    /**
     * Pomocná metoda pro reakci na stisk ESC
     *
     * @param event
     */
    public void presESC(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            canvasController.pressESCAction();
        }
    }

    /**
     * Pomocná metoda pro nastavaní reakce na stisk tlačítka pro přepnutí modu
     */
    public void createArrowButtonEvent() {

        if (canvasController.changeArrow()) {
            canvasController.setCursorToCanvas(Cursor.CROSSHAIR);
            linkButton.setCursor(Cursor.DEFAULT);

        } else {
            canvasController.setCursorToCanvas(Cursor.DEFAULT);

        }
    }

}
