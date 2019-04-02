package controllers;

import abstractControlPane.ControlPanel;
import controlPanels.WorkUnitControlPanel;
import graphics.*;
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

public class ControlPanelController {

    private int lineCount;
    private ArrayList<ControlPanelLine> controlPanelLines;
    private ControlPanelLine radioButtonLine;
    private ControlPanelLine radioExistButtonLine;
    private Label countLB;
    private ControlPanelLine countLine;
    private ControlPanelLine classLine;
    private ControlPanelLine superClassLine;
    private Button staticButton;

    private boolean isRadioButtonLine;
    private boolean isSecondRadioButtonLine;
    private boolean isCountLine;
    private boolean isStaticButton;
    private boolean isClass;

    private int classIndex;
    private int superIndex;

    private  int staticObjectCount;
    private ClassSwitcher switcher;


    public ControlPanelController(ArrayList<ControlPanelLine> controlPanelLines){
        switcher = new ClassSwitcher();
        this.controlPanelLines = controlPanelLines;
        this.countLB = new Label("Instance count: ");
        resetPanel();

    }


    public String checkValueFromTextItem(TextFieldItem textFieldItem){
        String desc = "null*";
        if (textFieldItem.getTextFromTextField() != null){
            desc = textFieldItem.getTextFromTextField();
        }
        return desc;
    }

