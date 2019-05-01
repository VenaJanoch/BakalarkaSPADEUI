package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import controllers.formControllers.ManipulationController;
import controllers.graphicsComponentsControllers.CanvasController;
import graphics.canvas.ElementsLink;
import graphics.canvas.NodeLink;
import interfaces.IDeleteFormController;
import javafx.geometry.Point2D;
import model.DataManipulator;
import model.IdentificatorCreater;
import services.*;

public class LinkControl {

    /**
     * Globální proměnné třídy
     */

    private int id;

    private ElementsLink changeArtifactLink;

    private int startSegmentId = -1;
    private int endSegmentId = -1;

    private IdentificatorCreater identificatorCreater;
    private FormController formController;
    private SegmentLists segmentLists;

    private DataManipulator dataManipulator;
    private IDeleteFormController deleteFormController;

    private Map<Integer, List<Integer>> startLinkIdMap;
    private Map<Integer, List<Integer>> endLinkIdMap;

    private SegmentType firstSegmentType;

    ManipulationController manipulationController;

    /**
     * Konstruktor třídy Zinicializuje globální proměnné třídy
     */
    public LinkControl(FormController formController, IdentificatorCreater identificatorCreater, SegmentLists segmentLists,
                       DeleteFormController deleteFormController, ManipulationController manipulationController) {
        this.segmentLists = segmentLists;
        this.identificatorCreater = identificatorCreater;
        this.formController = formController;

        this.deleteFormController = deleteFormController;
        this.startLinkIdMap = new HashMap<>();
        this.endLinkIdMap = new HashMap<>();
        this.manipulationController = manipulationController;
    }

    /**
     * Rozhodne o propojení Change a Artifact Vytvoří instanci třídy NodeLink a
     * přídá ji do seznamu Rozhodne o počátečním a koncovém prvku
     */
    public void ArrowManipulation(boolean startArrow, CanvasController canvasController, int segmentIdAct, SegmentType segmentType,
                                  double x, double y, double width, double height, boolean isXML, int id) {

        if (!startArrow) {
            this.id = id;
            if (segmentControl(segmentType) == -1) { // Zvoleny prvek neni konfigurace muze byt zalozena relace
                Alerts.showConfigurationAsStartElement();
            } else {
                changeArtifactLink = createLink(canvasController, segmentIdAct, x, y, width, height, isXML);
            }


        } else {
            int operation = segmentControl(segmentType);
            if (operation < 10) {
                    finishLinkFromOperation(operation, segmentIdAct, x, y, height, canvasController, isXML);
            } else {
                startSegmentId = -1;
                endSegmentId = -1;
                firstSegmentType = null;
                showOperationAlert(operation, segmentType);
            }
        }

    }

    private void showOperationAlert(int operation, SegmentType segmentType) {
        if (operation == 10) {
            Alerts.showBadCommitRelation(segmentType);
        } else if (operation == 11) {
            Alerts.showBadCommitedConfigurationRelation(segmentType);
        } else if (operation == 12) {
            Alerts.showBadConfigurationRelation(segmentType);
        } else if (operation == 13) {
            Alerts.showBadArtifactRelation(segmentType);
        } else if (operation == 14) {
            Alerts.showBadRoleRelation(segmentType);
        }
    }

