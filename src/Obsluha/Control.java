package Obsluha;

import java.util.ArrayList;

import com.sun.javafx.geom.transform.GeneralTransform3D;

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

		int id = idCreater.createLineID();
		if (!isStartArrow()) {
		link = new NodeLink(id);
			
			item.getCanvas().getChildren().add(link);
			getArrows().add(id,link);
			link.setStart(new Point2D(item.getTranslateX() + (item.getWidth()), item.getTranslateY()+(item.getHeight() / 2)));
			item.registerStartLink(id);			
			setStartArrow(true);
		
		} else {
			link.setEnd(new Point2D(item.getTranslateX(), item.getTranslateY()+(item.getHeight() / 2)));
			item.registerEndLink(id);
			setStartArrow(false);
			
		}

	}

	public SegmentType findSegmentType(String segmentName){
		
		for (int i = 0; i < SegmentType.values().length; i++) {
			
			if (SegmentType.values()[i].name().equals(segmentName)) {
				
				return SegmentType.values()[i];
			}
			
		}
		return null;
		
	}
		
	
	public int createForm(InfoBoxSegment infoBox) {
		SegmentType sType = infoBox.getType();

		switch (sType) {
		case Phase:
			forms.add(index, new PhaseForm(infoBox));
			index++;
			return index - 1;

		case Iteration:
			forms.add(index, new IterationForm(infoBox));
			index++;
			return index - 1;
		case Activity:
			forms.add(index, new ActivityForm(infoBox));
			index++;
			return index - 1;

		case WorkUnit:
			forms.add(index, new WorkUnitForm(infoBox));
			index++;
			return index - 1;
		

		case Milestone:
			forms.add(index, new MilestoneForm(infoBox));
			index++;
			return index - 1;

		case Criterion:
			forms.add(index, new CriterionForm(infoBox));
			index++;
			return index - 1;

		case Configuration:
			forms.add(index, new ConfigurationForm(infoBox));
			index++;
			return index - 1;

		case ConfigPersonRelation:
			forms.add(index, new ConfigPersonRelationForm(infoBox));
			index++;
			return index - 1;
		case Branch:
			forms.add(index, new BranchForm(infoBox));
			index++;
			return index - 1;

		case Change:
			forms.add(index, new ChangeForm(infoBox));
			index++;
			return index - 1;
		case Artifact:
			forms.add(index, new ArtifactForm(infoBox));
			index++;
			return index - 1;
		case Role:
			forms.add(index, new RoleForm(infoBox));
			index++;
			return index - 1;
		default:
			return -1;
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
