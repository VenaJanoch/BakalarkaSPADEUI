package abstractControlPane;

import controllers.FormController;
import graphics.controlPanelItems.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TextField;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;

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
        lineList.add(new ControlPanelLineObject("Name: ", ControlPanelLineType.Text, ParamType.Name));
    }
    protected abstract void createBaseControlPanel();

    public String getName() {
        return nameTF.getTextFromTextField();
    }

    public TextField getNameTF() {
        return nameTF.getItemTF();
    }
}