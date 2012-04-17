package tracar.dates;

import java.util.Date;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tracar.util.CustomPropertyConfigurer;

public class BusinessDateLoaderSpring implements Runnable, BusinessDate {

	private Properties properties;
	
	private BusinessDate businessDate;
	
	@Override
	public void run() {
		
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] { "tracar/dates/LoadBusinessDate.spg.xml" }, 
						false);

		if (properties != null) {
			context.addBeanFactoryPostProcessor(
					new CustomPropertyConfigurer(properties));
		}
		
		context.refresh();
		
		Runnable main = context.getBean("loadBusinessDate", Runnable.class);
		
		main.run();
		
		businessDate = (BusinessDate) main;
		
		context.close();
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public Date getPrevious() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return businessDate.getPrevious();
	}
	
	@Override
	public Date getCurrent() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return businessDate.getCurrent();
	}
	
	@Override
	public Date getNext() {
		BusinessDate businessDate = this.businessDate;
		if (businessDate == null) {
			return null;
		}
		return businessDate.getNext();
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
