package graphics;

import SPADEPAC.Relation;
import SPADEPAC.WorkUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import services.Control;

public class LineComboBox extends ComboBox<String>{

	private Control control;
	private int relationIndex;
	
	private int[] startIDs;
	private int[] endIDs;
	
	private WorkUnit leftUnit;
	private WorkUnit rightUnit;
	
	public LineComboBox(Control control) {
		super(control.getLists().getRelationTypeObservable());
		this.control = control;
		
	
		
		this.getSelectionModel().selectedIndexProperty().addListener(relationListener);
		this.setVisibleRowCount(5);

	}
	
	

	ChangeListener<Number> relationListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			setRelationIndex(newValue.intValue());
			leftUnit.setRelationIndex(relationIndex);
			rightUnit.setRelationIndex(relationIndex);
					
		}
	};
	
	
	public int getRelationIndex() {
		return relationIndex;
	}

	public void setRelationIndex(int relationIndex) {
		this.relationIndex = relationIndex;
	}

	public WorkUnit getLeftUnit() {
		return leftUnit;
	}

	public void setLeftUnit(WorkUnit leftUnit) {
		this.leftUnit = leftUnit;
	}

	public WorkUnit getRightUnit() {
		return rightUnit;
	}

	public void setRightUnit(WorkUnit rightUnit) {
		this.rightUnit = rightUnit;
	}

	public int[] getStartIDs() {
		return startIDs;
	}

	public void setStartIDs(int[] startIDs) {
		this.startIDs = startIDs;
	}

	public int[] getEndIDs() {
		return endIDs;
	}

	public void setEndIDs(int[] endIDs) {
		this.endIDs = endIDs;
	}
	

	
	

}
