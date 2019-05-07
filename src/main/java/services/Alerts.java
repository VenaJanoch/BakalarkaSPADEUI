package services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

import model.FileManipulator;
import tables.*;

public class Alerts {

    FileManipulator fileManipulator;

    public Alerts(FileManipulator fileManipulator) {
        this.fileManipulator = fileManipulator;
    }

    /**
     * Alert s informací o chybě při validaci procesu
     *
     * @param error část chybového hlášení
     */
    public static void showValidationError(String error) {


    }

    /**
     * Alert s informací o nalezené chybě v procesu, při ukládání, Umoznuje
     * vyber ulozeni s chybou nebo neulození
     *
     * @param error část chybového hlášení
     * @return boolean info zvolené volbě
     */
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

    /**
     * Alert s informací o úspěšné validaci
     */
    public static void showValidationOK() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Validation OK");
        alert.setHeaderText("Validation OK!");
        alert.setContentText("Validation is OK");

        alert.showAndWait();

    }

    /**
     * Alert s informací o nevybrání, žádné položky z tabulky pro smazání
     */
    public static void showNoItemsDeleteAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Deleting items");
        alert.setHeaderText("No items for deleting were selected!");
        alert.setContentText("Please select items for deleting.");
        alert.showAndWait();
    }


    public static boolean showDeleteItemCascadeAlert(ArrayList<BasicTable> selection, Map<Integer, ArrayList<TableToObjectInstanc>> mapper) {

        ObservableList<String> deleteList = createDeleteObservableList(selection, mapper);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Deleting selection");
        alert.setHeaderText("Do you want to delete selected elements?");
        alert.setGraphic(new ListView<>(deleteList));

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }

        return false;

    }

    private static ObservableList<String> createDeleteObservableList(ArrayList<BasicTable> selection, Map<Integer, ArrayList<TableToObjectInstanc>> mapper) {
        ObservableList<String> deleteList = FXCollections.observableArrayList();
        for (BasicTable table : selection) {
            deleteList.add(table.getAlias());
            if (mapper.containsKey(table.getId())) {
                deleteList.addAll(mapper.get(table.getId()).toString());
            }
        }
        return deleteList;
    }

    private static ObservableList<String> createDeleteObservableList(ObservableList<String> deleteList, ArrayList<Integer> indices, Map<Integer, ArrayList<TableToObjectInstanc>> mapper) {

        for (int i : indices) {
            if (mapper.containsKey(i)) {
                deleteList.addAll(mapper.get(i).toString());
            }
        }
        return deleteList;
    }

    public static boolean showDeleteItemCascadeAlert(ObservableList<String> deleteList) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setGraphic(new ListView<>(deleteList));
        return deleteItemDialog(alert);
    }

    public static boolean showDeleteItemCascadeAlert(ArrayList<BasicTable> deleteList) {
        ObservableList list = FXCollections.observableList(deleteList);
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setGraphic(new ListView<BasicTable>(list));
        return deleteItemDialog(alert);
    }

    private static boolean deleteItemDialog(Alert alert) {
        alert.setTitle("Deleting selection");
        alert.setHeaderText("Do you want to delete selected elements?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        }

        return false;

    }

    //(ArrayList<BasicTable> selection, Map<Integer, ArrayList<TableToObjectInstanc>> mapper ) {

    /**
     * Alert s infomací o výběru prvků z tabulky Criterii pro smazání a možností
     * zrušení akce.
     *
     * @param selection vybrané prvky
     * @return smazané prvky
     */
    public static boolean showDeleteItemCascadeAlert(ArrayList<BasicTable> selection, ArrayList<Map<Integer, ArrayList<TableToObjectInstanc>>> mappers) {
        ObservableList<String> deleteList = FXCollections.observableArrayList();
        for (BasicTable table : selection) {
            deleteList.add(table.getAlias());
            for (Map<Integer, ArrayList<TableToObjectInstanc>> map : mappers) {
                if (map.containsKey(table.getId())) {
                    deleteList.addAll(map.get(table.getId()).toString());
                }
            }
        }
        return showDeleteItemCascadeAlert(deleteList);
    }


    /**
     * Alert s infomací o zavření formuláře bez uložení a možnostmi uložení
     * formuláře, případně zrušení akce
     *
     * @return smazané prvky
     */
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
        } else if (result.get() == buttonOK) {
            return 0;
        }

        return -1;

    }

    public static void showWrongNumberFormat(String fieldName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Insert error");
        alert.setHeaderText("Wrong number input!");
        alert.setContentText("Please provide a number in " + fieldName);
        alert.showAndWait();
    }


    public static void showNumberOffInterval(int minValue, int maxValue) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Insert error");
        alert.setHeaderText("Wrong number input!");
        alert.setContentText("Please provide a number in interval: <" + minValue + "," + maxValue + ">");
        alert.showAndWait();
    }

    public static void showWrongDoubleFormat() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Insert error");
        alert.setHeaderText("Wrong real number input!");
        alert.setContentText("Please provide a number in 'x.xxx' or 'xxx' format");
        alert.showAndWait();
    }

    public static void showDoubleOffInterval(double minValue, double maxValue) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Insert error");
        alert.setHeaderText("Wrong number input!");
        alert.setContentText("Please provide a number in interval: <" + minValue + "," + maxValue + ">");
        alert.showAndWait();
    }

    public static void SQLConnectionError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Connection Error");
        alert.setHeaderText("Conntection to Database is not ");
        alert.setContentText("Try again");
        Optional<ButtonType> result = alert.showAndWait();


    }

    public static void SQLLogError() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Log Error");
        alert.setHeaderText("Your name or password is bad");
        alert.setContentText("Try again");
        Optional<ButtonType> result = alert.showAndWait();


    }


    /**
     * Alert s infomací o ukončení aplikace s možností uložení procesu, přídně
     * zrušením akce
     */
    public int showCloseApp() {
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
            boolean isSave = fileManipulator.saveFile();
            if (isSave) {
                return 0;
            } else {
                return -1;
            }
        } else if (result.get() == buttonOK) {
            return 1;
        }

        return -1;
    }

    /**
     * Alert s informací o špatně vkládaném prvku na plátno
     *
     * @param segment    SegmentType
     * @param canvasType CanvasType
     */
    public static void badCopyItem(SegmentType segment, CanvasType canvasType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Copy error");
        alert.setHeaderText("Bad segment " + segment + " for this type canvas !");
        alert.setContentText("Please paste item in corresponding canvas!");
        alert.showAndWait();

    }

    /**
     * Alert s informací o pokusu propojení nevyplněného Work Unitu
     */


    public static void showNoWorkUnit() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Uncomplete Workunit segment !");
        alert.setContentText("Please fill this item!");
        alert.showAndWait();
    }

    public static void showConfigurationAsStartElement() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Configuration must not be start element!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }

    public static void showBadCommitRelation(SegmentType segmentType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Commit could not be add" + segmentType.name() + "!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }

    public static void showBadCommitedConfigurationRelation(SegmentType segmentType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Commited Configuration could not be add" + segmentType.name() + "!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }

    public static void showBadConfigurationRelation(SegmentType segmentType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Configuration could not be add to" + segmentType.name() + "!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }

    public static void showBadArtifactRelation(SegmentType segmentType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Artifact could not be add to" + segmentType.name() + "!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }

    public static void showBadRoleRelation(SegmentType segmentType) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Link error");
        alert.setHeaderText("Person could not be add to" + segmentType.name() + "!");
        alert.setContentText("Please choose another element!");
        alert.showAndWait();
    }
}
