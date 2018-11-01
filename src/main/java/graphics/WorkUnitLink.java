package graphics;

import SPADEPAC.WorkUnit;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import services.Constans;
import services.Control;
import Controllers.LinkControl;
import services.SegmentType;

/**
 * Třída vykreslující spojení mezi WorkUnity odděděná od třídy NodeLink
 * @author Václav Janoch
 *
 */
public class WorkUnitLink extends NodeLink {
	
	/**
	 * Konstruktor třídy
	 * Naplní globálí proměné rodičovské třídy
	 * @param ID
	 * @param control
	 * @param canvas
	 * @param linkControl
	 */
	
	public WorkUnitLink(int ID, Control control, AnchorPane canvas, LinkControl linkControl) {
		super(ID, control, SegmentType.WorkUnit, linkControl, canvas);

		relationCB = new LineComboBox(control);
		polygon = new Polygon();

		relationCB.setVisible(false);
		polygon.setVisible(false);

		canvas.getChildren().addAll(relationCB, polygon);

	}

	/**
	 * Metoda pro nastavení koncového bodu spojnice, zviditelnění spojnice, šipky a výběrového boxu
	 * @param endPointL koncový bod
	 */
	
	public void setArrowAndBox(Point2D endPointL) {

		setEndPoint(endPointL, Constans.ArrowRadius);

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

	/**
	 * MouseEvent handler pro reakci na kliknutí na šipku
	 */
	EventHandler<MouseEvent> polygonMouseEvent = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
				pressedDeleteArrow(t);
		}
	};

	/**
	 * Metoda pro smazání spojnice mezi Work Units, vymazání spojení z datových struktur
	 */
	public void deleteArrow() {
		this.setVisible(false);
		relationCB.setVisible(false);
		relationCB = null;
		polygon.setVisible(false);
		polygon = null;
		getBackgroundPolygon().setVisible(false);
		//setBackgroundPolygon(null);
		linkControl.deleteWorkUnitArrow(id, startIDs[1], endIDs[1]);
	}

	/**
	 * Metoda kontrolující dvojklik na spojnici
	 */
	protected void pressedDeleteArrow(MouseEvent t) {
		
		control.getManipulation().setLink(this);
		control.getManipulation().setClicItem(null);
		
		getBackgroundPolygon().setStroke(Color.BLACK);
		getBackgroundPolygon().getStrokeDashArray().add(2d);
		
		if (t.getButton().equals(MouseButton.PRIMARY)) {
			if (t.getClickCount() == 2) {
				deleteArrow();
			}

		}else{
		
		}

	}

}
