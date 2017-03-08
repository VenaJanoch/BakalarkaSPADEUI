package Obsluha;

import java.util.ArrayList;

import Forms.ActivityForm;
import Forms.ArtifactForm;
import Forms.BasicForm;
import Forms.BranchForm;
import Forms.ChangeForm;
import Forms.ConfigPersonRelationForm;
import Forms.ConfigurationForm;
import Forms.CriterionForm;
import Forms.IterationForm;
import Forms.MilestoneForm;
import Forms.PhaseForm;
import Forms.RoleForm;
import Forms.WorkUnitForm;
import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Grafika.NodeLink;
import javafx.geometry.Point2D;

public class Control {

	private boolean arrow;
	private boolean startArrow;
	private ArrayList<BasicForm> forms;
	private ArrayList<NodeLink> arrows;
	private int index;
	private NodeLink link;
	private IdentificatorCreater idCreater;
	private int id;

	public Control() {
		idCreater = new IdentificatorCreater();
		setArrow(false);
		setStartArrow(false);
		setForms(new ArrayList<>());
		arrows = new ArrayList<>();
		index = 0;
	}

	public boolean changeArrow() {

		if (arrow) {

			arrow = false;

		} else {
			arrow = true;
		}

		return arrow;

	}

	public void ArrowManipulation(CanvasItem item) {

		if (!isStartArrow()) {
			id = idCreater.createLineID();
			link = new NodeLink(id);
			item.getCanvas().getChildren().add(link);
			getArrows().add(id, link);
			link.setStart(new Point2D(item.getTranslateX() + (item.getWidth()),
					item.getTranslateY() + (item.getHeight() / 2)));
			item.registerStartLink(id);
			setStartArrow(true);

		} else {
			link.setEnd(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
			item.registerEndLink(id);
			setStartArrow(false);

		}

	}

	public SegmentType findSegmentType(String segmentName) {

		for (int i = 0; i < SegmentType.values().length; i++) {

			if (SegmentType.values()[i].name().equals(segmentName)) {

				return SegmentType.values()[i];
			}

		}
		return null;

	}

	public int[] createForm(CanvasItem item) {
		SegmentType sType = item.getType();
		int[] IDs = new int[2];
		switch (sType) {
		case Phase:

			forms.add(index, new PhaseForm(item));
			index++;
			IDs[0] = index - 1;
			IDs[1] = idCreater.createPhaseID();
			return IDs;

		case Iteration:
			forms.add(index, new IterationForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createIterationID();
			return IDs;
		case Activity:
			forms.add(index, new ActivityForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createActivityID();
			return IDs;
		case WorkUnit:
			forms.add(index, new WorkUnitForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createWorkUnitID();
			return IDs;
		case Milestone:
			forms.add(index, new MilestoneForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createMilestoneID();
			return IDs;
		case Criterion:
			forms.add(index, new CriterionForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createCriterionID();
			return IDs;
		case Configuration:
			forms.add(index, new ConfigurationForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createConfigurationID();
			return IDs;
		case ConfigPersonRelation:
			forms.add(index, new ConfigPersonRelationForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createCPRID();
			return IDs;
		case Branch:
			forms.add(index, new BranchForm(item));
			System.out.println("Branch");
			IDs[0] = index;
			System.out.println("Branch1");

			index++;
			System.out.println("Branch2");

			IDs[1] = idCreater.createBranchID();

			System.out.println("Branch3");

			return IDs;
		case Change:
			forms.add(index, new ChangeForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createChangeID();
			return IDs;
		case Artifact:
			forms.add(index, new ArtifactForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createArtifactID();
			return IDs;
		case Role:
			forms.add(index, new RoleForm(item));
			IDs[0] = index;
			index++;
			IDs[1] = idCreater.createRoleID();
			return IDs;

		default:
			return IDs;
		}
	}

	/** Getrs and Setrs ***/

	public boolean isArrow() {
		return arrow;
	}

	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public ArrayList<BasicForm> getForms() {
		return forms;
	}

	public void setForms(ArrayList<BasicForm> forms) {
		this.forms = forms;
	}

	public boolean isStartArrow() {
		return startArrow;
	}

	public void setStartArrow(boolean startArrow) {
		this.startArrow = startArrow;
	}

	public ArrayList<NodeLink> getArrows() {
		return arrows;
	}

	public void setArrows(ArrayList<NodeLink> arrows) {
		this.arrows = arrows;
	}

}
