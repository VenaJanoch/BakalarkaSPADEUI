package abstractControlPane;

import Controllers.FormController;
import Controllers.FormDataController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tables.BasicTable;

public abstract class DescriptionControlPanel extends NameControlPanel {

    protected Label descriptionLB;
    protected TextField descriptionTF;



    public DescriptionControlPanel(String buttonText, FormDataController formDataController, FormController formController) {
        super(buttonText, formDataController, formController);
        createBaseControlPanel();
    }

    public DescriptionControlPanel(String buttonText, FormDataController formDataController) {
        super(buttonText, formDataController);
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