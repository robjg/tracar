package tracar.feeds.random;

import org.junit.Assert;
import org.junit.Test;


public class SequenceNumberGeneratorTest {

	@Test
	public void testSimpleNumbers() {
		
		SequenceNumberGenerator test = new SequenceNumberGenerator();
				
		Assert.assertEquals("000000001", test.nextTradeRef());
		Assert.assertEquals("000000002", test.nextTradeRef());
		Assert.assertEquals("000000003", test.nextTradeRef());
		
		test.setStartNumber(1000);
		
		Assert.assertEquals("000001000", test.nextTradeRef());
		Assert.assertEquals("000001001", test.nextTradeRef());
		Assert.assertEquals("000001002", test.nextTradeRef());
	}
}
