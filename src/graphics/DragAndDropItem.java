package graphics;

import abstractform.BasicForm;
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
import services.Constans;
import services.Control;
import services.SegmentType;

public class DragAndDropItem extends HBox {
	
	private DragText[] dragSegmnets;
	private int[] itemArray;
	private Control control;
	BasicForm form;

	public DragAndDropItem(int[] itemArray) {
		super(5);
		this.setMaxWidth(Constans.width);
		this.setPadding(new Insets(10));
		this.itemArray = itemArray;
		this.setId("dgItem");
	//	this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		dragSegmnets = new DragText[itemArray.length];
	}

	public DragAndDropItem(Control control, int[] itemArray) {
		this(itemArray);
		this.control = control;

		createDragItemsProject();
	}

	public DragAndDropItem(Control control, int[] itemArray, BasicForm form) {
		this(itemArray);
		this.control = control;
		this.form = form;

		createDragItems();
	}

	public void createDragItems() {

		
		for (int i = 0; i < itemArray.length; i++) {
			dragSegmnets[i] = new DragText(SegmentType.values()[itemArray[i]], form);
			this.getChildren().add(dragSegmnets[i]);

		}

	}

	public void createDragItemsProject() {

		
		for (int i = 0; i < itemArray.length; i++) {
			dragSegmnets[i] = new DragText(SegmentType.values()[itemArray[i]], control.getCanvas());

			this.getChildren().add(dragSegmnets[i]);

		}


	}

}
