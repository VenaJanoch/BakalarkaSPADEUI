package graphics;

import controllers.VerifyController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Constans;
import tables.VerifyTable;

import java.util.ArrayList;

public class VerifyWindow extends Stage {
    /**
     * Globální proměnné třídy
     */
    private BorderPane mainPanel;
    private GridPane centralPanel;
    private Scene scena;
    private Button submitBT;
    private VerifyController verifyController;
    private TableView<VerifyTable> tableTV;



    public VerifyWindow(VerifyController verifyController) {
        this.verifyController = verifyController;
        tableTV = getTable();
        this.setScene(creatScene());
    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private Scene creatScene() {

        scena = new Scene(creatPanel(), Constans.PROJECT_TABLE_WIDTH, Constans.PROJECT_TABLE_HEIGHT);

        return scena;
    }

    /**
     * Vytvoří a rozloží základní prvky ve formuláři
     *
     * @return BorderPane
     */
    private Parent creatPanel() {

        mainPanel = new BorderPane();
        mainPanel.setPadding(new Insets(5));

        mainPanel.setCenter(tableTV);

        return mainPanel;
    }

    public TableView<VerifyTable> getTable() {
        tableTV = new TableView<VerifyTable>();

        TableColumn<VerifyTable, String> elementNameColumn = new TableColumn<VerifyTable, String>("Model element");
        TableColumn<VerifyTable, String> exist = new TableColumn<VerifyTable, String>("Exist");
        TableColumn<VerifyTable, String> isProjectExistColumn = new TableColumn<VerifyTable, String>("Exist in Project");
        TableColumn<VerifyTable, String> result = new TableColumn<VerifyTable, String>("Result");
        TableColumn<VerifyTable, String> sql = new TableColumn<VerifyTable, String>("SQL Command");
       
        elementNameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        elementNameColumn.setMinWidth(100);
        elementNameColumn.setMaxWidth(100);
        elementNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        exist.setCellValueFactory(new PropertyValueFactory("existString"));
        exist.setMinWidth(50);
        exist.setMaxWidth(50);
        exist.setCellFactory(TextFieldTableCell.forTableColumn());

        isProjectExistColumn.setCellValueFactory(new PropertyValueFactory("isExistInProject"));
        isProjectExistColumn.setMinWidth(100);
        isProjectExistColumn.setMaxWidth(100);
        isProjectExistColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        result.setCellValueFactory(new PropertyValueFactory("result"));
        result.setMinWidth(50);
        result.setMaxWidth(50);
        result.setCellFactory(TextFieldTableCell.forTableColumn());
        result.setEditable(false);
        sql.setCellValueFactory(new PropertyValueFactory("sql"));
        sql.setMinWidth(300);
        sql.setResizable(true);
        sql.setCellFactory(param ->  {
                final TableCell<VerifyTable, String> cell = new TableCell<VerifyTable, String>() {
                    private Text text;
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            text = new Text(item.toString());
                            text.setWrappingWidth(200); // Setting the wrapping width to the Text
                            setGraphic(text);
                        }
                    }
                };
                return cell;

        });
        sql.setEditable(true);



        tableTV.getColumns().addAll(elementNameColumn, exist, isProjectExistColumn, result, sql);
        //tableTV.setOnMousePressed(OnMousePressedEventHandler);
        tableTV.setEditable(true);

        tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BorderPane.setMargin(tableTV, new Insets(5));

        return tableTV;
    }


    public void setItemsToTable(ArrayList<VerifyTable> verifyTables) {
        tableTV.getItems().clear();
        tableTV.getItems().addAll(verifyTables);
      //  tableTV.getItems().addAll(new VerifyTable("Name", 3, true, "Yes", "Yes", "Nevim"));
    }
}
