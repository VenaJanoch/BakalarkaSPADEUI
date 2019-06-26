package interfaces;

import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici rozhrani pro editaci dat v datovem modelu
 *
 * @author Václav Janoch
 */
public interface IEditDataModel {

    /**
     * @param alias                 alias prvku
     * @param nameForManipulator    zpracovany seznam parametu z pole name
     * @param nameIndicators        seznam indexu ukazatelu nerovnosti
     * @param description           zpracovany seznam parametu z pole name
     * @param descriptionIndicators seznam indexu ukazatelu nerovnosti
     * @param roleIndex             zpracovany seznam parametu z pole name
     * @param roleIndicator         seznam indexu ukazatelu nerovnosti
     * @param exist                 informace o existenci prvku v patternu
     * @param id                    identifikator instance
     */
    void editDataInCPR(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> roleIndex, ArrayList<Integer> roleIndicator, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param indicators         seznam indexu ukazatelu nerovnosti
     * @param isMainBranch       informace o main vetvi
     * @param exist              informace o existenci prvku v patternu
     * @param id                 identifikator instance
     */
    void editDataInBranch(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> indicators, boolean isMainBranch, boolean exist, int id);

    /**
     * @param alias                 alias prvku
     * @param nameForManipulator    zpracovany seznam parametu z pole name
     * @param nameIndicators        seznam indexu ukazatelu nerovnosti
     * @param descForManipulator    zpracovany seznam parametu z pole description
     * @param descriptionIndicators seznam indexu ukazatelu nerovnosti
     * @param createdDate           zpracovany seznam parametu z pole created
     * @param dateIndicator         seznam indexu ukazatelu nerovnosti
     * @param isCreate              informace o existenci prvku v patternu
     * @param authorIndex           zpracovany seznam parametu z pole name
     * @param typeIndex             seznam indexu s typem artefaktu
     * @param authorIndicator       seznam indexu ukazatelu nerovnosti
     * @param typeIndicator         seznam indexu ukazatelu nerovnosti
     * @param instanceCount         pocet instanci prvku
     * @param countIndicator        seznam indexu ukazatelu nerovnosti
     * @param id                    identifikator instance
     */
    void editDataInArtifact(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                            ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                            ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                            ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                            ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator, int id);

    /**
     * @param alias                  alias prvku
     * @param nameForManipulator     zpracovany seznam parametu z pole name
     * @param nameIndicators         seznam indexu ukazatelu nerovnosti
     * @param descForManipulator     zpracovany seznam parametu z pole description
     * @param descIndicator          seznam indexu ukazatelu nerovnosti
     * @param artifactForManipulator zpracovany seznam parametu z pole artifact
     * @param selected               informace o existenci prvku v patternu
     * @param id                     identifikator instance
     */
    void editDataInChange(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                          ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected, int id);

    /**
     * @param alias              alias prvku
     * @param actName            zpracovany seznam parametu z pole name
     * @param endDateL           zpracovany seznam parametu z pole end date
     * @param desc               zpracovany seznam parametu z pole description
     * @param confIndex          zpracovany seznam parametu z pole configuration
     * @param milestoneIndex     zpracovany seznam parametu z pole milestone
     * @param workUnitIndexList  zpracovany seznam parametu z pole worku unit
     * @param workUnitIndicators seznam indexu ukazatelu nerovnosti
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param endDateIndicator   seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param confIndicator      seznam indexu ukazatelu nerovnosti
     * @param milestoneIndicator seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v patternu
     * @param id
     */

    void editDataInPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                         ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                         ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                         ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param actName            zpracovany seznam parametu z pole name
     * @param endDateL           zpracovany seznam parametu z pole end date
     * @param startDateL         zpracovany seznam parametu z pole end date
     * @param desc               zpracovany seznam parametu z pole description
     * @param confIndex          zpracovany seznam parametu z pole configuration
     * @param workUnitIndexList  zpracovany seznam parametu z pole worku unit
     * @param workUnitIndicators seznam indexu ukazatelu nerovnosti
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param endDateIndicator   seznam indexu ukazatelu nerovnosti
     * @param startDateIndicator seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param confIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v patternu
     * @param id                 identifikator instance
     */
    void editDataInIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                             ArrayList<Integer> confIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                             ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                             ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, boolean exist, int id);

