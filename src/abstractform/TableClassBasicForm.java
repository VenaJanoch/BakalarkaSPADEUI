package abstractform;

import services.ClassSwitcher;
import services.Control;
import services.DeleteControl;
import tables.ClassTable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;

public class TableClassBasicForm extends TableBasicForm {

	private Label classLB;
	private Label superLB;
	private TableView<ClassTable> tableTV;

	private ClassSwitcher switcher;
	private Control control;
	
	
	protected int classIndex;
	protected int superIndex;

	public TableClassBasicForm(Control control, DeleteControl deleteControl) {

		super(control, deleteControl);
		this.control = control;
		this.deleteControl = deleteControl;
		setSwitcher(new ClassSwitcher(control));
		classIndex = 0;
		setSuperIndex(0);
		createforms();

	}

	public void createforms() {

		setClassLB(new Label("Class: "));
		setSuperLB(new Label("Super"));
	
		tableTV = new TableView<ClassTable>();
		tableTV.setId("classTable");
		TableColumn<ClassTable, String> nameColumn = new TableColumn<ClassTable, String>("Name");
		TableColumn<ClassTable, String> classColumn = new TableColumn<ClassTable, String>("Class");
		TableColumn<ClassTable, String> superColumn = new TableColumn<ClassTable, String>("SuperClass");

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
