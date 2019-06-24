package controllers.graphicsComponentsControllers;

import abstractControlPane.ControlPanel;
import abstractform.BasicForm;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Trida predstavujici controller pro rizeni postranich Drawer panelu
 *
 * @author Václav Janoch
 */
public class DrawerPanelController {

    /** Levi postrani panel**/
    private JFXDrawer leftDrawerPanel;
    /** Pravi postrani panel**/
    private JFXDrawer rightDrawerPanel;
    /** Pravi StackPane panel**/
    private StackPane righDrawerStackPane;
    /** Levi postrani panel**/
    private StackPane leftDrawerStackPane;
    /** Stack pro panely**/
    private JFXDrawersStack jfxDrawersStack;

    /**
     * Konstruktor tridy
     * Zinicialuzuje globalni promenne tridy
     * @param leftDrawerPane Instace leveho postraniho panelu
     * @param rightDrawerPane Instace praveho postraniho panelu
     * @param jfxDrawersStack Stack s panely
     */
    public DrawerPanelController(JFXDrawer leftDrawerPane, JFXDrawer rightDrawerPane, JFXDrawersStack jfxDrawersStack) {
        this.leftDrawerPanel = leftDrawerPane;
        this.rightDrawerPanel = rightDrawerPane;
        this.jfxDrawersStack = jfxDrawersStack;

        this.righDrawerStackPane = new StackPane();
        this.leftDrawerStackPane = new StackPane();
        rightDrawerPanel.setOverLayVisible(true);
        rightDrawerPanel.setResizableOnDrag(false);
        rightDrawerPanel.setResizeContent(true);
        rightDrawerPanel.setDirection(JFXDrawer.DrawerDirection.RIGHT);
        leftDrawerPanel.setOverLayVisible(true);
        leftDrawerPanel.setResizeContent(true);
        leftDrawerPanel.setResizableOnDrag(false);
        leftDrawerPanel.setDirection(JFXDrawer.DrawerDirection.LEFT);

    }

    /**
     * Metoda pro zobrazeni leveho postraniho panelu
     * @param form Formular, ktery bude v panelu zobrazen
     */
    public void showLeftPanel(BasicForm form) {
        jfxDrawersStack.toggle(leftDrawerPanel);
        leftDrawerPanel.setDefaultDrawerSize(form.getMinWidth());
        leftDrawerStackPane.getChildren().clear();
        leftDrawerStackPane.getChildren().add(form);
        showPanel(leftDrawerPanel, leftDrawerStackPane);
    }

    /**
     * Metoda pro zobrazeni praveho panelu
     * @param panel Controlni panel, ktery bude v panelu zobrazen
     */
    public void showRightPanel(ControlPanel panel) {
        jfxDrawersStack.toggle(rightDrawerPanel);
        rightDrawerPanel.setDefaultDrawerSize(panel.getWidth());
        righDrawerStackPane.getChildren().clear();
        righDrawerStackPane.getChildren().add(panel.getControlPane());
        showPanel(rightDrawerPanel, righDrawerStackPane);
    }

    /**
     * Metoda pro vyjeti konkretniho panelu
     * @param drawer Postrani panel pro zobrazeni
     * @param stackPane Stack Pane
     */
    public void showPanel(JFXDrawer drawer, StackPane stackPane) {

        drawer.setSidePane(stackPane);
        if (drawer.isOpened()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

}
