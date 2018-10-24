package Controllers;

import model.DataManipulator;
import services.CanvasType;
import services.Control;

public class FormController {

    private DataManipulator dataManipulator;
    private Control control;
    private CanvasType canvasType;

    public FormController(DataManipulator dataManipulator, Control control, CanvasType canvasType){
        this.dataManipulator = dataManipulator;
        this.control = control;
        this.canvasType = canvasType;
    }




}
