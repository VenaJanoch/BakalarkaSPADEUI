package abstractControlPane;

import Controllers.FormController;
import Controllers.FormDataController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tables.BasicTable;
import tables.CriterionTable;

public abstract class ControlPanel extends Stage {

    protected GridPane controlPane;
    protected Button button;

    protected FormDataController formDataController;
    protected FormController formController;
    protected BorderPane mainPanel;


    public ControlPanel(String buttonText, FormDataController formDataController, FormController formController){
        this(buttonText, formDataController);
        this.formController = formController;
    }

    public ControlPanel(String buttonText, FormDataController formDataController){
        super();
        this.formDataController = formDataController;
        createMainPanel(buttonText);
    }

    private void createMainPanel(String buttonText){

        mainPanel = new BorderPane();
        button = new Button(buttonText);
        button.setPrefWidth(60);
        button.setPrefHeight(60);

        controlPane = new GridPane();
        controlPane.setVgap(5);

        controlPane.setHgap(3);
        controlPane.setVgap(3);

        controlPane.setAlignment(Pos.CENTER);
        controlPane.setPadding(new Insets(5));

    }

    abstract protected void createBasicPanel();

    public Button getButton() {
        return button;
    }
}
