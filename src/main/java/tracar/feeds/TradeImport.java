package tracar.feeds;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import tracar.domain.AccountBean;
import tracar.domain.ProductBean;
import tracar.domain.TradeBean;

public class TradeImport implements Runnable {

	private static final Logger logger = Logger.getLogger(
			TradeImport.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void run() {
		
		Session session = sessionFactory.openSession();
		Transaction trn = session.beginTransaction();
		
		Criteria feedTradesQuery = session.createCriteria(FeedTradeBean.class);
		
		@SuppressWarnings("unchecked")
		List<FeedTradeBean> feedTrades = feedTradesQuery.list();
		
		for (FeedTradeBean feedTrade: feedTrades ) {
			
			TradeBean trade = new TradeBean();
			
			Criteria productQuery = session.createCriteria(ProductBean.class) 
					.add(Restrictions.eq(
							"type", feedTrade.getProductType()))
					.add(Restrictions.eq(
							"code", feedTrade.getProductCode()));
			
			ProductBean product = (ProductBean) productQuery.uniqueResult();
			if (product == null) {
				logger.warn("No product for Feed Trade Id " + 
						feedTrade.getId());
				continue;
			}
			
			trade.setProduct(product);
			
			
			Criteria accountQuery = session.createCriteria(AccountBean.class) 
					.add(Restrictions.eq(
							"code", feedTrade.getAccountCode()));
			
			
			AccountBean account = (AccountBean) accountQuery.uniqueResult();
			if (account == null) {
				logger.warn("No account for Feed Trade Id " + 
						feedTrade.getId());
				continue;
			}
			
			trade.setAccount(account);
			
			trade.setTradeDate(feedTrade.getTradeDate());
			trade.setTradePrice(feedTrade.getTradePrice());
			trade.setQuantity(feedTrade.getQuantity() * 
					(feedTrade.getSide().equals("S") ? -1 : 1));
			
			session.save(trade);
			session.delete(feedTrade);
		}		
		
		trn.commit();
		session.close();
	}	
}
