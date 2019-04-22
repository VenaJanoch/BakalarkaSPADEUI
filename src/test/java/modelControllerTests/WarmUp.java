package modelControllerTests;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import controllers.*;
import XML.ProcessGenerator;
import controllers.formControllers.FormController;
import controllers.formControllers.FormDataController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import model.DataManipulator;
import model.DataModel;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.*;

public class WarmUp {

    private SegmentLists lists;
    private FormDataController formDataController;
    private DataModel dataModel;
    private DataManipulator data;
    private FormController formController;

    public WarmUp(){
        this.lists =  new SegmentLists();
        MapperTableToObject mapperTableToObject = new MapperTableToObject(lists);
        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        dataModel = new DataModel(processGenerator);
        data =  new DataManipulator(processGenerator, dataModel);

        FileManipulator file = new FileManipulator(dataModel);
        Alerts alerts = new Alerts(file);
        DrawerPanelController drawerPanelController = new DrawerPanelController(new JFXDrawer(), new JFXDrawer(), new JFXDrawersStack());
        SelectItemController selectItemController = new SelectItemController(drawerPanelController);
        ApplicationController ap = new ApplicationController(dataModel, idCreator, lists, drawerPanelController, selectItemController);
        DeleteControl deleteControl = new DeleteControl(new SegmentLists(), mapperTableToObject, idCreator);
        DataPreparer dataPreparer = new DataPreparer(idCreator);
        formController = new FormController(idCreator, dataModel, ap, lists, dataPreparer, drawerPanelController, selectItemController);
        selectItemController.setFormController(formController);
        for(int i = 0; i < 12; i++){
            formController.getForms().add(null);
        }
        this.formDataController = new FormDataController(formController, lists, mapperTableToObject, dataModel, idCreator, dataPreparer);
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
}

