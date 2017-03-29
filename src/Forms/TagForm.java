/**
 * 
 */
package Forms;

import Grafika.CanvasItem;
import Interfaces.ISegmentForm;
import Obsluha.Control;
import SPADEPAC.Configuration;
import SPADEPAC.Tag;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.WindowEvent;

/**
 * @author Václav
 *
 */
public class TagForm extends BasicForm implements ISegmentForm {

	private TextArea area;
	private Tag tag;

	/**
	 * @param item
	 * @param control
	 * @param itemArray
	 * @param indexForm
	 */
	public TagForm(CanvasItem item, Control control, Tag tag) {
		super(item, control);

		this.tag = tag;

		this.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				closeForm();
			}
		});

		getSubmitButton().setOnAction(event -> setActionSubmitButton());
		createForm();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.ISegmentForm#closeForm()
	 */
	@Override
	public void closeForm() {
		setName(getNameTF().getText());
		getCanvasItem().setNameText(getName());
		getControl().getFillForms().fillTag(tag, getCanvasItem().getIDs()[2], area.getText(),
				(int) getCanvasItem().getTranslateX(), (int) getCanvasItem().getTranslateY());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.ISegmentForm#setActionSubmitButton()
	 */
	@Override
	public void setActionSubmitButton() {
		closeForm();
		close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Interfaces.ISegmentForm#createForm()
	 */
	@Override
	public void createForm() {

		area = new TextArea();
		Label tagLB = new Label("Tag: ");
		getNameTF().setVisible(false);
		getNameLB().setVisible(false);
		getInfoPart().add(tagLB, 0, 1);
		getInfoPart().setHalignment(tagLB, HPos.RIGHT);
		getInfoPart().add(area, 1, 1);

	}

	public TextArea getArea() {
		return area;
	}

	public void setArea(TextArea area) {
		this.area = area;
	}
	
	

}
