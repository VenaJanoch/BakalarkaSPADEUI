package services;

import model.IdentificatorCreater;
import tables.BasicTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Třída predstavujici pomocny kotroler pro funkcnost mazani
 *
 * @author Václav Janoch
 */
public class DeleteControl {

    /**
     * Globální proměnné třídy
     **/

    private SegmentLists lists;
    private MapperTableToObject mapperTableToObject;
    private IdentificatorCreater idCreater;


    /**
     * Konstruktor třídy
     * Zinicializuje globální proměnné třídy
     *
     * @param lists                instace tridy SegmentList
     * @param mapperTableToObject  instacne tridy MapperTableObject
     * @param identificatorCreater instnce tridy IdentificatorCreator
     */
    public DeleteControl(SegmentLists lists, MapperTableToObject mapperTableToObject, IdentificatorCreater identificatorCreater) {

        this.lists = lists;
        this.mapperTableToObject = mapperTableToObject;
        this.idCreater = identificatorCreater;
    }

    /**
     * Metoda pro nalezeni identifikatoru vybranych prvku z tabulky
     *
     * @param tables seznam prvku z tabulky
     * @return seznam identifikatoru
     */
    public ArrayList<Integer> findIndicesForDelete(ArrayList<BasicTable> tables) {
        ArrayList idForDelete = new ArrayList();

        for (BasicTable table : tables) {
            idForDelete.add(table.getId());
        }
        return idForDelete;
    }


    /**
     * Metoda pro ziskani identifikatoru v zavislosti na poradi prvku v tablce
     *
     * @param segmentType typ elementu pro hledani
     * @param indicesList seznam indexu zvolenych z tabulky
     * @return seznam identifikatoru
     */
    public ArrayList<Integer> findIndicesForDelete(SegmentType segmentType, ArrayList<Integer> indicesList) {

        switch (segmentType) {
            case Branch:
                break;
            case Priority:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUToPriorityMapper());
            case Severity:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUToSeverityMapper());
            case Milestone:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getMilestoneToCriterionMapper());
            case Criterion:
                break;
            case Person:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getPersonToRoleTypeMapper());
            case Role_Type:
                break;
            case Config_Person_Relation:
                break;
            case Relation:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUTORelationMapper());
            case Resolution:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUToResolutionMapper());
            case Status:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUStatusMapper());
            case Type:
                return findTableToObjectIndicesForDelete(indicesList, mapperTableToObject.getWUTotypeMapper());
            case Configuration:
                break;
            default:

        }
        return null;
    }

    /**
     * Metoda projde seznam z tabulky najde prislusne identificatory
     *
     * @param id  seznam identifikatoru
     * @param map mapa
     * @return identifikatory
     */
    private ArrayList<Integer> findTableToObjectIndicesForDelete(ArrayList<Integer> id, Map<Integer, ArrayList<TableToObjectInstanc>> map) {
        ArrayList<Integer> listToDelete = new ArrayList<>();
        for (Integer i : id) {
            List<TableToObjectInstanc> tabToObjList = map.get(i);
            if (tabToObjList != null) {
                for (TableToObjectInstanc j : tabToObjList) {
                    if (!listToDelete.contains(j.getId())) {
                        listToDelete.add(j.getId());
                    }
                }
            }
        }
        return listToDelete;
    }
}
