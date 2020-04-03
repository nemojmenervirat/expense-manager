package com.nmn.em.front;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.YearMonth;
import java.util.Locale;

public class FrontUtils {

	private static String[] months = new String[] { "januar", "februar", "mart", "april", "maj", "jun", "jul", "avgust", "septembar", "oktobar", "novembar",
			"decembar" };

	private static NumberFormat decimalFormat = new DecimalFormat("###,###,##0.00 KM");

	public static Locale getLocale() {
		return new Locale.Builder().setLanguage("sr").setRegion("BA").setVariant("POSIX").setScript("Latn").build();
	}

	public static String getMonth(int monthValue, int year) {
		return months[monthValue - 1] + " " + year;
	}

	public static String getMonth(YearMonth yearMonth) {
		return getMonth(yearMonth.getMonthValue(), yearMonth.getYear());
	}

	public static String formatCurrency(BigDecimal value) {
		return decimalFormat.format(value);
	}

	public static String formatCurrency(double value) {
		return decimalFormat.format(value);
	}

}
