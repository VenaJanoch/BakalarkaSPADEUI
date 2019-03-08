package abstractControlPane;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Constans;

public abstract class ControlPanel extends BorderPane {

    protected GridPane controlPane;
    protected Button button;

    protected IFormDataController formDataController;
    protected IEditFormController editFormController;
    protected FormController formController;


    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        this(buttonText, formDataController, editFormController);
        this.formController = formController;

    }

    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController){
        super();
        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.setWidth(Constans.rightDrawerWidth);
        createMainPanel(buttonText);
    }

    private void createMainPanel(String buttonText){


        button = new Button(buttonText);
        button.setPrefWidth(60);
        button.setPrefHeight(60);

        controlPane = new GridPane();
        controlPane.setVgap(5);

        controlPane.setHgap(3);
        controlPane.setVgap(3);
        controlPane.setHalignment(button, HPos.RIGHT);
        controlPane.setAlignment(Pos.CENTER);
        controlPane.setPadding(new Insets(5));
        this.setCenter(controlPane);
    }

    abstract protected void createBasicPanel();

    public Button getButton() {
        return button;
    }

    public GridPane getControlPane() {
        return controlPane;
    }
}
