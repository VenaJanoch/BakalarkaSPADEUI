package controlPanels;

import abstractControlPane.DateControlPanel;
import controllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ParamType;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommitedConfigurationControlPanel extends DateControlPanel implements IControlPanel {

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
    public void showEditControlPanel(){
        List[] commitedData = formDataController.getCommitedConfigurationStringData(commitedConfigurationId);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, commitedData, commitedData[1], 0);
        controlPanelController.setValueDatePicker(this, lineList ,ParamType.Date, (ArrayList<LocalDate>) commitedData[2], commitedData[3]);

        List boolList = commitedData[4];
        controlPanelController.setCountToCountLine((int)boolList.get(1));
        boolean exist = (boolean) boolList.get(0);
        controlPanelController.setValueExistRadioButton(exist);
    }


    protected void addItemsToControlPanel() {

        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList,this, controlPanelController, controlPanelController.getLineCount() ));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> saveDataFromPanel());

    }

    public void saveDataFromPanel(){

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> dateIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<LocalDate> date = controlPanelController.processDateLines(ParamType.Date, dateIndicators);

        String count = controlPanelController.getInstanceCount();

        editFormController.editDataFromCommitedConfiguration(name, date, nameIndicators, dateIndicators, count, controlPanelController.isExist(), commitedConfigurationId);

    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
