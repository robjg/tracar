package tracar.feeds.random;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PriceGeneratorTest {

	@Test
	public void testSimple() {
				
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.doubleFromMidpoint(5.2, 1.0)).thenReturn(5.0);
		Mockito.when(randoms.doubleFromMidpoint(5.0, 1.0)).thenReturn(4.7);
		Mockito.when(randoms.doubleFromMidpoint(4.7, 1.0)).thenReturn(5.5);
		
		PriceGenerator test = new PriceGenerator(randoms);
		
		SeedTradeBean seed = new SeedTradeBean();
		seed.setSeedPrice(5.2);
		seed.setMaxPriceMovement(1.0);
		
		Assert.assertEquals(5.0, test.generatePriceFor(seed), 0.001);
		Assert.assertEquals(4.7, test.generatePriceFor(seed), 0.001);
		Assert.assertEquals(5.5, test.generatePriceFor(seed), 0.001);
	}
	
	public void testScaling() {
		
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.doubleFromMidpoint(5.0, 1.0)).thenReturn(5.333333);
		
		PriceGenerator test = new PriceGenerator(randoms);
		
		SeedTradeBean seed = new SeedTradeBean();
		seed.setSeedPrice(5.0);
		seed.setMaxPriceMovement(1.0);
		
		Assert.assertEquals(5.33, test.generatePriceFor(seed), 0.001);
	}
	
	public void testBigNumberScaling() {
		
		Randoms randoms = Mockito.mock(Randoms.class);
		Mockito.when(randoms.doubleFromMidpoint(
				50000.0, 10000.0)).thenReturn(53333.0);
		
		PriceGenerator test = new PriceGenerator(randoms);
		
		SeedTradeBean seed = new SeedTradeBean();
		seed.setSeedPrice(50000.0);
		seed.setMaxPriceMovement(10000.0);
		seed.setPriceScale(-2);
		
		Assert.assertEquals(53300.0, test.generatePriceFor(seed), 0.001);
	}

}
