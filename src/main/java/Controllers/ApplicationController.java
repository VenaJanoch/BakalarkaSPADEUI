package Controllers;

import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import services.DeleteControl;
import services.SegmentLists;

public class ApplicationController {

    CanvasItemController canvasItemController;
    FormController formController;
    FormDataController formDataController;
    LinkControl linkControl;
    ManipulationController manipulationController;

    public ApplicationController(FileManipulator fileManipulator, DataManipulator dataManipulator, Alerts alerts,
                                 IdentificatorCreater identificatorCreater, SegmentLists segmentLists){
        DeleteControl deleteControl = new DeleteControl();
        this.formController = new FormController(identificatorCreater, dataManipulator, this, segmentLists, deleteControl);
        formDataController = new FormDataController(formController, deleteControl, segmentLists, dataManipulator, identificatorCreater);
        formController.initBasicForms(formDataController);
        this.manipulationController = new ManipulationController(formController);
        this.linkControl = new LinkControl(formController, identificatorCreater, segmentLists, dataManipulator);
        this.canvasItemController = new CanvasItemController(linkControl, formController, manipulationController);
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
}
