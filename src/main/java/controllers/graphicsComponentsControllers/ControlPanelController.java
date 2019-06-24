package controllers.graphicsComponentsControllers;

import abstractControlPane.ControlPanel;
import graphics.controlPanelItems.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.layout.GridPane;
import services.ClassSwitcher;
import services.ControlPanelLineObject;
import services.ParamType;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici controller pro editacni panel
 *
 * @author Václav Janoch
 */
public class ControlPanelController {

    /**Graficke prvky vyuzivane v panelu**/
    private ArrayList<ControlPanelLine> controlPanelLines;
    private ControlPanelLine radioButtonLine;
    private ControlPanelLine radioExistButtonLine;
    private Label countLB;
    private ControlPanelLine countLine;
    private ControlPanelLine classLine;
    private ControlPanelLine superClassLine;
    private Button staticButton;

    /**Logicke promenne pro rizeni prvku v panelu**/
    private boolean isRadioButtonLine;
    private boolean isSecondRadioButtonLine;
    private boolean isCountLine;
    private boolean isStaticButton;
    private boolean isClass;

    /**Promenne uchovavajici stav v class a superClass ComboBoxu**/
    private int classIndex;
    private int superIndex;

    /**Promenna pro uchovani poctu radku**/
    private int lineCount;
    /**Promenna pro uchovani poctu statickych prvku**/
    private int staticObjectCount;
    /**Promenna pro mechanismus nastaveni Class a SuperClass**/
    private ClassSwitcher switcher;


    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne tridy
     * @param controlPanelLines seznam radku ktere bude mozne v panelu vybrat
     */
    public ControlPanelController(ArrayList<ControlPanelLine> controlPanelLines) {
        switcher = new ClassSwitcher();
        this.controlPanelLines = controlPanelLines;
        this.countLB = new Label("Instance count: ");
        resetPanel();

    }

