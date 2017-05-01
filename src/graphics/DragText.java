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
/**
 * Třída definující tlačítko umožnující přídání prvku na plátno
 * @author Václav Janoch
 *
 */
public class DragText extends Button {

	/** Globální proměnné třídy **/
	private SegmentType type;
	private DragAndDropCanvas canvas;
	
	/**
	 * Konstruktor třídy
	 * Nastaví vlastnosti tlačítka a drag and drop vlastností
	 * @param type SegmentType
	 */
	public DragText(SegmentType type) {
		super();
		this.setType(type);
		this.setText(type.name());
		this.setFont(Font.font ("Verdana", 15));
		this.setId("dg"+ type);
		setDragDetected();
		setDragDone();

	}
	
	/**
	 * Přetížený konstruktor třídy pro přidání tlačítek do hlavního okna
	 * Nastaví vlastnosti tlačítka
	 * @param type SegmentType
	 * @param canvas DragAndDropCanvas
	 */
	public DragText(SegmentType type, DragAndDropCanvas canvas) {
		this(type);
		this.canvas = canvas;
		this.setOnAction(event -> canvas.addItem(type.name(),0,0));

	}
	
	/**
	 * Přetížený konstruktor třídy pro přidání tlačítek do formulářového okna
	 * Nastaví vlastnosti tlačítka
	 * @param type SegmentType
	 * @param form formulář
	 */
	public DragText(SegmentType type, BasicForm form) {
		this(type);
		this.setOnAction(event -> form.getCanvas().addItem(type.name(),0,0));

	}

	/**
	 * Pomocná metoda pro detekci drag and drop
	 */
	private void setDragDetected() {

		this.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				
				dragDetected();
				event.consume();
			}
		});
	}

	/**
	 * Metoda pro nastavení detekce drag and drop
	 */
	
	private void dragDetected(){
		Dragboard db = this.startDragAndDrop(TransferMode.ANY);

		ClipboardContent content = new ClipboardContent();
		content.putString(this.getText());
		db.setContent(content);

	}
	
	/**
	 * Metoda pro nastavení dokončení drag and drop
	 */
	private void setDragDone(){
		
	this.setOnDragDone(new EventHandler<DragEvent>() {
		public void handle(DragEvent event) {
			if (event.getTransferMode() == TransferMode.MOVE) {
				
			}

			event.consume();
		}
	});
	
}
	/** Getrs and Setrs	 */
	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}

}
