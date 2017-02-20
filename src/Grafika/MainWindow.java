package Grafika;

import Obsluha.Constans;
import Obsluha.PrepinacObrazovek;
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
	private BorderPane mainPanel;
	private DragAndDropCanvas dragCanvas;
	private MenuPanel menu;
	private DragAndDropItem dragAndDrop;

	public MainWindow(Main main) {
		super();
		this.setTitle("SPADE XML editor");
		mainPanel = new BorderPane();
		this.setScene(creatScene());
	}

	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.width, Constans.height);
		return scena;
	}

	private Parent creatPanel() {
		dragCanvas = new DragAndDropCanvas();
		menu = new MenuPanel();
		dragAndDrop = new DragAndDropItem();
		VBox topPanel = new VBox();
		
		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
		// hlavniPanel.setCenter(dragAndDrop);
		mainPanel.setCenter(dragCanvas);

		return mainPanel;
	}

}
