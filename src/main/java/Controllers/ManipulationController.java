package Controllers;

import abstractform.BasicForm;
import graphics.CanvasItem;
import graphics.DragAndDropCanvas;
import graphics.NodeLink;
import javafx.scene.paint.Color;
import services.*;

public class ManipulationController {

    /**
     * Globální proměnné třídy
     */

    private DeleteControl deleteControl;

    private CanvasItem chooseCanvasItem;
    private DragAndDropCanvas chooseCanvas;

    private boolean isCut;
    private boolean isCopy;

    private NodeLink link;
    ;

    private FormController formController;
    private FormFillController formFillController;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     * instance třídy control
     */
    public ManipulationController(FormController formController) {

        this.formController = formController;
        isCut = false;
    }

    /**
     * Uloží data o kopírovaném prvku
     */
    public void copyItem(CanvasController canvasController) {

        this.isCopy = true;

    }

    public void controlCopyItem() {

        if (!isCopy) {
            chooseCanvasItem = null;
        }
    }

    /**
     * Uloží data o vyjmutém prvku a smaže ho z plátna
     */
    public void cutItem(CanvasController canvasController, CanvasItemController canvasItemController) {

        if (chooseCanvasItem != null) {
            copyItem(canvasController);
            deleteItem(canvasItemController);
        }
    }

    /**
     * Smaže prvek ze seznamů a zneviditelní na plátně
     */
    public void deleteItem(CanvasItemController canvasItemController) {
        if (chooseCanvasItem != null) {
            canvasItemController.deleteItem(chooseCanvasItem);
            int index = chooseCanvasItem.getFormIdentificator();
            //formController.deleteForm(index); Navic asi
        }
        chooseCanvasItem = null;

    }

    /**
     * Vloží nový pvek na plátno
     */
    public void pasteItem(CanvasController canvasController, CanvasItemController canvasItemController) {
        SegmentType segmentType = canvasItemController.getSegmentType(chooseCanvasItem);
        CanvasType canvasType = canvasController.getCanvasType();

        if (chooseCanvasItem != null) {

            if (FormControl.copyControl(segmentType, canvasType)) {

                createCopyForm(chooseCanvasItem.getFormIdentificator(), segmentType, canvasController);
            }
        } else {
            Alerts.badCopyItem(segmentType, canvasType);
        }

    }


    /**
     * Rozhodne, který segment nebo element se vytvoří
     *
     *            instance seznamu formulářů
     * @return pole identifikátorů prvku
     */
    public void createCopyForm(int oldFormIndex, SegmentType segmentType, CanvasController canvasController) {

        switch (segmentType) {
            case Phase:
                formFillController.fillPhaseForm(oldFormIndex);
            case Iteration:
                formFillController.fillIterationForm(oldFormIndex);
            case Activity:
                formFillController.fillActivityForm(oldFormIndex);
            case WorkUnit:
                formFillController.fillWorkUnitForm(oldFormIndex, canvasController.getCanvasType(), canvasController);
            case Change:

            case Artifact:

            default:
    }
    }



    /**
     * Getrs and Setrs
     */

    public CanvasItem getChooseCanvasItem() {
        return chooseCanvasItem;
    }

    public void setChooseCanvasItem(CanvasItem chooseCanvasItem) {
        this.chooseCanvasItem = chooseCanvasItem;
    }

    public NodeLink getLink() {
        return link;
    }

    public void setLink(NodeLink link) {
        if (this.link != null) {
            this.link.coverBackgroundPolygon();
        }
        this.link = link;
    }

    public DragAndDropCanvas getChooseCanvas() {
        return chooseCanvas;
    }

    public void setChooseCanvas(DragAndDropCanvas chooseCanvas) {
        this.chooseCanvas = chooseCanvas;
    }

    public FormFillController getFormFillController() {
        return formFillController;
    }

    public void setFormFillController(FormFillController formFillController) {
        this.formFillController = formFillController;
    }
}
