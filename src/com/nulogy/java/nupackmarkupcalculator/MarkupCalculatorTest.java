package com.nulogy.java.nupackmarkupcalculator;
import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Test;


public class MarkupCalculatorTest extends TestCase {
	
	private MarkupCalculator markupCalculator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		this.markupCalculator = NuPack.createMarkupCalculator();
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
	public void testNotNull() {
		TestCase.assertNotNull(this.markupCalculator);
	}

	@Test
	public void testCorrectOutput1() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("1299.99"), 3, "food"); 
		
		TestCase.assertEquals(new BigDecimal("1591.58"), cost);
	}
	
	@Test
	public void testCorrectOutput2() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("5432.00"), 1, "drugs"); 
		
		TestCase.assertEquals(new BigDecimal("6199.81"), cost);
	}
	
	@Test
	public void testCorrectOutput3() {
		BigDecimal cost = this.markupCalculator.calculateCost(
				new BigDecimal("12456.95"), 4, "books"); 
		
		TestCase.assertEquals(new BigDecimal("13707.63"), cost);
	}
	
	@Test
	public void testNullBasePriceException() {
		try {
			this.markupCalculator.calculateCost(null, 1, "books");
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	@Test
	public void testNullCategoryException() {
		try {
			this.markupCalculator.calculateCost(new BigDecimal("1299.99"), 1, null);
			TestCase.fail();
		} catch (IllegalArgumentException e) {
		}
	}

}
