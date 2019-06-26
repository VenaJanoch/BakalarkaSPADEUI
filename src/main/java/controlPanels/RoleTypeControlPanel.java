package controlPanels;

import controllers.formControllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.control.TableView;
import services.ControlPanelLineObject;
import services.ControlPanelLineType;
import services.ParamType;
import services.SegmentType;
import tables.BasicTable;
import tables.RoleTypeTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici editacni panel pro element Activity
 *
 * @author Vaclav Janoch
 */
public class RoleTypeControlPanel extends ClassControlPanel {

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     * Je zde rozsiren seznam poznych typu panelu pro dany element
     *
     * @param buttonName         textovy retezec pro potvrzovaci tlacitko
     * @param formDataController instace tridy FormDataController pro ziskani dat z datoveho modelu
     * @param editFormController instace tridy EditDataController pro predani novych dat
     * @param formController     instace tridy FormController
     */
    public RoleTypeControlPanel(String buttonName, SegmentType segmentType, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonName, segmentType, formDataController, editFormController, formController);
        lineList.add(new ControlPanelLineObject("Description: ", ControlPanelLineType.Text, ParamType.Description));
        controlPanelController.resetPanel(controlPane);

    }

    /**
     * Metoda pro zobrazeni postraniho editacniho panelu
     * Nejprve jsou ziskana data z datoveho modelu
     * nasledne pomoci kontroleru ControlPanelController pridana do panelu
     *
     * @param basicTable Instance BasicTable
     * @param tableView  Instace TableView
     */
    public void showEditControlPanelRole(BasicTable basicTable, TableView tableView) {
        RoleTypeTable table = (RoleTypeTable) basicTable;
        int id = table.getId();
        List[] classData = formDataController.getRoleTypeStringData(id);
        controlPanelController.resetPanel(controlPane);
        createControlPanel();

        setClassData(classData);
        controlPanelController.setValueTextField(this, lineList, ParamType.Description, classData, classData[6], 5);


        button.setOnAction(event -> {

            saveClassData();
            ArrayList<Integer> descriptionIndicators = new ArrayList<>();
            ArrayList<String> description = controlPanelController.processTextLines(ParamType.Description, descriptionIndicators);
            editFormController.editDataFromRoleType(aliasTF.getText(), name, nameIndicators, description, descriptionIndicators, classListIndex, superClassListIndex,
                    classNameList, superClassNameList, table, controlPanelController.isExist(), id);
            clearPanel(tableView);
        });


    }
}
