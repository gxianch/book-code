package ch08;

import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PickStockImperativeClubbed {
	public static void findHighPriced(final Stream<String> symbols) {
		final StockInfo highPriced =
		symbols.map(StockUtil::getPrice)
		.filter(StockUtil.isPriceLessThan(5))
		.reduce(StockUtil::pickHigh)
		.get();
		System.out.println("High priced under $500 is " + highPriced);
		}
	public static void main(String[] args) {
		StockInfo highPriced = new StockInfo("", BigDecimal.ZERO);
		final Predicate<StockInfo> isPriceLessThan500 = StockUtil.isPriceLessThan(5);
		for(String symbol : SinaTickers.symbols) {
			StockInfo stockInfo = StockUtil.getPrice(symbol);
			if(isPriceLessThan500.test(stockInfo))
				highPriced = StockUtil.pickHigh(highPriced, stockInfo);
		}
		System.out.println("High priced under $5 is " + highPriced);
//		SinaTickers.symbols.stream()
//		.map(SinaFinace::getPrice)
//		.filter(StockUtil.isPriceLessThan(5))
//		.reduce(StockUtil::pickHigh)
//		.get();
		findHighPriced(SinaTickers.symbols.stream());
		findHighPriced(SinaTickers.symbols.parallelStream());
	}
}
