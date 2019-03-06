package forms;

import controllers.FormController;
import abstractform.DescriptionBasicForm;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import interfaces.ISegmentForm;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import services.Alerts;
import services.Constans;
import services.SegmentType;

/**
 * Třída představující formulář pro element Change, odděděná od třídy BasicForm
 * a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class ChangeForm extends DescriptionBasicForm implements ISegmentForm {

    /**
     * Globální proměnné třídy
     */
    private ComboBox<String> changeCB;
    private RadioButton existRB;

    private boolean newChange;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy Nastaví velikost
     * formuláře a reakci na uzavření formuláře
     */
    public ChangeForm(FormController formController, IFormDataController formDataController, IEditFormController editFormController, IDeleteFormController deleteFormController, SegmentType type, int indexForm) {
        super(formController, formDataController, editFormController, deleteFormController, type);

        this.newChange = true;
        this.indexForm = indexForm;

        this.setMinSize(Constans.littleformWidth, Constans.littleformHeight);
        this.setMaxSize(Constans.littleformWidth, Constans.littleformHeight);

       /* this.setOnCloseRequest(e -> {

            e.consume();
            int result = Alerts.showSaveSegment();
            if (result == 1) {
                setActionSubmitButton();
            } else if (result == 0) {
                this.close();
            }
        });*/

        getSubmitButton().setOnAction(event -> setActionSubmitButton());
        fillForm();

    }

    @Override
    public void closeForm() {

        String actName = getNameTF().getText();
        String desc = getDescriptionTF().getText();

        isSave = formDataController.saveDataFromChange(actName, desc, existRB.isSelected(), indexForm);
    }

    @Override
    public void setActionSubmitButton() {

        closeForm();
        if (isSave) {
          //  close();
        }


    }

    @Override
    public void deleteItem() {
        deleteFormController.deleteChange(indexForm);
    }

    public void fillForm() {

        existRB = new RadioButton("Exist");
        existRB.setSelected(true);
        getInfoPart().add(existRB, 1, 3);

    }

    public void setDataToForm(String name, String descriptoin, boolean isExist) {
        getNameTF().setText(name);
        getDescriptionTF().setText(descriptoin);
        existRB.setSelected(isExist);
        isSave = true;
    }
}
