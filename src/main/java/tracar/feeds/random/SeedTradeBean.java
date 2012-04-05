package tracar.feeds.random;

public class SeedTradeBean {

	private String exchangeCode;
	
	private String securityType;
	
	private String securityCode;
	
	private double seedPrice;
	
	private double maxPriceMovement;
	
	private int priceScale = 2;

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getSecurityType() {
		return securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String instrumentCode) {
		this.securityCode = instrumentCode;
	}

	public double getSeedPrice() {
		return seedPrice;
	}

	public void setSeedPrice(double seedPrice) {
		this.seedPrice = seedPrice;
	}

	public double getMaxPriceMovement() {
		return maxPriceMovement;
	}

	public void setMaxPriceMovement(double maxPriceMovement) {
		this.maxPriceMovement = maxPriceMovement;
	}

	public int getPriceScale() {
		return priceScale;
	}

	public void setPriceScale(int priceScale) {
		this.priceScale = priceScale;
	}
	
}
