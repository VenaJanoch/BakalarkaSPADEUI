package GUI;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Activity;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import services.FillForms;

public class ActivityFormTest {

	private static ObjectFactory objF;
	Activity activity;
	LocalDate date;

	@Before
	public void setUp() throws Exception {

		objF = new ObjectFactory();
		activity = (Activity) objF.createActivity();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		FillForms.fillActivity(activity, pole, "Desc", "Activity", 250, 350, false, objF);
	}

	@Test
	public void testDes() {
		assertEquals(activity.getDescription(), "Desc");
	}

	@Test
	public void testName() {
		assertEquals(activity.getName(), "Activity");
	}

	@Test
	public void testCoorX() {
		assertEquals(activity.getCoordinates().getXCoordinate().toString(), "250");
	}

	@Test
	public void testCoorY() {
		assertEquals(activity.getCoordinates().getYCoordinate().toString(), "350");
	}

}
