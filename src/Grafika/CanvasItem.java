package Grafika;

import Obsluha.Constans;
import Obsluha.Control;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

public class CanvasItem extends Group{
	
	private DragPoint[] dgPoints;
	private InfoBoxSegment segmentInfo;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	private double length;
	private Scene scene;
	private Control control;
	
	public CanvasItem(Scene scene, String segmentName, String name, Control control) {

		this.scene = scene;
		this.control = control;
		this.dgPoints = new DragPoint[4];
		this.segmentInfo = new InfoBoxSegment(segmentName, name, scene,control);
		this.length = segmentInfo.getLength();
		this.getChildren().add(segmentInfo);
		createDgPoints();
	}
	
				
		private void createDgPoints() {

			dgPoints[0] = new DragPoint(this, (float)(length/2), 0, 0, scene);
			dgPoints[1] = new DragPoint(this, 0, 20, 90, scene);
			dgPoints[2] = new DragPoint(this, (float)(length/2), 40, 180, scene);
			dgPoints[3] = new DragPoint(this, (float)length, 20, 270, scene);
			
			this.getChildren().addAll(dgPoints);

		}
		
		
		public void setClicFromDragPoint(double sceneX, double sceneY, double translateX, double translateY) {
			orgSceneX = sceneX;
			orgSceneY = sceneY;
			orgTranslateX = translateX;
			orgTranslateY = translateY;
			
		}

		public void setDragFromDragPoint(double newTranslateX, double newTranslateY) {

		
			System.out.println("old item tranX " + orgTranslateX);
			System.out.println("old item tranY " + orgTranslateY);
			
			
			System.out.println("new item tranX " + newTranslateX);
			System.out.println("new item tranY " + newTranslateY);
			

			
			((Group)(this)).setTranslateX(newTranslateX);
			((Group)(this)).setTranslateY(newTranslateY);
	      
			
				System.out.println("drag");

		}


}
