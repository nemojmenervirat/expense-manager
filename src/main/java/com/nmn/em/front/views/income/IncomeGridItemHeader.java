package com.nmn.em.front.views.income;

import com.nmn.em.front.FrontUtils;

public class IncomeGridItemHeader {

	private String month;
	private double planned;
	private double actual;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getPlanned() {
		return planned;
	}

	public String getPlannedString() {
		return FrontUtils.formatCurrency(planned);
	}

	public void setPlanned(double planned) {
		this.planned = planned;
	}

	public double getActual() {
		return actual;
	}

	public String getActualString() {
		return FrontUtils.formatCurrency(actual);
	}

	public void setActual(double actual) {
		this.actual = actual;
	}

	public String getPercentageString() {
		double percentage = getPercentage();
		return "(" + (percentage > 0 ? "+" : "") + percentage + "%)";
	}

	public double getPercentage() {
		return Math.round((actual - planned) / planned * 100 * 100) / (double) 100;
	}

}
