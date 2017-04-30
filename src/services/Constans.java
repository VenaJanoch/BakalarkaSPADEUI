package services;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Constans {

	public static final int width = 1100;
	public static final int height = 800;
	public static final int offset = 12;

	public static final int formWidth = 700;
	public static final int formHeight = 600;
	public static final int workUnitformWidth = 350;
	public static final int workUnitformHeight = 500;
	public static final double littleformWidth = 300;
	public static final double littleformHeight = 300;


	
	public static final int canvasMaxWidth = 2000;
	public static final int canvasMaxHeight = 2000;
	public static final int milestoneFormWidth = 1100;
	public static final int milestoneFormHeight = 750;

	public static final int countDragItems = 13;
	public static final int countItemsBox = 6;

	public static final double minCanvasItemWidth = 90;
	public static final double maxCanvasItemWidth = 90;

	public static final String XSDNAME = "SPADE2.xsd";
	public static final String LIBRARY = "SPADEPAC";

	public static final int[] projectDragTextIndexs = { 0, 1, 2, 3 };
	public static final int[] phaseDragTextIndexs = { 3 };
	public static final int[] iterationDragTextIndexs = { 3 };
	public static final int[] activityDragTextIndexs = { 3 };
	public static final int[] configurationDragTextIndexs = { 9, 10 };

	public static final int addButtonCount = 12;
	public static final String[] addButtonsNames = { "Project", "Add Milestone", "Add Config-role", "Add Role",
			"Add Priority", "Add Severity", "Add Relation", "Add Resolution", "Add Status", "Add Type", "Add Branch",
			"Add Configuration" };
	

	public static final String linkSimbol = "-->";

	public static final int roleTypeManagementClass = 1;
	public static final int roleTypeTeamMemberClass = 7;
	public static final int roleTypeStakeholederClass = 9;
	public static final int roleTypeOtherClass = 10;

	public static final int typeSuperClassUnassigned = 0;
	public static final int roleTypeManagementSuperClass = 1;
	public static final int roleTypeTeamMemberSuperClass = 2;
	public static final int roleTypeStakeholederSuperClass = 3;
	public static final int roleTypeOtherSuperClass = 4;

	public static final int priorityTypeLowClass = 2;
	public static final int priorityTypeNormalClass = 3;
	public static final int priorityTypeHighClass = 5;
	public static final int priorityTypeOtherClass = 6;

	public static final int priorityTypeLowSuperClass = 1;
	public static final int priorityTypeNormaSuperClass = 2;
	public static final int priorityTypeHighClassSuperClass = 3;
	public static final int priorityTypeOtherSuperClass = 4;

	public static final int resolutionTypeFinishedClass = 5;

	public static final int resolutionFinishedSuperClass = 1;
	public static final int resolutionUnFinishedSuperClass = 2;

	public static final int relationTypeSimilaryClass = 2;
	public static final int relationTypeTemporalClass = 4;
	public static final int relationTypeGeneralClass = 5;
	public static final int relationTypeHierachyClass = 6;

	public static final int relationTypeSimilarySuperClass = 1;
	public static final int relationTypeTemporalSuperClass = 2;
	public static final int relationTypeGeneralSuperClass = 3;
	public static final int relationTypeHierachySuperClass = 4;
	public static final int relationTypeCasualSuperClass = 5;

	public static final int statusTypeOpenClass = 5;
	public static final int statusTypeOpenSuperClass = 1;
	public static final int statusTypeCloseSuperClass = 2;

	public static final int typeEditionClass = 2;
	public static final int typeCreationClass = 3;

	public static final int typeEditionSuperClass = 1;
	public static final int typeCreationSuperClass = 2;
	public static final int typeGeneralSuperClass = 3;

	public static final int infoBoxHeightRectangle = 20;
	public static final double infoBoxHeight = 40;
	public static final double relationCBOffset = 20;
	
	public static final double ArrowRadius = 10;
	public static final double polygonHeight = 5;
	
	public static final double checkComboBox = 50;
	public static final Color rectangleBorderColor = Color.BLACK;
	public static final Color nonExistRectangleBorderColor = Color.RED;
	
	
	public static final KeyCodeCombination controlN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination controlO = new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination controlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination controlSA = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_ANY,KeyCodeCombination.SHIFT_ANY);
	public static final KeyCodeCombination controlF = new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination altF4 = new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_ANY);
	
	public static final KeyCodeCombination controlC = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination controlV = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_ANY);
	public static final KeyCodeCombination controlX = new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_ANY);
	public static final KeyCode delete = KeyCode.DELETE;
	public static final String linkPicture = "link.svn";

}
