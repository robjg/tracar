package tracar.feeds.random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class QuantityGeneratorTest {

	@Test
	public void testDefault() {
		
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.integerInRange(1, 1000)
				).thenReturn(501).thenReturn(333);
		
		QuantityGenerator test = new QuantityGenerator(randoms);
		
		Assert.assertEquals(500, test.generateQuantity());
		
		Assert.assertEquals(330, test.generateQuantity());
	}

	public void testBigNumbers() {
		
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.integerInRange(100, 100000)
				).thenReturn(50100).thenReturn(33333);
		
		QuantityGenerator test = new QuantityGenerator(randoms);
		test.setMaxQuantity(100000);
		test.setMinQuantity(100);
		
		Assert.assertEquals(50000, test.generateQuantity());
		
		Assert.assertEquals(33000, test.generateQuantity());
	}
}
