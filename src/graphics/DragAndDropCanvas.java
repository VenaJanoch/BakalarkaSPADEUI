package graphics;

import abstractform.BasicForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
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
import services.SegmentType;

public class DragAndDropCanvas extends ScrollPane {

	private Scene mScene;
	private Control control;
	private BasicForm form;
	private int indexForm;
	private AnchorPane canvas;
	private ItemContexMenu contexMenu;

	private CanvasType canvasType;

	private boolean arrow;
	private boolean startArrow;
	
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
	
	public boolean changeArrow() {

		if (arrow) {

			arrow = false;
		} else {
			arrow = true;
			setStartArrow(false);
		}

		return arrow;

	}

	public void setClicFromDragPoint(MouseEvent t) {

		if (t.getButton().equals(MouseButton.SECONDARY)) {
			contexMenu.setDgCanvas(this);

			contexMenu.show(canvas, t.getScreenX(), t.getScreenY());
		}

	}

	EventHandler<MouseEvent> OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			System.out.println("Form6 platno " + control.getForms().toString());
			setClicFromDragPoint(t);
		}
	};

	public void changePosition(double position) {
		this.setLayoutY(position);
	}

	public CanvasItem addItem(String segment, double x, double y) {

		SegmentType type = Control.findSegmentType(segment);
		CanvasItem item = new CanvasItem(type, "Name", control, control.getForms().get(indexForm), 0, x, y, contexMenu,
				control.getLinkControl(), this);
		canvas.getChildren().add(item);
		return item;

	}

	public CanvasItem addCopyItem(SegmentType segment, double x, double y) {
		if (FormControl.copyControl(segment, canvasType)) {

			CanvasItem item = new CanvasItem(segment, "Name", control, control.getForms().get(indexForm), 2, x, y,
					contexMenu, control.getLinkControl(), this);
			canvas.getChildren().add(item);
			return item;
		} else {

			Alerts.badCopyItem(segment, canvasType);
		}
		return null;

	}

	public void restart() {
		
		setArrow(false);
		setStartArrow(false);
		
		canvas.getChildren().clear();
	}

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
