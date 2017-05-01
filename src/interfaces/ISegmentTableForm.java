package interfaces;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
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
	public void createForm();
	/**
	 * Metoda pro přídání TableView do formuláře
	 */
	public Node getTable();
	/**
	 * Metoda pro nastavení reakce na klávesu delete
	 */
	public void deleteSelected(KeyEvent event);
	/**
	 * Metoda pro přídání prvku do gridPanelu
	 */
	public GridPane createControlPane();
	/**
	 * Metoda pro přídání prvku dané tabulky
	 */
	public void addItem();
	/**
	 * Metoda pro určení reakce stisknutí tlačítka pro potvrzení formuláře
	 */
	public void setActionSubmitButton();
	
}
