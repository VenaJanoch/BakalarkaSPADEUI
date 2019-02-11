package abstractform;

import Controllers.FormController;
import Controllers.FormDataController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.*;
import model.IdentificatorCreater;

/**
 * Třída umoznující vytvoření tabulkového formuláře
 *
 * @author Václav Janoch
 */
public abstract class TableBasicForm extends BasicForm {

    /**
     * Globální proměnné třídy
     **/
    private BorderPane mainPanel;
    private Scene scena;
    protected Button add;
    private Button submitButton;
    private Label formName;
    protected EventHandler<MouseEvent> OnMousePressedEventHandler;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public TableBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        this.setScene(creatSceneProject());
    }

    /**
     * Vytvoří scénu pro formulář projektu
     *
     * @return Scene
     */
    private Scene creatSceneProject() {

        scena = new Scene(creatPanel(), Constans.formWidth, Constans.formHeight);

        return scena;
    }

    abstract protected void setEventHandler();


    @Override
    void createForm() {

    }

    /**
     * Vytvoří a rozloží základní prvky ve formuláři
     *
     * @return BorderPane
     */
    private Parent creatPanel() {

        mainPanel = new BorderPane();
        mainPanel.setPadding(new Insets(5));

        submitButton = new Button("OK");

        formName = new Label();
        formName.setAlignment(Pos.CENTER);
        formName.setFont(Font.font(25));
        formName.setId("formID");

        mainPanel.setAlignment(formName, Pos.CENTER);
        mainPanel.setRight(submitButton);
        mainPanel.setAlignment(submitButton, Pos.BOTTOM_CENTER);
        mainPanel.setTop(formName);

        return mainPanel;
    }

    /*** Getrs and Setrs ***/

    public BorderPane getMainPanel() {
        return mainPanel;
    }


    public Button getSubmitButton() {
        return submitButton;
    }


    public Label getFormName() {
        return formName;
    }

}
