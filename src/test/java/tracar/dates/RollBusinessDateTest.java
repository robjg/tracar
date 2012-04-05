package tracar.dates;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.oddjob.arooa.utils.DateHelper;
import org.oddjob.schedules.Schedule;
import org.oddjob.schedules.schedules.DateSchedule;

public class RollBusinessDateTest {

	@Test
	public void testNextBusinessDate() throws ParseException {
		
		DateSchedule goodFriday = new DateSchedule();
		goodFriday.setOn("2012-04-06");
		 
		DateSchedule easterMonday = new DateSchedule();
		easterMonday.setOn("2012-04-09");
		 
		Schedule[] holidays = new Schedule[] {
		            goodFriday, easterMonday };
		
		Date date = new RollBusinessDate().nextBusinessDate(
				DateHelper.parseDate("2012-04-05"), holidays);
		
		assertEquals(DateHelper.parseDate("2012-04-10"), date);
	}
}
