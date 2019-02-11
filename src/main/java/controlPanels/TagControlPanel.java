package controlPanels;

import Controllers.FormDataController;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.NameControlPanel;
import interfaces.IDeleteFormController;
import interfaces.IEditFormController;
import interfaces.IFormDataController;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.CriterionTable;
import tables.TagTable;

public class TagControlPanel extends NameControlPanel {


    public TagControlPanel(String buttonName, IFormDataController formDataController, IEditFormController editFormController){
        super(buttonName, formDataController, editFormController);
    }

    @Override
    protected void createBaseControlPanel() {

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


    public void showEditControlPanel(BasicTable basicTable, int configId, TableView tableView){
        TagTable tagTable = (TagTable) basicTable;
        int id = tagTable.getId();

        String tagData = formDataController.getTagData(id, configId);

        nameTF.setText(tagData);
        button.setOnAction(event ->{
            editFormController.editDataFromTag(nameTF.getText(), tagTable, configId, id);
            clearPanel(tableView);
            this.close();
        });

        this.show();
    }

    public void clearPanel(TableView<TagTable> tableView) {
        tableView.refresh();
        tableView.getSelectionModel().clearSelection();
        nameTF.setText("");
    }
}
