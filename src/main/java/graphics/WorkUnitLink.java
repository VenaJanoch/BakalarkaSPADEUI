package graphics;

import Controllers.CanvasController;
import Controllers.ListController;
import SPADEPAC.WorkUnit;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import services.Constans;
import Controllers.LinkControl;

/**
 * Třída vykreslující spojení mezi WorkUnity odděděná od třídy NodeLink
 * @author Václav Janoch
 *
 */
public class WorkUnitLink extends NodeLink {


	private LineComboBox relationCB;
	private Polygon polygon;

	/**
	 * Konstruktor třídy
	 * Naplní globálí proměné rodičovské třídy
	 * @param ID
	 * @param linkControl
	 */
	
	public WorkUnitLink(int ID, LinkControl linkControl, ListController listController, CanvasController canvasController) {
		super(ID, linkControl);

		relationCB = new LineComboBox(listController);
		polygon = new Polygon();

		relationCB.setVisible(false);
		polygon.setVisible(false);

		canvasController.addRelationCBToCanvas(relationCB, polygon);

	}

	/**
	 * Metoda pro nastavení koncového bodu spojnice, zviditelnění spojnice, šipky a výběrového boxu
	 * @param endPointL koncový bod
	 */
	
	public void setArrowAndBox(Point2D endPointL) {

		setEndPoint(endPointL, Constans.ArrowRadius);

		Point2D center = linkController.calculateCenter(startPoint, endPoint);

		relationCB.setTranslateX(center.getX());
		relationCB.setTranslateY(center.getY());

		polygon.getPoints().addAll(linkController.calculateArrowPosition(endPoint));

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
		linkControl.deleteWorkUnitArrow(linkController);
	}

	/**
	 * Metoda kontrolující dvojklik na spojnici
	 */
	protected void pressedDeleteArrow(MouseEvent t) {
		
	//	control.getManipulation().setLink(this);
	//	control.getManipulation().setClicItem(null);

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
