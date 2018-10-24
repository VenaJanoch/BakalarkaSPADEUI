package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Change;
import SPADEPAC.ObjectFactory;
import graphics.CanvasItem;
import services.FillForms;

public class ChangeFormTest {

	private static ObjectFactory objF;
	Change change;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		change = objF.createChange();
		FillForms.fillChange(change, pole, "Desc", "Change", false, 250, 350, true, objF);
		
	}
	
	

	@Test
	public void testName() {
		assertEquals(change.getName(), "Change");
	}
	
	@Test
	public void testDesc() {
		assertEquals(change.getDescriptoin(), "Desc");
	}
	
	@Test
	public void testX() {
		assertEquals(change.getCoordinates().getXCoordinate().intValue(), 250);
	}
	
	@Test
	public void testY() {
		assertEquals(change.getCoordinates().getYCoordinate().intValue(), 350);
	}
	
	@Test
	public void testIsExist() {
		assertEquals(change.isExist(), true);
	}
	
	

}
