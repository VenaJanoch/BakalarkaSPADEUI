package graphics.panels;

import controllers.*;
import controllers.formControllers.FormController;
import controllers.graphicsComponentsControllers.CanvasController;
import controllers.graphicsComponentsControllers.DrawerPanelController;
import controllers.graphicsComponentsControllers.SelectItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import services.Constans;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Třída tvořící panel tlačítek
 *
 * @author Václav Janoch
 */
public class DragAndDropPanel extends BorderPane {

    /**
     * Globální proměnné třídy
     */
    private DragAndDropItemPanel items;
    private Button[] addButtons;
    private Button projectButton;
    private Button projectConfirmButton;
    private HBox buttonBox;

    private FormController formController;
    private WindowController windowController;
    private CanvasController canvasController;
    private SelectItemController selectItemController;
    private ComboBox<String> formBox;

    /**
     * Konstruktor třídy,
     * Zinicializuje globální proměnné třídy
     * Nastavi prvky do panelu
     * @param formController instace tridy FormController
     * @param windowController instace tridy WindowController
     * @param canvasController instace tridy CanvasController
     * @param drawerPanelController instace tridy DrawePanelController
     * @param selectItemController intanece tridy selctItemController
     */

    public DragAndDropPanel(FormController formController, WindowController windowController, CanvasController canvasController,
                            DrawerPanelController drawerPanelController, SelectItemController selectItemController) {

        super();
        this.formController = formController;
        this.windowController = windowController;
        this.canvasController = canvasController;
        this.selectItemController = selectItemController;

        this.projectButton = new Button();
        FileInputStream input = null;
        try {
            input = new FileInputStream(Constans.PROJECT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        Background background = new Background(backgroundImage);
        projectButton.setMinWidth(image.getWidth());
        projectButton.setMinHeight(image.getHeight());
        projectButton.setBackground(background);


        this.projectConfirmButton = new Button("Confirm project");
        this.setPrefWidth(Constans.width);
        this.buttonBox = new HBox(5);
        this.setPadding(new Insets(10));

        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 146, 202), CornerRadii.EMPTY, Insets.EMPTY)));
        this.setId("panelTable");

        ObservableList<String> itemsList = FXCollections.observableArrayList();

        for (String item : Constans.addItemNames) {
            itemsList.add(item);
        }

        this.formBox = new ComboBox<>(itemsList);
        selectItemController.setBox(formBox);

        items = new DragAndDropItemPanel(canvasController, Constans.projectDragTextIndexs, drawerPanelController);


        addButtons = new Button[Constans.addButtonCount];
        projectButton.setOnAction(event -> {
            formController.showEditControlPanel(0);

        });

        projectConfirmButton.setOnAction(event -> windowController.showConfirmWindow());


        HBox projectPanel = new HBox(10);
        SplitPane splitPane = new SplitPane();
        splitPane.setMaxHeight(projectButton.getMaxHeight());
        projectPanel.getChildren().addAll(projectButton, splitPane);
        projectPanel.setMinWidth(150);
        projectPanel.setAlignment(Pos.CENTER_RIGHT);
        this.setLeft(projectPanel);
        this.setCenter(items);
        HBox bottomPanel = new HBox(10);
        Label tableLabel = new Label("Review tables: ");
        tableLabel.setTextFill(Color.WHITE);
        bottomPanel.getChildren().addAll(tableLabel, formBox, projectConfirmButton);

        HBox.setMargin(projectConfirmButton, new Insets(0, 0, 0, 700));
        this.setBottom(bottomPanel);
        //this.setRight(projectConfirmButton);
        this.setAlignment(formBox, Pos.BOTTOM_LEFT);
        BorderPane.setMargin(bottomPanel, new Insets(0, 0, 0, 160));
        BorderPane.setMargin(items, new Insets(0, 0, 0, 50));
    }


    /**
     * Pomocná metoda pro vytvoření tlačítek
     */
    public void createButtons() {

        for (int i = 0; i < addButtons.length; i++) {
            addButtons[i] = new Button(Constans.addButtonsNames[i]);

        }
    }

    /**
     * Pomocná metoda pro přidání reakce na stisk tlačítka
     */

    public void createAction() {
        addButtons[0].setOnAction(event -> formController.showForm(Constans.projectFormIndex));
        addButtons[1].setOnAction(event -> formController.showForm(Constans.milestoneFormIndex));
        addButtons[2].setOnAction(event -> formController.showForm(Constans.roleFormIndex));
        addButtons[3].setOnAction(event -> formController.showForm(Constans.cprFormIndex));
        addButtons[4].setOnAction(event -> formController.showForm(Constans.priorityFormIndex));
        addButtons[5].setOnAction(event -> formController.showForm(Constans.severityFormIndex));
        addButtons[6].setOnAction(event -> formController.showForm(Constans.relationFormIndex));
        addButtons[7].setOnAction(event -> formController.showForm(Constans.resolutionormIndex));
        addButtons[8].setOnAction(event -> formController.showForm(Constans.statusFormIndex));
        addButtons[9].setOnAction(event -> formController.showForm(Constans.wuTypeFormIndex));
        addButtons[10].setOnAction(event -> formController.showForm(Constans.branchIndex));
        addButtons[11].setOnAction(event -> formController.showForm(Constans.configurationFormIndex));
    }


    /*** Getrs and Setrs */
    public DragAndDropItemPanel getItems() {
        return items;
    }

    public void setItems(DragAndDropItemPanel items) {
        this.items = items;
    }

}
