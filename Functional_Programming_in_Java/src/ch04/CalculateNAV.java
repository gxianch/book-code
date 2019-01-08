package ch04;

import java.math.BigDecimal;
import java.util.function.Function;

public class CalculateNAV {
	private Function<String, BigDecimal> priceFinder;
	public BigDecimal computeStockWorth(
			final String ticker, final int shares) {
		return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
	}
	public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
		priceFinder = aPriceFinder;
	}
	//... other methods that use the priceFinder ...
	 public static void main(String[] args) {
		    final CalculateNAV calculateNav = new CalculateNAV(YahooFinance::getPrice);
		    
		    System.out.println(String.format("100 shares of Google worth: $%.2f",
		      calculateNav.computeStockWorth("GOOG", 100)));
		  }
}