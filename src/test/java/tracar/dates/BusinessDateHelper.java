package tracar.dates;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import tracar.DBTestUtils;

public class BusinessDateHelper {

	final DBTestUtils dbTestUtils;
	
	public BusinessDateHelper(DBTestUtils dbTestUtils) {
		this.dbTestUtils = dbTestUtils;
	}
	
	public void setBusinessDates(Date previous, Date current, Date next) {
		
		SessionFactory sessionFactory = dbTestUtils.createSessionFactory(
				("tracar/dates/Hibernate.cfg.xml"));
		
		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();

		Criteria businessDateQuery = session.createCriteria(
				BusinessDateBean.class);
		
		BusinessDateBean businessDate = 
				(BusinessDateBean) businessDateQuery.uniqueResult();

		businessDate.setPrevious(previous);
		businessDate.setCurrent(current);
		businessDate.setNext(next);

		session.save(businessDate);
		
		trn.commit();
		sessionFactory.close();
	}
	
	
}
