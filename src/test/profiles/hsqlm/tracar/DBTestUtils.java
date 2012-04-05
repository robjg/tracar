package tracar;

import junit.framework.Assert;

import org.oddjob.Oddjob;
import org.oddjob.arooa.xml.XMLConfiguration;
import org.oddjob.state.ParentState;

public class DBTestUtils extends DBTestUtilsBase {
			
	public void setUp(String setupConfigPrefix) {
		bootDBInstallOddjob(setupConfigPrefix);
	}
		
	public void tearDown() {
		
		Oddjob oddjob = new Oddjob();
		oddjob.setConfiguration(new XMLConfiguration(
				"tracar/dbshutdown.oj.xml", getClass().getClassLoader()));
		oddjob.run();
		
		Assert.assertEquals(ParentState.COMPLETE, 
				oddjob.lastStateEvent().getState());		
	}	
}
