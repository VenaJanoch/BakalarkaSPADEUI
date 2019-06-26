package graphics.canvas;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import services.Constans;

/**
 * Třída predstavujici ikonku poctu instaci u prvku platna
 *
 * @author Václav Janoch
 */
public class InstanceCount extends AnchorPane {

    /**
     * Globalni promenne tridy
     **/
    private Text instaceCountText;
    private Ellipse ellipse;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param count
     */
    public InstanceCount(String count) {
        super();
        this.ellipse = new Ellipse(Constans.INSTACE_COUNT_RADIUSX, Constans.INSTACE_COUNT_RADIUSY);
        this.ellipse.setFill(Color.rgb(0, 146, 202));
        this.instaceCountText = new Text(count);
        this.instaceCountText.setFill(Color.WHITE);
        this.instaceCountText.setFont(Font.font(null, FontWeight.BOLD, 25));
        this.getChildren().addAll(ellipse, instaceCountText);

    }

    /**
     * Metoda pro nastaveni pozice ikonky
     *
     * @param x x souradnice
     * @param y y souradnice
     */
    public void setPosition(double x, double y) {

        this.setTranslateX(x);
        this.setTranslateY(y);
        instaceCountText.setTranslateX(-(Constans.INSTACE_COUNT_RADIUSX / 2));
        instaceCountText.setTranslateY((Constans.INSTACE_COUNT_RADIUSY / 2));

    }

    public void setInstaceCount(String instaceCount) {
        this.instaceCountText.setText(instaceCount);
    }

}
