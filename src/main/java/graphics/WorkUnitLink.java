package graphics;

import controllers.CanvasController;
import controllers.ManipulationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import services.Constans;
import controllers.LinkControl;
import tables.BasicTable;

/**
 * Třída vykreslující spojení mezi WorkUnity odděděná od třídy NodeLink
 *
 * @author Václav Janoch
 */
public class WorkUnitLink extends NodeLink {


    private LineComboBox relationCB;
    private Polygon polygon;
    private int relationIndex;

    /**
     * Konstruktor třídy
     * Naplní globálí proměné rodičovské třídy
     *
     * @param ID
     * @param linkControl
     */

    public WorkUnitLink(int ID, LinkControl linkControl, CanvasController canvasController, ObservableList<BasicTable> relationTypeList, ManipulationController manipulationController) {
        super(ID, linkControl, canvasController, manipulationController);

        relationCB = new LineComboBox(relationTypeList);
        relationCB.getSelectionModel().selectedIndexProperty().addListener(relationListener);
        polygon = new Polygon();

        relationCB.setVisible(false);
        polygon.setVisible(false);

        canvasController.addRelationCBToCanvas(relationCB, polygon);
      }

    /**
     * Metoda pro nastavení koncového bodu spojnice, zviditelnění spojnice, šipky a výběrového boxu
     *
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



    public void setRelationIndexToComboBox(int relationIndex){
        relationCB.getSelectionModel().select(relationIndex);
    }

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

        } else {

        }

    }

    public void repaintEndPolygon(double x, double y) {
        setEndPoint(new Point2D(x, y), Constans.ArrowRadius);
        polygon.getPoints().clear();
        polygon.getPoints().addAll(linkController.calculateArrowPosition(endPoint));
    }

    public void repaintComboBox() {

        Point2D center = linkController.calculateCenter(startPoint, endPoint);
        relationCB.setTranslateX(center.getX());
        relationCB.setTranslateY(center.getY());
    }

    ChangeListener<Number> relationListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            relationIndex = newValue.intValue();
       //     linkControl.setRealtionIndexToLink(id, relationIndex);

        }
    };
}
