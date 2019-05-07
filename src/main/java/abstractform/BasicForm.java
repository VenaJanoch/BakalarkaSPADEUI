package abstractform;

import abstractControlPane.ControlPanel;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.formControllers.FormController;
import graphics.canvas.DragAndDropCanvas;
import graphics.panels.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import services.Constans;
import services.SegmentType;

/**
 * Absraktní třídy pro formuláře volané z kreslícího plátna
 *
 * @author Václav Janoch
 */
public abstract class BasicForm extends BorderPane {

    /**
     * Globální proměnné třídy
     */

    private Label nameLB;
    protected Label formName;
    protected TextField nameTF;
    protected Button submitButton;

    private GridPane infoPart;
    private HBox buttonBox;

    private DragAndDropItemPanel dgItem;
    private DragAndDropCanvas canvas;
    private BorderPane dragBox;

    private SegmentType segmentType;

    protected boolean isSave;
    protected int indexForm;

    protected FormController formController;
    protected IFormDataController formDataController;
    protected IEditFormController editFormController;
    protected IDeleteFormController deleteFormController;

    protected CanvasController canvasController;
    protected ControlPanel controlPanel;

    /**
     * Konstruktor třídy pro formuláře s vlastním plátnem Zinicializuje globální
     * proměnné třídy
     */
    public BasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController,
                     CanvasController canvasController, DragAndDropItemPanel dgItem, SegmentType type) {
        this(formController, formDataController, editFormController, deleteFormController, canvasController, type);
        this.formName.setText(type.name() + " Form");
        this.dgItem = dgItem;
        this.dragBox = new BorderPane();
        createPanelCanvas();

    }

    public BasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, SegmentType type) {
        this(formController, formDataController, editFormController, deleteFormController, type);
        this.canvasController = canvasController;
        this.canvas = canvasController.getCanvas();
    }

    /**
     * Konstruktor třídy pro prvky bez plátna
     */

    public BasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController,
                     IDeleteFormController deleteFormController, SegmentType type) {
        super();

        this.formController = formController;
        this.formDataController = formDataController;
        this.editFormController = editFormController;
        this.deleteFormController = deleteFormController;
        this.segmentType = type;
        creatPanelProject();
        this.setMinHeight(Constans.formHeight);
        this.setMinWidth(Constans.formWidth);

    }

    /**
     * Metoda pro pridani prvku do formulare
     */
    abstract void createForm();


    /**
     * Vytvoří rozložení prvků pro plátno ve formuláři
     *
     * @return BorderPane
     */
    private void createPanelCanvas() {
        creatPanelProject();
        dragBox.setTop(dgItem);
        dragBox.setCenter(canvas);

        this.setCenter(dragBox);
        this.setLeft(infoPart);

    }

    /**
     * Vytvoří a rozloží základní prvky ve formuláři
     *
     * @return BorderPane
     */
    private void creatPanelProject() {

        this.setPadding(new Insets(5));
        buttonBox = new HBox(5);
        this.setMinSize(Constans.formWidth, Constans.formHeight);
        infoPart = new GridPane();
        infoPart.setAlignment(Pos.CENTER);
        infoPart.setVgap(10);
        infoPart.setHgap(10);
        nameLB = new Label("Name: ");
        nameTF = new TextField();
        nameTF.setId("formName");

        submitButton = new Button("OK");
        submitButton.setId("formSubmit");
        nameLB.setAlignment(Pos.CENTER_RIGHT);

        formName = new Label("Form " + segmentType.name());
        formName.setAlignment(Pos.CENTER);
        formName.setFont(Font.font(25));
        formName.setId("formID");
        this.setAlignment(formName, Pos.CENTER);
        this.setTop(formName);

        HBox nameBox = new HBox(5);
        nameBox.getChildren().addAll(nameLB, nameTF);

        buttonBox.getChildren().add(submitButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        infoPart.setPadding(new Insets(5));
        infoPart.add(nameLB, 0, 0);
        infoPart.add(nameTF, 1, 0);
        infoPart.setHalignment(nameLB, HPos.RIGHT);

        this.setCenter(infoPart);
        this.setBottom(buttonBox);
    }

    /**
     * Getrs and Setrs
     **/

    public boolean isSave() {
        return isSave;
    }

    public Label getFormName() {
        return formName;
    }

    public TextField getNameTF() {
        return nameTF;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public GridPane getInfoPart() {
        return infoPart;
    }

    public Label getNameLB() {
        return nameLB;
    }

    public CanvasController getCanvasController() {
        return canvasController;
    }

    public SegmentType getSegmentType() {
        return segmentType;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }
}
