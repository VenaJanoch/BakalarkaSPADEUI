package graphics;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import services.Alerts;
import services.Constans;
import services.Control;

public class MenuPanel extends VBox {
	Control control;
	MainWindow mainWindow;

	public MenuPanel(Control control, MainWindow mainWindow) {
		super();
		this.control = control;
		this.mainWindow = mainWindow;
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMinWidth(Constans.width);
		createMenu();

	}

	private void createMenu() {

		MenuBar menuMB = new MenuBar();

		Menu fileMenu = new Menu("File");

		MenuItem newItem = new MenuItem("New");
		newItem.setAccelerator(Constans.controlN);
		MenuItem openItem = new MenuItem("Open...");
		openItem.setAccelerator(Constans.controlO);
		MenuItem saveItem = new MenuItem("Save");
		saveItem.setAccelerator(Constans.controlS);
		MenuItem saveAsItem = new MenuItem("Save as...");
		saveAsItem.setAccelerator(Constans.controlSA);
		MenuItem validationItem = new MenuItem("Validate");
		validationItem.setAccelerator(Constans.controlF);
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setAccelerator(Constans.altF4);
		
		fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), openItem, new SeparatorMenuItem(), saveItem,
				saveAsItem, new SeparatorMenuItem(), validationItem, new SeparatorMenuItem(), exitItem);

		exitItem.setOnAction(event -> {
			event.consume();
			int result = Alerts.showCloseApp(control);

			if (result == 1) {
				mainWindow.close();
			}

		});
		saveItem.setOnAction(event -> control.saveFile());
		saveAsItem.setOnAction(event -> control.saveAsFile());
		openItem.setOnAction(event -> openFile());
		newItem.setOnAction(event -> mainWindow.newItem());
		validationItem.setOnAction(event -> control.validate());
		menuMB.getMenus().addAll(fileMenu);

		this.getChildren().add(menuMB);
	}

	private void openFile() {
		control.openFile();
	}
}
