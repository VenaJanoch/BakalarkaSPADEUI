package services;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Constans {

	public static final int width = 1000;
	public static final int height = 800;
	public static final int offset = 12;

	public static final int formWidth = 900;
	public static final int formHeight = 600;
	public static final int milestoneFormWidth = 950;
	public static final int milestoneFormHeight = 450;

	public static final int countDragItems = 13;
	public static final int countItemsBox = 6;

	public static final double maxCanvasItemWidth = 90;

	public static final String XSDNAME = "SPADE2.xsd";
	public static final String LIBRARY = "SPADEPAC";

	public static final int[] projectDragTextIndexs = { 0, 1, 2, 3 };
	public static final int[] phaseDragTextIndexs = { 3 };
	public static final int[] iterationDragTextIndexs = { 3 };
	public static final int[] activityDragTextIndexs = { 3 };
	public static final int[] configurationDragTextIndexs = { 9, 10};
	
	public static final int addButtonCount = 12;
	public static final String[] addButtonsNames = { "Project", "Add Milestone", "Add Config-role", "Add Role",
			"Add Priority", "Add severity", "Add Relation", "Add Resolution", "Add Status","Add Type", "Add Branch", "Add Configuration" };

	public static final int roleTypeManagementClass = 0;
	public static final int roleTypeTeamMemberClass = 6;
	public static final int roleTypeStakeholederClass = 8;
	public static final int roleTypeOtherClass = 9;

	public static final int roleTypeManagementSuperClass = 0;
	public static final int roleTypeTeamMemberSuperClass = 1;
	public static final int roleTypeStakeholederSuperClass = 2;
	public static final int roleTypeOtherSuperClass = 3;

	public static final int priorityTypeLowClass = 1;
	public static final int priorityTypeNormalClass = 2;
	public static final int priorityTypeHighClass = 4;
	public static final int priorityTypeOtherClass = 5;

	public static final int priorityTypeLowSuperClass = 0;
	public static final int priorityTypeNormaSuperClass = 1;
	public static final int priorityTypeHighClassSuperClass = 2;
	public static final int priorityTypeOtherSuperClass = 3;

	public static final int resolutionTypeFinishedClass = 5;

	public static final int resolutionFinishedSuperClass = 0;
	public static final int resolutionUnFinishedSuperClass = 1;

	public static final int relationTypeSimilaryClass = 1;
	public static final int relationTypeTemporalClass = 3;
	public static final int relationTypeGeneralClass = 4;
	public static final int relationTypeHierachyClass = 5;

	public static final int relationTypeSimilarySuperClass = 0;
	public static final int relationTypeTemporalSuperClass = 1;
	public static final int relationTypeGeneralSuperClass = 2;
	public static final int relationTypeHierachySuperClass = 3;
	public static final int relationTypeCasualSuperClass = 4;

	public static final int statusTypeOpenClass = 5;

	public static final int statusTypeOpenSuperClass = 0;
	public static final int statusTypeCloseSuperClass = 1;
	
	public static final int typeEditionClass = 1;
	public static final int typeCreationClass = 2;

	
	public static final int typeEditionSuperClass = 0;
	public static final int typeCreationSuperClass = 1;
	public static final int typeGeneralSuperClass = 2;

}
