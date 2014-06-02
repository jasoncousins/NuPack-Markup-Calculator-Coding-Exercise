package com.nulogy.java.nupackmarkupcalculator;
import java.math.BigDecimal;
import java.util.HashMap;

import junit.framework.TestCase;

import org.junit.Test;


public class MarkupCalculatorTest extends TestCase {
	private MarkupCalculator markupCalculator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		this.markupCalculator = NuPackFactory.createMarkupCalculator();
	}
	
	@Test
	public void testCalculatorNotNull() {
		TestCase.assertNotNull(this.markupCalculator);
	}
	
	@Test
	public void testCreationNegativeFlatMarkup() {
		try {
			new MarkupCalculator(-0.1f, 0.1f, new HashMap<String, Float>());
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCreationNegativePersonMarkup() {
		try {
			new MarkupCalculator(0.1f, -0.1f, new HashMap<String, Float>());
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCreationNullMarkupCategories() {
		try {
			new MarkupCalculator(0.1f, 0.1f, null);
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testCalculationCorrectOutput1() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("1299.99"), 3, "food"); 
		
		TestCase.assertEquals(new BigDecimal("1591.58"), cost);
	}
	
	@Test
	public void testCalculationCorrectOutput2() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("5432.00"), 1, "drugs"); 
		
		TestCase.assertEquals(new BigDecimal("6199.81"), cost);
	}
	
	@Test
	public void testCalculationCorrectOutput3() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("12456.95"), 4, "books"); 
		
		TestCase.assertEquals(new BigDecimal("13707.63"), cost);
	}
	
	@Test
	public void testCalculationNullBasePriceException() {
		try {
			this.markupCalculator.calculateCost(null, 1, "books");
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCalculationNegativeBasePriceException() {
		try {
			this.markupCalculator.calculateCost(new BigDecimal("-123.45"), 1, "books");
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testCalculationNegativePeopleException() {
		try {
			this.markupCalculator.calculateCost(new BigDecimal("123.45"), -1, "books");
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
