package com.nulogy.java.nupackmarkupcalculator;
import java.util.HashMap;
import java.util.Map;


/**
 * Factory that creates the NuPack markup calculator.
 */
public final class NuPackFactory {
	/** The flat markup to apply to the base price of projects. */
	private static final float FLAT_MARKUP = 0.05f;
	
	/** The markup per person to apply. */
	private static final float PERSON_MARKUP = 0.012f;
	
	/**
	 * @return A NuPack markup calculator.
	 */
	public static MarkupCalculator createMarkupCalculator() {
		Map<String, Float> categoryMarkups = new HashMap<String, Float>();
		categoryMarkups.put("drugs", 0.075f);
		categoryMarkups.put("food", 0.13f);
		categoryMarkups.put("electronics", 0.02f);
		
		return new MarkupCalculator(FLAT_MARKUP, PERSON_MARKUP, categoryMarkups);
	}
}
