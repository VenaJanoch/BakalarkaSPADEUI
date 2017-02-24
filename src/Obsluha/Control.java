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
import Grafika.InfoBoxSegment;

public class Control {

	private boolean arrow;
	private ArrayList<BasicForm> forms;
	private int index;

	public Control() {
		setArrow(false);
		setForms(new ArrayList<>());
		index = 0;
	}

	public int createForm(InfoBoxSegment infoBox) {

		System.out.println(infoBox.getSegmentName().getText());

		if (Constans.dragItemsName[0].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new PhaseForm(infoBox));
			index++;
			return index - 1;
		} else if (Constans.dragItemsName[1].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new IterationForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[2].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new ActivityForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[3].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new WorkUnitForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[4].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new MilestoneForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[5].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new CriterionForm(infoBox));
			index++;
			return index - 1 ;

		}else if (Constans.dragItemsName[6].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new ConfigurationForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[7].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new ConfigPersonRelationForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[8].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new BranchForm(infoBox));
		index++;
			return index - 1;

		} else if (Constans.dragItemsName[9].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new ChangeForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[10].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new ArtifactForm(infoBox));
			index++;
			return index - 1;

		} else if (Constans.dragItemsName[11].equals(infoBox.getSegmentName().getText())) {

			forms.add(index, new RoleForm(infoBox));
			index++;
			return index - 1;
		}

		return -1;
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

}
