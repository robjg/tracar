package tracar.dates;

import java.text.ParseException;
import java.util.Date;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oddjob.arooa.convert.ArooaConversionException;
import org.oddjob.arooa.reflect.ArooaPropertyException;
import org.oddjob.arooa.registry.BeanDirectory;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;

public class RollBusinessDateSpringTest {

	static DBTestUtils dbTestUtils;
	
	@BeforeClass
	public static void setUp() {

		dbTestUtils = new DBTestUtils();
		
		dbTestUtils.setUp("dates");
	}

	@AfterClass
	public static void tearDown() {
		new DBTestUtils().tearDown();
	}
	
	@Test
	public void testRollBusinessDate() throws ArooaPropertyException, ArooaConversionException, ParseException {

		new BusinessDateHelper(dbTestUtils).setBusinessDates(DateHelper.parseDate("2012-04-03"), 
				DateHelper.parseDate("2012-04-04"), 
				DateHelper.parseDate("2012-04-05"));
		
		RollBusinessDateSpring test = new RollBusinessDateSpring();
		test.setProperties(dbTestUtils.getTracarProps());
		test.run();
		
		BeanDirectory beans = dbTestUtils.execTextSQL(
				"select * from ${tracar.db.schema}.BUSINESS_DATE");
		
		Assert.assertEquals(1, beans.lookup("results.rowCount"));
				
		Assert.assertEquals(DateHelper.parseDate("2012-04-04"), 
				beans.lookup("results.rows[0].PREVIOUS", Date.class));		
		Assert.assertEquals(DateHelper.parseDate("2012-04-05"), 
				beans.lookup("results.rows[0].CURRENT", Date.class));		
		Assert.assertEquals(DateHelper.parseDate("2012-04-10"), 
				beans.lookup("results.rows[0].NEXT", Date.class));		
		
	}
}
