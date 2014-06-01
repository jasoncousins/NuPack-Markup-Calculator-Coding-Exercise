package com.nulogy.java.nupackmarkupcalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


public class MarkupCalculator {
	private final float flatMarkup;
	private final float personMarkup;
	private final Map<String, Float> categoryMarkups;
	
	public MarkupCalculator(float flatMarkup, float personMarkup, Map<String, Float> categoryMarkups) {
		if (categoryMarkups == null) {
			throw new IllegalArgumentException("categoryMarkups cannot be null");
		}
		
		this.flatMarkup = flatMarkup;
		this.personMarkup = personMarkup;
		this.categoryMarkups = categoryMarkups;
	}
	
	public BigDecimal calculateCost(BigDecimal basePrice, int people, String category) {
		if (basePrice == null) {
			throw new IllegalArgumentException("basePrice cannot be null");
		}
		if (category == null) {
			throw new IllegalArgumentException("category cannot be null");
		}
		
		basePrice = basePrice.add(this.calculateMarkup(basePrice, this.flatMarkup));
		
		BigDecimal total = basePrice;
		
		total = total.add(this.calculateMarkup(basePrice, people * this.personMarkup));
		
		if (this.categoryMarkups.containsKey(category)) {
			total = total.add(this.calculateMarkup(basePrice, this.categoryMarkups.get(category)));
		}

		return total.setScale(2, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calculateMarkup(BigDecimal base, float percentage) {
		return base.multiply(new BigDecimal(percentage));
	}

}
