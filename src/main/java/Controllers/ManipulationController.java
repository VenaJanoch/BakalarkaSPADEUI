package Controllers;

import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.NodeLink;
import javafx.scene.paint.Color;
import services.*;

public class ManipulationController {

	/** Globální proměnné třídy */

	private int[] itemIds;
	private String itemName;
	private SegmentType type;
	private DeleteControl deleteControl;

	private CanvasItem chooseCanvasItem;
	private DragAndDropCanvas chooseCanvas;


	private boolean isCut;
	private boolean isCopy;

	private NodeLink link;;

	private FormController formController;
	private CanvasItemController canvasItemController;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 *            instance třídy control
	 */
	public ManipulationController(FormController formController, CanvasItemController canvasItemController ) {
		//this.copyForms = copyForms;
		//this.control = control;
		//this.project = project;
		//this.lists = lists;
		//this.deleteControl = deleteControl;
		this.formController = formController;
		this.canvasItemController = canvasItemController;
		//this.forms = forms;
		isCut = false;
	}

	/**
	 * Uloží data o kopírovaném prvku
	 * 
	 *
	 */
	public void copyItem(CanvasController canvasController) {

			this.isCopy = true;

	}

	public void controlCopyItem() {

		if (!isCopy){
			chooseCanvasItem = null;
		}
	}

	/**
	 * Uloží data o vyjmutém prvku a smaže ho z plátna
	 * 

	 */
	public void cutItem(CanvasController canvasController) {

		if (chooseCanvasItem != null) {
			copyItem(canvasController);
			deleteItem(canvasController);
		}
	}

	/**
	 * Smaže prvek ze seznamů a zneviditelní na plátně
	 *
	 */
	public void deleteItem(CanvasController canvasController) {
		if (chooseCanvasItem != null) {
			canvasItemController.deleteItem(chooseCanvasItem);
			int index = chooseCanvasItem.getFormIdentificator();
			formController.deleteForm(index);
		}
		chooseCanvasItem = null;

	}

	/**
	 * Vloží nový pvek na plátno
	 *
	 */
	public void pasteItem(CanvasController canvasController) {
		SegmentType segmentType = canvasItemController.getSegmentType(chooseCanvasItem);
		CanvasType canvasType  = canvasController.getCanvasType();

		if (chooseCanvasItem != null) {

			if(FormControl.copyControl(segmentType, canvasType)){

				canvasController.addCopyCanvasItemToCanvas(segmentType, 0, 0);
				formController.createCopyForm(chooseCanvasItem.getFormIdentificator(),segmentType);
			}
		} else {
			Alerts.badCopyItem(segmentType, canvasType);
		}

	}


	/** Getrs and Setrs */

	public CanvasItem getChooseCanvasItem() {
		return chooseCanvasItem;
	}

	public void setChooseCanvasItem(CanvasItem chooseCanvasItem) {
		this.chooseCanvasItem = chooseCanvasItem;
	}

	public NodeLink getLink() {
		return link;
	}

	public void setLink(NodeLink link) {
		if (this.link != null) {
			this.link.getBackgroundPolygon().setStroke(Color.TRANSPARENT);
		}
		this.link = link;
	}

	public DragAndDropCanvas getChooseCanvas() {
		return chooseCanvas;
	}

	public void setChooseCanvas(DragAndDropCanvas chooseCanvas) {
		this.chooseCanvas = chooseCanvas;
	}


}
