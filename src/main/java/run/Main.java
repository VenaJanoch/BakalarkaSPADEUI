package run;

import javafx.application.Application;
import javafx.stage.Stage;
import controllers.WindowController;


public class Main extends Application {

    /**
     * Hlavní metoda aplikace
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Hlavní metoda FX
     **/
    @Override
    public void start(Stage primaryStage) throws Exception {

        new WindowController(primaryStage);
        primaryStage.show();
    }


}
