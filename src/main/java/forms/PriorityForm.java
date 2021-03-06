package forms;

import SPADEPAC.WorkUnitPriorityClass;
import SPADEPAC.WorkUnitPrioritySuperClass;
import abstractform.TableClassBasicForm;
import controlPanels.ClassControlPanel;
import controllers.formControllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.SegmentType;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Priority, odděděná od třídy
 * TableClassBasicForm
 *
 * @author Václav Janoch
 */
public class PriorityForm extends TableClassBasicForm {

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
    public PriorityForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.Priority, formDataController, editFormController, formController);
        int i = 0;
        for (WorkUnitPriorityClass classItem : WorkUnitPriorityClass.values()) {
            classArray.add(classItem.name());
            i++;
        }
        i = 0;
        for (WorkUnitPrioritySuperClass superClass : WorkUnitPrioritySuperClass.values()) {
            superClassArray.add(superClass.name());
            i++;
        }

        editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

        setEventHandler();
        createForm();
        setActionSubmitButton();

    }

}
