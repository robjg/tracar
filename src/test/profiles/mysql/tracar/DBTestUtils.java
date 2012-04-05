package tracar;


public class DBTestUtils extends DBTestUtilsBase {

	public void setUp(String setupConfigPrefix) {
		
	}
		
	public void tearDown() {
		
		bootDBInstallOddjob("truncate");
	}	
}
