package Controllers;

import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
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
    public ApplicationController(FileManipulator fileManipulator, DataManipulator dataManipulator, Alerts alerts,
                                 IdentificatorCreater identificatorCreater, SegmentLists segmentLists){
        MapperTableToObject mapperTableToObject = new MapperTableToObject(segmentLists);
        dataPreparer = new DataPreparer(identificatorCreater);
        DeleteControl deleteControl = new DeleteControl(segmentLists, mapperTableToObject, identificatorCreater);
        this.formController = new FormController(identificatorCreater, dataManipulator, this, segmentLists, deleteControl, dataPreparer);
        this.formDataController = new FormDataController(formController, deleteControl, segmentLists, mapperTableToObject, dataManipulator, identificatorCreater, dataPreparer);
        this.manipulationController = new ManipulationController(formController);
        this.linkControl = new LinkControl(formController, identificatorCreater, segmentLists, dataManipulator, manipulationController);
        this.canvasItemController = new CanvasItemController(linkControl, formController, manipulationController);
        this.formFillController = new FormFillController(formController,dataManipulator,canvasItemController, identificatorCreater,dataPreparer, segmentLists,
                 linkControl, formController.getCanvasItemList());
        formController.initBasicForms(formDataController);
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

    public LinkControl getLinkControl() {
        return linkControl;
    }

    public ManipulationController getManipulationController() {
        return manipulationController;
    }

    public FormFillController getFormFillController() {
        return formFillController;
    }
}
