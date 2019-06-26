package modelControllerCopyTests;

import XML.ProcessGenerator;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import controllers.ApplicationController;
import controllers.DataPreparer;
import controllers.LinkControl;
import controllers.formControllers.*;
import controllers.graphicsComponentsControllers.CanvasItemController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import controllers.graphicsComponentsControllers.SelectionController;
import model.DataManipulator;
import model.DataModel;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import services.DeleteControl;
import services.MapperTableToObject;
import services.SegmentLists;

public class WarmUp {

    private SegmentLists lists;
    private FormDataController formDataController;
    private EditFormController editFormController;
    private DataModel dataModel;
    private DataManipulator data;
    private FormController formController;
    private ManipulationController manipulationController;
    private DeleteFormController deleteFormController;
    private SelectionController selectionController;
    private FormFillController formFillController;

    public WarmUp() {
        this.lists = new SegmentLists();
        MapperTableToObject mapperTableToObject = new MapperTableToObject(lists);
        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        dataModel = new DataModel(processGenerator);
        data = new DataManipulator(dataModel);

        FileManipulator file = new FileManipulator(dataModel);
        Alerts alerts = new Alerts(file);
        DrawerPanelController drawerPanelController = new DrawerPanelController(new JFXDrawer(), new JFXDrawer(), new JFXDrawersStack());
        SelectItemController selectItemController = new SelectItemController(drawerPanelController);
        ApplicationController ap = new ApplicationController(dataModel, idCreator, lists, drawerPanelController, selectItemController);
        DeleteControl deleteControl = new DeleteControl(new SegmentLists(), mapperTableToObject, idCreator);
        DataPreparer dataPreparer = new DataPreparer();
        formController = new FormController(idCreator, dataModel, ap, lists, dataPreparer, drawerPanelController, selectItemController);
        editFormController = new EditFormController(dataModel, idCreator, mapperTableToObject, lists, dataPreparer, formController);
        selectItemController.setFormController(formController);
        for (int i = 0; i < 12; i++) {
            formController.getForms().add(null);
        }
        this.formDataController = new FormDataController(formController, lists, mapperTableToObject, dataModel, idCreator, dataPreparer);
        deleteFormController = new DeleteFormController(formController, dataModel, idCreator, mapperTableToObject, deleteControl, lists);
        selectionController = new SelectionController();
        this.manipulationController = new ManipulationController(deleteFormController, selectionController);
        LinkControl linkControl = new LinkControl(formController, idCreator, lists, deleteFormController, manipulationController);
        CanvasItemController canvasItemController = new CanvasItemController(linkControl, formController, manipulationController, selectionController);
        this.formFillController = new FormFillController(formController, formDataController, dataModel, canvasItemController, idCreator, lists, linkControl);
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

    public EditFormController getEditFormController() {
        return editFormController;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public ManipulationController getManipulationController() {
        return manipulationController;
    }

    public FormFillController getFormFillController() {
        return formFillController;
    }
}

