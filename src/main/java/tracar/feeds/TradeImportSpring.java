package tracar.feeds;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TradeImportSpring implements Runnable {

	
	@Override
	public void run() {
		
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext(
						"tracar/feeds/TradeImport.spg.xml");

		Runnable main = context.getBean("tradeImport", Runnable.class);
		
		main.run();
		
		context.close();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
}
