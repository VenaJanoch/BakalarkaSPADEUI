package Grafika;

import Obsluha.Control;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

public class DragAndDropPanel extends BorderPane{
	
	private DragAndDropItem items;
	private Button arrowB;
	private Control control;
	public DragAndDropPanel(Control control) {
	
	super();
	this.control = control;
	
	items = new DragAndDropItem(control);
	arrowB = new Button("", new Line(0, 0, 10, 10));
	this.setCenter(items);
	this.setLeft(arrowB);
	
	
	
	
	}

}