    /**
     * Metoda pro kontorlu vyplneneho textoveho pole
     * @param textFieldItem  prvek TextFiel ze ktereho se ziskava vystup
     * @return upraveny retezec
     */
    public String checkValueFromTextItem(TextFieldItem textFieldItem) {
        String desc = "null*";
        if (textFieldItem.getTextFromTextField() != null) {
            desc = textFieldItem.getTextFromTextField();
        }
        return desc;
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu a naplneni techto radku textovymi hodnotami z datoveho modelu
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     * @param values hodnoty pro vlozeni
     * @param indicatorList indexi pro nastaveni comboboxu s ukazateli rovnosti
     * @param index poradi seznamu s daty v poli
     */
    public void setValueTextField(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                  ParamType type, List<String>[] values, List<Integer> indicatorList, int index) {
        int i = 0;
        if (indicatorList.size() != 0) {
            for (String value : values[index]) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillTextLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu a naplneni techto radku  ciselnymi hodnotami z datoveho modelu
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     * @param values hodnoty pro vlozeni
     * @param indicatorList indexi pro nastaveni comboboxu s ukazateli rovnosti
     * @param index poradi seznamu s daty v poli
     */
    public void setValueNumberField(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                  ParamType type, List<String>[] values, List<Integer> indicatorList, int index) {
        int i = 0;
        if (indicatorList.size() != 0) {
            for (String value : values[index]) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillNumberLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu Work Unit
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     */
    public void setValueRelationBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                    ParamType type, ArrayList<Integer> relation, ArrayList<ArrayList<Integer>> workUnit) {
        int i = 0;
        for (int value : relation) {
            createNewLine(controlPanel, lineList);
            ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
            line.fillRelationComboBoxLine(value, workUnit.get(i), type);
            i++;
            incrementLineCounter();
        }
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu s komponenoutou ComboBox a naplneni techto radku daty z datoveho modelu
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     * @param values hodnoty pro vlozeni
     * @param indicatorList indexi pro nastaveni comboboxu s ukazateli rovnosti
     */
    public void setValueComboBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                 ParamType type, ArrayList<Integer> values, List<Integer> indicatorList) {
        int i = 0;
        if (indicatorList.size() != 0) {
            for (int value : values) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillComboBoxLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu s komponenoutou DatePicker a naplneni techto radku daty z datoveho modelu
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     * @param values hodnoty pro vlozeni
     * @param indicatorList indexi pro nastaveni comboboxu s ukazateli rovnosti
     */
    public void setValueDatePicker(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                   ParamType type, ArrayList<LocalDate> values, List<Integer> indicatorList) {
        int i = 0;

        if (indicatorList.size() != 0) {
            for (LocalDate value : values) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillDateLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }

    /**
     * Metoda pro vytvoreni novych  radku v panelu s komponenoutou CheckComboBox a naplneni techto radku daty z datoveho modelu
     * @param controlPanel instace ControlPanel
     * @param lineList seznam moznych typu radku
     * @param type Typ parametru radku
     * @param values hodnoty pro vlozeni
     * @param indicatorList indexi pro nastaveni comboboxu s ukazateli rovnosti
     */
    public void setValueCheckComboBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                      ParamType type, ArrayList<ArrayList<Integer>> values, List<Integer> indicatorList) {
        int i = 0;
        if (values != null) {
            for (List<Integer> value : values) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillCheckComboBoxLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }


    /**
     * Metoda pro nastaveni boxu s ukazateli nerovnosti
     * @param controlPane controlni panel pro vlozeni
     * @param line radek pro vlozeni
     */
    public void setParamBoxToControlPanel(GridPane controlPane, ControlPanelLine line) {
        controlPane.add(line.getExitButton(), 0, lineCount);
        controlPane.add(line.getParamBox(), 1, lineCount);
    }


    /**
     * Metoda pro pridani ComboBoxuDoControlniho panelu
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param item ComboBoxItem
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setComboBoxItemToControlPanel(GridPane controlPane, ComboBoxItem item, int startColomIndex, int lineIndex) {
        controlPane.add(item.getIndicatorCB(), startColomIndex, lineIndex);
        controlPane.add(item.getItemCB(), startColomIndex + 1, lineIndex);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineIndex);
    }

    /**
     * Metoda pro pridani ComboBoxuDoControlniho panelu
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param workUnitItem ComboBoxItem
     * @param relationItem CheckComboBox pro vyber relace
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setRelationComboBoxItemToControlPanel(GridPane controlPane, ComboBoxItem relationItem, CheckComboBoxItem workUnitItem, int startColomIndex, int lineIndex) {
        controlPane.add(relationItem.getItemCB(), startColomIndex, lineIndex);
        controlPane.add(workUnitItem.getItemCB(), startColomIndex + 1, lineIndex);
        controlPane.add(relationItem.getItemButton(), startColomIndex + 2, lineIndex);
    }

    /**
     * Metoda pro pridani ComboBoxu do Controlniho panelu
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param item DateItem
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setDateItemToControlPanel(GridPane controlPane, DateItem item, int startColomIndex, int lineIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineIndex);
        controlPane.add(item.getItemDate(), startColomIndex + 1, lineIndex);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineIndex);
    }

    /**
     * Metoda pro pridani Textove komponenty do Controlniho panelu s vlastnostmi cisla
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param item TextFieldItem
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setNumberItemToControlPanel(GridPane controlPane, TextFieldItem item, int startColomIndex, int lineIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineIndex);
        controlPane.add(item.getItemTF(), startColomIndex + 1, lineIndex);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineIndex);
    }
    /**
     * Metoda pro pridani Textove komponenty do Controlniho panelu s vlastnostmi textu
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param item TextFieldItem
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setTextItemToControlPanel(GridPane controlPane, TextFieldItem item, int startColomIndex, int lineIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineIndex);
        controlPane.add(item.getItemTF(), startColomIndex + 1, lineIndex);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineIndex);
    }

    /**
     * Metoda pro pridani CheckComboBox komponenty do Controlniho panelu s vlastnostmi textu
     * @param controlPane kontrolni panel do ktereho se ma kompnenta pridat
     * @param item TextFieldItem
     * @param startColomIndex pocatecni sloupec pro vlozeni
     * @param lineIndex radek pro vlozeni
     */
    public void setCheckComboBoxItemToControlPanel(GridPane controlPane, CheckComboBoxItem item, int startColomIndex, int lineIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineIndex);
        controlPane.add(item.getItemCB(), startColomIndex + 1, lineIndex);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineIndex);
    }

    /**
     * Metoda pro nastaveni listeneru skupine RadioBoxu
     * @return
     */
    public ChangeListener<Toggle> radioButtonGroupListener() {
        ChangeListener<Toggle> listener = new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = radioButtonLine.getRadioButtonItem().getYesRb();

            }
        };
        return listener;
    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Class. Zavolá
     * metody pro mapování Class na Super Class
     */

    public ChangeListener<Number> getClassListener(SegmentType segmentType) {

        ChangeListener<Number> classListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                classIndex = newValue.intValue();
                superIndex = switcher.ClassToSuperClass(segmentType, classIndex);
                if (superIndex == -1) {

                    superClassLine.getComboBoxItem().selectItemInComboBox(superIndex);
                    superClassLine.lockComboBox();
                    superIndex = 0;
                } else {
                    superClassLine.getComboBoxItem().selectItemInComboBox(superIndex);
                    superClassLine.unlockComboBox();
                }
            }
        };

        return classListener;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Super Class
     */

    public ChangeListener<Number> getSuperClassListener() {
        ChangeListener<Number> superListener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                superIndex = newValue.intValue();
            }
        };
        return superListener;
    }

    /**
     * Metoda pro vytvoreni noveho radku a prekopirovani dat z predchoziho radku
     * @param line instance noveho radku
     * @param controlPanel controlni panel pro vlozeni
     * @param lineList seznam parametru obsazenych v panelu
     * @param paramIndex index parametru pro kopirovani
     */
    public void copyLine(ControlPanelLine line, ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList, int paramIndex) {
        incrementLineCounter();
        ControlPanelLineController copyLine = createNewLine(controlPanel, lineList);
        copyLine.copyLine(line, paramIndex);
        shiftStaticObjects(controlPanel);
    }

    /**
     * Metoda pro vytvoreni noveho radku bez volby existence
     * @param controlPane controlni panel pro vlozeni
     * @param lineList seznam parametru obsazenych v panelu
     * @return ControlPanelLineController s konkretnim radkem
     */
    public ControlPanelLineController createNewLineWithExist(ControlPanel controlPane, ObservableList<ControlPanelLineObject> lineList) {
        controlPane.getControlPane().getChildren().clear();
        setExistRadioButton(controlPane, 1);
        controlPane.setAliasToPanel();
        return createNewLine(controlPane, lineList);
    }

    /**
     * Metoda pro vytvoreni noveho radku s volbou existence
     * @param controlPane controlni panel pro vlozeni
     * @param lineList seznam parametru obsazenych v panelu
     * @return ControlPanelLineController s konkretnim radkem
     */
    public ControlPanelLineController createNewLine(ControlPanel controlPane, ObservableList<ControlPanelLineObject> lineList) {

        ControlPanelLine line = new ControlPanelLine(lineList, controlPane, this, lineCount);
        setParamBoxToControlPanel(controlPane.getControlPane(), line);
        controlPanelLines.add(line);
        shiftStaticObjects(controlPane);
        return line.getControlPanelLineController();
    }

    /**
     * Metoda pro zpracovani radku s textovim obsahem
     * @param type Typ parametru
     * @param indicators Seznam pro ulozeni ukazatelu rovnosti
     * @return seznam s daty z textovych radku konkretniho typu parametru
     */
    public ArrayList<String> processTextLines(ParamType type, ArrayList<Integer> indicators) {
        ArrayList<String> list = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(checkValueFromTextItem(line.getTextItem()));
                    indicators.add(line.getTextItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }

    /**
     * Metoda pro zpracovani radku s ciselnym obsahem
     * @param type Typ parametru
     * @param indicators Seznam pro ulozeni ukazatelu rovnosti
     * @return seznam s daty z ciselnych radku konkretniho typu parametru
     */
    public ArrayList<String> processNumberLines(ParamType type, ArrayList<Integer> indicators) {
        ArrayList<String> list = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(checkValueFromTextItem(line.getNumberItem()));
                    indicators.add(line.getNumberItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }
    /**
     * Metoda pro zpracovani radku s vyberovym seznamem
     * @param type Typ parametru
     * @param indicators Seznam pro ulozeni ukazatelu rovnosti
     * @return seznam s daty z radku s Comboboxem konkretniho typu parametru
     */
    public ArrayList<Integer> processComboBoxLines(ParamType type, ArrayList<Integer> indicators) {
        ArrayList<Integer> list = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(line.getComboBoxItem().getItemIndex());
                    indicators.add(line.getComboBoxItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }

    /**
     * Metoda pro zpracovani radku relace WorkUnitu
     * @param type Typ parametru
     * @param workUnints seznam seznamu WorkUnitu v relaci
     * @return seznam relaci
     */

    public ArrayList<Integer> processRelationComboBoxLines(ParamType type, ArrayList<ArrayList<Integer>> workUnints) {
        ArrayList<Integer> listRelationList = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    listRelationList.add(line.getComboBoxItem().getItemIndex());
                    workUnints.add(new ArrayList<>(line.getCheckComboBoxItem().getChoosedIndicies()));
                }
            }

        }


        return listRelationList;
    }

    /**
     * Metoda pro zpracovani radku s multi vyberovym seznamem
     * @param type Typ parametru
     * @param indicators Seznam pro ulozeni ukazatelu rovnosti
     * @return seznam s daty z radku s CheckComboboxem konkretniho typu parametru
     */
    public ArrayList<ArrayList<Integer>> processCheckComboBoxLines(ParamType type, ArrayList<Integer> indicators) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(new ArrayList<>(line.getCheckComboBoxItem().getChoosedIndicies()));
                    indicators.add(line.getCheckComboBoxItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }

    /**
     * Metoda pro zpracovani radku s DataPickerem
     * @param type Typ parametru
     * @param indicators Seznam pro ulozeni ukazatelu rovnosti
     * @return seznam s daty z radku s DataPicker konkretniho typu parametru
     */
    public ArrayList<LocalDate> processDateLines(ParamType type, ArrayList<Integer> indicators) {
        ArrayList<LocalDate> list = new ArrayList<>();
        for (ControlPanelLine line : controlPanelLines) {
            if (line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(line.getDateItem().getDateFromDatePicker());
                    indicators.add(line.getDateItem().getIndicatorIndex());
                }
            }
        }

        return list;
    }

