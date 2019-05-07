package forms;

import controllers.formControllers.FormController;
import SPADEPAC.*;
import abstractform.TableClassBasicForm;
import controlPanels.ClassControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.SegmentType;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Relation, odděděná od třídy
 * TableClassBasicForm a implementující ISegmentTableForm
 *
 * @author Václav Janoch
 */
public class RelationForm extends TableClassBasicForm {
    private ArrayList<String> classArray = new ArrayList<>();
    private ArrayList<String> superClassArray = new ArrayList<>();

    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     * Nastaví reakci na potvrzovací tlačítko
     */
    public RelationForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        //	this.setTitle("Edit Relations");

        editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.Relation, formDataController, editFormController, formController);
        int i = 0;
        for (WorkUnitRelationClass classItem : WorkUnitRelationClass.values()) {
            classArray.add(classItem.name());
            i++;
        }
        i = 0;
        for (WorkUnitResolutionsSuperClass superClass : WorkUnitResolutionsSuperClass.values()) {
            superClassArray.add(superClass.name());
            i++;
        }

        editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

        setEventHandler();
        createForm();
        setActionSubmitButton();

    }

}
