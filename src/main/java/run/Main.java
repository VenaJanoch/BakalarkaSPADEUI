package run;

import Controllers.ApplicationController;
import XML.ProcessGenerator;
import graphics.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.Alerts;
import Controllers.WindowController;

public class Main extends Application {

	/** Globální proměnné třídy **/


	private Alerts alerts;
	private FileManipulator fileManipulator;
	private ApplicationController applicationController;
	private DataManipulator dataManipulator;
	private ProcessGenerator processGenerator;
	private IdentificatorCreater identificatorCreater;

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

		this.processGenerator = new ProcessGenerator();
		this.identificatorCreater = new IdentificatorCreater();
		this.dataManipulator = new DataManipulator(processGenerator, identificatorCreater);
		fileManipulator = new FileManipulator(processGenerator, dataManipulator);
		alerts = new Alerts(fileManipulator);
		this.applicationController = new ApplicationController(primaryStage, fileManipulator, dataManipulator, alerts, identificatorCreater, dataManipulator.getSegmentLists());
		primaryStage.show();

		MainWindow mainWindow = new MainWindow(applicationController);

	}



}
