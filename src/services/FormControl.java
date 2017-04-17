package services;

public class FormControl {

	private SegmentLists lists;

	public FormControl(SegmentLists lists) {
		this.lists = lists;
	}

	public static boolean copyControl(SegmentType itemType, CanvasType canvasType) {

		if (itemType == SegmentType.WorkUnit) {

			if (canvasType == canvasType.Phase || canvasType == canvasType.Iteration
					|| canvasType == canvasType.Activity || canvasType == canvasType.Project) {
				return true;
			}

		}else if(itemType == SegmentType.Change || itemType == SegmentType.Artifact){
			if (canvasType == CanvasType.Configuration) {
				return true;
			}
		}else if(itemType == SegmentType.Phase || itemType == SegmentType.Iteration || itemType == SegmentType.Activity){
			if (canvasType == CanvasType.Project) {
				return true;
			}
		}

		return false;

	}

	public boolean phaseControl() {

		if (lists.getConfigList().isEmpty()) {
			Alerts.showNoText("Configuration");
			return false;
		} else if (lists.getMilestoneList().isEmpty()) {
			Alerts.showNoText("Milestone");
			return false;
		}

		return true;

	}

	public boolean artifactControl() {
		if (lists.getRoleList().isEmpty()) {
			Alerts.showNoText("Role");
			return false;
		}
		return true;
	}

	public boolean configControl() {
		if (lists.getRoleList().isEmpty()) {
			Alerts.showNoText("Author");
			return false;
		} else if (lists.getBranchList().isEmpty()) {
			Alerts.showNoText("Branch");
			return false;
		}

		return true;
	}

	public boolean iterationControl() {

		if (lists.getConfigList().isEmpty()) {
			Alerts.showNoText("Configuration");
			return false;
		}

		return true;
	}

	public boolean workUnitControl(String estimate) {

		double estimated = 0;

		try {
			estimated = Double.parseDouble(estimate);
		} catch (NumberFormatException e) {
			Alerts.showWrongEstimatedTimeAlert();
			return false;
		}

		if (lists.getPriorityTypeList().isEmpty()) {
			Alerts.showNoText("Priority");
			return false;
		} else if (lists.getSeverityTypeList().isEmpty()) {
			Alerts.showNoText("Severity");
			return false;
		} else if (lists.getResolutionTypeList().isEmpty()) {
			Alerts.showNoText("Resolution");
			return false;
		} else if (lists.getStatusTypeList().isEmpty()) {
			Alerts.showNoText("Status");
			return false;
		} else if (lists.getTypeList().isEmpty()) {
			Alerts.showNoText("Type");
			return false;
		} else if (lists.getRoleList().isEmpty()) {
			Alerts.showNoText("Role-Type");
			return false;
		}

		return true;

	}

}
