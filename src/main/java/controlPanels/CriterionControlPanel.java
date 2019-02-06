package controlPanels;

import Controllers.FormDataController;
import abstractControlPane.ControlPanel;
import abstractControlPane.DescriptionControlPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.CriterionTable;

public class CriterionControlPanel extends DescriptionControlPanel {


    public CriterionControlPanel(String buttonName, FormDataController formDataController){
        super(buttonName, formDataController);
    }

    /**
     * Vytvoří scénu s formulářem
     *
     * @return Scene
     */
    private void creatSceneCanvas() {
        this.setScene(new Scene(controlPane));

    }

    public GridPane createControlPanel(){
        controlPane.add(button, 4, 0);
        creatSceneCanvas();
        return controlPane;
    }


    public void showEditControlPanel(BasicTable basicTable, TableView tableView){
        CriterionTable criterionTable = (CriterionTable) basicTable;
        int id = criterionTable.getId();
        String[] criterionData = formDataController.getCriterionData(id);

        nameTF.setText(criterionData[0]);
        descriptionTF.setText(criterionData[1]);
        button.setOnAction(event ->{
            criterionTable.setName(id + "_" + nameTF.getText());
            criterionTable.setDescription(descriptionTF.getText());
            formDataController.editDataFromCriterion(nameTF.getText(), criterionTable, id);
            tableView.refresh();
            nameTF.setText("");
            descriptionTF.setText("");
            this.close();
        });

        this.show();
    }


    public TextField getNameTF() {
        return nameTF;
    }

}
