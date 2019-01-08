package ch04;

import org.junit.Test;

import junit.framework.JUnit4TestAdapter;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class CalculateNAVTest {
	@Test
	public void computeStockWorth() {
		final CalculateNAV calculateNAV =
				new CalculateNAV(ticker -> new BigDecimal("6.01"));
		BigDecimal expected = new BigDecimal("6010.00");
		assertEquals(0,
				calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected),
				0.001);
	}
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(CalculateNAVTest.class));
	}
}
