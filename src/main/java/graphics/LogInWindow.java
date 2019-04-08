package graphics;

import controllers.DatabaseController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Constans;
import tables.ProjectTable;

import java.util.ArrayList;

public class LogInWindow extends Stage {
    /**
     * Globální proměnné třídy
     */
    private Label nameLB;
    private TextField nameTF;
    private Label passwordLB;
    private PasswordField passwordField;
    private BorderPane mainPanel;
    private GridPane centralPanel;
    private Scene scena;
    private Button submitBT;
    private Button projectBT;
    private DatabaseController databaseController;
    private TableView<ProjectTable> tableTV;



    public LogInWindow(DatabaseController databaseController) {
        this.databaseController = databaseController;
        tableTV = getTable();
        projectBT = new Button("OK");
        this.setScene(creatScene());
    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private Scene creatScene() {

        scena = new Scene(creatPanel(), Constans.PROJECT_CHOOSE_WINDOW_WIDTH, Constans.PROJECT_CHOOSE_WINDOW_HEIGHT);

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

        submitBT = new Button("OK");
        submitBT.setId("formSubmit");
        submitBT.setAlignment(Pos.BOTTOM_CENTER);

        nameLB = new Label("Name: ");
        nameTF = new TextField();
        nameTF.setId("formName");
        submitBT = new Button("Connect");
        submitBT.setOnAction(event -> logInToDatabase() );
        passwordLB = new Label("Password: ");
        passwordField = new PasswordField();
        nameTF.setText("vasek");
        passwordField.setText("janoch");
        centralPanel = new GridPane();
        centralPanel.setVgap(10);
        centralPanel.add(nameLB,1,1);
        centralPanel.add(nameTF,2,1);
        centralPanel.add(passwordLB,1,2);
        centralPanel.add(passwordField,2,2);
        centralPanel.add(submitBT, 3, 4);

        mainPanel.setCenter(centralPanel);
        mainPanel.setAlignment(centralPanel, Pos.CENTER_RIGHT);

        return mainPanel;
    }

    private void logInToDatabase(){
      if(databaseController.logIn(nameTF.getText(),passwordField.getText())){
          fillProjectTable();
      }
    }

    private void fillProjectTable(){
        ArrayList<ProjectTable> projectList = databaseController.findProjectInDatabase();
        if (projectList.size() != 0){
            tableTV.getItems().addAll(projectList);
            projectBT.setOnAction(event -> chooseProject());
            mainPanel.setCenter(tableTV);
            mainPanel.setBottom(projectBT);
            this.setWidth(Constans.PROJECT_TABLE_WIDTH);
            this.setHeight(Constans.PROJECT_TABLE_HEIGHT);
        }

    }

    private void chooseProject() {
        ProjectTable projectTable = tableTV.getSelectionModel().getSelectedItem();
        int projectId = projectTable.getId();
        this.close();
        databaseController.confirmProjectWithModel(projectId);

    }

    public TableView<ProjectTable> getTable() {
        tableTV = new TableView<ProjectTable>();

        TableColumn<ProjectTable, String> nameColumn = new TableColumn<ProjectTable, String>("Name");
        TableColumn<ProjectTable, String> id = new TableColumn<ProjectTable, String>("Id");

        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        nameColumn.setMinWidth(300);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        id.setCellValueFactory(new PropertyValueFactory("idString"));
        id.setMinWidth(50);
        id.setCellFactory(TextFieldTableCell.forTableColumn());


        tableTV.getColumns().addAll(nameColumn, id);
        //tableTV.setOnMousePressed(OnMousePressedEventHandler);
        tableTV.setEditable(false);

        tableTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableTV.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        BorderPane.setMargin(tableTV, new Insets(5));

        return tableTV;
    }

    /**** Getrs and Setrs ***/

    public TextField getNameTF() {
        return nameTF;
    }

    public Button getSubmitBT() {
        return submitBT;
    }

    public void setSubmitBT(Button submitBT) {
        this.submitBT = submitBT;
    }

    public BorderPane getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(BorderPane mainPanel) {
        this.mainPanel = mainPanel;
    }

    public Label getNameLB() {
        return nameLB;
    }

    public void setNameLB(Label nameLB) {
        this.nameLB = nameLB;
    }



}
