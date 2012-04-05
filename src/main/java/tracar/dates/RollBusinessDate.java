package tracar.dates;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.oddjob.arooa.utils.DateHelper;
import org.oddjob.schedules.Schedule;
import org.oddjob.schedules.ScheduleContext;
import org.oddjob.schedules.ScheduleList;
import org.oddjob.schedules.ScheduleResult;
import org.oddjob.schedules.schedules.BrokenSchedule;
import org.oddjob.schedules.schedules.DailySchedule;
import org.oddjob.schedules.schedules.DateSchedule;
import org.oddjob.schedules.schedules.WeeklySchedule;
import org.oddjob.schedules.units.DayOfWeek;

import tracar.feeds.TradeImport;

public class RollBusinessDate implements Runnable {

	private static final Logger logger = Logger.getLogger(
			TradeImport.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void run() {

		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();
		
		Criteria businessDateQuery = session.createCriteria(
				BusinessDateBean.class);
		
		BusinessDateBean businessDate = 
				(BusinessDateBean) businessDateQuery.uniqueResult();

		Criteria holidayQuery = session.createCriteria(HolidayBean.class);
		holidayQuery.addOrder(Order.asc("holiday"));
		
		@SuppressWarnings("unchecked")
		List<Holiday> resultHolidays = 
				(List<Holiday>) holidayQuery.list();

		Schedule[] holidayArray = new Schedule[resultHolidays.size()];
		
		int i = 0;
		for (Holiday holiday : resultHolidays) {
			DateSchedule date = new DateSchedule();
			date.setOn(DateHelper.formatDate(holiday.getHoliday()));
			holidayArray[i++] = date;
		}
		
		businessDate.setPrevious(businessDate.getCurrent());
		businessDate.setCurrent(businessDate.getNext());
		businessDate.setNext(nextBusinessDate(
				businessDate.getNext(), holidayArray));
		
		logger.info("Setting next business date " + businessDate.getNext());
		
		session.save(businessDate);
		
		trn.commit();
		session.close();
	}
	
	Date nextBusinessDate(Date after, Schedule[] holidays) {
		
		
		DailySchedule dailySchedule = new DailySchedule();
		 
		WeeklySchedule weeklySchedule = new WeeklySchedule();
		weeklySchedule.setFrom(DayOfWeek.Days.MONDAY);
		weeklySchedule.setTo(DayOfWeek.Days.FRIDAY);
		weeklySchedule.setRefinement(dailySchedule);
		
		ScheduleList holidayList = new ScheduleList();
		holidayList.setSchedules(holidays);
		
		BrokenSchedule brokenSchedule = new BrokenSchedule();
		brokenSchedule.setSchedule(weeklySchedule);
		brokenSchedule.setBreaks(holidayList);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(after);
		cal.add(Calendar.DATE, 1);
		
		ScheduleContext context = new ScheduleContext(
				cal.getTime()); 
		
		ScheduleResult scheduleResult = brokenSchedule.nextDue(context);
		
		Date nextBusinessDate = scheduleResult.getFromDate();
		
		return nextBusinessDate;
	}
	
	
}
