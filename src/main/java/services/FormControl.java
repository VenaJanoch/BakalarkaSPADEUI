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


	/**
	 * Kontrola vyplněných hodnot formuláře WorkUnit
	 * 
	 **/
	public void workUnitFormControl(WorkUnitForm form, WorkUnit unit) {

		//TODO: Volat manipulaci s daty v jednom z modelu (Nejspise DataManipulator)

	try {
		Integer.parseInt("a");
	}catch (Exception e ){
		e.printStackTrace();
		System.out.println("Dodelat");
	}

		/*
		if (unit.getPriorityIndex() != null) {
			form.getPriorityCB().setValue(lists.getPriorityObservable().get(unit.getPriorityIndex() + 1));
		}
		if (unit.getSeverityIndex() != null) {
			form.getSeverityCB().setValue(lists.getSeverityTypeObservable().get(unit.getSeverityIndex() + 1));
		}
		if (unit.getTypeIndex() != null) {
			form.getTypeCB().setValue(lists.getTypeObservable().get(unit.getTypeIndex() + 1));
		}
		if (unit.getStatusIndex() != null) {
			form.getStatusCB().setValue(lists.getStatusTypeObservable().get(unit.getStatusIndex() + 1));
		}
		if (unit.getResolutionIndex() != null) {
			form.getResolutionCB().setValue(lists.getResolutionTypeObservable().get(unit.getResolutionIndex() + 1));
		}
		if (unit.getAuthorIndex() != null) {
			String author = lists.getRoleList().get(unit.getAuthorIndex() + 1).getName();
			form.getAuthorRoleCB().setValue(author);
		}
		if (unit.getAssigneeIndex() != null) {
			String assignee = lists.getRoleList().get(unit.getAssigneeIndex() + 1).getName();
			form.getAsigneeRoleCB().setValue(assignee);
		}
		if (unit.getEstimatedTime() == null) {
			form.getEstimatedTimeTF().setText("");
		} else {
			form.getEstimatedTimeTF().setText(String.valueOf(unit.getEstimatedTime()));
		}

		form.getExistRB().setSelected(unit.isExist());*/

	}

}
