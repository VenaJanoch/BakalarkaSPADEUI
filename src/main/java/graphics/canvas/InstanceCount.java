package graphics.canvas;

import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import services.Constans;

public class InstanceCount extends AnchorPane {

    private Text instaceCountText;
    private Circle circle;

    public InstanceCount(String count) {
        super();
        this.circle = new Circle(Constans.INSTACE_COUNT_RADIUS);
        this.circle.setFill(Color.rgb(0,146,202));
        this.instaceCountText = new Text(count);
        this.instaceCountText.setFill(Color.WHITE);
        this.instaceCountText.setFont(Font.font(null, FontWeight.BOLD, 25));
        this.getChildren().addAll(circle, instaceCountText);

    }

    public void setPosition(double x, double y){

        this.setTranslateX(x);
        this.setTranslateY(y);
        instaceCountText.setTranslateX(-(Constans.INSTACE_COUNT_RADIUS/2));
        instaceCountText.setTranslateY((Constans.INSTACE_COUNT_RADIUS/2));

    }

    public void setInstaceCount(String instaceCount){
        this.instaceCountText.setText(instaceCount);
    }

}
