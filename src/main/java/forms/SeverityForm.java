package forms;

import controllers.formControllers.FormController;
import SPADEPAC.WorkUnitSeverityClass;
import SPADEPAC.WorkUnitSeveritySuperClass;
import abstractform.TableClassBasicForm;
import controlPanels.ClassControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import services.SegmentType;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Severity, odděděná od
 * třídy TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class SeverityForm extends TableClassBasicForm{

	private ArrayList<String> classArray = new ArrayList<>();
	private ArrayList<String> superClassArray = new ArrayList<>();

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 *
	 */
	public SeverityForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.Severity, formDataController, editFormController, formController);
		int i = 0;
		for(WorkUnitSeverityClass classItem : WorkUnitSeverityClass.values()){
			classArray.add(classItem.name());
			i++;
		}
		i = 0;
		for(WorkUnitSeveritySuperClass superClass : WorkUnitSeveritySuperClass.values()){
			superClassArray.add(superClass.name());
			i++;
		}

		editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

		setEventHandler();
		createForm();
		setActionSubmitButton();

	}
}
