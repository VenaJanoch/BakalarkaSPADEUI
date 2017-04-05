package graphics;

import abstractform.BasicForm;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import services.SegmentType;

public class DragText extends Button {

	private SegmentType type;
	private DragAndDropCanvas canvas;
	
	public DragText(SegmentType type) {
		super();
		this.setType(type);
		this.setText(type.name());
		setDragDetected();
		setDragDone();

	}

	
	
	
	public DragText(SegmentType type, DragAndDropCanvas canvas) {
		this(type);
		this.canvas = canvas;
		this.setOnAction(event -> canvas.addItem(type.name()));

	}
	
	public DragText(SegmentType type, BasicForm form) {
		this(type);
		this.setOnAction(event -> form.getCanvas().addItem(type.name()));

	}

	private void setDragDetected() {

		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				//System.out.println("onDragDetected");
				dragDetected();
				event.consume();
			}
		});
	}

	private void dragDetected(){
		Dragboard db = this.startDragAndDrop(TransferMode.ANY);

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString(this.getText());
		db.setContent(content);

	}
	private void setDragDone(){
		
	this.setOnDragDone(new EventHandler<DragEvent>() {
		public void handle(DragEvent event) {
			if (event.getTransferMode() == TransferMode.MOVE) {
				
			}

			event.consume();
		}
	});
	
}

	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}

}