    /**
     * Metoda pro zvyseni poctu radku v panelu
     */
    public void incrementLineCounter() {
        lineCount++;
    }

    /**
     * Metoda pro odstraneni radku z konkretniho panelu
     * @param line instace predstavujici radek
     * @param controlPanel ControlPanel pro odstaraneni
     */
    public void removeFromControlPanel(ControlPanelLine line, GridPane controlPanel) {
        TextFieldItem text = line.getTextItem();
        TextFieldItem number = line.getNumberItem();
        CheckComboBoxItem check = line.getCheckComboBoxItem();
        ComboBoxItem combo = line.getComboBoxItem();
        DateItem dateItem = line.getDateItem();


        controlPanel.getChildren().remove(text.getItemTF());
        controlPanel.getChildren().remove(text.getIndicatorCB());
        controlPanel.getChildren().remove(text.getItemButton());
        controlPanel.getChildren().remove(number.getItemTF());
        controlPanel.getChildren().remove(number.getIndicatorCB());
        controlPanel.getChildren().remove(number.getItemButton());
        controlPanel.getChildren().remove(dateItem.getItemDate());
        controlPanel.getChildren().remove(dateItem.getIndicatorCB());
        controlPanel.getChildren().remove(dateItem.getItemButton());
        controlPanel.getChildren().remove(check.getItemCB());
        controlPanel.getChildren().remove(check.getIndicatorCB());
        controlPanel.getChildren().remove(check.getItemButton());
        controlPanel.getChildren().remove(combo.getItemCB());
        controlPanel.getChildren().remove(combo.getIndicatorCB());
        controlPanel.getChildren().remove(combo.getItemButton());
    }

