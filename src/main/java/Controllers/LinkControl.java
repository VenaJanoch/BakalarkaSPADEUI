package Controllers;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.Link;
import SPADEPAC.ObjectFactory;
import graphics.CanvasItem;
import graphics.ChangeArtifactLink;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.geometry.Point2D;
import model.IdentificatorCreater;
import services.*;

public class LinkControl {

	/** Globální proměnné třídy */
	private ArrayList<NodeLink> arrows;
	private NodeLink link;
	private WorkUnitLink wLink;
	private Control control;
	private SegmentLists lists;
	private int id;
	private IdentificatorController identificatorController;
	private FormController formController;

	/**
	 * Konstruktor třídy Zinicializuje globální proměnné třídy
	 *
	 */
	public LinkControl(FormController formController, IdentificatorController identificatorController) {
		this.setArrows(new ArrayList<>());
		this.control = control;
		this.lists = lists;

	}
	
	public void restart(){
		arrows.clear();
	}

	/**
	 * Rozhodne o propojení Change a Artifact Vytvoří instanci třídy NodeLink a
	 * přídá ji do seznamu Rozhodne o počátečním a koncovém prvku
	 *
	 * @param isSave
	 *            informace o uložení a ochrana před vytvořením dvojího spojení
	 */
	public void ArrowManipulation(boolean isSave, boolean startArrow) {

		if (!startArrow) {
			try {
				if (isFormfillControl(item)) {

					id = idCreator.createLineID();
					link = new ChangeArtifactLink(id, control, SegmentType.Configuration, this);

					sortSegmentConf(item);
					item.getCanvas().getChildren().add(link);
					getArrows().add(id, link);
					link.setStartPoint(new Point2D(item.getTranslateX() + (item.getWidth()),
							item.getTranslateY() + (item.getHeight() / 2)));

					item.registerStartLink(id);

					item.getDgCanvas().setStartArrow(true);
				} else {
					Alerts.showNoWorkUnit();
				}

			} catch (Exception e) {
				e.printStackTrace();
				Alerts.showNoWorkUnit();
			}

		} else {
			try {
				if (segmentControl(item)) {

					sortSegmentConf(item);

					link.setEndPoint(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
					item.registerEndLink(id);
					item.getDgCanvas().setStartArrow(false);
					if (!isSave) {
						setChangeArtifactRelation(link);
					}
				}

			} catch (Exception e) {
				Alerts.showNoWorkUnit();
			}

		}

	}

	/**
	 * Kontrola vyplněného formuláře
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 * @return true pokud je kontrola v pořadku v opačném případě false
	 */

	private boolean isFormfillControl(CanvasItem item) {

		if (item.getType() == SegmentType.Change) {
			if (lists.getChangeList().get(item.getIDs()[1]) == null) {
				return false;
			}
		} else {
			if (lists.getArtifactList().get(item.getIDs()[1]) == null) {
				return false;
			}

		}
		return true;
	}

	/**
	 * Rozhodne o zvoleném elementu přidá jeho IDs do Linku
	 * 
	 * @param item
	 *            CanvasItem
	 * @return rozhodnuti o pridani prvku
	 */
	private boolean segmentControl(CanvasItem item) {
		if (isFormfillControl(item)) {

			if (link.getStartIDs() == null && item.getType() == SegmentType.Change) {
				return true;
			} else if (link.getEndIDs() == null && item.getType() == SegmentType.Artifact) {
				return true;
			}
		} else {
			Alerts.showNoWorkUnit();
		}

		return false;
	}

	/**
	 * Rozhodne o propojení Work Unit a Work Unit Vytvoří instanci třídy
	 * WorkUnitLink a přídá ji do seznamu. Rozhodne o počátečním a koncovém
	 * prvku
	 * 
	 * @param item
	 *            instance třídy CanvasItem
	 * @param isSave
	 *            informace o uložení a ochrana před vytvořením dvojího spojení
	 */
	public void ArrowManipulationWorkUnit(CanvasItem item, boolean isSave) {

		if (!item.getDgCanvas().isStartArrow()) {
			try {

				if (control.getLists().getWorkUnitList().get(item.getIDs()[1]) != null) {

					id = idCreator.createLineID();
					wLink = new WorkUnitLink(id, control, item.getCanvas(), this);

					wLink.setStartIDs(item.getIDs());
					item.getCanvas().getChildren().add(wLink);

					getArrows().add(id, wLink);

					wLink.setStartPoint(new Point2D(item.getTranslateX() + (item.getWidth()),
							item.getTranslateY() + (item.getHeight() / 2)));

					item.registerStartLink(id);

					item.getDgCanvas().setStartArrow(true);
				} else {
					Alerts.showNoWorkUnit();
				}

			} catch (Exception e) {
				e.printStackTrace();
				Alerts.showNoWorkUnit();
			}

		} else {

			try {
				if (segmentWorkUnitControl(item)) {

					wLink.setEndIDs(item.getIDs());
					wLink.setArrowAndBox(
							new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
					item.registerEndLink(id);
					item.getDgCanvas().setStartArrow(false);

					if (!isSave) {
						setWorkUnitRelation(wLink);
					}

				}

			} catch (Exception e) {
				Alerts.showNoWorkUnit();
			}

		}

	}

	/**
	 * Kontrola o vyplnění formuláře daného Work Unit
	 **/

	private boolean segmentWorkUnitControl(CanvasItem item) {
		if (lists.getWorkUnitList().get(item.getIDs()[1]) != null) {

			if (item.getIDs()[1] != wLink.getStartIDs()[1]) {
				return true;
			}
		} else {
			Alerts.showNoWorkUnit();
		}

		return false;
	}

	/**
	 * Smaže spojnici ze seznamu a odmaže spojení z elementu Change a Artifact
	 */

	public void deleteArrow(int arrowId, int changeID, int artifactID) {

		arrows.remove(arrowId);
		arrows.add(arrowId, null);

		lists.getArtifactList().get(artifactID).getChangeIndex().remove(0);
		lists.getChangeList().get(changeID).getArtifactIndex().remove(0);

	}

	/**
	 * Smaže spojnici ze seznamu a odmaže spojení z Work Unit a Work Unit
	 */
	public void deleteWorkUnitArrow(int arrowId, int leftID, int rightID) {

		arrows.remove(arrowId);
		arrows.add(arrowId, null);

		lists.getWorkUnitList().get(leftID).setRightUnitIndex(-1);
		lists.getWorkUnitList().get(rightID).setLeftUnitIndex(-1);
	}

	/**
	 * Nastaví indexi propojovaným elementů Change a Artifact, nastaví
	 * identifikaci spojení
	 **/
	public void setChangeArtifactRelation(NodeLink link) {

		int changeIndex = link.getStartIDs()[1];

		int artifactIndex = link.getEndIDs()[1];
		Link linkP = objF.createLink();

		linkP.setType("Config");
		linkP.setArtifactIndex(artifactIndex);
		linkP.setChangeIndex(changeIndex);

		lists.getLinksList().add(linkP);

		lists.getChangeList().get(changeIndex).getArtifactIndex().add(artifactIndex);
		lists.getArtifactList().get(artifactIndex).getChangeIndex().add(changeIndex);

	}

	/**
	 * Nastaví indexi propojovaným elementů WorkUnit a nastaví identifikaci
	 * spojení
	 **/
	public void setWorkUnitRelation(NodeLink link) {

		int leftIndex = link.getStartIDs()[1];

		int rightIndex = link.getEndIDs()[1];
		Link linkP = objF.createLink();

		linkP.setType("WorkUnit");
		linkP.setLeftUnitIndex(leftIndex);
		linkP.setRightUnitIndex(rightIndex);

		lists.getLinksList().add(linkP);

		lists.getWorkUnitList().get(leftIndex).setRightUnitIndex(rightIndex);
		;
		lists.getWorkUnitList().get(rightIndex).setLeftUnitIndex(leftIndex);

	}

	/**
	 * Rozhodnutí typu prvku pro spojení
	 **/
	private void sortSegmentConf(CanvasItem item) {

		if (item.getType() == SegmentType.Change) {
			link.setStartIDs(item.getIDs());
		} else {
			link.setEndIDs(item.getIDs());
		}

	}

	/**
	 * Geters and Setrs
	 **/
	public ArrayList<NodeLink> getArrows() {
		return arrows;
	}

	public void setArrows(ArrayList<NodeLink> arrows) {
		this.arrows = arrows;
	}

	public void deleteLinks(List<Integer> mStartLinkIds, List<Integer> mEndLinkIds) {

		for (int i = 0; i < mStartLinkIds.size(); i++) {
			if (arrows.get(mStartLinkIds.get(i)) != null) {
				arrows.get(mStartLinkIds.get(i)).deleteArrow();
			}
		}

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			if (arrows.get(mEndLinkIds.get(i)) != null) {
				arrows.get(mEndLinkIds.get(i)).deleteArrow();
			}
		}

	}

	public void repaintArrow(SegmentType segmentType, List<Integer> mStartLinkIds, List<Integer> mEndLinkIds,
							 double translateX, double translateY, double width, double height) {


		for (int i = 0; i < mStartLinkIds.size(); i++) {
			if (arrows.get(mStartLinkIds.get(i)) != null) {

				int arrowIndex = mStartLinkIds.get(i);

				double newWidth = translateX + width;
				double newHeight = translateY + (height / 2);

				NodeLink link = arrows.get(arrowIndex);

				link.setStartPoint(new Point2D(newWidth, newHeight));

				if (segmentType == SegmentType.WorkUnit) {
					Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
					link.relationCB.setTranslateX(center.getX());
					link.relationCB.setTranslateY(center.getY());
				}
			}
		}

		for (int i = 0; i < mEndLinkIds.size(); i++) {
			if (arrows.get(mEndLinkIds.get(i)) != null) {
				NodeLink link = arrows.get(mEndLinkIds.get(i));

				link.setEndPoint(new Point2D(translateX, translateY + (height / 2)));

				if (segmentType == SegmentType.WorkUnit) {
					link.setEndPoint(new Point2D(translateX, translateY + (height / 2)),
							Constans.ArrowRadius);
					Point2D center = control.calculateCenter(link.startPoint, link.endPoint);
					link.relationCB.setTranslateX(center.getX());
					link.relationCB.setTranslateY(center.getY());
					link.polygon.getPoints().clear();
					link.polygon.getPoints().addAll(control.calculateArrowPosition(link.endPoint));
				}

			}
		}

	}

}
