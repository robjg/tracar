package tracar.feeds;

import java.text.ParseException;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oddjob.arooa.convert.ArooaConversionException;
import org.oddjob.arooa.reflect.ArooaPropertyException;
import org.oddjob.arooa.registry.BeanDirectory;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;
import tracar.domain.AccountBean;
import tracar.domain.CurrencyBean;
import tracar.domain.ProductBean;

public class TradeImportSpringTest {

	static DBTestUtils dbTestUtils;
	
	@BeforeClass
	public static void setUp() throws ParseException {

		dbTestUtils = new DBTestUtils();
		
		dbTestUtils.setUp("domain");
		dbTestUtils.setUp("feeds");

		setUpData();
		
	}

	@AfterClass
	public static void tearDown() {
		new DBTestUtils().tearDown();
	}

	static void setUpData() throws ParseException {
		
		SessionFactory sessionFactory = dbTestUtils.createSessionFactory(
				"tracar/domain/Hibernate.cfg.xml", 
				"tracar/feeds/Hibernate.cfg.xml");

		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();

		FeedTradeBean trade1 = new FeedTradeBean();
		trade1.setSequenceNumber("00000001");
		trade1.setTradeRef("ABC201203060001");
		trade1.setTradeDate(DateHelper.parseDate("2012-03-06"));
		trade1.setAccountCode("A10101");
		trade1.setExchangeCode("FRUITEX");
		trade1.setProductType("FRUIT");
		trade1.setProductCode("APL");
		trade1.setSide("S");
		trade1.setQuantity(42);
		trade1.setTradePrice(57.4);
		trade1.setCounterpartyCode("FMR1");

		FeedTradeBean trade2 = new FeedTradeBean();
		trade2.setSequenceNumber("00000002");
		trade2.setTradeRef("ABC201203060002");
		trade2.setTradeDate(DateHelper.parseDate("2012-03-06"));
		trade2.setAccountCode("A10101");
		trade2.setExchangeCode("FRUITEX");
		trade2.setProductType("FRUIT");
		trade2.setProductCode("BAN");
		trade2.setSide("B");
		trade2.setQuantity(72);
		trade2.setTradePrice(24.3);
		trade2.setCounterpartyCode("FMR1");

		AccountBean account = new AccountBean();
		account.setCode("A10101");
		account.setDescription("FARMER PICKLES");
		    
		CurrencyBean currency = new CurrencyBean();
		currency.setCode("GBP");
		currency.setDescription("BRITISH POUND");
		
		ProductBean product1 = new ProductBean();
		product1.setCode("APL");
		product1.setType("FRUIT");
		product1.setDescription("APPLE");
		product1.setCurrency(currency);

		ProductBean product2 = new ProductBean();
		product2.setCode("BAN");
		product2.setType("FRUIT");
		product2.setDescription("BANANA");
		product2.setCurrency(currency);
		
		session.save(currency);
		session.save(product1);
		session.save(product2);
		session.save(account);
		session.save(trade1);
		session.save(trade2);
		
		trn.commit();
		sessionFactory.close();
	}
		
	@Test
	public void testTradeImport() throws ArooaPropertyException, ArooaConversionException, ParseException {
		
		new TradeImportSpring().run();
		
		BeanDirectory beans = dbTestUtils.execTextSQL(
				"select TRADE.TRADE_PRICE, TRADE.QUANTITY, " +
				"  PRODUCT.CODE" +
				" from ${tracar.db.schema}.TRADE, " +
				"   ${tracar.db.schema}.PRODUCT," +
				"   ${tracar.db.schema}.ACCOUNT" +
				" where TRADE.PRODUCT_ID = PRODUCT.ID " +
				" and TRADE.ACCOUNT_ID = ACCOUNT.ID ");
		
		Assert.assertEquals(2, beans.lookup("results.rowCount"));
				
		Assert.assertEquals(57.4, beans.lookup("results.rows[0].TRADE_PRICE"));
		Assert.assertEquals(-42, beans.lookup("results.rows[0].QUANTITY"));
		Assert.assertEquals("APL", beans.lookup("results.rows[0].CODE"));
		
		Assert.assertEquals(24.3, beans.lookup("results.rows[1].TRADE_PRICE"));
		Assert.assertEquals(72, beans.lookup("results.rows[1].QUANTITY"));
		Assert.assertEquals("BAN", beans.lookup("results.rows[1].CODE"));
	}
}
