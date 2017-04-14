package services;

public class FormControl {

	private SegmentLists lists;
	public FormControl(SegmentLists lists) {
		this.lists = lists;
	}
	
	
	public boolean phaseControl(){
		
		if (lists.getConfigList().isEmpty()) {
			Alerts.showNoText("Configuration");
			return false;
		}else if(lists.getMilestoneList().isEmpty()){
			Alerts.showNoText("Milestone");
			return false;
		}
		
		return true;
		
		
	}

}
