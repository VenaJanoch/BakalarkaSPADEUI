package graphics;

import controllers.CanvasController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import services.Constans;


/**
 * Třída reprezentujcí kreslící plátno
 * 
 * @author Václav Janoch
 *
 */
public class DragAndDropCanvas extends ScrollPane {

	/** Globální proměnné třídy **/
	private Scene mScene;
	private AnchorPane canvas;
	private ItemContexMenu contexMenu;

	private CanvasController canvasController;




	/**
	 * Konstruktor třídy Zinicializuje Globální proměnné třídy a nastaví reakce
	 * na detekci drag and drop
	 * 
	 * @param canvasController
	 *            ItemContexMenu
	 */
	public DragAndDropCanvas(CanvasController canvasController) {

		super();
		this.canvasController = canvasController;

		this.canvas = new AnchorPane();

		canvas.setMinWidth(Constans.canvasMaxWidth);
		canvas.setMinHeight(Constans.canvasMaxHeight);

		canvas.setId("canvasID");
		this.setId("canvas");
		this.setContent(canvas);

		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

		canvas.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		canvas.setOnMouseClicked(canvasController.getOnMousePressedHandler());

		this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
				canvasController.keyPressAction(event);
			});

		canvas.setOnDragOver(event -> {
				canvasController.dragAndOver(event);
		});

		canvas.setOnDragEntered(event ->  {
			event.consume();

		});

		canvas.setOnDragExited(event ->  {
				event.consume();
		});

		canvas.setOnDragDropped(event ->  {
			canvasController.dragAndDrop(event);


		});

	}

	public void clearCanvas(){
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

}
