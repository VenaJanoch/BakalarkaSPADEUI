package services;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import java.util.List;

import tables.BranchTable;
import tables.CPRTable;
import tables.ClassTable;
import tables.ConfigTable;
import tables.CriterionTable;
import tables.MilestoneTable;
import tables.RoleTable;
import tables.TagTable;

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

	public static void showWrongEstimatedTimeAlert() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Insert error");
		alert.setHeaderText("Wrong Estimated time!");
		alert.setContentText("Please provide a number in Estimated time filed! \n" + "in format 1; 1.1;");
		alert.showAndWait();

	}

	public static void showValidationError(String error) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText("Validation error!");
		alert.setContentText("Validation massage: " + error);

		alert.showAndWait();

	}

	public static boolean showValidationErrorSave(String error) {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Validation error");
		alert.setHeaderText("Validation error!");
		alert.setContentText("Validation massage: " + error);

		ButtonType buttonTypeOne = new ButtonType("Accept");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			return true;
		}

		return false;

	}

	public static void showValidationOK() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Validation OK");
		alert.setHeaderText("Validation OK!");
		alert.setContentText("Validation is OK");

		alert.showAndWait();

	}

	public static void showNoItemsDeleteAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Deleting items");
		alert.setHeaderText("No items for deleting were selected!");
		alert.setContentText("Please select items for deleting.");
		alert.showAndWait();
	}

	public static ObservableList<ClassTable> showDeleteItemAlert(TableView table, ObservableList selection) {
		List<ClassTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<CriterionTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
		
			table.getSelectionModel().clearSelection();
		}

		return selection;

	}
	
	public static ObservableList<ConfigTable> showDeleteItemConfigAlert(TableView<ConfigTable> table, ObservableList<ConfigTable> selection) {
		List<ConfigTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<ConfigTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
		
			table.getSelectionModel().clearSelection();
		}

		return selection;

	}
	
	public static ObservableList<CriterionTable> showDeleteItemCriterionAlert(TableView<CriterionTable> table,
			ObservableList<CriterionTable> selection) {
		
		List<CriterionTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<CriterionTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
	
			table.getSelectionModel().clearSelection();
		}

		return selection;

	}
	
	public static ObservableList<MilestoneTable> showDeleteItemMilestoneAlert(TableView<MilestoneTable> table,
			ObservableList<MilestoneTable> selection) {
		
		List<MilestoneTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<MilestoneTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
	
			table.getSelectionModel().clearSelection();
		}

		return selection;

	}

	public static ObservableList<CPRTable> showDeleteItemCPRAlert(TableView<CPRTable> table,
			ObservableList<CPRTable> selection) {
		
		List<CPRTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<CPRTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
		
			table.getSelectionModel().clearSelection();
		}

		return selection;

	}

	public static ObservableList<BranchTable> showDeleteItemBranchAlert(TableView<BranchTable> table,
			ObservableList<BranchTable> selection) {

		List<BranchTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<BranchTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
		
			table.getSelectionModel().clearSelection();
		}

		return selection;
	}
	
	public static ObservableList<RoleTable> showDeleteItemRoleAlert(TableView<RoleTable> table,
			ObservableList<RoleTable> selection) {

		List<RoleTable> list = null;
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<RoleTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
		
			table.getSelectionModel().clearSelection();
		}

		return selection;
	}

	public static ObservableList<TagTable> showDeleteItemTagAlert(TableView<TagTable> table, ObservableList selection) {

		List<TagTable> list = null;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Deleting selection");
		alert.setHeaderText("Do you want to delete selected elements?");
		alert.setGraphic(new ListView<CriterionTable>(selection));

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {

			list = table.getSelectionModel().getSelectedItems();

			table.getItems().removeAll(selection);
			
			table.getSelectionModel().clearSelection();
		}

		return selection;
	}

	public static void showNoText(String name) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Insert error");
		alert.setHeaderText("No " + name + " is provided!");
		alert.setContentText("Please provide a " + name + " in corresponding field or create new segment!");
		alert.showAndWait();

	}

	public static int showSaveSegment() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Closing without save");
		alert.setHeaderText("Segment did not save!");
		
		ButtonType buttonSave = new ButtonType("Save");
		ButtonType buttonOK = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonSave, buttonOK, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonSave) {
			return 1;
		}else if (result.get() == buttonOK) {
			return 0;
		} 
		
		return -1;
		
	}

	public static int showCloseApp(Control control) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Closing APP");
		alert.setHeaderText("You closing a program");
		alert.setContentText("Would you save a project?.");

		ButtonType buttonTypeOne = new ButtonType("Save");
		ButtonType buttonOK = new ButtonType("Close", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(buttonTypeOne, buttonOK, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeOne) {
			control.saveFile();
			return 0;
		}else if(result.get() == buttonOK){
			return 1;
		}
		
		return -1;
	}

	public static void badCopyItem(SegmentType segment, CanvasType canvasType) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Copy error");
		alert.setHeaderText("Bad segment " + segment + " for this type canvas !");
		alert.setContentText("Please paste item in corresponding segment!");
		alert.showAndWait();
		
	}

	public static void showNoWorkUnit() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Link error");
		alert.setHeaderText("Uncomplete Workunit segment !");
		alert.setContentText("Please fill this item!");
		alert.showAndWait();
		
	}

	

}
