package services;

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
import javafx.scene.layout.HBox;

public class ManipulationControl {

	private Control control;
	private int[] itemIds;
	private String itemName;
	private SegmentType type;
	private FillCopyForms copyForms;
	private Project project;
	private SegmentLists lists;

	public ManipulationControl(Control control, FillCopyForms copyForms, Project project, SegmentLists lists) {
		this.copyForms = copyForms;
		this.control = control;
		this.project = project;
		this.lists = lists;
	}

	public void restart(FillCopyForms copyForms, Project project) {
		this.copyForms = copyForms;
		this.project = project;

	}

	public void copyItem(CanvasItem item) {

		itemIds = item.getIDs();
		itemName = item.getNameText();
		type = item.getType();

	}

	public void cutItem(CanvasItem item) {

		itemIds = item.getIDs();
		itemName = item.getNameText();
		type = item.getType();

		deleteItem(item);
	}

	public void deleteItem(CanvasItem item) {

	}

	public void pasteItem(DragAndDropCanvas canvas) {

		CanvasItem item = canvas.addCopyItem(type, 0, 0);

	}

	public int[] createCopyForm(CanvasItem item, BasicForm form) {
		SegmentType sType = item.getType();

		int[] IDs = new int[3];

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

			WorkUnit unit = form.getWorkUnitArray().get(itemIds[2]);
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
}
