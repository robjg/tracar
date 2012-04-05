package tracar.feeds.random;

public class QuantityGenerator {

	private final Randoms randoms;
	
	private int maxQuantity = 1000;
	
	private int minQuantity = 1;
	
	private int rounding = 2;
	
	public QuantityGenerator(Randoms randoms) {
		this.randoms = randoms;
	}
	
	public int generateQuantity() {
		
		int number = randoms.integerInRange(minQuantity, maxQuantity);

		number = number + minQuantity;
		
		int digits = (int) Math.log10(number) + 1;

		int digitsToZero = digits - rounding;
		
		if (digitsToZero > 0) {
			int multiplier = (int) Math.pow(10, digitsToZero);
			
			number = (number / multiplier) * multiplier;
		}

		return number;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getRounding() {
		return rounding;
	}

	public void setRounding(int rounding) {
		this.rounding = rounding;
	}
	
}
