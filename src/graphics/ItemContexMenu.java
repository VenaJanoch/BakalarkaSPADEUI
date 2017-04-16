package graphics;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import services.Constans;
import services.Control;
import services.ManipulationControl;

public class ItemContexMenu extends ContextMenu {

	private Control control;
	private ManipulationControl manipulation;
	private MainWindow mainWindow;
	private CanvasItem item;
	private DragAndDropCanvas dgCanvas;
	private MenuItem copyItem;
	private MenuItem pasteItem;
	private MenuItem deleteItem;
	private MenuItem cutItem;
	
	
	public ItemContexMenu(Control control, ManipulationControl manipulation, DragAndDropCanvas dgCanvas) {
		super();
		this.control = control;
		this.mainWindow = mainWindow;
		this.manipulation = manipulation;
		this.item = item;
		this.dgCanvas = dgCanvas;
	
		createMenu();

	}

	private void createMenu() {

		copyItem = new MenuItem("Copy");
		pasteItem = new MenuItem("Paste");

		deleteItem = new MenuItem("Delete");
		cutItem = new MenuItem("Cut");
		
		this.getItems().addAll(copyItem, new SeparatorMenuItem(), cutItem, new SeparatorMenuItem(), pasteItem,
				new SeparatorMenuItem(), deleteItem);

	}

	public void setActions(CanvasItem item){
		
		copyItem.setOnAction(event -> manipulation.copyItem(item));
		deleteItem.setOnAction(event -> manipulation.cutItem(item));
		cutItem.setOnAction(event -> manipulation.deleteItem(item));
	}
	
	
	
	
	public DragAndDropCanvas getDgCanvas() {
		return dgCanvas;
	}

	public void setDgCanvas(DragAndDropCanvas dgCanvas) {
		this.dgCanvas = dgCanvas;
		
		pasteItem.setOnAction(event -> manipulation.pasteItem(dgCanvas));
	}

	public CanvasItem getItem() {
		return item;
	}

	public void setItem(CanvasItem item) {
		this.item = item;
		this.setActions(item);
		
	}

	public MenuItem getCopyItem() {
		return copyItem;
	}

	public void setCopyItem(MenuItem copyItem) {
		this.copyItem = copyItem;
	}

	public MenuItem getPasteItem() {
		return pasteItem;
	}

	public void setPasteItem(MenuItem pasteItem) {
		this.pasteItem = pasteItem;
	}

	public MenuItem getDeleteItem() {
		return deleteItem;
	}

	public void setDeleteItem(MenuItem deleteItem) {
		this.deleteItem = deleteItem;
	}

	public MenuItem getCutItem() {
		return cutItem;
	}

	public void setCutItem(MenuItem cutItem) {
		this.cutItem = cutItem;
	}
	
	
	
	
	

}
