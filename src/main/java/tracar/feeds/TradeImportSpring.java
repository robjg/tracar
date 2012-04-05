package tracar.feeds;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TradeImportSpring implements Runnable {

	
	@Override
	public void run() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"tracar/feeds/TradeImport.spg.xml");

		Runnable main = context.getBean("tradeImport", Runnable.class);
		
		main.run();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
