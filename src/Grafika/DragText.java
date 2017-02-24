package Grafika;

import javafx.event.EventHandler;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DragText extends Text {

	
	public DragText(String name) {
		super();
		this.setText(name);
		this.setFont(Font.font ("Verdana",15));
		this.setFill(Color.BURLYWOOD);
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
