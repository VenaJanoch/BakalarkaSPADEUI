package Controllers;

import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import services.Alerts;

public class ApplicationController {

    CanvasItemController canvasItemController;
    FormController formController;
    IdentificatorController identificatorController;
    LinkControl linkControl;
    WindowController windowController;
    ManipulationController manipulationController;

    public ApplicationController(Stage primaryStage, FileManipulator fileManipulator, DataManipulator dataManipulator, Alerts alerts){

        this.identificatorController = new IdentificatorController();
        this.formController = new FormController();
        this.linkControl = new LinkControl(formController, identificatorController);
        this.canvasItemController = new CanvasItemController(linkControl, formController);
        this.windowController = new WindowController(primaryStage,fileManipulator,dataManipulator,alerts);
        this.manipulationController = new ManipulationController(formController, canvasItemController);
    }

    /** Getrs and Setrs **/
    public CanvasItemController getCanvasItemController() {
        return canvasItemController;
    }

    public FormController getFormController() {
        return formController;
    }

    public IdentificatorController getIdentificatorController() {
        return identificatorController;
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
