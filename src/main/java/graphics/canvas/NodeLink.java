package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.LinkController;
import controllers.formControllers.ManipulationController;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import controllers.LinkControl;
import services.LinkType;

/**
 * /** Třída vykreslující spojení mezi Change a Artifact
 *
 * @author Václav Janoch
 */

public abstract class NodeLink extends Line {

    /*** Globální proměnné třídy */
    protected Point2D startPoint;
    protected Point2D endPoint;

    protected Polygon backgroundPolygon;
    protected LinkControl linkControl;
    protected LinkController linkController;
    protected CanvasController canvasController;
    protected ManipulationController manipulationController;
    protected LinkType linkType;
    protected int id;


    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param Id          Identifikace spojnice
     *                    SegmentType
     * @param linkControl LinkControl
     */
    public NodeLink(int Id, LinkControl linkControl, CanvasController canvasController, ManipulationController manipulationController) {
        super();

        this.manipulationController = manipulationController;
        this.linkController = new LinkController(Id);
        this.canvasController = canvasController;
        this.setVisible(false);
        this.linkControl = linkControl;
        setId(Integer.toString(Id));
        this.id = Id;
        endPoint = new Point2D(0, 0);
        backgroundPolygon = new Polygon();
        backgroundPolygon.setFill(Color.TRANSPARENT);
        backgroundPolygon.getStrokeDashArray().add(2d);
        canvasController.addPolygonToCanvas(backgroundPolygon);
    }


    abstract void pressedDeleteArrow(MouseEvent t);

    abstract void deleteArrow(boolean isModelDelete);


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

    public void setIdsToController(int startId, int endId) {
        linkController.setIds(startId, endId);
    }


    public void coverBackgroundPolygon() {
        backgroundPolygon.setStroke(Color.TRANSPARENT);
    }

    /*** Getrs and Setrs ***/

    public Polygon getBackgroundPolygon() {
        return backgroundPolygon;
    }

    public void repaintArrowStartPoint(int index, double newWidth, double newHeight) {

        linkController.repaintArrowStartPoint(this, newWidth, newHeight);

    }

    public void repaintArrowEndPoint(int index, double newWidth, double newHeight) {

        linkController.repaintArrowEndPoint(this, newWidth, newHeight);

    }

    public void setLinkType(LinkType linkType) {
        this.linkType = linkType;
    }


    public int getLinkId() {
        return id;
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public Point2D getEndPoint() {
        return endPoint;
    }
}
