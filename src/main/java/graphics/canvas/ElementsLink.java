package graphics.canvas;

import controllers.LinkControl;
import controllers.formControllers.ManipulationController;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.LinkController;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ElementsLink extends NodeLink {
    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param ID                     identifikator spojnice
     * @param linkControl            controller pro rizeni cinosti spojnice
     * @param canvasController       instace CanvasController
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
     *
     * @param event instace Mouse event
     */
    public void polygonClickedReaction(MouseEvent event) {
        manipulationController.setLink(this);
        backgroundPolygon.setVisible(true);
        backgroundPolygon.setStroke(Color.BLACK);
    }


    public LinkController getLinkController() {
        return linkController;
    }


}
