package tracar.feeds.random;

public class MathRandoms implements Randoms {

	@Override
	public int integerInRange(int lowerBound, int upperBound) {
		
		return integerInRange(lowerBound, upperBound, Math.random());
	}
	
	int integerInRange(int lowerBound, int upperBound, double random) {
		
		int range = upperBound - lowerBound;
		
		int number = (int) Math.round(random * range);

		return number + lowerBound;	
	}
	
	@Override
	public double doubleFromMidpoint(double middle, double range) {
		return doubleFromMidpoint(middle, range, Math.random());
	}

	double doubleFromMidpoint(double middle, double range, double random) {
		return middle + (random * 2 * range) - 
				range;
	}
}
