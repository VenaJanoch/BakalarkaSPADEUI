package run;

import graphics.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.FileManipulator;
import services.Alerts;
import services.Control;
import services.WindowController;

public class Main extends Application {

	/** Globální proměnné třídy **/


	WindowController windowController;
	Alerts alerts;
	FileManipulator fileManipulator;

	/**
	 * Hlavní metoda aplikace
	 * 
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Hlavní metoda FX
	 * 
	 **/
	@Override
	public void start(Stage primaryStage) throws Exception {

		fileManipulator = new FileManipulator();
		alerts = new Alerts(fileManipulator);
		windowController = new WindowController(primaryStage, fileManipulator, alerts);

		primaryStage.show();

		MainWindow mainWindow = new MainWindow(windowController);

	}



}
