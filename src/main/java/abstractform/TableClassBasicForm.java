package abstractform;

import controllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import services.SegmentType;
import tables.ClassTable;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

/**
 * Třída sloužící k vytvoření tabulkového formuláře výčtových typů. Odděděná od
 * třídy TableBasicForm
 * 
 * @author Václav Janoch
 *
 */
public abstract class TableClassBasicForm extends TableBasicForm {
	/**
	 * Globální proměnné třídy
	 */

	protected TableView<ClassTable> tableTV;
	protected EventHandler<MouseEvent> OnMousePressedEventHandler;


	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 */
	public TableClassBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {

		super(formController, formDataController, editFormController, deleteFormController, type);
		createforms();

	}

	abstract protected void setEventHandler();


	/**
	 * Vytvoří TableView pro typ ClassTable
	 * 
	 */
	public void createforms() {

		tableTV = new TableView<ClassTable>();
		tableTV.setId("classTable");
		TableColumn<ClassTable, String> nameColumn = new TableColumn<ClassTable, String>("Name");
		TableColumn<ClassTable, String> classColumn = new TableColumn<ClassTable, String>("Class");
		TableColumn<ClassTable, String> superColumn = new TableColumn<ClassTable, String>("Super Class");

		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		classColumn.setCellValueFactory(new PropertyValueFactory("classType"));
		classColumn.setMinWidth(150);
		classColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		superColumn.setCellValueFactory(new PropertyValueFactory("superType"));
		superColumn.setMinWidth(150);
		superColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		tableTV.getColumns().addAll(nameColumn, classColumn, superColumn);

		tableTV.setEditable(false);

		tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		BorderPane.setMargin(tableTV, new Insets(5));
	}

	public TableView<ClassTable> getTableTV() {
		return tableTV;
	}
}
