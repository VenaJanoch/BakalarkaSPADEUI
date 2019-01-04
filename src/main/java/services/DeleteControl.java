package services;

import java.util.ArrayList;
import java.util.List;

import SPADEPAC.*;
import javafx.collections.ObservableList;
import tables.*;

public class DeleteControl {

    /**
     * Globální proměnné třídy
     **/

    private SegmentLists lists;
    private MapperTableToObject mapperTableToObject;


    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     */
    public DeleteControl(SegmentLists lists, MapperTableToObject mapperTableToObject) {

        this.lists = lists;
        this.mapperTableToObject = mapperTableToObject;
    }


    /**
     * Vymazání informací o elementu Tag a smazání ze seznamů
     *
     * @param tables
     */
    public ArrayList deleteTag(ObservableList<TagTable> tables) {
        ArrayList indexForDelete = new ArrayList();

        for (TagTable table : tables) {

            int index = lists.getPriorityTypeObservable().indexOf(table.getTag());
            indexForDelete.add(index);
        }
        return indexForDelete;
    }

    /**
     * Vymazání informací o výčtovém typu Severity a smazání ze seznamů
     *
     * @param tables
     */
    /**
     * Vymazání informací o výčtovém typu Relation a smazání ze seznamů
     *
     * @param tables
     */
    public ArrayList deleteRelation(ObservableList<ClassTable> tables) {
        ArrayList indexForDelete = new ArrayList();

        for (ClassTable table : tables) {

            int index = lists.getRelationTypeObservable().indexOf(table.getName());
            indexForDelete.add(index);
        }
        return indexForDelete;

    }

    /**
     * Vymazání informací o výčtovém typu Status a smazání ze seznamů
     *
     * @param tables
     */

    /**
     * Vymazání informací o elementu Criterion a smazání ze seznamů
     *
     * @param tables
     */
    public ArrayList findIndexesForDelete(ArrayList<BasicTable> tables) {
        ArrayList indexForDelete = new ArrayList();

        for (BasicTable table : tables) {
            indexForDelete.add(table.getId());
        }
        return indexForDelete;
    }

    public ArrayList findMilestonesIndexesForDelete(ArrayList<Integer> criterionIndexes) {
        ArrayList<Integer> milestonesToDelete = new ArrayList<>();
        for (Integer i : criterionIndexes) {
            List<TableToObjectInstanc> milestones = mapperTableToObject.getMilestoneToCriterionMapper().get(i);
            if (milestones != null) {

                for (TableToObjectInstanc j : milestones) {
                    if (!milestonesToDelete.contains(j.getId())) {
                        milestonesToDelete.add(j.getId());
                    }
                }
            }
        }
        return milestonesToDelete;
    }

    public ArrayList findRoleIndexesForDelete(ArrayList<Integer> roleTypeIndexes) {
        ArrayList<Integer> rolesToDelete = new ArrayList<>();
        for (Integer i : roleTypeIndexes) {
            List<TableToObjectInstanc> roles = mapperTableToObject.getRoleTypeToRoleMapper().get(i);
            if (roles != null) {
                for (TableToObjectInstanc j : roles) {
                    if (!rolesToDelete.contains(j.getId())) {
                        rolesToDelete.add(j.getId());
                    }
                }
            }
        }
        return rolesToDelete;
    }
}
