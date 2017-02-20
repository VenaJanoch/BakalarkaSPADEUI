package Obsluha;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DragSegment extends Text {

	
	public DragSegment(String name) {
		super();
		this.setText(name);
		this.setFont(Font.font ("Verdana",11));
		this.setFill(Color.AQUA);
		setDragDetected();
		setDragDone();

	}

	private void setDragDetected() {

		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				System.out.println("onDragDetected");
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
			System.out.println("onDragDone");
			/* if the data was successfully moved, clear it */
			if (event.getTransferMode() == TransferMode.MOVE) {
				
			}

			event.consume();
		}
	});
	
}

}
