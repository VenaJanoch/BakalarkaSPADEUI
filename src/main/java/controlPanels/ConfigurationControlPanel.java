package controlPanels;

import abstractControlPane.DateControlPanel;
import controllers.FormController;
import forms.TagForm;
import graphics.CheckComboBoxItem;
import graphics.ComboBoxItem;
import graphics.ControlPanelLine;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.*;
import services.*;
import tables.BasicTable;
import tables.ConfigTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationControlPanel extends DateControlPanel {

    private Label isReleaseLB;


    private Button addTag;


    private int configId;
    private ConfigTable configTable;

    public ConfigurationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                                     FormController formController, ConfigTable configTable, int configId, int configIndex) {
        super(buttonName, formDataController, editFormController, formController);

        this.configId = configId;
        this.configTable = configTable;
        lineList.add(new ControlPanelLineObject("CPRs: ", ControlPanelLineType.ComboBox, ParamType.CPR));
        lineList.add(new ControlPanelLineObject("Branches: ", ControlPanelLineType.ComboBox, ParamType.Branch));
        lineList.add(new ControlPanelLineObject("Changes: ", ControlPanelLineType.ComboBox, ParamType.Change));
        this.addItemsToControlPanel();
    }


    protected void addItemsToControlPanel() {


        controlPanelController.setRadioButton(this, "Release: ", true);
        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController));
        addTag = new Button("add Tag");
        controlPanelController.setStaticButton(this, 3, addTag);
        controlPanelController.createNewLine(this, lineList);



        button.setOnAction(event -> {

            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<Integer> dateIndicators = new ArrayList<>();
            ArrayList<Integer> roleIndicators = new ArrayList<>();

            ArrayList<Integer> cprsIndicators = new ArrayList<>();
            ArrayList<Integer> branchIndicators = new ArrayList<>();
            ArrayList<Integer> changeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
            ArrayList<LocalDate> dates = controlPanelController.processDateLines(ParamType.Date, dateIndicators);
            ArrayList<ArrayList<Integer>> cprs = controlPanelController.processCheckComboBoxLines(ParamType.CPR, cprsIndicators);
            ArrayList<ArrayList<Integer>> branchs = controlPanelController.processCheckComboBoxLines(ParamType.Branch, branchIndicators);
            ArrayList<ArrayList<Integer>> changes = controlPanelController.processCheckComboBoxLines(ParamType.Change, changeIndicators);
            String instanceCount = controlPanelController.getInstanceCount();
            editFormController.editDataFromConfiguration(name, dates, controlPanelController.isMain(), role, cprs, branchs, changes, cprsIndicators,
                    nameIndicators, dateIndicators, roleIndicators, branchIndicators, changeIndicators, instanceCount,  configId);
        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        List[] configData = formDataController.getConfigurationStringData(configId);

        controlPane.getChildren().clear();
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, configData, configData[3], 0);
        controlPanelController.setValueDatePicker(this, lineList ,ParamType.Date, (ArrayList<LocalDate>) configData[1], configData[4]);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Role, configData, configData[5], 2);

        ArrayList<ArrayList<Integer>> cprList = formDataController.getCPRFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.CPR, cprList, configData[6]);

        ArrayList<ArrayList<Integer>> branches = formDataController.getBranchesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.Branch, branches, configData[7]);

        ArrayList<ArrayList<Integer>> changeList = formDataController.getChangesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(this, lineList ,ParamType.WorkUnit, changeList, configData[8]);


       boolean isRelease = Boolean.valueOf((Boolean) configData[9].get(0));
       controlPanelController.setValueRadioButton(isRelease);
    }
}
