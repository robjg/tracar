package tracar.dates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;

public class HibernateMappingsTest {

	static DBTestUtils dbTestUtils;
	
	@BeforeClass
	public static void setUp() {

		dbTestUtils = new DBTestUtils();
		
		dbTestUtils.setUp("dates");
	}

	@AfterClass
	public static void tearDown() {
		dbTestUtils.tearDown();
	}

	@Test
	public void testHibernateMapping() throws ParseException {

		SessionFactory sessionFactory = dbTestUtils.createSessionFactory(
				"tracar/dates/Hibernate.cfg.xml");
		
		Session session = sessionFactory.openSession();


		Criteria businessDateQuery = session.createCriteria(
				BusinessDateBean.class);
		
		BusinessDate resultBusinessDate = 
				(BusinessDate) businessDateQuery.uniqueResult();

		assertNotNull(resultBusinessDate);
		
		Criteria holidayQuery = session.createCriteria(HolidayBean.class);
		holidayQuery.addOrder(Order.asc("holiday"));
		
		@SuppressWarnings("unchecked")
		List<Holiday> resultHolidays = 
				(List<Holiday>) holidayQuery.list();

		assertEquals(8, resultHolidays.size());
		
		Holiday resultHoliday = resultHolidays.get(0);
		assertEquals(DateHelper.parseDate("2012-04-06"), 
				resultHoliday.getHoliday());
				
		// Finish

		session.close();

		sessionFactory.close();
	}
}
