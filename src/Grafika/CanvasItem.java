package Grafika;

import java.util.ArrayList;
import java.util.List;

import Obsluha.Constans;
import Obsluha.Control;
import Obsluha.DragContainer;
import Obsluha.SegmentType;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class CanvasItem extends AnchorPane {

	private InfoBoxSegment segmentInfo;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	private double length;
	private Scene scene;
	private Control control;

	private NodeLink mDragLink = null;
	private DragAndDropCanvas canvas = null;

	private final List<Integer> mStartLinkIds = new ArrayList<Integer>();
	private final List<Integer> mEndLinkIds = new ArrayList<Integer>();

	private boolean firstClick;
	private int idForm;

	
	
	
	public CanvasItem(Scene scene, SegmentType type, String name, Control control) {

		this.setOnMousePressed(circleOnMousePressedEventHandler);
		this.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		this.setOnMouseReleased(onMouseReleaseEventHandler);

		this.firstClick = true;
		this.scene = scene;
		this.control = control;
		this.segmentInfo = new InfoBoxSegment(this, type, name, scene, control);
		this.length = segmentInfo.getLength();
		this.getChildren().add(segmentInfo);

		mDragLink = new NodeLink(-1);
		mDragLink.setVisible(false);

		parentProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				canvas = (DragAndDropCanvas) getParent();

			}

		});

	}

	public void setNewCoordStartLine() {

	}

	public void setNewCoordendLine() {

	}

	public void setClicFromDragPoint(MouseEvent t) {

		if (t.getButton().equals(MouseButton.PRIMARY)) {

			if (control.isArrow()) {
				control.ArrowManipulation(this);

			} else {

				if (t.getClickCount() == 2) {
					pressedHandleControl();

				} else {

					orgSceneX = t.getSceneX();
					orgSceneY = t.getSceneY();
					orgTranslateX = ((AnchorPane) (t.getSource())).getTranslateX();
					orgTranslateY = ((AnchorPane) (t.getSource())).getTranslateY();
				}
			}
		}

	}

	public void setDragFromDragPoint(MouseEvent t) {

		double offsetX = t.getSceneX() - orgSceneX;
		double offsetY = t.getSceneY() - orgSceneY;
		double newTranslateX = orgTranslateX + offsetX;
		double newTranslateY = orgTranslateY + offsetY;

		((AnchorPane) (t.getSource())).setTranslateX(newTranslateX);
		((AnchorPane) (t.getSource())).setTranslateY(newTranslateY);

	}

	EventHandler<MouseEvent> onMouseReleaseEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			repaintStartArrow();
			repaintEndArrow();

		}

	};

	private void repaintStartArrow() {

		for (int i = 0; i < mStartLinkIds.size(); i++) {
			control.getArrows().get(mStartLinkIds.get(i))
					.setStart(new Point2D(getTranslateX() + (getWidth()), getTranslateY() + (getHeight() / 2)));
		}
	}

	private void repaintEndArrow() {
		for (int i = 0; i < mEndLinkIds.size(); i++) {

			control.getArrows().get(i).setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)));
		}

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setClicFromDragPoint(t);
		}
	};

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setDragFromDragPoint(t);
		}
	};

	public void pressedHandleControl() {

		if (firstClick) {
			idForm = control.createForm(segmentInfo);
			firstClick = false;
		} else {
			control.getForms().get(idForm).show();
		}

	}

	
	
	/*** Getrs and Setrs ***/
	
	public void registerStartLink(int linkId) {
		mStartLinkIds.add(linkId);
	}

	public void registerEndLink(int linkId) {
		mEndLinkIds.add(linkId);
	}

	public DragAndDropCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(DragAndDropCanvas canvas) {
		this.canvas = canvas;
	}

}
