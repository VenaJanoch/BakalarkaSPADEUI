package graphics.panels;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import graphics.canvas.DragSegmentButton;
import javafx.geometry.Insets;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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

    private Background background;
    private Background background2;
    private Image image;
    private Image image2;


    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     *
     * @param itemArray             pole prvku v panelu
     * @param drawerPanelController instace tridy DrawerPanelController
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
     * @param itemArray int[]
     * @param canvasController instace canvasController
     * @param drawerPanelController kontroler pro drawer panely
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
        background = canvasController.getBackground();
        background2 = canvasController.getBackground2();
        image = canvasController.getImage();
        image2 = canvasController.getImage2();


        box.setMinWidth(80);
        canvasController.setLinkButtonBackground(background, image);
        SplitPane splitPane = new SplitPane();
        box.getChildren().addAll(splitPane, linkButton);
        HBox.setMargin(splitPane, new Insets(0, 0, 0, 5));

        this.getChildren().addAll(box);

    }


}
