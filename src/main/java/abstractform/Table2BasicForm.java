package abstractform;

import Controllers.FormController;
import Controllers.FormDataController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
    private Scene scena;
    protected BorderPane internalPanel;
    private Button submitBT;
    private SplitPane splitPane;
    protected Button add;
    protected EventHandler<MouseEvent> OnMousePressedEventHandler;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public Table2BasicForm(FormController formController, FormDataController formDataController, SegmentType type) {

        super(formController, formDataController, type);
        this.setScene(creatScene());
        this.setMinHeight(Constans.twoFormHeight);
        this.setMinWidth(Constans.twoFormWidth);
    }

    abstract protected void setEventHandler();

    @Override
    void createForm() {

    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private Scene creatScene() {

        scena = new Scene(creatPanel(), Constans.twoFormWidth, Constans.twoFormHeight);

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

    public void setSubmitBT(Button submitBT) {
        this.submitBT = submitBT;
    }

    public BorderPane getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(BorderPane mainPanel) {
        this.mainPanel = mainPanel;
    }

    public BorderPane getInternalPanel() {
        return internalPanel;
    }

    public void setInternalPanel(BorderPane internalPanel) {
        this.internalPanel = internalPanel;
    }


}
