package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import SPADEPAC.Branch;
import SPADEPAC.Configuration;
import SPADEPAC.ObjectFactory;
import graphics.CanvasItem;
import services.FillForms;

public class ConfigTest {

	private static ObjectFactory objF;
	Configuration config;
	LocalDate date;

	@Before
	public void setUp() throws Exception {
		objF = new ObjectFactory();
		int[] pole = { 1, 2 };
		date = LocalDate.now();
		config = objF.createConfiguration();
		FillForms.fillConfiguration(config, pole, false, date, "Config", 2, false, new CanvasItem(), true);
		
	}
	
	

	@Test
	public void testName() {
		assertEquals(config.getName(), "Config");
	}	
	
	
	@Test
	public void testRoleIndex() {
		assertEquals(config.getAuthorIndex().toString(), "1");
	}
	
	
	@Test
	public void testDate() {
		assertEquals(config.getCreate().toString(), date.toString()+"T00:00:00.000+02:00");
	}
	
	@Test
	public void testReliase() {
		assertEquals(config.isIsRelease().toString(), "false");
	}
}
