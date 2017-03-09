package Grafika;

import java.io.IOException;
import java.util.UUID;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.When;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class NodeLink extends Group {

	private Line nodeLink;
	private TextField labelLineTF;

	public NodeLink(int ID) {
		super();
		// provide a universally unique identifier for this object
		this.setVisible(false);

		nodeLink = new Line();
		labelLineTF = new TextField("-");
		labelLineTF
				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
		labelLineTF.setFont(Font.font ("Verdana",15));
		labelLineTF.setMaxWidth(300);
		this.getChildren().addAll(nodeLink, labelLineTF);

		setId(Integer.toString(ID));

	}

	public void setStart(Point2D startPoint) {

		nodeLink.setStartX(startPoint.getX());
		nodeLink.setStartY(startPoint.getY());
	}

	public void setEnd(Point2D endPoint) {

		nodeLink.setEndX(endPoint.getX());
		nodeLink.setEndY(endPoint.getY());
		this.setVisible(true);
	}

	public void setTFCoordinate() {
		double x = (nodeLink.getStartX() + nodeLink.getEndX()) / 2;
		double y = (nodeLink.getStartY() + nodeLink.getEndY()) / 2;

		labelLineTF.setTranslateX(x);
		labelLineTF.setTranslateY(y);

	}

}
