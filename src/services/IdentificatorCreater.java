package services;


public class IdentificatorCreater {

	private static int linesID = -1;
	private int phaseID = -1 ;
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
	public IdentificatorCreater() {

	}

	public static int createLineID() {

		linesID++;

		return linesID;

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

	
}
