package forms;

import java.time.LocalDate;
import controllers.FormController;
import abstractform.Date2DescBasicForm;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentForm;
import services.Alerts;
import services.Constans;
import services.SegmentType;

/**
 * Třída představující formulář pro kořenový Element Project, odděděná od třídy
 * Dete2DescBasicForm a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class ProjectForm extends Date2DescBasicForm implements ISegmentForm {

    /**
     * Konstruktor třídy Nastaví velikost okna, reakci na uzavření okna
     * formuláře a zinicializuje globální proměnné tříd
     * <p>
     * Project
     */
    public ProjectForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        this.setMinSize(Constans.littleformWidth, Constans.littleformHeight);
        this.setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

      //  this.setOnCloseRequest(e -> {

       //     e.consume();
            //int result = Alerts.showSaveSegment();
            //if (result == 1) {
            //    setActionSubmitButton();
            //} else if (result == 0) {
       //        this.close();
        //    }
      //  });

        getSubmitButton().setOnAction(event -> setActionSubmitButton());
       fillForm();
        getFormName().setText(type.name());
    }

    @Override
    public void closeForm() {

        String actName = getNameTF().getText();
        LocalDate endDate = date2DP.getValue();
        LocalDate startDate = dateDP.getValue();
        String desc = getDescriptionTF().getText();

        formDataController.saveDataFromProjectFrom(actName, endDate, startDate, desc);
    }

    @Override
    public void setActionSubmitButton() {
        closeForm();
      //  close();
}

    @Override
    public void deleteItem() {

    }


    public void fillForm() {

        dateLB.setText("Start-Date");
        date2LB.setText("End-Date");

    }

    public void setDataToForm(String name, String description, LocalDate startDate, LocalDate endDate) {
        getNameTF().setText(name);
        getDescriptionTF().setText(description);
        dateDP.setValue(startDate);
        date2DP.setValue(endDate);
    }
}
