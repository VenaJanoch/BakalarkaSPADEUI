package Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphics.ChangeArtifactLink;
import graphics.NodeLink;
import graphics.WorkUnitLink;
import interfaces.IDeleteDataModel;
import javafx.geometry.Point2D;
import model.DataManipulator;
import model.DataModel;
import model.IdentificatorCreater;
import services.*;

public class LinkControl {

    /**
     * Globální proměnné třídy
     */

    private int id;

    private ChangeArtifactLink changeArtifactLink;
    private WorkUnitLink workUnitLink;

    private int startSegmentId = -1;
    private int endSegmentId = -1;

    private IdentificatorCreater identificatorCreater;
    private FormController formController;
    private SegmentLists segmentLists;

    private DataManipulator dataManipulator;
    private IDeleteDataModel deleteDataModel;

    private Map<Integer, List<Integer>> startLinkIdMap;
    private Map<Integer, List<Integer>> endLinkIdMap;

    ManipulationController manipulationController;
    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public LinkControl(FormController formController, IdentificatorCreater identificatorCreater, SegmentLists segmentLists,
            DataModel dataModel, ManipulationController manipulationController) {
        this.segmentLists = segmentLists;
        this.identificatorCreater = identificatorCreater;
        this.formController = formController;
        this.deleteDataModel = dataModel.getDeleteDataModel();
        this.dataManipulator = dataModel.getDataManipulator();
        this.startLinkIdMap = new HashMap<>();
        this.endLinkIdMap = new HashMap<>();
        this.manipulationController = manipulationController;
    }


    public void ArrowManipulation(boolean isSave, boolean startArrow, CanvasController canvasController, int segmentIdAct, SegmentType segmentType,
                                  double x, double y, double width, double height, int relationIndex) {

        ArrowManipulation(isSave, startArrow, canvasController, segmentIdAct, segmentType, x, y, width, height);
        workUnitLink.setRelationIndexToComboBox(relationIndex);

    }

