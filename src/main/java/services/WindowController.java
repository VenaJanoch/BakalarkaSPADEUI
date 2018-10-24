package services;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FileManipulator;

import java.io.File;

public class WindowController {


    private Stage primaryStage;
    private boolean isClose;
    private FileManipulator fileManipulator;
    private Alerts alerts;


    public WindowController(Stage primaryStage, FileManipulator fileManipulator, Alerts alerts){
        this.primaryStage = primaryStage;
        this.fileManipulator = fileManipulator;
        this.alerts = alerts;
    }


    public void setSceneToPrimaryStage(Scene scene, String title){
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(scene);

    }


    /********* Getrs and Setrs *************/
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void closeProjectWindow() {

        primaryStage.setOnCloseRequest(event -> {
            event.consume();

            if (!isClose()) {
                int result = alerts.showCloseApp();

                if (result != -1) {
                    Platform.exit();
                }

            }

        });
    }


    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean isClose) {
        this.isClose = isClose;
    }
}
