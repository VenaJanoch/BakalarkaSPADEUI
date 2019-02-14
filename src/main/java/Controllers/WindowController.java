package Controllers;

import XML.ProcessGenerator;
import graphics.MainWindow;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataManipulator;
import model.DataModel;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import services.SegmentLists;

import javax.xml.crypto.Data;
import java.io.File;

public class WindowController {


    private Stage primaryStage;
    private boolean isClose;
    private FileManipulator fileManipulator;
    private DataModel dataModel;
    private FormFillController formFillController;
    private ApplicationController applicationController;
    private Alerts alerts;
    MainWindow mainWindow;

    public WindowController(Stage primaryStage){
        this.primaryStage = primaryStage;
        initApplication();
        this.mainWindow = new MainWindow(this, applicationController);
        setSceneToPrimaryStage(mainWindow.getScene(), mainWindow.getTitle());
        applicationController.getFormFillController().setProjectCanvasController(mainWindow.getCanvasController());
        primaryStage.setMaximized(true);
        }

    private void initApplication(){
        SegmentLists segmentLists = new SegmentLists();
        ProcessGenerator processGenerator = new ProcessGenerator();
        IdentificatorCreater identificatorCreater = new IdentificatorCreater();
        this.dataModel = new DataModel(processGenerator);
        this.fileManipulator = new FileManipulator(dataModel);
        this.alerts = new Alerts(fileManipulator);
        this.applicationController = new ApplicationController(dataModel, identificatorCreater, segmentLists);
        this.formFillController = applicationController.getFormFillController();

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

    public void createNewProcessAction() {

        initApplication();
        this.mainWindow = new MainWindow(this, applicationController);
        setSceneToPrimaryStage(mainWindow.getScene(), mainWindow.getTitle());
        applicationController.getFormFillController().setProjectCanvasController(mainWindow.getCanvasController());

    }

    public void openProccesXMLAction() {

        File xmlFile = fileManipulator.loadFile();
        if(xmlFile != null){
            createNewProcessAction();
            dataModel.parseProject(xmlFile);
            formFillController.createFormsFromData();
        }
    }

    public void validationAction() {
        dataModel.validate();
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
