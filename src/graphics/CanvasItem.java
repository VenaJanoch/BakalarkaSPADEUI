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
import javafx.scene.input.KeyEvent;
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
import services.LinkControl;
import services.ManipulationControl;
import services.SegmentType;

/**
 * Třída představující prvek plátna, kontajner pro přídu InfoBoxSegment
 * 
 * @author Václav Janoch
 *
 */

public class CanvasItem extends AnchorPane {
	/** Globální proměnné tříd **/
	private InfoBoxSegment segmentInfo;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

	private double length;
	private Scene scene;
	private Control control;
	private FillFormsXML fillFormsXML;
	private FillForms fillForms;
	private FillCopyForms fillCopy;
	private ManipulationControl manipulation;
	private Tooltip tooltip;

	private ItemContexMenu contextMenu;

	private AnchorPane canvas = null;
	private DragAndDropCanvas dgCanvas;

	private final List<Integer> mStartLinkIds = new ArrayList<Integer>();
	private final List<Integer> mEndLinkIds = new ArrayList<Integer>();

	int[] IDs;
	private int idForm;
	private String ID;

	private SegmentType type;
	private BasicForm form;
	private LinkControl linkControl;

	/**
	 * Konstruktor třídy. Zinicializuje globální proměnné třídy. Získá
	 * identifikátory formuláře a objektu v seznamech. Vytvoří zobrazované ID
	 * prvku
	 * 
	 * @param type
	 *            SegmentType
	 * @param name
	 * @param control
	 *            Control
	 * @param rootForm
	 *            BasicForm
	 * @param isCreated
	 * @param x
	 * @param y
	 * @param contexMenu
	 *            ItemContexMenu
	 * @param linkControl
	 *            LinkControl
	 * @param dgCanvas
	 *            DragAndDropCanvas
	 */
	public CanvasItem(SegmentType type, String name, Control control, BasicForm rootForm, int isCreated, double x,
			double y, ItemContexMenu contexMenu, LinkControl linkControl, DragAndDropCanvas dgCanvas) {
		this.setOnMousePressed(circleOnMousePressedEventHandler);
		this.setOnMouseDragged(circleOnMouseDraggedEventHandler);
		this.setOnMouseReleased(onMouseReleaseEventHandler);
		this.setForm(rootForm);
		this.setType(type);
		this.setFillForms(control.getFillForms());
		this.setFillFormsXML(control.getFillFormsXML());
		this.fillCopy = control.getFillCopy();
		this.manipulation = control.getManipulation();
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.contextMenu = contexMenu;
		this.linkControl = linkControl;
		this.dgCanvas = dgCanvas;
		this.segmentInfo = new InfoBoxSegment(this, type, name);

		if (isCreated == 0) {
			IDs = control.createForm(this, rootForm);
		} else if (isCreated == 1) {
			IDs = control.createFormFromXML(this, rootForm);
		} else if (isCreated == 2) {
			IDs = manipulation.createCopyForm(this, rootForm);
		} else {
			IDs = manipulation.createCopyWorkUnitForm(this, rootForm);
		}
		idForm = IDs[0];
		ID = type.name() + "_" + String.format("%03d", IDs[1]);
		if (IDs[0] != 0) {
			String title = control.getForms().get(IDs[0]).getTitle();
			control.getForms().get(IDs[0]).getFormName().setText(title + " " + ID);

		}

		this.setID(ID);
		this.tooltip = new Tooltip(ID);
		Tooltip.install(this, tooltip);

		this.control = control;
		segmentInfo.getSegmentName().setText(ID);

		this.length = segmentInfo.getLength();
		this.setMaxHeight(segmentInfo.getHeight());
		this.setMaxWidth(segmentInfo.getLength());
		this.getChildren().add(segmentInfo);

		parentProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				canvas = (AnchorPane) getParent();
			}

		});

	}

	/**
	 * Metoda určující reakce na kliknutí na prvek, Při jednoduchém kliku načte
	 * aktuální polohu prvku pro výpočet posunu prvku, nebo pokud se jedná o mod
	 * kreslení vybere prvek pro spojení. Dvojklikem vyvolá příslušny formulář
	 * 
	 * @param t
	 */
	public void setClicFromDragPoint(MouseEvent t) {
		manipulation.setClicItem(this);
		manipulation.setLink(null);
		if (t.getButton().equals(MouseButton.PRIMARY)) {

			if (dgCanvas.isArrow()) {

				if (type == SegmentType.WorkUnit) {

					linkControl.ArrowManipulationWorkUnit(this, false);
				} else if (type == SegmentType.Artifact || type == SegmentType.Change) {

					linkControl.ArrowManipulation(this, false);
				}

			} else {

				if (t.getClickCount() == 2) {
					control.getForms().get(idForm).show();
					control.getForms().get(idForm).toFront();

				} else if (!dgCanvas.isArrow()) {

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

	/**
	 * Pomocná metoda pro výpočet polohy prvku při drag and drop
	 * 
	 * @param t
	 */
	public void setDragFromDragPoint(MouseEvent t) {
		if (!dgCanvas.isArrow()) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((AnchorPane) (t.getSource())).setTranslateX(newTranslateX);
			((AnchorPane) (t.getSource())).setTranslateY(newTranslateY);

		}

	}

	/**
	 * Pomocná metoda pro kontrolu polohy prvku
	 * 
	 * @param point
	 *            Point2D
	 */
	public void setPosition(Point2D point) {
		this.setTranslateX(point.getX());
		this.setTranslateY(point.getY());
	}

	/**
	 * Event handler pro zachycení spuštění tlačítka myši na prvku
	 */
	EventHandler<MouseEvent> onMouseReleaseEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setPosition(control.canvasItemPositionControl(getTranslateX(), getTranslateY()));
			repaintStartArrow();
			repaintEndArrow();

		}

	};

	/**
	 * Překreslí spojnici mezi prvky po přesunu počátečního prvku
	 */
	private void repaintStartArrow() {

		for (int i = 0; i < mStartLinkIds.size(); i++) {
			if (linkControl.getArrows().get(mStartLinkIds.get(i)) != null) {

				int arrowIndex = mStartLinkIds.get(i);

				double width = getTranslateX() + segmentInfo.getLength();
				double height = getTranslateY() + (getHeight() / 2);

				NodeLink link = linkControl.getArrows().get(arrowIndex);

				link.setStart(new Point2D(width, height));

				if (type == SegmentType.WorkUnit) {
					Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
					link.relationCB.setTranslateX(center.getX());
					link.relationCB.setTranslateY(center.getY());
				}
			}
		}
	}

	/**
	 * Překreslí spojnici mezi prvky po přesunu koncového prvku
	 */
	private void repaintEndArrow() {

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			if (linkControl.getArrows().get(mEndLinkIds.get(i)) != null) {
				NodeLink link = linkControl.getArrows().get(mEndLinkIds.get(i));

				link.setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)));

				if (type == SegmentType.WorkUnit) {
					link.setEnd(new Point2D(getTranslateX(), getTranslateY() + (getHeight() / 2)),
							Constans.ArrowRadius);
					Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
					link.relationCB.setTranslateX(center.getX());
					link.relationCB.setTranslateY(center.getY());
					link.polygon.getPoints().clear();
					link.polygon.getPoints().addAll(control.calculateArrowPosition(link.endPoint));
				}

			}
		}

	}

	/**
	 * Event handler pro zachycení kliku na prvek
	 */
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setClicFromDragPoint(t);
		}
	};

	/**
	 * Event hadler pro zachycení drag and drop
	 */
	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setDragFromDragPoint(t);
		}
	};

	/**
	 * Metoda pro smazání spojnic mezi prvky a smazání z datových struktur
	 */
	public void deleteLinks() {

		for (int i = 0; i < mStartLinkIds.size(); i++) {
			if (linkControl.getArrows().get(mStartLinkIds.get(i)) != null) {
				linkControl.getArrows().get(mStartLinkIds.get(i)).deleteArrow();
			}
		}

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			if (linkControl.getArrows().get(mEndLinkIds.get(i)) != null) {
				linkControl.getArrows().get(mEndLinkIds.get(i)).deleteArrow();
			}
		}

	}

	/** Gerts and Setrs **/
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

	public DragAndDropCanvas getDgCanvas() {
		return dgCanvas;
	}

	public void setDgCanvas(DragAndDropCanvas dgCanvas) {
		this.dgCanvas = dgCanvas;
	}

	public FillCopyForms getFillCopy() {
		return fillCopy;
	}

	public void setFillCopy(FillCopyForms fillCopy) {
		this.fillCopy = fillCopy;
	}

	public InfoBoxSegment getSegmentInfo() {
		return segmentInfo;
	}

	public void setSegmentInfo(InfoBoxSegment segmentInfo) {
		this.segmentInfo = segmentInfo;
	}

}
