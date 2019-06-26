package controllers.graphicsComponentsControllers;

import abstractControlPane.ControlPanel;
import graphics.controlPanelItems.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import services.ControlPanelLineObject;

/**
 * Trida predstavujici controller pro rizeni funkcnosti radku v editacnim panelu
 *
 * @author Václav Janoch
 */
public class ControlPanelLineController {

    /**
     * Kontrolery potrebne pro cinnost kontroleru
     **/
    private ControlPanelController controlPanelController;
    private ControlPanel controlPanel;

    /**
     * Instace radku v panelu
     **/
    private ControlPanelLine line;
    /**
     * Poradi radku v panelu
     **/
    private int lineIndex;

    /**
     * Poradi vybraneho prvku v ComboBoxu
     **/
    private int itemIndex = 0;
    /**
     * Seznam parametru ktere se mohou v radku vyskytovat
     **/
    private ObservableList<ControlPanelLineObject> paramList;
    private boolean isSetType = false;

    /**
     * Listener pro jednotlive komponenty
     **/
    private ChangeListener<Number> listener;

    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     *
     * @param line                   instace ControlPanelLine
     * @param controlPanel           ControlPanel ve kterem se radek nachazi
     * @param controlPanelController ControlPanelController pro rizeni panelu
     * @param paramList              seznam moznzych parametru radku
     * @param lineIndex              poradi radku v panelu
     */
    public ControlPanelLineController(ControlPanelLine line, ControlPanel controlPanel, ControlPanelController controlPanelController,
                                      ObservableList<ControlPanelLineObject> paramList, int lineIndex) {
        this.controlPanelController = controlPanelController;
        this.controlPanel = controlPanel;
        this.line = line;
        this.lineIndex = lineIndex;
        this.paramList = paramList;

    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */

    public ChangeListener<Number> comboBoxListener() {

        listener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                itemIndex = newValue.intValue();
                ControlPanelLineObject object = paramList.get(itemIndex);
                line.setType(object.getType());

            }
        };
        return listener;
    }

    /**
     * Metoda pro rozhodnuti ktery parametr radku bude zobrazen v panelu
     */
    public void setBoxToControlPanel() {
        ControlPanelLineObject object = paramList.get(itemIndex);
        controlPanelController.removeFromControlPanel(line, controlPanel.getControlPane());

        switch (object.getLineType()) {
            case Text:
                controlPanelController.setTextItemToControlPanel(controlPanel.getControlPane(), line.getTextItem(), 2, lineIndex);
                break;
            case Number:
                controlPanelController.setNumberItemToControlPanel(controlPanel.getControlPane(), line.getNumberItem(), 2, lineIndex);
                break;
            case Date:
                controlPanelController.setDateItemToControlPanel(controlPanel.getControlPane(), line.getDateItem(), 2, lineIndex);
                break;
            case ComboBox:
                line.createComboBoxItem(object.getSegmentsList());
                controlPanelController.setComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getComboBoxItem(), 2, lineIndex);
                break;
            case RelationComboBoxes:
                line.createRelationComboBoxItem(object.getSegmentsList(), object.getSegmentsList2());
                controlPanelController.setRelationComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getComboBoxItem(), line.getCheckComboBoxItem(), 2, lineIndex);
                break;
            case CheckBox:
                line.createCheckComboBoxItem(object.getSegmentsList());
                controlPanelController.setCheckComboBoxItemToControlPanel(controlPanel.getControlPane(), line.getCheckComboBoxItem(), 2, lineIndex);
                break;
            default:
        }
    }

    /**
     * Metoda pro ziskani indexu radku a zavolani metody pro rozhodnuti o zobrazeni radku
     *
     * @param index index vybraneho parametru v ComboBoxu
     */
    public void setBoxToControlPanel(int index) {
        itemIndex = index;
        if (paramList.size() != 0) {
            setBoxToControlPanel();
        }
    }

    /**
     * Metoda pro vykopirovani radku do noveho
     *
     * @param oldLine    instace kopirovaneho radku
     * @param paramIndex index parametru pro vyber
     */
    public void copyLine(ControlPanelLine oldLine, int paramIndex) {
        line.getParamBox().getSelectionModel().select(paramIndex);
        setBoxToControlPanel(paramIndex);
        setDataFromLine(oldLine);
    }

    /**
     * Metoda pro nastaveni dat do radkku v zavislosti na zvolenem typu parametru
     *
     * @param oldLine instance stareho radku
     */
    private void setDataFromLine(ControlPanelLine oldLine) {
        ControlPanelLineObject object = paramList.get(itemIndex);
        switch (object.getLineType()) {
            case Text:
                setDataToTextFieldLine(oldLine);
                break;
            case Date:
                setDataToDateFieldLine(oldLine);
                break;
            case ComboBox:
                setDataToComboBoxFieldLine(oldLine);
                break;
            case CheckBox:
                setDataToCheckComboBoxFieldLine(oldLine);
                break;
            default:
        }
    }


    /**
     * Metoda pro nastaveni dat do komponenty CheckComboBox
     *
     * @param oldLine instace stareho radku
     */
    private void setDataToCheckComboBoxFieldLine(ControlPanelLine oldLine) {
        CheckComboBoxItem oldItem = oldLine.getCheckComboBoxItem();
        CheckComboBoxItem item = line.getCheckComboBoxItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.selectItemsInComboBox(oldItem.getChoosedIndicies());
    }

    /**
     * Metoda pro nastaveni dat do komponenty ComboBox
     *
     * @param oldLine instace stareho radku
     */
    private void setDataToComboBoxFieldLine(ControlPanelLine oldLine) {
        ComboBoxItem oldItem = oldLine.getComboBoxItem();
        ComboBoxItem item = line.getComboBoxItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.selectItemInComboBox(oldItem.getItemIndex());
    }

    /**
     * Metoda pro nastaveni dat do komponenty DatePicker
     *
     * @param oldLine instace stareho radku
     */
    private void setDataToDateFieldLine(ControlPanelLine oldLine) {
        DateItem oldItem = oldLine.getDateItem();
        DateItem item = line.getDateItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.setDateToPicker(oldItem.getDateFromDatePicker());
    }

    /**
     * Metoda pro nastaveni dat do komponenty TextField
     *
     * @param oldLine instace stareho radku
     */
    private void setDataToTextFieldLine(ControlPanelLine oldLine) {

        TextFieldItem oldItem = oldLine.getTextItem();
        TextFieldItem item = line.getTextItem();

        item.setIndicator(oldItem.getIndicatorIndex());
        item.getItemTF().setText(oldItem.getTextFromTextField());

    }
}
