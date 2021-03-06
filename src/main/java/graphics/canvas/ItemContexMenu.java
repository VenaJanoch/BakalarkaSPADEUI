package graphics.canvas;

import controllers.formControllers.ManipulationController;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.CanvasItemController;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import services.Constans;

/**
 * Třída definující kontextové menu pro prvku plátna odděděná od třídy
 * ContexMenu
 *
 * @author Václav Janoch
 */
public class ItemContexMenu extends ContextMenu {

    /**
     * Globální proměnné třídy
     */
    private ManipulationController manipulation;
    private CanvasController canvasController;
    private CanvasItemController canvasItemController;
    private MenuItem copyItem;
    private MenuItem pasteItem;
    private MenuItem deleteItem;
    private MenuItem cutItem;

    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     *
     * @param manipulation         instace tridy ManipulationController
     * @param canvasController     instace tridy CanvasController
     * @param canvasItemController instace tridy CanvasItemController
     */
    public ItemContexMenu(ManipulationController manipulation, CanvasController canvasController, CanvasItemController canvasItemController) {
        super();
        this.manipulation = manipulation;
        this.canvasController = canvasController;
        this.canvasItemController = canvasItemController;
        createMenu();

    }

    /**
     * Vytvoří položky kontextového menu s navázáním na klávesové zkratky
     */
    private void createMenu() {

        copyItem = new MenuItem("Copy");
        copyItem.setAccelerator(Constans.controlC);
        pasteItem = new MenuItem("Paste");
        pasteItem.setAccelerator(Constans.controlV);
        deleteItem = new MenuItem("Delete");
        cutItem = new MenuItem("Cut");
        cutItem.setAccelerator(Constans.controlX);

        this.getItems().addAll(copyItem, new SeparatorMenuItem(), cutItem, new SeparatorMenuItem(), pasteItem,
                new SeparatorMenuItem(), deleteItem);
        setActions();
    }

    /**
     * Pomocná metoda pro nastavení reakce na stisk položky v menu
     */
    public void setActions() {

        copyItem.setOnAction(event -> manipulation.copyItem(canvasController));
        deleteItem.setOnAction(event -> manipulation.deleteItem(canvasItemController, canvasController));
        cutItem.setOnAction(event -> manipulation.cutItem(canvasController, canvasItemController));
        pasteItem.setOnAction(event -> canvasController.pasteItem());
    }

    /**
     * Getrs and Setrs
     **/

    public MenuItem getCopyItem() {
        return copyItem;
    }

    public void setCopyItem(MenuItem copyItem) {
        this.copyItem = copyItem;
    }

    public MenuItem getPasteItem() {
        return pasteItem;
    }

    public void setPasteItem(MenuItem pasteItem) {
        this.pasteItem = pasteItem;
    }

    public MenuItem getDeleteItem() {
        return deleteItem;
    }

    public void setDeleteItem(MenuItem deleteItem) {
        this.deleteItem = deleteItem;
    }

    public MenuItem getCutItem() {
        return cutItem;
    }

    public void setCutItem(MenuItem cutItem) {
        this.cutItem = cutItem;
    }

}
