package graphics;

import Controllers.LinkController;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import Controllers.LinkControl;

/**
 * /** Třída vykreslující spojení mezi Change a Artifact
 *
 * @author Václav Janoch
 */

public abstract class NodeLink extends Line {

    /*** Globální proměnné třídy */
    //protected int[] startIDs;
    //protected int[] endIDs;
    //protected Control control;
    protected Point2D startPoint;
    protected Point2D endPoint;

    protected Polygon backgroundPolygon;
    //	private SegmentType type;
    protected LinkControl linkControl;
    protected LinkController linkController;


    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param Id          Identifikace spojnice
     *                    SegmentType
     * @param linkControl LinkControl
     */
    public NodeLink(int Id, LinkControl linkControl) {
        super();

        this.linkController = new LinkController(Id);
        this.setVisible(false);
        this.linkControl = linkControl;
        setId(Integer.toString(Id));

        endPoint = new Point2D(0, 0);
        backgroundPolygon = new Polygon();
        backgroundPolygon.setOnMouseClicked(event -> {
            backgroundPolygon.setStroke(Color.BLACK);
            backgroundPolygon.getStrokeDashArray().add(2d);

        });
        backgroundPolygon.setFill(Color.TRANSPARENT);
        //	canvas.getChildren().add(backgroundPolygon);
    }


    abstract void pressedDeleteArrow(MouseEvent t);

    abstract void deleteArrow();


    /**
     * Metoda pro nastavení počáteční polohy spojnice
     *
     * @param startPoint Point2D počáteční bod
     */
    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;

        this.setStartX(startPoint.getX());
        this.setStartY(startPoint.getY());
        if (endPoint != null) {
            backgroundPolygon.getPoints().clear();
            backgroundPolygon.getPoints().addAll(linkController.countBackgroundPlygon(startPoint, endPoint));
        }
    }


    /**
     * Metoda pro nastavení koncového bodu spojnice, spočtení velikosti
     * obdelníku pro zvýranění a zobrazení spojnice
     *
     * @param endPoint
     */
    public void setEndPoint(Point2D endPoint) {

        this.endPoint = endPoint;

        this.setEndX(endPoint.getX());
        this.setEndY(endPoint.getY());
        this.setVisible(true);
        backgroundPolygon.getPoints().clear();
        backgroundPolygon.getPoints().addAll(linkController.countBackgroundPlygon(startPoint, endPoint));
        backgroundPolygon.setVisible(true);

    }

    /**
     * Přetížená metoda umožnující přídání offsetu pro výpočet velikosti
     * spojnice a možnosti vložení šipky určující směr
     *
     * @param endPoint
     * @param offset
     */
    public void setEndPoint(Point2D endPoint, double offset) {

        this.endPoint = endPoint;

        this.setEndX(endPoint.getX() - offset);
        this.setEndY(endPoint.getY());

        this.setVisible(true);

    }

    /*** Getrs and Setrs ***/

    public Polygon getBackgroundPolygon() {
        return backgroundPolygon;
    }

    public void setBackgroundPolygon(Polygon backgroundPolygon) {
        this.backgroundPolygon = backgroundPolygon;
    }

}
