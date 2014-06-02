package com.nulogy.java.nupackmarkupcalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;


/**
 * Calculates the total cost of a project.
 */
public class MarkupCalculator {
	/** The flat markup applied to the base price. */
	private final float flatMarkup;
	
	/** The markup applied to the base price + markup per person working on the project. */
	private final float personMarkup;
	
	/** Maps a string category to a markup percentage to be applied for that category. */
	private final Map<String, Float> categoryMarkups;
	
	/**
	 * Create a new MarkupCaculator.
	 * @param flatMarkup The flat markup applied to the base price.
	 * @param personMarkup The markup applied per person working on the project. 
	 * @param categoryMarkups A mapping between categories and their associated markups.
	 */
	public MarkupCalculator(float flatMarkup, float personMarkup, Map<String, Float> categoryMarkups) {
		if (categoryMarkups == null) {
			throw new IllegalArgumentException("categoryMarkups cannot be null");
		}
		
		this.flatMarkup = flatMarkup;
		this.personMarkup = personMarkup;
		this.categoryMarkups = categoryMarkups;
	}
	
	/**
	 * Calculate the cost of a project.
	 * @param basePrice The base price of the project.
	 * @param people The number of people working on the job.
	 * @param category The category of the products involved.
	 * @return
	 */
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
	
	/**
	 * Helper to calculate a percentage markup.
	 * @param base The base value.
	 * @param percentage The percent markup.
	 * @return The base value times the percent markup.
	 */
	private BigDecimal calculateMarkup(BigDecimal base, float percentage) {
		return base.multiply(new BigDecimal(percentage));
	}

}
