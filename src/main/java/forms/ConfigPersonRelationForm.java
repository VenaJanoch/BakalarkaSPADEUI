package forms;

import Controllers.FormController;
import Controllers.FormDataController;
import SPADEPAC.ObjectFactory;
import abstractform.BasicForm;
import abstractform.TableBasicForm;
import interfaces.ISegmentTableForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import services.Alerts;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import services.SegmentType;
import tables.BasicTable;
import tables.CPRTable;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Třída představující tabulkový formulář pro element Configuration-Person-Relatio, odděděná od třídy
 * TableBasicForm a implementující ISegmentTableForm
 * 
 * @author Václav Janoch
 *
 */
public class ConfigPersonRelationForm extends TableBasicForm implements ISegmentTableForm {

	/**
	 * Globální proměnné třídy
	 */
	private Label personRoleLB;

	private ComboBox<BasicTable> personCB;

	private TableView<CPRTable> tableTV;

	private int roleIndex;
	private int configIndex;

	/**
	 * Konstruktor třídy
	 * Zinicializuje globální proměnné tříd
	 * Nastaví vlastnost pro tlačítko OK
	 *
	 */
	
	public ConfigPersonRelationForm(FormController formController, FormDataController formDataController, String name) {
		super(formController, formDataController, name);

		createForm();
		getSubmitButton().setOnAction(event -> setActionSubmitButton());

	}

	@Override
	public void setActionSubmitButton() {
		close();
	}

	@Override
	public void createForm() {

		getFormName().setText("Configuration Person Relation Form");

		getMainPanel().setCenter(getTable());
		getMainPanel().setBottom(createControlPane());

	}

	@Override
	public Node getTable() {
		tableTV = new TableView<CPRTable>();
		tableTV.setId("cprTable");
		TableColumn<CPRTable, String> nameColumn = new TableColumn<CPRTable, String>("Name");
		TableColumn<CPRTable, String> roleColumn = new TableColumn<CPRTable, String>("Role");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		roleColumn.setCellValueFactory(new PropertyValueFactory("role"));
		roleColumn.setMinWidth(150);
		roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, roleColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		tableTV.setOnKeyReleased(event -> deleteSelected(event));

		BorderPane.setMargin(tableTV, new Insets(5));

		return tableTV;
	}

	@Override
	public void deleteSelected(KeyEvent event) {
		ObservableList<CPRTable> selection = FXCollections
				.observableArrayList(tableTV.getSelectionModel().getSelectedItems());
		if (event.getCode() == KeyCode.DELETE) {
			if (selection.size() == 0) {
				Alerts.showNoItemsDeleteAlert();
			}
			else{
				ArrayList<BasicTable> list = new ArrayList<>(selection);
				formDataController.deleteCPR(list);
				tableTV.getItems().removeAll(selection);
				tableTV.getSelectionModel().clearSelection();

			}
		}

	}

	@Override
	public GridPane createControlPane() {

		personRoleLB = new Label("Person-Role: ");
		personCB = new ComboBox<BasicTable>(formController.getRoleObservable());
		personCB.setVisibleRowCount(5);
		personCB.getSelectionModel().selectedIndexProperty().addListener(roleListener);
		personCB.setId("personCB");
		getControlPane().add(personRoleLB, 4, 0);
		getControlPane().add(personCB, 5, 0);

		getControlPane().add(getAddBT(), 6, 0);

		getAddBT().setOnAction(event -> addItem());

		return getControlPane();
	}
	/**
	 * ChangeListener pro určení indexu prvku z comboBoxu pro Role
	 */
	ChangeListener<Number> roleListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

			roleIndex = newValue.intValue();

		}
	};


	@Override
	public void addItem() {

		BasicTable roleST = formController.getRoleObservable().get(roleIndex);
		String nameST = getNameTF().getText();
		int id = formController.createTableItem(SegmentType.ConfigPersonRelation);
		String idName = id + "_" + nameST;
		CPRTable cpr = new CPRTable(idName, roleST.getName(), id);
		tableTV.getItems().add(cpr);
		tableTV.sort();
		formDataController.saveDataFromCPR(nameST, idName,roleIndex, configIndex, id);
	}

	/** Getrs and Setrs ***/
	public TableView<CPRTable> getTableTV() {
		return tableTV;
	}

}
