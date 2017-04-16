package services;

import java.util.ArrayList;

import SPADEPAC.Link;
import SPADEPAC.ObjectFactory;
import graphics.CanvasItem;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import javafx.geometry.Point2D;

public class LinkControl {

	private ArrayList<NodeLink> arrows;
	private NodeLink link;
	private WorkUnitLink wLink;
	private Control control;
	private SegmentLists lists;
	private int id;
	private ObjectFactory objF;
	public LinkControl(Control control, SegmentLists lists, ObjectFactory objF) {
		this.setArrows(new ArrayList<>());
		this.control = control;
		this.lists = lists;
		this.objF = objF;
	}
	
	public void ArrowManipulation(CanvasItem item, boolean isSave) {

		if (!control.isStartArrow()) {

			id = IdentificatorCreater.createLineID();
			link = new NodeLink(id, control, SegmentType.Configuration, this);

			sortSegmentConf(item);
			// line = new Line();
			item.getCanvas().getChildren().add(link);

			getArrows().add(id, link);

			link.setStart(new Point2D(item.getTranslateX() + (item.getWidth()),
					item.getTranslateY() + (item.getHeight() / 2)));

			item.registerStartLink(id);

			control.setStartArrow(true);

		} else {

			sortSegmentConf(item);

			link.setEnd(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
			item.registerEndLink(id);
			control.setStartArrow(false);
			if(!isSave){
				setChangeArtifactRelation(link);				
			}

		}

	}

	public void ArrowManipulationWorkUnit(CanvasItem item, boolean isSave) {

		if (!control.isStartArrow()) {

			id = IdentificatorCreater.createLineID();
			wLink = new WorkUnitLink(id, control, item.getCanvas(), this);

			wLink.setStartIDs(item.getIDs());
			item.getCanvas().getChildren().add(wLink);

			getArrows().add(id, wLink);

			wLink.setStart(new Point2D(item.getTranslateX() + (item.getWidth()),
					item.getTranslateY() + (item.getHeight() / 2)));

			item.registerStartLink(id);

			control.setStartArrow(true);

		} else {

			// sortSegmentConf(item);
			wLink.setEndIDs(item.getIDs());
			wLink.setArrowAndBox(new Point2D(item.getTranslateX(), item.getTranslateY() + (item.getHeight() / 2)));
			item.registerEndLink(id);
			control.setStartArrow(false);
			if (!isSave) {
				setWorkUnitRelation(wLink);	
			}

		}

	}

	public void deleteArrow(int arrowId, int changeID, int artifactID) {
		lists.getArtifactList().get(artifactID).setChangeIndex(-1);
		lists.getChangeList().get(changeID).setArtifactIndex(-1);

	}
	
	public void deleteWorkUnitArrow(int arrowId, int leftID, int rightID) {
			lists.getWorkUnitList().get(leftID).setRightUnitIndex(-1);
			lists.getWorkUnitList().get(rightID).setLeftUnitIndex(-1);
	}

	public void setChangeArtifactRelation(NodeLink link) {

		int changeIndex = link.getStartIDs()[1];
		
		int artifactIndex = link.getEndIDs()[1];
		Link linkP = objF.createLink();
		
		linkP.setType("Config");
		linkP.setArtifactIndex(artifactIndex);
		linkP.setChangeIndex(changeIndex);

		lists.getLinksList().add(linkP);
		
		lists.getChangeList().get(changeIndex).setArtifactIndex(artifactIndex);
		lists.getArtifactList().get(artifactIndex).setChangeIndex(changeIndex);

	}
	
	public void setWorkUnitRelation(NodeLink link) {

		int leftIndex = link.getStartIDs()[1];
		
		int rightIndex = link.getEndIDs()[1];
		Link linkP = objF.createLink();
		
		linkP.setType("WorkUnit");
		linkP.setLeftUnitIndex(leftIndex);
		linkP.setRightUnitIndex(rightIndex);

		lists.getLinksList().add(linkP);
		
		lists.getWorkUnitList().get(leftIndex).setRightUnitIndex(rightIndex);;
		lists.getWorkUnitList().get(rightIndex).setLeftUnitIndex(leftIndex);

	}

	private void sortSegmentConf(CanvasItem item) {

		if (item.getType() == SegmentType.Change) {
			link.setStartIDs(item.getIDs());
		} else {
			link.setEndIDs(item.getIDs());
		}

	}

	public ArrayList<NodeLink> getArrows() {
		return arrows;
	}

	public void setArrows(ArrayList<NodeLink> arrows) {
		this.arrows = arrows;
	}

}
