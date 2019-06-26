package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.formControllers.FormController;
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

/**
 * Trida predstavujici editacni panel pro vyctove typy elementu Work Unit
 *
 * @author Vaclav Janoch
 */
public class ClassControlPanel extends NameControlPanel {

    /**
     * Globální proměnné třídy
     */
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

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
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

    /**
     * Pretizena metoda pro nastaveni seznamu class a superclass
     * Pripadne rozsireni o staticke objekty
     * @param classList seznam class
     * @param superClassList seznam superclass
     */
    public void createControlPanel(ArrayList classList, ArrayList superClassList) {

        this.classList = classList;
        this.superClassList = superClassList;
        createControlPanel();

    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
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

    /**
     * Metoda pro nastaveni dat do comboboxu
     *
     * @param classData seznam s parametry elementu
     */
    protected void setClassData(List[] classData) {
        controlPanelController.setValueTextField(this, lineList, ParamType.Name, classData, classData[3], 0);
        controlPanelController.setValueToClassBox(classData[1], classData[2]);

        List boolList = classData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setAlias((String) boolList.get(2), this);
    }

    /**
     * Metoda pro ziskani dat z grafickych komponent a editaci pomoci kontroleru ControlPanelController
     */
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

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
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

    /**
     * Metoda pro smazani vyberu a aktualizaci dat v tabulce
     * @param tableView tabulka pro aktualizaci dat
     */
    public void clearPanel(TableView<ClassTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }

    @Override
    protected void createBaseControlPanel() {

    }
}
