package tracar.feeds.random;

public interface Randoms {

	/** Used for quantities and indexes. */
	public int integerInRange(int lowBound, int upperBound);
		
	/** Used for prices. */
	public double doubleFromMidpoint(double middle, double range);
	
}