    /**
     * Metoda pro vlozeni radku s RadioButtonem do panelu
     * @param controlPane ControlPanel pro vlozeni
     * @param rbYes RadionButton
     * @param i sloupec v radku
     * @param lineShift posun radku pod dynamicke radky
     */
    public void setRadioButton(GridPane controlPane, RadioButton rbYes, int i, int lineShift) {
        controlPane.add(rbYes, i, lineCount + lineShift);
        isRadioButtonLine = true;

    }

    /**
     * Pretizena metoda pro vlozeni radku o existenci prvku do controlniho panelu
     * @param controlPanel control panel
     * @param lineShift posun radku pod dynamicke radky
     */
    public void setExistRadioButton(ControlPanel controlPanel, int lineShift) {
        staticObjectCount++;
        radioExistButtonLine = new ControlPanelLine(FXCollections.observableArrayList(), controlPanel, this,
                lineCount + lineShift);
        setExistRadioButton(controlPanel.getControlPane(), lineShift);
    }

    /**
     * Pretizena metoda pro vlozeni radku o existenci prvku do controlniho panelu
     * @param gridPane panel pro vlozeni
     * @param lineShift posun radku pod dynamicke radky
     */
    public void setExistRadioButton(GridPane gridPane, int lineShift) {
        gridPane.getChildren().remove(radioExistButtonLine.getRadioButtonItem().getNameLb());
        gridPane.getChildren().remove(radioExistButtonLine.getRadioButtonItem().getYesRb());
        gridPane.add(radioExistButtonLine.getRadioButtonItem().getNameLb(), 1, lineCount + lineShift);
        gridPane.add(radioExistButtonLine.getRadioButtonItem().getYesRb(), 2, lineCount + lineShift);
    }

