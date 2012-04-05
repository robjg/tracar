package tracar.dates;

import java.text.ParseException;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oddjob.arooa.convert.ArooaConversionException;
import org.oddjob.arooa.reflect.ArooaPropertyException;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;

public class LoadBusinessDateSpringTest {

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
	public void testLoadBusinessDate() throws ArooaPropertyException, ArooaConversionException, ParseException {

		new BusinessDateHelper(dbTestUtils).setBusinessDates(
				DateHelper.parseDate("2012-04-03"), 
				DateHelper.parseDate("2012-04-04"), 
				DateHelper.parseDate("2012-04-05"));
		
		
		BusinessDateLoaderSpring test = new BusinessDateLoaderSpring();
		test.setProperties(dbTestUtils.getTracarProps());
		test.run();
						
		Assert.assertEquals(DateHelper.parseDate("2012-04-03"), 
				test.getPrevious());		
		Assert.assertEquals(DateHelper.parseDate("2012-04-04"), 
				test.getCurrent());		
		Assert.assertEquals(DateHelper.parseDate("2012-04-05"), 
				test.getNext());		
		
	}
}
