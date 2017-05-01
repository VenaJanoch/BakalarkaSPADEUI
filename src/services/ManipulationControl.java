package services;

import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.Change;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.Project;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.NodeLink;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ManipulationControl {

	/** Globální proměnné třídy */
	private Control control;
	private int[] itemIds;
	private String itemName;
	private SegmentType type;
	private FillCopyForms copyForms;
	private Project project;
	private SegmentLists lists;
	private DeleteControl deleteControl;
	private ArrayList<BasicForm> forms;
	private CanvasItem clicItem;
	private boolean isCut;
	private NodeLink link;;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 * 
	 * @param control
	 *            instance třídy control
	 * @param copyForms
	 *            instrance třídy copyForms
	 * @param project
	 *            kořenový element
	 * @param lists
	 *            instance třídy SegmentLists
	 * @param deleteControl
	 *            instanace třídy DeleteControl
	 * @param forms
	 *            instance seznamu všech formulářů
	 */
	public ManipulationControl(Control control, FillCopyForms copyForms, Project project, SegmentLists lists,
			DeleteControl deleteControl, ArrayList<BasicForm> forms) {
		this.copyForms = copyForms;
		this.control = control;
		this.project = project;
		this.lists = lists;
		this.deleteControl = deleteControl;
		this.forms = forms;
		isCut = false;
	}

	/**
	 * Restartuje globální proměnné třídy
	 * 
	 * @param copyForms
	 *            instance třídy CopyForm
	 * @param project
	 *            instance kořenového elementu
	 * @param deleteControl
	 *            instance třídy DeleteControl
	 * @param forms
	 *            instance seznamu formulářů
	 */
	public void restart(FillCopyForms copyForms, Project project, DeleteControl deleteControl,
			ArrayList<BasicForm> forms) {
		this.copyForms = copyForms;
		this.project = project;
		this.deleteControl = deleteControl;
		this.forms = forms;

	}

	/**
	 * Uloží data o kopírovaném prvku
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 */
	public void copyItem(CanvasItem item) {

		if (clicItem != null) {
			itemIds = item.getIDs();
			itemName = item.getNameText();
			type = item.getType();
		}

	}

	/**
	 * Uloží data o vyjmutém prvku a smaže ho z plátna
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 */
	public void cutItem(CanvasItem item) {

		if (clicItem != null) {
			copyItem(item);
			deleteItem(item);
		}
	}

	/**
	 * Smaže prvek ze seznamů a zneviditelní na plátně
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 */
	public void deleteItem(CanvasItem item) {
		if (clicItem != null) {
			item.setVisible(false);
			item.deleteLinks();
			int index = item.getIDs()[0];
			if (!forms.get(index).isNew()) {
				forms.get(index).deleteItem(item.getIDs());
			}

		}
		clicItem = null;

	}

	/**
	 * Vloží nový pvek na plátno
	 * 
	 * @param canvas
	 *            instance třídy DragAndDrop
	 */
	public void pasteItem(DragAndDropCanvas canvas) {
		if (clicItem != null && itemIds != null) {

			int index = itemIds[0];
			if (forms.get(index).isNew()) {
				canvas.addItem(type.name(), 0, 0);
			} else {
				canvas.addCopyItem(type, 0, 0);
			}
		}

	}

	/**
	 * Rozhodne, který segment nebo element se vytvoří
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 * @param form
	 *            instance seznamu formulářů
	 * @return pole identifikátorů prvku
	 */
	public int[] createCopyForm(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();

		int[] IDs = new int[4];

		switch (sType) {
		case Phase:
			Phase phase = project.getPhases().get(itemIds[1]);
			return copyForms.createPhase(item, form, phase, IDs, itemIds);

		case Iteration:

			Iteration iteration = project.getIterations().get(itemIds[1]);
			return copyForms.createIteration(item, form, iteration, IDs, itemIds);
		case Activity:

			Activity activity = project.getActivities().get(itemIds[1]);
			return copyForms.createActivity(item, form, activity, IDs, itemIds);

		case WorkUnit:

			WorkUnit unit = lists.getWorkUnitList().get(itemIds[1]);
			return copyForms.createWorkUnit(item, form, unit, IDs, itemIds);

		case Change:
			Change change = lists.getChangeList().get(itemIds[1]);
			return copyForms.createChange(item, form, change, IDs, itemIds);

		case Artifact:
			Artifact artifact = lists.getArtifactList().get(itemIds[1]);
			return copyForms.createArtifact(item, form, artifact, IDs, itemIds);

		default:
			return IDs;
		}
	}

	/**
	 * Metoda pro vytvoření nových Work Unit u nových prvků s daty starých
	 * 
	 * @param canvasItem
	 *            instance třídy CanvasItem
	 * @param rootForm
	 *            kořenový formulář
	 * @return
	 */
	public int[] createCopyWorkUnitForm(CanvasItem canvasItem, BasicForm rootForm) {
		int[] IDs = new int[4];
		return copyForms.createCopyWorkUnit(canvasItem, rootForm, IDs);
	}

	/** Getrs and Setrs */

	public CanvasItem getClicItem() {
		return clicItem;
	}

	public void setClicItem(CanvasItem clicItem) {
		this.clicItem = clicItem;
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
}
