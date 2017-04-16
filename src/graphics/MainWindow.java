package graphics;

import forms.ProjectForm;
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
import services.Alerts;
import services.Constans;
import services.Control;

public class MainWindow extends Stage {

	private Scene scena;
	private Control control;
	private BorderPane mainPanel;
	private DragAndDropCanvas dragCanvas;
	private MenuPanel menu;
	private DragAndDropPanel dragAndDrop;
	
	public MainWindow(Main main) {
		super();
		
		this.setTitle("SPADE XML editor");
		

	
		main.getPrimaryStage().setOnCloseRequest(event -> Alerts.showCloseApp(control));
				
		this.setScene(creatScene());
	}

	
	private Scene creatScene() {

		scena = new Scene(creatPanel(), Constans.width, Constans.height);
		dragCanvas.setMScene(scena);
		
		return scena;
	}

	private Parent creatPanel() {
		mainPanel = new BorderPane();
		control = new Control();
		menu = new MenuPanel(control, this);

		dragCanvas = new DragAndDropCanvas(control, 0, control.getContexMenu());
		control.setCanvas(dragCanvas);
		dragAndDrop = new DragAndDropPanel(control);
		
		
		
		VBox topPanel = new VBox();

		topPanel.getChildren().addAll(menu, dragAndDrop);
		mainPanel.setTop(topPanel);
	//	mainPanel.setRight(dragAndDrop);
		mainPanel.setCenter(dragCanvas);

		return mainPanel;
	}


	public void newItem() {
		this.setScene(creatScene());
	}
	


}
