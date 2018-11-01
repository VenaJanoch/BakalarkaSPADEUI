package graphics;

import Controllers.CanvasController;
import Controllers.WindowController;
import abstractform.BasicForm;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import services.Constans;
import services.SegmentType;

/**
 * Třída vytvářející panel tlačítek umožnujících přidání prvku na plátno
 * 
 * @author Václav Janoch
 *
 */
public class DragAndDropItemPanel extends HBox {

	/** Globální proměnné třídy **/
	private DragSegmentButton[] dragSegmnets;
	private ToggleButton linkButton;
	private int[] itemArray;
	private BasicForm form;
	private CanvasController canvasController;
	private WindowController windowController;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param itemArray
	 *            int[]
	 */
	public DragAndDropItemPanel(int[] itemArray) {
		super(5);
		this.setPrefWidth(Constans.width);
		this.setPadding(new Insets(5));
		this.itemArray = itemArray;
		this.setId("dgItem");

		dragSegmnets = new DragSegmentButton[itemArray.length];

	}

	/**
	 * Přetížený konstruktor třídy Zinicializuje globální proměnné třídy a
	 * nastaví Control, určen pro přidání panelu do hlavního okna
	 *
	 * @param itemArray
	 *            int[]
	 */public DragAndDropItemPanel(CanvasController canvasController, int[] itemArray) {
		this(itemArray);
		this.canvasController = canvasController;
		createDragItems();
	}

	/**
	 * Pomocná metoda pro vytvoření tlačítek v hlavním okně
	 */
	public void createDragItems() {

		for (int i = 0; i < itemArray.length; i++) {
			dragSegmnets[i] = new DragSegmentButton(SegmentType.values()[itemArray[i]], canvasController);

			this.getChildren().add(dragSegmnets[i]);

		}
		createLinkButton();

	}

	/**
	 * Metoda pro vytvoření tlačítka umožnujícího přepnutí na kreslící mod.
	 * Posunutí tlačítka od ostatních prvků, vytvoření oddělujícího panelu
	 *
	 */
	public void createLinkButton() {

		HBox box = new HBox();
		linkButton = new ToggleButton();
		linkButton.setId("linkButton");
		box.setMinWidth(80);
		box.getChildren().add(linkButton);
		box.setAlignment(Pos.BASELINE_RIGHT);
		SplitPane splitPane = new SplitPane();
		linkButton.setText(Constans.linkSimbol);
		linkButton.setFont(Font.font("Verdana", 25));
		linkButton.setOnAction(event -> createArrowButtonEvent());
		linkButton.setOnKeyPressed(event -> presESC(event));

		this.getChildren().addAll(splitPane, box);

	}


	/**
	 * Pomocná metoda pro reakci na stisk ESC
	 * 
	 * @param event
	 */
	public void presESC(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {

			linkButton.setSelected(false);
		}
	}

	/**
	 * Pomocná metoda pro nastavaní reakce na stisk tlačítka pro přepnutí modu
	 *
	 */
	public void createArrowButtonEvent() {

		if (canvasController.changeArrow()) {
			canvasController.setCursorToCanvas(Cursor.CROSSHAIR);
			linkButton.setCursor(Cursor.DEFAULT);

		} else {
			canvasController.setCursorToCanvas(Cursor.DEFAULT);

		}
	}

}
