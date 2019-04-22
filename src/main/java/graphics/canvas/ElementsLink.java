package graphics.canvas;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.LinkController;
import controllers.formControllers.ManipulationController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import controllers.LinkControl;

public class ElementsLink extends NodeLink {
    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param ID          Identifikace spojnice
     * @param linkControl LinkControl
     */


    public ElementsLink(int ID, LinkControl linkControl, CanvasController canvasController, ManipulationController manipulationController) {
        super(ID, linkControl,canvasController, manipulationController);
        backgroundPolygon.setOnMouseClicked(polygonMouseEvent);
    }

    /**
     * Metoda pro smazání spojice mezi prvky a zavolání metody pro smazání
     * spojení z datových struktur
     */
    public void deleteArrow() {
        this.setVisible(false);
        backgroundPolygon.setVisible(false);
        linkControl.deleteArrow(linkController.getLinkId(), linkController.getStartItemId(), linkController.getEndItemId(), linkType);

    }

    /**
     * Kontrolní metoda pro reakci na dvojklik do okolí spojnice
     *
     * @param t
     *            MouseEvent
     */
    protected void pressedDeleteArrow(MouseEvent t) {

//        control.getManipulation().setLink(this);
  //      control.getManipulation().setClicItem(null);
        if (t.getButton().equals(MouseButton.PRIMARY)) {
            if (t.getClickCount() == 2) {
                deleteArrow();
            }

        }

    }

    /**
     * MouseEvent handler pro reakci na kliknutí na šipku
     */
    EventHandler<MouseEvent> polygonMouseEvent = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            backgroundPolygon.setVisible(true);
            if (t.getClickCount() == 2){
                pressedDeleteArrow(t);
            }
        }
    };

    public LinkController getLinkController(){
        return linkController;
    }


}
