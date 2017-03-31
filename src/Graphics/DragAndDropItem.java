package Graphics;

import Services.Constans;
import Services.Control;
import Services.SegmentType;
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
	private HBox box;
	private DragText[] dragSegmnets;
	private int[] itemArray; 
	private Control control;

	public DragAndDropItem(Control control, int[] itemArray) {
		super(5);
		this.itemArray = itemArray;
		this.control = control;
		this.setAlignment(Pos.CENTER);
		this.setMaxWidth(Constans.width);
		//this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);
		dragSegmnets = new DragText[itemArray.length];
		createDragItems();
	}

	public void createDragItems() {

		
		box = new HBox(5);
		box.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < itemArray.length; i++) {
				dragSegmnets[i] = new DragText(SegmentType.values()[itemArray[i]]);
				box.getChildren().add(dragSegmnets[i]);
		
		}

		this.getChildren().addAll(box);
	}

}
