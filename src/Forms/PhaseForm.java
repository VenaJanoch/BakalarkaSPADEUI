package Forms;


import Grafika.InfoBoxSegment;
import Interfaces.ISegmentForm;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

public class PhaseForm extends BasicForm implements ISegmentForm{

	
	private TextField tx;
	
	
	public PhaseForm(InfoBoxSegment infoBox) {
		super(infoBox);
		closeForm();
		
		tx = new TextField();
		getMainPanel().setBottom(tx);
	
		  
		
	}

	@Override
	public void closeForm() {
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				
				getInfoBox().setNameText(tx.getText());
			}
		});
		
		
		
	}
	
	
	
	

}
