package Grafika;

import Forms.ProjectForm;
import Obsluha.Control;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;

public class DragAndDropPanel extends BorderPane {

	private DragAndDropItem items;
	private Button arrowB;
	private Button formBT;
	private HBox buttonBox;
	private Control control;
	private ProjectForm projectForm;
	public DragAndDropPanel(Control control, ProjectForm projectForm) {

		super();
		this.control = control;
		this.buttonBox = new HBox();
		this.projectForm = projectForm;
		items = new DragAndDropItem(control);

		formBT = new Button("Project");
		arrowB = new Button("", new Line(0, 0, 10, 10));
		buttonBox.getChildren().addAll(formBT, arrowB);
		this.setCenter(items);

		this.setLeft(buttonBox);

		arrowB.setOnAction(event -> createArrowButtonEvent());
		formBT.setOnAction(event -> projectForm.show());

	}

	public void createArrowButtonEvent() {

		if (control.changeArrow()) {
			getParent().getParent().setCursor(Cursor.CROSSHAIR);
			arrowB.setCursor(Cursor.DEFAULT);
		} else {
			getParent().getParent().setCursor(Cursor.DEFAULT);
		}

	}

	public DragAndDropItem getItems() {
		return items;
	}

	public void setItems(DragAndDropItem items) {
		this.items = items;
	}

}