    /**
     * Rozhodne o propojení Change a Artifact Vytvoří instanci třídy NodeLink a
     * přídá ji do seznamu Rozhodne o počátečním a koncovém prvku
     *
     * @param isSave informace o uložení a ochrana před vytvořením dvojího spojení
     */
    public void ArrowManipulation(boolean isSave, boolean startArrow, CanvasController canvasController, int segmentIdAct, SegmentType segmentType,
                                  double x, double y, double width, double height) {

        if (!startArrow) {
            try {
                if (isFormfillControl(segmentIdAct)) {
                    if (segmentControl(segmentIdAct, segmentType)) {
                        changeArtifactLink = createChangeArtifactLink(canvasController, segmentIdAct, x, y, width, height);
                        startLinkIdMap.get(segmentIdAct).add(this.id);
                    } else if(SegmentType.WorkUnit == segmentType) {
                        workUnitLink = createWorkUnitLink(canvasController, segmentIdAct, x, y, width, height);
                        startLinkIdMap.get(segmentIdAct).add(this.id);
                    }else {
                        Alerts.showNoWorkUnit();
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
                    endLinkIdMap.get(segmentIdAct).add(this.id);
                    startSegmentId = -1;
                    endSegmentId = -1;
                } else if (segmentWorkUnitControl(segmentIdAct)) {
                    endSegmentId = segmentIdAct;
                    workUnitLink.setArrowAndBox(
                            new Point2D(x, y + (height / 2)));
                    canvasController.setStartArrow(false);
                    if (!isSave) {
                        formController.createWorkUnitRelation(startSegmentId, endSegmentId);
                        }
                    endLinkIdMap.get(segmentIdAct).add(this.id);
                    startSegmentId = -1;
                    endSegmentId = -1;
                }

            } catch (Exception e) {
                Alerts.showNoWorkUnit();
            }
        }

    }

    private WorkUnitLink createWorkUnitLink(CanvasController canvasController, int segmentIdAct, double x, double y, double width, double height) {

        id = identificatorCreater.createLineID();
        WorkUnitLink workUnitLink = new WorkUnitLink(id, this, canvasController, segmentLists.getRelationTypeObservable(), manipulationController);
        canvasController.addLinkToCanvas(workUnitLink);

        segmentLists.addLinkToList(workUnitLink);

        workUnitLink.setStartPoint(new Point2D(x + (width), y + (height / 2)));

        canvasController.setStartArrow(true);
        startSegmentId = segmentIdAct;

        return workUnitLink;
    }

    public void setRealtionIndexToLink(int id, int relationIndex){
        dataManipulator.setRelationIndexToLink(id, relationIndex);
    }

    private ChangeArtifactLink createChangeArtifactLink(CanvasController canvasController, int startSegmentIdAct, double x, double y, double width, double height) {

        id = identificatorCreater.createLineID();
        ChangeArtifactLink link = new ChangeArtifactLink(id, this, canvasController, manipulationController);

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
        //Todo zjistit za muze spojnice vychazet z Artifactu
            if (startSegmentId == -1 && (segmentType == SegmentType.Change || segmentType == SegmentType.Artifact)) {
                return true;
            } else if (endSegmentId == -1 && (segmentType == SegmentType.Change || segmentType == SegmentType.Artifact)) {
                return true;
            }
            return false;
    }

    /**
     * Kontrola o vyplnění formuláře daného Work Unit
     **/

    private boolean segmentWorkUnitControl(int segmentId) {
        if (startSegmentId != segmentId) {
            return true;
        }

        return false;
    }

    /**
     * Smaže spojnici ze seznamu a odmaže spojení z elementu Change a Artifact
     */

    public void deleteArrow(int arrowId, int changeID, int artifactID) {

        segmentLists.removeArrow(arrowId);

        deleteDataModel.removeArtifactChangeLink(artifactID, changeID);
    }

    /**
     * Smaže spojnici ze seznamu a odmaže spojení z Work Unit a Work Unit
     */
    public void deleteWorkUnitArrow(LinkController linkController) {
        segmentLists.removeArrow(linkController.getLinkId());
        deleteDataModel.removeWorkUnitRelation(linkController.getStartItemId(), linkController.getEndItemId());
    }

    /**
     * Nastaví indexi propojovaným elementů Change a Artifact, nastaví
     * identifikaci spojení
     **/
    public void setChangeArtifactRelation(NodeLink link) {

        //int changeIndex = link.getStartIDs()[1];

        //int artifactIndex = link.getEndIDs()[1];


    }


    public void deleteLinks(int itemIdentificator) {

        List<Integer> mStartLinkIds = startLinkIdMap.get(itemIdentificator);
        List<Integer> mEndLinkIds = endLinkIdMap.get(itemIdentificator);

        for (int i = 0; i < mStartLinkIds.size(); i++) {
            segmentLists.removeArrow(mStartLinkIds.get(i));
        }

        for (int i = 0; i < mEndLinkIds.size(); i++) {
            segmentLists.removeArrow(mEndLinkIds.get(i));
        }
    }

    public void repaintArrow(SegmentType segmentType, int itemIdentificator,
                             double translateX, double translateY, double width, double height) {

        List<Integer> mStartLinkIds = startLinkIdMap.get(itemIdentificator);
        List<Integer> mEndLinkIds = endLinkIdMap.get(itemIdentificator);

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

            int index = mEndLinkIds.get(i);
            segmentLists.repaintArrowEndPoint(index, translateX, translateY + (height / 2));

            if (segmentType == SegmentType.WorkUnit) {
                segmentLists.repaintWorkUnitComboBox(index);
                segmentLists.repaintWorkUnitRelationEndPoint(index, translateX, translateY + (height / 2));
            }

        }
    }

    public void createLinkInstanceInMap(int formIndex) {
        startLinkIdMap.put(formIndex, new ArrayList<>());
        endLinkIdMap.put(formIndex, new ArrayList<>());
    }
}
