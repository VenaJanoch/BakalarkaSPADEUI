package controlPanels;

import controllers.FormController;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractControlPane.NameControlPanel;
import graphics.ComboBoxItem;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import services.ClassSwitcher;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

public class ClassControlPanel extends NameControlPanel {

    private ComboBoxItem classCB;
    private ComboBoxItem superClassCB;
    private ClassSwitcher switcher;
    private String[] classList;
    private String[] superClassList;
    private SegmentType segmentType;

    private int classIndex;
    private int superIndex;

    public ClassControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        switcher = new ClassSwitcher();
        this.segmentType = segmentType;

    }


    public void createControlPanel(String[] classList, String[] superClassList){

        this.classList = classList;
        this.superClassList = superClassList;


        classCB = new ComboBoxItem("Class: ", FXCollections.observableArrayList(classList));

        superClassCB = new ComboBoxItem("Super class: ", FXCollections.observableArrayList(superClassList));

        classCB.selectItemInComboBox(0);
        superClassCB.selectItemInComboBox(0);

        controlPanelController.setComboBoxItemToControlPanel(controlPane, classCB, 0 , 1);
        controlPanelController.setComboBoxItemToControlPanel(controlPane, superClassCB, 0 , 2);

        classCB.selectItemInComboBox(0);
        superClassCB.selectItemInComboBox(0);

        controlPane.add(button, 1, 3);


    }

    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        ClassTable classTable = (ClassTable) basicTable;
        int id = classTable.getId();
        String[] classData = formDataController.getClassStringData(segmentType, id);

        nameTF.setTextToTextField(classData[0]);

        classCB.selectItemInComboBox(classData[1]);

        button.setOnAction(event ->{
            editFormController.editDataFromClass(segmentType, nameTF.getTextFromTextField(), getClassName(), getSuperClassName(), classTable, id);
            clearPanel(tableView);
        });


    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Class. Zavolá
     * metody pro mapování Class na Super Class
     */
    ChangeListener<Number> classListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            classIndex = newValue.intValue();
            superIndex = switcher.ClassToSuperClass(segmentType, classIndex);
            if (superIndex == -1) {
                superClassCB.setDisable(false);
                superClassCB.selectItemInComboBox(superClassList[0]);
                superIndex = 0;
            } else {
                superClassCB.setDisable(true);
                superClassCB.selectItemInComboBox(superClassList[superIndex]);
            }
        }
    };
    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Super Class
     */
    ChangeListener<Number> superListener = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

            superIndex = newValue.intValue();
        }
    };

    public String getClassName() {
        if (classIndex == 0) {
            return classList[0]; //   RoleClass.UNASSIGNED.name();
        } else {
            return classList[classIndex];
        }
    }

    public String getSuperClassName() {
     return superClassList[superIndex];
    }

    public void clearPanel(TableView<ClassTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    @Override
    protected void createBaseControlPanel() {

    }
}
