package Graphics;

import Forms.ProjectForm;
import Run.Main;
import Services.Constans;
import Services.Control;
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

	public MainWindow(Main main) {
		super();
		control = new Control();
		this.setTitle("SPADE XML editor");
		this.control = control;

		mainPanel = new BorderPane();
		this.setScene(creatScene());
	}

	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.width, Constans.height);
		dragCanvas.setMScene(scena);
		control.setCanvas(dragCanvas);
		return scena;
	}

	private Parent creatPanel() {
		menu = new MenuPanel(control, this);

		dragCanvas = new DragAndDropCanvas(control, 0);
		dragAndDrop = new DragAndDropPanel(control);
		VBox topPanel = new VBox();

		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
		// hlavniPanel.setCenter(dragAndDrop);
		mainPanel.setCenter(dragCanvas);

		return mainPanel;
	}
	


}
