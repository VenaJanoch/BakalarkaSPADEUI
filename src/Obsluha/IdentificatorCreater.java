package Obsluha;


public class IdentificatorCreater {

	private static int linesID = -1;
	private static int phaseID = -1 ;
	private static int iterationID = -1;
	private static int activityID = -1;
	private static int workUnitID = -1;
	private static int milestoneID = -1;
	private static int criteriumID = -1;
	private static int roleID = -1;
	private static int branchID = -1;
	private static int changeID = -1;
	private static int artifactID = -1;
	private static int configPRID = -1;
	private static int configID = -1;
	
	public IdentificatorCreater() {

	}

	public int createLineID() {

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

	
}
