package graphics;

import SPADEPAC.ArtifactClass;
import SPADEPAC.WorkUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Point2D;
import javafx.scene.control.ComboBox;
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
}
