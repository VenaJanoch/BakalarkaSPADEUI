package services;

public class IdentificatorCreater {
	/** Globální proměnné třídy **/
	private int linesID = -1;
	private int phaseID = -1;
	private int iterationID = -1;
	private int activityID = -1;
	private int workUnitID = -1;
	private int milestoneID = -1;
	private int criteriumID = -1;
	private int roleID = -1;
	private int branchID = -1;
	private int changeID = -1;
	private int artifactID = -1;
	private int configPRID = -1;
	private int configID = -1;
	private int tagID = -1;
	private int CPRID = -1;

	private int priorityID = -1;
	private int severityID = -1;
	private int resolutionID = -1;
	private int relationID = -1;
	private int statusID = -1;
	private int typeID = -1;
	private int roleTypeID = -1;

	private static int index;

	/**
	 * Metody pro inkrementaci počtu daného prvku
	 */
	
	public int createRoleTypeID() {

		roleTypeID++;

		return roleTypeID;

	}

	public int createTypeID() {

		typeID++;

		return typeID;

	}

	public int createStatusID() {

		statusID++;

		return statusID;

	}

	public int createRelationID() {

		relationID++;

		return relationID;

	}

	public int createResolutionID() {

		resolutionID++;

		return resolutionID;

	}

	public int createSeverityID() {

		severityID++;

		return severityID;

	}

	public int createPriorityID() {

		priorityID++;

		return priorityID;

	}

	public int createPhaseID() {

		phaseID++;

		return phaseID;

	}

	public int createIterationID() {

		iterationID++;

		return iterationID;
	}

	public int createActivityID() {

		activityID++;

		return activityID;
	}

	public int createWorkUnitID() {

		workUnitID++;

		return workUnitID;
	}

	public int createMilestoneID() {
		milestoneID++;
		return milestoneID;
	}

	public int createCriterionID() {
		criteriumID++;
		return criteriumID;
	}

	public int createRoleID() {
		roleID++;
		return roleID;
	}

	public int createBranchID() {
		branchID++;
		return branchID;
	}

	public int createChangeID() {
		changeID++;
		return changeID;
	}

	public int createArtifactID() {
		artifactID++;
		return artifactID;
	}

	public int createCPRID() {
		configPRID++;
		return configPRID;
	}

	public int createConfigurationID() {
		configID++;
		return configID;
	}

	public int createTagID() {
		tagID++;
		return tagID;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		IdentificatorCreater.index = index;
	}

	public IdentificatorCreater() {
		setIndex(1);
	}

	public int createLineID() {

		linesID++;

		return linesID;

	}

}
