package ch08;


import static java.util.stream.Collectors.joining;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class SinaStocks100 {
  public static void main(final String[] args) {
    final BigDecimal HUNDRED = new BigDecimal("5");
    System.out.println("Stocks priced over $5 are " + 
    		SinaTickers.symbols
             .stream()
             .filter(
               symbol -> SinaFinace.getPrice(symbol).compareTo(HUNDRED) > 0)
             .sorted()
             .collect(joining(", ")));
    
    SinaTickers.symbols.stream()
    .filter(symbol -> SinaFinace.getPrice(symbol).compareTo(HUNDRED)>0)
    .sorted()
    .collect(Collectors. toList())
    .forEach(System.out::println);
  }
}

