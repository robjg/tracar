package tracar.util;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DBCPService {

	private Logger logger = Logger.getLogger(DBCPService.class);
	
	private String name;
	
	private String driver;
	
	private String username;
	
	private String password;
	
	private String url;

	private ClassLoader classLoader;
	
	private BasicDataSource dataSource;
	
	
	public void start() throws SQLException {

		logger.info("Creating data source for " + url + " user " + username);
		
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setDriverClassLoader(classLoader);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		// test connection.
		dataSource.getConnection().close();
	}

	public synchronized void stop() throws SQLException {
		
		dataSource.close();
		dataSource = null;
		
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get this connections driver class name.
	 * 
	 * @return The driver class name.
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Set this connections dirver class name.
	 * 
	 * @param driver The driver class name.
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * Get the password.
	 * 
	 * @return The password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password.
	 * 
	 * @param password The password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the url.
	 * 
	 * @return The url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set the url.
	 * 
	 * @param url The url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Get the username.
	 * 
	 * @return The username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username.
	 * 
	 * @param username The username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Inject
	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (name == null) {
			return getClass().getSimpleName() + 
					(url == null ? "" : " for " + url) + 
					(username == null ? "" : " as " + username);
		}
		else {
			return name;
		}
	}
}
