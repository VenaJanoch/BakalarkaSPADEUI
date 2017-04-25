package graphics;

import java.io.IOException;
import java.util.UUID;

import SPADEPAC.Artifact;
import SPADEPAC.Change;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import services.Control;
import services.LinkControl;
import services.SegmentType;

public class NodeLink extends Line {

	protected int[] startIDs;
	protected int[] endIDs;
	protected int id;
	protected Control control;
	protected Point2D startPoint;
	protected Point2D endPoint;
	protected LineComboBox relationCB;
	protected Polygon polygon;
	private SegmentType type;
	protected LinkControl linkControl;

	public NodeLink(int ID, Control control, SegmentType type, LinkControl linkControl) {
		super();

		this.type = type;
		this.control = control;
		this.id = ID;
		this.setVisible(false);
		this.linkControl = linkControl;
		// this.setBackground(new Background(new BackgroundFill(Color.BROWN,
		// CornerRadii.EMPTY, Insets.EMPTY)));

		// this.getChildren().addAll(nodeLink);
		this.setOnMousePressed(circleOnMousePressedEventHandler);
		setId(Integer.toString(ID));

		endPoint = new Point2D(0, 0);

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			pressedDeleteArrow(t);
		}
	};

	public void setStart(Point2D startPoint) {
		this.startPoint = startPoint;

		this.setStartX(startPoint.getX());
		this.setStartY(startPoint.getY());
	}

	protected void deleteArrow() {
		this.setVisible(false);

		linkControl.deleteArrow(id, startIDs[1], endIDs[1]);

	}

	protected void pressedDeleteArrow(MouseEvent t) {

		if (t.getButton().equals(MouseButton.PRIMARY)) {
			if (t.getClickCount() == 2) {
				deleteArrow();
			}

		}

	}

	public void setEnd(Point2D endPoint) {

		this.endPoint = endPoint;

		this.setEndX(endPoint.getX());
		this.setEndY(endPoint.getY());

		this.setVisible(true);

	}

	public void setEnd(Point2D endPoint, double offset) {

		this.endPoint = endPoint;

		this.setEndX(endPoint.getX() - offset);
		this.setEndY(endPoint.getY());

		this.setVisible(true);

	}

	/*** Getrs and Setrs ***/
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
