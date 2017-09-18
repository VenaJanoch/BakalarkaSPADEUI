package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.FillForms;

public class MilestoneFormTest {

	private static ObjectFactory objF;
	Milestone milestone;
	LocalDate date;
	ObservableList<Integer> criterionObservable;
	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		criterionObservable = FXCollections.observableArrayList();
		criterionObservable.add(1);
		criterionObservable.add(2);
		criterionObservable.add(3);
		milestone = FillForms.fillMilestone("1", "Milestone", criterionObservable, objF, true);
	}

	@Test
	public void testName() {
		assertEquals(milestone.getName(), "Milestone");
	}

	@Test
	public void testConfig() {
		assertEquals(milestone.getCriteriaIndexs().size(), 3);
	}

}
