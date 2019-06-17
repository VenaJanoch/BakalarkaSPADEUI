package controllers.graphicsComponentsControllers;

import controllers.formControllers.FormController;
import controllers.LinkControl;
import controllers.formControllers.ManipulationController;
import graphics.canvas.CanvasItem;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.*;


public class CanvasItemController {

    private ManipulationController manipulation;
    private SegmentType type;
    private LinkControl linkControl;
    private FormController formController;
    private SelectionController selectionController;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param linkControl instace tridy LinkContro l
     * @param formController instace tridy FormController
     * @param manipulationController instace tridy ManipulationController
     * @param selectionController instace tridy SelectionController
     */
    public CanvasItemController(LinkControl linkControl, FormController formController, ManipulationController manipulationController, SelectionController selectionController) {

        this.manipulation = manipulationController;
        this.linkControl = linkControl;
        this.formController = formController;
        this.selectionController = selectionController;
        formController.setCanvasItemController(this);

    }

    /**
     * Metoda pro vytvoreni nove instace tridy CanvasItem
     * @param type instace tridy SegmentType
     * @param segmentIdentificator identificator prvku
     * @param formIndex  identificator elemetnu
     * @param name
     * @param instanceCount
     * @param countIndicator
     * @param x
     * @param y
     * @param canvasController
     * @return CanvasItem
     */
    public CanvasItem createCanvasItem(SegmentType type, String segmentIdentificator, int formIndex, String name, int instanceCount, int countIndicator, double x, double y,
                                       CanvasController canvasController) {

        linkControl.createLinkInstanceInMap(formIndex);
        this.type = type;
        CanvasItem item = new CanvasItem(type, segmentIdentificator, formIndex, name, instanceCount, 0, x, y,
                canvasController, this);
        formController.addCanvasItemToList(formIndex, item);
        setInstanceCount(item, instanceCount, countIndicator);
        return item;
    }

    /**
     * Pomocná metoda pro výpočet polohy prvku při drag and drop
     *
     * @param t
     */
    public void setDragFromDragPoint(MouseEvent t, CanvasItem canvasItemORG, CanvasController canvasController) {

        double offsetX = t.getSceneX() - canvasItemORG.getOrgSceneX();
        double offsetY = t.getSceneY() - canvasItemORG.getOrgSceneY();

        for (CanvasItem canvasItem : selectionController.getSelection()){
            if (!canvasController.isArrow()) {

                double newTranslateX = canvasItem.getOrgTranslateX() + offsetX;
                double newTranslateY = canvasItem.getOrgTranslateY() + offsetY;

                canvasItem.setTranslateX(newTranslateX);
                canvasItem.setTranslateY(newTranslateY);
            }
        }
    }

    /**
     * Metoda určující reakce na kliknutí na prvek, Při jednoduchém kliku načte
     * aktuální polohu prvku pro výpočet posunu prvku, nebo pokud se jedná o mod
     * kreslení vybere prvek pro spojení. Dvojklikem vyvolá příslušny formulář
     *
     * @param t
     */
    public void setClicFromDragPoint(MouseEvent t, CanvasItem item, CanvasController canvasController, SegmentType segmentType) {

        manipulation.setChoosedItem(true);

        if (t.getButton().equals(MouseButton.PRIMARY)) {

            if (canvasController.isArrow()) {

                linkControl.ArrowManipulation(canvasController.isStartArrow(), canvasController, item.getFormIdentificator(), segmentType, item.getTranslateX(),
                        item.getTranslateY(), item.getLength(), item.getHeight(), false, -1);
            } else {

                if (t.getClickCount() == 2) {

                    formController.showEditControlPanel(item.getFormIdentificator());

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

    /**
     * Slouží ke kontorle pozice prvku na plátně, při přejetí hranic plátna je
     * prvek vrácen na okraj plátna
     *
     * @param canvasItem prvek platna urceny pro kontrolu pozice
     * @return Point2D zkontrolovaná poloha
     */
    public Point2D canvasItemPositionControl(CanvasItem canvasItem) {

        double x = canvasItem.getTranslateX();
        double y = canvasItem.getTranslateY();
        SegmentType segmentType = canvasItem.getSegmentType();
        int formIndex = canvasItem.getFormIdentificator();
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
        formController.setCoordinatesToCanvasItem(segmentType, point.getX(), point.getY(), formIndex);
        manipulation.setChoosedItem(false);
        return point;

    }


    /**
     * Metoda pro smazání spojnic mezi prvky a smazání z datových struktur
     */
    public void deleteLinks(int itemIdentificator) {
        linkControl.deleteLinks(itemIdentificator, false);
    }

    /**
     * Překreslí spojnici mezi prvky po přesunu počátečního prvku
     */
    public void repaintArrows(SegmentType segmentType, int itemIdentificator, double translateX, double translateY, double width, double height) {
        linkControl.repaintArrow(segmentType, itemIdentificator, translateX, translateY, width, height);
    }

    public void deleteItem(CanvasItem chooseCanvasItem, boolean isModelDelete, CanvasController canvasController) {

        chooseCanvasItem.setVisible(false);
        int id = chooseCanvasItem.getFormIdentificator();
        linkControl.deleteLinks(id, isModelDelete);
        formController.removeCanvasItemFromList(id);
        canvasController.removeCanvasItem(chooseCanvasItem);
        selectionController.clear();


    }

    public SegmentType getSegmentType(CanvasItem chooseCanvasItem) {

        return chooseCanvasItem.getSegmentType();

    }

    /**
     * Metoda pro nastaveni poctu instaci do prvku platna
     * @param canvasItem konkretni CanvasItem
     * @param instanceCount pocet instaci
     * @param indicator ukazatel nerovnosti
     */
    public void setInstanceCount(CanvasItem canvasItem, int instanceCount, int indicator) {
       String indicatorMark = "";
        if (indicator != 0){
           indicatorMark = Constans.NUMBER_INDICATORS[indicator];
       }
        canvasItem.getSegmentInfo().setInstanceCount(indicatorMark + Integer.toString(instanceCount));


        if (instanceCount > 1) {
            canvasItem.getSegmentInfo().getInstanceCount().setVisible(true);
        } else {
            canvasItem.getSegmentInfo().getInstanceCount().setVisible(false);
        }
    }

    /**
     * Metoda pro realci na ukonceni Drag and Drop
     */
    public void releasedItem() {
        for (CanvasItem canvasItem : selectionController.getSelection()){
            canvasItem.setPosition(canvasItemPositionControl(canvasItem));
        }
    }
}
