package abstractform;

import controllers.formControllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import services.*;
import tables.BasicTable;

import java.util.ArrayList;

/**
 * Třída umoznující vytvoření tabulkového formuláře
 *
 * @author Václav Janoch
 */
public abstract class TableBasicForm extends BasicForm {

    /**
     * Globální proměnné třídy
     **/
   // private BorderPane mainPanel;
   // private Scene scena;
    protected Button addButton;
    protected Button removeButton;
    protected Button editButton;

    protected EventHandler<MouseEvent> OnMousePressedEventHandler;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public TableBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        this.setBackground(new Background(new BackgroundFill(Constans.lg1, CornerRadii.EMPTY, Insets.EMPTY)));

        createPanel();
    }


    abstract protected void setEventHandler();


    @Override
    void createForm() {

    }

    public void deleteItem(TableView tableTV){
        ObservableList selection = FXCollections
                .observableArrayList(tableTV.getSelectionModel().getSelectedItems());

        if (selection.size() == 0) {
            Alerts.showNoItemsDeleteAlert();
        }
        else{
            ArrayList<BasicTable> list = new ArrayList<>(selection);
            deleteFormController.deleteItemWithDialog(list, tableTV, getSegmentType());
        }
    }



    /**
     * Vytvoří a rozloží základní prvky ve formuláři
     *
     * @return BorderPane
     */
    private Parent createPanel() {

        this.setPadding(new Insets(5));

        GridPane buttonPanel = new GridPane();

        buttonPanel.setMinHeight(100);
        addButton = new Button("+");
        addButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        addButton.setBackground(new Background(new BackgroundFill(Color.rgb(129,129, 129), CornerRadii.EMPTY, Insets.EMPTY)));
        addButton.setTextFill(Color.WHITE);
        addButton.setStyle("-fx-font-size: 30px;");
        removeButton = new Button("-");
        removeButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        removeButton.setBackground(new Background(new BackgroundFill(Color.rgb(129,129, 129), CornerRadii.EMPTY, Insets.EMPTY)));
        removeButton.setTextFill(Color.WHITE);
        removeButton.setStyle("-fx-font-size: 30px;");
        editButton = new Button("Edit");
        editButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        editButton.setBackground(new Background(new BackgroundFill(Color.rgb(0,163, 211), CornerRadii.EMPTY, Insets.EMPTY)));
        editButton.setTextFill(Color.WHITE);
        editButton.setStyle("-fx-font-size: 20px;");

        buttonPanel.add(addButton, 0,0);
        buttonPanel.add(removeButton, 1,0);
        buttonPanel.add(editButton, 15,0);
        //buttonPanel.setAlignment(Pos.);
        buttonPanel.setHgap(15);
        GridPane.setMargin(addButton, new Insets(0,0,0,15));
        this.setBottom(buttonPanel);
        return this;
    }

    /*** Getrs and Setrs ***/


    public Button getSubmitButton() {
        return submitButton;
    }


    public Label getFormName() {
        return formName;
    }

}
