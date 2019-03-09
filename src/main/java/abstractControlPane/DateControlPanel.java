package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import tables.BasicTable;

public abstract class DateControlPanel extends NameControlPanel {

    protected Label dateLB;
    protected DatePicker dateDP;

    private Button dateButton;
    private boolean isShowDate;

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        createBaseControlPanel();
    }

    public DateControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        createBaseControlPanel();
    }

    protected void createBaseControlPanel() {

        dateLB = new Label("Created: ");
        dateDP = new DatePicker();
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

        controlPane.add(dateLB, 0, 1);
        controlPane.setHalignment(dateLB, HPos.RIGHT);
        controlPane.add(dateDP, 2, 1);
        controlPane.add(dateButton, 1, 1);

    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);

}
