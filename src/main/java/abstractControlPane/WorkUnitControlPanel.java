package abstractControlPane;

import controllers.FormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import services.SegmentLists;
import tables.BasicTable;

public abstract class WorkUnitControlPanel extends DescriptionControlPanel {

    private Label workUnitLB;

    protected CheckComboBox<BasicTable> workUnitCB;

    protected ObservableList<Integer> workUnitIndicies;

    protected SegmentLists segmentLists;
    private boolean isShowWorkUnit;
    private Button workUnitButton;

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController, FormController formController) {
        super(buttonText, formDataController, editFormController, formController);
        this.segmentLists = formController.getSegmentLists();
        addItemsToControlPanel2();
    }

    public WorkUnitControlPanel(String buttonText, IFormDataController formDataController, IEditFormController editFormController) {
        super(buttonText, formDataController, editFormController);
        addItemsToControlPanel2();
    }

    protected void addItemsToControlPanel2() {


        workUnitLB = new Label("Work Unit: ");
        workUnitCB = new CheckComboBox<>(segmentLists.getWorkUnitsObservable());
        workUnitCB.setMaxWidth(Constans.checkComboBox);
        workUnitCB.setVisible(false);
        workUnitCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(Change<? extends BasicTable> c) {
                workUnitIndicies = workUnitCB.getCheckModel().getCheckedIndices();
            }
        });

        isShowWorkUnit = false;
        workUnitButton = new Button("+");
        workUnitButton.setOnAction(event -> {
            if (!isShowWorkUnit){
                workUnitCB.setVisible(true);
                isShowWorkUnit  = true;
                workUnitButton.setText("-");
            }else{
                workUnitCB.setVisible(false);
                workUnitCB.getCheckModel().check(0);
                isShowWorkUnit = false;
                workUnitButton.setText("+");
            }
        });
        controlPane.add(workUnitLB, 1, 2);
        controlPane.setHalignment(workUnitLB, HPos.LEFT);
        controlPane.add(workUnitCB, 2, 2);
        controlPane.add(workUnitButton, 0, 2);
    }


    protected abstract void showEditControlPanel(BasicTable basicTable, TableView tableView);


}
