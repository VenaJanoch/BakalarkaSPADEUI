package controllers.graphicsComponentsControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.controlsfx.control.CheckComboBox;
import tables.BasicTable;
/**
 * Trida predstavujici controller pro komponenty patrici do jednotlivych radku
 *
 * @author Václav Janoch
 */
public class ItemBoxController {

    /**Index prvku vybraneho v ComboBoxu**/
    private int itemIndex;
    /**Indexi zvolene v komponente CheckComboBox**/
    private ObservableList<Integer> choosedIndicies;
    /**Listener pro ComoboBox **/
    private ChangeListener<Number> listener;


    /**
     * Konstruktor tridy
     * Zinicializuje globalni promenne
     */
    public ItemBoxController() {
        choosedIndicies = FXCollections.observableArrayList();
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */

    public ChangeListener<Number> comboBoxListener() {

        listener = new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                itemIndex = newValue.intValue();

            }
        };
        return listener;
    }

    /**
     * ChangeListener pro určení indexu prvku z comboBoxu pro Priority
     */

    public ListChangeListener CheckBoxListener(CheckComboBox item) {

        ListChangeListener checkListener = new ListChangeListener<BasicTable>() {

            public void onChanged(ListChangeListener.Change<? extends BasicTable> c) {
                choosedIndicies = item.getCheckModel().getCheckedIndices();
            }

        };

        return checkListener;
    }

    /**Getrs and Setrs**/
    public int getItemIndex() {
        return itemIndex;
    }

    public ObservableList<Integer> getChoosedIndicies() {
        return choosedIndicies;
    }

}
