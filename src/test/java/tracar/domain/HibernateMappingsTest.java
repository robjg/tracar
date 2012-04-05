package tracar.domain;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.oddjob.arooa.utils.DateHelper;

import tracar.DBTestUtils;

public class HibernateMappingsTest {

	static DBTestUtils dbTestUtils;
	
	@BeforeClass
	public static void setUp() {

		dbTestUtils = new DBTestUtils();
		
		dbTestUtils.setUp("domain");
	}

	@AfterClass
	public static void tearDown() {
		dbTestUtils.tearDown();
	}

	@Test
	public void testHibernateMapping() throws ParseException {

		SessionFactory sessionFactory = dbTestUtils.createSessionFactory(
				"tracar/domain/Hibernate.cfg.xml");

		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();
		
		CurrencyBean currency = new CurrencyBean();
		currency.setCode("GBP");
		currency.setDescription("British Pound");
		
		ProductBean product = new ProductBean();
		product.setCode("APL");
		product.setDescription("APPLE");
		product.setType("FRUIT");
		product.setCurrency(currency);
		
		
		AccountBean account = new AccountBean();
		account.setCode("A00100");
		account.setDescription("Farmer Pickles");
		
		TradeBean trade = new TradeBean();
		trade.setAccount(account);
		trade.setProduct(product);
		trade.setQuantity(20);
		trade.setTradePrice(0.57);
		trade.setTradeDate(DateHelper.parseDate("2012-03-17"));
		
		session.save(currency);
		session.save(product);
		session.save(account);
		session.save(trade);

		long id = trade.getId();

		trn.commit();
		session.close();

		// Check
		
		session = sessionFactory.openSession();
		trn = session.beginTransaction();

		TradeBean resultTrade = (TradeBean) session.get(
				TradeBean.class, id);

		Assert.assertNotNull(resultTrade);
		Assert.assertEquals(20, resultTrade.getQuantity());

		AccountBean resultAccount = resultTrade.getAccount();
		
		Assert.assertNotNull(resultAccount);

		ProductBean resultProduct = resultTrade.getProduct();
		
		Assert.assertNotNull(resultProduct);
		Assert.assertEquals("APL", resultProduct.getCode());
		
		CurrencyBean resultCurrency = resultProduct.getCurrency();
		Assert.assertNotNull(resultCurrency);
		Assert.assertEquals("GBP", resultCurrency.getCode());
		
		
		// Finish
		
		session.delete(resultTrade);
		trn.commit();
		
		session.close();

		sessionFactory.close();
	}
}
