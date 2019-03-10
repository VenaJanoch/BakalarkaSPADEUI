package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class NameControlPanel extends ControlPanel {

    protected Label nameLB;
    protected TextField nameTF;

    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBasicPanel();
    }

    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBasicPanel();
    }

    protected void createBasicPanel() {
        nameLB = new Label("Name");
        nameTF = new TextField();
        nameTF.setId("formName");

        controlPane.add(nameLB, 1, 0);
        controlPane.add(nameTF, 2, 0);

    }
    protected abstract void createBaseControlPanel();

    public String getName() {
        return nameTF.getText();
    }

    public Label getNameLB() {
        return nameLB;
    }

    public TextField getNameTF() {
        return nameTF;
    }
}