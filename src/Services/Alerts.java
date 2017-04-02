package Services;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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

	public static void showNoNameAlert() {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Insert error");
		alert.setHeaderText("No name is provided!");
		alert.setContentText("Please provide a name in corresponding filed!");
		alert.showAndWait();

	}

	public static void showNoItemsDeleteAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Deleting items");
		alert.setHeaderText("No items for deleting were selected!");
		alert.setContentText("Please select items for deleting.");
		alert.show();
	}

	public static void showDeleteItemAlert(TableView table, ObservableList selection) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<CriterionTable>(selection));
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> {
			table.getItems().removeAll(selection);
			table.getSelectionModel().clearSelection();
		});

	}

	public static void showNoDescription() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Insert error");
		alert.setHeaderText("No description is provided!");
		alert.setContentText("Please provide a description in corresponding filed!");
		alert.showAndWait();

	}

}
