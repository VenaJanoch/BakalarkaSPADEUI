package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.FormController;
import graphics.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import services.ParamType;
import tables.BranchTable;
import tables.CommitTable;

import java.util.ArrayList;
import java.util.List;

public class CommitControlPanel extends NameControlPanel implements IControlPanel {


    private int commitId;
    private int commitFormId;
    private CommitTable commitTable;

    public CommitControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, CommitTable branchTable, int id, int formIndex){
        super(buttonName, formDataController, editFormController, formController);
        this.commitFormId = formIndex;
        this.commitId = id;
        this.commitTable = branchTable;
        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {

    }



    public void createControlPanel(){

        controlPanelController.setRadioButton(this, "Release: ", true);
        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController));
        controlPanelController.createNewLine(this, lineList);

        button.setOnAction(event ->{
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            String count = controlPanelController.getInstanceCount();
            boolean exist = controlPanelController.isMain();

            editFormController.editDataFromCommit(name, nameIndicators, exist, count, commitId);

        });

    }


    public void showEditControlPanel() {

        List[] commitData = formDataController.getCommitStringData(commitId);

        controlPane.getChildren().clear();
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, commitData, commitData[2], 0);
        boolean exist = false;
        List boolList = commitData[1];
        if (boolList.size() != 0){
            exist = true;
        }

        controlPanelController.setValueRadioButton(exist);

    }


}
