package Grafika;

import java.util.concurrent.ConcurrentHashMap;

import com.sun.jndi.cosnaming.CNNameParser;

import Obsluha.Constans;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class DragPoint extends Group {

	CanvasItem canvasSeg;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;
	private Arc arc;
	private Scene scene;
	private float x;
	private float y;
	

	public DragPoint(CanvasItem canvasSeg, float x, float y, float startAngle, Scene scene) {
		super();
		this.canvasSeg = canvasSeg;
		this.scene = scene;
		arc = new Arc();
		this.getChildren().add(arc);
		this.x = x;
		this.y = y;
		//arc.setCenterX(x);
		//arc.setCenterY(y);
		this.setTranslateX(x);
		this.setTranslateY(y);
		
		arc.setRadiusX(Constans.xRadius);
		arc.setRadiusY(Constans.yRadius);
		arc.setStartAngle(startAngle);
		arc.setLength(Constans.angleLength);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.RED);
		arc.setStrokeWidth(2);

		this.setOnMousePressed(circleOnMousePressedEventHandler);
		this.setOnMouseDragged(circleOnMouseDraggedEventHandler);

	}

	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {

			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Group) (t.getSource())).getTranslateX();
			orgTranslateY = ((Group) (t.getSource())).getTranslateY();

			canvasSeg.setClicFromDragPoint(orgSceneX, orgSceneY, orgTranslateX-x, orgTranslateY-y);
		}
	};

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			
			  double offsetX = t.getSceneX() - orgSceneX;
	            double offsetY = t.getSceneY() - orgSceneY;
	            double newTranslateX = orgTranslateX + offsetX;
	            double newTranslateY = orgTranslateY + offsetY;
	        
	            System.out.println("old point tranX " + orgTranslateX);
				System.out.println("old point tranY " + orgTranslateY);
				
				
				System.out.println("offset point  " + offsetX);
				System.out.println("offset point " + offsetY);
	

	            
			  ((Group)(t.getSource())).setTranslateX(newTranslateX);
	          ((Group)(t.getSource())).setTranslateY(newTranslateY);
	          System.out.println("new point tranX " + ((Group)(t.getSource())).getTranslateX());
	          System.out.println("new point tranY " + ((Group)(t.getSource())).getTranslateY());
	          
	          System.out.println(t.getSceneX());
	          canvasSeg.setDragFromDragPoint(newTranslateX - x , newTranslateY - y);
		}
	};
	
	/** Getrs and Setrs **/
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
