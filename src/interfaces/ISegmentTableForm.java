package interfaces;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public interface ISegmentTableForm {

	public void createForm();
	public Node getTable();
	public void deleteSelected(KeyEvent event);
	public GridPane createControlPane();
	public void addItem();
	public void setActionSubmitButton();
	
}
