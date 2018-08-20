package services;

import javafx.scene.control.TableCell;
import tables.TagTable;

/**
 * Bunka umoznujici vypis poradi prvku
 * 
 * @author Václav Janoch
 *
 */

public class OrderCell extends TableCell<TagTable, Integer> {
	public void updateItem(Integer value, boolean empty) {
		super.updateItem(value, empty);
		if (empty) {
			setText(null);
		} else {
			setText((getIndex() + 1) + " : ");
		}
	}
}
