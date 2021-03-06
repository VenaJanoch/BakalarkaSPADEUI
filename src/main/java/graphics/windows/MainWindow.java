package graphics.windows;

import com.jfoenix.controls.JFXDrawersStack;
import controllers.ApplicationController;
import controllers.WindowController;
import controllers.formControllers.FormController;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import graphics.panels.DragAndDropPanel;
import graphics.panels.MenuPanel;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CanvasType;
import services.Constans;

/**
 * Třída s hlavní layoutem aplikace
 *
 * @author Václav Janoch
 */
public class MainWindow extends Stage {
    /**
     * Globální proměnné třídy
     **/
    private Scene scena;

    private BorderPane mainPanel;
    private CanvasController canvasController;
    private MenuPanel menu;
    private DragAndDropPanel dragAndDrop;
    private WindowController windowController;
    private FormController formController;
    private JFXDrawersStack drawersStack;
    private DrawerPanelController drawerPanelController;
    private SelectItemController selectItemController;

    /**
     * Konstruktor třídy Nastaví reakci na uzavírání aplikace
     *
     * @param windowController      instace tridy WindowController
     * @param drawersStack          instance tridy DrawerStack
     * @param drawerPanelController instance tridy DrawerPanelController
     * @param selectItemController  instance tridy SelectItemController
     * @param applicationController instance tridy ApplicationController
     */
    public MainWindow(WindowController windowController, JFXDrawersStack drawersStack, DrawerPanelController drawerPanelController,
                      SelectItemController selectItemController, ApplicationController applicationController) {
        super();
        this.windowController = windowController;
        this.selectItemController = selectItemController;
        this.canvasController = new CanvasController(CanvasType.Project, applicationController);
        this.drawersStack = drawersStack;
        this.formController = applicationController.getFormController();
        this.drawerPanelController = drawerPanelController;
        this.setTitle(Constans.mainWindowTitle);
        windowController.closeProjectWindow();

        this.setScene(creatScene());

    }

    /**
     * Vytvoří instanci Scene
     *
     * @return Scene
     */
    private Scene creatScene() {

        scena = new Scene(creatPanel(), Constans.width, Constans.height);
        return scena;
    }

    /**
     * Vytvoří panely a přidá je hlavního panelu aplikace
     *
     * @return BorderPane
     */
    private Parent creatPanel() {
        mainPanel = new BorderPane();
        menu = new MenuPanel(windowController);
        dragAndDrop = new DragAndDropPanel(formController, windowController, canvasController, drawerPanelController, selectItemController);

        VBox topPanel = new VBox();
        mainPanel.setId("main");
        topPanel.getChildren().addAll(menu, dragAndDrop);
        mainPanel.setTop(topPanel);
        mainPanel.setAlignment(dragAndDrop, Pos.CENTER_RIGHT);
        drawersStack.setContent(canvasController.getCanvas());
        mainPanel.setCenter(drawersStack);

        return mainPanel;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }
}
