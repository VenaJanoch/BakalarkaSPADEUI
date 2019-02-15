package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tables.BasicTable;

public abstract class DescriptionControlPanel extends NameControlPanel {

    protected Label descriptionLB;
    protected TextField descriptionTF;

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        descriptionLB = new Label("Description: ");
        descriptionTF = new TextField();

        controlPane.add(descriptionLB, 2, 0);
        controlPane.add(descriptionTF, 3, 0);

    }


   protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

    public String getDescriptionText() {
        return descriptionTF.getText();
    }
}