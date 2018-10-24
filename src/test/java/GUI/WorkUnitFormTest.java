package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SPADEPAC.ObjectFactory;
import SPADEPAC.WorkUnit;
import forms.WorkUnitForm;
import graphics.MainWindow;
import run.Main;
import services.Control;
import services.FillForms;
import services.SegmentType;

public class WorkUnitFormTest {
	private static ObjectFactory objF;
	WorkUnit workUnit;
	LocalDate date;
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		workUnit = (WorkUnit) objF.createWorkUnit();
		int[] pole = {1,2};		
		date = LocalDate.now();
		FillForms.fillWorkUnit(workUnit, pole, "Desc", "WorkUnit", 2, 2, "2", 250, 350, 2, 2, 2, 2, 2, 2.0, false, true, objF);	}

	
	
	@Test
	public void testEstimate() {
		assertEquals(workUnit.getEstimatedTime().intValue(), 2);
	}
	
	@Test
	public void testIsExist() {
		assertEquals(workUnit.isExist(), true);
	}
	@Test
	public void testPriorityIndex() {
		assertEquals(workUnit.getPriorityIndex().intValue(), 1);
	}
	
//	@Test
//	public void testSeverityIndex() {
//		assertEquals(workUnit.getSeverityIndex().intValue(), 1);
//	}
//	
//	@Test
//	public void testStatusIndex() {
//		assertEquals(workUnit.getStatusIndex().intValue(), 1);
//	}
//	
//	@Test
//	public void testResolutionIndex() {
//		assertEquals(workUnit.getResolutionIndex().intValue(), 1);
//	}
//	
//	@Test
//	public void testTypeIndex() {
//		assertEquals(workUnit.getTypeIndex().intValue(), 1);
//	}
//	
	@Test
	public void testDes() {
		assertEquals(workUnit.getDescription(), "Desc");
	}
	
	@Test
	public void testName() {
		assertEquals(workUnit.getName(), "WorkUnit");
	}
	
	@Test
	public void testCoorX() {
		assertEquals(workUnit.getCoordinates().getXCoordinate().intValue(), 250);
	}
	
	@Test
	public void testCoorY() {
		assertEquals(workUnit.getCoordinates().getYCoordinate().intValue(), 350);
	}
	
	
//	@Test
//	public void testAuthorIndex() {
//		assertEquals(workUnit.getAuthorIndex().intValue(), 1);
//	}
//	
//	@Test
//	public void testAssigneIndex() {
//		assertEquals(workUnit.getAssigneeIndex().intValue(), 1);
//	}
//	@Test
//	public void testCategory() {
//		assertEquals(workUnit.getCategory().toString(), "2");
//	}
	

}
