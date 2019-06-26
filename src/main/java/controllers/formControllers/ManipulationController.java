package controllers.formControllers;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import controllers.graphicsComponentsControllers.SelectionController;
import graphics.canvas.CanvasItem;
import graphics.canvas.DragAndDropCanvas;
import graphics.canvas.NodeLink;
import interfaces.IDeleteFormController;
import services.Alerts;
import services.CanvasType;
import services.SegmentType;

/**
 * Trida predstavujici controller manipulaci s prvky na platne
 * Predstavujici modelovaci platno
 *
 * @author Václav Janoch
 */
public class ManipulationController {

    /**
     * Globální proměnné třídy
     */

    private DragAndDropCanvas chooseCanvas;

    /**
     * Promenna pro urceni vybrane funkce pro vyjmuti prvku
     **/
    private boolean isCut;
    /**
     * Promenna pro urceni vybrane funkce pro kopirovani prvku
     **/
    private boolean isCopy;

    private boolean isChoosedItem = false;
    private boolean isMultiSelect = false;

    private NodeLink link;

    /**
     * Promenne pro kontrolery potrebne pro funkcnosti
     */
    private FormFillController formFillController;
    private IDeleteFormController deleteFormController;
    private SelectionController selectionController;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     * instance třídy control
     *
     * @param deleteFormController instace tridy kontroleru pro odstraneni prvku
     * @param selectionController  instace tridy kontroleru pro vyber vice prvku
     */
    public ManipulationController(IDeleteFormController deleteFormController, SelectionController selectionController) {

        this.deleteFormController = deleteFormController;
        this.selectionController = selectionController;
        isCut = false;
    }

    /**
     * Uloží data o kopírovaném prvku
     */
    public void copyItem(CanvasController canvasController) {

        this.isCopy = true;

    }


    /**
     * Uloží data o vyjmutém prvku a smaže ho z plátna
     */
    public void cutItem(CanvasController canvasController, CanvasItemController canvasItemController) {

        if (selectionController.getSelection().size() != 0) {
            copyItem(canvasController);
            isCut = true;
        }
    }

    /**
     * Smaže zvolene prvky ze seznamů  na plátně
     *
     * @param canvasItemController instace tridy canvasItemController
     * @param canvasController     instace tridy canvasController
     */
    public void deleteItem(CanvasItemController canvasItemController, CanvasController canvasController) {
        for (CanvasItem chooseCanvasItem : selectionController.getSelection()) {
            deleteItem(canvasItemController, chooseCanvasItem, canvasController);
        }
    }

    /**
     * Pretizena metoda pro odstraneni prvku na platne
     *
     * @param canvasItemController instace tridy canvasItemController
     * @param chooseCanvasItem     instace vybraneho prvku pro odstraneni
     * @param canvasController     instace tridy canvasController
     */
    public void deleteItem(CanvasItemController canvasItemController, CanvasItem chooseCanvasItem, CanvasController canvasController) {
        int index = chooseCanvasItem.getFormIdentificator();
        boolean isDelete = deleteForm(index, chooseCanvasItem.getSegmentType());
        if (isDelete) {
            canvasItemController.deleteItem(chooseCanvasItem, isDelete, canvasController);
        }
    }

    /**
     * Metoda pro vlozeni noveho prveku na platno
     *
     * @param canvasController     instace tridy canvasController
     * @param canvasItemController instace tridy canvasItemController
     * @param x                    souradnice pro vlozeni
     * @param y                    souradnice pro vlozeni
     * @param chooseCanvasItem     instace prvku na platne pro napleni daty
     */
    public void pasteItem(CanvasController canvasController, CanvasItemController canvasItemController, double x, double y, CanvasItem chooseCanvasItem) {

        SegmentType segmentType = canvasItemController.getSegmentType(chooseCanvasItem);
        CanvasType canvasType = canvasController.getCanvasType();

        if (chooseCanvasItem != null) {

            if (isCut) {
                createCopyForm(chooseCanvasItem.getFormIdentificator(), segmentType, canvasController, x, y);
                deleteItem(canvasItemController, chooseCanvasItem, canvasController);
            } else {
                createCopyForm(chooseCanvasItem.getFormIdentificator(), segmentType, canvasController, x, y);
            }
        } else {
            Alerts.badCopyItem(segmentType, canvasType);
        }

    }

    /**
     * Pretizena metoda pro vlozeni novych prvku na platno
     *
     * @param canvasController     instace tridy canvasController
     * @param canvasItemController instace tridy canvasItemController
     * @param x                    souradnice pro vlozeni
     * @param y                    souradnice pro vlozeni
     */
    public void pasteItem(CanvasController canvasController, CanvasItemController canvasItemController, double x, double y) {

        for (CanvasItem canvasItem : selectionController.getSelection()) {
            pasteItem(canvasController, canvasItemController, x, y, canvasItem);
        }

    }

    /**
     * Rozhodne, který segment nebo element se vytvoří
     * <p>
     * instance seznamu formulářů
     *
     * @param oldFormIndex identifikator vzoru
     * @param segmentType typ prvku
     * @param canvasController instance CanvasController
     * @param x x souradnice
     * @param y y souranice
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

    /**
     * Metoda pro rozhodnuti, ktery z datoveho modelu bude smazany
     *
     * @param formIndex   formIndex
     * @param segmentType SegmentType
     * @return rozhodnuti zda se ma prvek smazat
     */
    public boolean deleteForm(int formIndex, SegmentType segmentType) {
        boolean isDelete = false;
        switch (segmentType) {
            case Artifact:
                isDelete = deleteFormController.deleteArtifact(formIndex);
                break;
            case Person:
                isDelete = deleteFormController.deletePersonWithDialog(formIndex);
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


    public NodeLink getLink() {
        return link;
    }

    public void setLink(NodeLink link) {
        if (this.link != null) {
            this.link.coverBackgroundPolygon();
        }
        this.link = link;
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

    public boolean isChoosedItem() {
        return isChoosedItem;
    }

    public void setChoosedItem(boolean choosedItem) {
        isChoosedItem = choosedItem;
    }

    public boolean isMultiSelect() {
        return isMultiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        isMultiSelect = multiSelect;
    }
}
