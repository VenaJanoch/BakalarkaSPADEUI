package abstractControlPane;

import Controllers.FormController;
import Controllers.FormDataController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tables.BasicTable;

public abstract class NameControlPanel extends ControlPanel {

    protected Label nameLB;
    protected TextField nameTF;

    public NameControlPanel(String buttonText, FormDataController formDataController, FormController formController) {
        super(buttonText, formDataController, formController);
        createBasicPanel();

    }

    public NameControlPanel(String buttonText, FormDataController formDataController) {
        super(buttonText, formDataController);
        createBasicPanel();

    }

    protected void createBasicPanel() {
        nameLB = new Label("Name");
        nameTF = new TextField();
        controlPane.add(nameLB, 0, 0);
        controlPane.add(nameTF, 1, 0);
        nameLB = new Label("Name: ");
        nameTF.setId("formName");

    }
    abstract protected void createBaseControlPanel();

    public String getName() {
        return nameTF.getText();
    }
}