package controlPanels;

import abstractControlPane.NameControlPanel;
import controllers.formControllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IControlPanel;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.*;
import tables.PersonTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Person
 *
 * @author Vaclav Janoch
 */
public class PersonControlPanel extends NameControlPanel implements IControlPanel {

    /**
     * Globální proměnné třídy
     */
    private int roleId;
    private PersonTable personTable;
    private int roleIndex;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public PersonControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController,
                              FormController formController, PersonTable personTable, int roleId, int roleIndex) {
        super(buttonName, formDataController, editFormController, formController);
        this.roleId = roleId;
        this.roleIndex = roleIndex;
        this.personTable = personTable;
        SegmentLists segmentLists = formController.getSegmentLists();
        lineList.add(new ControlPanelLineObject("Role type: ", ControlPanelLineType.ComboBox, ParamType.RoleType, segmentLists.getRoleTypeObservable()));
        this.addItemsToControlPanel();
    }

    /**
     * Metoda volajici kontroler ControlPanelController pro vygenerovani noveho radku
     * Pripadne rozsireni o staticke objekty
     */
    protected void addItemsToControlPanel() {

        controlPanelController.setCountLine(this, 1, new ControlPanelLine(lineList, this, controlPanelController, Constans.numberIndicatorList, controlPanelController.getLineCount()));
        controlPanelController.createNewLineWithExist(this, lineList);

        button.setOnAction(event -> {
            ArrayList<Integer> nameIndicators = new ArrayList<>();
            //ArrayList<Integer> descIndicators = new ArrayList<>();
            ArrayList<Integer> roleTypeIndicators = new ArrayList<>();

            ArrayList<String> name = controlPanelController.processTextLines(ParamType.Name, nameIndicators);
            ArrayList<Integer> roleType = controlPanelController.processComboBoxLines(ParamType.RoleType, roleTypeIndicators);

            String count = controlPanelController.getInstanceCount();
            int countIndicator = controlPanelController.getInstanceCountIndicator();
            editFormController.editDataFromPerson(aliasTF.getText(), name, nameIndicators, count, countIndicator, roleType, roleTypeIndicators, personTable,
                    controlPanelController.isExist(), roleId);

        });

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     */
    @Override
    public void showEditControlPanel() {

        List[] roleData = formDataController.getPersonStringData(roleId);
        controlPanelController.resetPanel(controlPane);
        addItemsToControlPanel();

        controlPanelController.setValueTextField(this, lineList, ParamType.Name, roleData, roleData[2], 0);
        // controlPanelController.setValueTextField(this, lineList ,ParamType.Description, roleData, roleData[4], 1);
        controlPanelController.setValueComboBox(this, lineList, ParamType.RoleType, (ArrayList<Integer>) roleData[1], roleData[3]);
        List boolList = roleData[4];
        boolean exist = (boolean) boolList.get(0);

        controlPanelController.setValueExistRadioButton(exist);
        controlPanelController.setCountToCountLine((int) boolList.get(1), (int) boolList.get(2));
        controlPanelController.setAlias((String) boolList.get(3), this);
    }

    @Override
    protected void createBaseControlPanel() {

    }
}
