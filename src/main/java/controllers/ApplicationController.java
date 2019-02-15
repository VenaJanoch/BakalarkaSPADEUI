package controllers;

import model.DataModel;
import model.IdentificatorCreater;
import services.DeleteControl;
import services.MapperTableToObject;
import services.SegmentLists;

public class ApplicationController {

    private CanvasItemController canvasItemController;
    private FormController formController;
    private FormDataController formDataController;
    private LinkControl linkControl;
    private ManipulationController manipulationController;
    private FormFillController formFillController;
    private DataPreparer dataPreparer;
    private MapperTableToObject mapperTableToObject;
    private DeleteControl deleteControl;
    private DeleteFormController deleteFormController;
    private EditFormController editFormController;

    public ApplicationController(DataModel dataModel, IdentificatorCreater identificatorCreater, SegmentLists segmentLists){
        this.mapperTableToObject = new MapperTableToObject(segmentLists);
        this.dataPreparer = new DataPreparer(identificatorCreater);
        this.deleteControl = new DeleteControl(segmentLists, mapperTableToObject, identificatorCreater);
        this.formController = new FormController(identificatorCreater, dataModel, this, segmentLists, dataPreparer);
        this.formDataController = new FormDataController(formController, segmentLists, mapperTableToObject, dataModel, identificatorCreater, dataPreparer);
        this.deleteFormController = new DeleteFormController(formController, dataModel, identificatorCreater, mapperTableToObject, deleteControl, segmentLists);
        this.editFormController = new EditFormController(dataModel, identificatorCreater, mapperTableToObject, segmentLists, dataPreparer);
        this.manipulationController = new ManipulationController(deleteFormController);
        this.linkControl = new LinkControl(formController, identificatorCreater, segmentLists, dataModel, manipulationController);
        this.canvasItemController = new CanvasItemController(linkControl, formController, manipulationController);
        this.formFillController = new FormFillController(formController, dataModel, canvasItemController, identificatorCreater,dataPreparer, segmentLists,
                 linkControl, formController.getCanvasItemList());
        formController.initBasicForms(formDataController, editFormController, deleteFormController);
        formController.setFormFillController(formFillController);
        this.manipulationController.setFormFillController(formFillController);
    }

    /** Getrs and Setrs **/
    public CanvasItemController getCanvasItemController() {
        return canvasItemController;
    }

    public FormController getFormController() {
        return formController;
    }

    public ManipulationController getManipulationController() {
        return manipulationController;
    }

    public FormFillController getFormFillController() {
        return formFillController;
    }
}
