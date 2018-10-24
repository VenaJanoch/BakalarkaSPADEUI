package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Branch;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.ObjectFactory;
import services.FillForms;

public class CPRTest {

	private static ObjectFactory objF;
	ConfigPersonRelation branch;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		branch = FillForms.fillCPR("1", "CPR", 1, 3, objF, true);

	}

	@Test
	public void testName() {
		assertEquals(branch.getName(), "CPR");
	}

	@Test
	public void testConfig() {
		assertEquals(branch.getConfigurationIndex().toString(), "0");
	}
	
	@Test
	public void testRole() {
		assertEquals(branch.getPersonIndex().toString(), "2");
	}

}
