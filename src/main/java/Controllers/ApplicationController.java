package Controllers;

import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import services.SegmentLists;

public class ApplicationController {

    CanvasItemController canvasItemController;
    FormController formController;
    LinkControl linkControl;
    WindowController windowController;
    ManipulationController manipulationController;

    public ApplicationController(Stage primaryStage, FileManipulator fileManipulator, DataManipulator dataManipulator, Alerts alerts,
                                 IdentificatorCreater identificatorCreater, SegmentLists segmentLists){

        this.formController = new FormController(identificatorCreater, dataManipulator, this, segmentLists);
        this.manipulationController = new ManipulationController(formController);
        this.linkControl = new LinkControl(formController, identificatorCreater, segmentLists, dataManipulator);
        this.canvasItemController = new CanvasItemController(linkControl, formController, manipulationController);
        this.windowController = new WindowController(primaryStage,fileManipulator,dataManipulator,alerts);

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

    public WindowController getWindowController() {
        return windowController;
    }

    public ManipulationController getManipulationController() {
        return manipulationController;
    }
}
