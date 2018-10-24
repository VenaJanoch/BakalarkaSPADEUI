package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import forms.PhaseForm;
import graphics.MainWindow;
import run.Main;
import services.Control;
import services.FillForms;
import services.SegmentType;

public class PhaseFormTest {
	private static ObjectFactory objF;
	Phase phase;
	LocalDate date;
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		phase = (Phase) objF.createPhase();
		int[] pole = {1,2};		
		date = LocalDate.now();
		FillForms.fillPhase(phase, pole, "descPhase", "Phase1", date, 1, 5, 250, 300, false, objF);	
	}

	@Test
	public void testDes() {
		assertEquals(phase.getDescription(), "descPhase");
	}
	
	@Test
	public void testName() {
		assertEquals(phase.getName(), "Phase1");
	}
	
	@Test
	public void testCoorX() {
		assertEquals(phase.getCoordinates().getXCoordinate().toString(), "250");
	}
	
	@Test
	public void testCoorY() {
		assertEquals(phase.getCoordinates().getYCoordinate().toString(), "300");
	}
	
	
	@Test
	public void testMilestoneIndex() {
		assertEquals(phase.getMilestoneIndex().toString(), "4");
	}
	
	@Test
	public void testConfigIndex() {
		assertEquals(phase.getConfiguration().toString(), "0");
	}
	
	@Test
	public void testDate() {
		assertEquals(phase.getEndDate().toString(), date.toString()+"T00:00:00.000+02:00");
	}

}