    /**
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param endDate                   zpracovany seznam parametu z pole end date
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param workUnitIndicators        seznam indexu ukazatelu nerovnosti
     * @param nameIndicators            seznam indexu ukazatelu nerovnosti
     * @param endDateIndicators         seznam indexu ukazatelu nerovnosti
     * @param descIndicators            seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v patternu
     * @param id                        identifikator instance
     */
    void editDataInActivity(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> setOfItemOnCanvas,
                            ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicators, ArrayList<Integer> workUnitIndicators, ArrayList<LocalDate> endDate,
                            ArrayList<Integer> endDateIndicators, boolean exist, int id);

    /**
     * @param alias
     * @param progress                   zpracovany seznam parametu z pole progress
     * @param progressIndicator          seznam indexu ukazatelu nerovnosti
     * @param nameForManipulator         zpracovany seznam parametu z pole name
     * @param description                zpracovany seznam parametu z pole description
     * @param categoryForManipulator     zpracovany seznam parametu z pole category
     * @param assigneIndex               zpracovany seznam parametu z pole assigne
     * @param authorIndex                zpracovany seznam parametu z pole author
     * @param priorityIndex              zpracovany seznam parametu z pole priority
     * @param severityIndex              zpracovany seznam parametu z pole severity
     * @param typeIndex                  zpracovany seznam parametu z pole type
     * @param resolutionIndex            zpracovany seznam parametu z pole resolution
     * @param statusIndex                zpracovany seznam parametu z pole status
     * @param estimateForDataManipulator zpracovany seznam parametu z pole estimate
     * @param nameIndicator              seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator       seznam indexu ukazatelu nerovnosti
     * @param categoryIndicator          seznam indexu ukazatelu nerovnosti
     * @param assigneIndicator           seznam indexu ukazatelu nerovnosti
     * @param authorIndicator            seznam indexu ukazatelu nerovnosti
     * @param priorityIndicator          seznam indexu ukazatelu nerovnosti
     * @param severityIndicator          seznam indexu ukazatelu nerovnosti
     * @param typeIndicator              seznam indexu ukazatelu nerovnosti
     * @param resolutionIndicator        seznam indexu ukazatelu nerovnosti
     * @param statusIndicator            seznam indexu ukazatelu nerovnosti
     * @param estimateIndicator          seznam indexu ukazatelu nerovnosti
     * @param createDate                 zpracovany seznam parametu z pole created day
     * @param createIndicator            seznam indexu ukazatelu nerovnosti
     * @param isExist                    informace o existenci prvku v patternu
     * @param relations                  zpracovany seznam parametu z pole relation
     * @param workUnits                  zpracovany seznam parametu z pole workUnit
     * @param id                         identifikator prvku
     */
    void editDataInWorkUnit(String alias, ArrayList<Integer> progress, ArrayList<Integer> progressIndicator, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                            ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                            ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                            ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                            ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                            ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                            ArrayList<Integer> estimateIndicator, ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean isExist, ArrayList<Integer> relations, ArrayList<ArrayList<Integer>> workUnits, int id);

