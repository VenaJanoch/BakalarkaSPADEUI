package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import services.SegmentType;
/**
 * Třída definující tlačítko umožnující přídání prvku na plátno
 * @author Václav Janoch
 *
 */
public class DragSegmentButton extends Button {

	/** Globální proměnné třídy **/
	//private SegmentType type;
	private double x = 0;
	private double y = 0;

	/**
	 * Přetížený konstruktor třídy pro přidání tlačítek do hlavního okna
	 * Nastaví vlastnosti tlačítka
	 * @param type SegmentType
	 * @param canvasController DragAndDropCanvas
	 */
	public DragSegmentButton(SegmentType type, CanvasController canvasController) {
		super();
		//this.setText(type.name());
		this.setFont(Font.font ("Verdana", 15));
		this.setId("dg"+ type);
		this.setBackground(Background.EMPTY);
		setDragDetected();
		setDragDone();
		this.setOnAction(event -> canvasController.addCanvasItemFromPanel(type.name(),x,y));

	}

	/**
	 * Pomocná metoda pro detekci drag and drop
	 */
	private void setDragDetected() {

		this.setOnDragDetected(event -> {
			Dragboard db = this.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.putString(this.getText());
			db.setContent(content);
			event.consume();
			}
		);
	}

	/**
	 * Metoda pro nastavení dokončení drag and drop
	 */
	private void setDragDone() {

		this.setOnDragDone(event -> { //TODO Umistit prvek na pozici mysi
			if (event.getTransferMode() == TransferMode.MOVE) {
				x = getTranslateX();
				y = getTranslateY();
			}
			event.consume();
		});
	}

}
