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
import services.SegmentLists;

public class Main extends Application {

	/** Globální proměnné třídy **/


	private Alerts alerts;
	private FileManipulator fileManipulator;
	private ApplicationController applicationController;
	private DataManipulator dataManipulator;
	private ProcessGenerator processGenerator;
	private IdentificatorCreater identificatorCreater;
	private SegmentLists segmentLists;
	private WindowController windowController;

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

		this.segmentLists = new SegmentLists();
		this.processGenerator = new ProcessGenerator();
		this.identificatorCreater = new IdentificatorCreater();
		this.dataManipulator = new DataManipulator(processGenerator, identificatorCreater);
		this.fileManipulator = new FileManipulator(processGenerator, dataManipulator);
		this.alerts = new Alerts(fileManipulator);
		this.applicationController = new ApplicationController(fileManipulator, dataManipulator, alerts,
				identificatorCreater, segmentLists);
		primaryStage.show();
		this.windowController = new WindowController(primaryStage,fileManipulator,dataManipulator,alerts);

		MainWindow mainWindow = new MainWindow(windowController, applicationController);

	}



}
