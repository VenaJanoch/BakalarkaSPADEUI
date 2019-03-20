package controlPanels;

import controllers.FormController;
import abstractControlPane.NameControlPanel;
import graphics.ComboBoxItem;
import graphics.ControlPanelLine;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.ClassSwitcher;
import services.ParamType;
import services.SegmentType;
import sun.plugin.javascript.navig.Array;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;
import java.util.List;

public class ClassControlPanel extends NameControlPanel {


    private ArrayList<String> classList;
    private ArrayList<String > superClassList;
    private SegmentType segmentType;

    private  int classIndex;
    private  int superClassIndex;

    public ClassControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        super(buttonName, formDataController, editFormController, formController);

        this.segmentType = segmentType;

    }


    public void createControlPanel(ArrayList classList, ArrayList superClassList){

        this.classList = classList;
        this.superClassList = superClassList;
        createControlPanel();

    }

    public void createControlPanel(){

        ObservableList oClassList = FXCollections.observableArrayList();
        ObservableList oSuperClassList = FXCollections.observableArrayList();

        oClassList.addAll(classList);
        oSuperClassList.addAll(superClassList);
        controlPanelController.setStaticClassBoxes(this, 1,
                new ControlPanelLine(this, controlPanelController, controlPanelController.getClassListener(segmentType), oClassList ),
                new ControlPanelLine( this, controlPanelController, controlPanelController.getSuperClassListener(), oSuperClassList));
        controlPanelController.createNewLine(this, lineList);
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        ClassTable classTable = (ClassTable) basicTable;
        int id = classTable.getId();
       List[] classData = formDataController.getClassStringData(segmentType, id);
        controlPane.getChildren().clear();
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList ,ParamType.Name, classData, classData[3], 0);
        controlPanelController.setValueToClassBox(classData[1], classData[2]);


        button.setOnAction(event ->{

            classIndex = controlPanelController.getClassIndex();
            superClassIndex = controlPanelController.getSuperClassIndex();
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);

            ArrayList<Integer> classList = new ArrayList<>();
            classList.add(classIndex);

            ArrayList<Integer> superClassList = new ArrayList<>();
            superClassList.add(superClassIndex);

            ArrayList<String> classList1 = new ArrayList<>();
            classList1.add(getClassName());

            ArrayList<String> superClassList1 = new ArrayList<>();
            superClassList1.add(getSuperClassName());

            editFormController.editDataFromClass(segmentType, name, nameIndicators, classList, superClassList, classList1, superClassList1,  classTable, id);
            clearPanel(tableView);
        });


    }


    public String getClassName() {
        if (classIndex == 0) {
            return classList.get(0); //   RoleClass.UNASSIGNED.name();
        } else {
            return classList.get(classIndex);
        }
    }

    public String getSuperClassName() {
     return superClassList.get(superClassIndex);
    }

    public void clearPanel(TableView<ClassTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    @Override
    protected void createBaseControlPanel() {

    }
}
