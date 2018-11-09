package Controllers;

import java.util.List;

import graphics.ChangeArtifactLink;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.geometry.Point2D;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;

public class LinkControl {

	/** Globální proměnné třídy */

	private int id;

	private ChangeArtifactLink changeArtifactLink;
	private WorkUnitLink workUnitLink;

	private int startSegmentId = -1;
	private int endSegmentId = -1;

	private IdentificatorCreater identificatorCreater;
	private FormController formController;
	private SegmentLists segmentLists;

	private DataManipulator dataManipulator;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 *
	 */
	public LinkControl(FormController formController, IdentificatorCreater identificatorCreater, SegmentLists segmentLists,
					   DataManipulator dataManipulator) {
		this.segmentLists = segmentLists;
		this.identificatorCreater = identificatorCreater;
		this.formController = formController;
		this.dataManipulator = dataManipulator;
	}
	

	/**
	 * Rozhodne o propojení Change a Artifact Vytvoří instanci třídy NodeLink a
	 * přídá ji do seznamu Rozhodne o počátečním a koncovém prvku
	 *
	 * @param isSave
	 *            informace o uložení a ochrana před vytvořením dvojího spojení
	 */
	public void ArrowManipulation(boolean isSave, boolean startArrow, CanvasController canvasController, int segmentIdAct, SegmentType segmentType,
								  double x, double y, double width, double height) {

		if (!startArrow) {
			try {
				if (isFormfillControl(segmentIdAct)) {
					if(segmentControl(segmentIdAct, segmentType)){
						changeArtifactLink = createChangeArtifactLink(canvasController, segmentIdAct, x, y, width, height);
						// Na vic nevim kam s tim
						//WorkUnit left = control.getLists().getWorkUnitList().get(getStartIDs()[1]);
						//WorkUnit right = control.getLists().getWorkUnitList().get(getEndIDs()[1]);
						//relationCB.setLeftUnit(left);
						//relationCB.setRightUnit(right);
						//	relationCB.setStartIDs(startIDs);
						//	relationCB.setEndIDs(endIDs);

					}else if(segmentType == SegmentType.WorkUnit){

						workUnitLink = createWorkUnitLink(canvasController, segmentIdAct, x, y, width, height);
					}

				} else {
					Alerts.showNoWorkUnit();
				}

			} catch (Exception e) {
				e.printStackTrace();
				Alerts.showNoWorkUnit();
			}

		} else {
			try {
				if (segmentControl(segmentIdAct, segmentType)) {
					endSegmentId = segmentIdAct;
					changeArtifactLink.setEndPoint(new Point2D(x, y + (height / 2)));
					canvasController.setStartArrow(false);
					if (!isSave) {
						formController.createChangeArtifactRelation(startSegmentId, endSegmentId);
					}
				}else if (segmentWorkUnitControl(segmentIdAct)) {
					endSegmentId = segmentIdAct;
					workUnitLink.setArrowAndBox(
							new Point2D(x, y + (height / 2)));
					canvasController.setStartArrow(false);
					if (!isSave) {
						formController.createWorkUnitRelation(startSegmentId, endSegmentId);

						setWorkUnitRelation(workUnitLink);
					}

			}
			}catch (Exception e) {
				Alerts.showNoWorkUnit();
			}

		}

	}

	private WorkUnitLink createWorkUnitLink(CanvasController canvasController, int segmentIdAct, double x, double y, double width, double height) {

		id = identificatorCreater.createLineID();
		WorkUnitLink workUnitLink = new WorkUnitLink(id, this, canvasController, segmentLists.getRelationTypeObservable());
		canvasController.addLinkToCanvas(workUnitLink);

		segmentLists.addLinkToList(workUnitLink);

		workUnitLink.setStartPoint(new Point2D(x + (width), y + (height / 2)));

		canvasController.setStartArrow(true);
		startSegmentId = segmentIdAct;

		return  workUnitLink;
	}

