package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tables.BasicTable;

public abstract class DescriptionControlPanel extends NameControlPanel {

    protected Label descriptionLB;
    protected TextField descriptionTF;
    private Button descriptionButoon;
    private boolean isShowDescription;

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DescriptionControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    public void createBaseControlPanel() {

        descriptionLB = new Label("Description: ");
        descriptionTF = new TextField();
        descriptionTF.setVisible(false);
        isShowDescription = false;
        descriptionButoon = new Button("+");
        descriptionButoon.setOnAction(event -> {
            if (!isShowDescription){
                descriptionTF.setVisible(true);
                isShowDescription = true;
                descriptionButoon.setText("-");
            }else{
                descriptionTF.setVisible(false);
                descriptionTF.setText(null);
                isShowDescription = false;
                descriptionButoon.setText("+");
            }
        });
        controlPane.add(descriptionLB, 1, 1);
        controlPane.add(descriptionTF, 2, 1);
        controlPane.add(descriptionButoon, 0, 1);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);
    protected abstract void addItemsToControlPanel();

}