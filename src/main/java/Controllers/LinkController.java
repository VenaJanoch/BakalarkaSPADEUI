package Controllers;

import javafx.geometry.Point2D;
import services.Constans;

public class LinkController {

    private int id;

    public LinkController(int id){
        this.id = id;
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
}
