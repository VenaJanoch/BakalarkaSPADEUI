package graphics;

import java.io.IOException;
import java.util.UUID;

import SPADEPAC.Artifact;
import SPADEPAC.Change;
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
	
	private int[] changeIDs;
	private int[] artifactIDs;

	public NodeLink(int ID) {
		super();
		
		this.setVisible(false);
		nodeLink = new Line();
		
//		labelLineTF = new TextField("-");
//		labelLineTF
//				.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
//		labelLineTF.setFont(Font.font ("Verdana",15));
//		labelLineTF.setMaxWidth(300);
		
		this.getChildren().addAll(nodeLink);

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

	
	
	/*** Getrs and Setrs ***/

	public int[] getChange() {
		return changeIDs;
	}

	public void setChange(int[] changeIDs) {
		this.changeIDs = changeIDs;
	}

	public int[] getArtifact() {
		return artifactIDs;
	}

	public void setArtifact(int[] artifactIDs) {
		this.artifactIDs = artifactIDs;
	}

}