	private ChangeArtifactLink createChangeArtifactLink(CanvasController canvasController, int startSegmentIdAct, double x, double y, double width, double height) {

		int id = identificatorCreater.createLineID();
		ChangeArtifactLink link = new ChangeArtifactLink(id,this);

		canvasController.addLinkToCanvas(link);
		segmentLists.addLinkToList(link);

		link.setStartPoint(new Point2D(x + (width), y + (height / 2)));

		canvasController.setStartArrow(true);
		startSegmentId = startSegmentIdAct;

		return link;

	}

	/**
	 * Kontrola vyplněného formuláře
	 *
	 * @return true pokud je kontrola v pořadku v opačném případě false
	 */

	private boolean isFormfillControl(int formIdentificator) {

		return formController.isFormFill(formIdentificator);
	}

	/**
	 * Rozhodne o zvoleném elementu přidá jeho IDs do Linku
	 *
	 * @return rozhodnuti o pridani prvku
	 */
	private boolean segmentControl(int segmentIdAct, SegmentType segmentType) {
		if (isFormfillControl(segmentIdAct)) {

			if (startSegmentId == -1 && segmentType == SegmentType.Change) {
				return true;
			} else if (endSegmentId == -1 && segmentType == SegmentType.Artifact) {
				return true;
			}
		} else {
			Alerts.showNoWorkUnit();
		}

		return false;
	}
	/**
	 * Kontrola o vyplnění formuláře daného Work Unit
	 **/

	private boolean segmentWorkUnitControl(int segmentId) {
		//if (lists.getWorkUnitList().get(item.getIDs()[1]) != null) {

			if (startSegmentId != segmentId) {
				return true;
			}
		//} else {
		//	Alerts.showNoWorkUnit();
		//}

		return false;
	}

	/**
	 * Smaže spojnici ze seznamu a odmaže spojení z elementu Change a Artifact
	 */

	public void deleteArrow(int arrowId, int changeID, int artifactID) {

		segmentLists.removeArrow(arrowId);

		dataManipulator.removeArtifactChangeLink(artifactID, changeID);
	}

	/**
	 * Smaže spojnici ze seznamu a odmaže spojení z Work Unit a Work Unit
	 */
	public void deleteWorkUnitArrow(LinkController linkController) {

		segmentLists.removeArrow(linkController.getLinkId());
		dataManipulator.removeWorkUnitRelation(linkController.getStartItemId(), linkController.getEndItemId());
	}

	/**
	 * Nastaví indexi propojovaným elementů Change a Artifact, nastaví
	 * identifikaci spojení
	 **/
	public void setChangeArtifactRelation(NodeLink link) {

		//int changeIndex = link.getStartIDs()[1];

		//int artifactIndex = link.getEndIDs()[1];



	}

	/**
	 * Nastaví indexi propojovaným elementů WorkUnit a nastaví identifikaci
	 * spojení
	 **/
	public void setWorkUnitRelation(NodeLink link) {

	}

	public void deleteLinks(List<Integer> mStartLinkIds, List<Integer> mEndLinkIds) {

		for (int i = 0; i < mStartLinkIds.size(); i++) {
			segmentLists.removeArrow(mStartLinkIds.get(i));
			}

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			segmentLists.removeArrow(mEndLinkIds.get(i));
		}
	}

	public void repaintArrow(SegmentType segmentType, List<Integer> mStartLinkIds, List<Integer> mEndLinkIds,
							 double translateX, double translateY, double width, double height) {

		double newWidth = translateX + width;
		double newHeight = translateY + (height / 2);

		for (int i = 0; i < mStartLinkIds.size(); i++) {

			int index = mStartLinkIds.get(i);
			segmentLists.repaintArrowStartPoint(index, newWidth, newHeight);

			if (segmentType == SegmentType.WorkUnit) {
				segmentLists.repaintWorkUnitComboBox(index);
			}
		}

		for (int i = 0; i < mEndLinkIds.size(); i++) {

			int index = mStartLinkIds.get(i);
			segmentLists.repaintArrowEndPoint(index, translateX, translateY + (height / 2));

			if (segmentType == SegmentType.WorkUnit) {
					segmentLists.repaintWorkUnitComboBox(index);
					segmentLists.repaintWorkUnitRelationEndPoint(index, translateX, translateY + (height / 2));
			}

		}
	}
}
