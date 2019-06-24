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
/**
 * Trida predstavujici controller pro rizeni vytvoreni, smazani spojnice mezi prvky
 *
 * @author Václav Janoch
 */
public class LinkControl {

    /**
     * Globální proměnné třídy
     */

    /**Identificator nove spojnice**/
    private int id;


    private ElementsLink newLink;

    /**Identificator pocatecniho prvku**/
    private int startSegmentId = -1;
    /**Identificator koncoveho prvku**/
    private int endSegmentId = -1;

    /**Promenne z datoveho modelu**/
    private SegmentLists segmentLists;
    private IdentificatorCreater identificatorCreater;

    /**Kontrolery**/
    private FormController formController;
    private DataManipulator dataManipulator;
    private IDeleteFormController deleteFormController;

    /**
     * Mapa mapujici identifikator pocatecniho prvku na linky
     */
    private Map<Integer, List<Integer>> startLinkIdMap;
    /**
     * Mapa mapujici identifikator koncoveho prvku na linky
     */
    private Map<Integer, List<Integer>> endLinkIdMap;

    /**Typ prvniho zvoleneho prvku**/
    private SegmentType firstSegmentType;

    ManipulationController manipulationController;

    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     * @param formController instace FormController
     * @param identificatorCreater instace idetificatorCreater
     * @param segmentLists instace prehledovych seznamu
     * @param deleteFormController instace kontroleru pro odstraneni
     * @param manipulationController instace kontroleru pro kopirovani
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
     * @param startArrow logicka promena s informaci zada bude konec nebo zacatek sipky
     * @param canvasController instace CanvasController
     * @param segmentIdAct identifikator zvoleneho prvku
     * @param segmentType type zvoleneho prvku
     * @param x x souradnice prvku
     * @param y y souradnice prvku
     * @param width sirka prvku
     * @param height vyska prvku
     * @param isXML informace zda se spojnice nacita z XML
     * @param id identifikator spojnice
     */
    public void ArrowManipulation(boolean startArrow, CanvasController canvasController, int segmentIdAct, SegmentType segmentType,
                                  double x, double y, double width, double height, boolean isXML, int id) {

        if (!startArrow) {
            this.id = id;
            if (segmentControl(segmentType) == -1) { // Zvoleny prvek neni konfigurace muze byt zalozena relace
                Alerts.showConfigurationAsStartElement();
            } else {
                newLink = createLink(canvasController, segmentIdAct, x, y, width, height, isXML);
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

    /**
     * Metoda pro vyvolani informacniho okna s hlaskou o spatnem spojeni
     * @param operation cislo operace spojeni
     * @param segmentType typ prvku
     */
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

    /**
     * Metoda pro dokonceni operace vytvoreni spojnice mezi prvky
     * @param operation cislo operace spojeni
     * @param segmentIdAct identifikator konecneho prvku
     * @param x x souradnice
     * @param y y souradnice
     * @param height vyska prvku
     * @param canvasController instace CanvasController
     * @param isXML info o tom zda se nacita z XML
     */
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
            formController.createRoleToArtifactRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Artifact);
        } else if (operation == 6) {
            endSegmentId = segmentIdAct;
            formController.createRoleToCommitRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Commit);
        } else if (operation == 7) {
            endSegmentId = segmentIdAct;
            formController.createRoleToCommttedConfigurationRelation(id, startSegmentId, endSegmentId, isXML);
            finisLink(x, y, height, canvasController, LinkType.Person_Commtted_Configuration);
        }
    }


    /**
     * Metoda pro dokonceni graficke podoby spojnice
     * @param x x souradnice
     * @param y y souradnice
     * @param height vyska prvku
     * @param canvasController instace CanvasCotroller
     * @param linkType typ spojnice
     */
    private void finisLink(double x, double y, double height, CanvasController canvasController, LinkType linkType) {
        newLink.setEndPoint(new Point2D(x, y + (height / 2)));
        canvasController.setStartArrow(false);
        newLink.setLinkType(linkType);
        newLink.setIdsToController(startSegmentId, endSegmentId);
        newLink.setVisible(true);
        startLinkIdMap.get(startSegmentId).add(id);
        endLinkIdMap.get(endSegmentId).add(id);
        startSegmentId = -1;
        endSegmentId = -1;
    }

    /**
     * Metoda pro vytvoreni noveho Linku
     * @param canvasController instace CanvasController
     * @param startSegmentIdAct identifikator pocatecniho prvku
     * @param x x souradnice
     * @param y y souradnice
     * @param width sirka prvku
     * @param height vyska prvku
     * @param isXML info o tom zda se nacita z XML
     * @return nova instace ElementLink
     */
    private ElementsLink createLink(CanvasController canvasController, int startSegmentIdAct, double x, double y, double width, double height, boolean isXML) {

        if (!isXML) {
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
                if (segmentType == SegmentType.Committed_Configuration) {
                    return 1;
                } else if (segmentType == SegmentType.Person) {
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
                } else if (segmentType == SegmentType.Commit) {
                    return 6;
                } else if (segmentType == SegmentType.Committed_Configuration) {
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
     * Zavola metodu pro smazani prvku z prehledoveho seznamu
     * @param arrowId identifikator spojnice
     */

    public void deleteArrow(int arrowId) {

        segmentLists.removeArrow(arrowId);

    }

    /**
     * Metoda pro odstraneni spojnice
     * Zavola metody pro odstraneni spojnice pomoci DeleteFormController
     * @param arrowId identificator spojnice
     * @param startId identificator pocatecniho prvku
     * @param endId identifikator koncoveho prvku
     * @param linkType typ spojnice
     * @param isModelDelete informace o tam zda byla spojnice smazana z modelu
     */
    public void deleteArrow(int arrowId, int startId, int endId, LinkType linkType, boolean isModelDelete) {

        ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);
        List<Integer> mStartLinkIds = startLinkIdMap.get(startId);
        List<Integer> mEndLinkIds = endLinkIdMap.get(endId);
        removeLinkFromIds(arrowId, mStartLinkIds);
        removeLinkFromIds(arrowId, mEndLinkIds);
        switch (linkType) {
            case Person_Artifact:
                deleteFormController.removePersonArtifactLink(arrowId, startId, endId, isModelDelete);
                break;
            case Person_Configuration:
                deleteFormController.removePersonConfigurationLink(arrowId, startId, endId, isModelDelete);
                break;
            case Commit_Commited_Configuration:
                deleteFormController.removeCommitComiitedConfigurationLink(arrowId, startId, endId, isModelDelete);
                break;
            case Commited_Configuration_Configuration:
                deleteFormController.removeCommitedConfigurationConfigurationLink(arrowId, startId, endId, isModelDelete);
                break;
            case Artifact_Configuration:
                deleteFormController.removeArtifactConfiguraionLink(arrowId, startId, endId, isModelDelete);
                break;
            case Person_Commit:
                deleteFormController.removePersonCommitLink(arrowId, startId, endId, isModelDelete);
                break;
            case Person_Commtted_Configuration:
                deleteFormController.removePersonCommittedConfigurationLink(arrowId, startId, endId, isModelDelete);
                break;
            default:
        }

        deleteArrow(arrowId);
    }


    /**
     * Odstraneni prvku ze senamu spojnic pripojenych ke konkretnimu prvku
     * @param arrowId identifikator spojnice
     * @param mStartLinkIds seznam s identifikatory spojnic
     */
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


    /**
     * Metoda pro odstraneni spojnice z map
     * @param itemIdentificator identifikator prvku platna
     * @param isModelDelete info o tom zada je spojnice smazana v modelu
     */
    public void deleteLinks(int itemIdentificator, boolean isModelDelete) {

        List<Integer> mStartLinkIds = startLinkIdMap.get(itemIdentificator);
        List<Integer> mEndLinkIds = endLinkIdMap.get(itemIdentificator);

        for (int i = mStartLinkIds.size() - 1; i >= 0; i--) {
            int arrowId = mStartLinkIds.get(i);
            ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);

            int endId = link.getLinkController().getEndItemId();
            List<Integer> mEndLinkIdsLoc = endLinkIdMap.get(endId);
            removeLinkFromIds(arrowId, mEndLinkIdsLoc);
            mStartLinkIds.remove(i);

            link.deleteArrow(isModelDelete);
        }

        for (int i = mEndLinkIds.size() - 1; i >= 0; i--) {
            int arrowId = mEndLinkIds.get(i);
            ElementsLink link = (ElementsLink) segmentLists.getArrow(arrowId);
            int startId = link.getLinkController().getStartItemId();
            List<Integer> mStartLinkIdsLoc = startLinkIdMap.get(startId);
            removeLinkFromIds(arrowId, mStartLinkIdsLoc);
            mEndLinkIds.remove(i);
            link.deleteArrow(isModelDelete);
        }
    }

    /**
     * Metoda pro prekresleni spojnice v zavislosti na presunu prvku platna
     * @param segmentType typ prvku
     * @param itemIdentificator identifikator prvku
     * @param translateX x souradnice
     * @param translateY y souradnice
     * @param width sirka prvku
     * @param height vyska prvku
     */
    public void repaintArrow(SegmentType segmentType, int itemIdentificator,
                             double translateX, double translateY, double width, double height) {

        List<Integer> mStartLinkIds = startLinkIdMap.get(itemIdentificator);
        List<Integer> mEndLinkIds = endLinkIdMap.get(itemIdentificator);

        double newWidth = translateX + width;
        double newHeight = translateY + (height / 2);

        for (int i = 0; i < mStartLinkIds.size(); i++) {

            int index = mStartLinkIds.get(i);
            if ((Integer) index != null){
                NodeLink link = segmentLists.getArrow(index);
                Point2D vector = coutDirectVector(link);

                if (vector.getX() < 0){
                    newWidth = translateX;
                }else {
                    newWidth = translateX + width;
                }

                link.repaintArrowStartPoint(index, newWidth, newHeight);
            }

        }

        for (int i = 0; i < mEndLinkIds.size(); i++) {

            int index = mEndLinkIds.get(i);
            if ((Integer)index != null){
                NodeLink link = segmentLists.getArrow(index);
                Point2D vector = coutDirectVector(link);

                if (vector.getX() > 0){
                    newWidth = translateX;
                }else {
                    newWidth = translateX + width;
                }
                link.repaintArrowEndPoint(index, newWidth, newHeight);
            }

        }
    }

    /**
     * Metoda pro urceni smeru a polohy spojnice
     * @param link instace spojnice
     * @return nova poloha spojnice
     */
    private Point2D coutDirectVector(NodeLink link) {
        Point2D start = link.getStartPoint();
        Point2D end = link.getEndPoint();

        double x = end.getX() - start.getX();
        double y = end.getY() - start.getY();

        Point2D directVector = new Point2D(x, y);

        return directVector;
    }

    /**
     * Metoda pro nastaveni spojnice do mapy
     * @param formIndex identifikator formulare
     */
    public void createLinkInstanceInMap(int formIndex) {
        startLinkIdMap.put(formIndex, new ArrayList<>());
        endLinkIdMap.put(formIndex, new ArrayList<>());
    }
}
