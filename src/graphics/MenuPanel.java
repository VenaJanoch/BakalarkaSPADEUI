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
		MenuItem openItem = new MenuItem("Open");
		MenuItem saveItem = new MenuItem("Save");
		MenuItem saveAsItem = new MenuItem("Save as");
		MenuItem validationItem = new MenuItem("Validate");
		MenuItem exitItem = new MenuItem("Exit");
		fileMenu.getItems().addAll(newItem, new SeparatorMenuItem(), openItem, new SeparatorMenuItem(), saveItem,
				saveAsItem, new SeparatorMenuItem(),validationItem, new SeparatorMenuItem(), exitItem);

		exitItem.setOnAction(ActionEvent -> Platform.exit());
		saveItem.setOnAction(event -> control.saveFile());
		saveAsItem.setOnAction(event -> control.saveAsFile());
		openItem.setOnAction(event -> openFile());
		newItem.setOnAction(event -> control.restartControl());
		validationItem.setOnAction(event -> control.validate());
		menuMB.getMenus().addAll(fileMenu);

		this.getChildren().add(menuMB);
	}

	private void openFile() {
		control.openFile();
	}
}
