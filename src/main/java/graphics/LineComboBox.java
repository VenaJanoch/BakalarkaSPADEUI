package graphics;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Třída definující box pro výběr relace mezi dvěma Work Unit. Odděděná od třídy
 * ComboBox
 * 
 * @author Václav Janoch
 *
 */
public class LineComboBox extends ComboBox<String> {

	/** Globální proměnné třídy */
	private int relationIndex;

	public LineComboBox(ObservableList<String> relationTypeObservable) {
		super(relationTypeObservable);

		this.getSelectionModel().selectedIndexProperty().addListener(relationListener);
		this.setVisibleRowCount(5);

	}

	/**
	 * ChangeListener pro získání indexu vybrané relace a přídání relace od
	 * datových struktru
	 */
	ChangeListener<Number> relationListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {


			setRelationIndex(newValue.intValue());

		}
	};

	/** Getrs and Setrs */
	public int getRelationIndex() {
		return relationIndex;
	}

	public void setRelationIndex(int relationIndex) {
		this.relationIndex = relationIndex;
	}

}
