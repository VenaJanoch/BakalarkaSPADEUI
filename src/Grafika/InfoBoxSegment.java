package Grafika;

import Forms.BasicForm;
import Forms.PhaseForm;
import Obsluha.Constans;
import Obsluha.Control;
import Obsluha.SegmentType;
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
	private double length;
	private CanvasItem canItem;

	public InfoBoxSegment(CanvasItem canItem, SegmentType type, String name) {
		super();
		System.out.println(type.toString() + " nevim1");
		this.canItem = canItem;
		this.name = new Text(name);
		
		System.out.println(type.toString() + " nevim2");
		System.out.println(type.toString() + " nevim3");
		
		this.segmentName = new Text(type.name());
		this.length = this.segmentName.getLayoutBounds().getWidth() + Constans.offset * 2;
		if (length < 40) {
			length = 40.0;
		}
		this.topRectangle = new Rectangle(length, 20);
		this.botomRectangle = new Rectangle(length, 20);
		this.getChildren().addAll(topRectangle, botomRectangle, this.segmentName, this.name);
		

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

		segmentName.setTranslateX(Constans.offset);
		segmentName.setTranslateY(13);

		name.setTranslateX(Constans.offset);
		name.setTranslateY(33);

	}

	public void setNameText(String nameStr) {
		name.setText(nameStr);
		if (name.getLayoutBounds().getWidth() > length) {
			repaintBox(name.getLayoutBounds().getWidth());

		}

	}

	public void repaintBox(double width) {

		length = width + 2 * Constans.offset;

		topRectangle.setWidth(length);
		botomRectangle.setWidth(length);

	}

	
	/** Getrs and Setrs **/
	

	public Text getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(Text segmentName) {
		this.segmentName = segmentName;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public Text getName() {
		return name;
	}

	public void setName(Text name) {
		this.name = name;
	}

	
}
