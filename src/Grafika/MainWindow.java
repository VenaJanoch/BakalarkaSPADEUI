package Grafika;

import Obsluha.Constans;
import Obsluha.Control;
import Run.Main;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Stage {

	private Scene scena;
	private Control control;
	private BorderPane mainPanel;
	private DragAndDropCanvas dragCanvas;
	private MenuPanel menu;
	private DragAndDropPanel dragAndDrop;

	public MainWindow(Main main, Control control) {
		super();
		this.setTitle("SPADE XML editor");
		this.control = control;
		
		mainPanel = new BorderPane();
		this.setScene(creatScene());
	}

	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.width, Constans.height);
		dragCanvas.setMScene(scena);
		return scena;
	}

	private Parent creatPanel() {
		dragCanvas = new DragAndDropCanvas(control);
		menu = new MenuPanel();
		dragAndDrop = new DragAndDropPanel(control);
		VBox topPanel = new VBox();
		
		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
		// hlavniPanel.setCenter(dragAndDrop);
		mainPanel.setCenter(dragCanvas);

		return mainPanel;
	}

}
