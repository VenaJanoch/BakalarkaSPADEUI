package abstractControlPane;

import controllers.ControlPanelController;
import controllers.FormController;
import graphics.controlPanelItems.ControlPanelLine;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import services.Constans;
import services.ControlPanelLineObject;

import java.util.ArrayList;

public abstract class ControlPanel extends ScrollPane {

    protected GridPane controlPane;
    protected Button button;
    protected ObservableList<ControlPanelLineObject> lineList;
    protected ArrayList<ControlPanelLine> controlPanelLines;

    protected IFormDataController formDataController;
    protected IEditFormController editFormController;
    protected FormController formController;

    protected ControlPanelController controlPanelController;


    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController){
        this(buttonText, formDataController, editFormController);
        this.formController = formController;
    }

    public ControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController){
        super();
        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.controlPanelLines = new ArrayList<ControlPanelLine>();
        this.controlPanelController = new ControlPanelController(controlPanelLines);
        this.lineList = FXCollections.observableArrayList();
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

        controlPane.setBackground(new Background(new BackgroundFill(Constans.lg1, CornerRadii.EMPTY, Insets.EMPTY)));


        this.getChildren().add(controlPane);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    abstract protected void createBasicPanel();

    public Button getButton() {
        return button;
    }

    public GridPane getControlPane() {
        return controlPane;
    }
}
