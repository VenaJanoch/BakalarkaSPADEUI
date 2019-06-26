package controlPanels;

import abstractControlPane.DateDescControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.Constans;
import services.ParamType;
import tables.BasicTable;
import tables.CommitTable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Commit
 *
 * @author Vaclav Janoch
 */
public class CommitControlPanel extends DateDescControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */
    private int commitId;
    private int commitFormId;
    private CommitTable commitTable;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public CommitControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, CommitTable branchTable, int id, int formIndex) {
        super(buttonName, formDataController, editFormController, formController);
        this.commitFormId = formIndex;
        this.commitId = id;
        this.commitTable = branchTable;
        createControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    public void createControlPanel() {

        controlPanelController.setRadioButton(this, 1, "Release: ", true);
        controlPanelController.setCountLine(this, 2, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> {
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);

            ArrayList<Integer> descriptionIndicators = new ArrayList<>();
            ArrayList<Integer> createdIndicators = new ArrayList<>();
            ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
            ArrayList<LocalDate> created = controlPanelController.processDateLines(ParamType.Date, createdIndicators);


            String count = controlPanelController.getInstanceCount();
            int countIndicator = controlPanelController.getInstanceCountIndicator();
            boolean exist = controlPanelController.isMain();

            editFormController.editDataFromCommit(aliasTF.getText(), name, nameIndicators, description, descriptionIndicators, created, createdIndicators, exist,
                    count, countIndicator, controlPanelController.isExist(), commitId);

        });

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    public void showEditControlPanel() {

        List[] commitData = formDataController.getCommitStringData(commitId);

        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, commitData, commitData[1], 0);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, commitData, commitData[4], 3);
        controlPanelController.setValueDatePicker(this, lineList, ParamType.Date, (ArrayList<LocalDate>) commitData[5], commitData[6]);


        boolean release = false;
        List boolList = commitData[2];
        if (boolList.size() > 2) {
            release = true;
        }

        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setValueRadioButton(release);
        controlPanelController.setCountToCountLine((int) boolList.get(1), (int) boolList.get(2));
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {

    }
}
