package model;

import SPADEPAC.*;
import interfaces.IEditDataModel;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida slouzici pro vyhledani spravne instance datoveho modelu a nasledneho zavolani metody datoveho modelu.
 * Druhou funkcnosti teto tridy je uprava zavislosti jednotlivych prvku
 *
 * @author Václav Janoch
 */
public class EditDataModel implements IEditDataModel {

    /**
     * Promenna datoveho modelu
     */
    private DataModel dataModel;


    /**
     * Konstruktor tridy, zinicializuje promenou datoveho modelu
     *
     * @param dataModel instance datoveho modelu
     */
    public EditDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    /**
     * Metoda pro vyhledani instance Configuration Person Relation podle id a predani dat do datoveho modelu
     *
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
    public void editDataInCPR(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> roleIndex,
                              ArrayList<Integer> roleIndicator, boolean exist, int id) {

        ConfigPersonRelation cpr = dataModel.getConfigPersonRelation(id);
        dataModel.addDataToCPR(cpr, alias, nameForManipulator, nameIndicators, description, descriptionIndicators, roleIndex, roleIndicator, exist);

    }

    /**
     * Metoda pro vyhledani instance Branch podle id a predani dat do datoveho modelu
     *
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicators
     * @param isMainBranch       informace o main vetvi
     * @param exist              informace o existenci prvku v patternu
     * @param id                 identifikator instance
     */
    public void editDataInBranch(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, boolean isMainBranch, boolean exist, int id) {
        Branch branch = dataModel.getBranch(id);
        dataModel.addDataToBranch(branch, alias, nameForManipulator, nameIndicators, isMainBranch, exist);
    }

    /**
     * Metoda pro vyhledani instance Artifact podle id a predani dat do datoveho modelu
     *
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
    public void editDataInArtifact(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                                   ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                                   ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                                   ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                                   ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator, int id) {
        Artifact artifact = dataModel.getArtifact(id);
        dataModel.addDataToArtifact(artifact, alias, nameForManipulator, nameIndicators, descForManipulator, descriptionIndicators,
                createdDate, dateIndicator, isCreate, authorIndex, typeIndex, authorIndicator, typeIndicator, instanceCount, countIndicator);
    }

    /**
     * Metoda pro vyhledani instance Change podle id a predani dat do datoveho modelu
     *
     * @param alias                  alias prvku
     * @param nameForManipulator     zpracovany seznam parametu z pole name
     * @param descForManipulator     zpracovany seznam parametu z pole description
     * @param artifactForManipulator zpracovany seznam parametu z pole artifact
     * @param nameIndicators         seznam indexu ukazatelu nerovnosti
     * @param descIndicator          seznam indexu ukazatelu nerovnosti
     * @param selected               informace o existenci prvku v patternu
     * @param id                     identifikator instance
     */
    public void editDataInChange(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                                 ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator,
                                 boolean selected, int id) {
        Change change = dataModel.getChange(id);
        dataModel.addDataToChange(change, alias, nameForManipulator, descForManipulator, artifactForManipulator, nameIndicators, descIndicator, selected);
    }

    /**
     * ]
     * Metoda pro vyhledani instance Phase podle id a predani dat do datoveho modelu
     *
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
    public void editDataInPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                                ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                                ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, boolean exist, int id) {
        Phase phase = dataModel.getPhase(id);
        dataModel.addDataToPhase(phase, alias, actName, endDateL, desc, confIndex, milestoneIndex, workUnitIndexList,
                workUnitIndicators, nameIndicator, endDateIndicator, descIndicator, confIndicator, milestoneIndicator, exist);
    }

    /**
     * Metoda pro vyhledani instance Iteration podle id a predani dat do datoveho modelu
     *
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
    public void editDataInIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                    ArrayList<Integer> confIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                                    ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                    ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, boolean exist, int id) {
        Iteration iteration = dataModel.getIteration(id);
        dataModel.addDataToIteration(iteration, alias, actName, endDateL, startDateL, desc,
                confIndex, workUnitIndexList, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator, exist);
    }

    /**
     * Metoda pro vyhledani instance Activity podle id a predani dat do datoveho modelu
     *
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param setOfItemOnCanvas
     * @param nameIndicators            seznam indexu ukazatelu nerovnosti
     * @param descIndicators            seznam indexu ukazatelu nerovnosti
     * @param workUnitIndicators        seznam indexu ukazatelu nerovnosti
     * @param endDate                   zpracovany seznam parametu z pole end date
     * @param endDateIndicators         seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v patternu
     * @param id                        identifikator instance
     */
    public void editDataInActivity(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                   ArrayList<ArrayList<Integer>> setOfItemOnCanvas, ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicators,
                                   ArrayList<Integer> workUnitIndicators, ArrayList<LocalDate> endDate, ArrayList<Integer> endDateIndicators, boolean exist, int id) {
        Activity activity = dataModel.getActivity(id);
        dataModel.addDataToActivity(activity, alias, nameForManipulator, descriptionForManipulator, setOfItemOnCanvas, nameIndicators, descIndicators,
                workUnitIndicators, endDate, endDateIndicators, exist);

    }

