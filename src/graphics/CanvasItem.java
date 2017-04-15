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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Constans;
import services.Control;
import services.FillCopyForms;
import services.FillForms;
import services.FillFormsXML;
import services.ManipulationControl;
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
	private ManipulationControl manipulation;
	private Tooltip tooltip;

	private ItemContexMenu contextMenu;

	private NodeLink mDragLink = null;
	private AnchorPane canvas = null;
	private DragAndDropCanvas dgCanvas;

	private final List<Integer> mStartLinkIds = new ArrayList<Integer>();
	private final List<Integer> mEndLinkIds = new ArrayList<Integer>();

	int[] IDs;
	private int idForm;
	private String ID;

	private SegmentType type;
	private BasicForm form;

	public CanvasItem(SegmentType type, String name, Control control, BasicForm rootForm, int isCreated, double x,
			double y, ItemContexMenu contexMenu) {
		this.setOnMousePressed(circleOnMousePressedEventHandler);
		this.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		this.setOnMouseReleased(onMouseReleaseEventHandler);
		this.setForm(rootForm);
		this.setType(type);
		this.setFillForms(control.getFillForms());
		this.setFillFormsXML(control.getFillFormsXML());
		this.manipulation = control.getManipulation();
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.contextMenu = contexMenu;
		//this.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));

		if (isCreated == 0) {
			IDs = control.createForm(this, rootForm);
		} else if (isCreated == 1) {
			IDs = control.createFormFromXML(this, rootForm);
		} else {
			IDs = manipulation.createCopyForm(this, rootForm);
		}

		this.dgCanvas = control.getCanvas();

		idForm = IDs[0];
		ID = type.name() + "_" + String.format("%03d", IDs[1]);

		this.tooltip = new Tooltip(ID);
		Tooltip.install(this, tooltip);

		this.control = control;
		this.segmentInfo = new InfoBoxSegment(this, type, name);
		this.length = segmentInfo.getLength();
		this.setMaxHeight(segmentInfo.getHeight());
		this.setMaxWidth(segmentInfo.getLength());
		this.getChildren().add(segmentInfo);

		mDragLink = new NodeLink(-1, control);
		mDragLink.setVisible(false);

		parentProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				canvas = (AnchorPane) getParent();
			}

		});

	}

	public void setClicFromDragPoint(MouseEvent t) {

		if (t.getButton().equals(MouseButton.PRIMARY)) {

			if (control.isArrow()) {
				if (type == SegmentType.WorkUnit) {
					control.ArrowManipulationWorkUnit(this);
				} else if (type == SegmentType.Artifact || type == SegmentType.Change) {
					control.ArrowManipulation(this);
				}

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
		} else if (t.getButton().equals(MouseButton.SECONDARY)) {
			contextMenu.setItem(this);
			contextMenu.show(canvas, t.getScreenX(), t.getScreenY());
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

			NodeLink link = control.getArrows().get(arrowIndex);

			link.setStart(new Point2D(width, height));

			if (type == SegmentType.WorkUnit) {
				Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
				link.relationCB.setTranslateX(center.getX());
				link.relationCB.setTranslateY(center.getY());
			}

		}
	}

	private void repaintEndArrow() {

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			NodeLink link = control.getArrows().get(mEndLinkIds.get(i));

			link.setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)));

			if (type == SegmentType.WorkUnit) {
				link.setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)), Constans.ArrowRadius);
				Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
				link.relationCB.setTranslateX(center.getX());
				link.relationCB.setTranslateY(center.getY());
				link.polygon.getPoints().clear();
				link.polygon.getPoints().addAll(control.calculateArrowPosition(link.endPoint));
			}

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

	public String getNameText() {

		return segmentInfo.getName().getText();
	}

	public void registerStartLink(int linkId) {
		mStartLinkIds.add(linkId);
	}

	public void registerEndLink(int linkId) {
		mEndLinkIds.add(linkId);
	}

	public void deleteStartLink(int linkId) {
		mStartLinkIds.remove(linkId);
	}

	public void deleteEndLink(int linkId) {
		mEndLinkIds.remove(linkId);
	}

	/*** Getrs and Setrs ***/

	public AnchorPane getCanvas() {
		return canvas;
	}

	public void setCanvas(AnchorPane canvas) {
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
