package tracar.feeds.random;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tracar.feeds.FeedTradeBean;

public class RandomTradeFeedTest {

	@Test
	public void testRun() throws ParseException {
		
		SeedTradeBean seed1 = new SeedTradeBean();
				
		seed1.setExchangeCode("XCME");
		seed1.setSecurityType("FUT");
		seed1.setSecurityCode("EDZ5");
		seed1.setSeedPrice(99.5);
		seed1.setMaxPriceMovement(0.1);

		List<SeedTradeBean> seeds = Arrays.asList(seed1);
				
		RandomTradeSource test = new RandomTradeSource();

		test.setTradeDate(new SimpleDateFormat("yyyyMMdd").parse("20120208"));

		test.setSeedTrades(seeds);
		
		test.setAccounts(Arrays.asList("A101"));
		test.setCounterparties(Arrays.asList("ABC"));
		
		FeedTradeBean result = test.iterator().next();
		
		assertEquals("000000001", result.getSequenceNumber());
		assertNotNull(result.getTradeRef());
		assertEquals(new SimpleDateFormat("yyyyMMdd").parse("20120208"), 
				result.getTradeDate());
		assertEquals("A101", result.getAccountCode());
		assertEquals("XCME", result.getExchangeCode());
		assertEquals("FUT", result.getProductType());
		assertEquals("EDZ5", result.getProductCode());
		assertTrue("B".equals(result.getSide()) || "S".equals(result.getSide()));
		assertTrue(result.getQuantity() >= 10);
		assertTrue(result.getTradePrice() >= 99.4 );
		assertEquals("ABC", result.getCounterpartyCode());
	}
}
