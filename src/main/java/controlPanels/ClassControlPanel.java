package controlPanels;

import controllers.formControllers.FormController;
import abstractControlPane.NameControlPanel;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.ParamType;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;
import java.util.List;

public class ClassControlPanel extends NameControlPanel {


    protected ArrayList<String> classList;
    protected ArrayList<String> superClassList;
    protected ArrayList<Integer> classListIndex;
    protected ArrayList<Integer> superClassListIndex;
    protected ArrayList<String> classNameList;
    protected ArrayList<String> superClassNameList;
    protected ArrayList<String> name;
    protected ArrayList<Integer> nameIndicators;
    protected SegmentType segmentType;

    private int classIndex;
    private int superClassIndex;

    public ClassControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);

        this.segmentType = segmentType;

        classList = new ArrayList<>();
        superClassList = new ArrayList<>();
        classListIndex = new ArrayList<>();
        superClassListIndex = new ArrayList<>();
        name = new ArrayList<>();
        nameIndicators = new ArrayList<>();

    }


    public void createControlPanel(ArrayList classList, ArrayList superClassList) {

        this.classList = classList;
        this.superClassList = superClassList;
        createControlPanel();

    }

    public void createControlPanel() {

        ObservableList oClassList = FXCollections.observableArrayList();
        ObservableList oSuperClassList = FXCollections.observableArrayList();

        oClassList.addAll(classList);
        oSuperClassList.addAll(superClassList);
        controlPanelController.setStaticClassBoxes(this, 2,
                new ControlPanelLine(this, controlPanelController, controlPanelController.getClassListener(segmentType), oClassList),
                new ControlPanelLine(this, controlPanelController, controlPanelController.getSuperClassListener(), oSuperClassList));
        controlPanelController.createNewLineWithExist(this, lineList);
    }

    protected void setClassData(List[] classData) {
        controlPanelController.setValueTextField(this, lineList, ParamType.Name, classData, classData[3], 0);
        controlPanelController.setValueToClassBox(classData[1], classData[2]);

        List boolList = classData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
    }

    protected void saveClassData() {
        classIndex = controlPanelController.getClassIndex();
        superClassIndex = controlPanelController.getSuperClassIndex();

        name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);

        classListIndex.clear();
        superClassListIndex.clear();

        classListIndex.add(classIndex);
        superClassListIndex.add(superClassIndex);
        classNameList = new ArrayList<>();
        classNameList.add(classList.get(classIndex));
        superClassNameList = new ArrayList<>();
        superClassNameList.add(superClassList.get(superClassIndex));
    }

    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        ClassTable classTable = (ClassTable) basicTable;
        int id = classTable.getId();
        List[] classData = formDataController.getClassStringData(segmentType, id);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        setClassData(classData);


        button.setOnAction(event -> {

            saveClassData();

            editFormController.editDataFromClass(segmentType, aliasTF.getText(), name, nameIndicators, classListIndex, superClassListIndex, classNameList, superClassNameList,
                    classTable, controlPanelController.isExist(), id);
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
