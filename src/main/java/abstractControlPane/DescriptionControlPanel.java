package abstractControlPane;

import controllers.FormController;
import graphics.TextFieldItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;

import java.util.ArrayList;

public abstract class DescriptionControlPanel extends NameControlPanel {

    protected TextFieldItem descriptionTF;

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    public void createBaseControlPanel() {


        lineList.add(new ControlPanelLineObject("Description: ", ControlPanelLineType.Text, ParamType.Description));
    //    descriptionTF = new TextFieldItem("Description: ");
   //     controlPanelController.setTextItemToControlPanel(controlPane, descriptionTF, 0, 1);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);
    protected abstract void addItemsToControlPanel();

    public TextFieldItem getDescriptionTF() {
        return descriptionTF;
    }

}