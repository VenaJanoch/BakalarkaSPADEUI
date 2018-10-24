package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Branch;
import SPADEPAC.Criterion;
import SPADEPAC.ObjectFactory;
import services.FillForms;

public class CriterionTest {

	private static ObjectFactory objF;
	Criterion criterion;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		criterion = FillForms.fillCriterion("4", "Criterion", "Desc", objF, true);
	}

	@Test
	public void testName() {
		assertEquals(criterion.getName(), "Criterion");
	}

	@Test
	public void testMain() {
		assertEquals(criterion.getDescription(), "Desc");
	}

}