    /**
     * @param alias                alias prvku
     * @param actName              zpracovany seznam parametu z pole name
     * @param description          zpracovany seznam parametu z pole description
     * @param createDate           zpracovany seznam parametu z pole created
     * @param isRelease            zpracovany seznam parametu z pole release
     * @param cprs                 zpracovany seznam parametu z pole configuration person relation
     * @param changeIndexs         zpracovany seznam parametu z pole change
     * @param branchIndexs         zpracovany seznam parametu z pole branch
     * @param cprIndicators        seznam indexu ukazatelu nerovnosti
     * @param nameIndicator        seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator seznam indexu ukazatelu nerovnosti
     * @param tag                  zpracovany seznam parametu z pole VCSTag
     * @param tagIndicator         seznam indexu ukazatelu nerovnosti
     * @param createdIndicator     seznam indexu ukazatelu nerovnosti
     * @param changeIndicator      seznam indexu ukazatelu nerovnosti
     * @param branchIndicators     seznam indexu ukazatelu nerovnosti
     * @param instanceCount        pocet instanci prvku
     * @param countIndicator       seznam indexu ukazatelu nerovnost
     * @param id                   identificator instance
     * @param exist                 informace o existenci prvku v patternu
     */
    void editDataInConfiguration(String alias, ArrayList<String> actName, ArrayList<String> description, ArrayList<LocalDate> createDate,
                                 boolean isRelease, ArrayList<ArrayList<Integer>> cprs,
                                 ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<ArrayList<Integer>> branchIndexs, ArrayList<Integer> branchIndicators,
                                 ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator, ArrayList<Integer> createdIndicator,
                                 ArrayList<Integer> changeIndicator, int instanceCount, int countIndicator, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v patternu
     * @param id                 identifikator instance
     */
    void editDataInCriterion(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                             ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInPriority(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                            ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInSeverity(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                            ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInRelation(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                            ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInResolution(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param type               zpracovany seznam parametu z pole Role Type
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param typeIndicator      seznam indexu ukazatelu nerovnosti
     * @param instanceCount      informace o existenci prvku v modelu
     * @param countIndicator     seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInPerson(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> type, ArrayList<Integer> nameIndicator,
                          ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param criterionIndicator seznam indexu ukazatelu nerovnosti
     * @param criterionIndex     zpracovany seznam parametu z pole n
     * @param id                 identifikator instance
     */
    void editDataInMilestone(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                             ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                             ArrayList<ArrayList<Integer>> criterionIndex, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInRoleType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descForManipulator, ArrayList<Integer> descIndicator,
                            ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInStatus(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                          ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    void editDataInType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                        ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id);

    /**
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator             seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v modelu
     * @param id
     */
    void editDataInVCSTag(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                          ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, boolean exist, int id);

    /**
     * @param alias                 alias prvku
     * @param nameForManipulator    zpracovany seznam parametu z pole name
     * @param nameIndicator         seznam indexu ukazatelu nerovnosti
     * @param descriptions          zpracovany seznam parametu z pole description
     * @param descriptionsIndicator seznam indexu ukazatelu nerovnosti
     * @param createDate            zpracovany seznam parametu z pole create date
     * @param createIndicator       seznam indexu ukazatelu nerovnosti
     * @param release               informace o releasu
     * @param instanceCount         pocet instaci v modelu
     * @param countIndicator        seznam indexu ukazatelu nerovnosti
     * @param exist                 informace o existenci prvku v modelu
     * @param id
     */
    void editDataInCommit(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator,
                          ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean release, int instanceCount, int countIndicator, boolean exist, int id);

    /**
     * @param nameForManipulator   zpracovany seznam parametu z pole name
     * @param nameIndicator        seznam indexu ukazatelu nerovnosti
     * @param description          zpracovany seznam parametu z pole description
     * @param descriptionIndicator seznam indexu ukazatelu nerovnosti
     * @param createDate           zpracovany seznam parametu z pole created
     * @param dateIndicator        seznam indexu ukazatelu nerovnosti
     * @param exist                informace o existenci prvku v patternu
     * @param instanceCount        pocet instanci prvku
     * @param countIndicator       seznam indexu ukazatelu nerovnosti
     * @param id
     */
    void editDataInCommitedConfiguration(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> descriptionIndicator,
                                         ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, ArrayList<LocalDate> dateFromDatePicker, ArrayList<Integer> dateIndicator, int instanceCount, int countIndicator, boolean exist, int id);

    /**
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param startDate1                zpracovany seznam parametu z pole start date
     * @param endDate1                  zpracovany seznam parametu z pole end date
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param workUnitsForManipulator   zpracovany seznam parametu z pole workUnits
     * @param workUnitIndicators        seznam indexu ukazatelu nerovnosti
     * @param nameIndicators            seznam indexu ukazatelu nerovnosti
     * @param date1Indicators           seznam indexu ukazatelu nerovnosti
     * @param date2Indicators           seznam indexu ukazatelu nerovnosti
     * @param descIndicators            seznam indexu ukazatelu nerovnosti
     */
    void editDataInProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                           ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators);

    /**
     * Metoda pro nastaveni souradnic do Commit
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    void editCoordinatesInCommit(int x, int y, int id);

    /**
     * Metoda pro nastaveni souradnic do CommittedConfiguration
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    void editCoordinatesInCommitedConfiguration(int x, int y, int id);

    /**
     * Metoda pro nastaveni souradnic do Configuration
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    void editCoordinatesInConfiguration(int x, int y, int id);

    /**
     * Metoda pro nastaveni souradnic do Artifact
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    void editCoordinatesInArtifact(int x, int y, int id);

    /**
     * Metoda pro nastaveni souradnic do Person
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    void editCoordinatesInPerson(int x, int y, int id);

    /**
     * @param formType         typ zavisejiciho prvku
     * @param elementType      typ
     * @param elementIndexList seznam indexu
     */
    void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIndexList);

    /**
     * @param formType         typ zavisejiciho prvku
     * @param elementType      typ
     * @param elementIndexList index
     */
    void updateItemList(SegmentType formType, SegmentType elementType, int elementIndexList);

}
