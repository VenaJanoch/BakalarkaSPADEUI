package abstractform;

import controllers.formControllers.FormController;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import services.Constans;
import services.SegmentType;

/**
 * Třída umožnující zobrazení dvou formulářů v jednom okně
 *
 * @author Václav Janoch
 */

public abstract class Table2BasicForm extends BasicForm {
    /**
     * Globální proměnné třídy
     */
    protected BorderPane mainPanel;
    //private Scene scena;
    protected BorderPane internalPanel;
    private Button submitBT;
    private SplitPane splitPane;
    protected Button add;
    protected EventHandler<MouseEvent> OnMousePressedEventHandler;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public Table2BasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {

        super(formController, formDataController, editFormController, deleteFormController, type);
        //this.setScene(creatScene());
        this.setMinHeight(Constans.twoFormHeight);
        this.setMinWidth(Constans.twoFormWidth);
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

        internalPanel = new BorderPane();
        splitPane = new SplitPane();
        splitPane.setMinWidth(10);
        internalPanel.setRight(splitPane);

        mainPanel.setCenter(internalPanel);

        submitBT = new Button("OK");
        submitBT.setId("formSubmit");
        submitBT.setAlignment(Pos.BOTTOM_CENTER);

        mainPanel.setBottom(submitBT);
        mainPanel.setAlignment(submitBT, Pos.TOP_RIGHT);

        return mainPanel;
    }

    /**** Getrs and Setrs ***/

    public Button getSubmitBT() {
        return submitBT;
    }

    public BorderPane getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(BorderPane mainPanel) {
        this.mainPanel = mainPanel;
    }

}
