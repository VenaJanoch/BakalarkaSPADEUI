package graphics;

import java.util.ArrayList;
import java.util.List;

import abstractform.BasicForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.Constans;
import services.Control;
import services.FillForms;
import services.FillFormsXML;
import services.SegmentType;

public class CanvasItem extends AnchorPane {

	private InfoBoxSegment segmentInfo;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

	private double length;
	private Scene scene;
	private Control control;
	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private Tooltip tooltip;

	private NodeLink mDragLink = null;
	private DragAndDropCanvas canvas = null;

	private final List<Integer> mStartLinkIds = new ArrayList<Integer>();
	private final List<Integer> mEndLinkIds = new ArrayList<Integer>();

	int[] IDs;
	private int idForm;
	private String ID;

	private SegmentType type;
	private BasicForm form;

	public CanvasItem(SegmentType type, String name, Control control, BasicForm rootForm, boolean isCreated) {

		this.setOnMousePressed(circleOnMousePressedEventHandler);
		this.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		this.setOnMouseReleased(onMouseReleaseEventHandler);
		this.setForm(rootForm);
		this.setType(type);
		this.setFillForms(control.getFillForms());
		this.setFillFormsXML(control.getFillFormsXML());
		
		if (isCreated) {
			IDs = control.createForm(this, rootForm);
		} else {
			IDs = control.createFormFromXML(this, rootForm);
		}

		idForm = IDs[0];
		ID = type.name() + "_" + String.format("%03d", IDs[1]);

		this.tooltip = new Tooltip(ID);
		Tooltip.install(this, tooltip);

		this.control = control;
		this.segmentInfo = new InfoBoxSegment(this, type, name);
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

	public void setClicFromDragPoint(MouseEvent t) {

		if (t.getButton().equals(MouseButton.PRIMARY)) {

			if (control.isArrow()) {
				control.ArrowManipulation(this);

			} else {

				if (t.getClickCount() == 2) {
					control.getForms().get(idForm).show();

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

		// if (t.getSceneX() > 0 && t.getSceneX() < canvas.getWidth() &&
		// t.getSceneY() > canvas.getTranslateY() + 90
		// && t.getSceneY() < canvas.getHeight() + 50) {
		((AnchorPane) (t.getSource())).setTranslateX(newTranslateX);
		((AnchorPane) (t.getSource())).setTranslateY(newTranslateY);

		// }

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
			int arrowIndex = mStartLinkIds.get(i);
			double width = getTranslateX() + segmentInfo.getLength();
			double height = getTranslateY() + (getHeight() / 2);
			
			control.getArrows().get(arrowIndex).setStart(new Point2D(width, height));

		}
	}

	private void repaintEndArrow() {

		for (int i = 0; i < mEndLinkIds.size(); i++) {

			control.getArrows().get(mEndLinkIds.get(i))
					.setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)));

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

	public void setNameText(String name) {

		segmentInfo.setNameText(name);
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

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getIdForm() {
		return idForm;
	}

	public void setIdForm(int idForm) {
		this.idForm = idForm;
	}

	public SegmentType getType() {
		return type;
	}

	public void setType(SegmentType type) {
		this.type = type;
	}

	public Control getControl() {
		return control;
	}

	public void setControl(Control control) {
		this.control = control;
	}

	public int[] getIDs() {
		return IDs;
	}

	public void setIDs(int[] iDs) {
		IDs = iDs;
	}

	public BasicForm getForm() {
		return form;
	}

	public void setForm(BasicForm form) {
		this.form = form;
	}

	public FillFormsXML getFillFormsXML() {
		return fillFormsXML;
	}

	public void setFillFormsXML(FillFormsXML fillFormsXML) {
		this.fillFormsXML = fillFormsXML;
	}

	public FillForms getFillForms() {
		return fillForms;
	}

	public void setFillForms(FillForms fillForms) {
		this.fillForms = fillForms;
	}

}
