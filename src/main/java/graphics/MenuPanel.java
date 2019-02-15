package graphics;

import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import services.Constans;
import controllers.WindowController;

/**
 * Třídy definující hlavní menu aplikace
 * @author Václav Janoch
 *
 */
public class MenuPanel extends VBox {
	
	/**
	 * Globální proměnné třídy
	 */
	
	private WindowController windowController;

	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 */
	public MenuPanel(WindowController windowController) {
		super();

		this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMinWidth(Constans.width);
		this.windowController = windowController;
		createMenu();

	}

	/**
	 * Vytvoří MenuBar a vloží do něho pložky s natavenými klávesovými zkratkami
	 */
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

		exitItem.setOnAction(event -> windowController.closeProject());
		saveItem.setOnAction(event -> windowController.saveItemAction());// control.saveFile());
		saveAsItem.setOnAction(event -> windowController.saveItemAsAction()); //.saveAsFile());
		openItem.setOnAction(event -> windowController.openProccesXMLAction()); //.openFile());
		newItem.setOnAction(event -> windowController.createNewProcessAction()); //.newItem());
		validationItem.setOnAction(event -> windowController.validationAction()); //.validate());
		menuMB.getMenus().addAll(fileMenu);

		this.getChildren().add(menuMB);
	}

}
