package tracar.feeds.random;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

public class TradeRefGenerator {
	
	private Randoms randoms;
	
	private String format = "000000";

	private final Set<Integer> usedRefs = new HashSet<Integer>();
	
	public TradeRefGenerator(Randoms randoms) {
		this.randoms = randoms;
	}
	
	public TradeRefGenerator() {
		this(new MathRandoms());
	}
	
	public String nextTradeRef() {
		
		while (true) {
			Integer nextRef = randoms.integerInRange(50000, 999999); 
			if (usedRefs.contains(nextRef)) {
				continue;
			}
			else {
				usedRefs.add(nextRef);
				return new DecimalFormat(format).format(nextRef);
			}
		}		
	}
	
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
}
