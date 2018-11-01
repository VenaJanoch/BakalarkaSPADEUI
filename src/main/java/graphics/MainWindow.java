package graphics;

import Controllers.ApplicationController;
import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.WindowController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.*;

/**
 * Třída s hlavní layoutem aplikace
 * 
 * @author Václav Janoch
 *
 */
public class MainWindow extends Stage {
	/** Globální proměnné třídy **/
	private Scene scena;

	private BorderPane mainPanel;
	private CanvasController canvasController;
	private MenuPanel menu;
	private DragAndDropPanel dragAndDrop;
	private WindowController windowController;
	private FormController formController;
	/**
	 * Konstruktor třídy Nastaví reakci na uzavírání aplikace
	 * 
	 * @param
	 */
	public MainWindow(ApplicationController applicationController) {
		super();
		this.windowController = applicationController.getWindowController();
		this.canvasController = new CanvasController(CanvasType.Project, applicationController);
		this.formController = applicationController.getFormController();

		this.setTitle(Constans.mainWindowTitle);

		windowController.closeProjectWindow();

		this.setScene(creatScene());
		windowController.setSceneToPrimaryStage(scena, this.getTitle());
	}

	/**
	 * Vytvoří instanci Scene
	 * 
	 * @return Scene
	 */
	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.width, Constans.height);

		//dragCanvas.setMScene(scena); Todo je to potreba

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

		dragAndDrop = new DragAndDropPanel(formController, windowController, canvasController);

		VBox topPanel = new VBox();
		mainPanel.setId("main");
		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
		// mainPanel.setRight(dragAndDrop);
		mainPanel.setCenter(canvasController.getCanvas());

		return mainPanel;
	}

}
