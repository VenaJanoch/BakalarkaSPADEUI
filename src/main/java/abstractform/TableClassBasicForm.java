package abstractform;

import Controllers.FormController;
import Controllers.FormDataController;
import services.ClassSwitcher;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import services.SegmentType;
import tables.ClassTable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
public class TableClassBasicForm extends TableBasicForm {
	/**
	 * Globální proměnné třídy
	 */
	protected Label classLB;
	protected Label superLB;
	private TableView<ClassTable> tableTV;

	private ClassSwitcher switcher;
	private Control control;

	protected int classIndex;
	protected int superIndex;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 */
	public TableClassBasicForm(FormController formController, FormDataController formDataController, SegmentType type) {

		super(formController,formDataController, type);
		setSwitcher(new ClassSwitcher(control));
		classIndex = 0;
		setSuperIndex(0);
		createforms();

	}
	/**
	 * Vytvoří TableView pro typ ClassTable
	 * 
	 */
	public void createforms() {

		setClassLB(new Label("Class: "));
		setSuperLB(new Label("Super Class: "));

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

	/**
	 * Getrs and Setrs
	 */
	public ClassSwitcher getSwitcher() {
		return switcher;
	}

	public void setSwitcher(ClassSwitcher switcher) {
		this.switcher = switcher;
	}

	public Label getClassLB() {
		return classLB;
	}

	public void setClassLB(Label classLB) {
		this.classLB = classLB;
	}

	public Label getSuperLB() {
		return superLB;
	}

	public void setSuperLB(Label superLB) {
		this.superLB = superLB;
	}

	public TableView<ClassTable> getTableTV() {
		return tableTV;
	}

	public void setTableTV(TableView<ClassTable> tableTV) {
		this.tableTV = tableTV;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public int getSuperIndex() {
		return superIndex;
	}

	public void setSuperIndex(int superIndex) {
		this.superIndex = superIndex;
	}

}
