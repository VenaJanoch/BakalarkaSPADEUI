package graphics;

import forms.ProjectForm;
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
import services.Constans;
import services.Control;

public class DragAndDropPanel extends BorderPane {

	private DragAndDropItem items;
	private Button[]  addButtons;


	private HBox buttonBox;
	private Control control;

	public DragAndDropPanel(Control control) {

		super();
		this.control = control;
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

		items = new DragAndDropItem(control, Constans.projectDragTextIndexs);

		addButtons = new Button[Constans.addButtonCount];
		createButtons();
		createAction();
	
		buttonBox.getChildren().addAll(addButtons);
		
		this.setCenter(items);

		this.setBottom(buttonBox);

		
	}
	
	public void createButtons(){
		
		for (int i = 0; i < addButtons.length; i++) {
			addButtons[i] = new Button(Constans.addButtonsNames[i]);
		}
	}
	
	public void createAction(){
		addButtons[0].setOnAction(event -> control.showProjectForm());
		addButtons[1].setOnAction(event -> control.showMilestones());
		addButtons[2].setOnAction(event -> control.showCPR());
		addButtons[3].setOnAction(event -> control.showRole());
		addButtons[4].setOnAction(event -> control.showPriority());
		addButtons[5].setOnAction(event -> control.showSeverity());
		addButtons[6].setOnAction(event -> control.showRelation());
		addButtons[7].setOnAction(event -> control.showResolution());
		addButtons[8].setOnAction(event -> control.showStatus());
	}

	public DragAndDropItem getItems() {
		return items;
	}

	public void setItems(DragAndDropItem items) {
		this.items = items;
	}

}
