package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.ObjectFactory;
import services.FillForms;

public class BranchFormTest {

	private static ObjectFactory objF;
	Branch branch;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		branch = FillForms.fillBranch("Branch", "2", true, objF, true);

	}

	@Test
	public void testName() {
		assertEquals(branch.getName(), "Branch");
	}

	@Test
	public void testMain() {
		assertEquals(branch.isIsMain().toString(), "true");
	}

}
