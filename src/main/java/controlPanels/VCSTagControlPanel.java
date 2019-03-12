package controlPanels;

import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.WorkUnitControlPanel;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
        String[] milestoneData = formDataController.getVCSTagStringData(id);

        nameTF.setText(milestoneData[0]);
        descriptionTF.setShowItem(false);
        if (milestoneData[1] != null){
            descriptionTF.setTextToTextField(milestoneData[1]);
            descriptionTF.setShowItem(true);
        }

        button.setOnAction(event -> saveDataFromPanel(basicTable, tableView));
    }

    @Override
    protected void addItemsToControlPanel() {

        controlPane.add(button, 2, 3);


    }

    public void saveDataFromPanel(BasicTable basicTable, TableView tableView){
        tagTable = (VCSTagTable) basicTable;
        int id = tagTable.getId();


        String desc = "null";
        if (descriptionTF.getTextFromTextField() != null){
            desc = descriptionTF.getTextFromTextField();
        }

        editFormController.editDataFromVCSTag(nameTF.getText(), desc, tagTable, id);
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
