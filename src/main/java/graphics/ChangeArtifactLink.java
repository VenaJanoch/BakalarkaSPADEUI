package graphics;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import services.Control;
import Controllers.LinkControl;
import services.SegmentType;

public class ChangeArtifactLink extends NodeLink {
    /**
     * Konstruktor třídy Zinicizalizuje globální proměnné třídy
     *
     * @param ID          Identifikace spojnice
     * @param control     instance třídy Control
     * @param type        SegmentType
     * @param linkControl LinkControl
     */
    public ChangeArtifactLink(int ID, Control control, SegmentType type, LinkControl linkControl) {
        super(ID, linkControl);
    }


    /**
     * Metoda pro smazání spojice mezi prvky a zavolání metody pro smazání
     * spojení z datových struktur
     */
    public void deleteArrow() {
        this.setVisible(false);
        backgroundPolygon.setVisible(false);
        linkControl.deleteArrow(id, startIDs[1], endIDs[1]);

    }

    /**
     * Kontrolní metoda pro reakci na dvojklik do okolí spojnice
     *
     * @param t
     *            MouseEvent
     */
    protected void pressedDeleteArrow(MouseEvent t) {

        control.getManipulation().setLink(this);
        control.getManipulation().setClicItem(null);
        if (t.getButton().equals(MouseButton.PRIMARY)) {
            if (t.getClickCount() == 2) {
                deleteArrow();
            }

        }

    }
}
