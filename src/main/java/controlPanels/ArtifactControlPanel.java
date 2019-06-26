package controlPanels;

import SPADEPAC.ArtifactClass;
import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.*;
import tables.ArtifactTable;
import tables.BasicTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Trida predstavujici editacni panel pro element Artifact
 *
 * @author Vaclav Janoch
 */
public class ArtifactControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */
    private SegmentLists segmentLists;
    private boolean exist;
    private ArtifactTable artifactTable;
    private ObservableList<String> artifactArray;

    private int artifactId;
    private int artifactFormIndex;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public ArtifactControlPanel(String buttonName, IFormDataController formDataController,
                                IEditFormController editFormController, FormController formController, ArtifactTable artifactTable, int id, int formIndex) {
        super(buttonName, formDataController, editFormController, formController);
        this.artifactTable = artifactTable;
        this.artifactId = id;
        this.artifactFormIndex = formIndex;
        this.segmentLists = formController.getSegmentLists();
        int i = 0;

        artifactArray = FXCollections.observableArrayList();
        for (ArtifactClass classItem : ArtifactClass.values()) {
            artifactArray.add(classItem.name());
            i++;
        }


        lineList.add(new ControlPanelLineObject("Type: ", ControlPanelLineType.ComboBox, ParamType.ArtifactType, artifactArray));
        createControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    public void createControlPanel() {

        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> saveDataFromPanel());
    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    @Override
    public void showEditControlPanel() {

        List[] artifactData = formDataController.getArtifactStringData(artifactId);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, artifactData, artifactData[5], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, artifactData, artifactData[6], 1);
        controlPanelController.setValueComboBox(this, lineList, ParamType.Role, (ArrayList<Integer>) artifactData[2], artifactData[7]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) artifactData[3], artifactData[8]);
        controlPanelController.setValueComboBox(this, lineList, ParamType.ArtifactType, (ArrayList<Integer>) artifactData[4], artifactData[9]);

        boolean exist = false;
        List boolList = artifactData[10];

        exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setCountToCountLine((Integer) boolList.get(1), (int) boolList.get(2));
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    /**
     * Metoda pro ziskani dat z grafickych komponent a predani dat do editacniho kontroleru EditFormController
     */
    public void saveDataFromPanel() {
        int id = artifactId;

        ArrayList<Integer> roleIndicators = new ArrayList<>();
        ArrayList<Integer> typeIndicators = new ArrayList<>();
        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> dateIndicators = new ArrayList<>();
        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<Integer> role = controlPanelController.processComboBoxLines(ParamType.Role, roleIndicators);
        ArrayList<Integer> type = controlPanelController.processComboBoxLines(ParamType.ArtifactType, typeIndicators);
        ArrayList<LocalDate> date = controlPanelController.processDateLines(ParamType.Date, dateIndicators);

        exist = controlPanelController.isExist();
        String count = controlPanelController.getInstanceCount();
        int countIndicator = controlPanelController.getInstanceCountIndicator();
        editFormController.editDataFromArtifact(aliasTF.getText(), name, desc, exist, role, type, date, nameIndicators, descIndicators, roleIndicators,
                typeIndicators, dateIndicators, artifactTable, count, countIndicator, id);

    }


    public Button getButton() {
        return button;
    }

    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
