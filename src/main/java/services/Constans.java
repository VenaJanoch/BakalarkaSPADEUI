package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.time.LocalDate;
import java.util.ArrayList;

public class Constans {


    public static final String mainWindowTitle = "SPADe Process Editor";
    public static final String fileChooserTitle = "Open Process";

    /**
     * Konstanty pro nastavení velikosti oken
     */
    public static final int width = 1300;
    public static final int height = 800;
    public static final int offset = 12;

    public static final int formWidth = 550;
    public static final int formHeight = 550;
    public static final int workUnitformWidth = 350;
    public static final int workUnitformHeight = 550;
    public static final double littleformWidth = 350;
    public static final double littleformHeight = 350;
    public static final int canvasMaxWidth = 2000;
    public static final int canvasMaxHeight = 2000;
    public static final int twoFormWidth = 1250;
    public static final int twoFormHeight = 850;

    public static final double minCanvasItemWidth = 210;
    public static final double maxCanvasItemWidth = 210;

    /**
     * Konstanta pro nastavení počtu segmentů a elementů
     */
    public static final int countDragItems = 13;

    /**
     * Konstanty se jmény XSD schématu a vytvořené knihovy
     */
    public static final String XSDNAME = "SPADE2.xsd";
    public static final String LIBRARY = "SPADEPAC";

    /**
     * Konstanty určující tlačítka pro formulářová plátna
     */

    public static final int[] projectDragTextIndexs = {11, 6, 21, 22, 10};
    public static final int[] phaseDragTextIndexs = {3};
    public static final int[] iterationDragTextIndexs = {3};
    public static final int[] activityDragTextIndexs = {3};
    public static final int[] configurationDragTextIndexs = {9, 10};

    /**
     * Konstanta pro určení počtu tlačítek pro tabulkové formuláře
     */
    public static final int addButtonCount = 12;
    /**
     * Konstanta pro určtní poředí jmen tlačítek s tablukovými formuláři
     */
    public static final String[] addButtonsNames = {"Project", "Add Milestone", "Add Person", "Add Config-role",
            "Add Priority", "Add Severity", "Add Relation", "Add Resolution", "Add Status", "Add Type", "Add Branch",
            "Add Configuration"};



    /**
     * Konstanta pro určení symbolu tlačítka
     */
    public static final String linkSimbol = "→";

    /**
     * Konstanty pro mapování role Class na role Super Class
     */
    public static final int roleTypeManagementClass = 1;
    public static final int roleTypeTeamMemberClass = 7;
    public static final int roleTypeStakeholederClass = 9;
    public static final int roleTypeOtherClass = 10;

    public static final int typeSuperClassUnassigned = 0;
    public static final int roleTypeManagementSuperClass = 1;
    public static final int roleTypeTeamMemberSuperClass = 2;
    public static final int roleTypeStakeholederSuperClass = 3;
    public static final int roleTypeOtherSuperClass = 4;

    /**
     * Konstanty pro mapování priority Class na priority Super Class
     */
    public static final int priorityTypeLowClass = 2;
    public static final int priorityTypeNormalClass = 3;
    public static final int priorityTypeHighClass = 5;
    public static final int priorityTypeOtherClass = 6;

    public static final int priorityTypeLowSuperClass = 1;
    public static final int priorityTypeNormaSuperClass = 2;
    public static final int priorityTypeHighClassSuperClass = 3;
    public static final int priorityTypeOtherSuperClass = 4;

    /**
     * Konstanty pro mapování priority Class na priority Super Class
     */
    public static final int severityTypeMajorlClass = 2;
    public static final int severityTypeNormalClass = 3;
    public static final int severityTypeMinorClass = 5;
    public static final int severityTypeOtherSuperClass = 6;

    public static final int severityTypeMinorSuperClass = 1;
    public static final int severityTypeNormalSuperClass = 2;
    public static final int severityTypeMajorClassSuperClass = 3;

    /**
     * Konstanty pro mapování resolution Class na Super Class
     */
    public static final int resolutionTypeFinishedClass = 5;

    public static final int resolutionFinishedSuperClass = 1;
    public static final int resolutionUnFinishedSuperClass = 2;

