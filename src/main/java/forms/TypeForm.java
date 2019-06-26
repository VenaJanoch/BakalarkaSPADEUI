package forms;

import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
import abstractform.TableClassBasicForm;
import controlPanels.ClassControlPanel;
import controllers.formControllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import services.SegmentType;

import java.util.ArrayList;

;

/**
 * Třída představující tabulkový formulář pro výčtový typ Work Unit Type,
 * odděděná od třídy TableClassBasicForm
 *
 * @author Václav Janoch
 */
public class TypeForm extends TableClassBasicForm implements ISegmentTableForm {
    /**
     * Globální proměnné třídy
     */

    private ArrayList<String> classArray = new ArrayList<>();
    private ArrayList<String> superClassArray = new ArrayList<>();

    /**
     * Konstruktor Třídy Zinicializuje globální proměnné tříd Nastaví reakci
     * na klik do tabulky, naplni panel a nastavi akce tlacitkum
     *
     * @param formController       instance tridy FormController
     * @param formDataController   instance tridy FormDataController
     * @param editFormController   instance tridy EditFormController
     * @param deleteFormController instace tridy DeleteFormController
     * @param type                 instace SegmentType pro urceni typu formulare
     */
    public TypeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.Type, formDataController, editFormController, formController);
        int i = 0;
        for (WorkUnitTypeClass classItem : WorkUnitTypeClass.values()) {
            classArray.add(classItem.name());
            i++;
        }
        i = 0;
        for (WorkUnitTypeSuperClass superClass : WorkUnitTypeSuperClass.values()) {
            superClassArray.add(superClass.name());
            i++;
        }

        editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

        setEventHandler();
        createForm();
        setActionSubmitButton();

    }

}
