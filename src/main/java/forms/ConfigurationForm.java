package forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Controllers.CanvasController;
import Controllers.FormController;
import Controllers.FormDataController;
import abstractform.DateBasicForm;
import forms.TagForm;
import graphics.DragAndDropItemPanel;
import javafx.collections.FXCollections;
import org.controlsfx.control.CheckComboBox;

import SPADEPAC.Configuration;
import abstractform.BasicForm;
import graphics.CanvasItem;
import interfaces.ISegmentForm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.WindowEvent;
import services.Alerts;
import services.CanvasType;
import services.Constans;
import services.Control;
import services.DeleteControl;
import model.IdentificatorCreater;
import tables.BasicTable;

/**
 * Třída představující formulář pro element Configuration, odděděná od třídy
 * BasicForm a implementující ISegmentForm
 *
 * @author Václav Janoch
 */
public class ConfigurationForm extends DateBasicForm implements ISegmentForm {

    /**
     * Globální proměnné třídy
     */
    private boolean isRelease;

    private Button addTag;
    private boolean isNew;

    final ToggleGroup group = new ToggleGroup();

    private Label createdLB;
    private Label isReleaseLB;
    private Label authorRoleLB;
    private Label cprLB;
    private Label branchLB;

    private RadioButton rbYes;
    private RadioButton rbNo;
    private DatePicker createdDP;
    private CheckComboBox<String> branchCB;
    private ComboBox<BasicTable> authorRoleCB;
    private CheckComboBox<BasicTable> cprCB;

    private int authorIndex;

    private ArrayList<Integer> branchIndex;
    private ObservableList<Integer> cprIndex;

    private TagForm tagForm;

    private ObservableList<BasicTable> cprList;
    private ObservableList<String> branchList;
    private ObservableList<BasicTable> roleList;
    /**
     * Konstruktor třídy Zinicializuje globální proměnné tříd Nastaví reakci na
     * uzavření okna
     */
    public ConfigurationForm(FormController formController, FormDataController formDataController, CanvasController canvasController,
                             DragAndDropItemPanel dgItemPanel, String name, ObservableList<BasicTable> cprList, ObservableList<String> branchList,
                             ObservableList<BasicTable> roleList, int indexForm) {

        super(formController, formDataController, canvasController, dgItemPanel, name);
        this.cprList = cprList;
        this.branchList = branchList;
        this.roleList = roleList;
        this.indexForm = indexForm;

        this.tagForm = new TagForm(formController, formDataController,"Tag", indexForm);
        isNew = true;
        isRelease = true;

        branchIndex = new ArrayList<>();
        cprIndex = FXCollections.observableArrayList();

        this.setOnCloseRequest(event -> Alerts.showSaveSegment());

        getSubmitButton().setText("Add");
        getSubmitButton().setOnAction(event -> setActionSubmitButton());
        createForm();

    }

    @Override
    public void closeForm() {

        String actName = getNameTF().getText();
        LocalDate createDate = createdDP.getValue();
        branchIndex.addAll(branchCB.getCheckModel().getCheckedIndices());
        cprIndex.addAll(cprCB.getCheckModel().getCheckedIndices());

        isSave =  formDataController.saveDataFromConfiguration(actName, createDate, isRelease, authorIndex, branchIndex, new ArrayList<>(cprIndex),
                canvasController.getListOfItemOnCanvas(), isNew, indexForm);
    }

    @Override
    public void setActionSubmitButton() {

        closeForm();
        if(isSave){
            isNew = false;
            getSubmitButton().setText("Ok");
            close();

        }

    }

    @Override
    public void deleteItem() {}

    public void createForm() {

        createdLB = new Label("Created: ");
        createdDP = new DatePicker();

        isReleaseLB = new Label("Release: ");
        rbNo = new RadioButton("No");
        rbNo.setToggleGroup(group);
        rbYes = new RadioButton("Yes");
        rbYes.setToggleGroup(group);
        rbYes.setSelected(true);

        authorRoleLB = new Label("Author-role: ");
        authorRoleCB = new ComboBox<BasicTable>(roleList);
        authorRoleCB.setVisibleRowCount(5);
        authorRoleCB.getSelectionModel().selectedIndexProperty().addListener(roleListenerAut);

        cprLB = new Label("CPR");
        cprCB = new CheckComboBox<BasicTable>(cprList);
        cprCB.setMaxWidth(Constans.checkComboBox);
        cprCB.getCheckModel().getCheckedItems().addListener(cprListener);

        branchLB = new Label("Branches");
        branchCB = new CheckComboBox<String>(branchList);
        branchCB.setMaxWidth(Constans.checkComboBox);
        branchCB.getCheckModel().getCheckedItems().addListener(branchListener);



        addTag = new Button("Add Tag");
        addTag.setOnAction(event -> tagForm.show());

        fillInfoPart();
    }

