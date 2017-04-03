package graphics;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import services.SegmentType;

public class DragText extends Text {

	private SegmentType type;
	public DragText( SegmentType type) {
		super();
		this.setType(type);
		this.setText(type.name());
		this.setFont(Font.font ("Verdana",25));
		this.setFill(Color.BURLYWOOD);
		setDragDetected();
		setDragDone();

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
			/* the drag-and-drop gesture ended */
			//System.out.println("onDragDone");
			/* if the data was successfully moved, clear it */
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
