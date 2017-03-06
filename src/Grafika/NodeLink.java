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
import javafx.geometry.Point2D;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

public class NodeLink extends Line {


	Line node_link;

	public NodeLink(int ID) {
		super();
		// provide a universally unique identifier for this object
		this.setVisible(false);
		setId(Integer.toString(ID));

		}

	public void setStart(Point2D startPoint) {

		this.setStartX(startPoint.getX());
		this.setStartY(startPoint.getY());
		System.out.println("Osmej");
	}

	public void setEnd(Point2D endPoint) {

		this.setEndX(endPoint.getX());
		this.setEndY(endPoint.getY());
		this.setVisible(true);
		System.out.println("9");
	}

	public void bindEnds(CanvasItem target) {

		System.out.println("11");
	}

}
