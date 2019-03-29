package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.WorkUnitControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ParamType;
import services.SegmentType;
import tables.ActivityTable;
import tables.BasicTable;
import tables.VCSTagTable;

import java.util.ArrayList;
import java.util.List;

public class VCSTagControlPanel extends DescriptionControlPanel {

    /**
     * Globální proměnné třídy
     */

    private VCSTagTable tagTable;

    public VCSTagControlPanel(String buttonName, IFormDataController formDataController,
                              IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        addItemsToControlPanel();
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        int id = basicTable.getId();
        List[] vcsTagStringData = formDataController.getVCSTagStringData(id);

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, vcsTagStringData, vcsTagStringData[2], 0);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, vcsTagStringData, vcsTagStringData[3], 1);
        List boolList = vcsTagStringData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLine(this, lineList);

    }

    public void saveDataFromPanel(BasicTable basicTable, TableView tableView){
        tagTable = (VCSTagTable) basicTable;
        int id = tagTable.getId();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);

        editFormController.editDataFromVCSTag(name, desc, nameIndicators, descIndicators, tagTable, controlPanelController.isExist(), id);
        clearPanelCB(tableView);
    }


    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

}