    /**
     * Pomocná metoda pro nastavení prvků do GridPane
     */
    private void fillInfoPart() {

        getInfoPart().add(createdLB, 0, 1);
        getInfoPart().setHalignment(createdLB, HPos.RIGHT);
        getInfoPart().add(createdDP, 1, 1);

        getInfoPart().add(isReleaseLB, 0, 2);
        getInfoPart().setHalignment(isReleaseLB, HPos.RIGHT);
        getInfoPart().add(rbYes, 1, 2);
        getInfoPart().add(rbNo, 2, 2);

        getInfoPart().add(authorRoleLB, 0, 3);
        getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);
        getInfoPart().add(authorRoleCB, 1, 3);

        getInfoPart().add(cprLB, 0, 4);
        getInfoPart().setHalignment(cprLB, HPos.RIGHT);
        getInfoPart().add(cprCB, 1, 4);

        getInfoPart().add(branchLB, 0, 5);
        getInfoPart().setHalignment(branchLB, HPos.RIGHT);
        getInfoPart().add(branchCB, 1, 5);

        getInfoPart().add(addTag, 1, 6);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = (RadioButton) newValue.getToggleGroup().getSelectedToggle();

                if (chk.getText().contains("Yes")) {
                    isRelease = true;
                } else {
                    isRelease = false;
                }

            }
        });

    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Role
     */
    ChangeListener<Number> roleListenerAut = new ChangeListener<Number>() {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            authorIndex = newValue.intValue();

        }
    };

    /**
     * ChangeListener pro určení indexů prvků z CheckComboBoxu pro CPR
     */
    ListChangeListener<BasicTable> cprListener = new ListChangeListener<BasicTable>() {

        public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
            cprIndex.addAll(cprCB.getCheckModel().getCheckedIndices());

        }
    };

    ListChangeListener<String> branchListener = new ListChangeListener<String>() {

        public void onChanged(ListChangeListener.Change<? extends String> c) {
            branchIndex.addAll(branchCB.getCheckModel().getCheckedIndices());
        }
    };

    public void setDataToForm(String name, LocalDate createdDate, int authorIndex, ArrayList<Integer> cprIndexs, ArrayList<Integer> branchIndexs) {
        getNameTF().setText(name);
        getDateDP().setValue(createdDate);
        authorRoleCB.getSelectionModel().select(authorIndex);
        for(int i : cprIndexs){
            cprCB.getCheckModel().check(i);
        }

        for(int i : branchIndexs){
            branchCB.getCheckModel().check(i);
        }

        isSave = true;
        isNew = false;
        getSubmitButton().setText("Ok");
    }

    /*** Getrs and Setrs ***/

    public TagForm getTagForm() {
        return tagForm;
    }

    public void setTagForm(TagForm tagForm) {
        this.tagForm = tagForm;
    }

    public boolean isRelease() {
        return isRelease;
    }

    public void setRelease(boolean isRelease) {
        this.isRelease = isRelease;
    }

    public RadioButton getRbYes() {
        return rbYes;
    }

    public void setRbYes(RadioButton rbYes) {
        this.rbYes = rbYes;
    }

    public RadioButton getRbNo() {
        return rbNo;
    }

    public void setRbNo(RadioButton rbNo) {
        this.rbNo = rbNo;
    }

    public DatePicker getCreatedDP() {
        return createdDP;
    }

    public void setCreatedDP(DatePicker createdDP) {
        this.createdDP = createdDP;
    }

    public ComboBox<BasicTable> getAuthorRoleCB() {
        return authorRoleCB;
    }

    public void setAuthorRoleCB(ComboBox<BasicTable> authorRoleCB) {
        this.authorRoleCB = authorRoleCB;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public CheckComboBox<BasicTable> getCprCB() {
        return cprCB;
    }
}