    /**
     * Metoda pro vyhledani instance Work Unit podle id a predani dat do datoveho modelu
     *
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
    public void editDataInWorkUnit(String alias, ArrayList<Integer> progress, ArrayList<Integer> progressIndicator, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                                   ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                   ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                   ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                   ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                   ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                   ArrayList<Integer> estimateIndicator, ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean isExist, ArrayList<Integer> relations, ArrayList<ArrayList<Integer>> workUnits, int id) {
        WorkUnit workUnit = dataModel.getWorkUnit(id);
        dataModel.addDataToWorkUnit(workUnit, alias, progress, progressIndicator, nameForManipulator, description, categoryForManipulator,
                assigneIndex, authorIndex, priorityIndex, severityIndex,
                typeIndex, resolutionIndex, statusIndex,
                estimateForDataManipulator, nameIndicator, descriptionIndicator, categoryIndicator,
                assigneIndicator, authorIndicator, priorityIndicator, severityIndicator,
                typeIndicator, resolutionIndicator, statusIndicator,
                estimateIndicator, createDate, createIndicator, isExist, relations, workUnits);
    }

    /**
     * Metoda pro vyhledani instance Configuration podle id a predani dat do datoveho modelu
     *
     * @param alias                alias prvku
     * @param actName              zpracovany seznam parametu z pole name
     * @param description          zpracovany seznam parametu z pole description
     * @param createDate           zpracovany seznam parametu z pole created
     * @param isRelease            zpracovany seznam parametu z pole release
     * @param cprs                 zpracovany seznam parametu z pole configuration person relation
     * @param changeIndexs         zpracovany seznam parametu z pole change
     * @param branchIndexs         zpracovany seznam parametu z pole branch
     * @param branchIndicators     seznam indexu ukazatelu nerovnosti
     * @param cprIndicators        seznam indexu ukazatelu nerovnosti
     * @param nameIndicator        seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator seznam indexu ukazatelu nerovnosti
     * @param tag                  zpracovany seznam parametu z pole VCSTag
     * @param tagIndicator         seznam indexu ukazatelu nerovnosti
     * @param createdIndicator     seznam indexu ukazatelu nerovnosti
     * @param changeIndicator      seznam indexu ukazatelu nerovnosti
     * @param instanceCount        pocet instanci prvku
     * @param countIndicator       seznam indexu ukazatelu nerovnost
     * @param exist
     * @param id                   identificator instance
     */
    public void editDataInConfiguration(String alias, ArrayList<String> actName, ArrayList<String> description, ArrayList<LocalDate> createDate,
                                        boolean isRelease, ArrayList<ArrayList<Integer>> cprs,
                                        ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<ArrayList<Integer>> branchIndexs, ArrayList<Integer> branchIndicators,
                                        ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator, ArrayList<Integer> createdIndicator,
                                        ArrayList<Integer> changeIndicator, int instanceCount, int countIndicator, boolean exist, int id) {
        Configuration configuration = dataModel.getConfiguration(id);
        dataModel.addDataToConfiguration(configuration, alias, actName, description, createDate, isRelease, cprs, changeIndexs, branchIndexs,
                cprIndicators, nameIndicator, descriptionIndicator, tag, tagIndicator, createdIndicator, changeIndicator, branchIndicators, instanceCount, countIndicator, exist);

    }

    /**
     * Metoda pro vyhledani instance Criterion podle id a predani dat do datoveho modelu
     *
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v patternu
     * @param id                 identifikator instance
     */
    public void editDataInCriterion(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                    ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, boolean exist, int id) {
        Criterion criterion = dataModel.getCriterion(id);
        dataModel.addDataToCriterion(criterion, alias, nameForManipulator, descForManipulator, nameIndicator, descIndicator, exist);
    }

