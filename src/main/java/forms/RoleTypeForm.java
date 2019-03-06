package forms;

import controllers.FormController;
import SPADEPAC.RoleClass;
import SPADEPAC.RoleSuperClass;
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
import services.Alerts;
import services.SegmentType;
import tables.BasicTable;
import tables.ClassTable;

import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro výčtový typ Role-Type, odděděná od
 * třídy TableClassBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class RoleTypeForm extends TableClassBasicForm{

	private String[] classArray = new String[RoleClass.values().length];
	private String[] superClassArray = new String[RoleSuperClass.values().length];


	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 *
	 */
	public RoleTypeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		editClassControlPanelTCB = new ClassControlPanel("Edit", SegmentType.RoleType, formDataController, editFormController, formController);
		int i = 0;
		for(RoleClass roleClass : RoleClass.values()){
			classArray[i] = roleClass.name();
			i++;
		}
		i = 0;
		for(RoleSuperClass roleSuperClass : RoleSuperClass.values()){
			superClassArray[i] = roleSuperClass.name();
			i++;
		}

		editClassControlPanelTCB.createControlPanel(classArray, superClassArray);

		setEventHandler();
		createForm();
		setActionSubmitButton();

	}

}
