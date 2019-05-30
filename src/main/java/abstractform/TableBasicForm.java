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

    protected Button addButton;
    protected Button removeButton;
    protected Button editButton;
    protected Button copyButton;

    protected EventHandler<MouseEvent> OnMousePressedEventHandler;

    /**
     * Konstruktor tridy zavola nadrazenou tridu BasicForm a zinicializuje ostatni globalni promenne
     * Zavola metodu pro pridani potrebny prvku do panelu
     * @param formController
     * @param formDataController
     * @param editFormController
     * @param deleteFormController
     * @param type
     */
    public TableBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        this.setBackground(new Background(new BackgroundFill(Constans.lg1, CornerRadii.EMPTY, Insets.EMPTY)));

        createPanel();
    }

    /**
     * Metoda nastavi event handler pro tabulku
     * Pokud je na radek dvakrat kliknuto mysi
     * zavola se metoda pro zobrazeni panelu a nastaveni
     * prislusneho formulare do panelu
     */
    abstract protected void setEventHandler();


    /**
     * Metoda pro smazani prvku z tabulky potazmo z datovych struktur
     * Metoda vyvola informaci okno pro radky, ktere se maji smazat
     * Pri potvrzeni akce je zavolana metoda deleteItemWithDialog ze tridy deleteFormController
     * @param tableTV instance tridy TableView, ze ktere se budou polozky mazat
     */
    public void deleteItem(TableView tableTV) {
        ObservableList selection = FXCollections
                .observableArrayList(tableTV.getSelectionModel().getSelectedItems());

        if (selection.size() == 0) {
            Alerts.showNoItemsDeleteAlert();
        } else {
            ArrayList<BasicTable> list = new ArrayList<>(selection);
            deleteFormController.deleteItemWithDialog(list, tableTV, getSegmentType());
        }
    }

    /**
     * Metoda pro duplikaci prvku z tabulce
     * @param tableTV instance tridy TableView, ze ktere se budou polozky mazat
     */
    public void copyItem(TableView tableTV) {
        ObservableList selection = FXCollections
                .observableArrayList(tableTV.getSelectionModel().getSelectedItems());

            ArrayList<BasicTable> list = new ArrayList<>(selection);
            formDataController.createCopyTableItem(list, tableTV, getSegmentType());
    }

    /**
     * Vytvoří a rozloží základní prvky ve formuláři
     */
    private Parent createPanel() {

        this.setPadding(new Insets(5));

        GridPane buttonPanel = new GridPane();

        buttonPanel.setMinHeight(100);
        addButton = new Button("+");
        addButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        addButton.setBackground(new Background(new BackgroundFill(Color.rgb(129, 129, 129), CornerRadii.EMPTY, Insets.EMPTY)));
        addButton.setTextFill(Color.WHITE);
        addButton.setStyle("-fx-font-size: 30px;");
        removeButton = new Button("-");
        removeButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        removeButton.setBackground(new Background(new BackgroundFill(Color.rgb(129, 129, 129), CornerRadii.EMPTY, Insets.EMPTY)));
        removeButton.setTextFill(Color.WHITE);
        removeButton.setStyle("-fx-font-size: 30px;");
        editButton = new Button("Edit");
        editButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        editButton.setBackground(new Background(new BackgroundFill(Color.rgb(0, 163, 211), CornerRadii.EMPTY, Insets.EMPTY)));
        editButton.setTextFill(Color.WHITE);
        editButton.setStyle("-fx-font-size: 20px;");
        copyButton = new Button("D");
        copyButton.setMinSize(Constans.tableControlButtonWidth, Constans.tableControlButtonHeight);
        copyButton.setBackground(new Background(new BackgroundFill(Color.rgb(129, 129, 129), CornerRadii.EMPTY, Insets.EMPTY)));
        copyButton.setTextFill(Color.WHITE);
        copyButton.setStyle("-fx-font-size: 20px;");

        buttonPanel.add(addButton, 0, 0);
        buttonPanel.add(removeButton, 1, 0);
        buttonPanel.add(copyButton, 2, 0);
        buttonPanel.add(editButton, 15, 0);
        buttonPanel.setHgap(15);
        GridPane.setMargin(addButton, new Insets(0, 0, 0, 15));
        this.setBottom(buttonPanel);
        return this;
    }

}
