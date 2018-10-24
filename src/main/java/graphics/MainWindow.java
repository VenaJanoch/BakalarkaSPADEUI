package graphics;

import java.io.File;

import forms.ProjectForm;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import run.Main;
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
	private DragAndDropCanvas dragCanvas;
	private MenuPanel menu;
	private DragAndDropPanel dragAndDrop;


	private Control control;
	private WindowController windowController;
	/**
	 * Konstruktor třídy Nastaví reakci na uzavírání aplikace
	 * 
	 * @param
	 */
	public MainWindow(WindowController windowController) {
		super();
		this.windowController = windowController;

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

		dragCanvas.setMScene(scena);

		return scena;
	}

	/**
	 * Vytvoří panely a přidá je hlavního panelu aplikace
	 * 
	 * @return BorderPane
	 */
	private Parent creatPanel() {
		mainPanel = new BorderPane();
		control = new Control(this);
		menu = new MenuPanel(control, this);

		dragCanvas = new DragAndDropCanvas(control, 0, control.getContexMenu(), CanvasType.Project);
		control.setCanvas(dragCanvas);

		dragAndDrop = new DragAndDropPanel(control);

		VBox topPanel = new VBox();
		mainPanel.setId("main");
		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
		// mainPanel.setRight(dragAndDrop);
		mainPanel.setCenter(dragCanvas);

		return mainPanel;
	}

}
