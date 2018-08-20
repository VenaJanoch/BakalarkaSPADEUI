package interfaces;

/**
 * Rozhraní s metodami pro formuláře vázané k prvkům plátna
 * @author Václav Janoch
 *
 */
public interface ISegmentForm {
	
	/**
	 * Metoda pro nastavení reakce na zavření formuláře
	 */
	public void closeForm();
	
	/**
	 * Metoda pro nastavení reakce na potvrzovací tlačítko
	 */
	public void setActionSubmitButton();
	
	/**
	 * Metoda pro vytvoření a přídání prvků do gridPanelu
	 */
	public void createForm();
	
	/**
	 * Metoda pro nastavení reakce smazání prvku z plátna
	 */
	public void deleteItem(int[] IDs);
	
	
	
}
