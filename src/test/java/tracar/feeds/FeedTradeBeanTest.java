package tracar.feeds;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import tracar.DBTestUtils;

public class FeedTradeBeanTest {

	static DBTestUtils dbTestUtils;
	
	@BeforeClass
	public static void setUp() {

		dbTestUtils = new DBTestUtils();

		dbTestUtils.setUp("feeds");
	}

	@AfterClass
	public static void tearDown() {
		dbTestUtils.tearDown();
	}

	@Test
	public void testHibernateMapping() {

		SessionFactory sessionFactory = dbTestUtils.createSessionFactory(
				"tracar/feeds/Hibernate.cfg.xml");
		Session session = sessionFactory.openSession();

		FeedTradeBean bean = new FeedTradeBean();

		session.save(bean);

		long id = bean.getId();

		session.close();

		session = sessionFactory.openSession();

		FeedTradeBean result = (FeedTradeBean) session.get(
				FeedTradeBean.class, id);

		Assert.notNull(result);

		session.delete(result);

		session.close();

		sessionFactory.close();
	}
}
