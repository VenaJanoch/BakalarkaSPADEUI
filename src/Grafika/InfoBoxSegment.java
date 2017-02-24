package Grafika;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InfoBoxSegment extends Group {

	private Rectangle topRectangle;
	private Rectangle botomRectangle;
	private Text segmentName;
	private Text name;
	private Scene scene;
	private ZadejInfo zi;

	public InfoBoxSegment(String segmentName, String name, Scene scene) {
		super();
		this.topRectangle = new Rectangle(55, 20);
		this.botomRectangle = new Rectangle(55, 20);
		this.segmentName = new Text(segmentName);
		this.name = new Text(name);
		this.scene = scene;
		this.getChildren().addAll(topRectangle, botomRectangle, this.segmentName, this.name);
		this.setOnMousePressed(circleOnMousePressedEventHandler);
		
		createBlock();

	}

	private void createBlock() {

		topRectangle.setFill(Color.TRANSPARENT);
		botomRectangle.setFill(Color.TRANSPARENT);
		topRectangle.setStroke(Color.RED);
		topRectangle.setStrokeWidth(3);
		botomRectangle.setStroke(Color.RED);
		botomRectangle.setStrokeWidth(3);

		topRectangle.setTranslateX(0);
		topRectangle.setTranslateY(0);
		botomRectangle.setTranslateX(0);
		botomRectangle.setTranslateY(20);

		segmentName.setTranslateX(5);
		segmentName.setTranslateY(13);

		name.setTranslateX(13);
		name.setTranslateY(33);


	}


	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			System.out.println("Click " + t.getSceneX());
			zi = new ZadejInfo();
			
		}
	};

	

}
