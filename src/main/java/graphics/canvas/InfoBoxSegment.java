package graphics.canvas;

import graphics.canvas.CanvasItem;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import services.Constans;
import services.SegmentType;

/**
 * Třída definující ohraničení prvku plátna
 *
 * @author Václav Janoch
 */
public class InfoBoxSegment extends Group {
    /**
     * Glolobální proměnné třídy
     **/
    private Rectangle topRectangle;
    private Rectangle botomRectangle;
    private Text segmentName;
    private InstanceCount instanceCount;
    private Text name;
    private double length;
    private CanvasItem canItem;
    private double height;
    private double totalHeight;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy a přidá prvku do
     * panelu
     *
     * @param canItem CanvasItem
     * @param type    SegmentType
     * @param name    {@link String}
     */
    public InfoBoxSegment(CanvasItem canItem, SegmentType type, String name, String instanceCount) {
        super();
        this.canItem = canItem;
        this.name = new Text(name);

        this.segmentName = new Text(type.name());
        this.instanceCount = new InstanceCount(instanceCount);
        this.instanceCount.setVisible(false);
        this.length = Constans.minCanvasItemWidth;

        this.topRectangle = new Rectangle(length, Constans.infoBoxHeightRectangle);
        this.botomRectangle = new Rectangle(length, Constans.infoBoxHeightRectangle);
        height = Constans.infoBoxHeight;
        this.getChildren().addAll(topRectangle, botomRectangle, this.segmentName, this.name, this.instanceCount);

        createBlock();

    }

    /**
     * Metoda pro nastevní barvy vykreslených obdelníků
     *
     * @param color Color
     */
    public void setRectangleColor(Color color) {

        topRectangle.setStroke(color);
        botomRectangle.setStroke(color);
    }

    /**
     * Metoda pro nastavení pozice, barvy pozadí, rámečku a velikosti
     * vykresleným obdelníkům
     */
    private void createBlock() {

        setRectangleColor(Constans.rectangleBorderColor);

        this.botomRectangle.setFill(Color.rgb(174, 219, 241));
        this.topRectangle.setFill(Color.rgb(216, 217, 217));

        topRectangle.setStrokeWidth(1);
        botomRectangle.setStrokeWidth(1);

        topRectangle.setTranslateX(0);
        topRectangle.setTranslateY(0);
        botomRectangle.setTranslateX(0);
        botomRectangle.setTranslateY(20);

        segmentName.setTranslateX(Constans.offset);
        segmentName.setTranslateY(13);

        name.setTranslateX(Constans.offset);
        name.setTranslateY(33);

        totalHeight = topRectangle.getHeight() + botomRectangle.getHeight();
        instanceCount.setPosition(botomRectangle.getWidth(), totalHeight);

        height = botomRectangle.getHeight();

    }

    /**
     * Nastaví jméno objektu a spočte z něho velikost spodní obdelníku
     *
     * @param nameStr String
     */
    public void setNameText(String nameStr) {

        Text testname = new Text(nameStr);

        double width = testname.getLayoutBounds().getWidth();

        name.setText(nameStr);
        name.setWrappingWidth(Constans.maxCanvasItemWidth - Constans.offset);

        if (width > length && width < (Constans.maxCanvasItemWidth - Constans.offset)) {
            repaintBox(width, height);

        } else if (width > Constans.maxCanvasItemWidth) {
            int count = countHeightBotomRectangle((int) width);
            repaintBox(Constans.maxCanvasItemWidth, count * height);
        }

    }

    /**
     * Pomocná metoda pro výpočet výšky spodního rámečku
     *
     * @param width int
     * @return int výška rámečku
     */
    private int countHeightBotomRectangle(int width) {

        int row = width / (int) (Constans.maxCanvasItemWidth - Constans.offset);

        return row + 1;
    }

    /**
     * Přenastaví velikosti obdelníků a velikost CanvasItem
     *
     * @param width  double
     * @param height double
     */
    public void repaintBox(double width, double height) {

        length = width + Constans.offset;

        topRectangle.setWidth(length);
        botomRectangle.setWidth(length);
        botomRectangle.setHeight(height);

        canItem.setMaxHeight(height);
        canItem.setMaxWidth(length);
        totalHeight = topRectangle.getHeight() + botomRectangle.getHeight();
        instanceCount.setPosition(length, totalHeight);
    }

    /**
     * Getrs and Setrs
     **/

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

    public InstanceCount getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(String instanceCount) {
        this.instanceCount.setInstaceCount(instanceCount);
        this.instanceCount.setVisible(true);
    }

    public double getTotalHeight() {
        return totalHeight;
    }
}
