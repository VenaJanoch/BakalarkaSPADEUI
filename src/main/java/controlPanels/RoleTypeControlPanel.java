package controlPanels;

import SPADEPAC.RoleType;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.*;
import tables.BasicTable;
import tables.ClassTable;
import tables.RoleTypeTable;

import java.util.ArrayList;
import java.util.List;

public class RoleTypeControlPanel extends ClassControlPanel {

    public RoleTypeControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonName, segmentType, formDataController, editFormController, formController);
        lineList.add(new ControlPanelLineObject("Description: ", ControlPanelLineType.Text, ParamType.Description));
        controlPanelController.resetPanel(controlPane);

    }


    public void showEditControlPanelRole(BasicTable basicTable, TableView tableView) {
        RoleTypeTable table = (RoleTypeTable) basicTable;
        int id = table.getId();
        List[] classData = formDataController.getRoleTypeStringData(id);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        setClassData(classData);
        controlPanelController.setValueTextField(this, lineList ,ParamType.Description, classData, classData[6], 5);


        button.setOnAction(event ->{

            saveClassData();
            ArrayList<Integer> descriptionIndicators = new ArrayList<>();
            ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
            editFormController.editDataFromRoleType(name, nameIndicators, description, descriptionIndicators, classListIndex, superClassListIndex,
                    classList, superClassList,  table, controlPanelController.isExist(), id);
            clearPanel(tableView);
        });


    }
}
