package controlPanels;

import Controllers.FormController;
import Controllers.FormDataController;
import abstractControlPane.ControlPanel;
import abstractControlPane.DescriptionControlPanel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.CheckComboBox;
import services.Constans;
import tables.BasicTable;
import tables.CriterionTable;
import tables.MilestoneTable;

import java.util.ArrayList;
import java.util.List;

public class MilestoneControlPanel extends DescriptionControlPanel {

    private Label criteriaLB;
    private CheckComboBox<BasicTable> criteriaCB;
    private ObservableList<BasicTable> criterionArray;
    private ObservableList<Integer> criterionIndex;

    public MilestoneControlPanel(String buttonName, FormDataController formDataController, FormController formController){
        super(buttonName, formDataController, formController);
        criterionIndex = FXCollections.observableArrayList();
    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private void creatSceneCanvas() {
        mainPanel.setCenter(controlPane);
        this.setScene(new Scene(mainPanel));

    }

    public GridPane createControlPanel(){

        criteriaLB = new Label("Criteria: ");
        criteriaCB = new CheckComboBox<BasicTable>(formController.getCriterionObservable());
        criteriaCB.setMaxWidth(Constans.checkComboBox);

        criteriaCB.getCheckModel().getCheckedItems().addListener(new ListChangeListener<BasicTable>() {

            public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
                criterionIndex = criteriaCB.getCheckModel().getCheckedIndices();
                criterionArray = criteriaCB.getCheckModel().getCheckedItems();
            }
        });

        controlPane.add(criteriaLB, 4, 0);
        controlPane.add(criteriaCB, 5, 0);
        controlPane.add(button, 6, 0);

        creatSceneCanvas();
        return controlPane;
    }

    @Override
    public void showEditControlPanel(BasicTable basicTable, TableView tableView) {
        MilestoneTable milestoneTable = (MilestoneTable) basicTable;
        int id = milestoneTable.getId();
        String[] milestoneData = formDataController.getMilestoneStringData(id);
        List<Integer> criteriaID = formDataController.getCriterionFromMilestone(id);
        for(int i : criteriaID){
            criteriaCB.getCheckModel().check(i);
        }

        nameTF.setText(milestoneData[0]);
        descriptionTF.setText(milestoneData[1]);

        button.setOnAction(event ->{

            milestoneTable.setName(id + "_" + nameTF.getText());
            milestoneTable.setDescription(descriptionTF.getText());
            ArrayList<Integer> criterionList = new ArrayList<>(criterionIndex);

            formDataController.editDataFromMilestone(nameTF.getText(), milestoneTable, criterionList, id);

            nameTF.setText("");
            descriptionTF.setText("");

            tableView.refresh();
            tableView.getSelectionModel().clearSelection();
            this.close();
        });

        this.show();

    }

    public Button getButton() {
        return button;
    }

    public ObservableList<Integer> getCriterionIndex() {
        return criterionIndex;
    }


    public void clearCriteriaCB() {
        criteriaCB.getCheckModel().clearChecks();
    }
}
