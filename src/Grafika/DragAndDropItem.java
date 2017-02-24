package Grafika;

import Obsluha.Constans;
import Obsluha.Control;
import Obsluha.DragSegment;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DragAndDropItem extends VBox {
	Group root = new Group();
	HBox[] box;
	DragSegment[] dragSegmnets;
	private Control control;

	public DragAndDropItem(Control control) {
		super(5);
		this.control = control;
		this.setAlignment(Pos.CENTER);
		this.setMinWidth(Constans.width);
		//this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);
		dragSegmnets = new DragSegment[Constans.countDragItems];
		createDragItems();
	}

	public void createDragItems() {

		int countBox = dragSegmnets.length / Constans.countItemsBox;
		
		box = new HBox[countBox];
		
		int k = 0, j;
		for (int i = 0; i < box.length; i++) {
			box[i] = new HBox(5);
			box[i].setAlignment(Pos.CENTER);
			for ( j = k ; j < (k+(dragSegmnets.length/countBox)); j++) {
				dragSegmnets[j] = new DragSegment(Constans.dragItemsName[j]);
				box[i].getChildren().add(dragSegmnets[j]);
			
			}
			
			k= j;

		}

		this.getChildren().addAll(box);
	}

}
