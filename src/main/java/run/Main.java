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

		new WindowController(primaryStage);
		primaryStage.show();
	}



}