    /**
     * Pretizena metoda pro RadioButtonu do controlniho panelu
     * @param controlPanel control panel
     * @param lineShift posun radku pod dynamicke radky
     */
    public void setRadioButton(ControlPanel controlPanel, int lineShift, String name, boolean secondButton) {
        isSecondRadioButtonLine = secondButton;
        staticObjectCount++;
        radioButtonLine = new ControlPanelLine(FXCollections.observableArrayList(), controlPanel, this, lineCount + lineShift);
        RadioButtonItem item = radioButtonLine.getRadioButtonItem();
        item.setGroup();
        item.getNameLb().setText(name);
        setRadioButton(controlPanel, item, lineShift);
    }

    /**
     * Pretizena metoda pro RadioButtonu do controlniho panelu
     * @param controlPanel control panel
     * @param lineShift posun radku pod dynamicke radky
     */
    public void setRadioButton(ControlPanel controlPanel, RadioButtonItem item, int lineShift) {

        GridPane pane = controlPanel.getControlPane();
        pane.getChildren().remove(item.getNameLb());
        pane.getChildren().remove(item.getYesRb());
        pane.getChildren().remove(item.getNoRb());
        pane.add(item.getNameLb(), 1, lineCount + lineShift);
        setRadioButton(pane, item.getYesRb(), 2, lineShift);
        if (isSecondRadioButtonLine) {
            setRadioButton(pane, item.getNoRb(), 3, lineShift);
            item.setGroup();
        }
    }

