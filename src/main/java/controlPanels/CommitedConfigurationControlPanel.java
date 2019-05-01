package controlPanels;

import abstractControlPane.DateControlPanel;
import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import tables.BasicTable;
import tables.CommitedConfigurationTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommitedConfigurationControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */


    private CommitedConfigurationTable commitedConfigurationTable;

    private int commitedConfigurationId;
    private int commitedConfigurationFormId;
    
    public CommitedConfigurationControlPanel(String buttonName, IFormDataController formDataController,
                                             IEditFormController editFormController, FormController formController, CommitedConfigurationTable branchTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);

        lineList.add(new ControlPanelLineObject("Committed: ", ControlPanelLineType.Date, ParamType.EndDate));

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
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, commitedData, commitedData[8], 7);
        controlPanelController.setValueDatePicker(this, lineList ,ParamType.EndDate, (ArrayList<LocalDate>) commitedData[2], commitedData[3]);
        controlPanelController.setValueDatePicker(this, lineList ,ParamType.Date, (ArrayList<LocalDate>) commitedData[5], commitedData[6]);

        List boolList = commitedData[4];
        controlPanelController.setCountToCountLine((int)boolList.get(1));
        boolean exist = (boolean) boolList.get(0);
        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String)boolList.get(2), this);
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
        ArrayList<LocalDate> date = controlPanelController.processDateLines(ParamType.EndDate, dateIndicators);
        ArrayList<Integer> descriptionIndicators = new ArrayList<>();
        ArrayList<Integer> createdIndicators = new ArrayList<>();
        ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
        ArrayList<LocalDate> created = controlPanelController.processDateLines(ParamType.Date, createdIndicators);



        String count = controlPanelController.getInstanceCount();

        editFormController.editDataFromCommitedConfiguration(aliasTF.getText(), name, nameIndicators, description, descriptionIndicators, created, createdIndicators, date, dateIndicators, count, controlPanelController.isExist(), commitedConfigurationId);

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
