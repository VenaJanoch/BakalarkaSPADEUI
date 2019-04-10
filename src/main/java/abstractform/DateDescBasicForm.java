package abstractform;

import controllers.CanvasController;
import controllers.FormController;
import graphics.panels.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.SegmentType;

/**
 * Třída odděděná ze třídy DescriptionBasicForm přidávající vstupní pole pro Date
 *
 * @author Václav Janoch
 */
public class DateDescBasicForm extends DescriptionBasicForm {

    protected Label dateLB;
    protected DatePicker dateDP;

    /**
     * Konstruktor třídy
     */
    public DateDescBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, SegmentType type) {

        super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItemPanel, type);
        fillFormDate();
    }

    /**
     * Přetížený konstruktor třídy
     */
    public DateDescBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);
        fillFormDate();
    }

    /**
     * Vytvoří vstupní pole pro Date a přidá ho do GridPane
     */
    public void fillFormDate() {

        dateLB = new Label("Created: ");
        dateDP = new DatePicker();
        dateDP.setId("DP1");

        getInfoPart().add(dateLB, 0, 2);
        getInfoPart().setHalignment(dateLB, HPos.RIGHT);
        getInfoPart().add(dateDP, 1, 2);

    }


}
