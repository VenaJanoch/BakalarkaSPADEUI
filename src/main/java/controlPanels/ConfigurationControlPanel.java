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

    private boolean isRelease;
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
                                     FormController formController, ConfigTable configTable, int configId, int configIndex){
        super(buttonName, formDataController, editFormController, formController);

        this.segmentLists = formController.getSegmentLists();
        this.configId = configId;
        this.configIndex = configIndex;
        this.configTable = configTable;
        this.addItemsToControlPanel();
    }



    protected void addItemsToControlPanel(){
        authorRoleCB = new ComboBoxItem("Author: ", formController.getRoleObservable());
        dateDP.setItemNameLB("Created: ");

        isReleaseLB = new Label("Release: ");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);

        cprCB = new CheckComboBoxItem("CPRs", segmentLists.getCPRObservable() );
        branchCB = new CheckComboBoxItem("Branches", segmentLists.getBranchObservable());
        changeCB = new CheckComboBoxItem("Changes", segmentLists.getBranchObservable());
        addTag = new Button("Add Tag");

        controlPane.add(authorRoleCB.getItemButton(), 0, 2);
        controlPane.add(authorRoleCB.getItemNameLB(), 1, 2);
        controlPane.add(authorRoleCB.getItemCB(), 2, 2);
       
        controlPane.add(cprCB.getItemButton(), 0, 3);
        controlPane.add(cprCB.getItemNameLB(), 1, 3);
        controlPane.add(cprCB.getItemCB(), 2, 3);

        controlPane.add(branchCB.getItemButton(), 0, 4);
        controlPane.add(branchCB.getItemNameLB(), 1, 4);
        controlPane.add(branchCB.getItemCB(), 2, 4);

        controlPane.add(changeCB.getItemButton(), 0, 5);
        controlPane.add(changeCB.getItemNameLB(), 1, 5);
        controlPane.add(changeCB.getItemCB(), 2, 5);

        controlPane.add(isReleaseLB, 0, 6);
        controlPane.add(rbYes, 1, 6);
        controlPane.add(rbNo, 2, 6);


        controlPane.add(addTag, 2, 7);
        controlPane.add(button, 2, 8);

        button.setOnAction(event ->{
            editFormController.editDataFromConfiguration(nameTF.getText(), dateDP.getDateFromDatePicker(),  authorRoleCB.getItemIndex(), isRelease, cprCB.getItemIndicies(),
                    branchCB.getItemIndicies(), changeCB.getItemIndicies(), configId);
            formController.setNameToItem(configIndex, nameTF.getText());
        });

    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

        String[] configData = formDataController.getConfigurationStringData(configId);

        nameTF.setText(configData[0]);

        dateDP.setShowItem(false);
        if(configData[1] != null){
            dateDP.setDateToPicker(LocalDate.parse(configData[1]));
            dateDP.setShowItem(false);
        }

        authorRoleCB.setShowItem(false);
        if (configData[2] != null){
            authorRoleCB.setShowItem(true);
            authorRoleCB.selectItemInComboBox(Integer.parseInt(configData[2]));
        }

        cprCB.setShowItem(false);
        List<Integer> cprs = formDataController.getCPRFromConfiguration(configId);
        if (cprs.size() != 0){
            cprCB.selectItemsInComboBox(cprs);
            cprCB.setShowItem(true);
        }

        branchCB.setShowItem(false);
        List<Integer> branches = formDataController.getBranchesFromConfiguration(configId);
        if (branches.size() != 0){
            branchCB.selectItemsInComboBox(branches);
            branchCB.setShowItem(true);
        }

        changeCB.setShowItem(false);
        List<Integer> changes = formDataController.getChangesFromConfiguration(configId);
        if (changes.size() != 0){
            changeCB.selectItemsInComboBox(changes);
            changeCB.setShowItem(true);
        }


        isRelease = Boolean.valueOf(configData[3]);
        if (isRelease){
            rbYes.setSelected(true);
        }else {
            rbNo.setSelected(false);
        }
    }
}
