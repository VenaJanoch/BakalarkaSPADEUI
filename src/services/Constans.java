package services;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Constans {

	public static final int width = 1100;
	public static final int height = 800;
	public static final int offset = 12;

	public static final int formWidth = 700;
	public static final int formHeight = 600;
	public static final int workUnitformWidth = 300;
	public static final int workUnitformHeight = 500;
	
	
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
			"Add Priority", "Add severity", "Add Relation", "Add Resolution", "Add Status", "Add Type", "Add Branch",
			"Add Configuration" };
	

	public static final String linkSimbol = "-->";

	public static final int roleTypeManagementClass = 1;
	public static final int roleTypeTeamMemberClass = 7;
	public static final int roleTypeStakeholederClass = 9;
	public static final int roleTypeOtherClass = 10;

	public static final int roleTypeManagementSuperClass = 0;
	public static final int roleTypeTeamMemberSuperClass = 1;
	public static final int roleTypeStakeholederSuperClass = 2;
	public static final int roleTypeOtherSuperClass = 3;

	public static final int priorityTypeLowClass = 2;
	public static final int priorityTypeNormalClass = 3;
	public static final int priorityTypeHighClass = 5;
	public static final int priorityTypeOtherClass = 6;

	public static final int priorityTypeLowSuperClass = 0;
	public static final int priorityTypeNormaSuperClass = 1;
	public static final int priorityTypeHighClassSuperClass = 2;
	public static final int priorityTypeOtherSuperClass = 3;

	public static final int resolutionTypeFinishedClass = 5;

	public static final int resolutionFinishedSuperClass = 0;
	public static final int resolutionUnFinishedSuperClass = 1;

	public static final int relationTypeSimilaryClass = 2;
	public static final int relationTypeTemporalClass = 4;
	public static final int relationTypeGeneralClass = 5;
	public static final int relationTypeHierachyClass = 6;

	public static final int relationTypeSimilarySuperClass = 0;
	public static final int relationTypeTemporalSuperClass = 1;
	public static final int relationTypeGeneralSuperClass = 2;
	public static final int relationTypeHierachySuperClass = 3;
	public static final int relationTypeCasualSuperClass = 4;

	public static final int statusTypeOpenClass = 5;
	public static final int statusTypeOpenSuperClass = 0;
	public static final int statusTypeCloseSuperClass = 1;

	public static final int typeEditionClass = 2;
	public static final int typeCreationClass = 3;

	public static final int typeEditionSuperClass = 0;
	public static final int typeCreationSuperClass = 1;
	public static final int typeGeneralSuperClass = 2;

	public static final int infoBoxHeightRectangle = 20;
	public static final double infoBoxHeight = 40;
	public static final double relationCBOffset = 20;
	public static final double ArrowRadius = 10;
	public static final double checkComboBox = 50;
	public static final Color rectangleBorderColor = Color.BLACK;
	public static final Color nonExistRectangleBorderColor = Color.RED;

}
