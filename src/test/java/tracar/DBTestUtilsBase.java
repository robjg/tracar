package tracar;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.oddjob.Oddjob;
import org.oddjob.arooa.ArooaValue;
import org.oddjob.arooa.registry.BeanDirectory;
import org.oddjob.arooa.xml.XMLConfiguration;
import org.oddjob.io.BufferType;
import org.oddjob.io.ResourceType;
import org.oddjob.state.ParentState;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

abstract public class DBTestUtilsBase extends TracarTestUtils {
	
	private final String driverClassName = 
			tracarProps.getProperty("tracar.db.driver");
	
	private final String url = 
			tracarProps.getProperty("tracar.db.url");

	private final String user = 
			tracarProps.getProperty("tracar.db.user");
	
	private final String password =
			tracarProps.getProperty("tracar.db.password");
	
	public DBTestUtilsBase() {
		if (driverClassName == null) {
			throw new NullPointerException("No Driver class name");
		}
		if (url == null) {
			throw new NullPointerException("No Driver URL");
		}
		if (user == null) {
			throw new NullPointerException("No Driver user");
		}
		if (password == null) {
			throw new NullPointerException("No Driver password");
		}
	}
	
	public Properties getHibernateProperties() {
		
		Properties properties = new Properties();
		properties.setProperty(Environment.DIALECT,
				tracarProps.getProperty("tracar.hibernate.dialect"));
		properties.setProperty(Environment.DEFAULT_SCHEMA, 
				tracarProps.getProperty("tracar.db.schema"));
		properties.setProperty(Environment.URL, 
				url);
		properties.setProperty(Environment.DRIVER, 
				driverClassName);
		properties.setProperty(Environment.USER, 
				user);
		properties.setProperty(Environment.PASS, 
				password);
		properties.setProperty(Environment.SHOW_SQL, 
				"true");
		
		return properties;
	}
	
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		
		return dataSource;
	}
		
	abstract public void setUp(String setupConfigPrefix);
		
	public void bootDBInstallOddjob(String setupConfigPrefix) {
		execOddjobInConfig("install/db/boot.oj.xml", setupConfigPrefix);
	}
	
	public void execOddjobInConfig(String path, String... args) {
		
		File configDir = new File(
				System.getProperty("tracar.config", "./src/main/config"));
		
		Oddjob oddjob = new Oddjob();
		oddjob.setFile(new File(configDir, path));
		oddjob.setArgs(args);
		
		oddjob.setProperties(tracarProps);
		oddjob.run();
		
		Assert.assertEquals(ParentState.COMPLETE, 
				oddjob.lastStateEvent().getState());		
	}	
	
	public BeanDirectory execTextSQL(String sql) {
		
		BufferType bufferType = new BufferType();
		try {
			bufferType.setText(sql);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		bufferType.configured();
		
		return execSQL(bufferType);
	}
	
	
	public BeanDirectory execResourceSQL(String resource) {
		
		ResourceType resourceType = new ResourceType();
		resourceType.setResource(resource);
		
		return execSQL(resourceType);
	}
	
	private BeanDirectory execSQL(ArooaValue type) {

		Oddjob oddjob = new Oddjob();
		oddjob.setConfiguration(new XMLConfiguration(
				"tracar/dbexec.oj.xml", getClass().getClassLoader()));		
		oddjob.setExport("sql", type);
		oddjob.run();
		
		Assert.assertEquals(ParentState.COMPLETE, 
				oddjob.lastStateEvent().getState());		
		
		return oddjob.provideBeanDirectory();		
	}
	
	abstract public void tearDown();
	
	public SessionFactory createSessionFactory(String... mappings) {
		
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(
				getHibernateProperties());

		ServiceRegistry serviceRegistry = serviceRegistryBuilder
				.buildServiceRegistry();

		Configuration configuration = new Configuration();
		configuration.setProperties(getHibernateProperties());
		for (String mapping : mappings) {
			configuration.configure(mapping);
		}

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
		
		return sessionFactory;
	}
}
