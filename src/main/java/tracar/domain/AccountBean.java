package tracar.domain;

public class AccountBean implements Account {

	private long id;
	
	private String code;
	
	private String Description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	public static void main(String... args) {
		
	}}
