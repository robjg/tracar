package tracar.feeds.random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.junit.Test;
import org.oddjob.arooa.utils.DateHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import tracar.feeds.FeedTradeBean;

public class RandomTradeSourceSpringTest {

	@Test
	public void testJob() throws Exception {

		GenericApplicationContext parentContext = new StaticApplicationContext();

		parentContext.getBeanFactory().registerSingleton("tradeDate",
				DateHelper.parseDate("2012-02-10"));
		parentContext.refresh();

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "tracar/feeds/random/RandomTradeSource.spg.xml" },
				parentContext);

		RandomTradeSource test = context.getBean(RandomTradeSource.class);
		assertNotNull(test);

		Iterator<FeedTradeBean> iterator = test.iterator();

		FeedTradeBean result = iterator.next();

		assertEquals("000000001", result.getSequenceNumber());
		assertNotNull(result.getTradeRef());
		assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20120210"),
				result.getTradeDate());
		assertNotNull(result.getAccountCode());
		assertEquals("FRUITEX", result.getExchangeCode());
		assertEquals("FRUIT", result.getProductType());
		assertNotNull(result.getProductCode());
		assertTrue("B".equals(result.getSide()) || "S".equals(result.getSide()));
		assertTrue(result.getQuantity() >= 10);
		assertTrue(result.getTradePrice() >= 90.0);
		assertNotNull(result.getCounterpartyCode());

		result = iterator.next();

		assertEquals("000000002", result.getSequenceNumber());
		assertNotNull(result.getTradeRef());
		assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20120210"),
				result.getTradeDate());
		assertNotNull(result.getAccountCode());
		assertEquals("FRUITEX", result.getExchangeCode());
		assertEquals("FRUIT", result.getProductType());
		assertNotNull(result.getProductCode());
		assertTrue("B".equals(result.getSide()) || "S".equals(result.getSide()));
		assertTrue(result.getQuantity() >= 10);
		assertTrue(result.getTradePrice() >= 90.0);
		assertNotNull(result.getCounterpartyCode());
	}
}
