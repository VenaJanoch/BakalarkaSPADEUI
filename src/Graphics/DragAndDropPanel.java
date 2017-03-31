package Graphics;

import Forms.ProjectForm;
import Services.Constans;
import Services.Control;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DragAndDropPanel extends BorderPane {

	private DragAndDropItem items;
	private Button arrowB;
	private Button formBT;
	private HBox buttonBox;
	private Control control;
	
	public DragAndDropPanel(Control control) {

		super();
		this.control = control;
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

		items = new DragAndDropItem(control, Constans.projectDragTextIndexs);

		formBT = new Button("Project");
		arrowB = new Button("", new Line(0, 0, 10, 10));
		buttonBox.getChildren().addAll(formBT, arrowB);
		this.setCenter(items);

		this.setLeft(buttonBox);

		arrowB.setOnAction(event -> createArrowButtonEvent());
		formBT.setOnAction(event -> control.showProjectForm());

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
