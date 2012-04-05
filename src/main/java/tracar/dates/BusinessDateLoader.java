package tracar.dates;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BusinessDateLoader implements Runnable, BusinessDate {

	private SessionFactory sessionFactory;

	private BusinessDate businessDate;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void run() {

		Session session = sessionFactory.openSession();
		
		Criteria businessDateQuery = session.createCriteria(
				BusinessDateBean.class);
		
		businessDate = 
				(BusinessDate) businessDateQuery.uniqueResult();

		
		session.close();
	}
		
	@Override
	public Date getPrevious() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return new Date(businessDate.getPrevious().getTime());
	}
	
	@Override
	public Date getCurrent() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return new Date(businessDate.getCurrent().getTime());
	}
	
	@Override
	public Date getNext() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return new Date(businessDate.getNext().getTime());
	}
}
