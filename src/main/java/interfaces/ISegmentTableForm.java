package interfaces;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
/**
 * Rozhraná s metodami pro práci s tabulkovými formuláři
 * @author Václav Janoch
 *
 */
public interface ISegmentTableForm {

	/**
	 * Metoda pro přídání prvku do interního gridPanelu
	 */
	void createForm();
	/**
	 * Metoda pro přídání TableView do formuláře
	 */
	Node getTable();
	/**
	 * Metoda pro nastavení reakce na klávesu delete
	 */
	void deleteSelected(KeyEvent event);
	/**
	 * Metoda pro přídání prvku do gridPanelu
	 */
	GridPane createControlPane();
	/**
	 * Metoda pro přídání prvku dané tabulky
	 */
	void addItem();
	/**
	 * Metoda pro určení reakce stisknutí tlačítka pro potvrzení formuláře
	 */
	void setActionSubmitButton();
	
}
