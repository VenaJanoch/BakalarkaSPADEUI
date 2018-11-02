package Controllers;

import graphics.ChangeArtifactLink;
import graphics.WorkUnitLink;
import javafx.collections.ObservableList;
import services.SegmentLists;

public class ListController {

    private SegmentLists segmentLists;

    public ListController(SegmentLists segmentLists){

        this.segmentLists = segmentLists;

    }

    public ObservableList<String> getRelationTypeObservable() {

        return segmentLists.getRelationTypeObservable();
    }

    public void removeWorkUnitRelation(int startItemId, int endItemId) {

        segmentLists.removeWorkUnitRelation(startItemId, endItemId);

    }

    public void addLinkToList(WorkUnitLink wuLink){
        segmentLists.getArrows().add(wuLink);

    }

    public void addLinkToList(ChangeArtifactLink caLink){
        segmentLists.getArrows().add(caLink);

    }
}
