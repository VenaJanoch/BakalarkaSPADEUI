package graphics;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.WindowController;
import abstractform.BasicForm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import services.Constans;

/**
 * Třída tvořící panel tlačítek 
 * @author Václav Janoch
 *
 */
public class DragAndDropPanel extends BorderPane {

	/**
	 * Globální proměnné třídy
	 */
	private DragAndDropItemPanel items;
	private Button[] addButtons;
	private HBox buttonBox;

	private FormController formController;
	private WindowController windowController;
	private CanvasController canvasController;

	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné třídy
	 *
	 */
	
	public DragAndDropPanel(FormController formController, WindowController windowController, CanvasController canvasController) {

		super();
		this.formController = formController;
		this.windowController = windowController;
		this.canvasController = canvasController;

		this.setPrefWidth(Constans.width);
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setId("panelTable");
		items = new DragAndDropItemPanel(canvasController, Constans.projectDragTextIndexs);
		this.setAlignment(items, Pos.BOTTOM_LEFT);

		addButtons = new Button[Constans.addButtonCount];
		createButtons();
		createAction();

		buttonBox.getChildren().addAll(addButtons);

		buttonBox.setPadding(new Insets(0,0,0,5));
		this.setTop(buttonBox);

		this.setCenter(items);

	}

	/**
	 * Přetížený konstruktor třídy využívají kořenový formulář do kterého se přidá
	 * @param form BasicForm
	 */
	public DragAndDropPanel(FormController formController, WindowController windowController, BasicForm form) {
	//todo Asi zrusit protoze je k nicemu :D
		super();
		this.formController = formController;
		this.windowController = windowController;
		this.buttonBox = new HBox(5);
		this.setPadding(new Insets(10));
		this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

		items = new DragAndDropItemPanel(canvasController, Constans.projectDragTextIndexs);
		this.setAlignment(items, Pos.BOTTOM_LEFT);

		addButtons = new Button[Constans.addButtonCount];
		createButtons();
		createAction();

		buttonBox.getChildren().addAll(addButtons);

		this.setCenter(buttonBox);

		this.setBottom(items);

	}
	
	
	/**
	 * Pomocná metoda pro vytvoření tlačítek
	 */
	public void createButtons() {

		for (int i = 0; i < addButtons.length; i++) {
			addButtons[i] = new Button(Constans.addButtonsNames[i]);
			
		}
	}

	/**
	 * Pomocná metoda pro přidání reakce na stisk tlačítka
	 */
	
	public void createAction() {
		addButtons[0].setOnAction(event -> formController.showForm(Constans.projectFormIndex));
		addButtons[1].setOnAction(event -> formController.showForm(Constans.milestoneFormIndex));
		addButtons[2].setOnAction(event -> formController.showForm(Constans.roleFormIndex));
		addButtons[3].setOnAction(event -> formController.showForm(Constans.cprFormIndex));
		addButtons[4].setOnAction(event -> formController.showForm(Constans.priorityFormIndex));
		addButtons[5].setOnAction(event -> formController.showForm(Constans.severityFormIndex));
		addButtons[6].setOnAction(event -> formController.showForm(Constans.relationFormIndex));
		addButtons[7].setOnAction(event -> formController.showForm(Constans.resolutionormIndex));
		addButtons[8].setOnAction(event -> formController.showForm(Constans.statusFormIndex));
		addButtons[9].setOnAction(event -> formController.showForm(Constans.wuTypeFormIndex));
		addButtons[10].setOnAction(event -> formController.showForm(Constans.branchIndex));
		addButtons[11].setOnAction(	event -> formController.showForm(Constans.configurationFormIndex));
	}

	
	/*** Getrs and Setrs */
	public DragAndDropItemPanel getItems() {
		return items;
	}

	public void setItems(DragAndDropItemPanel items) {
		this.items = items;
	}

}