    public void setValueTextField(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                  ParamType type, List<String>[] values, List<Integer> indicatorList, int index) {
        int i = 0;
        for (String value : values[index]){
            createNewLine(controlPanel, lineList);
            ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() -1);
            line.fillTextLine(value, indicatorList.get(i), type );
            i++;
            incrementLineCounter();
        }

    }

    public void setValueRelationBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                    ParamType type, ArrayList<Integer> relation, ArrayList<ArrayList<Integer>> workUnit) {
        int i = 0;
        for ( int value : relation){
            createNewLine(controlPanel, lineList);
            ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() -1);
            line.fillRelationComboBoxLine(value, workUnit.get(i), type );
            i++;
            incrementLineCounter();
        }
    }

    public void setValueComboBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                 ParamType type, ArrayList<Integer> values, List<Integer> indicatorList) {
        int i = 0;
        for ( int value : values){
            createNewLine(controlPanel, lineList);
            ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() -1);
                line.fillComboBoxLine(value, indicatorList.get(i), type );
            i++;
            incrementLineCounter();
        }

    }

    public void setValueDatePicker(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                   ParamType type, ArrayList<LocalDate> values, List<Integer> indicatorList) {
        int i = 0;

        if(values != null) {
            for (LocalDate value : values) {
                createNewLine(controlPanel, lineList);
                ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
                line.fillDateLine(value, indicatorList.get(i), type);
                i++;
                incrementLineCounter();
            }
        }
    }

    public void setValueCheckComboBox(ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList,
                                      ParamType type, ArrayList<ArrayList<Integer>> values, List<Integer> indicatorList) {
       int i = 0;
       if(values != null) {
           for (List<Integer> value : values) {
               createNewLine(controlPanel, lineList);
               ControlPanelLine line = controlPanelLines.get(controlPanelLines.size() - 1);
               line.fillCheckComboBoxLine(value, indicatorList.get(i), type);
               i++;
               incrementLineCounter();
           }
       }
    }


    public void setParamBoxToControlPanel(GridPane controlPane, ControlPanelLine line) {
        controlPane.add(line.getExitButton(), 0, lineCount);
        controlPane.add(line.getParamBox(), 1, lineCount);
    }


    public void setComboBoxItemToControlPanel(GridPane controlPane, ComboBoxItem item, int startColomIndex) {
        controlPane.add(item.getIndicatorCB(), startColomIndex, lineCount);
        controlPane.add(item.getItemCB(), startColomIndex + 1, lineCount);
        controlPane.add(item.getItemButton(), startColomIndex +2, lineCount);
    }

    public void setRelationComboBoxItemToControlPanel(GridPane controlPane, ComboBoxItem relationItem, CheckComboBoxItem workUnitItem, int startColomIndex) {
        controlPane.add(relationItem.getItemCB(), startColomIndex, lineCount);
        controlPane.add(workUnitItem.getItemCB(), startColomIndex + 1, lineCount);
        controlPane.add(relationItem.getItemButton(), startColomIndex + 2, lineCount);
    }

    public void setDateItemToControlPanel(GridPane controlPane, DateItem item, int startColomIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineCount);
        controlPane.add(item.getItemDate(), startColomIndex + 1, lineCount);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineCount);
    }

    public void setTextItemToControlPanel(GridPane controlPane, TextFieldItem item, int startColomIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineCount);
        controlPane.add(item.getItemTF(), startColomIndex + 1, lineCount);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineCount);
    }

    public void setCheckComboBoxItemToControlPanel(GridPane controlPane, CheckComboBoxItem item, int startColomIndex) {

        controlPane.add(item.getIndicatorCB(), startColomIndex, lineCount);
        controlPane.add(item.getItemCB(), startColomIndex + 1, lineCount);
        controlPane.add(item.getItemButton(), startColomIndex + 2, lineCount);
    }

    public ChangeListener<Toggle> radioButtonGroupListener() {
        ChangeListener<Toggle> listener = new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton chk = radioButtonLine.getRadioButtonItem().getYesRb();

                if (chk.isSelected()) {
                  //  isMain = true;
                } else {
                  //  isMain = false;
                }

            }
        };
        return listener;
    }


    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Class. Zavolá
     * metody pro mapování Class na Super Class
     */

    public ChangeListener<Number> getClassListener(SegmentType segmentType){

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

    public boolean isMain() {
        return radioButtonLine.isCheckYesRadioButton();
    }

    public boolean isExist() {
        return radioExistButtonLine.isCheckYesRadioButton();
    }

    public void copyLine(ControlPanelLine line, ControlPanel controlPanel, ObservableList<ControlPanelLineObject> lineList, int paramIndex) {
        incrementLineCounter();
        ControlPanelLineController copyLine = createNewLine(controlPanel,  lineList);
        copyLine.copyLine(line, paramIndex);
        shiftStaticObjects(controlPanel);
    }

    public ControlPanelLineController createNewLine(ControlPanel controlPane, ObservableList<ControlPanelLineObject> lineList) {
        setExistRadioButton(controlPane, 1);
        ControlPanelLine line = new ControlPanelLine(lineList, controlPane, this);
        setParamBoxToControlPanel(controlPane.getControlPane(), line);
        controlPanelLines.add(line);
        shiftStaticObjects(controlPane);
        return line.getControlPanelLineController();
    }

    public ControlPanelLineController createNewLine(ControlPanel controlPane, ObservableList<ControlPanelLineObject> lineList, String[] indicators) {

        ControlPanelLine line = new ControlPanelLine(lineList, controlPane, this, indicators);
        setParamBoxToControlPanel(controlPane.getControlPane(), line);
        controlPanelLines.add(line);
        setEditButton(controlPane, 1);
        return line.getControlPanelLineController();
    }


    public ArrayList<String> processTextLines(ParamType type, ArrayList<Integer> indicators){
        ArrayList<String> list = new ArrayList<>();
        for ( ControlPanelLine line : controlPanelLines){
            if(line.getExitButton().isSelected()){
                if (line.getType() == type){
                    list.add(checkValueFromTextItem(line.getTextItem()));
                    indicators.add(line.getTextItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> processComboBoxLines(ParamType type, ArrayList<Integer> indicators){
        ArrayList<Integer> list = new ArrayList<>();
        for ( ControlPanelLine line : controlPanelLines){
            if ( line.getExitButton().isSelected()) {
                if (line.getType() == type) {
                    list.add(line.getComboBoxItem().getItemIndex());
                    indicators.add(line.getComboBoxItem().getIndicatorIndex());
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> processRelationComboBoxLines(ParamType type, ArrayList<ArrayList<Integer>> workUnints){
        ArrayList<Integer> listRelationList = new ArrayList<>();
        for ( ControlPanelLine line : controlPanelLines){
            if ( line.getExitButton().isSelected()){
                if (line.getType() == type){
                    listRelationList.add(line.getComboBoxItem().getItemIndex());
                    workUnints.add(new ArrayList<>(line.getCheckComboBoxItem().getChoosedIndicies()));
                }
            }

        }


        return listRelationList;
    }

    public ArrayList<ArrayList<Integer>> processCheckComboBoxLines(ParamType type, ArrayList<Integer> indicators){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for ( ControlPanelLine line : controlPanelLines){
            if (line.getType() == type){
                list.add(new ArrayList<>(line.getCheckComboBoxItem().getChoosedIndicies()));
                indicators.add(line.getCheckComboBoxItem().getIndicatorIndex());
            }
        }
        return list;
    }

    public ArrayList<LocalDate> processDateLines(ParamType type, ArrayList<Integer> indicators){
        ArrayList<LocalDate> list = new ArrayList<>();
        for ( ControlPanelLine line : controlPanelLines){
            if (line.getType() == type){
                list.add(line.getDateItem().getDateFromDatePicker());
                indicators.add(line.getDateItem().getIndicatorIndex());
            }
        }

        return list;
    }

    public void incrementLineCounter(){
        lineCount++;
    }

    public void removeFromControlPanel(ControlPanelLine line, GridPane controlPanel) {
        TextFieldItem text = line.getTextItem();
        CheckComboBoxItem check = line.getCheckComboBoxItem();
        ComboBoxItem combo = line.getComboBoxItem();
        DateItem dateItem = line.getDateItem();

        controlPanel.getChildren().remove(text.getItemTF());
        controlPanel.getChildren().remove(text.getIndicatorCB());
        controlPanel.getChildren().remove(text.getItemButton());
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

    public void setRadioButton(GridPane controlPane, RadioButton rbYes, int i, int lineShift) {
        controlPane.add(rbYes, i, lineCount + lineShift);
        isRadioButtonLine = true;

    }

    public void setExistRadioButton(ControlPanel controlPanel, int lineShift){
        staticObjectCount++;
        radioExistButtonLine = new ControlPanelLine(FXCollections.observableArrayList(), controlPanel, this);
        setExistRadioButton(controlPanel.getControlPane(), lineShift);
    }

    public void setExistRadioButton(GridPane gridPane, int lineShift){
        gridPane.getChildren().remove(radioExistButtonLine.getRadioButtonItem().getNameLb());
        gridPane.getChildren().remove(radioExistButtonLine.getRadioButtonItem().getYesRb());
        gridPane.add(radioExistButtonLine.getRadioButtonItem().getNameLb(), 1, lineCount + lineShift);
        gridPane.add(radioExistButtonLine.getRadioButtonItem().getYesRb(), 2, lineCount + lineShift);
    }

    public void setRadioButton(ControlPanel controlPanel, int lineShift, String name, boolean secondButton) {
        isSecondRadioButtonLine = secondButton;
        staticObjectCount++;
        radioButtonLine = new ControlPanelLine(FXCollections.observableArrayList(), controlPanel, this);
        RadioButtonItem item = radioButtonLine.getRadioButtonItem();
        item.setGroup();
        item.getNameLb().setText(name);
        setRadioButton(controlPanel, item, lineShift);
    }

    public void setRadioButton(ControlPanel controlPanel, RadioButtonItem item, int lineShift) {

        GridPane pane = controlPanel.getControlPane();
        pane.getChildren().remove(item.getNameLb());
        pane.getChildren().remove(item.getYesRb());
        pane.getChildren().remove(item.getNoRb());
        pane.add(item.getNameLb(), 1, lineCount + lineShift);
        setRadioButton(pane, item.getYesRb(), 2, lineShift);
        if (isSecondRadioButtonLine){
            setRadioButton(pane, item.getNoRb(), 3, lineShift);
            item.setGroup();
        }
    }

    public void setEditButton(ControlPanel controlPanel, int lineShift){
        GridPane controlPane = controlPanel.getControlPane();
        Button button  =  controlPanel.getButton();
        controlPane.getChildren().remove(button);
        controlPane.add(button, 3, lineCount + lineShift);
    }

    public void setStaticButton(ControlPanel controlPanel, int lineShift, Button button){
        staticButton = button;
        isStaticButton = true;
        setStaticButton(controlPanel, lineShift);
        staticObjectCount++;
    }

    public void setStaticButton(ControlPanel controlPanel, int lineShift){
        GridPane controlPane = controlPanel.getControlPane();

        controlPane.getChildren().remove(staticButton);
        controlPane.add(staticButton, 2, lineCount + lineShift);
    }

    public void setStaticClassBoxes(ControlPanel controlPanel, int lineShift, ControlPanelLine classLine,  ControlPanelLine superLine){
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

    public void setStaticComboBox(ControlPanel controlPanel, int lineShift, ComboBoxItem item, int startColumn){

        GridPane controlPane = controlPanel.getControlPane();
        controlPane.getChildren().remove(item.getLabel());
        controlPane.getChildren().remove(item.getItemCB());
        controlPane.add(item.getLabel(), startColumn, lineCount + lineShift);
        controlPane.add(item.getItemCB(), startColumn + 1, lineCount + lineShift);
    }

    public void setCountLine(ControlPanel controlPanel, int lineShift, ControlPanelLine countLine){
        this.countLine = countLine;
        countLine.getTextItem().setTextToTextField("1");
        setCountLine(controlPanel, lineShift);
        staticObjectCount++;
    }

    public void setCountLine(ControlPanel controlPanel, int lineShift){
        GridPane controlPane = controlPanel.getControlPane();

        controlPane.getChildren().remove(countLB);
        controlPane.getChildren().remove(countLine.getTextItem().getItemTF());
        controlPane.add(countLB, 1, lineCount + lineShift);
        controlPane.add(countLine.getTextItem().getItemTF(), 2, lineCount + lineShift);

        isCountLine = true;
    }

    public void shiftStaticObjects(ControlPanel controlPanel){
        int shift = 1;

        if (isClass){
            ComboBoxItem classBox = classLine.getComboBoxItem();
            ComboBoxItem superClassBox = superClassLine.getComboBoxItem();
            setStaticComboBox(controlPanel, shift, classBox, 1 );
            setStaticComboBox(controlPanel, shift, superClassBox, 3 );
            shift++;
        }

        if (isRadioButtonLine){
            RadioButtonItem item = radioButtonLine.getRadioButtonItem();
            setRadioButton(controlPanel, item, shift);
            shift++;
        }

        if(isCountLine){
            setCountLine(controlPanel, shift);
            shift++;
        }

        if (isStaticButton){
            setStaticButton(controlPanel, shift);
            shift++;
        }

        setExistRadioButton(controlPanel.getControlPane(), shift);
        shift++;

        setEditButton(controlPanel, shift + 1);
    }

    public void setValueRadioButton(boolean isRelease) {
        if (isRelease){
            radioButtonLine.getRadioButtonItem().getYesRb().setSelected(true);
        }else{
            radioButtonLine.getRadioButtonItem().getYesRb().setSelected(false);
        }

    }

    public void setValueExistRadioButton(boolean exist) {
        if (exist){
            radioExistButtonLine.getRadioButtonItem().getYesRb().setSelected(true);
        }else{
            radioExistButtonLine.getRadioButtonItem().getYesRb().setSelected(false);
        }
    }

    public String getInstanceCount() {
        return countLine.getTextItem().getTextFromTextField();
    }

    public void setValueToClassBox(List classIndex, List superClassIndex) {
        if (classIndex != null){
            ComboBoxItem item1 = classLine.getComboBoxItem();
            item1.getItemCB().getSelectionModel().select((Integer)classIndex.get(0));
        }

        if(superClassIndex != null){
            ComboBoxItem item2 = superClassLine.getComboBoxItem();
            item2.getItemCB().getSelectionModel().select((Integer)superClassIndex.get(0));
        }


    }

    public Integer getSuperClassIndex() {
        return superClassLine.getComboBoxItem().getItemIndex();
    }

    public Integer getClassIndex() {
        return classLine.getComboBoxItem().getItemIndex();
    }

    public void setCountToCountLine(Integer value) {
        countLine.setCount(value);
    }


    public void resetPanel(GridPane controlPane) {
        controlPane.getChildren().clear();
        controlPanelLines.clear();
        resetPanel();
    }


    public void resetPanel() {
        this.lineCount = 0;
        this.isRadioButtonLine = false;
        this.isSecondRadioButtonLine = false;
        this.isCountLine = false;
        this.isStaticButton = false;
        this.isClass = false;
        this.staticObjectCount = 1;
    }


}

