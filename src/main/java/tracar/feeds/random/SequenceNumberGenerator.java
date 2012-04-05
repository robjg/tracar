package tracar.feeds.random;

import java.text.DecimalFormat;

public class SequenceNumberGenerator {

	private int lastCount = 1;
	
	private String format = "000000000";
	
	public String nextTradeRef() {
		
		DecimalFormat decimalFormat = new DecimalFormat(format);
		
		return decimalFormat.format(lastCount++);
	}
	
	public void setStartNumber(int startNumber) {
		this.lastCount = startNumber;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getFormat() {
		return format;
	}
}
