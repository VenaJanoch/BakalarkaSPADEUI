package graphics;

import abstractform.BasicForm;
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
import services.SegmentType;

public class DragAndDropPanel extends BorderPane {

	private DragAndDropItem items;
	private Button[] addButtons;

	private HBox buttonBox;
	private Control control;

	
	public DragAndDropPanel(Control control) {

		super();
		this.setPrefWidth(Constans.width);
		this.control = control;
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setId("panelTable");
		items = new DragAndDropItem(control, Constans.projectDragTextIndexs);
		this.setAlignment(items, Pos.BOTTOM_LEFT);

		addButtons = new Button[Constans.addButtonCount];
		createButtons();
		createAction();

		buttonBox.getChildren().addAll(addButtons);

		buttonBox.setPadding(new Insets(0,0,0,5));
		this.setTop(buttonBox);

		this.setCenter(items);

	}

	public DragAndDropPanel(Control control, BasicForm form, int i) {

		super();
		this.control = control;
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

		items = new DragAndDropItem(control, Constans.projectDragTextIndexs, form);
		this.setAlignment(items, Pos.BOTTOM_LEFT);

		addButtons = new Button[Constans.addButtonCount];
		createButtons();
		createAction();

		buttonBox.getChildren().addAll(addButtons);

		this.setCenter(buttonBox);

		this.setBottom(items);

	}
	
	
	public void createButtons() {

		for (int i = 0; i < addButtons.length; i++) {
			addButtons[i] = new Button(Constans.addButtonsNames[i]);
			
		}
	}

	public void createAction() {
		addButtons[0].setOnAction(event -> control.showProjectForm());
		addButtons[1].setOnAction(event -> {control.getMilestoneForm().show();control.getMilestoneForm().toFront();});
		addButtons[2].setOnAction(event -> {control.getCPRForm().show(); control.getCPRForm().toFront();});
		addButtons[3].setOnAction(event -> {control.getRoleForm().show(); control.getRoleForm().toFront();});
		addButtons[4].setOnAction(event -> {control.getPriorityForm().show(); control.getPriorityForm().toFront();});
		addButtons[5].setOnAction(event -> {control.getSeverityForm().show(); control.getSeverityForm().toFront();});
		addButtons[6].setOnAction(event -> {control.getRelationForm().show(); control.getRelationForm().toFront();});
		addButtons[7].setOnAction(event -> {control.getResolutionForm().show(); control.getResolutionForm().toFront();});
		addButtons[8].setOnAction(event -> {control.getStatusForm().show(); control.getStatusForm().toFront();});
		addButtons[9].setOnAction(event -> {control.getTypeForm().show(); control.getTypeForm().toFront();});
		addButtons[10].setOnAction(event ->{control.getBranchFrom().show(); control.getBranchFrom().toFront();});
		addButtons[11].setOnAction(
				event -> {control.getConfTableForm().show(); control.getConfTableForm().toFront();});
	}

	public DragAndDropItem getItems() {
		return items;
	}

	public void setItems(DragAndDropItem items) {
		this.items = items;
	}

}
