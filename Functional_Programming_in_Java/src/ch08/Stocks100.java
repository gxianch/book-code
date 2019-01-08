package ch08;


import java.math.BigDecimal;

import ch04.YahooFinance;

import static java.util.stream.Collectors.joining;

public class Stocks100 {
  public static void main(final String[] args) {
    final BigDecimal HUNDRED = new BigDecimal("100");
    System.out.println("Stocks priced over $100 are " + 
      Tickers.symbols
             .stream()
             .filter(
               symbol -> YahooFinance.getPrice(symbol).compareTo(HUNDRED) > 0)
             .sorted()
             .collect(joining(", ")));
  }
}

