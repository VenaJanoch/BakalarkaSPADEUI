package Forms;

import Grafika.CanvasItem;
import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class WorkUnitForm extends BasicForm implements ISegmentForm{
		
	private Label descriptionLB;
	private Label estimatedTimeLB;
	private Label priorityLB;
	private Label severityLB;
	private Label categoryLB;
	private Label typeLB;
	private Label asigneeRoleLB;
	private Label authorRoleLB;
	
	private TextField descriptionTF;
	private TextField estimatedTimeTF;
	private TextField priorityTF;
	private TextField severityTF;
	private TextField categoryTF;
	private TextField typeTF;
	private TextField asigneeRoleTF;
	private TextField authorRoleTF;
	
	public WorkUnitForm(CanvasItem item, Control control) {
		super(item, control);
		
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				closeForm();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();
	
	}
	

	@Override
	public void closeForm() {

		getCanvasItem().setNameText(getNameTF().getText());
		getControl().fillWorkUnit(getCanvasItem().getIDs()[1], descriptionTF.getText(), getName());

	}

	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}
	
	@Override
	public void createForm() {
		
		estimatedTimeLB = new Label("Estimated Time: ");
		estimatedTimeTF = new TextField();
		
		priorityLB = new Label("Priority: ");
		priorityTF = new TextField();

		severityLB = new Label("Severity: ");
		severityTF = new TextField();

		categoryLB = new Label("CategoryLB: ");
		categoryTF = new TextField();

		typeLB = new Label("Type: ");
		typeTF = new TextField();

		asigneeRoleLB = new Label("Asignee-role: ");
		asigneeRoleTF = new TextField();

		authorRoleLB = new Label("Author-role: ");
		authorRoleTF = new TextField();
		
		descriptionLB = new Label("Description: ");
		descriptionTF = new TextField();
		
		fillInfoPart();
	}


		private void fillInfoPart() {
		
		getInfoPart().add(descriptionLB,0,1); 
		getInfoPart().setHalignment(descriptionLB, HPos.RIGHT);
		getInfoPart().add(descriptionTF,1,1);
		
		getInfoPart().add(estimatedTimeLB,0,2);
		getInfoPart().setHalignment(estimatedTimeLB, HPos.RIGHT);
		getInfoPart().add(estimatedTimeTF,1,2);
		
		getInfoPart().add(priorityLB,0,3); 
		getInfoPart().setHalignment(priorityLB, HPos.RIGHT);	
		getInfoPart().add(priorityTF,1,3);
		
		getInfoPart().add(severityLB,0,4); 
		getInfoPart().setHalignment(severityLB, HPos.RIGHT);
		getInfoPart().add(severityTF,1,4);
		
		getInfoPart().add(categoryLB,0,5); 
		getInfoPart().setHalignment(categoryLB, HPos.RIGHT);		
		getInfoPart().add(categoryTF,1,5);	
		
		getInfoPart().add(typeLB,0,6); 
		getInfoPart().setHalignment(typeLB, HPos.RIGHT);	
		getInfoPart().add(typeTF,1,6);
		
		getInfoPart().add(asigneeRoleLB,0,7); 
		getInfoPart().setHalignment(asigneeRoleLB, HPos.RIGHT);
		getInfoPart().add(asigneeRoleTF,1,7);
		
		getInfoPart().add(authorRoleLB,0,8); 
		getInfoPart().setHalignment(authorRoleLB, HPos.RIGHT);		
		getInfoPart().add(authorRoleTF,1,8);	
		
		
	}
	
}
