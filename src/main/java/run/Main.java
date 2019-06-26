package run;

import controllers.WindowController;
import javafx.application.Application;
import javafx.stage.Stage;


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