    /**
     * Metoda pro vyhledani instance Priority podle id a predani dat do datoveho modelu
     *
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
    public void editDataInPriority(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        Priority priority = dataModel.getPriority(id);
        dataModel.addDataToPriority(priority, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);

    }

    /**
     * Metoda pro vyhledani instance Severity podle id a predani dat do datoveho modelu
     *
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
    public void editDataInSeverity(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        Severity severity = dataModel.getSeverity(id);
        dataModel.addDataToSeverity(severity, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance Relation podle id a predani dat do datoveho modelu
     *
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
    public void editDataInRelation(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {

        Relation relation = dataModel.getRelation(id);
        dataModel.addDataToRelation(relation, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance Resolution podle id a predani dat do datoveho modelu
     *
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
    public void editDataInResolution(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                     ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        Resolution relation = dataModel.getResolution(id);
        dataModel.addDataToResolution(relation, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance Person podle id a predani dat do datoveho modelu
     *
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
    public void editDataInPerson(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> type,
                                 ArrayList<Integer> nameIndicator, ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator, boolean exist, int id) {

        Person role = dataModel.getPerson(id);
        dataModel.addDatToPerson(role, alias, nameForManipulator, type, nameIndicator, typeIndicator, instanceCount, countIndicator, exist);
    }

    /**
     * Metoda pro vyhledani instance Milestone podle id a predani dat do datoveho modelu
     *
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param criterionIndicator seznam indexu ukazatelu nerovnosti
     * @param criterionIndex     zpracovany seznam parametu z pole n
     * @param exist
     * @param id                 identifikator instance
     */
    public void editDataInMilestone(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                    ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                                    ArrayList<ArrayList<Integer>> criterionIndex, boolean exist, int id) {
        Milestone milestone = dataModel.getMilestone(id);
        dataModel.addDataToMilestone(milestone, alias, nameForManipulator, descForManipulator,
                nameIndicator, descIndicator, criterionIndicator, criterionIndex, exist);
    }

