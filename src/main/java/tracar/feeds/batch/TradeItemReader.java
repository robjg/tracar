package tracar.feeds.batch;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import tracar.feeds.FeedTradeBean;

public class TradeItemReader implements ItemReader<FeedTradeBean> {

	private Iterable<FeedTradeBean> tradeSource;
	
	private Iterator<FeedTradeBean> iterator;
	
	@Override
	public FeedTradeBean read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		
		if (iterator == null) {
			iterator = tradeSource.iterator();
		}
		
		if (!iterator.hasNext()) {
			return null;
		}
		
		return iterator.next();
	
	}

	public Iterable<FeedTradeBean> getTradeSource() {
		return tradeSource;
	}

	public void setTradeSource(Iterable<FeedTradeBean> tradeSource) {
		this.tradeSource = tradeSource;
	}

}
