package tracar.feeds;

import java.util.Properties;
import java.util.concurrent.Callable;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tracar.util.CustomContextBuilder;
import tracar.util.CustomPropertyConfigurer;

public class SpringBatchLauncher implements Callable<Integer> {

	private String name;
	
	private CustomContextBuilder parentContext = new CustomContextBuilder();

	private String[] resources;
	
	private Properties properties;
	
	public Integer call() throws Exception {

		if (resources == null) {
			throw new NullPointerException("No configuration resource!");
		}
				
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext(resources, 
						false, parentContext.buildContext());

		if (properties != null) {
			context.addBeanFactoryPostProcessor(
					new CustomPropertyConfigurer(properties));
		}
		context.refresh();
		
		
		try {			
			JobLauncher launcher = context.getBean(JobLauncher.class);
			Job job = context.getBean(Job.class);
						
			JobExecution jobExecution = launcher.run(job, new JobParameters());
			
			if (jobExecution.getExitStatus().equals(ExitStatus.FAILED)) {
				return 1;
			} else {
				return 0;
			}
		} finally {
			context.close();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setExport(String key, Object value) {
		parentContext.register(key, value);
	}

	public String[] getResources() {
		return resources;
	}

	public void setResources(String[] resources) {
		this.resources = resources;
	}
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		if (name == null) {
			return getClass().getSimpleName();
		}
		else {
			return name; 
		}
	}
}