package services;

import SPADEPAC.Artifact;
import SPADEPAC.Configuration;
import SPADEPAC.Iteration;
import SPADEPAC.Phase;
import SPADEPAC.WorkUnit;
import forms.WorkUnitForm;

public class FormControl {

	/** Globální proměnné třídy **/
	private SegmentLists lists;

	/**
	 * Konstruktor třídy
	 * 
	 * @param lists
	 *            instance třídy SegmentLists
	 */
	public FormControl(SegmentLists lists) {
		this.lists = lists;
	}

	/**
	 * Metoda sloužící k namapování prázdného nebo neexistujícího prvku na
	 * prázdný element
	 * 
	 * @param text
	 *            text
	 * @return vysledný řetězec
	 */
	public static String fillTextMapper(String text) {

		
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
	 * Kontrola vyplněných hodnot Phase
	 * 
	 * @param phase
	 * @param milestone
	 * @param config
	 */
	public static void phaseControl(Phase phase, int milestone, int config) {

		if (config != 0) {
			phase.setConfiguration(config - 1);
		} 
	
		if (milestone != 0) {
			phase.setMilestoneIndex(milestone - 1);			
			
		}

	}

	/**
	 * Kontrola vyplněných hodnot Artifact
	 * 
	 **/

	public static void artifactControl(Artifact artifact, int index) {
		if (index != 0) {
			artifact.setAuthorIndex(index - 1);
		}
	}

	/**
	 * Kontrola vyplněných hodnot Configuration
	 * 
	 **/
	public static void configControl(Configuration conf, int index) {
		if (index != 0) {
			conf.setAuthorIndex(index - 1);
		}
	}

	/**
	 * Kontrola vyplněných hodnot Iteration
	 * 
	 **/
	public static void iterationControl(Iteration iteration, int index) {

		if (index != 0) {
			iteration.setConfiguration(index - 1);
		}
	}

	/**
	 * Kontrola vyplněných hodnot Work Unit
	 * 
	 **/
	public static boolean workUnitControl(WorkUnit workUnit, int priority, int severity, int type, int resolution, int status,
			int author, int assignee) {

		if (priority != 0) {
			workUnit.setPriorityIndex(priority - 1);
		} else if (severity != 0) {
			workUnit.setSeverityIndex(severity - 1);
		} else if (type != 0) {
			workUnit.setTypeIndex(type - 1);
		} else if (status != 0) {
			workUnit.setStatusIndex(status - 1);
		} else if (resolution != 0) {
			workUnit.setResolutionIndex(resolution - 1);
		} else if (author != 0) {
			workUnit.setAuthorIndex(author - 1);
		} else if (assignee != 0) {
			workUnit.setAssigneeIndex(assignee - 1);
		}

		return true;

	}

	/**
	 * Kontrola vyplněných hodnot formuláře WorkUnit
	 * 
	 **/
	public void workUnitFormControl(WorkUnitForm form, WorkUnit unit) {
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

		form.getExistRB().setSelected(unit.isExist());

	}

}
