package tracar.domain;

public class ProductBean implements Product {

	private long id;
	
	private String type;
	
	private String code;
	
	private String description;
	
	private CurrencyBean currency;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
	@Override
	public CurrencyBean getCurrency() {
		return currency;
	}
	
	public void setCurrency(CurrencyBean currency) {
		this.currency = currency;
	}
}
