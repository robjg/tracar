package tracar.dates;

import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tracar.util.CustomPropertyConfigurer;

public class RollBusinessDateSpring implements Runnable {

	private Properties properties;
	
	@Override
	public void run() {
		
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext(
						new String[] { "tracar/dates/RollBusinessDate.spg.xml" }, 
						false);

		if (properties != null) {
			context.addBeanFactoryPostProcessor(
					new CustomPropertyConfigurer(properties));
		}
		
		context.refresh();
		
		Runnable main = context.getBean("rollBusinessDate", Runnable.class);
		
		main.run();
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
