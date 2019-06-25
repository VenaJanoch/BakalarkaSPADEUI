package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.LinkController;
import controllers.formControllers.ManipulationController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import controllers.LinkControl;
import javafx.scene.paint.Color;

public class ElementsLink extends NodeLink {
    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param ID identifikator spojnice
     * @param linkControl controller pro rizeni cinosti spojnice
     * @param canvasController instace CanvasController
     * @param manipulationController instace ManipulationController
     */
    public ElementsLink(int ID, LinkControl linkControl, CanvasController canvasController, ManipulationController manipulationController) {
        super(ID, linkControl, canvasController, manipulationController);
        backgroundPolygon.setOnMouseClicked(event -> {
           polygonClickedReaction(event);
        });
    }

    /**
     * Pomocna metoda pro nastaveni pozadi spojnice a pripadne jeji smazani v pripade dvojkliku
     * @param event instace Mouse event
     */
    public void polygonClickedReaction(MouseEvent event){
        manipulationController.setLink(this);
        backgroundPolygon.setVisible(true);
        backgroundPolygon.setStroke(Color.BLACK);
        if (event.getClickCount() == 2) {
            pressedDeleteArrow(event);
        }
    }

    /*
     * Metoda pro smazání spojice mezi prvky a zavolání metody pro smazání
     * spojení z datových struktur
     * @param isModelDelete informace o tom zda je spojnice smazana v datovem modelu
     */
    public void deleteArrow(boolean isModelDelete) {
        this.setVisible(false);
        backgroundPolygon.setVisible(false);
        linkControl.deleteArrow(linkController.getLinkId(), linkController.getStartItemId(), linkController.getEndItemId(), linkType, isModelDelete);

    }

    /**
     * Kontrolní metoda pro reakci na dvojklik do okolí spojnice
     *
     * @param t MouseEvent
     */
    protected void pressedDeleteArrow(MouseEvent t) {

        if (t.getButton().equals(MouseButton.PRIMARY)) {
            if (t.getClickCount() == 2) {
                deleteArrow(false);
            }

        }

    }

    public LinkController getLinkController() {
        return linkController;
    }


}
