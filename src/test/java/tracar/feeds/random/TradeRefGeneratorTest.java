package tracar.feeds.random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TradeRefGeneratorTest {

	@Test
	public void testSimple() {
		
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.integerInRange(50000, 999999)
				).thenReturn(123456).thenReturn(123457);
		
		TradeRefGenerator test = new TradeRefGenerator(randoms);
				
		Assert.assertEquals("123456", test.nextTradeRef());
		Assert.assertEquals("123457", test.nextTradeRef());
	}
}
