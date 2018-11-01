package graphics;

import Controllers.CanvasController;
import Controllers.DragController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
	private CanvasController canvasController;
	private DragController dragController;



	/**
	 * Přetížený konstruktor třídy pro přidání tlačítek do formulářového okna
	 * Nastaví vlastnosti tlačítka
	 * @param type SegmentType
	 */
	public DragSegmentButton(SegmentType type) {
		//this(type);
		this.setOnAction(event -> canvasController.addCanvasItemFromPanel(type.name(),0,0));

	}

	/**
	 * Přetížený konstruktor třídy pro přidání tlačítek do hlavního okna
	 * Nastaví vlastnosti tlačítka
	 * @param type SegmentType
	 * @param canvasController DragAndDropCanvas
	 */
	public DragSegmentButton(SegmentType type, CanvasController canvasController) {
		//this(type);
		super();
		//this.setType(type); //todo smazat Segment type vymyslet jak rozlisit jednotliva platna
		this.setText(type.name());
		this.setFont(Font.font ("Verdana", 15));
		this.setId("dg"+ type);
		setDragDetected();
		setDragDone();
		this.canvasController = canvasController;
		this.dragController = dragController;
		this.setOnAction(event -> canvasController.addCanvasItemFromPanel(type.name(),0,0));

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
	private void setDragDone(){
		new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getTransferMode() == TransferMode.MOVE) {
				}
				event.consume();
			}
		};
	}




	/** Getrs and Setrs	 */
//	public SegmentType getType() {
//		return type;
//	}

//	public void setType(SegmentType type) {
//		this.type = type;
//	}

}
