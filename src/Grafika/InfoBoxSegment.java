package Grafika;

import Obsluha.Constans;
import Obsluha.SegmentType;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class InfoBoxSegment extends Group {

	private Rectangle topRectangle;
	private Rectangle botomRectangle;
	private Text segmentName;
	private Text name;
	private double length;
	private CanvasItem canItem;
	private double height;

	public InfoBoxSegment(CanvasItem canItem, SegmentType type, String name) {
		super();
		this.canItem = canItem;
		this.name = new Text(name);

		this.segmentName = new Text(type.name());
		this.length = this.segmentName.getLayoutBounds().getWidth() + Constans.offset * 2;

		if (length < 40) {
			length = 42.0;
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

		height = botomRectangle.getHeight();

	}

	public void setNameText(String nameStr) {

		Text testname = new Text(nameStr);
		double width = testname.getLayoutBounds().getWidth();

		name.setText(nameStr);
		name.setWrappingWidth(Constans.maxCanvasItemWidth);

		if (width > length && width < Constans.maxCanvasItemWidth) {
			repaintBox(width, height);

		} else if (width > Constans.maxCanvasItemWidth) {
			int count = countHeightBotomRectangle((int) width);
			repaintBox(Constans.maxCanvasItemWidth, count * height);
		}

	}

	private int countHeightBotomRectangle(int width) {

		int row = width / (int) Constans.maxCanvasItemWidth;

		return row + 1;
	}

	public void repaintBox(double width, double height) {

		length = width + 2 * Constans.offset;

		topRectangle.setWidth(length);
		botomRectangle.setWidth(length);
		botomRectangle.setHeight(height);

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
