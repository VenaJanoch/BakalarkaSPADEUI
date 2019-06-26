package interfaces;

import services.SegmentType;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici rozhrani pro editaci dat ve formularich a datovem modelu
 *
 * @author Václav Janoch
 */
public interface IEditFormController {

    /**
     * Metoda pro zavolani konkretnich metod pro editaci vyctoveho typu Work Unit
     *
     * @param segmentType       Typ elementu
     * @param alias             alias objektu
     * @param name              seznam Stringu s obsazenymi jmeny
     * @param nameIndicator     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param classIndices      seznam zvolených tříd
     * @param superClassIndices seznam zvolených super tříd
     * @param classString       seznam s jmeny tříd
     * @param superSting        seznam s jmeny super tříd
     * @param classTable        instance třídy prvku tabulky
     * @param exist             bool promenna s inforací o existenci prvku v modelu
     * @param id                identifikator konkrétní instance
     */
    void editDataFromClass(SegmentType segmentType, String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Person
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias              alias objektu
     * @param name               seznam Stringu s obsazenymi jmeny
     * @param nameIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param roleTypeIndex      seznam zvolených tříd
     * @param roleTypeIndicators seznam zvolených super tříd
     * @param count              pocet instaci objektu v modelu
     * @param personTable        instance třídy prvku tabulky
     * @param exist              bool promenna s inforací o existenci prvku v modelu
     * @param id                 identifikator konkrétní instance
     */
    void editDataFromPerson(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, String count, int countIndicator, ArrayList<Integer> roleTypeIndex,
                            ArrayList<Integer> roleTypeIndicators, PersonTable personTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Milestone
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias               alias objektu
     * @param nameST              seznam Stringu s obsazenymi jmeny
     * @param nameIndicators      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param criterionIndex      seznam zvolených criterii
     * @param criterionIndicators seznam zvolených super tříd
     * @param milestoneTable      instance třídy prvku tabulky
     * @param exist               bool promenna s inforací o existenci prvku v modelu
     * @param id                  identifikator konkrétní instance
     */
    void editDataFromMilestone(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                               MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                               ArrayList<Integer> criterionIndicators, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Criterion
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param description           list s descriptions
     * @param descriptionIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param alias                 alias objektu
     * @param nameST                seznam Stringu s obsazenymi jmeny
     * @param nameIndicators        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param criterionTable        instance třídy prvku tabulky
     * @param exist                 bool promenna s inforací o existenci prvku v modelu
     * @param id                    identifikator konkrétní instance
     */
    void editDataFromCriterion(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                               CriterionTable criterionTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Resolution
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                 alias objektu
     * @param nameST                seznam Stringu s obsazenymi jmeny
     * @param nameIndicators        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description           list s descriptions
     * @param descriptionIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param roleIndex             seznam zvolených tříd
     * @param roleIndicators        seznam zvolených super tříd
     * @param cprTable              instance třídy prvku tabulky
     * @param exist                 bool promenna s inforací o existenci prvku v modelu
     */
    void editDataFromCPR(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> roleIndex,
                         ArrayList<Integer> roleIndicators, boolean exist, CPRTable cprTable);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Resolution
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias          alias objektu
     * @param nameST         seznam Stringu s obsazenymi jmeny
     * @param nameIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param isMainBranch   bool promenna s hodnoutou yda se jedna o hlavni vetev
     * @param exist          bool promenna s inforací o existenci prvku v modelu
     * @param branchTable    instance třídy prvku tabulky
     */
    void editDataFromBranch(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, boolean isMainBranch, boolean exist, BranchTable branchTable);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Phase
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias              alias objektu
     * @param actName            seznam Stringu s obsazenymi jmeny
     * @param nameIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDateL           seznam LocalDate s daty ukonceni
     * @param desc               seznam Stringu s descrtiption
     * @param confIndex          seznam Integeru jako indexu do seznamu configuration
     * @param milestoneIndex     seznam Integeru jako indexu do seznamu milestone
     * @param workUnitIndexList  seznam Integeru jako indexu do seznamu Work Unit
     * @param workUnitIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDateIndicator   seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param confIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param milestoneIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param phaseTable         instance třídy prvku tabulky
     * @param exist              bool promenna s inforací o existenci prvku v modelu
     * @param id                 identifikator konkrétní instance
     */
    void editDataFromPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                           ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                           ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                           ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator,
                           PhaseTable phaseTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Iteration
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias              alias objektu
     * @param actName            seznam Stringu s obsazenymi jmeny
     * @param nameIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDateL           seznam LocalDate s daty ukonceni
     * @param startDateL         seznam LocalDate s daty zalozeni
     * @param desc               seznam Stringu s descrtiption
     * @param confIndex          seznam Integeru jako indexu do seznamu configuration
     * @param workUnitIndexList  seznam Integeru jako indexu do seznamu Work Unit
     * @param workUnitIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDateIndicator   seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param startDateIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param confIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param iterationTable     instance třídy prvku tabulky
     * @param exist              bool promenna s inforací o existenci prvku v modelu
     * @param id                 identifikator konkrétní instance
     */
    void editDataFromIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator,
                               IterationTable iterationTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias              alias objektu
     * @param name               seznam Stringu s obsazenymi jmeny
     * @param nameIndicators     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDate            seznam LocalDate s daty ukonceni
     * @param description        seznam Stringu s descrtiption
     * @param workUnits          seznam Integeru jako indexu do seznamu Work Unit
     * @param workUnitIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDateIndicators  seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descIndicators     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param activityTable      instance třídy prvku tabulky
     * @param exist              bool promenna s inforací o existenci prvku v modelu
     * @param id                 identifikator konkrétní instance
     */
    void editDataFromActivity(String alias, ArrayList<String> name, ArrayList<String> description, ArrayList<ArrayList<Integer>> workUnits,
                              ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicators, ArrayList<Integer> workUnitIndicators,
                              ArrayList<LocalDate> endDate, ArrayList<Integer> endDateIndicators, ActivityTable activityTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Change
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                alias objektu
     * @param name                 seznam Stringu s obsazenymi jmeny
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description          seznam Stringu s descrtiption
     * @param artifacts            seznam Itegeru s indexi na Artifact
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param changeTable          instance třídy prvku tabulky
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param id                   identifikator konkrétní instance
     */
    void editDataFromChange(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> artifacts,
                            ArrayList<Integer> descriptionIndicator, boolean exist, ChangeTable changeTable,
                            int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                objektu
     * @param name                 seznam Stringu s obsazenymi jmeny
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description          seznam Stringu s descrtiption
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param roleIndex            seznam Itegeru s indexi naPerson
     * @param typeIndex            seznam Itegeru s indexi na
     * @param localDate            seznam LocalDate s daty ukonceni
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param roleIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param typeIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param dateIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param artifactTable        instance třídy prvku tabulky
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param id                   identifikator konkrétní instance
     */
    void editDataFromArtifact(String alias, ArrayList<String> name, ArrayList<String> description, boolean exist,
                              ArrayList<Integer> roleIndex, ArrayList<Integer> typeIndex, ArrayList<LocalDate> localDate,
                              ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                              ArrayList<Integer> roleIndicator, ArrayList<Integer> typeIndicator, ArrayList<Integer> dateIndicator,
                              ArtifactTable artifactTable, String count, int countIndicator, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                alias objektu
     * @param progress             seznam double hodnot pro progress
     * @param progressIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param name                 seznam Stringu se jmeny
     * @param description          seznam Stringu s descrtiption
     * @param category             seznam Stringu s category
     * @param assigneIndex         seznam Itegeru s indexi na Person
     * @param authorIndex          seznam Itegeru s indexi na Person
     * @param priorityIndex        seznam Itegeru s indexi na Priority
     * @param severityIndex        seznam Itegeru s indexi na Severity
     * @param typeIndex            seznam Itegeru s indexi na Type
     * @param resolutionIndex      seznam Itegeru s indexi na Resolution
     * @param statusIndex          seznam Itegeru s indexi na Status
     * @param estimatedTime        seznam Double s estimate time
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param categoryIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param assigneIndicator     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param authorIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param priorityIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param severityIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param typeIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param resolutionIndicator  seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param statusIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param estimateIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param isExist              bool promenna s inforací o existenci prvku v modelu
     * @param relations            seznam Itegeru s indexi na Relation
     * @param workUnits            seznam Itegeru s indexi na Work Unit
     * @param createDate           seznam LocalDate s daty ukonceni
     * @param createdIndicator     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param workUnitTable        instance třídy prvku tabulky
     * @param id                   identificator konkretni instance
     */
    void editDataFromWorkUnit(String alias, ArrayList<String> progress, ArrayList<Integer> progressIndicator, ArrayList<String> name, ArrayList<String> description, ArrayList<String> category,
                              ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                              ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                              ArrayList<String> estimatedTime, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                              ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                              ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                              ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations, ArrayList<ArrayList<Integer>> workUnits,
                              ArrayList<LocalDate> createDate, ArrayList<Integer> createdIndicator, WorkUnitTable workUnitTable, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                alias objektu
     * @param actName              seznam Stringu s obsazenymi jmeny
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description          seznam Stringu s descrtiption
     * @param createDate           seznam LocalDate s daty ukonceni
     * @param isRelease            bool promenna s informací o tom zda je release
     * @param tag                  seznam Itegeru s indexi na VCSTag
     * @param cprs                 seznam Itegeru s indexi na Work Unit
     * @param branchIndexs         seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param branchIndicators     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param changeIndexs         seznam Itegeru s indexi na Work Unit
     * @param cprIndicators        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param createdIndicator     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param tagIndicator         seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param changeIndicator      seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param count                pocet instanci prvku v objektu
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param configId             identifikator konkrétní instance
     */
    void editDataFromConfiguration(String alias, ArrayList<String> actName, ArrayList<String> description, ArrayList<LocalDate> createDate,
                                   boolean isRelease, ArrayList<Integer> tag, ArrayList<ArrayList<Integer>> cprs, ArrayList<ArrayList<Integer>> branchIndexs, ArrayList<Integer> branchIndicators,
                                   ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                                   ArrayList<Integer> createdIndicator, ArrayList<Integer> tagIndicator, ArrayList<Integer> changeIndicator,
                                   String count, int countIndicator, boolean exist, int configId);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                alias objektu
     * @param name                 seznam Stringu s obsazenymi jmeny
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description          list s descriptions
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param tagTable             instance třídy prvku tabulky
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param id                   identifikator konkrétní instance
     */
    void editDataFromVCSTag(String alias, ArrayList<String> name, ArrayList<String> description,
                            ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                 alias objektu
     * @param name                  seznam Stringu s obsazenymi jmeny
     * @param nameIndicator         seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descriptions          seznam Stringu s descrtiption
     * @param createDate            seznam LocalDate s daty ukonceni
     * @param release               bool promenna s informací o tom zda je release
     * @param descriptionsIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param createIndicator       seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param count                 pocet instanci prvku v objektu
     * @param exist                 bool promenna s inforací o existenci prvku v modelu
     * @param id                    identifikator konkrétní instance
     */
    void editDataFromCommit(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator,
                            ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean release, String count, int countIndicator, boolean exist, int id);

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Activity
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                   alias objektu
     * @param name                    seznam Stringu s obsazenymi jmeny
     * @param nameIndicator           seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description             seznam Stringu s descrtiption
     * @param created                 seznam LocalDate s daty ukonceni
     * @param descriptionIndicator    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param createdIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param count                   pocet instanci prvku v objektu
     * @param exist                   bool promenna s inforací o existenci prvku v modelu
     * @param commitedConfigurationId identifikator konkrétní instance
     */
    void editDataFromCommitedConfiguration(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> descriptionIndicator, ArrayList<LocalDate> created,
                                           ArrayList<Integer> createdIndicator, ArrayList<LocalDate> committed, ArrayList<Integer> committedIndicator, String count, int countIndicator, boolean exist, int commitedConfigurationId);

    /**
     * Metoda pro editaci dat v datovych strukturach Projectu
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param name               seznam Stringu s obsazenymi jmeny
     * @param nameIndicators     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param endDate            seznam LocalDate s daty ukonceni
     * @param startDate          seznam LocalDate s daty zalozeni
     * @param desc               seznam Stringu s descrtiption
     * @param workUnit           seznam Integeru jako indexu do seznamu Work Unit
     * @param workUnitIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param date1Indicators    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param date2Indicators    seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param descIndicators     seznam Integeru uchovávající indexi vybraných znaků rovnosti
     */
    void editDataFromProject(ArrayList<String> name, ArrayList<LocalDate> startDate, ArrayList<LocalDate> endDate, ArrayList<String> desc, ArrayList<ArrayList<Integer>> workUnit, ArrayList<Integer> workUnitIndicators,
                             ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators, ArrayList<Integer> date2Indicators,
                             ArrayList<Integer> descIndicators);

    /**
     * Metoda pro zmenu souradnic elementu Commit na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    void editCoordsInCommit(double x, double y, int id);

    /**
     * Metoda pro zmenu souradnic elementu  Committed Configurations na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    void editCoordsInCommitedConfiguration(double x, double y, int id);

    /**
     * Metoda pro zmenu souradnic elementu Configuration na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    void editCoordsInConfiguration(double x, double y, int id);

    /**
     * Metoda pro zmenu souradnic elementu Artifact na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    void editCoordsInArtifact(double x, double y, int id);

    /**
     * Metoda pro zmenu souradnic elementu Person na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    void editCoordsInPerson(double x, double y, int id);


    /**
     * Metoda pro editaci dat v datovych strukturach elementu Role Type
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param description           list s descriptions
     * @param descriptionIndicators seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param alias                 alias objektu
     * @param name                  seznam Stringu s obsazenymi jmeny
     * @param nameIndicators        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param classListIndex        seznam zvolených tříd
     * @param superClassListIndex   seznam zvolených super tříd
     * @param classList             seznam s jmeny tříd
     * @param superClassList        seznam s jmeny super tříd
     * @param table                 instance třídy prvku tabulky
     * @param exist                 bool promenna s inforací o existenci prvku v modelu
     * @param id                    identifikator konkrétní instance
     */
    void editDataFromRoleType(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                              ArrayList<Integer> classListIndex, ArrayList<Integer> superClassListIndex, ArrayList<String> classList,
                              ArrayList<String> superClassList, RoleTypeTable table, boolean exist, int id);

}
