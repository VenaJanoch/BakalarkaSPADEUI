package services;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import tables.CriterionTable;

public class Alerts {

	public Alerts() {

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

	public static void showNoText(String name) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Insert error");
		alert.setHeaderText("No " + name + " is provided!");
		alert.setContentText("Please provide a "+ name +" in corresponding filed or create new segment!");
		alert.showAndWait();

	}

	public static void showSaveSegment() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Closing without save");
		alert.setHeaderText("Segment did not save!");
		alert.showAndWait();
	}


	public static void showCloseApp(Control control) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Closing APP");
		alert.setHeaderText("You closing a program");
		alert.setContentText("Would you save a project?.");

		ButtonType buttonTypeOne = new ButtonType("Save");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		    control.saveFile();
		}
	}

}
