package tracar.feeds;

import java.util.Date;


public class FeedTradeBean {

	private long id;

	private String sequenceNumber;
	
	private String tradeRef;
	
	private Date tradeDate;
	
	private String accountCode;
	
	/** http://www.iso15022.org/MIC/homepageMIC.htm */
	private String exchangeCode;
	
	private String productType;
	
	private String productCode;
	
	private String side;
	
	private int quantity;
	
	private double tradePrice;
	
	private String counterpartyCode;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getTradeRef() {
		return tradeRef;
	}

	public void setTradeRef(String tradeRef) {
		this.tradeRef = tradeRef;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String account) {
		this.accountCode = account;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String type) {
		this.productType = type;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public String getCounterpartyCode() {
		return counterpartyCode;
	}

	public void setCounterpartyCode(String counterParty) {
		this.counterpartyCode = counterParty;
	}
}