    private void finishLinkFromOperationWithOutCreateLink(int operation, double x, double y, double height, CanvasController canvasController) {
        if (operation == 1) {
                    } else if (operation == 2) {
         } else if (operation == 3) {
        } else if (operation == 5) {
           } else if (operation == 4) {

        }
    }
    private void finishLinkFromOperation(int operation, int segmentIdAct, double x, double y, double height, CanvasController canvasController, boolean isXML) {
        if (operation == 1) {
            endSegmentId = segmentIdAct;
            formController.createCommitToCommitedConfigurationRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Commit_Commited_Configuration);
        } else if (operation == 2) {
            endSegmentId = segmentIdAct;
            formController.createCommitedConfigurationToConfigurationRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Commited_Configuration_Configuration);
        } else if (operation == 3) {
            endSegmentId = segmentIdAct;
            formController.createArtifactToConfigurationRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Artifact_Configuration);
        } else if (operation == 5) {
            endSegmentId = segmentIdAct;
            formController.createRoleToConfigurationRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Configuration);
        } else if (operation == 4) {
            endSegmentId = segmentIdAct;
            formController.createRoleToArtifactRelation(id ,startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Artifact);
        }else if (operation == 6) {
            endSegmentId = segmentIdAct;
            formController.createRoleToCommitRelation(id ,startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Commit);
        }else if (operation == 7) {
            endSegmentId = segmentIdAct;
            formController.createRoleToCommttedConfigurationRelation(id ,startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Commtted_Configuration);
        }
    }


    private void finisLink(double x, double y, double height, CanvasController canvasController, LinkType linkType) {
        changeArtifactLink.setEndPoint(new Point2D(x, y + (height / 2)));
        canvasController.setStartArrow(false);
        changeArtifactLink.setLinkType(linkType);
        changeArtifactLink.setIdsToController(startSegmentId, endSegmentId);
        changeArtifactLink.setVisible(true);
        startLinkIdMap.get(startSegmentId).add(id);
        endLinkIdMap.get(endSegmentId).add(id);
        startSegmentId = -1;
        endSegmentId = -1;
    }

    private ElementsLink createLink(CanvasController canvasController, int startSegmentIdAct, double x, double y, double width, double height, boolean isXML) {

        if(!isXML){
            id = identificatorCreater.createLineID();
        }
        ElementsLink link = new ElementsLink(id, this, canvasController, manipulationController);

        canvasController.addLinkToCanvas(link);
        segmentLists.addLinkToList(link);

        link.setStartPoint(new Point2D(x + (width - Constans.offset), y + (height / 2)));

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
            if (firstSegmentType == SegmentType.Commit) {
                if (segmentType == SegmentType.Committed_Configuration){
                    return 1;
                }else if (segmentType == SegmentType.Person){
                    return 6;
                }

            } else if (firstSegmentType == SegmentType.Committed_Configuration) {

                if (segmentType == SegmentType.Commit) {
                    return 1;
                } else if (segmentType == SegmentType.Configuration) {
                    return 2;
                } else if (segmentType == SegmentType.Person) {
                    return 7;
                }
                return 11;
            } else if (firstSegmentType == SegmentType.Artifact) {
                if (segmentType == SegmentType.Person) {
                    return 4;
                } else if (segmentType == SegmentType.Configuration) {
                    return 3;
                }
                return 12;
            } else if (firstSegmentType == SegmentType.Person) {
                if (segmentType == SegmentType.Artifact) {
                    return 4;
                } else if (segmentType == SegmentType.Configuration) {
                    return 5;
                }else if (segmentType == SegmentType.Commit) {
                    return 6;
                }else if (segmentType == SegmentType.Committed_Configuration) {
                    return 7;
                }
                return 13;
            } else if (firstSegmentType == SegmentType.Configuration) {
                if (segmentType == SegmentType.Committed_Configuration) {
                    return 2;
                } else if (segmentType == SegmentType.Artifact) {
                    return 3;
                } else if (segmentType == SegmentType.Person) {
                    return 5;
                }
                return 14;
            }
        }
        return 10;
    }

    /**
     * Smaže spojnici ze seznamu a odmaže spojení z elementu Change a Artifact
     */

    public void deleteArrow(int arrowId) {

        segmentLists.removeArrow(arrowId);
     //   deleteDataModel.removeArtifactChangeLink(artifactID, changeID);

    }

    public void deleteArrow(int arrowId, int startId, int endId, LinkType linkType){

        ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);
        List<Integer> mStartLinkIds = startLinkIdMap.get(startId);
        List<Integer> mEndLinkIds = endLinkIdMap.get(endId);
        removeLinkFromIds(arrowId, mStartLinkIds);
        removeLinkFromIds(arrowId, mEndLinkIds);
        switch (linkType){
            case Person_Artifact:
                deleteFormController.removePersonArtifactLink(arrowId, startId, endId);

            break;
            case Person_Configuration:
                deleteFormController.removePersonConfigurationLink(arrowId, startId, endId);
                break;
            case Commit_Commited_Configuration:
                deleteFormController.removeCommitComiitedConfigurationLink(arrowId, startId, endId);
                break;
            case Commited_Configuration_Configuration:
                deleteFormController.removeCommitedConfigurationConfigurationLink(arrowId, startId, endId);
                break;
            case Artifact_Configuration:
                deleteFormController.removeArtifactConfiguraionLink(arrowId, startId, endId);
                break;
            default:
        }

        deleteArrow(arrowId);
    }

    private void removeLinkFromIds(int arrowId, List<Integer> mStartLinkIds) {
        if (mStartLinkIds != null) {
            for (int i = 0; i < mStartLinkIds.size(); i++) {
                int startIndex = mStartLinkIds.get(i);
                if (startIndex == arrowId) {
                    mStartLinkIds.remove(i);
                    break;
                }


            }
        }

    }


    public void deleteLinks(int itemIdentificator) {

        List<Integer> mStartLinkIds = startLinkIdMap.get(itemIdentificator);
        List<Integer> mEndLinkIds = endLinkIdMap.get(itemIdentificator);

        for (int i = mStartLinkIds.size() - 1; i >= 0; i--) {
            int arrowId = mStartLinkIds.get(i);
            ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);

            int endId = link.getLinkController().getEndItemId();
            List<Integer> mEndLinkIdsLoc = endLinkIdMap.get(endId);
            removeLinkFromIds(arrowId, mEndLinkIdsLoc);
            mStartLinkIds.remove(i);

            link.deleteArrow();
        }

        for (int i = mEndLinkIds.size() - 1; i >= 0; i--) {
            int arrowId = mEndLinkIds.get(i);
            ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);
            int startId = link.getLinkController().getStartItemId();
            List<Integer> mStartLinkIdsLoc = startLinkIdMap.get(startId);
            removeLinkFromIds(arrowId, mStartLinkIdsLoc);
            mEndLinkIds.remove(i);
            link.deleteArrow();
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
            NodeLink link = segmentLists.getArrow(index);
            link.repaintArrowStartPoint(index, newWidth, newHeight);
        }

        for (int i = 0; i < mEndLinkIds.size(); i++) {

            int index = mEndLinkIds.get(i);
            NodeLink link = segmentLists.getArrow(index);
            link.repaintArrowEndPoint(index, translateX, translateY + (height / 2));
        }
    }

    public void createLinkInstanceInMap(int formIndex) {
        startLinkIdMap.put(formIndex, new ArrayList<>());
        endLinkIdMap.put(formIndex, new ArrayList<>());
    }
}
