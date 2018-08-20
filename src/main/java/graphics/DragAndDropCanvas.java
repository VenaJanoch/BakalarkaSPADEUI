package graphics;

import abstractform.BasicForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.Alerts;
import services.CanvasType;
import services.Constans;
import services.Control;
import services.FormControl;
import services.ManipulationControl;
import services.SegmentType;

/**
 * Třída reprezentujcí kreslící plátno
 * 
 * @author Václav Janoch
 *
 */
public class DragAndDropCanvas extends ScrollPane {

	/** Globální proměnné třídy **/
	private Scene mScene;
	private Control control;
	private BasicForm form;
	private int indexForm;
	private AnchorPane canvas;
	private ItemContexMenu contexMenu;

	private CanvasType canvasType;

	private boolean arrow;
	private boolean startArrow;

	/**
	 * Konstruktor třídy Zinicializuje Globální proměnné třídy a nastaví reakce
	 * na detekci drag and drop
	 * 
	 * @param control
	 *            Control
	 * @param indexForm
	 *            int
	 * @param contexMenu
	 *            ItemContexMenu
	 * @param canvasType
	 *            CanvasType
	 */
	public DragAndDropCanvas(Control control, int indexForm, ItemContexMenu contexMenu, CanvasType canvasType) {

		super();
		this.control = control;
		this.indexForm = indexForm;
		this.contexMenu = contexMenu;
		this.canvasType = canvasType;

		this.canvas = new AnchorPane();
		canvas.setMinWidth(Constans.canvasMaxWidth);
		canvas.setMinHeight(Constans.canvasMaxHeight);
		canvas.setId("canvasID");

		setArrow(false);
		setStartArrow(false);

		this.setId("canvas");
		this.setContent(canvas);

		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		canvas.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		canvas.setOnMouseClicked(OnMousePressedEventHandler);

		this.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (Constans.controlV.match(event)) {
					pasteItem();
				} else if (Constans.controlC.match(event)) {
					copyItem();
				} else if (Constans.controlX.match(event)) {
					cutItem();
				} else if (event.getCode() == KeyCode.DELETE) {
					if(control.getManipulation().getClicItem() != null){
					CanvasItem item = control.getManipulation().getClicItem();
					control.getManipulation().deleteItem(item);
					}else if(control.getManipulation().getLink() != null){
						control.getManipulation().getLink().deleteArrow();
						
					}
					
				} else if (event.getCode() == KeyCode.ESCAPE) {
					if (control.getManipulation().getLink() != null) {
						control.getManipulation().getLink().getBackgroundPolygon().setStroke(Color.TRANSPARENT);
					}
				}

			}

		});

		canvas.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				event.consume();
			}
		});

		canvas.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				}

				event.consume();
			}
		});

		canvas.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				event.consume();
			}
		});

		canvas.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;

				if (db.hasString()) {

					addItem(db.getString(), event.getSceneX(), event.getSceneY());

					success = true;
				}

				event.setDropCompleted(success);

				event.consume();
			}

		});

	}

	/**
	 * Metoda rozhodující o aktuálním modu
	 */

	public boolean changeArrow() {

		if (arrow) {

			arrow = false;
		} else {
			arrow = true;
			setStartArrow(false);
		}

		return arrow;

	}

	/**
	 * Metoda pro reakci na klávesouvou zkratku pro vložení prvku
	 */

	private void pasteItem() {
		control.getManipulation().pasteItem(this);
	}

	/**
	 * Metoda pro reakci na klávesouvou zkratku pro kopírování prvku
	 */
	private void copyItem() {
		CanvasItem item = control.getManipulation().getClicItem();
		control.getManipulation().copyItem(item);
	}

	/**
	 * Metoda pro reakci na klávesouvou zkratku pro vyjmutí prvku
	 */
	private void cutItem() {
		CanvasItem item = control.getManipulation().getClicItem();
		control.getManipulation().cutItem(item);
	}

	/**
	 * Kontrolní metoda pro kontrolu dvojkliku
	 * 
	 * @param t
	 */
	public void setClicFromDragPoint(MouseEvent t) {

		if (t.getButton().equals(MouseButton.SECONDARY)) {
			contexMenu.setDgCanvas(this);

			contexMenu.show(canvas, t.getScreenX(), t.getScreenY());
		}

	}

	/**
	 * MouseEvent Handler pro reakci na stisknutí tlačítka myši a zavolání
	 * kontrolní metody pro vyvolání kontextového okna
	 */
	EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setClicFromDragPoint(t);
		}
	};

	/**
	 * Metoda pro přidání nového prvku na plátno
	 * 
	 * @param segment
	 *            Sting
	 * @param x
	 * @param y
	 * @return CanvasItem
	 */
	public CanvasItem addItem(String segment, double x, double y) {

		SegmentType type = Control.findSegmentType(segment);
		CanvasItem item = new CanvasItem(type, "New", control, control.getForms().get(indexForm), 0, x, y, contexMenu,
				control.getLinkControl(), this);
		canvas.getChildren().add(item);
		return item;

	}

	/**
	 * Metoda pro přídání z kopírovaného prvku na plátno
	 * 
	 * @param segment
	 *            SegmentType
	 * @param x
	 * @param y
	 * @return CanvasItem
	 */
	public CanvasItem addCopyItem(SegmentType segment, double x, double y) {
		if (FormControl.copyControl(segment, canvasType)) {

			CanvasItem item = new CanvasItem(segment, "New", control, control.getForms().get(indexForm), 2, x, y,
					contexMenu, control.getLinkControl(), this);
			canvas.getChildren().add(item);
			return item;
		} else {

			Alerts.badCopyItem(segment, canvasType);
		}
		return null;

	}

	/**
	 * Restartuje plátno. Vymaže všechny prvky plátna
	 */
	public void restart() {

		setArrow(false);
		setStartArrow(false);

		canvas.getChildren().clear();
	}

	/** Getrs and Setrs **/
	public Scene getMScene() {
		return mScene;
	}

	public void setMScene(Scene scene) {
		this.mScene = scene;
	}

	public AnchorPane getCanvas() {
		return canvas;
	}

	public void setCanvas(AnchorPane canvas) {
		this.canvas = canvas;
	}

	public CanvasType getCanvasType() {
		return canvasType;
	}

	public void setCanvasType(CanvasType canvasType) {
		this.canvasType = canvasType;
	}

	public boolean isArrow() {
		return arrow;
	}

	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public boolean isStartArrow() {
		return startArrow;
	}

	public void setStartArrow(boolean startArrow) {
		this.startArrow = startArrow;
	}

}
