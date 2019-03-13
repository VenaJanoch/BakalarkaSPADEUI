package abstractControlPane;

import controllers.FormController;
import graphics.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public abstract class NameControlPanel extends ControlPanel {

    protected TextFieldItem nameTF;

    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBasicPanel();
    }

    public NameControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBasicPanel();
    }

    protected void createBasicPanel() {

        nameTF = new TextFieldItem("Name: ");
        nameTF.setId("formName");
        nameTF.getItemTF().setText(" ");
        nameTF.setShowItem(nameTF.getItemTF(), true);
        controlPane.add(nameTF.getItemNameLB(), 1, 0);
        controlPane.add(nameTF.getItemTF(), 2, 0);

    }
    protected abstract void createBaseControlPanel();

    public String getName() {
        return nameTF.getTextFromTextField();
    }

    public TextField getNameTF() {
        return nameTF.getItemTF();
    }
}