package services;

import javafx.scene.control.TableCell;
import tables.TagTable;

/**
 * 
 * Buňka umožnující výpis pořadí v tabulce
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
