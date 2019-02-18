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
public class RoleTypeForm extends TableClassBasicForm implements ISegmentTableForm {

	private ClassControlPanel classControlPanel;
	private ClassControlPanel editClassControlPanel;
	private String[] classArray = new String[RoleClass.values().length];
	private String[] superClassArray = new String[RoleSuperClass.values().length];


	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví reakci na
	 * potvrzovací tlačítko
	 *
	 */
	public RoleTypeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
		super(formController, formDataController, editFormController, deleteFormController, type);

		classControlPanel = new ClassControlPanel("Add", SegmentType.RoleType, formDataController, editFormController, formController);
		editClassControlPanel = new ClassControlPanel("Edit", SegmentType.RoleType, formDataController, editFormController, formController);
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

		editClassControlPanel.createControlPanel(classArray, superClassArray);

		setEventHandler();
		createForm();
		getSubmitButton().setVisible(false);

	}

	protected void setEventHandler(){
		OnMousePressedEventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if(t.getClickCount() == 2) {
					ClassTable classTable = tableTV.getSelectionModel().getSelectedItems().get(0);
					if (classTable != null) {
						editClassControlPanel.showEditControlPanel(classTable, SegmentType.RoleType, tableTV);
					}
				}
			}
		};
	}


	@Override
	public void createForm() {

		getFormName().setText("Role Classification Type Form");

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	@Override
	public Node getTable() {
		tableTV.setOnKeyReleased(event -> deleteSelected(event));
		tableTV.setOnMousePressed(OnMousePressedEventHandler);
		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<ClassTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());

		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				deleteFormController.deleteRoleTypeWithDialog(list, tableTV);
				}
		}

	}

	@Override
	public GridPane createControlPane() {

		GridPane controlPane = classControlPanel.createControlPanel(classArray, superClassArray);

		add = classControlPanel.getButton();
		add.setOnAction(event -> addItem());

		return controlPane;
	}


	@Override
	public void addItem() {
		String nameST =  classControlPanel.getName();

		int id = formController.createTableItem(SegmentType.RoleType);
		String idName = id + "_" + nameST;

		String classST = classControlPanel.getClassName();
		String superST = classControlPanel.getSuperClassName();

		ClassTable type = new ClassTable(idName, classST, superST, id);

		tableTV.getItems().add(type);
		tableTV.sort();
		formDataController.saveDataFromRoleTypeForm(nameST, type);
		classControlPanel.clearPanel(tableTV);
	}

	@Override
	public void setActionSubmitButton() {
		close();

	}

}
