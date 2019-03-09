package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import tables.BasicTable;

public abstract class DateDescControlPanel extends DescriptionControlPanel {

    protected Label dateLB;
    protected DatePicker dateDP;

    private Button dateButton;
    private boolean isShowDate;

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        addItemsToControlPanel();
    }

    public DateDescControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel();
    }

    protected void addItemsToControlPanel() {

        dateLB = new Label("Created: ");
        dateDP = new DatePicker();
        dateDP.setId("DP1");
        dateDP.setVisible(false);

        isShowDate = false;
        dateButton = new Button("+");
        dateButton.setOnAction(event -> {
            if (!isShowDate){
                dateDP.setVisible(true);
                isShowDate  = true;
                dateButton.setText("-");
            }else{
                dateDP.setVisible(false);
                dateDP.setValue(null);
                isShowDate = false;
                dateButton.setText("+");
            }
        });

        controlPane.add(dateLB, 1, 2);
        controlPane.setHalignment(dateLB, HPos.LEFT);
        controlPane.add(dateDP, 2, 2);
        controlPane.add(dateButton, 0, 2);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
