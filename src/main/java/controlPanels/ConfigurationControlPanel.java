package controlPanels;

import abstractControlPane.DateControlPanel;
import abstractControlPane.DescriptionControlPanel;
import controllers.FormController;
import forms.TagForm;
import graphics.CheckComboBoxItem;
import graphics.ComboBoxItem;
import graphics.DateItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import services.SegmentType;
import tables.BasicTable;
import tables.ConfigTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationControlPanel extends DateControlPanel {

    private Label isReleaseLB;


    private Button addTag;
    final ToggleGroup group = new ToggleGroup();

    private RadioButton rbYes;
    private RadioButton rbNo;
    private CheckComboBoxItem branchCB;
    private ComboBoxItem authorRoleCB;
    private CheckComboBoxItem cprCB;
    private CheckComboBoxItem changeCB;


    private TagForm tagForm;
    private SegmentLists segmentLists;


    private int configId;
    private ConfigTable configTable;
    private int configIndex;

    public ConfigurationControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                                     FormController formController, ConfigTable configTable, int configId, int configIndex) {
        super(buttonName, formDataController, editFormController, formController);

        this.segmentLists = formController.getSegmentLists();
        this.configId = configId;
        this.configIndex = configIndex;
        this.configTable = configTable;
        this.addItemsToControlPanel();
    }


    protected void addItemsToControlPanel() {
        authorRoleCB = new ComboBoxItem("Author: ", formController.getRoleObservable());
        dateDP.setItemNameLB("Created: ");

        isReleaseLB = new Label("Release: ");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);

        group.selectedToggleProperty().addListener(controlPanelController.radioButtonListener());

        cprCB = new CheckComboBoxItem("CPRs", segmentLists.getCPRObservable());
        branchCB = new CheckComboBoxItem("Branches", segmentLists.getBranchObservable());
        changeCB = new CheckComboBoxItem("Changes", segmentLists.getBranchObservable());
        addTag = new Button("Add Tag");

        controlPanelController.setComboBoxItemToControlPanel(controlPane, authorRoleCB, 0, 2);
        controlPanelController.setCheckComboBoxItemToControlPanel(controlPane, cprCB, 0, 3);
        controlPanelController.setCheckComboBoxItemToControlPanel(controlPane, branchCB, 0, 4);
        controlPanelController.setCheckComboBoxItemToControlPanel(controlPane, changeCB, 0, 5);


        controlPane.add(isReleaseLB, 0, 6);
        controlPane.add(rbYes, 1, 6);
        controlPane.add(rbNo, 2, 6);


        controlPane.add(addTag, 2, 7);
        controlPane.add(button, 2, 8);

        button.setOnAction(event -> {

            LocalDate date = controlPanelController.checkValueFromDateItem(dateDP);
            editFormController.editDataFromConfiguration(nameTF.getTextFromTextField(), date, authorRoleCB.getItemIndex(),
                    controlPanelController.isMain(), cprCB.getChoosedIndicies(),
                    branchCB.getChoosedIndicies(), changeCB.getChoosedIndicies(), configId);
        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        String[] configData = formDataController.getConfigurationStringData(configId);

        nameTF.setTextToTextField(configData[0]);

        controlPanelController.setValueDatePicker(dateDP, configData, 1);

        controlPanelController.setValueComboBox(authorRoleCB, configData, 2);

        List<Integer> cprList = formDataController.getCPRFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(cprCB, cprList);

        List<Integer> branches = formDataController.getBranchesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(branchCB, branches);

        List<Integer> changeList = formDataController.getChangesFromConfiguration(configId);
        controlPanelController.setValueCheckComboBox(changeCB, changeList);


       boolean isRelease = Boolean.valueOf(configData[3]);
        if (isRelease) {
            rbYes.setSelected(true);
        } else {
            rbNo.setSelected(false);
        }
    }
}
