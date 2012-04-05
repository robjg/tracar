package tracar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TracarTestUtils {

	protected final Properties tracarProps;
	
	public TracarTestUtils() {
		
		InputStream input = getClass().getClassLoader().getResourceAsStream(
				"tracar.properties");
		if (input == null) {
			throw new NullPointerException("" +
					"No tracar.properties on classpath.");
		}
		tracarProps = new Properties();
		try {
			tracarProps.load(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Properties getTracarProps() {
		return tracarProps;
	}
	
}
