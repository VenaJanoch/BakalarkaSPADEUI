package graphics;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import services.Control;
import services.ManipulationControl;

public class CanvasContexMenu extends ContextMenu {

	Control control;
	ManipulationControl manipulation;
	private DragAndDropCanvas canvas;

	public CanvasContexMenu(Control control, ManipulationControl manipulation, DragAndDropCanvas canvas) {
		super();
		this.control = control;
		this.manipulation = manipulation;
		this.canvas = canvas;
		createMenu();

	}

	private void createMenu() {

		MenuItem pasteItem = new MenuItem("Paste");
		pasteItem.setOnAction(event -> manipulation.pasteItem(canvas));
		this.getItems().addAll(pasteItem);

	}

}
