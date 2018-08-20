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

/**
 * /** Třída vykreslující spojení mezi Change a Artifact
 * 
 * @author Václav Janoch
 *
 */

public class NodeLink extends Line {

	/*** Globální proměnné třídy */
	protected int[] startIDs;
	protected int[] endIDs;
	protected int id;
	protected Control control;
	protected Point2D startPoint;
	protected Point2D endPoint;
	protected LineComboBox relationCB;
	protected Polygon polygon;
	private Polygon backgroundPolygon;
	private SegmentType type;
	protected LinkControl linkControl;
	protected AnchorPane canvas;

	/**
	 * Konstruktor třídy Zinicizalizuje globální proměnné třídy
	 * 
	 * @param ID
	 *            Identifikace spojnice
	 * @param control
	 *            instance třídy Control
	 * @param type
	 *            SegmentType
	 * @param linkControl
	 *            LinkControl
	 * @param canvas
	 *            Canvas
	 */
	public NodeLink(int ID, Control control, SegmentType type, LinkControl linkControl, AnchorPane canvas) {
		super();

		this.type = type;
		this.canvas = canvas;
		this.control = control;
		this.id = ID;
		this.setVisible(false);
		this.linkControl = linkControl;
		setId(Integer.toString(ID));

		endPoint = new Point2D(0, 0);
		backgroundPolygon = new Polygon();
		backgroundPolygon.setOnMouseClicked(polygonMouseEvent);
		backgroundPolygon.setFill(Color.TRANSPARENT);
		canvas.getChildren().add(backgroundPolygon);
	}

	/**
	 * MouseEvent Handler pro reakci na kliknutí do okolí spojnice a zvýraznění
	 * oblasti šipky a zavolání kontroly smazání šipky
	 */
	EventHandler<MouseEvent> polygonMouseEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			backgroundPolygon.setStroke(Color.BLACK);
			backgroundPolygon.getStrokeDashArray().add(2d);

			pressedDeleteArrow(t);
		}
	};

	/**
	 * Metoda pro nastavení počáteční polohy spojnice
	 * 
	 * @param startPoint
	 *            Point2D počáteční bod
	 */
	public void setStart(Point2D startPoint) {
		this.startPoint = startPoint;

		this.setStartX(startPoint.getX());
		this.setStartY(startPoint.getY());
		if (endPoint != null) {
			backgroundPolygon.getPoints().clear();
			backgroundPolygon.getPoints().addAll(control.countBackgroundPlygon(startPoint, endPoint));
		}
	}

	/**
	 * Metoda pro smazání spojice mezi prvky a zavolání metody pro smazání
	 * spojení z datových struktur
	 */
	public void deleteArrow() {
		this.setVisible(false);
		backgroundPolygon.setVisible(false);
		linkControl.deleteArrow(id, startIDs[1], endIDs[1]);

	}

	/**
	 * Kontrolní metoda pro reakci na dvojklik do okolí spojnice
	 * 
	 * @param t
	 *            MouseEvent
	 */
	protected void pressedDeleteArrow(MouseEvent t) {
		
		control.getManipulation().setLink(this);
		control.getManipulation().setClicItem(null);
		if (t.getButton().equals(MouseButton.PRIMARY)) {
			if (t.getClickCount() == 2) {
				deleteArrow();
			}

		}

	}

	/**
	 * Metoda pro nastavení koncového bodu spojnice, spočtení velikosti
	 * obdelníku pro zvýranění a zobrazení spojnice
	 * 
	 * @param endPoint
	 */
	public void setEnd(Point2D endPoint) {

		this.endPoint = endPoint;

		this.setEndX(endPoint.getX());
		this.setEndY(endPoint.getY());
		this.setVisible(true);
		backgroundPolygon.getPoints().clear();
		backgroundPolygon.getPoints().addAll(control.countBackgroundPlygon(startPoint, endPoint));
		backgroundPolygon.setVisible(true);

	}

	/**
	 * Přetížená metoda umožnující přídání offsetu pro výpočet velikosti
	 * spojnice a možnosti vložení šipky určující směr
	 * 
	 * @param endPoint
	 * @param offset
	 */
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

	public Polygon getBackgroundPolygon() {
		return backgroundPolygon;
	}

	public void setBackgroundPolygon(Polygon backgroundPolygon) {
		this.backgroundPolygon = backgroundPolygon;
	}

}
