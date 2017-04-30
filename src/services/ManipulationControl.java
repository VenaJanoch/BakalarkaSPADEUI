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

	public void restart(FillCopyForms copyForms, Project project, DeleteControl deleteControl,
			ArrayList<BasicForm> forms) {
		this.copyForms = copyForms;
		this.project = project;
		this.deleteControl = deleteControl;
		this.forms = forms;

	}

	public void copyItem(CanvasItem item) {
		System.out.println(item.getID() + " click " + clicItem.getID());
		if (clicItem != null) {
			System.out.println(item.getID());
			itemIds = item.getIDs();
			itemName = item.getNameText();
			type = item.getType();
		}

	}

	public void cutItem(CanvasItem item) {

		if (clicItem != null) {
			copyItem(item);
			deleteItem(item);
		}
	}

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

		// case Configuration:
		//
		// return fillFormsXML.createConfigurationFormXML(item, form, IDs);
		//
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

	public int[] createCopyWorkUnitForm(CanvasItem canvasItem, BasicForm rootForm) {
		int[] IDs = new int[4];
		return copyForms.createCopyWorkUnit(canvasItem, rootForm, IDs);
	}

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
