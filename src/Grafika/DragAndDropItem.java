package Grafika;

import Obsluha.Constans;
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

public class DragAndDropItem extends HBox{
	Group root = new Group();
	DragSegment[] dragSegmnets;
	
	public DragAndDropItem() {
		super(5);
		this.setAlignment(Pos.CENTER);
		this.setMinWidth(Constans.width);
		dragSegmnets = new DragSegment[Constans.countDragItems];
		createDragItems();
	}
	
	
	public void createDragItems(){
		
		for (int i = 0; i < dragSegmnets.length; i++) {
			dragSegmnets[i] = new DragSegment(Constans.dragItemsName[i]);
		}
		
		this.getChildren().addAll(dragSegmnets);
	}
	

}
