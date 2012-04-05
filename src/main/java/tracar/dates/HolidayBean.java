package tracar.dates;

import java.util.Date;

public class HolidayBean implements Holiday {

	private long id;
	
	private Date holiday;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getHoliday() {
		return holiday;
	}

	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}
}
