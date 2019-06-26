package controllers.formControllers;

import controllers.DataPreparer;
import controllers.InputController;
import interfaces.IEditDataModel;
import interfaces.IEditFormController;
import model.DataModel;
import model.IdentificatorCreater;
import services.MapperTableToObject;
import services.SegmentLists;
import services.SegmentType;
import services.TableToObjectInstanc;
import tables.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trida predstavujici controller pro editaci prvku z datovych struktur
 *
 * @author Václav Janoch
 */
public class EditFormController implements IEditFormController {

    /** Globalni promenne tridy **/

    /**
     * Potrebne tridy z datoveho modelu
     **/
    private IEditDataModel dataManipulator;
    private DataModel dataModel;
    private IdentificatorCreater identificatorCreater;
    private MapperTableToObject mapperTableToObject;
    private SegmentLists segmentLists;

    /**
     * Tridy pro upravu vkladanych dat
     **/
    private DataPreparer dataPreparer;
    private FormController formController;

    /**
     * Konstruktor tridy
     * Inicializuje potrebne globalni promenne
     * Parametry jsou tridy
     *
     * @param dataModel
     * @param identificatorCreater
     * @param mapperTableToObject
     * @param segmentLists
     * @param dataPreparer
     * @param formController
     */
    public EditFormController(DataModel dataModel, IdentificatorCreater identificatorCreater,
                              MapperTableToObject mapperTableToObject, SegmentLists segmentLists, DataPreparer dataPreparer, FormController formController) {
        this.dataModel = dataModel;
        this.dataManipulator = dataModel.getEditDataModel();
        this.identificatorCreater = identificatorCreater;
        this.mapperTableToObject = mapperTableToObject;
        this.segmentLists = segmentLists;
        this.dataPreparer = dataPreparer;
        this.formController = formController;
    }

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
    public void editDataFromClass(SegmentType segmentType, String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

        classTable.setAlias(alias);
        classTable.setClassType(classString.get(0));
        classTable.setSuperType(superSting.get(0));
        classTable.setExist(exist);
        switch (segmentType) {
            case Severity:
                editDataFromSeverity(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            case Priority:
                editDataFromPriority(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            case Status:
                editDataFromStatus(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            case Type:
                editDataFromType(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            case Relation:
                editDataFromRelation(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            case Resolution:
                editDataFromResolution(alias, nameForManipulator, nameIndicator, classIndices, superClassIndices, classString, superSting, classTable, exist, id);
                break;
            default:
        }
    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Resolution
     * Upravena data jsou predany konkretnim strukturam
     *
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

    private void editDataFromResolution(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                        ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInResolution(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Resolution, classTable.getId(), classTable);

    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Relation
     * Upravena data jsou predany do konkretnich struktur
     *
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
    private void editDataFromRelation(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInRelation(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Relation, classTable.getId(), classTable);

    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Type
     * Upravena data jsou predany konkretnim strukturam
     *
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
    private void editDataFromType(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInType(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Type, classTable.getId(), classTable);

    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Status
     * Upravena data jsou predany konkretnim strukturam
     *
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
    private void editDataFromStatus(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {

        dataManipulator.editDataInStatus(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Status, classTable.getId(), classTable);
    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Priority
     * Upravena data jsou predany konkretnim strukturam
     *
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
    private void editDataFromPriority(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,

                                      ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInPriority(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Priority, classTable.getId(), classTable);
    }

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
    public void editDataFromRoleType(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicators,
                                     ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> classListIndex,
                                     ArrayList<Integer> superClassListIndex, ArrayList<String> classList, ArrayList<String> superClassList,
                                     RoleTypeTable table, boolean exist, int id) {

        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);

        table.setAlias(alias);
        table.setExist(exist);
        dataManipulator.editDataInRoleType(alias, nameForManipulator, nameIndicators, descForManipulator, descriptionIndicators, classListIndex, superClassListIndex,
                classList, superClassList, exist, id);
        segmentLists.updateListItem(SegmentType.Role_Type, table.getId(), table);
    }

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
    public void editDataFromPerson(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, String count, int countIndicator, ArrayList<Integer> roleTypeIndex,
                                   ArrayList<Integer> roleTypeIndicators, PersonTable personTable, boolean exist, int id) {

        int instanceCount;
        try {
            instanceCount = InputController.isNumber(count, 1, 99, "Count");
            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<Integer> typeFormManipulator = dataPreparer.prepareIndexForManipulator(roleTypeIndex);

            dataManipulator.editDataInPerson(alias, nameForManipulator, typeFormManipulator, nameIndicator, roleTypeIndicators, instanceCount, countIndicator, exist, id);

            personTable.setAlias(alias);
            segmentLists.updateListItem(SegmentType.Person, id, personTable);

            ArrayList<Integer> roleType = dataModel.getRoleTypeIndex(typeFormManipulator);
            mapperTableToObject.mapTableToObject(SegmentType.Person, roleType, new TableToObjectInstanc(personTable.getAlias(), personTable.getId(),
                    SegmentType.Person));
            mapperTableToObject.updateValueList(roleType, mapperTableToObject.getPersonToRoleTypeMapper(),
                    id, alias);

            int formIndex = identificatorCreater.getPersonSegmentIndexToFormMaper().get(id);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount, countIndicator);
            formController.setItemColor(formIndex, exist);
        } catch (NumberFormatException e) {
            //  Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }


    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Severity
     * Upravena data jsou predany konkretnim strukturam
     *
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
    private void editDataFromSeverity(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator,
                                      ArrayList<Integer> classIndices, ArrayList<Integer> superClassIndices, ArrayList<String> classString, ArrayList<String> superSting
            , ClassTable classTable, boolean exist, int id) {
        dataManipulator.editDataInSeverity(alias, name, nameIndicator, classIndices, superClassIndices, classString, superSting, exist, id);
        segmentLists.updateListItem(SegmentType.Severity, classTable.getId(), classTable);
    }

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
    public void editDataFromMilestone(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                                      MilestoneTable milestoneTable, ArrayList<ArrayList<Integer>> criterionIndex,
                                      ArrayList<Integer> criterionIndicators, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        criterionIndex = dataPreparer.prepareIndicesForManipulator(criterionIndex);
        dataManipulator.editDataInMilestone(alias, nameForManipulator, descForManipulator, nameIndicators, descriptionIndicators, criterionIndicators, criterionIndex, exist, milestoneTable.getId());

        milestoneTable.setAlias(alias);
        milestoneTable.setExist(exist);

        if (criterionIndex.size() != 0) {
            ArrayList<Integer> criterionIndicies = dataModel.getCriterionIds(criterionIndex);
            mapperTableToObject.mapTableToObject(SegmentType.Milestone, criterionIndicies, new TableToObjectInstanc(milestoneTable.getAlias(), id,
                    SegmentType.Milestone));
            mapperTableToObject.updateValueList(criterionIndicies, mapperTableToObject.getMilestoneToCriterionMapper(),
                    milestoneTable.getId(), milestoneTable.getAlias());
        }

        segmentLists.updateListItem(SegmentType.Milestone, id, milestoneTable);


    }

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
    public void editDataFromCriterion(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators,
                                      CriterionTable criterionTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descForManipulator = InputController.fillTextMapper(description);
        criterionTable.setAlias(alias);
        criterionTable.setExist(exist);

        dataManipulator.editDataInCriterion(alias, nameForManipulator, descForManipulator, nameIndicators, descriptionIndicators, exist, criterionTable.getId());
        segmentLists.updateListItem(SegmentType.Criterion, id, criterionTable);
    }

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
    public void editDataFromCPR(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> roleIndex,
                                ArrayList<Integer> roleIndicators, boolean exist, CPRTable cprTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);
        ArrayList<Integer> roleManipulatorId = dataPreparer.prepareIndexForManipulator(roleIndex);

        int cprId = cprTable.getId();
        dataManipulator.editDataInCPR(alias, nameForManipulator, nameIndicators, descriptionForManipulator, descriptionIndicators, roleManipulatorId, roleIndicators, exist, cprId);
        cprTable.setAlias(alias);
        cprTable.setExist(exist);

        if (roleManipulatorId.size() != 0) {
            ArrayList<Integer> roleId = dataModel.getRoleId(roleManipulatorId);
            segmentLists.updateListItem(SegmentType.Config_Person_Relation, cprId, cprTable);
            mapperTableToObject.mapTableToObject(SegmentType.Config_Person_Relation, roleId,
                    new TableToObjectInstanc(cprTable.getAlias(), cprTable.getId(), SegmentType.Config_Person_Relation));
            mapperTableToObject.updateValueList(roleId, mapperTableToObject.getRoleMaps().get(3), cprId, alias);
        }

    }

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
    public void editDataFromBranch(String alias, ArrayList<String> nameST, ArrayList<Integer> nameIndicators, boolean isMainBranch, boolean exist, BranchTable branchTable) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(nameST);
        int branchId = branchTable.getId();
        dataManipulator.editDataInBranch(alias, nameForManipulator, nameIndicators, isMainBranch, exist, branchId);
        branchTable.setAlias(alias);
        if (isMainBranch) {
            branchTable.setMain("YES");
        } else {
            branchTable.setMain("NO");
        }
        branchTable.setMainBool(isMainBranch);
        branchTable.setExist(exist);
        segmentLists.updateListItem(SegmentType.Branch, branchId, branchTable);
    }

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
    @Override
    public void editDataFromPhase(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                                  ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                                  ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                  ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator,
                                  PhaseTable phaseTable, boolean exist, int id) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> date = InputController.checkDate(endDateL);
        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);
        ArrayList<Integer> milestoneForManipulator = dataPreparer.prepareIndexForManipulator(milestoneIndex);
        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);
        dataManipulator.editDataInPhase(alias, nameForManipulator, date, descriptionForManipulator, configIdForManipulator, milestoneForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicator, endDateIndicator, descIndicator, confIndicator, milestoneIndicator, exist, id);

        phaseTable.setAlias(alias);
        phaseTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToPhase(milestoneForManipulator, configIdForManipulator, workUnitIndicies, alias, id);

    }

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
    @Override
    public void editDataFromIteration(String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                      ArrayList<Integer> confIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                                      ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                      ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator,
                                      IterationTable iterationTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> startDate1 = InputController.checkDate(startDateL);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDateL);

        ArrayList<Integer> configIdForManipulator = dataPreparer.prepareIndexForManipulator(confIndex);
        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnitIndexList);

        dataManipulator.editDataInIteration(alias, nameForManipulator, endDate1, startDate1, descriptionForManipulator, configIdForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicator, endDateIndicator, startDateIndicator, descIndicator, confIndicator, exist, id);

        iterationTable.setAlias(alias);
        iterationTable.setExist(exist);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToIteration(configIdForManipulator, workUnitIndicies, alias, id);


    }

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
    @Override
    public void editDataFromProject(ArrayList<String> name, ArrayList<LocalDate> startDate, ArrayList<LocalDate> endDate, ArrayList<String> desc,
                                    ArrayList<ArrayList<Integer>> workUnit, ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators,
                                    ArrayList<Integer> date1Indicators, ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators) {

        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(desc);
        ArrayList<LocalDate> startDate1 = InputController.checkDate(startDate);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDate);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnit);

        dataManipulator.editDataInProject(nameForManipulator, startDate1, endDate1, descriptionForManipulator,
                workUnitsForManipulator, workUnitIndicators, nameIndicators, date1Indicators, date2Indicators, descIndicators);


    }


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
    @Override
    public void editDataFromActivity(String alias, ArrayList<String> name, ArrayList<String> description, ArrayList<ArrayList<Integer>> workUnits,
                                     ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicators, ArrayList<Integer> workUnitIndicators,
                                     ArrayList<LocalDate> endDate, ArrayList<Integer> endDateIndicators, ActivityTable activityTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
        ArrayList<LocalDate> endDate1 = InputController.checkDate(endDate);

        dataManipulator.editDataInActivity(alias, nameForManipulator, descriptionForManipulator, workUnitsForManipulator,
                nameIndicators, descIndicators, workUnitIndicators, endDate1, endDateIndicators, exist, id);

        activityTable.setExist(exist);
        activityTable.setAlias(alias);
        ArrayList<Integer> workUnitIndicies = dataModel.getWorkUnitIds(workUnitsForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Activity, workUnitIndicies, new TableToObjectInstanc(alias, id, SegmentType.Activity));

    }

    /**
     * Metoda pro editaci dat v datovych strukturach elementu Change
     * Nejprve je provedena kontorla vstup nasledne
     * Upravena data jsou predany konkretnim strukturam
     *
     * @param alias                alias objektu
     * @param name                 seznam Stringu s obsazenymi jmeny
     * @param nameIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param description          seznam Stringu s descrtiption
     * @param artifact             seznam Itegeru s indexi na Artifact
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param changeTable          instance třídy prvku tabulky
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param id                   identifikator konkrétní instance
     */
    @Override
    public void editDataFromChange(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> artifact,
                                   ArrayList<Integer> descriptionIndicator, boolean exist, ChangeTable changeTable,
                                   int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);
        ArrayList<Integer> artifactForManipulator = dataPreparer.prepareIndexForManipulator(artifact);

        dataManipulator.editDataInChange(alias, nameForManipulator, descriptionForManipulator, artifactForManipulator, nameIndicator, descriptionIndicator, exist, id);
        changeTable.setExist(exist);
        changeTable.setAlias(alias);
        ArrayList<Integer> artifactId = dataModel.getArtifactId(artifactForManipulator);
        mapperTableToObject.mapTableToObject(SegmentType.Change, artifactId, new TableToObjectInstanc(alias, id, SegmentType.Change));

    }

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
     * @param personIndex          seznam Itegeru s indexi naPerson
     * @param typeIndex            seznam Itegeru s indexi na
     * @param created              seznam LocalDate s daty ukonceni
     * @param descriptionIndicator seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param roleIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param typeIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param dateIndicator        seznam Integeru uchovávající indexi vybraných znaků rovnosti
     * @param artifactTable        instance třídy prvku tabulky
     * @param exist                bool promenna s inforací o existenci prvku v modelu
     * @param id                   identifikator konkrétní instance
     */
    @Override
    public void editDataFromArtifact(String alias, ArrayList<String> name, ArrayList<String> description, boolean exist,
                                     ArrayList<Integer> personIndex, ArrayList<Integer> typeIndex, ArrayList<LocalDate> created,
                                     ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                                     ArrayList<Integer> roleIndicator, ArrayList<Integer> typeIndicator, ArrayList<Integer> dateIndicator,
                                     ArtifactTable artifactTable, String count, int countIndicator, int id) {
        int instanceCount = 1;
        try {
            instanceCount = InputController.isNumber(count, 1, 99, "Count");

            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(created);
            ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

            dataManipulator.editDataInArtifact(alias, nameForManipulator, nameIndicator, descriptionForManipulator, descriptionIndicator,
                    date, dateIndicator, exist, personIndex, typeIndex, roleIndicator, typeIndicator, instanceCount, countIndicator, id);

            artifactTable.setAlias(alias);
            artifactTable.setExist(exist);
            int formIndex = identificatorCreater.getArtifactSegmentIdToFormIndexMaper().get(id);
            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount, countIndicator);
            formController.setItemColor(formIndex, exist);

        } catch (NumberFormatException e) {
            //    Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

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
    @Override
    public void editDataFromWorkUnit(String alias, ArrayList<String> progress, ArrayList<Integer> progressIndicator, ArrayList<String> name, ArrayList<String> description, ArrayList<String> category,
                                     ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                     ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                     ArrayList<String> estimatedTime, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                     ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                     ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                     ArrayList<Integer> estimateIndicator, boolean isExist, ArrayList<Integer> relations, ArrayList<ArrayList<Integer>> workUnits,
                                     ArrayList<LocalDate> createDate, ArrayList<Integer> createdIndicator, WorkUnitTable workUnitTable, int id) {

        try {
            ArrayList<Double> estimateForDataManipulator = new ArrayList<>();
            if (estimatedTime.size() != 0) {
                estimateForDataManipulator = InputController.isDoubleNumber(estimatedTime, "Estimated time");
            }

            ArrayList<Integer> progressForDataManipulator = new ArrayList<>();
            if (progress.size() != 0) {
                progressForDataManipulator = InputController.isNumber(progress, "Progress", 0, 100);
            }

            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<String> categoryForManipulator = InputController.fillTextMapper(category);
            ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

            ArrayList<ArrayList<Integer>> workUnitsForManipulator = dataPreparer.prepareIndicesForManipulator(workUnits);
            ArrayList<Integer> relationForManipulator = dataPreparer.prepareIndexForManipulator(relations);


            ArrayList<Integer> assigneForManipulator = dataPreparer.prepareIndexForManipulator(assigneIndex);
            ArrayList<Integer> authorForManipulator = dataPreparer.prepareIndexForManipulator(authorIndex);
            ArrayList<Integer> priorityForManipulator = dataPreparer.prepareIndexForManipulator(priorityIndex);
            ArrayList<Integer> severityForManipulator = dataPreparer.prepareIndexForManipulator(severityIndex);
            ArrayList<Integer> typeForManipulator = dataPreparer.prepareIndexForManipulator(typeIndex);
            ArrayList<Integer> resolutionForManipulator = dataPreparer.prepareIndexForManipulator(resolutionIndex);
            ArrayList<Integer> statusForManipulator = dataPreparer.prepareIndexForManipulator(statusIndex);

            dataManipulator.editDataInWorkUnit(alias, progressForDataManipulator, progressIndicator, nameForManipulator, descriptionForManipulator, categoryForManipulator,
                    assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator,
                    typeForManipulator, resolutionForManipulator, statusForManipulator,
                    estimateForDataManipulator, nameIndicator, descriptionIndicator, categoryIndicator,
                    assigneIndicator, authorIndicator, priorityIndicator, severityIndicator,
                    typeIndicator, resolutionIndicator, statusIndicator, estimateIndicator, createDate, createdIndicator, isExist, relationForManipulator, workUnitsForManipulator, id);
            workUnitTable.setExist(isExist);
            workUnitTable.setAlias(alias);
            mapperTableToObject.mapTableToWU(assigneForManipulator, authorForManipulator, priorityForManipulator, severityForManipulator, typeForManipulator, resolutionForManipulator
                    , statusIndex, id, workUnitTable.getAlias());
            segmentLists.updateListItem(SegmentType.Work_Unit, id, workUnitTable);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

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
     * @param tag                  seznam Itegeru s indexi na Work Unit
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
    @Override
    public void editDataFromConfiguration(String alias, ArrayList<String> actName, ArrayList<String> description, ArrayList<LocalDate> createDate,
                                          boolean isRelease, ArrayList<Integer> tag, ArrayList<ArrayList<Integer>> cprs, ArrayList<ArrayList<Integer>> branchIndexs, ArrayList<Integer> branchIndicators,
                                          ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator,
                                          ArrayList<Integer> createdIndicator, ArrayList<Integer> tagIndicator, ArrayList<Integer> changeIndicator,
                                          String count, int countIndicator, boolean exist, int configId) {

        int instanceCount;
        try {
            instanceCount = InputController.isNumber(count, 1, 99, "Count");

            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(actName);
            ArrayList<LocalDate> date = InputController.checkDate(createDate);
            ArrayList<Integer> tagForManipulator = dataPreparer.prepareIndexForManipulator(tag);
            ArrayList<ArrayList<Integer>> branchesForManipulator = dataPreparer.prepareIndicesForManipulator(branchIndexs);
            ArrayList<ArrayList<Integer>> cprsForManipulator = dataPreparer.prepareIndicesForManipulator(cprs);

            ArrayList<ArrayList<Integer>> changesForManipulator = dataPreparer.prepareIndicesForManipulator(changeIndexs);

            dataManipulator.editDataInConfiguration(alias, nameForManipulator, description, date, isRelease,
                    cprsForManipulator, changesForManipulator, branchesForManipulator, branchIndicators, cprIndicators, nameIndicator, descriptionIndicator,
                    tagForManipulator, tagIndicator, createdIndicator, changeIndicator, instanceCount, countIndicator, exist, configId);

            int formIndex = identificatorCreater.getConfigurationFormIndex(configId);
            ConfigTable configTable = new ConfigTable(alias, "", formIndex, exist, configId);
            configTable.setExist(exist);
            segmentLists.updateListItem(SegmentType.Configuration, configId, configTable);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount, countIndicator);
            formController.setItemColor(formIndex, exist);
            mapperTableToObject.mapTableToConfiguration(branchesForManipulator, cprsForManipulator, changesForManipulator, tagForManipulator, alias, configId);


        } catch (NumberFormatException e) {
            //   Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }
    }

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
    @Override
    public void editDataFromVCSTag(String alias, ArrayList<String> name, ArrayList<String> description,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, VCSTagTable tagTable, boolean exist, int id) {
        ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
        ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(description);

        dataManipulator.editDataInVCSTag(alias, nameForManipulator, descriptionForManipulator, nameIndicator, descriptionIndicator, exist, id);
        tagTable.setExist(exist);
        tagTable.setAlias(alias);
        segmentLists.getVCSTag().add(tagTable);
    }

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
    @Override
    public void editDataFromCommit(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator,
                                   ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean release, String count, int countIndicator, boolean exist, int id) {

        int instanceCount = 0;
        try {
            instanceCount = InputController.isNumber(count, 1, 99, "Count");

            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(createDate);
            ArrayList<String> descriptionForManipulator = InputController.fillTextMapper(descriptions);
            dataManipulator.editDataInCommit(alias, nameForManipulator, nameIndicator, descriptionForManipulator, descriptionsIndicator, date,
                    createIndicator, release, instanceCount, countIndicator, exist, id);

            int formIndex = identificatorCreater.getCommitFormIndex(id);

            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount, countIndicator);
            formController.setItemColor(formIndex, exist);

        } catch (NumberFormatException e) {
            //    Alerts.showWrongNumberFormat("Instance count");
            e.printStackTrace();
        }

    }

    /**
     * Metoda pro zmenu souradnic elementu na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    @Override
    public void editCoordsInCommit(double x, double y, int id) {
        dataManipulator.editCoordinatesInCommit((int) x, (int) y, id);
    }

    /**
     * Metoda pro zmenu souradnic elementu na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    @Override
    public void editCoordsInCommitedConfiguration(double x, double y, int id) {
        dataManipulator.editCoordinatesInCommitedConfiguration((int) x, (int) y, id);
    }

    /**
     * Metoda pro zmenu souradnic elementu na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    @Override
    public void editCoordsInConfiguration(double x, double y, int id) {
        dataManipulator.editCoordinatesInConfiguration((int) x, (int) y, id);
    }

    /**
     * Metoda pro zmenu souradnic elementu na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    @Override
    public void editCoordsInArtifact(double x, double y, int id) {
        dataManipulator.editCoordinatesInArtifact((int) x, (int) y, id);
    }

    /**
     * Metoda pro zmenu souradnic elementu na platne
     *
     * @param x  double Xova souradnice
     * @param y  double Yova souradnice
     * @param id identifikator instance
     */
    @Override
    public void editCoordsInPerson(double x, double y, int id) {
        dataManipulator.editCoordinatesInPerson((int) x, (int) y, id);
    }


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
    @Override
    public void editDataFromCommitedConfiguration(String alias, ArrayList<String> name, ArrayList<Integer> nameIndicator, ArrayList<String> description, ArrayList<Integer> descriptionIndicator, ArrayList<LocalDate> created,
                                                  ArrayList<Integer> createdIndicator, ArrayList<LocalDate> committed, ArrayList<Integer> committedIndicator, String count, int countIndicator, boolean exist, int commitedConfigurationId) {

        int instanceCount = 0;
        try {
            instanceCount = InputController.isNumber(count, 1, 99, "Count");

            ArrayList<String> nameForManipulator = InputController.fillNameTextMapper(name);
            ArrayList<LocalDate> date = InputController.checkDate(committed);
            ArrayList<LocalDate> createdl = InputController.checkDate(created);

            dataManipulator.editDataInCommitedConfiguration(alias, nameForManipulator, nameIndicator, description, descriptionIndicator, createdl,
                    createdIndicator, date, committedIndicator, instanceCount, countIndicator, exist, commitedConfigurationId);

            int formIndex = identificatorCreater.getCommitedConfigurationFormIndex(commitedConfigurationId);
            formController.setNameToItem(formIndex, alias);
            formController.setItemInstanceCount(formIndex, instanceCount, countIndicator);
            formController.setItemColor(formIndex, exist);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}


