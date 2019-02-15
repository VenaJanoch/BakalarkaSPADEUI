package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class ControlPanel extends Stage {

    protected GridPane controlPane;
    protected Button button;

    protected IFormDataController formDataController;
    protected IEditFormController editFormController;
    protected FormController formController;
    protected BorderPane mainPanel;


    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        this(buttonText, formDataController, editFormController);
        this.formController = formController;

    }

    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController){
        super();
        this.formDataController = formDataController;
        this.editFormController = editFormController;
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