    /**
     * Konstanty pro mapování relation Class na Super Class
     */
    public static final int relationTypeSimilaryClass = 2;
    public static final int relationTypeTemporalClass = 4;
    public static final int relationTypeGeneralClass = 5;
    public static final int relationTypeHierachyClass = 6;

    public static final int relationTypeSimilarySuperClass = 1;
    public static final int relationTypeTemporalSuperClass = 2;
    public static final int relationTypeGeneralSuperClass = 3;
    public static final int relationTypeHierachySuperClass = 4;
    public static final int relationTypeCasualSuperClass = 5;

    /**
     * Konstanty pro mapování Status Class na Super Class
     */
    public static final int statusTypeOpenClass = 5;
    public static final int statusTypeOpenSuperClass = 1;
    public static final int statusTypeCloseSuperClass = 2;

    /**
     * Konstanty pro mapování Work Unit type Class na Super Class
     */
    public static final int typeEditionClass = 2;
    public static final int typeCreationClass = 3;

    public static final int typeEditionSuperClass = 1;
    public static final int typeCreationSuperClass = 2;
    public static final int typeGeneralSuperClass = 3;

    /**
     * Konstanty pro nastavení velikosti prvku plátna
     */
    public static final int infoBoxHeightRectangle = 20;
    public static final double infoBoxHeight = 40;
    public static final double relationCBOffset = 20;

    /**
     * Konstanty pro nastavení velikosti šipky
     */
    public static final double ArrowRadius = 10;
    public static final double polygonHeight = 10;
    public static final double checkComboBox = 50;

    /**
     * Konstanty pro nastavení barev prvku plátna
     */
    public static final Color rectangleBorderColor = Color.BLACK;
    public static final Color nonExistRectangleBorderColor = Color.RED;

    /**
     * Konstanty pro definování klávesových zkratek
     */

    public static final KeyCodeCombination controlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination controlO = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination controlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination controlSA = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY,
            KeyCodeCombination.SHIFT_ANY);
    public static final KeyCodeCombination controlF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination altF4 = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_ANY);

    public static final KeyCodeCombination controlC = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination controlV = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY);
    public static final KeyCodeCombination controlX = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY);
    public static final KeyCode delete = KeyCode.DELETE;
    public static final String linkPicture = "link.svn";

    public static final String[] addItemNames = {"Phase", "Iteration", "Activity", "Work Unit", "Milestone", "Criterion", "Config-role",
            "Role Type", "Priority", "Severity", "Relation", "Resolution", "Status", "Type", "Branch", "Change", "VCSTag"};

    public static final String[] textIndicatorList = {"IS", "IS NOT","CONTAINS", "NOT CONTAINS"};
    public static final String[] numberIndicatorList = {"EQUEL", "BIGGER", "SMALLER" };

    public static final int projectFormIndex = 0;
    public static final int phaseFormIndex = 0;
    public static final int iterationFormIndex = 1;
    public static final int activityFormIndex = 2;
    public static final int workUnitFormIndex = 3;
    public static final int milestoneFormIndex = 4;
    public static final int criterionFormIndex = 5;
    public static final int cprFormIndex = 6;
    public static final int roleTypeIndex = 7;
    public static final int priorityFormIndex = 8;
    public static final int severityFormIndex = 9;
    public static final int relationFormIndex = 10;
    public static final int resolutionormIndex = 11;
    public static final int statusFormIndex = 12;
    public static final int wuTypeFormIndex = 13;
    public static final int branchIndex = 14;
    public static final int changeFormIndex = 15;
    public static final int VCSTagIndex = 16;
    public static final int artifactFormIndex = 17;
    public static final int configurationFormIndex = 18;
    public static final int roleFormIndex = 19;



    public static final double rightDrawerWidth = 470;
    public static final double tableControlButtonWidth = 70;
    public static final double tableControlButtonHeight = 70;
    public static final int indicatorIndex = 2;

    public static final LocalDate nullDate = LocalDate.of(1900,1,1);

    public static final String CESTA_K_DATABAZI = "jdbc:mysql://localhost:3306/opswi?allowMultiQueries=true"; //cesta k databázi

}
