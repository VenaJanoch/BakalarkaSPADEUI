package run;

import graphics.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.Control;

public class Main extends Application {

	/** Globální proměnné třídy **/
	private Stage primaryStage;
	MainWindow mainWindow;

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
		this.primaryStage = primaryStage;
		this.primaryStage.show();

		mainWindow = new MainWindow(this);
		this.primaryStage.setTitle(mainWindow.getTitle());
		this.primaryStage.setScene(mainWindow.getScene());

	}

	/********* Getrs and Setrs *************/
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
