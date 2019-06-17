package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import services.Constans;
import services.SegmentType;

/**
 * Třída představující prvek plátna, kontajner pro přídu InfoBoxSegment
 *
 * @author Václav Janoch
 */

public class CanvasItem extends AnchorPane {
    /**
     * Globální proměnné tříd
     **/
    private InfoBoxSegment segmentInfo;
    private Polygon contour;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    private double length;

    private Tooltip tooltip;
    private ItemContexMenu contextMenu;

    private CanvasItemController canvasItemController;
    private CanvasController canvasController;

    private SegmentType segmentType;

    private String segmentIdentificator;
    private int formIdentificator;

    /**
     * Konstruktor třídy. Zinicializuje globální proměnné třídy. Získá
     * identifikátory formuláře a objektu v seznamech. Vytvoří zobrazované ID
     * prvku
     *
     * @param name      BasicForm
     * @param isCreated
     * @param x
     * @param y
     */
    public CanvasItem(SegmentType type, String segmentIdentificator, int formIdentificator, String name, int instanceCount, int isCreated, double x,
                      double y, CanvasController canvasController, CanvasItemController canvasItemController) {

        this.canvasController = canvasController;
        this.canvasItemController = canvasItemController;
        this.setOnMousePressed(event -> canvasItemController.setClicFromDragPoint(event, this, canvasController, type));
        this.setOnMouseDragged(event -> canvasItemController.setDragFromDragPoint(event, this, canvasController));
        this.setOnMouseReleased(event -> canvasItemController.releasedItem());

        //this.setForm(rootForm);
        this.segmentType = type;
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.segmentInfo = new InfoBoxSegment(this, type, name, Integer.toString(instanceCount));

        //this.canvasItemController.connectItemWithForm(isCreated);
        this.segmentIdentificator = segmentIdentificator;
        this.formIdentificator = formIdentificator;

        this.setID(segmentIdentificator);
        this.tooltip = new Tooltip(segmentIdentificator);
        Tooltip.install(this, tooltip);

        segmentInfo.getSegmentName().setText(segmentIdentificator);

        this.length = segmentInfo.getLength();
        this.setMaxHeight(segmentInfo.getHeight());
        this.setMaxWidth(segmentInfo.getLength());
        this.getChildren().add(segmentInfo);


        this.contour = new Polygon();
        contour.getPoints().addAll(createPolygon(segmentInfo.getTranslateX() - Constans.CONTOURE_OFFSET, segmentInfo.getTranslateY() - Constans.CONTOURE_OFFSET,
                segmentInfo.getLength() + (2 * Constans.CONTOURE_OFFSET), segmentInfo.getTotalHeight() + (2 * Constans.CONTOURE_OFFSET)));
        contour.setFill(Color.TRANSPARENT);
        contour.setStroke(Color.TRANSPARENT);
        contour.getStrokeDashArray().add(2d);
        this.getChildren().add(contour);

    }

    /**
     * Metoda pro zobrazeni obrysu prvku
     *
     * @param value Color obrysu
     */
    public void setContourViseble(Color value) {
        contour.setStroke(value);
    }

    /**
     * Metoda pro vytvoreni polygonu kolem spojnice prvku
     *
     * @param startX souradnice x
     * @param startY souradnice y
     * @param width  sirka  prvku platna
     * @param height vyska prvku platna
     * @return Body polygonu
     */
    private Double[] createPolygon(double startX, double startY, double width, double height) {

        Double[] points = new Double[8];
        points[0] = startX;
        points[1] = startY;
        points[2] = startX + width;
        points[3] = startY;
        points[4] = startX + width;
        points[5] = startY + height;
        points[6] = startX;
        points[7] = startY + height;
        return points;

    }


    /**
     * Pomocná metoda pro kontrolu polohy prvku
     *
     * @param point Point2D
     */
    public void setPosition(Point2D point) {
        this.setTranslateX(point.getX());
        this.setTranslateY(point.getY());
        this.setOrgTranslateX(point.getX());
        this.setOrgTranslateY(point.getY());
        canvasItemController.repaintArrows(segmentType, formIdentificator, getTranslateX(), getTranslateY(), segmentInfo.getLength(), getHeight());
    }


    /**
     * Gerts and Setrs
     **/
    public void setNameText(String name) {

        segmentInfo.setNameText(name);
        contour.getPoints().clear();
        contour.getPoints().addAll(createPolygon(segmentInfo.getTranslateX() - Constans.CONTOURE_OFFSET, segmentInfo.getTranslateY() - Constans.CONTOURE_OFFSET,
                segmentInfo.getLength() + (2 * Constans.CONTOURE_OFFSET), segmentInfo.getTotalHeight() + (2 * Constans.CONTOURE_OFFSET)));

    }


    /*** Getrs and Setrs ***/

    public String getNameText() {

        return segmentInfo.getName().getText();
    }

    public String getID() {
        return segmentIdentificator;
    }

    public void setID(String iD) {
        segmentIdentificator = iD;
    }

    public double getLength() {
        return length + Constans.offset;
    }

    public InfoBoxSegment getSegmentInfo() {
        return segmentInfo;
    }

    public void setSegmentInfo(InfoBoxSegment segmentInfo) {
        this.segmentInfo = segmentInfo;
    }

    public double getOrgSceneX() {
        return orgSceneX;
    }

    public void setOrgSceneX(double orgSceneX) {
        this.orgSceneX = orgSceneX;
    }

    public double getOrgSceneY() {
        return orgSceneY;
    }

    public void setOrgSceneY(double orgSceneY) {
        this.orgSceneY = orgSceneY;
    }

    public double getOrgTranslateX() {
        return orgTranslateX;
    }

    public void setOrgTranslateX(double orgTranslateX) {
        this.orgTranslateX = orgTranslateX;
    }

    public double getOrgTranslateY() {
        return orgTranslateY;
    }

    public void setOrgTranslateY(double orgTranslateY) {
        this.orgTranslateY = orgTranslateY;
    }

    public String getSegmentIdentificator() {
        return segmentIdentificator;
    }

    public void setSegmentIdentificator(String segmentIdentificator) {
        this.segmentIdentificator = segmentIdentificator;
    }

    public int getFormIdentificator() {
        return formIdentificator;
    }

    public void setFormIdentificator(int formIdentificator) {
        this.formIdentificator = formIdentificator;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    @Override
    public String toString() {
        return segmentInfo.toString();
    }
}
