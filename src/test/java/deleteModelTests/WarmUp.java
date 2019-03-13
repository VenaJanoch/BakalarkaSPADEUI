package deleteModelTests;

import XML.ProcessGenerator;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import controllers.*;
import model.*;
import services.Alerts;
import services.DeleteControl;
import services.MapperTableToObject;
import services.SegmentLists;

public class WarmUp {

    private SegmentLists lists;
    private FormDataController formDataController;
    private DataModel dataModel;
    private DataManipulator data;
    private FormController formController;
    private EditFormController editFormController;
    private DeleteFormController deleteFormController;


    public WarmUp(){
        this.lists =  new SegmentLists();
        MapperTableToObject mapperTableToObject = new MapperTableToObject(lists);
        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        dataModel = new DataModel(processGenerator);
        data =  new DataManipulator(processGenerator, dataModel);
        DrawerPanelController drawerPanelController = new DrawerPanelController(new JFXDrawer(), new JFXDrawer(), new JFXDrawersStack());
        SelectItemController selectItemController = new SelectItemController(drawerPanelController);
        ApplicationController ap = new ApplicationController(dataModel, idCreator, lists, drawerPanelController, selectItemController);

        DataPreparer dataPreparer = new DataPreparer(idCreator);
        formController = new FormController(idCreator, dataModel, ap, lists, dataPreparer, drawerPanelController, selectItemController);
        editFormController = new EditFormController(dataModel, idCreator, mapperTableToObject, lists, dataPreparer, formController);
        selectItemController.setFormController(formController);
        deleteFormController = new DeleteFormController(formController, dataModel , idCreator, mapperTableToObject,
                new DeleteControl(lists, mapperTableToObject, idCreator), lists);
        for(int i = 0; i < 12; i++){
            formController.getForms().add(null);
        }
        this.formDataController = new FormDataController(formController, lists, mapperTableToObject, dataModel, idCreator, dataPreparer);
    }

    public EditFormController getEditFormController() {
        return editFormController;
    }

    public SegmentLists getLists() {
        return lists;
    }

    public FormController getFormController() {
        return formController;
    }

    public DataManipulator getData() {
        return data;
    }

    public FormDataController getFormDataController() {
        return formDataController;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public DeleteFormController getDeleteFormController() {
        return deleteFormController;
    }
}

