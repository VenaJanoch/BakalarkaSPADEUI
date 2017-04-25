package graphics;

import SPADEPAC.ArtifactClass;
import SPADEPAC.WorkUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import services.Alerts;
import services.Constans;
import services.Control;
import services.LinkControl;
import services.SegmentType;

public class WorkUnitLink extends NodeLink {

	private AnchorPane canvas;

	public WorkUnitLink(int ID, Control control, AnchorPane canvas, LinkControl linkControl) {
		super(ID, control, SegmentType.WorkUnit, linkControl);

		this.canvas = canvas;

		relationCB = new LineComboBox(control);
		polygon = new Polygon();

		relationCB.setVisible(false);
		polygon.setVisible(false);

		canvas.getChildren().addAll(relationCB, polygon);
		// this.getChildren().addAll(relationCB, polygon);

	}

	public void setArrowAndBox(Point2D endPointL) {

		setEnd(endPointL, Constans.ArrowRadius);

		Point2D center = control.calculateCenter(startPoint, endPoint);

		relationCB.setTranslateX(center.getX());
		relationCB.setTranslateY(center.getY());

		WorkUnit left = control.getLists().getWorkUnitList().get(getStartIDs()[1]);
		WorkUnit right = control.getLists().getWorkUnitList().get(getEndIDs()[1]);
		relationCB.setLeftUnit(left);
		relationCB.setRightUnit(right);
		relationCB.setStartIDs(startIDs);
		relationCB.setEndIDs(endIDs);

		polygon.getPoints().addAll(control.calculateArrowPosition(endPoint));

		relationCB.setVisible(true);

		polygon.setVisible(true);
	}
	
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			pressedDeleteArrow(t);
		}
	};


	protected void deleteArrow(){
		this.setVisible(false);
		relationCB.setVisible(false);
		relationCB = null;
		polygon.setVisible(false);
		polygon = null;
			linkControl.deleteWorkUnitArrow(id, startIDs[1], endIDs[1]);
	}
	
	
	protected void pressedDeleteArrow(MouseEvent t) {
		
		if (t.getButton().equals(MouseButton.PRIMARY)) {
			if (t.getClickCount() == 2) {
				deleteArrow();
			}

		}

	}

}
