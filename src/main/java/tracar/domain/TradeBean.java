package tracar.domain;

import java.util.Date;


public class TradeBean implements Trade {
	
	private long id;
	
	private AccountBean account;
	
	private ProductBean product;
	
	private Date tradeDate;
	
	private double tradePrice;
	
	private int quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public AccountBean getAccount() {
		return account;
	}
	
	public void setAccount(AccountBean account) {
		this.account = account;
	}
	
	@Override
	public ProductBean getProduct() {
		return product;
	}
	
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	@Override
	public Date getTradeDate() {
		return tradeDate;
	}
	
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Override
	public double getTradePrice() {
		return tradePrice;
	}
	
	public void setTradePrice(double getPrice) {
		this.tradePrice = getPrice;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
