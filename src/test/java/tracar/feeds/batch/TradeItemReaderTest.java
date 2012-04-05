package tracar.feeds.batch;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tracar.feeds.FeedTradeBean;
import tracar.feeds.batch.TradeItemReader;

public class TradeItemReaderTest {

	@Test
	public void testRead() throws Exception {
		
		FeedTradeBean trade = new FeedTradeBean();
		
		List<FeedTradeBean> trades = Arrays.asList(trade);
		
		TradeItemReader test = new TradeItemReader();
		test.setTradeSource(trades);
		FeedTradeBean result = test.read();
		
		assertTrue(result == trade);
		assertNull(test.read());
	}
	
}
