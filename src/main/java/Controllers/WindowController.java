package Controllers;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import services.Alerts;

import javax.xml.crypto.Data;
import java.io.File;

public class WindowController {


    private Stage primaryStage;
    private boolean isClose;
    private FileManipulator fileManipulator;
    private DataManipulator dataManipulator;
    private Alerts alerts;


    public WindowController(Stage primaryStage, FileManipulator fileManipulator, DataManipulator dataManipulator, Alerts alerts){
        this.primaryStage = primaryStage;
        this.fileManipulator = fileManipulator;
        this.dataManipulator = dataManipulator;
        this.alerts = alerts;
    }


    public void setSceneToPrimaryStage(Scene scene, String title){
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(scene);

    }

    public void saveItemAction(){
            fileManipulator.saveFile();
    }

    public void saveItemAsAction(){
            fileManipulator.saveAsFile();
    }

    public void openProccesXMLAction() {

        fileManipulator.loadFile();
    }

    public void createNewProcessAction() {

        //Todo obsluha udalosti
    }

    public void validationAction() {
        dataManipulator.validate();
    }

    public void closeProjectWindow() {

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            if (!isClose()) {
               closeProject();
            }
        });
    }

    public void closeProject(){
        int result = alerts.showCloseApp();
        if (result != -1) {
            Platform.exit();
        }
    }


    /********* Getrs and Setrs *************/
    public boolean isClose() {
        return isClose;
    }

}
