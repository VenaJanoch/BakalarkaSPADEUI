package Grafika;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ctverec extends Rectangle {

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	public Ctverec(int sirka, int vyska) {
		super();
		 this.setOnMousePressed(circleOnMousePressedEventHandler);
	     this.setOnMouseDragged(circleOnMouseDraggedEventHandler);
	     this.setWidth(sirka);
	     this.setHeight(vyska);
	     this.setFill(Color.BLACK);
	     
	}
	
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
	        new EventHandler<MouseEvent>() {
	 
	        @Override
	        public void handle(MouseEvent t) {
	            orgSceneX = t.getSceneX();
	            orgSceneY = t.getSceneY();
	            orgTranslateX = ((Rectangle)(t.getSource())).getTranslateX();
	            orgTranslateY = ((Rectangle)(t.getSource())).getTranslateY();
	        }
	    };
	     
	    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
	        new EventHandler<MouseEvent>() {
	 
	        @Override
	        public void handle(MouseEvent t) {
	            double offsetX = t.getSceneX() - orgSceneX;
	            double offsetY = t.getSceneY() - orgSceneY;
	            double newTranslateX = orgTranslateX + offsetX;
	            double newTranslateY = orgTranslateY + offsetY;
	             
	            ((Rectangle)(t.getSource())).setTranslateX(newTranslateX);
	            ((Rectangle)(t.getSource())).setTranslateY(newTranslateY);
	        }
	    };
}