    /**
     * Metoda pro vlozeni editacniho tlacitka
     * @param controlPanel controlni panel pro nastaveni
     * @param lineShift posun
     */
    public void setEditButton(ControlPanel controlPanel, int lineShift) {
        GridPane controlPane = controlPanel.getControlPane();
        Button button = controlPanel.getButton();
        controlPane.getChildren().remove(button);
        controlPane.add(button, 3, lineCount + lineShift);
    }


    /**
     * Metoda pro vlozeni statickeho tlacitka
     * @param controlPanel controlni panel pro nastaveni
     * @param lineShift posun
     */
    public void setStaticButton(ControlPanel controlPanel, int lineShift) {
        GridPane controlPane = controlPanel.getControlPane();

        controlPane.getChildren().remove(staticButton);
        controlPane.add(staticButton, 2, lineCount + lineShift);
    }

    /**
     * Metoda pro vlozeni statickeho Class boxu
     * @param controlPanel controlni panel pro nastaveni
     * @param lineShift posun
     * @param classLine radek s Class
     * @param superLine radek s SuperClass
     */
    public void setStaticClassBoxes(ControlPanel controlPanel, int lineShift, ControlPanelLine classLine, ControlPanelLine superLine) {
        this.classLine = classLine;
        this.superClassLine = superLine;
        ComboBoxItem classBox = classLine.getComboBoxItem();
        ComboBoxItem superClassBox = superLine.getComboBoxItem();
        classBox.getLabel().setText("Class: ");
        superClassBox.getLabel().setText("Super class: ");
        setStaticComboBox(controlPanel, lineShift, classBox, 1);
        setStaticComboBox(controlPanel, lineShift, superClassBox, 3);
        isClass = true;
        staticObjectCount++;
    }

    /**
     * Metoda pro vlozeni statickeho comboboxu pro Class a SuperClass
     * @param controlPanel controlni panel pro nastaveni
     * @param lineShift posun
     * @param startColumn
     */
    public void setStaticComboBox(ControlPanel controlPanel, int lineShift, ComboBoxItem item, int startColumn) {

        GridPane controlPane = controlPanel.getControlPane();
        controlPane.getChildren().remove(item.getLabel());
        controlPane.getChildren().remove(item.getItemCB());
        controlPane.add(item.getLabel(), startColumn, lineCount + lineShift);
        controlPane.add(item.getItemCB(), startColumn + 1, lineCount + lineShift);
    }

    /**
     * Metoda pro nastaveni radku s count line do panelu
     * @param controlPanel controlni panel pro nastaveni
     * @param lineShift posun
     * @param countLine radek pro vlozeni
     */
    public void setCountLine(ControlPanel controlPanel, int lineShift, ControlPanelLine countLine) {
        this.countLine = countLine;
        countLine.getTextItem().setTextToTextField("1");
        setCountLine(controlPanel, lineShift);
        staticObjectCount++;
    }

    /**
     * Metoda pro nastaveni CountLine radku v panelu
     * @param controlPanel controlni panel pro nastaveni radku
     * @param lineShift posun radku
     */
    public void setCountLine(ControlPanel controlPanel, int lineShift) {
        GridPane controlPane = controlPanel.getControlPane();

        controlPane.getChildren().remove(countLB);
        controlPane.getChildren().remove(countLine.getTextItem().getItemTF());
        controlPane.getChildren().remove(countLine.getTextItem().getIndicatorCB());
        controlPane.add(countLB, 1, lineCount + lineShift);
        controlPane.add(countLine.getTextItem().getIndicatorCB(), 2, lineCount + lineShift);
        controlPane.add(countLine.getTextItem().getItemTF(), 3, lineCount + lineShift);

        isCountLine = true;
    }

