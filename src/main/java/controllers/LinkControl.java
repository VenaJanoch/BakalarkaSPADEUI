package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphics.ElementsLink;
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

    private ElementsLink changeArtifactLink;
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

    private SegmentType firstSegmentType;

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

                if (segmentControl(segmentType) == -1) { // Zvoleny prvek neni konfigurace muze byt zalozena relace
                    Alerts.showConfigurationAsStartElement();
                }else{
                    changeArtifactLink = createChangeArtifactLink(canvasController, segmentIdAct, x, y, width,  height);
                }



        } else {
            int operation = segmentControl(segmentType);
            if (operation < 10){
                finishLinkFromOperation(operation, segmentIdAct, x, y, height, canvasController);
            }else{
                startSegmentId = -1;
                endSegmentId = -1;
                firstSegmentType = null;
                showOperationAlert(operation, segmentType);
            }
        }

    }

    private void showOperationAlert(int operation, SegmentType segmentType) {
        if ( operation == 10) {
          Alerts.showBadCommitRelation(segmentType);
        } else if (operation == 11) {
            Alerts.showBadCommitedConfigurationRelation(segmentType);
        } else if (operation == 12) {
            Alerts.showBadConfigurationRelation(segmentType);
        }else if (operation == 13) {
            Alerts.showBadArtifactRelation(segmentType);
        }else if (operation == 14) {
            Alerts.showBadRoleRelation(segmentType);
        }
    }

    private void finishLinkFromOperation(int operation, int segmentIdAct, double x, double y, double height, CanvasController canvasController){
        if ( operation == 1) {
            endSegmentId = segmentIdAct;
            formController.createCommitToCommitedConfigurationRelation(startSegmentId, endSegmentId);
            finishLink(x, y, height, canvasController);
        } else if (operation == 2) {
            endSegmentId = segmentIdAct;
       //     formController.createCommitedConfigurationToConfigurationRelation(startSegmentId, endSegmentId);
            finishLink(x, y, height, canvasController);
        } else if (operation == 3) {
            endSegmentId = segmentIdAct;
        //    formController.createArtifactToConfigurationRelation(startSegmentId, endSegmentId);
            finishLink(x, y, height, canvasController);
        }else if (operation == 4) {
            endSegmentId = segmentIdAct;
        //    formController.createRoleToConfigurationRelation(startSegmentId, endSegmentId);
            finishLink(x, y, height, canvasController);
        }else if (operation == 5) {
            endSegmentId = segmentIdAct;
        //    formController.createRoleToArtifactRelation(startSegmentId, endSegmentId);
            finishLink(x, y, height, canvasController);
        }
    }


    private void finishLink(double x, double y, double height, CanvasController canvasController){
        changeArtifactLink.setEndPoint(new Point2D(x, y + (height / 2)));
        canvasController.setStartArrow(false);

        endLinkIdMap.get(endSegmentId).add(this.id);
        startSegmentId = -1;
        endSegmentId = -1;
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

    private ElementsLink createChangeArtifactLink(CanvasController canvasController, int startSegmentIdAct, double x, double y, double width, double height) {

        id = identificatorCreater.createLineID();
        ElementsLink link = new ElementsLink(id, this, canvasController, manipulationController);

        canvasController.addLinkToCanvas(link);
        segmentLists.addLinkToList(link);

        link.setStartPoint(new Point2D(x + (width-Constans.offset), y + (height / 2)));

        canvasController.setStartArrow(true);
        startSegmentId = startSegmentIdAct;

        return link;

    }


    /**
     * Rozhodne o zvoleném elementu přidá jeho IDs do Linku
     *
     * @return rozhodnuti o pridani prvku
     */
    private int segmentControl(SegmentType segmentType) {

            if (startSegmentId == -1) {
                firstSegmentType = segmentType;
                return 0;
            } else if (endSegmentId == -1 && (segmentType != firstSegmentType)) {
                if (firstSegmentType == SegmentType.Commit && segmentType == SegmentType.Committed_Configuration){
                    return 1;
                }else if(firstSegmentType == SegmentType.Committed_Configuration){

                    if (segmentType == SegmentType.Commit){
                        return 1;
                    }else if( segmentType == SegmentType.Configuration){
                        return 2;
                    }
                    return 11;
                }else if(firstSegmentType == SegmentType.Artifact){
                    if (segmentType == SegmentType.Person){
                        return 4;
                    }else if( segmentType == SegmentType.Configuration){
                        return 3;
                    }
                    return 12;
                }else if(firstSegmentType == SegmentType.Person){
                    if (segmentType == SegmentType.Artifact){
                        return 4;
                    }else if( segmentType == SegmentType.Configuration){
                        return 5;
                    }
                    return 13;
                }else if(firstSegmentType == SegmentType.Configuration){
                    if (segmentType == SegmentType.Committed_Configuration){
                        return 2;
                    }else if( segmentType == SegmentType.Artifact){
                        return 3;
                    }else if( segmentType == SegmentType.Person){
                    return 5;
                    }
                    return 14;
                }
            }
            return 10;
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

            if (segmentType == SegmentType.Work_Unit) {
                segmentLists.repaintWorkUnitComboBox(index);
            }
        }

        for (int i = 0; i < mEndLinkIds.size(); i++) {

            int index = mEndLinkIds.get(i);
            segmentLists.repaintArrowEndPoint(index, translateX, translateY + (height / 2));

            if (segmentType == SegmentType.Work_Unit) {
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
