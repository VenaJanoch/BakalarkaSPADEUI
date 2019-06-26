package controlPanels;

import abstractControlPane.WorkUnitDateControlPanel;
import controllers.formControllers.FormController;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentType;
import tables.BasicTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro Project
 *
 * @author Vaclav Janoch
 */
public class ProjectControlPanel extends WorkUnitDateControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */


    public ProjectControlPanel(String buttonName, IFormDataController formDataController,
                               IEditFormController editFormController, FormController formController) {
        super(buttonName, formDataController, editFormController, formController);
        lineList.add(new ControlPanelLineObject("End date: ", ControlPanelLineType.Date, ParamType.EndDate));
        addItemsToControlPanel();
    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    @Override
    protected void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    @Override
    public void showEditControlPanel() {
        List[] projectData = formDataController.getProjectStringData();

        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, projectData, projectData[4], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, projectData, projectData[5], 1);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) projectData[2], projectData[6]);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.EndDate, (ArrayList<LocalDate>) projectData[3], projectData[7]);


        ArrayList<ArrayList<Integer>> workUnits = formDataController.getWorkUnitFromSegment(0, SegmentType.Project);
        controlPanelController.setValueCheckComboBox(this, lineList, ParamType.WorkUnit, workUnits, projectData[8]);

    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    @Override
    protected void addItemsToControlPanel() {

        controlPanelController.createNewLineWithExist(this, lineList);
        button.setOnAction(event -> saveDataFromPanel());
    }

    /**
     * Metoda pro ziskani dat z grafickych komponent a predani dat do editacniho kontroleru EditFormController
     */
    public void saveDataFromPanel() {

        ArrayList<Integer> nameIndicators = new ArrayList<>();
        ArrayList<Integer> descIndicators = new ArrayList<>();
        ArrayList<Integer> workUnitIndicators = new ArrayList<>();
        ArrayList<Integer> startDateIndicators = new ArrayList<>();
        ArrayList<Integer> endDateIndicators = new ArrayList<>();

        ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
        ArrayList<String> desc = controlPanelController.processTextLines(ParamType.Description, descIndicators);
        ArrayList<ArrayList<Integer>> workUnit = controlPanelController.processCheckComboBoxLines(ParamType.WorkUnit, workUnitIndicators);
        ArrayList<LocalDate> startDate = controlPanelController.processDateLines(ParamType.Date, startDateIndicators);
        ArrayList<LocalDate> endDate = controlPanelController.processDateLines(ParamType.EndDate, endDateIndicators);


        editFormController.editDataFromProject(name, startDate, endDate, desc, workUnit, workUnitIndicators, nameIndicators,
                startDateIndicators, endDateIndicators, descIndicators);
    }

    public Button getButton() {
        return button;
    }

    public void clearPanelCB(TableView tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
    }
}
