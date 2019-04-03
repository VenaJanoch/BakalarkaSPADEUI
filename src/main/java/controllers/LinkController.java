package controllers;

import graphics.NodeLink;
import javafx.geometry.Point2D;
import services.Constans;
import services.SegmentLists;

public class LinkController {

    private int linkId;
    private int startItemId;
    private int endItemId;

    public LinkController(int linkId)
    {
        this.linkId = linkId;
    }

    /**
     * Vypočte souřadnice pro zvýraznění spojnice při kliku na ni
     *
     * @param startPoint
     *            počáteční bod
     * @param endPoint
     *            koncový bod
     * @return body pro vykreslení zvýraznění
     */
    public Double[] countBackgroundPlygon(Point2D startPoint, Point2D endPoint) {

        Double[] points = new Double[8];
        points[0] = startPoint.getX();
        points[1] = startPoint.getY() + Constans.polygonHeight;
        points[2] = startPoint.getX();
        points[3] = startPoint.getY() - Constans.polygonHeight;
        points[4] = endPoint.getX();
        points[5] = endPoint.getY() - Constans.polygonHeight;
        points[6] = endPoint.getX();
        points[7] = endPoint.getY() + Constans.polygonHeight;
        return points;
    }


    public void repaintArrowStartPoint(NodeLink link, double newWidth, double newHeight) {

        if (link != null) {
            link.setStartPoint(new Point2D(newWidth, newHeight));
        }
    }
    public void repaintArrowEndPoint(NodeLink link, double newWidth, double newHeight) {
        if (link != null) {
            link.setEndPoint(new Point2D(newWidth, newHeight));
        }
    }



    /**
     * Vypočte střed spojovací čáry pro vložení boxu s výběrem relace
     *
     * @param startPoint
     *            startovní bod spojnice
     * @param endPoint
     *            koncový bod spojnice
     * @return Point2D bod pro box
     */
    public Point2D calculateCenter(Point2D startPoint, Point2D endPoint) {

        Point2D point = null;
        double x = Math.abs((startPoint.getX() - endPoint.getX()) / 2);
        double y = Math.abs((startPoint.getY() - endPoint.getY()) / 2) + Constans.relationCBOffset;

        if (startPoint.getY() <= endPoint.getY()) {

            if (startPoint.getX() <= endPoint.getX()) {
                point = new Point2D(startPoint.getX() + x, startPoint.getY() + y);
            } else {
                point = new Point2D(startPoint.getX() - x, startPoint.getY() + y);
            }

        } else {

            if (startPoint.getX() <= endPoint.getX()) {

                point = new Point2D(startPoint.getX() + x, startPoint.getY() - y);
            } else {
                point = new Point2D(startPoint.getX() - x, startPoint.getY() - y);
            }
        }

        return point;
    }

    /**
     * Vypočte polohu šipky ukazující směr propojení Work Unit
     *
     * @param endPoint
     * @return Double body s polohou
     */
    public Double[] calculateArrowPosition(Point2D endPoint) {

        double x = endPoint.getX() - Constans.ArrowRadius;
        double yUP = endPoint.getY() + Constans.ArrowRadius;
        double yDW = endPoint.getY() - Constans.ArrowRadius;

        Double[] position = new Double[] { endPoint.getX(), endPoint.getY(), x, yUP, x, yDW };
        return position;

    }



    public int getLinkId() {
        return linkId;
    }

    public int getStartItemId() {
        return startItemId;
    }

    public void setStartItemId(int startItemId) {
        this.startItemId = startItemId;
    }

    public int getEndItemId() {
        return endItemId;
    }

    public void setEndItemId(int endItemId) {
        this.endItemId = endItemId;
    }

    public void setIds(int startId, int endId) {
        startItemId = startId;
        endItemId = endId;
    }
}
