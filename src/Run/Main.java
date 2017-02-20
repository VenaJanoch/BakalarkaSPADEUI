package Run;

import Grafika.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	private Stage primaryStage;
	MainWindow mainWindow;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.show();
		mainWindow = new MainWindow(this);
		nastavObrazovku(mainWindow);
	
	}
	
	

	public void nastavObrazovku(Stage obrazovka){
		this.primaryStage.setTitle(obrazovka.getTitle());
		this.primaryStage.setScene(obrazovka.getScene());
		
	}
	
	/********* Getrs and Setrs *************/
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


}
