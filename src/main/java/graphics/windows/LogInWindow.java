package graphics.windows;

import controllers.DatabaseController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import services.Constans;
import tables.ProjectTable;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Třídy tvorici prihlasovaci okno do Databaze
 *
 * @author Václav Janoch
 */
public class LogInWindow extends Stage {
    /**
     * Globální proměnné třídy
     */
    private String name;
    private String password;
    private boolean isLog = false;

    private BorderPane mainPanel;
    private GridPane centralPanel;
    private Scene scena;
    private Button submitBT;
    private Button projectBT;
    private DatabaseController databaseController;
    private TableView<ProjectTable> tableTV;


    /** Konstruktor tridy,
     * Zinicializuje globalni promenne tridy
     * @param databaseController instace tridy DatabaseController
     */
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
        centralPanel = new GridPane();
        centralPanel.setVgap(10);
        centralPanel.add(submitBT, 3, 4);

        mainPanel.setCenter(centralPanel);
        mainPanel.setAlignment(centralPanel, Pos.CENTER_RIGHT);

        return mainPanel;
    }

    /**
     * Metoda pro zobrazeni dialogoveho okna pro prihlaseni do databaze
     * @return informace o stavu prihlaseni
     */
    public boolean showLogDialog() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField("vasek");
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setText("janoch");
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            name = usernamePassword.getKey();
            this.password = usernamePassword.getValue();
            isLog = logInToDatabase();
            this.show();
        });

        return isLog;
    }


    /**
     * Metoda pro prihlaseni do Databaze
     * @return informaci o uspesnem ci neuspesnem prihlaseni
     */
    private boolean logInToDatabase() {
        if (databaseController.logIn(name, password)) {
            fillProjectTable();
            return true;
        }
        return false;
    }

    private void fillProjectTable() {
        ArrayList<ProjectTable> projectList = databaseController.findProjectInDatabase();
        if (projectList.size() != 0) {
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
        if (projectTable != null){
            int projectId = projectTable.getId();
            this.close();
            databaseController.confirmProjectWithModel(projectId);
        }

    }

    public TableView<ProjectTable> getTable() {
        tableTV = new TableView<ProjectTable>();

        TableColumn<ProjectTable, String> nameColumn = new TableColumn<ProjectTable, String>("Name");
        TableColumn<ProjectTable, String> id = new TableColumn<ProjectTable, String>("Id");

        nameColumn.setCellValueFactory(new PropertyValueFactory("alias"));
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


}
