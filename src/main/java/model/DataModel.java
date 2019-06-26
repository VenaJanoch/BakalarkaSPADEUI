package model;

import SPADEPAC.*;
import XML.ProcessGenerator;
import interfaces.IDeleteDataModel;
import interfaces.IEditDataModel;
import interfaces.ISaveDataModel;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Trida uchovavajici instaci Project se vsemi datovymi seznamy. Dale obsahuje metody pro praci s dannymi seznamy
 *
 * @author Václav Janoch
 */
public class DataModel {

    /**
     * Globalni promenne tridy
     */
    private IEditDataModel editDataModel;
    private ISaveDataModel saveDataModel;
    private IDeleteDataModel deleteDataModel;
    private DataManipulator dataManipulator;
    private ProcessGenerator processGenerator;

    private Project project;
    private ObjectFactory objF;

    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     *
     * @param processGenerator instace tridy ProcessGenerator pro nacteni modelu z xml souboru
     */
    public DataModel(ProcessGenerator processGenerator) {
        this.objF = new ObjectFactory();
        this.project = objF.createProject();
        this.processGenerator = processGenerator;
        this.editDataModel = new EditDataModel(this);
        this.saveDataModel = new SaveDataModel(this, objF);
        this.deleteDataModel = new DeleteDataModel(this);
        this.dataManipulator = new DataManipulator(this);


    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Phase z indexu v seznamu entit
     *
     * @param id identifikator Phase
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getPhaseIndexInProject(int id) {
        List<Phase> items = project.getPhases();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Iteration z indexu v seznamu entit
     *
     * @param id identifikator Iteration
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getIterationIndexInProject(int id) {
        List<Iteration> items = project.getIterations();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Activity z indexu v seznamu entit
     *
     * @param id identifikator Activity
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getActivityIndexInProject(int id) {
        List<Activity> items = project.getActivities();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Milestone z indexu v seznamu entit
     *
     * @param id identifikator Milestone
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getMilestoneIndexInProject(int id) {
        List<Milestone> items = project.getMilestones();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Role type z indexu v seznamu entit
     *
     * @param id identifikator Role type
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getRoleTypeIndexInProject(int id) {
        List<RoleType> roleTypes = project.getRoleType();
        for (int i = 0; i < roleTypes.size(); i++) {

            if (roleTypes.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Severity z indexu v seznamu entit
     *
     * @param id identifikator Severity
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getSeverityIndexInProject(int id) {
        List<Severity> severities = project.getSeverity();
        for (int i = 0; i < severities.size(); i++) {

            if (severities.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Priority z indexu v seznamu entit
     *
     * @param id identifikator Priority
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getPriorityIndexInProject(int id) {
        List<Priority> item = project.getPriority();
        for (int i = 0; i < item.size(); i++) {

            if (item.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Status z indexu v seznamu entit
     *
     * @param id identifikator Phase
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getStatusIndexInProject(int id) {
        List<Status> items = project.getStatus();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Type z indexu v seznamu entit
     *
     * @param id identifikator Type
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getTypeIndexInProject(int id) {
        List<Type> items = project.getTypes();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Relation z indexu v seznamu entit
     *
     * @param id identifikator Relation
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getRelationIndexInProject(int id) {
        List<Relation> items = project.getRelation();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Resolution z indexu v seznamu entit
     *
     * @param id identifikator Resolution
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getResolutionIndexInProject(int id) {
        List<Resolution> items = project.getResolution();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Person z indexu v seznamu entit
     *
     * @param id identifikator Person
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getPersonIndexInProject(int id) {
        List<Person> items = project.getRoles();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Configuration Person z indexu v seznamu entit
     *
     * @param id identifikator Configuration Person
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getCPRIndexInProject(int id) {
        List<ConfigPersonRelation> items = project.getCpr();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu VCSTag z indexu v seznamu entit
     *
     * @param deleteId identifikator VCSTag
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getVCSTAgProjectIndex(int deleteId) {
        List<VCSTag> items = project.getVcsTag();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == deleteId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Configuration z indexu v seznamu entit
     *
     * @param id identifikator Configuration
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getConfigurationIndexInProject(int id) {
        List<Configuration> items = project.getConfiguration();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Artifact z indexu v seznamu entit
     *
     * @param id identifikator Configuration
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getArtifactIndexInProject(int id) {
        List<Artifact> items = project.getArtifacts();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Change z indexu v seznamu entit
     *
     * @param id identifikator Change
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getChangeIndexInProject(int id) {
        List<Change> items = project.getChanges();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Branch z indexu v seznamu entit
     *
     * @param id identifikator Branch
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getBranchIndexInProject(int id) {
        List<Branch> items = project.getBranches();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Metoda pro ziskani identifikatoru segmentu Criterion z indexu v seznamu entit
     *
     * @param id identifikator Criterion
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getCriterionIndexInProject(int id) {
        List<Criterion> items = project.getCriterions();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Metoda pro ziskani identifikatoru segmentu Work Unit z indexu v seznamu entit
     *
     * @param id identifikator Work Unit
     * @return index v seznamu, v pripade nenalezeni prvku -1
     */
    public int getWUIndexInProject(int id) {
        List<WorkUnit> items = project.getWorkUnits();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Metoda pro editaci elementu Configuration Person,
     * do instace ConfigPersonRelation jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInCPR
     *
     * @param cpr                   Instace ConfigPersonRelation pro editaci
     * @param alias                 alias prvku
     * @param nameForManipulator    zpracovany seznam parametu z pole name
     * @param nameIndicators        seznam indexu ukazatelu nerovnosti
     * @param description           zpracovany seznam parametu z pole name
     * @param descriptionIndicators seznam indexu ukazatelu nerovnosti
     * @param roleIndex             zpracovany seznam parametu z pole name
     * @param roleIndicators        seznam indexu ukazatelu nerovnosti
     * @param exist                 informace o existenci prvku v patternu
     */
    public void addDataToCPR(ConfigPersonRelation cpr, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                             ArrayList<String> description, ArrayList<Integer> descriptionIndicators, ArrayList<Integer> roleIndex
            , ArrayList<Integer> roleIndicators, boolean exist) {

        clearDataInCPR(cpr);
        cpr.setAlias(alias);
        cpr.getPersonIndex().addAll(roleIndex);
        cpr.getPersonIndicator().addAll(roleIndicators);
        cpr.getName().addAll(nameForManipulator);
        cpr.getNameIndicator().addAll(nameIndicators);
        cpr.getDescription().addAll(description);
        cpr.getDescriptionIndicator().addAll(descriptionIndicators);
        cpr.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci ConfigPersonRelation
     *
     * @param cpr instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInCPR(ConfigPersonRelation cpr) {
        cpr.getPersonIndicator().clear();
        cpr.getPersonIndex().clear();
        cpr.getNameIndicator().clear();
        cpr.getName().clear();
        cpr.getDescriptionIndicator().clear();
        cpr.getDescription().clear();
    }

    /**
     * Metoda pro editaci elementu Branch,
     * do instace Branch jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInBranch
     *
     * @param branch             Instace Branch pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicators     seznam indexu ukazatelu nerovnosti
     * @param isMain             informace o main vetvi
     * @param exist              informace o existenci prvku v patternu
     */
    public void addDataToBranch(Branch branch, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators, boolean isMain, boolean exist) {

        clearDataInBranch(branch);
        branch.setAlias(alias);
        branch.getName().addAll(nameForManipulator);
        branch.getNameIndicator().addAll(nameIndicators);
        branch.setIsMain(isMain);
        branch.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Branch
     *
     * @param branch instace Branch pro vymazani dat
     */
    private void clearDataInBranch(Branch branch) {
        branch.getName().clear();
        branch.getNameIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Artifact,
     * do instace Artifact jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInArtifact
     *
     * @param artifact              Instace Artifact pro editaci
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
     */
    public void addDataToArtifact(Artifact artifact, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicators,
                                  ArrayList<String> descForManipulator, ArrayList<Integer> descriptionIndicators,
                                  ArrayList<LocalDate> createdDate, ArrayList<Integer> dateIndicator, boolean isCreate,
                                  ArrayList<Integer> authorIndex, ArrayList<Integer> typeIndex, ArrayList<Integer> authorIndicator,
                                  ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator) {
        clearDataInArtifact(artifact);

        artifact.setAlias(alias);
        artifact.getName().addAll(nameForManipulator);
        artifact.getAuthorIndex().addAll(authorIndex);
        artifact.getCreated().addAll(convertDate(createdDate));
        artifact.getDescription().addAll(descForManipulator);
        artifact.setExist(isCreate);
        artifact.setCount(instanceCount);
        artifact.setCountIndicator(countIndicator);

        artifact.getMimeType().addAll(getItemFormEnumList(ArtifactClass.values(), typeIndex));
        artifact.getMimeTypeIndex().addAll(typeIndex);
        artifact.getNameIndicator().addAll(nameIndicators);
        artifact.getDescriptionIndicator().addAll(descriptionIndicators);
        artifact.getCreatedIndicator().addAll(dateIndicator);
        artifact.getMimeTypeIndicator().addAll(typeIndicator);
        artifact.getAuthorIndicator().addAll(authorIndicator);

    }

    /**
     * Metoda pro smazani dat v instanci Artifact
     *
     * @param artifact instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInArtifact(Artifact artifact) {
        artifact.getNameIndicator().clear();
        artifact.getName().clear();
        artifact.getAuthorIndicator().clear();
        artifact.getAuthorIndex().clear();
        artifact.getCreatedIndicator().clear();
        artifact.getCreated().clear();
        artifact.getDescriptionIndicator().clear();
        artifact.getDescription().clear();
        artifact.getMimeTypeIndicator().clear();
        artifact.getMimeType().clear();
        artifact.getMimeTypeIndex().clear();
    }

    private ArrayList<String> getItemFormEnumList(ArtifactClass[] values, ArrayList<Integer> typeIndex) {
        ArrayList<String> valueList = new ArrayList<>();
        for (int i = 0; i < typeIndex.size(); i++) {
            valueList.add(values[typeIndex.get(i)].name());
        }
        return valueList;
    }

    /**
     * Metoda pro editaci elementu Configuration Person,
     * do instace Change jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInChange
     *
     * @param change                 Instace Change pro editaci
     * @param alias                  alias prvku
     * @param nameForManipulator     zpracovany seznam parametu z pole name
     * @param nameIndicators         seznam indexu ukazatelu nerovnosti
     * @param descForManipulator     zpracovany seznam parametu z pole description
     * @param descIndicator          seznam indexu ukazatelu nerovnosti
     * @param artifactForManipulator zpracovany seznam parametu z pole artifact
     * @param selected               informace o existenci prvku v patternu
     */
    public void addDataToChange(Change change, String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator, ArrayList<Integer> artifactForManipulator,
                                ArrayList<Integer> nameIndicators, ArrayList<Integer> descIndicator, boolean selected) {
        clearDataInChange(change);
        change.setAlias(alias);
        change.getName().addAll(nameForManipulator);
        change.getDescription().addAll(descForManipulator);
        change.getNameIndicator().addAll(nameIndicators);
        change.getDescriptionIndicator().addAll(descIndicator);
        change.getArtifactIndex().addAll(artifactForManipulator);
        change.setExist(selected);

    }

    /**
     * Metoda pro smazani dat v instanci Change
     *
     * @param change instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInChange(Change change) {
        change.getNameIndicator().clear();
        change.getName().clear();
        change.getDescriptionIndicator().clear();
        change.getDescription().clear();
    }

    /**
     * Metoda pro editaci segmentu Phase,
     * do instace Phase jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInPhase
     *
     * @param phase              Instace Phase pro editaci
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
     */
    public void addDataToPhase(Phase phase, String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<String> desc,
                               ArrayList<Integer> confIndex, ArrayList<Integer> milestoneIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                               ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                               ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, ArrayList<Integer> milestoneIndicator, boolean exist) {
        clearDataInPhase(phase);
        phase.setAlias(alias);
        phase.getName().addAll(actName);
        phase.getDescription().addAll(desc);
        phase.getConfiguration().addAll(confIndex);
        phase.getMilestoneIndex().addAll(milestoneIndex);
        phase.getEndDate().addAll(convertDate(endDateL));
        for (List<Integer> list : workUnitIndexList) {
            phase.getWorkUnits().add(addWorkUnitList(list));
        }
        phase.getNameIndicator().addAll(nameIndicator);
        phase.getDescriptionIndicator().addAll(descIndicator);
        phase.getConfigurationIndicator().addAll(confIndicator);
        phase.getEndDateIndicator().addAll(endDateIndicator);
        phase.getMilestoneIndicator().addAll(milestoneIndicator);
        phase.getWorkUnitsIndicator().addAll(workUnitIndicators);
        phase.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Phase
     *
     * @param phase instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInPhase(Phase phase) {

        phase.getNameIndicator().clear();
        phase.getName().clear();
        phase.getDescriptionIndicator().clear();
        phase.getDescription().clear();
        phase.getConfigurationIndicator().clear();
        phase.getConfiguration().clear();
        phase.getMilestoneIndicator().clear();
        phase.getMilestoneIndex().clear();
        phase.getEndDateIndicator().clear();
        phase.getEndDate().clear();
        phase.getWorkUnitsIndicator().clear();
        phase.getWorkUnits().clear();
    }

    /**
     * Metoda pro editaci segmentu Iteration
     * do instace Iteration jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInIteration
     *
     * @param iteration          Instace Iteration pro editaci
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
     */
    public void addDataToIteration(Iteration iteration, String alias, ArrayList<String> actName, ArrayList<LocalDate> endDateL, ArrayList<LocalDate> startDateL, ArrayList<String> desc,
                                   ArrayList<Integer> confIndex, ArrayList<ArrayList<Integer>> workUnitIndexList,
                                   ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> endDateIndicator,
                                   ArrayList<Integer> startDateIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> confIndicator, boolean exist) {
        clearDataInIteration(iteration);
        iteration.setAlias(alias);
        iteration.getName().addAll(actName);
        iteration.getDescription().addAll(desc);
        iteration.getEndDate().addAll(convertDate(endDateL));
        iteration.getStartDate().addAll(convertDate(startDateL));
        iteration.getConfiguration().addAll(confIndex);
        for (List<Integer> list : workUnitIndexList) {
            iteration.getWorkUnits().add(addWorkUnitList(list));
        }

        iteration.getNameIndicator().addAll(nameIndicator);
        iteration.getDescriptionIndicator().addAll(descIndicator);
        iteration.getEndDateIndicator().addAll(endDateIndicator);
        iteration.getStartDateIndicator().addAll(startDateIndicator);
        iteration.getConfigurationIndicator().addAll(confIndicator);

        iteration.getWorkUnitsIndicator().addAll(workUnitIndicators);
        iteration.setExist(exist);

    }

    /**
     * Metoda pro smazani dat v instanci Iteration
     *
     * @param iteration instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInIteration(Iteration iteration) {
        iteration.getNameIndicator().clear();
        iteration.getName().clear();
        iteration.getDescriptionIndicator().clear();
        iteration.getDescription().clear();
        iteration.getEndDateIndicator().clear();
        iteration.getEndDate().clear();
        iteration.getStartDateIndicator().clear();
        iteration.getStartDate().clear();
        iteration.getConfigurationIndicator().clear();
        iteration.getConfiguration().clear();
        iteration.getWorkUnitsIndicator().clear();
        iteration.getWorkUnits().clear();
    }

    /**
     * Metoda pro editaci segmentu Committed Configuration
     * do instace Committed Configuration jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInCommitedConfiguration
     *
     * @param alias                 alias prvku
     * @param nameForManipulator    zpracovany seznam parametu z pole name
     * @param nameIndicator         seznam indexu ukazatelu nerovnosti
     * @param descriptions          zpracovany seznam parametu z pole description
     * @param descriptionsIndicator seznam indexu ukazatelu nerovnosti
     * @param createDate            zpracovany seznam parametu z pole created
     * @param dateIndicator         seznam indexu ukazatelu nerovnosti
     * @param exist                 informace o existenci prvku v patternu
     * @param instanceCount         pocet instanci prvku
     * @param countIndicator        seznam indexu ukazatelu nerovnosti
     */
    public void addDataToCommitedConfiguration(CommitedConfiguration commitedConfiguration, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                               ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator, ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator,
                                               ArrayList<LocalDate> startDate, ArrayList<Integer> dateIndicator, int instanceCount, int countIndicator, boolean exist) {

        clearDataInCommitedConfiguration(commitedConfiguration);
        commitedConfiguration.setAlias(alias);
        commitedConfiguration.getName().addAll(nameForManipulator);
        commitedConfiguration.getNameIndicator().addAll(nameIndicator);
        commitedConfiguration.getCommitedDayIndicator().addAll(dateIndicator);
        commitedConfiguration.getCommitedDay().addAll(convertDate(startDate));
        commitedConfiguration.getCreated().addAll(convertDate(createDate));
        commitedConfiguration.getCreatedIndicator().addAll(createIndicator);
        commitedConfiguration.getDescription().addAll(descriptions);
        commitedConfiguration.getDescriptionIndicator().addAll(descriptionsIndicator);
        commitedConfiguration.setCount(instanceCount);
        commitedConfiguration.setCountIndicator(countIndicator);

        commitedConfiguration.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Committed Configuration
     *
     * @param commitedConfiguration instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInCommitedConfiguration(CommitedConfiguration commitedConfiguration) {
        commitedConfiguration.getNameIndicator().clear();
        commitedConfiguration.getName().clear();
        commitedConfiguration.getCommitedDayIndicator().clear();
        commitedConfiguration.getCommitedDay().clear();
        commitedConfiguration.getDescriptionIndicator().clear();
        commitedConfiguration.getDescription().clear();
        commitedConfiguration.getCreatedIndicator().clear();
        commitedConfiguration.getCreated().clear();
    }


    public WorkUnitList addWorkUnitList(List<Integer> list) {
        WorkUnitList wulist = objF.createWorkUnitList();
        wulist.getWorkUnits().addAll(list);
        return wulist;
    }


    /**
     * Metoda pro editaci segmentu Activity
     * do instace Activity jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInActivity
     *
     * @param activity                  Instace Activity pro editaci
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param endDate                   zpracovany seznam parametu z pole end date
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param workUnitIndicators        seznam indexu ukazatelu nerovnosti
     * @param nameIndicators            seznam indexu ukazatelu nerovnosti
     * @param endDateIndicators         seznam indexu ukazatelu nerovnosti
     * @param descIndicators            seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v patternu
     */
    public void addDataToActivity(Activity activity, String alias, List<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                  ArrayList<ArrayList<Integer>> setOfItemOnCanvas, ArrayList<Integer> nameIndicators,
                                  ArrayList<Integer> descIndicators, ArrayList<Integer> workUnitIndicators, ArrayList<LocalDate> endDate, ArrayList<Integer> endDateIndicators, boolean exist) {

        clearDataInActivity(activity);
        activity.setAlias(alias);
        activity.getDescription().addAll(descriptionForManipulator);
        activity.getName().addAll(nameForManipulator);
        activity.getEndDate().addAll(convertDate(endDate));
        for (List<Integer> list : setOfItemOnCanvas) {
            activity.getWorkUnits().add(addWorkUnitList(list));
        }
        activity.getNameIndicator().addAll(nameIndicators);
        activity.getDescriptionIndicator().addAll(descIndicators);
        activity.getWorkUnitsIndicator().addAll(workUnitIndicators);
        activity.getEndDateIndicator().addAll(endDateIndicators);
        activity.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Activity
     *
     * @param activity instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInActivity(Activity activity) {
        activity.getNameIndicator().clear();
        activity.getName().clear();
        activity.getDescription().clear();
        activity.getWorkUnits().clear();
        activity.getDescriptionIndicator().clear();
        activity.getEndDateIndicator().clear();
        activity.getEndDate().clear();
        activity.getWorkUnitsIndicator().clear();
    }


    /**
     * Metoda pro editaci segmentu Work Unit
     * do instace Work Unit jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInWork Unit
     *
     * @param workUnit                   Instace Work Unit pro editaci
     * @param alias                      alias prvku
     * @param nameForManipulator         zpracovany seznam parametu z pole name
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
     */
    public void addDataToWorkUnit(WorkUnit workUnit, String alias, ArrayList<Integer> progress, ArrayList<Integer> progressIndicator, List<String> nameForManipulator, List<String> description, List<String> categoryForManipulator,
                                  ArrayList<Integer> assigneIndex, ArrayList<Integer> authorIndex, ArrayList<Integer> priorityIndex, ArrayList<Integer> severityIndex,
                                  ArrayList<Integer> typeIndex, ArrayList<Integer> resolutionIndex, ArrayList<Integer> statusIndex,
                                  ArrayList<Double> estimateForDataManipulator, List<Integer> nameIndicator, List<Integer> descriptionIndicator, List<Integer> categoryIndicator,
                                  ArrayList<Integer> assigneIndicator, ArrayList<Integer> authorIndicator, ArrayList<Integer> priorityIndicator, ArrayList<Integer> severityIndicator,
                                  ArrayList<Integer> typeIndicator, ArrayList<Integer> resolutionIndicator, ArrayList<Integer> statusIndicator,
                                  ArrayList<Integer> estimateIndicator, ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean isExist, ArrayList<Integer> relations,
                                  ArrayList<ArrayList<Integer>> workUnits) {
        clearDataInWorkUnit(workUnit);
        workUnit.setAlias(alias);
        workUnit.getProgress().addAll(progress);
        workUnit.getProgressIndicator().addAll(progressIndicator);
        workUnit.getCreated().addAll(convertDate(createDate));
        workUnit.getCreatedIndicator().addAll(createIndicator);
        workUnit.getAssigneeIndex().addAll(assigneIndex);
        workUnit.getAuthorIndex().addAll(authorIndex);
        workUnit.getCategory().addAll(categoryForManipulator);
        workUnit.getDescription().addAll(description);
        workUnit.getEstimatedTime().addAll(estimateForDataManipulator);
        workUnit.setExist(isExist);
        workUnit.getName().addAll(nameForManipulator);
        workUnit.getPriorityIndex().addAll(priorityIndex);
        workUnit.getSeverityIndex().addAll(severityIndex);
        workUnit.getTypeIndex().addAll(typeIndex);
        workUnit.getStatusIndex().addAll(statusIndex);
        workUnit.getResolutionIndex().addAll(resolutionIndex);
        workUnit.getRelationIndex().addAll(relations);
        for (List<Integer> list : workUnits) {
            workUnit.getWorkUnits().add(addWorkUnitList(list));
        }
        workUnit.getAssigneeIndicator().addAll(assigneIndicator);
        workUnit.getAuthorIIndicator().addAll(authorIndicator);
        workUnit.getCategoryIndicator().addAll(categoryIndicator);
        workUnit.getDescriptionIndicator().addAll(descriptionIndicator);
        workUnit.getEstimatedTimeIndicator().addAll(estimateIndicator);
        workUnit.getNameIndicator().addAll(nameIndicator);
        workUnit.getPriorityIndicator().addAll(priorityIndicator);
        workUnit.getSeverityIndicator().addAll(severityIndicator);
        workUnit.getTypeIndicator().addAll(typeIndicator);
        workUnit.getStatusIndicator().addAll(statusIndicator);
        workUnit.getResolutionIndicator().addAll(resolutionIndicator);
    }

    /**
     * Metoda pro smazani dat v instanci Work Unit
     *
     * @param workUnit instace ConfigPersonRelation pro vymazani dat
     */
    private void clearDataInWorkUnit(WorkUnit workUnit) {
        workUnit.getCreatedIndicator().clear();
        workUnit.getCreated().clear();
        workUnit.getAssigneeIndex().clear();
        workUnit.getAuthorIndex().clear();
        workUnit.getCategory().clear();
        workUnit.getDescription().clear();
        workUnit.getEstimatedTime().clear();
        ;
        workUnit.getName().clear();
        workUnit.getPriorityIndex().clear();
        workUnit.getSeverityIndex().clear();
        workUnit.getTypeIndex().clear();
        workUnit.getStatusIndex().clear();
        workUnit.getResolutionIndex().clear();
        workUnit.getRelationIndex().clear();
        workUnit.getWorkUnits().clear();

        workUnit.getProgress().clear();
        workUnit.getProgressIndicator().clear();

        workUnit.getAssigneeIndicator().clear();
        workUnit.getAuthorIIndicator().clear();
        workUnit.getCategoryIndicator().clear();
        workUnit.getDescriptionIndicator().clear();
        workUnit.getEstimatedTimeIndicator().clear();
        workUnit.getNameIndicator().clear();
        workUnit.getPriorityIndicator().clear();
        workUnit.getSeverityIndicator().clear();
        workUnit.getTypeIndicator().clear();
        workUnit.getStatusIndicator().clear();
        workUnit.getResolutionIndicator().clear();

    }

    /**
     * Metoda pro editaci segmentu Configuration
     * do instace  Configuration jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInConfiguration
     *
     * @param alias                alias prvku
     * @param configuration        instance Configuration pro editaci
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
     * @param branchIndicator      seznam indexu ukazatelu nerovnosti
     * @param instanceCount        pocet instanci prvku
     * @param countIndicator       seznam indexu ukazatelu nerovnosti
     * @param exist informace o existenci prvku v patternu
     */
    public void addDataToConfiguration(Configuration configuration, String alias, ArrayList<String> actName, ArrayList<String> description, ArrayList<LocalDate> createDate,
                                       boolean isRelease, ArrayList<ArrayList<Integer>> cprs,
                                       ArrayList<ArrayList<Integer>> changeIndexs, ArrayList<ArrayList<Integer>> branchIndexs,
                                       ArrayList<Integer> cprIndicators, ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, ArrayList<Integer> tag, ArrayList<Integer> tagIndicator, ArrayList<Integer> createdIndicator,
                                       ArrayList<Integer> changeIndicator, ArrayList<Integer> branchIndicator, int instanceCount, int countIndicator, boolean exist) {
        clearDataInConfiguration(configuration);
        configuration.setAlias(alias);
        configuration.getName().addAll(actName);
        configuration.getDescription().addAll(description);
        configuration.getDescriptionIndicator().addAll(descriptionIndicator);
        configuration.getCreated().addAll(convertDate(createDate));
        configuration.getNameIndicator().addAll(nameIndicator);
        configuration.getCreatedIndicator().addAll(createdIndicator);
        configuration.getCPRsIndicator().addAll(cprIndicators);
        configuration.getChangesIndicator().addAll(changeIndicator);
        configuration.getBranchIndicator().addAll(branchIndicator);
        configuration.setIsRelease(isRelease);
        configuration.setCount(instanceCount);
        configuration.setCountIndicator(countIndicator);
        configuration.getTagIndex().addAll(tag);
        configuration.getTagsIndicator().addAll(tagIndicator);
        configuration.setExist(exist);
        for (List<Integer> list : cprs) {
            CPRSList cprList = objF.createCPRSList();
            cprList.getCPRs().addAll(list);
            configuration.getCPRsIndexs().add(cprList);
        }

        for (List<Integer> list : branchIndexs) {
            BranchList branchList = objF.createBranchList();
            branchList.getBranches().addAll(list);
            configuration.getBranchIndexs().add(branchList);
        }

        for (List<Integer> list : changeIndexs) {
            ChangeList cprList = objF.createChangeList();
            cprList.getChanges().addAll(list);
            configuration.getChangesIndexs().add(cprList);
        }
    }

    /**
     * Metoda pro smazani dat v instanci Configuration
     *
     * @param configuration instace Configuration pro vymazani dat
     */
    private void clearDataInConfiguration(Configuration configuration) {
        configuration.getCPRsIndexs().clear();
        configuration.getChangesIndexs().clear();
        configuration.getName().clear();
        configuration.getCreated().clear();
        configuration.getAuthorIndex().clear();
        configuration.getNameIndicator().clear();
        configuration.getCreatedIndicator().clear();
        configuration.getAuthorIndicator().clear();
        configuration.getCPRsIndicator().clear();
        configuration.getChangesIndicator().clear();
        configuration.getBranchIndicator().clear();
        configuration.getBranchIndexs().clear();
        configuration.getDescription().clear();
        configuration.getDescriptionIndicator().clear();
        configuration.getTagIndex().clear();
        configuration.getTagsIndicator().clear();
    }


    /**
     * Metoda pro editaci elementu Criterion,
     * do instace Criterion jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInCriterion
     *
     * @param criterion          Instace Criterion pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v patternu
     */
    public void addDataToCriterion(Criterion criterion, String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, boolean exist) {
        clearDataInCriterion(criterion);
        criterion.setAlias(alias);
        criterion.getName().addAll(nameForManipulator);
        criterion.getDescription().addAll(descForManipulator);
        criterion.getNameIndicator().addAll(nameIndicator);
        criterion.getDescriptionIndicator().addAll(descIndicator);
        criterion.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Criterion
     *
     * @param criterion instace Criterion pro vymazani dat
     */
    private void clearDataInCriterion(Criterion criterion) {
        criterion.getDescriptionIndicator().clear();
        criterion.getDescription().clear();
        criterion.getNameIndicator().clear();
        criterion.getName().clear();
    }

    /**
     * Metoda pro editaci elementu Priority,
     * do instace Priority jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInPriority
     *
     * @param priority           Instace Priority pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToPriority(Priority priority, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {

        clearDataInPriority(priority);
        priority.setAlias(alias);
        priority.getName().addAll(nameForManipulator);
        priority.getNameIndicator().addAll(nameIndicator);
        priority.getPriorityClass().addAll(classString);
        priority.getPriorityClassIndex().addAll(classST);
        priority.getPrioritySuperClass().addAll(superSting);
        priority.getPrioritySuperClassIndex().addAll(superST);
        priority.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Priority
     *
     * @param priority instace Priority pro vymazani dat
     */
    private void clearDataInPriority(Priority priority) {
        priority.getNameIndicator().clear();
        priority.getName().clear();
        priority.getPrioritySuperClass().clear();
        priority.getPrioritySuperClassIndex().clear();
        priority.getPriorityClass().clear();
        priority.getPriorityClassIndex().clear();
        priority.getPriorityClassIndicator().clear();
        priority.getPriorityClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Severity,
     * do instace Severity jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInSeverity
     *
     * @param severity           Instace Severity pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToSeverity(Severity severity, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInSeverity(severity);
        severity.setAlias(alias);
        severity.getName().addAll(nameForManipulator);
        severity.getNameIndicator().addAll(nameIndicator);
        severity.getSeverityClass().addAll(classString);
        severity.getSeverityClassIndex().addAll(classST);
        severity.getSeveritySuperClass().addAll(superSting);
        severity.getSeveritySuperClassIndex().addAll(superST);
        severity.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Severity
     *
     * @param severity instace Severity pro vymazani dat
     */
    private void clearDataInSeverity(Severity severity) {
        severity.getNameIndicator().clear();
        severity.getName().clear();
        severity.getSeveritySuperClass().clear();
        severity.getSeveritySuperClassIndex().clear();
        severity.getSeverityClass().clear();
        severity.getSeverityClassIndex().clear();
        severity.getSeverityClassIndicator().clear();
        severity.getSeverityClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Role Type,
     * do instace Role Type jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInPerson Type
     *
     * @param roleType           Instace Role Type pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToRoleType(RoleType roleType, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descForManipulator, ArrayList<Integer> descIndicator,
                                  ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInRoleType(roleType);
        roleType.setAlias(alias);
        roleType.getDescriptionIndicator().addAll(descIndicator);
        roleType.getDescription().addAll(descForManipulator);
        roleType.getName().addAll(nameForManipulator);
        roleType.getNameIndicator().addAll(nameIndicator);
        roleType.getRoleTypeClass().addAll(classString);
        roleType.getRoleTypeClassIndex().addAll(classST);
        roleType.getRoleTypeSuperClass().addAll(superSting);
        roleType.getRoleTypeSuperClassIndex().addAll(superST);
        roleType.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Role Type
     *
     * @param roleType instace Role Type pro vymazani dat
     */
    private void clearDataInRoleType(RoleType roleType) {
        roleType.getDescription().clear();
        roleType.getDescriptionIndicator().clear();
        roleType.getNameIndicator().clear();
        roleType.getName().clear();
        roleType.getRoleTypeSuperClass().clear();
        roleType.getRoleTypeSuperClassIndex().clear();
        roleType.getRoleTypeClass().clear();
        roleType.getRoleTypeClassIndex().clear();
        roleType.getRoleTypeClassIndicator().clear();
        roleType.getRoleTypeClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Status,
     * do instace Status jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInStatus
     *
     * @param status             Instace Status pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToStatus(Status status, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInStatus(status);
        status.setAlias(alias);
        status.getName().addAll(nameForManipulator);
        status.getNameIndicator().addAll(nameIndicator);
        status.getStatusClass().addAll(classString);
        status.getStatusClassIndex().addAll(classST);
        status.getStatusSuperClass().addAll(superSting);
        status.getStatusSuperClassIndex().addAll(superST);
        status.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Status
     *
     * @param status instace Status pro vymazani dat
     */
    private void clearDataInStatus(Status status) {
        status.getNameIndicator().clear();
        status.getName().clear();
        status.getStatusSuperClass().clear();
        status.getStatusSuperClassIndex().clear();
        status.getStatusClass().clear();
        status.getStatusClassIndex().clear();
        status.getStatusClassIndicator().clear();
        status.getStatusClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Type,
     * do instace Type jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInType
     *
     * @param type               Instace Type pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToType(Type type, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                              ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInType(type);
        type.setAlias(alias);
        type.getName().addAll(nameForManipulator);
        type.getNameIndicator().addAll(nameIndicator);
        type.getTypeClass().addAll(classString);
        type.getTypeClassIndex().addAll(classST);
        type.getTypeSuperClass().addAll(superSting);
        type.getTypeSuperClassIndex().addAll(superST);
        type.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Type
     *
     * @param type instace Type pro vymazani dat
     */
    private void clearDataInType(Type type) {
        type.getNameIndicator().clear();
        type.getName().clear();
        type.getTypeSuperClass().clear();
        type.getTypeSuperClassIndex().clear();
        type.getTypeClass().clear();
        type.getTypeClassIndex().clear();
        type.getTypeClassIndicator().clear();
        type.getTypeClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Relation,
     * do instace Relation jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInRelation
     *
     * @param relation           Instace Relation pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToRelation(Relation relation, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                  ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInRelation(relation);
        relation.setAlias(alias);
        relation.getName().addAll(nameForManipulator);
        relation.getNameIndicator().addAll(nameIndicator);
        relation.getRelationClass().addAll(classString);
        relation.getRelationClassIndex().addAll(classST);
        relation.getRelationSuperClass().addAll(superSting);
        relation.getRelationSuperClassIndex().addAll(superST);
        relation.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Relation
     *
     * @param relation instace Relation pro vymazani dat
     */
    private void clearDataInRelation(Relation relation) {
        relation.getNameIndicator().clear();
        relation.getName().clear();
        relation.getRelationSuperClass().clear();
        relation.getRelationSuperClassIndex().clear();
        relation.getRelationClass().clear();
        relation.getRelationClassIndex().clear();
        relation.getRelationClassIndicator().clear();
        relation.getRelationClassIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Resolution,
     * do instace Resolution jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInResolution
     *
     * @param resolution         Instace Resolution pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param classST            index v seznamu typu trid
     * @param superST            index v seznamu typu supertrid
     * @param classString        nazev  vybrane tridy
     * @param superSting         nazev vybrane supertridy
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToResolution(Resolution resolution, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator,
                                    ArrayList<Integer> classST, ArrayList<Integer> superST, ArrayList<String> classString, ArrayList<String> superSting, boolean exist) {
        clearDataInResolution(resolution);
        resolution.setAlias(alias);
        resolution.getName().addAll(nameForManipulator);
        resolution.getNameIndicator().addAll(nameIndicator);
        resolution.getResolutionClass().addAll(classString);
        resolution.getResolutionClassIndex().addAll(classST);
        resolution.getResolutionSuperClass().addAll(superSting);
        resolution.getResolutionSuperClassIndex().addAll(superST);
        resolution.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci Resolution
     *
     * @param resolution instace Resolution pro vymazani dat
     */
    private void clearDataInResolution(Resolution resolution) {
        resolution.getNameIndicator().clear();
        resolution.getName().clear();
        resolution.getResolutionSuperClass().clear();
        resolution.getResolutionSuperClassIndex().clear();
        resolution.getResolutionClass().clear();
        resolution.getResolutionClassIndex().clear();
        resolution.getResolutionClassIndicator().clear();
        resolution.getResolutionClassIndicator().clear();
    }


    /**
     * Metoda pro editaci elementu Person,
     * do instace Person jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInPerson
     *
     * @param role               Instace Person pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param type               zpracovany seznam parametu z pole Role Type
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param typeIndicator      seznam indexu ukazatelu nerovnosti
     * @param instanceCount      informace o existenci prvku v modelu
     * @param countIndicator     seznam indexu ukazatelu nerovnosti
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDatToPerson(Person role, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> type,
                               ArrayList<Integer> nameIndicator, ArrayList<Integer> typeIndicator, int instanceCount, int countIndicator, boolean exist) {
        clearDataInPerson(role);
        role.getName().addAll(nameForManipulator);
        role.getNameIndicator().addAll(nameIndicator);
        role.getTypeIndicator().addAll(typeIndicator);
        role.setExist(exist);
        role.setAlias(alias);
        role.setCount(instanceCount);
        role.setCountIndicator(countIndicator);
        role.getType().addAll(type);
    }

    /**
     * Metoda pro smazani dat v instanci Person
     *
     * @param role instace Person pro vymazani dat
     */
    private void clearDataInPerson(Person role) {
        role.getName().clear();
        role.getNameIndicator().clear();
        role.getTypeIndicator().clear();
        role.getType().clear();
    }

    /**
     * Metoda pro editaci projektu,
     * do instace Project jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInProject
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
    public void addDataToProject(ArrayList<String> nameForManipulator, ArrayList<LocalDate> startDate1, ArrayList<LocalDate> endDate1, ArrayList<String> descriptionForManipulator, ArrayList<ArrayList<Integer>> workUnitsForManipulator,
                                 ArrayList<Integer> workUnitIndicators, ArrayList<Integer> nameIndicators, ArrayList<Integer> date1Indicators,
                                 ArrayList<Integer> date2Indicators, ArrayList<Integer> descIndicators) {

        clearDataInProject();

        project.getName().addAll(nameForManipulator);
        project.getDescription().addAll(descriptionForManipulator);
        project.getEndDate().addAll(convertDate(endDate1));
        project.getStartDate().addAll(convertDate(startDate1));

        for (List<Integer> list : workUnitsForManipulator) {
            project.getWorkUnitIndexs().add(addWorkUnitList(list));
        }

        project.getNameIndicator().addAll(nameIndicators);
        project.getDescriptionIndicator().addAll(descIndicators);
        project.getEndDateIndicator().addAll(date2Indicators);
        project.getStartDateIndicator().addAll(date1Indicators);
        project.getWorkUnitsIndicator().addAll(workUnitIndicators);

    }

    /**
     * Metoda pro smazani dat v instanci Projektu
     */
    private void clearDataInProject() {
        project.getName().clear();
        project.getDescription().clear();
        project.getEndDate().clear();
        project.getStartDate().clear();
        project.getWorkUnitIndexs().clear();

        project.getNameIndicator().clear();
        project.getDescriptionIndicator().clear();
        project.getEndDateIndicator().clear();
        project.getStartDateIndicator().clear();
        project.getWorkUnitsIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu Milestone,
     * do instace Milestone jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInMilestone
     *
     * @param milestone          Instace Milestone pro editaci
     * @param alias              alias prvku
     * @param nameForManipulator zpracovany seznam parametu z pole name
     * @param descForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator      seznam indexu ukazatelu nerovnosti
     * @param descIndicator      seznam indexu ukazatelu nerovnosti
     * @param criterionIndicator seznam indexu ukazatelu nerovnosti
     * @param criterionIndex     zpracovany seznam parametu z pole name
     * @param exist              informace o existenci prvku v modelu
     */
    public void addDataToMilestone(Milestone milestone, String alias, ArrayList<String> nameForManipulator, ArrayList<String> descForManipulator,
                                   ArrayList<Integer> nameIndicator, ArrayList<Integer> descIndicator, ArrayList<Integer> criterionIndicator,
                                   ArrayList<ArrayList<Integer>> criterionIndex, boolean exist) {
        clearDataInMilestone(milestone);
        milestone.setAlias(alias);
        milestone.getCriteriaIndexs().clear();
        milestone.setExist(exist);
        for (List<Integer> list : criterionIndex) {
            CriterionList cprList = objF.createCriterionList();
            cprList.getCriterions().addAll(list);
            milestone.getCriteriaIndexs().add(cprList);
        }

        milestone.getName().addAll(nameForManipulator);
        milestone.getDescription().addAll(descForManipulator);
        milestone.getCriteriaIndicator().addAll(criterionIndicator);
        milestone.getNameIndicator().addAll(nameIndicator);
        milestone.getDescriptionIndicator().addAll(descIndicator);
    }

    /**
     * Metoda pro smazani dat v instanci Milestone
     *
     * @param milestone instace Milestone pro vymazani dat
     */
    private void clearDataInMilestone(Milestone milestone) {
        milestone.getCriteriaIndexs().clear();
        milestone.getName().clear();
        milestone.getDescription().clear();
        milestone.getCriteriaIndicator().clear();
        milestone.getNameIndicator().clear();
        milestone.getDescriptionIndicator().clear();
    }

    /**
     * Metoda pro editaci elementu VCSTag,
     * do instace VCSTag jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInVCSTag
     *
     * @param tag                       Instace VCSTag pro editaci
     * @param alias                     alias prvku
     * @param nameForManipulator        zpracovany seznam parametu z pole name
     * @param descriptionForManipulator zpracovany seznam parametu z pole description
     * @param nameIndicator             seznam indexu ukazatelu nerovnosti
     * @param descriptionIndicator      seznam indexu ukazatelu nerovnosti
     * @param exist                     informace o existenci prvku v modelu
     */
    public void addDataToVCSTag(VCSTag tag, String alias, ArrayList<String> nameForManipulator, ArrayList<String> descriptionForManipulator,
                                ArrayList<Integer> nameIndicator, ArrayList<Integer> descriptionIndicator, boolean exist) {
        clearDataInVCSTag(tag);
        tag.setAlias(alias);
        tag.getName().addAll(nameForManipulator);
        tag.getNameIndicator().addAll(nameIndicator);
        tag.getDescription().addAll(descriptionForManipulator);
        tag.getDescriptionIndicator().addAll(descriptionIndicator);
        tag.setExist(exist);
    }

    /**
     * Metoda pro smazani dat v instanci VCSTag
     *
     * @param tag instace VCSTag pro vymazani dat
     */
    private void clearDataInVCSTag(VCSTag tag) {
        tag.getNameIndicator().clear();
        tag.getName().clear();
        tag.getDescriptionIndicator().clear();
        tag.getDescription().clear();

    }

    /**
     * Metoda pro editaci elementu Commit,
     * do instace Commit jsou pridany upravene parametry z editacniho panelu.
     * Stara data jsou smazana pomoci metody clearDataInCommit
     *
     * @param commit                Instace Commit pro editaci
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
     */
    public void addDataToCommit(Commit commit, String alias, ArrayList<String> nameForManipulator, ArrayList<Integer> nameIndicator, ArrayList<String> descriptions, ArrayList<Integer> descriptionsIndicator,
                                ArrayList<LocalDate> createDate, ArrayList<Integer> createIndicator, boolean release, int instanceCount, int countIndicator, boolean exist) {
        clearDataInCommit(commit);
        commit.setRelease(release);
        commit.setAlias(alias);
        commit.getName().addAll(nameForManipulator);
        commit.getNameIndicator().addAll(nameIndicator);
        commit.getCreated().addAll(convertDate(createDate));
        commit.getCreatedIndicator().addAll(createIndicator);
        commit.getDescription().addAll(descriptions);
        commit.getDescriptionIndicator().addAll(descriptionsIndicator);
        commit.setCount(instanceCount);
        commit.setCountIndicator(countIndicator);
        commit.setExist(exist);
    }

    /**
     * Metoda pro nastaveni souradnic do Commitu
     *
     * @param coordinates instance souradnic
     * @param commit      Commit pro prirazeni souradnic
     */
    public void setCoordinatesToCommit(Coordinates coordinates, Commit commit) {
        commit.setCoordinates(coordinates);
    }

    /**
     * Metoda pro nastaveni souradnic do Commited Configuration
     *
     * @param coordinates           instance souradnic
     * @param commitedConfiguration Committed Configuration pro prirazeni souradnic
     */
    public void setCoordinatesToCommitedConfiguration(Coordinates coordinates, CommitedConfiguration commitedConfiguration) {
        commitedConfiguration.setCoordinates(coordinates);
    }

    /**
     * Metoda pro nastaveni souradnic do Configuration
     *
     * @param coordinates   instance souradnic
     * @param configuration Configuration pro prirazeni souradnic
     */
    public void setCoordinatesToConfiguration(Coordinates coordinates, Configuration configuration) {
        configuration.setCoordinates(coordinates);
    }

    /**
     * Metoda pro nastaveni souradnic do Artifact
     *
     * @param coordinates instance souradnic
     * @param artifact    Artifact pro prirazeni souradnic
     */
    public void setCoordinatesToArtifact(Coordinates coordinates, Artifact artifact) {
        artifact.setCoordinates(coordinates);
    }

    /**
     * Metoda pro nastaveni souradnic do Person
     *
     * @param coordinates instance souradnic
     * @param role        Person pro prirazeni souradnic
     */
    public void setCoordinatesToPerson(Coordinates coordinates, Person role) {
        role.setCoordinates(coordinates);
    }

    /**
     * Metoda pro smazani dat v instanci Commit
     *
     * @param commit instace Commit pro vymazani dat
     */
    private void clearDataInCommit(Commit commit) {
        commit.getNameIndicator().clear();
        commit.getName().clear();
        commit.getCreated().clear();
        commit.getCreatedIndicator().clear();
        commit.getDescription().clear();
        commit.getDescriptionIndicator().clear();

    }


    /**
     * Gettrs and Setters
     **/

    public int getMilestoneId(int milestoneIndexForManipulator) {

        return project.getMilestones().get(milestoneIndexForManipulator).getId();
    }

    public int getConfigurationId(int configIndex) {

        return project.getConfiguration().get(configIndex).getId();
    }

    public ArrayList<Integer> getMilestoneId(List<Integer> milestoneIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : milestoneIndex) {
            list.add(getMilestoneId(i));
        }
        return list;
    }

    public ArrayList<Integer> getRoleId(List<Integer> roleIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : roleIndex) {
            list.add(getRoleId(i));
        }
        return list;
    }


    public int getRoleId(int roleIndex) {
        int index = roleIndex;
        if (roleIndex != -1) {
            index = project.getRoles().get(roleIndex).getId();
        }
        return index;
    }

    public ArrayList<Integer> getRoleTypeIndex(ArrayList<Integer> typeFormManipulator) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : typeFormManipulator) {
            list.add(getRoleTypeIndex(i));
        }
        return list;
    }

    public int getRoleTypeIndex(int typeFormManipulator) {
        int index = typeFormManipulator;
        if (typeFormManipulator != -1) {
            index = project.getRoleType().get(typeFormManipulator).getId();
        }
        return index;
    }

    public ArrayList getWorkUnitIds(ArrayList<ArrayList<Integer>> workUnitIndex) {

        List<WorkUnit> criterion = project.getWorkUnits();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for (ArrayList<Integer> list : workUnitIndex) {
            for (int i : list) {
                if (i != -1) {
                    existCriterionId.add(criterion.get(i).getId());
                }

            }
        }
        return existCriterionId;
    }

    public ArrayList getCriterionIds(ArrayList<ArrayList<Integer>> criterionIndex) {

        List<Criterion> criterion = project.getCriterions();
        ArrayList<Integer> existCriterionId = new ArrayList();
        for (ArrayList<Integer> list : criterionIndex) {
            for (int i : list) {
                if (i != -1) {
                    existCriterionId.add(criterion.get(i).getId());
                }

            }
        }
        return existCriterionId;
    }

    /**
     * Pretizena metoda možní převedení data ve formátu LocalDate do formátu
     * XMLGregorianCalendar pro uložení do XML
     *
     * @param date seznam LocalDate
     * @return XMLGregorianCalendar
     */
    public static ArrayList<XMLGregorianCalendar> convertDate(ArrayList<LocalDate> date) {

        ArrayList<XMLGregorianCalendar> list = new ArrayList<>();

        for (LocalDate lDate : date) {
            list.add(convertDate(lDate));
        }

        return list;
    }


    /**
     * Umožní převedení data ve formátu LocalDate do formátu
     * XMLGregorianCalendar pro uložení do XML
     *
     * @param Ldate LocalDate
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertDate(LocalDate Ldate) {

        if (Ldate == null) {
            return null;
        }

        Instant instant = Instant.from(Ldate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        GregorianCalendar c = new GregorianCalendar();

        XMLGregorianCalendar dateXML = null;
        try {

            c.setTime(date);
            dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return dateXML;
    }

    public Coordinates createCoords(int x, int y) {
        Coordinates coord = objF.createCoordinates();
        coord.setXCoordinate(x);
        coord.setYCoordinate(y);
        return coord;
    }

    public void saveProcess(File xmlProcessFile) {
        processGenerator.saveProcess(xmlProcessFile, project);
    }

    public void parseProject(File xmlProject) {
        project = processGenerator.readProcess(xmlProject);
    }

    /**
     * Pomocná metoda pro validaci souboru
     */

    public void validate() {

        processGenerator.validate(project);
    }


    public Activity getActivity(int id) {
        return project.getActivities().get(getActivityIndexInProject(id));
    }

    public Artifact getArtifact(int id) {
        return project.getArtifacts().get(getArtifactIndexInProject(id));
    }

    public Branch getBranch(int id) {
        return project.getBranches().get(getBranchIndexInProject(id));
    }

    public Change getChange(int id) {
        return project.getChanges().get(getChangeIndexInProject(id));
    }

    public ConfigPersonRelation getConfigPersonRelation(int id) {
        return project.getCpr().get(getCPRIndexInProject(id));
    }

    public Configuration getConfiguration(int id) {
        return project.getConfiguration().get(getConfigurationIndexInProject(id));
    }

    public Criterion getCriterion(int id) {
        return project.getCriterions().get(getCriterionIndexInProject(id));
    }

    public int getCriterionId(int index) {
        return project.getCriterions().get(index).getId();
    }

    public Iteration getIteration(int id) {
        return project.getIterations().get(getIterationIndexInProject(id));
    }

    public Milestone getMilestone(int id) {
        return project.getMilestones().get(getMilestoneIndexInProject(id));
    }

    public Phase getPhase(int id) {
        return project.getPhases().get(getPhaseIndexInProject(id));
    }

    public Priority getPriority(int id) {
        return project.getPriority().get(getPriorityIndexInProject(id));
    }

    public Relation getRelation(int id) {
        return project.getRelation().get(getRelationIndexInProject(id));
    }

    public Resolution getResolution(int id) {
        return project.getResolution().get(getResolutionIndexInProject(id));
    }

    public Person getPerson(int id) {
        return project.getRoles().get(getPersonIndexInProject(id));
    }

    public RoleType getRoleType(int id) {
        return project.getRoleType().get(getRoleTypeIndexInProject(id));
    }

    public Severity getSeverity(int id) {
        return project.getSeverity().get(getSeverityIndexInProject(id));
    }

    public Status getStatus(int id) {
        return project.getStatus().get(getStatusIndexInProject(id));
    }

    public Type getType(int id) {
        return project.getTypes().get(getTypeIndexInProject(id));
    }

    public WorkUnit getWorkUnit(int id) {
        return project.getWorkUnits().get(getWUIndexInProject(id));
    }


    public IEditDataModel getEditDataModel() {
        return editDataModel;
    }

    public ISaveDataModel getSaveDataModel() {
        return saveDataModel;
    }

    public IDeleteDataModel getDeleteDataModel() {
        return deleteDataModel;
    }

    public DataManipulator getDataManipulator() {
        return dataManipulator;
    }

    public Project getProject() {
        return project;
    }

    public ObjectFactory getObjF() {
        return objF;
    }

    public ArrayList<Integer> getConfigurationId(List<Integer> index) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<Configuration> configList = project.getConfiguration();
        for (int configurationIndex : index) {
            idList.add(configList.get(configurationIndex).getId());
        }
        return idList;
    }

    public List<Integer> getTagId(List<Integer> index) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<VCSTag> configList = project.getVcsTag();
        for (int configurationIndex : index) {
            idList.add(configList.get(configurationIndex).getId());
        }
        return idList;

    }

    public ArrayList<Integer> getArtifactId(List<Integer> artifactForManipulator) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<Artifact> artifactList = project.getArtifacts();
        for (int artifactIndex : artifactForManipulator) {
            idList.add(artifactList.get(artifactIndex).getId());
        }
        return idList;
    }

    public int getArtifactId(int index) {
        return project.getArtifacts().get(index).getId();
    }

    public int getCommitedConfigurationId(int index) {
        return project.getCommitConfiguration().get(index).getId();
    }

    public ArrayList<Integer> getCommitedConfigurationId(List<Integer> artifactForManipulator) {
        ArrayList<Integer> idList = new ArrayList<>();
        List<CommitedConfiguration> artifactList = project.getCommitConfiguration();
        for (int artifactIndex : artifactForManipulator) {
            idList.add(artifactList.get(artifactIndex).getId());
        }
        return idList;
    }

    public int getRoleTypeId(int index) {
        return project.getRoleType().get(index).getId();
    }


    public ArrayList<Integer> getRoleTypeId(List<Integer> roleTypeForManipulator) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<RoleType> roleTypeList = project.getRoleType();
        for (int artifactIndex : roleTypeForManipulator) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getPriorityId(List<Integer> priorityIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Priority> roleTypeList = project.getPriority();
        for (int artifactIndex : priorityIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getTypeId(List<Integer> typeIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Type> roleTypeList = project.getTypes();
        for (int artifactIndex : typeIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getStatusId(List<Integer> statusIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Status> roleTypeList = project.getStatus();
        for (int artifactIndex : statusIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getSeverityId(List<Integer> severityIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Severity> roleTypeList = project.getSeverity();
        for (int artifactIndex : severityIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getRelationId(List<Integer> relationIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Relation> roleTypeList = project.getRelation();
        for (int artifactIndex : relationIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public ArrayList<Integer> getResolutionId(List<Integer> relationIndicies) {

        ArrayList<Integer> idList = new ArrayList<>();
        List<Resolution> roleTypeList = project.getResolution();
        for (int artifactIndex : relationIndicies) {
            idList.add(roleTypeList.get(artifactIndex).getId());
        }
        return idList;
    }

    public int getPriorityId(int index) {

        return project.getPriority().get(index).getId();
    }

    public int getSeverityId(int index) {

        return project.getSeverity().get(index).getId();
    }

    public int getTypeId(int index) {

        return project.getTypes().get(index).getId();
    }

    public int getResolutionId(int index) {

        return project.getResolution().get(index).getId();
    }

    public int getRelationId(int index) {

        return project.getRelation().get(index).getId();
    }

    public int getStatusId(int index) {

        return project.getStatus().get(index).getId();
    }


    public VCSTag getVCSTag(int id) {
        return project.getVcsTag().get(getVCSTagIndexInProject(id));
    }

    private int getVCSTagIndexInProject(int id) {
        List<VCSTag> items = project.getVcsTag();
        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Commit getCommit(int commitId) {
        return project.getCommit().get(getCommitIndexInProject(commitId));
    }

    public int getCommitIndexInProject(int id) {
        List<Commit> items = project.getCommit();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public CommitedConfiguration getCommitedConfiguration(int commitedId) {
        return project.getCommitConfiguration().get(getCommitedConfigurationIndexInProject(commitedId));
    }

    public int getCommitedConfigurationIndexInProject(int id) {
        List<CommitedConfiguration> items = project.getCommitConfiguration();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }


    public int getLinkIndexInProject(int arrowId) {
        List<Link> items = project.getLinks();

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == arrowId) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Getrs and Setrs
     **/

    public int getCommitId(int commitIndex) {
        return project.getCommit().get(commitIndex).getId();
    }

    public int getCPRId(int cprIndex) {
        return project.getCpr().get(cprIndex).getId();
    }

    public int getTagId(int tagIndex) {
        return project.getVcsTag().get(tagIndex).getId();
    }

    public int getChangeId(int i) {
        return project.getChanges().get(i).getId();
    }

    public int getBranchId(int i) {
        return project.getBranches().get(i).getId();
    }

    public int getWorkUnitId(int workUnitIndex) {
        return project.getWorkUnits().get(workUnitIndex).getId();
    }

    public List<Activity> getActivities() {
        return project.getActivities();
    }

    public List<Artifact> getArtifacts() {
        return project.getArtifacts();
    }

    public List<Branch> getBranches() {
        return project.getBranches();
    }

    public List<Change> getChanges() {
        return project.getChanges();
    }

    public List<Commit> getCommits() {
        return project.getCommit();
    }

    public List<CommitedConfiguration> getCommitedConfiguration() {
        return project.getCommitConfiguration();
    }

    public List<VCSTag> getVCSTags() {
        return project.getVcsTag();
    }

    public List<ConfigPersonRelation> getConfigPersonRelations() {
        return project.getCpr();
    }

    public List<Configuration> getConfigurations() {
        return project.getConfiguration();
    }

    public List<Criterion> getCriterions() {
        return project.getCriterions();
    }


    public List<Iteration> getIterations() {
        return project.getIterations();
    }

    public List<Milestone> getMilestones() {
        return project.getMilestones();
    }

    public List<Phase> getPhases() {
        return project.getPhases();
    }

    public List<Priority> getPriorities() {
        return project.getPriority();
    }

    public List<Relation> getRelations() {
        return project.getRelation();
    }

    public List<Resolution> getResolutions() {
        return project.getResolution();
    }

    public List<Person> getPersons() {
        return project.getRoles();
    }

    public List<RoleType> getRoleTypes() {
        return project.getRoleType();
    }

    public List<Severity> getSeverities() {
        return project.getSeverity();
    }

    public List<Status> getStatuses() {
        return project.getStatus();
    }

    public List<Type> getTypes() {
        return project.getTypes();
    }

    public List<WorkUnit> getWorkUnits() {
        return project.getWorkUnits();
    }

    public List<Link> getLinks() {
        return project.getLinks();
    }


}
