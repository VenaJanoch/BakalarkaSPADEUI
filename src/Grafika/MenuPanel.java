package Grafika;

import Obsluha.Constans;
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

public class MenuPanel extends VBox{
	
	public MenuPanel() {
	super();
	this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	this.setMinWidth(Constans.width);
	createMenu();

	}
	
	
	private void createMenu(){
		
MenuBar menuMB = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		MenuItem newItem = new MenuItem("New");
		MenuItem openItem = new MenuItem("Open");
		MenuItem closeItem = new MenuItem("Close");
		MenuItem exitItem = new MenuItem("Exit");
		fileMenu.getItems().addAll(newItem, openItem, new SeparatorMenuItem(),
				closeItem, new SeparatorMenuItem(), exitItem);
		
		exitItem.setOnAction(ActionEvent -> Platform.exit());
		menuMB.getMenus().addAll(fileMenu);		
		
		this.getChildren().add(menuMB);
	}
	
	
}
