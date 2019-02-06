package controlPanels;

import Controllers.FormDataController;
import abstractControlPane.DescriptionControlPanel;
import abstractControlPane.NameControlPanel;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import tables.BasicTable;
import tables.CriterionTable;
import tables.TagTable;

public class TagControlPanel extends NameControlPanel {


    public TagControlPanel(String buttonName, FormDataController formDataController){
        super(buttonName, formDataController);
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
            tagTable.setName(id + "_" + nameTF.getText());
            formDataController.editDataFromTag(nameTF.getText(), tagTable, configId, id);
            tableView.refresh();
            nameTF.setText("");
            this.close();
        });

        this.show();
    }
}
