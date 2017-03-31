package Services;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerts {

	public Alerts() {

	}

	public void showNoConfigAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("NO CONFIGURATION");
		alert.setContentText("You must choose configuration or create!");

		alert.showAndWait();
	}

	public void showNoAuthorAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("NO Author");
		alert.setContentText("You must choose Author or create!");

		alert.showAndWait();
	}
	
	public void showNoAssigneeAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("NO Assigne");
		alert.setContentText("You must choose Assignne or create!");

		alert.showAndWait();
	}
	
	public void showNoArtifactAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText("NO ARTIFACT");
		alert.setContentText("You must choose artifact or create!");

		alert.showAndWait();
	}
	
	

}
