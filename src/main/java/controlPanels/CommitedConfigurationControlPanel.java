package controlPanels;

import abstractControlPane.DateControlPanel;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.WorkUnitControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.SegmentType;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommitedConfigurationControlPanel extends DateControlPanel {

    /**
     * Globální proměnné třídy
     */


    private CommitedConfigurationTable commitedConfigurationTable;

    private int commitedConfigurationId;
    private int commitedConfigurationFormId;
    
    public CommitedConfigurationControlPanel(String buttonName, IFormDataController formDataController,
                                             IEditFormController editFormController, FormController formController, CommitedConfigurationTable branchTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
       
        this.commitedConfigurationFormId = formIndex;
        this.commitedConfigurationId = id;
        this.commitedConfigurationTable = branchTable;

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView){
        String[] commitedData = formDataController.getCommitedConfigurationStringData(commitedConfigurationId);

        nameTF.setTextToTextField(commitedData[0]);
        controlPanelController.setValueDatePicker(dateDP, commitedData, 1);
    }


    protected void addItemsToControlPanel() {

        controlPane.add(button, 2, 3);

        button.setOnAction(event -> saveDataFromPanel());

    }

    public void saveDataFromPanel(){

        LocalDate date = controlPanelController.checkValueFromDateItem(dateDP);


        editFormController.editDataFromCommitedConfiguration(nameTF.getTextFromTextField(), date, commitedConfigurationId);
        formController.setNameToItem(commitedConfigurationFormId, nameTF.getTextFromTextField());

    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
