package abstractform;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import graphics.DragAndDropItemPanel;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.SegmentType;

import java.time.LocalDate;

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
    public DateDescBasicForm(FormController formController, FormDataController formDataController, CanvasController canvasController, DragAndDropItemPanel dgItemPanel, SegmentType type) {

        super(formController, formDataController, canvasController, dgItemPanel, type);
        fillFormDate();
    }

    /**
     * Přetížený konstruktor třídy
     */
    public DateDescBasicForm(FormController formController, FormDataController formDataController, SegmentType type) {
        super(formController, formDataController, type);
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
