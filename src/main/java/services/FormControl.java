package services;

import SPADEPAC.Artifact;
import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnit;
import forms.WorkUnitForm;

public class FormControl {

	/**
	 * Metoda sloužící k namapování prázdného nebo neexistujícího prvku na
	 * prázdný element
	 * 
	 * @param text
	 *            text
	 * @return vysledný řetězec
	 */
	public String fillTextMapper(String text) {

		
		if (text == null || text.equals("")) {
			return null;
		}

		return text;

	}

	/**
	 * Metoda sloužící k namapování neezistujícího prvku z XML na prázdný
	 * řežezec
	 * 
	 * @param text
	 *            text
	 * @return řetězec
	 */
	public String fillTextFromXMLMapper(String text) {

		if (text == null) {
			return "";
		}

		return text;

	}

	/**
	 * Umoznuje kontrolu vkládaného prvku do plátna
	 * 
	 * @param itemType
	 *            vkládaný CanvasItem
	 * @param canvasType
	 *            Typ plátna pro přidání
	 * @return uspěšné\neúspěšné přídání do plátna
	 */
	public static boolean copyControl(SegmentType itemType, CanvasType canvasType) {

		if (itemType == SegmentType.WorkUnit) {

			if (canvasType == canvasType.Phase || canvasType == canvasType.Iteration
					|| canvasType == canvasType.Activity || canvasType == canvasType.Project) {
				return true;
			}

		} else if (itemType == SegmentType.Change || itemType == SegmentType.Artifact) {
			if (canvasType == CanvasType.Configuration) {
				return true;
			}
		} else if (itemType == SegmentType.Phase || itemType == SegmentType.Iteration
				|| itemType == SegmentType.Activity) {
			if (canvasType == CanvasType.Project) {
				return true;
			}
		}

		return false;

	}
}
