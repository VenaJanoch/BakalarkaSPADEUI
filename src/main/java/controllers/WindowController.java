package controllers;

import XML.ProcessGenerator;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import controllers.formControllers.FormFillController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import graphics.windows.LogInWindow;
import graphics.windows.MainWindow;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataModel;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import services.SegmentLists;

import java.io.File;

/**
 * Trida predstavujici controller pro hlavniho okna aplikace
 *
 * @author Václav Janoch
 */
public class WindowController {


    /**Graficke prvky **/
    private Stage primaryStage;
    private JFXDrawer leftDrawer;
    private JFXDrawer rightDrawer;
    private JFXDrawersStack drawersStack;
    private LogInWindow logInWindow;
    private Alerts alerts;
    private MainWindow mainWindow;

    private boolean isClose;
    private boolean logToDB;

    /**Kontrolery**/
    private FileManipulator fileManipulator;
    private DataModel dataModel;
    private FormFillController formFillController;
    private DrawerPanelController drawerPanelController;
    private SelectItemController selectItemController;
    private ApplicationController applicationController;

    /**
     * Kontroler tridy
     * Zinicializuje globalni promenne tridy
     * @param primaryStage
     */
    public WindowController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initApplication();
        this.mainWindow = new MainWindow(this, drawersStack, drawerPanelController, selectItemController, applicationController);
        setSceneToPrimaryStage(mainWindow.getScene(), mainWindow.getTitle());
        applicationController.getFormFillController().setProjectCanvasController(mainWindow.getCanvasController());
        setKeyActions();
    }

    /**
     * Metoda pro nastaveni klavesovych zkratek do okna
     */
    private void setKeyActions(){
        primaryStage.getScene().setOnKeyPressed(event -> mainWindow.getCanvasController().keyPressAction(event));
    }

    /**
     * Metoda pro inicializaci aplikace
     */
    private void initApplication() {
        logToDB = false;
        SegmentLists segmentLists = new SegmentLists();
        ProcessGenerator processGenerator = new ProcessGenerator();
        IdentificatorCreater identificatorCreater = new IdentificatorCreater();
        this.dataModel = new DataModel(processGenerator);
        this.fileManipulator = new FileManipulator(dataModel);
        this.alerts = new Alerts(fileManipulator);
        this.leftDrawer = new JFXDrawer();
        this.rightDrawer = new JFXDrawer();
        this.drawersStack = new JFXDrawersStack();
        leftDrawer.setId("LEFT");
        rightDrawer.setId("RIGHT");
        this.drawerPanelController = new DrawerPanelController(leftDrawer, rightDrawer, drawersStack);
        this.selectItemController = new SelectItemController(drawerPanelController);
        this.applicationController = new ApplicationController(dataModel, identificatorCreater, segmentLists, drawerPanelController, selectItemController);
        selectItemController.setFormController(applicationController.getFormController());
        this.formFillController = applicationController.getFormFillController();
        this.logInWindow = new LogInWindow(applicationController.getDatabaseController());


    }

    /**
     * Metoda pro nastaveni sceny do PrimaryStage
     * @param scene scena pro vlozeni
     * @param title titulek okna
     */
    public void setSceneToPrimaryStage(Scene scene, String title) {
        this.primaryStage.setTitle(title);
        this.primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setMaximized(true);

    }

    /**
     * Metoda pro zavolani FileManipulator pro ulozeni modelu
     */
    public void saveItemAction() {
        fileManipulator.saveFile();
    }

    /**
     * Metoda pro zavolani FileManipulator pro ulozeni jako modelu
     */
    public void saveItemAsAction() {
        fileManipulator.saveAsFile();
    }

    /**
     * Metoda pro reset aplikace
     */
    public void createNewProcessAction() {

        initApplication();
        this.mainWindow = new MainWindow(this, drawersStack, drawerPanelController, selectItemController, applicationController);
        setSceneToPrimaryStage(mainWindow.getScene(), mainWindow.getTitle());
        applicationController.getFormFillController().setProjectCanvasController(mainWindow.getCanvasController());
        primaryStage.setMaximized(true);
    }

    /**
     * Metoda pro reakci na volbu nacteni modelu z XML
     */
    public void openProccesXMLAction() {

        File xmlFile = fileManipulator.loadFile();
        if (xmlFile != null) {
            createNewProcessAction();
            dataModel.parseProject(xmlFile);
            formFillController.createFormsFromData();
        }
    }

    /**
     * Reakce na validaci
     */
    public void validationAction() {
        dataModel.validate();
    }

    /**
     * Metoda pro zavreni okna aplikace
     */
    public void closeProjectWindow() {

        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            if (!isClose()) {
                closeProject();
            }
        });
    }

    /**
     * Metoda pro ukonceni celeho projektu
     */
    public void closeProject() {
        int result = alerts.showCloseApp();
        if (result != -1) {
            Platform.exit();
        }
    }

    /**
     * Metoda pro zavolani okna pro prihlaseni pripadne okna s projekty
     */
    public void showConfirmWindow() {
        if (!logToDB) {
            logToDB = logInWindow.showLogDialog();
        } else {
            logInWindow.show();
        }
    }


    /********* Getrs and Setrs *************/
    public boolean isClose() {
        return isClose;
    }


}
