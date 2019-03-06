package forms;

import controllers.FormController;
import SPADEPAC.WorkUnitTypeClass;
import SPADEPAC.WorkUnitTypeSuperClass;
import abstractform.TableClassBasicForm;
import controlPanels.ClassControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentTableForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import services.Alerts;;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Work Unit Type,
 * odděděná od třídy TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class TypeForm extends TableClassBasicForm implements ISegmentTableForm {
	/**
	 * Globální proměnné třídy
	 */

	private String[] classArray = new String[WorkUnitTypeClass.values().length];
	private String[] superClassArray = new String[WorkUnitTypeSuperClass.values().length];

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 *
	 */
	public TypeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.Type, formDataController, editFormController, formController);
		int i = 0;
		for(WorkUnitTypeClass classItem : WorkUnitTypeClass.values()){
			classArray[i] = classItem.name();
			i++;
		}
		i = 0;
		for(WorkUnitTypeSuperClass superClass : WorkUnitTypeSuperClass.values()){
			superClassArray[i] = superClass.name();
			i++;
		}

		editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

		setEventHandler();
		createForm();
		setActionSubmitButton();

	}

}
