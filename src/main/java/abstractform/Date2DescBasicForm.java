/**
 *
 */
package abstractform;

import controllers.graphicsComponentsControllers.CanvasController;
import controllers.formControllers.FormController;
import graphics.panels.DragAndDropItemPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import services.SegmentType;

/**
 * Třída odděděná ze třídy DateDescBasicForm přidávající vstupní pole pro druhý Date
 *
 * @author Václav Janoch
 */
public class Date2DescBasicForm extends DateDescBasicForm {

    protected Label date2LB;
    protected DatePicker date2DP;

    /**
     * Konstruktor Třídy
     */
    public Date2DescBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {

        super(formController, formDataController, editFormController, deleteFormController, type);
        fillFormDate2();
    }

    /**
     * Přetížený konstruktor
     */
    public Date2DescBasicForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, CanvasController canvasController,
                              DragAndDropItemPanel dgItemPanel, SegmentType type) {

        super(formController, formDataController, editFormController, deleteFormController, canvasController, dgItemPanel, type);
        fillFormDate2();
    }


    /**
     * Vytvoří vstupní pole pro druhý Date a přidá ho do GridPane
     */
    public void fillFormDate2() {

        date2LB = new Label("Created: ");
        date2DP = new DatePicker();
        date2DP.setId("DP2");

        getInfoPart().add(date2LB, 0, 3);
        getInfoPart().setHalignment(date2LB, HPos.RIGHT);
        getInfoPart().add(date2DP, 1, 3);

    }

}
