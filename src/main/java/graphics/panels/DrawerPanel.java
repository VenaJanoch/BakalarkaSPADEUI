package graphics.panels;

import abstractform.BasicForm;
import com.jfoenix.controls.JFXDrawer;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DrawerPanel extends AnchorPane {
    private JFXDrawer drawer;

    public DrawerPanel(){
        super();
        drawer = new JFXDrawer();
    }

    public JFXDrawer getDrawer() {
        return drawer;
    }

    public void showDrawerPanel(BasicForm form) {

    }
}
