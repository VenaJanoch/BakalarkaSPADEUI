package graphics;

import java.io.File;

import abstractform.BasicForm;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import services.Constans;
import services.Control;
import services.SegmentType;

/**
 * Třída vytvářející panel tlačítek umožnujících přidání prvku na plátno
 * 
 * @author Václav Janoch
 *
 */
public class DragAndDropItem extends HBox {
	/** Globální proměnné třídy **/
	private DragText[] dragSegmnets;
	private ToggleButton link;
	private int[] itemArray;
	private Control control;
	private BasicForm form;
	private DragAndDropCanvas canvas;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param itemArray
	 *            int[]
	 */
	public DragAndDropItem(int[] itemArray) {
		super(5);
		this.setPrefWidth(Constans.width);
		this.setPadding(new Insets(5));
		this.itemArray = itemArray;
		this.setId("dgItem");

		dragSegmnets = new DragText[itemArray.length];

	}

	/**
	 * Přetížený konstruktor třídy Zinicializuje globální proměnné třídy a
	 * nastaví Control, určen pro přidání panelu do hlavního okna
	 * 
	 * @param control
	 *            Control
	 * @param itemArray
	 *            int[]
	 */
	public DragAndDropItem(Control control, int[] itemArray) {
		this(itemArray);
		this.control = control;

		createDragItemsProject();
	}

	/**
	 * Přetížený konstruktor třídy Zinicializuje globální proměnné třídy,
	 * nastaví control, určen pro přidání panlu do formuláře
	 * 
	 * @param itemArray
	 *            int[]
	 */
	public DragAndDropItem(Control control, int[] itemArray, BasicForm form) {
		this(itemArray);
		this.control = control;
		this.form = form;

		createDragItems();
	}

	/**
	 * Pomocná metoda pro vytvoření tlačítek ve formuláři
	 */
	public void createDragItems() {

		for (int i = 0; i < itemArray.length; i++) {
			dragSegmnets[i] = new DragText(SegmentType.values()[itemArray[i]], form);
			this.getChildren().add(dragSegmnets[i]);

		}
		createLinkButton(form.getCanvas());
	}

	/**
	 * Pomocná metoda pro vytvoření tlačítek v hlavním okně
	 */
	public void createDragItemsProject() {

		for (int i = 0; i < itemArray.length; i++) {
			dragSegmnets[i] = new DragText(SegmentType.values()[itemArray[i]], control.getCanvas());

			this.getChildren().add(dragSegmnets[i]);

		}
		createLinkButton(control.getCanvas());

	}

	/**
	 * Metoda pro vytvoření tlačítka umožnujícího přepnutí na kreslící mod.
	 * Posunutí tlačítka od ostatních prvků, vytvoření oddělujícího panelu
	 * 
	 * @param canvas
	 */
	public void createLinkButton(DragAndDropCanvas canvas) {

		this.canvas = canvas;
		HBox box = new HBox();
		link = new ToggleButton();
		box.setMinWidth(80);
		box.getChildren().add(link);
		box.setAlignment(Pos.BASELINE_RIGHT);
		SplitPane splitPane = new SplitPane();
		link.setText(Constans.linkSimbol);
		link.setFont(Font.font("Verdana", 25));
		link.setOnAction(event -> createArrowButtonEvent(canvas));
		link.setOnKeyPressed(keyListener);
		canvas.setOnKeyPressed(keyListener);
		this.getChildren().addAll(splitPane, box);

	}

	/**
	 * KeyEvent Handler pro vytvoření rekace na stisknutí ESC a vypnutí
	 * kreslícího modu
	 */
	EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			presESC(event);
		}
	};

	/**
	 * Pomocná metoda pro reakci na stisk ESC
	 * 
	 * @param event
	 */
	public void presESC(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {

			canvas.setArrow(false);
			canvas.setStartArrow(false);
			canvas.setCursor(Cursor.DEFAULT);
			link.setSelected(false);

		}
	}

	/**
	 * Pomocná metoda pro nastavaní reakce na stisk tlačítka pro přepnutí modu
	 * 
	 * @param canvas
	 *            DragAndDropCanvas
	 */
	public void createArrowButtonEvent(DragAndDropCanvas canvas) {

		if (canvas.changeArrow()) {
			canvas.setCursor(Cursor.CROSSHAIR);
			link.setCursor(Cursor.DEFAULT);

		} else {
			canvas.setCursor(Cursor.DEFAULT);

		}
	}

}
