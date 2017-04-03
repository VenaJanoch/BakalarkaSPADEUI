package services;



import javafx.scene.control.TableCell;
import tables.TagTable;

/**
 * 
 * Cell that is only displaying its number + 1 - number of the line in the collection
 * 
 * @author Richard Lipka
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
