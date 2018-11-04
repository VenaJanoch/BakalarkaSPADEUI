package Controllers;

import abstractform.BasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanvasItemController {

    private ManipulationController manipulation;


    private Map<Integer, List<Integer>> startLinkIdMap;
    private Map<Integer, List<Integer>> endLinkIdMap;
    int[] IDs;
    private int idForm;
    private String ID;

    private AnchorPane canvas = null;
    private DragAndDropCanvas dgCanvas;

    private SegmentType type;
    private BasicForm form;
    private LinkControl linkControl;
    private FormController formController;


    public CanvasItemController(LinkControl linkControl, FormController formController, ManipulationController manipulationController) {

        this.manipulation = manipulationController;
        this.linkControl = linkControl;
        this.startLinkIdMap = new HashMap<>();
        this.endLinkIdMap = new HashMap<>();
        this.formController = formController;
    }

    public CanvasItem createCanvasItem(SegmentType type, String segmentIdentificator, int formIndex, String name, double x, double y, CanvasController canvasController) {

        startLinkIdMap.put(formIndex, new ArrayList<>());
        endLinkIdMap.put(formIndex, new ArrayList<>());

        CanvasItem item = new CanvasItem(type, segmentIdentificator, formIndex, name, 0, x, y, canvasController, this);
        formController.addCanvasItemToList(formIndex, item);
        return item;
    }

    /**
     * Pomocná metoda pro výpočet polohy prvku při drag and drop
     *
     * @param t
     */
    public void setDragFromDragPoint(MouseEvent t, CanvasItem canvasItem, CanvasController canvasController) {
        if (!canvasController.isArrow()) {
            double offsetX = t.getSceneX() - canvasItem.getOrgSceneX();
            double offsetY = t.getSceneY() - canvasItem.getOrgSceneY();
            double newTranslateX = canvasItem.getOrgTranslateX() + offsetX;
            double newTranslateY = canvasItem.getOrgSceneY() + offsetY;

            ((AnchorPane) (t.getSource())).setTranslateX(newTranslateX);
            ((AnchorPane) (t.getSource())).setTranslateY(newTranslateY);

        }

    }

    /**
     * Metoda určující reakce na kliknutí na prvek, Při jednoduchém kliku načte
     * aktuální polohu prvku pro výpočet posunu prvku, nebo pokud se jedná o mod
     * kreslení vybere prvek pro spojení. Dvojklikem vyvolá příslušny formulář
     *
     * @param t
     */
    public void setClicFromDragPoint(MouseEvent t, CanvasItem item, CanvasController canvasController) {

        manipulation.setChooseCanvasItem(item);

        if (t.getButton().equals(MouseButton.PRIMARY)) {

            if (canvasController.isArrow()) {

                linkControl.ArrowManipulation(false, canvasController.isStartArrow(), canvasController, item.getFormIdentificator(), type, item.getTranslateX(),
                        item.getOrgTranslateY(), item.getWidth(), item.getHeight());
            } else {

                if (t.getClickCount() == 2) {

                    formController.showForm(item.getFormIdentificator());

                } else if (!canvasController.isArrow()) {

                    item.setOrgSceneX(t.getSceneX());
                    item.setOrgSceneY(t.getSceneY());
                    item.setOrgTranslateX(((AnchorPane) (t.getSource())).getTranslateX());
                    item.setOrgTranslateY(((AnchorPane) (t.getSource())).getTranslateY());
                }
            }
        } else if (t.getButton().equals(MouseButton.SECONDARY)) {
            canvasController.getItemContexMenu(item, t);

        }

    }

    /** public void connectItemWithForm(int isCreated){

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


     }**/


    /**
     * Slouží ke kontorle pozice prvku na plátně, při přejetí hranic plátna je
     * prvek vrácen na okraj plátna
     *
     * @param x souřadnice prvku
     * @param y souřadnice prvku
     * @return Point2D zkontrolovaná poloha
     */
    public Point2D canvasItemPositionControl(double x, double y) {

        Point2D point = new Point2D(x, y);

        if (y <= 0) {
            point = new Point2D(x, 0);
        }

        if (x <= 0) {

            point = new Point2D(0, y);
        }

        if (x >= Constans.canvasMaxWidth) {
            point = new Point2D(Constans.canvasMaxWidth - Constans.offset, y);
        }

        if (y >= Constans.canvasMaxHeight) {
            point = new Point2D(x, Constans.canvasMaxHeight - Constans.offset);
        }

        return point;

    }


    /**
     * Metoda pro smazání spojnic mezi prvky a smazání z datových struktur
     */
    public void deleteLinks(int itemIdentificator) {
        linkControl.deleteLinks(startLinkIdMap.get(itemIdentificator), endLinkIdMap.get(itemIdentificator));
    }

    /**
     * Překreslí spojnici mezi prvky po přesunu počátečního prvku
     */
    public void repaintArrows(SegmentType segmentType, int itemIdentificator, double translateX, double translateY, double width, double height) {
        linkControl.repaintArrow(segmentType, startLinkIdMap.get(itemIdentificator), endLinkIdMap.get(itemIdentificator), translateX, translateY, width, height);

    }

    public void deleteItem(CanvasItem chooseCanvasItem) {

        chooseCanvasItem.setVisible(false);
        int id = chooseCanvasItem.getFormIdentificator();
        linkControl.deleteLinks(startLinkIdMap.get(id), endLinkIdMap.get(id));
        formController.removeCanvasItemFromList(id);

    }

    public SegmentType getSegmentType(CanvasItem chooseCanvasItem) {

        return chooseCanvasItem.getSegmentType();

    }
}