    /**
     * Metoda pro vyhledani instance Role Type podle id a predani dat do datoveho modelu
     *
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descForManipulator
     * @param descIndicator
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     * @param id                 identifikator instance
     */
    public void editDataInRoleType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descForManipulator, ArrayList<Integer> descIndicator, ArrayList<Integer> classST,
                                   ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        RoleType roleType = dataModel.getRoleType(id);
        dataModel.addDataToRoleType(roleType, alias, nameForManipulator, nameIndicator, descForManipulator, descIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance Status podle id a predani dat do datoveho modelu
     *
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
    public void editDataInStatus(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                                 ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        Status status = dataModel.getStatus(id);
        dataModel.addDataToStatus(status, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance Type podle id a predani dat do datoveho modelu
     *
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
    public void editDataInType(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<Integer> classST,
                               ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist, int id) {
        Type type = dataModel.getType(id);
        dataModel.addDataToType(type, alias, nameForManipulator, nameIndicator, classST, superST, classString, superSting, exist);
    }

    /**
     * Metoda pro vyhledani instance VCSTag podle id a predani dat do datoveho modelu
     *
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator             seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v modelu
     * @param id
     */
    public void editDataInVCSTag(String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                 ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, boolean exist, int id) {
        VCSTag tag = dataModel.getVCSTag(id);
        dataModel.addDataToVCSTag(tag, alias, nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator, exist);
    }

    /**
     * Metoda pro vyhledani instance Commit podle id a predani dat do datoveho modelu
     *
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
    public void editDataInCommit(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator,
                                 ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator,
                                 boolean release, int instanceCount, int countIndicator, boolean exist, int id) {
        Commit commit = dataModel.getCommit(id);
        dataModel.addDataToCommit(commit, alias, nameForManipulator, nameIndicator, descriptions, descriptionsIndicator, createDate, createIndicator, release, instanceCount, countIndicator, exist);
    }

    /**
     * Metoda pro nastaveni souradnic do Commit
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    public void editCoordinatesInCommit(int x, int y, int id) {
        Commit commit = dataModel.getCommit(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToCommit(coordinates, commit);
    }

    /**
     * Metoda pro nastaveni souradnic do Committed Configuration
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    public void editCoordinatesInCommitedConfiguration(int x, int y, int id) {
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToCommitedConfiguration(coordinates, commitedConfiguration);
    }

    /**
     * Metoda pro nastaveni souradnic do Configuration
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    public void editCoordinatesInConfiguration(int x, int y, int id) {
        Configuration configuration = dataModel.getConfiguration(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToConfiguration(coordinates, configuration);
    }

    /**
     * Metoda pro nastaveni souradnic do Artifact
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    public void editCoordinatesInArtifact(int x, int y, int id) {
        Artifact artifact = dataModel.getArtifact(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToArtifact(coordinates, artifact);
    }

    /**
     * Metoda pro nastaveni souradnic do Person
     *
     * @param x  x souradnice
     * @param y  y souradnice
     * @param id identifikator instance
     */
    public void editCoordinatesInPerson(int x, int y, int id) {
        Person role = dataModel.getPerson(id);
        Coordinates coordinates = dataModel.createCoords(x, y);
        dataModel.setCoordinatesToPerson(coordinates, role);
    }

    /**
     * Metoda pro vyhledani instance CommittedConfiguration podle id a predani dat do datoveho modelu
     *
     * @param alias
     * @param nameForManipulator   zpracovany seznam parametu z pole name
     * @param nameIndicator        seznam indexu ukazatelu nerovnosti
     * @param description          zpracovany seznam parametu z pole description
     * @param descriptionIndicator seznam indexu ukazatelu nerovnosti
     * @param createDate           zpracovany seznam parametu z pole created
     * @param createIndicator
     * @param dateFromDatePicker
     * @param dateIndicator        seznam indexu ukazatelu nerovnosti
     * @param instanceCount        pocet instanci prvku
     * @param countIndicator       seznam indexu ukazatelu nerovnosti
     * @param exist                informace o existenci prvku v patternu
     * @param id
     */
    public void editDataInCommitedConfiguration(String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> descriptionIndicator,
                                                ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, ArrayList<LocalDate> dateFromDatePicker, ArrayList<Integer> dateIndicator, int instanceCount, int countIndicator, boolean exist, int id) {
        CommitedConfiguration commitedConfiguration = dataModel.getCommitedConfiguration(id);
        dataModel.addDataToCommitedConfiguration(commitedConfiguration, alias, nameForManipulator, nameIndicator, description, descriptionIndicator, createDate, createIndicator,
                dateFromDatePicker, dateIndicator, instanceCount, countIndicator, exist);
    }

    /**
     * Metoda pro predani dat do datoveho modelu
     *
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
    public void editDataInProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                                  ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                                  ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators) {
        dataModel.addDataToProject(nameForManipulator, startDate1, endDate1, descriptionForManipulator, workUnitsForManipulator,
                workUnitIndicators, nameIndicators, date1Indicators, date2Indicators, descIndicators);
    }

    /**
     * Metoda pro upravu zavislosti mezi Change a artifact
     *
     * @param segment       instace Change
     * @param elementIdList indexi pro zmenu
     */
    private void editChangeArtifact(Change segment, int elementIdList) {
        List<Integer> type = segment.getArtifactIndex();
        List<Integer> artifactIdId = dataModel.getArtifactId(type);
        int deleteIndexInProject = dataModel.getArtifactIndexInProject(elementIdList);
        for (int i = 0; i < type.size(); i++) {
            int index = type.get(i);
            if (artifactIdId.get(i) == elementIdList) {
                segment.getArtifactIndex().remove(i);
            } else if (index > deleteIndexInProject) {
                segment.getArtifactIndex().remove(i);
                segment.getArtifactIndex().add(i, index - 1);
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Configuration a Committed Configuration
     *
     * @param segment       instace Configuration
     * @param elementIdList indexi pro zmenu
     */
    private void editConfigurationCommittedConfiguration(Configuration segment, int elementIdList) {
        List<Integer> type = segment.getCommitedConfiguration();
        List<Integer> commitedConfigurationId = dataModel.getCommitedConfigurationId(type);
        int deleteIndexInProject = dataModel.getCommitedConfigurationIndexInProject(elementIdList);
        for (int i = 0; i < type.size(); i++) {
            int index = type.get(i);
            if (commitedConfigurationId.get(i) == elementIdList) {
                segment.getCommitedConfiguration().remove(i);
            } else if (index > deleteIndexInProject) {
                segment.getCommitedConfiguration().remove(i);
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Phase a Configuration
     *
     * @param segment       instace Phase
     * @param elementIdList indexi pro zmenu
     */
    private void editPhaseConfiguration(Phase segment, int elementIdList) {
        List<Integer> type = segment.getConfiguration();
        List<Integer> configurationId = dataModel.getConfigurationId(type);
        int deleteIndexInProject = dataModel.getConfigurationIndexInProject(elementIdList);
        for (int i = type.size() - 1; i >= 0; i--) {
            int index = type.get(i);
            if (configurationId.get(i) == elementIdList) {
                segment.getConfiguration().remove(i);
                segment.getConfigurationIndicator().remove(i);
            } else if (index > deleteIndexInProject) {
                segment.getConfiguration().remove(i);
                segment.getConfiguration().add(i, index - 1);
                segment.getConfigurationIndicator().remove(i);
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Iteration a Configuration
     *
     * @param segment       instace Iteration
     * @param elementIdList indexi pro zmenu
     */
    private void editIterationConfiguration(Iteration segment, int elementIdList) {
        List<Integer> type = segment.getConfiguration();
        List<Integer> configurationId = dataModel.getConfigurationId(type);
        int deleteIndexInProject = dataModel.getConfigurationIndexInProject(elementIdList);
        for (int i = type.size() - 1; i >= 0; i--) {
            int index = type.get(i);
            if (configurationId.get(i) == elementIdList) {
                segment.getConfiguration().remove(i);
                segment.getConfigurationIndicator().remove(i);
            } else if (index > deleteIndexInProject) {
                segment.getConfiguration().remove(i);
                segment.getConfiguration().add(i, index - 1);
                segment.getConfigurationIndicator().remove(i);
            }
        }
    }


    /**
     * Metoda pro upravu zavislosti jednotlivych prvku
     *
     * @param formType      typ zavisejiciho prvku
     * @param elementType   typ
     * @param elementIdList
     */
    public void updateItemList(SegmentType formType, SegmentType elementType, int elementIdList) {
        switch (formType) {
            case Change:
                switch (elementType) {
                    case Artifact:
                        for (Change segment : dataModel.getChanges()) {
                            editChangeArtifact(segment, elementIdList);

                        }
                }
                break;
            case Configuration:
                switch (elementType) {
                    case Committed_Configuration:
                        for (Configuration segment : dataModel.getConfigurations()) {
                            editConfigurationCommittedConfiguration(segment, elementIdList);
                        }
                }
            case Phase:
                switch (elementType) {
                    case Configuration:
                        for (Phase segment : dataModel.getPhases()) {
                            editPhaseConfiguration(segment, elementIdList);
                        }
                        break;
                }
                break;
            case Iteration:
                switch (elementType) {
                    case Configuration:
                        for (Iteration segment : dataModel.getIterations()) {
                            editIterationConfiguration(segment, elementIdList);
                        }
                        break;
                }
                break;
            default:
        }

    }

    public void editPersonRoleType(Person segment, ArrayList<Integer> elementIdList) {
        List<Integer> type = segment.getType();
        List<Integer> roleTypeId = dataModel.getRoleTypeId(type);
        for (int deleteId : elementIdList) {
            int deleteIndexInProject = dataModel.getRoleTypeIndexInProject(deleteId);
            for (int i = roleTypeId.size() - 1; i >= 0; i--) {
                int index = type.get(i);
                if (roleTypeId.get(i) == deleteId) {
                    segment.getType().remove(i);
                    segment.getTypeIndicator().remove(i);
                } else if (type.get(i) > deleteIndexInProject) {
                    segment.getType().remove(i);
                    segment.getType().add(i, index - 1);
                    segment.getTypeIndicator().remove(i);
                }
            }
        }
    }


    /**
     * Metoda pro upravu zavislosti mezi CPR a Person
     *
     * @param segment       instace ConfigPersonRelation
     * @param elementIdList indexi pro zmenu
     */
    private void editCPRPerson(ConfigPersonRelation segment, ArrayList<Integer> elementIdList) {
        List<Integer> type = segment.getPersonIndex();
        List<Integer> personId = dataModel.getRoleId(type);

        for (int deleteId : elementIdList) {
            int deleteIndexInProject = dataModel.getPersonIndexInProject(deleteId);
            for (int i = 0; i < personId.size(); i++) {
                int index = type.get(i);
                if (personId.get(i) == deleteId) {
                    segment.getPersonIndex().remove(i);
                    segment.getPersonIndicator().remove(i);
                } else if (index > deleteIndexInProject) {
                    segment.getPersonIndex().remove(i);
                    segment.getPersonIndex().add(i, index - 1);
                    segment.getPersonIndicator().remove(index);
                }
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Artifactem a Person
     *
     * @param segment       instace Artifact
     * @param elementIdList indexi pro zmenu
     */
    private void editArtifactPerson(Artifact segment, ArrayList<Integer> elementIdList) {
        List<Integer> type = segment.getAuthorIndex();
        List<Integer> personId = dataModel.getRoleId(type);
        for (int deleteId : elementIdList) {
            int deleteIndexInProject = dataModel.getPersonIndexInProject(deleteId);
            for (int i = 0; i < personId.size(); i++) {
                int index = type.get(i);
                if (personId.get(i) == deleteId) {
                    segment.getAuthorIndex().remove(i);
                    segment.getAuthorIndicator().remove(i);
                } else if (index > deleteIndexInProject) {
                    segment.getAuthorIndex().remove(i);
                    segment.getAuthorIndex().add(i, index - 1);
                    segment.getAuthorIndicator().remove(index);
                }
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Phase a Milestone
     *
     * @param segment       instace Phase
     * @param elementIdList indexi pro zmenu
     */
    private void editPhaseMilestone(Phase segment, ArrayList<Integer> elementIdList) {
        List<Integer> type = segment.getMilestoneIndex();
        List<Integer> milestoneId = dataModel.getMilestoneId(type);

        for (int deleteId : elementIdList) {
            int deleteIndexInProject = dataModel.getMilestoneIndexInProject(deleteId);
            for (int i = type.size() - 1; i >= 0; i--) {
                int index = type.get(i);
                if (milestoneId.get(i) == deleteId) {
                    segment.getMilestoneIndex().remove(i);
                    segment.getMilestoneIndicator().remove(i);
                } else if (index > deleteIndexInProject) {
                    segment.getMilestoneIndex().remove(i);
                    segment.getMilestoneIndex().add(i, index - 1);
                    segment.getMilestoneIndicator().remove(index);
                }
            }
        }
    }

    /**
     * Metoda pro upravu zavislosti mezi Iteration a Configuration
     *
     * @param segment       instace Iteration
     * @param elementIdList indexi pro zmenu
     */
    private void editIterationConfiguration(Iteration segment, ArrayList<Integer> elementIdList) {
        List<Integer> type = segment.getConfiguration();
        List<Integer> configurationId = dataModel.getConfigurationId(type);
        for (int deleteId : elementIdList) {
            int deleteIndexInProject = dataModel.getConfigurationIndexInProject(deleteId);
            for (int i = 0; i < type.size(); i++) {
                int index = type.get(i);
                if (configurationId.get(i) == deleteId) {
                    segment.getConfiguration().remove(i);
                    segment.getConfigurationIndicator().remove(i);
                } else if (index > deleteIndexInProject) {
                    segment.getConfiguration().remove(i);
                    segment.getConfiguration().add(i, index - 1);
                    segment.getConfigurationIndicator().remove(i);
                }
            }
        }
    }

    /**
     * Pretizena metoda pro upravu zavislosti jednotlivych prvku
     *
     * @param formType      typ zavisejiciho prvku
     * @param elementType   typ
     * @param elementIdList
     */
    public void updateItemList(SegmentType formType, SegmentType elementType, ArrayList<Integer> elementIdList) {
        if (elementIdList == null) {
            return;
        }
        switch (formType) {
            case Work_Unit:
                updateWUListItem(elementType, elementIdList);
                break;
            case Milestone:
                switch (elementType) {
                    case Criterion:
                        for (Milestone segment : dataModel.getMilestones()) {
                            int i = 0;
                            for (CriterionList list : segment.getCriteriaIndexs()) {
                                updateElementListFromSegment(elementIdList, list.getCriterions());
                                if (list.getCriterions().size() == 0) {
                                    segment.getCriteriaIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;
                    default:
                }
                break;
            case Person:
                for (Person segment : dataModel.getPersons()) {
                    editPersonRoleType(segment, elementIdList);
                }

                break;
            case Config_Person_Relation:
                switch (elementType) {
                    case Person:
                        for (ConfigPersonRelation segment : dataModel.getConfigPersonRelations()) {
                            editCPRPerson(segment, elementIdList);
                        }
                        break;
                    default:
                }
                break;
            case Artifact:
                switch (elementType) {
                    case Person:
                        for (Artifact segment : dataModel.getArtifacts()) {
                            editArtifactPerson(segment, elementIdList);
                        }
                        break;
                    default:
                }
                break;
            case Phase:
                switch (elementType) {
                    case Milestone:
                        for (Phase segment : dataModel.getPhases()) {
                            editPhaseMilestone(segment, elementIdList);
                        }
                        break;
                    case Work_Unit:
                        for (Phase segment : dataModel.getPhases()) {
                            int i = 0;
                            for (WorkUnitList list : segment.getWorkUnits()) {
                                updateElementListFromSegment(elementIdList, list.getWorkUnits());
                                if (list.getWorkUnits().size() == 0) {
                                    segment.getWorkUnitsIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;
                    default:
                }
                break;
            case Iteration:
                switch (elementType) {
                    case Configuration:
                        for (Iteration segment : dataModel.getIterations()) {
                            editIterationConfiguration(segment, elementIdList);
                        }
                        break;
                    case Work_Unit:
                        for (Iteration segment : dataModel.getIterations()) {
                            int i = 0;
                            for (WorkUnitList list : segment.getWorkUnits()) {
                                updateElementListFromSegment(elementIdList, list.getWorkUnits());
                                if (list.getWorkUnits().size() == 0) {
                                    segment.getWorkUnitsIndicator().remove(i);
                                }
                                i++;
                            }
                        }
                        break;
                }
                break;
            case Activity:
                for (Activity segment : dataModel.getActivities()) {
                    int i = 0;
                    for (WorkUnitList list : segment.getWorkUnits()) {
                        updateElementListFromSegment(elementIdList, list.getWorkUnits());
                        if (list.getWorkUnits().size() == 0) {
                            segment.getWorkUnitsIndicator().remove(i);
                        }
                        i++;
                    }
                }
                break;
            case Commit:
                switch (elementType) {

                }
                break;
            case Configuration:
                configurationEdit(elementType, elementIdList);
                break;
            default:

        }
    }

    /**
     * Metoda pro rozhodnuti o jakou zavislost na configuration se jedna a naslede upravi potrebne indexi
     *
     * @param elementType   instace SegmentType pro urceni zavislosti
     * @param elementIdList seznam identifikatoru zavislich
     */
    private void configurationEdit(SegmentType elementType, ArrayList<Integer> elementIdList) {
        switch (elementType) {
            case Person:
                for (Configuration segment : dataModel.getConfigurations()) {
                    List<Integer> type = segment.getAuthorIndex();
                    List<Integer> personId = dataModel.getRoleId(type);
                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getPersonIndexInProject(deleteId);
                        for (int i = 0; i < personId.size(); i++) {
                            int index = type.get(i);
                            if (personId.get(i) == deleteId) {
                                segment.getAuthorIndex().remove(i);
                                segment.getAuthorIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getAuthorIndex().remove(i);
                                segment.getAuthorIndex().add(i, index - 1);
                                segment.getAuthorIndicator().remove(i);
                            }
                        }
                    }
                }
                break;
            case Config_Person_Relation:
                for (Configuration segment : dataModel.getConfigurations()) {
                    int i = 0;
                    for (CPRSList list : segment.getCPRsIndexs()) {
                        updateElementListFromSegment(elementIdList, list.getCPRs());
                        if (list.getCPRs().size() == 0) {
                            segment.getCPRsIndicator().remove(i);
                        }
                        i++;
                    }
                }
                break;
            case Change:
                for (Configuration segment : dataModel.getConfigurations()) {
                    int i = 0;
                    ArrayList<ChangeList> tmp = new ArrayList(segment.getChangesIndexs());
                    for (ChangeList list : tmp) {
                        updateElementListFromSegment(elementIdList, list.getChanges());
                        if (list.getChanges().size() == 0) {
                            segment.getChangesIndexs().remove(i);
                        }
                        i++;
                    }
                }
                break;
            case Branch:
                for (Configuration segment : dataModel.getConfigurations()) {
                    int i = 0;
                    for (BranchList list : segment.getBranchIndexs()) {
                        updateElementListFromSegment(elementIdList, list.getBranches());
                        if (list.getBranches().size() == 0) {
                            segment.getBranchIndicator().remove(i);
                        }
                        i++;
                    }
                }
                break;
            case VCSTag:
                for (Configuration segment : dataModel.getConfigurations()) {
                    List<Integer> type = segment.getTagIndex();
                    List<Integer> tagId = dataModel.getTagId(type);
                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getVCSTAgProjectIndex(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (tagId.get(i) == deleteId) {
                                segment.getTagIndex().remove(i);
                                segment.getTagsIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getTagIndex().remove(i);
                                segment.getTagIndex().add(i, index - 1);
                                segment.getTagsIndicator().remove(i);
                            }
                        }
                    }
                }
                break;

            default:
        }
    }


    /**
     * Metoda pro upravu jednotlivych zavislosti v seznamu
     *
     * @param changeValue index pro zmenu
     * @param elementList seznam indexu
     */
    private void updateElementListFromSegmet(int changeValue, List<Integer> elementList) {

        int changeIndex = 0;
        int change = 0;

        for (int i = 0; i < elementList.size(); i++) {
            if (changeValue == elementList.get(i)) {
                changeIndex = i;
                change++;
            }
        }

        if (change == 0) {
            for (int i = 0; i < elementList.size(); i++) {
                if (changeValue > elementList.get(i)) {
                    continue;
                }
                int value = elementList.get(i) - 1;
                elementList.set(i, value);
            }
            return;
        }

        for (int i = elementList.size() - 1; i >= 0; i--) {
            if (elementList.get(i) == changeValue) {
                break;
            } else {
                int value = elementList.get(i) - 1;
                elementList.set(i, value);
            }

        }

        elementList.remove(changeIndex);

    }

    /**
     * Pretizena metoda pro upravu jednotlivych zavislosti v seznamu
     *
     * @param indices     indexy pro zmenu
     * @param elementList seznam indexu
     */
    private void updateElementListFromSegment(ArrayList<Integer> indices, List<Integer> elementList) {

        for (int j = indices.size() - 1; j >= 0; j--) {

            updateElementListFromSegmet(indices.get(j), elementList);

        }
    }

    /**
     * Metoda pro zmenu zavislosti na Work Unit
     *
     * @param element       Typ zavisleho prvku
     * @param elementIdList seznam indexu
     */
    private void updateWUListItem(SegmentType element, ArrayList<Integer> elementIdList) {

        switch (element) {
            case Priority:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
                    List<Integer> type = segment.getPriorityIndex();
                    List<Integer> milestoneId = dataModel.getPriorityId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getPriorityIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getPriorityIndex().remove(i);
                                segment.getPriorityIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getPriorityIndex().remove(i);
                                segment.getPriorityIndex().add(i, index - 1);
                                segment.getPriorityIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            case Severity:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
                    List<Integer> type = segment.getSeverityIndex();
                    List<Integer> milestoneId = dataModel.getSeverityId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getSeverityIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getSeverityIndex().remove(i);
                                segment.getSeverityIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getSeverityIndex().remove(i);
                                segment.getSeverityIndex().add(i, index - 1);
                                segment.getSeverityIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            case Person:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
//                    int index = segment.getAuthorIndex();
//                    int index2 = segment.getAssigneeIndex();
//                    int personId = dataModel.getRoleId(index);
//                    int personId2 = dataModel.getRoleId(index);
//                    for(int deleteId : elementIdList){
//                        int deleteIndexInProject = dataModel.getPersonIndexInProject(deleteId);
//                        if( personId == deleteId ){
//                            segment.setAuthorIndex(-1);
//                        }else if(index > deleteIndexInProject ){
//                            segment.setAuthorIndex(index - 1);
//                        }
//                        if( personId2 == deleteId ){
//                            segment.setAssigneeIndex(-1);
//                        }else if(index2 > deleteIndexInProject ){
//                            segment.setAssigneeIndex(index2 - 1);
//                        }
//                    }
                }
                break;
            case Resolution:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
                    List<Integer> type = segment.getResolutionIndex();
                    List<Integer> milestoneId = dataModel.getResolutionId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getResolutionIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getResolutionIndex().remove(i);
                                segment.getResolutionIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getResolutionIndex().remove(i);
                                segment.getResolutionIndex().add(i, index - 1);
                                segment.getResolutionIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            case Status:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
                    List<Integer> type = segment.getStatusIndex();
                    List<Integer> milestoneId = dataModel.getStatusId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getStatusIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getStatusIndex().remove(i);
                                segment.getStatusIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getStatusIndex().remove(i);
                                segment.getStatusIndex().add(i, index - 1);
                                segment.getStatusIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            case Type:

                for (WorkUnit segment : dataModel.getWorkUnits()) {

                    List<Integer> type = segment.getTypeIndex();
                    List<Integer> milestoneId = dataModel.getTypeId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getTypeIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getTypeIndex().remove(i);
                                segment.getTypeIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getTypeIndex().remove(i);
                                segment.getTypeIndex().add(i, index - 1);
                                segment.getTypeIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            case Relation:
                for (WorkUnit segment : dataModel.getWorkUnits()) {
                    List<Integer> type = segment.getRelationIndex();
                    List<Integer> milestoneId = dataModel.getRelationId(type);

                    for (int deleteId : elementIdList) {
                        int deleteIndexInProject = dataModel.getRelationIndexInProject(deleteId);
                        for (int i = type.size() - 1; i >= 0; i--) {
                            int index = type.get(i);
                            if (milestoneId.get(i) == deleteId) {
                                segment.getRelationIndex().remove(i);
                                //          segment.getRelationIndicator().remove(i);
                            } else if (index > deleteIndexInProject) {
                                segment.getRelationIndex().remove(i);
                                segment.getRelationIndex().add(i, index - 1);
//                                segment.getRelationIndicator().remove(index);
                            }
                        }
                    }
                }
                break;
            default:

        }

    }

}
