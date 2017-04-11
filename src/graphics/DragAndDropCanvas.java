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
import services.Constans;
import services.Control;
import services.SegmentType;

public class DragAndDropCanvas extends HBox {

	private Scene mScene;
	private Control control;
	private BasicForm form;
	private int indexForm;
	
	public DragAndDropCanvas(Control control, int indexForm) {

		super();
		this.control = control;
		this.indexForm = indexForm;
		this.setMinWidth(Constans.canvasMaxWidth);
		this.setMinHeight(Constans.canvasMaxHeight);
		
		        
        
		this.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data is dragged over the target */

				/*
				 * accept it only if it is not dragged from the same node and if
				 * it has a string data
				 */
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					/*
					 * allow for both copying and moving, whatever user chooses
					 */
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}

				event.consume();
			}
		});

		this.setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* the drag-and-drop gesture entered the target */
				/* show to the user that it is an actual gesture target */
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
				}

				event.consume();
			}
		});

		this.setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* mouse moved away, remove the graphical cues */
				// this.setBackground(new Background(new
				// BackgroundFill(Color.BLUE, CornerRadii.EMPTY,
				// Insets.EMPTY)));

				event.consume();
			}
		});

		this.setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				/* data dropped */
				/* if there is a string data on dragboard, read it and use it */
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					addItem(db.getString());

					success = true;
				}
				/*
				 * let the source know whether the string was successfully
				 * transferred and used
				 */
				event.setDropCompleted(success);

				event.consume();
			}

		});

	}

	ChangeListener<Number> scListener  = new ChangeListener<Number>()  {
        public void changed(ObservableValue<? extends Number> ov,
            Number old_val, Number new_val) {
        	
           changePosition(-new_val.doubleValue());
        
        }
    };
    
    public void changePosition(double position){
    	this.setLayoutY(position);           
    }
    
	public void addItem(String segment) {

		SegmentType type = control.findSegmentType(segment);
		
		this.getChildren().add(new CanvasItem(type, "Name", control, control.getForms().get(indexForm) , true));

	}
	
	public void restart(){
		this.getChildren().clear();
	}

	public Scene getMScene() {
		return mScene;
	}

	public void setMScene(Scene scene) {
		this.mScene = scene;
	}

}
