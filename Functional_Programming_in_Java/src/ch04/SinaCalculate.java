package ch04;

import java.math.BigDecimal;
import java.util.function.Function;

public class SinaCalculate {
	private Function<String, BigDecimal> priceFinder;
	public SinaCalculate(final Function<String, BigDecimal> aPriceFinder) {
		priceFinder = aPriceFinder;
	}
	public BigDecimal computeStockWorth(final String stockId, final int shares) {
		return	priceFinder.apply(stockId).multiply(new BigDecimal(shares));
	}
	public static void main(String[] args) {
		SinaCalculate sinaCalculate = new SinaCalculate(SinaFinace::getPrice);
		   System.out.println(String.format("100 shares of 柳钢股份 worth: $%.2f",
				   sinaCalculate.computeStockWorth("sh601003", 100)));
	}
}
