package tracar.domain;

import java.util.Date;


public interface Trade {

	public Product getProduct();

	public Account getAccount();
	
	public Date getTradeDate();
	
	public double getTradePrice();
	
	public int getQuantity();
	
}
