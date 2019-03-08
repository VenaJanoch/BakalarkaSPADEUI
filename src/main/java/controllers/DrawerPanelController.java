package controllers;

import abstractControlPane.ControlPanel;
import abstractform.BasicForm;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import javafx.scene.layout.StackPane;

public class DrawerPanelController {

    private JFXDrawer leftDrawerPanel;
    private JFXDrawer rightDrawerPanel;
    private StackPane righDrawerStackPane;
    private StackPane leftDrawerStackPane;
    private JFXDrawersStack jfxDrawersStack;

    public DrawerPanelController(JFXDrawer leftDrawerPane, JFXDrawer rightDrawerPane, JFXDrawersStack jfxDrawersStack){
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

    public void hideLeftPanel(){
        if (leftDrawerPanel.isOpened()) {
            leftDrawerPanel.close();
        }
    }
    public void showLeftPanel(BasicForm form){
        jfxDrawersStack.toggle(leftDrawerPanel);
        leftDrawerPanel.setDefaultDrawerSize(form.getMinWidth());
        leftDrawerStackPane.getChildren().clear();
        leftDrawerStackPane.getChildren().add(form);
        showPanel(leftDrawerPanel, leftDrawerStackPane);
        leftDrawerPanel.setOnDrawerClosed(event -> System.out.println("Ahoj zaviram se") );
    }

    public void hideRightPanel(){
        if (rightDrawerPanel.isOpened()) {
            rightDrawerPanel.close();
        }
    }
    public void showRightPanel(ControlPanel panel){
        jfxDrawersStack.toggle(rightDrawerPanel);
        rightDrawerPanel.setDefaultDrawerSize(panel.getWidth());
        righDrawerStackPane.getChildren().clear();
        righDrawerStackPane.getChildren().add(panel.getControlPane());
        showPanel(rightDrawerPanel, righDrawerStackPane);
    }
    public void showPanel(JFXDrawer drawer, StackPane stackPane){

        drawer.setSidePane(stackPane);
        if (drawer.isOpened()) {
            drawer.close();
        }else{
            drawer.open();
        }
    }
    
}