    /**
     * Metoda pro posun statickych objektu obsazenych v panelu
     * @param controlPanel conrolni panel pro posunuti prvku
     */
    public void shiftStaticObjects(ControlPanel controlPanel) {
        int shift = 2;

        if (isClass) {
            ComboBoxItem classBox = classLine.getComboBoxItem();
            ComboBoxItem superClassBox = superClassLine.getComboBoxItem();
            setStaticComboBox(controlPanel, shift, classBox, 1);
            setStaticComboBox(controlPanel, shift, superClassBox, 3);
            shift++;
        }

        if (isRadioButtonLine) {
            RadioButtonItem item = radioButtonLine.getRadioButtonItem();
            setRadioButton(controlPanel, item, shift);
            shift++;
        }

        if (isCountLine) {
            setCountLine(controlPanel, shift);
            shift++;
        }

        if (isStaticButton) {
            setStaticButton(controlPanel, shift);
            shift++;
        }

        setExistRadioButton(controlPanel.getControlPane(), shift);
        shift++;

        setEditButton(controlPanel, shift + 1);
    }

    /**
     * Metoda pro nastaveni hodnty do radku s RadioButton
     * @param isRelease hodnota pro nastaveni
     */
    public void setValueRadioButton(boolean isRelease) {
        if (isRelease) {
            radioButtonLine.getRadioButtonItem().getYesRb().setSelected(true);
        } else {
            radioButtonLine.getRadioButtonItem().getYesRb().setSelected(false);
        }

    }

    /**
     * metoda pro nastaveni hodnoty do radku s existenci radku
     * @param exist existence radku
     */
    public void setValueExistRadioButton(boolean exist) {
        if (exist) {
            radioExistButtonLine.getRadioButtonItem().getYesRb().setSelected(true);
        } else {
            radioExistButtonLine.getRadioButtonItem().getYesRb().setSelected(false);
        }
    }

    /**
     * Metoda pro ziskani daty z radku s instace Count
     * @return pocet instacni
     */
    public String getInstanceCount() {
        return countLine.getTextItem().getTextFromTextField();
    }

    /**
     * Metod pro nastaveni hodnot do radku s Class a SuperClass
     * @param classIndex index pro class
     * @param superClassIndex index pro superClass
     */
    public void setValueToClassBox(List classIndex, List superClassIndex) {
        if (classIndex != null) {
            ComboBoxItem item1 = classLine.getComboBoxItem();
            item1.selectItemInComboBox((Integer) classIndex.get(0));
        }

        if (superClassIndex != null) {
            ComboBoxItem item2 = superClassLine.getComboBoxItem();
            item2.getItemCB().getSelectionModel().select((Integer) superClassIndex.get(0));
        }


    }

    /**
     * Metoda pro nastaveni poctu instaci do radku s instancem i
     * @param value hodnota
     * @param indicator ukazatel hodnoty
     */
    public void setCountToCountLine(Integer value, int indicator) {
        countLine.setCount(value);
        countLine.getTextItem().setIndicator(indicator);
    }

    /**
     * Metoda pro odstraneni prvku z panelu
     * @param controlPane
     */
    public void resetPanel(GridPane controlPane) {
        controlPane.getChildren().clear();
        controlPanelLines.clear();
        resetPanel();
    }


    /**
     * Metoda pro vymazani panelu
     */
    public void resetPanel() {
        this.lineCount = 1;
        this.isRadioButtonLine = false;
        this.isSecondRadioButtonLine = false;
        this.isCountLine = false;
        this.isStaticButton = false;
        this.isClass = false;
        this.staticObjectCount = 1;
    }

    /** Getters and Settrs **/
    public int getLineCount() {
        return lineCount;
    }

    public void setAlias(String alias, ControlPanel controlPanel) {
        controlPanel.getAliasTF().setText(alias);
    }

    public int getInstanceCountIndicator() {

        return countLine.getTextItem().getIndicatorIndex();
    }

    public boolean isMain() {
        return radioButtonLine.isCheckYesRadioButton();
    }

    public boolean isExist() {
        return radioExistButtonLine.isCheckYesRadioButton();
    }


    public Integer getSuperClassIndex() {
        return superIndex;
    }

    public Integer getClassIndex() {
        return classIndex;
    }
}

