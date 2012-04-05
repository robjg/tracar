package tracar.feeds.random;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import tracar.feeds.FeedTradeBean;

public class RandomTradeSource implements Iterable<FeedTradeBean> {

	private final Randoms randoms;
	
	private Date tradeDate;
	
	private List<SeedTradeBean> seedTrades;
	
	private List<String> accounts;
	
	private List<String> counterparties;

	public RandomTradeSource(Randoms randoms) {
		this.randoms = randoms;
	}
	
	public RandomTradeSource() {
		this(new MathRandoms());
	}
	
	@Override
	public Iterator<FeedTradeBean> iterator() {
		
		final TradeFactory factory = new TradeFactory();
		
		return new Iterator<FeedTradeBean>() {
			
			@Override
			public boolean hasNext() {
				return true;
			}
			
			@Override
			public FeedTradeBean next() {
				return factory.createTrade();
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
		
	}
	
	public List<SeedTradeBean> getSeedTrades() {
		return seedTrades;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public List<String> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<String> accounts) {
		this.accounts = accounts;
	}

	public void setSeedTrades(List<SeedTradeBean> seedTrades) {
		this.seedTrades = seedTrades;
	}

	public List<String> getCounterparties() {
		return counterparties;
	}

	public void setCounterparties(List<String> counterparties) {
		this.counterparties = counterparties;
	}

	class TradeFactory {

		SequenceNumberGenerator sequenceNumberGenerator = 
				new SequenceNumberGenerator();
		
		TradeRefGenerator tradeRefGenerator = new TradeRefGenerator(randoms);
		
		QuantityGenerator quantityGenerator = new QuantityGenerator(randoms);
		
		PriceGenerator priceGenerator = new PriceGenerator(randoms);
		
		FeedTradeBean createTrade() {
			
			FeedTradeBean feedTrade = new FeedTradeBean();

			feedTrade.setSequenceNumber(sequenceNumberGenerator.nextTradeRef());
			feedTrade.setTradeRef(tradeRefGenerator.nextTradeRef());
			feedTrade.setTradeDate(tradeDate);
			int accountsIndex = randoms.integerInRange(
					0, accounts.size() - 1);
			feedTrade.setAccountCode(accounts.get(accountsIndex));

			int seedIndex = randoms.integerInRange(0, seedTrades.size() - 1);
			
			SeedTradeBean seed = seedTrades.get(seedIndex);

			feedTrade.setExchangeCode(seed.getExchangeCode());
			feedTrade.setProductType(seed.getSecurityType());
			feedTrade.setProductCode(seed.getSecurityCode());
			feedTrade.setQuantity(quantityGenerator.generateQuantity());
			feedTrade.setSide(randoms.integerInRange(0, 1) == 0 ? "B" : "S");
			feedTrade.setTradePrice(priceGenerator.generatePriceFor(seed));
						
			if (counterparties != null) {

				int counterpartyIndex = randoms.integerInRange(
						0, counterparties.size() - 1);
				feedTrade.setCounterpartyCode(counterparties.get(counterpartyIndex));
			}
			
			return feedTrade;			
		}		
	}
}
