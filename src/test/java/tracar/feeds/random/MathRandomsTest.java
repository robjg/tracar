package tracar.feeds.random;

import org.junit.Assert;
import org.junit.Test;

public class MathRandomsTest {

	@Test
	public void testIntegerRange() {
		
		MathRandoms test = new MathRandoms();
		
		Assert.assertEquals(0, 
				test.integerInRange(0, 1, 0.49));
		
		Assert.assertEquals(1, 
				test.integerInRange(0, 1, 0.51));
		
		Assert.assertEquals(42, 
				test.integerInRange(42, 42, 0.0));
		
		Assert.assertEquals(42, 
				test.integerInRange(42, 42, 1.0));
		
		Assert.assertEquals(-100, 
				test.integerInRange(-100, 200, 0.0));
		
		Assert.assertEquals(200, 
				test.integerInRange(-100, 200, 1.0));
		
		double halfRandom = (50000.0 - 100) / (100000 - 100);
		
		Assert.assertEquals(50000, 
				test.integerInRange(100, 100000, halfRandom));
		
		double thirdRandom = (33333.0 - 100) / (100000 - 100);
		
		Assert.assertEquals(33333, 
				test.integerInRange(100, 100000, thirdRandom));
	}
	
	@Test
	public void testDoubleFromMidpoint() {
				
		MathRandoms test = new MathRandoms();
		
		Assert.assertEquals(5.0, 
				test.doubleFromMidpoint(5, 5, 0.5), 0.001);
		
		Assert.assertEquals(0.0, 
				test.doubleFromMidpoint(5, 5, 0.0), 0.001);
		
		Assert.assertEquals(10.0, 
				test.doubleFromMidpoint(5, 5, 1.0), 0.001);
	}
}
