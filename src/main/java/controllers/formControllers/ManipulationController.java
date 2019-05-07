package controllers.formControllers;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import graphics.canvas.CanvasItem;
import graphics.canvas.DragAndDropCanvas;
import graphics.canvas.ElementsLink;
import graphics.canvas.NodeLink;
import interfaces.IDeleteFormController;
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

    private FormController formController;
    private FormFillController formFillController;
    private IDeleteFormController deleteFormController;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     * instance třídy control
     */
    public ManipulationController(IDeleteFormController deleteFormController) {

        this.deleteFormController = deleteFormController;
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
            isCut = true;
        }
    }

    /**
     * Smaže prvek ze seznamů a zneviditelní na plátně
     */
    public void deleteItem(CanvasItemController canvasItemController) {
        if (chooseCanvasItem != null) {

            int index = chooseCanvasItem.getFormIdentificator();
            boolean isDelete = deleteForm(index, chooseCanvasItem.getSegmentType());
            if (isDelete) {
                canvasItemController.deleteItem(chooseCanvasItem);
            }
        }
        chooseCanvasItem = null;

    }

    /**
     * Vloží nový pvek na plátno
     */
    public void pasteItem(CanvasController canvasController, CanvasItemController canvasItemController, double x, double y) {
        SegmentType segmentType = canvasItemController.getSegmentType(chooseCanvasItem);
        CanvasType canvasType = canvasController.getCanvasType();

        if (chooseCanvasItem != null) {

            if (isCut) {
                createCopyForm(chooseCanvasItem.getFormIdentificator(), segmentType, canvasController, x, y);
                deleteItem(canvasItemController);
            } else {
                createCopyForm(chooseCanvasItem.getFormIdentificator(), segmentType, canvasController, x, y);
            }
        } else {
            Alerts.badCopyItem(segmentType, canvasType);
        }

    }


    /**
     * Rozhodne, který segment nebo element se vytvoří
     * <p>
     * instance seznamu formulářů
     *
     * @return pole identifikátorů prvku
     */
    public void createCopyForm(int oldFormIndex, SegmentType segmentType, CanvasController canvasController, double x, double y) {

        switch (segmentType) {
            case Configuration:
                formFillController.fillConfigurationForm(oldFormIndex, x, y);
                break;
            case Committed_Configuration:
                formFillController.fillCommitedConfigurationForm(oldFormIndex, x, y);
                break;
            case Commit:
                formFillController.fillCommitForm(oldFormIndex, x, y);
                break;
            case Person:
                formFillController.fillPersonForm(oldFormIndex, x, y);
                break;
            case Artifact:
                formFillController.fillArtifactForm(oldFormIndex, x, y);
                break;
            default:
                break;
        }
    }


    public boolean deleteForm(int formIndex, SegmentType segmentType) {
        boolean isDelete = false;
        switch (segmentType) {
            case Artifact:
                isDelete = deleteFormController.deleteArtifact(formIndex);
                break;
            case Person:
                isDelete = deleteFormController.deleteRoleWithDialog(formIndex);
                break;
            case Configuration:
                isDelete = deleteFormController.deleteConfigurationWithDialog(formIndex);
                break;
            case Committed_Configuration:
                isDelete = deleteFormController.deleteCommitedConfigurationWithDialog(formIndex);
                break;
            case Commit:
                isDelete = deleteFormController.deleteCommitWithDialog(formIndex);
                break;
            default:
                return isDelete;
        }
        return isDelete;
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
