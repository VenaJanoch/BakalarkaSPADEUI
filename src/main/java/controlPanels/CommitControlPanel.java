package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.FormController;
import graphics.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentLists;
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
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("VCS Tag: ", ControlPanelLineType.ComboBox, ParamType.VCSTag, segmentLists.getVCSTag() ));
        lineList.add(new ControlPanelLineObject("Branches: ", ControlPanelLineType.CheckBox, ParamType.Branch, segmentLists.getBranchObservable()));
        createControlPanel();
    }

    @Override
    protected void createBaseControlPanel() {

    }



    public void createControlPanel(){

        controlPanelController.setRadioButton(this,1, "Release: ", true);
        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController));
        controlPanelController.createNewLine(this, lineList);

        button.setOnAction(event ->{
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> branchIndicators = new ArrayList<>();
            ArrayList<ArrayList<Integer>> branchs = controlPanelController.processCheckComboBoxLines(ParamType.Branch, branchIndicators);

            ArrayList<Integer> tagIndicators = new ArrayList<>();
            ArrayList<Integer> tag = controlPanelController.processComboBoxLines(ParamType.VCSTag, tagIndicators);

            String count = controlPanelController.getInstanceCount();
            boolean exist = controlPanelController.isMain();

            editFormController.editDataFromCommit(name, nameIndicators, tag, tagIndicators, branchs, branchIndicators, exist, count,
                    controlPanelController.isExist(), commitId);

        });

    }


    public void showEditControlPanel() {

        List[] commitData = formDataController.getCommitStringData(commitId);

        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, commitData, commitData[1], 0);
        ArrayList<ArrayList<Integer>> branches = formDataController.getBranchesFromCommit(commitId);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.Branch, branches, commitData[4]);
        controlPanelController.setValueComboBox(this, lineList ,ParamType.VCSTag, (ArrayList<Integer>) commitData[2], commitData[3]);


        boolean release = false;
        List boolList = commitData[5];
        if (boolList.size() > 2){
            release = true;
        }

        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setValueRadioButton(release);
        controlPanelController.setCountToCountLine((int) boolList.get(1));
    }


}
