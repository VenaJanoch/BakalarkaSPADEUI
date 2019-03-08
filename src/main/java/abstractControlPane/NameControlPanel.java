package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        controlPane.add(nameLB, 0, 0);
        controlPane.add(nameTF, 1, 0);
        nameLB = new Label("Name: ");
        nameTF.setId("formName");

    }
    protected abstract void createBaseControlPanel();

    public String getName() {
        return nameTF.getText();
    }
}