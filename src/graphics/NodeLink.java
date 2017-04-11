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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import services.Control;

public class NodeLink extends Group {

	private Line nodeLink;
	private TextField labelLineTF;
	
	private int[] changeIDs;
	private int[] artifactIDs;
	private int id;
	private Control control;
	public NodeLink(int ID, Control control) {
		super();
		this.control = control;
		this.id = ID;
		this.setVisible(false);
		nodeLink = new Line();
				
		this.getChildren().addAll(nodeLink);
		this.setOnMousePressed(circleOnMousePressedEventHandler);
		setId(Integer.toString(ID));

	}
	
	EventHandler<MouseEvent> circleOnMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			setDeleteArrow(t);
		}
	};

	public void setStart(Point2D startPoint) {

		nodeLink.setStartX(startPoint.getX());
		nodeLink.setStartY(startPoint.getY());
	}

	protected void setDeleteArrow(MouseEvent t) {
		if (t.getButton().equals(MouseButton.PRIMARY)) {
			if (t.getClickCount() == 2) {
				this.setVisible(false);
				control.deleteArrow(id,changeIDs[1], artifactIDs[1]);
			}
			
		}
		
		
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
