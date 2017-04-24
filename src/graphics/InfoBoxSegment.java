package graphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.Constans;
import services.SegmentType;

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

		this.topRectangle = new Rectangle(length, Constans.infoBoxHeightRectangle);
		this.botomRectangle = new Rectangle(length, Constans.infoBoxHeightRectangle);
		height = Constans.infoBoxHeight;
		this.getChildren().addAll(topRectangle, botomRectangle, this.segmentName, this.name);

		createBlock();

	}
	
	public void setRectangleColor(Color color){
		
		topRectangle.setStroke(color);
		botomRectangle.setStroke(color);
	}

	private void createBlock() {

		setRectangleColor(Constans.rectangleBorderColor);
		
		topRectangle.setFill(Color.TRANSPARENT);
		botomRectangle.setFill(Color.TRANSPARENT);
		
		topRectangle.setStrokeWidth(3);

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
		
		canItem.setMaxHeight(height);
		canItem.setMaxWidth(length);

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

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	

}
