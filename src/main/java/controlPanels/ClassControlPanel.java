package controlPanels;

import controllers.FormController;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
import abstractControlPane.NameControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import services.ClassSwitcher;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

public class ClassControlPanel extends NameControlPanel {

    private Label classLB;
    private Label superLB;
    private ComboBox classCB;
    private ComboBox superClassCB;
    private ClassSwitcher switcher;
    private String[] classList;
    private String[] superClassList;
    private SegmentType segmentType;

    protected int classIndex;
    protected int superIndex;

    public ClassControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);
        classIndex = 0;
        superIndex = 0;
        switcher = new ClassSwitcher();
        this.segmentType = segmentType;

    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private void creatSceneCanvas() {

      //  this.setScene(new Scene(mainPanel));

    }

    public GridPane createControlPanel(String[] classList, String[] superClassList){

        this.classList = classList;
        this.superClassList = superClassList;

        classLB = new Label("Class: ");
        superLB = new Label("Super Class: ");

        classCB = new ComboBox<>(FXCollections.observableArrayList(classList));
        classCB.getSelectionModel().selectedIndexProperty().addListener(classListener);

        superClassCB = new ComboBox<>(FXCollections.observableArrayList(superClassList));
        superClassCB.getSelectionModel().selectedIndexProperty().addListener(superListener);

        classCB.setValue(RoleClass.UNASSIGNED);
        superClassCB.setValue(RoleSuperClass.UNASSIGNED);

        controlPane.add(classLB, 0, 1);
        controlPane.add(classCB, 1, 1);
        controlPane.add(superLB, 0, 2);
        controlPane.add(superClassCB, 1, 2);
        controlPane.add(button, 1, 3);

        return controlPane;
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        ClassTable classTable = (ClassTable) basicTable;
        int id = classTable.getId();
        String[] classData = formDataController.getClassStringData(segmentType, id);

        nameTF.setText(classData[0]);
        classCB.getSelectionModel().select(classData[1]);
        superClassCB.getSelectionModel().select(classData[2]);

        button.setOnAction(event ->{
            editFormController.editDataFromClass(segmentType, nameTF.getText(), getClassName(), getSuperClassName(), classTable, id);
            clearPanel(tableView);
         //  this.close();
        });

        // this.show();

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
                superClassCB.setValue(superClassList[0]);
                superIndex = 0;
            } else {
                superClassCB.setDisable(true);
                superClassCB.setValue(superClassList[superIndex]);
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
        if (classCB.getValue() == null || classIndex == 0) {
            return classCB.getItems().get(0).toString(); //   RoleClass.UNASSIGNED.name();
        } else {
            return classCB.getValue().toString();
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
