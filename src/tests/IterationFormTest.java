package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Iteration;
import SPADEPAC.ObjectFactory;
import graphics.CanvasItem;
import services.FillForms;

public class IterationFormTest {

	private static ObjectFactory objF;
	Iteration iteration;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		iteration = objF.createIteration();
		FillForms.fillIteration(iteration, pole, "Desc", "Iteration", date, date, 2, 250, 350, false, objF);
	}
	
	

	@Test
	public void testName() {
		assertEquals(iteration.getName(), "Iteration");
	}	
	
	@Test
	public void testDesc() {
		assertEquals(iteration.getDescription(), "Desc");
	}
	
	@Test
	public void testEndDate() {
		assertEquals(iteration.getEndDate().toString(), date.toString()+"T00:00:00.000+02:00");
	}
	
	@Test
	public void testStartDate() {
		assertEquals(iteration.getStartDate().toString(), date.toString()+"T00:00:00.000+02:00");
	}
	
	@Test
	public void testCoorX() {
		assertEquals(iteration.getCoordinates().getXCoordinate().toString(), "250");
	}
	
	@Test
	public void testCoorY() {
		assertEquals(iteration.getCoordinates().getYCoordinate().toString(), "350");
	}
	
	
	
	@Test
	public void testConfigIndex() {
		assertEquals(iteration.getConfiguration().toString(), "1");
	}
}
