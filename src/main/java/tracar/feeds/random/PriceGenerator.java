package tracar.feeds.random;

import java.util.HashMap;
import java.util.Map;

public class PriceGenerator {

	private final Randoms randoms;
	
	private final Map<SeedTradeBean, Double> lastPrices = 
			new HashMap<SeedTradeBean, Double>(); 

	public PriceGenerator(Randoms randoms) {
		this.randoms = randoms;
	}
	
	public double generatePriceFor(SeedTradeBean seed) {
		
		Double last = lastPrices.get(seed);
		if (last == null) {
			last = seed.getSeedPrice();
		}
		
		last = randoms.doubleFromMidpoint(last, seed.getMaxPriceMovement());
		
		double scale = Math.pow(10, seed.getPriceScale());
		
		last = ((double) Math.round(last * scale)) / scale;
		
		lastPrices.put(seed, last);
		
		return last;
	}	
}
