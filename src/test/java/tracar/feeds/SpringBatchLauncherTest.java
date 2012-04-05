package tracar.feeds;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.oddjob.arooa.registry.BeanDirectory;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;

public class SpringBatchLauncherTest {

	@Test
	public void testReallySimpleLaunch() throws Exception {
		
		SpringBatchLauncher test = new SpringBatchLauncher();
		test.setResources(new String[] {
				"tracar/feeds/batch/DummyItemReader.spg.xml",
				"tracar/feeds/batch/DummyItemWriter.spg.xml",
				"tracar/feeds/SpringBatchMain.spg.xml",
			});
		
		Integer result = test.call();
		
		assertEquals(new Integer(0), result);
	}
	
	@Test
	public void testTradeFileReader() throws Exception {
		
		DBTestUtils testUtils = new DBTestUtils();
		testUtils.setUp("feeds");
				
		SpringBatchLauncher testCreate = new SpringBatchLauncher();
		testCreate.setResources(new String[] {
				"tracar/feeds/random/RandomTradeSource.spg.xml",
				"tracar/feeds/batch/TradeFeedFileWriter.spg.xml",
				"tracar/feeds/SpringBatchMain.spg.xml",
			});
				
		Properties props = new Properties();
		props.setProperty("tracar.feed.file.name", 
				System.getProperty("tracar.feed.dir", "./work/feeds") + 
						"/TestTrades.txt");
		
		testCreate.setProperties(props);
		testCreate.setExport("tradeDate", 
				DateHelper.parseDate("2012-03-24"));

		Integer result = testCreate.call();
		
		assertEquals(new Integer(0), result);

		Properties tracarProps = testUtils.getTracarProps();		
		String schema = tracarProps.getProperty("tracar.db.schema");
				
		DataSource dataSource = testUtils.getDataSource();
		
		
		SpringBatchLauncher testRead = new SpringBatchLauncher();
		testRead.setResources(new String[] {
				"tracar/feeds/batch/SpringDataSource.spg.xml",
				"tracar/feeds/batch/TradeFeedFileReader.spg.xml",
				"tracar/feeds/batch/TradeFeedDBWriter.spg.xml",
				"tracar/feeds/SpringBatchMain.spg.xml",
			});
				
		props.setProperty("tracar.db.schema", schema);
		
		testRead.setProperties(props);
		testRead.setExport("datasource", dataSource);
		result = testRead.call();
		
		assertEquals(new Integer(0), result);
		
		BeanDirectory lookup = testUtils.execTextSQL(
				"select * from " + schema + ".FEED_TRADE");
		
		assertEquals(100, lookup.lookup("results.rowCount"));
		
		testUtils.execTextSQL("truncate table ${tracar.db.schema}.FEED_TRADE");
		
		testUtils.tearDown();
	}
}
